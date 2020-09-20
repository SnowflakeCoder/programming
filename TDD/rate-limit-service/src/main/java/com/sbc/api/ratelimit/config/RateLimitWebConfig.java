package com.sbc.api.ratelimit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sbc.api.ratelimit.interceptor.RateLimitingInterceptor;

@Configuration
@EnableWebMvc
public class RateLimitWebConfig implements WebMvcConfigurer {
	
	@Autowired
	RateLimitingInterceptor rateLimitingInterceptor;
	
	/*
	 * Adding rateLimit check only on MainController nor RateLimit controller.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(rateLimitingInterceptor).addPathPatterns("/main/*");
	}
}
