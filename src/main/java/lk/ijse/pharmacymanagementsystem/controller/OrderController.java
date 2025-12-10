package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OrderLayoutController {

    private DashLayoutController parentController;

    public void setParentController(DashLayoutController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void backToDashBoard(ActionEvent event) {
        parentController.backToDashBoard();
    }
}
