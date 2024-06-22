package view.user;

import java.awt.Color;
import java.util.Objects;

import javax.swing.ListSelectionModel;

import controller.UserController;
import model.entities.User;
import model.table.UserTableModel;
import util.Message;
import util.Session;
import util.TableUtil;

public class ManangerUserView extends javax.swing.JDialog {

    private javax.swing.JButton jButt_Add;
    private javax.swing.JButton jButt_Close;
    private javax.swing.JButton jButt_Bloquear;
    private javax.swing.JButton jButt_Remove;
    private javax.swing.JButton jButt_Update;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

    private UserController controller;

    public ManangerUserView() {
        initComponents();
        setModal(true);
        this.controller = new UserController();
        this.loadTable();
    }

    private void add() {
        UserView vew = new UserView();
        vew.setVisible(true);
        this.loadTable();
    }

    private void alterar() {
        if (jTable1.getSelectedRow() >= 0) {
            int idUser = Integer.parseInt(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
            User user = this.controller.findById(idUser);

            if (!user.getNomeLogin().equals("ADM")) {
                UserView vew = new UserView(user);
                vew.setVisible(true);
                this.loadTable();
            } else {
                Message.errorX(null, "Não é possível alterar o usuário administrador!!");
            }

        } else {
            Message.errorX(rootPane, "Por favor, selecione um item!");
        }
    }

    private void bloquear() {
        if (jTable1.getSelectedRow() >= 0) {
            String userName = Session.getUser().getNomeLogin();
            if (userName.equals("ADM")) {
                int idUser = Integer.parseInt(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
                User user = this.controller.findById(idUser);

                controller.bloquear(user);
                this.loadTable();
            } else {
                Message.erroTrist(null, "Seu usuário não tem permisão!!!");
            }

        } else {
            Message.errorX(rootPane, "Por favor, selecione um item!");
        }
    }

    private void fechar() {
        this.dispose();

    }

    private void loadTable() {
        UserTableModel model = new UserTableModel(this.controller.findAll());
        this.configTable(model);
    }

    private void configTable(UserTableModel model) {
        jTable1.setModel(model);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        TableUtil.hide(jTable1, 0);

        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(5).setResizable(false);

        jTable1.getColumnModel().getColumn(1).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jButt_Remove = new javax.swing.JButton();
        jButt_Bloquear = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButt_Add = new javax.swing.JButton();
        jButt_Update = new javax.swing.JButton();
        jButt_Close = new javax.swing.JButton();

        jTable1.setBackground(Color.white);

        setMinimumSize(new java.awt.Dimension(800, 580));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButt_Remove.setText("Bloquear");
        jButt_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButt_RemoveActionPerformed(evt);
            }
        });

        jButt_Bloquear.setIcon(
                new javax.swing.ImageIcon(
                        Objects.requireNonNull(getClass().getResource("/resources/exit.png"))));
        jButt_Bloquear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Bloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButt_ExitActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Gerenciar Usuários");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        {},
                        {},
                        {},
                        {}
                },
                new String[] {

                }));
        jScrollPane1.setViewportView(jTable1);

        jButt_Add.setText("Cadastrar");
        jButt_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButt_AddActionPerformed(evt);
            }
        });

        jButt_Update.setText("Alterar");
        jButt_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButt_UpdateActionPerformed(evt);
            }
        });

        jButt_Close.setText("Sair");
        jButt_Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButt_CloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jButt_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 144,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButt_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 144,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButt_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 144,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButt_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 144,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                .createSequentialGroup()
                                                .addComponent(jButt_Bloquear, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        22,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                .createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 779,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButt_Bloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButt_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButt_Close, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButt_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButt_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(20, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>

    private void jButt_ExitActionPerformed(java.awt.event.ActionEvent evt) {
        this.fechar();
    }

    private void jButt_RemoveActionPerformed(java.awt.event.ActionEvent evt) {
        this.bloquear();
    }

    private void jButt_AddActionPerformed(java.awt.event.ActionEvent evt) {
        this.add();
    }

    private void jButt_UpdateActionPerformed(java.awt.event.ActionEvent evt) {
        this.alterar();
    }

    private void jButt_CloseActionPerformed(java.awt.event.ActionEvent evt) {
        this.fechar();
    }

}