package databaseconnection;
import java.sql.*;

public class databasecon
{
	static Connection con;
	public static Connection getconnection()
	{
 		
 			
		try
		{
			Class.forName("org.mariadb.jdbc.Driver");	
			con = DriverManager.getConnection("jdbc:mysql://localhost/library_books", "butters", "pass");
		}
		catch(Exception e)
		{
			System.out.println("class error");
		}
		return con;
	}
	
}
