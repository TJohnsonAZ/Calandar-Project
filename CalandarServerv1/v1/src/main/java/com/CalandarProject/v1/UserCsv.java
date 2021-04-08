package com.CalandarProject.v1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

public class UserCsv {
	
	private static final String file = "Users.csv";
	
	@PreDestroy
	public void writeToCSV() {
			
		try {
				
			FileWriter outputFile = new FileWriter(file);
			
			CSVWriter writer = new CSVWriter(outputFile);
			
			String[] header = {"Username", "UserID"};
			writer.writeNext(header);
				
			for( User user : UserDatabase.getAllUsers() ) {
				writer.writeNext(user.toStringArray());
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
				
			FileReader filereader = new FileReader( file ); 

			CSVReader csvReader = new CSVReaderBuilder( filereader ) 
									.withSkipLines(1) 
									.build(); 
			List<String[]> allData = csvReader.readAll(); 
			
			for( String[] row : allData ) {
				User newUser = new User( row[0], row[1] );
				System.out.println( newUser );
				UserDatabase.addUser( newUser );
			}
				
			csvReader.close();
		}
		catch ( Exception e ) { 
			e.printStackTrace(); 
		} 
	}
}
