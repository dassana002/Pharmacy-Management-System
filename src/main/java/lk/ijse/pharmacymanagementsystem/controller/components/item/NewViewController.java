package lk.ijse.pharmacymanagementsystem.controller.components.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewViewController implements Initializable {

    @FXML
    private TextField des_text;

    @FXML
    private TextField dosage_txt;

    @FXML
    private TextField itemCode_text;

    @FXML
    private StackPane mainContent;

    private final ItemModel itemModel = new ItemModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Move Next - check if fields are not null first
        if (itemCode_text != null && des_text != null && dosage_txt != null) {
            moveToNextOnEnter(itemCode_text, des_text);
            moveToNextOnEnter(des_text, dosage_txt);
        }

        // Close dialog when clicking outside the popup card
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
        if (References.itemController != null) {
            References.itemController.closeNewItemDialog();
        }
    }

    @FXML
    void handleSaveItem(ActionEvent event) throws SQLException {
        try {
            int itemCode = Integer.parseInt(itemCode_text.getText());
            String desc = des_text.getText();
            String dosage = dosage_txt.getText();

            ItemDTO itemDTO = new ItemDTO(
                    itemCode,
                    desc
            );

            boolean isSave = itemModel.saveAll(itemDTO, dosage);

            if (isSave) {
                System.out.println("Items saved successfully");
                new Alert(Alert.AlertType.INFORMATION, "Items Saved successfully", ButtonType.OK).show();

                cleanText();

                // Close dialog after successful save
                handleClose(null);
            } else {
                System.out.println("Error while saving items");
                new Alert(Alert.AlertType.ERROR, "Error while saving items", ButtonType.OK).show();
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Item Code. Please enter a number.", ButtonType.OK).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage(), ButtonType.OK).show();
            throw new RuntimeException(e);
        }
    }

    private void cleanText() {
        itemCode_text.clear();
        des_text.clear();
        dosage_txt.clear();
    }
}