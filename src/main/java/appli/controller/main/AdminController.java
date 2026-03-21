package appli.controller.main;

import appli.main.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import session.SessionUtilisateur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import appli.controller.HistoriqueConnexionController;
import appli.dao.logs.jdbc.LogsUtilisateurDAO;
import appli.model.logs.LogsUtilisateur;
import appli.model.logs.HistoriqueConnexion;

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

    @FXML
    public void onHistoriqueConnexion(ActionEvent actionEvent) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/appli/admin/HistoriqueConnexion.fxml"));
        Parent root = loader.load();

        HistoriqueConnexionController controller = loader.getController();

        LogsUtilisateurDAO logsDAO = new LogsUtilisateurDAO();
        ObservableList<HistoriqueConnexion> data = FXCollections.observableArrayList();

        for (LogsUtilisateur log : logsDAO.getAll()) {
            String utilisateur = (log.getUtilisateur() != null) ? String.valueOf(log.getUtilisateur().getId()) : "Inconnu";

            data.add(new HistoriqueConnexion(
                    (log.getDateAction() != null) ? log.getDateAction().toString() : "",
                    utilisateur,
                    (log.getTypeAction() != null) ? log.getTypeAction().toString() : "",
                    (log.getDetails() != null) ? log.getDetails() : ""
            ));
        }

        controller.setData(data);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
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