package com.sbc.api.ratelimit;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RateLimitId implements Serializable{

	private static final long serialVersionUID = 1L;

	private String apiName;
	private String clientId;
	
	public RateLimitId() {
		
	}
	
	public RateLimitId(String apiName, String clientId) {
		super();
		this.apiName = apiName;
		this.clientId = clientId;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiName == null) ? 0 : apiName.hashCode());
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
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
		RateLimitId other = (RateLimitId) obj;
		if (apiName == null) {
			if (other.apiName != null)
				return false;
		} else if (!apiName.equals(other.apiName))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RateLimitId [apiName=" + apiName + ", clientId=" + clientId + "]";
	}
	
}
