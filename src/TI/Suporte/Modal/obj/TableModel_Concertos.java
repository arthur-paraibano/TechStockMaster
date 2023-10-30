package TI.Suporte.Modal.obj;

import TI.Suporte.Modal.dao.Concertos;
import TI.Suporte.Util.util.DataUtil;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableModel_Concertos extends AbstractTableModel {

    private List<ConcertosEq> dados;
    private String[] colunas = {"ID", "Nome do equipamento", "Tecnico", "Data", "Setor", "Descrição", "Status"};
    private Map<String, Integer> equipamentoToID = new HashMap<>();

    public TableModel_Concertos() {
    }

    public TableModel_Concertos(List<ConcertosEq> dados) {
        this.dados = dados;
        int id = 1;
        for (ConcertosEq entrada : dados) {
            String equipamento = entrada.getNomeDoEquipamento();
            if (!equipamentoToID.containsKey(equipamento)) {
                equipamentoToID.put(equipamento, id++);
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 6: return true;
            default:
                return false;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    // {"ID", "Nome do equipamento", "Tecnico", "Data", "Setor", "Descrição", "Status"}
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return dados.get(rowIndex).getId();
            case 1:
                return dados.get(rowIndex).getNomeDoEquipamento();
            case 2:
                return dados.get(rowIndex).getTecnico();
            case 3:
                return DataUtil.toString(dados.get(rowIndex).getData());
            case 4:
                return dados.get(rowIndex).getSetor();
            case 5:
                return dados.get(rowIndex).getDescricao();
            case 6:
                return dados.get(rowIndex).getStatus();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        ConcertosEq objToUpdate = getDataAt(rowIndex);

        String newValue = String.valueOf(aValue);
        String oldValue = objToUpdate.getStatus();

        if(!newValue.equals(oldValue)){

            objToUpdate.setStatus(newValue);

            Concertos.updateCompras(objToUpdate);
        }
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    public ConcertosEq getDataAt(int rowIndex) {
        return dados.get(rowIndex);
    }

}