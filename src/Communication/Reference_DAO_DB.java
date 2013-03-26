package Communication;

import java.lang.ref.Reference;
import java.sql.*;
import java.util.Calendar;
import java.util.Date; 
import java.util.Vector;

import Model.*;



public class Reference_DAO_DB implements InterfaceDAO{

	private String input;
	private String output;

	private int reference_id;
	private int reference_type_id;
	private long reference_date;
	private String reference_keywords;
	private String reference_abstract;
	private String reference_notes;

	private Reference_VO reference_vo = null;
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
			reference_vo = (Reference_VO) vo;
			reference_type_id = reference_vo.getRt_id();
			reference_date = reference_vo.getDate();
			reference_keywords = reference_vo.getKeywords().replace("'", "''");
			reference_abstract = reference_vo.getRef_abstract().replace("'", "''");
			reference_notes = reference_vo.getNotes().replace("'", "''");
			Calendar cal = Calendar.getInstance(); 
			cal.setTimeInMillis(reference_date);
			//			Date date = new Date(System.currentTimeMillis());
			java.sql.Date sqldate = new java.sql.Date(System.currentTimeMillis());

			connection = this.startDB();
			Statement statement = connection.createStatement();

			if(reference_vo.getRef_id()!=0)
			{
				input = "update reference set keywords = '"+reference_vo.getKeywords().replace("'", "''")+"', " +
											 "abstract = '"+reference_vo.getRef_abstract().replace("'", "''")+"', " +
											 "notes = '"+reference_vo.getNotes().replace("'", "''")+"', " +
											 "quote = '"+reference_vo.getQuote().replace("'", "''")+"' " +
											 "where ref_id = '"+reference_vo.getRef_id()+"';";
			}
			else
			{
				input = "INSERT INTO reference (rt_id, creation_date, keywords, abstract, notes, quote) VALUES ('"+reference_type_id+"', '"+sqldate+"', '"+reference_keywords+"', '"+reference_abstract+"', '"+reference_notes+"', '"+reference_vo.getQuote().replace("'", "''")+"')";
			}

			statement.executeUpdate(input);
			connection.close();
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in setReferenceValue: "+e.getErrorCode());
			e.printStackTrace();
		}		
	}

//	public Object getValue(Object vo) {
//		try
//		{
//			reference_vo = (Reference_VO) vo;
//			connection = this.startDB();
//			Statement statement = connection.createStatement();
//			String sqlcode = "select * from reference ref ";
//
//			if(reference_vo.getRef_id()!=0)
//			{
//				sqlcode=sqlcode+"where ref.ref_id = '"+reference_vo.getRef_id()+"' ";
//				ResultSet result = statement.executeQuery(sqlcode+";");
//				while(result.next())
//				{
//					reference_vo.setRef_id(Integer.parseInt(result.getString(1)));
//					reference_vo.setRt_id(Integer.parseInt(result.getString(2)));
//					reference_vo.setDate(result.getDate(3).getTime());
//					reference_vo.setKeywords(result.getString(4));
//					reference_vo.setRef_abstract(result.getString(5));
//					reference_vo.setNotes(result.getString(6));
//					reference_vo.setQuote(result.getString(7));
//				}
//
//				return reference_vo;			
//			}
//			else
//			{
//				Vector out= new Vector();
//				sqlcode=sqlcode+"where ref.rt_id = '"+reference_vo.getRt_id()+"' ";
//				ResultSet result = statement.executeQuery(sqlcode+";");
//
//				while(result.next())
//				{
//					reference_vo= new Reference_VO();
//					reference_vo.setRef_id(Integer.parseInt(result.getString(1)));
//					reference_vo.setRt_id(Integer.parseInt(result.getString(2)));
//					reference_vo.setDate(result.getDate(3).getTime());
//					reference_vo.setKeywords(result.getString(4));
//					reference_vo.setRef_abstract(result.getString(5));
//					reference_vo.setNotes(result.getString(6));
//					reference_vo.setQuote(result.getString(7));
//					out.add(reference_vo);
//				}
////				return out;
//			}
//		}
//		catch(SQLException e)
//		{
//			System.err.println("Error caused in getReferenceValue: "+e.getErrorCode());
//			e.printStackTrace();			
//		}				
//		return null;
//	}

	public Object getValue(Object vo) {
		try
		{
			reference_vo = (Reference_VO) vo;
			connection = this.startDB();
			Statement statement = connection.createStatement();
			String sqlcode = "select * from reference ref ";
			
			if(reference_vo.getRef_id()!=0)
			{
				sqlcode=sqlcode+"where ref.ref_id = '"+reference_vo.getRef_id()+"' ";
			}
			else
			{
				sqlcode=sqlcode+"where ref.rt_id = '"+reference_vo.getRt_id()+"' ";
			}
			ResultSet result = statement.executeQuery(sqlcode+";");
			reference_vo.setRef_id(0);
			while(result.next())
			{
				reference_vo.setRef_id(Integer.parseInt(result.getString(1)));
				reference_vo.setRt_id(Integer.parseInt(result.getString(2)));
				reference_vo.setDate(result.getDate(3).getTime());
				reference_vo.setKeywords(result.getString(4));
				reference_vo.setRef_abstract(result.getString(5));
				reference_vo.setNotes(result.getString(6));
				reference_vo.setQuote(result.getString(7));
			}			
			return reference_vo;			
		}
		catch(SQLException e)
		{
			System.err.println("Error caused in getReferenceValue: "+e.getErrorCode());
			e.printStackTrace();			
		}				
		return null;
	}
	
	public String[][] getAll() {
		try 
		{			
			connection = this.startDB();
			int size = 0;
			int i = 0;
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from reference;");
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
				ausgabe[i][4] = result.getString(5);
				ausgabe[i][5] = result.getString(6);
				i++;
			}									
			connection.close();
			return ausgabe;			
		} catch (SQLException e) 
		{
			System.err.println("Error caused in getAllReferences: "+e.getErrorCode());
			e.printStackTrace();
		}
		return null;
	}

	public void deleteValue(Object vo) {
		try{
			reference_vo= (Reference_VO) vo;
			connection = this.startDB();
			Statement statement = connection.createStatement();
			input = "DELETE FROM reference WHERE ref_id = "+reference_vo.getRef_id()+";";
			statement.executeUpdate(input);
			connection.close();
			
		}catch(SQLException e)
		{
			System.err.println("Error caused in deleteReference: "+e.getErrorCode());
			e.printStackTrace();
		}
	}

}
