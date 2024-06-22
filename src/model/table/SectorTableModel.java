package model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.entities.Sector;

public class SectorTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private final List<Sector> rows;
    private final String[] columns;

    public SectorTableModel(List<Sector> sectors) {
        this.rows = sectors;
        this.columns = new String[] { "ID", "Nome", "Supervisor", "Locação" };
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

        Sector obj = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getnome();
            case 2:
                return obj.getSupervisor().getName();
            case 3:
                return obj.getLocacao();
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente");
        }
    }
}
