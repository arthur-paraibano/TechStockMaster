package TI.Suporte.View;


import TI.Suporte.Modal.dao.Cadatro_Mat;
import TI.Suporte.Modal.obj.Equipamentos_Ent_Est;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class AlterarCadastro extends javax.swing.JDialog {
    private javax.swing.JButton jButt_Cadastro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextF_Equipamento;
    private javax.swing.JTextField jTextF_ID;
    private java.awt.TextField txtF_Descricao;
    private java.util.List<Equipamentos_Ent_Est> dados;

    public AlterarCadastro(int id, String equipamento, String descricao) {
        initComponents();
        jTextF_ID.setText(String.valueOf(id));
        jTextF_Equipamento.setText(equipamento);
        txtF_Descricao.setText(descricao);
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JLabel jLb_Nome = new JLabel();
        JLabel jLb_Descricao = new JLabel();
        txtF_Descricao = new java.awt.TextField();
        JLabel jLb_Nome1 = new JLabel();
        jButt_Cadastro = new javax.swing.JButton();
        jTextF_Equipamento = new javax.swing.JTextField();
        JLabel jLabel1 = new JLabel();
        jTextF_ID = new javax.swing.JTextField();

        setTitle("Alterações de Informações");
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLb_Nome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLb_Nome.setText("Nome do equipamento");

        jLb_Descricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLb_Descricao.setText("Descrição");

        txtF_Descricao.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLb_Nome1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLb_Nome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLb_Nome1.setText("Alterações");

        jTextF_ID.setEnabled(false);

        jButt_Cadastro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButt_Cadastro.setText("Confirmar");
        // O metodo "addKeyListener" chama que tiver dentro quando o usuario apertar "Enter".
        jButt_Cadastro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jButt_Cadastro();
                }
            }
        });

        // Metodo chama quando o usurario "Clica" em cima do mesmo.
        jButt_Cadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButt_Cadastro();
            }
        });


        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLb_Nome1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(164, 164, 164)
                                                .addComponent(jLb_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtF_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jTextF_Equipamento, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLb_Descricao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(167, 167, 167)
                                                .addComponent(jButt_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(155, 155, 155)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLb_Nome1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(jLb_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(jTextF_Equipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLb_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtF_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButt_Cadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
        );

        jLabel1.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextF_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(398, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextF_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(272, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap()))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void jButt_Cadastro() {
        atualizarDados();
    }


    public void atualizarDados() {

        int confirma = JOptionPane.showConfirmDialog(null, "Confirma o salvamento dos dados?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {

                Equipamentos_Ent_Est objToUpdate = new Equipamentos_Ent_Est();

                objToUpdate.setId(Integer.valueOf(jTextF_ID.getText()));
                objToUpdate.setNome_equipamento(jTextF_Equipamento.getText());
                objToUpdate.setDescricao(txtF_Descricao.getText());

                Cadatro_Mat.updateCadastro(objToUpdate);

                this.dispose();

            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showConfirmDialog(null, "Erro ao salvar dados! \n\n" + ex);
            }
        }
    }

    public void setDados(List<Equipamentos_Ent_Est> dados) {
        this.dados = dados;
    }
}
