
package com.CalandarProject.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {
	public static Calendar calendar = new Calendar();
	
	/**
	 * Gives the client a month to be displayed on the client's side
	 * @param monthName Name of the month to be found
	 * @param year Year of the month to be found 
	 * @return The month that was found as a JSON
	 * Note: adds new months to the calendar object as it needs
	 */
	@GetMapping("/getMonth")
	public Month nextMonth( @RequestParam(value = "monthName", defaultValue = "JANUARY") String monthName,
							@RequestParam( value = "year", defaultValue = "2020" ) int year ) {
		return calendar.findMonth( monthName, year );
	}
	
}