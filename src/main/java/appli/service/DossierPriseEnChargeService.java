package appli.service;

import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.DossierPriseEnCharge;
import javafx.collections.ObservableList;

public class DossierPriseEnChargeService {


    public ObservableList<DossierPriseEnCharge> findAll() {

        GenericDAO<DossierPriseEnCharge> dossierPriseEnChargeDAO = DaoFactory.getDossierPriseEnChargeDAO();
        ObservableList<DossierPriseEnCharge> dossierPriseEnCharges;
        assert dossierPriseEnChargeDAO != null;
        dossierPriseEnCharges = (ObservableList<DossierPriseEnCharge>) dossierPriseEnChargeDAO.getAll();

        return  dossierPriseEnCharges;
    }
}
