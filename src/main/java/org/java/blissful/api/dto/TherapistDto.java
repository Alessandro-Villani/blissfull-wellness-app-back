package org.java.blissful.api.dto;

import java.util.List;



public class TherapistDto {
	
	
	private String therapistName;
	
	private String description;

	private long userId;
	
	private long[] massages;
	
	public TherapistDto() {}

	public TherapistDto(String therapistName, String description, long userId) {
		
		setTherapistName(therapistName);
		setDescription(description);
		setUserId(userId);
		
	}
	
	public TherapistDto(String therapistName, String description, long userId, long[] massages) {
		
		this(therapistName, description, userId);
		setMassages(massages);
		
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

	public long[] getMassages() {
		return massages;
	}

	public void setMassages(long[] massages) {
		this.massages = massages;
	}	
	
	
}
