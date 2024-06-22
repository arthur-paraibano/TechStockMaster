package controller;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import model.dao.UserDAO;
import model.entities.User;
import util.Encrypt;
import util.Message;
import util.Session;
import util.Token;
import util.ValidGmail;
import view.ViewMain;
import view.login.Loading;
import view.login.LoginView;
import view.login.RedefinePassword;

public class UserController {

    // instanciar o dao
    private final UserDAO dao;

    public UserController() {
        this.dao = new UserDAO();
    }

    public List<User> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: 'findAll' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public void bloquear(User user) {
        try {
            if (!user.getNomeLogin().equals("ADM")) {
                dao.remove(user);
                Message.sucess(null, "Usuario Bloqueado!!!");
            } else {
                Message.errorX(null, "Não é possível bloquear o usuário administrador!!");
            }
        } catch (Exception e) {
            Message.errorX(null, "Erro ao bloquear: 'bloquear(user)' " + e.getMessage() + e.getClass().getName());
        }
    }

    public User findByUserName(User enty) {
        try {
            return dao.findById(enty);
        } catch (Exception e) {
            Message.errorX(null, "Erro ao BUSCAR USUARIO: 'findByUserName' " + e.getMessage() + e.getClass().getName());
            return null;
        }
    }

    /***
     * Verificação inicial do login no banco de dados.
     */
    public void login(User user, LoginView loginView, JPasswordField jTexF_Senha) {
        try {
            if (!dao.loginCheck(user)) {
                if (dao.login(user)) {
                    user = findByUserName(user);
                    if (dao.checkPassword(user)) {
                        jTexF_Senha.setText("");
                        RedefinePassword tela = new RedefinePassword(user.getNomeLogin());
                        tela.setVisible(true);

                    } else {
                        Session.setUser(user);
                        ViewMain telaView = new ViewMain();
                        telaView.setVisible(true);
                        loginView.dispose();
                    }

                } else {
                    Message.erroTrist(loginView.getParent(), "Parece que você digitou o Usuário ou Senha incorreto!");
                }
            } else {
                Message.erroTrist(loginView.getParent(), "Usuário bloqueado, contate o ADM do sistema!");
            }

        } catch (Exception e) {
            Message.errorX(loginView.getParent(),
                    "Erro ao verificar credenciais: 'findByUserName' " + e.getMessage() + e.getClass().getName());
            e.printStackTrace();
        }
    }

    /***
     * Redefinir senha 'RedefinePassord'.
     */
    public void redefinePassord(String name, String primeiraSenha, String segundaSenha,
            RedefinePassword redefinePassword, JPasswordField jPass_PrimSenha) {
        try {
            if (primeiraSenha.equals(segundaSenha)) {
                User user = new User();
                user.setNomeLogin(name);
                user.setSenha(Encrypt.encriptografat(segundaSenha));

                dao.updatePassword(user);
                redefinePassword.dispose();
            } else {
                Message.erroTrist(redefinePassword.getParent(),
                        "Por gentileza digite a mesma senha nos dois campos!!!");
                jPass_PrimSenha.grabFocus();
            }
        } catch (Exception e) {
            Message.erroTrist(redefinePassword.getParent(),
                    "Erro ao redefinir senha: 'redefinePassord' " + e.getMessage() + e.getClass().getName());
            jPass_PrimSenha.grabFocus();
        }
    }

    public void checkGmail(User user, Window windowToClose) {
        try {
            if (user.getGmailHotmail().equalsIgnoreCase(dao.getGmail(user))) {
                Loading loading = new Loading();
                Thread emailThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String token = Token.generator();
                            ShippingGmail envio = new ShippingGmail();
                            boolean emailSent = envio.envioGmail(user.getGmailHotmail(), token);
                            UserDAO dao = new UserDAO();
                            boolean usuario = dao.tokenUpdate(user, token);
                            loading.setVisible(false);

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (emailSent && usuario) {
                                        Message.sucess(null,
                                                "Enviado para " + user.getGmailHotmail()
                                                        + "\n\n Verifique a caixa de spam");
                                    } else {
                                        Message.erroTrist(null, "Falha no envio de email");
                                    }
                                }
                            });
                        } catch (Exception e) {
                            Message.erroTrist(null, "Erro ao enviar o Email!");
                        }
                    }
                });
                emailThread.start();
                loading.setVisible(true);
            } else {
                Message.errorX(null, "Gmail não cadastrado!!!");
            }
        } catch (Exception e) {
            Message.fatal(null,
                    "Erro ao verificar GMAIL no Banco de dados \nContate o ADM do sistema " + e.getMessage());
        }
    };

    /***
     * Metodo que valida dos compos e pedi pra registrar no banco de dados.
     */
    public boolean addOrUpdate(User user) {
        // Method to register user with encryption
        try {
            if (user.getNoemCompleto().isEmpty() || user.getGmailHotmail().isEmpty() || user.getNomeLogin().isEmpty()
                    || user.getSenha().isEmpty()
                    || user.getAcessoModulo().trim().isEmpty() || user.getTipoUsuario().trim().isEmpty()) {
                Message.errorX(null, "Preencha todos os campos");
            } else {
                String novaSenha = (Encrypt.encriptografat(user.getSenha()));
                user.setSenha(novaSenha);

                if (ValidGmail.isValidEmail(user.getGmailHotmail())) {
                    user.setGmailHotmail(user.getGmailHotmail());
                    if (user.getAcessoModulo().equals("SIM") && user.getTipoUsuario().equals("COMUM")) {
                        Message.errorX(null, "Acesso total \n" + "Permitido para Técnicos e Administradores!");
                    } else {
                        if (user.getId() == 0 ? this.dao.toCheckUser(user) : this.dao.toCheckUserUpdate(user)) {
                            Message.errorX(null, "Usuario já cadastrado!!!");
                        } else if (user.getId() == 0 ? this.dao.toCheckGmailHotmail(user.getGmailHotmail())
                                : this.dao.toCheckGmailHotmailUpdate(user.getId(), user.getGmailHotmail())) {
                            Message.errorX(null, "Gmail Já existente!!");
                        } else {
                            if (user.getId() == 0) {
                                this.dao.add(user);
                                Message.sucess(null, "Dados salvos com sucesso!");
                                return true;
                            } else {
                                this.dao.update(user);
                                Message.sucess(null, "Usuario alterado!!!");
                                return true;
                            }

                        }
                    }
                } else {
                    Message.erroTrist(null, "Por favor, insira um endereço de e-mail válido!!!");
                }
            }
            return false;

        } catch (Exception e) {
            Message.errorX(null, "Erro ao cadastrar o novo usuário!\nError: " + e.getMessage());
            return false;
        }

    }

    public User findById(int idUser) {
        try {
            return this.dao.findById(idUser);
        } catch (Exception e) {
            Message.errorX(null, "Erro ao buscar o usuário!\nError: " + e.getMessage());
            return null;
        }
    }

}