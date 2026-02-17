package appli.controller.main;

import appli.dao.principal.jdbc.UtilisateurDAO;
import appli.main.HelloApplication;
import appli.model.enums.Role;
import appli.model.principal.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class InscriptionController {

    public void initialize() {
        roleBox.getItems().addAll(Role.values());
    }

    private final UtilisateurDAO repo = new UtilisateurDAO();
    @FXML
    private Button inscriptionButton;

    @FXML
    private Button pageConnexionButton;

    @FXML
    private TextField mdpDeuxField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField mdpField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private Label error;

    @FXML
    private ChoiceBox roleBox;

    @FXML
    void onInscriptionButtonClick(ActionEvent event) {

        if (mdpDeuxField.getText().isEmpty() ||
                mdpField.getText().isEmpty() ||
                emailField.getText().isEmpty() ||
                prenomField.getText().isEmpty() ||
                nomField.getText().isEmpty()) {

            error.setText("Il faut que tous les champs soient renseignés !");

        } else if (!mdpDeuxField.getText().equals(mdpField.getText())) {

            error.setText("La confirmation n'est pas égale au mot de passe !");

        } else {

            Utilisateur utilisateur = new Utilisateur(
                    nomField.getText(),
                    prenomField.getText(),
                    emailField.getText(),
                    mdpField.getText(),
                    roleBox.getValue().toString(),
                    1
            );

            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            utilisateurDAO.insert(utilisateur);

            error.setText("Inscription réussie !");
        }
    }

    public void onPageConnexionButtonClick() throws IOException {
        HelloApplication.changeScene("Login.fxml");
        System.out.println("Vous êtes maintenant sur la page d'inscription");
    }
}

