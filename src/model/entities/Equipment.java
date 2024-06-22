package model.entities;

public class Equipment {
    private int id;
    private Long id_kery;
    private String codigo;
    private String nome;
    private String abreviacao_un;
    private String descricao_un;
    private Double quantidade;
    private String descricao;
    private Sector setor;
    private Tag tag;
    private String status;
    //private Repair repair;

    public Equipment() {
        this.setor = new Sector();
        this.tag = new Tag();
        //this.repair = new Repair();
    }

    public Equipment(String codigo, String nome, String abreviacao_un, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.abreviacao_un = abreviacao_un;
        this.descricao = descricao;
    }

    public Equipment(String nome, String abreviacao_un, Double quantidade) {
        this.nome = nome;
        this.abreviacao_un = abreviacao_un;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getId_kery() {
        return id_kery;
    }

    public void setId_kery(Long id_kery) {
        this.id_kery = id_kery;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAbreviacao_un() {
        return abreviacao_un;
    }

    public void setAbreviacao_un(String abreviacao_un) {
        this.abreviacao_un = abreviacao_un;
    }

    public String getDescricao_un() {
        return descricao_un;
    }

    public void setDescricao_un(String descricao_un) {
        this.descricao_un = descricao_un;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Sector getSetor() {
        return setor;
    }

    public void setSetor(Sector setor) {
        this.setor = setor;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // public Repair getRepair() {
    //     return repair;
    // }

    // public void setRepair(Repair repair) {
    //     this.repair = repair;
    // }

    @Override
    public String toString() {
        return "Equipment [id=" + id + ", id_kery=" + id_kery + ", codigo=" + codigo + ", nome=" + nome
                + ", abreviacao_un=" + abreviacao_un + ", descricao_un=" + descricao_un + ", quantidade=" + quantidade
                + ", descricao=" + descricao + ", setor=" + setor + ", tag=" + tag + ", status=" + status + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Equipment other = (Equipment) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

}
