package com.CalandarProject.v1.DayData;

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
public class DayDataCsv {

	private static final String file = "dayData.csv";
		
	@PreDestroy
	public void writeToCSV() throws IOException {
		
		
		FileWriter outputFile = new FileWriter(file);
		
			try {
				
				CSVWriter writer = new CSVWriter(outputFile);
				
				String[] header = {"UserID", "Day of Year", "Activity1 Status", "Activity2 Status", "Activity3 Status", "Activity4 Status"};
				writer.writeNext(header);
					
				for(DayData dayData : DayDatabase.getAllDayData() ) {
					writer.writeNext(dayData.toStringArray());
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
				DayData newDayData = new DayData(row[0], row[1], row[2], row[3], row[4], row[5]);
				System.out.println(newDayData);
				DayDatabase.addDayData(newDayData);
			}
				
			csvReader.close();
		}
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 	 
}
