package model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.entities.Supervisor;

public class SupervisorTableModel extends AbstractTableModel {
    private final List<Supervisor> rows;
    private final String[] columns;

    public SupervisorTableModel(List<Supervisor> supervisors) {
        this.rows = supervisors;
        this.columns = new String[] { "ID", "Nome" };
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

        Supervisor obj = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getName();
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente");
        }
    }
}
