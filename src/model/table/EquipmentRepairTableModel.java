package model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.entities.Equipment;


public class EquipmentRepairTableModel extends AbstractTableModel {
    private final List<Equipment> rows;
    private final String[] columns;

    public EquipmentRepairTableModel(List<Equipment> equipment) {
        this.rows = equipment;
        this.columns = new String[] { "ID", "EQUIPAMENTO", "SETOR", "Tag", "STATUS" };
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
                return obj.getNome();
            case 2:
                return obj.getSetor().getnome();
            case 3:
                return obj.getTag().getSequence();
            case 4:
                return "";
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente");
        }
    }
}