package model.table;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.entities.Repair;

public class RepairTableModel extends AbstractTableModel {
    private final List<Repair> rows;
    private final String[] columns;

    public RepairTableModel(List<Repair> movements) {
        this.rows = movements;
        this.columns = new String[] { "ID", "EQUIPAMENTO", "TAG", "SETOR", "TÉCNICO", "DATA", "STATUS" };
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
        Repair obj = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getEquipment().getNome();
            case 2:
                return obj.getTag().getSequence();
            case 3:
                return obj.getSector().getnome();
            case 4:
                return obj.getUser().getNomeLogin();
            case 5:
                Date date = obj.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(date);
            case 6:
                return obj.getStatus();
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente");
        }
    }
}
