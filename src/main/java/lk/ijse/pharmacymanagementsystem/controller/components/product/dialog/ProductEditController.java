package lk.ijse.pharmacymanagementsystem.controller.components.product.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProductEditController implements Initializable {

    @FXML
    private TextField des_text;

    @FXML
    private TextField itemCode_text;

    @FXML
    private StackPane mainContent;

    private String oldDescription;
    private static final String ITEM_CODE_REGEX = "^[0-9]+$";
    private static final String DESCRIPTION_REGEX = "^[A-Za-z ]{5,}$";

    private final ItemModel itemModel = new ItemModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Close dialog when clicking outside the popup card
        if (mainContent != null) {
            mainContent.setOnMouseClicked(event -> {
                if (event.getTarget() == mainContent) {
                    handleClose(null);
                }
            });
        }
    }

    // Validation Check
    private boolean isValid(TextField field, String regex) {
        return field.getText() != null && field.getText().matches(regex);
    }

    @FXML
    void handleClose(ActionEvent event) {
        // Close the dialog through ItemController
        if (References.productController != null) {
            References.productController.closeItemAddDialog();
        }
    }

    private boolean existItem(int itemCode) {
        try {
            ItemDTO dbItem = itemModel.getItem(itemCode);
            if (dbItem != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleFindItem(KeyEvent event) {
        des_text.clear();
        try {
            if (event.getCode() == KeyCode.ENTER) {
                String item = itemCode_text.getText();

                // Check Validation Item Code
                if (item.matches(ITEM_CODE_REGEX) || itemCode_text.getText() == null || "".equals(itemCode_text.getText())) {
                    int itemCode = Integer.parseInt(itemCode_text.getText());

                    // Check Exist Item
                    if (existItem(itemCode)) {
                        ItemDTO dbItem = itemModel.getItem(itemCode);
                        des_text.setText(dbItem.getDescription());
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Product Not Found..").show();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR, "Invalid Item Code..Try again..").show();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleSaveItem(ActionEvent event) throws SQLException {
        try {
            // Item code and description validation check
            if (!isValid(itemCode_text,ITEM_CODE_REGEX) || !isValid(des_text, DESCRIPTION_REGEX) ) {
                new Alert(Alert.AlertType.WARNING, "Invalid input detected").show();
                return;
            }

            int itemCode = Integer.parseInt(itemCode_text.getText());
            String desc = des_text.getText();

            if (!existItem(itemCode)) {
                ItemDTO itemDTO = new ItemDTO(
                        itemCode,
                        desc
                );

                // Item Save
                boolean isSave = itemModel.saveAll(itemDTO);

                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION, "Product Saved successfully", ButtonType.OK).show();
                    cleanText();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Product Not Saved ? Please Try again..", ButtonType.OK).show();
                    cleanText();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "This Product Already Added", ButtonType.OK).show();
                cleanText();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage(), ButtonType.OK).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        try {
            ItemDTO item = itemModel.getItem(Integer.parseInt(itemCode_text.getText()));

            if (item != null) {
                boolean isChange = !Objects.equals(des_text.getText(), item.getDescription());
                if (isChange) {
                    boolean isUpdated = itemModel.updateDesc(item.getItem_code(), des_text.getText());
                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, "Product Saved successfully", ButtonType.OK).show();
                        cleanText();
                    }else {
                        new Alert(Alert.AlertType.ERROR, "Product Saved successfully", ButtonType.OK).show();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR, "This Product Already Not Change..", ButtonType.OK).show();
                }

            }else {
                new Alert(Alert.AlertType.ERROR, "Product Not Found..Please first Save to Product", ButtonType.OK).show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void cleanText() {
        itemCode_text.clear();
        des_text.clear();
    }
}