package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AirplaneDatabase {

	public static void main(String[] args) {
        try {
            // Database connection properties
            String jdbcUrl = "jdbc:mysql://localhost:3306/AirplaneDatabase";
            String username = "root";
            String password = "password";
            
            // Establish a database connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a Statement object
            Statement statement = connection.createStatement();
            
            // Create the Aircraft table
            String createAircraftTable = "CREATE TABLE Aircraft ("
                    + "AircraftID INT AUTO_INCREMENT PRIMARY KEY, "
                    + "AircraftName VARCHAR(50), "
                    + "ManufacturingDate DATE"
                    + ")";
            statement.execute(createAircraftTable);
            
            // Create the Flights table
            String createFlightsTable = "CREATE TABLE Flights ("
                    + "FlightID VARCHAR(6) PRIMARY KEY, "
                    + "Origin VARCHAR(50), "
                    + "Destination VARCHAR(50), "
                    + "DepartureDate DATE, "
                    + "SeatMap VARCHAR(255),"
                    + "AircraftID INT"
                    + ")";
            statement.execute(createFlightsTable);

            // Create the Seats table
            String createSeatsTable = "CREATE TABLE Seats ("
                    + "SeatID INT AUTO_INCREMENT PRIMARY KEY, "
                    + "FlightID VARCHAR(6), "
                    + "SeatType VARCHAR(20), "
                    + "Price DECIMAL(10, 2), "
                    + "FOREIGN KEY (FlightID) REFERENCES Flights(FlightID) ON DELETE CASCADE"
                    + ")";
            statement.execute(createSeatsTable);

            // Create the Users table
            String createUsersTable = "CREATE TABLE Users ("
                    + "UserID INT AUTO_INCREMENT PRIMARY KEY, "
                    + "Username VARCHAR(50), "
                    + "Password VARCHAR(50), "
                    + "Email VARCHAR(50), "
                    + "MembershipStatus BOOLEAN"
                    + ")";
            statement.execute(createUsersTable);

            // Create the Bookings table
            String createBookingsTable = "CREATE TABLE Bookings ("
                    + "BookingID VARCHAR(6) PRIMARY KEY, "
                    + "UserID INT, "
                    + "FlightID VARCHAR(6), "
                    + "SeatID INT, "
                    + "Insurance BOOLEAN, "
                    + "PaymentStatus BOOLEAN, "
                    + "FOREIGN KEY (UserID) REFERENCES Users(UserID), "
                    + "FOREIGN KEY (FlightID) REFERENCES Flights(FlightID) ON DELETE CASCADE,"
                    + "FOREIGN KEY (SeatID) REFERENCES Seats(SeatID) ON DELETE CASCADE"
                    + ")";
            statement.execute(createBookingsTable);

            // Create the Admins table
            String createAdminsTable = "CREATE TABLE Admins ("
                    + "AdminID INT AUTO_INCREMENT PRIMARY KEY, "
                    + "Username VARCHAR(50), "
                    + "Password VARCHAR(50)"
                    + ")";
            statement.execute(createAdminsTable);
           
            // Create the Airline Staff table
            String createAirlineStaffTable = "CREATE TABLE AirlineStaff ("
                    + "StaffID INT AUTO_INCREMENT PRIMARY KEY, "
                    + "Name VARCHAR(50), "
                    + "Username VARCHAR(50), "
                    + "Password VARCHAR(50), "
                    + "FlightID VARCHAR(6), "
                    + "FOREIGN KEY (FlightID) REFERENCES Flights(FlightID)"
                    + ")";
            statement.execute(createAirlineStaffTable);
            
         // Create the Registered_users table
            String createRegisteredUsersTable = "CREATE TABLE RegisteredUsers ("
                + "UserID INT PRIMARY KEY, "
                + "Name VARCHAR(100), "
                + "Address VARCHAR(255), "
                + "FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE"
                + ")";
            statement.execute(createRegisteredUsersTable);
            
            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("Airplane database setup completed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}