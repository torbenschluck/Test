package View;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import Model.Attribute_VO;
import Model.Content_VO;
import Model.Reference_VO;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.util.Vector;


public class RefPanel extends JPanel implements ActionListener{

	private JLabel label_capture = new JLabel("Create new Reference:");
	private JLabel label_description = new JLabel("Choose Type:");
	private JLabel label_description2 = new JLabel("Attributes:");

	private JLabel label_author = new JLabel("Author(s):");
	private JLabel label_title = new JLabel("Title:");
	private JLabel label_year = new JLabel("Year:");
	private JLabel label_group = new JLabel("Group: ");
	private Vector<JLabel> label_otherattributes = new Vector<JLabel>();

	private JLabel label_keywords = new JLabel("Keywords:");
	private JLabel label_refabstract = new JLabel("Abstract:");
	private JLabel label_notes = new JLabel("Notes:");
	private JLabel label_quote = new JLabel("Quote:");

	private JComboBox combobox_type = new JComboBox();
	private JComboBox combobox_group = new JComboBox();
	
	private JTextField textfield_author = new JTextField();
	private JTextField textfield_title = new JTextField();
	private JTextField textfield_year = new JTextField();
	private Vector<JTextField> textfield_otherattributes= new Vector<JTextField>();

	private JTextArea textarea_keywords = new JTextArea("");
	private JTextArea textarea_refabstract = new JTextArea("");
	private JTextArea textarea_notes = new JTextArea("");
	private JTextArea textarea_quote = new JTextArea("");
	
	private JTabbedPane tabbedpane_ref = new JTabbedPane();
	private JPanel panel_keywords = new JPanel();
	private JPanel panel_abstract = new JPanel();
	private JPanel panel_notes = new JPanel();
	private JPanel panel_quote = new JPanel();

	private JButton button_save = new JButton("Save");

	private String[][] types;
	private String[][] groups;
	private Vector<Attribute_VO> attributes = new Vector<Attribute_VO>();
	
	private int count=4; //reset count to 4 when this panel changed
	private int i;

	public RefPanel()
	{
		init();
	}

	private void init()
	{
		this.setSize(800,360);
		this.setLayout(null);

		this.add(label_capture);
		this.add(label_description);
		this.add(label_description2);

		this.add(label_group);

		panel_keywords.add(label_keywords);
		panel_notes.add(label_notes);
		panel_abstract.add(label_refabstract);
		panel_quote.add(label_quote);
		
		this.add(combobox_type);
		this.add(combobox_group);		
		
		panel_keywords.setLayout(null);
		panel_abstract.setLayout(null);
		panel_notes.setLayout(null);
		panel_quote.setLayout(null);
		
		panel_keywords.add(textarea_keywords);
		panel_abstract.add(textarea_refabstract);
		panel_notes.add(textarea_notes);
		panel_quote.add(textarea_quote);
		
		tabbedpane_ref.addTab( "keywords", panel_keywords );
		tabbedpane_ref.addTab( "abstract", panel_abstract);
		tabbedpane_ref.addTab( "notes", panel_notes);
		tabbedpane_ref.addTab( "quote", panel_quote );
		tabbedpane_ref.setSelectedComponent(panel_quote);
		
		this.add(tabbedpane_ref);
		
		this.add(button_save);

		label_capture.setBounds(300, 10, 250, 20);
		label_capture.setFont(new Font("Arial", Font.BOLD, 20));

		label_description.setBounds(20, 50, 200, 20);
		label_description.setFont(new Font("Arial", Font.BOLD, 15));

		label_description2.setBounds(400, 50, 350, 20);
		label_description2.setFont(new Font("Arial", Font.BOLD, 15));			
		
		label_group.setBounds(10, 190, 100, 20);
		
		label_keywords.setBounds(10, 8, 100, 20);
		label_refabstract.setBounds(10,8,200,20);
		label_notes.setBounds(10,8,200,20);
		label_quote.setBounds(10,8,200,20);

		combobox_type.setBounds(130, 50, 150, 20);
		combobox_group.setBounds(120, 190, 100, 20);
		
		textarea_keywords.setBounds(160, 8, 600, 50);
		textarea_refabstract.setBounds(160, 8, 600, 50);
		textarea_notes.setBounds(160, 8, 600, 50);
		textarea_quote.setBounds(160, 8, 600, 50);
		tabbedpane_ref.setBounds(10, 220, 770, 90);
		
		textarea_keywords.setLineWrap(true);
		textarea_refabstract.setLineWrap(true);
		textarea_notes.setLineWrap(true);
		textarea_quote.setLineWrap(true);

		button_save.setBounds(650, 320, 100, 20);
		combobox_type.addActionListener(this);

		if(!attributes.isEmpty())
		{				
			for(int i=0, j=0; i<attributes.size(); i++)
			{	
				if(attributes.get(i).getAttribute_id()==13)
				{
					label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));					
					textfield_otherattributes.add(new JTextField(""));

					this.add(label_otherattributes.get(i));
					this.add(textfield_otherattributes.get(i));

					label_otherattributes.get(i).setBounds(10, 100, 100, 20);
					textfield_otherattributes.get(i).setBounds(120,100,100,20);						
				}
				else													
				if(attributes.get(i).getAttribute_id()==14)
				{
					label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));					
					textfield_otherattributes.add(new JTextField(""));

					this.add(label_otherattributes.get(i));
					this.add(textfield_otherattributes.get(i));

