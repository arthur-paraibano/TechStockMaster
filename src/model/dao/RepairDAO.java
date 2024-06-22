package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import imp.GenericDao;
import model.entities.Repair;
import util.Session;
import util.base.BaseDados;

public class RepairDAO implements GenericDao<Repair> {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public void add(Repair enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void update(Repair enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(Repair enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public List<Repair> findAll() throws SQLException, Exception {
        List<Repair> lista = new ArrayList<>();
        String sql = "SELECT CO.ID AS ID, EQ.EQUIPAMENTO AS EQUIPAMENTO, CO.TAG AS TAG, ST.NOME AS SETOR, LG.USERNAME AS TECNICO, CO.DATA AS DATA,  CO.STATUS AS STATUS FROM bd_estoque.conserto CO  LEFT JOIN bd_estoque.equipamento EQ ON CO.FK_EQUIPE = EQ.ID LEFT JOIN bd_estoque.setor ST ON CO.FK_SET = ST.ID LEFT JOIN bd_estoque.user LG ON CO.FK_TECNIC = LG.ID ORDER BY CO.ID ASC";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (rs.next()) {
            Repair obj = new Repair();
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

    public List<Repair> findAllNome() throws SQLException, Exception {
        List<Repair> list = new ArrayList<>();

        this.con = BaseDados.getConnection();
        String sql = "SELECT CO.ID AS ID, EQ.EQUIPAMENTO AS EQUIPAMENTO FROM bd_estoque.conserto CO  LEFT JOIN bd_estoque.equipamento EQ ON CO.FK_EQUIPE = EQ.ID ORDER BY CO.ID ASC";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        while (this.rs.next()) {
            Repair e = new Repair();
            e.setId(rs.getInt("ID"));
            e.getEquipment().setNome(rs.getString("EQUIPAMENTO"));
            list.add(e);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    public List<Repair> findAllTag() throws SQLException, Exception {
        List<Repair> list = new ArrayList<>();

        this.con = BaseDados.getConnection();
        String sql = "SELECT CO.ID AS ID, CO.TAG AS TAG FROM bd_estoque.conserto CO ORDER BY CO.ID ASC";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        while (this.rs.next()) {
            Repair e = new Repair();
            e.setId(rs.getInt("ID"));
            e.getTag().setSequence(rs.getString("TAG"));
            list.add(e);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    @Override
    public Repair findById(Repair enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Double getRepair(Repair enty) throws SQLException, Exception {
        this.con = BaseDados.getConnection();
        String sql = "SELECT ID, CODIGO, EQUIPAMENTO, CONSERTO FROM bd_estoque.equipamento WHERE ID = ?";
        this.stmt = con.prepareStatement(sql);
        this.stmt.setInt(1, enty.getId());
        this.rs = stmt.executeQuery();
        Double result = this.rs.next() ? this.rs.getDouble("CONSERTO") : 0.0;
        return result;
    }

    public Integer updateRepair(Repair enty) throws SQLException, Exception {
        String sql = "UPDATE bd_estoque.equipamento SET CONSERTO = ? WHERE ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setDouble(1, enty.getAmount());
        this.stmt.setInt(2, enty.getId());
        int rowsAffected = stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
        return rowsAffected;
    }

    public boolean findExist(Repair enty) throws SQLException, Exception {
        String sql = "SELECT CO.ID AS ID, EQ.EQUIPAMENTO AS EQUIPAMENTO, CO.TAG, CO.STATUS FROM bd_estoque.conserto CO LEFT JOIN bd_estoque.equipamento EQ ON CO.FK_EQUIPE = EQ.ID WHERE CO.TAG = ? AND (CO.STATUS = 'AGUARDANDO ENVIO' OR CO.STATUS = 'EM CONSERTO')";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getTag().getSequence());
        rs = stmt.executeQuery();
        boolean equipmentExists = rs.next();
        BaseDados.closeConnection(con, stmt, rs);
        return equipmentExists;
    }

    public Integer addId(Repair enty) throws SQLException, Exception {
        String sql = "INSERT INTO bd_estoque.conserto (FK_EQUIPE, TAG, FK_SET, FK_TECNIC, DATA, DESCRICAO, STATUS) VALUES (?, ?, ?, ?, ?, ?, ?)";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);

        this.stmt.setInt(1, enty.getId());
        this.stmt.setString(2, enty.getTag().getSequence());
        this.stmt.setInt(3, enty.getSector().getId());
        this.stmt.setInt(4, Session.getUser().getId());
        this.stmt.setDate(5, enty.getDate());
        this.stmt.setString(6, enty.getDescription());
        this.stmt.setString(7, enty.getStatus());

        int rowsAffected = stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
        return rowsAffected;
    }

    public Repair findById(Integer id) throws SQLException, Exception {
        Repair object = new Repair();
        String sql = "SELECT CO.ID AS ID, EQ.EQUIPAMENTO AS EQUIPAMENTO, CO.TAG AS TAG, ST.NOME AS SETOR, LG.USERNAME AS TECNICO, CO.DATA AS DATA, CO.DESCRICAO AS DESCRICAO, CO.STATUS AS STATUS FROM bd_estoque.conserto CO  LEFT JOIN bd_estoque.equipamento EQ ON CO.FK_EQUIPE = EQ.ID LEFT JOIN bd_estoque.setor ST ON CO.FK_SET = ST.ID LEFT JOIN bd_estoque.user LG ON CO.FK_TECNIC = LG.ID WHERE CO.ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, id);
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object.setId(rs.getInt("ID"));
            object.getEquipment().setNome(rs.getString("EQUIPAMENTO"));
            object.getTag().setSequence(rs.getString("TAG"));
            object.getSector().setnome(rs.getString("SETOR"));
            object.getUser().setNomeLogin(rs.getString("TECNICO"));
            object.setDescription(rs.getString("DESCRICAO"));
            object.setDate(rs.getDate("DATA"));
            object.setStatus(rs.getString("STATUS"));
        }
        return object;
    }

}
