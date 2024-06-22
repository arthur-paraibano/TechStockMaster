package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import imp.GenericDao;
import model.entities.CalledTI;
import util.base.BaseDados;

public class CalledTIDAO implements GenericDao<CalledTI> {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public void add(CalledTI enty) throws SQLException, Exception {

        String sql = "INSERT INTO bd_estoque.chamados_ti  (DATA, DESCRICAO, FK_CODLOGIN, FK_CODSETOR)  VALUES(?,?,?,?);";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setDate(1, new Date(enty.getDate().getTime()));
        this.stmt.setString(2, enty.getDescricao());
        this.stmt.setInt(3, enty.getTecnico().getId());
        this.stmt.setInt(4, enty.getSetor().getId());
        this.stmt.execute();

        BaseDados.closeConnection(this.con, this.stmt, this.rs);
    }

    @Override
    public void update(CalledTI enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(CalledTI enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public List<CalledTI> findAll() throws SQLException, Exception {
        List<CalledTI> lista = new ArrayList<>();
        String sql = "SELECT CH.ID AS ID, ST.NOME AS SETOR, LG.USERNAME AS TECNICO, CH.DESCRICAO, CH.DATA FROM bd_estoque.chamados_ti CH LEFT JOIN bd_estoque.setor ST ON CH.FK_CODSETOR = ST.ID LEFT JOIN bd_estoque.user LG ON CH.FK_CODLOGIN = LG.ID ORDER BY CH.FK_CODLOGIN ASC";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        while (rs.next()) {
            CalledTI obj = new CalledTI();
            obj.setId(rs.getInt("ID"));
            obj.getSetor().setnome(rs.getString("SETOR"));
            obj.getTecnico().setNomeLogin(rs.getString("TECNICO"));
            obj.setDescricao(rs.getString("DESCRICAO"));
            obj.setDate(rs.getDate("DATA"));
            lista.add(obj);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    @Override
    public CalledTI findById(CalledTI enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
