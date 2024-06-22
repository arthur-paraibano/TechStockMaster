package view.equipment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JPanel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controller.EquipmentController;
import controller.SectorController;
import model.entities.Equipment;
import model.entities.Sector;
import model.entities.Tag;
import util.Message;

public class EquipmentView extends javax.swing.JDialog {
        private javax.swing.JPanel DadosGerais;
        private javax.swing.JButton jButt_Atualizar;
        private javax.swing.JButton jButt_Exit;
        private javax.swing.JButton jButt_Limpar;
        private javax.swing.JButton jButt_Salvar;
        private javax.swing.JTextField jTexF_Codigo;
        private javax.swing.JComboBox<String> jCBox_Pesquisar;
        private javax.swing.JComboBox<String> jCBox_Setor;
        private javax.swing.JComboBox<String> jCBox_Status;
        private javax.swing.JComboBox<String> jCBox_TagPesquisar;
        private javax.swing.JCheckBox jCheckBox_Equipamento;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel11;
        private javax.swing.JLabel jLabel13;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTabbedPane jTabbedPane;
        private javax.swing.JTextField jTexF_ExibirTag;
        private javax.swing.JTextField jTexF_NovaTag;
        private javax.swing.JTextArea jTexF_Observacoes;
        private javax.swing.JTextField jTexF_UnD;
        private javax.swing.JPanel tag;
        private JPanel grayBackgroundPanel;
        private EquipmentController controller = new EquipmentController();
        private List<Sector> sectores;
        private List<Tag> tags;
        private List<Equipment> equip;
        private SectorController sectorController;
        private final Equipment obj_material;

        public EquipmentView() {
                initComponents();
                setModal(true);
                this.obj_material = null;
                this.loadEquip();
                jTabbedPane.setEnabledAt(jTabbedPane.indexOfTab("Tag"), false);
                this.sectores = new ArrayList<>();
                this.tags = new ArrayList<>();
                this.sectorController = new SectorController();
                autCombobox();
                actionJcombox();
        }

        public EquipmentView(Equipment equip) {
                initComponents();
                setModal(true);
                this.obj_material = equip;
                this.loadEquip();
                jTabbedPane.setEnabledAt(jTabbedPane.indexOfTab("Tag"), false);
                this.sectores = new ArrayList<>();
                this.tags = new ArrayList<>();
                this.sectorController = new SectorController();
                autCombobox();
                actionJcombox();
                update();
        }

        private void update() {
                jLabel5.setText("Alterar Equipamentos/Ferramentas");
                jCheckBox_Equipamento.setSelected(this.obj_material.getTag().getNova() != null);
                jCheckBox_Equipamento.setEnabled(this.obj_material.getTag() == null);
                jTexF_Codigo.setText(this.obj_material.getCodigo());
                jCBox_Pesquisar.setSelectedItem(this.obj_material.getNome());
                jTexF_UnD.setText(this.obj_material.getAbreviacao_un());
                jTexF_Observacoes.setText(this.obj_material.getDescricao());
                jCBox_TagPesquisar.setSelectedItem(this.obj_material.getTag().getType());
                jTexF_ExibirTag.setText(this.obj_material.getTag().getAbreviacao());
                jTexF_NovaTag.setText(this.obj_material.getTag().getNova());
                jCBox_Status.setSelectedItem(this.obj_material.getStatus());
                jCBox_Setor.setSelectedItem(this.obj_material.getSetor().getnome());

                jCBox_Pesquisar.setEnabled(false);
                jCBox_TagPesquisar.setEnabled(false);
                jTexF_ExibirTag.setEditable(false);
                jTexF_NovaTag.setEditable(false);

                jButt_Limpar.setEnabled(false);
                jButt_Atualizar.setEnabled(false);
        }

        private void loadEquip() {
                this.equip = this.controller.comboboxEquip();
                jCBox_Pesquisar.removeAllItems();
                for (Equipment equip : equip) {
                        jCBox_Pesquisar.addItem(equip.getNome());
                }
                loadEquipDados();
        }

        private void loadEquipDados() {
                if (jCBox_Pesquisar.getItemCount() > 0) {
                        String item = String.valueOf(jCBox_Pesquisar.getSelectedItem());
                        Equipment equipm = new Equipment();
                        equipm.setNome(item);

                        List<Equipment> equip = this.controller.comboboxEquip(equipm);
                        jTexF_ExibirTag.setText("");
                        for (Equipment equipe : equip) {
                                String codigo = String.valueOf(equipe.getCodigo());
                                jTexF_Codigo.setText(codigo);
                                jTexF_UnD.setText(equipe.getAbreviacao_un());
                        }
                }
        }

