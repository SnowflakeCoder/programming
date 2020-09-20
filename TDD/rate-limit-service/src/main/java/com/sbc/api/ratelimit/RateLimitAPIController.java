package com.sbc.api.ratelimit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitAPIController {

	@Autowired
	private RateLimitRepository rateLimiterRepository;

	@GetMapping("/rateLimits")
	public List<RateLimit> getAllRateLimits() {
		return rateLimiterRepository.findAll();
	}

	@PostMapping("/getRateLimits")
	public RateLimit getRateLimit(@RequestBody RateLimitId rateLimitId) {
		Optional<RateLimit> rateLimit = rateLimiterRepository.findById(rateLimitId);

		if (!rateLimit.isPresent())
			throw new RateLimitNotFoundException("id-" + rateLimitId);

		return rateLimit.get();
	}

	@DeleteMapping("/rateLimits")
	public void deleteRateLimit(@RequestBody RateLimitId rateLimitId) {
		rateLimiterRepository.deleteById(rateLimitId);
	}

	@PostMapping("/rateLimits")
	@ResponseStatus(HttpStatus.CREATED)
	public RateLimit createRateLimit(@RequestBody RateLimit rateLimit) {
		return rateLimiterRepository.save(rateLimit);
	}

	@PutMapping("/rateLimits")
	public RateLimit updateRateLimit(@RequestBody RateLimit rateLimit) {

		Optional<RateLimit> rateLimitOptional = rateLimiterRepository.findById(rateLimit.getRateLimitId());

		if (!rateLimitOptional.isPresent())
			throw new RateLimitNotFoundException("id-" + rateLimit.getRateLimitId());
		return rateLimiterRepository.save(rateLimit);
	}
}
