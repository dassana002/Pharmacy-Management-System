package lk.ijse.pharmacymanagementsystem.Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashBoardController {

    private DashLayoutController parentController;

    public void setParentController(DashLayoutController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void orderOnAction(ActionEvent event) {
        parentController.loadOrderPage();
    }

    @FXML
    void employeeOnAction(ActionEvent event) {
        parentController.loadEmployeePage();
    }

    @FXML
    void medicineOnAction(ActionEvent event) {
        parentController.loadMedicinePage();
    }

}
