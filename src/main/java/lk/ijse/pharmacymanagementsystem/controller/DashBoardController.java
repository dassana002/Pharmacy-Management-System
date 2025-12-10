package lk.ijse.pharmacymanagementsystem.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import lk.ijse.pharmacymanagementsystem.controller.layout.DashLayoutController;

public class DashBoardController {

    private DashLayoutController parentController;

    public void initialize(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DashLayout.fxml"));
        parentController = fxmlLoader.getController();
    }

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
