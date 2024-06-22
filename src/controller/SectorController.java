package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.SectorDAO;
import model.entities.Sector;
import util.Message;

public class SectorController {

    // instanciar o dao
    private final SectorDAO dao;

    public SectorController() {
        this.dao = new SectorDAO();
    }

    public List<Sector> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: 'findAll' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Sector> findAllTab() {
        try {
            return dao.findAllTab();
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: EquipmetController 'findAllTab' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public Sector findById(int idUser) {
        try {
            return dao.findById(idUser);
        } catch (Exception e) {
            Message.errorX(null, "Erro ao buscar o setores!\nError: " + e.getMessage());
            return null;
        }
    }

    public boolean add(Sector obj) {
        try {
            if (obj.getId() == 0) {
                this.dao.add(obj);
                Message.sucess(null, "Setor salvos com sucesso!");
                return true;
            } else {
                this.dao.update(obj);
                Message.sucess(null, "Setor alterado!!!");
                return true;
            }
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: 'Controller(add)' " + e.getMessage());
            return false;
        }
    }

    public Sector findByNome(String nome) {
        try {
            return dao.findByName(nome);
        } catch (Exception e) {
            Message.errorX(null, "Erro ao buscar o nomes!\nError: " + e.getMessage());
            return null;
        }
    }

}