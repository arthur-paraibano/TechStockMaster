package TI.Suporte.Modal.dao;

import TI.Suporte.DataBase.BD_Base;
import TI.Suporte.Modal.obj.Equipamentos_Ent_Est;
import TI.Suporte.Modal.obj.Soli_Compas;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Cadatro_Mat {

    public void cadastro_database(Equipamentos_Ent_Est cadastroObj) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement("INSERT INTO bd_estoque.cadastro_equipamento " +
                    "(EQUIPAMENTO, DESCRICAO) VALUES (?,?)");

            stmt.setString(1, cadastroObj.getNome_equipamento());
            stmt.setObject(2, cadastroObj.getDescricao());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar SQL: " + e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
    }

    public static void updateCadastro(Equipamentos_Ent_Est updateObj) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement(
                    "UPDATE bd_estoque.cadastro_equipamento SET EQUIPAMENTO = ?, DESCRICAO = ? WHERE ID = ?");

            stmt.setString(1, updateObj.getNome_equipamento());
            stmt.setObject(2, updateObj.getDescricao());
            stmt.setInt(3, updateObj.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Material atualizado.");
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Erro ao fazer as alterações no Banco de dados: \n " + e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
    }


    public static void updateCompras(Soli_Compas updateObj) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = Objects.requireNonNull(BD_Base.getConnection()).prepareStatement(
                    "UPDATE bd_estoque.solicitacao_compra SET QUANTIDADE = ?, STATUS = ? WHERE ID = ?");

            stmt.setDouble(1, updateObj.getQuantidade());
            stmt.setString(2, updateObj.getStatus());
            stmt.setInt(3, updateObj.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Material atualizado.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fazer as alterações no Banco de dados: \n " + e);
        } finally {
            BD_Base.closeConnection((java.sql.Connection) con, stmt, rs);
        }
    }
}