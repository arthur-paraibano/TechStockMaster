package view.feedback;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Objects;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.FeedbackController;
import model.entities.Feedback;
import util.InternetConnectionChecker;
import util.Message;
import util.Session;
import util.TransformFieldUppcase;

public class FeedbackView extends javax.swing.JDialog {
    private javax.swing.JButton jButt_Exit;
    private javax.swing.JButton jButt_Salvar;
    private javax.swing.JComboBox<String> jCBox_Status;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JFormattedTextField jTexF_Data;
    private javax.swing.JTextField jTexF_Tecnico;
    private javax.swing.JTextArea jText_Descricao;

    private FeedbackController controller;

    public FeedbackView() {
        initComponents();
        setModal(true);
        this.initMetods();
        this.controller = new FeedbackController();
    }

    private void initMetods() {
        jTexF_Tecnico.setText(Session.getUser().getNomeLogin());
        AutoCompleteDecorator.decorate(jCBox_Status);
        jText_Descricao.setDocument(new TransformFieldUppcase(300));
        jTexF_Data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
    }

    private void enviar() {
        try {

            if (jText_Descricao.getText().isEmpty() || jTexF_Tecnico.getText().isEmpty()
                    || jTexF_Data.getText().isEmpty() || jCBox_Status.getSelectedIndex() == 0) {
                Message.erroTrist(null, "Preencha todos os campos!!!");
            } else if (InternetConnectionChecker.isConnectedToInternet() == false) {
                Message.errorX(null, "É necessário ter conexão com a internet!!!");
            } else {

                Feedback obj = new Feedback();
                
                obj.getUser().setNomeLogin(jTexF_Tecnico.getText());

                String dataTexto = jTexF_Data.getText();
                dataTexto = dataTexto.replaceAll("/", "");
                SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
                java.util.Date dataUtil = formato.parse(dataTexto);
                java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
                obj.setDate(dataSql);
                obj.setStatus(jCBox_Status.getSelectedItem().toString());
                obj.setComment(jText_Descricao.getText());

                controller.addFeedback(obj);
                Message.sucess(null, "Obrigado(a) pelo seu Feedback !!!");
                this.exit();
            }
        } catch (Exception e) {
            Message.errorX(null, "Erro ao coletar dados!!!" + e.getMessage());
        }
    }

    private void exit() {
        this.dispose();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButt_Exit = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTexF_Tecnico = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jText_Descricao = new javax.swing.JTextArea();
        jTexF_Data = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jButt_Salvar = new javax.swing.JButton();
        jCBox_Status = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();

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
        jLabel5.setText("Feedback");

        jButt_Exit.setIcon(
                new javax.swing.ImageIcon(
                        Objects.requireNonNull(getClass().getResource("/resources/exit.png"))));
        jButt_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButt_ExitActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Usurário:");

        jTexF_Tecnico.setEditable(false);
        jTexF_Tecnico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Deixe sua Opinião / Sugestão : ");

        jText_Descricao.setColumns(20);
        jText_Descricao.setRows(5);
        jScrollPane1.setViewportView(jText_Descricao);

        try {
            jTexF_Data.setFormatterFactory(
                    new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTexF_Data.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTexF_Data.setEnabled(false);
        jTexF_Data.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Data:");

        jButt_Salvar.setText("Enviar");
        jButt_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButt_SalvarActionPerformed(evt);
            }
        });

        jCBox_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "ALTA", "MÉDIA", "BAIXA" }));

        jLabel15.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
        jLabel15.setText("RELEVâNCIA:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                .createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jTexF_Data, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(72, 72, 72))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                .createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 788,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                148, Short.MAX_VALUE)
                                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jCBox_Status,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTexF_Tecnico,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 302,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(311, 311, 311)
                                .addComponent(jButt_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 144,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTexF_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTexF_Data, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jCBox_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButt_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));

        jTabbedPane1.addTab("Feedback", jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel1Layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(jButt_Exit,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTabbedPane1))
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButt_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
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
    }// </editor-fold>

    private void jButt_ExitActionPerformed(java.awt.event.ActionEvent evt) {
        this.exit();
    }

    private void jButt_SalvarActionPerformed(java.awt.event.ActionEvent evt) {
        this.enviar();
    }
}
