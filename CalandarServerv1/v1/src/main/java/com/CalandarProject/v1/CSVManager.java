package com.CalandarProject.v1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

@Component
public class CSVManager {

	private static final String file = "backupData.csv";
	
	@PreDestroy
	public void writeToCSV() {
		
		try {
			
			FileWriter outputFile = new FileWriter(file);
			
			CSVWriter writer = new CSVWriter(outputFile);
			
			String[] header = { "Activity ID", "Activity Color", "Activity Name, Activity Description, Activity Complete" };
			writer.writeNext(header);
			
			for(Activity activity : ActivityDatabase.getAllEvents()) {
				writer.writeNext(activity.toStringArray());
			}
			
			writer.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void readAllDataAtOnce() { 
		try {
			
			FileReader filereader = new FileReader(file); 

			CSVReader csvReader = new CSVReaderBuilder(filereader) 
									.withSkipLines(1) 
									.build(); 
			List<String[]> allData = csvReader.readAll(); 
			
			for(String[] row : allData) {
				Activity newActivity = new Activity(row[0], row[1], row[2], row[3], row[4]);
				System.out.println(newActivity);
				ActivityDatabase.addActivity(newActivity);
			}
			
			csvReader.close();
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
 
}
