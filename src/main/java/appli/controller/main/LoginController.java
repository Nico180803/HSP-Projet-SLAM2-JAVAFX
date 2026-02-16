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
import appli.dao.logs.jdbc.LogsUtilisateurDAO;
import appli.model.logs.LogsUtilisateur;
import appli.model.logs.TableCible;
import appli.model.logs.TypeAction;
import java.time.LocalDateTime;

public class LoginController {
    private final LogsUtilisateurDAO logDAO = new LogsUtilisateurDAO();

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
            UtilisateurDAO repo = new UtilisateurDAO();
            Utilisateur utilisateur = repo.getByEmail(email);
            if (utilisateur != null) {

                System.out.println("Connexion réussie pour : " + utilisateur.getNom());

                SessionUtilisateur.getInstance().sauvegardeSession(utilisateur);

                LogsUtilisateur log = new LogsUtilisateur();

                log.setUtilisateur(utilisateur);
                log.setDateAction(LocalDateTime.now());
                log.setTypeAction(TypeAction.CONNEXION);
                log.setTableCible(TableCible.UTILISATEUR);
                log.setLogsUtilisateur(
                        "Connexion de l'utilisateur : " +
                                utilisateur.getNom() + " " + utilisateur.getPrenom()
                );

                logDAO.create(log);

                HelloApplication.changeScene("/appli/management/SecretariatManagement.fxml");


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
        handleLogin(null);
        System.out.println("Arrivé sur le secrétariat management");
    }
}
