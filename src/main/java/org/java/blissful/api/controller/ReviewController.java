package org.java.blissful.api.controller;

import org.java.blissful.api.dto.ReviewDto;
import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.pojo.User;
import org.java.blissful.auth.services.TherapistService;
import org.java.blissful.auth.services.UserService;
import org.java.blissful.pojo.Review;
import org.java.blissful.services.BookingService;
import org.java.blissful.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TherapistService therapistService;
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping("reviews/store")
	public ResponseEntity<Review> storeReview(@RequestBody ReviewDto reviewDto){
		
		Therapist therapist = therapistService.findById(reviewDto.getTherapist()).get();
		User author = userService.findById(reviewDto.getAuthor()).get();
		
		Review review = new Review(reviewDto.getGrade(), reviewDto.getReview(), therapist, author, reviewDto.getMassageName(), reviewDto.getDuration(), reviewDto.getDate());
		
		reviewService.save(review);
		
		return new ResponseEntity<>(review, HttpStatus.OK);
	}

}
