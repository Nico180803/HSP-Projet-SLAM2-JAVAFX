package appli.dao.principal;

import appli.dao.GenericDAO;
import appli.model.principal.Utilisateur;

import java.util.List;

public class UtilisateurDAO implements GenericDAO<Utilisateur> {

    @Override
    public List<Utilisateur> getAll() {
        return List.of();
    }

    @Override
    public Utilisateur getById(int id) {
        return null;
    }

    @Override
    public void insert(Utilisateur utilisateur) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Utilisateur toUpdate) {

    }
}
