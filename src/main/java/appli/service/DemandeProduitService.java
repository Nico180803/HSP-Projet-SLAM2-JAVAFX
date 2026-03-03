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
        ProduitFournisseurDAO produitFournisseurDAO = DaoFactory.getProduitFournisseurDAO();
        GenericDAO<Commande> commandeDAO = DaoFactory.getCommandeDAO();
        GenericDAO<Produit> produitDAO = DaoFactory.getProduitDAO();
        assert demandeProduitDAO != null;
        assert produitFournisseurDAO != null;
        assert commandeDAO != null;
        assert produitDAO != null;
        Utilisateur utilisateur = SessionUtilisateur.getInstance().getUtilisateur();
        System.out.println(demandeProduit.getProduit().getId());
        ProduitFournisseur moinsCher = produitFournisseurDAO.moinsCher(demandeProduit.getProduit().getId());
        Commande commande = new Commande(utilisateur,moinsCher,demandeProduit.getQuantite(),(demandeProduit.getQuantite()* moinsCher.getPrix()));
        commandeDAO.insert(commande);
        Statut statut = Statut.VALIDE;
        demandeProduit.setStatut(statut);
        demandeProduitDAO.update(demandeProduit);
        Produit produit = demandeProduit.getProduit();
        produit.setQuantite(produit.getQuantite() + demandeProduit.getQuantite());
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
