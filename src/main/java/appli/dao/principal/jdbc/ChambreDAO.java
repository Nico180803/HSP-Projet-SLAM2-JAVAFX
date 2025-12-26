package appli.dao.principal.jdbc;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.model.principal.Chambre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChambreDAO implements GenericDAO<Chambre> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final String TABLE = "chambre";
    private static final String NUM = "num";
    private static final String EST_OCCUPE = "estOccupe";

    @Override
    public List<Chambre> getAll() {
        List<Chambre> chambres = new ArrayList<>();
        this.sql = "SELECT * FROM " + TABLE;
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Chambre c = new Chambre(
                        rs.getInt("id"),
                        rs.getString(NUM),
                        rs.getBoolean(EST_OCCUPE)
                );
                chambres.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des chambres : " + e.getMessage());
        }
        return chambres;
    }

    @Override
    public Chambre getById(int id) {
        this.sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Chambre(
                        rs.getInt("id"),
                        rs.getString(NUM),
                        rs.getBoolean(EST_OCCUPE)
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la chambre : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void insert(Chambre chambre) {
        this.sql = "INSERT INTO " + TABLE + " (" + NUM + "," + EST_OCCUPE + ") VALUES (?,?)";
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            mappingBdd(chambre, statement);
            statement.executeUpdate();
            System.out.println("Ajout de la chambre effectué");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insert dans la table : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        this.sql = "DELETE FROM " + TABLE + " WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Chambre supprimée");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la chambre : " + e.getMessage());
        }
    }

    @Override
    public void update(Chambre toUpdate) {
        this.sql = "UPDATE " + TABLE + " SET " + NUM + " = ?, " + EST_OCCUPE + " = ? WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            mappingBdd(toUpdate, statement);
            statement.setInt(3, toUpdate.getId());
            statement.executeUpdate();
            System.out.println("Chambre mise à jour");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la chambre : " + e.getMessage());
        }
    }

    private void mappingBdd(Chambre chambre, PreparedStatement statement) throws SQLException {
        statement.setString(1, chambre.getNum());
        statement.setBoolean(2, chambre.isEstOccupe());
    }
}
