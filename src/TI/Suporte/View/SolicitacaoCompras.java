package TI.Suporte.View;


import TI.Suporte.DataBase.BD_Base;
import TI.Suporte.Modal.dao.Soli_Compras;
import TI.Suporte.Modal.obj.Soli_Compas;
import TI.Suporte.Modal.obj.TableModel_SoliCompras;
import TI.Suporte.Util.util.FormatTable;
import TI.Suporte.Util.util.NumberFilter;
import TI.Suporte.Util.util.StatusComprasCellRenderer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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

public class SolicitacaoCompras extends javax.swing.JFrame {
    private javax.swing.JButton jButt_Gerar_Solicitacoes;
    private javax.swing.JButton jButt_Salvar;
    private javax.swing.JButton jButt_Status;
    private javax.swing.JComboBox<String> jCoBox_Entrada;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Solicitacoes;
    private javax.swing.JTextField jText_Data;
    private javax.swing.JTextField jText_Descricao;
    private javax.swing.JTextField jText_Quantidade;
    private javax.swing.JTextField jText_Setor;
    private javax.swing.JTextField jText_Solicitante;
    private javax.swing.JTextField jText_Tecnico;
    private java.util.Timer timer = null;
    private TimerTask task = null;


    public SolicitacaoCompras() {
        initComponents();
        this.EntradaMaterial();
        this.atualizarDadosTabela();
        this.tabelaCfg();
        this.mouseListener();
        this.task();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exibirContEstoque();
            }
        });
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
        jTable_Solicitacoes.addMouseListener(new MouseListener() {
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
                //System.out.println("atualiza");

            }
        });
    }

    private void tabelaCfg() {
        jTable_Solicitacoes.setDefaultRenderer(Object.class, new FormatTable());
        jTable_Solicitacoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTable_Solicitacoes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        jScrollPane2.setViewportView(jTable_Solicitacoes);

        jTable_Solicitacoes.setDefaultRenderer(Object.class, new StatusComprasCellRenderer());
        jTable_Solicitacoes.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = jTable_Solicitacoes.getSelectedRow();
            if (selectedRow >= 0) {
                jTable_Solicitacoes.repaint();
            }
        });

        jTable_Solicitacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    private void initComponents() {

        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        jButt_Gerar_Solicitacoes = new javax.swing.JButton();
        jCoBox_Entrada = new javax.swing.JComboBox<>();
        JLabel jLabel3 = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel5 = new JLabel();
        JLabel jLabel6 = new JLabel();
        JLabel jLabel7 = new JLabel();
        jText_Quantidade = new javax.swing.JTextField();
        jText_Setor = new javax.swing.JTextField();
        jText_Data = new javax.swing.JTextField();
        JPanel jPanel1 = new JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Solicitacoes = new javax.swing.JTable();
        jText_Descricao = new javax.swing.JTextField();
        jText_Tecnico = new javax.swing.JTextField();
        JLabel jLabel8 = new JLabel();
        jButt_Status = new javax.swing.JButton();
        JLabel jLabel9 = new JLabel();
        jText_Solicitante = new javax.swing.JTextField();
        jButt_Salvar = new javax.swing.JButton();
        JScrollPane jScrollPane1 = new JScrollPane(jTable_Solicitacoes);

        // Configurações da Frame.
        setTitle("Solicitações de compras");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Equipamento");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Solicitação de Compra");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jButt_Gerar_Solicitacoes.setText("Relatório de Solicitações");
        jButt_Gerar_Solicitacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarRelatorio();
            }
        });

        jCoBox_Entrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Quantidade ");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Setor ");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Solicitante ");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Descrição ");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Técnico");

        jText_Quantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jText_Quantidade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AbstractDocument doc = (AbstractDocument) jText_Quantidade.getDocument();
        doc.setDocumentFilter(new NumberFilter());

        jText_Setor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jText_Data.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        try {
            jText_Data = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        jText_Data.setHorizontalAlignment(JTextField.CENTER);

        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jText_Descricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jText_Tecnico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Informações");
        jLabel8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Data");

        jText_Solicitante.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButt_Status.setText("Status");
        jButt_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButt_StatusActionPerformed(evt);
            }
        });
        jButt_Status.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    salvarDados();
                    dispose();
                    atualizarDadosTabela();
                    setVisible(true);
                }
            }
        });

        jButt_Salvar.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jButt_Salvar.setText("Salvar");
        jButt_Salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDados();
                dispose();
                atualizarDadosTabela();
                setVisible(true);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(66, 66, 66)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                        .addComponent(jCoBox_Entrada, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(44, 44, 44)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jText_Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addComponent(jText_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(6, 6, 6)
                                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(27, 27, 27)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jText_Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jText_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(10, 10, 10)
                                                                                .addComponent(jButt_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(6, 6, 6)
                                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(50, 50, 50)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jText_Data, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                                                        .addComponent(jText_Solicitante, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(6, 6, 6)
                                                                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 75, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButt_Gerar_Solicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(314, 314, 314)
                                                .addComponent(jButt_Status)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jText_Quantidade)
                                        .addComponent(jCoBox_Entrada)
                                        .addComponent(jText_Setor, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jText_Solicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel9))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jText_Tecnico)
                                                        .addComponent(jText_Data, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(jText_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(36, 36, 36)
                                .addComponent(jButt_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButt_Gerar_Solicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButt_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void salvarDados() {
        jButt_SalvarActionPerformed();
        limpar_cadatro();
    }

    private void jButt_SalvarActionPerformed() {
        Soli_Compas obj = new Soli_Compas();
        Soli_Compras dao = new Soli_Compras();

        int confirma = JOptionPane.showConfirmDialog(null, "Confirma o salvamento dos dados?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            try {
                String equipamento = Objects.requireNonNull(jCoBox_Entrada.getSelectedItem()).toString();
                obj.setEquipamento(equipamento);
                obj.setQuantidade(Double.valueOf(jText_Quantidade.getText()));
                obj.setSetor(String.valueOf(jText_Setor.getText()));
                obj.setSolicitante(String.valueOf(jText_Solicitante.getText()));
                obj.setDescricao(String.valueOf(jText_Descricao.getText()));
                obj.setTecnico(String.valueOf(jText_Tecnico.getText()));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(jText_Data.getText(), formatter);
                Date sqlDate = Date.valueOf(date);
                obj.setData(sqlDate);

                dao.add_Database(obj);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar dados! \n\n" + ex);
            }
        }
    }

    private void limpar_cadatro() {
        jCoBox_Entrada.setSelectedIndex(0);
        jText_Quantidade.setText("");
        jText_Setor.setText("");
        jText_Solicitante.setText("");
        jText_Descricao.setText("");
        jText_Tecnico.setText("");
        jText_Data.setText("");
    }

    private void jButt_StatusActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable_Solicitacoes.getSelectedRow();

        if (selectedRow != -1) {
            int id = (int) jTable_Solicitacoes.getValueAt(selectedRow, 0);
            String equipamento = (String) jTable_Solicitacoes.getValueAt(selectedRow, 1);
            Double quantSolicitada = (double) jTable_Solicitacoes.getValueAt(selectedRow, 2);
            String setor = (String) jTable_Solicitacoes.getValueAt(selectedRow, 4);

            Status_tab status = new Status_tab(id, equipamento, quantSolicitada, setor);
            status.setModal(true);
            status.setVisible(true);
            atualizarDadosTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um item da tabela");
        }
    }

    private void gerarRelatorio() {
        popularRelatorio();
    }

    private void popularRelatorio() {
        Connection connection = BD_Base.getConnection();

        try {
            String reportPath = "/Dpadua/Suporte/Relatorios/SolicitacoesCompras.jrxml";
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

    // Metodo que exibirar a tela principal novamente.
    private void exibirContEstoque() {
        ContEstoque tela = new ContEstoque();
        tela.setVisible(true);
    }

    private void atualizarDadosTabela() {
        Soli_Compras dao = new Soli_Compras();
        java.util.List<Soli_Compas> dados = dao.red();
        TableModel_SoliCompras tableModel = new TableModel_SoliCompras(dados);
        jTable_Solicitacoes.setModel(tableModel);
        jTable_Solicitacoes.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable_Solicitacoes.getColumnModel().getColumn(1).setPreferredWidth(200);

        JComboBox jCbBox_Status = new JComboBox();
        jCbBox_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Comprado", "Solicitado","Cancelado", "Recebido", "Em Análise"}));
        jTable_Solicitacoes.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jCbBox_Status));

        //redimencionar colunas
        jTable_Solicitacoes.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable_Solicitacoes.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable_Solicitacoes.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable_Solicitacoes.getColumnModel().getColumn(5).setPreferredWidth(300);
        jTable_Solicitacoes.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    label.setToolTipText(value.toString()); // Define a dica de ferramenta como o valor da célula
                }
                return c;
            }
        });
        jTable_Solicitacoes.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTable_Solicitacoes.getTableHeader().setEnabled(false);

    }

    private void EntradaMaterial() {
        // Combobox do Frame de Entrada de material
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
            jCoBox_Entrada.setModel(model_);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher JComboBox: " + e.getMessage());
        } finally {
            BD_Base.closeConnection(con_, null, resultadoConsulta_);
        }
    }
}