        private void loadSector() {
                this.sectores = this.sectorController.findAll();
                jCBox_Setor.removeAllItems();
                for (Sector sector : sectores) {
                        jCBox_Setor.addItem(sector.getnome());
                }
        }

        private void loadTag() {
                this.tags = this.controller.findAllTag();
                jCBox_TagPesquisar.removeAllItems();
                for (Tag tag : tags) {
                        jCBox_TagPesquisar.addItem(tag.getType());
                }
        }

        private void loadTagDados() {
                if (jCBox_TagPesquisar.getItemCount() > 0) {
                        String item = String.valueOf(jCBox_TagPesquisar.getSelectedItem());

                        List<String> tagst = controller.comboboxTag(item);
                        jTexF_ExibirTag.setText("");
                        for (String tag : tagst) {
                                jTexF_ExibirTag.setText(tag);
                        }

                        Tag tag2 = controller.proxTag(item);
                        jTexF_NovaTag.setText("");
                        jTexF_NovaTag.setText(tag2.getNova());
                }
        }

        private void updateEquipmentGeral() {
                grayBackgroundPanel.setVisible(true);
                setAlwaysOnTop(false);
                setEnabled(false);
                controller.updateEquipamneto();
                grayBackgroundPanel.setVisible(false);
                setAlwaysOnTop(true);
                setEnabled(true);
        }

