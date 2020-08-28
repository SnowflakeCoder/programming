package com.spark.vending.productInventory;

import org.springframework.stereotype.Service;

@Service
public class ProductInventoryService {

	private ProductInventoryRepository productInventoryRepository; 
	
	
	public ProductInventoryService(ProductInventoryRepository productInventoryRepository) {
		super();
		this.productInventoryRepository = productInventoryRepository;
	}
	
	public ProductInventory addProductInventory(ProductInventory productInventory) {
		return productInventoryRepository.save(productInventory);
	}
	
	public ProductInventory getProductInventoryByProductId(long productId) {
		return productInventoryRepository.findProductInventoryByProductId(productId);
	}
	
}
