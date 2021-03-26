package com.CalandarProject.v1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ActivityController {
	
	// Create
	@PostMapping("/activity")
	public Activity activity(@RequestBody Activity newActivity) {
		ActivityDatabase.addActivity(newActivity);
		return newActivity;
	}
	
	// retrieve
	@GetMapping("/activity")
	public Object retrieveActivity(@RequestParam(value = "id", required = false) String id) {
		if(id != null) {
		 	return ActivityDatabase.getActivity(id);	
		}
		return ActivityDatabase.getAllActivities();
	}
	
	// update
	@PutMapping("/activity")
	public Activity updateActivity( @RequestParam(value = "id", required = true ) String id,
									@RequestBody Activity activity) {
		return ActivityDatabase.updateActivity(id, activity);
	}
	
	// delete
	@DeleteMapping("/activity")
	public Activity removeActivity( @RequestParam(value = "id", required = true) String id) {
		return ActivityDatabase.deleteActivity(id);
	}
	
}
	

