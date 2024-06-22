package view.sector;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.SectorController;
import controller.SupervisorController;
import model.entities.Sector;
import model.entities.Supervisor;
import util.EnterToTab;
import util.Message;
import util.TransformFieldUppcase;

public class SectorView extends JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Limpar;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JComboBox<String> jCBox_Referencia;
        private javax.swing.JTextField jTexF_NomeSetor;
        private javax.swing.JComboBox<String> jCBox_Supervisor;
        private static JDialog parentDialog;
        private javax.swing.JLabel jLabel5 = new JLabel();

        private final SupervisorController controller;
        private List<Supervisor> supervisors;
        private final SectorController sectorController;
        private final Sector sector;

        public SectorView() {
                initComponents();
                setModal(true);
                this.sector = null;
                this.formatComponets();

                this.controller = new SupervisorController();
                this.sectorController = new SectorController();
                this.loadJcombobox();
        }

        public SectorView(Sector sector) {
                initComponents();
                setModal(true);
                this.sector = sector;
                this.formatComponets();

                this.controller = new SupervisorController();
                this.sectorController = new SectorController();
                this.loadJcombobox();
                this.loadForUpdate();
        }

        private void loadForUpdate() {
                jLabel5.setText("Alterar Setor");
                jTexF_NomeSetor.setText(sector.getnome());
                jCBox_Supervisor.setSelectedItem(sector.getSupervisor().getName());
                jCBox_Referencia.setSelectedItem(sector.getLocacao());
                jButt_Limpar.setVisible(false);
                jTexF_NomeSetor.setEditable(false);
        }

        private void loadJcombobox() {
                this.supervisors = controller.findAll();
                jCBox_Supervisor.removeAllItems();
                for (Supervisor supervisor : supervisors) {
                        jCBox_Supervisor.addItem(supervisor.getName());
                }
        }

        public void confirmar() {
                String setor = jTexF_NomeSetor.getText().toUpperCase();

                if (!setor.isEmpty() && jCBox_Supervisor.getSelectedIndex() >= 0
                                && jCBox_Referencia.getSelectedIndex() != 0) {
                        String referencia = (String) jCBox_Referencia.getSelectedItem();
                        Supervisor supervisor = this.supervisors.get(jCBox_Supervisor.getSelectedIndex());
                        Sector obj = new Sector((sector == null ? 0 : sector.getId()), setor, supervisor, referencia);
                        boolean value = sectorController.add(obj);
                        if (value) {
                                if (this.sector == null) {
                                        this.limparCampo();
                                } else {
                                        this.dispose();
                                }
                        }
                } else {
                        Message.errorX(parentDialog, "Por gentileza preencha todos os campos!!!");
                }
        }

        private void limparCampo() {
                jTexF_NomeSetor.setText("");
                jCBox_Supervisor.setSelectedIndex(0);
                jCBox_Referencia.setSelectedIndex(0);
                jTexF_NomeSetor.requestFocus();
        }

        public static void setParentDialog(JDialog parentDialog) {
                SectorView.parentDialog = parentDialog;
        }

        private void formatComponets() {
                AutoCompleteDecorator.decorate(jCBox_Supervisor);
                jTexF_NomeSetor.setDocument(new TransformFieldUppcase(50));

                EnterToTab.add(jTexF_NomeSetor);
                EnterToTab.add(jCBox_Supervisor);
                jTexF_NomeSetor.requestFocus();
        }

        private void initComponents() {
                JPanel jPanel1 = new JPanel();
                JLabel jLabel1 = new JLabel();
                jCBox_Supervisor = new javax.swing.JComboBox<>();
                JLabel jLabel2 = new JLabel();
                jButt_Salvar = new javax.swing.JButton();
                jButt_Limpar = new javax.swing.JButton();
                jCBox_Referencia = new javax.swing.JComboBox<>();
                JLabel jLabel3 = new JLabel();
                JLabel jLabel4 = new JLabel();
                JLabel jLabel6 = new JLabel();
                JLabel jLabel7 = new JLabel();
                JLabel jLabel8 = new JLabel();
                jTexF_NomeSetor = new javax.swing.JTextField();
                jButt_Exit = new javax.swing.JButton();

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
                jLabel1.setText("Referênte:");
                jCBox_Supervisor.setFont(new java.awt.Font("Perpetua Titling MT", Font.PLAIN, 14));
                jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", Font.BOLD, 12));
                jLabel2.setText("Nome do setor:");

                jButt_Salvar.setText("Salvar");
                jButt_Salvar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Salvar();
                        }
                });
                jButt_Salvar.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        actionPerformed_Salvar();
                                }
                        }
                });

                jButt_Limpar.setText("Limpar");
                jButt_Limpar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Limpar();
                        }
                });
                jButt_Limpar.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        actionPerformed_Limpar();
                                }
                        }
                });

                jCBox_Referencia.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jCBox_Referencia.setModel(
                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { " ", "D'Padua", "Pro-fé", "D'Padua / Pro-fé" }));

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", Font.BOLD, 26));
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Cadastro de Setor");
                jLabel3.setText("Departamento Pessoal: D'Padua");
                jLabel4.setText("Escritorio Agricola: Pro-FÉ");
                jLabel6.setFont(new java.awt.Font("Perpetua Titling MT", Font.BOLD, 12));
                jLabel6.setText("Supervisor(es) do setor:");
                jLabel7.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
                jLabel7.setText("EX:");
                jLabel8.setText("TI: D'Pdua/Pro-FÉ");

                jTexF_NomeSetor.setFont(new java.awt.Font("Perpetua Titling MT", Font.PLAIN, 14));

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

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(26, 26, 26)
                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                false)
                                                                                                                                                                .addComponent(jCBox_Supervisor)
                                                                                                                                                                .addComponent(jLabel2,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                372,
                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                .addComponent(jLabel1,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                .addGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                                                                jPanel1Layout
                                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                                .addComponent(
                                                                                                                                                                                                                                jLabel7)
                                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                                                                                .addGroup(
                                                                                                                                                                                                                                jPanel1Layout
                                                                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                                                                .addComponent(
                                                                                                                                                                                                                                                                jLabel3)
                                                                                                                                                                                                                                                .addComponent(
                                                                                                                                                                                                                                                                jLabel4,
                                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                166,
                                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                                                                .addComponent(
                                                                                                                                                                                                                                                                jLabel8,
                                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                                166,
                                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                                                                .addGap(29, 29,
                                                                                                                                                                                                                                29))
                                                                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addComponent(
                                                                                                                                                                                                                jCBox_Referencia,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                160,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addGap(17, 17, 17)))
                                                                                                                                                                .addComponent(jTexF_NomeSetor,
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING))
                                                                                                                                                .addComponent(jLabel6,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                372,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(316, 316,
                                                                                                                                                316)
                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jButt_Salvar,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                144,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                .addGap(22, 22, 22)
                                                                                                                                                                .addComponent(jButt_Limpar,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                100,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                                                .addGap(0, 334, Short.MAX_VALUE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                .addComponent(jButt_Exit,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                22,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap()));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jButt_Exit,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                22,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(16, 16, 16)
                                                                .addComponent(jLabel5)
                                                                .addGap(34, 34, 34)
                                                                .addComponent(jLabel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTexF_NomeSetor,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                36,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)
                                                                .addComponent(jLabel6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jCBox_Supervisor,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                36,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                25,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jCBox_Referencia,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                36,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel3)
                                                                                .addComponent(jLabel7))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                16,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel8,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                16,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(31, 31, 31)
                                                                .addComponent(jButt_Salvar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                34,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28)
                                                                .addComponent(jButt_Limpar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                31,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(32, 32, 32)));

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

        private void actionPerformed_Salvar() {
                this.confirmar();
        }

        private void actionPerformed_Limpar() {
                limparCampo();
        }

        private void actionPerformed_Exit() {
                dispose();
        }

}
