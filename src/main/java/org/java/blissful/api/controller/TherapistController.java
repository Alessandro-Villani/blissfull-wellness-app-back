package org.java.blissful.api.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.java.blissful.api.dto.TherapistDto;
import org.java.blissful.auth.pojo.Role;
import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.pojo.User;
import org.java.blissful.auth.services.RoleService;
import org.java.blissful.auth.services.TherapistService;
import org.java.blissful.auth.services.UserService;
import org.java.blissful.pojo.Massage;
import org.java.blissful.services.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@Autowired
	private MassageService massageService;
	
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
		
		System.out.println(user);
		
		List<Massage> massages = new ArrayList<>();
		
		for(long massageId : therapistDto.getMassages()) {
			
			Massage massage = massageService.findById(massageId).get();
			
			massages.add(massage);
			
		}
		
		Therapist therapist = new Therapist(user, therapistDto.getTherapistName(), LocalDate.now(), therapistDto.getDescription(), massages);
		
		therapistService.save(therapist);
		
		return new ResponseEntity<>(therapist, HttpStatus.OK);
		
	}
	
	@PutMapping("therapists/update/{id}")
	public ResponseEntity<Therapist> updateTherapist(@PathVariable long id, @RequestBody TherapistDto therapistDto){
		
		System.out.println(therapistDto.getMassages());
		
		List<Massage> massages = new ArrayList<>();
		
		for(long massageId : therapistDto.getMassages()) {
			
			Massage massage = massageService.findById(massageId).get();
			
			massages.add(massage);
			
		}
		
		Optional<Therapist> optTherapist = therapistService.findById(id);
		
		if(optTherapist.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		Therapist therapist = optTherapist.get();
		
		therapist.setTherapistName(therapistDto.getTherapistName());
		therapist.setDescription(therapistDto.getDescription());
		therapist.setMassages(massages);
		
		therapistService.save(therapist);
		
		return new ResponseEntity<>(therapist, HttpStatus.OK);
	}
	
	@DeleteMapping("therapists/delete/{id}")
	public ResponseEntity<Therapist> deleteTherapist(@PathVariable long id){
		
		Optional<Therapist> optTherapist = therapistService.findById(id);
		
		if(optTherapist.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		Therapist therapist = optTherapist.get();
		
		Role therapistRole = roleService.findByName("therapist").get();
		
		User user = userService.findById(therapist.getUser().getId()).get();
		
		user.deleteRole(therapistRole);
		
		userService.save(user);
		
		therapistService.delete(therapist);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
