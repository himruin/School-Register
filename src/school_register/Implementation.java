package school_register;

import java.sql.*; 
import javax.swing.JOptionPane;  

// Implementing the remote interface 
public class Implementation implements Interface {  
	
	
   // Implementing the interface method 
  
	public static Connection teacher_connection() { 
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conn=DriverManager.getConnection("jdbc:sqlite:SQLite/teacher.sqlite");
				return conn;
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return null;
			}		
	}


	public static Connection student_data() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:SQLite/students.sqlite");
			return conn;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}		
}

		
}
