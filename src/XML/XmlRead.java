package XML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import org.apache.xpath.XPathAPI;

import Model.Ref_Type_VO;

import java.sql.*;
import java.io.*;


import java.util.*;
import java.sql.*;

public class XmlRead {

	private String input;
	private String output;

	private String ref_type_name;
	private int ref_type_id;

	private Ref_Type_VO ref_type_vo = null;
	private static Connection connection = null;


	public XmlRead()
	{
	}
	
	
	public void main() {
		try
		{
			//-----------------------Connection 2 Database--------------------------

			Class.forName("com.mysql.jdbc.Driver").newInstance();		
			connection = DriverManager.getConnection
			("jdbc:mysql://i-intra-02.informatik.hs-ulm.de:3306/sofen_md5_04","sofen_md5_04","sofen_md5_04");
			//("jdbc:mysql://127.0.0.1:3306/sofen_md5_04","sofen_md5_04","sofen_md5_04");			


			Statement stmt_type = connection.createStatement();
			Statement stmt_reference = connection.createStatement();
			Statement stmt_group = connection.createStatement();
			Statement stmt_ra_table = connection.createStatement();
			Statement stmt_rg_table = connection.createStatement();
			Statement stmt_attribute = connection.createStatement();
			Statement stmt_content = connection.createStatement();

			//-----------------------DB Table Statements--------------------------

			String sql;

			sql = "Select* from ref_type";
			ResultSet rs_type = stmt_type.executeQuery(sql);;

			sql = "Select* from rg_table";
			ResultSet rs_rg_table = stmt_type.executeQuery(sql);



			//-----------------------import xml-File--------------------------

			File fXmlFile = new File("fileRead.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("type");
			System.out.println("-----------------------");

			//-----------------------read xml and write DB--------------------------

			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				Node nNode = nList.item(temp);
				String info;

				//------ check if type name already exists and write or not
				String type_name = XPathAPI.selectSingleNode(nNode, "@name").getNodeValue();
				System.out.println("Typename: " +type_name);

				sql = "Select* from ref_type where name='"+type_name+"'";
				rs_type = stmt_type.executeQuery(sql);

				boolean new_type = true;
				int id_type = 0;

				while (rs_type.next())
					if (type_name.equalsIgnoreCase(rs_type.getString("name")))
						new_type=false;

				if (new_type)
				{
					sql = "INSERT INTO ref_type (name) VALUES ('"+type_name+"')";
					stmt_type.executeUpdate(sql);

					info = "Set Type in Table:";
				}

				else
					info="Type is already in Table:";

				//------ get type id
				sql = "Select * from ref_type where name='"+type_name+"'";
				rs_type = stmt_type.executeQuery(sql);

				while (rs_type.next())
					id_type = Integer.parseInt(rs_type.getString("rt_id"));

				System.out.println(info+" "+id_type+" "+type_name);


				//------ Enter reference Node
				NodeList nList_reference = XPathAPI.selectNodeList(nNode,"reference");
				for(int j=0 ; j< nList_reference.getLength() ; j++ )
				{
					Node nNode_reference = nList_reference.item(j);					

					//------ Read reference values
					String date = XPathAPI.selectSingleNode(nNode_reference,"creation_date").getTextContent();
					System.out.println("  date: " +date);

					String abstract_ref = XPathAPI.selectSingleNode(nNode_reference,"abstract").getTextContent();
					System.out.println("  abstract: " +abstract_ref);

					String keywords = XPathAPI.selectSingleNode(nNode_reference,"keywords").getTextContent();
					System.out.println("  keywords: " +keywords);

					String notes = XPathAPI.selectSingleNode(nNode_reference,"notes").getTextContent();
					System.out.println("  notes: " +notes);

					String quote = XPathAPI.selectSingleNode(nNode_reference,"quote").getTextContent();
					System.out.println("  quote: " +quote);


					//------ create new Reference
					int id_ref = 0;

					sql = "INSERT INTO reference (rt_id, creation_date, keywords, abstract, notes, quote ) VALUES ('"+id_type+"', '"+date+"', '"+keywords+"', '"+abstract_ref+"', '"+notes+"', '"+quote+"')";
					stmt_reference.executeUpdate(sql);
					info ="  Set Attr in Table";

					//------ get new Reference id
					sql = "select * from reference where rt_id="+id_type+" and creation_date='"+date+"' and keywords='"+keywords+"' and abstract='"+abstract_ref+"' and notes='"+notes+"' and quote='"+quote+"'";
					ResultSet rs_reference = stmt_reference.executeQuery(sql);

					while(rs_reference.next())
						id_ref = Integer.parseInt(rs_reference.getString("ref_id"));

					System.out.println("  Wrote Reference with id: "+id_ref);


					//------ Enter content Node
					NodeList nList_content = XPathAPI.selectNodeList(nNode_reference,"content/attribute");
					for (int i=0 ; i<nList_content.getLength() ;i++)
					{
						Node nNode_content = nList_content.item(i);

						//------ check if attribute name already exists and write or not
						String attr_name = XPathAPI.selectSingleNode(nNode_content, "@name").getNodeValue();
						String attr_ausgabe = nNode_content.getTextContent();

						System.out.println("    "+attr_name+": "+attr_ausgabe);

						sql = "Select* from attribute where name='"+attr_name+"'";
						ResultSet rs_attribute = stmt_attribute.executeQuery(sql);

						boolean new_attr = true;
						int id_attr= 0;

						while (rs_attribute.next())
							if (attr_name.equalsIgnoreCase(rs_attribute.getString("name")))
								new_attr=false;


						if (new_attr)
						{
							sql = "INSERT INTO attribute (name) VALUES ('"+attr_name+"')";
							stmt_attribute.executeUpdate(sql);
							info ="    Set Attr in Table";
						}

						else
							info= "    Attr is already in Table:";

						//------ get attribute id
						sql = "Select * from attribute where name='"+attr_name+"'";
						rs_attribute = stmt_attribute.executeQuery(sql);

						while (rs_attribute.next())
							id_attr = Integer.parseInt(rs_attribute.getString("a_id"));

						System.out.println(info+" "+id_attr+" "+attr_name);

						//------ ra_table
						sql = "select * from ra_table where rt_id="+id_type+" AND a_id="+id_attr;
						ResultSet rs_ra_table = stmt_ra_table.executeQuery(sql);

						boolean newEntry = true;

						while (rs_ra_table.next())
							newEntry = false;

						if (newEntry)
						{
							sql = "INSERT INTO ra_table (rt_id, a_id) VALUE ("+id_type+", "+id_attr+")";
							stmt_ra_table.executeUpdate(sql);
							System.out.println("    new Entry in ra_table: "+id_type +" "+id_attr);
						}

						else
							System.out.println("    Entry in ra_table already exists: "+id_type +" "+id_attr);


						sql = "INSERT INTO content (ref_id, a_id, content) VALUE ("+id_ref+", "+id_attr+", '"+attr_ausgabe+"')";
						stmt_content.executeUpdate(sql);


						System.out.println("    new Entry in content table with ref_id:"+id_ref +" attr_id:"+id_attr+" content:"+attr_ausgabe);

					}
					//------ Enter groups Node
					NodeList nList_groups = XPathAPI.selectNodeList(nNode_reference,"groups/group");
					for (int i=0 ; i<nList_groups.getLength() ;i++)
					{
						Node nNode_groups = nList_groups.item(i);
						
						//------ check if group name already exists and write or not
						String group_name = nNode_groups.getTextContent();
						boolean new_group = true;
						int id_group = 0;
						
						sql = "select * from ref_group where name='"+group_name+"'";
						ResultSet rs_group = stmt_group.executeQuery(sql);
						
						while(rs_group.next())
							new_group=false;
						
						if (new_group)
						{
							sql = "INSERT INTO ref_group (name) VALUE ('"+group_name+"')";
							stmt_content.executeUpdate(sql);
							info ="    Set Group in Table";
						}

						else
							info= "    Group is already in Table:";
						
						//------ get group id
						sql = "Select * from ref_group where name='"+group_name+"'";
						rs_group = stmt_attribute.executeQuery(sql);

						while (rs_group.next())
							id_group = Integer.parseInt(rs_group.getString("g_id"));

						System.out.println(info+" "+id_group+" "+group_name);

					}
				}
				System.out.println("");
			}
			//-----------------------close Connection--------------------------

			rs_type.close();
			rs_rg_table.close();


			stmt_type.close();
			stmt_reference.close();
			stmt_group.close();
			stmt_ra_table.close();
			stmt_rg_table.close();
			stmt_attribute.close();


		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}