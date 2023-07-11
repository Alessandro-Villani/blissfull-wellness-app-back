package org.java.blissful.api.dto;

public class PurchaseOrderDto {
	
	private long productId;
	
	private long userId;
	
	private boolean delivery;
	
	private String dateOfPickup;
	
	private String address;
	
	private double latitude;
	
	private double longitude;
	
	private int quantity;
	
	public PurchaseOrderDto() {}
	
	public PurchaseOrderDto(long productId, long userId, boolean delivery, int quantity, String dateOfPickup) {
		
		setProductId(productId);
		setUserId(userId);
		setDelivery(delivery);
		setQuantity(quantity);
		setDateOfPickup(dateOfPickup);
		
	}
	
	public PurchaseOrderDto(long productId, long userId, boolean delivery, int quantity, String address, double latitude, double longitude) {
		
		setProductId(productId);
		setUserId(userId);
		setDelivery(delivery);
		setQuantity(quantity);
		setAddress(address);
		setLatitude(latitude);
		setLongitude(longitude);
		
	}
	

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isDelivery() {
		return delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

	public String getDateOfPickup() {
		return dateOfPickup;
	}

	public void setDateOfPickup(String dateOfPickup) {
		this.dateOfPickup = dateOfPickup;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	

}
