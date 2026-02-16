package appli.dao.logs.jdbc;

import appli.model.logs.TypeAction;
import appli.utils.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TypeActionDAO {

    public TypeAction findByAction(String action) {
        String sql = "SELECT * FROM type_action WHERE action = ?";

        try (Connection cnx = ConnexionBDD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, action);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TypeAction(
                        rs.getInt("id"),
                        rs.getString("action"),
                        rs.getString("description")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
