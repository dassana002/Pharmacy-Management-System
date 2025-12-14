package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.Utility.References;
import lk.ijse.pharmacymanagementsystem.controller.layout.DashLayoutController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.itemController = this;
        try {
            loadMainUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadMainUI() throws IOException {
        handleItemsAddToInvetory(null);
    }

    @FXML
    void backToDashBoard(ActionEvent event) throws IOException {
        References.dashLayoutController.backToDashBoard();
    }

    @FXML
    void handleItemListDialog(ActionEvent event) {

    }

    @FXML
    void handleItemsAddToInvetory(ActionEvent event) throws IOException{
        Parent mainUI = Launcher.loadFXML("components/Item/AddView");
        ItemMain_Content.getChildren().setAll(mainUI);
    }

    @FXML
    void handleNewItemAdd(ActionEvent event) throws IOException {
        Parent newItemUI = Launcher.loadFXML("components/Item/NewView");
        ItemMain_Content.getChildren().setAll(newItemUI);
    }

    @FXML
    void handleReturnItems(ActionEvent event) {

    }
}
