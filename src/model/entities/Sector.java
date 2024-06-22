package model.entities;

public class Sector {

    private int id;
    private String nome;
    private Supervisor supervisor;
    private String locacao;

    public Sector() {
        this.supervisor = new Supervisor();
    }

    public Sector(int id, String nome, Supervisor supervisor, String locacao) {
        this.id = id;
        this.nome = nome;
        this.supervisor = supervisor;
        this.locacao = locacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public String getLocacao() {
        return locacao;
    }

    public void setLocacao(String locacao) {
        this.locacao = locacao;
    }

}
