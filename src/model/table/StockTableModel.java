package model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.entities.Equipment;

public class StockTableModel extends AbstractTableModel {
    private final List<Equipment> rows;
    private final String[] columns;

    public StockTableModel(List<Equipment> equipment) {
        this.rows = equipment;
        this.columns = new String[] { "ID", "CODIGO", "EQUIPAMENTO", "UND", "QUANTIDADE" };
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
        Equipment obj = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getCodigo();
            case 2:
                return obj.getNome();
            case 3:
                return obj.getAbreviacao_un();
            case 4:
                return obj.getQuantidade();
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente");
        }
    }
}