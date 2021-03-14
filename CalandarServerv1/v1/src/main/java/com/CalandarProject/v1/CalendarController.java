
package com.CalandarProject.v1;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {
	
	@GetMapping("/getMonth")
	public Month nextMonth( @RequestParam(value = "monthName", defaultValue = "January") String monthName,
							@RequestParam( value = "year", defaultValue = "2020" ) int year ) {
		return V1Application.calendar.findMonth( monthName, year );
	}
	
}