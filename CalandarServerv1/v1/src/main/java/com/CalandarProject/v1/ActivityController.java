package com.CalandarProject.v1;

import java.util.concurrent.atomic.AtomicLong;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ActivityController {

	@GetMapping("/activity")
	public Activity activity( @RequestParam( value = "name", defaultValue = "New Activity" ) String name,
							  @RequestParam( value = "color", defaultValue = "0x000000" ) int color,
							  @RequestParam( value = "endColor", defaultValue = "0xFFFFFF" ) int endColor,
							  @RequestParam( value = "units", defaultValue = "" ) String units, 
							  @RequestParam( value = "daysBetween", defaultValue = "1" ) int daysBetween ) {
		Counter newCounter = new Counter( units, daysBetween );
		Activity newActivity = new Activity( name, color, endColor, newCounter );
		newActivity.saveActivity();
		return newActivity;
	}
}

