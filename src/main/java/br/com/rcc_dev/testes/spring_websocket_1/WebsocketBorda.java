package br.com.rcc_dev.testes.spring_websocket_1;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

/**
 * Essa classe irá criar rotas para responder a requisições de Websocket.
 * Porém, para enviar mensagens para canais Websocket/Stomp é necessário 
 * usar o "SimpMessagingTemplate" (Veja na classe "App").
 */
@Controller
@Slf4j
public class WebsocketBorda {

  @MessageMapping("/mensagem")
  @SendTo("/topic/mensagem")
  public String mensagem(String msg) {
    log.info("Mensagem: {}", msg);
    return "Mensagem -> " + msg;
  }

}