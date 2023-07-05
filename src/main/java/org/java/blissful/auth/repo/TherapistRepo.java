package org.java.blissful.auth.repo;

import java.util.Optional;

import org.java.blissful.auth.pojo.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TherapistRepo extends JpaRepository<Therapist, Long>{
	
	public Optional<Therapist> findByUserId(long id);

}
