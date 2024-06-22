package view.movement;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import model.entities.Movement;
import model.table.ProhibitetableModel;
import util.Message;

public class ManagerProhibitedView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Close;
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_ToRemove;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTable1;

        private List<Movement> movement;

        public ManagerProhibitedView(List<Movement> movement) {
                initComponents();
                setModal(true);
                this.movement = movement;
                this.loadTable();
        }

        private void loadTable() {
                ProhibitetableModel model = new ProhibitetableModel(movement);
                jTable1.setModel(model);
                jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jTable1.getTableHeader().setReorderingAllowed(false);

                jTable1.getColumnModel().getColumn(0).setResizable(false);
                jTable1.getColumnModel().getColumn(1).setResizable(false);
                jTable1.getColumnModel().getColumn(2).setResizable(false); 
        }

        private void toRemove() {
                if (jTable1.getSelectedRow() >= 0) {
                        Movement select = movement.get(jTable1.getSelectedRow());

                        if (JOptionPane.showConfirmDialog(rootPane,
                                        "Deseja deletar material da lista " + select.getEquipment().getNome() + "?", "Deletar?",
                                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                                movement.remove(select);
                                Message.sucess(null, "Material removido com sucesso!");
                                this.loadTable();
                        }
                } else {
                        Message.errorX(rootPane, "Por favor, selecione um item!");
                }
        }

        private void close() {
                this.dispose();
        }

        private void initComponents() {
                jPanel1 = new javax.swing.JPanel();
                jButt_Exit = new javax.swing.JButton();
                jLabel5 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jButt_ToRemove = new javax.swing.JButton();
                jButt_Close = new javax.swing.JButton();

                setMinimumSize(new java.awt.Dimension(800, 580));
                setResizable(false);
                setType(java.awt.Window.Type.UTILITY);

                setUndecorated(true);

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26)); // NOI18N
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Item(ns) Selecionados");

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

                jButt_ToRemove.setText("Remover");
                jButt_ToRemove.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_ToRemoveActionPerformed(evt);
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
                                                                                                                .addContainerGap())
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jButt_ToRemove,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                144,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(52, 52, 52)
                                                                                                                .addComponent(jButt_Close,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                144,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(226, 226,
                                                                                                                                226)))));
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
                                                                                .addComponent(jButt_Close,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButt_ToRemove,
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

        private void jButt_ToRemoveActionPerformed(java.awt.event.ActionEvent evt) {
                this.toRemove();
        }

        private void jButt_CloseActionPerformed(java.awt.event.ActionEvent evt) {
                this.close();
        }

        private void jButt_ExitActionPerformed(java.awt.event.ActionEvent evt) {
                this.close();
        }
}
