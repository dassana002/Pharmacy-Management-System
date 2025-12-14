package lk.ijse.pharmacymanagementsystem.controller.layout;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.Utility.References;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashLayoutController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            References.dashLayoutController = this;
            backToDashBoard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void backToDashBoard() throws IOException {
        try {
            Parent dashBoard = Launcher.loadFXML("pages/DashBoardPage");
            mainContent.getChildren().setAll(dashBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load Order Page
    public void loadOrderPage() {

    }

    // Load Employee Page
    public void loadEmployeePage() {

    }

    // Load Item Page
    public void loadItemPage() throws IOException{
        Parent medicinePage = Launcher.loadFXML("pages/ItemPage");
        mainContent.getChildren().setAll(medicinePage);
    }
}
