package appli.controller.shared;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class OverlayController {

    @FXML
    private StackPane contentPane;

    @FXML
    private Label userLabel;


    public void setUser(String username) {
        userLabel.setText(username);
    }

    public StackPane getContentPane() {
        return contentPane;
    }

    @FXML
    public void initialize(){
        load("/appli/main/login.fxml");
    }

    @FXML
    private void onAccueil() {
        load("/appli/main/login.fxml");
        System.out.println("Accueil cliqué");
    }

    @FXML
    private void onPatients() {
        System.out.println("Patients cliqué");
    }

    @FXML
    private void onStock() {
        System.out.println("Stock cliqué");
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