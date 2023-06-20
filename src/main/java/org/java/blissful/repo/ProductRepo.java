package org.java.blissful.repo;

import java.util.List;

import org.java.blissful.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long>{
	
	public List<Product> findByNameContaining(String name);
	
}
