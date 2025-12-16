package lk.ijse.pharmacymanagementsystem.controller.components.item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pharmacymanagementsystem.Dto.AddItemTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddViewController implements Initializable {

    @FXML
    private DatePicker expireDate_text;

    @FXML
    private DatePicker receivedDate_text;

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

        // Auto Number
        colNo.setCellFactory(column -> new TableCell<AddItemTM, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });

//        colNo.setCellValueFactory(new PropertyValueFactory<>("NO"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("Unit_Cost"));
        colSellPrice.setCellValueFactory(new PropertyValueFactory<>("Sell_Price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("Sub_Total"));

        // Today Date Add
        todayDate_text.setValue(LocalDate.now());
        receivedDate_text.setValue(LocalDate.now());
    }
    
    @FXML
    void handleAddToCart(ActionEvent event) {
        String invoiceText= invoice_number_text.getText();
        String todayDate = todayDate_text.getValue().toString();
        String receivedDate = receivedDate_text.getValue().toString();
        int itemCode = Integer.parseInt(itemCode_text.getText());
        String dosage = dosage_txt.getText();
        double unitCost = Double.parseDouble(unitCost_txt.getText());
        double sellPrice = Double.parseDouble(sellPrice_txt.getText());
        int qty = Integer.parseInt(qty_txt.getText());
    }

    @FXML
    void handleCloseBIll(ActionEvent event) {

    }

    @FXML
    void handleHoldLIst(ActionEvent event) {

    }
}
