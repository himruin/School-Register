package school_register;

 
import java.rmi.Remote; 
import java.sql.Connection;


// Creating Remote interface for our application 
public interface Interface extends Remote {  
   
   public static Connection teacher_connection() throws Exception {
	return null;
   }
   public static Connection student_data() throws Exception {
	   return null;
   }
}