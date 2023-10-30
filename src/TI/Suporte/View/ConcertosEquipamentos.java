package TI.Suporte.View;

import TI.Suporte.DataBase.BD_Base;
import TI.Suporte.Modal.dao.Concertos;
import TI.Suporte.Modal.obj.*;
import TI.Suporte.Util.util.DataUtil;
import TI.Suporte.Util.util.NumberFilter;
import TI.Suporte.Util.util.StatusConsertosCellRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TimerTask;

public class ConcertosEquipamentos extends javax.swing.JFrame {
    private java.awt.Button btt_Editar_Status;
    private java.awt.Button btt_Gerar_Relatorio;
    private java.awt.Button btt_SalvarDados;
    private javax.swing.JComboBox<String> jCoBox_Equipamento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTbl_Informacoes;
    private javax.swing.JFormattedTextField txtF_Data_Entrada;
    private java.awt.TextField txtF_Descricao_Problema;
    private javax.swing.JFormattedTextField txtFild_Modelo;
    private javax.swing.JFormattedTextField txtFild_Tecnico;
    private javax.swing.JFormattedTextField txtFild_setor;
    private java.util.Timer timer = null;
    private TimerTask task = null;


    public ConcertosEquipamentos() {
        initComponents();
        this.comboBoxEquipamento();
        this.tableCfg();
        this.atualizarDadosTabela();
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
                //System.out.println("atualiza");

            }
        });
    }


    private void tableCfg() {
        jTbl_Informacoes.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTbl_Informacoes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        jTbl_Informacoes.setDefaultRenderer(Object.class, new StatusConsertosCellRenderer());
        jTbl_Informacoes.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = jTbl_Informacoes.getSelectedRow();
            if (selectedRow >= 0) {
                jTbl_Informacoes.repaint();
            }
        });
        jTbl_Informacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPane1.setViewportView(jTbl_Informacoes);
    }


    private void initComponents() {

        JLabel jLabel2 = new JLabel();
        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel();
        JLabel jLb_Equipamento = new JLabel();
        jCoBox_Equipamento = new javax.swing.JComboBox<>();
        JLabel jLb_Modelo = new JLabel();
        txtFild_Modelo = new javax.swing.JFormattedTextField();
        txtF_Descricao_Problema = new java.awt.TextField();
        JLabel jLb_Descricao = new JLabel();
        txtF_Data_Entrada = new javax.swing.JFormattedTextField();
        JLabel jLb_Data_Entrada = new JLabel();
        JLabel jLb_Tecnico = new JLabel();
        txtFild_Tecnico = new javax.swing.JFormattedTextField();
        JLabel jLb_Setor = new JLabel();
        txtFild_setor = new javax.swing.JFormattedTextField();
        btt_SalvarDados = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbl_Informacoes = new javax.swing.JTable();
        JPanel jPanel2 = new JPanel();
        btt_Gerar_Relatorio = new java.awt.Button();
        btt_Editar_Status = new java.awt.Button();

        // Configurações da Frame.
        setTitle("Concerto(s) de Equipamento");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Concertos de Equipamentos");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Adicionar Equipamento");

        jLb_Equipamento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLb_Equipamento.setText("Nome do equipamento");

        jLb_Modelo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLb_Modelo.setText("Modelo (Referência)");

        txtFild_Modelo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFild_Modelo.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFild_Modelo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtF_Descricao_Problema.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLb_Descricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLb_Descricao.setText("Descrição do problema");

        txtF_Data_Entrada.setFont(new Font("Dialog", Font.PLAIN, 14));
        AbstractDocument doc_ = (AbstractDocument) txtF_Data_Entrada.getDocument();
        doc_.setDocumentFilter(new NumberFilter());
        try {
            txtF_Data_Entrada = new JFormattedTextField(new MaskFormatter("##/##/####"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        txtF_Data_Entrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtF_Data_Entrada.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtF_Data_Entrada.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));

        jLb_Data_Entrada.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLb_Data_Entrada.setText("Data de entrada");

        jLb_Tecnico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLb_Tecnico.setText("Nome (Tecnico)");

        txtFild_Tecnico.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFild_Tecnico.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFild_Tecnico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLb_Setor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLb_Setor.setText("Setor");

        txtFild_setor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtFild_setor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtFild_setor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btt_SalvarDados.setLabel("Salvar dados");
        btt_SalvarDados.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    salvarDados();
                    dispose();
                    atualizarDadosTabela();
                    setVisible(true);
                }
            }
        });
        btt_SalvarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDados();
                dispose();
                atualizarDadosTabela();
                setVisible(true);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(txtF_Descricao_Problema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jCoBox_Equipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel1Layout.createSequentialGroup().addGap(12, 12, 12).addComponent(jLb_Equipamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addGap(30, 30, 30).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(txtFild_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel1Layout.createSequentialGroup().addGap(11, 11, 11).addComponent(jLb_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(40, 40, 40).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(txtFild_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLb_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(35, 35, 35).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(txtF_Data_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLb_Data_Entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(35, 35, 35).addComponent(txtFild_setor)).addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE).addComponent(jLb_Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(62, 62, 62)).addGroup(jPanel1Layout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLb_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btt_SalvarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(476, 476, 476)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLb_Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLb_Data_Entrada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(txtF_Data_Entrada, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE).addComponent(txtFild_setor))).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLb_Equipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLb_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jCoBox_Equipamento).addComponent(txtFild_Tecnico, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLb_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(txtFild_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLb_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(txtF_Descricao_Problema, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(btt_SalvarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(34, 34, 34)));

        btt_Gerar_Relatorio.setLabel("Gerar Relatório de Concertos");
        btt_Gerar_Relatorio.setEnabled(false);
        btt_Gerar_Relatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btt_Editar_Status.setLabel("Editar Status");
        btt_Editar_Status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterStatus();

            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btt_Gerar_Relatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(294, 294, 294).addComponent(btt_Editar_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(23, 23, 23)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addContainerGap(20, Short.MAX_VALUE).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(btt_Editar_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(btt_Gerar_Relatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(12, 12, 12)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1).addContainerGap()).addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, Short.MAX_VALUE).addGap(18, 18, 18).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

        pack();
        setLocationRelativeTo(null);
    }

    private void salvarDados() {
        jButt_SalvarActionPerformed();
        limpar_cadatro();
    }

    private void jButt_SalvarActionPerformed() {
        ConcertosEq obj = new ConcertosEq();
        Concertos dao = new Concertos();

        int confirma = JOptionPane.showConfirmDialog(null, "Confirma o salvamento dos dados?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            try {
                String equipamento = Objects.requireNonNull(jCoBox_Equipamento.getSelectedItem()).toString();
                obj.setNomeDoEquipamento(equipamento);
                obj.setModeloReferancia(String.valueOf(txtFild_Modelo.getText()));
                obj.setTecnico(String.valueOf(txtFild_Tecnico.getText()));

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate date = LocalDate.parse(txtF_Data_Entrada.getText(), formatter);
                Date sqlDate = Date.valueOf(date);
                obj.setData(sqlDate);

                obj.setSetor(String.valueOf(txtFild_setor.getText()));
                obj.setDescricao(String.valueOf(txtF_Descricao_Problema.getText()));

                dao.add_Database(obj);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar dados! \n\n" + ex);
            }
        }
    }

    private void limpar_cadatro() {
        jCoBox_Equipamento.setSelectedIndex(0);
        txtFild_Modelo.setText("");
        txtFild_Tecnico.setText("");
        txtF_Data_Entrada.setText("");
        txtFild_setor.setText("");
        txtF_Descricao_Problema.setText("");
    }

    private void alterStatus() {
        int selectedRow = jTbl_Informacoes.getSelectedRow();

        if (selectedRow != -1) {
            int id = (int) jTbl_Informacoes.getValueAt(selectedRow, 0);
            String equipamento = (String) jTbl_Informacoes.getValueAt(selectedRow, 1);
            Date data = DataUtil.formatBrToDate(String.valueOf(jTbl_Informacoes.getValueAt(selectedRow, 3)));

            Status_Conserto status = new Status_Conserto(id, equipamento, data);
            status.setModal(true);
            status.setVisible(true);
            atualizarDadosTabela();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um item da tabela");
        }
    }

    private void atualizarDadosTabela() {
        // Recuperação dos dados do banco de dados e configuração do modelo personalizado da tabela.
        Concertos dao = new Concertos();
        java.util.List<ConcertosEq> dados = dao.red();
        TableModel_Concertos tableModel = new TableModel_Concertos(dados);
        jTbl_Informacoes.setModel(tableModel);

        JComboBox jCbBox_Status = new JComboBox();
        jCbBox_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{ "Aguardando envio", "Em Conserto", "Retorno"}));
        jTbl_Informacoes.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jCbBox_Status));

        //redimencionar colunas
        jTbl_Informacoes.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTbl_Informacoes.getColumnModel().getColumn(5).setPreferredWidth(300);
        jTbl_Informacoes.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
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
        jTbl_Informacoes.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTbl_Informacoes.getTableHeader().setEnabled(false);

        // System.out.println( jTbl_Informacoes.getColumnModel().getColumn(1).getPreferredWidth());
        //jTbl_Informacoes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    // Metodo que exibirar a tela principal novamente.
    private void exibirContEstoque() {
        ContEstoque tela = new ContEstoque();
        tela.setVisible(true);
    }

    private void comboBoxEquipamento() {
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
            jCoBox_Equipamento.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher JComboBox: " + e.getMessage());
        } finally {
            BD_Base.closeConnection(con, null, resultadoConsulta);
        }
    }
}
