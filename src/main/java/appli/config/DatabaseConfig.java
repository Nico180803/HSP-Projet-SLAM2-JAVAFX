package appli.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private static final Properties prop = new Properties();

    static {
        try {
            prop.load(DatabaseConfig.class.getResourceAsStream("/appli/db.properties"));
            System.out.println("Database config chargé");
        } catch (IOException e) {
            System.out.println("Erreur : Database Config non chargé");
            throw new RuntimeException(e);
        }
    }


    public  static String getUsername()
    {
        return prop.getProperty("db.username");
    }

    public  static String getPassword()
    {
        return prop.getProperty("db.password");
    }

    public  static String getUrlMain()
    {
        return prop.getProperty("db.url.main");
    }

    public  static String getUrlLogs()
    {
        return prop.getProperty("db.url.logs");
    }

}
