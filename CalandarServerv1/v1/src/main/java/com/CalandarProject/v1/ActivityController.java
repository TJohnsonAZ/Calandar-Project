package com.CalandarProject.v1;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

	

	@GetMapping("/activity")
	public Activity activity(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Activity();
	}
}

