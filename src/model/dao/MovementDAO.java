package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import imp.GenericDao;
import model.entities.Movement;
import util.Session;
import util.base.BaseDados;

public class MovementDAO implements GenericDao<Movement> {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    /***
     * Dados apara atualizar os equipamentos do banco de dados da Lykos para base de
     * dados local.
     ***/
    public Integer addEntry(Movement movement) throws SQLException, Exception {
        this.con = BaseDados.getConnection();
        String sql = "INSERT INTO bd_estoque.movimento (FK_CODEQUIP, QUANTIDADE, N_LYKOS, DATA, FK_CODUSER, TYPE, FK_CODSETOR) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        this.stmt = this.con.prepareStatement(sql);
        int rowsAffected = 0;
        this.stmt.setInt(1, movement.getEquipment().getId());
        this.stmt.setDouble(2, movement.getAmount());
        this.stmt.setString(3, movement.getOsLykos());
        this.stmt.setDate(4, (Date) movement.getDate());
        this.stmt.setLong(5, Session.getUser().getId());
        this.stmt.setString(6, movement.getType().name());
        this.stmt.setInt(7, movement.getSector().getId());
        rowsAffected = this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, null);
        return rowsAffected;
    }

    @Override
    public void add(Movement enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }





    @Override
    public void update(Movement enty) throws SQLException, Exception {
        String sql = "UPDATE bd_estoque.conserto SET DESCRICAO = ?, STATUS = ? WHERE ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getDescription());
        this.stmt.setString(2, enty.getStatus());
        this.stmt.setInt(3, enty.getId());
        stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
    }

    @Override
    public void remove(Movement enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public List<Movement> findAll() throws SQLException, Exception {
        List<Movement> lista = new ArrayList<>();
        String sql = "SELECT CO.ID AS ID, EQ.EQUIPAMENTO AS EQUIPAMENTO, CO.TAG AS TAG, ST.NOME AS SETOR, LG.USERNAME AS TECNICO, CO.DATA AS DATA,  CO.STATUS AS STATUS FROM bd_estoque.conserto CO  LEFT JOIN bd_estoque.equipamento EQ ON CO.FK_EQUIPE = EQ.ID LEFT JOIN bd_estoque.setor ST ON CO.FK_SET = ST.ID LEFT JOIN bd_estoque.user LG ON CO.FK_TECNIC = LG.ID ORDER BY CO.ID ASC";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (rs.next()) {
            Movement obj = new Movement();
            obj.setId(rs.getInt("ID"));
            obj.getEquipment().setNome(rs.getString("EQUIPAMENTO"));
            obj.getTag().setSequence(rs.getString("TAG"));
            obj.getSector().setnome(rs.getString("SETOR"));
            obj.getUser().setNomeLogin(rs.getString("TECNICO"));
            obj.setDate(rs.getDate("DATA"));
            obj.setStatus(rs.getString("STATUS"));
            lista.add(obj);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    @Override
    public Movement findById(Movement enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
