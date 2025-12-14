module lk.ijse.pharmacymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires jbcrypt;

    exports lk.ijse.pharmacymanagementsystem;
    opens lk.ijse.pharmacymanagementsystem to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller;
    opens lk.ijse.pharmacymanagementsystem.controller to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller.components.item;
    opens lk.ijse.pharmacymanagementsystem.controller.components.item to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller.layout;
    opens lk.ijse.pharmacymanagementsystem.controller.layout to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.Dto;
    opens lk.ijse.pharmacymanagementsystem.Dto to java.base;
}