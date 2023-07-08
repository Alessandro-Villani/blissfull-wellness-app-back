package org.java.blissful.repo;

import java.util.List;

import org.java.blissful.pojo.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long>{
	
	public List<ChatMessage> findByChatId(long id);

}
