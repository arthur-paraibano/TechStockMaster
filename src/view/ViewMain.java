package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import util.Message;
import util.Session;
import view.called.CalledTIView;
import view.extra.ExtraOptions;
import view.feedback.FeedbackView;
import view.login.LoginView;
import view.movement.ExitStockView;
import view.movement.ProhibitedView;
import view.register.Register;
import view.report.ReportJasper;

public class ViewMain extends javax.swing.JFrame {
        private javax.swing.JButton jButt_Cadastro;
        private javax.swing.JButton jButt_Chamados;
        private javax.swing.JButton jButt_Entradas;
        private javax.swing.JButton jButt_Extras;
        private javax.swing.JButton jButt_Feedback;
        private javax.swing.JButton jButt_Relatorios;
        private javax.swing.JButton jButt_Saidas;
        private javax.swing.JEditorPane jEditorPane1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel14;
        private javax.swing.JLabel jLabel15;
        private javax.swing.JLabel jLabel16;
        private javax.swing.JLabel jLabel17;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JPanel jPanel7;
        private javax.swing.JScrollPane jScrollPane2;

        private JPanel grayBackgroundPanel;

        public ViewMain() {
                initComponents();
                this.initMetods();
        }

        private void initMetods() {
                // Maximizar tela
                setExtendedState(MAXIMIZED_BOTH);
                // Adicione um WindowListener para detectar o fechamento da tela principal
                adicionarWindowListenerParaTelaLogin();
                setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/computador.png")));
        }

