package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.FichePatient;
import javafx.collections.ObservableList;


public class FichePatientService {

    public ObservableList <FichePatient> findAll()
    {

        GenericDAO<FichePatient> fichePatientsDAO = DaoFactory.getFichePatientDAO();
        ObservableList<FichePatient> fichePatients;
        assert fichePatientsDAO != null;
        fichePatients = (ObservableList<FichePatient>) fichePatientsDAO.getAll();

        return  fichePatients;
    }

    public void add(FichePatient fichePatient){
        GenericDAO<FichePatient> fichePatientsDAO = DaoFactory.getFichePatientDAO();

        assert fichePatientsDAO != null;
        fichePatientsDAO.insert(fichePatient);
    }

}
