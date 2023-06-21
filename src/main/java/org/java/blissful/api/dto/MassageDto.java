package org.java.blissful.api.dto;

public class MassageDto {

	private String name;
	
	private String description;
	
	private double pricePerHour;
	
	private String color;
	
	public MassageDto() {}
	public MassageDto(String name, String description, double pricePerHour, String color) {
		setName(name);
		setDescription(description);
		setPricePerHour(pricePerHour);
		setColor(color);
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
	
	
	
}
