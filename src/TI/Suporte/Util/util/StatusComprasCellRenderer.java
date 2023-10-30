package TI.Suporte.Util.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class StatusComprasCellRenderer extends DefaultTableCellRenderer {
    private Color compra = new Color(50, 183, 24, 194);
    private Color solicitado = new Color(2, 255, 253, 252);
    private Color cancelado = new Color(255, 0, 0, 168);
    private Color recebido = new Color(97, 49, 253, 252);
    private Color vazio = new Color(121, 115, 115, 255);


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
            // "Comprado", "Cancelado", "Recebido", "Em Análise"
            if ("Comprado".equals(status)) {
                c.setBackground(compra);
            } else if ("Solicitado".equals(status)) {
                c.setBackground(solicitado);
            } else if ("Cancelado".equals(status)) {
                c.setBackground(cancelado);
            } else if ("Recebido".equals(status)) {
                c.setBackground(recebido);
            } else if ("Em Análise".equals(status)) {
                c.setBackground(Color.orange);
            } else {
                c.setBackground(vazio);
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


