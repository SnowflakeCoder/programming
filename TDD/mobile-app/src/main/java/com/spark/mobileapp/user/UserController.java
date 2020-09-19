package com.spark.mobileapp.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserServiceOne userServiceOne;
	
	@GetMapping("/users")
	ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userServiceOne.getUsers(), HttpStatus.OK);
	}
	
	/*
	 * save.
	 * return 201 instead of 200
	 */
	@PostMapping("/users")
	ResponseEntity<User> newUser(@RequestBody User user){
		return new ResponseEntity<>(userServiceOne.save(user), HttpStatus.CREATED);
	}
	
	/*
	 * Find by id
	 */
	
	@GetMapping("/user/{id}")
	ResponseEntity<User> getUserById(@PathVariable Long id){
		return new ResponseEntity<>(userServiceOne.getById(id), HttpStatus.OK);
	}
	
	/*
	 * 
	 * save / update
	 */
	
	@PutMapping("/user/{id}")
	ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
		return new ResponseEntity<>(userServiceOne.update(user, id), HttpStatus.OK);
	}
	

	@PatchMapping("/user/{id}")
	ResponseEntity<User> patchUser(@RequestBody Map<String, String> update, @PathVariable Long id){
		return new ResponseEntity<>(userServiceOne.patch(update, id), HttpStatus.OK);
	}

	
	@DeleteMapping("/user/{id}")
	ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userServiceOne.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
