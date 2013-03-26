package View;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import Model.Attribute_VO;

import java.util.*;

public class MainPanel extends JPanel 
{
	//labels
	private JLabel label_search = new JLabel("Textsearch:");
	private JLabel label_type = new JLabel("Type:");
	private JLabel label_author = new JLabel("Author(s):");
	private JLabel label_title = new JLabel("Title:");
	private JLabel label_year = new JLabel("Year:");
	private JLabel label_group = new JLabel("Reference Groups:");
	private JLabel label_or1 = new JLabel ("OR");
	private JLabel label_or2 = new JLabel ("OR");
	private JLabel label_or3 = new JLabel ("OR");
	private JLabel[] label_otherattributes = new JLabel[12];

	//inputs
	//	private JTextField textfield_search = new JTextField();
	private JTextField textfield_author = new JTextField();
	private JTextField textfield_title = new JTextField();
	private JTextField textfield_year = new JTextField();
	private JTextArea textarea_search = new JTextArea();
	private JTextField[] textfield_otherattributes = new JTextField[12];

	private JCheckBox checkbox_type = new JCheckBox("OR");
	private JCheckBox checkbox_author = new JCheckBox("OR");
	private JCheckBox checkbox_title = new JCheckBox("OR");
	private JCheckBox checkbox_year = new JCheckBox("OR");
	private JCheckBox[] checkbox_otherattributes = new JCheckBox[12];

	private JComboBox combobox_type = new JComboBox();
	private JComboBox combobox_group = new JComboBox();

	private JButton button_search = new JButton("Search");
	private String[][] types;
	private String[][] groups;

	private int i;
	private int count=0;

	public MainPanel()
	{
		init();
	}

	private void init()
	{
		this.setSize(800,350);
		this.setLayout(null);
		this.add(label_type);
		this.add(label_author);
		this.add(label_title);
		this.add(label_year);
		this.add(label_group);
		this.add(label_search);

		this.add(combobox_type);
		this.add(combobox_group);

		this.add(textfield_author);
		this.add(textfield_title);
		this.add(textfield_year);
		this.add(textarea_search);

		this.add(checkbox_type);
		this.add(checkbox_author);
		this.add(checkbox_title);
		this.add(checkbox_year);
		this.add(label_or1);

		this.add(button_search);

		label_type.setBounds(10,20,100,20);
		label_author.setBounds(10,50,100,20);
		label_title.setBounds(10,80,100,20);
		label_year.setBounds(10,110,100,20);
		label_group.setBounds(360,170,150,20);
		label_search.setBounds(10,170,100,20);
		label_or1.setBounds(230, 130, 20, 20);

		textfield_author.setBounds(120,50,100,20);
		textfield_title.setBounds(120,80,100,20);
		textfield_year.setBounds(120,110,100,20);
		textarea_search.setBounds(10,200, 250, 100);

		checkbox_type.setBounds(230, 20, 20, 20);
		checkbox_author.setBounds(230, 50, 20, 20);
		checkbox_title.setBounds(230, 80, 20, 20);
		checkbox_year.setBounds(230, 110, 20, 20);

		combobox_type.setBounds(120, 20, 100, 20);
		combobox_group.setBounds(360,200,150,20);

		button_search.setBounds(600, 300, 150, 20);

		//		for(int i=4; i<12/*attributlisteausdb*/; i++)
		//		{
		//			if(i<8)
		//			{
		//				label_otherattributes[i] = new JLabel("label nr: "+(i+1));
		//				textfield_otherattributes[i] = new JTextField();
		//				checkbox_otherattributes[i] = new JCheckBox();
		//
		//				this.add(label_otherattributes[i]);
		//				this.add(textfield_otherattributes[i]);
		//				this.add(checkbox_otherattributes[i]);
		//				this.add(label_or2);
		//
		//				label_otherattributes[i].setBounds(270, (20*(i-3)+10*(i-4)), 100, 20);
		//				textfield_otherattributes[i].setBounds(380, (20*(i-3)+10*(i-4)), 100, 20);
		//				checkbox_otherattributes[i].setBounds(490, (20*(i-3)+10*(i-4)), 20, 20);
		//				label_or2.setBounds(490, 130, 20, 20);
		//			}
		//
		//			else
		//			{
		//				label_otherattributes[i] = new JLabel("label nr: "+(i+1));
		//				textfield_otherattributes[i] = new JTextField();
		//				checkbox_otherattributes[i] = new JCheckBox();
		//
		//				this.add(label_otherattributes[i]);
		//				this.add(textfield_otherattributes[i]);
		//				this.add(checkbox_otherattributes[i]);
		//				this.add(label_or3);
		//
		//				label_otherattributes[i].setBounds(540, (20*(i-7)+10*(i-8)), 100, 20);
		//				textfield_otherattributes[i].setBounds(650, (20*(i-7)+10*(i-8)), 100, 20);
		//				checkbox_otherattributes[i].setBounds(760, (20*(i-7)+10*(i-8)), 20, 20);
		//				label_or3.setBounds(760, 130, 20, 20);
		//			}
		//		}
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
		if(checkbox_otherattributes!= null){
			for(JCheckBox l : checkbox_otherattributes){
				if(l!=null)this.remove(l);
			}
		}
		this.repaint();

		label_otherattributes = new JLabel[12];		
		textfield_otherattributes = new JTextField[12];	
		//		//this.removeAll();		
	}

