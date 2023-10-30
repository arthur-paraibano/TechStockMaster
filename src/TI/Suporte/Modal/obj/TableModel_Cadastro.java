package TI.Suporte.Modal.obj;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableModel_Cadastro extends AbstractTableModel {

    private List<Equipamentos_Ent_Est> dados;
    private String[] colunas = {"ID", "Nome do equipamento", "Descrição"};
    private Map<String, Integer> equipamentoToID = new HashMap<>();
    private String equipamento;

    public TableModel_Cadastro(List<Equipamentos_Ent_Est> dados) {
        this.dados = dados;

        int id = 1;
        for (Equipamentos_Ent_Est entrada : dados) {
            String equipamento = entrada.getNome_equipamento();
            if (!equipamentoToID.containsKey(equipamento)) {
                equipamentoToID.put(equipamento, id++);
            }
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
            case 1 -> dados.get(rowIndex).getNome_equipamento();
            case 2 -> dados.get(rowIndex).getDescricao();
            default -> null;
        };
    }

    public Equipamentos_Ent_Est getDataAt(int rowIndex) {
        return dados.get(rowIndex);
    }

    public void updateRow(int selectedRowIndex, Equipamentos_Ent_Est objToUpdate) {
    }
}