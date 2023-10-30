package TI.Suporte.View;

import TI.Suporte.Modal.dao.Entradas_Mat;
import TI.Suporte.Modal.dao.Estoque_Mat;
import TI.Suporte.Modal.obj.Equipamentos_Ent_Est;
import TI.Suporte.Modal.obj.TableModel_Estoque;
import TI.Suporte.Modal.obj.Saida_Mat;
import TI.Suporte.Util.util.FormatTable;
import TI.Suporte.Util.util.NumberFilter;
import TI.Suporte.Modal.dao.Saidas_Met;
import TI.Suporte.DataBase.BD_Base;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TimerTask;
import javax.swing.JTextField;

public class ContEstoque extends JFrame {
    private java.awt.Button jButt_Relat_Estoque;
    private java.awt.Button jButt_Relat_Saida;
    private javax.swing.JButton jButt_Entrada;
    private javax.swing.JButton jButt_Saida;
    private javax.swing.JComboBox<String> jCoBox_Entrada;
    private javax.swing.JComboBox<String> jCoBox_Saida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private JTable jTbl_Informacoes;
    private javax.swing.JFormattedTextField txtF_Data_Entrada;
    private javax.swing.JFormattedTextField txtF_Data_Saida;
    private JTextField txtF_Quant_Saida;
    private java.awt.TextField txtF_Descricao_Entrada;
    private JTextField txtF_Quant_Entrada;
    private java.awt.TextField txtF_Tecnico_Saida;
    private TimerTask task = null;


    public ContEstoque() {
        initComponents();
        this.tabelaCfg();
        this.comboBoxEntrada();
        this.comboBoxSaida();
        this.atualizarDadosTabela();
        this.mouseListener();
        this.task();
    }

