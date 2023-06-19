package org.java.blissful.auth.repo;

import java.util.Optional;

import org.java.blissful.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long>{
	
	public Optional<Role> findByName(String name);
	
}
