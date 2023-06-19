package org.java.blissful.auth.repo;

import java.util.List;
import java.util.Optional;

import org.java.blissful.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
	
	public Optional<User> findByUsername(String username);
	public List<User> findByUsernameContaining(String username);
	
}
