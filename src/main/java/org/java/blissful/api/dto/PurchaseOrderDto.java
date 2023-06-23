package org.java.blissful.api.dto;

public class PurchaseOrderDto {
	
	private long productId;
	
	private long userId;
	
	private boolean delivery;
	
	private String dateOfPickup;
	
	private String address;
	
	private int quantity;
	
	public PurchaseOrderDto() {}
	
	public PurchaseOrderDto(long productId, long userId, boolean delivery, int quantity, String addressOrDate) {
		
		setProductId(productId);
		setUserId(userId);
		setDelivery(delivery);
		setQuantity(quantity);
		
		if(delivery) {
			setAddress(addressOrDate);
		}
		else setDateOfPickup(addressOrDate);
		
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	

}
