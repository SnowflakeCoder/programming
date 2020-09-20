package com.sbc.api.ratelimit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RateLimitServiceTest {

	@Autowired
	private RateLimitRepository rateLimitRepository;
	
	@AfterEach
	void tearDown() {
		rateLimitRepository.deleteAll();
	}

	
	@Test
	void getProdyctByName_Success() {
		RateLimit rateLimit = new RateLimit(new RateLimitId("main/api1", "testClient"), TimeUnit.HOURS, 1, 10);
		rateLimitRepository.save(rateLimit);
		
		RateLimitService rateLimitService = new RateLimitService(rateLimitRepository);
		RateLimit savedRateLimit = rateLimitService.getRateLimitByClientIdNApi("testClient", "main/api1");
		
		assertNotNull(savedRateLimit);
		assertEquals(rateLimit, savedRateLimit);
	}
}
