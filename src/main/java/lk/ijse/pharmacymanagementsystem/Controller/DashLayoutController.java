package lk.ijse.pharmacymanagementsystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacymanagementsystem.Launcher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashLayoutController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent dashBoardFXML = null;
        try {
            dashBoardFXML = Launcher.loadFXML("DashBoard");
            mainContent.getChildren().setAll(dashBoardFXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

