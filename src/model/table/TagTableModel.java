package model.table;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.entities.Tag;

public class TagTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final List<Tag> rows;
    private final String[] columns;

    public TagTableModel(List<Tag> tags) {
        this.rows = tags;
        this.columns = new String[] { "ID", "ABREV. DA TAG", "DESCRIÇÃO", "DATA" };
    }

    // Retorna a quantidade de linhas
    @Override
    public int getRowCount() {
        return this.rows.size();
    }

    // Retorna a quantidade de colunas
    @Override
    public int getColumnCount() {
        return this.columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.columns[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            default:
                return String.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Tag obj = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getAbreviacao();
            case 2:
                return obj.getType();
            case 3:
                Date date = obj.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(date);
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente");
        }
    }
}
