package TI.Suporte.Util.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;

public class StatusConsertosCellRenderer extends DefaultTableCellRenderer {
    private Color redColor = new Color(255, 0, 0, 168);
    private Color greenColor = new Color(0, 255, 0, 136);
    private Color BackgroundColor = new Color(0, 41, 255, 255);
    private int statusColumnIndex = 6;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Verifique se a coluna atual é a coluna "Status"
        if (column == statusColumnIndex) {
            // Obtém o valor da coluna "Status" na linha atual
            String status = (String) table.getModel().getValueAt(row, statusColumnIndex);

            // Defina a cor de fundo com base no valor da coluna "Status"
            if ("Em Conserto".equals(status)) {
                c.setBackground(redColor);
            } else if ("Retorno".equals(status)) {
                c.setBackground(greenColor);
            } else {
                c.setBackground(Color.orange);
            }

            // Defina a cor do texto com base na seleção
            if (isSelected) {
                c.setForeground(Color.WHITE); // Defina a cor do texto quando a célula estiver selecionada
            } else {
                c.setForeground(Color.BLACK); // Cor do texto padrão
            }
        } else {
            // Coluna não é "Status", mantenha as cores padrão
            c.setBackground(table.getBackground());
            c.setForeground(table.getForeground());
        }

        // Verifique se a linha está selecionada
        if (isSelected) {
            c.setForeground(Color.WHITE);
            c.setBackground(BackgroundColor); // Define a cor de fundo da linha toda quando a linha estiver selecionada
        }
        return c;
    }
}


