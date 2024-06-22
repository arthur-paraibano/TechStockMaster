package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.RepairDAO;
import model.entities.Equipment;
import model.entities.Repair;
import model.entities.Sector;
import model.entities.TypeRepair;
import util.Message;

public class RepairController {
    private EquipmentController equipmentController;
    private SectorController sectorController;
    private RepairDAO dao;

    public RepairController() {
        this.equipmentController = new EquipmentController();
        this.sectorController = new SectorController();
        this.dao = new RepairDAO();
    }

    public Repair findAllId(int idConsert) {
        try {
            return dao.findById(idConsert);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addRepair(Repair repair) {
        try {
            Double qtdAtual = dao.getRepair(repair);
            if (repair.getType().equals(TypeRepair.IN)) {
                if (checkRegistration(repair)) {
                    repair.setAmount(qtdAtual + 1);
                    dao.updateRepair(repair);
                    return true;
                } else {
                    return false;
                }
            } else if (repair.getType().equals(TypeRepair.STATUS)) {
                dao.update(repair);
            }
            return true;

        } catch (Exception ex) {
            Message.errorX(null, "Erro ao atualizar equipamento em 'productEntry': " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public boolean checkRegistration(Repair repair) {
        try {
            if (!dao.findExist(repair)) {
                dao.addId(repair);
                Message.sucess(null, "Equipamento cadastrado P/ conserto!");
                return true;
            } else {
                Message.sqlErro(null, "Equipamento já cadastrado!");
                return false;
            }
        } catch (Exception ex) {
            Message.errorX(null,
                    "Erro ao inserir na tabela metodo 'add' do RepairController: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public List<Repair> findAllRepair() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: 'findAllRepair' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Equipment> findAllEquipments() {
        return equipmentController.findAllEquipments();
    }

    public List<Repair> findAllReapirNome() {
        try {
            return dao.findAllNome();
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: MovementController 'findAllReapirNome' " + e.getMessage()
                            + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Repair> findAllReapirTag() {
        try {
            return dao.findAllTag();
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: MovementController 'findAllReapirTag' " + e.getMessage()
                            + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Equipment> findByIdRepair(Equipment item) {
        return equipmentController.findByIdRepair(item);
    }

    public Sector findByNome(String nome) {
        return sectorController.findByNome(nome);
    }
}
