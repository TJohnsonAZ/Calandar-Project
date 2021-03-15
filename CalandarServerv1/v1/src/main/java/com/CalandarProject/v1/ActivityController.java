package com.CalandarProject.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ActivityController {

	
	@GetMapping("/activity")
	public Activity activity( @RequestParam( value = "id", defaultValue = "" ) String id,
							  @RequestParam( value = "name", defaultValue = "" ) String name,
							  @RequestParam( value = "color", defaultValue = "red" ) String color,
							  @RequestParam( value = "description", defaultValue = "" ) String description, 
							  @RequestParam( value = "completionStatus", defaultValue = "false" ) String complete ) {
		Activity newActivity = new Activity(name, color, description, complete);
		ActivityDatabase.addActivity(newActivity);
		return newActivity;
	}
}

