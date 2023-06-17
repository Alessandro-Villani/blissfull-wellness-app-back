package org.java.blissful.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.pojo.Review;
import org.java.blissful.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	public List<Review> findAll() {
		
		return reviewRepo.findAll();
		
	}
	
	public List<Review> findByTherapistId(long id){
		
		return reviewRepo.findByTherapistId(id);
		
	}
	
	public Optional<Review> findById(long id){
		
		return reviewRepo.findById(id);
		
	}
	
	public Review save(Review review) {
		
		return reviewRepo.save(review);
		
	}
	
	public void delete(Review review) {
		
		reviewRepo.delete(review);
		
	}
	
}
