package org.java.blissful.repo;

import java.util.List;

import org.java.blissful.pojo.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long>{
	
	public List<PurchaseOrder> findByUserId(long id);
	
	public List<PurchaseOrder> findByProductId(long id);
}
