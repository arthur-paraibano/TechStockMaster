package view.movement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.EquipmentController;
import controller.SectorController;
import model.entities.Equipment;
import model.entities.Movement;
import model.entities.Sector;
import model.entities.TypeMovement;
import util.EnterToTab;
import util.Message;
import util.NumericFilter;
import util.Session;
import util.TransformFieldUppcase;

public class ProhibitedView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Itens;
        private javax.swing.JButton jButt_Limpar;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JButton jButt_Visualizar;
        private javax.swing.JComboBox<String> jCBox_Pesquisar = new javax.swing.JComboBox<>();;
        private javax.swing.JCheckBox jCheckBox_LykosOs;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JFormattedTextField jTexF_Data;
        private javax.swing.JTextField jTexF_OsLykos;
        private javax.swing.JTextField jTexF_Quantidade;
        private javax.swing.JTextField jTexF_Tecnico;
        private javax.swing.JTextField jTexF_Und;

        private EquipmentController controller;
        private List<Equipment> equip;
        private List<Movement> movement;
        private final SectorController sectorController;

        public ProhibitedView() {
                initComponents();
                setModal(true);
                this.controller = new EquipmentController();
                this.equip = new ArrayList<>();
                this.movement = new ArrayList<>();
                this.sectorController = new SectorController();
                this.initMetodos();
                this.actionJcombox();
        }

        private void initMetodos() {
                this.loadEquip();
                AutoCompleteDecorator.decorate(jCBox_Pesquisar);
                NumericFilter.addNumericFilter(jTexF_Quantidade, 6);
                jTexF_Tecnico.setText(Session.getUser().getNomeLogin());
                jTexF_OsLykos.setEnabled(false);
                EnterToTab.add(jCBox_Pesquisar);
                EnterToTab.add(jTexF_Und);
                EnterToTab.add(jTexF_Quantidade);
                EnterToTab.add(jTexF_Tecnico);
                EnterToTab.add(jTexF_Data);
                jCBox_Pesquisar.requestFocus();
                jTexF_Data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        }

        private void add() {
                if (jCheckBox_LykosOs.isSelected() && jTexF_OsLykos.getText().trim().isEmpty()) {
                        Message.erroTrist(null, "Por favor informe o número da OS!!!");
                        jTexF_OsLykos.requestFocus();
                } else if (jTexF_Data.getText().replaceAll("\\d", "").trim().isEmpty()) {
                        Message.erroTrist(null, "Por favor informe a data!!!");
                        jTexF_Data.requestFocus();
                } else if (movement.isEmpty()) {
                        Message.errorX(null, "Pro favor adicione pelo menos um produto!");
                } else {
                        boolean valou = controller.productEntry(movement);
                        if (valou) {
                                Message.sucess(null, "Lista geral Atualizado!!!");
                                limparCampo();
                        } else {
                                Message.errorX(null, "Falha ao Atualizar Dados!!!");
                        }
                }
        }

        private void visualizarItem() {
                if (movement != null && !movement.isEmpty()) {
                        ManagerProhibitedView tView = new ManagerProhibitedView(movement);
                        tView.setVisible(true);
                } else {
                        Message.erroTrist(null, "Lista Vazia!!!");
                }
        }

        private void addItens() {
                if ("".equals(jTexF_Quantidade.getText())) {
                        Message.erroTrist(null, "Insira a quantidade do item!");
                } else if ("".equals(jTexF_Data.getText().replaceAll("/", "").trim())) {
                        Message.erroTrist(null, "Insira uma data valida!");
                } else {
                        try {
                                String nome = jCBox_Pesquisar.getSelectedItem().toString();
                                Double quantidade = Double.valueOf(jTexF_Quantidade.getText());
                                String dataTexto = jTexF_Data.getText();
                                dataTexto = dataTexto.replaceAll("/", "");
                                SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
                                java.util.Date dataUtil;
                                dataUtil = formato.parse(dataTexto);
                                java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

                                String nomeSetor = "TECNOLOGIA DA INFORMAÇÃO";
                                Sector sector = this.sectorController.findByNome(nomeSetor);

                                String osLykos = jTexF_OsLykos.getText();
                                Equipment equipment = this.controller.findByNome(nome);

                                Movement equipm = new Movement(TypeMovement.IN, quantidade, dataSql, sector, osLykos,
                                                equipment);

                                if (!movement.contains(equipm)) {
                                        movement.add(equipm);
                                        Message.sucess(null, "Add item!");
                                } else {
                                        Message.erroTrist(null, "Item ja adicionado!");
                                }
                        } catch (ParseException e) {
                                e.printStackTrace();
                        }
                }
        }

        private void limparCampo() {
                jCBox_Pesquisar.setSelectedIndex(0);
                loadEquipDados();
                jTexF_Quantidade.setText("");
                jTexF_OsLykos.setText("");
                movement.clear();
        }

        private void loadEquip() {
                this.equip = this.controller.findAll();
                jCBox_Pesquisar.removeAllItems();
                for (Equipment equip : equip) {
                        jCBox_Pesquisar.addItem(equip.getNome());
                }
                loadEquipDados();
        }

        private void loadEquipDados() {
                if (jCBox_Pesquisar.getItemCount() > 0) {
                        String item = String.valueOf(jCBox_Pesquisar.getSelectedItem());
                        Equipment equipm = new Equipment();
                        equipm.setNome(item);
                        List<Equipment> equip = this.controller.comboboxMat(equipm);
                        jTexF_Und.setText("");
                        for (Equipment equipe : equip) {
                                jTexF_Und.setText(equipe.getAbreviacao_un());
                        }
                }
        }

        private void actionJcombox() {
                jCBox_Pesquisar.addItemListener(new java.awt.event.ItemListener() {
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
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jTexF_OsLykos = new javax.swing.JTextField();
                jTexF_Quantidade = new javax.swing.JTextField();
                jTexF_Data = new javax.swing.JFormattedTextField();
                jLabel7 = new javax.swing.JLabel();
                jButt_Salvar = new javax.swing.JButton();
                jButt_Visualizar = new javax.swing.JButton();
                jTexF_Tecnico = new javax.swing.JTextField();
                jTexF_Und = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                jButt_Itens = new javax.swing.JButton();
                jButt_Limpar = new javax.swing.JButton();
                jCheckBox_LykosOs = new javax.swing.JCheckBox();
                jButt_Exit = new javax.swing.JButton();

                setTitle("Entradas");
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

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26)); // NOI18N
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Entradas de Materiais em Estoque");

                jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel2.setText("Quantidade:");

                jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel3.setText("Nome:");

                jLabel6.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel6.setText("Data:");

                jTexF_OsLykos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jTexF_Quantidade.setFont(new java.awt.Font("Segoe UI", 0, 14));

                try {
                        jTexF_Data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
                                        new javax.swing.text.MaskFormatter("##/##/####")));
                } catch (java.text.ParseException ex) {
                        ex.printStackTrace();
                }
                jTexF_Data.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                jTexF_Data.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel7.setText("Técnico:");

                jButt_Salvar.setText("Salvar");
                jButt_Salvar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Salvar();
                        }
                });

                jButt_Visualizar.setText("Visualizar");
                jButt_Visualizar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Visualizar();
                        }
                });

                jCBox_Pesquisar.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                jTexF_Tecnico.setEditable(false);
                jTexF_Tecnico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jTexF_Und.setEditable(false);
                jTexF_Und.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel8.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel8.setText("UN");

                jButt_Itens.setText("Adicinar Item");
                jButt_Itens.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Itens();
                        }
                });

                jButt_Limpar.setText("Limpar");
                jButt_Limpar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Limpar();
                        }
                });

                jCheckBox_LykosOs.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jCheckBox_LykosOs.setText("N° da os ( - lykos - )");
                jCheckBox_LykosOs.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                                if (e.getStateChange() == ItemEvent.SELECTED) {
                                        jTexF_OsLykos.setEnabled(true);
                                        jTexF_OsLykos.setDocument(new TransformFieldUppcase(10));
                                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                                        jTexF_OsLykos.setText("");
                                        jTexF_OsLykos.setEnabled(false);
                                }
                        }
                });

                jButt_Exit.setIcon(new javax.swing.ImageIcon(
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
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 800,
                                                                Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(34, 34, 34)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jTexF_OsLykos,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                154,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(56, 56, 56))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jCheckBox_LykosOs)
                                                                                                .addGap(49, 49, 49)))
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jLabel6,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                140,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_Data,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                140,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel7,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                148,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_Tecnico,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                302,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(43, 43, 43))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(jLabel3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                170,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(569, 569,
                                                                                                                                569))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(jCBox_Pesquisar,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                634,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(105, 105,
                                                                                                                                105))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel1Layout.createSequentialGroup()
                                                                                                                .addComponent(jButt_Exit,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                22,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addContainerGap())))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(313, 313, 313)
                                                                                                .addComponent(jButt_Itens,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                141,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(231, 231, 231)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(jTexF_Und)
                                                                                                                .addComponent(jLabel8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                72,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(129, 129,
                                                                                                                                                129)
                                                                                                                                .addComponent(jTexF_Quantidade,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                72,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel1Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(107, 107,
                                                                                                                                                107)
                                                                                                                                .addComponent(jLabel2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                111,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(327, 327, 327)
                                                                                                .addComponent(jButt_Salvar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                144,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(220, 220, 220)
                                                                                                .addComponent(jButt_Limpar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                100,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(159, 159, 159)
                                                                                                .addComponent(jButt_Visualizar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                100,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
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
                                                                .addComponent(jLabel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jCBox_Pesquisar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                37,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel2,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                30,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                30,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jTexF_Und,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                36,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(36, 36, 36)
                                                                                                .addComponent(jTexF_Quantidade,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                36,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(31, 31, 31)
                                                                .addComponent(jButt_Itens,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                42,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel7,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jTexF_Tecnico,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                34,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(jCheckBox_LykosOs)
                                                                                                                .addComponent(jLabel6,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                30,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel1Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jTexF_Data,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                36,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jTexF_OsLykos,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                36,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                65, Short.MAX_VALUE)
                                                                .addComponent(jButt_Salvar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                34,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jButt_Limpar,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                31,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButt_Visualizar,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                31,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(31, 31, 31)));

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
                this.add();
        }

        private void actionPerformed_Visualizar() {
                this.visualizarItem();
        }

        private void actionPerformed_Itens() {
                this.addItens();
        }

        private void actionPerformed_Limpar() {
                this.limparCampo();
        }

        private void actionPerformed_Exit() {
                dispose();
        }

}
