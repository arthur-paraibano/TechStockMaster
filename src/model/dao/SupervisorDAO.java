package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import imp.GenericDao;
import model.entities.Supervisor;
import util.base.BaseDados;

public class SupervisorDAO implements GenericDao<Supervisor> {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public void add(Supervisor enty) throws SQLException, Exception {
        String sql = "INSERT INTO bd_estoque.supervisor (NOME) VALUES(?)";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getName());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt);
    }

    @Override
    public void update(Supervisor enty) throws SQLException, Exception {
        String sql = "UPDATE bd_estoque.supervisor SET NOME = ? WHERE ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getName());
        this.stmt.setInt(2, enty.getId());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt);
    }

    @Override
    public void remove(Supervisor enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public List<Supervisor> findAll() throws SQLException, Exception {
        List<Supervisor> list = new ArrayList<>();

        String sql = "SELECT * FROM bd_estoque.supervisor ORDER BY NOME";

        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (this.rs.next()) {
            list.add(new Supervisor(
                    this.rs.getInt("ID"),
                    this.rs.getString("NOME")));
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    public List<Supervisor> findAllTab() throws SQLException, Exception {
        List<Supervisor> lista = new ArrayList<>();
        this.con = BaseDados.getConnection();
        String sql = "SELECT ID, NOME FROM bd_estoque.supervisor";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (rs.next()) {
            lista.add(new Supervisor(rs.getInt("ID"), rs.getString("NOME")));
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    @Override
    public Supervisor findById(Supervisor enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Supervisor findById(Integer id) throws SQLException, Exception {
        Supervisor object = null;
        String sql = "SELECT ID, NOME FROM bd_estoque.supervisor where id = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, id);
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object = new Supervisor(
                    this.rs.getInt("ID"),
                    this.rs.getString("NOME"));
        }
        return object;
    }
}
