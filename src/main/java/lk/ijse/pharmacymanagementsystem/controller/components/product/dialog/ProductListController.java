package lk.ijse.pharmacymanagementsystem.controller.components.product.dialog;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.input.KeyEvent;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.dto.item.NewItemTM;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;
import javafx.collections.transformation.FilteredList;
import lk.ijse.pharmacymanagementsystem.utility.References;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductListController implements Initializable {

    @FXML
    private TableColumn<NewItemTM, String> des_col;

    @FXML
    private TextField itemCode_txt;

    @FXML
    private TableView<NewItemTM> itemTable;

    @FXML
    private TableColumn<NewItemTM, Integer> itemCode_col;

    @FXML
    private StackPane mainContent;

    @FXML
    private VBox popupCard;


    private final ItemModel itemModel = new  ItemModel();
    private final ObservableList<NewItemTM> items = FXCollections.observableArrayList();
    private FilteredList<NewItemTM> filteredItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemCode_col.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        des_col.setCellValueFactory(new PropertyValueFactory<>("Description"));

        loadTable();

        filteredItems = new FilteredList<>(items, p -> true);
        itemTable.setItems(filteredItems);


        // This listener is triggered whenever the text in the Item Code TextField changes
        //  (while typing or deleting characters)
        itemCode_txt.textProperty().addListener((obs, oldVal, newVal) -> {

            //    Allow only numeric input (digits 0–9)
            //    If the user enters letters or symbols, they are automatically removed
            if (!newVal.matches("\\d*")) {
                itemCode_txt.setText(newVal.replaceAll("[^\\d]", ""));
                return;
            }

            //    Filter the TableView based on the entered item code
            //    If the TextField is empty → show all items
            //    Otherwise → show only items whose itemCode contains the entered value
            filteredItems.setPredicate(item ->
                    newVal.isEmpty() || String.valueOf(item.getItemCode()).contains(newVal)
            );
        });

        // Row Catch
        itemTable.setRowFactory(tv -> {
            TableRow<NewItemTM> row = new TableRow<>();

            // Double Mouse Click Check
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {

                    NewItemTM selectedItem = row.getItem();

                    int itemCode = selectedItem.getItemCode();
                    String description = selectedItem.getDescription();

                    // Add to Item Add Controller TextFields
                    References.itemAddController.setItem(itemCode,description);

                    handleClose(null);
                }
            });

            return row;
        });

        if (mainContent != null) {
            mainContent.setOnMouseClicked(event -> {
                if (event.getTarget() == mainContent) {
                    handleClose(null);
                }
            });
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        // Close the dialog through ItemController
        if (References.productController != null) {
            References.productController.closeItemListDialog();
        }
        if (References.orderController != null) {
            References.orderController.closeItemListDialog();
        }
    }

    public void loadTable() {
        try {
            ArrayList<ItemDTO> itemDTOS = itemModel.getAllItems();
            for(ItemDTO itemDTO : itemDTOS) {
                NewItemTM newItemTM = new NewItemTM(
                        itemDTO.getItem_code(),
                        itemDTO.getDescription()
                );
                items.add(newItemTM);
            }
            itemTable.setItems(items);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
