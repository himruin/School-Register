package school_register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  

public class Client {  
   private Client() {}  
   public static void main(String[] args)throws Exception {  
      try { 
         // Getting the registry 
         Registry registry = LocateRegistry.getRegistry(null); 
    
         // Looking up the registry for the remote object 
         Interface stub = (Interface) registry.lookup("Interface"); 
    

		login window = new login();
		window.frame.setVisible(true);
		
		login.LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//In Teacher.java: query to the database to validate input id and password.
				
				Teacher.LogIn();
			}
		});
	
			
      } catch (Exception e) { 
         System.err.println("Client exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   } 
}