package util;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableUtil {

    public static void hide(JTable table, int indexColum) {
        // tem que ser nessa sequencia para ocultar a tabela
        table.getColumnModel().getColumn(indexColum).setMaxWidth(0);
        table.getColumnModel().getColumn(indexColum).setMinWidth(0);
        table.getColumnModel().getColumn(indexColum).setPreferredWidth(0);
        table.getColumnModel().getColumn(indexColum).setWidth(0);
    }

    public static void showToltip(JTable table, int indexColum) {

    table.getColumnModel().getColumn(indexColum).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (c instanceof JLabel) {
                JLabel label = (JLabel) c;
                label.setToolTipText(value == null?" ":value.toString()); // Define a dica de ferramenta como o valor da célula
            }
            return c;
        }
    });

    

    }
}
