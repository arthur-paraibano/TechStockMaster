package view.extra.stock;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.EquipmentController;
import model.entities.Equipment;
import model.table.StockTableModel;
import util.EnterToTab;
import util.TableUtil;

public class StockView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Clean;
        private javax.swing.JButton jButt_Close;
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_PesquisarOS;
        private javax.swing.JComboBox<String> jCBox_PesquisarOS;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable jTable1;

        private EquipmentController controller;
        private List<Equipment> equip;

        public StockView() {
                initComponents();
                setModal(true);
                this.controller = new EquipmentController();
                this.equip = new ArrayList<>();
                this.initMetodos();
        }

        private void initMetodos() {
                this.loadEquip();
                this.loadTable();
                AutoCompleteDecorator.decorate(jCBox_PesquisarOS);
                EnterToTab.add(jCBox_PesquisarOS);
                jCBox_PesquisarOS.requestFocus();
                jButt_PesquisarOS.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        lookFor();
                                }
                        }
                });
        }

        private void lookFor() {
                if (jCBox_PesquisarOS.getSelectedIndex() >= 0) {
                        String nome = jCBox_PesquisarOS.getSelectedItem().toString();
                        StockTableModel model = new StockTableModel(equip);
                        jTable1.setModel(model);
                        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        jTable1.getTableHeader().setReorderingAllowed(false);
                        TableUtil.hide(jTable1, 0);

                        for (int row = 0; row < jTable1.getRowCount(); row++) {
                                Object value = jTable1.getValueAt(row, 2);
                                if (value != null && value.toString().equals(nome)) {
                                        jTable1.setRowSelectionInterval(row, row);
                                        break;
                                }
                        }
                }
        }

        private void Clean() {
                jCBox_PesquisarOS.setSelectedIndex(0);
                this.loadTable();
        }

        private void Close() {
                this.dispose();
        }

        private void loadEquip() {
                this.equip = this.controller.findAll();
                jCBox_PesquisarOS.removeAllItems();
                for (Equipment equip : equip) {
                        jCBox_PesquisarOS.addItem(equip.getNome());
                }
        }

        private void loadTable() {
                StockTableModel model = new StockTableModel(this.controller.findAll());
                jTable1.setModel(model);
                jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jTable1.getTableHeader().setReorderingAllowed(false);
                TableUtil.hide(jTable1, 0);

                jTable1.getColumnModel().getColumn(1).setResizable(false);
                jTable1.getColumnModel().getColumn(2).setResizable(false);
                jTable1.getColumnModel().getColumn(3).setResizable(false); 
                jTable1.getColumnModel().getColumn(4).setResizable(false); 
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jButt_Exit = new javax.swing.JButton();
                jLabel5 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jButt_Clean = new javax.swing.JButton();
                jButt_Close = new javax.swing.JButton();
                jLabel7 = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                jCBox_PesquisarOS = new javax.swing.JComboBox<>();
                jButt_PesquisarOS = new javax.swing.JButton();

                setTitle("Estoque");
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

                jButt_Exit.setIcon(new javax.swing.ImageIcon(
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
                jLabel5.setText("Estoque de Materiais");

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

                jButt_Clean.setText("Limpar");
                jButt_Clean.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_CleanActionPerformed(evt);
                        }
                });

                jButt_Close.setText("Sair");
                jButt_Close.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_CloseActionPerformed(evt);
                        }
                });

                jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel7.setText("Informações");

                jLabel8.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel8.setText("pesquisa:");

                jCBox_PesquisarOS.setModel(
                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                jButt_PesquisarOS.setIcon(new javax.swing.ImageIcon(
                                Objects.requireNonNull(getClass().getResource("/resources/pesquisar.png"))));
                jButt_PesquisarOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_PesquisarOS.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_PesquisarOSActionPerformed(evt);
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
                                                                                                                .addComponent(jButt_Clean,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                144,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(52, 52, 52)
                                                                                                                .addComponent(jButt_Close,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                144,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(226, 226,
                                                                                                                                226))))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(26, 26, 26)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(48, 48, 48)
                                                                                                                                .addComponent(jCBox_PesquisarOS,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                634,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(jLabel8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                258,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(jButt_PesquisarOS))
                                                                                .addComponent(jLabel7,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                258,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE)));
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
                                                                .addComponent(jLabel8,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jCBox_PesquisarOS,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButt_PesquisarOS))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel7,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                263,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jButt_Close,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButt_Clean,
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
        }

        private void jButt_CleanActionPerformed(java.awt.event.ActionEvent evt) {
                this.Clean();
        }

        private void jButt_CloseActionPerformed(java.awt.event.ActionEvent evt) {
                this.Close();
        }

        private void jButt_ExitActionPerformed(java.awt.event.ActionEvent evt) {
                this.Close();
        }

        private void jButt_PesquisarOSActionPerformed(java.awt.event.ActionEvent evt) {
                this.lookFor();
        }
}
