

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.text.StyledEditorKit.FontSizeAction;

@SuppressWarnings("serial")
public class MyTableCellRenderer extends
		javax.swing.table.DefaultTableCellRenderer {

	

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// component will actually be this.
		Component component = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);
		
	
	    
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font(getFont().toString(), Font.BOLD, 13));
			
		 if (column == 5){
			setHorizontalAlignment(SwingConstants.CENTER);
			
			if ((int) value > 0){
				component.setForeground(Color.GREEN);
			}
			else if ((int) value < 0)
				component.setForeground(Color.red);
			else
				component.setForeground(Color.white);
		}
		else
			component.setForeground(Color.white);
			
			
			
		return component;
	}
}