package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import imp.GenericDao;
import model.entities.Feedback;
import util.Session;
import util.base.BaseDados;

public class FeedbackDAO implements GenericDao<Feedback> {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public void add(Feedback enty) throws SQLException, Exception {
        String sql = "INSERT INTO bd_estoque.feedback (FK_USERID, DATA, STATUS, DESCRICAO) VALUES ( ?, ?, ?, ?)";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setInt(1, Session.getUser().getId());
        this.stmt.setDate(2, enty.getDate());
        this.stmt.setString(3, enty.getStatus());
        this.stmt.setString(4, enty.getComment());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
    }

    @Override
    public void update(Feedback enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(Feedback enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public List<Feedback> findAll() throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Feedback findById(Feedback enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

}
