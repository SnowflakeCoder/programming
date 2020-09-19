package com.spark.mobileapp;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.spark.mobileapp.user.UserServiceOne;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spark.mobileapp.user.User;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private UserServiceOne serviceOne;

	@Test
	void getUsers_NotEmpty() throws Exception {

		List<User> users = new ArrayList<>();
		users.add(new User(1, "Arun1"));
		users.add(new User(2, "Arun2"));

		Mockito.when(serviceOne.getUsers()).thenReturn(users);

		mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(2))).andDo(print());

	}
	
	@Test
	void saveUser_Success() throws Exception {
		User user = new User(1, "Arun");
		Mockito.when(serviceOne.save(Mockito.any(User.class))).thenReturn(user);
		
//		Mockito.when(serviceOne.save(Mockito.any(User.class))).thenReturn(Boolean.TRUE);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);  
		
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(json));
		resultActions.andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arun"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
		
	}

}
