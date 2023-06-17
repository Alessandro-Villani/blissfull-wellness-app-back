package org.java.blissful.auth.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.auth.repo.UserRepo;
import org.java.blissful.pojo.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	public List<User> findAll(){
		
		return userRepo.findAll();
		
	}
	
	public Optional<User> findById(long id){
		
		return userRepo.findById(id);
		
	}
	
	public User save(User user) {
		
		return userRepo.save(user);
		
	}
	
	public void delete(User user) {
		
		userRepo.delete(user);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> userOpt = userRepo.findByUsername(username);
		
		if(userOpt.isEmpty()) throw new UsernameNotFoundException("Username non trovato");
		
		return userOpt.get();
	}
	
	
}
