package appli.controller.main;

import appli.dao.principal.jdbc.UtilisateurDAO;
import appli.main.HelloApplication;
import appli.model.enums.Role;
import appli.model.principal.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import session.SessionUtilisateur;

import java.io.IOException;

public class LoginController {

    private final UtilisateurDAO repo = new UtilisateurDAO();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    @FXML
    private Label error;

    void handleLogin(ActionEvent event) throws IOException {

        String email = emailField.getText();
        String password = mdpField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            error.setText("Tous les champs doivent être remplis !");
            return;
        }

        Utilisateur utilisateur = repo.getByEmail(email);

        if (utilisateur == null) {
            error.setText("Email ou mot de passe incorrect.");
            return;
        }

        if (!passwordEncoder.matches(password, utilisateur.getMdp())) {
            error.setText("Email ou mot de passe incorrect.");
            return;
        }

        SessionUtilisateur.getInstance().sauvegardeSession(utilisateur);

        if (utilisateur.getRole() == Role.ROLE_SECRETAIRE) {
            HelloApplication.changeScene("/appli/management/SecretariatManagement.fxml");
        } else if (utilisateur.getRole() == Role.ROLE_MEDECIN) {
            HelloApplication.changeScene("/appli/management/MedecinManagement.fxml");
        } else if (utilisateur.getRole() == Role.ROLE_GESTIONNAIRE) {
            HelloApplication.changeScene("/appli/management/StockManagement.fxml");
        } else if (utilisateur.getRole() == Role.ROLE_ADMIN) {
            HelloApplication.changeScene("/appli/management/SecretariatManagement.fxml");
        }
    }

    public void onPageInscriptionButtonClick() throws IOException {
        HelloApplication.changeScene("Inscription.fxml");
    }

    public void onMdpOublieButtonClick() {
    }

    public void onConnexionButtonClick() throws IOException {
        handleLogin(null);
    }
}