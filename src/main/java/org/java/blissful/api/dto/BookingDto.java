package org.java.blissful.api.dto;

public class BookingDto {
	
	private String date;
	
	private int startHour;
	
	private int endHour;
	
	private int totalHours;
	
	private boolean homeService;
	
	private String address;
	
	private double latitude;
	
	private double longitude;
	
	private double price;
	
	private long userId;
	
	private long therapistId;
	
	private long massageId;
	
	public BookingDto() {}
	
	public BookingDto(String date, int startHour, int endHour, int totalHours, boolean homeService, double price, long userId, long therapistId, long massageId) {
		
		setDate(date);
		setStartHour(startHour);
		setEndHour(endHour);
		setTotalHours(totalHours);
		setHomeService(homeService);
		setPrice(price);
		setUserId(userId);
		setTherapistId(therapistId);
		setMassageId(massageId);
		
	}
	
	public BookingDto(String date, int startHour, int endHour, int totalHours, boolean homeService, double price, long userId, long therapistId, long massageId, String address, double latitude, double longitude) {
		
		this(date, startHour, endHour, totalHours, homeService, price, userId, therapistId, massageId);
		setAddress(address);
		setLatitude(latitude);
		setLongitude(longitude);
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTherapistId() {
		return therapistId;
	}

	public void setTherapistId(long therapistId) {
		this.therapistId = therapistId;
	}

	public long getMassageId() {
		return massageId;
	}

	public void setMassageId(long massageId) {
		this.massageId = massageId;
	}
	
	

}
