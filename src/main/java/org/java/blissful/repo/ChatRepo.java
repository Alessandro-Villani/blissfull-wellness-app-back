package org.java.blissful.repo;

import java.util.List;

import org.java.blissful.pojo.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<Chat, Long>{
	
	public List<Chat> findByUserIdOrderByLastMessageDateTimeDesc(long id);
	
	public List<Chat> findByTherapistIdOrderByLastMessageDateTimeDesc(long id);

}
