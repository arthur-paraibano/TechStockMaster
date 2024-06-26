package view.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.UserController;
import model.entities.User;
import util.EnterToTab;
import util.TransformFieldUppcase;

public class UserView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JComboBox<String> jCBox_Acesso;
        private javax.swing.JComboBox<String> jCBox_TipoUser;
        private javax.swing.JTextField jTexF_Gmail;
        private javax.swing.JTextField jTexF_NomeCompleto;
        private javax.swing.JTextField jTexF_Senha;
        private javax.swing.JTextField jTexF_Usename;
        private javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        private javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        private javax.swing.JButton jButt_Limpar = new javax.swing.JButton();
        private UserController controller = new UserController();
        private final User userSelect;

        public UserView() {
                initComponents();
                setModal(true);
                this.formatComponets();
                this.userSelect = null;

        }

        public UserView(User user) {
                initComponents();
                setModal(true);
                this.formatComponets();
                this.userSelect = user;
                this.loadForUpdate();
        }

        private void loadForUpdate() {
                jLabel5.setText("Alterar Usuário");
                jTexF_NomeCompleto.setText(userSelect.getNoemCompleto());
                jTexF_Gmail.setText(userSelect.getGmailHotmail());
                jTexF_Usename.setText(userSelect.getNomeLogin());
                jCBox_Acesso.setSelectedItem(userSelect.getAcessoModulo());
                jCBox_TipoUser.setSelectedItem(userSelect.getTipoUsuario());
                jButt_Limpar.setVisible(false);
        }

        private void formatComponets() {
                jTexF_NomeCompleto.setDocument(new TransformFieldUppcase(50));
                jTexF_Gmail.setDocument(new TransformFieldUppcase(50));
                jTexF_Usename.setDocument(new TransformFieldUppcase(50));

                EnterToTab.add(jTexF_NomeCompleto);
                EnterToTab.add(jTexF_Gmail);
                EnterToTab.add(jTexF_Usename);
                EnterToTab.add(jCBox_Acesso);
                EnterToTab.add(jCBox_TipoUser);
                EnterToTab.add(jTexF_Senha);

                AutoCompleteDecorator.decorate(jCBox_Acesso);
                AutoCompleteDecorator.decorate(jCBox_TipoUser);

                jTexF_NomeCompleto.requestFocus();
        }

        private void confirmar() {
                // Obtenha o texto dos campos e verifique se eles estão vazios
                String nomeCompleto = jTexF_NomeCompleto.getText().toUpperCase();
                String gmail = jTexF_Gmail.getText().toUpperCase();
                String username = jTexF_Usename.getText().toUpperCase();
                String senha = jTexF_Senha.getText().toUpperCase();
                String acesso = String.valueOf(jCBox_Acesso.getSelectedItem()).toUpperCase();
                String tipo = String.valueOf(jCBox_TipoUser.getSelectedItem()).toUpperCase();
                User object = new User((userSelect == null ? 0 : userSelect.getId()), nomeCompleto, gmail, username,
                                senha, acesso, tipo);
                boolean value = controller.addOrUpdate(object);
                if (value) {
                        if (this.userSelect == null) {
                                limparCampos();
                        } else {
                                this.dispose();
                        }
                }

        }

        private void limparCampos() {
                jTexF_Gmail.setText("");
                jTexF_NomeCompleto.setText("");
                jTexF_Senha.setText("12345678");
                jTexF_Usename.setText("");
                jCBox_Acesso.setSelectedIndex(0);
                jCBox_TipoUser.setSelectedIndex(0);
                jTexF_NomeCompleto.requestFocus();
        }

        private void initComponents() {
                javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
                javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
                jTexF_NomeCompleto = new javax.swing.JTextField();
                javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
                jTexF_Usename = new javax.swing.JTextField();
                jTexF_Senha = new javax.swing.JTextField();
                jTexF_Gmail = new javax.swing.JTextField();
                javax.swing.JLabel jLabel4 = new javax.swing.JLabel();

                jButt_Exit = new javax.swing.JButton();
                jButt_Salvar = new javax.swing.JButton();
                javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
                jCBox_Acesso = new javax.swing.JComboBox<>();
                javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
                jCBox_TipoUser = new javax.swing.JComboBox<>();

                setMinimumSize(new java.awt.Dimension(800, 580));
                setResizable(false);
                setType(java.awt.Window.Type.UTILITY);
                // Torna a tela sempre visível
                setAlwaysOnTop(true);
                // Desabilita a opção de mover a tela
                setUndecorated(true);
                addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                                // Torna a tela sempre visível
                                setAlwaysOnTop(false);
                        }
                });

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));
                jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", Font.BOLD, 12));
                jLabel1.setText("Nome de Usuario:");
                jTexF_NomeCompleto.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", Font.BOLD, 12));
                jLabel2.setText("Nome completo:");
                jTexF_Usename.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", Font.BOLD, 12));
                jLabel3.setText("Senha: ( - temporaria - ) ");
                jTexF_Senha.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jTexF_Senha.setText("12345678");
                jTexF_Gmail.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jLabel4.setFont(new java.awt.Font("Perpetua Titling MT", Font.BOLD, 12));
                jLabel4.setText("Informe o @Gmail/@hotmail:");
                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", Font.BOLD, 26));
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Cadastro de Usuários");

                jButt_Limpar.setText("Limpar");
                jButt_Limpar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Limpar();
                        }
                });

                jButt_Exit.setIcon(
                                new javax.swing.ImageIcon(
                                                Objects.requireNonNull(getClass().getResource("/resources/exit.png"))));
                jButt_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_Exit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Exit();
                        }
                });

                jButt_Salvar.setText("Salvar");
                jButt_Salvar.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        actionPerformed_Salvar();
                                }
                        }
                });
                jButt_Salvar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Salvar();
                        }
                });

                jLabel6.setFont(new java.awt.Font("Perpetua Titling MT", Font.BOLD, 12));
                jLabel6.setText("Acesso total? ");
                jCBox_Acesso.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jCBox_Acesso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Sim", "Não" }));
                jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", Font.BOLD, 12));
                jLabel7.setText("Tipo de Usuario? ");
                jCBox_TipoUser.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jCBox_TipoUser
                                .setModel(new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "", "Comum", "Técnico", "Admin" }));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(jPanel1Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout
                                                .createSequentialGroup().addGap(26, 26, 26).addGroup(jPanel1Layout
                                                                .createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                258,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jTexF_Gmail,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                350,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                258,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                false)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                258,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jTexF_Senha,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                267,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jCBox_TipoUser,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                133,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel7,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                258,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jCBox_Acesso,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                133,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel6,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                258,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addComponent(jTexF_NomeCompleto,
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                701,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                258,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jTexF_Usename,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                267,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(73, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                .createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                .addComponent(jButt_Limpar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                100,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(346, 346, 346))
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                .addComponent(jButt_Exit,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                22,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addContainerGap())
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                .addComponent(jButt_Salvar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                144,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(324, 324,
                                                                                                                324)))));
                jPanel1Layout.setVerticalGroup(jPanel1Layout
                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
                                                .addComponent(jButt_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTexF_NomeCompleto,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(24, 24, 24)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTexF_Gmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jLabel1,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTexF_Usename,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addGap(26, 26, 26)
                                                                                .addComponent(jLabel6,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jCBox_Acesso,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                36,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jTexF_Senha,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel7,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jCBox_TipoUser,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                36,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36,
                                                                Short.MAX_VALUE)
                                                .addComponent(jButt_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButt_Limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                                                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                                                jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pack();
                setLocationRelativeTo(null);
        }

        private void actionPerformed_Exit() {
                dispose();
        }

        private void actionPerformed_Limpar() {
                jTexF_NomeCompleto.grabFocus();
                limparCampos();
        }

        private void actionPerformed_Salvar() {
                this.confirmar();
        }
}
