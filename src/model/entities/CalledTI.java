package model.entities;

import java.sql.Date;

public class CalledTI {

    private int id;
    private Sector setor;
    private User tecnico;
    private Date date;
    private String descricao;

    public CalledTI() {
        this.setor = new Sector();
        this.tecnico = new User();
    }

    public CalledTI(int id, Sector setor, User tecnico, Date date, String descricao) {
        this.id = id;
        this.setor = setor;
        this.tecnico = tecnico;
        this.date = date;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sector getSetor() {
        return setor;
    }

    public void setSetor(Sector setor) {
        this.setor = setor;
    }

    public User getTecnico() {
        return tecnico;
    }

    public void setTecnico(User tecnico) {
        this.tecnico = tecnico;
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

}
