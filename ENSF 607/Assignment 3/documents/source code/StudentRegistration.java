package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This Java program demonstrates database operations using JDBC (Java Database Connectivity).
 * 
 * It connects to a local MySQL database, creates tables, inserts sample data, and queries the tables.
 * 
 * The database used is "student_registration,". The program drops the database if exists and creates it.
 * 		Note: This method of deleting/creating database is OKAY for a small sized group assignment.
 * 
 * The program assumes a local MySQL server with the following credentials:
 * - Database URL: jdbc:mysql://localhost:3306/student_registration
 * - USERNAME: root
 * - PASSWORD: root
 *
 * The program performs the following tasks:
 * 1. Establish a database connection.
 * 2. Drop and create student_registration database.
 * 3. Drop and create tables (Student, Course, Registration) with primary keys and foreign keys.
 * 4. Insert sample data into the tables.
 * 5. Query and print data from the tables (Students, Courses, Registrations).
 *
 */

public class StudentRegistration {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student_registration";
    private static final String JDBC_ROOT_URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE_NAME = "student_registration";
    
    public static void main(String[] args) {
        try {
        	// drop database
        	dropDatabase(DATABASE_NAME);
        	
        	// create database
        	createDatabase(DATABASE_NAME);
        	
        	// connect to student registration
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            
            // drop tables
            dropTables(statement);
            
            // create tables
            createTables(statement);

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

    /**
     * Drops the specified database if it exists.
     *
     * @throws SQLException if any SQL error occurs.
     */
    private static void dropDatabase(String database_name) throws SQLException {
        try(Connection conn = DriverManager.getConnection(JDBC_ROOT_URL, USERNAME, PASSWORD);
           Statement stmt = conn.createStatement();
        ) {		      
           String sql = "DROP DATABASE IF EXISTS "+ database_name;
           stmt.executeUpdate(sql);
           System.out.println("Dropped a database named \""+ database_name + "\"  ...\n");  	  
        } catch (SQLException e) {
           e.printStackTrace();
        } 
    }
    
    /**
     * Creates the specified database.
     *
     * @throws SQLException if any SQL error occurs.
     */
    private static void createDatabase(String database_name) throws SQLException {
        try(Connection conn = DriverManager.getConnection(JDBC_ROOT_URL, USERNAME, PASSWORD);
           Statement stmt = conn.createStatement();
        ) {		      
           String sql = "CREATE DATABASE "+ database_name;
           stmt.executeUpdate(sql);
           System.out.println("Created a database named \""+ database_name +"\" ...\n");     
        } catch (SQLException e) {
           e.printStackTrace();
        } 
    }
    
    /**
     * Drops the tables (Student, Course, Registration) if they exist.
     *
     * @param statement the SQL statement object.
     * @throws SQLException if any SQL error occurs.
     */
    private static void dropTables(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS Registration");
        statement.executeUpdate("DROP TABLE IF EXISTS Course");
        statement.executeUpdate("DROP TABLE IF EXISTS Student");
    }

    
    /**
     * Creates the tables (Student, Course, Registration) with appropriate keys.
     *
     * @param statement the SQL statement object.
     * @throws SQLException if any SQL error occurs.
     */
    private static void createTables(Statement statement) throws SQLException { 	  
        // Student table
        statement.executeUpdate(
            "CREATE TABLE Student (" +
            "StudentId VARCHAR(10) PRIMARY KEY," +
            "FirstName VARCHAR(50)," +
            "LastName VARCHAR(50)," +
            "Location VARCHAR(100))"
        );

        // Course table
        statement.executeUpdate(
            "CREATE TABLE Course (" +
            "CourseId VARCHAR(10) PRIMARY KEY," +
            "CourseName VARCHAR(50)," +
            "CourseTitle VARCHAR(50))"
        );

        // Registration table
        statement.executeUpdate(
            "CREATE TABLE Registration (" +
            "RegistrationId VARCHAR(10) PRIMARY KEY," +
            "CourseId VARCHAR(10)," +
            "StudentId VARCHAR(10)," +
            "FOREIGN KEY (CourseId) REFERENCES Course(CourseId)," +
            "FOREIGN KEY (StudentId) REFERENCES Student(StudentId))"
        );
        
        System.out.println("Created Student, Course, and Registration tables ...\n");  
    }

    /**
     * Inserts sample data into the tables (Student, Course, Registration).
     *
     * @param statement the SQL statement object.
     * @throws SQLException if any SQL error occurs.
     */
    private static void insertSampleData(Statement statement) throws SQLException {
        insertStudentsData(statement);
        insertCoursesData(statement);
        insertRegistrationsData(statement);
    }

    /**
     * Inserts sample student data into the Student table.
     *
     * @param statement the SQL statement object.
     * @throws SQLException if any SQL error occurs.
     */
    private static void insertStudentsData(Statement statement) throws SQLException {
        String[] students = {
            "('S1', 'John', 'Doe', 'New York')",
            "('S2', 'Jane', 'Smith', 'Los Angeles')",
            "('S3', 'Robert', 'Brown', 'Chicago')",
            "('S4', 'Linda', 'Johnson', 'Houston')",
            "('S5', 'Michael', 'Williams', 'Phoenix')",
            "('S6', 'Elizabeth', 'Jones', 'Philadelphia')",
            "('S7', 'David', 'Garcia', 'San Antonio')",
            "('S8', 'Sarah', 'Martinez', 'San Diego')",
            "('S9', 'Daniel', 'Rodriguez', 'Dallas')",
            "('S10', 'Emily', 'Taylor', 'San Jose')"
        };

        for (String student : students) {
            statement.executeUpdate("INSERT INTO Student (StudentId, FirstName, LastName, Location) VALUES " + student);
        }
    }
    
    /**
     * Inserts sample course data into the Course table.
     *
     * @param statement the SQL statement object.
     * @throws SQLException if any SQL error occurs.
     */
    private static void insertCoursesData(Statement statement) throws SQLException {
        String[] courses = {
            "('C1', 'Math', 'Algebra 101')",
            "('C2', 'Science', 'Biology 101')",
            "('C3', 'English', 'Literature 101')",
            "('C4', 'History', 'World History 101')"
        };

        for (String course : courses) {
            statement.executeUpdate("INSERT INTO Course (CourseId, CourseName, CourseTitle) VALUES " + course);
        }
    }
    
    /**
     * Inserts sample registration data into the Registration table.
     *
     * @param statement the SQL statement object.
     * @throws SQLException if any SQL error occurs.
     */
    private static void insertRegistrationsData(Statement statement) throws SQLException {
        String[] registrations = {
            "('R1', 'C1', 'S1')",  
            "('R2', 'C2', 'S2')",  
            "('R3', 'C3', 'S3')",  
            "('R4', 'C4', 'S4')",  
            "('R5', 'C1', 'S5')",
            "('R6', 'C2', 'S6')",  
            "('R7', 'C3', 'S7')",  
            "('R8', 'C4', 'S8')",  
            "('R9', 'C1', 'S9')",  
            "('R10', 'C2', 'S10')", 
            "('R11', 'C3', 'S1')", 
            "('R12', 'C4', 'S2')"   
        };

        for (String registration : registrations) {
            statement.executeUpdate("INSERT INTO Registration (RegistrationId, CourseId, StudentId) VALUES " + registration);
        }
    }
    
    /**
     * Queries and prints all students from the Student table.
     *
     * @param statement the SQL statement object.
     * @throws SQLException if any SQL error occurs.
     */
    private static void queryStudents(Statement statement) throws SQLException {
        System.out.println("Querying all data from Student:");
    	
    	ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
        while (resultSet.next()) {
            String studentId = resultSet.getString("StudentId");
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            String location = resultSet.getString("Location");
            System.out.println("Student: " + studentId + ", " + firstName + " " + lastName + ", " + location);
        }
        System.out.println();
    }

    /**
     * Queries and prints all courses from the Course table.
     *
     * @param statement the SQL statement object.
     * @throws SQLException if any SQL error occurs.
     */
    private static void queryCourses(Statement statement) throws SQLException {
    	System.out.println("Querying all data from Courses:");
    	
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Course");
        while (resultSet.next()) {
            String courseId = resultSet.getString("CourseId");
            String courseName = resultSet.getString("CourseName");
            String courseTitle = resultSet.getString("CourseTitle");
            System.out.println("Course: " + courseId + ", " + courseName + ", " + courseTitle);
        }
        
        System.out.println();
    }

    /**
     * Queries and prints all registrations from the Registration table.
     *
     * @param statement the SQL statement object.
     * @throws SQLException if any SQL error occurs.
     */
    private static void queryRegistrations(Statement statement) throws SQLException {
    	System.out.println("Querying all data from Registration:");
    	
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Registration");
        while (resultSet.next()) {
            String registrationId = resultSet.getString("RegistrationId");
            String courseId = resultSet.getString("CourseId");
            String studentId = resultSet.getString("StudentId");
            System.out.println("Registration: " + registrationId + ", Course: " + courseId + ", Student: " + studentId);
        }
        
        System.out.println();
    }
}