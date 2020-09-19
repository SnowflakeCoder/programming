package com.spark.mobileapp.user.error;

public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(long id) {
		super("User id not found : " + id);
	}
}
