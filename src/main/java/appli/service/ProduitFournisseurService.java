package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.ProduitFournisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProduitFournisseurService {

    public ObservableList<ProduitFournisseur> findAll() {
        GenericDAO<ProduitFournisseur> dao = DaoFactory.getProduitFournisseurDAO();
        assert dao != null;
        return FXCollections.observableArrayList(dao.getAll());
    }

    public void insert(ProduitFournisseur produitFournisseur) {
        GenericDAO<ProduitFournisseur> dao = DaoFactory.getProduitFournisseurDAO();
        assert dao != null;
        dao.insert(produitFournisseur);
    }
}
