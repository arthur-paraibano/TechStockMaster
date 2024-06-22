package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import imp.GenericDao;
import model.entities.Equipment;
import util.base.BaseDados;
import util.base.BaseDadosLykos;

public class EquipmentDAO implements GenericDao<Equipment> {

    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    @Override
    public void add(Equipment enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    /***
     * CASO SEJA UM EQUIPAMENTO <- IMPORTANTE
     * Vai salvar no banco de dados as informações
     */
    public Integer addEquip(Equipment enty) throws SQLException, Exception {
        int valor = 1;
        this.con = BaseDados.getConnection();
        String sql = "INSERT INTO bd_estoque.equipamento (CODIGO, EQUIPAMENTO, UND, EQUIP_CHECK, DESCRICAO, STATUS, ID_CODSETOR, FK_TAG, TAG_SEQ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, enty.getCodigo());
        this.stmt.setString(2, enty.getNome());
        this.stmt.setString(3, enty.getAbreviacao_un());
        this.stmt.setInt(4, valor);
        this.stmt.setString(5, enty.getDescricao());
        this.stmt.setString(6, enty.getStatus());
        this.stmt.setInt(7, enty.getSetor().getId());
        this.stmt.setLong(8, enty.getTag().getId());
        this.stmt.setString(9, enty.getTag().getSequence());
        int rowsAffected = stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
        return rowsAffected;
    }

    @Override
    public void update(Equipment enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public Integer updateMate(Equipment enty) throws SQLException, Exception {
        String sql = "UPDATE bd_estoque.equipamento SET DESCRICAO = ? WHERE ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getDescricao());
        this.stmt.setInt(2, enty.getId());
        int rowsAffected = stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
        return rowsAffected;
    }

    public Integer updateEquip(Equipment enty) throws SQLException, Exception {
        String sql = "UPDATE bd_estoque.equipamento SET DESCRICAO = ?, STATUS = ?, ID_CODSETOR = ? WHERE ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getDescricao());
        this.stmt.setString(2, enty.getStatus());
        this.stmt.setInt(3, enty.getSetor().getId());
        this.stmt.setInt(4, enty.getId());
        int rowsAffected = stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
        return rowsAffected;
    }

    @Override
    public void remove(Equipment enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    /***
     * Buscas os EQUIPAMENTOS para setar nos combobox.
     ***/
    @Override
    public List<Equipment> findAll() throws SQLException, Exception {
        List<Equipment> list = new ArrayList<>();

        this.con = BaseDados.getConnection();
        String sql = "SELECT ID, CODIGO, EQUIPAMENTO, UND, SALDO_ATUAL, EQUIP_CHECK FROM bd_estoque.equipamento WHERE EQUIP_CHECK = 0 ORDER BY EQUIPAMENTO ASC";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        while (this.rs.next()) {
            Equipment e = new Equipment();
            e.setId(rs.getInt("ID"));
            e.setCodigo(rs.getString("CODIGO"));
            e.setNome(rs.getString("EQUIPAMENTO"));
            e.setAbreviacao_un(rs.getString("UND"));
            e.setQuantidade(rs.getDouble("SALDO_ATUAL"));
            list.add(e);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    public List<Equipment> findAllEquipments() throws SQLException, Exception {
        List<Equipment> list = new ArrayList<>();

        this.con = BaseDados.getConnection();
        String sql = "SELECT ID, CODIGO, EQUIPAMENTO, UND, SALDO_ATUAL, EQUIP_CHECK FROM bd_estoque.equipamento WHERE EQUIP_CHECK = 1 ORDER BY EQUIPAMENTO ASC";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        while (this.rs.next()) {
            Equipment e = new Equipment();
            e.setId(rs.getInt("ID"));
            e.setCodigo(rs.getString("CODIGO"));
            e.setNome(rs.getString("EQUIPAMENTO"));
            e.setAbreviacao_un(rs.getString("UND"));
            e.setQuantidade(rs.getDouble("SALDO_ATUAL"));
            list.add(e);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    public List<Equipment> findAllGeneral() throws SQLException, Exception {
        List<Equipment> list = new ArrayList<>();

        this.con = BaseDados.getConnection();
        String sql = "SELECT ID, CODIGO, EQUIPAMENTO, UND, SALDO_ATUAL, EQUIP_CHECK FROM bd_estoque.equipamento ORDER BY EQUIPAMENTO ASC";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        while (this.rs.next()) {
            Equipment e = new Equipment();
            e.setId(rs.getInt("ID"));
            e.setCodigo(rs.getString("CODIGO"));
            e.setNome(rs.getString("EQUIPAMENTO"));
            e.setAbreviacao_un(rs.getString("UND"));
            e.setQuantidade(rs.getDouble("SALDO_ATUAL"));
            list.add(e);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    /***
     * Buscas os EQUIPAMENTOS para setar nos combobox GERAL.
     ***/
    public List<Equipment> findAllGeral() throws SQLException, Exception {
        List<Equipment> list = new ArrayList<>();

        this.con = BaseDados.getConnection();
        String sql = "SELECT ID, ID_KERY, CODIGO, DESCRICAO, ABREVIACAO_UM, DESCRICAO_UM FROM bd_estoque.equipamento_geral ORDER BY DESCRICAO ASC";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        while (this.rs.next()) {
            Equipment e = new Equipment();
            e.setCodigo(rs.getString("CODIGO"));
            e.setNome(rs.getString("DESCRICAO"));
            e.setAbreviacao_un(rs.getString("ABREVIACAO_UM"));
            list.add(e);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    public List<Equipment> findAllTab() throws SQLException, Exception {
        List<Equipment> lista = new ArrayList<>();
        this.con = BaseDados.getConnection();
        String sql = "SELECT ED.ID AS ID, CODIGO, EQUIPAMENTO, UND, STATUS, S.NOME AS SETOR, concat(ABREV_TAG,'-',LPAD(TAG_SEQ+1,3,0)) AS NOVA_TAG FROM bd_estoque.equipamento ED LEFT JOIN  bd_estoque.tag T ON FK_TAG = T.ID LEFT JOIN bd_estoque.setor S ON ID_CODSETOR = S.ID";
        this.stmt = this.con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();

        while (rs.next()) {
            Equipment obj = new Equipment();
            obj.setId(rs.getInt("ID"));
            obj.setCodigo(rs.getString("CODIGO"));
            obj.setNome(rs.getString("EQUIPAMENTO"));
            obj.setAbreviacao_un(rs.getString("UND"));
            obj.setStatus(rs.getString("STATUS"));
            obj.getSetor().setnome(rs.getString("SETOR"));
            obj.getTag().setSequence(rs.getString("NOVA_TAG"));
            lista.add(obj);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return lista;
    }

    public List<Equipment> getEquipDetails(Equipment enty) throws SQLException, Exception {
        List<Equipment> list = new ArrayList<>();

        this.con = BaseDados.getConnection();
        String sql = "SELECT CODIGO, ABREVIACAO_UM FROM bd_estoque.equipamento_geral WHERE DESCRICAO = ?";
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, enty.getNome());
        this.rs = this.stmt.executeQuery();
        if (this.rs.next()) {
            Equipment equipment = new Equipment();
            equipment.setCodigo(rs.getString("CODIGO"));
            equipment.setAbreviacao_un(rs.getString("ABREVIACAO_UM"));
            list.add(equipment);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    public List<Equipment> getEquipMat(Equipment enty) throws SQLException, Exception {
        List<Equipment> list = new ArrayList<>();

        this.con = BaseDados.getConnection();
        String sql = "SELECT CODIGO, EQUIPAMENTO, UND FROM bd_estoque.equipamento WHERE EQUIPAMENTO = ?";
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, enty.getNome());
        this.rs = this.stmt.executeQuery();
        if (this.rs.next()) {
            Equipment equipment = new Equipment();
            equipment.setCodigo(rs.getString("CODIGO"));
            equipment.setAbreviacao_un(rs.getString("UND"));
            list.add(equipment);
        }
        BaseDados.closeConnection(con, stmt, rs);
        return list;
    }

    @Override
    public Equipment findById(Equipment enty) throws SQLException, Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public Equipment findById(int idEquip) throws SQLException, Exception {
        Equipment object = null;
        String sql = "SELECT ED.ID AS ID, CODIGO, EQUIPAMENTO, UND, DESCRICAO, STATUS, S.NOME AS SETOR, DESC_TAG, ABREV_TAG, concat(ABREV_TAG,'-',LPAD(TAG_SEQ, 3,0)) AS TAG FROM bd_estoque.equipamento ED LEFT JOIN  bd_estoque.tag T ON FK_TAG = T.ID  LEFT JOIN bd_estoque.setor S ON ID_CODSETOR = S.ID WHERE ED.ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, idEquip);
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object = new Equipment();
            object.setId(rs.getInt("ID"));
            object.setCodigo(rs.getString("CODIGO"));
            object.setNome(rs.getString("EQUIPAMENTO"));
            object.setAbreviacao_un(rs.getString("UND"));
            object.setDescricao(rs.getString("DESCRICAO"));
            object.setStatus(rs.getString("STATUS"));
            object.getSetor().setnome(rs.getString("SETOR"));
            object.getTag().setType(rs.getString("DESC_TAG"));
            object.getTag().setAbreviacao(rs.getString("ABREV_TAG"));
            object.getTag().setNova(rs.getString("TAG"));
        }
        return object;
    }

    public List<Equipment> findByIdEquipm(Equipment item) throws SQLException, Exception {
        List<Equipment> list = new ArrayList<>();

        Equipment object = null;
        String sql = "SELECT ED.ID AS ID, CODIGO, EQUIPAMENTO, UND, DESCRICAO, STATUS, S.NOME AS SETOR, DESC_TAG, ABREV_TAG, concat(ABREV_TAG,'-',LPAD(TAG_SEQ, 3,0)) AS TAG FROM bd_estoque.equipamento ED LEFT JOIN  bd_estoque.tag T ON FK_TAG = T.ID  LEFT JOIN bd_estoque.setor S ON ID_CODSETOR = S.ID WHERE ED.ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setInt(1, item.getId());
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object = new Equipment();
            object.setId(rs.getInt("ID"));
            object.setCodigo(rs.getString("CODIGO"));
            object.setNome(rs.getString("EQUIPAMENTO"));
            object.setAbreviacao_un(rs.getString("UND"));
            object.setDescricao(rs.getString("DESCRICAO"));
            object.setStatus(rs.getString("STATUS"));
            object.getSetor().setnome(rs.getString("SETOR"));
            object.getTag().setType(rs.getString("DESC_TAG"));
            object.getTag().setAbreviacao(rs.getString("ABREV_TAG"));
            object.getTag().setNova(rs.getString("TAG"));
            list.add(object);
        }
        return list;
    }

    public Equipment findByNome(String item) throws SQLException, Exception {
        Equipment object = null;
        String sql = "SELECT ED.ID AS ID, CODIGO, EQUIPAMENTO, UND, DESCRICAO, STATUS, S.NOME AS SETOR, DESC_TAG, ABREV_TAG, concat(ABREV_TAG,'-',LPAD(TAG_SEQ, 3,0)) AS TAG FROM bd_estoque.equipamento ED LEFT JOIN  bd_estoque.tag T ON FK_TAG = T.ID  LEFT JOIN bd_estoque.setor S ON ID_CODSETOR = S.ID WHERE EQUIPAMENTO = ?";
        this.con = BaseDados.getConnection();
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setString(1, item);
        this.rs = this.stmt.executeQuery();

        if (this.rs.next()) {
            object = new Equipment();
            object.setId(rs.getInt("ID"));
            object.setCodigo(rs.getString("CODIGO"));
            object.setNome(rs.getString("EQUIPAMENTO"));
            object.setAbreviacao_un(rs.getString("UND"));
            object.setDescricao(rs.getString("DESCRICAO"));
            object.setStatus(rs.getString("STATUS"));
            object.getSetor().setnome(rs.getString("SETOR"));
            object.getTag().setType(rs.getString("DESC_TAG"));
            object.getTag().setAbreviacao(rs.getString("ABREV_TAG"));
            object.getTag().setNova(rs.getString("TAG"));
        }
        return object;
    }

    /***
     * CASO SEJA UM MATERIAL
     * Vai salvar no banco de dados as informações
     */
    public int registerMaterial(Equipment enty) throws SQLException, Exception {
        int rowsAffected = 0;
        String codigo = String.valueOf(enty.getCodigo());
        this.con = BaseDados.getConnection();
        String sql = "INSERT INTO bd_estoque.equipamento (CODIGO, EQUIPAMENTO, UND, DESCRICAO) VALUES (?, ?, ?, ?)";
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, codigo);
        this.stmt.setString(2, enty.getNome());
        this.stmt.setString(3, enty.getAbreviacao_un());
        this.stmt.setString(4, enty.getDescricao().toUpperCase());
        rowsAffected = this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
        return rowsAffected;
    }

    /***
     * Verifica se o MATERIAL já existe.
     */
    public boolean checkMaterial(Equipment enty) throws SQLException, Exception {
        int equipCheck = 0;
        this.con = BaseDados.getConnection();
        String sql = "SELECT * FROM bd_estoque.equipamento WHERE EQUIPAMENTO = ? AND EQUIP_CHECK = ?";
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getNome());
        this.stmt.setInt(2, equipCheck);
        this.rs = stmt.executeQuery();
        boolean result = this.rs.next();
        return result;
    }

    /***
     * Dados apara atualizar os equipamentos do banco de dados da Lykos para base de
     * dados local.
     ***/
    public Integer equipmentRegistration(Equipment equipment) throws SQLException, Exception {
        this.con = BaseDados.getConnection();
        String sql = "INSERT INTO bd_estoque.equipamento_geral (ID_KERY, CODIGO, DESCRICAO, ABREVIACAO_UM, DESCRICAO_UM) VALUES (?,?,?,?,?)";
        this.stmt = this.con.prepareStatement(sql);
        this.stmt.setLong(1, equipment.getId_kery());
        this.stmt.setString(2, equipment.getCodigo());
        this.stmt.setString(3, equipment.getDescricao());
        this.stmt.setString(4, equipment.getAbreviacao_un());
        this.stmt.setString(5, equipment.getDescricao_un());
        int rowsAffected = this.stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, null);
        return rowsAffected;
    }

    public List<Equipment> lista() throws SQLException, Exception {
        List<Equipment> list = new ArrayList<>();
        this.con = BaseDadosLykos.getConnection();
        String sequencia = ultimoSequencia();
        String sql = "SELECT CAST(ID AS SIGNED) AS ID_KERY, CODIGO, DESCRICAO, ID_UM, ABREVIACAO_UM, DESCRICAO_UM FROM integracao.view_material WHERE ID > ? ORDER BY ID ASC";
        this.stmt = Objects.requireNonNull(con).prepareStatement(sql);
        this.stmt.setString(1, sequencia);
        this.rs = this.stmt.executeQuery();
        while (rs.next()) {
            Equipment e = new Equipment();
            e.setId_kery(rs.getLong("ID_KERY"));
            e.setCodigo(rs.getString("CODIGO"));
            e.setDescricao(rs.getString("DESCRICAO"));
            e.setAbreviacao_un(rs.getString("ABREVIACAO_UM"));
            e.setDescricao_un(rs.getString("DESCRICAO_UM"));
            list.add(e);
        }
        BaseDadosLykos.closeConnection(con, stmt, rs);
        return list;
    }

    public String ultimoSequencia() throws SQLException, Exception {
        String id_kery = null;
        assert con != null;
        String sql = "SELECT ID_KERY FROM bd_estoque.equipamento_geral order by  ID_KERY desc limit 1";
        this.stmt = con.prepareStatement(sql);
        this.rs = this.stmt.executeQuery();
        if (rs.next()) {
            id_kery = String.valueOf(rs.getInt("ID_KERY"));
        }
        return id_kery;
    }

    public Double getSaldo(Equipment enty) throws SQLException, Exception {
        this.con = BaseDados.getConnection();
        String sql = "SELECT * FROM bd_estoque.equipamento WHERE EQUIPAMENTO = ?";
        this.stmt = con.prepareStatement(sql);
        this.stmt.setString(1, enty.getNome());
        this.rs = stmt.executeQuery();
        Double result = this.rs.next() ? this.rs.getDouble("SALDO_ATUAL") : 0.0;
        return result;
    }

    public Integer updateSaldo(Equipment enty) throws SQLException, Exception {
        String sql = "UPDATE bd_estoque.equipamento SET SALDO_ATUAL = ? WHERE ID = ?";
        this.con = BaseDados.getConnection();
        this.stmt = con.prepareStatement(sql);
        this.stmt.setDouble(1, enty.getQuantidade());
        this.stmt.setInt(2, enty.getId());

        int rowsAffected = stmt.executeUpdate();
        BaseDados.closeConnection(con, stmt, rs);
        return rowsAffected;
    }
}
