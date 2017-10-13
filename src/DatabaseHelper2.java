import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatabaseHelper2 {
	Properties props;
	FileInputStream fis = null;
	MysqlDataSource mysqlDS = null;
	
	public DatabaseHelper2() {
		
		props = new Properties();
				
		try {
			fis = new FileInputStream("C:\\Users\\xadp5\\eclipse-workspace\\MySQLCon\\db.properties.txt");
			props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/*public static DataSource getMySQLDataSource() {
		Properties props = new Properties();
		FileInputStream fis = null;
		MysqlDataSource mysqlDS = null;
		try {
			fis = new FileInputStream("C:\\Users\\xadp5\\eclipse-workspace\\MySQLCon\\db.properties.txt");
			props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("MYSQL_DB_URL"));
			mysqlDS.setUser(props.getProperty("MYSQL_DB_USERNAME"));
			mysqlDS.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	
	}*/
	
	public void AllFromOwner() {
		
		DataSource ds = mysqlDS;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from owner");
			while(rs.next()){
				System.out.println(rs.getString("SSN")+ "\n"+ rs.getString("fName")+"\n"+ 
						rs.getString("lName")+ "\n"+rs.getString("Street_addr")+"\n"+ 
						rs.getString("city")+"\n"+ rs.getString("state")+"\n"+
						rs.getString("zip")+"\n"+ rs.getString("email")+"\n"+
						rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					if(con != null) con.close();
					System.out.println("Closed");
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		}
		
		
	}
	
	
	//seperate the what(columns) and put those into the get string parameter
	
	public ArrayList<String> stringSeperator(String n) {
		
		ArrayList<String> seperations = new ArrayList<>();
		Scanner reader = new Scanner(n);
		reader.useDelimiter(",");
		
		while(reader.hasNext()) {
			
			seperations.add(reader.next());
			
		}
		
		
		return seperations;
		
	}
	

	public ArrayList<String> SelectQuery(String what, String from, String condition) {
		DataSource ds = mysqlDS;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> results =new ArrayList<>();
		ArrayList<String> columns = stringSeperator(what);
		
		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("select "+ what +" from " + from + " where " + condition+";");
			
			
			
			while(rs.next()) {
				while(columns.size() >0) {
				results.add(rs.getString(columns.get(0)));
				columns.remove(0);
				}
				
			}
			
			return results;//single value return
			
		}
			catch (SQLException e) {
				e.printStackTrace();
		
		
	}
		finally{
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) con.close();
				System.out.println("Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
		}
		return results;
	}
	
}