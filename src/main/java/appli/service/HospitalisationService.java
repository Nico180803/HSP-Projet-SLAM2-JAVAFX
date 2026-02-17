package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.FichePatient;
import appli.model.principal.Hospitalisation;
import javafx.collections.ObservableList;

public class HospitalisationService {
    public ObservableList<Hospitalisation> findAll()
    {
        GenericDAO<Hospitalisation> hospitalisationDAO = DaoFactory.getHospitalisationDAO();
        ObservableList<Hospitalisation> hospitalisations;
        assert hospitalisationDAO != null;
        hospitalisations = (ObservableList<Hospitalisation>) hospitalisationDAO.getAll();

        return  hospitalisations;
    }
    public void add(Hospitalisation hospitalisation){
        GenericDAO<Hospitalisation> hospitalisationDAO = DaoFactory.getHospitalisationDAO();

        assert hospitalisationDAO != null;
        hospitalisationDAO.insert(hospitalisation);
    }
}
