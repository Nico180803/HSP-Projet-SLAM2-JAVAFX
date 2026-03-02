package appli.controller.main;

import appli.dao.principal.jdbc.UtilisateurDAO;
import appli.main.HelloApplication;
import appli.model.enums.Role;
import appli.model.principal.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

public class InscriptionController {

    private final UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField mdpField;

    @FXML
    private TextField mdpDeuxField;

    @FXML
    private ChoiceBox<Role> roleBox;

    @FXML
    private Label error;

    @FXML
    private Button inscriptionButton;

    @FXML
    private Button pageConnexionButton;

    public void initialize() {
        roleBox.getItems().addAll(Role.values());
    }

    @FXML
    void onInscriptionButtonClick(ActionEvent event) {

        if (nomField.getText().isEmpty() ||
                prenomField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                mdpField.getText().isEmpty() ||
                mdpDeuxField.getText().isEmpty() ||
                roleBox.getValue() == null) {

            error.setText("Tous les champs doivent être remplis !");
            return;
        }

        if (!mdpField.getText().equals(mdpDeuxField.getText())) {
            error.setText("Les mots de passe ne correspondent pas !");
            return;
        }

        if (utilisateurDAO.getByEmail(emailField.getText()) != null) {
            error.setText("Cet email est déjà utilisé !");
            return;
        }

        String motDePasseHashe = passwordEncoder.encode(mdpField.getText());

        Utilisateur utilisateur = new Utilisateur(
                nomField.getText(),
                prenomField.getText(),
                emailField.getText(),
                motDePasseHashe,
                roleBox.getValue(),
                true
        );

        utilisateurDAO.insert(utilisateur);
        error.setText("Inscription réussie !");
    }

    @FXML
    public void onPageConnexionButtonClick() throws IOException {
        HelloApplication.changeScene("Login.fxml");
    }
}