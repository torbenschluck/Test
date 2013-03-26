package Communication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import Model.Ref_Group_VO;
import Model.Reference_Group_VO;
import Model.Reference_VO;

public class Reference_Group_DAO_DB implements InterfaceDAO{

	private String input;
	private String output;

	private Reference_Group_VO refgroup_vo = null;
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
			refgroup_vo = (Reference_Group_VO) vo;
			
			connection = this.startDB();
			Statement statement = connection.createStatement();
			input = "INSERT INTO rg_table (ref_id, g_id) VALUES ('"+refgroup_vo.getRef_id()+"', '"+refgroup_vo.getG_id()+"');";
			statement.executeUpdate(input);
			connection.close();
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in setReferenceValue: "+e.getErrorCode());
			e.printStackTrace();
		}
	}

	public Object getValue(Object vo) 
	{
		try
		{
			refgroup_vo = (Reference_Group_VO) vo;
			connection = this.startDB();
			int i = 0;
			Statement statement = connection.createStatement();
			String sqlcode = "select * from rg_table rgt ";
			
//			if(refgroup_vo.getRef_id()!=0)
			{
				sqlcode=sqlcode+"where rgt.ref_id = '"+refgroup_vo.getRef_id()+"' ";				
			}
//			if(refgroup_vo.getG_id()!=0)
			{
				sqlcode=sqlcode+"or rgt.g_id = '"+refgroup_vo.getG_id()+"' ";
			}
			ResultSet result = statement.executeQuery(sqlcode+";");
			
			Vector<Reference_Group_VO> refgroups = new Vector<Reference_Group_VO>();
			while(result.next())
			{
				refgroup_vo = new Reference_Group_VO();
				refgroup_vo.setRg_id(Integer.parseInt(result.getString(1)));
				refgroup_vo.setRef_id(Integer.parseInt(result.getString(2)));
				refgroup_vo.setG_id(Integer.parseInt(result.getString(3)));
				refgroups.add(refgroup_vo);
				i++;
			}
			
			return refgroups;			
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in getReferenceValue: "+e.getErrorCode());
			e.printStackTrace();			
		}				
		return null;
	}
	
	//unused
	public String[][] getAll(Reference_Group_VO refgroup)
	{
		try
		{
			connection = this.startDB();
			int size = 0;
			int i = 0;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from rg_table " +
					" where g_id = '"+refgroup.getG_id()+"';");
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
				ausgabe[i][2] = result.getString(3);	
				i++;
			}
		} 
		catch (SQLException e) 
		{
			System.err.println("Error caused in getAllRefGroupConnections: "+e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}
	
	public String[][] getAll() 
	{
		try 
		{			
			connection = this.startDB();
			int size = 0;
			int i = 0;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from rg_table;");
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
				 ausgabe[i][2] = result.getString(3);				 
				 i++;
			}									
			connection.close();
			return ausgabe;			
		} catch (SQLException e) 
		{
			System.err.println("Error caused in getAllRefGroupConnections: "+e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteValue(Object vo) 
	{
		try
		{
			refgroup_vo = (Reference_Group_VO) vo;			
			
			connection = this.startDB();
			Statement statement = connection.createStatement();
			input = "delete from rg_table where ref_id = '"+refgroup_vo.getRef_id()+"' and g_id = '"+refgroup_vo.getG_id()+"';";
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
