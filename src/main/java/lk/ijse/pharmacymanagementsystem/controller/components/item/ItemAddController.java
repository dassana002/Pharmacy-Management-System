package lk.ijse.pharmacymanagementsystem.controller.components.item;

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
import lk.ijse.pharmacymanagementsystem.model.DosageModel;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

public class ItemAddController implements Initializable {

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
        if (References.itemController != null) {
            References.itemController.closeItemAddDialog();
        }
    }

    private boolean findItem(int itemCode) {
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

                    if (findItem(itemCode)) {
                        ItemDTO dbItem = itemModel.getItem(itemCode);
                        des_text.setText(dbItem.getDescription());

                        ArrayList<String> dosages = dosageModel.getDosageById(itemCode);

                        if (dosages == null) {
                            dosage_cmb.setVisible(false);
                            dosageID.setVisible(false);
                        }else {
                            ObservableList<String> observableList = FXCollections.observableArrayList(dosages);
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

            if (!findItem(itemCode)) {
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
                int dosage_id = UUID.randomUUID().hashCode();
                DosageDTO dosageDTO = new DosageDTO(
                        dosage_id,
                        dosage,
                        itemCode
                );

                boolean isDosageSave = dosageModel.dosageSaveTemp(dosageDTO);
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