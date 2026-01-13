package appli.controller.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class SecretariatController {

    @FXML
    private StackPane contentPane;

    @FXML
    public Label userLabel;


    public void initialize(){
    }

    public void onFichesPatients(ActionEvent actionEvent) {
        load("/appli/patient/TableFichePatient.fxml");
        System.out.println("Fiches Patient cliqué");
    }

    public void onDossier(ActionEvent actionEvent) {
        load("/appli/patient/TableDossier.fxml");
        System.out.println("Dossiers cliqué");
    }


    private void load(String path){
        try {
            Parent view = FXMLLoader.load(getClass().getResource(path));
            contentPane.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
