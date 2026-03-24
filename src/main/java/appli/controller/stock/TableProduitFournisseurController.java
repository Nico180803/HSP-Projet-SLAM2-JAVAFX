package appli.controller.stock;

import appli.model.principal.Fournisseur;
import appli.model.principal.Produit;
import appli.model.principal.ProduitFournisseur;
import appli.service.FournisseurService;
import appli.service.ProduitFournisseurService;
import appli.service.ProduitService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class TableProduitFournisseurController implements Initializable {
    @FXML
    private TableView<ProduitFournisseur> tableauProduitFournisseur;
    @FXML
    private ComboBox<Produit> comboProduit;
    @FXML
    private ComboBox<Fournisseur> comboFournisseur;
    @FXML
    private Spinner<Double> spinnerPrix;

    private ObservableList<ProduitFournisseur> produitFournisseurs;
    private ProduitFournisseurService produitFournisseurService = new ProduitFournisseurService();
    private ProduitService produitService = new ProduitService();
    private FournisseurService fournisseurService = new FournisseurService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        produitFournisseurs = produitFournisseurService.findAll();
        tableauProduitFournisseur.setItems(produitFournisseurs);

        String[][] colonnes = {
                {"Produit", "refProduit"},
                {"Fournisseur", "refFournisseur"},
                {"Prix", "prix"},
        };

        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<ProduitFournisseur, String> maCol = new TableColumn<>(colonnes[i][0]);
            maCol.setCellValueFactory(
                    new PropertyValueFactory<>(colonnes[i][1])
            );
            tableauProduitFournisseur.getColumns().add(maCol);
        }
        tableauProduitFournisseur.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // ComboBox Produit
        comboProduit.setItems(produitService.findAll());
        comboProduit.setConverter(new StringConverter<Produit>() {
            @Override
            public String toString(Produit produit) {
                return produit != null ? produit.getLibelle() : "";
            }

            @Override
            public Produit fromString(String string) {
                return null;
            }
        });

        comboFournisseur.setItems(fournisseurService.findAll());
        comboFournisseur.setConverter(new StringConverter<Fournisseur>() {
            @Override
            public String toString(Fournisseur fournisseur) {
                return fournisseur != null ? fournisseur.getNom() : "";
            }

            @Override
            public Fournisseur fromString(String string) {
                return null;
            }
        });

        spinnerPrix.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.01, 100000, 1.00, 0.50));
    }

    @FXML
    private void onAjouterProduitFournisseur() {
        Produit produit = comboProduit.getValue();
        Fournisseur fournisseur = comboFournisseur.getValue();
        double prix = spinnerPrix.getValue();

        if (produit == null || fournisseur == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un produit et un fournisseur.");
            alert.showAndWait();
            return;
        }

        ProduitFournisseur nouveau = new ProduitFournisseur(produit, fournisseur, prix);
        produitFournisseurService.insert(nouveau);

        // Rafraîchir la liste
        produitFournisseurs.setAll(produitFournisseurService.findAll());

        // Réinitialiser les champs
        comboProduit.setValue(null);
        comboFournisseur.setValue(null);
        spinnerPrix.getValueFactory().setValue(1.00);
    }
}
