package org.java.blissful.api.dto;

public class TherapistWithBookingsHoursDto {
	
	private String therapistName;
	
	private int reviewsAverage;
	
	private int massageHours;
	
	public TherapistWithBookingsHoursDto(String therapistName, int reviewsAverage, int massageHours) {
		
		setTherapistName(therapistName);
		setReviewsAverage(reviewsAverage);
		setMassageHours(massageHours);
		
	}

	public String getTherapistName() {
		return therapistName;
	}

	public void setTherapistName(String therapistName) {
		this.therapistName = therapistName;
	}

	public int getReviewsAverage() {
		return reviewsAverage;
	}

	public void setReviewsAverage(int reviewsAverage) {
		this.reviewsAverage = reviewsAverage;
	}

	public int getMassageHours() {
		return massageHours;
	}

	public void setMassageHours(int massageHours) {
		this.massageHours = massageHours;
	}
	

}
