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

    public void update(DossierPriseEnCharge dossierPriseEnCharge) {
        GenericDAO<DossierPriseEnCharge> dossierPriseEnChargeDAO = DaoFactory.getDossierPriseEnChargeDAO();
        assert dossierPriseEnChargeDAO != null;
        dossierPriseEnChargeDAO.update(dossierPriseEnCharge);
    }

    public void addDossier(DossierPriseEnCharge dossierPriseEnCharge) {
        GenericDAO<DossierPriseEnCharge> dossierPriseEnChargeDAO = DaoFactory.getDossierPriseEnChargeDAO();

        assert dossierPriseEnChargeDAO != null;
        dossierPriseEnChargeDAO.insert(dossierPriseEnCharge);

        System.out.println("add dossier lancé");

    }
}
