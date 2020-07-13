package com.borjas.springboot.backend.chat.services;

import java.util.List;

import com.borjas.springboot.backend.chat.models.documents.Mensaje;

public interface ChatService {
	
	public List<Mensaje> obtenerUltimos10Mensajes();
	public Mensaje guardar(Mensaje mensaje);
}
