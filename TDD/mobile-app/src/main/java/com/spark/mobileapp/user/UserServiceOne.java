package com.spark.mobileapp.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spark.mobileapp.user.error.UserNotFoundException;
import com.spark.mobileapp.user.error.UserUnSupportedPatchException;

@Service
public class UserServiceOne {

	private UserRepository userRepository;
	
	public UserServiceOne(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User getById(long id) {
		return userRepository.getOne(id);
	}
	
	public User update(User user, long id) {
		User currentUser = getById(id);
		
		if(currentUser == null) {
			user.setId(id);
			currentUser = user;
		}
		else {
			currentUser.setName(user.getName());
		}
		return userRepository.save(currentUser);
	}
	
	public User patch(Map<String, String> update, long id) {
		User currentUser = getById(id);
		
		if(currentUser == null) {
			throw new UserNotFoundException(id);
		}
		else {
			String name = update.get("name");
			if(StringUtils.isEmpty(name)) {
				throw new UserUnSupportedPatchException(update.keySet());
			}
			currentUser.setName(name);
			return userRepository.save(currentUser);
		}
	}
	
	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

}
