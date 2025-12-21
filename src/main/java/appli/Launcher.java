package appli;

import appli.config.DatabaseConnection;
import appli.main.HelloApplication;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        DatabaseConnection.getConnexion();
        Application.launch(HelloApplication.class, args);
    }
}
