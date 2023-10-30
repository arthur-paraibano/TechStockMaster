package TI.Suporte.Modal.dao;

import TI.Suporte.Modal.obj.Soli_Compas;
import TI.Suporte.DataBase.BD_Base;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Soli_Compras {

    public void add_Database(Soli_Compas obj) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement("INSERT INTO bd_estoque.solicitacao_compra " +
                    "(EQUIPAMENTO, QUANTIDADE, SETOR, SOLICITANTE, DESCRICAO, TECNICO, DATA) VALUES (?,?,?,?,?,?,?)");

            stmt.setString(1, obj.getEquipamento());
            stmt.setDouble(2, obj.getQuantidade());
            stmt.setString(3, obj.getSetor());
            stmt.setString(4, obj.getSolicitante());
            stmt.setString(5, obj.getDescricao());
            stmt.setString(6, obj.getTecnico());
            stmt.setDate(7, obj.getData());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados salvo");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados no Banco de Dados");
        } finally {
            BD_Base.closeConnection(con, stmt);
        }
    }

    public List<Soli_Compas> red() {
        com.sun.jdi.connect.spi.Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Soli_Compas> objs = new ArrayList<>();

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement("SELECT * FROM bd_estoque.solicitacao_compra ORDER BY ID ASC");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Soli_Compas objEntrada = new Soli_Compas();

                objEntrada.setId(rs.getInt("ID"));
                objEntrada.setEquipamento(rs.getString("EQUIPAMENTO"));
                objEntrada.setQuantidade(rs.getDouble("QUANTIDADE"));
                objEntrada.setSetor(rs.getString("SETOR"));
                objEntrada.setSolicitante(rs.getString("SOLICITANTE"));
                objEntrada.setDescricao(rs.getString("DESCRICAO"));
                objEntrada.setTecnico(rs.getString("TECNICO"));
                objEntrada.setData(rs.getDate("DATA"));
                objEntrada.setStatus(rs.getString("STATUS"));

                objs.add(objEntrada);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela SQL: " + e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
        return objs;
    }

    public static double quantidadeTotal(String nomeEquipamento) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement(
                    "SELECT sum(QUANTIDADE) as QUANTIDADE FROM bd_estoque.entrada_estoque where NOME_EQUIPAMENTO = ?"
            );

            stmt.setString(1, nomeEquipamento);
            rs = stmt.executeQuery();

            if (rs.next()) {
                double quantidade = rs.getDouble("QUANTIDADE");
                return Math.round(quantidade * 100.00) / 100.00;
            } else {
                return 0.0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao exibir as quantidades em estoque!");
            return 0.0;
        } finally {
            BD_Base.closeConnection(con, stmt, rs);
        }
    }
}
