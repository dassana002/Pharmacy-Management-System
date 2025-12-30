package lk.ijse.pharmacymanagementsystem.controller.components.product.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierAddController implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Move Next - check if fields are not null first
//        if (itemCode_text != null && des_text != null && dosage_txt != null) {
//            moveToNextOnEnter(itemCode_text, des_text);
//            moveToNextOnEnter(des_text, dosage_txt);
//        }

        // Click background and Close dialog
        if (mainContent != null) {
            mainContent.setOnMouseClicked(event -> {
                if (event.getTarget() == mainContent) {
                    handleClose(null);
                }
            });
        }
    }

    private void moveToNextOnEnter(Control current, Control next) {
        current.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER -> {
                    next.requestFocus();
                    event.consume();
                }
                case ESCAPE -> handleClose(null); // ESC key to close
            }
        });
    }

    @FXML
    void handleClose(ActionEvent event) {
        // Close the dialog through ItemController
        if (References.productController != null) {
            References.productController.closeSupplierAddDialog();
        }
    }

    @FXML
    void handleSaveSupplier(ActionEvent event) {

    }
}
