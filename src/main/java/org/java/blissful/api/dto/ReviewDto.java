package org.java.blissful.api.dto;

public class ReviewDto {
	
	private int grade;
	
	private String review;
	
	private long author;
	
	private long therapist;
	
	public ReviewDto() {}

	public ReviewDto(int grade, String review, long author, long therapist) {
		
		setGrade(grade);
		setReview(review);
		setAuthor(author);
		setTherapist(therapist);
		
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
	
	

}
