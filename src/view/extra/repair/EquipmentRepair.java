package view.extra.repair;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.RepairController;
import model.entities.Equipment;
import model.entities.Repair;
import model.entities.Sector;
import model.entities.TypeRepair;
import model.table.RepairTableModel;
import util.EnterToTab;
import util.Message;
import util.Session;
import util.TableUtil;
import util.TransformFieldUppcase;

public class EquipmentRepair extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Limpar;
        private javax.swing.JButton jButt_LookForName;
        private javax.swing.JButton jButt_LookForTag;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JComboBox<String> jCBox_Equipment;
        private javax.swing.JComboBox<String> jCBox_PesquisarName;
        private javax.swing.JComboBox<String> jCBox_PesquisarTag;
        private javax.swing.JComboBox<String> jCBox_Status;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JTabbedPane jTabbedPane1;
        private javax.swing.JTable jTable1;
        private javax.swing.JFormattedTextField jTexF_Data;
        private javax.swing.JTextField jTexF_Setor;
        private javax.swing.JTextField jTexF_Tag;
        private javax.swing.JTextField jTexF_Tecnico;
        private javax.swing.JTextArea jText_Descricao;

        private RepairController controller;
        private List<Equipment> equip;
        private List<Repair> repairs;
        private Repair movemt;

        public EquipmentRepair() {
                initComponents();
                setModal(true);
                this.controller = new RepairController();
                this.equip = new ArrayList<>();
                this.repairs = new ArrayList<>();
                this.movemt = null;
                this.initMetods();
        }

        public EquipmentRepair(Repair mov) {
                initComponents();
                setModal(true);
                this.movemt = mov;
                this.controller = new RepairController();
                this.equip = new ArrayList<>();
                this.repairs = new ArrayList<>();
                this.loadEquip();
                this.initMetodsStatus();
        }

        private void initMetodsStatus() {
                jLabel5.setText("Alterar Descrição / Status de Equipamento");
                jCBox_Equipment.setSelectedItem(this.movemt.getEquipment().getNome());
                jTexF_Tag.setText(this.movemt.getTag().getSequence());
                jTexF_Setor.setText(this.movemt.getSector().getnome());
                jTexF_Tecnico.setText(this.movemt.getUser().getNomeLogin());
                jText_Descricao.setText(this.movemt.getDescription());
                Date date = this.movemt.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                jTexF_Data.setText(dateFormat.format(date));
                jCBox_Status.setSelectedItem(this.movemt.getStatus());

                jCBox_Equipment.setEditable(false);
                jCBox_Equipment.setEnabled(false);
                jTexF_Tag.setEditable(false);
                jTexF_Setor.setEditable(false);
                jTexF_Tecnico.setEditable(false);
                jText_Descricao.setDocument(new TransformFieldUppcase(100));
                jTexF_Data.setEditable(false);
                jTexF_Tecnico.setText(Session.getUser().getNomeLogin());
                jTexF_Data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
                jCBox_Status.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { " ", "AGUARDANDO ENVIO", "EM CONSERTO", "RECEBIDO" }));

                int index = jTabbedPane1.indexOfTab("Históricos");
                if (index != -1) {
                        jTabbedPane1.removeTabAt(index);
                }
                jPanel2.remove(jButt_Limpar);
        }

        private void initMetods() {
                this.loadTable();
                jTexF_Tag.setEditable(false);
                jTexF_Tecnico.setText(Session.getUser().getNomeLogin());
                jText_Descricao.setDocument(new TransformFieldUppcase(100));
                AutoCompleteDecorator.decorate(jCBox_Equipment);
                AutoCompleteDecorator.decorate(jCBox_Status);
                AutoCompleteDecorator.decorate(jCBox_PesquisarName);
                AutoCompleteDecorator.decorate(jCBox_PesquisarTag);
                EnterToTab.add(jCBox_Equipment);
                EnterToTab.add(jCBox_PesquisarName);
                EnterToTab.add(jCBox_PesquisarTag);
                jCBox_Equipment.requestFocus();
                this.loadEquip();
                this.loadRepairNome();
                this.loadRepairTag();
                this.actionJcombox();
                // jTexF_Data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new
                // Date(System.currentTimeMillis())));
        }

        private void add() {
                try {
                        if (jTexF_Data.getText().replaceAll("\\s+", "").replaceAll("/", "").trim().isEmpty()) {
                                Message.erroTrist(null, "Por favor, informe uma data.");
                                jTexF_Data.requestFocus();
                        } else if (jCBox_Equipment.getSelectedIndex() == 0 && jTexF_Tag.getText().trim().equals("")
                                        && jTexF_Setor.getText().trim().equals("")) {
                                Message.errorX(null, "Pro favor selecione um Equipamento!");
                        } else if (jTexF_Tecnico.getText().trim().equals("")) {
                                Message.errorX(null, "O nome do técnico não foi encontrado, contate o ADM do sistema!");
                        } else if (jCBox_Status.getSelectedIndex() == 0) {
                                Message.errorX(null, "Selecione o Status da Manutenção");
                                jCBox_Status.requestFocus();
                        } else {

                                Repair obj = new Repair();

                                int selectedIndex = jCBox_Equipment.getSelectedIndex();
                                Equipment selectedEquip = equip.get(selectedIndex);
                                int id = selectedEquip.getId();
                                String nomeSetor = jTexF_Setor.getText().toLowerCase();
                                Sector sector = this.controller.findByNome(nomeSetor);
                                String dataTexto = jTexF_Data.getText();
                                dataTexto = dataTexto.replaceAll("/", "");
                                SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
                                java.util.Date dataUtil = formato.parse(dataTexto);
                                java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                                String status = jCBox_Status.getSelectedItem().toString();
                                obj.setType(movemt == null ? TypeRepair.IN : TypeRepair.STATUS);
                                obj.setId(movemt == null ? id : movemt.getId());
                                obj.getEquipment().setNome(jCBox_Equipment.getSelectedItem().toString());
                                obj.getTag().setSequence(jTexF_Tag.getText());
                                obj.getSector().setId(sector.getId());
                                obj.setDescription(jText_Descricao.getText().toUpperCase());
                                obj.setDate(dataSql);
                                obj.setStatus(status);

                                boolean valou = controller.addRepair(obj);
                                if (valou) {
                                        if (this.movemt == null) {
                                                this.loadTable();
                                                this.loadRepairNome();
                                                this.loadRepairTag();
                                                this.clear();
                                        } else {
                                                this.dispose();
                                        }
                                }
                        }
                } catch (Exception e) {
                        Message.errorX(null, "Erro no método de adicionar, contate o ADM do sistema.");
                }
        }

        private void close() {
                this.dispose();
        }

        private void clear() {
                jCBox_Equipment.setSelectedIndex(0);
                jTexF_Data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
                jCBox_Status.setSelectedIndex(0);
                jText_Descricao.setText("");
        }

        private void lookByColumn(int columnIndex, JComboBox<String> comboBox) {
                if (comboBox.getSelectedIndex() >= 0) {
                        String valueToFind = comboBox.getSelectedItem().toString();
                        RepairTableModel model = new RepairTableModel(this.controller.findAllRepair());
                        configTable(model);

                        for (int row = 0; row < jTable1.getRowCount(); row++) {
                                Object value = jTable1.getValueAt(row, columnIndex);
                                if (value != null && value.toString().equals(valueToFind)) {
                                        jTable1.setRowSelectionInterval(row, row);
                                        break;
                                }
                        }
                }
        }

        private void lookName() {
                lookByColumn(1, jCBox_PesquisarName);
        }

        private void lookTag() {
                lookByColumn(2, jCBox_PesquisarTag);
        }

        private void loadTable() {
                RepairTableModel model = new RepairTableModel(this.controller.findAllRepair());
                configTable(model);
        }

        private void configTable(RepairTableModel model) {
                jTable1.setModel(model);
                jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jTable1.getTableHeader().setReorderingAllowed(false);
                TableUtil.hide(jTable1, 0);
                TableUtil.showToltip(jTable1, 1);
                TableUtil.showToltip(jTable1, 3);

                jTable1.getColumnModel().getColumn(1).setResizable(false);
                jTable1.getColumnModel().getColumn(2).setResizable(false);
                jTable1.getColumnModel().getColumn(3).setResizable(false);
                jTable1.getColumnModel().getColumn(4).setResizable(false);
                jTable1.getColumnModel().getColumn(5).setResizable(false);
                jTable1.getColumnModel().getColumn(6).setResizable(false);

                jTable1.getColumnModel().getColumn(1).setPreferredWidth(140);
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
                jTable1.getColumnModel().getColumn(3).setPreferredWidth(90);
                jTable1.getColumnModel().getColumn(4).setPreferredWidth(20);
                jTable1.getColumnModel().getColumn(5).setPreferredWidth(10);
                jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        private void loadEquip() {
                this.equip = this.controller.findAllEquipments();
                jCBox_Equipment.removeAllItems();
                for (Equipment equip : equip) {
                        jCBox_Equipment.addItem(equip.getNome());
                }
                loadEquipDados();
        }

        private void loadRepairNome() {
                this.repairs = this.controller.findAllReapirNome();
                jCBox_PesquisarName.removeAllItems();
                for (Repair repairs : repairs) {
                        jCBox_PesquisarName.addItem(repairs.getEquipment().getNome());
                }
                loadEquipDados();
        }

        private void loadRepairTag() {
                this.repairs = this.controller.findAllReapirTag();
                jCBox_PesquisarTag.removeAllItems();
                for (Repair repairs : repairs) {
                        jCBox_PesquisarTag.addItem(repairs.getTag().getSequence());
                }
                loadEquipDados();
        }

        private void loadEquipDados() {
                int selectedIndex = jCBox_Equipment.getSelectedIndex();
                if (selectedIndex != -1) {
                        Equipment selectedEquip = equip.get(selectedIndex);

                        List<Equipment> equip = this.controller.findByIdRepair(selectedEquip);
                        jTexF_Tag.removeAll();
                        jTexF_Setor.removeAll();
                        for (Equipment equipe : equip) {
                                jTexF_Tag.setText(equipe.getTag().getNova());
                                jTexF_Setor.setText(equipe.getSetor().getnome());
                        }
                }
        }

        private void actionJcombox() {
                jCBox_Equipment.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                if (evt.getStateChange() == ItemEvent.SELECTED) {
                                        loadEquipDados();
                                }
                        }
                });
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jLabel5 = new javax.swing.JLabel();
                jButt_Exit = new javax.swing.JButton();
                jTabbedPane1 = new javax.swing.JTabbedPane();
                jPanel2 = new javax.swing.JPanel();
                jCBox_Equipment = new javax.swing.JComboBox<>();
                jLabel3 = new javax.swing.JLabel();
                jTexF_Tag = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jTexF_Tecnico = new javax.swing.JTextField();
                jLabel10 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jText_Descricao = new javax.swing.JTextArea();
                jTexF_Data = new javax.swing.JFormattedTextField();
                jLabel6 = new javax.swing.JLabel();
                jButt_Salvar = new javax.swing.JButton();
                jButt_Limpar = new javax.swing.JButton();
                jTexF_Setor = new javax.swing.JTextField();
                jCBox_Status = new javax.swing.JComboBox<>();
                jLabel15 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jScrollPane3 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jLabel12 = new javax.swing.JLabel();
                jCBox_PesquisarName = new javax.swing.JComboBox<>();
                jLabel11 = new javax.swing.JLabel();
                jButt_LookForName = new javax.swing.JButton();
                jCBox_PesquisarTag = new javax.swing.JComboBox<>();
                jButt_LookForTag = new javax.swing.JButton();
                jLabel13 = new javax.swing.JLabel();
                jLabel14 = new javax.swing.JLabel();

                setTitle("Consertos");
                setBackground(new java.awt.Color(255, 255, 255));
                setMinimumSize(new java.awt.Dimension(300, 400));
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

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26)); // NOI18N
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Conserto Equipamento");

                jButt_Exit.setIcon(
                                new javax.swing.ImageIcon(
                                                Objects.requireNonNull(getClass().getResource("/resources/exit.png"))));
                jButt_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_Exit.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_ExitActionPerformed(evt);
                        }
                });

                jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel3.setText("Nome:");

                jTexF_Tag.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setText("Tag");

                jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel7.setText("Setor:");

                jLabel9.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel9.setText("Técnico:");

                jTexF_Tecnico.setEditable(false);
                jTexF_Tecnico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel10.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel10.setText("Descrição:");

                jText_Descricao.setColumns(20);
                jText_Descricao.setRows(5);
                jScrollPane1.setViewportView(jText_Descricao);

                try {
                        jTexF_Data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                                        new javax.swing.text.MaskFormatter("##/##/####")));
                } catch (java.text.ParseException ex) {
                        ex.printStackTrace();
                }
                jTexF_Data.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                jTexF_Data.setFont(new java.awt.Font("Segoe UI", 0, 14));

                jLabel6.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12));
                jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                jLabel6.setText("Data:");

                jButt_Salvar.setText("Salvar");
                jButt_Salvar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_SalvarActionPerformed(evt);
                        }
                });

                jButt_Limpar.setText("Limpar");
                jButt_Limpar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_LimparActionPerformed(evt);
                        }
                });

                jTexF_Setor.setEditable(false);
                jTexF_Setor.setFont(new java.awt.Font("Segoe UI", 0, 14));

                jCBox_Status.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { " ", "AGUARDANDO ENVIO", "EM CONSERTO" }));

                jLabel15.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12));
                jLabel15.setText("Status:");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel2Layout.createSequentialGroup()
                                                                                                                .addGap(38, 38, 38)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                .createSequentialGroup()
                                                                                                                                                .addComponent(jLabel10,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                148,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGap(113, 113,
                                                                                                                                                                113))
                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                jPanel2Layout.createSequentialGroup()
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                                .addComponent(jLabel7,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                148,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                                .addComponent(jTexF_Setor,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                302,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                                                47,
                                                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                                .addComponent(jLabel9,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                148,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                                .addComponent(jTexF_Tecnico,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                302,
                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                344,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                                                .addGap(43, 43, 43)
                                                                                                                                                                                                                                .addComponent(jLabel6,
                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                92,
                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                                                                                .addComponent(jTexF_Data,
                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                140,
                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                                                                                .addComponent(jLabel15,
                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                66,
                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                                                                                                .addComponent(jCBox_Status,
                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                                                200,
                                                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                                                                                                                .addGap(93, 93, 93))))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel2Layout.createSequentialGroup()
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(jCBox_Equipment,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                546,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jLabel3,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                170,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGap(29, 29, 29)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(jTexF_Tag)
                                                                                                                                .addComponent(jLabel2,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addGap(16, 16, 16))))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(321, 321, 321)
                                                                .addComponent(jButt_Salvar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                144,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jButt_Limpar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                104,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(363, 363, 363)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel2,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jCBox_Equipment,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_Tag,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                36,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(26, 26, 26)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel7,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(43, 43, 43))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel9,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jTexF_Tecnico,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                34,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jTexF_Setor,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                34,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addComponent(jLabel10,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel6,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                30,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jTexF_Data,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                36,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(31, 31, 31)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jCBox_Status,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                37,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel15,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                30,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addComponent(jScrollPane1))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                50, Short.MAX_VALUE)
                                                                .addComponent(jButt_Salvar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                34,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jButt_Limpar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                31,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(12, 12, 12)));

                jTabbedPane1.addTab("Conserto", jPanel2);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                {},
                                                {},
                                                {},
                                                {},
                                                {},
                                                {}
                                },
                                new String[] {

                                }));
                jScrollPane3.setViewportView(jTable1);

                jLabel12.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel12.setText("Informações:");

                jLabel11.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel11.setText("Pesquisa:");

                jButt_LookForName.setIcon(
                                new javax.swing.ImageIcon(
                                                Objects.requireNonNull(
                                                                getClass().getResource("/resources/pesquisar.png"))));
                jButt_LookForName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_LookForName.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_LookForNameActionPerformed(evt);
                        }
                });

                jButt_LookForTag.setIcon(
                                new javax.swing.ImageIcon(
                                                Objects.requireNonNull(
                                                                getClass().getResource("/resources/pesquisar.png"))));
                jButt_LookForTag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_LookForTag.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_LookForTagActionPerformed(evt);
                        }
                });

                jLabel13.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel13.setText("Nome:");

                jLabel14.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel14.setText("Tag:");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel11,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                258,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jScrollPane3)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                                .createParallelGroup(
                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                .addComponent(jLabel12,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                258,
                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                                                false)
                                                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                jPanel3Layout.createSequentialGroup()
                                                                                                                                                                                                .addGap(419, 419,
                                                                                                                                                                                                                419)
                                                                                                                                                                                                .addComponent(jLabel14,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                55,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                                                                .addComponent(jCBox_PesquisarTag,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                200,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                                                                                .addComponent(jButt_LookForTag))
                                                                                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                                                jPanel3Layout.createSequentialGroup()
                                                                                                                                                                                                .addGap(77, 77, 77)
                                                                                                                                                                                                .addComponent(jLabel13,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                55,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                                                                .addComponent(jCBox_PesquisarName,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                543,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addGap(34, 34, 34)
                                                                                                                                                                                                .addComponent(jButt_LookForName))))
                                                                                                                                .addGap(0, 25, Short.MAX_VALUE)))
                                                                                                .addContainerGap()))));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel11,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jCBox_PesquisarName,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                37,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel13,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jButt_LookForName))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jCBox_PesquisarTag,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                37,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jLabel14,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jButt_LookForTag))
                                                                .addGap(21, 21, 21)
                                                                .addComponent(jLabel12,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                272,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(17, Short.MAX_VALUE)));

                jTabbedPane1.addTab("Históricos", jPanel3);

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                .addComponent(jButt_Exit,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                22,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jTabbedPane1))
                                                                .addContainerGap()));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jButt_Exit,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                22,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(8, 8, 8)
                                                                .addComponent(jLabel5)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jTabbedPane1)
                                                                .addContainerGap()));

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

        private void jButt_ExitActionPerformed(java.awt.event.ActionEvent evt) {
                this.close();
        }

        private void jButt_SalvarActionPerformed(java.awt.event.ActionEvent evt) {
                this.add();
        }

        private void jButt_LimparActionPerformed(java.awt.event.ActionEvent evt) {
                this.clear();
        }

        private void jButt_LookForNameActionPerformed(java.awt.event.ActionEvent evt) {
                this.lookName();
        }

        private void jButt_LookForTagActionPerformed(java.awt.event.ActionEvent evt) {
                this.lookTag();
        }
}
