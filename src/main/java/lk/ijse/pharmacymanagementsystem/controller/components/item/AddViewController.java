package lk.ijse.pharmacymanagementsystem.controller.components.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableColumn;

public class AddViewController {

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
    private TableColumn<?, ?> colCost;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSellprice;

    @FXML
    private TableColumn<?, ?> colSubTotal;

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
    private TableView<?> itemAddView_tbl;

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


}
