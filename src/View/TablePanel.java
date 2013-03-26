package View;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.Enumeration;
import java.util.Vector;

public class TablePanel extends JPanel 
{

	JTable table_view;
	TableModel tablemodel_view= new TableModel();	
	String[][] tablevalues;

	public TablePanel()
	{

		this.setSize(800,390);
		this.setLayout(null);


		table_view = new JTable(tablemodel_view) 
		{
			public boolean isCellEditable(int x, int y)
			{
				return false;
			}
						
		};
		//table_view.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table_view.setFillsViewportHeight(true);
		table_view.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );

		JScrollPane scrollPane = new JScrollPane(table_view);
		this.add(scrollPane);
		scrollPane.setBounds(0,0,800,390);
	}
	
	public void setValueAll(String[][] content){
		String[][] value = content;
		tablevalues = content;
		this.repaint();
		for(int i = 0; i < content.length; i++)
		{
			for(int j = 0; j < content.length; j++)
			{				
//				tablemodel_view.insertValueAt(i, j, value[i][j]);
			}			
		}
	}		
	
	public void setValueAll(String[][] content, String tabletype){
		String[][] value = content;		
		tablevalues = content;
		this.repaint();
		tablemodel_view.clear();
		if(tabletype.equalsIgnoreCase("reference"))
		{				
			String[] columnHead = {"ref id", "ref type", "creation date", "keywords", "abstract", "notes" };			
			tablemodel_view.setHeaderAndTableSize(columnHead);
			for(int i = 0; i < columnHead.length; i++)
			{
				tablemodel_view.addColumn(columnHead[i]);
			}						
			for(int j = 0; j < content.length; j++)
			{				
				content[j][1] = content[j][6];
				tablemodel_view.insertRow(j, content[j]);
			}
		}
		if(tabletype.equalsIgnoreCase("group"))
		{
			String[] columnHead = {"ref id", "ref type", "creation date", "keywords", "abstract", "notes" };
			tablemodel_view.setHeaderAndTableSize(columnHead);
			for(int i = 0; i < columnHead.length; i++)
			{
				tablemodel_view.addColumn(columnHead[i]);				
			}
			for(int j = 0; j < content.length; j++)
			{
				content[j][1] = content[j][6];
				tablemodel_view.insertRow(j, content[j]);
			}
		}
		if(tabletype.equalsIgnoreCase("type"))
		{
			String[] columnHead = {"ref type id", "type name"};
			tablemodel_view.setHeaderAndTableSize(columnHead);
			for(int i = 0; i < columnHead.length; i++)
			{
				tablemodel_view.addColumn(columnHead[i]);
			}
			for(int j = 0; j < content.length; j++)
			{
				tablemodel_view.insertRow(j, content[j]);
			}
		}
	}
	
	public void reloadTable()
	{
		this.setValueAll(tablevalues);
	}
	
	public int getTableValue()
	{
		Enumeration ele = tablemodel_view.getDataVector().elements();
		int juppiedoo = table_view.getSelectedRow();
		
		Object hurray = tablemodel_view.getValueAt(juppiedoo, 0);
		
		System.out.println("selected row: "+table_view.getSelectedRow()+" value: "+hurray.toString());
		return (Integer.parseInt((tablemodel_view.getValueAt(table_view.getSelectedRow(), 0)).toString()));
	}
	
	class TableModel extends DefaultTableModel
//	class TableModel extends JTable
	{	
		private Vector<String> columnNames = new Vector<String>();
//		private String[][] content = new String[1][1];		
		private Vector content = new Vector();			

		public int getColumnCount() {
			return columnNames.size();
		}

		public String getColumnName(int col) {
			return columnNames.get(col);			
		}		
		
//		public Object getValueAt(int row, int col) {
//			return content[row][col];
//		}

//		public void insertValueAt(int row, int col, String content){
//			this.content[row][col] = content;
//		}
		
//		public void insertValueAt(int row, int col, String content)
//		{
////			this.content.add(row, content);
//		}
		
		public void clear()
		{
			this.columnNames = new Vector<String>();	
			this.content.removeAllElements();
			tablemodel_view.setNumRows(0);			
		}
		
//		public void insertRow(int row, String[][] input)
//		{
////			this.content.add(row, input);
//			content = new String[10][6];
//			this.content = input;
//		}
		
		public void insertRow(int row, String[][] input)
		{			
			content.add(row, input);
		}
		
		public void setHeaderAndTableSize(String[] columns)
		{
//			columnNames = new String[columns.length];
//			columnNames.setSize(columns.length);
			for(int i = 0; i < columns.length; i++)
			{
				columnNames.add(i, columns[i]);
			}
//			content = contentSize;
		}
	}

}