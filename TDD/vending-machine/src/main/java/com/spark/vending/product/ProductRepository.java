package com.spark.vending.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("Select p from Product p where p.name = ?1")
	Product findProductByName(String name);

}
