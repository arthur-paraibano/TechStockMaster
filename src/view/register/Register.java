package view.register;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

import javax.swing.JPanel;

import view.equipment.ManangerEquipmentView;
import view.sector.ManagerSectorView;
import view.supervisor.ManagerSupervisorView;
import view.tag.ManagerTagView;
import view.user.ManangerUserView;

public class Register extends javax.swing.JDialog {
    private javax.swing.JButton jButt_Supervisor;
    private javax.swing.JButton jButt_Equipameto;
    private javax.swing.JButton jButt_Setor;
    private javax.swing.JButton jButt_Tag;
    private javax.swing.JButton jButt_Usuario;
    private javax.swing.JButton jButt_Voltar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private JPanel grayBackgroundPanel;

    public Register() {
        initComponents();
        setModal(true);
        // Defina a janela para não ficar sempre no topo
        setAlwaysOnTop(false);

        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setSize(width, height - 40);
        setLocation(0, 0);
        setResizable(false);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButt_Usuario = new javax.swing.JButton();
        jButt_Equipameto = new javax.swing.JButton();
        jButt_Setor = new javax.swing.JButton();
        jButt_Supervisor = new javax.swing.JButton();
        jButt_Voltar = new javax.swing.JButton();
        jButt_Tag = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setTitle("Cadastros");
        setBackground(new java.awt.Color(255, 255, 255));

        this.telaCinza();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Baskerville Old Face", Font.BOLD, 26));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Cadastros");

        jButt_Usuario.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        jButt_Usuario.setIcon(
                new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/usuário.png"))));
        jButt_Usuario.setText("Usuarios");
        jButt_Usuario.setToolTipText("");
        jButt_Usuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Usuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButt_Usuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_Usuario();
            }
        });

        jButt_Equipameto.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        jButt_Equipameto.setIcon(new javax.swing.ImageIcon(
                Objects.requireNonNull(getClass().getResource("/resources/equipamento.png"))));
        jButt_Equipameto.setText("Equipamentos");
        jButt_Equipameto.setToolTipText("");
        jButt_Equipameto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Equipameto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButt_Equipameto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_Equipameto();
            }
        });

        jButt_Setor.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        jButt_Setor.setIcon(
                new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/setor.png"))));
        jButt_Setor.setText("Setores");
        jButt_Setor.setToolTipText("");
        jButt_Setor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Setor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButt_Setor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_Setor();
            }
        });

        jButt_Supervisor.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButt_Supervisor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/supervisor.png"))); // NOI18N
        jButt_Supervisor.setText("Supervisor");
        jButt_Supervisor.setToolTipText("");
        jButt_Supervisor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Supervisor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButt_Supervisor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_Supervisor();
            }
        });

        jButt_Voltar.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        jButt_Voltar.setIcon(
                new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/voltar.png"))));
        jButt_Voltar.setToolTipText("");
        jButt_Voltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Voltar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButt_Voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_Voltar();
            }
        });

        jButt_Tag.setFont(new java.awt.Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        jButt_Tag.setIcon(new javax.swing.ImageIcon(
                Objects.requireNonNull(getClass().getResource("/resources/adicionarEtiqueta.png"))));
        jButt_Tag.setText("Tag");
        jButt_Tag.setToolTipText("");
        jButt_Tag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButt_Tag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButt_Tag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformed_Tag();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jButt_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButt_Equipameto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButt_Setor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButt_Tag, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButt_Supervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButt_Voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButt_Tag, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButt_Usuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButt_Equipameto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButt_Setor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButt_Supervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButt_Voltar)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cadastramento.png")));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1074, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(2, 15, 47));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }                        

    private void actionPerformed_Usuario() {
        // Mostra o fundo cinza temporário
        grayBackgroundPanel.setVisible(true);
        // Abre a tela RedefineLogin
        ManangerUserView tela = new ManangerUserView();
        tela.setVisible(true);
        tela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                // Esconde o fundo cinza quando a tela RedefineLogin for fechada
                grayBackgroundPanel.setVisible(false);
            }
        });
    }

    private void actionPerformed_Equipameto() {
        // Mostra o fundo cinza temporário
        grayBackgroundPanel.setVisible(true);
        // Abre a tela RedefineLogin
        // RegisterMateriais tela = new RegisterMateriais();
        ManangerEquipmentView tela = new ManangerEquipmentView();
        tela.setVisible(true);
        tela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                // Esconde o fundo cinza quando a tela RedefineLogin for fechada
                grayBackgroundPanel.setVisible(false);
            }
        });
    }

    private void actionPerformed_Setor() {
        grayBackgroundPanel.setVisible(true);
        // SectorView tela = new SectorView();
        ManagerSectorView tela = new ManagerSectorView();
        tela.setVisible(true);
        tela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                grayBackgroundPanel.setVisible(false);
            }
        });
    }

    private void actionPerformed_Supervisor() {
        grayBackgroundPanel.setVisible(true);
        ManagerSupervisorView tela = new ManagerSupervisorView();
        tela.setVisible(true);
        tela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                grayBackgroundPanel.setVisible(false);
            }
        });
    }

    private void actionPerformed_Tag() {
        grayBackgroundPanel.setVisible(true);
        ManagerTagView tela = new ManagerTagView();
        tela.setVisible(true);
        tela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                grayBackgroundPanel.setVisible(false);
            }
        });
    }

    private void actionPerformed_Voltar() {
        // exibirTelaPrincipal();
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