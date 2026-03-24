package appli.dao.logs.jdbc;

import appli.config.DatabaseConnection;
import appli.model.logs.TypeAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeActionDAO {

    private final Connection db = DatabaseConnection.getMainConnexion();
    private String sql;

    private static final String TABLE = "type_action";
    private static final String CODE = "code";
    private static final String DESCRIPTION = "description";

    public TypeAction getById(int id) {
        this.sql = "SELECT * FROM " + TABLE + " WHERE id = ?";


        try (PreparedStatement statement = db.prepareStatement(this.sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new TypeAction(
                        rs.getInt("id"),
                        rs.getString(CODE),
                        rs.getString(DESCRIPTION)
                );
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du type d'action : " + e.getMessage());
        }
        return null;
    }
}
