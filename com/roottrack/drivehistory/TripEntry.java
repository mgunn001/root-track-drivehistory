package com.roottrack.drivehistory;

public class TripEntry {

	private float distance; // Distance in miles read as it is from input file
	private String startTime; // Time taken for the current trip in minutes
	private String endTime; // Time taken for the current trip in minutes

	public TripEntry(String startTime, String endTime, String tripdistance) { // constructor
		this.distance = Float.parseFloat(tripdistance);
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public float getDistance() {
		return this.distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	// got to organize better, also take care of validatation that start time but be
	// les than endtime, might have tpo use try catch blocks
	// logic to return the timein minutes (StartTime - EndTime)
	public int getTripTimeinMinutes() throws NumberFormatException {
		
		String startTimeHourAndMin[] = startTime.split(":");// hour at 0 index, Min at 1 index eg startTimeFormat: 17:05
		String endTimeHourAndMin[] = endTime.split(":");
		int curTripTime = 0;
		
		try {
			if (Integer.parseInt(startTimeHourAndMin[0]) > Integer.parseInt(endTimeHourAndMin[0])) {
				// got to implement throw exception as this is invalid, startTime can never be
				// greater than end time

			} else {
				curTripTime = (Integer.parseInt(endTimeHourAndMin[0]) - Integer.parseInt(startTimeHourAndMin[0]))
						* DriveHistoryConstants.Sec_In_Minute;
				curTripTime += Math.abs(Integer.parseInt(endTimeHourAndMin[1]) - Integer.parseInt(startTimeHourAndMin[1]));
			}
		} catch (NumberFormatException e) {
			throw e;
		}
		
		return curTripTime;
	}

}