					label_otherattributes.get(i).setBounds(10, 130, 100, 20);
					textfield_otherattributes.get(i).setBounds(120,130,100,20);				
				}
				else
				if(attributes.get(i).getAttribute_id()==15)
				{
					label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));					
					textfield_otherattributes.add(new JTextField(""));

					this.add(label_otherattributes.get(i));
					this.add(textfield_otherattributes.get(i));

					label_otherattributes.get(i).setBounds(10, 160, 100, 20);
					textfield_otherattributes.get(i).setBounds(120,160,100,20);					
				}
				else
				if(attributes.get(i).getAttribute_id()!=13 
						&& attributes.get(i).getAttribute_id()!=14 
						&& attributes.get(i).getAttribute_id()!=15)
				{
					if(j<4)
					{
						label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));					
						textfield_otherattributes.add(new JTextField(""));

						this.add(label_otherattributes.get(i));
						this.add(textfield_otherattributes.get(i));

						label_otherattributes.get(i).setBounds(270, 100+(30*j), 100, 20);
						textfield_otherattributes.get(i).setBounds(380, 100+(30*j), 100, 20);
					}

					else
					{
						label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));
						textfield_otherattributes.add(new JTextField(""));

						this.add(label_otherattributes.get(i));
						this.add(textfield_otherattributes.get(i));

						label_otherattributes.get(i).setBounds(540, 100+(30*(j-4)), 100, 20);
						textfield_otherattributes.get(i).setBounds(650, 100+(30*(j-4)), 100, 20);
					}
					j++;
				}
			}
		}
	}

	private void setVisibility(boolean visible)
	{
		label_description2.setVisible(visible);

		label_author.setVisible(visible);
		label_title.setVisible(visible);
		label_year.setVisible(visible);		

		textfield_author.setVisible(visible);
		textfield_title.setVisible(visible);
		textfield_year.setVisible(visible);

		label_keywords.setVisible(visible);
		label_refabstract.setVisible(visible);
		label_notes.setVisible(visible);
		label_group.setVisible(visible);
		if(!label_otherattributes.isEmpty())
		{
			for(int i = 0; i < label_otherattributes.size(); i++)
			{
				label_otherattributes.get(i).setVisible(visible);
				textfield_otherattributes.get(i).setVisible(visible);
			}
		}
		
		combobox_group.setVisible(visible);

		textarea_keywords.setVisible(visible);
		textarea_refabstract.setVisible(visible);
		textarea_notes.setVisible(visible);		
		textarea_quote.setVisible(visible);
		
		tabbedpane_ref.setVisible(visible);

		button_save.setVisible(visible);
	}
	
	public void clearAllFields(){
		Vector<JPanel> panels = new Vector<JPanel>();
		panels.add(this);
		panels.add(panel_keywords);
		panels.add(panel_abstract);
		panels.add(panel_notes);
		panels.add(panel_quote);
		for(int i = 0; i < panels.size(); i++)
		{
			for (Component C : panels.get(i).getComponents())
			{
				if (C instanceof JTextField || C instanceof JTextArea)
				{
					((JTextComponent) C).setText(""); //abstract superclass
				}
				if(C instanceof JComboBox)
				{
					((JComboBox) C).setSelectedItem("");
				}
			}
		}		
	}
	
	public void removeAttributes()
	{
		label_otherattributes.removeAllElements();		
		textfield_otherattributes.removeAllElements();	
		this.removeAll();
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
	
	public Object getTypeValue()
	{
		return combobox_type.getSelectedItem();
	}
	
	public Object getGroupValue()
	{
		return combobox_group.getSelectedItem();
	}
	
	public void setTypeAttributes(Vector<Attribute_VO> attributes)
	{		
		if(attributes!=null)
		{		
			this.attributes = attributes;						
		}
		else
		{
			this.attributes.removeAllElements();
			label_otherattributes.removeAllElements();
			textfield_otherattributes.removeAllElements();
			this.removeAll();
		}
		this.init();
	}
	
	public Vector<Attribute_VO> getTypeAttributes()
	{
		return attributes;
	}
	
	public Vector<Content_VO> getAttributeContents()
	{		
		try
		{					
			Vector<Content_VO> contents = new Vector<Content_VO>();
			for(int i = 0; i < attributes.size(); i++)
			{
				Content_VO dummy_content = new Content_VO();
				dummy_content.setAttr_id(attributes.get(i).getAttribute_id());
				dummy_content.setContent(textfield_otherattributes.get(i).getText().trim());	
				contents.add(dummy_content);
			}
			return contents;
		}
		catch(Exception e)
		{
			System.err.println("error in getAttributeContents");
		}
		return null;
	}
	
	public Reference_VO getReference()
	{
		Reference_VO reference = new Reference_VO();		
		reference.setKeywords(textarea_keywords.getText().trim());
		reference.setRef_abstract(textarea_refabstract.getText().trim());
		reference.setNotes(textarea_notes.getText().trim());	
		reference.setQuote(textarea_quote.getText().trim());
		
		return reference;
	}

	public void actionPerformed(ActionEvent evt)
	{
		Object src = evt.getSource();	

		if(src==combobox_type)
		{		
			if(combobox_type.getSelectedIndex()==0)
				setVisibility(false);
			
			else
				setVisibility(true);
		}
	}
	
	public void addButtonSaveRefListener(ActionListener actionlistener)
	{
		this.button_save.addActionListener(actionlistener);
	}
	
	public void addComboboxTypeListener(ActionListener actionlistener)
	{
		this.combobox_type.addActionListener(actionlistener);	
	}
		
}
