package com.cloud.lc.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuChao
 * resetful 风格
 */
@RestController
public class TestController {
	
	@GetMapping("/hello")
	public String hello(){
		
		return "GET : hello ervery one !";
	}
	
	@PostMapping("/hello1")
	public String hello1(){
		
		return "POST : hello ervery one !";
	}
	
	
	@PutMapping("/hello2")
	public String hello2(){
		
		return "PUT : hello ervery one !";
	}
	
	
	
}
