package appli.controller.main;

import appli.main.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import session.SessionUtilisateur;

import java.io.IOException;

public class AdminController extends MainController {

    @FXML
    private StackPane contentPane;

    @FXML
    private AnchorPane testDroite;

    @FXML
    public Label userLabel;

    public void initialize() {
        // Configurer les panes hérités de MainController
        mainPane = contentPane;
        sidePage = testDroite;

        // Par défaut, on charge la première vue de l'admin (la table des utilisateurs)
        load("/appli/admin/TableUtilisateur.fxml");
        System.out.println("Table des utilisateurs chargée");
    }

    @FXML
    public void onPageInscriptionButtonClick() throws IOException {
        HelloApplication.changeScene("Inscription.fxml");
    }

    public void onHistoriqueConnexion(ActionEvent actionEvent) {
    }

    public void onDeconnexionButtonClick(ActionEvent event) throws IOException {
        SessionUtilisateur.getInstance().deconnecter();
        HelloApplication.changeScene("/appli/main/Login.fxml");
        System.out.println("Déconnexion et retour à l'accueil");
    }

    public void onTableUtilisateurButtonClick(ActionEvent event) throws IOException {
        load("/appli/admin/TableUtilisateur.fxml");
        System.out.println("Table des utilisateurs chargée");
    }
}