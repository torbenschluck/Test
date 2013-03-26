package View;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonPanel  extends JPanel{
	
	//labels
	private JLabel label_search = new JLabel("Textsearch:");
	private JLabel label_type = new JLabel("Type:");
	private JLabel label_author = new JLabel("Author(s):");
	private JLabel label_title = new JLabel("Title:");
	private JLabel label_year = new JLabel("Year:");
	private JLabel[] label_otherattributes = new JLabel[100];
	
	//inputs
	private JTextField textfield_search = new JTextField();
	private JComboBox combobox_type = new JComboBox();
	private JTextField textfield_author = new JTextField();
	private JTextField textfield_title = new JTextField();
	private JTextField textfield_year = new JTextField();
	private JTextArea textarea_search = new JTextArea();
	private JTextField[] textfield_otherattributes = new JTextField[100];
	
	//buttons
	public JButton button_startsearch = new JButton("Start Search");
	public JButton button_newreference = new JButton("New Reference");
	public JButton button_newtype = new JButton("New Type");
	public JButton button_edittype = new JButton("Edit Type");
	public JButton button_editreference = new JButton("Edit Reference");
	public JButton button_groups = new JButton("Groups");
	
	public ButtonPanel()
	{
		init();
	}
	
	private void init()
	{
		this.setSize(800,60);
		this.setLayout(null);

		this.add(button_startsearch);
		
		this.add(button_newreference);
		this.add(button_editreference);
		
		this.add(button_newtype);
		this.add(button_edittype);
		
		this.add(button_groups);
		
		button_startsearch.setBounds(10, 15, 110, 20);
		
		button_newtype.setBounds(150, 15, 100, 20);
		button_edittype.setBounds(260, 15, 100, 20);
		
		button_newreference.setBounds(390, 15, 125, 20);
		button_editreference.setBounds(525, 15, 125, 20);
		
		button_groups.setBounds(680, 15, 100, 20);
	
	}		
}