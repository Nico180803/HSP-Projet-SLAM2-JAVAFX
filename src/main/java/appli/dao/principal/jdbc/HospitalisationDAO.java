package appli.dao.principal.jdbc;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.Hospitalisation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalisationDAO implements GenericDAO<Hospitalisation> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final DossierPriseEnChargeDAO dossierPriseEnChargeDAO = DaoFactory.getDossierPriseEnChargeDAO();
    private static final ChambreDAO chambreDAO = DaoFactory.getChambreDAO();

    private static final String TABLE = "hospitalisation";
    private static final String REF_DOSSIER = "refDossier";
    private static final String REF_CHAMBRE = "refChambre";
    private static final String DATE_DEBUT = "dateDebut";
    private static final String DATE_FIN = "dateFin";

    @Override
    public List<Hospitalisation> getAll() {
        List<Hospitalisation> hospitalisations = new ArrayList<>();

        this.sql = "SELECT * FROM " + TABLE;
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Hospitalisation h = new Hospitalisation(
                        rs.getInt("id"),
                        dossierPriseEnChargeDAO.getById(rs.getInt(REF_DOSSIER)),
                        chambreDAO.getById(rs.getInt(REF_CHAMBRE)),
                        rs.getTimestamp(DATE_DEBUT).toLocalDateTime(),
                        rs.getTimestamp(DATE_FIN).toLocalDateTime()
                );
                hospitalisations.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des hospitalisations");
        }

        return hospitalisations;
    }

    @Override
    public Hospitalisation getById(int id) {
        this.sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Hospitalisation(
                        rs.getInt("id"),
                        dossierPriseEnChargeDAO.getById(rs.getInt(REF_DOSSIER)),
                        chambreDAO.getById(rs.getInt(REF_CHAMBRE)),
                        rs.getTimestamp(DATE_DEBUT).toLocalDateTime(),
                        rs.getTimestamp(DATE_FIN).toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'hospitalisation");
        }
        return null;
    }

    @Override
    public void insert(Hospitalisation hospitalisation) {
        this.sql = "INSERT INTO " + TABLE +
                " (" + REF_DOSSIER + "," + REF_CHAMBRE + "," + DATE_DEBUT + "," + DATE_FIN + ") " +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);

            mappingBdd(hospitalisation, statement);

            statement.executeUpdate();
            System.out.println("Ajout de l'hospitalisation effectué");

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
            System.out.println("Erreur lors de la suppression de l'utilisateur");
        }
    }

    @Override
    public void update(Hospitalisation toUpdate) {
        this.sql = " UPDATE " + TABLE + " SET " +
                REF_DOSSIER + " = ?," +
                REF_CHAMBRE + " = ?," +
                DATE_DEBUT + " = ?," +
                DATE_FIN + " = ? " +
                "WHERE id = ? ";

        try {
            PreparedStatement statement = db.prepareStatement(this.sql);

            mappingBdd(toUpdate, statement);

            statement.setInt(5, toUpdate.getId());

            statement.executeUpdate();
            System.out.println("Hospitalisation mise à jour");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'hospitalisation");
        }
    }

    private void mappingBdd(Hospitalisation toUpdate, PreparedStatement statement) throws SQLException {
        statement.setInt(1, toUpdate.getDossierPriseEnCharge().getId());
        statement.setInt(2, toUpdate.getChambre().getId());
        statement.setTimestamp(3, Timestamp.valueOf(toUpdate.getDateDebut()));
        statement.setTimestamp(4, Timestamp.valueOf(toUpdate.getDateFin()));
    }

}
