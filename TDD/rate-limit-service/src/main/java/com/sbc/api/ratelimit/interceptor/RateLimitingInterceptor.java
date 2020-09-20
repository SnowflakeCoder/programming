package com.sbc.api.ratelimit.interceptor;

import java.util.Optional;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sbc.api.ratelimit.RateLimit;
import com.sbc.api.ratelimit.RateLimitRepository;
import com.sbc.api.ratelimit.RateLimitService;
import com.sbc.api.ratelimit.config.Constants;

@Component
public class RateLimitingInterceptor extends HandlerInterceptorAdapter {

	@Value("${rate.limit.enabled:true}")
	private boolean enabled;

	@Autowired
	RateLimitService rateLimitService;
	
	@Autowired
	private RateLimitRepository rateLimiterRepository;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!enabled) {
			return true;
		}
		String clientId = request.getHeader("Client-Id");
		String apiName = request.getRequestURI();
		
		Optional<RateLimit> rateLimitOptional = Optional.ofNullable(rateLimitService.getRateLimitByClientIdNApi(clientId, apiName));
		
		if(rateLimitOptional.isEmpty()) {
			clientId = Constants.Default_Client_Id;
			apiName = Constants.Default_Api_Name;
		}

		/*
		 * To prevent race conditions race check is executed inside a synchronized block. 
		 */
		
		synchronized (clientId.intern()) {
			rateLimitOptional = Optional.ofNullable(rateLimitService.getRateLimitByClientIdNApi(clientId, apiName));
			
			if(rateLimitOptional.isEmpty()) {
				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
				response.addHeader("X-RateLimit-Limit", "0");
				return false;
			}
			
			RateLimit rateLimit = rateLimitOptional.get();
			long currentTime = System.currentTimeMillis();
			boolean allowRequest = true;
			if(currentTime > rateLimit.getIntervalStartTime() + rateLimit.getTimeUnit().toMillis(rateLimit.getTimeValue())) {
				allowRequest = rateLimit.resetInterval(currentTime);
			}
			else {
				allowRequest = rateLimit.incrementAllowedPermits();
			}
			
			rateLimiterRepository.save(rateLimit);
			
			if (!allowRequest) {
				response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			}
			response.addHeader("X-RateLimit-Limit", String.valueOf(rateLimit.getMaxPermits()));
			return allowRequest;
		}
	}

	@PreDestroy
	public void destroy() {
		// loop and finalize all limiters
	}
}
