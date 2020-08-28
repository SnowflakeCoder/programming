package com.spark.vending.productInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("productInventoryRepository")
public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
	
	@Query("Select p from ProductInventory p where p.productId = ?1")
	ProductInventory findProductInventoryByProductId(long productId);

}
