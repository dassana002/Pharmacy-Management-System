package lk.ijse.pharmacymanagementsystem.controller.layout;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacymanagementsystem.controller.DashBoardController;
import lk.ijse.pharmacymanagementsystem.controller.ItemController;

import java.net.URL;
import java.util.ResourceBundle;

public class DashLayoutController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backToDashBoard();
    }

    public void backToDashBoard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/pharmacymanagementsystem/pages/DashBoardPage.fxml"));
            Parent ui = loader.load();

            DashBoardController controller = loader.getController();
            controller.setParentController(this);  // FIX

            mainContent.getChildren().setAll(ui);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load Order Page
    public void loadOrderPage() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/pharmacymanagementsystem/pages/OrderPage.fxml"));
//            Parent ui = loader.load();
//
//            OrderController controller = loader.getController();
//            controller.setParentController(this);   // IMPORTANT
//
//            mainContent.getChildren().setAll(ui);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    // Load Employee Page
    public void loadEmployeePage() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/pharmacymanagementsystem/View/OrderPage.fxml"));
//            Parent ui = loader.load();
//
//            SignUpController controller = loader.getController();
//            controller.setParentController(this);
//
//            mainContent.getChildren().setAll(ui);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    // Load Medicine Page
    public void loadMedicinePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/pharmacymanagementsystem/pages/MedicinePage.fxml"));
            Parent ui = loader.load();

            ItemController controller = loader.getController();
            controller.setParentController(this);

            mainContent.getChildren().setAll(ui);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
