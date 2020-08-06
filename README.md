# parkinglot

Requirements :
1. The parking lot should have multiple floors where customers can park their cars.
2. The parking has multiple entry and exit points.
3. Customers can collect a parking ticket from the entry points.
4. The system should not allow more vehicles than the maximum capacity of the parking lot.
5. Each parking floor will have many parking spots.
6. Parking spots should be allocated top to bottom.
7. When multiple spots are available at same floor then the slot vacated first needs to be allocated first.

The main Actors are :
1. Customer - All customers can get a parking ticket.
2. System - assigns and removes a vehicle from a parking spot.

Use Case of the system :
1. Take ticket : To provide customers with a new parking ticket when entering the parking lot. Customers can enter from any point so same spot should not be allocated to different customers.

Class Diagram :
1. ParkingLot : The central part of the organization for which this software has been designed. It has a attribute ‘Name’ to distinguish it from any other parking lots.
2. ParkingFloor : The parking lot will have many parking floors.
3. ParkingSpot : Each parking floor will have many parking spots.
4. Parking Ticket : This class will encapsulate a parking ticket. Customers will take a ticket when they enter the parking lot.
5. Vehicle : Vehicles will be parked in the parking spots.

The project uses following libraries:
1. JAVA 1.8
2. MAVEN 3.5.2
3. JUNIT 4.12
4. HARCREST 1.3

Run the test cases in following ways :
1. Execute the maven command "mvn clean test". The output will be shown on terminal/command prompt.
2. Extract execute-tests.zip at project root folder and double click on the extracted execute-tests.bat file. The output will be saved in file test-logs.txt at project root directory.