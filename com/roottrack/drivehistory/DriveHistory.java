package com.roottrack.drivehistory;

import java.util.ArrayList;
import java.util.Comparator;

public class DriveHistory {
	
	 // all drive history
		String driverName;
		ArrayList<TripEntry> validTripsList = new ArrayList<TripEntry>(); // contains only the trips considered, avoiding
																			// the edge cases <5 and >100 avg speed
		ArrayList<TripEntry> invalidTripsList = new ArrayList<TripEntry>(); // trips which yeilds <5mph and >100mph avg
																			// speed when included
		float curTotalDistance = 0f; // In Miles, gives the snapshot of the total distance of the drive
		int curTotalTime = 0; // snapshot of the toalTime in minutes for the valid trips considered

		// getter Setters goes here

		public String getDriverName() {
			return driverName;
		}

		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}

		public float getCurTotalDistance() {
			return curTotalDistance;
		}

		public void setCurTotalDistance(float curTotalDistance) {
			this.curTotalDistance = curTotalDistance;
		}

		public int getCurTotalTime() {
			return curTotalTime;
		}

		public void setCurTotalTime(int curTotalTime) {
			this.curTotalTime = curTotalTime;
		}

		public ArrayList<TripEntry> getValidTripsList() {
			return validTripsList;
		}

		public ArrayList<TripEntry> getInValidTripsList() {
			return invalidTripsList;
		}

		public void addNewTripDistance(float curTripDistance) {
			this.curTotalDistance += curTripDistance;
		}

		public void addNewTripTime(float curTripTime) {
			this.curTotalTime += curTotalTime;
		}

		public void addValidTripEntry(TripEntry tripObj) {
			validTripsList.add(tripObj);
		}

		public void addInvalidTripEntry(TripEntry tripObj) {
			invalidTripsList.add(tripObj);
		}

}

class DriveHistoryDistanceComparator implements Comparator<DriveHistory> {
	public int compare(DriveHistory obj1, DriveHistory obj2) {
		return Math.round(obj2.getCurTotalDistance() - obj1.getCurTotalDistance());
	}
}