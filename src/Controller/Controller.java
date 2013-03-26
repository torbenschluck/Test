package Controller;

import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

//import of classes
import Model.*;
import View.*;

public class Controller 
{

	private LoginView loginview;
	private LoginModel loginmodel;
	private MainView mainview;
	private MainModel mainmodel;
	private MainPanel mainpanel;


	public Controller(LoginView loginview, LoginModel loginmodel, MainView mainview, MainModel mainmodel)
	{
		this.loginview = loginview;
		this.loginmodel = loginmodel;
		this.mainview = mainview;
		this.mainmodel = mainmodel;

		loginview.addLoginListener(new LoginListener());
		//		loginview.addRegisterListner(new RegisterListener());

		mainview.addButtonStartSearchListner(new StartSearchListener());
		mainview.addButtonNewTypeListener(new NewTypeListener());
		mainview.addButtonEditTypeListener(new EditTypeListener());

		mainview.addButtonNewReferenceListener(new NewReferenceListener());
		mainview.addButtonEditReferenceListener(new EditReferenceListener());

		mainview.addButtonGroupListener(new GroupListener());
		mainview.grouppanel.addButtonNewGroupListener(new NewGroupListener());
		mainview.grouppanel.addButtonDeleteGroupListener(new DeleteGroupListener());
		mainview.grouppanel.addButtonAddRefToGroupListener(new AddRefToGroupListener());
		mainview.grouppanel.addButtonRemoveRefFromGroupListener(new RemoveRefFromGroupListener());

		mainview.typepanel.addButtonSaveTypeListener(new SaveTypeListener());
		mainview.mainpanel.addButtonSearchListener(new SearchListener());
		mainview.mainpanel.addComboboxTypeListener(new SearchTypeListener());

		mainview.grouppanel.addComboboxGroupListener(new GroupValueListener());

		mainview.typeeditpanel.addComboboxTypeListener(new TypeValueListener());
		mainview.typeeditpanel.addButtonSaveEditTypeListener(new SaveEditTypeListener());

		mainview.refpanel.addComboboxTypeListener(new ReferenceTypeValueListener());
		mainview.refpanel.addButtonSaveRefListener(new SaveRefListener());
		mainview.refeditpanel.addButtonSaveRefEditListener(new SaveRefEditListener());
		mainview.refeditpanel.addButtonLoadRefEditListener(new LoadRefEditListener());
		mainview.refeditpanel.addButtonDeleteRefEditListener(new DeleteRefEditListener());
				
		loginview.setVisible(true);
		mainview.setVisible(false);
//		if(loginview.canI())
//		{
//			
//			loginview.setVisible(false);
//		}
	}

