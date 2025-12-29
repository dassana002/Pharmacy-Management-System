package lk.ijse.pharmacymanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import lk.ijse.pharmacymanagementsystem.Launcher;
import lk.ijse.pharmacymanagementsystem.utility.References;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private AnchorPane ItemMain_Content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.productController = this;
        try {
            loadMainUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editItemDialog() throws IOException {
        showNewItemDialog("ItemEdit");
    }

    private void loadMainUI() throws IOException {
        Parent mainUI = Launcher.loadFXML("components/product/ItemAdd");
        ItemMain_Content.getChildren().setAll(mainUI);
    }

    @FXML
    void backToDashBoard(ActionEvent event) throws IOException {
        References.dashLayoutController.backToDashBoard();
    }

    @FXML
    void handleItemListDialog(ActionEvent event) throws IOException {
        showNewItemDialog("ProductList");
    }

    @FXML
    void handleItemsAddToInvetory(ActionEvent event) throws IOException{
        Parent addItemUI = Launcher.loadFXML("components/product/ItemAdd");
        ItemMain_Content.getChildren().setAll(addItemUI);
    }

    @FXML
    void handleNewItemAdd(ActionEvent event) throws IOException {
        showNewItemDialog("ProductAdd");
    }

    @FXML
    void handleReturnItems(ActionEvent event) {

    }

    @FXML
    void handleAddSupplier(ActionEvent event) throws IOException {
        showNewItemDialog("SupplierAdd");
    }

    private void showNewItemDialog(String root) throws IOException {
        // Load the dialog FXML
        Parent dialogContent = Launcher.loadFXML("components/product/dialog/"+root);

        // Create StackPane overlay on current content
        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.5);");
        overlay.getChildren().add(dialogContent);

        // Add overlay to main content
        ItemMain_Content.getChildren().add(overlay);

        // Make overlay fill the entire pane
        AnchorPane.setTopAnchor(overlay, 0.0);
        AnchorPane.setBottomAnchor(overlay, 0.0);
        AnchorPane.setLeftAnchor(overlay, 0.0);
        AnchorPane.setRightAnchor(overlay, 0.0);
    }

    public void closeItemAddDialog() {
        // Remove the last child (which should be the overlay)
        if (ItemMain_Content.getChildren().size() > 1) {
            ItemMain_Content.getChildren().remove(ItemMain_Content.getChildren().size() - 1);
        }
    }

    public void closeSupplierAddDialog() {
        // Remove the last child (which should be the overlay)
        if (ItemMain_Content.getChildren().size() > 1) {
            ItemMain_Content.getChildren().remove(ItemMain_Content.getChildren().size() - 1);
        }
    }

    @FXML
    void handleOutOfStock(ActionEvent event) {

    }

    @FXML
    void handleEditBatch(ActionEvent event) {

    }

    @FXML
    void handleEditItem(ActionEvent event) {

    }

    @FXML
    void handleEditSupplier(ActionEvent event) {

    }

    @FXML
    void handleExpireItem(ActionEvent event) {

    }

    public void payDialog(String invoice) {

    }
}
