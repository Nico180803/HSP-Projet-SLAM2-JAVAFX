package appli.controller.admin;

import appli.dao.principal.jdbc.UtilisateurDAO;
import appli.model.principal.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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

        // 🔹 Nettoyage des colonnes (important si rechargement)
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
        for (int i = 0; i < colonnes.length; i++) {
            TableColumn<Utilisateur, Object> colonne = new TableColumn<>(colonnes[i][0]);
            colonne.setCellValueFactory(
                    new PropertyValueFactory<>(colonnes[i][1])
            );
            tableUtilisateur.getColumns().add(colonne);
        }

        // 🔹 Ajout des données dans la table
        tableUtilisateur.setItems(utilisateurs);

        // 🔹 Ajustement automatique des colonnes
        tableUtilisateur.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
    }
}