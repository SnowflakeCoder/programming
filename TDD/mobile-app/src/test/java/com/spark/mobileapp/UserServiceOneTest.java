package com.spark.mobileapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spark.mobileapp.user.User;
import com.spark.mobileapp.user.UserRepository;
import com.spark.mobileapp.user.UserServiceOne;

@SpringBootTest
class UserServiceOneTest {
	
//	@MockBean
	@Autowired
	private UserRepository userRepository;

	
	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
	}
	
	
	@Test
	void getUsers_NotEmpty() {
		User user = new User(1, "Arun1");
		userRepository.save(user);
		
//		List<User> mockUsers = new ArrayList<User>();
//		mockUsers.add(user);
//		Mockito.when(userRepository.findAll()).thenReturn(mockUsers);
		
		UserServiceOne serviceOne = new UserServiceOne(userRepository);
		List<User> users = serviceOne.getUsers();
		
		assertFalse(users.isEmpty());
		
		User dbUser = users.get(0);
		
		assertEquals(user.getId(), dbUser.getId());
		assertEquals(user.getName(), dbUser.getName());
		
	}
	
	
	@Test
	void saveUser_Success() {
		User user = new User(2, "Arun3");
		UserServiceOne serviceOne = new UserServiceOne(userRepository);
		long prevCount = userRepository.count();
		serviceOne.save(user);
		assertEquals(prevCount + 1, userRepository.count());
	}
	
	@Test
	void saveUser_NoDuplicates() {
		User user = new User(1, "Arun3");
		List<User> users = userRepository.findAll();
		UserServiceOne serviceOne = new UserServiceOne(userRepository);
		serviceOne.save(user);
		users = userRepository.findAll();
		long prevCount = userRepository.count();
		serviceOne.save(user);
		users = userRepository.findAll();
		System.out.println("Success");
		assertEquals(prevCount+1, userRepository.count());
	}
	

}
