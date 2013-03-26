package Model;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.text.ParseException;
import java.util.*;


public abstract class ModelInterface {

	protected  String input = "";
	protected  String output = "";
	protected  Connection connection;

	public Connection startDB(){
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			connection = DriverManager.getConnection
					("jdbc:mysql://i-intra-02.informatik.hs-ulm.de:3306/sofen_md5_04","sofen_md5_04","sofen_md5_04");
//					("jdbc:mysql://127.0.0.1:3306/sofen_md5_04","sofen_md5_04","sofen_md5_04");			
			System.out.println("dbaccess successful");
			return connection;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(connection != null)
			{
				try 
				{
					connection.close();
				} 
				catch (SQLException e) 
				{
					System.err.println("Error caused in startDB: "+e.getErrorCode());
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public abstract void insert(Object input);
	public abstract void edit();
	public abstract String[][] getAll();
	public abstract void delete();
	
	
	public void insertAttributes(String attribute)
	{
		try
		{
			connection=this.startDB();
			Statement statement=connection.createStatement();
			input="INSERT INTO attribute (name) VALUES ('"+attribute+"')";
			statement.executeUpdate(input);
			connection.close();
		}catch(SQLException e)
		{
			System.err.println("Error caused in insertAttributes: "+e.getErrorCode());
			e.printStackTrace();
		}
	}
	
}



