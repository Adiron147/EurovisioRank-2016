import java.util.Date;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;


public class MyModel extends DefaultTableModel implements Reorderable {
	   @SuppressWarnings("rawtypes")
	Class[] types = { Icon.class, String.class, String.class, String.class, Integer.class, Integer.class, JLabel.class };


		@SuppressWarnings("unchecked")
		@Override
	    public Class getColumnClass(int columnIndex) {
	        return this.types[columnIndex];
		}

	    public boolean isCellEditable(int row, int column) {
	    	if (column == 6)
	    		return true;
	        //all cells false
	    	else
	    		return false;
	     }

		@Override
		public void reorder(int fromIndex, int toIndex) {
			if (fromIndex != toIndex && fromIndex < 43 && toIndex < 43 && fromIndex >=0  && toIndex >=0){
			int index = 0;
			for (int i=0; i<getRowCount(); i++){
				if (this.getValueAt(i, 1).equals(this.getValueAt(fromIndex, 1)))
					index = i;
			}
			int former = (int) this.getValueAt(fromIndex, 4);
			
			this.insertRow(toIndex, (Vector)this.getDataVector().elementAt(index));
			if (toIndex < fromIndex){
				this.setValueAt(toIndex+1, toIndex, 4);
			}
			else{
				this.setValueAt(toIndex+1, toIndex, 4);
			}
			for (int i = 0; i < this.getRowCount(); i++) {
				if (i != toIndex && this.getDataVector().elementAt(toIndex).equals(this.getDataVector().elementAt(i)))
					this.removeRow(i);
			}
			if (toIndex < fromIndex){
			for (int i = toIndex+1; i <= fromIndex; i++) {
				if ((int) this.getValueAt(i, 4) != 0)
					this.setValueAt((int) this.getValueAt(i, 4)+1, i, 4);
			}
			}
			else{
				for (int i = fromIndex; i <= toIndex-1; i++) {
					if ((int) this.getValueAt(i, 4) != 0)
						this.setValueAt((int) this.getValueAt(i, 4)-1, i, 4);
				}
			}
			if (former != 0){
			int sum = fromIndex-toIndex+1;
			if (toIndex > fromIndex)
				this.setValueAt(sum, toIndex-1, 5);
			else
				this.setValueAt(sum-1, toIndex, 5);
			}
			}
			else if (toIndex > 42 || fromIndex > 42){
				int former = (int) this.getValueAt(fromIndex, 4);
				int index = 0;
				for (int i=0; i<getRowCount(); i++){
					if (this.getValueAt(i, 1).equals(this.getValueAt(fromIndex, 1)))
						index = i;
				}
				this.insertRow(43, (Vector)this.getDataVector().elementAt(index));
				this.setValueAt(43, 43, 4);
				this.removeRow(fromIndex);
				for (int i = fromIndex; i < toIndex-1; i++) {
					if ((int) this.getValueAt(i, 4) != 0)
						this.setValueAt((int) this.getValueAt(i, 4)-1, i, 4);
			}
				
				if (former != 0){
					int sum = fromIndex-43+1;
					if (43 > fromIndex)
						this.setValueAt(sum, 43-1, 5);
					else
						this.setValueAt(sum-1, 43, 5);
					}
				
			
			
			
		}
		}
		
}
