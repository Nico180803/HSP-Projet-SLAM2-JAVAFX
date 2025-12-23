package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.Produit;

import java.util.List;

public class ProduitDAO implements GenericDAO<Produit> {

    @Override
    public List<Produit> getAll() {
        return List.of();
    }

    @Override
    public Produit getById(int id) {
        return null;
    }

    @Override
    public void insert(Produit produit) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Produit toUpdate) {

    }
}
