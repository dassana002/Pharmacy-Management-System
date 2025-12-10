package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lk.ijse.pharmacymanagementsystem.controller.layout.DashLayoutController;

public class ItemController {

    private DashLayoutController parentController =  new DashLayoutController();

    public void setParentController(DashLayoutController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void backToDashBoard(ActionEvent event) {
        parentController.backToDashBoard();
    }


}
