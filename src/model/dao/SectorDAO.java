package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import imp.GenericDao;
import model.entities.Sector;
import model.entities.Supervisor;
import util.base.BaseDados;

public class SectorDAO implements GenericDao<Sector> {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    /***
     * - Metodo aonde busca os equipamentos na Base da dados e manda pro Controller
     * pra popular a tela
     * e
     * - Metodo que faz a busca na Base e popula o view de editar Setores
     */

    @Override
    public void add(Sector enty) throws SQLException, Exception {

        String sql = "INSERT INTO bd_estoque.setor (NOME, FK_SUPERVISOR, LOCACAO) VALUES (?, ?, ?)";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getnome());
        this.stmt.setInt(2, enty.getSupervisor().getId());
        this.stmt.setString(3, enty.getLocacao());
        this.stmt.execute();
        BaseDados.closeConnection(this.con, this.stmt, this.rs);
    }

    @Override
    public void update(Sector enty) throws SQLException, Exception {
        String sql = "UPDATE bd_estoque.setor SET LOCACAO = ?, FK_SUPERVISOR = ? WHERE ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getLocacao());
        this.stmt.setInt(2, enty.getSupervisor().getId());
        this.stmt.setInt(3, enty.getId());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt);
    }

    @Override
    public void remove(Sector enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public List<Sector> findAll() throws SQLException, Exception {
        List<Sector> list = new ArrayList<>();

        String sql = "SELECT * FROM bd_estoque.setor SE inner join bd_estoque.SUPERVISOR S on FK_SUPERVISOR = S.ID ORDER BY SE.NOME ASC";

        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (this.rs.next()) {
            list.add(new Sector(
                    this.rs.getInt("ID"),
                    this.rs.getString("NOME"),
                    new Supervisor(this.rs.getInt("S.ID"), this.rs.getString("NOME")),
                    this.rs.getString("LOCACAO")));
        }

        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    public List<Sector> findAllTab() throws SQLException, Exception {
        List<Sector> lista = new ArrayList<>();
        this.con = BaseDados.getConnection();
        String sql = "SELECT SE.ID AS ID, SE.NOME AS NOME, SU.NOME AS SUPERVISOR, LOCACAO FROM bd_estoque.setor SE LEFT JOIN bd_estoque.supervisor SU ON FK_SUPERVISOR = SU.ID";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (rs.next()) {
            Sector obj = new Sector();
            obj.setId(rs.getInt("ID"));
            obj.setnome(rs.getString("NOME"));
            obj.getSupervisor().setName(rs.getString("SUPERVISOR"));
            obj.setLocacao(rs.getString("LOCACAO"));

            lista.add(obj);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    @Override
    public Sector findById(Sector enty) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Sector findById(int item) throws SQLException, Exception {
        Sector object = null;
        String sql = "SELECT SE.ID AS ID, SE.NOME AS NOME, SU.NOME AS SUPERVISOR, LOCACAO FROM bd_estoque.setor SE LEFT JOIN bd_estoque.supervisor SU ON FK_SUPERVISOR = SU.ID WHERE SE.ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, item);
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object = new Sector();
            object.setId(rs.getInt("ID"));
            object.setnome(rs.getString("NOME"));
            object.getSupervisor().setName(rs.getString("SUPERVISOR"));
            object.setLocacao(rs.getString("LOCACAO"));
        }
        return object;
    }

    public Sector findByName(String item) throws SQLException, Exception {
        Sector object = null;
        String sql = "SELECT SE.ID AS ID, SE.NOME AS NOME, SU.NOME AS SUPERVISOR, LOCACAO FROM bd_estoque.setor SE LEFT JOIN bd_estoque.supervisor SU ON FK_SUPERVISOR = SU.ID WHERE SE.NOME = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, item);
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object = new Sector();
            object.setId(rs.getInt("ID"));
            object.setnome(rs.getString("NOME"));
            object.getSupervisor().setName(rs.getString("SUPERVISOR"));
            object.setLocacao(rs.getString("LOCACAO"));
        }
        return object;
    }
}
