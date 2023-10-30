package TI.Suporte.Modal.obj;

import java.sql.Date;

public class Saida_Mat {
    private Integer id;
    private String nome_equipamento;
    private Double quantidade;
    private Date date;
    private String tecnico;

    public Saida_Mat() {
    }

    public Saida_Mat(Integer id, String nome_equipamento, Double quantidade, Date date, String tecnico) {
        this.id = id;
        this.nome_equipamento = nome_equipamento;
        this.quantidade = quantidade;
        this.date = date;
        this.tecnico = tecnico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_equipamento() {
        return nome_equipamento;
    }

    public void setNome_equipamento(String nome_equipamento) {
        this.nome_equipamento = nome_equipamento;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }
}
