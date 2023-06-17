package org.java.blissful.api.dto;

public class MassageDto {

	private String name;
	
	private String description;
	
	private double pricePerHour;
	
	public MassageDto() {}
	public MassageDto(String name, String description, double pricePerHour) {
		setName(name);
		setDescription(description);
		setPrice(pricePerHour);
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
	public void setPrice(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}
	
}
