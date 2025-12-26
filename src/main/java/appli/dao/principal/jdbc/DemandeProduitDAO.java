package appli.dao.principal.jdbc;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.enums.Statut;
import appli.model.principal.DemandeProduit;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemandeProduitDAO implements GenericDAO<DemandeProduit> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final UtilisateurDAO utilisateurDAO = DaoFactory.getUtilisateurDAO();
    private static final ProduitDAO produitDAO = DaoFactory.getProduitDAO();

    private static final String TABLE = "demande_produit";
    private static final String REF_MEDECIN = "refMedecin";
    private static final String REF_PRODUIT = "refProduit";
    private static final String QUANTITE_DEMANDE = "quantiteDemandee";
    private static final String STATUT = "statut";
    private static final String DATE_DEMANDE = "dateDemande";

    @Override
    public List<DemandeProduit> getAll() {
        List<DemandeProduit> demandes = new ArrayList<>();
        this.sql = "SELECT * FROM " + TABLE;
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                DemandeProduit d = new DemandeProduit(
                        rs.getInt("id"),
                        utilisateurDAO.getById(rs.getInt(REF_MEDECIN)),
                        produitDAO.getById(rs.getInt(REF_PRODUIT)),
                        rs.getInt(QUANTITE_DEMANDE),
                        Statut.valueOf(rs.getString(STATUT)),
                        rs.getTimestamp(DATE_DEMANDE).toLocalDateTime()
                );
                demandes.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des demandes de produit");
        }
        return demandes;
    }

    @Override
    public DemandeProduit getById(int id) {
        this.sql = "SELECT * FROM " + TABLE + " WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new DemandeProduit(
                        rs.getInt("id"),
                        utilisateurDAO.getById(rs.getInt(REF_MEDECIN)),
                        produitDAO.getById(rs.getInt(REF_PRODUIT)),
                        rs.getInt(QUANTITE_DEMANDE),
                        Statut.valueOf(rs.getString(STATUT)),
                        rs.getTimestamp(DATE_DEMANDE).toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la demande de produit");
        }
        return null;
    }

    @Override
    public void insert(DemandeProduit demande) {
        this.sql = "INSERT INTO " + TABLE + " (" +
                REF_MEDECIN + "," +
                REF_PRODUIT + "," +
                QUANTITE_DEMANDE + "," +
                STATUT + "," +
                DATE_DEMANDE +
                ") VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            mappingBdd(demande, statement);
            statement.executeUpdate();
            System.out.println("Ajout de la demande de produit effectué");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insert dans la table " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        this.sql = "DELETE FROM " + TABLE + " WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("ligne supprimée");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la demande");
        }
    }

    @Override
    public void update(DemandeProduit toUpdate) {
        this.sql = "UPDATE " + TABLE + " SET " +
                REF_MEDECIN + " = ?," +
                REF_PRODUIT + " = ?," +
                QUANTITE_DEMANDE + " = ?," +
                STATUT + " = ?," +
                DATE_DEMANDE + " = ? " +
                "WHERE id = ?";
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            mappingBdd(toUpdate, statement);
            statement.setInt(6, toUpdate.getId());
            statement.executeUpdate();
            System.out.println("Demande de produit mise à jour");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la demande");
        }
    }

    private void mappingBdd(DemandeProduit demande, PreparedStatement statement) throws SQLException {
        statement.setInt(1, demande.getMedecinDemandeur().getId());
        statement.setInt(2, demande.getProduit().getId());
        statement.setInt(3, demande.getQuantite());
        statement.setString(4, demande.getStatut().name());
        statement.setTimestamp(5, Timestamp.valueOf(demande.getDateDemande()));
    }
}