	//inner LoginListener class
	class LoginListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent actionevent)
		{

			String usernameinput = "";
			boolean valid=false; // User valid or not

			try 
			{
				usernameinput = loginview.getUsername();				
				valid=loginmodel.validateUser(usernameinput);
				loginview.setUsername(loginmodel.getUserLastname());
				mainview.buttonpanel.button_startsearch.doClick();
				loginview.closeLoginView(valid);
				mainview.setVisible(valid);		
			} 
			catch (NumberFormatException nfex) 
			{
				loginview.showError("Bad input: '" + usernameinput + "'");
			}
		}
	}

	class NewTypeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent actionevent) 
		{
			try
			{
				mainview.setTypePanel();
				String[][] content = mainmodel.getTypeAll();
				mainview.setTablePanel(content, "type");
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in NewTypeListener: "+exception.getMessage());
			}
		}
	}

	class EditTypeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent actionevent)
		{
			try
			{
				String[][] content = mainmodel.getTypeAll();
				mainview.setTypeEditPanel(content);
				mainview.setTablePanel(content, "type");
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in EditTypeListener: "+exception.getMessage());
			}
		}
	}

	class NewReferenceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent actionevent) 
		{
			try
			{
				String[][] content_type = mainmodel.getTypeAll();
				String[][] content_group = mainmodel.getGroupAll();
				String[][] content_ref = mainmodel.getRefAll();
				mainview.setRefPanel(content_type, content_group);
				mainview.setTablePanel(content_ref, "reference");
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in NewTypeListener: "+exception.getMessage());
			}
		}
	}

	class EditReferenceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent actionevent) 
		{
			try
			{
				int ref_id_table = 0;
				mainview.refeditpanel.removeAttributes();
				try
				{
					ref_id_table = mainview.tablepanel.getTableValue();
				}
				catch(Exception e){	}				
				
				String[][] content_ref = mainmodel.getRefAll();
				mainview.setTablePanel(content_ref, "reference");

				Reference_VO reference = new Reference_VO();		
				reference.setRef_id(ref_id_table);
				
				if(ref_id_table!=0 && ((Reference_VO)mainmodel.getReferenceValue(reference)).getRef_id()!=0)
				{					
					reference.setRef_id(ref_id_table);
				}
				else
				{
					reference.setRef_id(Integer.parseInt(content_ref[0][0]));
				}

				reference =	(Reference_VO)mainmodel.getReferenceValue(reference);				

				Ref_Type_VO reftype = new Ref_Type_VO();
				reftype.setRef_type_id(reference.getRt_id());
				reftype = (Ref_Type_VO) mainmodel.getTypeValue(reftype);
				mainview.setRefEditPanel(reftype.getRef_type_name());

				mainview.refeditpanel.setReference(reference);

				Vector<Content_VO> contents = new Vector<Content_VO>();

				Vector<Attribute_VO> attributes = new Vector<Attribute_VO>();
				Content_VO content = new Content_VO();
				Attribute_VO attribute = new Attribute_VO();
				content.setRef_id(reference.getRef_id());

				contents = (Vector<Content_VO>) mainmodel.getContentValue(content);

				for(int i = 0; i < contents.size(); i++)
				{
					attribute = new Attribute_VO();
					attribute.setAttribute_id(contents.get(i).getAttr_id());
					attribute = (Attribute_VO) mainmodel.getAttributeValue(attribute);
					attributes.add(attribute);
				}
				mainview.refeditpanel.setTypeAttributes(attributes);
				mainview.refeditpanel.setRefContents(contents);

			}
			catch(Exception exception)
			{
				System.err.println("Error caused in EditReferenceListener: "+exception.getMessage());
			}
		}
	}

	class GroupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent actionevent) 
		{
			try
			{
				String[][] content_group = mainmodel.getGroupAll();
				String[][] content_ref = mainmodel.getRefAll();
				mainview.setTablePanel(content_ref, "group");
				mainview.setGroupPanel(content_group);
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in GroupListener: "+exception.getMessage());
			}
		}		
	}

	class StartSearchListener implements ActionListener
	{
		public void actionPerformed(ActionEvent actionevent)
		{
			try
			{
				String [][] content_type = mainmodel.getTypeAll();
				String [][] content_group = mainmodel.getGroupAll();
				mainview.setMainPanel(content_type, content_group);	
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in StartSearchListener: "+exception.getMessage());
			}
		}
	}

	class SaveTypeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent actionevent)
		{
			try
			{
				Ref_Type_VO ref_type= new Ref_Type_VO();
				Attribute_VO attribute= new Attribute_VO();	
				Type_Attr_VO type_attr= new Type_Attr_VO();
				Object compare =new Object();
				Vector attributeNames= mainview.typepanel.getAttributeNames();

				ref_type.setRef_type_name(mainview.typepanel.getTypeName().toLowerCase());
				compare= (Ref_Type_VO) mainmodel.getTypeValue(ref_type);

				if(mainview.typepanel.getTypeName().trim().equals("")) //Typename field empty
					JOptionPane.showMessageDialog(null,"Please fill in a name for your type","Illegal Status",JOptionPane.WARNING_MESSAGE);
				else
				{
					if(compare==null)//Typname doesn't exist
					{
						mainmodel.setTypeValue(ref_type);
						ref_type= (Ref_Type_VO) mainmodel.getTypeValue(ref_type);
						System.out.println(ref_type.getRef_type_id());


						for(int i=0; i<attributeNames.size();i++)
						{
							attribute= new Attribute_VO();
							attribute.setAttribute_name((String) attributeNames.get(i));
							compare= (Attribute_VO) mainmodel.getAttributeValue(attribute);				
							if(compare==null)// Attribute doesn't exist
							{
								mainmodel.setAttributeValue(attribute);
								attribute = (Attribute_VO)mainmodel.getAttributeValue(attribute);
							}
							else// Attribute exists
							{
								//								JOptionPane.showMessageDialog(null,"There is already a attribute with this name\n it will automatically connected with this type","Type Error",JOptionPane.WARNING_MESSAGE);
								mainmodel.getAttributeValue(attribute);
							}
							type_attr.setRt_id(ref_type.getRef_type_id());
							type_attr.setA_id(attribute.getAttribute_id());
							mainmodel.setTypeAttrValue(type_attr);
						}
						for(int i=13;i<=15;i++)
						{
							type_attr.setRt_id(ref_type.getRef_type_id());
							type_attr.setA_id(i);
							mainmodel.setTypeAttrValue(type_attr);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null,"There is already a type with this name","Type Error",JOptionPane.WARNING_MESSAGE);
					}
				}
				mainview.typepanel.clearAllFields();

				String[][] content_type = mainmodel.getTypeAll();
				mainview.setTablePanel(content_type, "type");
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in SaveTypeListener: "+exception.getMessage());
			}
		}
	}


	class SaveEditTypeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) {

			Ref_Type_VO ref_type= new Ref_Type_VO();
			Attribute_VO attribute= new Attribute_VO();	
			Type_Attr_VO type_attr= new Type_Attr_VO();
			Object compare =new Object();
			Vector attributes= mainview.typeeditpanel.getAttributes(); //all attributes from the panel (old id new name)

			ref_type.setRef_type_name(((String)mainview.typeeditpanel.getTypeValue()).toLowerCase());
			ref_type= (Ref_Type_VO)mainmodel.getTypeValue(ref_type);
			Vector ids = mainmodel.getTypeAttrValue(ref_type);//all attributes related to this ref type

			for(int i=0; i<attributes.size();i++)

			{
				attribute= new Attribute_VO();
				attribute.setAttribute_name(((Attribute_VO)attributes.elementAt(i)).getAttribute_name());
				compare= (Attribute_VO) mainmodel.getAttributeValue(attribute);	

				Vector ref= new Vector();
				Reference_VO ref_dummy= new Reference_VO();
				Content_VO insert = new Content_VO();

				if (compare == null) //attribute doesn't exists

				{
					mainmodel.setAttributeValue(attribute);
					attribute = (Attribute_VO)mainmodel.getAttributeValue(attribute);

					int old_id =((Attribute_VO)attributes.elementAt(i)).getAttribute_id();
					if(old_id == 0) // its a new attribute and it doesn't replace another
					{

						type_attr.setRt_id(ref_type.getRef_type_id());
						type_attr.setA_id(attribute.getAttribute_id());
						mainmodel.setTypeAttrValue(type_attr);

						insert.setAttr_id(attribute.getAttribute_id());
						insert.setContent("empty");
						ref_dummy.setRt_id(type_attr.getRt_id());
//						ref=(Vector)mainmodel.getReferenceValue(ref_dummy);
						String[][] ref_string = mainmodel.getRefAll();
						for(int k=0 ;k<ref_string.length;k++)
						{
							if(Integer.parseInt(ref_string[k][1])==type_attr.getRt_id())
							{										
								ref.addElement((ref_string)[k][0]);
							}
						}

						for(int j=0;j<ref.size();j++)
						{
							Reference_VO dummy_ref = new Reference_VO();
							dummy_ref.setRef_id((Integer.parseInt(ref.elementAt(j).toString())));
							insert.setRef_id(dummy_ref.getRef_id());
							mainmodel.setContentValue(insert);
						}

					}

					else // the new attribute replacing another existing attribute
					{

						for(int j=0; j<ids.size(); j++)
						{
							if((Integer)ids.elementAt(j)== old_id)
							{
								type_attr.setRt_id(ref_type.getRef_type_id()); 
								type_attr.setA_id(attribute.getAttribute_id());
								mainmodel.setTypeAttrValue(type_attr); // set new attribute to type

								type_attr.setA_id(old_id);
								mainmodel.deleteType_Attr(type_attr); // delete relation to old type

								insert.setAttr_id(old_id);
								ref_dummy.setRt_id(type_attr.getRt_id());
//								ref=(Vector)mainmodel.getReferenceValue(ref_dummy);	
								String[][] ref_string = mainmodel.getRefAll();
								for(int k=0 ;k<ref_string.length;k++)
								{
									if(Integer.parseInt(ref_string[k][1])==type_attr.getRt_id())
									{										
										ref.addElement((ref_string)[k][0]);
									}
								}

								for(int k=0;k<ref.size();k++)
								{
									Reference_VO dummy_ref = new Reference_VO();
									dummy_ref.setRef_id((Integer.parseInt(ref.elementAt(k).toString())));
									insert.setRef_id((dummy_ref.getRef_id()));									
									insert= (Content_VO)mainmodel.getContentValue(insert); // take content with old attribute name

									insert.setAttr_id(attribute.getAttribute_id());		//set the a_id of the new name
									mainmodel.setContentValue(insert);
									insert.setAttr_id(old_id);
									mainmodel.deleteContentValue(insert); // delete the relation to the old attribute name
								}
							}
						}
					}
				}

				else //attribute exists
				{

					compare= new Object();
					type_attr.setRt_id(ref_type.getRef_type_id());
					type_attr.setA_id(attribute.getAttribute_id());
					int old_id =((Attribute_VO)attributes.elementAt(i)).getAttribute_id();

					compare= (Type_Attr_VO) mainmodel.getTypeAttrValue(type_attr);



					if(compare==null) //attr exists no relation to type
					{
						mainmodel.setTypeAttrValue(type_attr); // set relation to type

						if(old_id!=0) // the new existing attribute replaces an existing attribute
						{
							type_attr.setA_id(old_id);
							mainmodel.deleteType_Attr(type_attr);	//delete old relation to type

							insert.setAttr_id(old_id);
							ref_dummy.setRt_id(type_attr.getRt_id());
//							ref=(Vector)mainmodel.getReferenceValue(ref_dummy);	
							String[][] ref_string = mainmodel.getRefAll();
							for(int k=0 ;k<ref_string.length;k++)
							{
								if(Integer.parseInt(ref_string[k][1])==type_attr.getRt_id())
								{										
									ref.addElement((ref_string)[k][0]);
								}
							}

							for(int k=0;k<ref.size();k++)
							{
								Reference_VO dummy_ref = new Reference_VO();
								dummy_ref.setRef_id(Integer.parseInt(ref.elementAt(k).toString()));
								insert.setRef_id(dummy_ref.getRef_id());
								insert= (Content_VO)mainmodel.getContentValue(insert); // take content with old attribute name

								insert.setAttr_id(attribute.getAttribute_id());		//set the a_id of the new name
								mainmodel.setContentValue(insert);
								insert.setAttr_id(old_id);
								mainmodel.deleteContentValue(insert); // delete the relation to the old attribute name
							}

						}

					}
					else //attr exists relation to type
					{}

				}

				//				type_attr.setRt_id(ref_type.getRef_type_id());
				//				type_attr.setA_id(attribute.getAttribute_id());
				//				mainmodel.setTypeAttrValue(type_attr);
			}

			mainview.typeeditpanel.clearAllFields();

			String[][] content_type = mainmodel.getTypeAll();
			mainview.setTablePanel(content_type, "type");
		}

	}


	class SearchListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			Vector data= new Vector();
			Ref_Type_VO reftype= new Ref_Type_VO(); 
			Ref_Group_VO group = new Ref_Group_VO();
			Attribute_VO dummy= new Attribute_VO();
			data=mainview.mainpanel.getSearch();
			if(data.elementAt(0).equals("")||data.elementAt(0).equals(null))
				data.setElementAt(0,0);
			else
			{
				reftype.setRef_type_name((String)data.elementAt(0));
				reftype= (Ref_Type_VO)mainmodel.getTypeValue(reftype);
				data.setElementAt(reftype.getRef_type_id(),0);
			}
			if(data.elementAt(data.size()-2).equals("")||data.elementAt(data.size()-2).equals(null))
				data.setElementAt(0,data.size()-2);
			else
			{
				group.setRef_group_name((String)data.elementAt(data.size()-2));
				group= (Ref_Group_VO)mainmodel.getGroupValue(group);
				data.setElementAt(group.getRef_group_id(),data.size()-2);
			}
				if(data.size()>10)
				{
					for(int i=9; i< data.size()-2;i+=2)
						{
							dummy= new Attribute_VO();
						
							dummy.setAttribute_name((String)data.elementAt(i));
							dummy=(Attribute_VO)mainmodel.getAttributeValue(dummy);
							data.setElementAt(dummy.getAttribute_id(),i);

							System.out.println(data.elementAt(i).toString());
						}
				}

			Vector<Reference_VO> refs = (Vector<Reference_VO>)mainmodel.search(data);
			String[][] reftable = new String[refs.size()][7];
			for(int i = 0; i < refs.size(); i++)
			{
				Reference_VO reference = new Reference_VO();
				reference=refs.elementAt(i);

				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(reference.getDate());
				reftable[i][0] = ""+reference.getRef_id();
//				reftable[i][1] = ""+reference.getRt_id();
				//abe
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				
				reftable[i][2] = dateformat.format((reference.getDate())); 
				reftable[i][3] = reference.getKeywords();
				reftable[i][4] = reference.getRef_abstract();
				reftable[i][5] = reference.getNotes();
//				Ref_Type_VO type = new Ref_Type_VO();
//				type.setRef_type_id(reference.getRt_id());
				reftable[i][6] = ((Ref_Type_VO)mainmodel.getTypeValue(reftype)).getRef_type_name();
			}
			//			System.out.println("notes: "+reftable[0][5]);
			mainview.tablepanel.setValueAll(reftable,"reference");
		}

	}
	class GroupValueListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				if(mainview.grouppanel.getGroupValue().toString().equals(""))
				{				
					mainview.grouppanel.setTableInvisible();				
				}
				else{
					mainview.grouppanel.getGroupValue();
					//					String[][] references_in_group = mainmodel.getRefAll();
					//					mainview.grouppanel.setValueAll(references_in_group);
					//getAll references in particular group
					Ref_Group_VO group = new Ref_Group_VO();
					group.setRef_group_name(mainview.grouppanel.getGroupValue().toString());
					group = mainmodel.getGroupValue(group);
					Reference_Group_VO refgroup = new Reference_Group_VO();
					refgroup.setG_id(group.getRef_group_id());
					Vector<Reference_Group_VO> refgroups = mainmodel.getRefGroupValue(refgroup);
					String[][] refcombo = new String[refgroups.size()][6];
					for(int i = 0; i < refgroups.size(); i++)
					{
						Reference_VO reference = new Reference_VO();
						reference.setRef_id(refgroups.get(i).getRef_id());
						reference = (Reference_VO)mainmodel.getReferenceValue(reference);
						Calendar cal = Calendar.getInstance();
						cal.setTimeInMillis(reference.getDate());
						refcombo[i][0] = ""+reference.getRef_id();
//						refcombo[i][1] = ""+reference.getRt_id();
						//abe table
						Ref_Type_VO type = new Ref_Type_VO();
						type.setRef_type_id(reference.getRt_id());
						refcombo[i][1] = ((Ref_Type_VO)mainmodel.getTypeValue(type)).getRef_type_name();

						SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

						refcombo[i][2] = dateformat.format((reference.getDate()));
						refcombo[i][3] = reference.getKeywords();
						refcombo[i][4] = reference.getRef_abstract();
						refcombo[i][5] = reference.getNotes();						
					}
					mainview.grouppanel.setValueAll(refcombo);					
				}
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in GroupValueListener: "+exception.getMessage());
			}
		}		
	}

	class NewGroupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Ref_Group_VO ref_group = new Ref_Group_VO();
				if(!mainview.grouppanel.getGroupname().equalsIgnoreCase(""))
				{
					ref_group.setRef_group_name(mainview.grouppanel.getGroupname());
					mainmodel.setGroupValue(ref_group);
					//could be to much
					mainview.grouppanel.clearAllFields();
					mainview.setGroupPanel(mainmodel.getGroupAll());
				}
				else
				{
					JOptionPane.showMessageDialog(null,"invalid groupname!","Illegal Status",JOptionPane.WARNING_MESSAGE);
					System.err.println("empty groupnamefield");
				}
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in NewGroupListener: "+exception.getMessage());
			}
		}
	}

	class DeleteGroupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Ref_Group_VO ref_group = new Ref_Group_VO();
				if(!mainview.grouppanel.getGroupValue().toString().equalsIgnoreCase(""))
				{
					ref_group.setRef_group_name(mainview.grouppanel.getGroupValue().toString());
					mainmodel.deleteGroupValue(ref_group);					
					mainview.setGroupPanel(mainmodel.getGroupAll());					
				}				
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in DeleteGroupListener: "+exception.getMessage());
			}
		}
	}

	class AddRefToGroupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				int ref_id_table = 0;
				String groupname = "";
				ref_id_table = mainview.tablepanel.getTableValue();

				if(ref_id_table!=0)										
				{
					Ref_Group_VO group = new Ref_Group_VO();
					groupname = mainview.grouppanel.getGroupValue().toString();
					if(!groupname.equalsIgnoreCase(""))
					{
						group.setRef_group_name(groupname);

						group = mainmodel.getGroupValue(group);
						Reference_Group_VO ref_group = new Reference_Group_VO();
						ref_group.setRef_id(ref_id_table);
						ref_group.setG_id(group.getRef_group_id());

						Vector<Reference_Group_VO> rg_values = new Vector<Reference_Group_VO>();

						rg_values = mainmodel.getRefGroupValue(ref_group);

						boolean refingroup = false;

						for(int i = 0; i < rg_values.size(); i++)
						{
							rg_values.get(i).getRef_id();
							if(rg_values.get(i).getRef_id()==ref_group.getRef_id() && rg_values.get(i).getG_id()==ref_group.getG_id())
							{
								JOptionPane.showMessageDialog(null,"selected reference is already in group!","Note!",JOptionPane.WARNING_MESSAGE);
								refingroup = true;
							}							
						}												
						rg_values.removeAllElements();
						if(refingroup==false)
						{
							mainmodel.setRefGroupValue(ref_group);
						}
					}
				}				

				String[][] content_group = mainmodel.getGroupAll();
				String[][] content_ref = mainmodel.getRefAll();
				mainview.setTablePanel(content_ref, "group");
				mainview.grouppanel.setGroupValue(groupname);
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in AddRefToGroupListener: "+exception.getMessage());
			}
		}
	}

	class RemoveRefFromGroupListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				int ref_id_table = 0;
				String groupname = "";
				ref_id_table = mainview.grouppanel.getTable().getTableValue();

				if(ref_id_table!=0)
				{
					Ref_Group_VO group = new Ref_Group_VO();
					groupname = mainview.grouppanel.getGroupValue().toString();
					if(!groupname.equalsIgnoreCase(""))
					{
						group.setRef_group_name(groupname);

						group = mainmodel.getGroupValue(group);
						Reference_Group_VO ref_group = new Reference_Group_VO();
						ref_group.setRef_id(ref_id_table);
						ref_group.setG_id(group.getRef_group_id());
						//						mainmodel.setRefGroupValue(ref_group);
						mainmodel.deleteReferenceInGroupValue(ref_group);
					}
				}				

				String[][] content_group = mainmodel.getGroupAll();
				String[][] content_ref = mainmodel.getRefAll();
				mainview.setTablePanel(content_ref, "group");
				mainview.grouppanel.setGroupValue(groupname);
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in RemoveRefFromGroupListener: "+exception.getMessage());
			}
		}
	}

	class ReferenceTypeValueListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				if(!mainview.refpanel.getTypeValue().toString().equals(""))
				{	
					mainview.refpanel.removeAttributes();
					mainview.refpanel.clearAllFields();

					Ref_Type_VO ref_type = new Ref_Type_VO();
					ref_type.setRef_type_name(mainview.refpanel.getTypeValue().toString());
					ref_type = (Ref_Type_VO) mainmodel.getTypeValue(ref_type);
					Vector<Integer> attr_ids = mainmodel.getTypeAttrValue(ref_type);
					if(!attr_ids.isEmpty())
					{
						Vector<Attribute_VO> attribute = new Vector<Attribute_VO>();
						for(int i = 0; i < attr_ids.size(); i++	)
						{					
							Attribute_VO dummy_attribute = new Attribute_VO();
							dummy_attribute.setAttribute_id(attr_ids.get(i));					
							attribute.add(((Attribute_VO)mainmodel.getAttributeValue(dummy_attribute)));						
						}
						mainview.refpanel.setTypeAttributes(attribute);
					}
					else
					{
						mainview.refpanel.setTypeAttributes(null);
					}
				}				
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in ReferenceTypeValueListener: "+exception.getMessage());
			}
		}
	}

	class SaveRefListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				Reference_VO reference = new Reference_VO();
				reference = mainview.refpanel.getReference();
				Calendar cal = Calendar.getInstance(); 
				reference.setDate(cal.getTimeInMillis());				
				//get ref_type				
				Ref_Type_VO ref_type = new Ref_Type_VO();
				ref_type.setRef_type_name(mainview.refpanel.getTypeValue().toString());
				ref_type = (Ref_Type_VO) mainmodel.getTypeValue(ref_type);

				reference.setRt_id(ref_type.getRef_type_id());
				//				mainmodel.setReferenceValue(reference);				
				//				reference = mainmodel.getReferenceValue(reference);

				Vector<Content_VO> contents = new Vector<Content_VO>();
				contents = mainview.refpanel.getAttributeContents();
				boolean emptycon = false;
				boolean falsedate = false;
				
				for(int i = 0; i < contents.size(); i++)
				{
					if(contents.get(i).getAttr_id()==13 || contents.get(i).getAttr_id() ==14 || contents.get(i).getAttr_id() ==15)
					{
						if(contents.get(i).getContent().trim().equals(""))
						{
							emptycon = true;							
						}
						if(contents.get(i).getAttr_id()==15)
						{
							SimpleDateFormat df = new SimpleDateFormat("yyyy");
							try
							{
								int date = Integer.parseInt(contents.get(i).getContent());
							}
							catch(Exception e2)
							{
								falsedate = true;
							}														
						}
					}
				}
				if(reference.getQuote().trim().equals(""))
				{
					emptycon = true;
				}
				
				if(emptycon==true)
				{
					JOptionPane.showMessageDialog(null,"author, title, year attributes\nand the actual quote\nmust be filled in!","Warning!",JOptionPane.WARNING_MESSAGE);
				}
				else if(falsedate==true)
				{
					JOptionPane.showMessageDialog(null,"        invalid year!","Warning!",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					mainmodel.setReferenceValue(reference);				
					reference = (Reference_VO) mainmodel.getReferenceValue(reference);
					for(int i = 0; i < contents.size(); i++)
					{																	
						contents.get(i).setRef_id(reference.getRef_id());
						mainmodel.setContentValue(contents.get(i));
					}
					mainview.refpanel.clearAllFields();
				}
				Ref_Group_VO group = new Ref_Group_VO();
				String groupname = mainview.refpanel.getGroupValue().toString();
				if(!groupname.equalsIgnoreCase(""))
				{
					group.setRef_group_name(groupname);

					group = mainmodel.getGroupValue(group);
					Reference_Group_VO ref_group = new Reference_Group_VO();
					ref_group.setRef_id(reference.getRef_id());
					ref_group.setG_id(group.getRef_group_id());
					mainmodel.setRefGroupValue(ref_group);					
				}

				

				String[][] content_ref = mainmodel.getRefAll();
				mainview.setTablePanel(content_ref, "reference");
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in SaveRefListener: "+exception.getMessage());
			}
		}
	}

	class SaveRefEditListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) 
		{
			try
			{

//				int ref_id_table = 0;
//				mainview.tablepanel.getTableValue();

				Reference_VO reference = new Reference_VO();
				reference = mainview.refeditpanel.getReference();
				Calendar cal = Calendar.getInstance(); 
				reference.setDate(cal.getTimeInMillis());						

				//get ref_type				
				Ref_Type_VO ref_type = new Ref_Type_VO();
				ref_type.setRef_type_id(reference.getRt_id());
				ref_type = (Ref_Type_VO) mainmodel.getTypeValue(ref_type);						

//				mainmodel.setReferenceValue(reference);				
//				reference = mainmodel.getReferenceValue(reference);

				Vector<Content_VO> contents = new Vector<Content_VO>();
				contents = mainview.refeditpanel.getRefContents();
				Content_VO dummy_content = new Content_VO();
				
				boolean emptycon = false;
				boolean falsedate = false;
				
				for(int i = 0; i < contents.size(); i++)
				{
					if(contents.get(i).getAttr_id()==13 || contents.get(i).getAttr_id() ==14 || contents.get(i).getAttr_id() ==15)
					{
						if(contents.get(i).getContent().trim().equals(""))
						{
							emptycon = true;							
						}												
						if(contents.get(i).getAttr_id()==15)
						{
							SimpleDateFormat df = new SimpleDateFormat("yyyy");
							try
							{
								int date = Integer.parseInt(contents.get(i).getContent());
							}
							catch(Exception e)
							{
								falsedate = true;
							}														
						}
					}
				}
				if(reference.getQuote().trim().equals(""))
				{
					emptycon = true;					
				}

				if(emptycon==true)
				{
					JOptionPane.showMessageDialog(null,"author, title, year attributes\nand the actual quote\nmust be filled in!","Warning!",JOptionPane.WARNING_MESSAGE);
				}
				else if(falsedate==true)
				{
					JOptionPane.showMessageDialog(null,"        invalid year!","Warning!",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					mainmodel.setReferenceValue(reference);				
					reference = (Reference_VO)mainmodel.getReferenceValue(reference);
					for(int i = 0; i < contents.size(); i++)
					{															
						mainmodel.setContentValue(contents.get(i));
					}
					mainview.refeditpanel.clearAllFields();
				}

				String[][] content_ref = mainmodel.getRefAll();
				mainview.setTablePanel(content_ref, "reference");
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in SaveRefEditListener: "+exception.getMessage());
			}
		}

	}

	class LoadRefEditListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) 
		{
			try
			{
				int ref_id_table = 0;
				mainview.refeditpanel.removeAttributes();

				ref_id_table = mainview.tablepanel.getTableValue();
				Reference_VO reference = new Reference_VO();

				if(ref_id_table==0)
				{
					reference.setRef_id(91);
				}
				else
				{
					reference.setRef_id(ref_id_table);
				}

				reference =	(Reference_VO) mainmodel.getReferenceValue(reference);				

				Ref_Type_VO reftype = new Ref_Type_VO();
				reftype.setRef_type_id(reference.getRt_id());
				reftype = (Ref_Type_VO) mainmodel.getTypeValue(reftype);
				mainview.setRefEditPanel(reftype.getRef_type_name());

				mainview.refeditpanel.setReference(reference);

				Vector<Content_VO> contents = new Vector<Content_VO>();

				Vector<Attribute_VO> attributes = new Vector<Attribute_VO>();
				Content_VO content = new Content_VO();
				Attribute_VO attribute = new Attribute_VO();
				content.setRef_id(reference.getRef_id());

				contents = (Vector<Content_VO>) mainmodel.getContentValue(content);

				for(int i = 0; i < contents.size(); i++)
				{
					attribute = new Attribute_VO();
					attribute.setAttribute_id(contents.get(i).getAttr_id());
					attribute = (Attribute_VO) mainmodel.getAttributeValue(attribute);
					attributes.add(attribute);
				}
				mainview.refeditpanel.setTypeAttributes(attributes);
				mainview.refeditpanel.setRefContents(contents);
			}
			catch(Exception exception)
			{
				System.err.println("Error caused in LoadRefEditListener: "+exception.getMessage());
			}
		}

	}
	
	class DeleteRefEditListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			int ref_id_table = 0;
			try
			{
				ref_id_table = mainview.tablepanel.getTableValue();
			}
			catch(Exception ex)
			{
				System.err.println("no reference to delete selected");
			}
			
			Reference_VO reference = new Reference_VO();
