package com.spark.vending;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spark.vending.product.Product;
import com.spark.vending.product.ProductRepository;
import com.spark.vending.productInventory.ProductInventory;
import com.spark.vending.productInventory.ProductInventoryRepository;
import com.spark.vending.productInventory.ProductInventoryService;

@SpringBootTest
class ProductInventoryTest {

	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductInventoryRepository productInventoryRepository;
	
	
	@AfterEach
	void tearDown() {
		productInventoryRepository.deleteAll();
		productRepository.deleteAll();
	}
	
	@Test
	void addProductInventory_success() {
		Product product = new Product("SparklingWater", 25);
		Product savedproduct = productRepository.save(product);
		
		ProductInventory productInventory = new ProductInventory(savedproduct.getId(), 1);
		ProductInventoryService productInventoryService = new ProductInventoryService(productInventoryRepository);
		ProductInventory savedProductInventory = productInventoryService.addProductInventory(productInventory);
		
		assertNotNull(savedProductInventory);
		assertEquals(productInventory.getProductCount(), savedProductInventory.getProductCount());
		assertEquals(productInventory.getProductId(), savedProductInventory.getProductId());
	}
	
	@Test
	void getProdctInventoryByProductId_NotEmpty() {
		Product product = new Product("SparklingWater", 25);
		Product savedproduct = productRepository.save(product);
		
		ProductInventory productInventory = new ProductInventory(savedproduct.getId(), 1);
		productInventoryRepository.save(productInventory);
		ProductInventoryService productInventoryService = new ProductInventoryService(productInventoryRepository);
		ProductInventory savedProductInventory = productInventoryService.getProductInventoryByProductId(savedproduct.getId());
		
		assertNotNull(savedProductInventory);
		assertEquals(productInventory.getProductCount(), savedProductInventory.getProductCount());
		assertEquals(productInventory.getProductId(), savedProductInventory.getProductId());
		
	}
	
	
	
	

}
