package Communication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import Model.*;

public class Ref_Type_DAO_DB implements InterfaceDAO {


	private String input;
	private String output;
	
	private String ref_type_name;
	private int ref_type_id;
	
	private Ref_Type_VO ref_type_vo = null;
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
			ref_type_vo = (Ref_Type_VO) vo;
			ref_type_name = ref_type_vo.getRef_type_name().replace("'", "''");
			
			connection = this.startDB();
			Statement statement = connection.createStatement();
			input = "INSERT INTO ref_type (name) VALUES ('"+ref_type_name+"');";
			statement.executeUpdate(input);
			connection.close();
			
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in insertType: "+e.getErrorCode());
			e.printStackTrace();
		}
		
	}

	@Override
	public Object getValue(Object vo) {
		try 
		{		
			ref_type_vo = (Ref_Type_VO) vo;
			ref_type_name = ref_type_vo.getRef_type_name().replace("'", "''");
			ref_type_id = ref_type_vo.getRef_type_id();
			
			connection = this.startDB();
			
			Statement statement = connection.createStatement();
			ResultSet result = null;
			if(ref_type_vo.getRef_type_id()==0)
			{
				result = statement.executeQuery("select * from ref_type rt where rt.name = '" + ref_type_name + "';");
			}
			else
			{
				result = statement.executeQuery("select * from ref_type rt where rt.rt_id = '" + ref_type_vo.getRef_type_id() + "';");
			}
			
			if (result.first()==false)
				return null;
			
			ref_type_vo.setRef_type_id(Integer.parseInt(result.getString(1)));
			ref_type_vo.setRef_type_name(result.getString(2));
			
			connection.close();
			return ref_type_vo;			
		} catch (SQLException e) 
		{
			System.err.println("Error caused in getType: "+e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String[][] getAll() {
		try 
		{			
			connection = this.startDB();
			int size = 0;
			int i = 0;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from ref_type;");
			ResultSetMetaData resultmeta = result.getMetaData();
			
			while(result.next())
			{
				size++;
			}
			result.beforeFirst();
			String[][] ausgabe = new String[size][resultmeta.getColumnCount()];
			
			while(result.next())
			{			
				 ausgabe[i][0] = result.getString(1);
				 ausgabe[i][1] = result.getString(2);
				 i++;
			}									
			connection.close();
			return ausgabe;			
		} catch (SQLException e) 
		{
			System.err.println("Error caused in getTypes: "+e.getErrorCode());
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public void deleteValue(Object vo) {
		// TODO Auto-generated method stub
		
	}

}
