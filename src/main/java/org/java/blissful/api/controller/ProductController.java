package org.java.blissful.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.java.blissful.api.controller.UserController.FileUploadUtil;
import org.java.blissful.api.dto.ProductDto;
import org.java.blissful.pojo.Product;
import org.java.blissful.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getAllProducts(@RequestParam(required = false) String name){
		
		List<Product> products = name != null ? productService.findByNameContaining(name) : productService.findAll();
		
		return products;
		
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id){
		
		Optional<Product> optProduct = productService.findById(id);
		
		if(optProduct.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		Product product = optProduct.get();
		
		return new ResponseEntity<>(product, HttpStatus.OK);
		
	}
	
	@Value("${upload.dir}")
    private String uploadDir;
	
	@PostMapping("/products/store")
	public ResponseEntity<Product> storeProduct(@RequestParam("file") MultipartFile file, @RequestParam("product") String productDtoJson){
		try {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDto productDto = objectMapper.readValue(productDtoJson, ProductDto.class);

        // Esegui l'upload dell'immagine del profilo
        String originalFileName = file.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(originalFileName);
        String fileName = UUID.randomUUID().toString() + "." + fileExtension;
        String uploadPath = uploadDir + "/product_pics/" + fileName;
        FileUploadUtil.saveFile(uploadPath, file);
        String relativePath = "images/product_pics/" + fileName;
		
		Product product = new Product(productDto.getName(), productDto.getDescription(), relativePath, productDto.getPrice(), productDto.getStockQuantity());
		
		productService.save(product);
		
		return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
	    	e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}
	
	@PutMapping("/products/update/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("product") String productDtoJson){
		try {
			Optional<Product> optProduct = productService.findById(id);
			
			if(optProduct.isEmpty()) {
				
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
			}
			
			Product product = optProduct.get();
			
			ObjectMapper objectMapper = new ObjectMapper();
	        ProductDto productDto = objectMapper.readValue(productDtoJson, ProductDto.class);
	        
			
			product.setName(productDto.getName());
			product.setDescription(productDto.getDescription());
			product.setPrice(productDto.getPrice());
			product.setStockQuantity(productDto.getStockQuantity());
			
			if(file != null) {
				String originalFileName = file.getOriginalFilename();
		        String fileExtension = FilenameUtils.getExtension(originalFileName);
		        String fileName = UUID.randomUUID().toString() + "." + fileExtension;
		        String uploadPath = uploadDir + "/product_pics/" + fileName;
		        FileUploadUtil.saveFile(uploadPath, file);
		        String relativePath = "images/product_pics/" + fileName;
		        
		        product.setImageUrl(relativePath);
			}
			
			productService.save(product);
			
			return new ResponseEntity<>(product, HttpStatus.OK);
			
		} catch (Exception e) {
	    	e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		
	}
	
	@PatchMapping("/products/{id}/quantity")
	public ResponseEntity<Product> patchStockQuantity(@PathVariable long id, @RequestBody ProductDto productDto){
		
		Product product = productService.findById(id).get();
		
		product.setStockQuantity(productDto.getStockQuantity());
		
		productService.save(product);
		
		return new ResponseEntity<>(product, HttpStatus.OK);
		
	}
	
	@DeleteMapping("products/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable long id){
		
		Product product = productService.findById(id).get();
		
		productService.delete(product);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
}
