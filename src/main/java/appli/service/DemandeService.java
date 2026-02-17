package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.DemandeProduit;
import appli.model.principal.Ordonnance;
import javafx.collections.ObservableList;

public class DemandeService {
    public ObservableList<DemandeProduit> findAll()
    {
        GenericDAO<DemandeProduit> demandeDAO = DaoFactory.getDemandeProduitDAO();
        ObservableList<DemandeProduit> demandeProduits;
        assert demandeDAO != null;
        demandeProduits = (ObservableList<DemandeProduit>) demandeDAO.getAll();

        return  demandeProduits;
    }
    public void add(DemandeProduit demandeProduit){
        GenericDAO<DemandeProduit> demandeDAO = DaoFactory.getDemandeProduitDAO();

        assert demandeDAO != null;
        demandeDAO.insert(demandeProduit);
    }
}
