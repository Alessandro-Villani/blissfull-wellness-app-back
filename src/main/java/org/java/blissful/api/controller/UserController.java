package org.java.blissful.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.java.blissful.api.dto.UserDto;
import org.java.blissful.auth.pojo.Role;
import org.java.blissful.auth.pojo.User;
import org.java.blissful.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/users")
	public List<User> getAllUserNonTherapists(@RequestParam(required = false) String username){
		
		List<User> users = username != null ? userService.findByUsernameContaining(username) : userService.findAll();
		
		users = users.stream()
		        .filter(user -> user.getRoles().stream()
		        .noneMatch(role -> role.getName().equals("therapist") || role.getName().equals("admin")))
		        .collect(Collectors.toList());
		
		return users;
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> getUserById(@RequestBody UserDto userDto){
		
		
		System.out.println(userDto.getPassword());
		
		User user = (User) userService.loadUserByUsername(userDto.getUsername());
		
		System.out.println(user.getPassword());
		
		System.out.println(passwordEncoder.matches(userDto.getPassword(), user.getPassword()));
		
		if(passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
			
			return new ResponseEntity<>(user, HttpStatus.OK);
			
		}
		
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
	}
	
}
