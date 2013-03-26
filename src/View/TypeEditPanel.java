package View;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Model.*;

public class TypeEditPanel extends JPanel implements ActionListener{

	//labels
	private JLabel label_capture = new JLabel("Edit Type:");
	private JLabel label_description = new JLabel("Choose Type:");
	private JLabel label_description2 = new JLabel("Custom Type-Attributes:");


	private JLabel label_predefined = new JLabel("Predefined");
	private JLabel label_predefined2 = new JLabel("Attributes:");
	private JLabel label_author = new JLabel("Author(s)");
	private JLabel label_title = new JLabel("Title");
	private JLabel label_year = new JLabel("Year");
	private JLabel[] label_otherattributes = new JLabel[12];

	//inputs
	private JComboBox combobox_type = new JComboBox();
	private JTextField textfield_author = new JTextField();
	private JTextField textfield_title = new JTextField();
	private JTextField textfield_year = new JTextField();
	private JTextField[] textfield_otherattributes= new JTextField[12];
	private JButton button_addattribute = new JButton("Add Attribute");
	private JButton button_deleteattribute = new JButton("Delete Attribute");
	private JButton button_save = new JButton("Save");

	private String[][] types;
	private Vector<Attribute_VO> attributes = new Vector<Attribute_VO>();

	private int count=4; //reset count to 4 when this panel changed
	private int i;

	public TypeEditPanel()
	{
		init();
	}

	private void init()
	{
		this.setSize(800,300);
		this.setLayout(null);

		this.add(combobox_type);

		this.add(label_capture);
		this.add(label_description);
		this.add(label_description2);


		this.add(label_predefined);
		this.add(label_predefined2);
		this.add(label_author);
		this.add(label_title);
		this.add(label_year);


		this.add(textfield_author);
		this.add(textfield_title);
		this.add(textfield_year);

		this.add(button_addattribute);
		this.add(button_deleteattribute);
		this.add(button_save);


		label_capture.setBounds(350, 10, 200, 20);
		label_capture.setFont(new Font("Arial", Font.BOLD, 20));

		label_description.setBounds(20, 50, 200, 20);
		label_description.setFont(new Font("Arial", Font.BOLD, 15));

		label_description2.setBounds(400, 50, 350, 20);
		label_description2.setFont(new Font("Arial", Font.BOLD, 15));

		combobox_type.setBounds(130, 50, 150, 20);


		label_predefined.setBounds(20,150,100,20);
		label_predefined2.setBounds(22,170,100,20);
		label_author.setBounds(120,130,100,20);
		label_title.setBounds(120,160,100,20);
		label_year.setBounds(120,190,100,20);



		button_addattribute.setBounds(250,250,150,20);
		button_deleteattribute.setBounds(410,250,150,20);
		button_save.setBounds(650, 270, 100, 20);
		button_deleteattribute.setEnabled(false);

		button_addattribute.addActionListener(this);
		button_deleteattribute.addActionListener(this);
		combobox_type.addActionListener(this);
	}

	private void setVisibility (boolean visible)
	{
		label_description2.setVisible(visible);


		label_predefined.setVisible(visible);
		label_predefined2.setVisible(visible);
		label_author.setVisible(visible);
		label_title.setVisible(visible);
		label_year.setVisible(visible);


		button_addattribute.setVisible(visible);
		button_deleteattribute.setVisible(visible);
		button_save.setVisible(visible);
	}

	public void fillType (String[][] content)
	{
		combobox_type.removeAllItems();
		types = content;

		combobox_type.addItem("");
		for (i=0 ; i<types.length ; i++ )
		{
			combobox_type.addItem(types[i][1]);
		}
	}

	public void removeAttributes()
	{	
		if(label_otherattributes!= null){
		for(JLabel l : label_otherattributes){
			if(l!=null)this.remove(l);
		}
		}
		if(textfield_otherattributes!= null){
		for(JTextField l : textfield_otherattributes){
			if(l!=null)this.remove(l);
		}
		}
		this.repaint();

		label_otherattributes = new JLabel[12];		
		textfield_otherattributes = new JTextField[12];	
		//		//this.removeAll();		
	}

	public Object getTypeValue()
	{
		return(combobox_type.getSelectedItem());
	}

