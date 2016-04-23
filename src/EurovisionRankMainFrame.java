import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;







import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.JTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;






import org.apache.commons.io.FileUtils;



@SuppressWarnings("serial")
public class EurovisionRankMainFrame extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	EurovisionSong[] entries = new EurovisionSong[43];
	EurovisionSongsDB db = new EurovisionSongsDB();
	JMenuBar menuBar = new JMenuBar();
	protected File resources = new File("Resources");
	protected File flags = new File("Resources/Flags");
	
	public EurovisionRankMainFrame() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		
		resources.mkdirs();
		flags.mkdirs();
	
	entries = db.createDB();
	
	for (int i = 0; i < entries.length; i++) {
		System.out.println(entries[i].toString());
	}

	final ImageIcon icon = new ImageIcon(getClass().getResource("/cover.png"));
	contentPane = new JPanel(){
		
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
	};
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 1));
	setContentPane(contentPane);
	
	MyModel model = new MyModel();

	model.setColumnIdentifiers(new Object[] {"Flag", "Country","Artist","Title","Rank", "Change","Listen"});
	table = new JTable(model);
	MyTableCellRenderer render = new MyTableCellRenderer();	
	table.setDefaultRenderer(table.getColumnClass(4),render);	
	table.setDefaultRenderer(table.getColumnClass(1),render);
	table.setDefaultRenderer(table.getColumnClass(0),new ImageRenderer());
	render.setOpaque(false);


	Action PlaySong = new AbstractAction()
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				@SuppressWarnings("unused")
				SimplePlayer sm = new SimplePlayer((String) model.getValueAt(table.getSelectedRow(),1),
						(String) model.getValueAt(table.getSelectedRow(),2),
						(String) model.getValueAt(table.getSelectedRow(),3),
						(ImageIcon) model.getValueAt(table.getSelectedRow(),0));
			} catch (MalformedURLException | UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	};
	
/*	Action PlaySong = new AbstractAction()
	{
		
		
		public void actionPerformed(ActionEvent e) {
			int selected = table.getSelectedRow();
			JButton st = new JButton("Stop");
			model.setValueAt(st,selected ,6);
			MP3Player m = new MP3Player(new File(model.getValueAt(selected, 1)+".mp3"));
			m.play();
			st.addMouseListener(new MouseAdapter() {
				
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					m.stop();
					
				}
			});*/
			
			
/*			int selected = table.getSelectedRow();
			MP3Player m = new MP3Player(new File(model.getValueAt(selected, 1)+".mp3"));

				model.setValueAt(stop,selected ,6);
				m.play();*/
			
				
			 

			
			
/*			if (table.getValueAt(selected, 6).equals(play)){
	        try{
	        
	        	model.setValueAt(stop,selected ,6);
	        	
	        	m.play();
	        	m.stop();
	        }  catch(Exception e1){
	             System.out.println(e1);
	           }
	    } 
		
		else if (table.getValueAt(selected, 6).equals(stop)){
			m.stop();
			model.setValueAt(play,selected ,6);
		}*/
/*		}
		
	};*/
	
	@SuppressWarnings("unused")
	ButtonColumn btnClm5 = new ButtonColumn(table,PlaySong,6);
	
	
	TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
