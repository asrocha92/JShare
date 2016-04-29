package br.alex.barraRender;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BarraRenderer extends JProgressBar implements TableCellRenderer{
	
	public BarraRenderer() {
		setStringPainted(true);
		setBackground(Color.WHITE);
		setForeground(Color.BLUE);
		setBorder(null);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {
		Float f = (Float) value;
		super.setValue(f.intValue());
		return this;
	}

}
