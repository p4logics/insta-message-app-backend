package com.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.model.Message;

@RestController
public class ChatController {

	@Autowired
	SimpMessagingTemplate template;

	@PostMapping("/send")
	public ResponseEntity<Void> sendMessage(@RequestBody Message textMessageDTO) {
		template.convertAndSend("/topic/message", textMessageDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@MessageMapping("/sendMessage")
	public void receiveMessage(@Payload Message textMessageDTO) {
		// receive message from client
	}

	@SendTo("/topic/message")
	public Message broadcastMessage(@Payload Message textMessageDTO) {
		return textMessageDTO;
	}

}
