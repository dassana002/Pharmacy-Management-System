package lk.ijse.pharmacymanagementsystem.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.pharmacymanagementsystem.Launcher;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashLayoutController implements Initializable {

    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource( "/lk/ijse/pharmacymanagementsystem/View/DashBoard.fxml"));
            Parent uI = loader.load();

            DashBoardController controller = loader.getController();
            controller.setParentController(this);

            mainContent.getChildren().setAll(uI);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void backToDashBoard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/pharmacymanagementsystem/View/DashBoard.fxml"));
            Parent ui = loader.load();

            DashBoardController controller = loader.getController();
            controller.setParentController(this);

            mainContent.getChildren().setAll(ui);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Load Order Page
    public void loadOrderPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/pharmacymanagementsystem/View/OrderLayout.fxml"));
            Parent ui = loader.load();

            OrderLayoutController controller = loader.getController();
            controller.setParentController(this);

            mainContent.getChildren().setAll(ui);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load Employee Page
    public void loadEmployeePage() {
        try {
            Parent uI = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/lk/ijse/pharmacymanagementsystem/View/SignUp.fxml")));
            mainContent.getChildren().setAll(uI);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Load Medicine Page
    public void loadMedicinePage() {
        try {
            Parent uI = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/lk/ijse/pharmacymanagementsystem/View/MedicineLayout.fxml")));
            mainContent.getChildren().setAll(uI);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
