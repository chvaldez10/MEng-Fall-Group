import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TicketGenerator {
    public static void main(String[] args) {
        try {
            // Database connection parameters
            String JDBC_URL = "jdbc:mysql://localhost:3306/tickets";
            String USERNAME = "root";
            String PASSWORD = "root";

            // Establish a database connection
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create the EventActivity, EventOrigin, EventStatus, and EventClass tables and populate them
            dropAndCreateTables(connection);

            // Create the EventLog table if it doesn't exist
            dropAndCreateEventLogTable(connection);

            // Fetch values from the database for activities, origins, status, and classes
            String[] activities = fetchDataFromDB(connection, "EventActivity", "ActivityName");
            String[] origins = fetchDataFromDB(connection, "EventOrigin", "Origin");
            String[] status = fetchDataFromDB(connection, "EventStatus", "Status");
            String[] classes = fetchDataFromDB(connection, "EventClass", "Class");

            // Input parameters from the user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Number of tickets to generate: ");
            int numTickets = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            System.out.print("Time window start date (yyyy-MM-dd): ");
            String startDateStr = scanner.nextLine();
            System.out.print("Time window end date (yyyy-MM-dd): ");
            String endDateStr = scanner.nextLine();
            scanner.close();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            // Generate and insert tickets into the EventLog table
            for (int i = 1; i <= numTickets; i++) {
                // Generate random start and end dates within the specified time window
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

    // Helper method to generate a random date within a given time window
    private static Date generateRandomDate(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long randomTime = startTime + (long) (Math.random() * (endTime - startTime));
        return new Date(randomTime);
    }


    private static void dropAndCreateTables(Connection connection) throws SQLException {
        // Drop EventActivity table if it exists
        String dropEventActivityTable = "DROP TABLE IF EXISTS EventActivity";
        String createEventActivityTable = "CREATE TABLE EventActivity ("
                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                + "ActivityName VARCHAR(20)"
                + ")";
        String insertEventActivityValues = "INSERT INTO EventActivity (ActivityName) VALUES "
                + "('Design'), "
                + "('Construction'), "
                + "('Test'), "
                + "('PASSWORD Reset')";

        // Drop EventOrigin table if it exists
        String dropEventOriginTable = "DROP TABLE IF EXISTS EventOrigin";
        String createEventOriginTable = "CREATE TABLE EventOrigin ("
                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                + "Origin VARCHAR(20)"
                + ")";
        String insertEventOriginValues = "INSERT INTO EventOrigin (Origin) VALUES "
                + "('Joe S.'), "
                + "('Bill B.'), "
                + "('George E.'), "
                + "('Achmed M.'), "
                + "('Rona E.')";

        // Drop EventStatus table if it exists
        String dropEventStatusTable = "DROP TABLE IF EXISTS EventStatus";
        String createEventStatusTable = "CREATE TABLE EventStatus ("
                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                + "Status VARCHAR(20)"
                + ")";
        String insertEventStatusValues = "INSERT INTO EventStatus (Status) VALUES "
                + "('Open'), "
                + "('On Hold'), "
                + "('In Process'), "
                + "('Deployed'), "
                + "('Deployed Failed')";

        // Drop EventClass table if it exists
        String dropEventClassTable = "DROP TABLE IF EXISTS EventClass";
        String createEventClassTable = "CREATE TABLE EventClass ("
                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                + "Class VARCHAR(20)"
                + ")";
        String insertEventClassValues = "INSERT INTO EventClass (Class) VALUES "
                + "('Change'), "
                + "('Incident'), "
                + "('Problem'), "
                + "('SR')";

        // Execute SQL statements to drop and create the tables
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dropEventActivityTable);
            statement.executeUpdate(createEventActivityTable);
            statement.executeUpdate(insertEventActivityValues);
            statement.executeUpdate(dropEventOriginTable);
            statement.executeUpdate(createEventOriginTable);
            statement.executeUpdate(insertEventOriginValues);
            statement.executeUpdate(dropEventStatusTable);
            statement.executeUpdate(createEventStatusTable);
            statement.executeUpdate(insertEventStatusValues);
            statement.executeUpdate(dropEventClassTable);
            statement.executeUpdate(createEventClassTable);
            statement.executeUpdate(insertEventClassValues);
        }
    }

    private static void dropAndCreateEventLogTable(Connection connection) throws SQLException {
        // Drop EventLog table if it exists
        String dropTableSQL = "DROP TABLE IF EXISTS EventLog";
        PreparedStatement dropStatement = connection.prepareStatement(dropTableSQL);
        dropStatement.execute();
        dropStatement.close();

        // Create EventLog table
        String createTableSQL = "CREATE TABLE EventLog ("
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
        PreparedStatement createStatement = connection.prepareStatement(createTableSQL);
        createStatement.execute();
        createStatement.close();
    }

    private static void insertTicket(Connection connection, int ticketNumber, Date startDate, Date endDate,
                                      String[] activities, String[] origins, String[] status, String[] classes)
            throws SQLException {
        // Generate random data
        Random random = new Random();
        String caseId = "CS_" + ticketNumber;
        String activity = activities[random.nextInt(activities.length)];
        String origin = origins[random.nextInt(origins.length)];
        String ticketStatus = status[random.nextInt(status.length)];
        String ticketClass = classes[random.nextInt(classes.length)];
        String urgency = String.valueOf(random.nextInt(3) + 1); // Random urgency between 1 and 3
        String impact = String.valueOf(random.nextInt(3) + 1);  // Random impact between 1 and 3

        // Calculate priority based on impact and urgency
        int priority = calculatePriority(Integer.parseInt(impact), Integer.parseInt(urgency));

        // Calculate ticket duration in hours
        long duration = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60);

        // Insert ticket into EventLog table
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

    private static String[] fetchDataFromDB(Connection connection, String tableName, String columnName) throws SQLException {
        List<String> data = new ArrayList<>();
        String query = "SELECT " + columnName + " FROM " + tableName;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                data.add(resultSet.getString(columnName));
            }
        }
        return data.toArray(new String[0]);
    }
}


