package employee.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection
{
	public static final String dburl="jdbc:mysql://localhost:3306/employee_db";
	public static final String userName="root";
	public static final String password="qwerty1234";
	
public static Connection getConnection()
{
	System.out.println("start getConnection"); //logger log4j
	try
	{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con=DriverManager.getConnection(dburl, userName, password);
		if(con!=null)
		{
			System.out.println("Connected !");
			return con;
		}
		else
		{
			System.out.println("Connection issue !!");
			return null;
		}
	}
	catch (Exception e)
	{
		System.out.println("Exception in DB Connection:"+e.getMessage());
		e.printStackTrace();
		return null;
	}
}
public static Connection con= getConnection();

public static void main(String args[])
{
	System.out.println(DbConnection.con);
}
}
