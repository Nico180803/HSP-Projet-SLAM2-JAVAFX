package appli.dao.principal.jdbc;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.enums.Gravite;
import appli.model.principal.DossierPriseEnCharge;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DossierPriseEnChargeDAO implements GenericDAO<DossierPriseEnCharge> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final FichePatientDAO fichePatientDAO = DaoFactory.getFichePatientDAO();
    private static final UtilisateurDAO utilisateurDAO = DaoFactory.getUtilisateurDAO();

    private static final String TABLE = "dossier_prise_en_charge";
    private static final String DATE_TIME_ARRIVE = "dateTimeArrive";
    private static final String DESCRIPTION = "description";
    private static final String GRAVITE = "gravite";
    private static final String REF_PATIENT = "ref_patient";
    private static final String REF_CREATEDBY = "ref_createdBy";
    private static final String EST_TRAITE = "est_traite";

    @Override
    public List<DossierPriseEnCharge> getAll() {
        List<DossierPriseEnCharge> dossiers = new ArrayList<>();
        this.sql = "SELECT * FROM " + TABLE;
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DossierPriseEnCharge d = new DossierPriseEnCharge(
                        rs.getInt("id"),
                        rs.getTimestamp(DATE_TIME_ARRIVE).toLocalDateTime(),
                        rs.getString(DESCRIPTION),
                        Gravite.fromSql(rs.getString(GRAVITE)),
                        fichePatientDAO.getById(rs.getInt(REF_PATIENT)),
                        utilisateurDAO.getById(rs.getInt(REF_CREATEDBY)),
                        rs.getBoolean(EST_TRAITE)
                );
                dossiers.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des dossiers de prise en charge");
        }
        return dossiers;
    }

    @Override
    public DossierPriseEnCharge getById(int id) {
        this.sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new DossierPriseEnCharge(
                        rs.getInt("id"),
                        rs.getTimestamp(DATE_TIME_ARRIVE).toLocalDateTime(),
                        rs.getString(DESCRIPTION),
                        Gravite.fromSql(rs.getString(GRAVITE)),
                        fichePatientDAO.getById(rs.getInt(REF_PATIENT)),
                        utilisateurDAO.getById(rs.getInt(REF_CREATEDBY)),
                        rs.getBoolean(EST_TRAITE)
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du dossier de prise en charge");
        }
        return null;
    }

    @Override
    public void insert(DossierPriseEnCharge dossier) {
        this.sql = "INSERT INTO " + TABLE + " (" +
                DATE_TIME_ARRIVE + "," +
                DESCRIPTION + "," +
                GRAVITE + "," +
                REF_PATIENT + "," +
                REF_CREATEDBY + "," +
                EST_TRAITE +
                ") VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(dossier, statement);
            statement.executeUpdate();
            System.out.println("Ajout du dossier de prise en charge effectué");
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
            System.out.println("Erreur lors de la suppression du dossier");
        }
    }

    @Override
    public void update(DossierPriseEnCharge toUpdate) {
        this.sql = "UPDATE " + TABLE + " SET " +
                DATE_TIME_ARRIVE + " = ?," +
                DESCRIPTION + " = ?," +
                GRAVITE + " = ?," +
                REF_PATIENT + " = ?," +
                REF_CREATEDBY + " = ?," +
                EST_TRAITE + " = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(toUpdate, statement);
            statement.setInt(7, toUpdate.getId());
            statement.executeUpdate();
            System.out.println("Dossier de prise en charge mis à jour");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du dossier");
        }
    }

    private void mappingBdd(DossierPriseEnCharge dossier, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(dossier.getDateArrive()));
        statement.setString(2, dossier.getDescription());
        statement.setString(3, dossier.getGravite().getNiveau());
        statement.setInt(4, dossier.getPatient().getId());
        statement.setInt(5, dossier.getCreatedBy().getId());
        statement.setBoolean(6, dossier.isEstTraite());
    }
}
