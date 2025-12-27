package lk.ijse.pharmacymanagementsystem.controller.components.product.dialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import lk.ijse.pharmacymanagementsystem.dto.item.DosageDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDosageDTO;
import lk.ijse.pharmacymanagementsystem.model.DosageModel;
import lk.ijse.pharmacymanagementsystem.model.ItemDosageModel;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class ProductAddController implements Initializable {

    @FXML
    private TextField des_text;

    @FXML
    private ComboBox<String> dosage_cmb;

    @FXML
    private TextField itemCode_text;

    @FXML
    private StackPane mainContent;

    @FXML
    private Label dosageID;

    // Item Code – numbers only
    private static final String ITEM_CODE_REGEX = "^[0-9]+$";

    // Description – letters, spaces allowed (min 5 chars)
    private static final String DESCRIPTION_REGEX = "^[A-Za-z ]{5,}$";

    // Dosage – numbers + optional unit
    private static final String DOSAGE_REGEX = "^[0-9]+(mg|ml|g)?$";

    private final ItemDosageModel itemDosageModel = new ItemDosageModel();
    private final ItemModel itemModel = new ItemModel();
    private final DosageModel dosageModel = new DosageModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Move Next - check if fields are not null first
//        if (itemCode_text != null && des_text != null && dosage_cmb != null) {
//            moveToNextOnEnter(itemCode_text, des_text);
//            moveToNextOnEnter(des_text, dosage_cmb);
//        }
//
//        if (itemCode_text != null && des_text != null && dosage_cmb != null) {
//            moveToNextOnEnter(itemCode_text, des_text);
//            moveToNextOnEnter(des_text, dosage_cmb);
//        }

        // Close dialog when clicking outside the popup card
        if (mainContent != null) {
            mainContent.setOnMouseClicked(event -> {
                if (event.getTarget() == mainContent) {
                    handleClose(null);
                }
            });
        }
    }

//    private void moveToNextOnEnter(Control current, Control next) {
//        current.setOnKeyPressed(event -> {
//            switch (event.getCode()) {
//                case DOWN -> {
//                    next.requestFocus();
//                    event.consume();
//                }
//                case ESCAPE -> handleClose(null);
//            }
//        });
//    }

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
        dosage_cmb.setItems(null);

        try {
            if (event.getCode() == KeyCode.ENTER) {
                dosage_cmb.setVisible(true);
                dosageID.setVisible(true);
                String item = itemCode_text.getText();

                // Check Validation Item Code
                if (item.matches(ITEM_CODE_REGEX) || itemCode_text.getText() == null || "".equals(itemCode_text.getText())) {
                    int itemCode = Integer.parseInt(itemCode_text.getText());

                    // Check Exist Item
                    if (existItem(itemCode)) {
                        ItemDTO dbItem = itemModel.getItem(itemCode);
                        des_text.setText(dbItem.getDescription());

                        // Get All Dosage Ids
                        ArrayList<Integer> dosageIds = itemDosageModel.getItemDosagesByItemCode(itemCode);

                        // Get All Dosages
                        ArrayList<String> sizes = dosageModel.getDosageIdsBySize(dosageIds);

                        // check sizes
                        if (sizes == null) {
                            dosage_cmb.setVisible(false);
                            dosageID.setVisible(false);
                        }else {
                            ObservableList<String> observableList = FXCollections.observableArrayList(sizes);
                            dosage_cmb.setItems(observableList);
                        }
                    }else {
                        new Alert(Alert.AlertType.WARNING, "Item Not Found..").show();
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
            int itemCode = Integer.parseInt(itemCode_text.getText());
            String desc = des_text.getText();
            String dosage = dosage_cmb.getValue();

            if (!existItem(itemCode)) {
                ItemDTO itemDTO = new ItemDTO(
                        itemCode,
                        desc
                );

                boolean isSave = itemModel.saveAll(itemDTO, dosage);

                if (isSave) {
                    new Alert(Alert.AlertType.INFORMATION, "Items Saved successfully", ButtonType.OK).show();

                    cleanText();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error while saving items", ButtonType.OK).show();
                    throw new RuntimeException();
                }

            } else {

                // New Dosage Add and Save
                int dosage_id = UUID.randomUUID().hashCode();

                DosageDTO dosageDTO = new DosageDTO(
                        dosage_id,
                        dosage
                );

                ItemDosageDTO itemDosageDTO = new ItemDosageDTO(
                        itemCode,
                        dosage_id
                );

                boolean isDosageSave = itemDosageModel.newDosageSave(dosageDTO,itemDosageDTO);
                if (isDosageSave) {
                    new Alert(Alert.AlertType.INFORMATION, "Item Saved successfully", ButtonType.OK).show();

                    cleanText();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error while saving item", ButtonType.OK).show();
                    throw new RuntimeException();
                }
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage(), ButtonType.OK).show();
            throw new RuntimeException(e);
        }
    }

    private void cleanText() {
        itemCode_text.clear();
        des_text.clear();
        dosage_cmb.getEditor().clear();
    }
}