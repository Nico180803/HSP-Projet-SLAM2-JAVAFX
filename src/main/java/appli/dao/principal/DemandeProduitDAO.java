package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.DemandeProduit;

import java.util.List;

public class DemandeProduitDAO implements GenericDAO<DemandeProduit> {
    @Override
    public List<DemandeProduit> getAll() {
        return List.of();
    }

    @Override
    public DemandeProduit getById(int id) {
        return null;
    }

    @Override
    public void insert(DemandeProduit demandeProduit) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(DemandeProduit toUpdate) {

    }
}
