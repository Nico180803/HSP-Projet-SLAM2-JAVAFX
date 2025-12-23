package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.Hospitalisation;
import appli.model.principal.Hospitalisation;

import java.util.List;

public class HospitalisationDAO implements GenericDAO<Hospitalisation> {
    @Override
    public List<Hospitalisation> getAll() {
        return List.of();
    }

    @Override
    public Hospitalisation getById(int id) {
        return null;
    }

    @Override
    public void insert(Hospitalisation Hospitalisation) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Hospitalisation toUpdate) {

    }
}
