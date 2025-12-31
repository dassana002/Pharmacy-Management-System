package lk.ijse.pharmacymanagementsystem.controller.components.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pharmacymanagementsystem.dto.item.*;
import lk.ijse.pharmacymanagementsystem.model.BatchModel;
import lk.ijse.pharmacymanagementsystem.model.BillModel;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExpireDateController implements Initializable {
    @FXML
    private TableColumn<ExpireDateTM, Integer> avaQTY_Col;

    @FXML
    private TableColumn<ExpireDateTM, Integer> batchNo_Col;

    @FXML
    private TableColumn<ExpireDateTM, String> desc_Col;

    @FXML
    private TableColumn<ExpireDateTM, String> expireDate_col;

    @FXML
    private TableColumn<ExpireDateTM, Integer> itemCode_Col;

    @FXML
    private TableView<ExpireDateTM> expire_tbl;

    @FXML
    private TableColumn<ExpireDateTM, String> supplier_Col;

    private final ObservableList<ExpireDateTM> items = FXCollections.observableArrayList();
    private final BatchModel batchModel = new BatchModel();
    private final ItemModel itemModel = new ItemModel();
    private final BillModel billModel = new BillModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemCode_Col.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        desc_Col.setCellValueFactory(new PropertyValueFactory<>("description"));
        batchNo_Col.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        avaQTY_Col.setCellValueFactory(new PropertyValueFactory<>("availableQty"));
        supplier_Col.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        expireDate_col.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        loadTable();
    }

    private void loadTable() {
        try {
            ArrayList<BatchDTO> expireBatches = batchModel.getExpireBatches();
            if (expireBatches == null || expireBatches.isEmpty()) {
                expire_tbl.getItems().clear();
                expire_tbl.setPlaceholder(new Label("All items are in stock"));
                return;
            }

            for (BatchDTO batchDTO : expireBatches) {
                ItemDTO item = itemModel.getItem(batchDTO.getItem_code());
                BillDTO billDTO = billModel.getBillById(batchDTO.getBill_id());

                ExpireDateTM expireDateTM = new ExpireDateTM(
                        batchDTO.getItem_code(),
                        item.getDescription(),
                        batchDTO.getBatch_number(),
                        batchDTO.getAvailable_qty(),
                        billDTO.getCompany_name(),
                        batchDTO.getExpired_date()
                );
                items.add(expireDateTM);
            }
            expire_tbl.setItems(items);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleSendMessage(ActionEvent event) {

    }

}
