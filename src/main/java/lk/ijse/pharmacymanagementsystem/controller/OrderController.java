package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.pharmacymanagementsystem.controller.layout.DashLayoutController;

public class OrderController {

    private DashLayoutController parentController;

    public void setParentController(DashLayoutController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void backToDashBoard(ActionEvent event) {
        parentController.backToDashBoard();
    }
}
