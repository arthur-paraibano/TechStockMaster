package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import imp.GenericDao;
import model.entities.User;
import util.Encrypt;
import util.base.BaseDados;

public class UserDAO implements GenericDao<User> {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    /***
     * Dados para inserir um novo usuario.
     ***/
    @Override
    public void add(User enty) throws SQLException, Exception {

        String sql = "INSERT INTO bd_estoque.user (FULL_NAME, GMAIL, USERNAME, PASSWORD, FULL_ACCESS, USER_TYPE) VALUES (?,?,?,?,?,?)";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getNoemCompleto());
        this.stmt.setString(2, enty.getGmailHotmail());
        this.stmt.setString(3, enty.getNomeLogin());
        this.stmt.setString(4, enty.getSenha());
        this.stmt.setString(5, enty.getAcessoModulo());
        this.stmt.setString(6, enty.getTipoUsuario());
        this.stmt.executeUpdate();

        BaseDados.closeConnection(con, stmt);
    }

    @Override
    public void update(User enty) throws SQLException, Exception {
        String sql = "UPDATE bd_estoque.user  SET  FULL_NAME =?, GMAIL=?, USERNAME=?, PASSWORD=?, FULL_ACCESS=?, USER_TYPE=? WHERE ID=?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getNoemCompleto());
        this.stmt.setString(2, enty.getGmailHotmail());
        this.stmt.setString(3, enty.getNomeLogin());
        this.stmt.setString(4, enty.getSenha());
        this.stmt.setString(5, enty.getAcessoModulo());
        this.stmt.setString(6, enty.getTipoUsuario());
        this.stmt.setInt(7, enty.getId());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt);

    }

    @Override
    public void remove(User enty) throws SQLException, Exception {
        int valor = 1;
        String sql = "UPDATE bd_estoque.user SET BLOCKED = ? WHERE ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setInt(1, valor);
        this.stmt.setInt(2, enty.getId());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt);
    }

    /***
     * Metodo para popular a tabela da tela 'ManangerUserView'
     */
    @Override
    public List<User> findAll() throws SQLException, Exception {
        List<User> lista = new ArrayList<>();
        String sql = "SELECT ID, FULL_NAME, USERNAME, FULL_ACCESS, USER_TYPE, BLOCKED FROM bd_estoque.user WHERE USERNAME <> 'ADM' ORDER BY ID ASC";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (rs.next()) {
            lista.add(new User(
                    rs.getInt("ID"),
                    rs.getString("FULL_NAME"),
                    rs.getString("USERNAME"),
                    rs.getString("FULL_ACCESS"),
                    rs.getString("USER_TYPE"),
                    rs.getString("BLOCKED")));
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    @Override
    public User findById(User enty) throws SQLException, Exception {
        User object = null;
        String sql = "SELECT * FROM bd_estoque.user where USERNAME = ? ";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, enty.getNomeLogin());
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object = new User(
                    this.rs.getInt("ID"),
                    this.rs.getString("FULL_NAME"),
                    this.rs.getString("USERNAME"),
                    this.rs.getString("FULL_ACCESS"),
                    this.rs.getString("USER_TYPE"),
                    this.rs.getString("GMAIL"),
                    this.rs.getBoolean("TEMPORARY_PASSWORD"));
        }
        return object;
    }


    public User findById(Integer id) throws SQLException, Exception {
        User object = null;
        String sql = "SELECT * FROM bd_estoque.user where id = ? ";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, id);
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object = new User(
                    this.rs.getInt("ID"),
                    this.rs.getString("FULL_NAME"),
                    this.rs.getString("USERNAME"),
                    this.rs.getString("FULL_ACCESS"),
                    this.rs.getString("USER_TYPE"),
                    this.rs.getString("GMAIL"),
                    this.rs.getBoolean("TEMPORARY_PASSWORD"));
        }
        return object;
    }

    public boolean loginCheck(User user) throws SQLException, Exception {
        boolean check = false;
        String sql = "SELECT BLOCKED FROM bd_estoque.user WHERE USERNAME = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, user.getNomeLogin());
        this.rs = this.stmt.executeQuery();
        if (rs.next()) {
            String temporaryPassword = rs.getString("BLOCKED");
            if (temporaryPassword.equals("1")) {
                check = true;
            }
        }
        BaseDados.closeConnection(con, stmt, rs);
        return check;
    }

    public boolean login(User user) throws SQLException, Exception {
        String sql = "SELECT * FROM bd_estoque.user WHERE USERNAME = ? and PASSWORD = ? ";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, user.getNomeLogin());
        this.stmt.setString(2, user.getSenha());
        this.rs = this.stmt.executeQuery();
        boolean check = rs.next();
        BaseDados.closeConnection(con, stmt, rs);
        return check;
    }

    public boolean tokenUpdate(User user, String newPassword) throws SQLException, Exception {
        String temporaryPassword = "1";
        String novaSenha = (Encrypt.encriptografat(newPassword));
        boolean chek = false;

        String sql = "UPDATE bd_estoque.user SET PASSWORD = ?, TEMPORARY_PASSWORD = ? WHERE GMAIL = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, novaSenha);
        this.stmt.setString(2, temporaryPassword);
        this.stmt.setString(3, user.getGmailHotmail());
        this.stmt.executeUpdate();
        chek = true;
        BaseDados.closeConnection(con, stmt, null);
        return chek;
    }

    public boolean checkPassword(User user) throws SQLException, Exception {
        boolean check = false;
        String sql = "SELECT TEMPORARY_PASSWORD FROM bd_estoque.user WHERE USERNAME = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, user.getNomeLogin());
        this.rs = this.stmt.executeQuery();
        if (rs.next()) {
            String temporaryPassword = rs.getString("TEMPORARY_PASSWORD");
            if (temporaryPassword.equals("1")) {
                check = true;
            }
        }
        BaseDados.closeConnection(con, stmt, rs);
        return check;
    }

    public void updatePassword(User user) throws SQLException, Exception {
        String temporaryPassword = "0";
        String sql = "UPDATE bd_estoque.user SET PASSWORD = ?, TEMPORARY_PASSWORD = ? WHERE USERNAME = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, user.getSenha());
        this.stmt.setString(2, temporaryPassword);
        this.stmt.setString(3, user.getNomeLogin());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, null);
    }

    public String getGmail(User user) throws SQLException, Exception {
        String sql = "SELECT * FROM bd_estoque.user WHERE GMAIL = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, user.getGmailHotmail());
        this.rs = this.stmt.executeQuery();
        String collum = "";
        if (rs.next()) {
            collum = rs.getString("GMAIL");
        }
        BaseDados.closeConnection(con, stmt, rs);
        return collum;
    }

    /***
     * Verificar se o usuario existe no Banco de dados com Id diferente.
     ***/
    public boolean toCheckUser(User user) throws SQLException, Exception {
        String sql = "SELECT * FROM bd_estoque.user WHERE USERNAME = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, user.getNomeLogin());
        this.rs = this.stmt.executeQuery();
        boolean result = rs.next();
        BaseDados.closeConnection(con, stmt, rs);
        return result;
    }

   
   public boolean toCheckUserUpdate(User user) throws SQLException, Exception {
       String sql = "SELECT * FROM bd_estoque.user WHERE ID <> ? AND USERNAME = ?";
       this.con = BaseDados.getConnection();
       this.stmt = con.prepareStatement(sql);
       this.stmt.setInt(1, user.getId());
       this.stmt.setString(2, user.getNomeLogin());
       this.rs = this.stmt.executeQuery();
       boolean result = rs.next();
       BaseDados.closeConnection(con, stmt, rs);
       return result;
   }

    /***
     * Verificar se o Gmail/Hotmail existe no Banco de dados.
     ***/
    public boolean toCheckGmailHotmail(String gmail) throws SQLException, Exception {
        String sql = "SELECT * FROM bd_estoque.user WHERE GMAIL = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, gmail);
        this.rs = this.stmt.executeQuery();
        boolean reseult = this.rs.next();
        BaseDados.closeConnection(con, stmt, rs);
        return reseult;
    }

    public boolean toCheckGmailHotmailUpdate(int id, String gmail) throws SQLException, Exception {
        String sql = "SELECT * FROM bd_estoque.user WHERE ID <> ? AND  GMAIL = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setInt(1, id);
        this.stmt.setString(2, gmail);
        this.rs = this.stmt.executeQuery();
        boolean reseult = this.rs.next();
        BaseDados.closeConnection(con, stmt, rs);
        return reseult;
    }

}
