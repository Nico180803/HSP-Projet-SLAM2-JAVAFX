package appli.dao.principal;

import appli.config.DatabaseConnection;
import appli.dao.GenericDAO;
import appli.model.enums.Role;
import appli.model.principal.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO implements GenericDAO<Utilisateur> {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final String TABLE = "utilisateur";
    private static final String NOM = "nom";
    private static final String PRENOM = "prenom";
    private static final String EMAIL = "email";
    private static final String MOT_DE_PASSE = "mot_de_passe";
    private static final String ROLE = "role";
    private static final String IS_ACTIVE = "isActive";

    @Override
    public List<Utilisateur> getAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        this.sql = "SELECT * FROM "+TABLE;
        try{
            PreparedStatement statement = db.prepareStatement(this.sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Utilisateur u = new Utilisateur(
                        rs.getInt("id"),
                        rs.getString(NOM),
                        rs.getString(PRENOM),
                        rs.getString(EMAIL),
                        rs.getString(MOT_DE_PASSE),
                        Role.valueOf(rs.getString(ROLE)),
                        rs.getBoolean(IS_ACTIVE)
                );
                utilisateurs.add(u);
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération des utilisateurs");
        }
        return utilisateurs;
    }

    @Override
    public Utilisateur getById(int id) {
        this.sql = "SELECT * FROM "+TABLE+" WHERE id = ?";
        try{
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString(NOM),
                        rs.getString(PRENOM),
                        rs.getString(EMAIL),
                        rs.getString(MOT_DE_PASSE),
                        Role.valueOf(rs.getString(ROLE)),
                        rs.getBoolean(IS_ACTIVE)
                );
            }
        }catch (SQLException e){
            System.out.println("Erreur lors de la récupération des utilisateurs");
        }
        return null;
    }

    @Override
    public void insert(Utilisateur utilisateur) {
        this.sql = "INSERT INTO "+ TABLE+" ("+NOM+","+PRENOM+","+EMAIL+","+MOT_DE_PASSE+","+ROLE+","+IS_ACTIVE+") VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = db.prepareStatement(this.sql);
            statement.setString(1,utilisateur.getNom());
            statement.setString(2,utilisateur.getPrenom());
            statement.setString(3,utilisateur.getEmail());
            statement.setString(4,utilisateur.getMdp());
            statement.setString(5,utilisateur.getRole().name());
            statement.setBoolean(6,utilisateur.isActive());
            statement.executeUpdate();
            System.out.println("Ajout de l'utilisateur efféctué");

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
            System.out.println("Utilisateur supprimé");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur");
        }
    }

    @Override
    public void update(Utilisateur toUpdate) {
        this.sql = " UPDATE "+TABLE+" SET "+NOM+" = ?,"+
            PRENOM+" = ?,"+
            EMAIL+" = ?,"+
            MOT_DE_PASSE+" = ?,"+
            ROLE+" = ?,"+
            IS_ACTIVE+" = ? "+
        "WHERE id = ? ";

        try (PreparedStatement statement = db.prepareStatement(this.sql)) {

            statement.setString(1, toUpdate.getNom());
            statement.setString(2, toUpdate.getPrenom());
            statement.setString(3, toUpdate.getEmail());
            statement.setString(4, toUpdate.getMdp());
            statement.setString(5, toUpdate.getRole().name()); // enum → String
            statement.setBoolean(6, toUpdate.isActive());

            statement.setInt(7, toUpdate.getId());

            statement.executeUpdate();

                System.out.println("Utilisateur mis à jour");

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur");

        }
    }
}
