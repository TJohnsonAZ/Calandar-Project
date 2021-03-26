package DayPackage;

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

	private static final String file = "DayData.csv";
		
	@PreDestroy
	public void writeToCSV() {
			
		try {
				
			FileWriter outputFile = new FileWriter(file);
			
			CSVWriter writer = new CSVWriter(outputFile);
			
			String[] header = {"Day of Year", "Default Color", "Incomplete Color", "Complete Color", "Set Color"};
			writer.writeNext(header);
				
			for(DayData dayData : DayDatabase.getAllDayData()) {
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
				DayData newDayData = new DayData(row[0], row[1], row[2], row[3], row[4]);
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
