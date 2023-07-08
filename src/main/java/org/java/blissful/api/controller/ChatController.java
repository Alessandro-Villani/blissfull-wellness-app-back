package org.java.blissful.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.java.blissful.api.dto.ChatDto;
import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.pojo.User;
import org.java.blissful.auth.services.TherapistService;
import org.java.blissful.auth.services.UserService;
import org.java.blissful.pojo.Chat;
import org.java.blissful.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TherapistService therapistService;
	
	@GetMapping("chats/therapist/{id}")
	public ResponseEntity<List<Chat>> getTherapistChats(@PathVariable long id){
		
		List<Chat> therapistChats = chatService.findByTherapistId(id);
		
		return new ResponseEntity<>(therapistChats, HttpStatus.OK);
		
	}
	
	@GetMapping("chats/user/{id}")
	public ResponseEntity<List<Chat>> getUserChats(@PathVariable long id){
		
		List<Chat> userChats = chatService.findByUserId(id);
		
		return new ResponseEntity<>(userChats, HttpStatus.OK);
		
	}
	
	@PostMapping("chats/store")
	public ResponseEntity<Chat> storeChat(@RequestBody ChatDto chatDto){
		
		List<Chat> chats = chatService.findByUserId(chatDto.getUserId());
		
		List<Chat> existentChats = chats.stream().filter(chat -> chat.getTherapist().getId() == chatDto.getTherapistId()).toList();
		
		if (!existentChats.isEmpty()) {
			
			Chat existentChat = existentChats.get(0);
			
			return new ResponseEntity<>(existentChat, HttpStatus.OK);
			
		}
		
		User user = userService.findById(chatDto.getUserId()).get();
		
		Therapist therapist = therapistService.findById(chatDto.getTherapistId()).get(); 
		
		Chat chat = new Chat(user, therapist, LocalDateTime.now());
		
		chatService.save(chat);
		
		return new ResponseEntity<>(chat, HttpStatus.OK);
		
		
	}
	

}
