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
import lk.ijse.pharmacymanagementsystem.model.FreeModel;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OutOfStockController implements Initializable {
    @FXML
    private TableColumn<OutOfStockTM, Integer> avaQTY_Col;

    @FXML
    private TableColumn<OutOfStockTM, Integer> batchNo_Col;

    @FXML
    private TableColumn<OutOfStockTM, String> desc_Col;

    @FXML
    private TableColumn<OutOfStockTM, Integer> itemCode_Col;

    @FXML
    private TableColumn<OutOfStockTM, String> supplier_Col;

    @FXML
    private TableView<OutOfStockTM> outOfStock_tbl;

    private final ObservableList<OutOfStockTM> outOfStockList = FXCollections.observableArrayList();
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

        loadTable();
    }

    private void loadTable() {
        try {
            ArrayList<BatchDTO> outOfStockBatches = batchModel.getOutOfStocks();
            if (outOfStockBatches == null || outOfStockBatches.isEmpty()) {
                outOfStock_tbl.getItems().clear();
                outOfStock_tbl.setPlaceholder(new Label("All items are in stock"));
                return;
            }

            for (BatchDTO batchDTO : outOfStockBatches) {
                ItemDTO item = itemModel.getItem(batchDTO.getItem_code());
                BillDTO billDTO = billModel.getBillById(batchDTO.getBill_id());

                OutOfStockTM outOfStockTM = new OutOfStockTM(
                        batchDTO.getItem_code(),
                        item.getDescription(),
                        batchDTO.getBatch_number(),
                        batchDTO.getAvailable_qty(),
                        billDTO.getCompany_name()
                );
                outOfStockList.add(outOfStockTM);
            }
            outOfStock_tbl.setItems(outOfStockList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleSendMessage(ActionEvent event) {

    }
}
