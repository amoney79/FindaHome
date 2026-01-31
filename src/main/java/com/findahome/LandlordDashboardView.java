package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LandlordDashboardView extends VBox {

    private static final String BACKGROUND_DARK = "#101922";
    private static final String CARD_BG = "#1c2127";
    private static final String PRIMARY = "#137fec";

    public LandlordDashboardView() {
        setSpacing(20);
        setPadding(new Insets(15));
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);

        Circle profilePic = new Circle(20, Color.web(PRIMARY));
        VBox welcomeBox = new VBox(2);
        Label welcome = new Label("Welcome back,");
        welcome.setTextFill(Color.GRAY);
        welcome.setFont(Font.font(12));
        Label name = new Label("James Mwangi");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 16));
        welcomeBox.getChildren().addAll(welcome, name);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label back = new Label("<");
        back.setTextFill(Color.WHITE);
        back.setOnMouseClicked(e -> MainApp.showHome());
        Label bell = new Label("\ud83d\udd14");
        bell.setStyle("-fx-font-size: 20;");
        bell.setOnMouseClicked(e -> MainApp.navigateTo(new NotificationView()));

        header.getChildren().addAll(profilePic, welcomeBox, spacer, back, bell);

        // Stats
        Label portfolioTitle = new Label("PORTFOLIO OVERVIEW");
        portfolioTitle.setTextFill(Color.GRAY);
        portfolioTitle.setFont(Font.font("System", FontWeight.BOLD, 10));

        GridPane statsGrid = new GridPane();
        statsGrid.setHgap(10);
        statsGrid.setVgap(10);
        statsGrid.add(createStatCard("Total Views", "12.4K", "+12%"), 0, 0);
        statsGrid.add(createStatCard("Active Bookings", "8", "+5%"), 1, 0);
        statsGrid.add(createStatCard("Total Listings", "15 Properties", null), 0, 1, 2, 1);

        // Add Property Button
        Button addPropBtn = new Button("+ Add New Property");
        addPropBtn.setMaxWidth(Double.MAX_VALUE);
        addPropBtn.setPrefHeight(50);
        addPropBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-font-weight: bold;");
        addPropBtn.setOnAction(e -> MainApp.navigateTo(new AddPropertyView()));

        // Notifications
        Label notifTitle = new Label("Notifications");
        notifTitle.setTextFill(Color.WHITE);
        notifTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox viewRequest = new VBox(10);
        viewRequest.setPadding(new Insets(15));
        viewRequest.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 12; -fx-border-color: #30363d; -fx-border-radius: 12;");
        Label reqTitle = new Label("Viewing Request: Sunset Apartments");
        reqTitle.setTextFill(Color.WHITE);
        reqTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label reqSub = new Label("Someone wants to see Unit 4B tomorrow at 2:00 PM");
        reqSub.setTextFill(Color.GRAY);
        reqSub.setWrapText(true);
        HBox actions = new HBox(10);
        Button accept = new Button("Accept");
        accept.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: white; -fx-background-radius: 8;");
        accept.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(accept, Priority.ALWAYS);
        Button decline = new Button("Decline");
        decline.setStyle("-fx-background-color: #30363d; -fx-text-fill: white; -fx-background-radius: 8;");
        decline.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(decline, Priority.ALWAYS);
        actions.getChildren().addAll(accept, decline);
        viewRequest.getChildren().addAll(reqTitle, reqSub, actions);

        getChildren().addAll(header, portfolioTitle, statsGrid, addPropBtn, notifTitle, viewRequest);
    }

    private VBox createStatCard(String title, String value, String trend) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 12; -fx-border-color: #30363d; -fx-border-radius: 12;");
        Label tLbl = new Label(title);
        tLbl.setTextFill(Color.GRAY);
        tLbl.setFont(Font.font(12));
        Label vLbl = new Label(value);
        vLbl.setTextFill(Color.WHITE);
        vLbl.setFont(Font.font("System", FontWeight.BOLD, 20));
        card.getChildren().addAll(tLbl, vLbl);
        if (trend != null) {
            Label trendLbl = new Label(trend);
            trendLbl.setTextFill(Color.web("#10b981"));
            trendLbl.setFont(Font.font(10));
            card.getChildren().add(trendLbl);
        }
        return card;
    }
}
