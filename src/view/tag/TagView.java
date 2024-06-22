package view.tag;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Objects;

import javax.swing.text.MaskFormatter;

import controller.TagController;
import model.entities.Tag;
import util.EnterToTab;
import util.Message;
import util.TransformFieldUppcase;

public class TagView extends javax.swing.JDialog {
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Limpar;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JFormattedTextField jFTexF_Data;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel16;
        private javax.swing.JLabel jLabel17;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JTextField jTexF_AbrevTag;
        private javax.swing.JTextField jTexF_Descricao;

        private TagController controller;

        public TagView() {
                initComponents();
                setModal(true);
                this.formatComponets();
                this.controller = new TagController();
        }

        private void formatComponets() {
                //jTexF_AbrevTag.setDocument(new TransformFieldUppcase(3));
                jTexF_Descricao.setDocument(new TransformFieldUppcase(50));

                EnterToTab.add(jTexF_AbrevTag);
                EnterToTab.add(jTexF_Descricao);
                EnterToTab.add(jFTexF_Data);
                jTexF_AbrevTag.requestFocus();
        }

        private void add() {
                String abrev = jTexF_AbrevTag.getText().toUpperCase();
                String descTag = jTexF_Descricao.getText().toUpperCase();

                if (!abrev.contains("#")) {
                        try {
                                String dataTexto = jFTexF_Data.getText();
                                dataTexto = dataTexto.replaceAll("/", "");
                                SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
                                java.util.Date dataUtil = formato.parse(dataTexto);
                                java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

                                Tag obj = new Tag();
                                obj.setAbreviacao(abrev);
                                obj.setType(descTag);
                                obj.setDate(dataSql);

                                controller.insert(obj);
                                jTexF_AbrevTag.requestFocus();
                                actionPerformed_Limpar();

                        } catch (Exception ex) {
                                Message.errorX(null, "Erro ao coletar a data, contate o ADM do sistema!!!");
                        }
                } else {
                        Message.errorX(null, "A abreviação deve conter pelo menos um caractere '#'.");
                }
        }

        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jButt_Salvar = new javax.swing.JButton();
                jButt_Limpar = new javax.swing.JButton();
                jLabel5 = new javax.swing.JLabel();
                jButt_Exit = new javax.swing.JButton();
                jLabel11 = new javax.swing.JLabel();
                jTexF_AbrevTag = new javax.swing.JTextField();
                jLabel9 = new javax.swing.JLabel();
                jLabel17 = new javax.swing.JLabel();
                jLabel16 = new javax.swing.JLabel();
                jLabel15 = new javax.swing.JLabel();
                jTexF_Descricao = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jFTexF_Data = new javax.swing.JFormattedTextField();

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

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26)); // NOI18N
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Cadastro de Tag p/ Equipamentos");

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

                jTexF_AbrevTag.setFont(new java.awt.Font("Perpetua Titling MT", Font.PLAIN, 14));

                try {
                        MaskFormatter maskFormatter = new MaskFormatter("UUU-");
                        maskFormatter.setPlaceholderCharacter('#');

                        jTexF_AbrevTag = new javax.swing.JFormattedTextField(maskFormatter);
                } catch (java.text.ParseException ex) {
                        ex.printStackTrace();
                }
                jTexF_Descricao.setFont(new java.awt.Font("Perpetua Titling MT", Font.PLAIN, 14));

                jLabel11.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel11.setText("Abreviação da tag:");
                jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel9.setText("EX:");
                jLabel17.setText("IMP-");
                jLabel16.setText("IMPRESSORA");
                jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
                jLabel15.setText("EX:");
                jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel2.setText("Descrição da Tag:");
                jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel3.setText("Data de Criação:");

                try {
                        jFTexF_Data.setFormatterFactory(
                                        new javax.swing.text.DefaultFormatterFactory(
                                                        new javax.swing.text.MaskFormatter("##/##/####")));
                } catch (java.text.ParseException ex) {
                        ex.printStackTrace();
                }
                jFTexF_Data.setHorizontalAlignment(javax.swing.JTextField.CENTER);

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jButt_Exit,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                22,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap())
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jButt_Salvar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                144,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(351, Short.MAX_VALUE)
                                                                .addComponent(jButt_Limpar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                100,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(349, 349, 349))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(52, 52, 52)
                                                                .addGroup(jPanel1Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jLabel2,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel11,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                156,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jFTexF_Data,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                109,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jTexF_Descricao,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                356,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel15)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel16,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                86,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jTexF_AbrevTag,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                183,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jLabel9)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(jLabel17,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                86,
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
                                                                .addGap(16, 16, 16)
                                                                .addComponent(jLabel5)
                                                                .addGap(83, 83, 83)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel11,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_AbrevTag,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel17,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                16,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel9))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                69,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel15)
                                                                                                .addComponent(jLabel16,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                16,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel1Layout
                                                                                                .createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                .addComponent(jLabel2,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                30,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(jTexF_Descricao,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                34,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(75, 75, 75)
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jFTexF_Data,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel3,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(59, 59, 59)
                                                                .addComponent(jButt_Salvar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                34,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(28, 28, 28)
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
        }

        private void actionPerformed_Salvar() {
                this.add();
        }

        private void actionPerformed_Limpar() {
                jTexF_AbrevTag.setText("");
                jTexF_Descricao.setText("");
                jFTexF_Data.setText("");
        }

        private void actionPerformed_Exit() {
                dispose();
        }

}
