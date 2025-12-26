package appli.dao.principal.jdbc;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.model.principal.FichePatient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class FichePatientDAO implements GenericDAO<FichePatient> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final String TABLE = "fiche_patient";
    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String NUM_SECU_SOCIAL = "numSecuSocial";
    private static final String EMAIL = "email";
    private static final String TEL = "tel";
    private static final String RUE = "rue";
    private static final String NUM_RUE = "numRue";
    private static final String VILLE = "ville";
    private static final String CP = "cp";

    @Override
    public List<FichePatient> getAll() {
        List<FichePatient> patients = new ArrayList<>();
        this.sql = "SELECT * FROM " + TABLE;
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                FichePatient p = new FichePatient(
                        rs.getInt("id"),
                        rs.getString(NOM),
                        rs.getString(PRENOM),
                        rs.getString(NUM_SECU_SOCIAL),
                        rs.getString(EMAIL),
                        rs.getString(TEL),
                        rs.getString(RUE),
                        rs.getString(NUM_RUE),
                        rs.getString(VILLE),
                        rs.getString(CP)
                );
                patients.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des fiches patients");
        }
        return patients;
    }

    @Override
    public FichePatient getById(int id) {
        this.sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new FichePatient(
                        rs.getInt("id"),
                        rs.getString(NOM),
                        rs.getString(PRENOM),
                        rs.getString(NUM_SECU_SOCIAL),
                        rs.getString(EMAIL),
                        rs.getString(TEL),
                        rs.getString(RUE),
                        rs.getString(NUM_RUE),
                        rs.getString(VILLE),
                        rs.getString(CP)
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la fiche patient");
        }
        return null;
    }

    @Override
    public void insert(FichePatient fichePatient) {
        this.sql = "INSERT INTO " + TABLE +
                " (" + NOM + "," + PRENOM + "," + NUM_SECU_SOCIAL + "," + EMAIL + "," +
                TEL + "," + RUE + "," + NUM_RUE + "," + VILLE + "," + CP + ") " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(fichePatient, statement);
            statement.executeUpdate();
            System.out.println("Ajout de la fiche patient effectué");
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
            System.out.println("Erreur lors de la suppression de la fiche patient");
        }
    }

    @Override
    public void update(FichePatient toUpdate) {
        this.sql = "UPDATE " + TABLE + " SET " +
                NOM + " = ?, " +
                PRENOM + " = ?, " +
                NUM_SECU_SOCIAL + " = ?, " +
                EMAIL + " = ?, " +
                TEL + " = ?, " +
                RUE + " = ?, " +
                NUM_RUE + " = ?, " +
                VILLE + " = ?, " +
                CP + " = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(toUpdate, statement);
            statement.setInt(10, toUpdate.getId());
            statement.executeUpdate();
            System.out.println("Fiche patient mise à jour");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la fiche patient");
        }
    }

    private void mappingBdd(FichePatient fichePatient, PreparedStatement statement) throws SQLException {
        statement.setString(1, fichePatient.getNom());
        statement.setString(2, fichePatient.getPrenom());
        statement.setString(3, fichePatient.getNumSecuSocial());
        statement.setString(4, fichePatient.getEmail());
        statement.setString(5, fichePatient.getTelephone());
        statement.setString(6, fichePatient.getRue());
        statement.setString(7, fichePatient.getNumRue());
        statement.setString(8, fichePatient.getVille());
        statement.setString(9, fichePatient.getCp());
    }
}
