package TI.Suporte.Modal.obj;


import java.sql.Date;

public class ConcertosEq {
    private Integer id;
    private String nomeDoEquipamento;
    private String modeloReferancia;
    private String Tecnico;
    private Date data;
    private String setor;
    private String descricao;
    private String status;

    public ConcertosEq() {
    }

    public ConcertosEq(Integer id, String nomeDoEquipamento, String modeloReferancia, String tecnico, Date data, String setor, String descricao, String status) {
        this.id = id;
        this.nomeDoEquipamento = nomeDoEquipamento;
        this.modeloReferancia = modeloReferancia;
        Tecnico = tecnico;
        this.data = data;
        this.setor = setor;
        this.descricao = descricao;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeDoEquipamento() {
        return nomeDoEquipamento;
    }

    public void setNomeDoEquipamento(String nomeDoEquipamento) {
        this.nomeDoEquipamento = nomeDoEquipamento;
    }

    public String getModeloReferancia() {
        return modeloReferancia;
    }

    public void setModeloReferancia(String modeloReferancia) {
        this.modeloReferancia = modeloReferancia;
    }

    public String getTecnico() {
        return Tecnico;
    }

    public void setTecnico(String tecnico) {
        Tecnico = tecnico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
