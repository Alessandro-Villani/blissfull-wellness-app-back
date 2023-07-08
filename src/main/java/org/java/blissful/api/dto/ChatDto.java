package org.java.blissful.api.dto;

public class ChatDto {
	
	private long userId;
	
	private long therapistId;
	
	public ChatDto() {}

	public ChatDto(long userId, long therapistId, String role) {
		
		setUserId(userId);
		setTherapistId(therapistId);
		
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTherapistId() {
		return therapistId;
	}

	public void setTherapistId(long therapistId) {
		this.therapistId = therapistId;
	}
	
}
