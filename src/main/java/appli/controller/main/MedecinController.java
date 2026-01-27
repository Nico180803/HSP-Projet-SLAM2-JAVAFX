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
    }

    public void onDemandeDeStock(ActionEvent actionEvent) {

    }

    public void onHospitalisation(ActionEvent actionEvent) {
        load("/appli/medecin/Hospitalisation.fxml");

        System.out.println("Dossiers cliqué");
    }
    public void onOrdonnance(ActionEvent actionEvent) {
        load("/appli/medecin/Ordonnance.fxml");

        System.out.println("Dossiers cliqué");
    }
}
