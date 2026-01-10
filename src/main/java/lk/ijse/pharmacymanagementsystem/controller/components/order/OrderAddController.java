package lk.ijse.pharmacymanagementsystem.controller.components.order;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.controller.OrderController;
import lk.ijse.pharmacymanagementsystem.dto.item.AddItemTM;
import lk.ijse.pharmacymanagementsystem.dto.item.BatchDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.dto.order.OrderDTO;
import lk.ijse.pharmacymanagementsystem.dto.order.OrderDetailDTO;
import lk.ijse.pharmacymanagementsystem.dto.order.OrderTM;
import lk.ijse.pharmacymanagementsystem.model.*;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;

public class OrderAddController implements Initializable {

    @FXML
    private TextField allTotal_lbl;

    @FXML
    private TextField avaQty_text;

    @FXML
    private TableColumn<OrderTM, String> colDesc;

    @FXML
    private TableColumn<OrderTM, Integer> colItemId;

    @FXML
    private TableColumn<OrderTM, Integer> colQty;

    @FXML
    private TableColumn<OrderTM, Double> colSellPrice;

    @FXML
    private TableColumn<OrderTM, Double> colSubTotal;

    @FXML
    private TextField date_txt;

    @FXML
    private TextField desc_txt;

    @FXML
    private ComboBox<OrderTM> discount_combo;

    @FXML
    private TextField discount_txt;

    @FXML
    private HBox holdList_bar;

    @FXML
    private TextField invoice_txt;

    @FXML
    private TableView<OrderTM> itemAddView_tbl;

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

    private static final String NIC_REGEX = "^([0-9]{9}[VvXx]|[0-9]{12})$";
    private static final String INT_REGEX = "^[0-9]+$";
    private static final String DOUBLE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    private static final String TEXT_REGEX = "^[A-Za-z0-9 .,-]+$";
    private static final String DATE_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";


    private final BatchModel batchModel = new BatchModel();
    private final BillModel billModel = new BillModel();
    private final ItemModel itemModel = new ItemModel();
    private final OrderModel orderModel = new OrderModel();
    private final OrderDetailModel orderDetailModel = new OrderDetailModel();
    private final ObservableList<OrderTM> orderTMList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.orderAddController =this;

        colItemId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        //Today date set
        date_txt.setText(String.valueOf(LocalDate.now()));
    }

    // Validation
    private boolean isValid(TextField field, String regex) {
        return field.getText() != null && !field.getText().isEmpty() && field.getText().matches(regex);
    }

    @FXML
    void handleFindItem(KeyEvent event) {
       halfClean();
       if (event.getCode() == KeyCode.ENTER) {
           int itemCode = Integer.parseInt(itemCode_txt.getText());

           try {
               if (!isValid(itemCode_txt, INT_REGEX)) {
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
        if (!isValid(itemCode_txt, INT_REGEX) ||
            !isValid(customer_id, NIC_REGEX) ||
            !isValid(qty_txt, INT_REGEX)
        ) {
            new Alert(Alert.AlertType.ERROR, "Please Complete All Fields.. ", ButtonType.OK).show();
            return;
        }

        String date = date_txt.getText();
        int itemCode = Integer.parseInt(itemCode_txt.getText());
        String customerId = customer_id.getText();
        String description = desc_txt.getText();
        double unitPrice = Double.parseDouble(unitPrice_txt.getText());
        int qty  = Integer.parseInt(qty_txt.getText());
        int qtyTotal = Integer.parseInt(totalQty_txt.getText());

        try {
            ItemDTO item = itemModel.getItem(itemCode);
            if (item == null) {
                new Alert(Alert.AlertType.WARNING, itemCode +" is not found.", ButtonType.OK).show();
                return;
            }

            // Add To Table
            double subTotal = qtyTotal * unitPrice;
            OrderTM orderTM = new OrderTM(
                    itemCode,
                    description,
                    unitPrice,
                    qty,
                    subTotal
            );
            orderTMList.add(orderTM);

            // Order Save
            String orderId = UUID.randomUUID().toString();
            OrderDTO orderDTO = new OrderDTO(
                    orderId,
                    date,
                    Status.DRAFF,
                    customerId,
                    1
            );

            // get Batch by item code
            BatchDTO latestBatch = batchModel.getLatestQTYBatchByItemCode(itemCode);
            if (latestBatch == null) {
                new Alert(Alert.AlertType.WARNING, itemCode +" is not on stock.", ButtonType.OK).show();
                return;
            }

            // Order Detail Save
            String orderDetailId = UUID.randomUUID().toString();
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
                    orderDetailId,
                    orderId,
                    latestBatch.getBatch_id(),
                    qty,
                    unitPrice,
                    subTotal
            );

            boolean isOrderSave = orderModel.placeOrder(orderDTO, orderDetailDTO);

            if (isOrderSave) {
                System.out.println("Order saved successfully");
                new Alert(Alert.AlertType.INFORMATION, "Items Saved successfully", ButtonType.OK).show();
                generateTotal();
            }else {
                System.out.println("Error while saving Order");
                new Alert(Alert.AlertType.ERROR, "Error while saving items", ButtonType.OK).show();
            }

            // load table
            loadItemTable();
            clean();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateTotal() {
        double total = 0.0;
        for (OrderTM orderTM : orderTMList) {
            total += orderTM.getSubTotal();
        }
        allTotal_lbl.setText("Total : "+ String.valueOf(total));
    }

    public void loadItemTable() {
        itemAddView_tbl.setItems(orderTMList);
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
        date_txt.clear();
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
