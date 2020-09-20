package com.sbc.api.ratelimit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateLimitService {

	@Autowired
	private RateLimitRepository rateLimitRepository;
	
	public RateLimitService(RateLimitRepository rateLimitRepository) {
		this.rateLimitRepository = rateLimitRepository;
	}
	
	public RateLimit getRateLimitByClientIdNApi(String clientId, String apiName) {
		return rateLimitRepository.getRateLimitByClientIdNApi(clientId, apiName);
	}

}
