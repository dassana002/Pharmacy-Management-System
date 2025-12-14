package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.Utility.References;
import lk.ijse.pharmacymanagementsystem.controller.layout.DashLayoutController;

import java.io.IOException;

public class ItemController {

    @FXML
    private AnchorPane ItemMain_Content;

    @FXML
    private Button back_btn;

    @FXML
    private Button itemAdd_btn;

    @FXML
    private Button itemList_btn;

    @FXML
    private Button newItem_btn;

    @FXML
    private Button returnItem_btn;

    @FXML
    void backToDashBoard(ActionEvent event) throws IOException {
        References.dashLayoutController.backToDashBoard();
    }

    @FXML
    void handleItemListDialog(ActionEvent event) {

    }

    @FXML
    void handleItemsAddToInvetory(ActionEvent event) {

    }

    @FXML
    void handleNewItemAdd(ActionEvent event) {

    }

    @FXML
    void handleReturnItems(ActionEvent event) {

    }

}
