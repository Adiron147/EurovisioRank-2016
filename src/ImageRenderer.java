import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;


public class ImageRenderer extends DefaultTableCellRenderer {

  
	JLabel lbl = new JLabel();
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column) {


		if (column == 0 && table.getColumnClass(0).equals(Icon.class)){
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			   lbl.setIcon((Icon) value);
			   
		}
		return lbl;
  }
}
