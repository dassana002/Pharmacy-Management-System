package lk.ijse.pharmacymanagementsystem.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.pharmacymanagementsystem.Model.EmployeeModel;

public class SignInController {

    @FXML
    private Button submit_btn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    private final EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void btnSubmitOnAction(ActionEvent event) {
        String userName = nameField.getText();
        String password = passwordField.getText();

        if (userName.equals("") || password.equals("")) {
            new Alert(Alert.AlertType.WARNING, "Please fill the fields", ButtonType.OK).show();
            cleanFields();

        }else {
            try {
                boolean isValid = employeeModel.checkValidation(userName, password);
                if (isValid) {
                    System.out.println("login");
                }else{
                    System.out.println("UnAuthorized access");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void resetFields(ActionEvent event) {
        cleanFields();
    }

    private void cleanFields() {
        nameField.clear();
        passwordField.clear();
    }
}
