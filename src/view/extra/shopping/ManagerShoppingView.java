package view.extra.shopping;

import java.util.Objects;

import javax.swing.ListSelectionModel;

import controller.ShoppingController;
import model.entities.Shopping;
import model.table.ShoppingTableModel;
import util.Message;
import util.TableUtil;

public class ManagerShoppingView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Add;
        private javax.swing.JButton jButt_ChangeStatus;
        private javax.swing.JButton jButt_Close;
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTable1;

        private ShoppingController controller;

        public ManagerShoppingView() {
                initComponents();
                setModal(true);
                this.controller = new ShoppingController();
                this.loadTable();
        }

        private void add() {
                ShoppingView tela = new ShoppingView();
                tela.setVisible(true);
                this.loadTable();
        }

        private void changeStatus() {
                 if (jTable1.getSelectedRow() >= 0) {
                        int idConsrt = Integer.parseInt(String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
                        Shopping equp = this.controller.findAllId(idConsrt);
                        ShoppingView vew = new ShoppingView(equp);
                        vew.setVisible(true);
                        this.loadTable();
                } else {
                        Message.errorX(rootPane, "Por favor, selecione um item!");
                }
        }

        private void close() {
                this.dispose();
        }

        private void loadTable() {
                ShoppingTableModel model = new ShoppingTableModel(this.controller.findAll());
                configTable(model);
        }

        private void configTable(ShoppingTableModel model) {
                jTable1.setModel(model);
                jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jTable1.getTableHeader().setReorderingAllowed(false);
                TableUtil.hide(jTable1, 0);
                TableUtil.showToltip(jTable1, 1);
                TableUtil.showToltip(jTable1, 3);
                TableUtil.showToltip(jTable1, 4);
                TableUtil.showToltip(jTable1, 5);
                TableUtil.showToltip(jTable1, 7);

                jTable1.getColumnModel().getColumn(1).setResizable(false);
                jTable1.getColumnModel().getColumn(2).setResizable(false);
                jTable1.getColumnModel().getColumn(3).setResizable(false);
                jTable1.getColumnModel().getColumn(4).setResizable(false);
                jTable1.getColumnModel().getColumn(5).setResizable(false);
                jTable1.getColumnModel().getColumn(6).setResizable(false);
                jTable1.getColumnModel().getColumn(7).setResizable(false);

                jTable1.getColumnModel().getColumn(1).setPreferredWidth(140);
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
                jTable1.getColumnModel().getColumn(3).setPreferredWidth(90);
                jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
                jTable1.getColumnModel().getColumn(5).setPreferredWidth(90);
                jTable1.getColumnModel().getColumn(6).setPreferredWidth(70);
                jTable1.getColumnModel().getColumn(7).setPreferredWidth(80);
        }

        private void initComponents() {
                jPanel1 = new javax.swing.JPanel();
                jButt_ChangeStatus = new javax.swing.JButton();
                jButt_Exit = new javax.swing.JButton();
                jLabel5 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jButt_Add = new javax.swing.JButton();
                jButt_Close = new javax.swing.JButton();

                setMinimumSize(new java.awt.Dimension(800, 580));
                setResizable(false);
                setType(java.awt.Window.Type.UTILITY);

                setUndecorated(true);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                jButt_ChangeStatus.setText("Alt. Status");
                jButt_ChangeStatus.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_ChangeStatusActionPerformed(evt);
                        }
                });

                jButt_Exit.setIcon(
                                new javax.swing.ImageIcon(
                                                Objects.requireNonNull(getClass().getResource("/resources/exit.png"))));
                jButt_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_Exit.setMaximumSize(new java.awt.Dimension(800, 595));
                jButt_Exit.setMinimumSize(new java.awt.Dimension(800, 595));
                jButt_Exit.setPreferredSize(new java.awt.Dimension(800, 595));
                jButt_Exit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_ExitActionPerformed(evt);
                        }
                });

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26));
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Gerenciar Solicitações de Compras");

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

                jButt_Add.setText("Solicitar");
                jButt_Add.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_AddActionPerformed(evt);
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
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jButt_Add,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                144,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(jButt_ChangeStatus,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                144,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                .addComponent(jButt_Close,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                144,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(170, 170,
                                                                                                                                170))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                791,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addContainerGap())
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jButt_Exit,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                22,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addContainerGap()))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jButt_Exit,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                22,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel5)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButt_ChangeStatus,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButt_Close,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButt_Add,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(44, Short.MAX_VALUE)));

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

        private void jButt_ChangeStatusActionPerformed(java.awt.event.ActionEvent evt) {
                this.changeStatus();
        }

        private void jButt_AddActionPerformed(java.awt.event.ActionEvent evt) {
                this.add();
        }

        private void jButt_CloseActionPerformed(java.awt.event.ActionEvent evt) {
                this.close();
        }

        private void jButt_ExitActionPerformed(java.awt.event.ActionEvent evt) {
                this.close();
        }
}