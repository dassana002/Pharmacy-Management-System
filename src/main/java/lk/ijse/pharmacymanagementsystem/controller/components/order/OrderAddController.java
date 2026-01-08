package lk.ijse.pharmacymanagementsystem.controller.components.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.controller.OrderController;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.io.IOException;
import java.net.URL;
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
    private TableColumn<?, ?> colUnitCost;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.orderAddController =this;
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

    public void setItem(int itemCode, String description) {
        itemCode_txt.setText(String.valueOf(itemCode));
        desc_txt.setText(description);
    }
}
