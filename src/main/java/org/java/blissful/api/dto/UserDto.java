package org.java.blissful.api.dto;

import java.time.LocalDate;

public class UserDto {
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate dateOfBirth;
	
	public UserDto() {}
	
	public UserDto(String username, String Password) {
		
		setUsername(username);
		setPassword(Password);
		
	}
	
	public UserDto(String username, String password, String firstName, String lastName, String dateOfBirth) {
		
		this(username, password);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = LocalDate.parse(dateOfBirth);
	}

	
	
	
	

}
