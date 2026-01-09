module lk.ijse.pharmacymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires jbcrypt;
    requires java.desktop;
    requires javafx.base;
    requires mysql.connector.j;
    requires itextpdf;
    requires org.json;

    exports lk.ijse.pharmacymanagementsystem;
    opens lk.ijse.pharmacymanagementsystem to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller;
    opens lk.ijse.pharmacymanagementsystem.controller to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller.components.product;
    opens lk.ijse.pharmacymanagementsystem.controller.components.product to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller.layout;
    opens lk.ijse.pharmacymanagementsystem.controller.layout to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.dto.order;
    opens lk.ijse.pharmacymanagementsystem.dto.order to javafx.base;

    exports lk.ijse.pharmacymanagementsystem.dto.item;
    opens lk.ijse.pharmacymanagementsystem.dto.item to java.base;

    exports lk.ijse.pharmacymanagementsystem.dto.employee;
    opens lk.ijse.pharmacymanagementsystem.dto.employee to java.base;

    exports lk.ijse.pharmacymanagementsystem.controller.components.employee;
    opens lk.ijse.pharmacymanagementsystem.controller.components.employee to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller.components.order;
    opens lk.ijse.pharmacymanagementsystem.controller.components.order to javafx.fxml;

    exports lk.ijse.pharmacymanagementsystem.controller.components.product.dialog;
    opens lk.ijse.pharmacymanagementsystem.controller.components.product.dialog to javafx.fxml;
}