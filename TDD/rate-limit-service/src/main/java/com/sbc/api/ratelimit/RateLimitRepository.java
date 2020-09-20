package com.sbc.api.ratelimit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("rateLimitRepository")
public interface RateLimitRepository extends JpaRepository<RateLimit, RateLimitId>{
	
	@Query("Select r from RateLimit r where r.rateLimitId.clientId = :clientId and r.rateLimitId.apiName = :apiName")
	RateLimit getRateLimitByClientIdNApi(@Param("clientId") String clientId, @Param("apiName") String apiName);

}

