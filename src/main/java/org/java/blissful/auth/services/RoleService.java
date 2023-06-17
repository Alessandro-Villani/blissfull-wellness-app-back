package org.java.blissful.auth.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.auth.repo.RoleRepo;
import org.java.blissful.pojo.auth.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> findAll(){
		
		return roleRepo.findAll();
		
	}
	
	public Optional<Role> findById(long id){
		
		return roleRepo.findById(id);
		
	}
	
	public Role save(Role role) {
		
		return roleRepo.save(role);
		
	}
	
	public void delete(Role role) {
		
		roleRepo.delete(role);
		
	}
	
}
