package Communication;

import java.sql.*;

import Model.Ref_Group_VO;

public class Ref_Group_DAO_DB implements InterfaceDAO{

	private String input;
	private String output;
	
	private String ref_group_name;
	private int ref_group_id;
	
	private Ref_Group_VO ref_group_vo = null;
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

	public void setValue(Object vo) 
	{		
		try
		{
			ref_group_vo = (Ref_Group_VO) vo;
			ref_group_name = ref_group_vo.getRef_group_name().replace("'", "''");
			
			connection = this.startDB();
			Statement statement = connection.createStatement();
			input = "INSERT INTO ref_group (name) VALUES ('"+ref_group_name+"')";
			statement.executeUpdate(input);
			connection.close();
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in insertGroups: "+e.getErrorCode());
			e.printStackTrace();
		}
	}

	public Object getValue(Object vo)
	{
		try
		{
			ref_group_vo = (Ref_Group_VO) vo;
			
			connection = this.startDB();
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from ref_group rg " +
					" where rg.name = '"+ref_group_vo.getRef_group_name()+"';");
			if(ref_group_vo.getRef_group_id()!=0)
			{
				result = statement.executeQuery("select * from ref_group rg " +
						" where rg.g_id = '"+ref_group_vo.getRef_group_id()+"' ;");
			}
			
			while(result.next())
			{
				ref_group_vo.setRef_group_id(Integer.parseInt(result.getString(1)));
				ref_group_vo.setRef_group_name(result.getString(2));
			}
			
			return ref_group_vo;
		}
		catch (SQLException e) 
		{
			System.err.println("Error caused in getGroups: "+e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}
	
	//unfinished
//	public String[][] getValue(Object vo) 
//	{
//		try 
//		{		
//			ref_group_vo = (Ref_Group_VO) vo;
//			ref_group_name = ref_group_vo.getRef_group_name();
//			ref_group_id = ref_group_vo.getRef_group_id();
//			
//			connection = this.startDB();
//			int size = 0;
//			int i = 0;
//			Statement statement = connection.createStatement();
//			ResultSet result = statement.executeQuery("select * from ref_group rg " +
//					" where rg.name = \""+ref_group_name+"\";");
//			ResultSetMetaData resultmeta = result.getMetaData();
//			
//			while(result.next())
//			{
//				size++;
//			}
//			result.beforeFirst();
//			String[][] ausgabe = new String[size][resultmeta.getColumnCount()+3];
//			
//			while(result.next())
//			{			
//				 ausgabe[i][0] = result.getString(1);
//				 ausgabe[i][1] = result.getString(2);
//				 i++;
//			}									
//			connection.close();
//			return ausgabe;			
//		} 
//		catch (SQLException e) 
//		{
//			System.err.println("Error caused in getGroups: "+e.getErrorCode());
//			e.printStackTrace();
//		}
//		return null;
//	}

	public String[][] getAll() 
	{
		try 
		{			
			connection = this.startDB();
			int size = 0;
			int i = 0;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from ref_group;");
			ResultSetMetaData resultmeta = result.getMetaData();
			
			while(result.next())
			{
				size++;
			}
			result.beforeFirst();
			String[][] ausgabe = new String[size][resultmeta.getColumnCount()+3];
			
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
			System.err.println("Error caused in getGroups: "+e.getErrorCode());
			e.printStackTrace();
		}
		return null;		
	}

	public void deleteValue(Object vo) 
	{
		try
		{
			ref_group_vo = (Ref_Group_VO) vo;
			ref_group_name = ref_group_vo.getRef_group_name().replace("'", "''");
			
			connection = this.startDB();
			Statement statement = connection.createStatement();
			input = "delete from ref_group where name = '"+ref_group_name+"'";
			statement.executeUpdate(input);
			connection.close();
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in insertGroups: "+e.getErrorCode());
			e.printStackTrace();
		}
	}	

}
