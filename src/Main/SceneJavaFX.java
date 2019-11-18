package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            String path = "../View";
            String resourceFile = "myScene.fxml";
            String viewFxml = path + resourceFile;

            Parent parent = FXMLLoader.load(getClass().getResource(viewFxml));
            primaryStage.setTitle("IPPO Assignment2");
            primaryStage.setScene(new Scene(parent));
            primaryStage.show();

        } catch (IOException ioe) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ioe);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
