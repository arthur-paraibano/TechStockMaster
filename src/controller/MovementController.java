package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.EquipmentDAO;
import model.dao.MovementDAO;
import model.entities.Movement;
import model.entities.TypeMovement;
import util.Message;

public class MovementController {
    private final MovementDAO dao;
    private final EquipmentDAO equipmentDAO;

    public MovementController() {
        this.dao = new MovementDAO();
        this.equipmentDAO = new EquipmentDAO();
    }

    public boolean productEntry(List<Movement> movements) {
        try {
            for (Movement movement : movements) {
                Double qtdAtual = equipmentDAO.getSaldo(movement.getEquipment());

                if (movement.getType().equals(TypeMovement.IN)) {
                    dao.addEntry(movement);
                    movement.getEquipment().setQuantidade(qtdAtual + movement.getAmount());
                    equipmentDAO.updateSaldo(movement.getEquipment());
                } else if (movement.getType().equals(TypeMovement.OUT)) {
                    if (movement.getAmount() > qtdAtual) {
                        Message.errorX(null, "Quantidade insuficiente no estoque, saldo atual: " + qtdAtual);
                        return false;
                    } else {
                        dao.addEntry(movement);
                        movement.getEquipment().setQuantidade(qtdAtual - movement.getAmount());
                        equipmentDAO.updateSaldo(movement.getEquipment());
                    }
                }
            }
            return true;

        } catch (Exception ex) {
            Message.errorX(null, "Erro ao atualizar equipamento em 'productEntry': " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }


    public List<Movement> findAllRepair() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: 'findAllRepair' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }
}
