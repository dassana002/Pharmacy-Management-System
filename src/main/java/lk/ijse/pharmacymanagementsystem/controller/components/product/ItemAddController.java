package lk.ijse.pharmacymanagementsystem.controller.components.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import lk.ijse.pharmacymanagementsystem.controller.components.order.OrderAddController;
import lk.ijse.pharmacymanagementsystem.dto.item.*;

import javafx.scene.input.KeyEvent;
import lk.ijse.pharmacymanagementsystem.model.*;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ItemAddController implements Initializable {

    @FXML
    private DatePicker expireDate_text;

    @FXML
    private DatePicker receivedDate_text;

    @FXML
    private TextField batchNo_txt;

    @FXML
    private TableColumn<AddItemTM, Double> colUnitCost;

    @FXML
    private TableColumn<AddItemTM, String> colDesc;

    @FXML
    private TableColumn<AddItemTM, Integer> colItemId;

    @FXML
    private TableColumn<AddItemTM, Integer> colQty;

    @FXML
    private TableColumn<AddItemTM, Double> colSellPrice;

    @FXML
    private TableColumn<AddItemTM, Double> colSubTotal;

    @FXML
    private ComboBox<String> companyName_cmb;

    @FXML
    private TextField des_text;

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

    @FXML
    private HBox holdList_bar;

    @FXML
    private TextField allTotal_lbl;

    private String currentInvoice;

    private final ItemModel itemModel = new ItemModel();
    private final SupplierModel supplierModel = new SupplierModel();
    private final BatchModel batchModel = new  BatchModel();
    private final FreeModel freeModel = new FreeModel();
    private final BillModel billModel = new BillModel();
    private final ObservableList<AddItemTM> itemTMList = FXCollections.observableArrayList();

    private static final String INT_REGEX = "^[0-9]+$";
    private static final String DOUBLE_REGEX = "^[0-9]+(\\.[0-9]{1,2})?$";
    private static final String TEXT_REGEX = "^[A-Za-z0-9 .,-]+$";
    private static final String INVOICE_REGEX = "^[A-Z0-9-]+$";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        References.itemAddController = this;

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitCost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        colSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        // Today Date Add
        todayDate_text.setValue(LocalDate.now());
        receivedDate_text.setValue(LocalDate.now());

        // Company Combo Box Load
        loadComboCompanies();

        // Move To Next Action
        moveToNextOnEnter(todayDate_text, receivedDate_text);
        moveToNextOnEnter(receivedDate_text, companyName_cmb);
        moveToNextOnEnter(companyName_cmb, itemCode_text);
//        moveToNextOnEnter(itemCode_text, dosage_cmb);
        moveToNextOnEnter(unitCost_txt, sellPrice_txt);
        moveToNextOnEnter(sellPrice_txt, qty_txt);
        moveToNextOnEnter(qty_txt, freeQty_txt);
        moveToNextOnEnter(freeQty_txt, batchNo_txt);
        moveToNextOnEnter(batchNo_txt, expireDate_text);

        // Table Row Edit ContextMenu
        itemAddView_tbl.setRowFactory(tv -> {

            TableRow<AddItemTM> row = new TableRow<>();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem edit = new MenuItem("Edit");

            edit.setOnAction(event -> {
                AddItemTM selectedItem = row.getItem();
                try {
                    editItem(selectedItem.getItemId());
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            });

            contextMenu.getItems().addAll(edit);

            // Row empty
            row.contextMenuProperty().bind(
                    javafx.beans.binding.Bindings
                            .when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });

        // Load Automate in Hold Bill List
        setHoldList_bar();
    }

    public void setItem(int itemCode, String desc) {
        itemCode_text.setText(String.valueOf(itemCode));
        des_text.setText(desc);
    }

    private boolean isValid(TextField field, String regex) {
        return field.getText() != null && !field.getText().isEmpty() && field.getText().matches(regex);
    }

    private void editItem(int itemCode) throws SQLException, IOException {
        // Load Edit Product
        References.productController.editItemDialog();
        // the Edit product passed
        References.itemEditController.setItemCode(itemCode, currentInvoice);
    }

    private void setHoldList_bar() {
        try {
            ArrayList<BillDTO> billDTOS = billModel.getAllBills();
            for (BillDTO billDTO : billDTOS) {
                createHoldBillButton(billDTO);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createHoldBillButton(BillDTO billDTO) {
        Button invoiceBtn = new Button(billDTO.getInvoice_number());

        invoiceBtn.setPrefHeight(30);
        invoiceBtn.setMaxWidth(Double.MAX_VALUE);
        invoiceBtn.setStyle("""
        -fx-background-color: #16a34a;
        -fx-text-fill: white;
        -fx-font-size: 14;
        -fx-background-radius: 8;
    """);

        // Action
        invoiceBtn.setOnAction(e -> {
            cleanTable();
            halfCleanText();
            allTotal_lbl.clear();
            todayDate_text.setValue(LocalDate.now());
            try {
                // get All Batches by BillId
                BillDTO billDTOs= billModel.getBillById(billDTO.getBill_id());

                invoice_number_text.setText(billDTOs.getInvoice_number());
                todayDate_text.setValue(LocalDate.parse(billDTOs.getToday_date()));
                receivedDate_text.setValue(LocalDate.parse(billDTOs.getReceived_date()));
                companyName_cmb.setValue(billDTOs.getCompany_name());

                // batches set To table
                for (BatchDTO batchDTO : billDTOs.getBatchDtoList()) {
                    // getItem
                    ItemDTO itemDTO = itemModel.getItem(batchDTO.getItem_code());
                    // calculate AllQty
                    int AllQty = batchDTO.getQty() + freeModel.getFreeQtyById(batchDTO.getBatch_id());
                    // calculate sub Total
                    double subTotal = batchDTO.getQty() * batchDTO.getCost_price();

                    AddItemTM addItemTM = new AddItemTM(
                            batchDTO.getItem_code(),
                            itemDTO.getDescription(),
                            batchDTO.getCost_price(),
                            batchDTO.getSell_price(),
                            AllQty,
                            subTotal
                    );
                    itemTMList.add(addItemTM);
                    calcAllTotal();
                }
                loadItemTable();

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Button Margin set
        HBox.setMargin(invoiceBtn, new Insets(0, 10, 0, 10));

        // Add button into Container
        holdList_bar.getChildren().add(invoiceBtn);
    }


    @FXML
    void handleHoldLIst(ActionEvent event) {
        try {
            // get Bill Id
            String billId = billModel.getBillIdByInvoice(invoice_number_text.getText());
            // get Bill Dto
            BillDTO billDTO = billModel.getBillById(billId);
            createHoldBillButton(billDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handlePayBill(ActionEvent event) {

    }

    @FXML
    void handleCloseBill(ActionEvent event) {
        try {
            String invoice = invoice_number_text.getText();
            References.productController.payDialog(invoice);
            boolean isPublished = billModel.changeStatus("PUBLISHED", invoice);

            if (isPublished) {
                new Alert(Alert.AlertType.INFORMATION, invoice+" Bill is Closed", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, invoice+" Bill is Not Closed", ButtonType.OK).show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void findManyItems(KeyEvent event) {
        cleanTable();
        halfCleanText();
        allTotal_lbl.clear();
        todayDate_text.setValue(LocalDate.now());
        try {
            if (event.getCode() == KeyCode.ENTER) {
                if (isValid(invoice_number_text, INVOICE_REGEX)) {
                    String billId = billModel.getBillIdByInvoice(invoice_number_text.getText());

                    currentInvoice = invoice_number_text.getText();
                    if (billId == null) {
                        new Alert(Alert.AlertType.WARNING, "Invoice Not Found", ButtonType.OK).show();
                        return;
                    }

                    // Check current status
                    Status status = Status.valueOf(billModel.searchStatusById(billId));

                    if (status != Status.DRAFF) {
                        new Alert(Alert.AlertType.WARNING, "This Invoice is Already Closed", ButtonType.OK).show();
                        return;
                    }

                    // get All Batches by BillId
                    BillDTO billDTOs= billModel.getBillById(billId);

                    todayDate_text.setValue(LocalDate.parse(billDTOs.getToday_date()));
                    receivedDate_text.setValue(LocalDate.parse(billDTOs.getReceived_date()));
                    companyName_cmb.setValue(billDTOs.getCompany_name());

                    // batches set To table
                    for (BatchDTO batchDTO : billDTOs.getBatchDtoList()) {
                        // getItem
                        ItemDTO itemDTO = itemModel.getItem(batchDTO.getItem_code());
                        // calculate AllQty
                        int AllQty = batchDTO.getQty() + freeModel.getFreeQtyById(batchDTO.getBatch_id());
                        // calculate sub Total
                        double subTotal = batchDTO.getQty() * batchDTO.getCost_price();

                        AddItemTM addItemTM = new AddItemTM(
                                batchDTO.getItem_code(),
                                itemDTO.getDescription(),
                                batchDTO.getCost_price(),
                                batchDTO.getSell_price(),
                                AllQty,
                                subTotal
                        );
                        itemTMList.add(addItemTM);
                    }
                    calcAllTotal();
                }else {
                    fullCleanText();
                    cleanTable();
                    new Alert(Alert.AlertType.WARNING, "Invalid Character using", ButtonType.OK).show();
                }
                loadItemTable();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void moveToNextOnEnter(Control current, Control next) {
        current.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER -> {
                    next.requestFocus();
                    event.consume();
                }
            }
        });
    }

    private void loadComboCompanies() {
        try {
            ArrayList<SupplierDTO> supplierDTOS = supplierModel.getSuppliers();

            if (supplierDTOS != null) {
                ObservableList<String> companyNames = FXCollections.observableArrayList();
                for (SupplierDTO supplierDTO : supplierDTOS) {
                    companyNames.add(supplierDTO.getCompany_name());
                }
                companyName_cmb.setItems(companyNames);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void handleFindItem(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                int itemCode = Integer.parseInt(itemCode_text.getText());

                ItemDTO item = itemModel.getItem(itemCode);
                if (item != null) {
                    des_text.setText(item.getDescription());
                    batchNo_txt.setText(String.valueOf(batchModel.getBatchesCount(itemCode) + 1));
                }else {
                    new Alert(Alert.AlertType.ERROR, "Item Not Found", ButtonType.OK).show();
                    itemCode_text.clear();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @FXML
    void handleAddToCart(ActionEvent event) throws SQLException {

        //  Validation
        if (!isValid(batchNo_txt, INT_REGEX) ||
                !isValid(freeQty_txt, INT_REGEX) ||
                !isValid(sellPrice_txt, DOUBLE_REGEX) ||
                !isValid(unitCost_txt, DOUBLE_REGEX) ||
                !isValid(qty_txt, INT_REGEX) ||
                !isValid(itemCode_text, INT_REGEX) ||
                !isValid(invoice_number_text, INVOICE_REGEX) ||
                !des_text.getText().matches(TEXT_REGEX) ||
                todayDate_text.getValue() == null ||
                receivedDate_text.getValue() == null ||
                expireDate_text.getValue() == null ||
                companyName_cmb.getValue() == null
        ) {
            new Alert(Alert.AlertType.WARNING, "Invalid input detected").show();
            return;
        }

        // Batch
        int batchNo = Integer.parseInt(batchNo_txt.getText());
        double sell_price = Double.parseDouble(sellPrice_txt.getText());
        double unit_cost = Double.parseDouble(unitCost_txt.getText());
        String todayDate = String.valueOf(todayDate_text.getValue());
        String receivedDate = String.valueOf(receivedDate_text.getValue());
        String expireDate = String.valueOf(expireDate_text.getValue());
        int qty = Integer.parseInt(qty_txt.getText());
        int free_qty = Integer.parseInt(freeQty_txt.getText());
        Status status = Status.DRAFF;
        String companyName = companyName_cmb.getValue();

        //  Bill
        String invoice = invoice_number_text.getText();

        // Item
        String desc = des_text.getText();
        int itemCode = Integer.parseInt(itemCode_text.getText());

        // Set to table
        int AllQty = qty + free_qty;
        double subTotal = qty * unit_cost;
        AddItemTM addItemTM = new AddItemTM(itemCode, desc, unit_cost, sell_price, AllQty, subTotal);
        itemTMList.add(addItemTM);

        // UUID generate

        try {
            String bill = billModel.isExistsBill(invoice);
            String batchID = UUID.randomUUID().toString();
            if (bill != null) {
                // save Batch
                BatchDTO batchDTO = new BatchDTO(
                        batchID,
                        batchNo,
                        sell_price,
                        unit_cost,
                        expireDate,
                        qty,
                        qty,
                        itemCode,
                        bill
                );

                String freeID = UUID.randomUUID().toString();
                FreeDTO freeDTO = new FreeDTO(
                        freeID, batchID, free_qty, free_qty
                );

                boolean isSave = batchModel.saveItem(batchDTO, freeDTO, bill);

                if (isSave) {
                    System.out.println("Items saved successfully");
                    new Alert(Alert.AlertType.INFORMATION, "Items Saved successfully", ButtonType.OK).show();
                    calcAllTotal();
                }else {
                    System.out.println("Error while saving items");
                    new Alert(Alert.AlertType.ERROR, "Error while saving items", ButtonType.OK).show();
                }
            } else {
                String billId = UUID.randomUUID().toString();
                BatchDTO batchDTO = new BatchDTO(
                        batchID,
                        batchNo,
                        sell_price,
                        unit_cost,
                        expireDate,
                        qty,
                        qty,
                        itemCode,
                        billId
                );

                String freeID = UUID.randomUUID().toString();
                FreeDTO freeDTO = new FreeDTO(
                        freeID, batchID, free_qty, free_qty
                );

                // save bill
                BillDTO billDTO = new BillDTO(
                        billId,
                        invoice,
                        status,
                        companyName,
                        todayDate,
                        receivedDate
                );
                boolean isSave = billModel.saveBill(batchDTO, freeDTO, billDTO);

                if (isSave) {
                    System.out.println("Items saved successfully");
                    new Alert(Alert.AlertType.INFORMATION, "Items Saved successfully", ButtonType.OK).show();
                    calcAllTotal();
                }else {
                    System.out.println("Error while saving items");
                    new Alert(Alert.AlertType.ERROR, "Error while saving items", ButtonType.OK).show();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        loadItemTable();
        cleanText();
    }

    public void loadItemTable() {
        itemAddView_tbl.setItems(itemTMList);
    }

    public void afterUpdate() {
        cleanTable();
        try {
            String billId = billModel.getBillIdByInvoice(invoice_number_text.getText());

            currentInvoice = invoice_number_text.getText();
            if (billId == null) {
                new Alert(Alert.AlertType.WARNING, "Invoice Not Found", ButtonType.OK).show();
                return;
            }

            // Check current status
            Status status = Status.valueOf(billModel.searchStatusById(billId));

            if (status != Status.DRAFF) {
                new Alert(Alert.AlertType.WARNING, "This Invoice is Already Closed", ButtonType.OK).show();
                return;
            }

            // get All Batches by BillId
            BillDTO billDTOs= billModel.getBillById(billId);

            todayDate_text.setValue(LocalDate.parse(billDTOs.getToday_date()));
            receivedDate_text.setValue(LocalDate.parse(billDTOs.getReceived_date()));
            companyName_cmb.setValue(billDTOs.getCompany_name());

            // batches set To table
            for (BatchDTO batchDTO : billDTOs.getBatchDtoList()) {
                // getItem
                ItemDTO itemDTO = itemModel.getItem(batchDTO.getItem_code());
                // calculate AllQty
                int AllQty = batchDTO.getQty() + freeModel.getFreeQtyById(batchDTO.getBatch_id());
                // calculate sub Total
                double subTotal = batchDTO.getQty() * batchDTO.getCost_price();

                AddItemTM addItemTM = new AddItemTM(
                        batchDTO.getItem_code(),
                        itemDTO.getDescription(),
                        batchDTO.getCost_price(),
                        batchDTO.getSell_price(),
                        AllQty,
                        subTotal
                );
                itemTMList.add(addItemTM);
            }
            calcAllTotal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void calcAllTotal() {
        double total = 0.0;
        for (AddItemTM addItemTM : itemTMList) {
            total += addItemTM.getSubTotal();
        }
        allTotal_lbl.setText("Total: "+String.valueOf(total));
    }

    private void cleanText() {
        itemCode_text.clear();
        des_text.clear();
        unitCost_txt.clear();
        sellPrice_txt.clear();
        qty_txt.clear();
        freeQty_txt.clear();
        batchNo_txt.clear();
        expireDate_text.getEditor().clear();
    }

    private void fullCleanText() {
        invoice_number_text.clear();
        todayDate_text.getEditor().clear();
        receivedDate_text.getEditor().clear();
        companyName_cmb.getEditor().clear();
        itemCode_text.clear();
        des_text.clear();
        unitCost_txt.clear();
        sellPrice_txt.clear();
        qty_txt.clear();
        freeQty_txt.clear();
        batchNo_txt.clear();
        expireDate_text.getEditor().clear();
    }

    private void halfCleanText() {
        companyName_cmb.getEditor().clear();
        receivedDate_text.getEditor().clear();
        companyName_cmb.getEditor().clear();
        itemCode_text.clear();
        des_text.clear();
        unitCost_txt.clear();
        sellPrice_txt.clear();
        qty_txt.clear();
        freeQty_txt.clear();
        batchNo_txt.clear();
        expireDate_text.getEditor().clear();
    }

    private void cleanTable() {
        itemTMList.clear();
        itemAddView_tbl.refresh();
    }
}
