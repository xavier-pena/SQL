import java.sql.*;
import java.util.ArrayList;

import javax.sql.*;



public class Test {

	public static void main (String []args) {
		
		DatabaseHelper myDB = new DatabaseHelper();
		
		DatabaseHelper2 secDB = new DatabaseHelper2();
		
		secDB.AllFromOwner();
	
		
		//secDB.SelectQuery("SSN","owner","fName = 'john'");//select what from where with what conditions
		while(!(secDB.SelectQuery("SSN","owner","fName = 'john'").isEmpty())) {
		System.out.println(secDB.SelectQuery("SSN","owner","fName = 'john'").get(0));
		secDB.SelectQuery("SSN","owner","fName = 'john'").remove(0);
		
		}
		
		
		
		
	}
		
}
