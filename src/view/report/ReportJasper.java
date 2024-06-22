package view.report;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

import controller.ReportsController;

public class ReportJasper extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Executar;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JList<String> jList1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane2;

        private ReportsController controller;

        public ReportJasper() {
                initComponents();
                setModal(true);
                controller = new ReportsController();
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jButt_Exit = new javax.swing.JButton();
                jLabel5 = new javax.swing.JLabel();
                jButt_Executar = new javax.swing.JButton();
                jScrollPane2 = new javax.swing.JScrollPane();
                jList1 = new javax.swing.JList<>();

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

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26)); // NOI18N
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Relatórios");

                jButt_Executar.setText("Executar");
                jButt_Executar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Executar();
                        }
                });

                jList1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                jList1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
                jList1.setModel(new javax.swing.AbstractListModel<String>() {
                        String[] strings = { "Usuário(s) Cadastrado(s)", "Equipamento(s) Cadastrado(s)",
                                        "Equipamento(s) C/ Tag",
                                        "Setor(es) Cadastrado(s)", "Tag(s) Cadastrada(s)", "Estoque de Material",
                                        "Chamados TI", "Concertos Equipamentos", "Solicitação de Compras TI" };

                        public int getSize() {
                                return strings.length;
                        }

                        public String getElementAt(int i) {
                                return strings[i];
                        }
                });
                jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
                jScrollPane2.setViewportView(jList1);

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 800,
                                                                Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(72, 72, 72)
                                                                .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                657,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jButt_Executar,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                139,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(324, 324,
                                                                                                                                324))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jButt_Exit,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                21,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addContainerGap()))));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jButt_Exit,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                25,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel5)
                                                                .addGap(53, 53, 53)
                                                                .addComponent(jScrollPane2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                375,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(34, 34, 34)
                                                                .addComponent(jButt_Executar)
                                                                .addContainerGap(31, Short.MAX_VALUE)));

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
        }

        private void actionPerformed_Executar() {
                if (jList1.getSelectedValue() == "Usuário(s) Cadastrado(s)") {
                        controller.usuarioCadastrado();
                        actionPerformed_Exit();
                } else if (jList1.getSelectedValue() == "Equipamento(s) Cadastrado(s)") {
                        controller.equipamentoCadastrado();
                        actionPerformed_Exit();
                } else if (jList1.getSelectedValue() == "Equipamento(s) C/ Tag") {
                        controller.equipamentoTag();
                        actionPerformed_Exit();
                } else if (jList1.getSelectedValue() == "Setor(es) Cadastrado(s)") {
                        controller.setorCadastrado();
                        actionPerformed_Exit();
                } else if (jList1.getSelectedValue() == "Tag(s) Cadastrada(s)") {
                        controller.tagCadastradas();
                        actionPerformed_Exit();
                } else if (jList1.getSelectedValue() == "Estoque de Material") {
                        controller.estoque();
                        actionPerformed_Exit();
                } else if (jList1.getSelectedValue() == "Chamados TI") {
                        controller.chamadosTI();
                        actionPerformed_Exit();
                } else if (jList1.getSelectedValue() == "Concertos Equipamentos") {
                        controller.concertos();
                        actionPerformed_Exit();
                } else if (jList1.getSelectedValue() == "Solicitação de Compras TI") {
                        controller.solicitacaoCompras();
                        actionPerformed_Exit();
                }
        }

        private void actionPerformed_Exit() {
                dispose();
        }
}
