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

public class OrderController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        References.orderController=this;
        try {
            loadMainUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadMainUI() throws IOException {
        Parent mainUI = Launcher.loadFXML("components/order/orderAdd");
        mainContent.getChildren().setAll(mainUI);
    }

    @FXML
    void backToDashBoard(ActionEvent event) throws IOException {
        References.dashLayoutController.backToDashBoard();
    }

    @FXML
    void handelProductList(ActionEvent event) throws IOException {
        showNewItemDialog("ProductList");
    }

    @FXML
    void handleProductAdd(ActionEvent event) throws IOException {
        loadMainUI();
    }

    private void showNewItemDialog(String root) throws IOException {
        // Load the dialog FXML
        Parent dialogContent = Launcher.loadFXML("components/product/dialog/"+root);

        // Create StackPane overlay on current content
        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.5);");
        overlay.getChildren().add(dialogContent);

        // Add overlay to main content
        mainContent.getChildren().add(overlay);

        // Make overlay fill the entire pane
        AnchorPane.setTopAnchor(overlay, 0.0);
        AnchorPane.setBottomAnchor(overlay, 0.0);
        AnchorPane.setLeftAnchor(overlay, 0.0);
        AnchorPane.setRightAnchor(overlay, 0.0);
    }

    public void closeItemListDialog() {
        // Remove the last child (which should be the overlay)
        if (mainContent.getChildren().size() > 1) {
            mainContent.getChildren().remove(mainContent.getChildren().size() - 1);
        }
    }

    public OrderController getCurrentClass() {
        return this;
    }
}
