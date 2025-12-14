package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacymanagementsystem.Utility.References;

public class DashBoardController {

    @FXML
    private Button employee_btn;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private Button medicine_btn;

    @FXML
    private Button orderBtn;

    @FXML
    void handleEmployeePage(ActionEvent event) {
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
