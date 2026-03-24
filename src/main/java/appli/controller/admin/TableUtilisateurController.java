package appli.controller.admin;

import appli.dao.principal.jdbc.UtilisateurDAO;
import appli.model.principal.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TableUtilisateurController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableUtilisateur;
    private ObservableList<Utilisateur> utilisateurs;
    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 🔹 Récupération des données depuis la BDD
        utilisateurs = FXCollections.observableArrayList(utilisateurDAO.getAll());
        if (utilisateurs == null || utilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur trouvé");
        }

        // 🔹 Nettoyage des colonnes
        tableUtilisateur.getColumns().clear();

        // 🔹 Définition des colonnes (Titre affiché / propriété de l'objet)
        String[][] colonnes = {
                {"ID", "id"},
                {"Nom", "nom"},
                {"Prénom", "prenom"},
                {"Email", "email"},
                {"Mot de passe", "mdp"},
                {"Rôle", "role"},
                {"Actif", "active"}
        };

        // 🔹 Création dynamique des colonnes
        for (String[] col : colonnes) {
            TableColumn<Utilisateur, Object> colonne = new TableColumn<>(col[0]);
            colonne.setCellValueFactory(new PropertyValueFactory<>(col[1]));
            tableUtilisateur.getColumns().add(colonne);
        }

        // 🔹 Colonne "Supprimer"
        TableColumn<Utilisateur, Void> colSupprimer = new TableColumn<>("Supprimer");
        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn = new Button("Supprimer");

            {
                btn.setOnAction(event -> {
                    Utilisateur user = getTableRow().getItem(); // ✅ Ligne correcte
                    if (user == null) return;

                    // 🔹 Confirmation avant suppression
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Supprimer l'utilisateur ?");
                    alert.setContentText(user.getNom() + " " + user.getPrenom());
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // 🔹 Suppression dans la BDD
                        utilisateurDAO.delete(user.getId());
                        System.out.println("Utilisateur supprimé : ID=" + user.getId());
                        // 🔹 Suppression dans la table locale
                        utilisateurs.remove(user);
                    }
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        };

        colSupprimer.setCellFactory(cellFactory);
        tableUtilisateur.getColumns().add(colSupprimer);

        // 🔹 Ajout des données dans la table
        tableUtilisateur.setItems(utilisateurs);

        // 🔹 Ajustement automatique des colonnes
        tableUtilisateur.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }
}