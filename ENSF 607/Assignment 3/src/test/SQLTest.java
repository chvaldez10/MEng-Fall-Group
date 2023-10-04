package test;
import java.sql.*;

public class SQLTest {
	public static void main(String[] args) {
        String JDBC_URL = "jdbc:mysql://localhost:3306/ensf607";
        String USERNAME = "root";
        String PASSWORD = "root";		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
			Statement stmt = connection.createStatement();
			System.out.println("Inserting records");
			String sql = "Insert into fruit values (43)";
			stmt.executeUpdate(sql);
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