	public void setAttr(Vector attridname)
	{

		//		try
		//		{
		//			if (label_otherattributes.length != 0)
		//			{
		//				for (i=0; i<label_otherattributes.length ; i++)
		//				{
		//					System.out.println("im here");
		//					label_otherattributes[i].setText("");
		//				}
		//			}
		//		}catch (Exception e)
		//		{}



		attributes = attridname;
		createLabels();

	}

	private void createLabels() {
		removeAttributes();
		//		for (i=0,count=4 ; i<attributes.size() ; count++, i++)
		//		{
		//			try
		//			{
		//				if(count<8)
		//				{	
		//					label_otherattributes[count] = new JLabel("Attribute nr: "+(count+1));
		//					textfield_otherattributes[count] = new JTextField();
		//					this.add(label_otherattributes[count]);
		//					this.add(textfield_otherattributes[count]);
		//					label_otherattributes[count].setBounds(270, 80+(20*(count-3)+10*(count-4)), 100, 20);
		//					textfield_otherattributes[count].setBounds(360, 80+(20*(count-3)+10*(count-4)), 100, 20);
		//					textfield_otherattributes[count].setText(((Attribute_VO)attributes.elementAt(i)).getAttribute_name());
		//				}
		//				else if(count<=12)
		//				{
		//					label_otherattributes[count] = new JLabel("Attribute no: "+(count+1));
		//					textfield_otherattributes[count] = new JTextField();
		//					this.add(label_otherattributes[count]);
		//					this.add(textfield_otherattributes[count]);
		//					label_otherattributes[count].setBounds(540, 80+(20*(count-7)+10*(count-8)), 100, 20);
		//					textfield_otherattributes[count].setBounds(650, 80+(20*(count-7)+10*(count-8)), 100, 20);
		//					textfield_otherattributes[count].setText(((Attribute_VO)attributes.elementAt(i)).getAttribute_name());
		//				}
		//			}catch(Exception e){System.err.println("Button addatribute excepton");
		//			}
		//		}
		//		

		int maxLabelsPerLine = 4;
		int currentRow = 0;
		int currentLine = 4;


		for(int i = 0; i<attributes.size();i++){
			if(i%maxLabelsPerLine == 0){
				//new row
				currentLine = 0;
				currentRow++;
			}

			addField(attributes.get(i).getAttribute_name(),currentRow,currentLine,i);

			currentLine++;

		}
		if(attributes.size()>7){
			//TODO not tested
			button_addattribute.setEnabled(false);
		}
		button_deleteattribute.setEnabled(false);
		this.repaint();
	}

	private void addField(String labelname, int currentRow, int currentLine, int currentNumber) {
		int rowDistance = 180;
		int labelToFieldDistance = 90;
		int distenceToTop = 80;
		int lineDistance = 30;
		int lineHight = 20;

		int currentX = currentRow*(labelToFieldDistance+rowDistance);
		int currentY = (currentLine*lineDistance)+distenceToTop;

		JTextField field = new JTextField(labelname);

		JLabel label = new JLabel("Attribut nr: "+(currentNumber+1));



		label.setBounds(
				currentX,
				currentY,
				100,
				lineHight);
		field.setBounds(
				currentX+labelToFieldDistance,
				currentY,
				100,
				lineHight);

		label_otherattributes[currentNumber] = label;
		textfield_otherattributes[currentNumber] = field;
		this.add(label);
		this.add(field);
	}

	public Vector getAttributes()
	{
//		Vector attr_oldidname= new Vector();
//
//		for (int i=4;i<count;i++)
//		{
//			if(textfield_otherattributes[i].getText().equals("")){}
//			else
//			{
//				Attribute_VO attr = new Attribute_VO();
//				if ( i-4<attributes.size())
//					attr.setAttribute_id(((Attribute_VO)attributes.elementAt(i-4)).getAttribute_id());
//
//				else 
//					attr.setAttribute_id(0);
//
//				attr.setAttribute_name(textfield_otherattributes[i].getText().toLowerCase().trim());
//				attr_oldidname.add(attr);
//			}
//		}
//		return attr_oldidname;
		
		Vector<Attribute_VO> allAttributes = new Vector<Attribute_VO>();
		
		for(int i = 0;i<label_otherattributes.length;i++) {
			if(label_otherattributes[i]==null){
				break;
			}
			Attribute_VO att = new Attribute_VO();
			
			if(attributes.size()>i){
				att.setAttribute_id(attributes.get(i).getAttribute_id());
			}else{
				att.setAttribute_id(0);
			}
			
			att.setAttribute_name(textfield_otherattributes[i].getText().toLowerCase().trim());
			allAttributes.add(att);
		}
		return allAttributes;
		
	}
	public void clearAllFields(){
		for (Component C : this.getComponents()){

			if (C instanceof JTextField || C instanceof JTextArea){

				((JTextComponent) C).setText(""); //abstract superclass
			}
		}
	}


