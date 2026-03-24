package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.Fournisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FournisseurService {

    public ObservableList<Fournisseur> findAll() {
        GenericDAO<Fournisseur> fournisseurDAO = DaoFactory.getFournisseurDAO();
        ObservableList<Fournisseur> fournisseurs;
        assert fournisseurDAO != null;
        fournisseurs = FXCollections.observableArrayList(fournisseurDAO.getAll());
        return fournisseurs;
    }

    public void insert(Fournisseur fournisseur) {
        GenericDAO<Fournisseur> fournisseurDAO = DaoFactory.getFournisseurDAO();
        assert fournisseurDAO != null;
        fournisseurDAO.insert(fournisseur);
    }

    public void delete(int id) {
        GenericDAO<Fournisseur> fournisseurDAO = DaoFactory.getFournisseurDAO();
        assert fournisseurDAO != null;
        fournisseurDAO.delete(id);
    }
}
