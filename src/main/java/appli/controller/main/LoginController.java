package appli.controller.main;

import appli.dao.principal.jdbc.UtilisateurDAO;
import appli.main.HelloApplication;
import appli.model.principal.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import session.SessionUtilisateur;
import java.io.IOException;

public class LoginController {
    private final UtilisateurDAO repo = new UtilisateurDAO();
    @FXML
    private Button connexionbutton;

    @FXML
    private Button pageInscriptionButton;

    @FXML
    private Button mdpOblieButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField mdpField;

    void handleLogin(ActionEvent event) throws IOException {
        System.out.println("Email: " + emailField.getText());
        System.out.println("Password: " + mdpField.getText());

        String email = emailField.getText();
        String password = mdpField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Tous les champs n'ont pas été remplis !");
        } else {
            Utilisateur utilisateur = repo.getByEmail(email);
            if (utilisateur != null) {
                System.out.println("Connexion réussie pour : " + utilisateur.getNom());
                SessionUtilisateur.getInstance().sauvegardeSession(utilisateur);
            } else {
                System.out.println("Échec de la connexion. Email ou mot de passe incorrect.");
            }
        }
    }

    public void onPageInscriptionButtonClick() {
    }
    public void onMdpOublieButtonClick(){
    }
    public void onConnexionButtonClick() throws IOException {
        HelloApplication.changeScene("/appli/management/SecretariatManagement.fxml");
        System.out.println("Arrivé sur le secrétariat management");
    }
}