        private void initComponents() {

                jScrollPane2 = new javax.swing.JScrollPane();
                jEditorPane1 = new javax.swing.JEditorPane();
                jPanel1 = new javax.swing.JPanel();
                jPanel3 = new javax.swing.JPanel();
                jLabel10 = new javax.swing.JLabel();
                jLabel16 = new javax.swing.JLabel();
                jButt_Chamados = new javax.swing.JButton();
                jButt_Cadastro = new javax.swing.JButton();
                jButt_Entradas = new javax.swing.JButton();
                jLabel9 = new javax.swing.JLabel();
                jButt_Saidas = new javax.swing.JButton();
                jLabel15 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                jButt_Relatorios = new javax.swing.JButton();
                jLabel14 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jButt_Extras = new javax.swing.JButton();
                jLabel17 = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                jPanel6 = new javax.swing.JPanel();
                jPanel7 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jButt_Feedback = new javax.swing.JButton();

                jScrollPane2.setViewportView(jEditorPane1);

                setTitle("Controle de Estoque");
                setBackground(new java.awt.Color(17, 18, 65));
                setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                setMinimumSize(new java.awt.Dimension(1100, 620));

                // Painel secundario de cinza
                this.telaCinza();
                this.sair();

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                jLabel10.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18)); // NOI18N
                jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel10.setText("Cadastro");

                jLabel16.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18)); // NOI18N
                jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel16.setText("Chamados");

                URL imageUrl = getClass().getResource("/resources/chamados.png");
                if (imageUrl != null) {
                        jButt_Chamados.setIcon(new javax.swing.ImageIcon(imageUrl));
                } else {
                        System.out.println("Erro: recurso 'chamados.png' não encontrado.");
                }
                jButt_Chamados.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Chamado();
                        }
                });

                URL cadastUrl = getClass().getResource("/resources/cadastro.png");
                if (cadastUrl != null) {
                        jButt_Cadastro.setIcon(new javax.swing.ImageIcon(cadastUrl));
                } else {
                        System.out.println("Erro: recurso 'chamados.png' não encontrado.");
                }
                jButt_Cadastro.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Cadastro();
                        }
                });

                URL entradaUrl = getClass().getResource("/resources/entradaCaxa.png");
                if (entradaUrl != null) {
                        jButt_Entradas.setIcon(new javax.swing.ImageIcon(entradaUrl));
                } else {
                        System.out.println("Erro: recurso 'chamados.png' não encontrado.");
                }
                jButt_Entradas.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Entradas();
                        }
                });

                jLabel9.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18)); // NOI18N
                jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel9.setText("Entradas");

                URL exittUrl = getClass().getResource("/resources/saidaCaxa.png");
                if (exittUrl != null) {
                        jButt_Saidas.setIcon(new javax.swing.ImageIcon(exittUrl));
                } else {
                        System.out.println("Erro: recurso 'chamados.png' não encontrado.");
                }
                jButt_Saidas.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Saidas();
                        }
                });

                jLabel15.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18)); // NOI18N
                jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel15.setText("Saídas");

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", Font.BOLD, 26)); // NOI18N
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Controle");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jButt_Cadastro,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel10,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                94,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jButt_Chamados,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel16,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                94,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jButt_Entradas,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                94,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel9,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                94,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel3Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jLabel15,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jButt_Saidas,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                94,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                231,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel5)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jButt_Cadastro,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                99,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jButt_Chamados,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                99,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(12, 12, 12)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel16,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                25,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel10,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                25,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jButt_Entradas,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                99,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jButt_Saidas,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                99,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel15,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                25,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel9,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                25,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap()));

                jPanel4.setBackground(new java.awt.Color(255, 255, 255));

                URL relUrl = getClass().getResource("/resources/relatório.png");
                if (relUrl != null) {
                        jButt_Relatorios.setIcon(new javax.swing.ImageIcon(relUrl));
                } else {
                        System.out.println("Erro: recurso 'chamados.png' não encontrado.");
                }
                jButt_Relatorios.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Relatorios();
                        }
                });

                jLabel14.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18)); // NOI18N
                jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel14.setText("Relatorios");

                jLabel6.setFont(new java.awt.Font("Baskerville Old Face", Font.BOLD, 26)); // NOI18N
                jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel6.setText("Relatorios e outras ações");

                URL extrasUrl = getClass().getResource("/resources/extra.png");
                if (extrasUrl != null) {
                        jButt_Extras.setIcon(new javax.swing.ImageIcon(extrasUrl));
                } else {
                        System.out.println("Erro: recurso 'chamados.png' não encontrado.");
                }
                jButt_Extras.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Extras();
                        }
                });

                jLabel17.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
                jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel17.setText("Extras");

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                                .addContainerGap(144, Short.MAX_VALUE)
                                                                .addGroup(jPanel4Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jButt_Relatorios,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel14,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                94,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                227,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(jPanel4Layout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                .addComponent(jButt_Extras,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jLabel17,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                94,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(95, Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                387,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel4Layout.setVerticalGroup(
                                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(9, Short.MAX_VALUE)
                                                                .addComponent(jLabel6)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel4Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jButt_Relatorios,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                99,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(12, 12, 12)
                                                                                                .addComponent(jLabel14,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                25,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel4Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jButt_Extras,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                99,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(12, 12, 12)
                                                                                                .addComponent(jLabel17,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                25,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addContainerGap()));

                jPanel5.setBackground(new java.awt.Color(0, 0, 0));

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                jPanel5Layout.setVerticalGroup(
                                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 14, Short.MAX_VALUE));

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
                jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel4.setText("@2023/2024");

                jPanel6.setBackground(new java.awt.Color(0, 0, 0));

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                jPanel6Layout.setVerticalGroup(
                                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 14, Short.MAX_VALUE));

                jPanel7.setBackground(new java.awt.Color(0, 0, 0));

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 0, Short.MAX_VALUE));
                jPanel7Layout.setVerticalGroup(
                                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 14, Short.MAX_VALUE));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jPanel3,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jPanel4,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE))
                                                                .addContainerGap())
                                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout
                                                                .createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(jLabel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                263,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(63, 63, 63)
                                                                .addComponent(jPanel6,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel5,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel4,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(10, 10, 10)
                                                                .addComponent(jPanel7,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                78,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel4)
                                                                .addContainerGap()));

                jPanel2.setBackground(new java.awt.Color(2, 15, 47));

                jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setIcon(
                                new javax.swing.ImageIcon(Objects
                                                .requireNonNull(getClass().getResource("/resources/telaInt.png"))));

                jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(204, 204, 204));
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setText("TechStockMaster");

                jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel3.setText("CONTROLE DE ESTOQUE ");

                jButt_Feedback.setText("Feedback");
                jButt_Feedback.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                jButt_Feedback.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_Feedback();
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jLabel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                321,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(63, Short.MAX_VALUE)
                                                                .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                339,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(38, 38, 38))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(165, 165, 165)
                                                                .addComponent(jButt_Feedback)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(72, 72, 72)
                                                                .addComponent(jLabel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                44,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                44,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                342,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jButt_Feedback)
                                                                .addContainerGap()));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, 0)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                pack();
                setLocationRelativeTo(null);
        }

        private void actionPerformed_Feedback() {
                // Mostra o fundo cinza temporário
                grayBackgroundPanel.setVisible(true);
                // Abre a tela RedefineLogin
                FeedbackView tela = new FeedbackView();
                tela.setVisible(true);
                tela.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent windowEvent) {
                                grayBackgroundPanel.setVisible(false);
                        }
                });
        }

        private void actionPerformed_Cadastro() {
                String acesso = Session.getUser().getAcessoModulo();
                String tipouser = Session.getUser().getTipoUsuario();
                if (acesso.equals("NÃO") || tipouser.equals("COMUM")) {
                        Message.erroTrist(null, "Seu usuário não tem permisão!!!");
                } else {
                        // Crie uma instância da classe register
                        Register tela = new Register();
                        // Configure a operação padrão de fechamento para ocultar o diálogo
                        tela.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
                        // Exiba a tela de cadastro
                        tela.setVisible(true);
                }

        }

        private void actionPerformed_Chamado() {
                String acesso = Session.getUser().getAcessoModulo();
                String tipouser = Session.getUser().getTipoUsuario();
                if (acesso.equals("NÃO") || tipouser.equals("COMUM")) {
                        Message.erroTrist(null, "Seu usuário não tem permisão!!!");
                } else {
                        // Mostra o fundo cinza temporário
                        grayBackgroundPanel.setVisible(true);
                        // Abre a tela RedefineLogin
                        CalledTIView tela = new CalledTIView();
                        tela.setVisible(true);
                        tela.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent windowEvent) {
                                        grayBackgroundPanel.setVisible(false);
                                }
                        });
                }
        }

        private void actionPerformed_Entradas() {
                String acesso = Session.getUser().getAcessoModulo();
                String tipouser = Session.getUser().getTipoUsuario();
                if (acesso.equals("NÃO") || tipouser.equals("COMUM")) {
                        Message.erroTrist(null, "Seu usuário não tem permisão!!!");
                } else {
                        // Mostra o fundo cinza temporário
                        grayBackgroundPanel.setVisible(true);
                        // Abre a tela RedefineLogin
                        ProhibitedView tela = new ProhibitedView();
                        tela.setVisible(true);
                        tela.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent windowEvent) {
                                        // Esconde o fundo cinza quando a tela RedefineLogin for fechada
                                        grayBackgroundPanel.setVisible(false);
                                }
                        });
                }
        }

        private void actionPerformed_Saidas() {
                String acesso = Session.getUser().getAcessoModulo();
                String tipouser = Session.getUser().getTipoUsuario();
                if (acesso.equals("NÃO") || tipouser.equals("COMUM")) {
                        Message.erroTrist(null, "Seu usuário não tem permisão!!!");
                } else {
                        // Mostra o fundo cinza temporário
                        grayBackgroundPanel.setVisible(true);
                        // Abre a tela RedefineLogin
                        ExitStockView tela = new ExitStockView();
                        tela.setVisible(true);

                        tela.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent windowEvent) {
                                        // Esconde o fundo cinza quando a tela RedefineLogin for fechada
                                        grayBackgroundPanel.setVisible(false);
                                }
                        });
                }
        }

        private void actionPerformed_Relatorios() {
                // Message.sqlInform(null, "As funções desta tela ainda estão sendo
                // implementadas!!!");
                // Mostra o fundo cinza temporário
                grayBackgroundPanel.setVisible(true);
                // Abre a tela RedefineLogin
                ReportJasper tela = new ReportJasper();
                tela.setVisible(true);
                tela.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent windowEvent) {
                                // Esconde o fundo cinza quando a tela RedefineLogin for fechada
                                grayBackgroundPanel.setVisible(false);
                        }
                });
        }

        private void actionPerformed_Extras() {

                grayBackgroundPanel.setVisible(true);
                ExtraOptions tela = new ExtraOptions();
                tela.setVisible(true);
                tela.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                                grayBackgroundPanel.setVisible(false);
                        }
                });
        }

        private void telaCinza() {
                // Painel secundario das cores
                grayBackgroundPanel = new JPanel();
                grayBackgroundPanel.setBackground(new Color(192, 192, 192, 128)); // Cor cinza com transparência
                grayBackgroundPanel.setVisible(false);
                getContentPane().add(grayBackgroundPanel);
                addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                                // Atualiza a posição e tamanho do painel cinza
                                grayBackgroundPanel.setBounds(0, 0, getWidth(), getHeight());
                        }
                });
        }

        private void sair() {

                addWindowListener(new WindowAdapter() {

                        public void windowClosing(WindowEvent evt) {

                                JOptionPane optionPane = new JOptionPane("Você deseja sair do programa?",
                                                JOptionPane.QUESTION_MESSAGE,
                                                JOptionPane.YES_NO_OPTION, null, new Object[] {}, null);
                                Icon figura = new ImageIcon(Objects
                                                .requireNonNull(getClass().getResource("/resources/pensando.png")));
                                optionPane.setIcon(figura);

                                JDialog dialog = optionPane.createDialog("Sair");

                                JButton btnSim = new JButton("Sim");
                                JButton btnNao = new JButton("Não");
                                btnSim.setRequestFocusEnabled(true);
                                btnSim.requestFocus();
                                btnSim.addKeyListener(new KeyListener() {

                                        @Override
                                        public void keyTyped(KeyEvent e) {
                                        }

                                        @Override
                                        public void keyPressed(KeyEvent e) {
                                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                                        setDefaultCloseOperation(
                                                                        javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                                                        dialog.setVisible(false);
                                                }
                                        }

                                        @Override
                                        public void keyReleased(KeyEvent e) {
                                        }

                                });
                                optionPane.setOptions(new Object[] { btnSim, btnNao });

                                btnSim.addActionListener(e -> {
                                        // Ação a ser executada quando o usuário escolher "Sim"
                                        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                                        dialog.setVisible(false);

                                });
                                btnNao.addActionListener(e -> {
                                        // Ação a ser executada quando o usuário escolher "Não"
                                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                        dialog.setVisible(false);
                                });

                                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                dialog.setVisible(true);
                        }
                });
        }

        private void adicionarWindowListenerParaTelaLogin() {
                addWindowListener(new WindowListener() {
                        @Override
                        public void windowOpened(WindowEvent e) {
                                // Método não utilizado
                        }

                        @Override
                        public void windowClosing(WindowEvent e) {
                                // Método não utilizado
                        }

                        @Override
                        public void windowClosed(WindowEvent e) {
                                // Tela principal fechada, reabrir a tela de login
                                LoginView telaLoginView = new LoginView();
                                telaLoginView.setVisible(true);
                        }

                        @Override
                        public void windowIconified(WindowEvent e) {
                                // Método não utilizado
                        }

                        @Override
                        public void windowDeiconified(WindowEvent e) {
                                // Método não utilizado
                        }

                        @Override
                        public void windowActivated(WindowEvent e) {
                                // Método não utilizado
                        }

                        @Override
                        public void windowDeactivated(WindowEvent e) {
                                // Método não utilizado
                        }
                });
        }

        public static void main(String[] args) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                new ViewMain().setVisible(true);
                        }
                });
        }
}
