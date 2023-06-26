package com.bolsadeideas.springboot.backend.chat.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bolsadeideas.springboot.backend.chat.models.documents.Cliente;

public interface ChatRepository extends MongoRepository<Cliente, String>{
	
    public List<Cliente> findFirst10ByOrderByFechaDesc();
}
