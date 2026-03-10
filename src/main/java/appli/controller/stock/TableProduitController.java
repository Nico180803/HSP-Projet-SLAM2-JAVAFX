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

        // ComboBox dangerosité
        comboDangerosite.setItems(FXCollections.observableArrayList(Dangerosite.values()));

        // Spinners
        spinnerQuantiteAjout.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 1));
        spinnerQuantiteRetrait.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10000, 1));

        // Listener sélection tableau
        tableauProduit.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                labelProduitSelectionne.setText(newVal.getLibelle() + " (qté: " + newVal.getQuantite() + ")");
            } else {
                labelProduitSelectionne.setText("Aucun produit sélectionné");
            }
        });
    }

    @FXML
    private void onAjouterProduit() {
        String libelle = fieldLibelle.getText();
        String description = fieldDescription.getText();
        Dangerosite dangerosite = comboDangerosite.getValue();
        int quantite = spinnerQuantiteAjout.getValue();

        if (libelle == null || libelle.isBlank() || dangerosite == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir au moins le libellé et la dangerosité.");
            alert.showAndWait();
            return;
        }

        Produit nouveau = new Produit(libelle, description, dangerosite, quantite);
        produitService.insert(nouveau);

        // Rafraîchir la liste
        produits.setAll(produitService.findAll());

        // Réinitialiser les champs
        fieldLibelle.clear();
        fieldDescription.clear();
        comboDangerosite.setValue(null);
        spinnerQuantiteAjout.getValueFactory().setValue(1);
    }

    @FXML
    private void onReduireQuantite() {
        Produit selectionne = tableauProduit.getSelectionModel().getSelectedItem();
        if (selectionne == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un produit dans le tableau.");
            alert.showAndWait();
            return;
        }

        int retrait = spinnerQuantiteRetrait.getValue();
        int nouvelleQuantite = selectionne.getQuantite() - retrait;

        if (nouvelleQuantite < 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "La quantité ne peut pas être négative. Stock actuel : " + selectionne.getQuantite());
            alert.showAndWait();
            return;
        }

        selectionne.setQuantite(nouvelleQuantite);
        produitService.update(selectionne);

        // Rafraîchir la liste
        produits.setAll(produitService.findAll());
        spinnerQuantiteRetrait.getValueFactory().setValue(1);
        labelProduitSelectionne.setText("Aucun produit sélectionné");
    }
}