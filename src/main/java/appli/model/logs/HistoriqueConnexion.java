package appli.model.logs;

import appli.controller.HistoriqueConnexionController;
import appli.dao.logs.jdbc.LogsUtilisateurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HistoriqueConnexion {

    private String date;
    private String utilisateur;
    private String action;
    private String description;

    public HistoriqueConnexion(String date, String utilisateur, String action, String description) {
        this.date = date;
        this.utilisateur = utilisateur;
        this.action = action;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public String getAction() {
        return action;
    }

    public String getDescription() {
        return description;
    }
}