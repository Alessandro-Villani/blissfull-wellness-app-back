package org.java.blissful.pojo;

import java.time.LocalDateTime;
import java.util.List;

import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonManagedReference
	private User user;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonManagedReference
	private Therapist therapist;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "chat")
	@JsonManagedReference
	private List<ChatMessage> messages;
	
	private LocalDateTime lastMessageDateTime;
	
	public Chat() {}
	
	public Chat(User user, Therapist therapist, LocalDateTime now) {
		
		setUser(user);
		setTherapist(therapist);
		setLastMessageDateTime(now);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Therapist getTherapist() {
		return therapist;
	}

	public void setTherapist(Therapist therapist) {
		this.therapist = therapist;
	}

	public List<ChatMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ChatMessage> messages) {
		this.messages = messages;
	}
	
	public void addMessage(ChatMessage message) {
		
		this.messages.add(message);
		
	}

	public LocalDateTime getLastMessageDateTime() {
		return lastMessageDateTime;
	}

	public void setLastMessageDateTime(LocalDateTime lastMessageDateTime) {
		this.lastMessageDateTime = lastMessageDateTime;
	}
	
	
}
