package View;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;


public class TypePanel extends JPanel implements ActionListener{

	//labels
	private JLabel label_capture = new JLabel("Create new Type:");
	private JLabel label_description = new JLabel("\"Must Have\" Attributes:");
	private JLabel label_description2 = new JLabel("Optional Attributes (klick buttons below):");


	private JLabel label_typename = new JLabel("Typename:");
	private JLabel label_author = new JLabel("Author(s):");
	private JLabel label_title = new JLabel("Title:");
	private JLabel label_year = new JLabel("Year:");
	private JLabel[] label_otherattributes = new JLabel[12];

	//inputs
	private JTextField textfield_typename = new JTextField();
	private JTextField textfield_author = new JTextField();
	private JTextField textfield_title = new JTextField();
	private JTextField textfield_year = new JTextField();
	private JTextField[] textfield_otherattributes= new JTextField[12];
	private JButton button_addattribute = new JButton("Add Attribute");
	private JButton button_deleteattribute = new JButton("Delete Attribute");
	private JButton button_save = new JButton("Save");
	private int count=4; //reset count to 4 when this panel changed

	public TypePanel()
	{
		init();
	}

	private void init()
	{

		this.setSize(800,300);
		this.setLayout(null);

		this.add(label_capture);
		this.add(label_description);
		this.add(label_description2);

		this.add(label_typename);
		this.add(label_author);
		this.add(label_title);
		this.add(label_year);

		this.add(textfield_typename);
		this.add(textfield_author);
		this.add(textfield_title);
		this.add(textfield_year);

		this.add(button_addattribute);
		this.add(button_deleteattribute);
		this.add(button_save);


		label_capture.setBounds(320, 10, 200, 20);
		label_capture.setFont(new Font("Arial", Font.BOLD, 20));

		label_description.setBounds(10, 50, 200, 20);
		label_description.setFont(new Font("Arial", Font.BOLD, 15));

		label_description2.setBounds(250, 50, 350, 20);
		label_description2.setFont(new Font("Arial", Font.BOLD, 15));

		label_typename.setBounds(30,100,100,20);
		label_author.setBounds(30,130,100,20);
		label_title.setBounds(30,160,100,20);
		label_year.setBounds(30,190,100,20);

		textfield_typename.setBounds(120, 100, 100, 20);

		button_addattribute.setBounds(250,250,150,20);
		button_deleteattribute.setBounds(410,250,150,20);
		button_save.setBounds(650, 270, 100, 20);
		button_deleteattribute.setEnabled(false);
		button_addattribute.addActionListener(this);
		button_deleteattribute.addActionListener(this);

	}

	public void actionPerformed(ActionEvent evt)
	{
		Object src = evt.getSource();	


		if(src==button_addattribute)
		{
			try
			{
				if(count<8)
				{
					label_otherattributes[count] = new JLabel("Attribute nr: "+(count+1));
					textfield_otherattributes[count] = new JTextField();
					this.add(label_otherattributes[count]);
					this.add(textfield_otherattributes[count]);
					label_otherattributes[count].setBounds(270, 80+(20*(count-3)+10*(count-4)), 100, 20);
					textfield_otherattributes[count].setBounds(360, 80+(20*(count-3)+10*(count-4)), 100, 20);
					count++;
				}
				else if(count<=12)
				{
					label_otherattributes[count] = new JLabel("Attribute no: "+(count+1));
					textfield_otherattributes[count] = new JTextField();
					this.add(label_otherattributes[count]);
					this.add(textfield_otherattributes[count]);
					label_otherattributes[count].setBounds(540, 80+(20*(count-7)+10*(count-8)), 100, 20);
					textfield_otherattributes[count].setBounds(650, 80+(20*(count-7)+10*(count-8)), 100, 20);
					count++;
				}
			}catch(Exception e){System.err.println("Button addatribute excepton");}
		}

		else if(src==button_deleteattribute)
		{
			try
			{
				if(count>4)
					count --;
				this.remove(label_otherattributes[count]);
				this.remove(textfield_otherattributes[count]);
				this.repaint();
			}catch(Exception e){System.err.println("Button deleteattribute exception");}
		}

		if(count==12)
		{
			button_addattribute.setEnabled(false);
		}
		if(count==4)
		{
			button_deleteattribute.setEnabled(false);
		}
		if(count>4&& count<12)
		{
			button_addattribute.setEnabled(true);
			button_deleteattribute.setEnabled(true);
		}

	}
	public void addButtonSaveTypeListener(ActionListener actionlistener)
	{
		this.button_save.addActionListener(actionlistener);
	}

	public String getTypeName()
	{
		if(textfield_typename.getText().equals(""))
			return null;
		else
			return textfield_typename.getText().trim();
	}

	public Vector getAttributeNames()
	{
		Vector attributes= new Vector();
		for (int i=4;i<count;i++)
		{
			if(textfield_otherattributes[i].getText().equals(""));
			else
				attributes.add(textfield_otherattributes[i].getText().toLowerCase().trim());
		}

		return attributes;
	}
	
	public void clearAllFields(){
		for (Component C : this.getComponents()){

		    if (C instanceof JTextField || C instanceof JTextArea){

		        ((JTextComponent) C).setText(""); //abstract superclass
		    }
		}
	}

}

