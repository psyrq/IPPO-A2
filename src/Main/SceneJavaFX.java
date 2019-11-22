package Main;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SceneJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            String path = "../View/";
            String resourceFile = "myScene.fxml";
            String viewFxml = path + resourceFile;

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(viewFxml));
            GridPane page = fxmlLoader.load();
            Scene scene = new Scene(page);

            primaryStage.setTitle("IPPO Assignment2");
            primaryStage.setScene(scene);

            Controller controller = fxmlLoader.getController();
            controller.initialize();

            primaryStage.show();

        } catch (IOException ioe) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ioe);
        }
    }

    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
}
