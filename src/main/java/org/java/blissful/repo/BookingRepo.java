package org.java.blissful.repo;

import java.util.List;

import org.java.blissful.pojo.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<Booking, Long>{
	
	public List<Booking> findByUserId(long id);
	
	public List<Booking> findByTherapistId(long id);
	
	public List<Booking> findByMassageId(long id);

}
