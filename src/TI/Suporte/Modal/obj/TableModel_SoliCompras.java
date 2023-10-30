package TI.Suporte.Modal.obj;

import TI.Suporte.Modal.dao.Cadatro_Mat;
import TI.Suporte.Modal.dao.Entradas_Mat;
import TI.Suporte.Modal.dao.Saidas_Met;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableModel_SoliCompras extends AbstractTableModel {

    private List<Soli_Compas> dados;
    private String[] colunas = {"ID", "Equipamento", "Qut. Solicitada", "Qut. Estoque", "Setor", "Descrição", "Status"};
    private Map<String, Integer> equipamentoToID = new HashMap<>();
    private String equipamento;

    public TableModel_SoliCompras(List<Soli_Compas> dados) {
        this.dados = dados;

        int id = 1;
        for (Soli_Compas entrada : dados) {
            String equipamento = entrada.getEquipamento();
            if (!equipamentoToID.containsKey(equipamento)) {
                equipamentoToID.put(equipamento, id++);
            }
        }
    }

//    public CadastroTableModel(List<Estoque_OBJ> dados) {
//        this.dados = dados;
//
//        int id = 1;
//        for (Estoque_OBJ entrada : dados) {
//            String equipamento = (entrada.getNome_equipamento());
//            if (!equipamentoToID.containsKey(equipamento)) {
//                equipamentoToID.put((equipamento), id++);
//            }
//        }
//    }


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

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> {
                yield dados.get(rowIndex).getId().intValue();
//                equipamento = dados.get(rowIndex).getNome_equipamento();
//                yield equipamentoToID.get(equipamento);
            }
            case 1 -> equipamento = dados.get(rowIndex).getEquipamento();
            case 2 -> dados.get(rowIndex).getQuantidade();
            case 3 -> {
                equipamento = dados.get(rowIndex).getEquipamento();
                double totalEntrada = Entradas_Mat.calcularQuantidadeTotalEntrada(equipamento);
                double totalSaida = Saidas_Met.calcularQuantidadeTotalSaida(equipamento);
                yield totalEntrada - totalSaida;
            }
            case 4 -> dados.get(rowIndex).getSetor();
            case 5 -> dados.get(rowIndex).getDescricao();
            case 6 -> dados.get(rowIndex).getStatus();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        Soli_Compas objToUpdate = getDataAt(rowIndex);

        String newValue = String.valueOf(aValue);
        String oldValue = objToUpdate.getStatus();

        if(!newValue.equals(oldValue)){

        objToUpdate.setStatus(newValue);

        Cadatro_Mat.updateCompras(objToUpdate);
        }
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    public Soli_Compas getDataAt(int rowIndex) {
        return dados.get(rowIndex);
    }

    public void updateRow(int selectedRowIndex, Soli_Compas objToUpdate) {
    }
}