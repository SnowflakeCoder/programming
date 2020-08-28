package com.spark.vending;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spark.vending.product.Product;
import com.spark.vending.product.ProductRepository;
import com.spark.vending.product.ProductService;

@SpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductRepository productRepository;
	
	
	@AfterEach
	void tearDown() {
		productRepository.deleteAll();
	}
	
	@Test
	void addProduct_Success() {
		Product product = new Product("SparklingWater", 25);
		
		long prevCount = productRepository.count();
		ProductService productService = new ProductService(productRepository);
		Product savedProduct = productService.addProduct(product);
		
		assertNotNull(savedProduct);
		assertEquals(product.getName(), savedProduct.getName());
		assertEquals(product.getPrice(), savedProduct.getPrice());
		assertEquals(prevCount + 1, productRepository.count());
		
	}
	
	@Test
	void getProducts_Success(){
		Product product = new Product("SparklingWater", 25);
		productRepository.save(product);
		
		ProductService productService = new ProductService(productRepository);
		List<Product> products = productService.getProducts();
		
		assertFalse(products.isEmpty());
		Product savedProduct = products.get(0);
		
		assertEquals(product.getName(), savedProduct.getName());
		assertEquals(product.getPrice(), savedProduct.getPrice());
	}
	
	
	@Test
	void getProdyctByName_Success() {
		Product product = new Product("SparklingWater", 25);
		productRepository.save(product);
		
		ProductService productService = new ProductService(productRepository);
		Product savedProduct = productService.getProductByName("SparklingWater");
		
		assertNotNull(savedProduct);
		assertEquals(product.getName(), savedProduct.getName());
		assertEquals(product.getPrice(), savedProduct.getPrice());
		
	}
	

}
