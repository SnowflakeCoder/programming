package com.sbc.api.ratelimit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sbc.api.ratelimit.config.Constants;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class MainAPIControllerTest {

	@MockBean
	private RateLimitRepository rateLimitRepository;

	@MockBean
	private RateLimitService rateLimitService;

	@AfterEach
	void tearDown() {
		rateLimitRepository.deleteAll();
	}

	@Autowired
	MockMvc mockMvc;

	@Test
	void rateLimitEmptyConfigTest() throws Exception {
		ResultActions result = mockMvc
				.perform(MockMvcRequestBuilders.get("/main/testApi1").contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isTooManyRequests());
	}

	@Test
	void rateLimitSingleRequestSuccessTest() throws Exception {

		when(rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1"))
				.thenReturn(new RateLimit(TimeUnit.SECONDS, 1, 10, "/main/testApi1", "testClient"));

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/main/testApi1")
				.contentType(MediaType.APPLICATION_JSON).header("Client-Id", "testClient"));
		result.andExpect(status().isOk());

		RateLimit rateLimit = rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1");
		assertEquals(rateLimit.getAllowedPermits(), 1);

	}

	@Test
	void rateLimitDefaultRequestSuccessTest() throws Exception {

		when(rateLimitService.getRateLimitByClientIdNApi(Constants.Default_Api_Name, Constants.Default_Client_Id))
				.thenReturn(new RateLimit(TimeUnit.SECONDS, 10, 10, Constants.Default_Api_Name,
						Constants.Default_Client_Id));

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/main/testApi1")
				.contentType(MediaType.APPLICATION_JSON).header("Client-Id", "testClient"));
		result.andExpect(status().isOk());
	}

	@Test
	void rateLimitMultipleRequestSuccessTest() throws Exception {

		when(rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1"))
				.thenReturn(new RateLimit(TimeUnit.SECONDS, 10, 2, "/main/testApi1", "testClient"));

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/main/testApi1")
				.contentType(MediaType.APPLICATION_JSON).header("Client-Id", "testClient"));
		result.andExpect(status().isOk());

		RateLimit rateLimit = rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1");
		assertEquals(rateLimit.getAllowedPermits(), 1);

		result = mockMvc.perform(MockMvcRequestBuilders.get("/main/testApi1").contentType(MediaType.APPLICATION_JSON)
				.header("Client-Id", "testClient"));
		result.andExpect(status().isOk());

		rateLimit = rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1");
		assertEquals(rateLimit.getAllowedPermits(), 2);

	}
	
	@Test
	void rateLimitMultipleRequestFailureTest() throws Exception {

		when(rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1"))
				.thenReturn(new RateLimit(TimeUnit.SECONDS, 10, 1, "/main/testApi1", "testClient"));

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/main/testApi1")
				.contentType(MediaType.APPLICATION_JSON).header("Client-Id", "testClient"));
		result.andExpect(status().isOk());

		RateLimit rateLimit = rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1");
		assertEquals(rateLimit.getAllowedPermits(), 1);

		result = mockMvc.perform(MockMvcRequestBuilders.get("/main/testApi1").contentType(MediaType.APPLICATION_JSON)
				.header("Client-Id", "testClient"));
		result.andExpect(status().isTooManyRequests());

		rateLimit = rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1");
		assertEquals(rateLimit.getAllowedPermits(), 1);

	}
	
	/*
	 * update will override previous values.
	 */
	
	@Test
	void rateLimitMultipleRequestWithUpdateTest() throws Exception {

		when(rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1"))
				.thenReturn(new RateLimit(TimeUnit.SECONDS, 10, 1, "/main/testApi1", "testClient"));

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/main/testApi1")
				.contentType(MediaType.APPLICATION_JSON).header("Client-Id", "testClient"));
		result.andExpect(status().isOk());

		RateLimit rateLimit = rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1");
		assertEquals(rateLimit.getAllowedPermits(), 1);

		rateLimit.setMaxPermits(2);
		
		result = mockMvc.perform(MockMvcRequestBuilders.get("/main/testApi1").contentType(MediaType.APPLICATION_JSON)
				.header("Client-Id", "testClient"));
		result.andExpect(status().isOk());

		rateLimit = rateLimitService.getRateLimitByClientIdNApi("testClient", "/main/testApi1");
		assertEquals(rateLimit.getAllowedPermits(), 2);

	}

}
