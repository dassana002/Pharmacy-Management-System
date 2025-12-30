package lk.ijse.pharmacymanagementsystem.controller.components.product.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lk.ijse.pharmacymanagementsystem.dto.item.BatchDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.FreeDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.model.BatchModel;
import lk.ijse.pharmacymanagementsystem.model.BillModel;
import lk.ijse.pharmacymanagementsystem.model.FreeModel;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemEditController implements Initializable {

    @FXML
    private TextField des_text;

    @FXML
    private DatePicker expireDate_text;

    @FXML
    private TextField freeQty_txt;

    @FXML
    private TextField itemCode_text;

    @FXML
    private StackPane mainContent;

    @FXML
    private TextField qty_txt;

    @FXML
    private TextField sellPrice_txt;

    @FXML
    private TextField unitCost_txt;

    private int oldItemCode;
    private int oldQty;
    private double oldSellPrice;
    private double oldUnitCost;
    private LocalDate oldExpireDate;
    private int oldFreeQty;

    private int itemCode;
    private String invoice;
    private int billId;
    private BatchDTO batchDTO = new BatchDTO();
    private final BillModel billModel = new BillModel();
    private final BatchModel batchModel = new BatchModel();
    private final ItemModel itemModel = new ItemModel();
    private final FreeModel freeModel = new FreeModel();

    private static final String INT_REGEX = "^[0-9]+$";
    private static final String DOUBLE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.itemEditController = this;

        // Click background and Close dialog
        if (mainContent != null) {
            mainContent.setOnMouseClicked(event -> {
                if (event.getTarget() == mainContent) {
                    handleClose(null);
                }
            });
        }
    }

    private boolean isValid(TextField field, String regex) {
        return field.getText() != null && field.getText().matches(regex);
    }


    private void loadItem() {
        try {
            billId = billModel.getBillIdByInvoice(invoice);
            batchDTO = batchModel.getBatchByItemCodeAndBillId(itemCode, billId);

            if (batchDTO == null) {
                new Alert(Alert.AlertType.ERROR,"Batch not found for selected item", ButtonType.OK).show();
                return;
            }

            // set To textField
            itemCode_text.setText(String.valueOf(batchDTO.getItem_code()));

            ItemDTO itemDTO = itemModel.getItem(batchDTO.getItem_code());
            des_text.setText(itemDTO.getDescription());

            unitCost_txt.setText(String.valueOf(batchDTO.getCost_price()));
            sellPrice_txt.setText(String.valueOf(batchDTO.getSell_price()));
            qty_txt.setText(String.valueOf(batchDTO.getQty()));

            int freeQTY = freeModel.getFreeQtyById(batchDTO.getBatch_id());
            freeQty_txt.setText(String.valueOf(freeQTY));

            expireDate_text.setValue(LocalDate.parse(batchDTO.getExpired_date()));

            // set Old
            oldItemCode = Integer.parseInt(itemCode_text.getText());
            oldQty = Integer.parseInt(qty_txt.getText());
            oldSellPrice = Double.parseDouble(sellPrice_txt.getText());
            oldUnitCost = Double.parseDouble(unitCost_txt.getText());
            oldExpireDate = expireDate_text.getValue();
            oldFreeQty = freeQTY;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        // Close the dialog through ItemController
        if (References.productController != null) {
            References.productController.closeItemEditDialog();
        }
    }

    @FXML
    void handleUpdateItem(ActionEvent event) {
        try {
            int newItemCode = Integer.parseInt(itemCode_text.getText());
            double newUnitCost = Double.parseDouble(unitCost_txt.getText());
            double newSellPrice = Double.parseDouble(sellPrice_txt.getText());
            int newQty = Integer.parseInt(qty_txt.getText());
            int newFreeQty = Integer.parseInt(freeQty_txt.getText());
            LocalDate newExpireDate = expireDate_text.getValue();

            // Validation
            if (!isValid(itemCode_text, INT_REGEX) ||
                    !isValid(qty_txt, INT_REGEX) ||
                    !isValid(freeQty_txt, INT_REGEX) ||
                    !isValid(sellPrice_txt, DOUBLE_REGEX) ||
                    !isValid(unitCost_txt, DOUBLE_REGEX) ||
                    expireDate_text.getValue() == null) {

                new Alert(Alert.AlertType.ERROR,"Invalid data detected while loading item", ButtonType.OK).show();
                return;
            }

            boolean isChanged = oldItemCode != newItemCode ||
                                Double.compare(oldUnitCost, newUnitCost) != 0 ||
                                Double.compare(oldSellPrice, newSellPrice) != 0 ||
                                oldQty != newQty ||
                                oldFreeQty != newFreeQty ||
                                !Objects.equals(oldExpireDate, newExpireDate);

            if (!isChanged) {
                new Alert(Alert.AlertType.INFORMATION, "No changes detected!", ButtonType.OK).show();
                return;
            }

            BatchDTO newBatchDTO = new BatchDTO(
                    batchDTO.getBatch_id(),
                    batchDTO.getBatch_number(),
                    newSellPrice,
                    newUnitCost,
                    String.valueOf(newExpireDate),
                    newQty,
                    newQty+newFreeQty,
                    newItemCode,
                    billId
            );

            FreeDTO freeDTO = freeModel.getFreeById(batchDTO.getBatch_id());
            FreeDTO newFreeDTO = new FreeDTO(
                    freeDTO.getFree_id(),
                    batchDTO.getBatch_id(),
                    newFreeQty,
                    newFreeQty
            );

            boolean isUpdated = batchModel.updateBatchAndFree(newBatchDTO, newFreeDTO);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Item Updated!", ButtonType.OK).show();
                References.itemAddController.afterUpdate();
                handleClose(null);
            } else {
                new Alert(Alert.AlertType.ERROR, "Item Not Updated!", ButtonType.OK).show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleDeleteItem(ActionEvent event) {

        // Delete Confirm Alert
        Alert confirmAlert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this item?",
                ButtonType.YES,
                ButtonType.NO
        );

        confirmAlert.setTitle("Confirm Delete");
        confirmAlert.setHeaderText("Delete Item");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.YES) {
            return;
        }

        try {
            billId = billModel.getBillIdByInvoice(invoice);
            boolean isDeleteItem = batchModel.deleteBatch(batchDTO.getBatch_id(), billId);

            if (isDeleteItem) {
                new Alert(Alert.AlertType.INFORMATION, "Item Deleted!", ButtonType.OK).show();
                References.itemAddController.afterUpdate();
                handleClose(null);
            }else {
                new Alert(Alert.AlertType.ERROR, "Item Not Deleted!", ButtonType.OK).show();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setItemCode(int itemCode, String invoice) throws SQLException, IOException {
        this.itemCode = itemCode;
        this.invoice = invoice;

        loadItem();
    }
}
