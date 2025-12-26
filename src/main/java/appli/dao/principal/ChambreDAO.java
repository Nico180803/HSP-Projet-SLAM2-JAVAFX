package appli.dao.principal;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.model.principal.Chambre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ChambreDAO implements GenericDAO<Chambre> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final String TABLE = "chambre";


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
        this.sql = "INSERT INTO 'table' () VALUES ()";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);

        }catch (SQLException e){
            System.out.println("Erreur lors de l'insert dans la table "+ e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        this.sql = "DELETE FROM "+TABLE+" WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("ligne supprimé");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur");
        }
    }

    @Override
    public void update(Chambre toUpdate) {

    }
}
