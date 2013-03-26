package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainView extends JFrame
{

	private JPanel framepanel = new JPanel();
	
	public MainPanel mainpanel = new MainPanel();
	public TablePanel tablepanel = new TablePanel();
	public ButtonPanel buttonpanel = new ButtonPanel();
	public TypePanel typepanel = new TypePanel();
	public TypeEditPanel typeeditpanel = new TypeEditPanel();
	public RefPanel refpanel = new RefPanel();
	public RefEditPanel refeditpanel = new RefEditPanel();
	public GroupPanel grouppanel = new GroupPanel();
	
	private String[][] attr_id_name = new String[12][2];
	
	public MainView()
	{
		init();
		
	}
	
	private void init()
	{
		this.setLayout(null);
		this.setSize(800, 800);
		this.setTitle("RefMan");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		framepanel.setLayout(null);		
		
		framepanel.add(mainpanel);
		framepanel.add(typepanel);
		framepanel.add(typeeditpanel);
		framepanel.add(refpanel);
		framepanel.add(refeditpanel);
		framepanel.add(tablepanel);
		framepanel.add(buttonpanel);
		framepanel.add(grouppanel);
		
		this.setPanelsInvisible();
		mainpanel.setVisible(true);
	
		mainpanel.setBounds(0, 0, 800, 350);
		buttonpanel.setBounds(0, 350, 800, 60);
		tablepanel.setBounds(0, 410, 800, 385);

		this.setContentPane(framepanel); 
	}
	
	private void setPanelsInvisible ()
	{
		mainpanel.setVisible(false);
		typepanel.setVisible(false);
		typeeditpanel.setVisible(false);
		refpanel.setVisible(false);
		refeditpanel.setVisible(false);
		grouppanel.setVisible(false);
	}
	
	public void setTypePanel()
	{
		this.setPanelsInvisible();
		typepanel.setVisible(true);
		
		typepanel.setBounds(0, 0, 800, 350);
	}
	
	public void setTypeEditPanel (String[][] content)
	{
		this.setPanelsInvisible();
		typeeditpanel.setVisible(true);

		typeeditpanel.fillType(content);
		typeeditpanel.setBounds(0, 0, 800, 350);
	}
	
	public void setRefPanel(String[][] content_type, String[][] content_group)
	{
		this.setPanelsInvisible();
		refpanel.setVisible(true);
		
		refpanel.fillType(content_type);
		refpanel.fillGroup(content_group);
		refpanel.setBounds(0, 0, 800, 350);
	}
	
	public void setRefEditPanel(String type)
	{
		this.setPanelsInvisible();
		refeditpanel.setVisible(true);
		
		refeditpanel.fillAttributes(type);
		refeditpanel.setBounds(0, 0, 800, 350);
	}
	
	public void setGroupPanel(String[][] content_group)
	{
		this.setPanelsInvisible();
		grouppanel.setVisible(true);
		
		grouppanel.fillGroup(content_group);
		grouppanel.setBounds(0, 0, 800, 350);
	}
	
	public void setMainPanel(String[][] content_type, String[][] content_group)
	{
		this.setPanelsInvisible();
		mainpanel.setVisible(true);
		
		mainpanel.fillType(content_type);
		mainpanel.fillGroup(content_group);
		mainpanel.setBounds(0, 0, 800, 350);
	}
	
	public void setTablePanel(String[][] content)
	{
		tablepanel.setValueAll(content);
	}
	
	public void setTablePanel(String[][] content, String tabletype)
	{
		tablepanel.setValueAll(content, tabletype);
	}

	
	//window actions
	protected void processWindowEvent(final WindowEvent we)
	{
		if(we.getID()==WindowEvent.WINDOW_CLOSING)
			System.exit(0);
	}
		
	public void addButtonStartSearchListner(ActionListener actionlistener)
	{
		buttonpanel.button_startsearch.addActionListener(actionlistener);
	}
	
	public void addButtonNewTypeListener(ActionListener actionlistener)
	{
		buttonpanel.button_newtype.addActionListener(actionlistener);
	}
	
	public void addButtonEditTypeListener(ActionListener actionlistener)
	{
		buttonpanel.button_edittype.addActionListener(actionlistener);
	}
	
	public void addButtonNewReferenceListener(ActionListener actionlistener)
	{
		buttonpanel.button_newreference.addActionListener(actionlistener);
	}
	
	public void addButtonEditReferenceListener(ActionListener actionlistener)
	{
		buttonpanel.button_editreference.addActionListener(actionlistener);
	}	
	
	public void addButtonGroupListener(ActionListener actionlistener)
	{
		buttonpanel.button_groups.addActionListener(actionlistener);
	}
			
}
