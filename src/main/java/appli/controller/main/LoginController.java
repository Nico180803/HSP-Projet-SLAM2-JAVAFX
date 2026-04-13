package appli.controller.main;

import appli.model.enums.Role;
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
import appli.dao.logs.jdbc.LogsUtilisateurDAO;
import appli.model.logs.LogsUtilisateur;
import appli.model.logs.TableCible;
import appli.model.logs.TypeAction;
import java.time.LocalDateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
            HelloApplication.changeScene("/appli/management/AdminManagement.fxml");
        }
    }

    public void onMdpOublieButtonClick() throws IOException {
        HelloApplication.changeScene("MotDePasseOublie.fxml");
    }

    public void onConnexionButtonClick() throws IOException {
        handleLogin(null);
    }
}