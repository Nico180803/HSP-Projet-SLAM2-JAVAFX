package appli.controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class MedecinController extends MainController{

    public AnchorPane testDroite;
    @FXML
    private StackPane contentPane;

    @FXML
    public Label userLabel;


    public void initialize(){
        mainPane = contentPane;
        sidePage = testDroite;
        load("/appli/patient/TableDossier.fxml");
        System.out.println("Fiches Patient cliqué");
    }

    public void onDemandeDeStock(ActionEvent actionEvent) {

    }

    public void onHospitalisation(ActionEvent actionEvent) {
        load("/appli/medecin/HospitalisationForm.fxml");
        loadSide("/appli/medecin/HospitalisationNew.fxml");
        System.out.println("Dossiers cliqué");
    }
    public void onOrdonnance(ActionEvent actionEvent) {
        load("/appli/medecin/OrdonnanceForm.fxml");
        loadSide("/appli/medecin/HospitalisationNew.fxml");
        System.out.println("Dossiers cliqué");
    }
}
