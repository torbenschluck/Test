package View;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

public class GroupPanel extends JPanel implements ActionListener{

	private JLabel label_capture = new JLabel("Select Group:");
	private JLabel label_refingroup = new JLabel("References in selected Group:");
	private JLabel label_explanation = new JLabel("Select a Reference above or below");
	private JLabel label_explanation2 = new JLabel("and click these buttons:");
	private JLabel label_newgroup = new JLabel("Groupname:");
	
	private JComboBox combobox_group = new JComboBox();
	
	private JTextField textfield_newgroup = new JTextField();
	
	private JButton button_addref = new JButton("Add Reference to Group");
	private JButton button_removeref = new JButton("Remove Reference from Group");
	private JButton button_delete = new JButton("Delete selected Group");
	private JButton button_newgroup = new JButton("Create Group");
	
	private TablePanel table_refgroups = new TablePanel();
	
	private String[][] groups;
	private int i;
	
	public GroupPanel()
	{
		init();
	}
	
	private void init()
	{

		this.setSize(800,300);
		this.setLayout(null);			

		this.add(label_capture);
		this.add(label_refingroup);
		this.add(label_explanation);
		this.add(label_explanation2);
		this.add(label_newgroup);
		
		this.add(combobox_group);
		
		this.add(textfield_newgroup);

		this.add(button_delete);
		this.add(button_addref);
		this.add(button_removeref);
		this.add(button_newgroup);
		this.add(table_refgroups);
		
		label_capture.setBounds(240, 10, 200, 20);
		label_capture.setFont(new Font("Arial", Font.BOLD, 15));
		label_explanation.setBounds(340,300,400,20);
		label_explanation2.setBounds(370,320,400,20);
		label_newgroup.setBounds(10,300,800,20);
		
		combobox_group.setBounds(350,10,150,20);
		
		textfield_newgroup.setBounds(90,300,150,20);
		
		button_delete.setBounds(580, 15, 160,20);
		button_addref.setBounds(550,300,200,20);
		button_removeref.setBounds(540,330,220,20);
		button_newgroup.setBounds(105,330,120,20);
		
		table_refgroups.setBounds(0, 50, 800, 230);
		
		combobox_group.addActionListener(this);
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
	
	public Object getGroupValue()
	{
		return combobox_group.getSelectedItem();
	}
	
	public void setGroupValue(String groupname)
	{
		combobox_group.setSelectedItem(groupname);
	}
	
	public void setTableInvisible()
	{
		table_refgroups.setVisible(false);
	}
	
	public void setValueAll(String[][] content)
	{		
		table_refgroups.setVisible(true);
		String[][] value = content;
		this.repaint();
		table_refgroups.tablemodel_view.clear();
		String[] columnHead = {"ref id", "ref type", "creation date", "keywords", "abstract", "notes" };			
		table_refgroups.tablemodel_view.setHeaderAndTableSize(columnHead);
		for(int i = 0; i < columnHead.length; i++)
		{
			table_refgroups.tablemodel_view.addColumn(columnHead[i]);
		}
		for(int j = 0; j < content.length; j++)
		{				
			table_refgroups.tablemodel_view.insertRow(j, content[j]);
		}
	}
	
	public TablePanel getTable()
	{
		return table_refgroups;
	}
	
	public String getGroupname()
	{
		if(!textfield_newgroup.getText().trim().equals(""))
		{
			return textfield_newgroup.getText().trim();
		}		
		else
		{
			
			return "";
		}
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
		
		if(combobox_group.getSelectedIndex()==0)
		{
			label_explanation.setVisible(false);
			label_explanation2.setVisible(false);
			button_delete.setEnabled(false);
			button_addref.setEnabled(false);
			button_removeref.setEnabled(false);		
		}
		
		else
		{
			label_explanation.setVisible(true);
			label_explanation2.setVisible(true);
			button_addref.setEnabled(true);
			button_removeref.setEnabled(true);
			button_delete.setEnabled(true);
		}
	}

	public void addButtonNewGroupListener(ActionListener actionlistener)
	{
		this.button_newgroup.addActionListener(actionlistener);
	}
	
	public void addButtonDeleteGroupListener(ActionListener actionlistener)
	{
		this.button_delete.addActionListener(actionlistener);
	}
	
	public void addButtonAddRefToGroupListener(ActionListener actionlistener)
	{
		this.button_addref.addActionListener(actionlistener);
	}
	
	public void addButtonRemoveRefFromGroupListener(ActionListener actionlistener)
	{
		this.button_removeref.addActionListener(actionlistener);
	}
	
	public void addComboboxGroupListener(ActionListener actionlistener) 
	{
		this.combobox_group.addActionListener(actionlistener);		
	}
		
}
