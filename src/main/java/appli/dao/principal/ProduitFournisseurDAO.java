package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.ProduitFournisseur;

import java.util.List;

public class ProduitFournisseurDAO implements GenericDAO<ProduitFournisseur> {

    @Override
    public List<ProduitFournisseur> getAll() {
        return List.of();
    }

    @Override
    public ProduitFournisseur getById(int id) {
        return null;
    }

    @Override
    public void insert(ProduitFournisseur produitFournisseur) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(ProduitFournisseur toUpdate) {

    }
}
