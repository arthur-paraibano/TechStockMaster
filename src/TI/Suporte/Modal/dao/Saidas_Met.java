package TI.Suporte.Modal.dao;

import TI.Suporte.Modal.obj.Saida_Mat;
import TI.Suporte.DataBase.BD_Base;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Saidas_Met {

    public void saida_database(Saida_Mat saidaObj) {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement("INSERT INTO bd_estoque.saida_estoque " +
                    "(NOME_EQUIPAMENTO, QUANTIDADE, DATA, TECNICO) VALUES (?,?,?,?)");

            stmt.setString(1, saidaObj.getNome_equipamento());
            stmt.setDouble(2, saidaObj.getQuantidade());
            stmt.setDate(3, saidaObj.getDate());
            stmt.setString(4, saidaObj.getTecnico());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar SQL: " + e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt);
        }
    }

    public static double calcularQuantidadeTotalSaida(String nomeEquipamento) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double totalSaida = 0.0;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement("SELECT SUM(QUANTIDADE) AS TOTAL_SAIDA FROM bd_estoque.saida_estoque WHERE NOME_EQUIPAMENTO = ?");
            stmt.setString(1, nomeEquipamento);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalSaida = rs.getDouble("TOTAL_SAIDA");
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
        return totalSaida;
    }
}