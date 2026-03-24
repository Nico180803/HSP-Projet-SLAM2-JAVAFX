package appli.controller;

import appli.controller.main.ChangerMotDePasseController;
import appli.service.EmailService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MotDePasseOublieController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField codeField;

    @FXML
    private Label statusLabel;

    private String codeGenere; // pour stocker le code généré temporairement

    @FXML
    private void envoyerCode() {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            statusLabel.setText("Veuillez entrer une adresse e-mail.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            codeGenere = EmailService.genererCode();
            EmailService.envoyerEmail(email, "Réinitialisation de mot de passe",
                    "Votre code de réinitialisation est : " + codeGenere);

            statusLabel.setText("Code envoyé à : " + email);
            statusLabel.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Erreur lors de l'envoi de l'e-mail.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void verifierCode() {
        String codeSaisi = codeField.getText().trim();
        if (codeSaisi.equals(codeGenere)) {
            // redirection vers l'écran de changement de mot de passe
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/appli/view/ChangerMotDePasse.fxml"));
                Stage stage = (Stage) codeField.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));

                // transmettre l'e-mail à la page suivante
                ChangerMotDePasseController ctrl = loader.getController();
                ctrl.setUtilisateurEmail(emailField.getText().trim());

            } catch (IOException e) {
                e.printStackTrace();
                statusLabel.setText("Erreur lors du chargement de la page.");
            }
        } else {
            statusLabel.setText("Code incorrect. Veuillez vérifier.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}