package com.borjas.springboot.backend.chat.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borjas.springboot.backend.chat.models.documents.Mensaje;
import com.borjas.springboot.backend.chat.repositorys.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public List<Mensaje> obtenerUltimos10Mensajes() {
		return chatRepository.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		return chatRepository.save(mensaje);
	}

}