	public void setAttr(Vector<Attribute_VO> attridname)
	{

		removeAttributes();
		Vector<Attribute_VO> attributes = attridname;
		createLabels(attributes);
		//		for (i=0,count=4 ; i<attributes.size() ; count++, i++)
		//		{
		//			try
		//			{
		//				if(count<8)
		//				{	
		//					label_otherattributes[count] = new JLabel();
		//					textfield_otherattributes[count] = new JTextField();
		//					checkbox_otherattributes[count] = new JCheckBox();
		//					this.add(label_otherattributes[count]);
		//					this.add(textfield_otherattributes[count]);
		//					this.add(checkbox_otherattributes[count]);
		//					this.add(label_or2);
		//					label_otherattributes[count].setBounds(270, (20*(count-3)+10*(count-4)), 100, 20);
		//					textfield_otherattributes[count].setBounds(380, (20*(count-3)+10*(count-4)), 100, 20);
		//					label_otherattributes[count].setText(((Attribute_VO)attributes.elementAt(i)).getAttribute_name()+":");
		//					checkbox_otherattributes[count].setBounds(490, (20*(count-3)+10*(count-4)), 20, 20);
		//					label_or2.setBounds(490, 130, 20, 20);
		//				}
		//				else if(count<=12)
		//				{
		//					label_otherattributes[count] = new JLabel();
		//					textfield_otherattributes[count] = new JTextField();
		//					checkbox_otherattributes[i] = new JCheckBox();
		//					this.add(label_otherattributes[count]);
		//					this.add(textfield_otherattributes[count]);
		//					this.add(checkbox_otherattributes[count]);
		//					this.add(label_or3);
		//					label_otherattributes[count].setBounds(540, (20*(count-7)+10*(count-8)), 100, 20);
		//					textfield_otherattributes[count].setBounds(650, (20*(count-7)+10*(count-8)), 100, 20);
		//					checkbox_otherattributes[count].setBounds(760, (20*(count-7)+10*(count-8)), 20, 20);
		//					label_or3.setBounds(760, 130, 20, 20);
		//					label_otherattributes[count].setText(((Attribute_VO)attributes.elementAt(i)).getAttribute_name()+":");
		//				}
		//			}catch(Exception e){System.err.println("Search addatribute excepton");
		//			}
		//			this.repaint();
		//		}
	}

	private void createLabels(Vector<Attribute_VO> attributes) {
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
		this.repaint();
	}

	private void addField(String labelname, int currentRow, int currentLine, int currentNumber) {
		int rowDistance = 5;
		int fieldToButtondistance = 100;
		int labelToFieldDistance = 90;
		int distenceToTop = 20;
		int lineDistance = 30;
		int lineHight = 20;
		int checkBoxDistance = 30;
		int distanceToLeft = 40;

		int currentX = distanceToLeft+(currentRow*(labelToFieldDistance+fieldToButtondistance+rowDistance+checkBoxDistance));
		int currentY = (currentLine*lineDistance)+distenceToTop;

		JTextField field = new JTextField();

		JLabel label = new JLabel(labelname);
		JCheckBox checkBox = new JCheckBox();


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
		checkBox.setBounds(
				currentX+labelToFieldDistance+fieldToButtondistance,
				currentY,
				lineHight,
				lineHight);

		label_otherattributes[currentNumber] = label;
		textfield_otherattributes[currentNumber] = field;
		checkbox_otherattributes[currentNumber] = checkBox;
		this.add(label);
		this.add(field);
		this.add(checkBox);
	}

	public void addButtonSearchListener(ActionListener actionlistener)
	{
		this.button_search.addActionListener(actionlistener);
	}

	public void addComboboxTypeListener(ActionListener actionlistener)
	{
		this.combobox_type.addActionListener(actionlistener);	
	}

	public void fillType(String[][] content)
	{
		combobox_type.removeAllItems();
		types = content;

		combobox_type.addItem("");
		for (i=0 ; i<types.length ; i++ )
		{
			combobox_type.addItem(types[i][1]);
		}
	}

	public void fillGroup (String[][] content)
	{
		combobox_group.removeAllItems();
		groups = content;

		combobox_group.addItem("");
		for (i=0 ; i<groups.length ; i++ )
		{
			combobox_group.addItem(groups[i][1]);
		}
	}

	public Vector<String> getSearch()
	{
		Vector<String> data= new Vector<String>();


		data.add((String)combobox_type.getSelectedItem());
		data.add(this.toString(checkbox_type));
		data.add(textfield_author.getText());
		data.add(this.toString(checkbox_author));
		data.add(textfield_title.getText());
		data.add(this.toString(checkbox_title));
		data.add(textfield_year.getText());
		data.add(this.toString(checkbox_year));


		for(int j=0;j<textfield_otherattributes.length;j++)
		{
			if (textfield_otherattributes[j] != null && label_otherattributes[j]!= null)
			{
				if(textfield_otherattributes[j].getText().equals(""));
				else
				{
					data.add(textfield_otherattributes[j].getText());
					data.add(label_otherattributes[j].getText());
				}
			}
		}
		data.add((String)combobox_group.getSelectedItem());	
		data.add(textarea_search.getText());

		System.out.println(data.toString());
		return data;

	}
	private String toString(JCheckBox box)
	{
		if (box.isSelected()==true)
			return "OR";
		else
			return "AND";
	}
	public Object getTypeValue()
	{
		return(combobox_type.getSelectedItem());
	}
}