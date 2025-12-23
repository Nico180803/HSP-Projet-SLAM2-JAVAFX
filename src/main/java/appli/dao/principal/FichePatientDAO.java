package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.FichePatient;

import java.util.List;

public class FichePatientDAO implements GenericDAO<FichePatient> {

    @Override
    public List<FichePatient> getAll() {
        return List.of();
    }

    @Override
    public FichePatient getById(int id) {
        return null;
    }

    @Override
    public void insert(FichePatient FichePatient) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(FichePatient toUpdate) {

    }
}
