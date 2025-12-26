package appli.dao.principal;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.factory.DaoFactory;
import appli.model.principal.Produit;
import appli.model.principal.ProduitFournisseur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitFournisseurDAO implements GenericDAO<ProduitFournisseur> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final ProduitDAO produitDAO = DaoFactory.getProduitDAO();
    private static final FournisseurDAO fournisseurDAO = DaoFactory.getFournisseurDAO();

    private static final String TABLE = "produit_fournisseur";
    private static final String REF_PRODUIT = "refProduit";
    private static final String REF_FOURNISSEUR = "refFournisseur";
    private static final String PRIX = "prix";


    @Override
    public List<ProduitFournisseur> getAll() {
        List<ProduitFournisseur> produitFournisseur = new ArrayList<>();

        this.sql = "SELECT * FROM "+TABLE;
        try{
            PreparedStatement statement = db.prepareStatement(this.sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                ProduitFournisseur u = new ProduitFournisseur(
                        rs.getInt("id"),
                        produitDAO.getById(rs.getInt(REF_PRODUIT)),
                        fournisseurDAO.getById(rs.getInt(REF_FOURNISSEUR)),
                        rs.getDouble(PRIX)
                );
                produitFournisseur.add(u);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération des produitFournisseurs");
        }
        return produitFournisseur;
    }

    @Override
    public ProduitFournisseur getById(int id) {
        this.sql = "SELECT * FROM "+TABLE+" WHERE id = ?";
        try{
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return new ProduitFournisseur(
                        rs.getInt("id"),
                        produitDAO.getById(rs.getInt(REF_PRODUIT)),
                        fournisseurDAO.getById(rs.getInt(REF_FOURNISSEUR)),
                        rs.getDouble(PRIX)
                );
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération des produitFournisseurs");
        }
        return null;
    }

    @Override
    public void insert(ProduitFournisseur produitFournisseur) {
        this.sql = "INSERT INTO "+ TABLE+" ("+REF_PRODUIT+","+REF_FOURNISSEUR+","+PRIX+") VALUES (?,?,?)";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(produitFournisseur, statement);


            statement.executeUpdate();
            System.out.println("Ajout du produitFournisseur effectué");

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
    public void update(ProduitFournisseur toUpdate) {
        this.sql = " UPDATE "+TABLE+" SET "+REF_PRODUIT+" = ?,"+
                REF_FOURNISSEUR+" = ?,"+
                PRIX+" = ? "+
                "WHERE id = ? ";

        try (PreparedStatement statement = db.prepareStatement(this.sql)) {

            mappingBdd(toUpdate, statement);


            statement.setInt(4, toUpdate.getId());

            statement.executeUpdate();

            System.out.println("Produit Fournisseur mis à jour");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la table");

        }
    }

    private void mappingBdd(ProduitFournisseur toUpdate, PreparedStatement statement) throws SQLException {
        statement.setInt(1, toUpdate.getProduit().getId());
        statement.setInt(2, toUpdate.getFournisseur().getId());
        statement.setDouble(3, toUpdate.getPrix());
    }
}
