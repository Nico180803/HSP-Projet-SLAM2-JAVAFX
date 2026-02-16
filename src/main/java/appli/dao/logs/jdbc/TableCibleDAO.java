package appli.dao.logs.jdbc;

import appli.model.logs.TableCible;
import appli.utils.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TableCibleDAO {

    public TableCible findByNom(String nom) {
        String sql = "SELECT * FROM table_cible WHERE nom = ?";

        try (Connection cnx = ConnexionBDD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {

            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TableCible(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
