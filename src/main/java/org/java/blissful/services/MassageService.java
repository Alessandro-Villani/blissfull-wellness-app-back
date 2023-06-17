package org.java.blissful.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.pojo.Massage;
import org.java.blissful.repo.MassageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MassageService {

	@Autowired
	private MassageRepo massageRepo;
	
	public List<Massage> findAll(){
		
		return massageRepo.findAll();
		
	}
	
	public Optional<Massage> findById(long id){
		
		return massageRepo.findById(id);
		
	}
	
	public Massage save(Massage massage) {
		
		return massageRepo.save(massage);
		
	}
	
	public void delete(Massage massage) {
		
		massageRepo.delete(massage);
		
	}
	
}
