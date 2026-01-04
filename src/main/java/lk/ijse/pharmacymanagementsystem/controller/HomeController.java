package lk.ijse.pharmacymanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox mainContainer;

    @FXML
    private Label welcomeTitle;

    @FXML
    private Label welcomeSubtitle;

    @FXML
    private Line dividerLine;

    @FXML
    private FlowPane statsContainer;

    @FXML
    private VBox ordersCard;

    @FXML
    private VBox employeesCard;

    @FXML
    private VBox medicinesCard;

    @FXML
    private Label totalOrdersLabel;

    @FXML
    private Label totalEmployeesLabel;

    @FXML
    private Label totalMedicinesLabel;

    @FXML
    private FlowPane quickActionsContainer;

    @FXML
    private VBox recentOrdersCard;

    @FXML
    private VBox lowStockCard;

    @FXML
    private VBox revenueCard;

    @FXML
    private VBox systemStatusCard;

    @FXML
    private Label recentOrdersLabel;

    @FXML
    private Label lowStockLabel;

    @FXML
    private Label revenueLabel;

    @FXML
    private Label systemStatusLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupResponsiveLayout();
        loadDashboardData();
    }

    private void setupResponsiveLayout() {
        // Listen for width changes on root pane
        rootPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            adjustLayoutForWidth(newVal.doubleValue());
        });

        // Listen for height changes
        rootPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            adjustLayoutForHeight(newVal.doubleValue());
        });
    }

    private void adjustLayoutForWidth(double width) {
        // Adjust welcome section
        adjustWelcomeSection(width);

        // Adjust statistics cards
        adjustStatsCards(width);

        // Adjust quick action cards
        adjustQuickActionCards(width);

        // Adjust divider line
        adjustDividerLine(width);
    }

    private void adjustWelcomeSection(double width) {
        if (width < 600) {
            // Mobile view
            welcomeTitle.setStyle("-fx-font-size: 24px;");
            welcomeSubtitle.setStyle("-fx-font-size: 14px;");
        } else if (width < 900) {
            // Tablet view
            welcomeTitle.setStyle("-fx-font-size: 28px;");
            welcomeSubtitle.setStyle("-fx-font-size: 15px;");
        } else {
            // Desktop view
            welcomeTitle.setStyle("-fx-font-size: 32px;");
            welcomeSubtitle.setStyle("-fx-font-size: 16px;");
        }
    }

    private void adjustStatsCards(double width) {
        double cardWidth;
        double padding = 40.0; // Total horizontal padding
        double gap = 20.0; // Gap between cards

        if (width < 600) {
            // Mobile: 1 column, full width
            cardWidth = width - padding;
            statsContainer.setPrefWrapLength(width);
        } else if (width < 900) {
            // Tablet: 2 columns
            cardWidth = (width - padding - gap) / 2;
            statsContainer.setPrefWrapLength(width);
        } else {
            // Desktop: 3 columns
            cardWidth = (width - padding - gap * 2) / 3;
            statsContainer.setPrefWrapLength(width);
        }

        // Apply width to all stat cards
        if (ordersCard != null) ordersCard.setPrefWidth(cardWidth);
        if (employeesCard != null) employeesCard.setPrefWidth(cardWidth);
        if (medicinesCard != null) medicinesCard.setPrefWidth(cardWidth);

        // Adjust font sizes for stat cards
        adjustStatCardFonts(width);
    }

    private void adjustStatCardFonts(double width) {
        String numberSize, labelSize, iconSize;

        if (width < 600) {
            iconSize = "32px";
            numberSize = "28px";
            labelSize = "12px";
        } else if (width < 900) {
            iconSize = "36px";
            numberSize = "32px";
            labelSize = "13px";
        } else {
            iconSize = "40px";
            numberSize = "36px";
            labelSize = "14px";
        }

        // Apply to all stat cards
        applyFontSizeToCard(ordersCard, iconSize, numberSize, labelSize);
        applyFontSizeToCard(employeesCard, iconSize, numberSize, labelSize);
        applyFontSizeToCard(medicinesCard, iconSize, numberSize, labelSize);
    }

    private void applyFontSizeToCard(VBox card, String iconSize, String numberSize, String labelSize) {
        if (card != null && card.getChildren().size() >= 3) {
            // Icon (first label)
            if (card.getChildren().get(0) instanceof Label) {
                ((Label) card.getChildren().get(0)).setStyle("-fx-font-size: " + iconSize + ";");
            }
            // Number (second label)
            if (card.getChildren().get(1) instanceof Label) {
                ((Label) card.getChildren().get(1)).setStyle("-fx-font-size: " + numberSize + "; -fx-font-weight: bold;");
            }
            // Text (third label)
            if (card.getChildren().get(2) instanceof Label) {
                ((Label) card.getChildren().get(2)).setStyle("-fx-font-size: " + labelSize + ";");
            }
        }
    }

    private void adjustQuickActionCards(double width) {
        double cardWidth;
        double padding = 40.0;
        double gap = 15.0;

        if (width < 600) {
            // Mobile: 1 column
            cardWidth = width - padding;
        } else if (width < 1000) {
            // Tablet: 1 column (wider)
            cardWidth = width - padding;
        } else {
            // Desktop: 2 columns
            cardWidth = (width - padding - gap) / 2;
        }

        // Apply width to all info cards
        if (recentOrdersCard != null) recentOrdersCard.setPrefWidth(cardWidth);
        if (lowStockCard != null) lowStockCard.setPrefWidth(cardWidth);
        if (revenueCard != null) revenueCard.setPrefWidth(cardWidth);
        if (systemStatusCard != null) systemStatusCard.setPrefWidth(cardWidth);

        // Adjust font sizes
        adjustInfoCardFonts(width);
    }

    private void adjustInfoCardFonts(double width) {
        String titleSize, iconSize, valueSize;

        if (width < 600) {
            iconSize = "20px";
            titleSize = "14px";
            valueSize = "12px";
        } else if (width < 900) {
            iconSize = "22px";
            titleSize = "15px";
            valueSize = "12.5px";
        } else {
            iconSize = "24px";
            titleSize = "16px";
            valueSize = "13px";
        }

        applyInfoCardFonts(recentOrdersCard, iconSize, titleSize, valueSize);
        applyInfoCardFonts(lowStockCard, iconSize, titleSize, valueSize);
        applyInfoCardFonts(revenueCard, iconSize, titleSize, valueSize);
        applyInfoCardFonts(systemStatusCard, iconSize, titleSize, valueSize);
    }

    private void applyInfoCardFonts(VBox card, String iconSize, String titleSize, String valueSize) {
        if (card != null && card.getChildren().size() >= 2) {
            // HBox with icon and title
            if (card.getChildren().get(0) instanceof javafx.scene.layout.HBox) {
                javafx.scene.layout.HBox hbox = (javafx.scene.layout.HBox) card.getChildren().get(0);
                if (hbox.getChildren().size() >= 2) {
                    // Icon
                    if (hbox.getChildren().get(0) instanceof Label) {
                        ((Label) hbox.getChildren().get(0)).setStyle("-fx-font-size: " + iconSize + ";");
                    }
                    // Title
                    if (hbox.getChildren().get(1) instanceof Label) {
                        ((Label) hbox.getChildren().get(1)).setStyle("-fx-font-size: " + titleSize + "; -fx-font-weight: bold;");
                    }
                }
            }
            // Value label
            if (card.getChildren().get(1) instanceof Label) {
                ((Label) card.getChildren().get(1)).setStyle("-fx-font-size: " + valueSize + ";");
            }
        }
    }

    private void adjustDividerLine(double width) {
        if (dividerLine != null) {
            double lineWidth = Math.min(width - 60, 600);
            dividerLine.setEndX(lineWidth);
        }
    }

    private void adjustLayoutForHeight(double height) {
        // Adjust spacing based on height
        if (height < 600) {
            mainContainer.setSpacing(15.0);
        } else if (height < 800) {
            mainContainer.setSpacing(20.0);
        } else {
            mainContainer.setSpacing(25.0);
        }
    }

    private void loadDashboardData() {
        // TODO: Load actual data from your database/service
        // This is placeholder data - replace with your actual data loading logic

        // Example: Load total orders
        loadTotalOrders();

        // Example: Load total employees
        loadTotalEmployees();

        // Example: Load total medicines
        loadTotalMedicines();

        // Example: Load recent orders
        loadRecentOrders();

        // Example: Load low stock items
        loadLowStockItems();

        // Example: Load today's revenue
        loadTodaysRevenue();

        // Example: Update system status
        updateSystemStatus();
    }

    private void loadTotalOrders() {
        // TODO: Implement database query to get total orders
        // Example:
        // int totalOrders = orderService.getTotalOrders();
        // totalOrdersLabel.setText(String.valueOf(totalOrders));
        totalOrdersLabel.setText("125");
    }

    private void loadTotalEmployees() {
        // TODO: Implement database query to get total employees
        totalEmployeesLabel.setText("18");
    }

    private void loadTotalMedicines() {
        // TODO: Implement database query to get total medicines
        totalMedicinesLabel.setText("342");
    }

    private void loadRecentOrders() {
        // TODO: Implement database query to get orders from last 24 hours
        recentOrdersLabel.setText("Last 24 hours: 23");
    }

    private void loadLowStockItems() {
        // TODO: Implement database query to get low stock items
        lowStockLabel.setText("Items below threshold: 5");
    }

    private void loadTodaysRevenue() {
        // TODO: Implement database query to get today's revenue
        revenueLabel.setText("Rs. 45,230.00");
    }

    private void updateSystemStatus() {
        // TODO: Implement system health check
        systemStatusLabel.setText("All systems operational");
    }

    // Method to refresh dashboard data
    public void refreshDashboard() {
        loadDashboardData();
    }
}