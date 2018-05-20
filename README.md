# root-track-drivehistory

Simple Assessment test
Assignment form Root to track the drivers drive history

### Approach
The given is a simple problem, but one's expertise can be evaluated looking at the usage of data structure, code base organisation, and how less is achieved  computational run-time complexity.

My approach is to quickly come up with a solution which is effective and works good for the happy path (kind of POC), then give a thought about different scenarios which would fail the system bascially writing the test cases and code accrodingly to get a success for every case hence following Test Driven Development Approach. My commits order for this repo shows the same.

I have followed the OOPs concepts wherever applicable to better segregate the code which could then be more scalable, reusable and readable.

I wanted to optimize run-time of the code,  HashMap is used for the same. HashMap<String, DriveHistory> where the drivername acts as the key, using which the drive history of a particular driver can be reached in O(1).

Every time a trip entry is found in the input file, the Driver name which acts as the key is searched in the HashMap, the drive history at the moment of the corresponding driver is fetched, then a new trip entry can be appeneded to the existing drive history or discarded based on the business logic (valid only when : Avg speed >5 mph or Avg Speed <100mph). When a trip entry satisfies the condition, this new trip entry is appended to the validTripsList and trip distance is added to existing TotalDistance and the current trip time is added to the TotalTime for this particular driver.


### Highlevel view of  core Data structure in use 

```
HashMap<String driverName, DriveHistory driverHist>
DriveHistory:  <String driverName, ArrayList<TripEntry> validTripsList, TotalDistance, TotalTime>
TripEntry: <Distance, startTime and endTime>
```


The coding standards and the naming conventions helps understand the code easily to a new person working on this code base, In addition the comments explaining the functionality at places needed makes the code even more comprehensable.


### Setup
1. Make sure java version "1.8.0_171" is installed.
2. Download JUnit4 jar and added to Java Build path to run the tests


### Running the Tests

1. root-track-drivehistory used JUnit 4 as test framework.
2. To Run the test cases, execute MainTest.java by choosing the run as JUnit Test that appears on right click.


### Usage

#### Through command prompt
The Main method accepts an optional input filename as the argument from stdin. Please follow the below steps.
1. Open the command prompt and move to the project directory "root-track-drivehistory".
2. Execute the Main method, which is the entry point of root-track-drivehistory using following commands:
	
```
javac -cp . com.roottrack.drivehistory.Main.java
java -cp . com.roottrack.drivehistory.Main [input file path]
```
	
3. If the optional argument [input file path] is not passed, then the Main method functions on the default input file present at "/root-track-drivehistory/InputFiles/input.txt"
	
#### Through Eclipse or other IDE
1. Clone this project from git, Use the option of "Import the existing project into workspace" if Eclipse is the IDE in use.
2. Right click on the Main.java file shown on the Package Explorer and click on run as Java Application to see the output on the console, remember that the contents on the `/root-track-drivehistory/InputFiles/input.txt` is processed by default.
	
	

### Problem Statement

Let's write some code to track driving history for people.

The code will process an input file. You can either choose to accept the input via stdin (e.g. if you're using Ruby `cat input.txt | ruby yourcode.rb`), or as a file name given on the command line (e.g. ruby yourcode.rb input.txt). You can use any programming language that you want. Please choose a language that allows you to best demonstrate your programming ability.

Each line in the input file will start with a command. There are two possible commands.

The first command is Driver, which will register a new Driver in the App. Example:

`Driver Dan`

The second command is Trip, which will record a trip attributed to a driver. The line will be space delimited with the following fields: the command (Trip), driver name, start time, stop time, miles driven. Times will be given in the format of hours:minutes. We'll use a 24-hour clock and will assume that drivers never drive past midnight (the start time will always be before the end time). Example:

`Trip Dan 07:15 07:45 17.3`

Discard any trips that average a speed of less than 5 mph or greater than 100 mph.

Generate a report containing each driver with total miles driven and average speed. Sort the output by most miles driven to least. Round miles and miles per hour to the nearest integer.

Example input:

```
Driver Dan
Driver Alex
Driver Bob
Trip Dan 07:15 07:45 17.3
Trip Dan 06:12 06:32 21.8
Trip Alex 12:01 13:16 42.0

```

Expected output:

```
Alex: 42 miles @ 34 mph
Dan: 39 miles @ 47 mph
Bob: 0 miles

```


