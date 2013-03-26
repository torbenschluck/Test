package View;

import XML.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import Model.Attribute_VO;
import Model.Content_VO;
import Model.Reference_VO;

public class RefEditPanel extends JPanel implements ActionListener{

	private JLabel label_capture = new JLabel("Edit Reference:");
	private JLabel label_description = new JLabel("Reference Type:");
	private JLabel label_description2 = new JLabel("Attributes:");

	private JLabel label_typename = new JLabel();
	private JLabel label_author = new JLabel("Author(s):");
	private JLabel label_title = new JLabel("Title:");
	private JLabel label_year = new JLabel("Year:");
	private Vector<JLabel> label_otherattributes = new Vector<JLabel>();

	private JLabel label_keywords = new JLabel("Keywords:");
	private JLabel label_refabstract = new JLabel("Abstract:");
	private JLabel label_notes = new JLabel("Notes:");
	private JLabel label_quote = new JLabel("Quote:");
	
	private JTextField textfield_author = new JTextField();
	private JTextField textfield_title = new JTextField();
	private JTextField textfield_year = new JTextField();
	private Vector<JTextField> textfield_otherattributes= new Vector<JTextField>();
	
	private JTextArea textarea_keywords = new JTextArea();
	private JTextArea textarea_refabstract = new JTextArea();
	private JTextArea textarea_notes = new JTextArea();	
	private JTextArea textarea_quote = new JTextArea("");
	
	private JTabbedPane tabbedpane_ref = new JTabbedPane();
	private JPanel panel_keywords = new JPanel();
	private JPanel panel_abstract = new JPanel();
	private JPanel panel_notes = new JPanel();
	private JPanel panel_quote = new JPanel();
	
	private JButton button_save = new JButton("Save");
	private JButton button_load = new JButton("Load Reference");
	private JButton button_delete = new JButton("Delete");
	private JButton button_import_xml= new JButton("Imoprt XML");
	private JButton button_export_xml= new JButton("Export XML");
	
	private String[][] groups;
	private Vector<Content_VO> contents = new Vector<Content_VO>();
	private Vector<Attribute_VO> attributes = new Vector<Attribute_VO>();
	private Reference_VO reference = new Reference_VO();
	
	private int count=4; //reset count to 4 when this panel changed
	private int i;
	
	public RefEditPanel ()
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
		
		this.add(label_typename);
		
		panel_keywords.add(label_keywords);
		panel_notes.add(label_notes);
		panel_abstract.add(label_refabstract);
		panel_quote.add(label_quote);			
		
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
		this.add(button_load);
		this.add(button_delete);
		this.add(button_import_xml);
		this.add(button_export_xml);
		
		
		label_capture.setBounds(300, 10, 250, 20);
		label_capture.setFont(new Font("Arial", Font.BOLD, 20));

		label_description.setBounds(20, 50, 150, 20);
		label_description.setFont(new Font("Arial", Font.BOLD, 15));
		
		label_description2.setBounds(400, 50, 350, 20);
		label_description2.setFont(new Font("Arial", Font.BOLD, 15));
		
		label_typename.setBounds(160,50,150,20);
		label_typename.setFont(new Font("Arial", Font.BOLD, 15));
		
		label_keywords.setBounds(10, 8, 100, 20);
		label_refabstract.setBounds(10,8,200,20);
		label_notes.setBounds(10,8,200,20);
		label_quote.setBounds(10,8,200,20);		
				
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
		button_load.setBounds(500, 320, 140, 20);
		button_delete.setBounds(390, 320, 100, 20);
		button_import_xml.setBounds(10,320,100,20);
		button_export_xml.setBounds(120,320,100,20);
		
		button_save.addActionListener(this);
		button_load.addActionListener(this);
		
		button_import_xml.addActionListener(this);;
		button_export_xml.addActionListener(this);
		
