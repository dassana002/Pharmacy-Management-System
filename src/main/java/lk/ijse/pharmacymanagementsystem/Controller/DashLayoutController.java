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

    }

    @FXML
    private void clickMedicineNav() throws IOException {
        Parent customerFXML = Launcher.loadFXML("Medicine");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void clickEmployeeNav() throws IOException {
        Parent customerFXML = Launcher.loadFXML("Employee");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void clickOrderNav() throws IOException {
        Parent customerFXML = Launcher.loadFXML("Order");
        mainContent.getChildren().setAll(customerFXML);
    }
}

