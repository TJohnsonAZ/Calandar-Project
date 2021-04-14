package com.CalandarProject.v1.User;

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
public class UserCsv {
	
	private static final String file = "Users.csv";
	
	/**
	 * Puts all of the current users into a csv to be saved. Runs on shutdown of the server.
	 */
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
	
	/**
	 * Reads all data from the user CSV and puts them into an active database. Runs on startup of the server. 
	 */
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
