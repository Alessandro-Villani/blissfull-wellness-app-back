package org.java.blissful.api.controller;

import java.util.List;

import org.java.blissful.auth.services.TherapistService;
import org.java.blissful.pojo.auth.Therapist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class TherapistController {

	@Autowired
	private TherapistService therapistService;
	
	@GetMapping("/therapists")
	public ResponseEntity<List<Therapist>> getAllTherapists(){
		
		List<Therapist> therapists = therapistService.findAll();
		
		return new ResponseEntity<>(therapists, HttpStatus.OK);
		
	}
	
}
