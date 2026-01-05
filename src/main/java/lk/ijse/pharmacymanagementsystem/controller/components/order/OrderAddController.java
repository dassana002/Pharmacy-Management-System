package lk.ijse.pharmacymanagementsystem.controller.components.order;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import lk.ijse.pharmacymanagementsystem.Launcher;
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
    private Button closeBill_btn;

    @FXML
    private Button close_btn;

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
    private HBox holdList_bar;

    @FXML
    private Button hold_btn;

    @FXML
    private TableView<?> itemAddView_tbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.orderAddController =this;
    }

    @FXML
    void handleAddToCart(ActionEvent event) {

    }

    @FXML
    void handleCloseBill(ActionEvent event) {

    }

    @FXML
    void handleHoldLIst(ActionEvent event) {

    }

    @FXML
    void handlePayBill(ActionEvent event) {

    }
}
