package org.java.blissful.auth.pojo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.java.blissful.pojo.PurchaseOrder;
import org.java.blissful.pojo.Review;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
	
	@NotNull
	@NotBlank
	private String firstName;
	
	@NotNull
	@NotBlank
	private String lastName;
	
	private String address;
	
	private LocalDate dateOfBirth;
	
	@ManyToMany(fetch  = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Set<Role> roles;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonBackReference
	private Therapist therapist;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
	@JsonBackReference
	private List<Review> sentReviews;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<PurchaseOrder> orders;
	
	public User() {}
	
	public User(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String profilePic) {
		
		setUsername(username);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		setProfilePic(profilePic);
		
	}
	
	public User(String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String profilePic, Role...roles) {
		
		this(username, password, firstName, lastName, dateOfBirth, profilePic);
		
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

	@JsonIgnore
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

	public List<Review> getSentReviews() {
		return sentReviews;
	}

	public void setSentReviews(List<Review> sentReviews) {
		this.sentReviews = sentReviews;
	}

	public List<PurchaseOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<PurchaseOrder> orders) {
		this.orders = orders;
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
