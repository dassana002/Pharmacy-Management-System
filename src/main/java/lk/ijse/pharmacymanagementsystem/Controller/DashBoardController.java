package lk.ijse.pharmacymanagementsystem.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashBoardController {

    private DashLayoutController parentController;

    public void setParentController(DashLayoutController controller) {
        this.parentController = controller;
    }

    @FXML
    void orderOnAction(ActionEvent event) {
        parentController.loadOrderPage();
    }
}
