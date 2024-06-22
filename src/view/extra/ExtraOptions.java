package view.extra;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

import javax.swing.JPanel;

import util.Message;
import util.Session;
import view.extra.repair.ManagerEquipmentRepairView;
import view.extra.shopping.ManagerShoppingView;
import view.extra.stock.StockView;

public class ExtraOptions extends javax.swing.JDialog {
    private javax.swing.JButton jButt_ConEquipamento;
    private javax.swing.JButton jButt_Estoque;
    private javax.swing.JButton jButt_Exit;
    private javax.swing.JButton jButt_Compras;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private JPanel grayBackgroundPanel;

    private final String acesso = Session.getUser().getAcessoModulo();
    private final String tipouser = Session.getUser().getTipoUsuario();

    public ExtraOptions() {
        initComponents();
        setModal(true);
    }

    private void initComponents() {

        jButt_Exit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButt_ConEquipamento = new javax.swing.JButton();
        jButt_Compras = new javax.swing.JButton();
        jButt_Estoque = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Opções Extras");
        setMinimumSize(new java.awt.Dimension(300, 400));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        // Torna a tela sempre visívela
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

        this.telaCinza();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButt_Exit.setIcon(
                new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/exit.png"))));
        jButt_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_Exit();
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButt_ConEquipamento.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButt_ConEquipamento.setText("Conserto Equipamento");
        jButt_ConEquipamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_ConEquipamento();
            }
        });

        jButt_Compras.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButt_Compras.setText("Solicitação de compra TI");
        jButt_Compras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_Compras();
            }
        });

        jButt_Estoque.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jButt_Estoque.setText("Estoque");
        jButt_Estoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_Estoque();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jButt_Compras, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButt_ConEquipamento,
                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                                        .addComponent(jButt_Estoque, javax.swing.GroupLayout.PREFERRED_SIZE, 242,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(47, Short.MAX_VALUE)
                                .addComponent(jButt_Estoque)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46,
                                        Short.MAX_VALUE)
                                .addComponent(jButt_ConEquipamento)
                                .addGap(44, 44, 44)
                                .addComponent(jButt_Compras)
                                .addContainerGap(137, Short.MAX_VALUE)));

        jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Outras ações");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(jButt_Exit,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButt_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 22,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()));

        pack();
        setLocationRelativeTo(null);
    }

    private void actionPerformed_Estoque() {
        setVisible(false);
        grayBackgroundPanel.setVisible(true);
        StockView tela = new StockView();
        tela.setVisible(true);
        tela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                grayBackgroundPanel.setVisible(false);
                setVisible(true);
            }
        });
    }

    private void actionPerformed_ConEquipamento() {
        if (acesso.equals("NÃO") || tipouser.equals("COMUM")) {
            Message.erroTrist(null, "Seu usuário não tem permisão!!!");
        } else {
            setVisible(false);

            grayBackgroundPanel.setVisible(true);
            ManagerEquipmentRepairView tela = new ManagerEquipmentRepairView();
            tela.setVisible(true);
            tela.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    grayBackgroundPanel.setVisible(false);
                    setVisible(true);
                }
            });
        }
    }

    private void actionPerformed_Compras() {
        if (acesso.equals("NÃO") || tipouser.equals("COMUM")) {
            Message.erroTrist(null, "Seu usuário não tem permisão!!!");
        } else {
            setVisible(false);

            grayBackgroundPanel.setVisible(true);
            ManagerShoppingView tela = new ManagerShoppingView();
            tela.setVisible(true);
            tela.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    grayBackgroundPanel.setVisible(false);
                    setVisible(true);
                }
            });
        }
    }

    private void actionPerformed_Exit() {
        dispose();
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
}
