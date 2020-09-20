package com.sbc.api.ratelimit;

import java.util.concurrent.TimeUnit;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class RateLimit {

	@EmbeddedId
	private RateLimitId rateLimitId;
	
	private TimeUnit timeUnit;
	private long timeValue;
	private long maxPermits;
	private long intervalStartTime;
	private long allowedPermits;
	
	public RateLimit() {
		super();
	}

	public RateLimit(RateLimitId rateLimitId, TimeUnit timeUnit, long timeValue, long maxPermits) {
		super();
		this.timeUnit = timeUnit;
		this.timeValue = timeValue;
		this.maxPermits = maxPermits;
		this.rateLimitId = rateLimitId;
	}
	
	public RateLimit(TimeUnit timeUnit, long timeValue, long maxPermits, String apiName, String clientId) {
		this(new RateLimitId(apiName, clientId), timeUnit, timeValue, maxPermits);
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	public long getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(long timeValue) {
		this.timeValue = timeValue;
	}

	public long getAllowedPermits() {
		return allowedPermits;
	}

	public void setAllowedPermits(long allowedPermits) {
		this.allowedPermits = allowedPermits;
	}

	public RateLimitId getRateLimitId() {
		return rateLimitId;
	}
	
	public void setRateLimitId(RateLimitId rateLimitId) {
		this.rateLimitId = rateLimitId;
	}

	public long getMaxPermits() {
		return maxPermits;
	}

	public void setMaxPermits(long maxPermits) {
		this.maxPermits = maxPermits;
	}

	public long getIntervalStartTime() {
		return intervalStartTime;
	}

	public void setIntervalStartTime(long intervalStartTime) {
		this.intervalStartTime = intervalStartTime;
	}
	
	public boolean resetInterval(long intervalStartTime) {
		this.intervalStartTime = intervalStartTime;
		this.allowedPermits = 1;
		return (maxPermits >= allowedPermits);
	}
	
	public boolean incrementAllowedPermits() {
		if(allowedPermits == maxPermits) {
			return false;
		}
		allowedPermits++;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (allowedPermits ^ (allowedPermits >>> 32));
		result = prime * result + (int) (intervalStartTime ^ (intervalStartTime >>> 32));
		result = prime * result + (int) (maxPermits ^ (maxPermits >>> 32));
		result = prime * result + ((rateLimitId == null) ? 0 : rateLimitId.hashCode());
		result = prime * result + ((timeUnit == null) ? 0 : timeUnit.hashCode());
		result = prime * result + (int) (timeValue ^ (timeValue >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RateLimit other = (RateLimit) obj;
		if (allowedPermits != other.allowedPermits)
			return false;
		if (intervalStartTime != other.intervalStartTime)
			return false;
		if (maxPermits != other.maxPermits)
			return false;
		if (rateLimitId == null) {
			if (other.rateLimitId != null)
				return false;
		} else if (!rateLimitId.equals(other.rateLimitId))
			return false;
		if (timeUnit != other.timeUnit)
			return false;
		if (timeValue != other.timeValue)
			return false;
		return true;
	}
	
}
