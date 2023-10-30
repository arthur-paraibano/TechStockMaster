package TI.Suporte.Modal.obj;

import java.sql.Date;

public class Soli_Compas {
    private Integer id;
    private String equipamento;
    private Double quantidade;
    private String setor;
    private String solicitante;
    private String descricao;
    private String tecnico;
    private Date data;
    private String status;

    public Soli_Compas() {
    }

    public Soli_Compas(Integer id, String equipamento, Double quantidade, String setor, String solicitante, String descricao, String tecnico, Date data, String status) {
        this.id = id;
        this.equipamento = equipamento;
        this.quantidade = quantidade;
        this.setor = setor;
        this.solicitante = solicitante;
        this.descricao = descricao;
        this.tecnico = tecnico;
        this.data = data;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CompasSL_OBJ{" +
                "id=" + id +
                ", equipamento='" + equipamento + '\'' +
                ", quantidade=" + quantidade +
                ", setor='" + setor + '\'' +
                ", solicitante='" + solicitante + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tecnico='" + tecnico + '\'' +
                ", data=" + data +
                '}';
    }
}
