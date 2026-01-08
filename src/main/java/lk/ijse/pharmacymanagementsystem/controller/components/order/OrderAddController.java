package lk.ijse.pharmacymanagementsystem.controller.components.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.controller.OrderController;
import lk.ijse.pharmacymanagementsystem.dto.item.BatchDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.model.BatchModel;
import lk.ijse.pharmacymanagementsystem.model.BillModel;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderAddController implements Initializable {

    @FXML
    private Button addToCart_btn;

    @FXML
    private TextField allTotal_lbl;

    @FXML
    private TextField avaQty_text;

    @FXML
    private Button closeBill_btn;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSellPrice;

    @FXML
    private TableColumn<?, ?> colSubTotal;

    @FXML
    private DatePicker date_txt;

    @FXML
    private TextField desc_txt;

    @FXML
    private ComboBox<?> discount_combo;

    @FXML
    private TextField discount_txt;

    @FXML
    private HBox holdList_bar;

    @FXML
    private Button hold_btn;

    @FXML
    private TextField invoice_txt;

    @FXML
    private TableView<?> itemAddView_tbl;

    @FXML
    private TextField itemCode_txt;

    @FXML
    private TextField qty_txt;

    @FXML
    private TextField totalQty_txt;

    @FXML
    private TextField unitPrice_txt;

    @FXML
    private TextField customer_id;

    private final BatchModel batchModel = new BatchModel();
    private final BillModel billModel = new BillModel();
    private final ItemModel itemModel = new ItemModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.orderAddController =this;

        //Today date set
        date_txt.setValue(LocalDate.now());
    }

    @FXML
    void handleFindItem(KeyEvent event) {
       halfClean();
       if (event.getCode() == KeyCode.ENTER) {
           int itemCode = Integer.parseInt(itemCode_txt.getText());

           try {
               BatchDTO latestBatch = batchModel.getLatestQTYBatchByItemCode(itemCode);

               if (latestBatch == null) {
                   new Alert(Alert.AlertType.WARNING, itemCode +" is Not on stock.", ButtonType.OK).show();
                   return;
               }

               String invoice = billModel.getInvoiceByBillId(latestBatch.getBill_id());
               ItemDTO itemDTO = itemModel.getItem(itemCode);

               if (itemDTO == null) {
                   new Alert(Alert.AlertType.WARNING, itemCode +" is Not on stock.", ButtonType.OK).show();
                   return;
               }

               invoice_txt.setText(invoice);
               desc_txt.setText(itemDTO.getDescription());
               unitPrice_txt.setText(String.valueOf(latestBatch.getSell_price()));
               avaQty_text.setText(String.valueOf(latestBatch.getQty()));
               totalQty_txt.setText(String.valueOf(latestBatch.getQty()));

           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }else {
           return;
       }
    }

    @FXML
    void handleAddToCart(ActionEvent event) {

    }

    @FXML
    void handleHoldLIst(ActionEvent event) {

    }

    @FXML
    void handlePayBill(ActionEvent event) {

    }

    private void halfClean() {
        invoice_txt.clear();
        desc_txt.clear();
        unitPrice_txt.clear();
        qty_txt.clear();
        avaQty_text.clear();
    }

    private void fullClean() {
        date_txt.getEditor().clear();
        invoice_txt.clear();
        itemCode_txt.clear();
        customer_id.clear();
        desc_txt.clear();
        unitPrice_txt.clear();
        qty_txt.clear();
        avaQty_text.clear();
        totalQty_txt.clear();
    }

    private void clean() {
        invoice_txt.clear();
        itemCode_txt.clear();
        customer_id.clear();
        desc_txt.clear();
        unitPrice_txt.clear();
        qty_txt.clear();
        avaQty_text.clear();
        totalQty_txt.clear();
    }
}
