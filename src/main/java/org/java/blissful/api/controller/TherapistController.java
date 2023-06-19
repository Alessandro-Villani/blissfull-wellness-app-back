package org.java.blissful.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.java.blissful.api.dto.TherapistDto;
import org.java.blissful.auth.pojo.Role;
import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.pojo.User;
import org.java.blissful.auth.services.RoleService;
import org.java.blissful.auth.services.TherapistService;
import org.java.blissful.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class TherapistController {

	@Autowired
	private TherapistService therapistService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/therapists")
	public ResponseEntity<List<Therapist>> getAllTherapists(){
		
		List<Therapist> therapists = therapistService.findAll();
		
		return new ResponseEntity<>(therapists, HttpStatus.OK);
		
	}
	
	@PostMapping("therapists/store")
	public ResponseEntity<Therapist> storeTherapist(@RequestBody TherapistDto therapistDto){
		
		User user = userService.findById(therapistDto.getUserId()).get();
		
		Role therapistRole = roleService.findByName("therapist").get();
		
		user.addRole(therapistRole);
		
		userService.save(user);
		
		Therapist therapist = new Therapist(user, therapistDto.getTherapistName(), LocalDate.now(), therapistDto.getDescription());
		
		therapistService.save(therapist);
		
		return new ResponseEntity<>(therapist, HttpStatus.OK);
		
	}
	
}
