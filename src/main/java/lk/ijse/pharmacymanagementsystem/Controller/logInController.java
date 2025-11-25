package lk.ijse.pharmacymanagementsystem.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.pharmacymanagementsystem.Launcher;

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

    }

    @FXML
    void logInOnAction(ActionEvent event) {

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
