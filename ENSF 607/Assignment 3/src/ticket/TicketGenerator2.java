package ticket;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;

public class TicketGenerator2 {
    public static void main(String[] args) {
        try {
            // Database connection parameters
            String JDBC_URL = "jdbc:mysql://localhost:3306/tickets";
            String USERNAME = "root";
            String PASSWORD = "root";

            // Establish a database connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create and populate tables
            createAndPopulateTables(connection);

            // Create the EventLog table if it doesn't exist
            createEventLogTable(connection);

            // Fetch values from the database for activities, origins, status, and classes
            List<String> activities = fetchDataFromDB(connection, "EventActivity", "ActivityName");
            List<String> origins = fetchDataFromDB(connection, "EventOrigin", "Origin");
            List<String> status = fetchDataFromDB(connection, "EventStatus", "Status");
            List<String> classes = fetchDataFromDB(connection, "EventClass", "Class");

            // Input parameters from the user
            int numTickets = getIntegerInput("Number of tickets to generate: ");
            Date startDate = getDateInput("Time window start date (yyyy-MM-dd): ");
            Date endDate = getDateInput("Time window end date (yyyy-MM-dd): ");

            // Generate and insert tickets into the EventLog table
            for (int i = 1; i <= numTickets; i++) {
                Date randomStartDate = generateRandomDate(startDate, endDate);
                Date randomEndDate = generateRandomDate(randomStartDate, endDate);

                insertTicket(connection, i, randomStartDate, randomEndDate, activities, origins, status, classes);
            }

            // Close the database connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getIntegerInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Consume the invalid input
            }
        }
        
        scanner.close();
        return value;
    }

    private static Date getDateInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        Date date = null;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = dateFormat.parse(input);
                validInput = true;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
        
        scanner.close();
        return date;
    }

    private static Date generateRandomDate(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long randomTime = startTime + (long) (Math.random() * (endTime - startTime));
        return new Date(randomTime);
    }

    private static void createAndPopulateTables(Connection connection) throws SQLException {
        String[][] tableInfo = {
            {"EventActivity", "ActivityName", "Design", "Construction", "Test", "PASSWORD Reset"},
            {"EventOrigin", "Origin", "Joe S.", "Bill B.", "George E.", "Achmed M.", "Rona E."},
            {"EventStatus", "Status", "Open", "On Hold", "In Process", "Deployed", "Deployed Failed"},
            {"EventClass", "Class", "Change", "Incident", "Problem", "SR"}
        };

        for (String[] info : tableInfo) {
            createAndPopulateTable(connection, info[0], info[1], info[2], info[3], info[4], info[5]);
        }
    }

    private static void createAndPopulateTable(Connection connection, String tableName, String columnName, String... values) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                + columnName + " VARCHAR(20)"
                + ")";
        
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
        }

        String insertValuesSQL = "INSERT INTO " + tableName + " (" + columnName + ") VALUES ";
        for (String value : values) {
            insertValuesSQL += "('" + value + "'), ";
        }
        insertValuesSQL = insertValuesSQL.substring(0, insertValuesSQL.length() - 2); // Remove the trailing comma and space

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertValuesSQL);
        }
    }

    private static void createEventLogTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS EventLog ("
                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                + "CaseID VARCHAR(20) UNIQUE,"
                + "Activity VARCHAR(20),"
                + "Urgency VARCHAR(1),"
                + "Impact VARCHAR(1),"
                + "Priority VARCHAR(1),"
                + "StartDate DATE,"
                + "EndDate DATE,"
                + "TicketStatus VARCHAR(20),"
                + "UpdateDateTime DATETIME,"
                + "Duration INT,"
                + "Origin VARCHAR(20),"
                + "Class VARCHAR(20)"
                + ")";
        
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
        }
    }

    private static void insertTicket(Connection connection, int ticketNumber, Date startDate, Date endDate,
                                      List<String> activities, List<String> origins, List<String> status, List<String> classes)
            throws SQLException {
        Random random = new Random();
        String caseId = "CS_" + ticketNumber;
        String activity = activities.get(random.nextInt(activities.size()));
        String origin = origins.get(random.nextInt(origins.size()));
        String ticketStatus = status.get(random.nextInt(status.size()));
        String ticketClass = classes.get(random.nextInt(classes.size()));
        String urgency = String.valueOf(random.nextInt(3) + 1);
        String impact = String.valueOf(random.nextInt(3) + 1);
        int priority = calculatePriority(Integer.parseInt(impact), Integer.parseInt(urgency));
        long duration = TimeUnit.MILLISECONDS.toHours(endDate.getTime() - startDate.getTime());

        String insertSQL = "INSERT INTO EventLog (CaseID, Activity, Urgency, Impact, Priority, StartDate, EndDate, TicketStatus, UpdateDateTime, Duration, Origin, Class) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, caseId);
            preparedStatement.setString(2, activity);
            preparedStatement.setString(3, urgency);
            preparedStatement.setString(4, impact);
            preparedStatement.setString(5, String.valueOf(priority));
            preparedStatement.setDate(6, new java.sql.Date(startDate.getTime()));
            preparedStatement.setDate(7, new java.sql.Date(endDate.getTime()));
            preparedStatement.setString(8, ticketStatus);
            preparedStatement.setLong(9, duration);
            preparedStatement.setString(10, origin);
            preparedStatement.setString(11, ticketClass);
            preparedStatement.executeUpdate();
        }
    }

    private static int calculatePriority(int impact, int urgency) {
        if (impact == 1 && urgency == 1) {
            return 1;
        } else if (impact == 2 && urgency == 1) {
            return 2;
        } else if (impact == 3 && urgency == 1) {
            return 3;
        } else if (impact == 1 && urgency == 2) {
            return 2;
        } else if (impact == 2 && urgency == 2) {
            return 3;
        } else if (impact == 3 && urgency == 2) {
            return 4;
        } else if (impact == 1 && urgency == 3) {
            return 3;
        } else if (impact == 2 && urgency == 3) {
            return 4;
        } else {
            return 5; // impact == 3 && urgency == 3
        }
    }

    private static List<String> fetchDataFromDB(Connection connection, String tableName, String columnName) throws SQLException {
        List<String> data = new ArrayList<>();
        String query = "SELECT " + columnName + " FROM " + tableName;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                data.add(resultSet.getString(columnName));
            }
        }
        return data;
    }
}
