package XML;

import java.sql.*;
import java.io.*;
import java.util.*;
import java.sql.*;

import Model.Ref_Type_VO;


public class XmlWrite {

	private String input;
	private String output;

	private String ref_type_name;
	private int ref_type_id;

	private Ref_Type_VO ref_type_vo = null;
	private static Connection connection = null;

	public XmlWrite()
	{
	}

	public void main(){
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			connection = DriverManager.getConnection
			("jdbc:mysql://i-intra-02.informatik.hs-ulm.de:3306/sofen_md5_04","sofen_md5_04","sofen_md5_04");
			//("jdbc:mysql://127.0.0.1:3306/sofen_md5_04","sofen_md5_04","sofen_md5_04");			


			Statement stmt = connection.createStatement();
			Statement stmt_reference = connection.createStatement();
			Statement stmt_content = connection.createStatement();
			Statement stmt_group = connection.createStatement();

			String sql = "Select* from ref_type";
			ResultSet rs_type = stmt.executeQuery(sql);
			
			BufferedWriter bw = new BufferedWriter (new FileWriter("fileWrite.xml"));
			bw.write("<?xml version=\"1.0\"?>\n\r");    
			bw.write("<refman>\n");
			while (rs_type.next()){

				String type_id = rs_type.getString("rt_id");

				sql = "Select * from reference where rt_id="+type_id;
				ResultSet rs_reference = stmt_reference.executeQuery(sql);

				bw.write("	<type name=\""+rs_type.getString("name")+"\">\n");

				while(rs_reference.next())
				{
					bw.write("		<reference id=\""+rs_reference.getString("ref_id")+"\">\n");

					String ref_id = rs_reference.getString("ref_id");

					sql = "Select * from attribute a inner join content c where a.a_id = c.a_id and ref_id="+ref_id;
					ResultSet rs_content = stmt_content.executeQuery(sql);

					bw.write("			<content>\n");
					while(rs_content.next())
					{
						bw.write("				<attribute id=\""+rs_content.getString("a_id")+"\" name=\""+rs_content.getString("name")+"\">");
						bw.write(rs_content.getString("content")+"</attribute>\n");
					}
					bw.write("			</content>\n");
					rs_content.close();
					

					sql = "Select * from rg_table r inner join ref_group g where g.g_id = r.g_id and ref_id="+ref_id;
					ResultSet rs_group = stmt_group.executeQuery(sql);
					
					bw.write("			<groups>\n");
					while(rs_group.next())
					{
						bw.write("				<group id=\""+rs_group.getString("g_id")+"\">");
						bw.write(rs_group.getString("name")+"</group>\n");
					}
					bw.write("			</groups>\n");
					rs_group.close();
					
					
					
					bw.write("			<creation_date>");
					bw.write(rs_reference.getString("creation_date"));
					bw.write("</creation_date>\n");
					
					bw.write("			<abstract>");
					bw.write(rs_reference.getString("abstract"));
					bw.write("</abstract>\n");

					bw.write("			<keywords>");
					bw.write(rs_reference.getString("keywords"));
					bw.write("</keywords>\n");

					bw.write("			<notes>");
					bw.write(rs_reference.getString("notes"));
					bw.write("</notes>\n");
					
					bw.write("			<quote>");
					bw.write(rs_reference.getString("quote"));
					bw.write("</quote>\n");

					bw.write("		</reference>\n");	
				}
				bw.write("	</type>\n");
				rs_reference.close();
			}
			bw.write("</refman>\n\n");

			bw.close();
			rs_type.close();
			stmt.close();
			stmt_reference.close();
			stmt_content.close();
			stmt_group.close();

			System.out.println("XML file successfully created");
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
	}
}