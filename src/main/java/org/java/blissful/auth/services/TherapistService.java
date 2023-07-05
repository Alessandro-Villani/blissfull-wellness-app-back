package org.java.blissful.auth.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.auth.pojo.Therapist;
import org.java.blissful.auth.repo.TherapistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TherapistService {

	@Autowired
	private TherapistRepo therapistRepo;
	
	public List<Therapist> findAll(){
		
		return therapistRepo.findAll();
		
	}
	
	public Optional<Therapist> findById(long id){
		
		return therapistRepo.findById(id);
		
	}
	
	public Optional<Therapist> findByUserId(long id){
		
		return therapistRepo.findByUserId(id);
		
	}
	
	public Therapist save(Therapist therapist) {
		
		return therapistRepo.save(therapist);
		
	}
	
	public void delete(Therapist therapist) {
		
		therapistRepo.delete(therapist);
		
	}
	
}
