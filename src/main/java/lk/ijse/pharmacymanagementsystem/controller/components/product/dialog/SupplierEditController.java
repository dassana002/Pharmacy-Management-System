package lk.ijse.pharmacymanagementsystem.controller.components.product.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lk.ijse.pharmacymanagementsystem.dto.item.SupplierDTO;
import lk.ijse.pharmacymanagementsystem.model.SupplierModel;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SupplierEditController implements Initializable {
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
    private TextField companyName_text;

    @FXML
    private StackPane mainContent;

    private static final String PHONE_REGEX = "^0\\d{9}$";
    private static final String EMAIL_REGEX ="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String NAME_REGEX = "^[A-Za-z ]+$";
    private static final String COMPANY_NAME_REGEX = "^.+$";
    private static final String ADDRESS_REGEX ="^[A-Za-z0-9 ./,-]+$";

    private final SupplierModel supplierModel = new SupplierModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    private boolean isValid(TextField field, String regex) {
        return field.getText() != null && !field.getText().trim().isEmpty() &&field.getText().matches(regex);
    }

    @FXML
    void handleClose(ActionEvent event) {
        // Close the dialog through ItemController
        if (References.productController != null) {
            References.productController.closeSupplierAddDialog();
        }
    }

    @FXML
    void findSupplier(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                if (!isValid(companyName_text, COMPANY_NAME_REGEX)) {
                    new Alert(Alert.AlertType.WARNING, "Invalid Characters Added this field..",ButtonType.OK).show();
                    return;
                }

                SupplierDTO supplierDTO = supplierModel.getSupplierByCompany(companyName_text.getText());
                if (supplierDTO != null) {
                       address_text.setText(supplierDTO.getAddress());
                       CompanyEmail_txt.setText(supplierDTO.getEmail());
                       CompanyContact_txt.setText(String.valueOf(supplierDTO.getCompany_contact()));
                       SupplierName_txt.setText(String.valueOf(supplierDTO.getSupplier_name()));
                       SupplierContact_txt.setText(String.valueOf(supplierDTO.getSupplier_contact()));
                }else {
                    new Alert(Alert.AlertType.ERROR,"Supplier Not Found", ButtonType.OK).show();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleSaveSupplier(ActionEvent event) {
        try {
            if (!isValid(companyName_text, COMPANY_NAME_REGEX) ||
                !isValid(address_text, ADDRESS_REGEX) ||
                    !isValid(CompanyEmail_txt, EMAIL_REGEX) ||
                    !isValid(CompanyContact_txt, PHONE_REGEX) ||
                    !isValid(SupplierName_txt, NAME_REGEX) ||
                    !isValid(SupplierContact_txt, PHONE_REGEX)
            ) {
                new Alert(Alert.AlertType.WARNING, "Invalid input detected",ButtonType.OK).show();
                return;
            }

            boolean isExist = supplierModel.getSupplierByCompany(companyName_text.getText()) == null;
            if (isExist) {
                SupplierDTO supplierDTO = new SupplierDTO(
                        companyName_text.getText(),
                        address_text.getText(),
                        CompanyEmail_txt.getText(),
                        Integer.parseInt(CompanyContact_txt.getText()),
                        SupplierName_txt.getText(),
                        Integer.parseInt(SupplierContact_txt.getText())
                );

                boolean isSupplier = supplierModel.saveSupplier(supplierDTO);
                if (isSupplier) {
                    new Alert(Alert.AlertType.INFORMATION,"Supplier Saved", ButtonType.OK).show();
                    cleanText();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Supplier Not Save", ButtonType.OK).show();
                }

            }else {
                new Alert(Alert.AlertType.WARNING, "This Company Is Already Added").show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleEditSupplier(ActionEvent event) {

        try {
            SupplierDTO supplier = supplierModel.getSupplierByCompany(companyName_text.getText());

            if (supplier == null) {
                new Alert(Alert.AlertType.WARNING,"Supplier Not Found",ButtonType.OK).show();
                return;
            }

            // Validation Check
            if (address_text.getText().isEmpty() ||
                    CompanyEmail_txt.getText().isEmpty() ||
                    CompanyContact_txt.getText().isEmpty() ||
                    SupplierName_txt.getText().isEmpty() ||
                    SupplierContact_txt.getText().isEmpty()) {

                new Alert(Alert.AlertType.WARNING,"Please fill all fields",ButtonType.OK).show();
                return;
            }

            int companyContact = Integer.parseInt(CompanyContact_txt.getText());
            int supplierContact = Integer.parseInt(SupplierContact_txt.getText());

            // Check changed
            boolean isSame =
                    Objects.equals(supplier.getAddress(), address_text.getText()) &&
                            Objects.equals(supplier.getEmail(), CompanyEmail_txt.getText()) &&
                            supplier.getCompany_contact() == companyContact &&
                            Objects.equals(supplier.getSupplier_name(), SupplierName_txt.getText()) &&
                            supplier.getSupplier_contact() == supplierContact;

            if (isSame) {
                new Alert(Alert.AlertType.INFORMATION,"No changes detected!",ButtonType.OK).show();
                cleanText();
                return;
            }

            SupplierDTO newSupplier = new SupplierDTO(
                    supplier.getCompany_name(),
                    address_text.getText(),
                    CompanyEmail_txt.getText(),
                    companyContact,
                    SupplierName_txt.getText(),
                    supplierContact
            );

            boolean isUpdated = supplierModel.updateSupplier(newSupplier);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION,"Supplier updated successfully!",ButtonType.OK).show();
                cleanText();
            } else {
                new Alert(Alert.AlertType.ERROR,"Supplier update failed!",ButtonType.OK).show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR,"Invalid contact number format",ButtonType.OK).show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void cleanText() {
        companyName_text.clear();
        address_text.clear();
        CompanyContact_txt.clear();
        CompanyEmail_txt.clear();
        SupplierName_txt.clear();
        SupplierContact_txt.clear();
    }
}
