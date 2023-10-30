package TI.Suporte.Modal.dao;

import TI.Suporte.DataBase.BD_Base;
import TI.Suporte.Modal.obj.ConcertosEq;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Concertos {

    public List<ConcertosEq> red() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ConcertosEq> entradaObjs = new ArrayList<>();

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement(
                    "SELECT * FROM bd_estoque.consertos_equipamentos ORDER BY ID ASC");

            rs = stmt.executeQuery();

            while (rs.next()) {
                ConcertosEq objEntrada = new ConcertosEq();

                objEntrada.setId(rs.getInt("ID"));
                objEntrada.setNomeDoEquipamento(rs.getString("EQUIPAMENTO"));
                objEntrada.setModeloReferancia(rs.getString("MODELO"));
                objEntrada.setTecnico(rs.getString("TECNICO"));
                objEntrada.setData(rs.getDate("DATA"));
                objEntrada.setSetor(rs.getString("SETOR"));
                objEntrada.setDescricao(rs.getString("DESCRICAO"));
                objEntrada.setStatus(rs.getString("STATUS"));

                entradaObjs.add(objEntrada);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela SQL: " + e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
        return entradaObjs;
    }

    public void add_Database(ConcertosEq obj) {
        java.sql.Connection con = null;
        PreparedStatement stmt = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement(
                    "INSERT INTO bd_estoque.consertos_equipamentos (EQUIPAMENTO, MODELO, TECNICO, DATA, SETOR, DESCRICAO) VALUES (?,?,?,?,?,?)");

            stmt.setString(1, obj.getNomeDoEquipamento());
            stmt.setString(2, obj.getModeloReferancia());
            stmt.setString(3, obj.getTecnico());
            stmt.setDate(4, obj.getData());
            stmt.setString(5, obj.getSetor());
            stmt.setString(6, obj.getDescricao());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Dados salvo");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados no Banco de Dados");
        } finally {
            BD_Base.closeConnection(con, stmt);
        }
    }

    public static void updateCompras(ConcertosEq updateObj) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement(
                    "UPDATE bd_estoque.consertos_equipamentos SET STATUS = ? WHERE ID = ?");

            stmt.setString(1, updateObj.getStatus());
            stmt.setInt(2, updateObj.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Material atualizado.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fazer as alterações no Banco de dados: \n " + e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
    }
}
