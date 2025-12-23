package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.Chambre;

import java.util.List;

public class ChambreDAO implements GenericDAO<Chambre> {
    @Override
    public List<Chambre> getAll() {
        return List.of();
    }

    @Override
    public Chambre getById(int id) {
        return null;
    }

    @Override
    public void insert(Chambre chambre) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Chambre toUpdate) {

    }
}
