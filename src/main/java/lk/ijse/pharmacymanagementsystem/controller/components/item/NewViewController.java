package lk.ijse.pharmacymanagementsystem.controller.components.item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pharmacymanagementsystem.dto.item.ItemDTO;
import lk.ijse.pharmacymanagementsystem.model.ItemModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 *
 *
 *
 * Items save part Check
 *
 *
 *
 * */

public class NewViewController implements Initializable {

    @FXML
    private TableColumn<ItemDTO, Integer> code_Col;

    @FXML
    private TextField des_text;

    @FXML
    private TableColumn<ItemDTO, String> description_Col;

    @FXML
    private TableColumn<ItemDTO, String> dosage_col;

    @FXML
    private TextField dosage_txt;

    @FXML
    private TextField itemCode_text;

    @FXML
    private TableView<ItemDTO> newView_table;

    private final ItemModel  itemModel = new ItemModel();
    private final ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        code_Col.setCellValueFactory(new PropertyValueFactory<>("item_code"));
        description_Col.setCellValueFactory(new PropertyValueFactory<>("description"));
        dosage_col.setCellValueFactory(new PropertyValueFactory<>("dosage"));
    }

    @FXML
    void handleAddToTable(ActionEvent event) {
        int itemCode = Integer.parseInt(itemCode_text.getText());
        String description = des_text.getText();
        String dosage = dosage_txt.getText();

        ItemDTO newItem = new ItemDTO(itemCode, description, dosage);
        itemList.add(newItem);

        cleanText();
        loadItemTable();
    }

    private void loadItemTable() {
        newView_table.setItems(itemList);
    }

    @FXML
    void handleSaveItem(ActionEvent event) throws SQLException {
        ArrayList<ItemDTO> items = new ArrayList<>(itemList);
        boolean isSave = itemModel.save(items);

        if (isSave) {
            System.out.println("Items saved successfully");
            new Alert(Alert.AlertType.INFORMATION, "Items Saved successfully", ButtonType.OK).show();

        }else{
            System.out.println("Error while saving items");
            new Alert(Alert.AlertType.ERROR, "Error while saving items", ButtonType.OK).show();
        }

        cleanText();
        cleanTable();
    }

    private void cleanTable() {
        itemList.clear();
        newView_table.refresh();
    }

    private void cleanText() {
        itemCode_text.clear();
        des_text.clear();
        dosage_txt.clear();
    }
}
