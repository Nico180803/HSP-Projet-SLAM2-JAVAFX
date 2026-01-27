package appli.controller.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public abstract class MainController {
    private static MainController instance = null;

    public MainController() {
        instance = this;
    }

    public final static MainController getInstance() {
        return instance;
    }

    protected Pane mainPane;
    protected Pane sidePage;

    protected void load(String path){
        try {
            Parent view = FXMLLoader.load(getClass().getResource(path));
            mainPane.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void loadSide(String path){
        try {
            Parent view = FXMLLoader.load(getClass().getResource(path));
            sidePage.getChildren().setAll(view);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

