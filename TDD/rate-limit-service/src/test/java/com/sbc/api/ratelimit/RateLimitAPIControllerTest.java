package com.sbc.api.ratelimit;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class RateLimitAPIControllerTest {

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
	void getAllRateLimits() throws Exception {
		List<RateLimit> rateLimits = new ArrayList<RateLimit>();
		rateLimits.add(new RateLimit(TimeUnit.HOURS, 1, 10, "main/api1", "testClient1"));
		rateLimits.add(new RateLimit(TimeUnit.HOURS, 1, 10, "main/api2", "testClient2"));

		when(rateLimitRepository.findAll()).thenReturn(rateLimits);

		ResultActions result = mockMvc.perform(get("/rateLimits").contentType(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andDo(print());
	}

	@Test
	void getRateLimit() throws Exception {

		RateLimitId rateLimitId = new RateLimitId("main/api1", "testClient");

		when(rateLimitRepository.findById(rateLimitId))
				.thenReturn(Optional.ofNullable(new RateLimit(TimeUnit.HOURS, 1, 10, "main/api1", "testClient")));

		ObjectMapper objectMapper = new ObjectMapper();
		String rateLimitIdJSON = objectMapper.writeValueAsString(rateLimitId);
		ResultActions result = mockMvc
				.perform(post("/getRateLimits").contentType(MediaType.APPLICATION_JSON).content(rateLimitIdJSON));

		result.andExpect(status().isOk()).andExpect(jsonPath("$.rateLimitId").value(rateLimitId));
	}

	@Test
	void createRateLimit() throws Exception {
		RateLimit rateLimit = new RateLimit(TimeUnit.HOURS, 1, 10, "main/api1", "testClient");
		when(rateLimitRepository.save(any(RateLimit.class))).thenReturn(rateLimit);
		ObjectMapper objectMapper = new ObjectMapper();
		String rateLimitJSON = objectMapper.writeValueAsString(rateLimit);

		ResultActions result = mockMvc
				.perform(post("/rateLimits").contentType(MediaType.APPLICATION_JSON).content(rateLimitJSON));

		result.andExpect(status().isCreated()).andExpect(jsonPath("$.rateLimitId").value(rateLimit.getRateLimitId()));
	}

	@Test
	void deleteRateLimit() throws Exception {
		RateLimitId rateLimitId = new RateLimitId("main/api1", "testClient");
		doNothing().when(rateLimitRepository).deleteById(rateLimitId);

		ObjectMapper objectMapper = new ObjectMapper();
		String rateLimitIdJSON = objectMapper.writeValueAsString(rateLimitId);

		ResultActions result = mockMvc
				.perform(delete("/rateLimits").contentType(MediaType.APPLICATION_JSON).content(rateLimitIdJSON));

		result.andExpect(status().isOk());
	}

	@Test
	void updateRateLimitNotFound() throws Exception {
		RateLimit rateLimit = new RateLimit(TimeUnit.HOURS, 1, 10, "main/api1", "testClient");
		ObjectMapper objectMapper = new ObjectMapper();
		String rateLimitJSON = objectMapper.writeValueAsString(rateLimit);
		ResultActions result = mockMvc
				.perform(put("/rateLimits").contentType(MediaType.APPLICATION_JSON).content(rateLimitJSON));

		result.andExpect(status().isNotFound());
	}

	@Test
	void updateRateLimitSuccess() throws Exception {

		RateLimitId rateLimitId = new RateLimitId("main/api1", "testClient");
		RateLimit rateLimit = new RateLimit(TimeUnit.HOURS, 1, 10, "main/api1", "testClient");
		when(rateLimitRepository.findById(rateLimitId)).thenReturn(Optional.ofNullable(rateLimit));

		when(rateLimitRepository.save(any(RateLimit.class))).thenReturn(rateLimit);
		ObjectMapper objectMapper = new ObjectMapper();
		String rateLimitJSON = objectMapper.writeValueAsString(rateLimit);

		ResultActions result = mockMvc
				.perform(put("/rateLimits").contentType(MediaType.APPLICATION_JSON).content(rateLimitJSON));

		result.andExpect(status().isOk()).andExpect(jsonPath("$.rateLimitId").value(rateLimitId));

	}

}
