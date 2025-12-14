package lk.ijse.pharmacymanagementsystem.controller.components.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;
import lk.ijse.pharmacymanagementsystem.Dto.AddItemTM;

import java.net.URL;
import java.util.ResourceBundle;

public class AddViewController implements Initializable {

    @FXML
    private DatePicker DueDate_text;

    @FXML
    private DatePicker ExpireDate_text;

    @FXML
    private DatePicker ReceivedDate_text;

    @FXML
    private TextField availableQty_txt;

    @FXML
    private TextField batchNo_txt;

    @FXML
    private TableColumn<AddItemTM, Double> colCost;

    @FXML
    private TableColumn<AddItemTM, String> colDesc;

    @FXML
    private TableColumn<AddItemTM, Integer> colNo;

    @FXML
    private TableColumn<AddItemTM, Integer> colQty;

    @FXML
    private TableColumn<AddItemTM, Double> colSellPrice;

    @FXML
    private TableColumn<AddItemTM, Double> colSubTotal;

    @FXML
    private TextField companyName_text;

    @FXML
    private TextField des_text;

    @FXML
    private TextField dosage_txt;

    @FXML
    private TextField freeQty_txt;

    @FXML
    private TextField invoice_number_text;

    @FXML
    private TableView<AddItemTM> itemAddView_tbl;

    @FXML
    private TextField itemCode_text;

    @FXML
    private TextField qty_txt;

    @FXML
    private TextField sellPrice_txt;

    @FXML
    private DatePicker todayDate_text;

    @FXML
    private TextField unitCost_txt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colNo.setCellValueFactory(new PropertyValueFactory<>("NO"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("Unit_Cost"));
        colSellPrice.setCellValueFactory(new PropertyValueFactory<>("Sell_Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("Sub_Total"));
    }

    public void addToCart() {
        invoice_number_text.getText();
    }
}
