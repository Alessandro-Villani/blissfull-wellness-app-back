package org.java.blissful.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.pojo.Booking;
import org.java.blissful.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepo bookingRepo;
	
	public List<Booking> findAll(){
		
		return bookingRepo.findAllByOrderByDate();
		
	}
	
	public List<Booking> findByUserId(long id){
		
		return bookingRepo.findByUserIdOrderByDate(id);
		
	}
	
	public List<Booking> findByTherapistId(long id){
		
		return bookingRepo.findByTherapistIdOrderByDate(id);
		
	}
	
	public List<Booking> findByMassageId(long id){
		
		return bookingRepo.findByMassageIdOrderByDate(id);
	}
	
	public Optional<Booking> findById(long id){
		
		return bookingRepo.findById(id);
		
	}
	
	public Booking save(Booking booking) {
		
		return bookingRepo.save(booking);
		
	}
	
	public void delete(Booking booking) {
		
		bookingRepo.delete(booking);
		
	}

}
