package com.borjas.springboot.backend.chat.repositorys;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.borjas.springboot.backend.chat.models.documents.Mensaje;

public interface ChatRepository extends MongoRepository<Mensaje, String>{
	
	public List<Mensaje> findFirst10ByOrderByFechaDesc();

}
