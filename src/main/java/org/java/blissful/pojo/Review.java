package org.java.blissful.pojo;

import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Min(1)
	@Max(5)
	private int grade;
	
	@NotNull
	@NotBlank
	@Column(columnDefinition = "text")
	private String review;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonBackReference
	private Therapist therapist;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonManagedReference
	private User author;
	
	private String massageName;
	
	private int massageDuration;
	
	private String massageDate;
	
	public Review() {}
	
	public Review(int grade, String review, Therapist therapist, User author, String massageName, int massageDuration, String massageDate) {
		
		setGrade(grade);
		setReview(review);
		setTherapist(therapist);
		setAuthor(author);
		setMassageName(massageName);
		setMassageDuration(massageDuration);
		setMassageDate(massageDate);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Therapist getTherapist() {
		return therapist;
	}

	public void setTherapist(Therapist therapist) {
		this.therapist = therapist;
	}
	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getMassageName() {
		return massageName;
	}

	public void setMassageName(String massageName) {
		this.massageName = massageName;
	}

	public int getMassageDuration() {
		return massageDuration;
	}

	public void setMassageDuration(int massageDuration) {
		this.massageDuration = massageDuration;
	}

	public String getMassageDate() {
		return massageDate;
	}

	public void setMassageDate(String massageDate) {
		this.massageDate = massageDate;
	}

	@Override
	public String toString() {
		
		return "[" + getId() + "], " + getGrade() + " " + getTherapist().getUser().getUsername() + "/n" + getReview();
		
	}
	
	
	
}
