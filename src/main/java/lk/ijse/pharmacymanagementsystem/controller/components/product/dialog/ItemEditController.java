package lk.ijse.pharmacymanagementsystem.controller.components.product.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lk.ijse.pharmacymanagementsystem.dto.item.BatchDTO;
import lk.ijse.pharmacymanagementsystem.model.BatchModel;
import lk.ijse.pharmacymanagementsystem.model.BillModel;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemEditController implements Initializable {

    @FXML
    private Button close_btn;

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
    private VBox popupCard;

    @FXML
    private TextField qty_txt;

    @FXML
    private Button save_btn;

    @FXML
    private TextField sellPrice_txt;

    @FXML
    private TextField unitCost_txt;

    private int itemCode;
    private String invoice;
    private final BillModel billModel = new BillModel();
    private final BatchModel batchModel = new BatchModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.itemEditController = this;
        loadItem();
    }

    private void loadItem() {
        try {
            int billId = billModel.getBillIdByInvoice(invoice);
//            BatchDTO batchDTO = batchModel.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleClose(ActionEvent event) {

    }

    @FXML
    void handleFindItem(KeyEvent event) {

    }

    @FXML
    void handleUpdateItem(ActionEvent event) {

    }

    @FXML
    void handleDeleteItem(ActionEvent event) {

    }

    public void setItemCode(int itemCode, String invoice) throws SQLException, IOException {
        this.itemCode = itemCode;
        this.invoice = invoice;
    }
}
