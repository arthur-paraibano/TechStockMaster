package view.movement;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
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

public class ExitStockView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Itens;
        private javax.swing.JButton jButt_Limpar;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JButton jButt_Visualizar;
        private javax.swing.JComboBox<String> jCBox_Pesquisar;
        private javax.swing.JComboBox<String> jCBox_Setor;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JFormattedTextField jTexF_Data;
        private javax.swing.JTextField jTexF_Quantidade;
        private javax.swing.JTextField jTexF_Tecnico;
        private javax.swing.JTextField jTexF_Und;

        private EquipmentController controller;
        private List<Equipment> equip;
        private List<Sector> sectores;
        private SectorController sectorController;
        private List<Movement> movement;

        public ExitStockView() {
                initComponents();
                setModal(true);
                jTexF_Tecnico.setText(Session.getUser().getNomeLogin());
                this.controller = new EquipmentController();
                this.equip = new ArrayList<>();
                this.sectores = new ArrayList<>();
                this.sectorController = new SectorController();
                this.movement = new ArrayList<>();
                this.initMetodos();
                this.actionJcombox();
        }

        private void initMetodos() {
                this.loadEquip();
                this.loadSector();
                AutoCompleteDecorator.decorate(jCBox_Pesquisar);
                AutoCompleteDecorator.decorate(jCBox_Setor);
                NumericFilter.addNumericFilter(jTexF_Quantidade, 6);
                jTexF_Tecnico.setText(Session.getUser().getNomeLogin());
                EnterToTab.add(jCBox_Pesquisar);
                EnterToTab.add(jTexF_Und);
                EnterToTab.add(jTexF_Quantidade);
                EnterToTab.add(jTexF_Tecnico);
                EnterToTab.add(jTexF_Data);
                jCBox_Pesquisar.requestFocus();
                jTexF_Data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
        }

        private void add() {
                if (jTexF_Data.getText().replaceAll("\\d", "").trim().isEmpty()) {
                        Message.errorX(null, "Erro ao obter a data!!!");
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
                        Message.erroTrist(null, "Erro ao obter data, contate o ADM!");
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

                                String nomeSetor = jCBox_Setor.getSelectedItem().toString();

                                Sector sector = this.sectorController.findByNome(nomeSetor);
                                Equipment equipment = this.controller.findByNome(nome);

                                Movement equipm = new Movement(TypeMovement.OUT, quantidade, dataSql, sector,
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
                jCBox_Setor.setSelectedIndex(0);
                loadEquipDados();
                jTexF_Quantidade.setText("");
                movement.clear();
        }

        private void close() {
                this.dispose();
        }

        private void loadSector() {
                this.sectores = this.sectorController.findAll();
                jCBox_Setor.removeAllItems();
                for (Sector sector : sectores) {
                        jCBox_Setor.addItem(sector.getnome());
                }
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
                jTexF_Quantidade = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                jButt_Salvar = new javax.swing.JButton();
                jButt_Visualizar = new javax.swing.JButton();
                jCBox_Pesquisar = new javax.swing.JComboBox<>();
                jTexF_Tecnico = new javax.swing.JTextField();
                jTexF_Und = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                jButt_Itens = new javax.swing.JButton();
                jButt_Limpar = new javax.swing.JButton();
                jButt_Exit = new javax.swing.JButton();
                jLabel9 = new javax.swing.JLabel();
                jCBox_Setor = new javax.swing.JComboBox<>();
                jTexF_Data = new javax.swing.JFormattedTextField();

                setBackground(new java.awt.Color(255, 255, 255));
                setTitle("Saídas");
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
                jLabel5.setText("Saída(s) de Material(is)");

                jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel2.setText("Quantidade:");

                jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel3.setText("Nome:");

                jTexF_Quantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel7.setText("Técnico:");

                jButt_Salvar.setText("Salvar");
                jButt_Salvar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_SalvarActionPerformed(evt);
                        }
                });

                jButt_Visualizar.setText("Visualizar");
                jButt_Visualizar.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_VisualizarActionPerformed(evt);
                        }
                });

                jCBox_Pesquisar.setModel(
                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                jTexF_Tecnico.setEditable(false);
                jTexF_Tecnico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jTexF_Und.setEditable(false);
                jTexF_Und.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel8.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel8.setText("UN");

                jButt_Itens.setText("Adicinar Item");
                jButt_Itens.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButt_ItensActionPerformed(evt);
                        }
                });

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

                jLabel9.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel9.setText("SEtor:");

                jCBox_Setor.setModel(
                                new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

                try {
                        jTexF_Data.setFormatterFactory(
                                        new javax.swing.text.DefaultFormatterFactory(
                                                        new javax.swing.text.MaskFormatter("##/##/####")));
                } catch (java.text.ParseException ex) {
                        ex.printStackTrace();
                }
                jTexF_Data.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                jTexF_Data.setEnabled(false);
                jTexF_Data.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 800,
                                                                Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
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
                                                                                                .addGap(313, 313, 313)
                                                                                                .addComponent(jButt_Itens,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                141,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
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
                                                                                                jPanel1Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jCBox_Pesquisar,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                634,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(105, 105,
                                                                                                                                105))
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
                                                                                                                .addComponent(jTexF_Data,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                90,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addContainerGap())))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(37, 37, 37)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jCBox_Setor,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                306,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel9,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                148,
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
                                                                .addGap(43, 43, 43)));
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
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel7,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel9,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(9, 9, 9)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jTexF_Tecnico,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jCBox_Setor,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                61,
                                                                                Short.MAX_VALUE)
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
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTexF_Data,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
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

        private void jButt_SalvarActionPerformed(java.awt.event.ActionEvent evt) {
                this.add();
        }

        private void jButt_VisualizarActionPerformed(java.awt.event.ActionEvent evt) {
                this.visualizarItem();
        }

        private void jButt_ItensActionPerformed(java.awt.event.ActionEvent evt) {
                this.addItens();
        }

        private void jButt_LimparActionPerformed(java.awt.event.ActionEvent evt) {
                this.limparCampo();
        }

        private void jButt_ExitActionPerformed(java.awt.event.ActionEvent evt) {
                this.close();
        }
}