package lk.ijse.pharmacymanagementsystem.controller.components.item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.pharmacymanagementsystem.Dto.ItemDTO;
import lk.ijse.pharmacymanagementsystem.Dto.NewItemTM;
import lk.ijse.pharmacymanagementsystem.Model.ItemModel;

import java.net.URL;
import java.util.ResourceBundle;

public class NewViewController implements Initializable {

    @FXML
    private Button addtable_btn;

    @FXML
    private TableColumn<NewItemTM, Integer> code_Col;

    @FXML
    private TextField des_text;

    @FXML
    private TableColumn<NewItemTM, String> description_Col;

    @FXML
    private TableColumn<NewItemTM, String> dosage_col;

    @FXML
    private TextField dosage_txt;

    @FXML
    private TextField itemCode_text;

    @FXML
    private TableView<NewItemTM> newView_table;

    @FXML
    private Button save_btn;

    private final ItemModel  itemModel = new ItemModel();
    private final ObservableList<NewItemTM> itemList = FXCollections.observableArrayList();

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

        NewItemTM newItem = new NewItemTM(itemCode, description, dosage);
        itemList.add(newItem);

        loadItemTable();
    }

    private void loadItemTable() {
        newView_table.setItems(itemList);
    }

    @FXML
    void handleSaveItem(ActionEvent event) {

    }
}
