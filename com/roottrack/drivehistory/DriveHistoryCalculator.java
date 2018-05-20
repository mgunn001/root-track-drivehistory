package com.roottrack.drivehistory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DriveHistoryCalculator {

	public static HashMap<String, DriveHistory> hmOfDriveHistory;
	public static List<String> _return;

	public static List<String> calculate(String inputFilePath) {

		hmOfDriveHistory = new HashMap<String, DriveHistory>();
		_return = new ArrayList<>();

		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(inputFilePath);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				// current line will be of the pattern "Driver Bod" || "Trip Dan 07:15 07:45 17.3"
				String words[] = line.split(" ");
				if (words[0].equals("Driver")) {
					addDriverInToSystem(words[1]);
				} else if (words[0].equals("Trip")) {
					addDriversTrip(words[1], words[2], words[3], words[4]);
				} else {
					_return.add("Error in the input file");
				}

			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} catch (IOException e) {

			System.err.println("File Exception - "+e.getMessage());
			
		}

		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (sc != null) {
				sc.close();
			}
		}

		printDrivingHistories();

		Iterator<String> _resultterator = _return.iterator();
		while (_resultterator.hasNext()) {
			System.out.println(_resultterator.next());
		}

		return _return;

	}

	// takes care of adding the driver trip in to the system
	public static void addDriversTrip(String driverName, String startTime, String endTime, String tripdistance) {

		try {
			if (hmOfDriveHistory.get(driverName) == null) {

				_return.add("Driver entry associated with the trip isn't found -> " + driverName);

			} else {

				DriveHistory curDriversDriveHist = hmOfDriveHistory.get(driverName);
				TripEntry tripObj = new TripEntry(startTime, endTime, tripdistance);
				float newTotalDistance = curDriversDriveHist.getCurTotalDistance()
						+ Float.valueOf(tripdistance).floatValue();
				int newTotalTime = curDriversDriveHist.getCurTotalTime() + tripObj.getTripTimeinMinutes();
				int newAvgSpeed = Math.round((newTotalDistance / newTotalTime) * DriveHistoryConstants.Sec_In_Minute);

				if (newAvgSpeed < 5 || newAvgSpeed > 100) {
					(curDriversDriveHist.getInValidTripsList()).add(tripObj);

				} else {
					(curDriversDriveHist.getValidTripsList()).add(tripObj);
					curDriversDriveHist.setCurTotalDistance(newTotalDistance);
					curDriversDriveHist.setCurTotalTime(newTotalTime);

				}
			}
		} catch (NumberFormatException e) {
			_return.add("Invalid Data - " + e.getMessage());
		} catch (InvalidTimeException ie) {
			_return.add(ie.getMessage());
		}
	}

	// Method to print the Driving summary of each of the Drivers in the system
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void printDrivingHistories() {

		List<DriveHistory> driveHistoriesList = new ArrayList(hmOfDriveHistory.values());
		Collections.sort(driveHistoriesList, new DriveHistoryDistanceComparator());
		for (DriveHistory drivingHist : driveHistoriesList) {
			int avgSpeed = Math.round((drivingHist.getCurTotalDistance() / drivingHist.getCurTotalTime())
					* DriveHistoryConstants.Sec_In_Minute);
			String driveNameAndMilesToPrint = drivingHist.getDriverName() + ": "
					+ Math.round(drivingHist.getCurTotalDistance()) + " miles";
			String avgSpeedToPrint = "";
			if (Math.round(drivingHist.getCurTotalDistance()) == 0) {
				avgSpeedToPrint = "";
			} else {
				avgSpeedToPrint = " @ " + avgSpeed + " mph";
			}

			_return.add(driveNameAndMilesToPrint + avgSpeedToPrint);
		}

	}

	// takes care of adding the driver in to the system for the first time
	public static void addDriverInToSystem(String driverName) {

		if (hmOfDriveHistory.get(driverName) == null) {
			DriveHistory driveHistoryObj = new DriveHistory();
			driveHistoryObj.setDriverName(driverName);
			hmOfDriveHistory.put(driverName, driveHistoryObj);
		} else {
			_return.add("Duplicate Entry for the driver " + driverName);
		}
	}

}
