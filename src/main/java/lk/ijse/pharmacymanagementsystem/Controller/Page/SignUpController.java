package lk.ijse.pharmacymanagementsystem.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.pharmacymanagementsystem.Dto.EmployeeDTO;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.Model.EmployeeModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private TextField employeeID_Col;

    @FXML
    private Button login_btn;

    @FXML
    private TextField name_Col;

    @FXML
    private TextField password_Col;

    @FXML
    private ComboBox<String> comboBOX;

    @FXML
    private Button submit_btn;

    @FXML
    private TextField userName_Col;

    private final EmployeeModel signUpModel = new EmployeeModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBOX.setItems(FXCollections.observableArrayList("ADMIN", "ASSISTANT"));
    }

    @FXML
    void BackToLoginPage(ActionEvent event) throws IOException {
        Launcher.setRoot("login");
    }

    @FXML
    void saveEmployee(ActionEvent event) {
        int id = Integer.parseInt(employeeID_Col.getText());
        String name = name_Col.getText();
        String password = password_Col.getText();
        String userName = userName_Col.getText();
        String role = comboBOX.getValue();

        try {
            EmployeeDTO employeeDTO = new EmployeeDTO(id, userName, name,  password, role);
            boolean isSaved = signUpModel.save(employeeDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Employee Save Successes", ButtonType.OK).show();
                cleanFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Employee Save Failed", ButtonType.OK).show();
                cleanFields();
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }

    public void cleanFields() {
        employeeID_Col.clear();
        name_Col.clear();
        password_Col.clear();
        userName_Col.clear();
        comboBOX.getSelectionModel().clearSelection();
    }
}
