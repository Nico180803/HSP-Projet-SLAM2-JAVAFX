package appli.controller.main;

import appli.main.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import session.SessionUtilisateur;

import java.io.IOException;

public class StockController extends MainController {

    public AnchorPane testDroite;
    @FXML
    private StackPane contentPane;

    @FXML
    public Label userLabel;

    public void initialize() {
        mainPane = contentPane;
        sidePage = testDroite;
    }

    public void onDeconnexionButtonClick(ActionEvent event) throws IOException {
        SessionUtilisateur.getInstance().deconnecter();
        HelloApplication.changeScene("/appli/main/Login.fxml");
        System.out.println("Déconnexion et retour à l'accueil");
    }

    @FXML
    public void onListeStock(ActionEvent event) {
        load("/appli/stock/TableProduit.fxml");
        System.out.println("Stock produit cliqué");
    }

    @FXML
    public void onValidRefus(ActionEvent event) {
        load("/appli/stock/TableDemandeProduit.fxml");
        System.out.println("Validation/Refus Demande produit cliqué");
    }

    @FXML
    public void onFournisseur(ActionEvent event) {
        load("/appli/stock/TableFournisseur.fxml");
        System.out.println("Table fournisseur cliqué");
    }
}
