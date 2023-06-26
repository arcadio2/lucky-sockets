package com.bolsadeideas.springboot.backend.chat.models.documents;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


public class Cliente implements Serializable {
	

	private String id;

	private String texto;
	private Long fecha;
	private String username;
	private String tipo;
	private Integer num_cliente; 
	private String color;
	private Integer cantidad; 
	
	private String tiro; 
	
	private Integer puntuacion; 
	
	private Integer turno; 
	
	private boolean ganador; 
	
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getNum_cliente() {
		return num_cliente;
	}

	public void setNum_cliente(Integer num_cliente) {
		this.num_cliente = num_cliente;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getTiro() {
		return tiro;
	}

	public void setTiro(String tiro) {
		this.tiro = tiro;
	}

	public Integer getTurno() {
		return turno;
	}

	public void setTurno(Integer turno) {
		this.turno = turno;
	}

	public boolean isGanador() {
		return ganador;
	}

	public void setGanador(boolean ganador) {
		this.ganador = ganador;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", texto=" + texto + ", fecha=" + fecha + ", username=" + username + ", tipo="
				+ tipo + ", num_cliente=" + num_cliente + ", color=" + color + ", cantidad=" + cantidad + ", tiro="
				+ tiro + ", puntuacion=" + puntuacion + ", turno=" + turno + ", ganador=" + ganador + "]";
	}

 
	

}
