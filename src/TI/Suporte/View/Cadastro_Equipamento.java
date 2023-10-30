package TI.Suporte.View;

import TI.Suporte.Modal.dao.Cadatro_Mat;
import TI.Suporte.Modal.dao.Estoque_Mat;
import TI.Suporte.Modal.obj.Equipamentos_Ent_Est;
import TI.Suporte.Modal.obj.TableModel_Cadastro;
import TI.Suporte.Util.util.StatusConsertosCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;

public class Cadastro_Equipamento extends javax.swing.JFrame {
    private java.awt.Button jButt_EditarRegistro;
    private java.awt.Button jButt_ExcluirRegistro;
    private javax.swing.JButton jButt_Cadastro;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private JTable jTbl_Informacoes;
    private javax.swing.JTextField jTextF_Equipamento;
    private java.awt.TextField txtF_Descricao;
    private TimerTask task = null;

    public Cadastro_Equipamento() {
        initComponents();
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
        jTbl_Informacoes.setFont(new Font("SansSerif", Font.PLAIN, 12));
        jScrollPane1.setViewportView(jTbl_Informacoes);

        jTbl_Informacoes.setDefaultRenderer(Object.class, new StatusConsertosCellRenderer());
        jTbl_Informacoes.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = jTbl_Informacoes.getSelectedRow();
            if (selectedRow >= 0) {
                jTbl_Informacoes.repaint();
            }
        });
        jTbl_Informacoes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JPanel jPanel1 = new JPanel();
        JLabel jLb_Nome = new JLabel();
        JLabel jLb_Descricao = new JLabel();
        txtF_Descricao = new java.awt.TextField();
        JLabel jLb_Nome1 = new JLabel();
        jButt_Cadastro = new javax.swing.JButton();
        jTextF_Equipamento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbl_Informacoes = new JTable();
        jButt_EditarRegistro = new java.awt.Button();
        jButt_ExcluirRegistro = new java.awt.Button();

        jScrollPane2.setViewportView(jEditorPane1);

        // // Configurações da Frame.
        setTitle("Cadatro");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONTROLE DE ESTOQUE ");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jLb_Nome.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLb_Nome.setText("Nome do equipamento");
        jLb_Descricao.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jLb_Descricao.setText("Descrição: EX: (UN) - ___________________");
        jLb_Nome1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        jLb_Nome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLb_Nome1.setText("Cadastro de equipamento");

        txtF_Descricao.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));

        jButt_Cadastro.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jButt_Cadastro.setText("Cadastrar");

        // O metodo "addKeyListener" chama que tiver dentro quando o usuario apertar "Enter".
        jButt_Cadastro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    salvar_dados();
                    dispose();
                    atualizarDadosTabela();
                    setVisible(true);

                }
            }
        });
        // Metodo chama quando o usurario "Clica" em cima do mesmo.
        jButt_Cadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvar_dados();
                dispose();
                atualizarDadosTabela();
                setVisible(true);
            }
        });

        // Configurações do Layout da tela.
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLb_Nome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(180, 180, 180)
                                                .addComponent(jLb_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(159, 159, 159)
                                                .addComponent(jTextF_Equipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtF_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLb_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(17, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButt_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(172, 172, 172))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLb_Nome1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLb_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jTextF_Equipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLb_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtF_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButt_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButt_EditarRegistro.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
        jButt_EditarRegistro.setLabel("Editar registro");
        jButt_EditarRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTbl_Informacoes.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) jTbl_Informacoes.getValueAt(selectedRow, 0);
                    String equipamento = (String) jTbl_Informacoes.getValueAt(selectedRow, 1);
                    String descricao = (String) jTbl_Informacoes.getValueAt(selectedRow, 2);

                    AlterarCadastro tela = new AlterarCadastro(id, equipamento, descricao);
                    tela.setModal(true);
                    tela.setVisible(true);
                    atualizarDadosTabela();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um item");
                }
            }
        });

        jButt_ExcluirRegistro.setFont(new java.awt.Font("Dialog", Font.PLAIN, 14));
        jButt_ExcluirRegistro.setLabel("Excluir regristro");
        jButt_ExcluirRegistro.setEnabled(false);

        // Configurações do Layout da tela.
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(167, 167, 167))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(282, 282, 282)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(371, 371, 371)
                                                .addComponent(jButt_EditarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(103, 103, 103)
                                                .addComponent(jButt_ExcluirRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(318, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButt_EditarRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButt_ExcluirRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void salvar_dados() {
        importar_dados_BD();
    }

    // Metodo que cadastras novos equipamentos no Data Base
    private void importar_dados_BD() {
        Cadatro_Mat dao = new Cadatro_Mat();
        Equipamentos_Ent_Est obj = new Equipamentos_Ent_Est();

        int confirma = JOptionPane.showConfirmDialog(null, "Confirma o salvamento dos dados?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {

                obj.setNome_equipamento(jTextF_Equipamento.getText());
                obj.setDescricao(txtF_Descricao.getText());

                dao.cadastro_database(obj);
                limpar_cadatro();

            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(null, "Erro ao salvar dados! \n\n" + ex);
            }
        }
    }

    private void limpar_cadatro() {
        jTextF_Equipamento.setText("");
        txtF_Descricao.setText("");
    }

    // Metodo que exibirar a tela principal novamente.
    private void exibirContEstoque() {
        ContEstoque tela = new ContEstoque();
        tela.setVisible(true);
    }

    private void atualizarDadosTabela() {
        Estoque_Mat dao = new Estoque_Mat();
        java.util.List<Equipamentos_Ent_Est> dados = dao.red();
        TableModel_Cadastro tableModel = new TableModel_Cadastro(dados);

        jTbl_Informacoes.setModel(tableModel);

        //redimencionar colunas
        jTbl_Informacoes.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTbl_Informacoes.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTbl_Informacoes.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTbl_Informacoes.getTableHeader().setEnabled(false);
    }
}
