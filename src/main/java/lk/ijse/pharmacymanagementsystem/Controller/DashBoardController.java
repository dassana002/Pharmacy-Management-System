package lk.ijse.pharmacymanagementsystem.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class DashBoardController {

    private DashLayoutController parentController;

    public void setParentController(DashLayoutController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void orderOnAction(ActionEvent event) {
        parentController.loadOrderPage();
    }
}
