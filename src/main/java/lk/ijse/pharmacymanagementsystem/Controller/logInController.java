package lk.ijse.pharmacymanagementsystem.Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.Model.EmployeeModel;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class logInController {

    @FXML
    private Button exit_btn;

    @FXML
    private Button logIn_btn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button reset_btn;

    @FXML
    void Exit(ActionEvent event) {
        Platform.exit();
    }

    private final EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void logInOnAction(ActionEvent event) {
        String userName = nameField.getText();
        String password = passwordField.getText();

        if (userName.equals("") || password.equals("")) {
            new Alert(Alert.AlertType.ERROR, "Please fill the fields", ButtonType.OK).show();
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
