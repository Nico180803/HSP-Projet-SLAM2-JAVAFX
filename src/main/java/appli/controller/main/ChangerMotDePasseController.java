package appli.controller.main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ChangerMotDePasseController {

    @FXML
    private PasswordField nouveauMotDePasseField;

    @FXML
    private PasswordField confirmationMotDePasseField;

    @FXML
    private Label statusLabel;

    private String utilisateurEmail;

    // Setter pour récupérer l'email depuis l'écran précédent
    public void setUtilisateurEmail(String email) {
        this.utilisateurEmail = email;
    }

    @FXML
    private void verifierEtModifierMotDePasse() {
        String motDePasse = nouveauMotDePasseField.getText().trim();
        String confirmation = confirmationMotDePasseField.getText().trim();

        if (motDePasse.isEmpty() || confirmation.isEmpty()) {
            statusLabel.setText("Veuillez remplir tous les champs.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!motDePasse.equals(confirmation)) {
            statusLabel.setText("Les mots de passe ne correspondent pas.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // TODO : mettre à jour le mot de passe en BDD
        // Exemple : UtilisateurDAO.modifierMotDePasse(utilisateurEmail, motDePasse);

        statusLabel.setText("Mot de passe modifié avec succès !");
        statusLabel.setStyle("-fx-text-fill: green;");

        // Rediriger vers la page de connexion (à adapter selon ton projet)
        // Stage stage = (Stage) nouveauMotDePasseField.getScene().getWindow();
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/appli/view/Connexion.fxml"));
        // stage.setScene(new Scene(loader.load()));
    }
}