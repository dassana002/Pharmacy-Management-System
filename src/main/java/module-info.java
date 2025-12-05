module lk.ijse.pharmacymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    opens lk.ijse.pharmacymanagementsystem.Controller to javafx.fxml;
    opens lk.ijse.pharmacymanagementsystem to javafx.fxml;
    exports lk.ijse.pharmacymanagementsystem;
    opens lk.ijse.pharmacymanagementsystem.Controller.Layout to javafx.fxml;
    opens lk.ijse.pharmacymanagementsystem.Controller.Page to javafx.fxml;
}