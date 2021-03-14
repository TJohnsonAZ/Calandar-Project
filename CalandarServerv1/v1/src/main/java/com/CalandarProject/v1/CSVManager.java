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
			
			String[] header = { "Event ID", "Event Color", "Event Name" };
			writer.writeNext(header);
			
			for(int i = 0; i < 10; i++) {
				ActivityDatabase.addEvent(new Event("green", "Test Event " + i));
			}
			
			for(Event event : ActivityDatabase.getAllEvents()) {
				writer.writeNext(event.toStringArray());
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
				Event newEvent = new Event(row[0], row[1], row[2]);
				System.out.println(newEvent);
				ActivityDatabase.addEvent(newEvent);
			}
			
			csvReader.close();
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 
 
}
