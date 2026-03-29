package appli.controller.stock;

import appli.model.enums.Dangerosite;
import appli.model.principal.Produit;
import appli.service.ProduitService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableProduitController implements Initializable {
    @FXML
    private TableView<Produit> tableauProduit;
    @FXML
    private TextField fieldLibelle;
    @FXML
    private TextField fieldDescription;
    @FXML
    private ComboBox<Dangerosite> comboDangerosite;
    @FXML
    private Spinner<Integer> spinnerQuantiteAjout;
    @FXML
    private Label labelProduitSelectionne;
    @FXML
    private Spinner<Integer> spinnerQuantiteRetrait;

    private ObservableList<Produit> produits;
    private ProduitService produitService = new ProduitService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        produits = produitService.findAll();
        if (produits.isEmpty()) {
            System.out.println("Aucun produit trouvé");
        }
        tableauProduit.setItems(produits);

        String[][] colonnes = {
                {"Libelle", "libelle"},
                {"Description", "description"},
                {"Dangerosité", "dangerosite"},
                {"Quantité", "quantite"},
        };

        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Produit, String> maCol = new TableColumn<>(colonnes[i][0]);
            maCol.setCellValueFactory(
                    new PropertyValueFactory<Produit, String>(colonnes[i][1])
            );
            tableauProduit.getColumns().add(maCol);
        }
        tableauProduit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        tableauProduit.setRowFactory(tv -> new TableRow<Produit>() {
            @Override
            protected void updateItem(Produit item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setStyle("");
                } else if (item.getQuantite() < 20) {
                    setStyle("-fx-control-inner-background: #ff4444; -fx-control-inner-background-alt: #ff4444;");
                } else {
                    setStyle("");
                }
            }
        });
    }
}