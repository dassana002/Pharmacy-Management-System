package lk.ijse.pharmacymanagementsystem.controller.components.item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lk.ijse.pharmacymanagementsystem.dto.item.*;

import javafx.scene.input.KeyEvent;
import lk.ijse.pharmacymanagementsystem.model.BatchModel;
import lk.ijse.pharmacymanagementsystem.model.DosageModel;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;
import lk.ijse.pharmacymanagementsystem.model.SupplierModel;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class AddViewController implements Initializable {

    @FXML
    private DatePicker expireDate_text;

    @FXML
    private DatePicker receivedDate_text;

    @FXML
    private TextField batchNo_txt;

    @FXML
    private TableColumn<AddItemTM, Double> colCost;

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
    private ComboBox<String> dosage_cmb;

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

    private final ItemModel itemModel = new ItemModel();
    private final DosageModel dosageModel = new DosageModel();
    private final SupplierModel supplierModel = new SupplierModel();
    private final BatchModel batchModel = new  BatchModel();
    private final ObservableList<AddItemTM> itemTMList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Auto Number Generate to table
//        colId.setCellFactory(column -> new TableCell<AddItemTM, Integer>() {
//            @Override
//            protected void updateItem(Integer item, boolean empty) {
//                super.updateItem(item, empty);
//
//                if (empty) {
//                    setText(null);
//                } else {
//                    setText(String.valueOf(getIndex() + 1));
//                }
//            }
//        });

        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        colSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        // Today Date Add
        todayDate_text.setValue(LocalDate.now());
        receivedDate_text.setValue(LocalDate.now());

        // Company Combo Box Load
        loadComboCompanies();

        // Move To Next Action
        moveToNextOnEnter(invoice_number_text, todayDate_text);
        moveToNextOnEnter(todayDate_text, receivedDate_text);
        moveToNextOnEnter(receivedDate_text, companyName_cmb);
        moveToNextOnEnter(companyName_cmb, itemCode_text);
//        moveToNextOnEnter(itemCode_text, dosage_cmb);
        moveToNextOnEnter(dosage_cmb, unitCost_txt);
        moveToNextOnEnter(unitCost_txt, sellPrice_txt);
        moveToNextOnEnter(sellPrice_txt, qty_txt);
        moveToNextOnEnter(qty_txt, freeQty_txt);
        moveToNextOnEnter(freeQty_txt, batchNo_txt);
        moveToNextOnEnter(batchNo_txt, expireDate_text);

        // Table Row Edit / Delete ContextMenu
        itemAddView_tbl.setRowFactory(tv -> {

            TableRow<AddItemTM> row = new TableRow<>();

            ContextMenu contextMenu = new ContextMenu();

            MenuItem edit = new MenuItem("Edit");
            MenuItem delete = new MenuItem("Delete");

            edit.setOnAction(event -> {
                AddItemTM selectedItem = row.getItem();
                System.out.println("Edit : " + selectedItem.getDescription());
                editItem(selectedItem.getItemId());
            });

            delete.setOnAction(event -> {
                AddItemTM selectedItem = row.getItem();
                System.out.println("Delete : " + selectedItem);
                deleteItem(selectedItem.getItemId());
            });

            contextMenu.getItems().addAll(edit, delete);

            // Row empty
            row.contextMenuProperty().bind(
                    javafx.beans.binding.Bindings
                            .when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );

            return row;
        });
    }

    private void editItem(int itemCode) {

    }

    private void deleteItem(int itemCode) {

    }

    private void setHoldList_bar() {

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

    private void loadComboDosages(int itemCode) {
        try {
            ArrayList<String> dosages = dosageModel.getDosageById(itemCode);

            if (dosages != null) {
                ObservableList<String> observableList = FXCollections.observableArrayList(dosages);
                dosage_cmb.setItems(observableList);
            }else {
                dosage_cmb.setItems(null);
            }
        } catch (Exception ex) {
            throw new RuntimeException();
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
                    loadComboDosages(itemCode);
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
    void handleAddToCart(ActionEvent event) {
        // Batch
        int batchNo = Integer.parseInt(batchNo_txt.getText());
        String invoice = invoice_number_text.getText();
        double sell_price = Double.parseDouble(sellPrice_txt.getText());
        double unit_cost = Double.parseDouble(unitCost_txt.getText());
        String todayDate = String.valueOf(todayDate_text.getValue());
        String receivedDate = String.valueOf(receivedDate_text.getValue());
        String expireDate = String.valueOf(expireDate_text.getValue());
        int qty = Integer.parseInt(qty_txt.getText());
        int free_qty = Integer.parseInt(freeQty_txt.getText());
        String status = "DRAFF";
        String companyName = companyName_cmb.getValue();

        // Item
        String desc = des_text.getText();
        int itemCode = Integer.parseInt(itemCode_text.getText());

        // Dosage
        String dosage = dosage_cmb.getValue();

        // Set to table
        int AllQty = qty + free_qty;
        double subTotal = qty * unit_cost;
        AddItemTM addItemTM = new AddItemTM(itemCode, desc, unit_cost, sell_price, AllQty, subTotal);
        itemTMList.add(addItemTM);

        try {
            int batchID = UUID.randomUUID().hashCode();
            int freeID = UUID.randomUUID().hashCode();
            int dosageID = UUID.randomUUID().hashCode();

            BatchDTO batchDTO = new BatchDTO(
                    batchID, batchNo, invoice, sell_price, unit_cost, todayDate,
                    receivedDate, expireDate, qty, AllQty, status, companyName, itemCode
            );

            FreeDTO freeDTO = new FreeDTO(
                    freeID, batchID, free_qty, free_qty
            );

            DosageDTO dosageDTO = new DosageDTO(
                    dosageID, dosage, itemCode
            );

            boolean isSave = batchModel.batchSaveTemp(batchDTO, freeDTO, dosageDTO);
            if (isSave) {
                System.out.println("Items saved successfully");
                new Alert(Alert.AlertType.INFORMATION, "Items Saved successfully", ButtonType.OK).show();
            }else {
                System.out.println("Error while saving items");
                new Alert(Alert.AlertType.ERROR, "Error while saving items", ButtonType.OK).show();
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

    @FXML
    void handleCloseBIll(ActionEvent event) {

    }

    @FXML
    void handleHoldLIst(ActionEvent event) {

    }

    @FXML
    void handleGetCompany(MouseEvent event) {

    }

    private void cleanText() {
        itemCode_text.clear();
        des_text.clear();
        dosage_cmb.getEditor().clear();
        unitCost_txt.clear();
        sellPrice_txt.clear();
        qty_txt.clear();
        freeQty_txt.clear();
        batchNo_txt.clear();
        expireDate_text.getEditor().clear();
    }

    private void cleanTable() {
        itemAddView_tbl.refresh();
    }
}
