package Model;

import java.util.*;

public class MainModel {

	private ModelInterface modelinterface;
	private Communicator communicator = new Communicator();

	public String[][] getType_AttrAll()
	{
		communicator.setInstance("type_attr");		
		String[][] ausgabe = (String[][]) communicator.getInstance().getAll();
		return ausgabe;		
	}

	public String[][] getGroupAll(){

		communicator.setInstance("ref_group");		
		String[][] ausgabe = (String[][]) communicator.getInstance().getAll();
		return ausgabe;		
	}		

	public String[][] getTypeAll()
	{
		communicator.setInstance("ref_type");		
		String[][] ausgabe = (String[][]) communicator.getInstance().getAll();
		return ausgabe;
	}

	public String[][] getRefAll(){

		//abe table
		communicator.setInstance("reference");		
		String[][] ausgabe = (String[][]) communicator.getInstance().getAll();
		for(int i = 0; i < ausgabe.length; i++)
		{
			Ref_Type_VO type = new Ref_Type_VO();
			type.setRef_type_id(Integer.parseInt(ausgabe[i][1]));
			ausgabe[i][6] = ((Ref_Type_VO)this.getTypeValue(type)).getRef_type_name();
//			ausgabe[i][1] = ((Ref_Type_VO)this.getTypeValue(type)).getRef_type_name();			
		}
		return ausgabe;		
	}


	public String[][] getRefGroupAll()
	{
		communicator.setInstance("reference_group");
		String[][] output = (String[][]) communicator.getInstance().getAll();
		return output;
	}
	
	public Ref_Group_VO getGroupValue(Ref_Group_VO group)
	{
		communicator.setInstance("ref_group");
		return (Ref_Group_VO) communicator.getInstance().getValue(group);
	}		

	public Object search(Vector data)

	{
		communicator.setInstance("search");
		Vector ausgabe= (Vector) communicator.getInstance().getValue(data);
		return ausgabe;

	}
	
	public void setReferenceValue(Reference_VO reference)
	{
		communicator.setInstance("reference");
		communicator.getInstance().setValue(reference);
	}

	public Object getReferenceValue(Reference_VO reference)
	{
		communicator.setInstance("reference");
		return communicator.getInstance().getValue(reference);
	}
	
	public void deleteReferenceValue(Reference_VO reference)
	{
		communicator.setInstance("reference");
		communicator.getInstance().deleteValue(reference);
	}

	public void setContentValue(Content_VO content)
	{
		communicator.setInstance("content");
		communicator.getInstance().setValue(content);
	}

	public Object getContentValue(Object content)
	{
		communicator.setInstance("content");
		return communicator.getInstance().getValue(content);
	}
	
	public void deleteContentValue(Object content)
	{
		communicator.setInstance("content");
		communicator.getInstance().deleteValue(content);
	}
	public Object getTypeValue(Ref_Type_VO ref_type)
	{
		communicator.setInstance("ref_type");
		return communicator.getValue(ref_type);
	}	

	public void setTypeValue(Ref_Type_VO ref_type)
	{
		communicator.setInstance("ref_type");	
		communicator.getInstance().setValue(ref_type);
		//		ref_type=(Ref_Type_VO) communicator.getValue(ref_type);
	}

	public Object getAttributeValue(Attribute_VO attribute)
	{
		communicator.setInstance("attribute");
		return communicator.getValue(attribute);
	}

	public void setAttributeValue(Attribute_VO attribute)
	{
		communicator.setInstance("attribute");
		System.out.println("mainmodel setValue");
		communicator.getInstance().setValue(attribute);
		//attribute=(Attribute_VO) communicator.getValue(attribute);
	}
	
	public Object getTypeAttrValue(Type_Attr_VO type_attr)
	
	{
		communicator.setInstance("type_attr");
		Object output= communicator.getInstance().getValue(type_attr);
		return output;
	}
	
	public Vector getTypeAttrValue(Ref_Type_VO ref_type)
	{
		communicator.setInstance("type_attr");
		Vector<Integer> output=(Vector<Integer>) communicator.getInstance().getValue(ref_type);
		return output;		
	}	

	public void setTypeAttrValue(Object type_attr)
	{
		communicator.setInstance("type_attr");	
		communicator.getInstance().setValue(type_attr);
	}

	public Vector<Reference_Group_VO> getRefGroupValue(Reference_Group_VO ref_group)
	{
		communicator.setInstance("reference_group");
		return (Vector<Reference_Group_VO>)communicator.getInstance().getValue(ref_group);
	}
	
	public void setRefGroupValue(Reference_Group_VO ref_group)
	{
		communicator.setInstance("reference_group");
		communicator.getInstance().setValue(ref_group);
	}		
	
	public void setGroupValue(Ref_Group_VO ref_group)
	{
		communicator.setInstance("ref_group");
		communicator.getInstance().setValue(ref_group);
	}

	public void deleteGroupValue(Ref_Group_VO ref_group)
	{
		communicator.setInstance("ref_group");
		communicator.getInstance().deleteValue(ref_group);
	}

	public void deleteType_Attr(Type_Attr_VO type_attr)
	{
		communicator.setInstance("type_attr");
		communicator.getInstance().deleteValue(type_attr);
	}
	
	public void deleteReferenceInGroupValue(Reference_Group_VO reference_group)
	{
		communicator.setInstance("reference_group");
		communicator.getInstance().deleteValue(reference_group);
	}
}
