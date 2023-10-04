import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This Java program demonstrates database operations using JDBC (Java Database Connectivity).
 * It connects to a MySQL database, creates tables, inserts sample data, and queries the tables.
 * The database used is "student_registration," and it assumes a local MySQL server with the following credentials:
 * - Database URL: jdbc:mysql://localhost:3306/student_registration
 * - USERNAME: root
 * - PASSWORD: root
 *
 * The program performs the following tasks:
 * 1. Establish a database connection.
 * 2. Drop and create tables (Student, Course, Registration) with primary keys and foreign keys.
 * 3. Insert sample data into the tables.
 * 4. Query and print data from the tables (Students, Courses, Registrations).
 *
 *

 */

public class MySQL {
    public static void main(String[] args) {
        String JDBC_URL = "jdbc:mysql://localhost:3306/student_registration";
        String USERNAME = "root";
        String PASSWORD = "root";

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            // Drop and create tables with primary keys and foreign keys
            dropAndCreateTable(statement, "Student");
            dropAndCreateTable(statement, "Course");
            dropAndCreateTable(statement, "Registration");

            // Insert sample data into the tables
            insertSampleData(statement);

            // Query 1: Get all students
            queryStudents(statement);

            // Query 2: Get all courses
            queryCourses(statement);

            // Query 3: Get all registrations
            queryRegistrations(statement);

            // Close resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to drop a table if it exists and then create it
    private static void dropAndCreateTable(Statement statement, String tableName) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);

        if ("Student".equals(tableName)) {
            // Create Student table
            statement.executeUpdate("CREATE TABLE Student (" +
                    "StudentId VARCHAR(10) PRIMARY KEY," +
                    "FirstName VARCHAR(50)," +
                    "LastName VARCHAR(50)," +
                    "Location VARCHAR(100))");
        } else if ("Course".equals(tableName)) {
            // Create Course table
            statement.executeUpdate("CREATE TABLE Course (" +
                    "CourseId VARCHAR(10) PRIMARY KEY," +
                    "CourseName VARCHAR(50)," +
                    "CourseTitle VARCHAR(50))");
        } else if ("Registration".equals(tableName)) {
            // Create Registration table with foreign keys
            statement.executeUpdate("CREATE TABLE Registration (" +
                    "RegistrationId VARCHAR(10) PRIMARY KEY," +
                    "CourseId VARCHAR(10)," +
                    "StudentId VARCHAR(10)," +
                    "FOREIGN KEY (CourseId) REFERENCES Course(CourseId)," +
                    "FOREIGN KEY (StudentId) REFERENCES Student(StudentId))");
        }
    }

    // Helper method to insert sample data into the tables
    private static void insertSampleData(Statement statement) throws SQLException {
        // Insert data into Student table
        statement.executeUpdate("INSERT INTO Student (StudentId, FirstName, LastName, Location) " +
                "VALUES ('S1', 'John', 'Doe', 'New York')");
        statement.executeUpdate("INSERT INTO Student (StudentId, FirstName, LastName, Location) " +
                "VALUES ('S2', 'Jane', 'Smith', 'Los Angeles')");

        // Insert data into Course table
        statement.executeUpdate("INSERT INTO Course (CourseId, CourseName, CourseTitle) " +
                "VALUES ('C1', 'Math', 'Algebra')");
        statement.executeUpdate("INSERT INTO Course (CourseId, CourseName, CourseTitle) " +
                "VALUES ('C2', 'Science', 'Biology')");

        // Insert data into Registration table
        statement.executeUpdate("INSERT INTO Registration (RegistrationId, CourseId, StudentId) " +
                "VALUES ('R1', 'C1', 'S1')");
        statement.executeUpdate("INSERT INTO Registration (RegistrationId, CourseId, StudentId) " +
                "VALUES ('R2', 'C2', 'S2')");
    }

    // Helper method to query and print students
    private static void queryStudents(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
        while (resultSet.next()) {
            String studentId = resultSet.getString("StudentId");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String location = resultSet.getString("Location");
            System.out.println("Student: " + studentId + ", " + firstName + " " + lastName + ", " + location);
        }
    }

    // Helper method to query and print courses
    private static void queryCourses(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Course");
        while (resultSet.next()) {
            String courseId = resultSet.getString("CourseId");
            String courseName = resultSet.getString("CourseName");
            String courseTitle = resultSet.getString("CourseTitle");
            System.out.println("Course: " + courseId + ", " + courseName + ", " + courseTitle);
        }
    }

    // Helper method to query and print registrations
    private static void queryRegistrations(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Registration");
        while (resultSet.next()) {
            String registrationId = resultSet.getString("RegistrationId");
            String courseId = resultSet.getString("CourseId");
            String studentId = resultSet.getString("StudentId");
            System.out.println("Registration: " + registrationId + ", Course: " + courseId + ", Student: " + studentId);
        }
    }
}
