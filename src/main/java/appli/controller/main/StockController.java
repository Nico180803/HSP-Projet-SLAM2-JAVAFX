package appli.controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

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
