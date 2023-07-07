package org.java.blissful.pojo;

import java.time.LocalDate;

import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private LocalDate date;
	
	@NotNull
	private int startHour;
	
	@NotNull
	private int endHour;
	
	@NotNull
	private int totalHours;
	
	private boolean homeService;
	
	private String address;
	
	private double latitude;
	
	private double longitude;
	
	@NotNull
	private double price;
	
	private boolean accepted;
	
	private boolean rejected;
	
	@Column(columnDefinition = "text")
	private String rejectionReason;
	
	private boolean completed;
	
	private boolean reviewed;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonManagedReference
	private User user;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonManagedReference
	private Therapist therapist;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonManagedReference
	private Massage massage;
	
	public Booking() {}
	
	public Booking(LocalDate date, int startHour, int endHour, int totalHour, boolean homeService, double price, User user, Therapist therapist, Massage massage) {
		
		setDate(date);
		setStartHour(startHour);
		setEndHour(endHour);
		setTotalHours(totalHour);
		setHomeService(homeService);
		setPrice(price);
		setAccepted(false);
		setRejected(false);
		setCompleted(false);
		setReviewed(false);
		setUser(user);
		setTherapist(therapist);
		setMassage(massage);
		
	}
	
	public Booking(LocalDate date, int startHour, int endHour, int totalHour, boolean homeService, double price, User user, Therapist therapist, Massage massage, String address, double latitude, double longitude) {
		
		this(date, startHour, endHour, totalHour, homeService, price, user, therapist, massage);
		setAccepted(false);
		setRejected(false);
		setCompleted(false);
		setReviewed(false);
		setAddress(address);
		setLatitude(latitude);
		setLongitude(longitude);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public boolean isHomeService() {
		return homeService;
	}

	public void setHomeService(boolean homeService) {
		this.homeService = homeService;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public String getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
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

	public Massage getMassage() {
		return massage;
	}

	public void setMassage(Massage massage) {
		this.massage = massage;
	}

	

}