        private void actionJcombox() {
                jCBox_TagPesquisar.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                if (evt.getStateChange() == ItemEvent.SELECTED) {
                                        loadTagDados();

                                }
                        }
                });

                jCBox_Pesquisar.addItemListener(new java.awt.event.ItemListener() {
                        public void itemStateChanged(java.awt.event.ItemEvent evt) {
                                if (evt.getStateChange() == ItemEvent.SELECTED) {
                                        loadEquipDados();
                                }
                        }
                });
        }

        private void add() {
                String codigo = jTexF_Codigo.getText();
                String codigo_novo = String.valueOf(codigo);
                String equipamento_View = (String) jCBox_Pesquisar.getSelectedItem();
                String und = jTexF_UnD.getText();
                String obs = jTexF_Observacoes.getText();

                Equipment obj = new Equipment();
                if (this.obj_material == null) {
                        obj.setId(0);
                } else {
                        int id = obj_material.getId();
                        obj.setId(id);
                }
                obj.setCodigo(codigo_novo);
                obj.setNome(equipamento_View);
                obj.setAbreviacao_un(und);
                obj.setDescricao(obs);

                if (jCheckBox_Equipamento.isSelected()) {
                        if (jCBox_Status.getSelectedIndex() != 0) {
                                String descEquipamento = (String) jCBox_TagPesquisar.getSelectedItem();
                                String status = (String) jCBox_Status.getSelectedItem();
                                Integer index = jCBox_Setor.getSelectedIndex();

                                Tag tag2 = controller.proxTag(descEquipamento);

                                obj.setTag(tag2);
                                obj.setStatus(status);
                                Sector sector = sectores.get(index);
                                obj.setSetor(sector);

                                boolean value = controller.saveEquip(obj);
                                if (value) {
                                        if (this.obj_material == null) {
                                                actionPerformed_Limpar();
                                                jTabbedPane.setEnabledAt(
                                                                jTabbedPane.indexOfTab("Tag"),
                                                                false);
                                                jCheckBox_Equipamento.setSelected(false);
                                                jTabbedPane.setSelectedIndex(0);
                                        } else {
                                                this.dispose();
                                        }
                                }

                        } else {
                                Message.errorX(this, "Altere o 'Status' do Equipamento na aba 'TAG'!!!");
                        }
                } else {

                        boolean value = controller.saveMaterial(obj);
                        if (value) {
                                if (this.obj_material == null) {
                                        actionPerformed_Limpar();
                                } else {
                                        this.dispose();
                                }
                        }
                }
        }

        private void initComponents() {
                jPanel3 = new javax.swing.JPanel();
                jTabbedPane = new javax.swing.JTabbedPane();
                DadosGerais = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTexF_Observacoes = new javax.swing.JTextArea();
                jCBox_Pesquisar = new javax.swing.JComboBox<>();
                jTexF_Codigo = new javax.swing.JTextField();
                jLabel3 = new javax.swing.JLabel();
                jCheckBox_Equipamento = new javax.swing.JCheckBox();
                jTexF_UnD = new javax.swing.JTextField();
                tag = new javax.swing.JPanel();
                jLabel8 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jLabel11 = new javax.swing.JLabel();
                jCBox_Setor = new javax.swing.JComboBox<>();
                jCBox_TagPesquisar = new javax.swing.JComboBox<>();
                jCBox_Status = new javax.swing.JComboBox<>();
                jTexF_ExibirTag = new javax.swing.JTextField();
                jTexF_NovaTag = new javax.swing.JTextField();
                jLabel13 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                jButt_Exit = new javax.swing.JButton();
                jButt_Atualizar = new javax.swing.JButton();
                jButt_Limpar = new javax.swing.JButton();
                jButt_Salvar = new javax.swing.JButton();

                setMinimumSize(new java.awt.Dimension(800, 580));
                setResizable(false);
                setType(java.awt.Window.Type.UTILITY);
                setAlwaysOnTop(true);
                setUndecorated(true);
                addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                                setAlwaysOnTop(false);
                        }
                });

                this.telaCinza();

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));

                jTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
                jTabbedPane.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                DadosGerais.setBackground(new java.awt.Color(255, 255, 255));

                jLabel1.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel1.setText("Observações:");

                jLabel2.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel2.setText("Nome:");

                jLabel4.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel4.setText("Unidade De medida:");

                jTexF_Observacoes.setColumns(20);
                jTexF_Observacoes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jTexF_Observacoes.setRows(5);
                jScrollPane1.setViewportView(jTexF_Observacoes);

                jLabel3.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel3.setText("Código:");

                jCheckBox_Equipamento.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
                jCheckBox_Equipamento.setText("(Material ou Equipamento): É um equipamento?");
                jCheckBox_Equipamento.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                                jTabbedPane.setEnabledAt(jTabbedPane.indexOfTab("Tag"),
                                                e.getStateChange() == ItemEvent.SELECTED);
                                loadTag();
                                loadSector();
                                loadTagDados();
                        }
                });

                jTexF_UnD.setEditable(false);
                jTexF_Codigo.setEditable(false);

                javax.swing.GroupLayout DadosGeraisLayout = new javax.swing.GroupLayout(DadosGerais);
                DadosGerais.setLayout(DadosGeraisLayout);
                DadosGeraisLayout.setHorizontalGroup(
                                DadosGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(DadosGeraisLayout.createSequentialGroup()
                                                                .addGap(26, 26, 26)
                                                                .addGroup(DadosGeraisLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel2,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addComponent(jScrollPane1)
                                                                                .addGroup(DadosGeraisLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(DadosGeraisLayout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(jLabel3,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                670,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jTexF_Codigo,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                634,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(jLabel1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                480,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(DadosGeraisLayout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addComponent(jLabel4)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(jTexF_UnD,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                86,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addGroup(DadosGeraisLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                false)
                                                                                                                                .addComponent(jCheckBox_Equipamento,
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(jCBox_Pesquisar,
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                                                                0,
                                                                                                                                                634,
                                                                                                                                                Short.MAX_VALUE)))
                                                                                                .addGap(0, 96, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                DadosGeraisLayout.setVerticalGroup(
                                DadosGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(DadosGeraisLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jTexF_Codigo,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                37,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(4, 4, 4)
                                                                .addComponent(jLabel2,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jCBox_Pesquisar,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                37,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(DadosGeraisLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel4,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_UnD,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                29,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(5, 5, 5)
                                                                .addComponent(jCheckBox_Equipamento,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                36,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jLabel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                30,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                73,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(36, Short.MAX_VALUE)));

                jTabbedPane.addTab("Dados gerais", DadosGerais);

                tag.setBackground(new java.awt.Color(255, 255, 255));
                tag.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                jLabel8.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel8.setText("Setor:");

                jLabel9.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel9.setText("tag :");

                jLabel11.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel11.setText("Status:");

                jCBox_Status
                                .setModel(new javax.swing.DefaultComboBoxModel<>(
                                                new String[] { " ", "Em Uso", "Estoque", "Inativo" }));

                jTexF_ExibirTag.setEditable(false);
                jTexF_NovaTag.setEditable(false);

                jLabel13.setFont(new java.awt.Font("Perpetua Titling MT", 1, 12)); // NOI18N
                jLabel13.setText("Descrição do Equipamento:");

                javax.swing.GroupLayout tagLayout = new javax.swing.GroupLayout(tag);
                tag.setLayout(tagLayout);
                tagLayout.setHorizontalGroup(
                                tagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(tagLayout.createSequentialGroup()
                                                                .addGroup(tagLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                tagLayout.createSequentialGroup()
                                                                                                                .addGap(0, 70, Short.MAX_VALUE)
                                                                                                                .addComponent(jLabel13,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                214,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                tagLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addContainerGap(
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                Short.MAX_VALUE)
                                                                                                                .addGroup(tagLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(jLabel11,
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                94,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jLabel8,
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                94,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addComponent(jLabel9,
                                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                94,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(tagLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jCBox_Setor,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                400,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_NovaTag,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                103,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(tagLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jCBox_TagPesquisar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                260,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18, 18, 18)
                                                                                                .addComponent(jTexF_ExibirTag,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                105,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jCBox_Status,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                103,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(106, Short.MAX_VALUE)));
                tagLayout.setVerticalGroup(
                                tagLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(tagLayout.createSequentialGroup()
                                                                .addGap(39, 39, 39)
                                                                .addGroup(tagLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jCBox_TagPesquisar,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel13,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_ExibirTag,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                38,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(tagLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel9,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jTexF_NovaTag,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                33,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(26, 26, 26)
                                                                .addGroup(tagLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jCBox_Status,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel11,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(27, 27, 27)
                                                                .addGroup(tagLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jCBox_Setor,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(jLabel8,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                30,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(119, 119, 119)));

                jTabbedPane.addTab("Tag", tag);

                jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 26)); // NOI18N
                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setText("Cadastro de Equipamentos/Ferramentas");

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
                jButt_Exit.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        actionPerformed_Exit();
                                }
                        }
                });

                jButt_Atualizar.setText("Atualizar Equip.");
                jButt_Atualizar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                actionPerformed_AtualizarEquip();
                        }
                });
                jButt_Atualizar.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        actionPerformed_AtualizarEquip();
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
                jButt_Limpar.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                        actionPerformed_Limpar();
                                }
                        }
                });

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

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTabbedPane)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                .addComponent(jLabel5,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                548,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(jButt_Exit,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                22,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(jButt_Atualizar,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                144,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(188, 188, 188)
                                                                                                .addGroup(jPanel3Layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(jPanel3Layout
                                                                                                                                .createSequentialGroup()
                                                                                                                                .addGap(24, 24, 24)
                                                                                                                                .addComponent(jButt_Limpar,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                100,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addGap(20, 20, 20))
                                                                                                                .addComponent(jButt_Salvar,
                                                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                144,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                jPanel3Layout.setVerticalGroup(
                                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addGap(24, 24, 24)
                                                                                                .addComponent(jLabel5))
                                                                                .addGroup(jPanel3Layout
                                                                                                .createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addComponent(jButt_Exit,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                22,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jTabbedPane)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel3Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                jPanel3Layout
                                                                                                                .createSequentialGroup()
                                                                                                                .addComponent(jButt_Salvar,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                34,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(jButt_Limpar,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                31,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(jButt_Atualizar,
                                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                34,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap()));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(jPanel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap()));

                pack();
                setLocationRelativeTo(null);
        }

        private void actionPerformed_AtualizarEquip() {
                this.updateEquipmentGeral();
        }

        private void actionPerformed_Salvar() {
                this.add();
        }

        private void actionPerformed_Limpar() {
                if (jCheckBox_Equipamento.isSelected()) {
                        jCBox_Pesquisar.setSelectedIndex(0);
                        jTexF_Observacoes.setText("");
                        jCBox_TagPesquisar.setSelectedIndex(0);
                        jTexF_NovaTag.setText("");
                        jCBox_Status.setSelectedIndex(0);
                        jCBox_Setor.setSelectedIndex(0);
                } else {
                        jCBox_Pesquisar.setSelectedIndex(0);
                        jTexF_Observacoes.setText("");
                }
        }

        private void actionPerformed_Exit() {
                dispose();
        }

        private void telaCinza() {
                grayBackgroundPanel = new JPanel();
                grayBackgroundPanel.setBackground(new Color(192, 192, 192, 128));
                grayBackgroundPanel.setVisible(false);
                getContentPane().add(grayBackgroundPanel);
                addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                                grayBackgroundPanel.setBounds(0, 0, getWidth(), getHeight());
                        }
                });
        }

        private void autCombobox() {
                AutoCompleteDecorator.decorate(jCBox_Pesquisar);
                AutoCompleteDecorator.decorate(jCBox_TagPesquisar);
                AutoCompleteDecorator.decorate(jCBox_Setor);
        }
}
