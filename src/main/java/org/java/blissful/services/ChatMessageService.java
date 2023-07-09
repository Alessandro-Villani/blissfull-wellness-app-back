package org.java.blissful.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.pojo.ChatMessage;
import org.java.blissful.repo.ChatMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {
	
	@Autowired
	private ChatMessageRepo chatMessageRepo;
	
	public List<ChatMessage> findByChatId(long id){
		
		return chatMessageRepo.findByChatId(id);
		
	}
	
	public Optional<ChatMessage> findById(long id){
		
		return chatMessageRepo.findById(id);
		
	}
	
	public ChatMessage save(ChatMessage chatMessage) {
		
		return chatMessageRepo.save(chatMessage);
		
	}
	
	public void delete(ChatMessage chatMessage) {
		
		chatMessageRepo.delete(chatMessage);
		
	}

}
