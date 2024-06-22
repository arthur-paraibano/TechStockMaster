package controller;

import java.util.ArrayList;
import java.util.List;

import model.dao.TagDao;
import model.entities.Tag;
import util.Message;

public class TagController {

    private final TagDao dao;

    public TagController() {
        this.dao = new TagDao();
    }

    /***
     * Metoro trás todos os nome 'DESC_TAG' para popular e tarzer os restantes das
     * informações
     */
    public List<Tag> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: Tags 'findAllTag' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public List<Tag> findAllTab() {
        try {
            return dao.findAllTab();
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao listar: EquipmetController 'findAllTab' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public void insert(Tag obj) {
        try {
            dao.add(obj);
            Message.sucess(null,"Cadastro realizado com sucesso!!!");
        } catch (Exception e) {
            Message.errorX(null,
                    "Erro ao cadastrar TAG 'insert'" + e.getMessage() + e.getClass().getName());
        }
    }

    /***
     * Faz a pesquisa no DAO e popula o COMBOBOX das TAG's anterior e NOVA, passando
     * nome do item como String.
     */
    public List<String> combobox(String item) {
        try {
            return dao.getTagDetails(item);
        } catch (Exception e) {
            Message.errorX(null, "Erro ao listar: TagController 'combobox' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }

    public Tag nextSequence(String description) {
        try {

            Tag next = dao.next(description); // verifico se EXISTE UM PROXIMO SEQUENCIAL
            if (next == null) {
                return dao.first(description); // incluo o primeiro sequencial
            }
            return next;
        } catch (Exception e) {
            Message.errorX(null, "Erro ao buscar: Tags 'nextTag' " + e.getMessage() + e.getClass().getName());
            return null;
        }
    }

}
