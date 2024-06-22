package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import imp.GenericDao;
import model.entities.Tag;
import util.base.BaseDados;

public class TagDao implements GenericDao<Tag> {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public void add(Tag enty) throws SQLException, Exception {
        String sql = "INSERT INTO bd_estoque.tag (ABREV_TAG, DESC_TAG, DATA) VALUES(?,?,?)";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getAbreviacao());
        this.stmt.setString(2, enty.getType());
        this.stmt.setDate(3, (Date) enty.getDate());
        this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt);
    }

    @Override
    public void update(Tag enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(Tag enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    /**
     * Meto puxa no banco de dados todos os dados da tabela tag
     */
    @Override
    public List<Tag> findAll() throws SQLException, Exception {
        List<Tag> list = new ArrayList<>();
        String sql = "SELECT ID, ABREV_TAG, DESC_TAG, DATA FROM bd_estoque.tag ORDER BY DESC_TAG ASC";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        while (this.rs.next()) {
            Tag obj = new Tag();
            obj.setId(this.rs.getLong("ID"));
            obj.setAbreviacao(this.rs.getString("ABREV_TAG"));
            obj.setType(this.rs.getString("DESC_TAG"));
            obj.setDate(this.rs.getDate("DATA"));
            list.add(obj);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    public List<Tag> findAllTab() throws SQLException, Exception {
        List<Tag> lista = new ArrayList<>();
        this.con = BaseDados.getConnection();
        String sql = "SELECT ID, ABREV_TAG, DESC_TAG, DATA FROM bd_estoque.tag";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (rs.next()) {
            Tag obj = new Tag();
            obj.setId(rs.getLong("ID"));
            obj.setAbreviacao(rs.getString("ABREV_TAG"));
            obj.setType(rs.getString("DESC_TAG"));
            obj.setDate(rs.getDate("DATA"));

            lista.add(obj);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    @Override
    public Tag findById(Tag enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    /**
     * Metodo que retona o sequencial anterior, o sequencial novo, o id e a
     * sequencia de uma nova TAG gerada .
     * A descricao da TAG ja deve ter sido usada na tabela de equipamento pelo menos
     * uma vez.
     * caso seja uma TAG nunca usada, ele retornara NULL.
     **/
    public Tag next(String description) throws SQLException, Exception {
        Tag obj = null;
        this.con = BaseDados.getConnection();
        String sql = "SELECT T.ID AS IDTAG,concat(ABREV_TAG,'-',TAG_SEQ) as ULTIMA_TAG, concat(ABREV_TAG,'-',LPAD(TAG_SEQ+1,3,0)) AS NOVA_TAG, LPAD(TAG_SEQ+1,3,0) AS PROX_SEQ FROM bd_estoque.equipamento LEFT JOIN  bd_estoque.tag T ON FK_TAG = T.ID WHERE DESC_TAG = ?  ORDER BY PROX_SEQ DESC LIMIT 1";
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, description);
        this.rs = this.stmt.executeQuery();

        if (rs.next()) {
            obj = new Tag();
            Long id = this.rs.getLong("IDTAG");
            String ultimaTag = rs.getString("ULTIMA_TAG");
            String novaTag = rs.getString("NOVA_TAG");
            String sequence = rs.getString("PROX_SEQ");
            obj.setId(id);
            obj.setAntiga(ultimaTag);
            obj.setNova(novaTag);
            obj.setSequence(sequence);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return obj;
    }

    /**
     * 
     * Metodo que retona o sequencial anterior, o sequencial novo, o id e a
     * sequencia de uma nova TAG .
     * Deve ser chamado caso a TAG nao tenha sido usada na tabela de equipamento.
     * 
     **/
    public Tag first(String description) throws SQLException, Exception {
        Tag obj = null;
        this.con = BaseDados.getConnection();
        String sql = "SELECT ID AS IDTAG,concat(ABREV_TAG,'-000') as ULTIMA_TAG, concat(ABREV_TAG,'-',LPAD(1,3,0)) AS NOVA_TAG, LPAD(1,3,0) AS PROX_SEQ FROM bd_estoque.tag  WHERE DESC_TAG = ?  ORDER BY PROX_SEQ DESC LIMIT 1";
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, description);
        this.rs = this.stmt.executeQuery();

        if (rs.next()) {
            obj = new Tag();
            Long id = this.rs.getLong("IDTAG");
            String ultimaTag = rs.getString("ULTIMA_TAG");
            String novaTag = rs.getString("NOVA_TAG");
            String sequence = rs.getString("PROX_SEQ");
            obj.setId(id);
            obj.setAntiga(ultimaTag);
            obj.setNova(novaTag);
            obj.setSequence(sequence);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return obj;
    }

    public List<String> getTagDetails(String item) throws SQLException, Exception {
        List<String> list = new ArrayList<>();

        this.con = BaseDados.getConnection();
        String sql = "SELECT ABREV_TAG, DESC_TAG FROM bd_estoque.tag WHERE DESC_TAG =?";
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, item);
        this.rs = this.stmt.executeQuery();

        while (this.rs.next()) {
            // Equipment dto = new Equipment();
            // sete um valor para um item que nao existe na memoria
            // dto.setTag(new Tag());
            String value = rs.getString("ABREV_TAG");
            list.add(value);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }
}
