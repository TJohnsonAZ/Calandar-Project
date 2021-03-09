package com.CalandarProject.v1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

public class CSVManager {

	/**
	 * Line by Line writing
	 * could be edited to overwrite specific lines
	 */
	public static void writeToCSV(String filePath) {
		File file = new File(filePath);
		
		try {
			FileWriter outputFile = new FileWriter(file);
			
			CSVWriter writer = new CSVWriter(outputFile);
			
			String[] header = { "Event ID", "Event Color", "Event Name" };
			writer.writeNext(header);
			
			String[] event1 = { "1", "Blue", "Jog" };
			writer.writeNext(event1);
			String[] event2 = { "2", "Purple", "Laundry" };
			writer.writeNext(event2);
			
			writer.close();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// Java code to illustrate reading a 
	// all data at once 
	public static void readAllDataAtOnce(String file) { 
		try { 
			// Create an object of file reader 
			// class with CSV file as a parameter. 
			FileReader filereader = new FileReader(file); 

			// create csvReader object and skip first Line 
			CSVReader csvReader = new CSVReaderBuilder(filereader) 
									.withSkipLines(1) 
									.build(); 
			List<String[]> allData = csvReader.readAll(); 

			// print Data 
			for (String[] row : allData) { 
				for (String cell : row) { 
					System.out.print(cell + "\t"); 
				} 
				System.out.println(); 
			} 
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 

}
