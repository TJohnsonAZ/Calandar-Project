package com.adam.Server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/")
	public String helloAgain() {
		return "Hello!\n";
	}
	
	@GetMapping("/HelloWorld")
	public String helloWorld() {
		return "Hello World!\n";
	}
	
	@GetMapping("/{name}")
	public String hello(@PathVariable("name") String name) {
		return "Hello " + name + "!\n";
	}
	
}
