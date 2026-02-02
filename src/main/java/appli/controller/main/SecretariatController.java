package appli.controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class SecretariatController extends MainController{

    public AnchorPane testDroite;
    @FXML
    private StackPane contentPane;

    @FXML
    public Label userLabel;


    public void initialize(){
        mainPane = contentPane;
        sidePage = testDroite;
        load("/appli/patient/TableFichePatient.fxml");
        loadSide("/appli/patient/FormFichePatient.fxml");
    }

    public void onFichesPatients(ActionEvent actionEvent) {
        load("/appli/patient/TableFichePatient.fxml");
        loadSide("/appli/patient/FormFichePatient.fxml");
        System.out.println("Fiches Patient cliqué");
    }

    public void onDossier(ActionEvent actionEvent) {
        load("/appli/patient/TableDossier.fxml");
        loadSide("/appli/patient/FormDossier.fxml");
        System.out.println("Dossiers cliqué");
    }
}
