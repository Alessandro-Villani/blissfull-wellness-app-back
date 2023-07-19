package org.java.blissful.api.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.java.blissful.api.dto.PurchaseOrderDto;
import org.java.blissful.auth.pojo.User;
import org.java.blissful.auth.services.UserService;
import org.java.blissful.pojo.Product;
import org.java.blissful.pojo.PurchaseOrder;
import org.java.blissful.services.ProductService;
import org.java.blissful.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/purchaseorders")
	public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders(@RequestParam(required = false) String date){
		
		List<PurchaseOrder> purchaseOrders = purchaseOrderService.findAll();
		
		if(date != null && !date.isEmpty()) {
			LocalDate searchDate = LocalDate.parse(date);
			purchaseOrders = purchaseOrders.stream().filter(b -> b.getDateOfPickUp().isEqual(searchDate)).toList();
		}
		
		return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
		
	}
	
	@GetMapping("purchaseorders/inlist/{id}")
	public ResponseEntity<List<PurchaseOrder>> findByIdInList(@PathVariable long id){
		
		Optional<PurchaseOrder> optPurchaseOrder = purchaseOrderService.findById(id);
		
		List<PurchaseOrder> purchaseOrderInList = new ArrayList<>();
		
		if(optPurchaseOrder.isEmpty()) return new ResponseEntity<List<PurchaseOrder>>(HttpStatus.NOT_FOUND);
		
		PurchaseOrder purchaseOrder = optPurchaseOrder.get();
		
		
		purchaseOrderInList.add(purchaseOrder);
		
		return new ResponseEntity<List<PurchaseOrder>>(purchaseOrderInList, HttpStatus.OK);
		
	}
	
	@GetMapping("/purchaseorders/user/{id}")
	public ResponseEntity<List<PurchaseOrder>> getUserOrders(@PathVariable long id, @RequestParam(required = false) String date){
		
		List<PurchaseOrder> userPurchaseOrders = purchaseOrderService.findByUserId(id);
		
		if(date != null && !date.isEmpty()) {
			LocalDate searchDate = LocalDate.parse(date);
			userPurchaseOrders = userPurchaseOrders.stream().filter(b -> b.getDateOfPickUp().isEqual(searchDate)).toList();
		}
		
		return new ResponseEntity<>(userPurchaseOrders, HttpStatus.OK);
		
	}
	
	@GetMapping("/purchaseorders/product/{id}")
	public ResponseEntity<List<PurchaseOrder>> getProductOrder(@PathVariable long id){
		
		List<PurchaseOrder> productPurchaseOrders = purchaseOrderService.findByProductId(id);
		
		return new ResponseEntity<>(productPurchaseOrders, HttpStatus.OK);
		
	}
	
	@PostMapping("/purchaseorders/store")
	public ResponseEntity<PurchaseOrder> storePurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto){
		
		System.out.println(purchaseOrderDto.getDateOfPickup());
		
		Product product = productService.findById(purchaseOrderDto.getProductId()).get();
		
		if(product.getStockQuantity() < purchaseOrderDto.getQuantity()) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		if(purchaseOrderDto.getDateOfPickup() != null || !purchaseOrderDto.getAddress().equals("")) product.setStockQuantity(product.getStockQuantity() - purchaseOrderDto.getQuantity());
		
		productService.save(product);
		
		User user = userService.findById(purchaseOrderDto.getUserId()).get();
		
		PurchaseOrder purchaseOrder = purchaseOrderDto.isDelivery() ?
				new PurchaseOrder(product, user, purchaseOrderDto.isDelivery(), purchaseOrderDto.getQuantity(), purchaseOrderDto.getAddress(), purchaseOrderDto.getLatitude(), purchaseOrderDto.getLongitude()) 
				:
				new PurchaseOrder(product, user, purchaseOrderDto.isDelivery(), purchaseOrderDto.getQuantity(), LocalDate.parse(purchaseOrderDto.getDateOfPickup()));
		
		purchaseOrderService.save(purchaseOrder);
		
		return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
		
	}
	
	@PatchMapping("/purchaseorders/{id}/accept")
	public ResponseEntity<PurchaseOrder> acceptOrder(@PathVariable long id){
		
		PurchaseOrder purchaseOrder = purchaseOrderService.findById(id).get();
		
		purchaseOrder.setAccepted(true);
		
		purchaseOrderService.save(purchaseOrder);
		
		return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
		
	}
	
	@PatchMapping("/purchaseorders/{id}/reject")
	public ResponseEntity<PurchaseOrder> rejectOrder(@PathVariable long id){
		
		PurchaseOrder purchaseOrder = purchaseOrderService.findById(id).get();
		
		Product product = productService.findById(purchaseOrder.getProduct().getId()).get();
		
		product.setStockQuantity(product.getStockQuantity() + purchaseOrder.getQuantity());
		
		productService.save(product);
		
		purchaseOrder.setRejected(true);
		
		purchaseOrderService.save(purchaseOrder);
		
		return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
		
	}
	
	@PatchMapping("/purchaseorders/{id}/delivered")
	public ResponseEntity<PurchaseOrder> deliveredOrder(@PathVariable long id){
		
		PurchaseOrder purchaseOrder = purchaseOrderService.findById(id).get();
		
		purchaseOrder.setDelivered(true);
		
		purchaseOrderService.save(purchaseOrder);
		
		return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
		
	}
	
	@PatchMapping("/purchaseorders/{id}/cancel")
	public ResponseEntity<PurchaseOrder> cancelOrder(@PathVariable long id){
		
		PurchaseOrder purchaseOrder = purchaseOrderService.findById(id).get();
		
		Product product = productService.findById(purchaseOrder.getProduct().getId()).get();
		
		product.setStockQuantity(product.getStockQuantity() + purchaseOrder.getQuantity());
		
		productService.save(product);
		
		purchaseOrder.setCanceled(true);
		
		purchaseOrderService.save(purchaseOrder);
		
		return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
		
	}
	

}
