
package com.CalandarProject.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {
	public static Calendar calendar = new Calendar();
	
	@GetMapping("/getMonth")
	public Month nextMonth( @RequestParam(value = "monthName", defaultValue = "JANUARY") String monthName,
							@RequestParam( value = "year", defaultValue = "2020" ) int year ) {
		return calendar.findMonth( monthName, year );
	}
	
}