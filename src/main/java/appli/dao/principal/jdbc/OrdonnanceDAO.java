package appli.dao.principal.jdbc;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.Ordonnance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdonnanceDAO implements GenericDAO<Ordonnance> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final DossierPriseEnChargeDAO dossierPriseEnChargeDAO = DaoFactory.getDossierPriseEnChargeDAO();

    private static final String TABLE = "ordonnance";
    private static final String DATE_TIME = "dateTimeOrdonnance";
    private static final String REF_DOSSIER = "refDossier";

    @Override
    public ObservableList<Ordonnance> getAll() {
        ObservableList<Ordonnance> ordonnances = FXCollections.observableArrayList();

        this.sql = "SELECT * FROM "+TABLE;
        try{
            PreparedStatement statement = db.prepareStatement(this.sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Ordonnance u = new Ordonnance(
                        rs.getInt("id"),
                        rs.getTimestamp(DATE_TIME).toLocalDateTime(),
                        dossierPriseEnChargeDAO.getById(rs.getInt(REF_DOSSIER))
                );
                ordonnances.add(u);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération des ordonnances");
        }
        return ordonnances;
    }

    @Override
    public Ordonnance getById(int id) {
        this.sql = "SELECT * FROM "+TABLE+" WHERE id=?";
        try{
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return new Ordonnance(
                        rs.getInt("id"),
                        rs.getTimestamp(DATE_TIME).toLocalDateTime(),
                        dossierPriseEnChargeDAO.getById(rs.getInt(REF_DOSSIER))
                );
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération de l'ordonnance");
        }
        return null;
    }

    @Override
    public void insert(Ordonnance ordonnance) {
        this.sql = "INSERT INTO "+ TABLE+" ("+DATE_TIME+","+REF_DOSSIER+") VALUES (?,?)";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(ordonnance, statement);


            statement.executeUpdate();
            System.out.println("Ajout de l'ordonnance efféctué");

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
    public void update(Ordonnance toUpdate) {
        this.sql = " UPDATE "+TABLE+" SET "+DATE_TIME+" = ?,"+
                REF_DOSSIER+" = ? "+
                "WHERE id = ? ";

        try (PreparedStatement statement = db.prepareStatement(this.sql)) {

            mappingBdd(toUpdate, statement);

            statement.setInt(3, toUpdate.getId());

            statement.executeUpdate();

            System.out.println("Ordonnance mise à jour");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'ordonnance");

        }
    }

    private void mappingBdd(Ordonnance toUpdate, PreparedStatement statement) throws SQLException {
        statement.setTimestamp(1, Timestamp.valueOf(toUpdate.getDateTimeOrdonnance()));
        statement.setInt(2, toUpdate.getDossierPriseEnCharge().getId());

    }
}
