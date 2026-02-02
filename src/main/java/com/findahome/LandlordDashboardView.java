package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LandlordDashboardView extends StackPane {

    private static final String BACKGROUND_DARK = "#0f172a"; // Matching screenshot dark theme
    private static final String PRIMARY = "#13ec5b"; // Using green from screenshot
    private static final String TEXT_GRAY = "#94a3b8";
    private static final String BORDER_COLOR = "#334155";

    private VBox contentArea;

    public LandlordDashboardView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox mainLayout = new VBox();

        // Content Area
        contentArea = new VBox();
        contentArea.setAlignment(Pos.CENTER);
        VBox.setVgrow(contentArea, Priority.ALWAYS);

        // Initial View (Properties)
        showPropertiesView();

        // Window Controls
        HBox windowControls = new HBox(15);
        windowControls.setAlignment(Pos.CENTER_RIGHT);
        windowControls.setPadding(new Insets(10, 15, 0, 0));

        Label minBtn = new Label("\u2014");
        minBtn.setTextFill(Color.WHITE);
        minBtn.setStyle("-fx-cursor: hand; -fx-font-size: 14;");
        minBtn.setOnMouseClicked(e -> ((javafx.stage.Stage) getScene().getWindow()).setIconified(true));

        Label maxBtn = new Label("\ud83d\uddd2");
        maxBtn.setTextFill(Color.WHITE);
        maxBtn.setStyle("-fx-cursor: hand; -fx-font-size: 14;");
        maxBtn.setOnMouseClicked(e -> {
            javafx.stage.Stage stage = (javafx.stage.Stage) getScene().getWindow();
            stage.setMaximized(!stage.isMaximized());
        });

        Label closeBtn = new Label("\u2715");
        closeBtn.setTextFill(Color.web("#ff5f57"));
        closeBtn.setStyle("-fx-cursor: hand; -fx-font-size: 14; -fx-font-weight: bold;");
        closeBtn.setOnMouseClicked(e -> ((javafx.stage.Stage) getScene().getWindow()).close());

        windowControls.getChildren().addAll(minBtn, maxBtn, closeBtn);

        mainLayout.getChildren().add(0, windowControls); // Add at top

        // Bottom Navigation (Fixed)
        HBox bottomNav = createBottomNav();

        mainLayout.getChildren().addAll(contentArea, bottomNav);
        getChildren().add(mainLayout);
    }

    private void showPropertiesView() {
        VBox view = new VBox(20);
        view.setPadding(new Insets(20));
        view.setAlignment(Pos.TOP_LEFT);

        Label title = new Label("Properties");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 24));

        Button addBtn = new Button("Add Property");
        addBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 8;");
        addBtn.setOnAction(e -> MainApp.navigateToFullScreen(new AddPropertyView())); // Assuming AddPropertyView
                                                                                      // exists/works

        Label empty = new Label("No properties listed yet.");
        empty.setTextFill(Color.web(TEXT_GRAY));

        view.getChildren().addAll(title, addBtn, empty);

        // Wrap in scrollpane if content is long
        ScrollPane scroll = new ScrollPane(view);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        contentArea.getChildren().setAll(scroll);
    }

    // Placeholder views for other tabs

    private void showProfileView() {
        // Re-using functionality or creating specific landlord profile logic
        // For now, let's create a simple landlord profile view or navigation back to
        // tenant mode
        VBox view = new VBox(20);
        view.setAlignment(Pos.CENTER);

        Label title = new Label("Landlord Profile");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font(20));

        Button switchBtn = new Button("Switch to Tenant View");
        switchBtn.setStyle("-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: bold;");
        switchBtn.setOnAction(e -> MainApp.showHome()); // Navigate back to main app

        view.getChildren().addAll(title, switchBtn);
        contentArea.getChildren().setAll(view);
    }

    private HBox createBottomNav() {
        HBox nav = new HBox(0);
        nav.setAlignment(Pos.CENTER);
        nav.setPrefHeight(70);
        nav.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");

        // Items: Properties, Requests, Revenue, Stats, Profile
        nav.getChildren().addAll(
                createNavItem("Properties", "\ud83c\udfe2", true, e -> showPropertiesView()),
                createNavItem("Requests", "\ud83d\udee0", false,
                        e -> contentArea.getChildren().setAll(new AdminMaintenanceDashboardView())),
                createNavItem("Revenue", "\ud83d\udcb3", false,
                        e -> contentArea.getChildren().setAll(new EarningsAnalyticsView())),
                createNavItem("Stats", "\ud83d\udcc8", false,
                        e -> contentArea.getChildren().setAll(new PropertyPerformanceView())),
                createNavItem("Profile", "\ud83d\udc64", false, e -> showProfileView()));

        return nav;
    }

    private VBox createNavItem(String label, String icon, boolean active,
            javafx.event.EventHandler<javafx.scene.input.MouseEvent> handler) {
        VBox item = new VBox(5);
        item.setAlignment(Pos.CENTER);
        item.setPrefWidth(80);
        item.setCursor(javafx.scene.Cursor.HAND);
        item.setOnMouseClicked(handler); // Add handler for tab switching visual logic if needed

        // Simple visual toggle logic would require state management,
        // for this iteration we'll just bind the action.
        // To make it look "active", we'd need to redraw or update styles.
        // For simplicity:
        item.setOnMouseClicked(e -> {
            handler.handle(e);
            // Reset all styles (quick hack, ideally use ToggleGroup or separate class)
            ((HBox) item.getParent()).getChildren().forEach(n -> {
                VBox v = (VBox) n;
                ((Label) v.getChildren().get(0)).setTextFill(Color.web(TEXT_GRAY));
                ((Label) v.getChildren().get(1)).setTextFill(Color.web(TEXT_GRAY));
            });
            ((Label) item.getChildren().get(0)).setTextFill(Color.web(PRIMARY));
            ((Label) item.getChildren().get(1)).setTextFill(Color.web(PRIMARY));
        });

        Label i = new Label(icon);
        i.setStyle("-fx-font-size: 20;");
        i.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));

        Label l = new Label(label);
        l.setFont(Font.font("System", FontWeight.MEDIUM, 10));
        l.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));

        item.getChildren().addAll(i, l);
        return item;
    }
}
