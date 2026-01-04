package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.dashBoardController = this;
        try {
            loadHome();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadHome() throws IOException {
        Parent dashBoard = Launcher.loadFXML("pages/Home");
        mainContent.getChildren().setAll(dashBoard);
    }

    @FXML
    void handleEmployeePage(ActionEvent event) throws IOException {
        References.dashLayoutController.loadEmployeePage();
    }

    @FXML
    void handleItemPage(ActionEvent event) {
        try {
            References.dashLayoutController.loadItemPage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void handleOrderPage(ActionEvent event) {
        References.dashLayoutController.loadOrderPage();
    }
}
