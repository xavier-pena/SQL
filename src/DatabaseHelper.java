import java.sql.*;

public class DatabaseHelper {
	
	//jdbc:mysql://hostname:port/databasename
	//hostname is either local host or an ip address, port by default is 3306,and db name
	//just an example using driver manager
	
	private String url = "jdbc:mysql://localhost:3306/lab5";
	private String username = "root";
	private String password = "password";
	private Connection connection;
	
	
	public DatabaseHelper(){
		System.out.println("Connecting database...");
	
		try {
			connection = DriverManager.getConnection(url, username, password);
		    System.out.println("Database connected!");
		    connection.close();
		    
		} catch (SQLException e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	

}
