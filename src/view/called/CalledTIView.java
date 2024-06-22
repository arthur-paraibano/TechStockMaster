package view.called;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.CalledTIController;
import model.entities.CalledTI;
import model.entities.Sector;
import model.table.CalledTITableModel;
import util.EnterToTab;
import util.Message;
import util.Session;
import util.TableUtil;
import util.TransformFieldUppcase;

public class CalledTIView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Limpar;
        private javax.swing.JButton jButt_PesquisarOS;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JComboBox<String> jCBox_PesquisarOS;
        private javax.swing.JComboBox<String> jCBox_Setor;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel12;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane3;
        private javax.swing.JTable jTable1;
        private javax.swing.JTabbedPane jTabbedPane1;
        private javax.swing.JFormattedTextField jTexF_Data;
        private javax.swing.JTextField jTexF_Tecnico;
        private javax.swing.JTextArea jText_Descricao;

        private int lastSelectedRowIndex = -1;
        private List<Sector> sectores;
        private List<CalledTI> calledTI;
        private final CalledTIController controller;

        public CalledTIView() {
                initComponents();
                setModal(true);
                this.sectores = new ArrayList<>();
                this.calledTI = new ArrayList<>();
                this.controller = new CalledTIController();
                this.initMetodos();
                this.formatComponets();
        }

        private void initMetodos() {
                this.loadSector();
                this.loadTable();
                this.loadPesquis();
        }

        private void formatComponets() {
                AutoCompleteDecorator.decorate(jCBox_Setor);
                AutoCompleteDecorator.decorate(jCBox_PesquisarOS);
                jTexF_Tecnico.setText(Session.getUser().getNomeLogin());
                jTexF_Data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
                jText_Descricao.setDocument(new TransformFieldUppcase(100));
                EnterToTab.add(jCBox_Setor);
                EnterToTab.add(jTexF_Tecnico);
                EnterToTab.add(jTexF_Data);
                EnterToTab.add(jCBox_PesquisarOS);
                jCBox_Setor.requestFocus();
        }

        private void pesquisa() {
                this.lookFor();
        }

        private void lookFor() {
                if (jCBox_PesquisarOS.getSelectedIndex() >= 0) {
                        String nome = jCBox_PesquisarOS.getSelectedItem().toString();
                        CalledTITableModel model = new CalledTITableModel(this.controller.findAllTable());
                        configTable(model);
                        int rowCount = jTable1.getRowCount();
                        int startRow = lastSelectedRowIndex + 1;
                        if (lastSelectedRowIndex < 0 || lastSelectedRowIndex >= rowCount) {
                                startRow = 0;
                        }
                        boolean found = false;
                        for (int row = startRow; row < rowCount; row++) {
                                Object value = jTable1.getValueAt(row, 2);
                                if (value != null && value.toString().equals(nome)) {
                                        jTable1.setRowSelectionInterval(row, row);
                                        lastSelectedRowIndex = row;
                                        found = true;
                                        break;
                                }
                        }
                        if (!found) {
                                for (int row = 0; row < startRow; row++) {
                                        Object value = jTable1.getValueAt(row, 2);
                                        if (value != null && value.toString().equals(nome)) {
                                                jTable1.setRowSelectionInterval(row, row);
                                                lastSelectedRowIndex = row;
                                                break;
                                        }
                                }
                        }
                }
        }

        private void add() {
                try {
                        Integer index = jCBox_Setor.getSelectedIndex();
                        Sector sector = sectores.get(index);

                        String descricao = jText_Descricao.getText();
                        String dataTexto = jTexF_Data.getText();
                        dataTexto = dataTexto.replaceAll("/", "");
                        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
                        java.util.Date dataUtil = formato.parse(dataTexto);
                        java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

                        if (dataTexto == null) {
                                Message.errorX(null, "Informe a data!!!");
                        } else {
                                CalledTI ti = new CalledTI();
                                ti.setSetor(sector);
                                ti.setTecnico(Session.getUser());
                                ti.setDate(dataSql);
                                ti.setDescricao(descricao);
                                boolean value = this.controller.save(ti);
                                if (value) {
                                        this.relatorio();
                                        this.clear();
                                        this.loadTable();
                                } else {
                                        Message.errorX(null, "Erro ao salvar");
                                }
                        }

                } catch (ParseException e) {
                        Message.errorX(null, "Erro na formatação da data");
                }
        }

        private void relatorio() {
                JOptionPane optionPane = new JOptionPane("Você deseja imprimir o Controle de Saída ?",
                                JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
                optionPane.setIcon(new ImageIcon(getClass().getResource("/resources/pensando.png")));

                JDialog dialog = optionPane.createDialog("Sair");

                optionPane.addPropertyChangeListener(evt -> {
                        if (evt.getPropertyName().equals(JOptionPane.VALUE_PROPERTY)) {
                                int choice = (int) evt.getNewValue();
                                if (choice == JOptionPane.YES_OPTION) {
                                        try {
                                                InputStream inputStream = getClass().getResourceAsStream(
                                                                "/resources/ControleDeSaída-Pro-Fé.pdf");
                                                File tempFile = File.createTempFile("ControleDeSaida", ".pdf");
                                                tempFile.deleteOnExit();
                                                try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
                                                        byte[] buffer = new byte[1024];
                                                        int length;
                                                        while ((length = inputStream.read(buffer)) != -1) {
                                                                outputStream.write(buffer, 0, length);
                                                        }
                                                }
                                                Desktop.getDesktop().open(tempFile);
                                        } catch (Exception ex) {
                                                Message.errorX(null,
                                                                "Erro ao exibir relatório contate o ADM do sistema "
                                                                                + ex.getMessage());
                                                System.out.println(ex.getMessage());
                                                // ex.printStackTrace();
                                        }
                                } else {
                                        dialog.setVisible(false);
                                }
                        }
                });

                dialog.setVisible(true);
        }

        private void clear() {
                jCBox_Setor.setSelectedIndex(0);
                jText_Descricao.setText("");
        }

        private void loadSector() {
                this.sectores = this.controller.findAll();
                jCBox_Setor.removeAllItems();
                for (Sector sector : sectores) {
                        jCBox_Setor.addItem(sector.getnome());
                }
        }

        private void loadPesquis() {
                this.calledTI = this.controller.findAllTable();
                jCBox_PesquisarOS.removeAllItems();
                for (CalledTI calledTI : calledTI) {
                        jCBox_PesquisarOS.addItem(calledTI.getSetor().getnome());
                }
        }

        private void loadTable() {
                CalledTITableModel model = new CalledTITableModel(this.controller.findAllTable());
                configTable(model);
        }

        private void configTable(CalledTITableModel model) {
                jTable1.setModel(model);
                jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                jTable1.getTableHeader().setReorderingAllowed(false);
                TableUtil.hide(jTable1, 0);
                TableUtil.showToltip(jTable1, 2);
                TableUtil.showToltip(jTable1, 3);

                jTable1.getColumnModel().getColumn(1).setResizable(false);
                jTable1.getColumnModel().getColumn(2).setResizable(false);
                jTable1.getColumnModel().getColumn(3).setResizable(false);
                jTable1.getColumnModel().getColumn(4).setResizable(false);

                jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(140);
                jTable1.getColumnModel().getColumn(3).setPreferredWidth(140);
                jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jLabel5 = new javax.swing.JLabel();
                jButt_Exit = new javax.swing.JButton();
                jTabbedPane1 = new javax.swing.JTabbedPane();
                jPanel2 = new javax.swing.JPanel();
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
                jPanel3 = new javax.swing.JPanel();
                jScrollPane3 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jLabel12 = new javax.swing.JLabel();
                jCBox_PesquisarOS = new javax.swing.JComboBox<>();
                jLabel11 = new javax.swing.JLabel();
                jButt_PesquisarOS = new javax.swing.JButton();

                setTitle("Chamados");
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
                jLabel5.setText("Chamados TI");

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

                jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel7.setText("SEtor:");

                jLabel9.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel9.setText("Técnico:");

                jTexF_Tecnico.setEditable(false);
                jTexF_Tecnico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

                jLabel10.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                jLabel10.setText("Descrição de problema(s):");

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

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                                                .addGap(343, 343, 343))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
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
                                                                                71, Short.MAX_VALUE)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel9,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                148,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_Tecnico,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                302,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(103, 103, 103))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(254, 254, 254)
                                                                .addComponent(jLabel6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                92,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jTexF_Data,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                140,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                jPanel2Layout.createSequentialGroup()
                                                                                .addContainerGap()
                                                                                .addComponent(jScrollPane1)
                                                                                .addContainerGap())
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel10,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(33, 33, 33)
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
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel6,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_Data,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                36,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(11, 11, 11)
                                                                .addComponent(jLabel10,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                91, Short.MAX_VALUE)
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

                jTabbedPane1.addTab("Chamados", jPanel2);

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

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
                jLabel11.setText("pesquisa:");

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
                                                                .addContainerGap())
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGap(322, 322, 322)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
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
                                                                                238,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                17, Short.MAX_VALUE)));
                jTabbedPane1.addTab("Historicos", jPanel3);

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

        private void actionPerformed_Salvar() {
                this.add();
        }

        private void actionPerformed_PesquisarOS() {
                this.pesquisa();
        }

        private void actionPerformed_Limpar() {
                this.clear();
        }

        private void actionPerformed_Exit() {
                dispose();
        }
}
