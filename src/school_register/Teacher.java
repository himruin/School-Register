package school_register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Teacher {     
	   
		static Connection connection = Implementation.teacher_connection();	   
	   
		public static void LogIn() {
			try {
				String query = "select * from teachers where ID=? and password=? ";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, login.IdField.getText() );
				pst.setString(2, login.passwordField.getText( ));
				
				ResultSet rs=pst.executeQuery();
				int count = 0;
				while(rs.next()) {
					count = count + 1;
				}
				if (count == 1)	{
					
					// If input data is correct, the login window is disposed,
					// then school register is opened
					
					JOptionPane.showMessageDialog(null, "ID and password are correct");
					login.frame.dispose();
					register reg = new register();
					reg.frame.setVisible(true);
				}
				else if (count > 1) {
					JOptionPane.showMessageDialog(null, "You are already logged in");
				}
				else {
					JOptionPane.showMessageDialog(null, "ID and password are not correct. Try again...");
				}
			
				rs.close();
				pst.close();		
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}   
}

