package com.spark.mobileapp.user.error;

import java.util.Set;

public class UserUnSupportedPatchException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserUnSupportedPatchException(Set<String> keys) {
		super("Fields :: " + keys + " not allowed");
	}
}
