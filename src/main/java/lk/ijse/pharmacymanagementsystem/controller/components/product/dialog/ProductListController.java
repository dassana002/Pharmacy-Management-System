package lk.ijse.pharmacymanagementsystem.controller.components.product.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.awt.event.KeyEvent;

public class ProductListController {

    @FXML
    private TableColumn<?, ?> des_col;

    @FXML
    private TextField itemCode_txt;

    @FXML
    private TableView<?> itemTable;

    @FXML
    private TableColumn<?, ?> itemCode_col;

    @FXML
    private StackPane mainContent;

    @FXML
    private VBox popupCard;

    @FXML
    void handleFindItem(KeyEvent event) {

    }
}
