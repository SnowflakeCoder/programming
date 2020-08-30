package com.spark.vending;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spark.vending.coin.Coin;
import com.spark.vending.coin.CoinCount;
import com.spark.vending.coin.CoinCountRepository;
import com.spark.vending.coin.CoinCountService;

@SpringBootTest
class CoinCountTest {

	@Autowired
	CoinCountRepository coinCountRepository;
	
	@AfterEach
	void tearDown() {
		coinCountRepository.deleteAll();
	}
	
	
	@Test
	void addCoinCount_Success() {
		CoinCount coinCount = new CoinCount(Coin.Spark_1.name(), 5);
		
		long prevCount = coinCountRepository.count();
		
		CoinCountService coinCountService = new CoinCountService(coinCountRepository);
		CoinCount savedCoinCount = coinCountService.addCoinCount(coinCount);
		
		assertEquals(prevCount+1, coinCountRepository.count());
		assertNotNull(savedCoinCount);
		assertEquals(coinCount.getCoinType(), savedCoinCount.getCoinType());
		assertEquals(coinCount.getCount(), savedCoinCount.getCount());
		
	}
	
	@Test
	void getCointCountByType_NotEmpty() {
		CoinCount coinCount = new CoinCount(Coin.Spark_1.name(), 5);
		coinCountRepository.save(coinCount);
		
		CoinCountService coinCountService = new CoinCountService(coinCountRepository);
		CoinCount savedCoinCount = coinCountService.getCoinCountByType(Coin.Spark_1.name());
		
		
		assertNotNull(savedCoinCount);
		assertEquals(coinCount.getCoinType(), savedCoinCount.getCoinType());
		assertEquals(coinCount.getCount(), savedCoinCount.getCount());
		
	}
	
	
	
	
}
