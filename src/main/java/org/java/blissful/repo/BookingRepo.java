package org.java.blissful.repo;

import java.util.List;

import org.java.blissful.pojo.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long>{
	
	public List<Booking> findAllByOrderByDate();
	
	public List<Booking> findByUserIdOrderByDate(long id);
	
	public List<Booking> findByTherapistIdOrderByDate(long id);
	
	public List<Booking> findByMassageIdOrderByDate(long id);

}
