package Communication;

import java.util.*;
import java.sql.*;
import Model.*;

public class Search_DAO_DB  implements InterfaceDAO {

	private Vector in = new Vector();
	private Connection connection= null;
	private int type=0;
	private String author="";
	private String title="";
	private String year="";
	private int group=0;

	private StringBuffer statement= new StringBuffer();
	private StringBuffer select = new StringBuffer();
	private StringBuffer from= new StringBuffer(" reference r ,content c");
	private StringBuffer where= new StringBuffer(" where ");
	private StringBuffer basicsql= new StringBuffer();


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
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue(Object vo) {
		try
		{

			in = (Vector) vo;

			Reference_VO reference_vo = new Reference_VO();
			Vector<Reference_VO> out = new Vector<Reference_VO>();
			String textsearch="";

			type=(Integer)in.elementAt(0);
			author=(String)in.elementAt(2);
			title=(String)in.elementAt(4);
			year=(String)in.elementAt(6);
			group=(Integer)in.elementAt(in.size()-2);
			textsearch=(String)in.elementAt(in.size()-1);

			statement= new StringBuffer();
			select = new StringBuffer();
			from= new StringBuffer(" reference r ,content c ,rg_table rg");
			where= new StringBuffer(" where ");
			basicsql= new StringBuffer();
			boolean first = true;
			boolean first_content = true;
			select.append("SELECT DISTINCT r.ref_id, r.rt_id, r.creation_date, r.keywords, r.abstract, r.notes from" +
			"");

			//			statement.append("SELECT DISTINCT r.ref_id, r.rt_id, r.creation_date, r.keywords, r.abstract, r.notes" +
			//					" FROM reference r inner join content c  inner join attribute a inner join rg_table rg inner join ref_group g " +
			//					"WHERE r.ref_id=c.ref_id AND a.a_id=c.a_id AND r.ref_id=rg.ref_id and rg.g_id=g.g_id");
			/**/
			if(type!=0)
			{
				basicsql.append(select);
				basicsql.append(from);
				where.append(" r.rt_id= "+type+" ");
				basicsql.append(where);

				statement.append(basicsql);
				first= false;
			}
			if(!author.equals(""))
			{
				if (first_content)
				{
					if(first== false)
					{
						basicsql.append("and ");
						basicsql.append("c.ref_id = r.ref_id and");
						statement.append("and ");
						statement.append("c.ref_id = r.ref_id and");
					}
					else
					{
						basicsql.append(select);
						basicsql.append(from);
						basicsql.append(where);
						basicsql.append("c.ref_id = r.ref_id and");
						statement.append(basicsql);
					}
				}


				statement.append( " c.a_id=13 AND c.content='"+author+"'");
				first= false;
				first_content=false;
			}
			if(!title.equals(""))
			{
				setStatement(first, first_content);
				statement.append( " c.a_id=14 AND c.content='"+title+"'");
				first= false;
				first_content = false;
			}
			if(!year.equals(""))
			{
				setStatement(first, first_content);
				statement.append( " c.a_id=15 AND c.content='"+year+"'");
				first= false;
				first_content=false;
			}
			if(group!=0)
			{
				if(first_content)
				{
					if (first == false)
					{
						statement.append(" and ");
						statement.append(" r.ref_id= rg.ref_id and");
					}
					else
					{
						statement.append(select);
						statement.append(from);
						statement.append(where);
						statement.append("r.ref_id= rg.ref_id and");
						basicsql.append(select);
						basicsql.append(from);
						basicsql.append(where);
						basicsql.append("c.ref_id = r.ref_id and");
					}
				}
				else
				{
					statement.append("\nUNION\n");
					statement.append(select);
					statement.append(from);
					statement.append(where);
					statement.append(" and r.ref_id= rg.ref_id and");
				}
				statement.append( " rg.g_id= "+group);
				first= false;
				first_content=false;
			}
			if(!textsearch.equals(""))
			{
				setStatement(first, first_content);
				statement.append(" (c.content LIKE '%"+textsearch+"%' OR r.keywords LIKE '%"+textsearch+"%' OR r.abstract LIKE '%"+textsearch+"%' OR  r.notes LIKE '%"+textsearch+"%' OR  r.quote LIKE '%"+textsearch+"%') ");
				first= false;
				first_content=false;
			}

			for(int i=9; i<in.size()-2;i+=2)
			{
				String other="";
				int other_id=0;
				other= (String)in.elementAt(i-1);
				other_id= (Integer)in.elementAt(i);


				setStatement(first, first_content);
				statement.append( " c.a_id=" +other_id+" AND c.content='"+other+"'");
				first_content=false;
			}

			if ( first_content && first)
			{
				statement.append(select);
				statement.append(from);
			}
			
			connection= this.startDB();
			Statement stmt= connection.createStatement();


			System.out.println(statement.toString());

			ResultSet result= stmt.executeQuery(statement.toString());

			while(result.next())
			{
				reference_vo= new Reference_VO();

				reference_vo.setRef_id(Integer.parseInt(result.getString(1)));
				reference_vo.setRt_id(Integer.parseInt(result.getString(2)));
				reference_vo.setDate(result.getDate(3).getTime());
				reference_vo.setKeywords(result.getString(4));
				reference_vo.setRef_abstract(result.getString(5));
				reference_vo.setNotes(result.getString(6));
				out.add(reference_vo);			
			}
			return out;		

		}
		catch(SQLException e)
		{
			System.err.println("Error caused in getAttributes: "+e.getErrorCode());
			e.printStackTrace();				
		}
		return null;

	}

	private void setStatement (boolean first, boolean first_content)
	{

		if (first_content)
		{
			if(first== false)
			{
				basicsql.append("and ");
				basicsql.append("c.ref_id = r.ref_id and");
				statement.append("and ");
				statement.append("c.ref_id = r.ref_id and");
			}
			else
			{
				basicsql.append(select);
				basicsql.append(from);
				basicsql.append(where);
				basicsql.append("c.ref_id = r.ref_id and");
				statement.append(basicsql);
			}
		}
		else
		{
			statement.append("\nUNION\n");
			statement.append(basicsql);
		}
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
