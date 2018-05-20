package com.roottrack.drivehistory;

public class Main {

	public static void main(String[] args) {
		
		
		if(args.length != 0){
			DriveHistoryCalculator.calculate(args[0]);
		} else {
			String inputFilePath = DriveHistoryConstants.filePath+"input.txt";
			DriveHistoryCalculator.calculate(inputFilePath);
		}
		

	}

}

// java -cp . com.roottrack.drivehistory.Main