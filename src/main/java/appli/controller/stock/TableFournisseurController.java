package appli.controller.stock;

import appli.model.principal.Fournisseur;
import appli.service.FournisseurService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableFournisseurController implements Initializable {
    @FXML
    private TableView<Fournisseur> tableauFournisseur;
    @FXML
    private TextField fieldNom;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldTel;
    @FXML
    private Label labelFournisseurSelectionne;

    private ObservableList<Fournisseur> fournisseurs;
    private FournisseurService fournisseurService = new FournisseurService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fournisseurs = fournisseurService.findAll();
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur trouvé");
        }
        tableauFournisseur.setItems(fournisseurs);

        String[][] colonnes = {
                {"Nom", "nom"},
                {"Email", "email"},
                {"Téléphone", "tel"},
        };

        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Fournisseur, String> maCol = new TableColumn<>(colonnes[i][0]);

            maCol.setCellValueFactory(
                    new PropertyValueFactory<Fournisseur, String>(colonnes[i][1])
            );
            tableauFournisseur.getColumns().add(maCol);
        }
        tableauFournisseur.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // Listener sélection tableau
        tableauFournisseur.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                labelFournisseurSelectionne.setText(newVal.getNom() + " (" + newVal.getEmail() + ")");
            } else {
                labelFournisseurSelectionne.setText("Aucun fournisseur sélectionné");
            }
        });
    }

    @FXML
    private void onAjouterFournisseur() {
        String nom = fieldNom.getText();
        String email = fieldEmail.getText();
        String tel = fieldTel.getText();

        if (nom == null || nom.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir au moins le nom du fournisseur.");
            alert.showAndWait();
            return;
        }

        Fournisseur nouveau = new Fournisseur(nom, email, tel);
        fournisseurService.insert(nouveau);

        // Rafraîchir la liste
        fournisseurs.setAll(fournisseurService.findAll());

        // Réinitialiser les champs
        fieldNom.clear();
        fieldEmail.clear();
        fieldTel.clear();
    }

    @FXML
    private void onSupprimerFournisseur() {
        Fournisseur selectionne = tableauFournisseur.getSelectionModel().getSelectedItem();
        if (selectionne == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner un fournisseur dans le tableau.");
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer le fournisseur \"" + selectionne.getNom() + "\" ?");
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                fournisseurService.delete(selectionne.getId());

                // Rafraîchir la liste
                fournisseurs.setAll(fournisseurService.findAll());
                labelFournisseurSelectionne.setText("Aucun fournisseur sélectionné");
            }
        });
    }
}
