package appli.dao.principal.jdbc;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.model.enums.Dangerosite;
import appli.model.principal.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO implements GenericDAO<Produit> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final String TABLE = "produit";
    private static final String LIBELLE = "libelle";
    private static final String DESCRIPTION = "description";
    private static final String DANGEROSITE = "dangerosite";
    private static final String QUANTITE = "quantite";


    @Override
    public List<Produit> getAll() {
        List<Produit> produits = new ArrayList<>();

        this.sql = "SELECT * FROM "+TABLE;
        try{
            PreparedStatement statement = db.prepareStatement(this.sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Produit u = new Produit(
                        rs.getInt("id"),
                        rs.getString(LIBELLE),
                        rs.getString(DESCRIPTION),
                        Dangerosite.fromSql(rs.getString(DANGEROSITE)),
                        rs.getInt(QUANTITE)
                );
                produits.add(u);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération des produits");
        }
        return produits;
    }

    @Override
    public Produit getById(int id) {
        this.sql = "SELECT * FROM "+TABLE+" WHERE id = ?";
        try{
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return new Produit(
                        rs.getInt("id"),
                        rs.getString(LIBELLE),
                        rs.getString(DESCRIPTION),
                        Dangerosite.fromSql(rs.getString(DANGEROSITE)),
                        rs.getInt(QUANTITE)
                );
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération du produit");
        }
        return null;
    }

    @Override
    public void insert(Produit produit) {
        this.sql = "INSERT INTO "+ TABLE+" ("+LIBELLE+","+DESCRIPTION+","+DANGEROSITE+","+QUANTITE+") VALUES (?,?,?,?)";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            mappingBdd(produit, statement);


            statement.executeUpdate();
            System.out.println("Ajout du produit efféctué");

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
            System.out.println("Erreur lors de la suppression du produit");
        }
    }

    @Override
    public void update(Produit toUpdate) {
        this.sql = " UPDATE "+TABLE+" SET "+LIBELLE+" = ?,"+
                DESCRIPTION+" = ?,"+
                DANGEROSITE+" = ?,"+
                QUANTITE+" = ? "+
                "WHERE id = ? ";

        try (PreparedStatement statement = db.prepareStatement(this.sql)) {

            mappingBdd(toUpdate, statement);

            statement.setInt(5, toUpdate.getId());

            statement.executeUpdate();

            System.out.println("Utilisateur mis à jour");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur");

        }
    }

    private void mappingBdd(Produit toUpdate, PreparedStatement statement) throws SQLException {
        statement.setString(1, toUpdate.getLibelle());
        statement.setString(2, toUpdate.getDescription());
        statement.setString(3, toUpdate.getDangerosite().getNiveau());
        statement.setInt(4, toUpdate.getQuantite());
    }
}
