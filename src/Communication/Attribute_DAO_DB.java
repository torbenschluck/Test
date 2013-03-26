package Communication;

import java.sql.*;

import Model.*;

public class Attribute_DAO_DB implements InterfaceDAO{

	private String input;
	private String output;

	private String attribute_name;
	private int attribute_id;

	private Attribute_VO attribute_vo;
	private Connection connection = null;

	public Connection startDB(){
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			connection = DriverManager.getConnection
			("jdbc:mysql://i-intra-02.informatik.hs-ulm.de:3306/sofen_md5_04","sofen_md5_04","sofen_md5_04");
			//					("jdbc:mysql://127.0.0.1:3306/sofen_md5_04","sofen_md5_04","sofen_md5_04");						
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

	@Override
	public void setValue(Object vo) {
		try
		{
			attribute_vo = (Attribute_VO) vo;
			attribute_name = attribute_vo.getAttribute_name().replace("'", "''");

			connection = this.startDB();
			Statement statement = connection.createStatement();
			input = "INSERT INTO attribute (name) VALUES ('"+attribute_name+"');";
			statement.executeUpdate(input);
			connection.close();
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in insertAttributes: "+e.getErrorCode());
			e.printStackTrace();
		}

	}

	@Override
	public Object getValue(Object vo) {
		try 
		{		
			attribute_vo = (Attribute_VO) vo;
			attribute_name = attribute_vo.getAttribute_name().replace("'", "''");
			attribute_id = attribute_vo.getAttribute_id();

			connection = this.startDB();			
			Statement statement = connection.createStatement();
			ResultSet result;
			
			//abe
			if(attribute_vo.getAttribute_id()!=0)
			{
				result = statement.executeQuery("select * from attribute where a_id = '"+attribute_vo.getAttribute_id()+"';");
				
				while(result.next())
				{
					attribute_vo.setAttribute_id(Integer.parseInt(result.getString(1)));
					attribute_vo.setAttribute_name(result.getString(2));
				}
								
				return attribute_vo;
			}
			
			result = statement.executeQuery("select * from attribute a" +
					" where a.name = \""+attribute_name+"\" or" +
					" a.a_id = '"+attribute_id+"';");			

			if (result.first()==false)
				return null;
			
			attribute_vo.setAttribute_id(Integer.parseInt(result.getString(1)));
			attribute_vo.setAttribute_name(result.getString(2));

			connection.close();
			return attribute_vo;			
		} 
		catch (SQLException e) 
		{
			System.err.println("Error caused in getAttributes: "+e.getErrorCode());
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public String[][] getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteValue(Object vo) {
		// TODO Auto-generated method stub

	}

}
