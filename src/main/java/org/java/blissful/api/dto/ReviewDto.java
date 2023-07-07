package org.java.blissful.api.dto;

public class ReviewDto {
	
	private int grade;
	
	private String review;
	
	private long author;
	
	private long therapist;
	
	private String massageName;
	
	private int duration;
	
	private String date;
	
	public ReviewDto() {}

	public ReviewDto(int grade, String review, long author, long therapist, String massageName, int duration, String date) {
		
		setGrade(grade);
		setReview(review);
		setAuthor(author);
		setTherapist(therapist);
		setMassageName(massageName);
		setDuration(duration);
		setDate(date);
		
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public long getAuthor() {
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public long getTherapist() {
		return therapist;
	}

	public void setTherapist(long therapist) {
		this.therapist = therapist;
	}

	public String getMassageName() {
		return massageName;
	}

	public void setMassageName(String massageName) {
		this.massageName = massageName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

}
