package lk.ijse.pharmacymanagementsystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        try {
            loadDashBoard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDashBoard() throws IOException {
        Parent dashboardUI = Launcher.loadFXML("DashBoard");
        mainContent.getChildren().setAll(dashboardUI);
    }

    // Load Order Page
    public void loadOrderPage() {

    }
}
