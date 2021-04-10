package com.CalandarProject.v1;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DayDataController {

	// Create
	@PostMapping("/dayData")
	public DayData createDayData(@RequestBody DayData newDayData) {
		DayDatabase.addDayData(newDayData);
		return newDayData;
	}
	
	// retrieve
	@GetMapping("/dayData")
	public Object retrieveDayData() {
		return DayDatabase.getAllDayData();
	}
	
	@GetMapping("/userDayData")
	public List<DayData> retrieveUserDayData(@RequestParam(value = "userID", required = true) String userId) {
		return DayDatabase.getDayDataByUser(userId);
	}
	
	// update
	@PutMapping("/dayData")
	public DayData updateDayData( @RequestParam(value = "dayNum", required = true ) String dayNum,
									@RequestBody DayData dayData) {
		
		DayData previousData = DayDatabase.getDayData(dayNum, dayData.getUser());
		if(dayData.getActivity1DayStatus().equals("-1")) {
			dayData.setActivity1DayStatus(previousData.getActivity1DayStatus());
		}
		if(dayData.getActivity2DayStatus().equals("-1")) {
			dayData.setActivity2DayStatus(previousData.getActivity2DayStatus());
		}
		if(dayData.getActivity3DayStatus().equals("-1")) {
			dayData.setActivity3DayStatus(previousData.getActivity3DayStatus());
		}
		if(dayData.getActivity4DayStatus().equals("-1")) {
			dayData.setActivity4DayStatus(previousData.getActivity4DayStatus());
		}
		return DayDatabase.updateDayData(dayNum, dayData);
	}
	
}
