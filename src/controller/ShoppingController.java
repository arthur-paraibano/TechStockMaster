package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.ShoppingDAO;
import model.entities.Equipment;
import model.entities.Sector;
import model.entities.Shopping;
import util.Message;

public class ShoppingController {
    private EquipmentController equipmentController;
    private SectorController sectorController;
    private ShoppingDAO dao;

    public ShoppingController() {
        this.equipmentController = new EquipmentController();
        this.sectorController = new SectorController();
        this.dao = new ShoppingDAO();
    }

    public List<Equipment> findAllEquip() {
        return equipmentController.findAllGeneral();
    }

    public List<Equipment> findByIdRepair(Equipment item) {
        return equipmentController.findByIdRepair(item);
    }

    public List<Sector> findAllSector() {
        return sectorController.findAll();
    }

    public List<Equipment> comboboxMat(Equipment item) {
        return equipmentController.comboboxMat(item);
    }

    public List<Shopping> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: 'findAll' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Shopping> findAllPes() {
        try {
            return dao.findAllPes();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: 'findAllPes' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public boolean saveEquip(Shopping shopping) {
        try {
            boolean valou = false;
            if (shopping.getId() == 0) {
                valou = dao.addEquip(shopping) > 0;
                if (valou) {
                    Message.sucess(null, "Cadastrado com sucesso!!!");
                } else {
                    Message.errorX(null, "Falha ao cadastrar!!!");
                }
                return true;
            } else {
                valou = dao.updateSol(shopping) > 0;
                if (valou) {
                    Message.sucess(null, "Informações atualizadas!!!");
                } else {
                    Message.errorX(null, "Falha na alteração das informações!!!");
                }
                return true;
            }

        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao salvar material: 'saveEquip' ShopingController " + e.getMessage()
                            + e.getClass().getName());
            return false;
        }
    }

    public Shopping findAllId(int idConsert) {
        try {
            return dao.findById(idConsert);
        } catch (Exception e) {
            return null;
        }
    }

}
