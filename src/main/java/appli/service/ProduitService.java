package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProduitService {

    public ObservableList<Produit> findAll() {
        GenericDAO<Produit> produitDAO = DaoFactory.getProduitDAO();
        ObservableList<Produit> produits;
        assert produitDAO != null;
        produits = FXCollections.observableArrayList(produitDAO.getAll());
        return produits;
    }

    public void insert(Produit produit) {
        GenericDAO<Produit> produitDAO = DaoFactory.getProduitDAO();
        assert produitDAO != null;
        produitDAO.insert(produit);
    }

    public void update(Produit produit) {
        GenericDAO<Produit> produitDAO = DaoFactory.getProduitDAO();
        assert produitDAO != null;
        produitDAO.update(produit);
    }
}
