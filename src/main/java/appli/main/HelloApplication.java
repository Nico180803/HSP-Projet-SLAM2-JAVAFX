package appli.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    private static Stage mainStage;

    @Override

    public void start(Stage stage) throws IOException {
        mainStage= stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/appli/main/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void changeScene(String fxml) throws IOException {

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(loader.load());
        mainStage.setScene(scene);

    }
}
