package view.extra.shopping;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.ShoppingController;
import model.entities.Equipment;
import model.entities.Sector;
import model.entities.Shopping;
import model.table.ShoppingTableModel;
import util.Message;
import util.NumericFilter;
import util.OptionPaneCreator;
import util.Session;
import util.TableUtil;
import util.TransformFieldUppcase;

public class ShoppingView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Limpar;
        private javax.swing.JButton jButt_PesquisarOS;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JComboBox<String> jCBox_Equipment;
        private javax.swing.JComboBox<String> jCBox_PesquisarOS;
        private javax.swing.JComboBox<String> jCBox_Setor;
        private javax.swing.JComboBox<String> jCBox_Status;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JTable jTable1;
        private javax.swing.JTabbedPane jTabbedPane1;
        private javax.swing.JFormattedTextField jTexF_Data;
        private javax.swing.JTextField jTexF_Quantid;
        private javax.swing.JTextField jTexF_Tecnico;
        private javax.swing.JTextField jTexF_Und;
        private javax.swing.JTextArea jText_Descricao;

        private int lastSelectedRowIndex = -1;
        private List<Equipment> equip;
        private List<Shopping> shoppings;
        private ShoppingController controller;
        private List<Sector> sectors;
        private final Shopping objShopping;

        public ShoppingView() {
                initComponents();
                setModal(true);
                this.controller = new ShoppingController();
                this.equip = new ArrayList<>();
                this.shoppings = new ArrayList<>();
                this.objShopping = null;
                this.initMetods();
                this.actionJcombox();
        }

        public ShoppingView(Shopping shopping) {
                initComponents();
                setModal(true);
                this.controller = new ShoppingController();
                this.equip = new ArrayList<>();
                this.shoppings = new ArrayList<>();
                this.objShopping = shopping;
                this.initMetodsUpdate();
                this.actionJcombox();
        }

        private void initMetods() {
                this.loadEquip();
                this.loadSector();
                this.loadTable();
                this.loadEquipPesquis();
                jLabel15.setVisible(false);
                jCBox_Status.setVisible(false);
                jTexF_Tecnico.setText(Session.getUser().getNomeLogin());
                AutoCompleteDecorator.decorate(jCBox_Equipment);
                AutoCompleteDecorator.decorate(jCBox_Setor);
                AutoCompleteDecorator.decorate(jCBox_PesquisarOS);
                NumericFilter.addNumericFilter(jTexF_Quantid, 6);
                jText_Descricao.setDocument(new TransformFieldUppcase(100));
                jTexF_Data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
                jButt_PesquisarOS.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        lookFor();
                                }
                        }
                });
        }

        private void initMetodsUpdate() {
                this.loadEquip();
                this.loadSector();
                AutoCompleteDecorator.decorate(jCBox_Equipment);
                AutoCompleteDecorator.decorate(jCBox_Setor);
                AutoCompleteDecorator.decorate(jCBox_Status);
                jLabel5.setText("Alterar Descrição / Status de Solicitações");
                jCBox_Equipment.setSelectedItem(this.objShopping.getEquipment().getNome());
                Double quantidade = this.objShopping.getAmount();
                jTexF_Quantid.setText(Double.toString(quantidade));
                jCBox_Setor.setSelectedItem(this.objShopping.getSector().getnome());
                jTexF_Tecnico.setText(this.objShopping.getUser().getNomeLogin());
                jText_Descricao.setDocument(new TransformFieldUppcase(100));
                jText_Descricao.setText(this.objShopping.getDescription());
                Date date = this.objShopping.getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                jTexF_Data.setText(dateFormat.format(date));
                jCBox_Status.setSelectedItem(this.objShopping.getStatus());

                int index = jTabbedPane1.indexOfTab("Históricos");
                if (index != -1) {
                        jTabbedPane1.removeTabAt(index);
                }
                jCBox_Equipment.setEditable(false);
                jCBox_Equipment.setEnabled(false);
                jTexF_Und.setEditable(false);
                jTexF_Quantid.setEditable(false);
                jCBox_Setor.setEnabled(false);
                jTexF_Tecnico.setEditable(false);
                jTexF_Data.setEditable(false);
                jPanel2.remove(jButt_Limpar);
        }

        private void add() {
                try {
                        Equipment equip = this.equip.get(jCBox_Equipment.getSelectedIndex());
                        Double quantidade = (jTexF_Quantid.getText() == null || jTexF_Quantid.getText().isEmpty()) ? 0.0
                                        : Double.valueOf(jTexF_Quantid.getText());

                        Sector sector = this.sectors.get(jCBox_Setor.getSelectedIndex());
                        String obs = jText_Descricao.getText();
                        String dataTexto = jTexF_Data.getText();
                        dataTexto = dataTexto.replaceAll("/", "");
                        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
                        java.util.Date dataUtil = formato.parse(dataTexto);
                        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                        String status = jCBox_Status.getSelectedItem().toString();

                        Shopping obj = new Shopping();
                        if (this.objShopping == null) {
                                obj.setId(0);
                        } else {
                                int id = objShopping.getId();
                                obj.setId(id);
                        }
                        obj.getEquipment().setId(equip.getId());
                        obj.setAmount(quantidade);
                        obj.getSector().setId(sector.getId());
                        obj.setDescription(obs);
                        obj.setDate(dataSql);
                        obj.setStatus(status);

                        OptionPaneCreator oCreator = new OptionPaneCreator();
                        JOptionPane optionPane = oCreator.createOptionPane();
                        JDialog dialog = oCreator.createDialog(optionPane);
                        JButton btnSim = new JButton("Sim");
                        JButton btnNao = new JButton("Não");

                        optionPane.setOptions(new Object[] { btnSim, btnNao });

                        if (quantidade == 0.0) {
                                Message.errorX(this, "A quantidade não pode ser 0 !!!");
                                jTexF_Quantid.requestFocus();
                        } else if (jTexF_Tecnico.getText().trim().equals("")) {
                                Message.errorX(this, "Deve conter o nome do Técnico, contate o ADM do sistema!!!");
                        } else if (jCBox_Setor == null) {
                                Message.errorX(this, "Selecione um setor !!!");
                        } else if (obj.getId() != 0 && jCBox_Status.getSelectedIndex() == 0) {
                                Message.errorX(this, "Selecione um status !!!");
                        }

                        else {
                                btnSim.addActionListener(e -> {
                                        boolean value = controller.saveEquip(obj);
                                        if (value) {
                                                if (obj.getId() == 0) {
                                                        this.limpar();
                                                        this.loadTable();
                                                } else {
                                                        this.dispose();
                                                }
                                        }
                                        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                        dialog.setVisible(false);
                                });
                                btnNao.addActionListener(e -> {
                                        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                        dialog.setVisible(false);
                                });
                                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                dialog.setVisible(true);
                        }
                } catch (Exception e) {
                        Message.errorX(null, "Erro no método de adicionar, contate o ADM do sistema.");
                        e.printStackTrace();
                }

        }

        private void pesquisarEquip() {
                this.lookFor();
        }

        private void limpar() {
                jCBox_Equipment.setSelectedIndex(0);
                jCBox_Setor.setSelectedIndex(0);
                jTexF_Quantid.setText("");
                jText_Descricao.setText("");
        }

        private void close() {
                this.dispose();
        }

        private void loadSector() {
                this.sectors = this.controller.findAllSector();
                jCBox_Setor.removeAllItems();
                for (Sector sector : sectors) {
                        jCBox_Setor.addItem(sector.getnome());
                }
        }

        private void loadEquipPesquis() {
                this.shoppings = this.controller.findAllPes();
                jCBox_PesquisarOS.removeAllItems();
                for (Shopping shoppings : shoppings) {
                        jCBox_PesquisarOS.addItem(shoppings.getEquipment().getNome());
                }
        }

        private void lookFor() {
                if (jCBox_PesquisarOS.getSelectedIndex() >= 0) {
                        String nome = jCBox_PesquisarOS.getSelectedItem().toString();
                        ShoppingTableModel model = new ShoppingTableModel(this.controller.findAll());
                        configTable(model);
                        int rowCount = jTable1.getRowCount();
                        int startRow = lastSelectedRowIndex + 1;
                        if (lastSelectedRowIndex < 0 || lastSelectedRowIndex >= rowCount) {
                                startRow = 0;
                        }
                        boolean found = false;
                        for (int row = startRow; row < rowCount; row++) {
                                Object value = jTable1.getValueAt(row, 1);
                                if (value != null && value.toString().equals(nome)) {
                                        jTable1.setRowSelectionInterval(row, row);
                                        lastSelectedRowIndex = row; // Atualiza o índice da última linha selecionada
                                        found = true;
                                        break;
                                }
                        }
                        if (!found) {
                                for (int row = 0; row < startRow; row++) {
                                        Object value = jTable1.getValueAt(row, 1);
                                        if (value != null && value.toString().equals(nome)) {
                                                jTable1.setRowSelectionInterval(row, row);
                                                lastSelectedRowIndex = row; // Atualiza o índice da última linha
                                                                            // selecionada
                                                break;
                                        }
                                }
                        }
                }
        }

        private void loadEquip() {
                this.equip = this.controller.findAllEquip();
                jCBox_Equipment.removeAllItems();
                for (Equipment equip : equip) {
                        jCBox_Equipment.addItem(equip.getNome());
                }
                this.loadEquipDados();
        }

        private void loadEquipDados() {
                if (jCBox_Equipment.getItemCount() > 0) {
                        String item = String.valueOf(jCBox_Equipment.getSelectedItem());
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
                jCBox_Equipment.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                if (evt.getStateChange() == ItemEvent.SELECTED) {
                                        loadEquipDados();
                                }
                        }
                });
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
                jLabel5 = new javax.swing.JLabel();
                jButt_Exit = new javax.swing.JButton();
                jTabbedPane1 = new javax.swing.JTabbedPane();
                jPanel2 = new javax.swing.JPanel();
                jCBox_Equipment = new javax.swing.JComboBox<>();
                jLabel3 = new javax.swing.JLabel();
                jTexF_Und = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                jTexF_Quantid = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                jCBox_Setor = new javax.swing.JComboBox<>();
                jLabel9 = new javax.swing.JLabel();
                jTexF_Tecnico = new javax.swing.JTextField();
                jLabel10 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jText_Descricao = new javax.swing.JTextArea();
                jTexF_Data = new javax.swing.JFormattedTextField();
                jLabel6 = new javax.swing.JLabel();
                jButt_Salvar = new javax.swing.JButton();
                jButt_Limpar = new javax.swing.JButton();
                jCBox_Status = new javax.swing.JComboBox<>();
                jLabel15 = new javax.swing.JLabel();
                jPanel3 = new javax.swing.JPanel();
                jScrollPane3 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jLabel12 = new javax.swing.JLabel();
                jCBox_PesquisarOS = new javax.swing.JComboBox<>();
                jLabel11 = new javax.swing.JLabel();
                jButt_PesquisarOS = new javax.swing.JButton();

                setTitle("Compras TI");
                setBackground(new java.awt.Color(255, 255, 255));
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
                jLabel5.setText("Solicitar Compra TI");

                jButt_Exit.setIcon(new javax.swing.ImageIcon(
                                Objects.requireNonNull(getClass().getResource("/resources/exit.png"))));
                jButt_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_Exit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Exit();
                        }
                });

                jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel3.setText("Nome:");

                jTexF_Und.setEditable(false);
                jTexF_Und.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel8.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel8.setText("UN");

                jTexF_Quantid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel2.setText("Quantidade:");

                jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel7.setText("Para o Setor:");

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
                        jTexF_Data.setFormatterFactory(
                                        new javax.swing.text.DefaultFormatterFactory(
                                                        new javax.swing.text.MaskFormatter("##/##/####")));
                } catch (java.text.ParseException ex) {
                        ex.printStackTrace();
                }
                jTexF_Data.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                jTexF_Data.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel6.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                jLabel6.setText("Data:");

                jButt_Salvar.setText("Salvar");
                jButt_Salvar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Salvar();
                        }
                });

                jButt_Limpar.setText("Limpar");
                jButt_Limpar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Limpar();
                        }
                });

                jCBox_Status.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "", "EM COTAÇÃO", "COMPRADO", "RECEBIDO", "REJEITADO" }));

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
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                170,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jCBox_Equipment,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                546,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(jTexF_Und)
                                                                                                                .addComponent(jLabel8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                72,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(34, 34, 34)
                                                                                                                                .addComponent(jTexF_Quantid,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                72,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(18, 18, 18)
                                                                                                                                .addComponent(jLabel2,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                96,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                                .addGap(16, 16, 16))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel2Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGap(38, 38, 38)
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
                                                                                                                                                                .addComponent(jCBox_Setor,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                306,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addPreferredGap(
                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
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
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addComponent(jScrollPane1,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                344,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                .addComponent(jLabel10,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                148,
                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                .createParallelGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                                                .addGroup(jPanel2Layout
                                                                                                                                                                                .createSequentialGroup()
                                                                                                                                                                                .addGap(56, 56, 56)
                                                                                                                                                                                .addComponent(jLabel15,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                66,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                                                .addComponent(jCBox_Status,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                200,
                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                                                                .addGroup(
                                                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                                                jPanel2Layout.createSequentialGroup()
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                                                                                .addComponent(jLabel6,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                92,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                                                                                .addPreferredGap(
                                                                                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                                                                                                                .addComponent(jTexF_Data,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                                                                                140,
                                                                                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                                                                                .addGap(72, 72, 72))))
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
                                                                                100,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(343, 343, 343)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(jLabel8,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                30,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel2,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                30,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jTexF_Und,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                36,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel3,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addGroup(jPanel2Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jTexF_Quantid,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                36,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jCBox_Equipment,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                37,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(26, 26, 26)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel7,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jCBox_Setor,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                37,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jLabel9,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jTexF_Tecnico,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                34,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(jPanel2Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel10,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jScrollPane1,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel2Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(29, 29, 29)
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
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
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
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                52,
                                                                                Short.MAX_VALUE)
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

                jTabbedPane1.addTab("Solicitações", jPanel2);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                {},
                                                {},
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

                jButt_PesquisarOS.setIcon(new javax.swing.ImageIcon(
                                Objects.requireNonNull(getClass().getResource("/resources/pesquisar.png"))));
                jButt_PesquisarOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_PesquisarOS.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_PesquisarOS();
                        }
                });

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jScrollPane3)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel11,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                258,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel12,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                258,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                524,
                                                                                                                Short.MAX_VALUE))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(54, 54, 54)
                                                                                                .addComponent(jCBox_PesquisarOS,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                634,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(27, 27, 27)
                                                                                                .addComponent(jButt_PesquisarOS)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addComponent(jLabel11,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(jCBox_PesquisarOS,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                37,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jButt_PesquisarOS))
                                                                .addGap(46, 46, 46)
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
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
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

        private void actionPerformed_Salvar() {
                this.add();
        }

        private void actionPerformed_PesquisarOS() {
                this.pesquisarEquip();
        }

        private void actionPerformed_Limpar() {
                this.limpar();
        }

        private void actionPerformed_Exit() {
                this.close();
        }
}