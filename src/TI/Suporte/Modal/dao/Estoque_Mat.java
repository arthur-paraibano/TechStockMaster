package TI.Suporte.Modal.dao;

import TI.Suporte.DataBase.BD_Base;
import TI.Suporte.Modal.obj.Equipamentos_Ent_Est;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Estoque_Mat {

    public List<Equipamentos_Ent_Est> red() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Equipamentos_Ent_Est> entradaObjs = new ArrayList<>();

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement("SELECT * FROM bd_estoque.cadastro_equipamento ORDER BY ID ASC");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamentos_Ent_Est objEntrada = new Equipamentos_Ent_Est();

                objEntrada.setId(rs.getInt("ID"));
                objEntrada.setNome_equipamento(rs.getString("EQUIPAMENTO"));
                objEntrada.setDescricao(rs.getString("DESCRICAO"));

                entradaObjs.add(objEntrada);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela SQL: " + e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
        return entradaObjs;
    }

    public static String ultimaData(String nomeEquipamento) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String dataFormatada = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement(
                    "SELECT MAX(DATA) AS UltimaData FROM bd_estoque.entrada_estoque WHERE NOME_EQUIPAMENTO = ?");

            stmt.setString(1, nomeEquipamento);
            rs = stmt.executeQuery();

            if (rs.next()) {
                java.sql.Date date = rs.getDate("UltimaData");
                if (date != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
                    dataFormatada = sdf.format(date);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir as datas!");
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
        return dataFormatada;
    }
}
