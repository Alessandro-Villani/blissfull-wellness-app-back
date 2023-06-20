package org.java.blissful.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.pojo.Product;
import org.java.blissful.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> findAll(){
		
		return productRepo.findAll();
		
	}
	
	public List<Product> findByNameContaining(String name){
		
		return productRepo.findByNameContaining(name);
		
	}
	
	public Optional<Product> findById(long id){
		
		return productRepo.findById(id);
		
	}
	
	public Product save(Product product) {
		
		return productRepo.save(product);
		
	}
	
	public void delete(Product product) {
		
		productRepo.delete(product);
		
	}
}
