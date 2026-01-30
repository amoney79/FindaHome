package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.scene.shape.Circle;

public class AdminDashboardView extends VBox {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String CARD_BG = "#1a2e20";
    private static final String PRIMARY = "#13ec5b";

    public AdminDashboardView() {
        setSpacing(20);
        setPadding(new Insets(15));
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        Label avatar = new Label("\ud83d\udc64");
        avatar.setStyle("-fx-background-color: #3b5443; -fx-padding: 10; -fx-background-radius: 20;");
        avatar.setOnMouseClicked(e -> MainApp.showHome());
        VBox titleBox = new VBox(2);
        Label welcome = new Label("Welcome back,");
        welcome.setTextFill(Color.web("#9db9a6"));
        Label title = new Label("Admin Dashboard");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleBox.getChildren().addAll(welcome, title);
        header.getChildren().addAll(avatar, titleBox);

        // Stats
        GridPane stats = new GridPane();
        stats.setHgap(10);
        stats.setVgap(10);
        stats.add(createStat("Pending", "24", "#f59e0b"), 0, 0);
        stats.add(createStat("Active Agents", "1,240", PRIMARY), 1, 0);
        stats.add(createStat("Flagged", "12", "#ef4444"), 0, 1);
        stats.add(createStat("Revenue", "$14.2k", PRIMARY), 1, 1);

        // Request List
        Label reqTitle = new Label("Verification Requests");
        reqTitle.setTextFill(Color.WHITE);
        reqTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

        VBox list = new VBox(10);
        list.getChildren().addAll(
                createRequestRow("Sarah Jenkins", "Oct 24", "Pending", "#f59e0b"),
                createRequestRow("Michael Chen", "Oct 23", "In Review", "#3b82f6"),
                createRequestRow("Elena Rodriguez", "Oct 22", "Pending", "#f59e0b"));

        getChildren().addAll(header, stats, reqTitle, list);
    }

    private VBox createStat(String label, String value, String color) {
        VBox box = new VBox(5);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 12;");
        Label l = new Label(label.toUpperCase());
        l.setTextFill(Color.web("#9db9a6"));
        l.setFont(Font.font(10));
        Label v = new Label(value);
        v.setTextFill(Color.WHITE);
        v.setFont(Font.font("System", FontWeight.BOLD, 22));
        box.getChildren().addAll(l, v);
        return box;
    }

    private HBox createRequestRow(String name, String date, String status, String statusColor) {
        HBox row = new HBox(15);
        row.setPadding(new Insets(12, 15, 12, 15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12;");

        Circle avatar = new Circle(18, Color.GRAY);
        VBox info = new VBox(2);
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label d = new Label(date + " • Agent ID: #4421");
        d.setTextFill(Color.web("#9db9a6"));
        d.setFont(Font.font(10));
        info.getChildren().addAll(n, d);

        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);

        Label st = new Label(status.toUpperCase());
        st.setStyle("-fx-text-fill: " + statusColor + "; -fx-font-size: 10; -fx-font-weight: bold;");

        row.setOnMouseClicked(e -> MainApp.navigateTo(new AdminReviewDetailView()));
        row.getChildren().addAll(avatar, info, s, st);
        return row;
    }
}
