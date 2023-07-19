package org.java.blissful.api.dto;

public class MassageDto {

	private String name;
	
	private String description;
	
	private double pricePerHour;
	
	private String color;
	
	private long[] therapists;
	
	public MassageDto() {}
	public MassageDto(String name, String description, double pricePerHour, String color) {
		setName(name);
		setDescription(description);
		setPricePerHour(pricePerHour);
		setColor(color);
	}
	
	public MassageDto(String name, String description, double pricePerHour, String color, long[] therapists) {
		this(name, description, pricePerHour, color);
		setTherapists(therapists);
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public long[] getTherapists() {
		return therapists;
	}
	public void setTherapists(long[] therapists) {
		this.therapists = therapists;
	}	
	
}
