package org.java.blissful.api.dto;

public class ChatMessageDto {
	
	private String sender;
	
	private String message;
	
	private long chatId;
	
	public ChatMessageDto() {}
	
	public ChatMessageDto(String sender, String message, long chatId) {
		
		setSender(sender);
		setMessage(message);
		setChatId(chatId);
		
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}
	
	

}
