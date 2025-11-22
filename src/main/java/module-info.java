module lk.ijse.pharmacymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens lk.ijse.pharmacymanagementsystem to javafx.fxml;
    exports lk.ijse.pharmacymanagementsystem;
}