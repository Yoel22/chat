package com.borjas.springboot.backend.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat-websocket")
		 		.setAllowedOrigins("http://localhost:4200")
		 		.withSockJS();
		
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		// Prefijo para el nombre de los eventos o canales "/chat/: 
		registry.enableSimpleBroker("/chat/");
		
		// Prefijo para el destino donde vamos a publicar "/app":
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	
	
}
