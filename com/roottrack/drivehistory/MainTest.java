package com.roottrack.drivehistory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

	@Test
	public void whenOnlyDriverEntriesAreFound() {

		
		String inputFilePath1 = DriveHistoryConstants.filePath+"input1.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Alex: 0 miles");
		_expectedOutput.add("Maheedhar: 0 miles");
		_expectedOutput.add("NewDriver: 0 miles");
		
		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath1));

		
		String duplicateRecords = DriveHistoryConstants.filePath+"input1-1.txt";
		
		_expectedOutput = new ArrayList<>();
		_expectedOutput.add("Duplicate Entry for the driver Alex");
		_expectedOutput.add("Duplicate Entry for the driver Alex");
		_expectedOutput.add("Alex: 0 miles");
		_expectedOutput.add("Bob: 0 miles");
		
		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(duplicateRecords));

	}
	


	@Test
	public void singleDriverWithSingleTrip() {

		String inputFilePath2 = DriveHistoryConstants.filePath+"input2.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Maheedhar: 17 miles @ 35 mph");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath2));

	}

	@Test
	public void multipleEntriesForASingleDriver() {
		
		String inputFilePath3 = DriveHistoryConstants.filePath+"input3.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Maheedhar: 50 miles @ 33 mph");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath3));

	}

	@Test
	public void whenThereAreMixedEntries() {
		
		String inputFilePath4 = DriveHistoryConstants.filePath+"input4.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Driver entry associated with the trip isn't found -> Maheedhar");
		_expectedOutput.add("Driver entry associated with the trip isn't found -> Maheedhar");
		_expectedOutput.add("Alex: 42 miles @ 34 mph");
		_expectedOutput.add("Jhon: 42 miles @ 34 mph");
		_expectedOutput.add("Bob: 0 miles");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath4));
		
	}

	@Test
	public void whenThereAreExtremelySlowOrFastTrips() {

		String inputFilePath5 = DriveHistoryConstants.filePath+"input5.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Dan: 111 miles @ 35 mph");
		_expectedOutput.add("Alex: 42 miles @ 34 mph");
		_expectedOutput.add("Bob: 0 miles");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath5));
		
	}
	
	@Test
	public void inValidTimeStrings() {

		String inputFilePath6 = DriveHistoryConstants.filePath+"input6.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Invalid Data - For input string: \"06s\"");
		_expectedOutput.add("Dan: 89 miles @ 31 mph");
		_expectedOutput.add("Alex: 42 miles @ 34 mph");
		_expectedOutput.add("Bob: 0 miles");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath6));
		
	}
	
	@Test
	public void extremelySlowOrFast() {

		String inputFilePath7 = DriveHistoryConstants.filePath+"input7.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Driver entry associated with the trip isn't found -> Mahee");
		_expectedOutput.add("Maheedhar: 21 miles @ 31 mph");
		_expectedOutput.add("Bob: 11 miles @ 31 mph");
		_expectedOutput.add("Al: 0 miles");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath7));
		
	}

}
