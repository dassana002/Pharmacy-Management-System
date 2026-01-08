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

    private static final String ORDER_ID_REGEX = "^[1-9][0-9]*$";
    private static final String DATE_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    private static final String NIC_REGEX = "^([0-9]{9}[VvXx]|[0-9]{12})$";
    private static final String ID_REGEX = "^[1-9][0-9]*$";
    private static final String QTY_REGEX = "^[1-9][0-9]*$";
    private static final String PRICE_REGEX = "^\\d+(\\.\\d{1,2})?$";
    private static final String INT_REGEX = "^[0-9]+$";
    private static final String DOUBLE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";

    private final BatchModel batchModel = new BatchModel();
    private final BillModel billModel = new BillModel();
    private final ItemModel itemModel = new ItemModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.orderAddController =this;

        //Today date set
        date_txt.setValue(LocalDate.now());
    }

    public static boolean isValid(String value, String regex) {
        return value != null && value.matches(regex);
    }

    @FXML
    void handleFindItem(KeyEvent event) {
       halfClean();
       if (event.getCode() == KeyCode.ENTER) {
           int itemCode = Integer.parseInt(itemCode_txt.getText());

           try {
               if (!isValid(itemCode_txt.getText(), INT_REGEX)) {
                   new Alert(Alert.AlertType.ERROR, "Invalid item code", ButtonType.OK).show();
                   return;
               }

               ItemDTO item = itemModel.getItem(itemCode);
               if (item == null) {
                   new Alert(Alert.AlertType.WARNING, itemCode +" is not found.", ButtonType.OK).show();
                   return;
               }

               BatchDTO latestBatch = batchModel.getLatestQTYBatchByItemCode(itemCode);
               if (latestBatch == null) {
                   new Alert(Alert.AlertType.WARNING, itemCode +" is not on stock.", ButtonType.OK).show();
                   return;
               }

               String invoice = billModel.getInvoiceByBillId(latestBatch.getBill_id());
               ItemDTO itemDTO = itemModel.getItem(itemCode);

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
        totalQty_txt.clear();
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
