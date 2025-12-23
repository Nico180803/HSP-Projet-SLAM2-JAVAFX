package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.Ordonnance;

import java.util.List;

public class OrdonnanceDAO implements GenericDAO<Ordonnance> {

    @Override
    public List<Ordonnance> getAll() {
        return List.of();
    }

    @Override
    public Ordonnance getById(int id) {
        return null;
    }

    @Override
    public void insert(Ordonnance ordonnance) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Ordonnance toUpdate) {

    }
}
