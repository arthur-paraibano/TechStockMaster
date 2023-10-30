package TI.Suporte.Modal.dao;

import TI.Suporte.Modal.obj.Equipamentos_Ent_Est;
import TI.Suporte.DataBase.BD_Base;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Entradas_Mat {

    public void add_database(Equipamentos_Ent_Est cadastroObj) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement("INSERT INTO bd_estoque.entrada_estoque " +
                    "(NOME_EQUIPAMENTO, QUANTIDADE, DATA, DESCRICAO) VALUES (?,?,?,?)");

            stmt.setString(1, cadastroObj.getNome_equipamento());
            stmt.setDouble(2, cadastroObj.getQuantidade());
            stmt.setDate(3, cadastroObj.getDate());
            stmt.setString(4, cadastroObj.getDescricao());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar SQL: " + e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt);
        }
    }

    public static double calcularQuantidadeTotalEntrada(String nomeEquipamento) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double totalEntrada = 0.0;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement("SELECT SUM(QUANTIDADE) AS TOTAL_ENTRADA FROM bd_estoque.entrada_estoque WHERE NOME_EQUIPAMENTO = ?");

            stmt.setString(1, nomeEquipamento);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalEntrada = rs.getDouble("TOTAL_ENTRADA");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
        return totalEntrada;
    }
}