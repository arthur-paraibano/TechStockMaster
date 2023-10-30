package TI.Suporte.Modal.obj;

import java.sql.Date;

public class Equipamentos_Ent_Est {
    private Integer id;
    private String nome_equipamento;
    private Double quantidade;
    private Date date;
    private String descricao;
    private double quantidadeEstoque;

    public Equipamentos_Ent_Est() {
    }

    public Equipamentos_Ent_Est(Integer id, String nome_equipamento, Double quantidade, Date date, String descricao, double quantidadeEstoque) {
        this.id = id;
        this.nome_equipamento = nome_equipamento;
        this.quantidade = quantidade;
        this.date = date;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