    private void task() {

        java.util.Timer timer = new java.util.Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                atualizarDadosTabela();
            }
        };
        timer.schedule(task, 3000, 3000);
    }


    private void mouseListener() {
        jTbl_Informacoes.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // se clicar na tabela, execute algo...

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // se pressionar algum botão do mouse na tabela, execute algo...
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // se o mouse passar sobre a tabela, execute algo...
            }

            @Override
            public void mouseEntered(MouseEvent e) {

                //Ao entrar na tabela, execute algo...

                if (task != null) {
                    task.cancel();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //ao sair da tabela, execute algo
                task();

            }
        });

    }

    private void tabelaCfg() {
        jTbl_Informacoes.setDefaultRenderer(Object.class, new FormatTable());
        jTbl_Informacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTbl_Informacoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTbl_Informacoes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        jScrollPane1.setViewportView(jTbl_Informacoes);
    }

    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        JEditorPane jEditorPane1 = new JEditorPane();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JPanel jPanel1 = new JPanel();
        JLabel jLb_Quantidade = new JLabel();
        JLabel jLb_Nome = new JLabel();
        JLabel jLb_Descricao = new JLabel();
        JLabel jLb_Data_Entrada = new JLabel();
        txtF_Quant_Entrada = new JTextField();
        txtF_Descricao_Entrada = new java.awt.TextField();
        txtF_Data_Entrada = new javax.swing.JFormattedTextField();
        JLabel jLb_Nome1 = new JLabel();
        jButt_Entrada = new javax.swing.JButton();
        jCoBox_Entrada = new javax.swing.JComboBox<>();
        JPanel jPanel2 = new JPanel();
        JLabel jLb_Nome2 = new JLabel();
        JLabel jLb_Nome3 = new JLabel();
        JLabel jLb_Quantidade2 = new JLabel();
        JLabel jLb_Quantidade3 = new JLabel();
        JLabel jLb_Data_Entrada1 = new JLabel();
        txtF_Tecnico_Saida = new java.awt.TextField();
        jButt_Saida = new javax.swing.JButton();
        txtF_Quant_Saida = new JTextField();
        txtF_Data_Saida = new javax.swing.JFormattedTextField();
        jCoBox_Saida = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbl_Informacoes = new JTable();
        Button jButt_AtualizarRegistro = new Button();
        jButt_Relat_Saida = new java.awt.Button();
        jButt_Relat_Estoque = new java.awt.Button();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();

        jScrollPane2.setViewportView(jEditorPane1);

        // Configurações da Frame.
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Estoque");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(1110, 680));
        setMinimumSize(new java.awt.Dimension(1110, 500));
        setPreferredSize(new java.awt.Dimension(1110, 690));
        setResizable(false);

        // Criação do menubar.
        criarMenu();

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONTROLE DE ESTOQUE ");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLb_Quantidade.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLb_Quantidade.setText("Quantidade");
        jLb_Nome.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLb_Nome.setText("Nome do equipamento");
        jLb_Descricao.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLb_Descricao.setText("Descrição");

        txtF_Quant_Entrada.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
        AbstractDocument doc = (AbstractDocument) txtF_Quant_Entrada.getDocument();
        doc.setDocumentFilter(new NumberFilter());

        txtF_Descricao_Entrada.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));

        jLb_Data_Entrada.setText("Data de entrada");
        try {
            txtF_Data_Entrada = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        txtF_Data_Entrada.setHorizontalAlignment(JTextField.CENTER);
        txtF_Data_Entrada.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        jLb_Nome1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        jLb_Nome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLb_Nome1.setText("Cadastro de estoque");

        jButt_Entrada.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jButt_Entrada.setText("Cadastrar");

        // O metodo "addKeyListener" chama que tiver dentro quando o usuario apertar "Enter".
        jButt_Entrada.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    salvar_dados();
                    dispose();
                    setVisible(true);
                }
            }
        });
        // Metodo chama quando o usurario "Clica" em cima do mesmo.
        jButt_Entrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar_dados();
                dispose();
                setVisible(true);
            }
        });


        // Configurações do Layout da tela.
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLb_Nome1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(15, 15, 15).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLb_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLb_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jCoBox_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLb_Quantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(txtF_Quant_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(22, 22, 22).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLb_Data_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(txtF_Data_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))).addComponent(txtF_Descricao_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)))).addGroup(jPanel1Layout.createSequentialGroup().addGap(177, 177, 177).addComponent(jButt_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLb_Nome1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLb_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jCoBox_Entrada)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLb_Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(txtF_Quant_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLb_Data_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(txtF_Data_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLb_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(txtF_Descricao_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButt_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLb_Nome2.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        jLb_Nome2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLb_Nome2.setText("Saída de estoque");
        jLb_Nome3.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLb_Nome3.setText("Nome do equipamento");
        jLb_Quantidade2.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLb_Quantidade2.setText("Quantidade");
        jLb_Quantidade3.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLb_Quantidade3.setText("Técnico e Setor");
        jLb_Data_Entrada1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLb_Data_Entrada1.setText("Data de saída");


        txtF_Tecnico_Saida.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));

        jButt_Saida.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jButt_Saida.setText("Dar saída");

        // O metodo "addKeyListener" chama que tiver dentro quando o usuario apertar "Enter".
        jButt_Saida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    saida_dados();
                    dispose();
                    setVisible(true);
                }
            }
        });

        // Metodo chama quando o usurario "Clica" em cima do mesmo.
        jButt_Saida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saida_dados();
                dispose();
                setVisible(true);
            }
        });

        txtF_Quant_Saida.setFont(new Font("Dialog", Font.PLAIN, 14));
        AbstractDocument doc_ = (AbstractDocument) txtF_Quant_Saida.getDocument();
        doc_.setDocumentFilter(new NumberFilter());
        try {
            txtF_Data_Saida = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        txtF_Data_Saida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtF_Data_Saida.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtF_Data_Saida.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));


        // Configurações do Layout da tela.
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLb_Nome2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLb_Nome3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jCoBox_Saida, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLb_Quantidade2).addComponent(txtF_Quant_Saida, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(28, 28, 28).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLb_Data_Entrada1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(txtF_Data_Saida, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(22, 22, 22)).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLb_Quantidade3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 348, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(txtF_Tecnico_Saida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()))).addGroup(jPanel2Layout.createSequentialGroup().addGap(200, 200, 200).addComponent(jButt_Saida, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(jLb_Nome2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLb_Nome3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLb_Quantidade2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLb_Data_Entrada1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(13, 13, 13).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jCoBox_Saida).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(txtF_Data_Saida).addComponent(txtF_Quant_Saida, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(4, 4, 4))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLb_Quantidade3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(txtF_Tecnico_Saida, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButt_Saida, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));

        // Botão que atualiza os dados da tabela e dos combosbox presente no Frame ao "Clicar" sobre o botão.
        jButt_AtualizarRegistro.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
        jButt_AtualizarRegistro.setLabel("Atualizar tabela");
        jButt_AtualizarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dadosAtualizados();
            }
        });

        // Botão que ao ser chamado vai passar as informações pra um arquivo do Jaspersoft pre configurado.
        jButt_Relat_Estoque.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
        jButt_Relat_Estoque.setLabel("Gerar Relatório de Estoque");
        jButt_Relat_Estoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                relatorioEstoque();
            }
        });

        // Botão que ao ser chamado vai passar as informações pra um arquivo do Jaspersoft pre configurado.
        jButt_Relat_Saida.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
        jButt_Relat_Saida.setLabel("Gerar Relatório de saída");
        jButt_Relat_Saida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                relatorioSaida();
            }
        });

        // Configurações do Layout da tela.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(23, 23, 23).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel4).addGap(23, 23, 23)).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jButt_Relat_Estoque, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jButt_AtualizarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(289, 289, 289).addComponent(jButt_Relat_Saida, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jScrollPane1).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(layout.createSequentialGroup().addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)).addComponent(jLabel3)).addComponent(jLabel4)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jButt_Relat_Estoque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jButt_AtualizarRegistro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jButt_Relat_Saida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }

    private void comboBoxEntrada() {
        // Combobox do Frame de '''Entrada''' de material, faz um select dentro do frame para ter as informações sempre atualizadas.
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        Connection con = null;
        ResultSet resultadoConsulta = null;

        try {
            con = BD_Base.getConnection();
            assert con != null;
            PreparedStatement stmt = con.prepareStatement("SELECT EQUIPAMENTO FROM bd_estoque.cadastro_equipamento ORDER BY EQUIPAMENTO ASC");

            resultadoConsulta = stmt.executeQuery();

            while (resultadoConsulta.next()) {
                String equipamento = resultadoConsulta.getString("EQUIPAMENTO");
                model.addElement(equipamento);
            }
            jCoBox_Entrada.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher JComboBox: " + e.getMessage());
        } finally {
            BD_Base.closeConnection(con, null, resultadoConsulta);
        }
    }

    private void comboBoxSaida() {
        // Combobox do Frame de ''''Saída''' de material, faz um select dentro do frame para ter as informações sempre atualizadas.
        DefaultComboBoxModel<String> model_ = new DefaultComboBoxModel<>();
        Connection con_ = null;
        ResultSet resultadoConsulta_ = null;
        try {
            con_ = BD_Base.getConnection();
            assert con_ != null;
            PreparedStatement stmt = con_.prepareStatement("SELECT EQUIPAMENTO FROM bd_estoque.cadastro_equipamento ORDER BY EQUIPAMENTO ASC");

            resultadoConsulta_ = stmt.executeQuery();

            while (resultadoConsulta_.next()) {
                String equipamento = resultadoConsulta_.getString("EQUIPAMENTO");
                model_.addElement(equipamento);
            }
            jCoBox_Saida.setModel(model_);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher JComboBox: " + e.getMessage());
        } finally {
            BD_Base.closeConnection(con_, null, resultadoConsulta_);
        }
    }


    private void salvar_dados() {
        importar_dados_BD();
    }

    // Metodo que '''envia''' as informações para o DataBase.
    private void importar_dados_BD() {
        Entradas_Mat dao = new Entradas_Mat();
        Equipamentos_Ent_Est obj = new Equipamentos_Ent_Est();

        // Antes de salvar mostra uma mendagem se que realmente salvar os dados.
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma o salvamento dos dados?", "Atenção", JOptionPane.YES_NO_OPTION);
        // se for, sim, ira salvar os dados dos campos.
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                String item = Objects.requireNonNull(jCoBox_Entrada.getSelectedItem()).toString();
                obj.setNome_equipamento(item);
                obj.setQuantidade(Double.parseDouble(txtF_Quant_Entrada.getText()));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(txtF_Data_Entrada.getText(), formatter);
                Date sqlDate = Date.valueOf(date);
                obj.setDate(sqlDate);

                obj.setDescricao(txtF_Descricao_Entrada.getText());

                dao.add_database(obj);
                limpar_cadatro();

            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(null, "Erro ao salvar dados! \n\n" + ex);
            }
        }
    }

    private void relatorioEstoque() {
        gerarRelatorioEstoque();
    }

    private void gerarRelatorioEstoque() {
        Connection connection = BD_Base.getConnection();

        try {
            String reportPath = "/Dpadua/Suporte/Relatorios/RelatorioDeEstoque.jrxml";
            InputStream jasperFile = getClass().getResourceAsStream(reportPath);
            JasperDesign jasperDesign = JRXmlLoader.load(jasperFile);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Preencher o relatório com os dados do banco de dados
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

            // Exibir o relatório na tela usando JasperViewer
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao exibir o relatório. Contate o administrador do sistema.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void relatorioSaida() {
        gerarRelatorioSaida();
    }

    private void gerarRelatorioSaida() {
        Connection connection = BD_Base.getConnection();

        try {
            String reportPath = "/Dpadua/Suporte/Relatorios/Saidas_de_materias.jrxml";
            InputStream jasperFile = getClass().getResourceAsStream(reportPath);
            JasperDesign jasperDesign = JRXmlLoader.load(jasperFile);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // Preencher o relatório com os dados do banco de dados
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

            // Exibir o relatório na tela usando JasperViewer
            JasperViewer.viewReport(jasperPrint, false);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao exibir o relatório. Contate o administrador do sistema.");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void saida_dados() {
        saida_dados_BD();
    }

    // Metodo que dar'''Saída''' dos mareriais do DataBase.
    private void saida_dados_BD() {
        Saidas_Met dao = new Saidas_Met();
        Saida_Mat obj = new Saida_Mat();

        // Antes de salvar mostra uma mendagem se que realmente salvar os dados.
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma o salvamento dos dados?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {

                String item = Objects.requireNonNull(jCoBox_Saida.getSelectedItem()).toString();
                obj.setNome_equipamento(item);

                obj.setQuantidade(Double.parseDouble(txtF_Quant_Saida.getText()));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(txtF_Data_Saida.getText(), formatter);
                Date sqlDate = Date.valueOf(date);
                obj.setDate(sqlDate);

                obj.setTecnico(txtF_Tecnico_Saida.getText());

                double quantidadeSaida = Double.parseDouble((txtF_Quant_Saida.getText()));
                double totalEntrada = Entradas_Mat.calcularQuantidadeTotalEntrada(item);
                double totalSaida = Saidas_Met.calcularQuantidadeTotalSaida(item);

                if (quantidadeSaida <= (totalEntrada - totalSaida)) {
                    dao.saida_database(obj);
                    limpar_saida();
                } else {
                    JOptionPane.showMessageDialog(null, "Quantidade indisponível em estoque! Disponível: " + (totalEntrada - totalSaida) + ", Solicitado: " + quantidadeSaida);
                }
            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(null, "Erro ao salvar dados! \n\n" + ex);
            }
        }
    }

    public void dadosAtualizados() {
        atualizarDadosCombobox();
        atualizarDadosTabela();
    }

    public void atualizarDadosCombobox() {
        comboBoxEntrada();
        comboBoxSaida();
    }

    public void atualizarDadosTabela() {
        // Recuperação dos dados do banco de dados e configuração do modelo personalizado da tabela.
        Estoque_Mat dao = new Estoque_Mat();
        java.util.List<Equipamentos_Ent_Est> dados = dao.red();
        TableModel_Estoque tableModel = new TableModel_Estoque(dados);
        jTbl_Informacoes.setModel(tableModel);

        //redimencionar colunas
        jTbl_Informacoes.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTbl_Informacoes.getColumnModel().getColumn(2).setPreferredWidth(300);
        jTbl_Informacoes.getTableHeader().setEnabled(false);

    }

    private void limpar_cadatro() {
        jCoBox_Entrada.setSelectedIndex(0);
        txtF_Quant_Entrada.setText("");
        txtF_Data_Entrada.setText("");
        txtF_Descricao_Entrada.setText("");
        jCoBox_Entrada.requestFocus();
    }

    private void limpar_saida() {
        jCoBox_Saida.setSelectedIndex(0);
        txtF_Quant_Saida.setText("");
        txtF_Data_Saida.setText("");
        txtF_Tecnico_Saida.setText("");
        jCoBox_Saida.requestFocus();
    }

    // Metodo que cria um menu bar e menu ‘item’.
    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Outras ações");
        JMenuItem cadatroEquipamento = new JMenuItem("Cadastro de Equipamento");
        JMenuItem solicitacaoCompras = new JMenuItem("Solicitação de Compras");
        JMenuItem concertosEquipamentos = new JMenuItem("Concertos de Equipamentos");
        cadatroEquipamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                Cadastro_Equipamento tela = new Cadastro_Equipamento();
                tela.setVisible(true);

            }
        });

        solicitacaoCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SolicitacaoCompras tela = new SolicitacaoCompras();
                tela.setVisible(true);

            }
        });

        concertosEquipamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                ConcertosEquipamentos tela = new ConcertosEquipamentos();
                tela.setVisible(true);

            }
        });


        menuArquivo.add(cadatroEquipamento);
        menuArquivo.add(solicitacaoCompras);
        menuArquivo.add(concertosEquipamentos);
        menuBar.add(menuArquivo);
        setJMenuBar(menuBar);
    }
}