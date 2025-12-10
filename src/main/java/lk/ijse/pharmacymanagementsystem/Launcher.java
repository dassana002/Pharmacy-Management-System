package lk.ijse.pharmacymanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        /// create a scene Object
        scene = new Scene(loadFXML("LogInPage"));
        /// scene object set to stage
        stage.setScene(scene);
        /// scene object pop up
        stage.show();
    }

    /// current load  .fxml file load to in this method
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/lk/ijse/pharmacymanagementsystem/" +fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
