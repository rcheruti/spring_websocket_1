package br.com.rcc_dev.testes.spring_websocket_1;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableConfigurationProperties
@EnableWebSocketMessageBroker
@EnableScheduling
@Slf4j
public class App implements WebSocketMessageBrokerConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	// ----------------------------------------

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
			config.enableSimpleBroker("/topic"); // clientes poderão se inscrever com "/topic/{ canal }"
			config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
			registry.addEndpoint("/websocket").withSockJS();
	}

	// ----------------------------------------

	@Autowired
	private SimpMessagingTemplate simp; // Serviço para enviar mensagens pelo Websocket

	@Scheduled(fixedRate = 2000)
	public void enviarHorario() {
		simp.convertAndSend( "/topic/mensagem", LocalDateTime.now().toString() );
	}
		
}