//			reference = mainview.refeditpanel.getReference();
			reference.setRef_id(ref_id_table);
			System.out.println("del ref: "+reference.getRef_id());
			if(reference.getRef_id()!=0)
			{
				mainmodel.deleteReferenceValue(reference);
			}			
			String[][] content_ref = mainmodel.getRefAll();
			mainview.setTablePanel(content_ref, "reference");
		}		
	}

	class TypeValueListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Ref_Type_VO reftype = new Ref_Type_VO();
			Vector typeattr = new Vector();
			Vector attridname = new Vector();


			if (mainview.typeeditpanel.getTypeValue()!="")
			{
				reftype.setRef_type_name((String)mainview.typeeditpanel.getTypeValue());
				reftype = (Ref_Type_VO)mainmodel.getTypeValue(reftype);

				typeattr= mainmodel.getTypeAttrValue(reftype);

				for (int i=0 ; i<typeattr.size() ; i++)
				{
					if ((Integer)typeattr.get(i) != 13 && (Integer)typeattr.get(i) != 14  &&
							(Integer)typeattr.get(i) != 15 ) 
						//if attribute not author, title or year
					{
						Attribute_VO attr = new Attribute_VO();
						attr.setAttribute_id((Integer)(typeattr.elementAt(i)));
						attridname.add((Attribute_VO)mainmodel.getAttributeValue(attr));
					}
				}	

				mainview.typeeditpanel.setAttr(attridname);
			}
			//mainview.typeeditpanel.removeAttributes();
		}		
	}
	class SearchTypeListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Ref_Type_VO reftype = new Ref_Type_VO();
			Vector typeattr = new Vector();
			Vector attridname = new Vector();

			if (mainview.mainpanel.getTypeValue()!="")
			{
				reftype.setRef_type_name((String)mainview.mainpanel.getTypeValue());
				reftype = (Ref_Type_VO)mainmodel.getTypeValue(reftype);

				typeattr= mainmodel.getTypeAttrValue(reftype);

				if (typeattr != null)
				{
					for (int i=0 ; i<typeattr.size() ; i++)
					{
						if ((Integer)typeattr.get(i) != 13 && (Integer)typeattr.get(i) != 14  &&
								(Integer)typeattr.get(i) != 15 ) 
							//if attribute not author, title or year
						{
							Attribute_VO attr = new Attribute_VO();
							attr.setAttribute_id((Integer)(typeattr.elementAt(i)));
							attridname.add((Attribute_VO)mainmodel.getAttributeValue(attr));
						}
					}	
				}
				mainview.mainpanel.setAttr(attridname);
			}
		}		
	}
}
