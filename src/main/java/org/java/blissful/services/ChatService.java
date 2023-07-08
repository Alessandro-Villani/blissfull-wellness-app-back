package org.java.blissful.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.pojo.Chat;
import org.java.blissful.repo.ChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
	
	@Autowired
	private ChatRepo chatRepo;
	
	public List<Chat> findByUserId(long id){
		
		return chatRepo.findByUserIdOrderByLastMessageDateTimeDesc(id);
		
	}
	
	public List<Chat> findByTherapistId(long id){
		
		return chatRepo.findByTherapistIdOrderByLastMessageDateTimeDesc(id);
		
	}
	
	public Chat save(Chat chat){
		
		return chatRepo.save(chat);
		
	}
	
	public void delete(Chat chat) {
		
		chatRepo.delete(chat);
		
	}

}
