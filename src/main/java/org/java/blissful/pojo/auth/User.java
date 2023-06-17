package org.java.blissful.pojo.auth;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	@NotBlank
	private String password;
	
	@Column(columnDefinition = "text")
	private String profilePic;
	
	private String address;
	
	private LocalDate dateOfBirth;
	
	@ManyToMany(fetch  = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Set<Role> roles;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonBackReference
	private Therapist therapist;
	
	public User() {}
	
	public User(String username, String password, LocalDate dateOfBirth, String profilePic) {
		
		setUsername(username);
		setPassword(password);
		setDateOfBirth(dateOfBirth);
		setProfilePic(profilePic);
		
	}
	
	public User(String username, String password, LocalDate dateOfBirth, String profilePic, Role...roles) {
		
		this(username, password, dateOfBirth, profilePic);
		
		setRoles(roles);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

	public Set<Role> getRoles() {
		return roles;
	}

	@JsonSetter
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void setRoles(Role[] roles) {
		setRoles(new HashSet<>(Arrays.asList(roles)));
	}
	
	public void addRole(Role role) {
		getRoles().add(role);
	}
	
	public void deleteRole(Role role) {
		getRoles().remove(role);
	}

	public Therapist getTherapist() {
		return therapist;
	}

	public void setTherapist(Therapist therapist) {
		this.therapist = therapist;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
