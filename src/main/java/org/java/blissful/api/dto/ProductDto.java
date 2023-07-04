package org.java.blissful.api.dto;

public class ProductDto {

	private long id;
	
	private String name;
	
	private String description;
	
	private double price;
	
	private int stockQuantity;
	
	
	public ProductDto() {}

	public ProductDto(long id, int stockQuantity) {
		
		setId(id);
		setStockQuantity(stockQuantity);
		
	}
	
	public ProductDto(String name, String description,  double price, int stockQuantity) {
		
		setName(name);
		setPrice(price);
		setStockQuantity(stockQuantity);
		
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	
}
