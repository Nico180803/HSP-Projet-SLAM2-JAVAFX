package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.Commande;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandeService {

    public ObservableList<Commande> findAll() {
        GenericDAO<Commande> commandeDAO = DaoFactory.getCommandeDAO();
        ObservableList<Commande> commandes;
        assert commandeDAO != null;
        commandes = FXCollections.observableArrayList(commandeDAO.getAll());
        return commandes;
    }
}
