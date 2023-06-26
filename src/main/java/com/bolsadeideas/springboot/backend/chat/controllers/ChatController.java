package com.bolsadeideas.springboot.backend.chat.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.bolsadeideas.springboot.backend.chat.models.documents.Cliente;


@Controller
public class ChatController {
	
	private String[] colores = {"red", "green", "blue", "magenta", "purple", "orange"};
	public Integer cantidad=0; 
	
	public List<Cliente> clientes =  new ArrayList<Cliente>(); 
	public Integer turno=1; 
	 
	@Autowired   
	private SimpMessagingTemplate webSocket; 
	
	@MessageMapping("/mensaje") 
	@SendTo("/chat/mensaje")
	public Cliente recibeMensaje(Cliente cliente) {
		cliente.setFecha(new Date().getTime()); 
		cliente.setNum_cliente(cantidad+1);  
		
		if(cliente.getTipo().equals("NUEVO_USUARIO")) {
			cliente.setColor(colores[new Random().nextInt(colores.length)]);
			cliente.setTexto("nuevo usuario");
			cliente.setPuntuacion(0);
			this.cantidad+=1; 
			cliente.setCantidad(this.cantidad);  
			this.clientes.add(cliente); 
		} else {
			System.out.println("XDD");
		} 
		
		return cliente;
	} 
  
	@MessageMapping("/disconnect") 
	@SendTo("/chat/cantidad")
	public String desconectar() {
		this.cantidad-=1; 
		System.out.println("Entra "+cantidad); 
		return this.cantidad+"";   
	} 
	

	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) {
		return username.concat(" está escribiendo ...");
	}
	
	@MessageMapping("/girar") 
	@SendTo("/chat/resultados")
	public Cliente resultadosRuleta(Cliente cliente ){
		//System.out.println(webSocket.get);
		if(cliente.getNum_cliente()==turno) {
			
		}  
		cliente.setTexto("");
		 Cliente obtenido = clientes.stream()  
                .filter(e -> e.getNum_cliente() == cliente.getNum_cliente())
                .collect(Collectors.toList()).get(0);
		int num1 = generarNumeroAleatorio();
        int num2 = generarNumeroAleatorio();
        int num3 = generarNumeroAleatorio();
        
        String proceso = "Tiro de "+cliente.getUsername() + " : " + num1 + ", " + num2 + ", " + num3;
        cliente.setTiro(proceso); 
        obtenido.setTiro(num1+""+num2+""+num3);
        Integer punt = obtenido.getPuntuacion()+1;
		obtenido.setPuntuacion(punt); 
		if(this.turno==3) {
			System.out.println(clientes);
			Integer id_mayor=0; 
			int maxValue = 0;
			for(int i = 0; i < clientes.size(); i++) {
				if (Integer.parseInt(clientes.get(i).getTiro()) > maxValue) {
	                maxValue = Integer.parseInt(clientes.get(i).getTiro()); 
	                id_mayor = i;
	            }
			}
			
			 
			List<Cliente>ganador = clientes.stream()  
	                .filter(e -> e.getNum_cliente() >=3)
	                .collect(Collectors.toList());
			if(ganador.size()>0) {
				cliente.setTexto("El jugador "+(id_mayor+1)+ " "+ganador.get(0).getUsername()+" Ha ganado el juego"); 
				obtenido.setGanador(true);  
			}else {
				cliente.setTexto("Ha gando el jugador "+(id_mayor+1)); 
			}
			
			
			turno=1; 
		}else {
			turno+=1;
		}
		cliente.setTurno(turno); 
	 
         
        return cliente; 
	}
	
	public int generarNumeroAleatorio() {
        Random rand = new Random();
        return rand.nextInt(3) + 1;
    }
	
	/*@MessageMapping("/historial")
	public void historial(String clienteId){
		webSocket.convertAndSend("/chat/historial/" + clienteId, chatService.obtenerUltimos10Mensajes());
	}*/

}
