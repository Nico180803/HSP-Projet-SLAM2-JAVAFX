package appli.controller.patient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FormFichePatientController {

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

    public void onValiderFiche(ActionEvent event) {
        // logique ici
    }
}

