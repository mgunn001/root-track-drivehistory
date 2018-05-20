package com.roottrack.drivehistory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

	@Test
	public void whenOnlyDriverEntriesAreFound() {

		/*
		 *  Has only Drivers info. No Trip Data.
		 */
		String inputFilePath1 = DriveHistoryConstants.filePath+"input1.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Alex: 0 miles");
		_expectedOutput.add("Maheedhar: 0 miles");
		_expectedOutput.add("NewDriver: 0 miles");
		
		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath1));

		
		/*
		 *  Duplicate driver records found in the input file.
		 */
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

		/*
		 *  Only one driver with one trip details.
		 */
		String inputFilePath2 = DriveHistoryConstants.filePath+"input2.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Maheedhar: 17 miles @ 35 mph");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath2));

	}

	@Test
	public void multipleEntriesForASingleDriver() {
		
		/*
		 *  Multiple entries for single driver.
		 */
		String inputFilePath3 = DriveHistoryConstants.filePath+"input3.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Maheedhar: 50 miles @ 33 mph");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath3));

	}

	@Test
	public void whenThereAreMixedEntries() {
		
		/*
		 *  Multiple drivers with multiple trip details.
		 */
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

		/*
		 *  Discards any trips that average a speed of less than 5 mph or greater than 100 mph.
		 */
		System.out.println("Discards any trips that average a speed of less than 5 mph or greater than 100 mph.");
		String inputFilePath5 = DriveHistoryConstants.filePath+"input5.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Dan: 134 miles @ 100 mph");
		_expectedOutput.add("Alex: 42 miles @ 34 mph");
		_expectedOutput.add("Bob: 0 miles");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath5));
		
	}
	
	@Test
	public void inValidTimeStrings() {

		/*
		 *  If Time has invalid strings. Example: 06d:45. 
		 *  If start time is greater than end time. 
		 */
		String inputFilePath6 = DriveHistoryConstants.filePath+"input6.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Invalid Data - For input string: \"06s\"");
		_expectedOutput.add("Invalid Time Exception. Start Time is greater than End Time.");
		_expectedOutput.add("Dan: 89 miles @ 31 mph");
		_expectedOutput.add("Alex: 0 miles");
		_expectedOutput.add("Bob: 0 miles");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath6));
		
	}
	
	@Test
	public void extremelySlowOrFast() {

		/*
		 * 	Driver name should be single word. If names has space, it wont consider.
		 */
		String inputFilePath7 = DriveHistoryConstants.filePath+"input7.txt";
		List<String> _expectedOutput = new ArrayList<>();
		_expectedOutput.add("Driver entry associated with the trip isn't found -> Mahee");
		_expectedOutput.add("Maheedhar: 21 miles @ 31 mph");
		_expectedOutput.add("Bob: 11 miles @ 31 mph");
		_expectedOutput.add("Al: 0 miles");

		Assert.assertEquals(_expectedOutput, DriveHistoryCalculator.calculate(inputFilePath7));
		
	}

}
