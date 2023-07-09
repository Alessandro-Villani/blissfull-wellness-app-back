package org.java.blissful.api.controller;

import java.time.LocalDateTime;

import org.java.blissful.api.dto.ChatMessageDto;
import org.java.blissful.pojo.Chat;
import org.java.blissful.pojo.ChatMessage;
import org.java.blissful.services.ChatMessageService;
import org.java.blissful.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ChatMessageController {
	
	@Autowired
	private ChatMessageService chatMessageService;
	
	@Autowired
	private ChatService chatService;
	
	@PostMapping("chatmessages/store")
	public ResponseEntity<ChatMessage> storeChatMessage(@RequestBody ChatMessageDto chatMessageDto){
		
		Chat chat = chatService.findById(chatMessageDto.getChatId()).get();
		
		LocalDateTime dateTime = LocalDateTime.now();
		
		ChatMessage chatMessage = new ChatMessage(dateTime, chatMessageDto.getSender(), chatMessageDto.getMessage(), chat);
		
		chat.setLastMessageDateTime(dateTime);
		
		chatMessageService.save(chatMessage);		
		
		return new ResponseEntity<>(chatMessage, HttpStatus.OK);
		
	}
	
	@PatchMapping("chatmessages/{id}/seen")
	public ResponseEntity<ChatMessage> setSeen(@PathVariable long id){
		
		ChatMessage chatMessage = chatMessageService.findById(id).get();
		
		chatMessage.setSeen(true);
		
		chatMessageService.save(chatMessage);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
