package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.model.EmployeeModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    private final EmployeeModel employeeModel = new EmployeeModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moveToNextOnEnter(nameField, passwordField);
    }

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
                    Launcher.setRoot("layouts/DashLayout");

                }else{
                    System.out.println("UnAuthorized access");
                    new Alert(Alert.AlertType.ERROR, "Please Try Again", ButtonType.OK).show();
                    cleanFields();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void moveToNextOnEnter(Control current, Control next) {
        current.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER -> {
                    next.requestFocus();
                    event.consume();
                }
            }
        });
    }

    private void cleanFields() {
        nameField.clear();
        passwordField.clear();
    }
}
