package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.Fournisseur;

import java.util.List;

public class FournisseurDAO implements GenericDAO<Fournisseur> {

    @Override
    public List<Fournisseur> getAll() {
        return List.of();
    }

    @Override
    public Fournisseur getById(int id) {
        return null;
    }

    @Override
    public void insert(Fournisseur Fournisseur) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Fournisseur toUpdate) {

    }
}
