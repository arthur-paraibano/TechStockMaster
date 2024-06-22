package model.entities;

public class User {
    private int id;
    private String noemCompleto;
    private String gmailHotmail;
    private String nomeLogin;
    private String senha;
    private String acessoModulo;
    private String tipoUsuario;
    private boolean passwordTemp;
    private String bloqueador;

    public User() {
    }

    public User(int id, String noemCompleto, String nomeLogin, String acessoModulo, String tipoUsuario,String gmailHotmail, boolean passwordTemp) {
        this.id = id;
        this.noemCompleto = noemCompleto;
        this.nomeLogin = nomeLogin;
        this.acessoModulo = acessoModulo;
        this.tipoUsuario = tipoUsuario;
        this.gmailHotmail = gmailHotmail;
    }

    public User(int id, String noemCompleto, String nomeLogin, String acessoModulo, String tipoUsuario, String bloqueador) {
        this.id = id;
        this.noemCompleto = noemCompleto;
        this.nomeLogin = nomeLogin;
        this.acessoModulo = acessoModulo;
        this.tipoUsuario = tipoUsuario;
        this.bloqueador = bloqueador;
    }

    public User(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    public User(int id,String noemCompleto, String gmailHotmail, String nomeLogin, String senha, String acessoModulo,
            String tipoUsuario) {
        this.noemCompleto = noemCompleto;
        this.gmailHotmail = gmailHotmail;
        this.nomeLogin = nomeLogin;
        this.senha = senha;
        this.acessoModulo = acessoModulo;
        this.tipoUsuario = tipoUsuario;
        this.id = id;
    }

    public String getNoemCompleto() {
        return noemCompleto;
    }

    public void setNoemCompleto(String noemCompleto) {
        this.noemCompleto = noemCompleto;
    }

    public String getGmailHotmail() {
        return gmailHotmail;
    }

    public void setGmailHotmail(String gmailHotmail) {
        this.gmailHotmail = gmailHotmail;
    }

    public String getNomeLogin() {
        return nomeLogin;
    }

    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAcessoModulo() {
        return acessoModulo;
    }

    public void setAcessoModulo(String acessoModulo) {
        this.acessoModulo = acessoModulo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPasswordTemp() {
        return passwordTemp;
    }

    public void setPasswordTemp(boolean passwordTemp) {
        this.passwordTemp = passwordTemp;
    }

    public String getBloqueador() {
        return bloqueador;
    }

    public void setBloqueador(String bloqueador) {
        this.bloqueador = bloqueador;
    }
}
