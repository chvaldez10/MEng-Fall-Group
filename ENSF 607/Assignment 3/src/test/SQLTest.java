package test;
import java.sql.*;

public class SQLTest {
	public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/ensf607";
        String username = "root";
        String password = "root";		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			Statement stmt = connection.createStatement();
			System.out.println("Inserting records");
			String sql = "Insert into fruit values (43)";
			stmt.executeUpdate(sql);
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
