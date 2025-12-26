package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

    @FXML
    private Button employee_btn;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private Button medicine_btn;

    @FXML
    private Button orderBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.dashBoardController = this;
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
