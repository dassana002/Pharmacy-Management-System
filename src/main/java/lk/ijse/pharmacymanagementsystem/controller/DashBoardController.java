package lk.ijse.pharmacymanagementsystem.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.pharmacymanagementsystem.Utility.References;

public class DashBoardController {

    @FXML
    void orderOnAction(ActionEvent event) {

    }

    @FXML
    void employeeOnAction(ActionEvent event) {

    }

    @FXML
    void medicineOnAction(ActionEvent event) {
        References.dashLayoutController.loadMedicinePage();
    }

}
