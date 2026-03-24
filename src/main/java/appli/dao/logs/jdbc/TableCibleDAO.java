package appli.dao.logs.jdbc;

import appli.config.DatabaseConnection;
import appli.model.logs.TableCible;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableCibleDAO {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final String TABLE = "table_cible";
    private static final String NOM_TABLE = "nom_table";
    private static final String DESCRIPTION = "description";

    public TableCible getById(int id) {
        this.sql = "SELECT * FROM " + TABLE + " WHERE id = ?";


        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new TableCible(
                        rs.getInt("id"),
                        rs.getString(NOM_TABLE),
                        rs.getString(DESCRIPTION)
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la table cible : " + e.getMessage());
        }
        return null;
    }
}