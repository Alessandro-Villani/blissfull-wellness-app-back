package org.java.blissful.repo;

import java.util.List;

import org.java.blissful.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Long>{

	public List<Review> findByTherapistId(long id);
	
}
