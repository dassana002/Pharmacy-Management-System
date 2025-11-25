package lk.ijse.pharmacymanagementsystem.Controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class logInController implements Initializable {
    @FXML
    private ComboBox<String> comboBOX;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBOX.setItems(FXCollections.observableArrayList("Admin", "Assistant"));
    }

    @FXML
    void logInOnAction(ActionEvent event) {
        
    }

    @FXML
    void signUpOnAction(ActionEvent event) {

    }
}
