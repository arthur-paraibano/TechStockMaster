package TI.Suporte.Modal.obj;

import TI.Suporte.Modal.dao.Estoque_Mat;
import TI.Suporte.Modal.dao.Entradas_Mat;
import TI.Suporte.Modal.dao.Saidas_Met;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableModel_Estoque extends AbstractTableModel {

    private List<Equipamentos_Ent_Est> dados;
    private String[] colunas = {"ID", "Nome do equipamento", "Descrição", "Quant. Estoque", "Última entrada"};
    private Map<String, Integer> equipamentoToID = new HashMap<>();
    private String equipamento;

    public TableModel_Estoque(List<Equipamentos_Ent_Est> dados) {
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
            case 0:
                equipamento = dados.get(rowIndex).getNome_equipamento();
                yield equipamentoToID.get(equipamento);
            case 1:
                yield dados.get(rowIndex).getNome_equipamento();
            case 2:
                yield dados.get(rowIndex).getDescricao();
            case 3:
                equipamento = dados.get(rowIndex).getNome_equipamento();
                double totalEntrada = Entradas_Mat.calcularQuantidadeTotalEntrada(equipamento);
                double totalSaida = Saidas_Met.calcularQuantidadeTotalSaida(equipamento);
                yield totalEntrada - totalSaida;

            case 4:
                yield Estoque_Mat.ultimaData(equipamento);
            default:
                yield null;
        };
    }
}
