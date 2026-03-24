package appli.service;

import appli.dao.GenericDAO;
import appli.dao.principal.jdbc.ProduitFournisseurDAO;
import appli.factory.DaoFactory;
import appli.model.enums.Statut;
import appli.model.principal.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import session.SessionUtilisateur;

public class DemandeProduitService {

    public ObservableList<DemandeProduit> findAll() {
        GenericDAO<DemandeProduit> demandeProduitDAO = DaoFactory.getDemandeProduitDAO();
        ObservableList<DemandeProduit> demandeProduits;
        assert demandeProduitDAO != null;
        demandeProduits = FXCollections.observableArrayList(demandeProduitDAO.getAll());
        return demandeProduits;
    }

    public void acceptDemandeProduit(DemandeProduit demandeProduit) {
        GenericDAO<DemandeProduit> demandeProduitDAO = DaoFactory.getDemandeProduitDAO();
        GenericDAO<Produit> produitDAO = DaoFactory.getProduitDAO();
        assert demandeProduitDAO != null;
        assert produitDAO != null;
        System.out.println(demandeProduit.getProduit().getId());
        Statut statut = Statut.VALIDE;
        demandeProduit.setStatut(statut);
        demandeProduitDAO.update(demandeProduit);
        Produit produit = demandeProduit.getProduit();
        produit.setQuantite(produit.getQuantite() - demandeProduit.getQuantite());
        produitDAO.update(produit);

    }

    public void refusDemandeProduit(DemandeProduit demandeProduit) {
        GenericDAO<DemandeProduit> demandeProduitGenericDAO = DaoFactory.getDemandeProduitDAO();
        assert demandeProduitGenericDAO != null;
        Statut statut = Statut.REFUSE;
        demandeProduit.setStatut(statut);
        demandeProduitGenericDAO.update(demandeProduit);
    }
}
