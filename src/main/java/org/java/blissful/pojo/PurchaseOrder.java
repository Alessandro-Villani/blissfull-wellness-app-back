package org.java.blissful.pojo;

import java.time.LocalDate;

import org.java.blissful.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonIgnoreProperties("product")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonIgnoreProperties("user")
	private User user;
	
	private boolean delivery;
	
	private LocalDate dateOfPickUp;
	
	private String address;
	
	private int quantity;
	
	private boolean accepted;
	
	private boolean seen;
	
	private boolean delivered;
	
	public PurchaseOrder() {}

	public PurchaseOrder(Product product, User user, boolean delivery, int quantity, String address) {
		
		setProduct(product);
		setUser(user);
		setDelivery(delivery);
		setQuantity(quantity);
		setAccepted(false);
		setSeen(false);
		setDelivered(false);
		setAddress(address);
		
	}
	
	public PurchaseOrder(Product product, User user, boolean delivery, int quantity, LocalDate dateOfPickUp) {
		
		setProduct(product);
		setUser(user);
		setDelivery(delivery);
		setQuantity(quantity);
		setAccepted(false);
		setSeen(false);
		setDelivered(false);
		setDateOfPickUp(dateOfPickUp);
		
	}
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isDelivery() {
		return delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

	public LocalDate getDateOfPickUp() {
		return dateOfPickUp;
	}

	public void setDateOfPickUp(LocalDate dateOfPickUp) {
		this.dateOfPickUp = dateOfPickUp;
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

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	

}
