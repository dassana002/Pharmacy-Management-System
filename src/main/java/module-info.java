module lk.ijse.pharmacymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires jbcrypt;
    requires java.desktop;

    exports lk.ijse.pharmacymanagementsystem;
    opens lk.ijse.pharmacymanagementsystem to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller;
    opens lk.ijse.pharmacymanagementsystem.controller to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller.components.item;
    opens lk.ijse.pharmacymanagementsystem.controller.components.item to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller.layout;
    opens lk.ijse.pharmacymanagementsystem.controller.layout to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.dto;
    opens lk.ijse.pharmacymanagementsystem.dto to java.base;
    exports lk.ijse.pharmacymanagementsystem.dto.item;
    opens lk.ijse.pharmacymanagementsystem.dto.item to java.base;
}