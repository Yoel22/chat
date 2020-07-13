package com.borjas.springboot.backend.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.borjas.springboot.backend.chat.models.documents.Mensaje;
import com.borjas.springboot.backend.chat.services.ChatService;

@Controller
public class ChatController {
	
	private String[] colores = {"red", "gree", "blue", "magenta", "purple", "orange"};
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	@MessageMapping("/mensaje") // Recibir mensaje de los clientes (cliente realiza el envio del mensaje a broker)
	@SendTo("/chat/mensaje")    //Notificar a los demás clientes que están suscriptos a este evento
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto("nuevo usuario");
		}else {
			chatService.guardar(mensaje);
		}

		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) {
		return username.concat(" está escribiendo...");
	}

	@MessageMapping("/historial")
	public void historial(String clienteId){
		 webSocket.convertAndSend("/chat/historial/" + clienteId, chatService.obtenerUltimos10Mensajes());
	}
}
