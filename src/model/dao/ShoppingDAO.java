package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import imp.GenericDao;
import model.entities.Shopping;
import util.Session;
import util.base.BaseDados;

public class ShoppingDAO implements GenericDao<Shopping> {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public void add(Shopping enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    public Integer addEquip(Shopping enty) throws SQLException, Exception {
        this.con = BaseDados.getConnection();
        String sql = "INSERT INTO bd_estoque.solicitacao_compra (FK_CODEQUIP, QUANTIDADE, FK_CODSETOR, FK_CODTECNICO, DESCRICAO, DATA) VALUES ( ?, ?, ?, ?, ?, ?)";
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, enty.getEquipment().getId());
        this.stmt.setDouble(2, enty.getAmount());
        this.stmt.setLong(3, enty.getSector().getId());
        this.stmt.setInt(4, Session.getUser().getId());
        this.stmt.setString(5, enty.getDescription());
        this.stmt.setDate(6, enty.getDate());
        int rowsAffected = stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
        return rowsAffected;
    }

    @Override
    public void update(Shopping enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public Integer updateSol(Shopping enty) throws SQLException, Exception {
        String sql = "UPDATE bd_estoque.solicitacao_compra SET DESCRICAO = ?, STATUS = ? WHERE ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getDescription());
        this.stmt.setString(2, enty.getStatus());
        this.stmt.setInt(3, enty.getId());
        int rowsAffected = stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
        return rowsAffected;
    }

    @Override
    public void remove(Shopping enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public List<Shopping> findAll() throws SQLException, Exception {
        List<Shopping> lista = new ArrayList<>();
        String sql = "SELECT CO.ID AS ID, EQ.EQUIPAMENTO AS EQUIPAMENTO, QUANTIDADE, ST.NOME AS SETOR, LG.USERNAME AS TECNICO, CO.DESCRICAO, CO.DATA, CO.STATUS FROM bd_estoque.solicitacao_compra CO LEFT JOIN bd_estoque.equipamento EQ ON CO.FK_CODEQUIP = EQ.ID LEFT JOIN bd_estoque.setor ST ON CO.FK_CODSETOR = ST.ID LEFT JOIN bd_estoque.user LG ON CO.FK_CODTECNICO = LG.ID ORDER BY CO.FK_CODEQUIP ASC";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (rs.next()) {
            Shopping obj = new Shopping();
            obj.setId(rs.getInt("ID"));
            obj.getEquipment().setNome(rs.getString("EQUIPAMENTO"));
            obj.setAmount(rs.getDouble("QUANTIDADE"));
            obj.getSector().setnome(rs.getString("SETOR"));
            obj.getUser().setNomeLogin(rs.getString("TECNICO"));
            obj.setDescription(rs.getString("DESCRICAO"));
            obj.setDate(rs.getDate("DATA"));
            obj.setStatus(rs.getString("STATUS"));
            lista.add(obj);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    public List<Shopping> findAllPes() throws SQLException, Exception {
        List<Shopping> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT EQ.EQUIPAMENTO AS EQUIPAMENTO FROM bd_estoque.solicitacao_compra CO LEFT JOIN bd_estoque.equipamento EQ ON CO.FK_CODEQUIP = EQ.ID ORDER BY CO.FK_CODEQUIP ASC";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (rs.next()) {
            Shopping obj = new Shopping();
            obj.getEquipment().setNome(rs.getString("EQUIPAMENTO"));
            lista.add(obj);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    @Override
    public Shopping findById(Shopping enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Shopping findById(Integer id) throws SQLException, Exception {
        Shopping object = new Shopping();
        String sql = "SELECT CO.ID AS ID, EQ.EQUIPAMENTO AS EQUIPAMENTO, QUANTIDADE, ST.NOME AS SETOR, LG.USERNAME AS TECNICO, CO.DESCRICAO, CO.DATA, CO.STATUS FROM bd_estoque.solicitacao_compra CO LEFT JOIN bd_estoque.equipamento EQ ON CO.FK_CODEQUIP = EQ.ID LEFT JOIN bd_estoque.setor ST ON CO.FK_CODSETOR = ST.ID LEFT JOIN bd_estoque.user LG ON CO.FK_CODTECNICO = LG.ID WHERE CO.ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, id);
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object.setId(rs.getInt("ID"));
            object.getEquipment().setNome(rs.getString("EQUIPAMENTO"));
            object.setAmount(rs.getDouble("QUANTIDADE"));
            object.getSector().setnome(rs.getString("SETOR"));
            object.getUser().setNomeLogin(rs.getString("TECNICO"));
            object.setDescription(rs.getString("DESCRICAO"));
            object.setDate(rs.getDate("DATA"));
            object.setStatus(rs.getString("STATUS"));
        }
        return object;
    }

}
