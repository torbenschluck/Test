package Communication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Vector;

import Model.Content_VO;
import Model.Reference_VO;
import Model.Type_Attr_VO;

public class Content_DAO_DB implements InterfaceDAO
{

	private String input;
	private String output;

	private Content_VO content = new Content_VO();
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
			content = (Content_VO) vo;												
			connection = this.startDB();
			Statement statement = connection.createStatement();

			if(content.getCon_id()!=0)
			{
				input = "update content set content = '"+content.getContent().replace("'", "''")+"' " +
				"where c_id = '"+content.getCon_id()+"';";
			}
			else
			{
				input = "INSERT INTO content (ref_id, a_id, content) VALUES ('"+content.getRef_id()+"', '"+content.getAttr_id()+"', '"+content.getContent().replace("'", "''")+"')";
			}

			statement.executeUpdate(input);
			connection.close();
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in setContentValue: "+e.getErrorCode());
			e.printStackTrace();
		}
	}

	@Override
	public Object getValue(Object vo) 
	{		
		try
		{			
			Content_VO content = new Content_VO();
			Vector data = new Vector();
			try
			{				
				content = (Content_VO) vo;
			}
			catch(Exception e)
			{
				data= (Vector) vo;
			}

			connection = this.startDB();
			Statement statement = connection.createStatement();
			ResultSet result;

			if(content.getRef_id()!=0 && content.getAttr_id()==0)
			{
				int i = 0;
				Vector<Content_VO> contents = new Vector<Content_VO>();
				result = statement.executeQuery("select * from content con where con.ref_id = '"+content.getRef_id()+"';");

				while(result.next())
				{
					content = new Content_VO();
					content.setCon_id(Integer.parseInt(result.getString(1)));
					content.setRef_id(Integer.parseInt(result.getString(2)));
					content.setAttr_id(Integer.parseInt(result.getString(3)));
					content.setContent(result.getString(4));
					contents.add(i, content);					
					i++;
				}
				return contents;	
			}
			if (content.getAttr_id()!=0 && content.getRef_id()!=0)
			{
				result = statement.executeQuery("select * from content con where con.ref_id = '"+content.getRef_id()+"'AND con.a_id = '"+content.getAttr_id()+"';");
				
				while(result.next())
				{
					content = new Content_VO();
					content.setCon_id(Integer.parseInt(result.getString(1)));
					content.setRef_id(Integer.parseInt(result.getString(2)));
					content.setAttr_id(Integer.parseInt(result.getString(3)));
					content.setContent(result.getString(4));
					
				}
				return content;	
			}
			else
			{
				result = statement.executeQuery("SELECT ref_id FROM CONTENT WHERE content LIKE");
			}
			return result;
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in getContentValue: "+e.getErrorCode());
			e.printStackTrace();
		}		
		return null;
	}

	@Override
	public String[][] getAll() 
	{
		try 
		{			
			connection = this.startDB();
			int size = 0;
			int i = 0;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from content;");
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
				ausgabe[i][3] = result.getString(4);				 
				i++;
			}									
			connection.close();
			return ausgabe;			
		} catch (SQLException e) 
		{
			System.err.println("Error caused in getAllContents: "+e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteValue(Object vo) {
		try
		{
			content= (Content_VO) vo;

			connection= this.startDB();
			Statement statement= connection.createStatement();
			input = "DELETE FROM content WHERE ref_id='"+content.getRef_id()+"' AND a_id='"+content.getAttr_id()+"';";
			statement.executeUpdate(input);
			connection.close();

		}catch (SQLException e) 
		{
			System.err.println("Error caused in getGroups: "+e.getErrorCode());
			e.printStackTrace();
		}

	}

	public String[][] getSearch(Vector data)
	{
		return null;

	}
}
