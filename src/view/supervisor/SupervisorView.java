package view.supervisor;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

import controller.SupervisorController;
import model.entities.Supervisor;
import util.EnterToTab;
import util.Message;
import util.TransformFieldUppcase;

public class SupervisorView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Limpar;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JTextField jTexF_Nome = new javax.swing.JTextField();

        private SupervisorController controller;
        private final Supervisor supervisor;

        public SupervisorView() {
                initComponents();
                setModal(true);
                this.formatComponets();
                this.controller = new SupervisorController();
                this.supervisor = null;
        }

        public SupervisorView(Supervisor supervisor) {
                initComponents();
                setModal(true);
                this.formatComponets();
                this.controller = new SupervisorController();
                this.supervisor = supervisor;
                loadForUpdate();
        }

        private void loadForUpdate() {
                jLabel5.setText("Alterar supervisor");
                jTexF_Nome.setText(this.supervisor.getName());
                jButt_Limpar.setVisible(false);
        }

        private void add() {
                String nome = jTexF_Nome.getText();
                if (!nome.isEmpty()) {
                        Supervisor object = new Supervisor((supervisor == null ? 0 : supervisor.getId()), nome);
                        boolean value = controller.add(object);
                        if (value) {
                                if (this.supervisor == null) {
                                        limparCampo();
                                } else {
                                        this.dispose();
                                }
                        }
                } else {
                        Message.erroTrist(null, "Informe um nome!!!");
                }
        }

        private void close() {
                this.dispose();
        }

        private void formatComponets() {
                jTexF_Nome.setDocument(new TransformFieldUppcase(50));
                EnterToTab.add(jTexF_Nome);
                jTexF_Nome.requestFocus();
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jButt_Limpar = new javax.swing.JButton();
                jButt_Exit = new javax.swing.JButton();
                jLabel5 = new javax.swing.JLabel();
                jButt_Salvar = new javax.swing.JButton();

                setMinimumSize(new java.awt.Dimension(800, 580));
                setResizable(false);
                setType(java.awt.Window.Type.UTILITY);
                setAlwaysOnTop(true);
                setUndecorated(true);
                addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                                // Torna a tela sempre visível
                                setAlwaysOnTop(false);
                        }
                });

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                jTexF_Nome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel2.setText("Primeiro Nome:");

                jButt_Limpar.setText("Limpar");
                jButt_Limpar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_LimparActionPerformed(evt);
                        }
                });

                jButt_Exit.setIcon(
                                new javax.swing.ImageIcon(
                                                Objects.requireNonNull(getClass().getResource("/resources/exit.png"))));
                jButt_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_Exit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_ExitActionPerformed(evt);
                        }
                });

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26)); // NOI18N
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Cadastro de Supervisor(es)");

                jButt_Salvar.setText("Salvar");
                jButt_Salvar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_SalvarActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(182, Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jButt_Limpar,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                100,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(346, 346,
                                                                                                                                346))
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
                                                                                                                .addComponent(jButt_Salvar,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                144,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(324, 324,
                                                                                                                                324))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(jLabel2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                258,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jTexF_Nome,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                485,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGap(133, 133,
                                                                                                                                133)))));
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
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                182,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTexF_Nome,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                36,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(156, 156, 156)
                                                                .addComponent(jButt_Salvar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                34,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jButt_Limpar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                31,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(26, 26, 26)));

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
                this.close();
        }

        private void jButt_LimparActionPerformed(java.awt.event.ActionEvent evt) {
                this.limparCampo();
        }

        private void jButt_SalvarActionPerformed(java.awt.event.ActionEvent evt) {
                this.add();
        }

        private void limparCampo() {
                jTexF_Nome.setText("");
        }
}
