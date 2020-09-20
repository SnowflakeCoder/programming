package com.sbc.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/main")
public class MainApiController {
	
	@GetMapping("/testApi1")
	public String TestApi1() {
		return "TestApi1 Success";
	}
	
}
