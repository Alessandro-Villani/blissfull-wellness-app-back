package org.java.blissful.pojo;

import java.util.Arrays;
import java.util.List;

import org.java.blissful.auth.pojo.Therapist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Massage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@NotNull
	private String name;
	
	@NotNull
	@NotBlank
	@Column(columnDefinition = "text")
	private String description;
	
	@NotNull
	@Positive
	private double pricePerHour;
	
	@ManyToMany(mappedBy = "massages", cascade = CascadeType.DETACH)
	@JsonBackReference
	private List<Therapist> therapists;
	
	private String color;
	
	public Massage() {}
	
	public Massage(String name, String description, double pricePerHour) {
		
		setName(name);
		setDescription(description);
		setPricePerHour(pricePerHour);
		
	}
	
	public Massage(String name, String description, double pricePerHour, String color) {
		
		this(name, description, pricePerHour);
		setColor(color);
		
	}
	
	public Massage(String name, String description, double pricePerHour, String color, Therapist...therapists) {
		
		this(name, description, pricePerHour, color);
		setTherapist(therapists);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
	
	
	public List<Therapist> getTherapists() {
		return therapists;
	}

	@JsonSetter
	public void setTherapists(List<Therapist> therapists) {
		this.therapists = therapists;
	}
	
	public void setTherapist(Therapist[] therapists) {
		
		setTherapists(Arrays.asList(therapists));
		
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "[" + getId() + "], " + getName();
	}
}
