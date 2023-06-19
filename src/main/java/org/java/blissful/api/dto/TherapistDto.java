package org.java.blissful.api.dto;

public class TherapistDto {
	
	
	private String therapistName;
	
	private String description;

	private long userId;
	
	public TherapistDto() {}

	public TherapistDto(String therapistName, String description, long userId) {
		
		setTherapistName(therapistName);
		setDescription(description);
		setUserId(userId);
		
	}

	public String getTherapistName() {
		return therapistName;
	}

	public void setTherapistName(String therapistName) {
		this.therapistName = therapistName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
