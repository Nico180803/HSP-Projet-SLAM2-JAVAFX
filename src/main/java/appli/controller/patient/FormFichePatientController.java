package appli.controller.patient;

import appli.controller.main.MainController;
import appli.model.principal.FichePatient;
import appli.service.FichePatientService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FormFichePatientController {

    public Text formInfo;
    @FXML
    private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField mailField;
    @FXML private TextField telField;
    @FXML private TextField numSecuField;
    @FXML private TextField rueField;
    @FXML private TextField numRueField;
    @FXML private TextField cpField;
    @FXML private TextField villeField;

    private final FichePatientService fichePatientService = new FichePatientService();

    public void onValiderFiche(ActionEvent event) {
        String prenom = prenomField.getText();
        String nom = nomField.getText();
        String mail = mailField.getText();
        String tel = telField.getText();
        String numRue = numRueField.getText();
        String cp = cpField.getText();
        String ville = villeField.getText();
        String numSecu = numSecuField.getText();
        String rue = rueField.getText();

        if (prenom.isEmpty() || nom.isEmpty() || mail.isEmpty() || tel.isEmpty()
                || numRue.isEmpty() || cp.isEmpty() || ville.isEmpty() || numSecu.isEmpty() || rue.isEmpty()) {

            System.out.println("Erreur champs incomplets");
            formInfo.setVisible(true);
            formInfo.setText("Erreur : champs incomplets");
            formInfo.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        } else {
            FichePatient fichePatient = new FichePatient(nom, prenom,numSecu,tel,mail,rue,numRue,ville,cp);
            fichePatientService.add(fichePatient);
            System.out.println("Tentative d'ajout");
            formInfo.setVisible(true);
            formInfo.setText("Fiche patient créée !");
            formInfo.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            MainController.getInstance().load("/appli/patient/TableFichePatient.fxml");

        }
    }
}

