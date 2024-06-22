package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.SupervisorDAO;
import model.entities.Supervisor;
import util.Message;

public class SupervisorController {
    private final SupervisorDAO dao;

    public SupervisorController() {
        this.dao = new SupervisorDAO();
    }

    public List<Supervisor> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao preencher ao listar dados: 'findAll' " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Supervisor findById(int idUser) {
        try {
            return this.dao.findById(idUser);
        } catch (Exception e) {
            Message.errorX(null, "Erro ao buscar o nomes!\nError: " + e.getMessage());
            return null;
        }
    }

    public List<Supervisor> findAllTab() {
        try {
            return dao.findAllTab();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao preencher ao listar dados: 'findAll' " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean add(Supervisor obj) {
        try {
            if (obj.getId() == 0) {
                this.dao.add(obj);
                Message.sucess(null, "Cadastro realizado com sucesso!!!");
                return true;
            } else {
                this.dao.update(obj);
                Message.sucess(null, "Nome alterado!!!");
                return true;
            }
        } catch (Exception e) {
            Message.errorX(null, "Erro ao inserir dados: 'add' " + e.getMessage());
            return  false;
        }
    }
}
