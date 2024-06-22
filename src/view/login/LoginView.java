package view.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.UserController;
import model.entities.User;
import util.Encrypt;
import util.EnterToTab;

public class LoginView extends javax.swing.JFrame {
        private javax.swing.JButton jButt_Entrar;
        private javax.swing.JButton jButt_RedefinirSenha;
        private javax.swing.JPasswordField jTexF_Senha;
        private javax.swing.JTextField jTexF_Usuario;

        private JPanel grayBackgroundPanel;
        private UserController user;
        private User obj;

        public LoginView() {
                initComponents();
                setIcon();
                this.user = new UserController();
                this.obj = new User();
                EnterToTab.add(jTexF_Usuario);
                EnterToTab.add(jTexF_Senha);
        }

        private void initComponents() {

                JPanel jPanel1 = new JPanel();
                JLabel jLabel1 = new JLabel();
                JLabel jLabel3 = new JLabel();
                JLabel jLabel4 = new JLabel();
                jTexF_Usuario = new javax.swing.JTextField();
                JLabel jLabel5 = new JLabel();
                jTexF_Senha = new javax.swing.JPasswordField();
                jButt_RedefinirSenha = new javax.swing.JButton();
                JLabel jLabel6 = new JLabel();
                jButt_Entrar = new javax.swing.JButton();
                JLabel jLabel7 = new JLabel();
                JPanel jPanel2 = new JPanel();
                JLabel jLabel2 = new JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Login");
                setBackground(new java.awt.Color(204, 204, 204));
                setMinimumSize(new java.awt.Dimension(1100, 620));
                setResizable(false);

                // Painel secundario das cores
                grayBackgroundPanel = new JPanel();
                grayBackgroundPanel.setBackground(new Color(192, 192, 192, 128)); // Cor cinza com transparência
                grayBackgroundPanel.setBounds(0, 0, getWidth(), getHeight()); // Cobrir toda a tela
                grayBackgroundPanel.setVisible(false);
                getContentPane().add(grayBackgroundPanel);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                jLabel1.setBackground(new java.awt.Color(255, 255, 255));
                jLabel1.setForeground(new java.awt.Color(114, 113, 113));
                jLabel1.setText("Por favor digite seu nome de usuário e");
                jLabel3.setBackground(new java.awt.Color(255, 255, 255));
                jLabel3.setForeground(new java.awt.Color(114, 113, 113));
                jLabel3.setText("senha para fazer Login");
                jLabel4.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jLabel4.setText("Usuário");
                jLabel5.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jLabel5.setText("Senha");
                jLabel6.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 10));
                jLabel6.setText("@2023_arthur");
                jLabel7.setFont(new java.awt.Font("Malgun Gothic", Font.BOLD, 26));
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel7.setText("Bem Vindo(a)!");

                jButt_RedefinirSenha.setText("Esqueceu a senha? ");
                jButt_RedefinirSenha.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        forgotPassword();
                                }
                        }
                });
                jButt_RedefinirSenha.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                forgotPassword();
                        }
                });

                jButt_Entrar.setText("Entrar");
                jButt_Entrar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                if (!"".equals(jTexF_Usuario.getText())) {
                                        userLogin();
                                } else {
                                        JOptionPane.showMessageDialog(null, "Informe Usuário e Senha");
                                }
                        }
                });
                jButt_Entrar.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        if (!"".equals(jTexF_Usuario.getText())) {
                                                userLogin();
                                        } else {
                                                JOptionPane.showMessageDialog(null, "Informe Usuário e Senha");
                                        }
                                }
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(jPanel1Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 65,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                .addComponent(jLabel1,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                240,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jLabel3,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                .addContainerGap(
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jButt_RedefinirSenha,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                184,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel1Layout.createSequentialGroup().addGap(0, 152, Short.MAX_VALUE)
                                                                .addComponent(jButt_Entrar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                132,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(152, 152, 152))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jTexF_Usuario)
                                                                .addComponent(jTexF_Senha,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                246, Short.MAX_VALUE)
                                                                .addComponent(jLabel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                90,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                90,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jLabel7,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                316,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(jPanel1Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup().addGap(70, 70, 70)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(31, 31, 31)
                                                                                .addComponent(jLabel1)
                                                                                .addGap(0, 0, 0).addComponent(jLabel3)
                                                                                .addGap(43, 43, 43)
                                                                                .addComponent(jLabel4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                26,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTexF_Usuario,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jLabel5,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                26,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTexF_Senha,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(28, 28, 28)
                                                                                .addComponent(jButt_Entrar,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                32,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jButt_RedefinirSenha)
                                                                                .addGap(33, 33, 33))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel6)))));

                jPanel2.setBackground(new java.awt.Color(204, 204, 204));
                jLabel2.setIcon(
                                new javax.swing.ImageIcon(Objects
                                                .requireNonNull(getClass().getResource("/resources/loginTech.png"))));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel2Layout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(jLabel2)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
                layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE));

                pack();
                setLocationRelativeTo(null);
        }

        private void userLogin() {
                String minusculaUsuario = jTexF_Usuario.getText();
                String usuario = minusculaUsuario.toUpperCase();
                String senha = new String(jTexF_Senha.getPassword()).toUpperCase();

                obj.setNomeLogin(usuario);
                obj.setSenha(Encrypt.encriptografat(senha));
                user.login(obj, this, jTexF_Senha);
        }

        private void forgotPassword() {
                grayBackgroundPanel.setVisible(true);
                // Abre a tela RedefineLogin
                RedefineLogin tela = new RedefineLogin();
                tela.setVisible(true);
                tela.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                // Esconde o fundo cinza quando a tela RedefineLogin for fechada
                                grayBackgroundPanel.setVisible(false);
                        }
                });
        }

        private void setIcon() {
                setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/computador.png")));
        }
}
