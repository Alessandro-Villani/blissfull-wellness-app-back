package org.java.blissful.services;

import java.util.List;
import java.util.Optional;

import org.java.blissful.pojo.PurchaseOrder;
import org.java.blissful.repo.PurchaseOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {
	
	@Autowired
	private PurchaseOrderRepo purchaseOrderRepo;
	
	public List<PurchaseOrder> findAll(){
		
		return purchaseOrderRepo.findAll();
		
	}
	
	public List<PurchaseOrder> findByUserId(long id){
		
		return purchaseOrderRepo.findByUserId(id);
		
	}
	
	public List<PurchaseOrder> findByProductId(long id){
		
		return purchaseOrderRepo.findByProductId(id);
		
	}
	
	public Optional<PurchaseOrder> findById(long id){
		
		return purchaseOrderRepo.findById(id);
		
	}
	
	public PurchaseOrder save(PurchaseOrder purchaseOrder) {
		
		return purchaseOrderRepo.save(purchaseOrder);
		
	}
	
	public void delete(PurchaseOrder purchaseOrder) {
		
		purchaseOrderRepo.delete(purchaseOrder);
		
	}

}
