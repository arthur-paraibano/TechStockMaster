package TI.Suporte.Util.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class FormatTable extends DefaultTableCellRenderer {
    private Color BackgroundColor = new Color(0, 41, 255, 255);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Verifique se a linha está selecionada
        if (isSelected) {
            c.setForeground(Color.WHITE);
            c.setBackground(BackgroundColor); // Define a cor de fundo da linha toda quando a linha estiver selecionada
        }else {
            c.setBackground(table.getBackground());
            c.setForeground(table.getForeground());
        }
        return c;
    }
}
