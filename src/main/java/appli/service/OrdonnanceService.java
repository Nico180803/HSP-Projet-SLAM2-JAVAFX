package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.Hospitalisation;
import appli.model.principal.Ordonnance;
import javafx.collections.ObservableList;


public class OrdonnanceService {
    public ObservableList<Ordonnance> findAll()
    {
        GenericDAO<Ordonnance> ordonnanceDAO = DaoFactory.getOrdonnanceDAO();
        ObservableList<Ordonnance> ordonnances;
        assert ordonnanceDAO != null;
        ordonnances = (ObservableList<Ordonnance>) ordonnanceDAO.getAll();

        return  ordonnances;
    }
    public void add(Ordonnance ordonnance){
        GenericDAO<Ordonnance> ordonnanceDAO = DaoFactory.getOrdonnanceDAO();

        assert ordonnanceDAO != null;
        ordonnanceDAO.insert(ordonnance);
    }
}
