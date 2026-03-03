package appli.dao.principal.jdbc;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.Commande;
import appli.model.principal.ProduitFournisseur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommandeDAO implements GenericDAO<Commande> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final UtilisateurDAO utilisateurDAO = DaoFactory.getUtilisateurDAO();
    private static final ProduitFournisseurDAO produitFournisseurDAO = DaoFactory.getProduitFournisseurDAO();

    private static final String TABLE = "commande";
    private static final String REF_UTILISATEUR = "refUtilisateur";
    private static final String REF_PRODUIT_FOURNISSEUR = "refProduit_Fournisseur";
    private static final String QUANTITE = "quantite";
    private static final String PRIX = "prix";

    @Override
    public List<Commande> getAll() {
        List<Commande> commandes = new ArrayList<>();
        this.sql = "SELECT * FROM" +TABLE;
        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Commande c = new Commande(
                        rs.getInt("id"),
                        utilisateurDAO.getById(rs.getInt(REF_UTILISATEUR)),
                        produitFournisseurDAO.getById(rs.getInt(REF_PRODUIT_FOURNISSEUR)),
                        rs.getInt(QUANTITE),
                        rs.getDouble(PRIX)
                );
                commandes.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des commandes : " + e.getMessage());
        }
        return commandes;
    }

    @Override
    public Commande getById(int id) {
        this.sql = "SELECT * FROM" +TABLE +" WHERE id=?";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Commande(
                        rs.getInt("id"),
                        utilisateurDAO.getById(rs.getInt(REF_UTILISATEUR)),
                        produitFournisseurDAO.getById((rs.getInt(REF_PRODUIT_FOURNISSEUR))),
                        rs.getInt(QUANTITE),
                        rs.getDouble(PRIX)
                );
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération de la commande : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void insert(Commande commande) {
        this.sql = "INSERT INTO "+ TABLE+"("+REF_UTILISATEUR+","+REF_PRODUIT_FOURNISSEUR+","+QUANTITE+","+PRIX+") VALUES (?,?,?)";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(commande, statement);

            statement.executeUpdate();
            System.out.println("Ajout de la Commande effectué");

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
            System.out.println("Erreur lors de la suppression de la table");
        }
    }

    @Override
    public void update(Commande toUpdate) {
        this.sql = " UPDATE "+TABLE+" SET "+REF_UTILISATEUR+" = ?,"+
                REF_PRODUIT_FOURNISSEUR+" = ?,"+
                QUANTITE+" = ?,"+
                PRIX+" = ? "+
                "WHERE id = ? ";

        try (PreparedStatement statement = db.prepareStatement(this.sql)) {

            mappingBdd(toUpdate, statement);


            statement.setInt(5, toUpdate.getId());

            statement.executeUpdate();

            System.out.println("Commande mis à jour");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la table");

        }
    }

    private void mappingBdd(Commande toUpdate, PreparedStatement statement) throws SQLException {
        statement.setInt(1, toUpdate.getUtilisateur().getId());
        statement.setInt(2, toUpdate.getProduitFournisseur().getId());
        statement.setInt(3, toUpdate.getQuantite());
        statement.setDouble(4, toUpdate.getPrix());

    }

}
