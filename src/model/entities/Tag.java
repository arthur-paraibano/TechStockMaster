package model.entities;

import java.util.Date;

public class Tag {

    private Long id;
    private String abreviacao;
    private String type;
    private String antiga;
    private String nova;
    private Date date;
    private String sequence;

    public Tag() {
    }

    public Tag(Long id, String abreviacao, String type, String antiga, String nova, Date date) {
        this.id = id;
        this.abreviacao = abreviacao;
        this.type = type;
        this.antiga = antiga;
        this.nova = nova;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }


    public String getAntiga() {
        return antiga;
    }

    public void setAntiga(String antiga) {
        this.antiga = antiga;
    }

    public String getNova() {
        return nova;
    }

    public void setNova(String nova) {
        this.nova = nova;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public void setNome() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNome'");
    }

    

    
}
