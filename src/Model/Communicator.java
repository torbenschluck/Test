package Model;

import Communication.*;

public class Communicator {

	private InterfaceDAO interface_dao;	
	
	public void setInstance(Object vo)
	{
		if(Ref_Group_VO.class.equals(vo.getClass()))
		{
			interface_dao = new Ref_Group_DAO_DB();	
		}
		if(Ref_Type_DAO_DB.class.equals(vo.getClass()))
		{
			interface_dao = new Ref_Type_DAO_DB();
		}	
		if(Ref_Type_DAO_DB.class.equals(vo.getClass()))
		{
			interface_dao = new Type_Attr_DAO_DB();
		}		
	}
	
	public void setInstance(String tabletype)
	{		
		tabletype.toLowerCase();
		if(tabletype.trim().equalsIgnoreCase("ref_group"))
		{
			interface_dao = new Ref_Group_DAO_DB();
		}
		if(tabletype.trim().equalsIgnoreCase("ref_type"))
		{
			interface_dao = new Ref_Type_DAO_DB();
		}
		if(tabletype.trim().equalsIgnoreCase("reference"))
		{
			interface_dao = new Reference_DAO_DB();
		}
		if(tabletype.trim().equalsIgnoreCase("attribute"))
		{
			interface_dao = new Attribute_DAO_DB();
		}
		if(tabletype.trim().equalsIgnoreCase("type_attr"))
		{
			interface_dao = new Type_Attr_DAO_DB();
		}
		if(tabletype.trim().equalsIgnoreCase("content"))
		{
			interface_dao = new Content_DAO_DB();
		}
		if(tabletype.trim().equalsIgnoreCase("reference_group"))
		{
			interface_dao = new Reference_Group_DAO_DB();
		}
		if(tabletype.trim().equalsIgnoreCase("search"))
		{
			interface_dao = new Search_DAO_DB();
		}
	}
	
	public InterfaceDAO getInstance()
	{
		return interface_dao;
	}	
	
	public void setValue(Object vo)
	{
		interface_dao.setValue(vo);
	}
	
	public Object getValue(Object vo)
	{
		return interface_dao.getValue(vo);
	}
	
	public String[][] getAll()
	{
		return interface_dao.getAll();
	}
	
	public void deleteValue(Object vo)
	{
		interface_dao.deleteValue(vo);
	}
}
