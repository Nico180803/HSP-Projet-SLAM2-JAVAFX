package appli.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getMainConnexion() {
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
        return cnx;
    }

    public static Connection getLogsConnexion() {
        Connection cnx = null;
        try {
            cnx = DriverManager.getConnection(
                    DatabaseConfig.getUrlLogs(),
                    DatabaseConfig.getUsername(),
                    DatabaseConfig.getPassword()
            );
            System.out.println("Connexion réussie à la base de données !");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
        return cnx;
    }
}

