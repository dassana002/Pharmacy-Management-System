package lk.ijse.pharmacymanagementsystem.controller.components.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lk.ijse.pharmacymanagementsystem.utility.References;

public class SupplierAddController {
    @FXML
    private TextField CompanyContact_txt;

    @FXML
    private TextField CompanyEmail_txt;

    @FXML
    private TextField SupplierContact_txt;

    @FXML
    private TextField SupplierName_txt;

    @FXML
    private TextField address_text;

    @FXML
    private Button close_btn;

    @FXML
    private TextField companyName_text;

    @FXML
    private StackPane mainContent;

    @FXML
    private VBox popupCard;

    @FXML
    private Button save_btn;

    @FXML
    void handleClose(ActionEvent event) {
        // Close the dialog through ItemController
        if (References.itemController != null) {
            References.itemController.closeNewItemDialog();
        }
    }

    @FXML
    void handleSaveSupplier(ActionEvent event) {

    }
}