//	table.setRowSorter(sorter);
	List<RowSorter.SortKey> sortKeys = new ArrayList<>();
	
	 table.setDragEnabled(true);
	  table.setDropMode(DropMode.INSERT_ROWS);
	  table.setTransferHandler(new TableRowTransferHandler(table)); 
	
	 
	int columnIndexToSort = 4;
	sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
	 
	sorter.setSortKeys(sortKeys);
	sorter.sort();
	
	
	

	for (int i=0; i<entries.length; i++)
	{ 

	model.addRow(new Object[] {entries[i].getFlag(),entries[i].getCountry(),entries[i].getArtist(),entries[i].getTitle(),entries[i].getRank(), entries[i].getChange(), "Listen"});

	}
	table.getColumn("Flag").setMaxWidth(35);
	table.getColumn("Rank").setMaxWidth(50);
	table.getColumn("Change").setMaxWidth(50);
	table.getColumn("Listen").setMaxWidth(70);
	
	setBounds(100, 100, 700, (int) (table.getPreferredSize().getHeight()) );
	JScrollPane scrollerTable = new JScrollPane(table);
	
	  table.setOpaque( false );
      

      //JScrollPane scrollPane = new JScrollPane( table );
      scrollerTable.setOpaque( false );
      scrollerTable.getViewport().setOpaque( false );
      
 
	
	contentPane.add(scrollerTable,BorderLayout.CENTER);
	model.fireTableRowsUpdated(0, entries.length - 1);
	
	table.addKeyListener(new KeyAdapter() {
		
		
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_F1){
				for (int i = 1; i < 44; i++) {
					int counter = 0;
					for (int j=0; j<entries.length; j++){
						if (i == entries[j].getRank())
							counter++;
					}
					if (counter > 1)
						JOptionPane.showMessageDialog(null, "You ranked " + i +" twice");
				}
			}
		}

	});
	
	table.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2){
			int selected = table.getSelectedRow();
			
			int index = 0;
			for (int i=0; i<entries.length; i++){
				if (entries[i].getCountry().equals(table.getValueAt(selected, 1)))
					index = i;
			}
			
			String input = (String) JOptionPane.showInputDialog(null,
					"Please enter your new rank:",
					"New Rank",
					JOptionPane.PLAIN_MESSAGE,
					entries[index].getFlag(),null,null);
			
			boolean flag = false;
			if (input == null){
				input = "";
			}
			else if (!input.equals("") && input != null){
				for (int i = 0; i < input.length(); i++) {
					if (!Character.isDigit(input.charAt(i))){
						JOptionPane.showMessageDialog(null, "Not a valid input");	
						flag = true;
					}
				}
				if (flag == false && (!input.equals("") && input != null)){
					int value = Integer.valueOf(input);
					if (value <= selected){
						System.out.println("Selected "+ selected);
						System.out.println("Value" + value);
						model.reorder(selected, value-1);
					}
					else{
						System.out.println("Selected "+ selected);
						System.out.println("Value" + value);
						model.reorder(selected, value);
					}
					//table.setRowSorter(sorter);
					//sorter.sort();
					try {
						db.saveDB(table,entries);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table.repaint();
					table.revalidate();
				}
			
		}

		}
		}
	});
	 JMenu fileMenu = new JMenu("File");
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    menuBar.add(fileMenu);
	    
	    JMenu about = new JMenu("About");
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    menuBar.add(about);
	    
	    JMenuItem version = new JMenuItem("EurovisionRank V2.0");
	    
	    about.add(version);
	    
	
  JMenuItem resetRank = new JMenuItem("Reset Rank");
  JMenuItem checkValidity = new JMenuItem("Check Validity");
  JMenuItem exit = new JMenuItem("Exit");
  
  resetRank.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < entries.length; i++) {
				model.setValueAt(0, i, 4);
				entries[i].setRank(0);
				model.setValueAt(0, i, 5);	
				entries[i].setChange(0);
				
			}
			//Arrays.sort(entries,new ComparyByCountry());
			table.repaint();
			try {
				db.saveDB(table,entries);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
  
  checkValidity.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int validity = 0;
		for (int i = 1; i < 44; i++) {
			int counter = 0;
			for (int j=0; j<entries.length; j++){
				if (i == entries[j].getRank()){
					counter++;
				}
			}
			if (counter > 1){
				JOptionPane.showMessageDialog(null, "You ranked " + i +" more than once");
				validity++;
			}
	}
		if (validity == 0)
			JOptionPane.showMessageDialog(null, "Your rank is valid (1-"+entries.length+")");
	}
});
  
  
  exit.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
		
	}
});
  
    model.addTableModelListener(new TableModelListener() {
	
	@Override
	public void tableChanged(TableModelEvent arg0) {
		
		//Arrays.sort(entries, new ComparyByRank());
		updateRowHeights(model.getRowCount());
		//table.repaint();
		if (model.getRowCount() == 43){
		for (int i = 0; i < model.getRowCount(); i++) {
			String temp = (String) model.getValueAt(i, 1);
			int index = 0;
			for (int j = 0; j < entries.length; j++) {
				if (entries[j].getCountry().equals(temp))
					index = j;
			}
			entries[index].setRank((int)model.getValueAt(i, 4));
			entries[index].setChange((int)model.getValueAt(i, 5));
			
		}
		try {
			db.saveDB(table,entries);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	}
});
    
    

    updateRowHeights(model.getRowCount());
  
/*  model.addTableModelListener(new TableModelListener() {
	
	@Override
	public void tableChanged(TableModelEvent arg0) {
		table.setRowSorter(sorter);
		sorter.sort();
		table.repaint();
		//table.setRowSorter(null);
		
	}
});*/
  
	/*model.addTableModelListener(new TableModelListener() {
		
		@Override
		public void tableChanged(TableModelEvent arg0) {
			int selected = arg0.getFirstRow();
			System.out.println(selected);
			if (arg0.getColumn() == 4 && selected > -1){
			
			
			int index = 0;
			for (int i=0; i<entries.length; i++){
				if (entries[i].getCountry().equals(table.getValueAt(selected, 1)))
					index = i;
			}
			for (int j = 0; j < model.getColumnCount(); j++) {
				if (model.getValueAt(selected, 1).equals(entries[j].getCountry()))
					index =j;
			}
			int former = entries[index].getRank();
			
			if ((Integer) table.getValueAt(selected, 4) > 43 || (Integer) table.getValueAt(selected, 4) < 0){
				JOptionPane.showMessageDialog(null, "Invalid Rank");
				model.setValueAt(former, index, 4);
			}
			else{
			entries[index].setRank((Integer) table.getValueAt(selected, 4));
			
			if (former != 0){
			int sum = former-entries[index].getRank();
			entries[index].setChange(sum);
			model.setValueAt(sum, index, 5);
			}
			else{
				entries[index].setChange(0);
				model.setValueAt(0, index, 5);
			}
			
			sorter.sort();

			try {
				db.saveDB(entries);
				table.repaint();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			table.repaint();

			
		}
			}
		}
	});*/


    	//updateRowHeights();
	   fileMenu.add(resetRank);
	   fileMenu.add(checkValidity);
	   fileMenu.add(exit);
	    
	 setJMenuBar(menuBar);
		java.net.URL inputUrl = getClass().getResource("/eurovision-logo.png");
		File dest = new File("Resources/eurovision-logo.png");
		FileUtils.copyURLToFile(inputUrl, dest);
	ImageIcon img = new ImageIcon("Resources/eurovision-logo.png");
	setIconImage(img.getImage());
	centreWindow(this);
	db.saveDB(table,entries);
	setTitle("Eurovision 2016 Rank");
	}
	
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
    private void updateRowHeights(int size)
    {
        try
        {
            for (int row = 0; row < size; row++)
            {
                int rowHeight = table.getRowHeight();

                for (int column = 0; column < table.getColumnCount(); column++)
                {
                    Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                    rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
                }
                table.setRowHeight(row, rowHeight);
            }
        }
        catch(ClassCastException e) {}
    }
    




	

	}
