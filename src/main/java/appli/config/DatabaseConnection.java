package appli.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static void getConnexion() {
        Connection cnx = null;
        try {
            cnx = DriverManager.getConnection(
                    DatabaseConfig.getUrlMain(),
                    DatabaseConfig.getUsername(),
                    DatabaseConfig.getPassword()
            );
            System.out.println("Connexion réussie à la base de données !");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }
}

