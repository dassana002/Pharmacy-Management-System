module lk.ijse.pharmacymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires jbcrypt;

    exports lk.ijse.pharmacymanagementsystem;
    exports lk.ijse.pharmacymanagementsystem.Controller;

    opens lk.ijse.pharmacymanagementsystem to javafx.fxml;
    opens lk.ijse.pharmacymanagementsystem.Controller to javafx.fxml;
}