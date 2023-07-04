package org.java.blissful.auth.pojo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.java.blissful.pojo.Booking;
import org.java.blissful.pojo.Massage;
import org.java.blissful.pojo.Review;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Therapist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String therapistName;
	
	private LocalDate hiringDate;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private User user;
	
	@ManyToMany(cascade = CascadeType.DETACH)
	@JsonManagedReference
	@JsonIgnoreProperties("bookings")
	private List<Massage> massages;
	
	@OneToMany(mappedBy = "therapist", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Review> reviews;
	
	@OneToMany(mappedBy = "therapist", cascade = CascadeType.REMOVE)
	@JsonBackReference
	private List<Booking> bookings;
	
	public Therapist() {}
	
	public Therapist(User user, String therapistName, LocalDate hiringDate, String description) {
		
		setUser(user);
		setTherapistName(therapistName);
		setHiringDate(hiringDate);
		setDescription(description);
		
	}
	
	public Therapist(User user, String therapistName, LocalDate hiringDate, String description, Massage... massages) {
		
		this(user, therapistName, hiringDate, description);
		setMassage(massages);
		
	}
	
	public Therapist(User user, String therapistName, LocalDate hiringDate, String description, List<Massage> massages) {
		
		this(user, therapistName, hiringDate, description);
		setMassages(massages);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTherapistName() {
		return therapistName;
	}

	public void setTherapistName(String therapistName) {
		this.therapistName = therapistName;
	}

	public LocalDate getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(LocalDate hiringDate) {
		this.hiringDate = hiringDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Massage> getMassages() {
		return massages;
	}

	@JsonSetter
	public void setMassages(List<Massage> massages) {
		this.massages = massages;
	}
	
	public void setMassage(Massage[] massages) {
		setMassages(Arrays.asList(massages));
	}
	
	public void addMassage(Massage massage) {
		
		getMassages().add(massage);
		
	}
	
	public void removeMassage(Massage massage) {
		
		getMassages().remove(massage);
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public int getReviewAverage() {
		
		if(getReviews() != null && !getReviews().isEmpty()) {
			
			int average = 0;
			
			for(Review review : getReviews()) {
				
				average += review.getGrade();
				
			}
			
			average = average / getReviews().size();
			
			return average;
			
		}
		
		return 0;
		
		
	}
	
}
