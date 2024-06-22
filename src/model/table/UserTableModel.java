package model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.entities.User;

public class UserTableModel extends AbstractTableModel {
    private final List<User> rows;
    private final String[] columns;

    public UserTableModel(List<User> users) {
        this.rows = users;
        this.columns = new String[] { "ID", "Nome", "Login", "Tipo", "Full Acesso", "Bloqueado" };
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

        User obj = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getNoemCompleto();
            case 2:
                return obj.getNomeLogin();
            case 3:
                return obj.getTipoUsuario();
            case 4:
                return obj.getAcessoModulo();
            case 5:
                String valor = obj.getBloqueador();
                if (valor.equals("0")) {
                    return "Não";
                } else {
                    return "Sim";
                }
            default:
                throw new IndexOutOfBoundsException("Coluna inexistente");
        }
    }
}
