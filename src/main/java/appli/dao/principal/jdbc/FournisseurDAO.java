package appli.dao.principal.jdbc;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.model.principal.Fournisseur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FournisseurDAO implements GenericDAO<Fournisseur> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final String TABLE = "fournisseur";
    private static final String NOM = "nom";
    private static final String EMAIL = "email";
    private static final String TEL = "tel";

    @Override
    public List<Fournisseur> getAll() {
        List<Fournisseur> fournisseurs = new ArrayList<>();

        this.sql = "SELECT * FROM " + TABLE;
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Fournisseur f = new Fournisseur(
                        rs.getInt("id"),
                        rs.getString(NOM),
                        rs.getString(EMAIL),
                        rs.getString(TEL)
                );
                fournisseurs.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des fournisseurs");
        }
        return fournisseurs;
    }

    @Override
    public Fournisseur getById(int id) {
        this.sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Fournisseur(
                        rs.getInt("id"),
                        rs.getString(NOM),
                        rs.getString(EMAIL),
                        rs.getString(TEL)
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du fournisseur");
        }
        return null;
    }

    @Override
    public void insert(Fournisseur fournisseur) {
        this.sql = "INSERT INTO " + TABLE + " (" + NOM + "," + EMAIL + "," + TEL + ") VALUES (?,?,?)";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(fournisseur, statement);
            statement.executeUpdate();
            System.out.println("Ajout du fournisseur effectué");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insert dans la table " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        this.sql = "DELETE FROM " + TABLE + " WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("ligne supprimée");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du fournisseur");
        }
    }

    @Override
    public void update(Fournisseur toUpdate) {
        this.sql = "UPDATE " + TABLE + " SET " + NOM + " = ?, " + EMAIL + " = ?, " + TEL + " = ? WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(toUpdate, statement);
            statement.setInt(4, toUpdate.getId());
            statement.executeUpdate();
            System.out.println("Fournisseur mis à jour");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du fournisseur");
        }
    }

    private void mappingBdd(Fournisseur fournisseur, PreparedStatement statement) throws SQLException {
        statement.setString(1, fournisseur.getNom());
        statement.setString(2, fournisseur.getEmail());
        statement.setString(3, fournisseur.getTel());
    }
}
