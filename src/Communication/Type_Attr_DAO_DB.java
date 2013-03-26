package Communication;

import java.sql.*;
import java.util.*;

import Model.*;

public class Type_Attr_DAO_DB implements InterfaceDAO{

	private String input;
	private String output;

	private int ra_id=0;
	private int type_id=0;
	private int attr_id=0;

	private Ref_Type_VO ref_type_vo =null;
	private Type_Attr_VO type_attr_vo = null;
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
			if(vo instanceof Type_Attr_VO)
			{
				type_attr_vo= (Type_Attr_VO) vo;
				type_id=type_attr_vo.getRt_id();
				attr_id=type_attr_vo.getA_id();

				connection = this.startDB();
				Statement statement = connection.createStatement();
				input = "INSERT INTO ra_table (rt_id,a_id) VALUES ('"+type_id+"','"+attr_id+"');";
				statement.executeUpdate(input);
				connection.close();
			}
			else if (vo instanceof Vector)
			{
				Vector rename = (Vector) vo;
				Type_Attr_VO old= (Type_Attr_VO)rename.elementAt(0);
				Type_Attr_VO neu= (Type_Attr_VO)rename.elementAt(1);
				
				connection = this.startDB();
				Statement statement = connection.createStatement();
				input="UPDATE ra_table SET a_id='"+neu.getA_id()+"' WHERE a_id='"+old.getA_id()+"' AND rt_id='"+old.getRt_id()+"'";
				statement.executeUpdate(input);
				
				Statement stmt_content = connection.createStatement();
				input="UPDATE content SET a_id='"+neu.getA_id()+"' WHERE a_id='"+old.getA_id()+"'";
				connection.close();
			}
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
			if(vo instanceof Ref_Type_VO)
			{
				Vector<Integer> out = new Vector<Integer>();
				ref_type_vo= (Ref_Type_VO) vo;
				type_id=ref_type_vo.getRef_type_id();

				connection = this.startDB();
				Statement statement = connection.createStatement();
				ResultSet result =statement.executeQuery("SELECT a_id FROM ra_table r WHERE r.rt_id = \""+type_id+"\";");

				while (result.next())
				{
					out.add(Integer.parseInt(result.getString(1)));
				}				
				return out;
			}
			else if(vo instanceof Type_Attr_VO)
			{
				type_attr_vo= (Type_Attr_VO) vo;
				type_id=type_attr_vo.getRt_id();
				attr_id=type_attr_vo.getA_id();

				connection = this.startDB();
				Statement statement = connection.createStatement();
				ResultSet result =statement.executeQuery("SELECT * FROM ra_table r WHERE r.rt_id = \""+type_id+"\" AND r.a_id=\""+attr_id+"\";");

				if (result.first()==false)
					return null;
				type_attr_vo.setRa_id(Integer.parseInt(result.getString(1)));
				type_attr_vo.setRt_id(Integer.parseInt(result.getString(2)));
				type_attr_vo.setA_id(Integer.parseInt(result.getString(3)));
				return type_attr_vo;
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.err.println("Error caused in getAttributes: "+e.getErrorCode());
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
			ResultSet result = statement.executeQuery("select rt_id, a_id from ra_table;");
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
			System.err.println("Error caused in getGroups: "+e.getErrorCode());
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public void deleteValue(Object vo) {
		try
		{
			type_attr_vo= (Type_Attr_VO) vo;
			type_id=type_attr_vo.getRt_id();
			attr_id=type_attr_vo.getA_id();
			connection= this.startDB();
			Statement statement= connection.createStatement();
			input = "DELETE FROM ra_table WHERE rt_id='"+type_id+"' AND a_id='"+attr_id+"';";
			statement.executeUpdate(input);
			connection.close();

		}catch (SQLException e) 
		{
			System.err.println("Error caused in getGroups: "+e.getErrorCode());
			e.printStackTrace();
		}
	}

}
