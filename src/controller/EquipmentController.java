package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import model.dao.EquipmentDAO;
import model.entities.Equipment;
import model.entities.Movement;
import model.entities.Tag;
import util.Message;
import view.login.Loading;

public class EquipmentController {
    private final EquipmentDAO dao;
    private final TagController tController;
    private final MovementController mController;

    public EquipmentController() {
        this.dao = new EquipmentDAO();
        this.tController = new TagController();
        this.mController = new MovementController();
    }

    public List<Equipment> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: EquipmetController 'findAll' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Equipment> findAllEquipments() {
        try {
            return dao.findAllEquipments();
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: EquipmetController 'findAllEquipments' " + e.getMessage()
                            + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Equipment> findAllGeneral() {
        try {
            return dao.findAllGeneral();
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: EquipmetController 'findAll' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Equipment> findAllTab() {
        try {
            return dao.findAllTab();
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: EquipmetController 'findAll' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public Equipment findById(int idEquip) {
        try {
            return dao.findById(idEquip);
        } catch (Exception e) {
            Message.errorX(null, "Erro ao buscar Equipamentoas! 'findByID'\nError: " + e.getMessage());
            return null;
        }
    }

    public List<Equipment> findByIdRepair(Equipment item) {
        try {
            return dao.findByIdEquipm(item);
        } catch (Exception e) {
            Message.errorX(null, "Erro ao buscar o usuário!\nError: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Equipment findByNome(String name) {
        try {
            return dao.findByNome(name);
        } catch (Exception e) {
            Message.errorX(null, "Erro ao buscar o equipamentos!\nError: " + e.getMessage());
            return null;
        }
    }

    /***
     * Faz a pesquisa no DAO e popula o COMBOBOX dos NOMES dos EQUIPAMENTOS.
     */
    public List<Equipment> comboboxEquip() {
        try {
            return dao.findAllGeral();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: Equipamento 'findAll' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Equipment> comboboxEquip(Equipment item) {
        try {
            return dao.getEquipDetails(item);
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: Equipamento 'comboboxEquip' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Equipment> comboboxMat(Equipment item) {
        try {
            return dao.getEquipMat(item);
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: Equipamento 'comboboxEquip' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public Tag proxTag(String descricao) {
        return tController.nextSequence(descricao);
    }

    /***
     * Metodo chama o Controller da Tag para passar as informações da tabela TAG
     */
    public List<Tag> findAllTag() {
        return tController.findAll();
    }

    /***
     * CASO SEJA UM MATERIAL
     * Pega as informações do cadastro de material e solicita ao DAO o salvamento,
     * tela RegisterMateriais
     */
    public boolean saveMaterial(Equipment equipment) {
        try {
            boolean valou = false;
            if (equipment.getId() == 0) {
                if (!dao.checkMaterial(equipment)) {
                    valou = dao.registerMaterial(equipment) > 0;
                    if (valou) {
                        Message.sucess(null, "Cadastrado com sucesso!!!");
                    } else {
                        Message.errorX(null, "Falha ao cadastrar!!!");
                    }
                } else {
                    Message.errorX(null, "Material já cadastrado!!!");
                }
                return true;

            } else {
                valou = dao.updateMate(equipment) > 0;
                if (valou) {
                    Message.sucess(null, "Material  atualizado com Sucesso!!!");
                } else {
                    Message.errorX(null, "Falha na alteração Material!!!");
                }
                return true;
            }
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao salvar equipamento: 'saveMaterial' " + e.getMessage() + e.getClass().getName());
            return false;
        }

    }

    /***
     * CASO SEJA UM EQUIPAMENTO
     * Pega as informações do cadastro de material e solicita ao DAO o salvamento,
     * tela RegisterMateriais
     */
    public boolean saveEquip(Equipment equipment) {
        try {
            boolean valou = false;
            if (equipment.getId() == 0) {
                valou = dao.addEquip(equipment) > 0;
                if (valou) {
                    Message.sucess(null, "Cadastrado com sucesso!!!");
                } else {
                    Message.errorX(null, "Falha ao cadastrar!!!");
                }
                return true;
            } else {
                valou = dao.updateEquip(equipment) > 0;
                if (valou) {
                    Message.sucess(null, "Equipamento alterado!!!");
                } else {
                    Message.errorX(null, "Falha na alteração Equipamento!!!");
                }
                return true;
            }

        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao salvar equipamento: 'saveEquip' " + e.getMessage() + e.getClass().getName());
            return false;
        }
    }

    /***
     * Chama o TagConroller para obter os dados das Tag.
     */
    public List<String> comboboxTag(String item) {
        return tController.combobox(item);
    }

    /***
     * Metodos para atualizar os equipamentos atraves do banco de dados da Lykos.
     */
    public void updateEquipamneto() {
        Loading loading = new Loading();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() { 
                try {
                    List<Equipment> list = new ArrayList<>(dao.lista());
                    for (Equipment eq : list) {
                        dao.equipmentRegistration(eq);
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Message.sucess(null, "Lista de equipamentos atualizados com sucesso!!!");
                        }
                    });
                    loading.dispose();
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            ex.printStackTrace();
                            Message.errorX(null, "Erro 'ContView = atualizarEquipamento' " + ex.getMessage());
                        }
                    });
                    loading.dispose();
                }

            }
        });
        thread.start();
        loading.setVisible(true);
    }

    public boolean productEntry(List<Movement> movements) {
        return mController.productEntry(movements);
    }
}