	public void actionPerformed(ActionEvent evt)
	{
		Object src = evt.getSource();	

		
		if(src==button_addattribute)
		{
			int currentAttributeNumber = -1;
			for(int i = 0; i<textfield_otherattributes.length;i++){
				if(textfield_otherattributes[i]== null){
					currentAttributeNumber=i;
					break;
				}
			}
			if(currentAttributeNumber>-1&&currentAttributeNumber<9){
				int currentLine = currentAttributeNumber%4;
				int currentRow =  ((currentAttributeNumber-currentLine)/4)+1;

				this.addField("",currentRow,currentLine,currentAttributeNumber);
				this.repaint();
			}

			//			try
			//			{
			//				if(count<8)
			//				{
			//					label_otherattributes[count] = new JLabel("Attribute nr: "+(count+1));
			//					textfield_otherattributes[count] = new JTextField();
			//					this.add(label_otherattributes[count]);
			//					this.add(textfield_otherattributes[count]);
			//					label_otherattributes[count].setBounds(270, 80+(20*(count-3)+10*(count-4)), 100, 20);
			//					textfield_otherattributes[count].setBounds(360, 80+(20*(count-3)+10*(count-4)), 100, 20);
			//					count++;
			//					i++;
			//				}
			//				else if(count<12)
			//				{
			//					label_otherattributes[count] = new JLabel("Attribute nr: "+(count+1));
			//					textfield_otherattributes[count] = new JTextField();
			//					this.add(label_otherattributes[count]);
			//					this.add(textfield_otherattributes[count]);
			//					label_otherattributes[count].setBounds(540, 80+(20*(count-7)+10*(count-8)), 100, 20);
			//					textfield_otherattributes[count].setBounds(650, 80+(20*(count-7)+10*(count-8)), 100, 20);
			//					count++;
			//					i++;
			//				}
			//			}catch(Exception e){System.err.println("Button addatribute excepton");}
		}

		else if(src==button_deleteattribute)
		{
			if(textfield_otherattributes[attributes.size()]!= null){
			for(int i = textfield_otherattributes.length-1; i>attributes.size()-1;i--){
				
				if(textfield_otherattributes[i]!= null){
					this.remove(textfield_otherattributes[i]);
					this.remove(label_otherattributes[i]);
					textfield_otherattributes[i] = null;
					label_otherattributes[i] = null;
					break;
				}
			}
			this.repaint();
			}
			
//			try
//			{
//				count --;
//				i--;
//				this.remove(label_otherattributes[count]);
//				this.remove(textfield_otherattributes[count]);
//				this.repaint();
//			}catch(Exception e){System.err.println("Button deleteattribute exception");}
		}
		
		int currentAttributeNumber = -1;
		for(int i = 0; i<textfield_otherattributes.length;i++){
			if(textfield_otherattributes[i]== null){
				currentAttributeNumber=i;
				break;
			}
		}
		
		if(currentAttributeNumber> attributes.size()){
			button_deleteattribute.setEnabled(true);
		}else{
			button_deleteattribute.setEnabled(false);
		}
		if(currentAttributeNumber<8){
			button_addattribute.setEnabled(true);
		}else{
			button_addattribute.setEnabled(false);
		}

//		if(count>=12)
//		{
//			button_addattribute.setEnabled(false);
//		}
//		else if(i <= attributes.size())
//		{
//			button_deleteattribute.setEnabled(false);
//		}
//		else if( i>attributes.size() && count<12)
//		{
//			button_addattribute.setEnabled(true);
//			button_deleteattribute.setEnabled(true);
//		}

		if(src==combobox_type)
		{

			if (combobox_type.getSelectedIndex()==0)
				setVisibility(false);
			else
			{
				setVisibility(true);
			}
		}
	}

	public void addComboboxTypeListener(ActionListener actionlistener)
	{
		this.combobox_type.addActionListener(actionlistener);	
	}

	public void addButtonSaveEditTypeListener(ActionListener actionlistener)
	{
		this.button_save.addActionListener(actionlistener);	
	}
}

