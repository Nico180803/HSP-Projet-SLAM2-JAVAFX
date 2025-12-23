package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.DossierPriseEnCharge;

import java.util.List;

public class DossierPriseEnChargeDAO implements GenericDAO<DossierPriseEnCharge> {
    @Override
    public List<DossierPriseEnCharge> getAll() {
        return List.of();
    }

    @Override
    public DossierPriseEnCharge getById(int id) {
        return null;
    }

    @Override
    public void insert(DossierPriseEnCharge dossierPriseEnCharge) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(DossierPriseEnCharge toUpdate) {

    }
}