		if(!contents.isEmpty())
		{				
			for(int i=0, j=0; i<contents.size(); i++)
			{	
				if(contents.get(i).getAttr_id()==13)
				{
					label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));					
					textfield_otherattributes.add(new JTextField(contents.get(i).getContent()));

					this.add(label_otherattributes.get(i));
					this.add(textfield_otherattributes.get(i));

					label_otherattributes.get(i).setBounds(10, 100, 100, 20);
					textfield_otherattributes.get(i).setBounds(120,100,100,20);						
				}
				else													
				if(contents.get(i).getAttr_id()==14)
				{
					label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));					
					textfield_otherattributes.add(new JTextField(contents.get(i).getContent()));

					this.add(label_otherattributes.get(i));
					this.add(textfield_otherattributes.get(i));

					label_otherattributes.get(i).setBounds(10, 130, 100, 20);
					textfield_otherattributes.get(i).setBounds(120,130,100,20);				
				}
				else
				if(contents.get(i).getAttr_id()==15)
				{
					label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));					
					textfield_otherattributes.add(new JTextField(contents.get(i).getContent()));

					this.add(label_otherattributes.get(i));
					this.add(textfield_otherattributes.get(i));

					label_otherattributes.get(i).setBounds(10, 160, 100, 20);
					textfield_otherattributes.get(i).setBounds(120,160,100,20);					
				}
				else
				if(contents.get(i).getAttr_id()!=13 
						|| contents.get(i).getAttr_id()!=14 
						|| contents.get(i).getAttr_id()!=15)
				{
					if(j<4)
					{
						label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));					
						textfield_otherattributes.add(new JTextField(contents.get(i).getContent()));

						this.add(label_otherattributes.get(i));
						this.add(textfield_otherattributes.get(i));

						label_otherattributes.get(i).setBounds(270, 100+(30*j), 100, 20);
						textfield_otherattributes.get(i).setBounds(380, 100+(30*j), 100, 20);
					}

					else
					{
						label_otherattributes.add(new JLabel(attributes.get(i).getAttribute_name()+":"));
						textfield_otherattributes.add(new JTextField(contents.get(i).getContent()));

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
	
	public void fillAttributes(String type)
	{
		label_typename.setText(type);
	}
	
	public void removeAttributes()
	{
		label_otherattributes.removeAllElements();		
		textfield_otherattributes.removeAllElements();
		contents.removeAllElements();
		attributes.removeAllElements();
		this.removeAll();
	}
	
	public Reference_VO getReference()
	{			
		reference.setKeywords(textarea_keywords.getText().trim());
		reference.setRef_abstract(textarea_refabstract.getText().trim());
		reference.setNotes(textarea_notes.getText().trim());
		reference.setQuote(textarea_quote.getText().trim());
		
		return reference;
	}
	
	public void setReference(Reference_VO reference)
	{
		this.reference = reference;
		System.out.println("ref_id: "+reference.getRef_id());
		textarea_keywords.setText(reference.getKeywords());
		textarea_refabstract.setText(reference.getRef_abstract());
		textarea_notes.setText(reference.getNotes());	
		textarea_quote.setText(reference.getQuote());
	}

	public void setRefContents(Vector<Content_VO> contents)
	{		
		if(contents!=null)
		{		
			this.contents = contents;						
		}
		else
		{
			this.contents.removeAllElements();
			label_otherattributes.removeAllElements();
			textfield_otherattributes.removeAllElements();
			this.removeAll();
		}
		this.init();
	}
	
	public Vector<Content_VO> getRefContents()
	{		
		try
		{					
			for(int i = 0; i < contents.size(); i++)
			{
				contents.get(i).setContent(textfield_otherattributes.get(i).getText().trim());	
			}
			return contents;
		}
		catch(Exception e)
		{
			System.err.println("error in getRefContents");
		}
		return null;
		
	}
	
	public void setTypeAttributes(Vector<Attribute_VO> attributes)
	{
		this.attributes = attributes;
	}
	
	public Vector<Attribute_VO> getTypeAttributes()
	{
		return attributes;
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
				if (C instanceof JTextField || C instanceof JTextArea){

					((JTextComponent) C).setText(""); //abstract superclass
				}
			}
		}		
	}
	
	
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();	
		
		if(src == button_import_xml )
		{
			XmlRead import_xml = new XmlRead();
			import_xml.main();
		}
		
		if(src == button_export_xml )
		{
			XmlWrite export_xml = new XmlWrite();
			export_xml.main();
		}
	}

	public void addButtonSaveRefEditListener(ActionListener actionlistener)
	{
		this.button_save.addActionListener(actionlistener);
	}
	
	public void addButtonLoadRefEditListener(ActionListener actionlistener)
	{
		this.button_load.addActionListener(actionlistener);
	}
	
	public void addButtonDeleteRefEditListener(ActionListener actionlistener)
	{
		this.button_delete.addActionListener(actionlistener);
	}
}
