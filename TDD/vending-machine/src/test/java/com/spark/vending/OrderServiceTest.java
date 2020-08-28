package com.spark.vending;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spark.vending.coin.Coin;
import com.spark.vending.coin.CoinCount;
import com.spark.vending.coin.CoinCountRepository;
import com.spark.vending.order.ProductOrder;
import com.spark.vending.order.OrderService;
import com.spark.vending.order.OrderStatus;
import com.spark.vending.product.Product;
import com.spark.vending.product.ProductRepository;


@SpringBootTest
class OrderServiceTest {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CoinCountRepository coinCountRepository;
	
	@Test
	void addOrder_Success() {
		
		Product product = new Product("SparklingWater", 25);
		Product savedproduct = productRepository.save(product);
		
		int productCount = 2;
		
//		CoinCount coinCount = new CoinCount(Coin.Spark_1.name(), 5);
//		coinCountRepository.save(coinCount);
		
		ProductOrder order = new ProductOrder(savedproduct.getId(), productCount, 50, "", 0);
		OrderService orderService = new OrderService();
		ProductOrder savedOrder = orderService.saveOrder(order);
		
		assertNotNull(savedOrder);
		assertEquals(order.getProductId(), savedOrder.getProductId());
		assertEquals(order.getAcceptedAmount(), savedOrder.getAcceptedAmount());
		assertEquals(order.getProductCount(), savedOrder.getProductCount());
		assertEquals(OrderStatus.Success.name(), savedOrder.getOrderStatus());
		assertEquals(productCount * product.getPrice() , savedOrder.getOrderAmount());
		
	}

}
