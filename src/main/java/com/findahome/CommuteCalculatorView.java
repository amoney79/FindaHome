package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CommuteCalculatorView extends VBox {

    private static final String BACKGROUND_DARK = "#221610";
    private static final String PRIMARY = "#f46a25";
    private static final String CARD_BG = "#2d1e17";
    private static final String TEXT_GRAY = "#8a6e60";
    private static final String BORDER_COLOR = "#e6dfdb";

    public CommuteCalculatorView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // --- Header ---
        HBox header = new HBox(0);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "cc; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

        Button backBtn = new Button("\u276E"); // arrow_back_ios
        backBtn.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18; -fx-cursor: hand;");
        backBtn.setOnAction(e -> MainApp.showHome());

        Label title = new Label("Commute Analysis");
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setTextFill(Color.WHITE);
        title.setAlignment(Pos.CENTER);
        HBox.setHgrow(title, Priority.ALWAYS);
        title.setMaxWidth(Double.MAX_VALUE);

        Region spacer = new Region();
        spacer.setPrefWidth(40);

        header.getChildren().addAll(backBtn, title, spacer);

        // --- Main Content ---
        VBox content = new VBox(16);
        content.setPadding(new Insets(80, 16, 100, 16));

        // 1. Property & Destination Card
        VBox locationCard = new VBox(16);
        locationCard.setPadding(new Insets(16));
        locationCard.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 12;");

        // Property Origin
        HBox originRow = new HBox(12);
        originRow.setAlignment(Pos.TOP_LEFT);

        VBox iconCol1 = new VBox(4);
        iconCol1.setAlignment(Pos.TOP_CENTER);
        Label homeIcon = new Label("\u2302"); // home
        homeIcon.setTextFill(Color.web(PRIMARY));
        homeIcon.setFont(Font.font(20));
        Region connector = new Region();
        connector.setPrefHeight(32);
        connector.setPrefWidth(2);
        connector.setStyle("-fx-background-color: rgba(255,255,255,0.1);");
        iconCol1.getChildren().addAll(homeIcon, connector);

        VBox originDetails = new VBox(4);
        HBox.setHgrow(originDetails, Priority.ALWAYS);
        Label originLabel = new Label("PROPERTY LOCATION");
        originLabel.setTextFill(Color.web(TEXT_GRAY));
        originLabel.setFont(Font.font("System", FontWeight.BOLD, 10));
        Label originAddress = new Label("123 Kilimani Road, Nairobi");
        originAddress.setTextFill(Color.WHITE);
        originAddress.setFont(Font.font("System", FontWeight.MEDIUM, 14));
        originDetails.getChildren().addAll(originLabel, originAddress);

        originRow.getChildren().addAll(iconCol1, originDetails);

        // Destination Input
        HBox destRow = new HBox(12);
        destRow.setAlignment(Pos.TOP_LEFT);

        Label destIcon = new Label("\uD83D\uDCCD"); // location_on
        destIcon.setTextFill(Color.web(TEXT_GRAY));
        destIcon.setFont(Font.font(20));

        VBox destDetails = new VBox(4);
        HBox.setHgrow(destDetails, Priority.ALWAYS);
        Label destLabel = new Label("DESTINATION");
        destLabel.setTextFill(Color.web(TEXT_GRAY));
        destLabel.setFont(Font.font("System", FontWeight.BOLD, 10));

        TextField destInput = new TextField("Two Rivers Mall, Limuru Road");
        destInput.setPromptText("Enter Work or School Address");
        destInput.setStyle(
                "-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: white; -fx-prompt-text-fill: rgba(138,110,96,0.6); -fx-background-radius: 8; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 8; -fx-padding: 10;");
        destInput.setPrefHeight(48);

        destDetails.getChildren().addAll(destLabel, destInput);
        destRow.getChildren().addAll(destIcon, destDetails);

        locationCard.getChildren().addAll(originRow, destRow);

        // 2. Interactive Map Section
        StackPane mapContainer = new StackPane();
        mapContainer.setPrefHeight(256);
        mapContainer.setStyle(
                "-fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 12;");

        ImageView mapView = new ImageView();
        try {
            Image mapImg = new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuDZWdy-HAeLnhUbXaOMGrMhyMzoqdStaYCktLYJepZCjUdaab4rej39c_5LisHVJlYt_u-Js_kGDjSsMm255UqP-lD1BAU17YUs2MZyTskFKGQ7mHJreOFUFATAJi1i1uLA_EUTYQo8PPemFZ67IhQEjxd-LVZBe5MXnDPfB1GvjzG0yffgGiUSfT11GIY27vv9W11DCa8Lp4gtqIfixEXi1k143yxwOyTnOb4HAw8WXrUFLpZTThZYXSaJqZVJsuDNn0V183hJTEM",
                    400, 256, false, true);
            mapView.setImage(mapImg);
        } catch (Exception e) {
        }
        mapView.setFitWidth(400);
        mapView.setFitHeight(256);
        mapView.setPreserveRatio(false);

        // Map markers overlay
        StackPane markersOverlay = new StackPane();

        VBox homeMarker = new VBox(2);
        homeMarker.setAlignment(Pos.CENTER);
        Label homeTag = new Label("HOME");
        homeTag.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-text-fill: white; -fx-font-size: 10; -fx-font-weight: bold; -fx-padding: 2 6; -fx-background-radius: 4;");
        Label homePin = new Label("\uD83D\uDCCD");
        homePin.setTextFill(Color.web(PRIMARY));
        homePin.setFont(Font.font(24));
        homeMarker.getChildren().addAll(homeTag, homePin);
        StackPane.setAlignment(homeMarker, Pos.TOP_LEFT);
        StackPane.setMargin(homeMarker, new Insets(64, 0, 0, 100));

        VBox workMarker = new VBox(2);
        workMarker.setAlignment(Pos.CENTER);
        Label workTag = new Label("WORK");
        workTag.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-text-fill: white; -fx-font-size: 10; -fx-font-weight: bold; -fx-padding: 2 6; -fx-background-radius: 4;");
        Label workPin = new Label("\uD83D\uDCCD");
        workPin.setTextFill(Color.WHITE);
        workPin.setFont(Font.font(24));
        workMarker.getChildren().addAll(workTag, workPin);
        StackPane.setAlignment(workMarker, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(workMarker, new Insets(0, 100, 85, 0));

        markersOverlay.getChildren().addAll(homeMarker, workMarker);

        // Location button
        Button locationBtn = new Button("\u2316"); // my_location
        locationBtn.setStyle("-fx-background-color: rgba(255,255,255,0.9); -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-background-radius: 25; -fx-font-size: 18; -fx-padding: 8;");
        StackPane.setAlignment(locationBtn, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(locationBtn, new Insets(0, 16, 16, 0));

        mapContainer.getChildren().addAll(mapView, markersOverlay, locationBtn);

        // 3. Traffic Toggle
        VBox trafficSection = new VBox(12);

        HBox trafficHeader = new HBox();
        trafficHeader.setAlignment(Pos.CENTER_LEFT);
        Label trafficTitle = new Label("Traffic Analysis");
        trafficTitle.setTextFill(Color.WHITE);
        trafficTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
        Region tSpacer = new Region();
        HBox.setHgrow(tSpacer, Priority.ALWAYS);
        Label trafficInfo = new Label("\u24D8 Updates live for Thika Road");
        trafficInfo.setTextFill(Color.web(TEXT_GRAY));
        trafficInfo.setFont(Font.font(10));
        trafficInfo.setStyle("-fx-font-style: italic;");
        trafficHeader.getChildren().addAll(trafficTitle, tSpacer, trafficInfo);

        HBox trafficToggle = new HBox(4);
        trafficToggle.setPadding(new Insets(4));
        trafficToggle.setAlignment(Pos.CENTER);
        trafficToggle.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12;");

        Button offPeakBtn = createToggleButton("Off-Peak", false);
        Button peakBtn = createToggleButton("Peak Traffic", true);
        HBox.setHgrow(offPeakBtn, Priority.ALWAYS);
        HBox.setHgrow(peakBtn, Priority.ALWAYS);

        trafficToggle.getChildren().addAll(offPeakBtn, peakBtn);
        trafficSection.getChildren().addAll(trafficHeader, trafficToggle);

        // 4. Transport Breakdown
        VBox transportList = new VBox(12);

        transportList.getChildren().add(createTransportCard(
                "\uD83D\uDE97", "Driving", "Fastest route via bypass",
                "25 mins", "+8 min traffic", PRIMARY, true));

        transportList.getChildren().add(createTransportCard(
                "\uD83D\uDE8C", "Matatu", "Stage: Kilimani Mall",
                "45 mins", "Incl. walking", TEXT_GRAY, false));

        transportList.getChildren().add(createTransportCard(
                "\uD83D\uDEB6", "Walking", "Via safe pedestrian path",
                "1.5 hrs", "7.2 km", TEXT_GRAY, false));

        content.getChildren().addAll(locationCard, mapContainer, trafficSection, transportList);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // --- Bottom Action Button ---
        HBox bottomBar = new HBox();
        bottomBar.setPadding(new Insets(16));
        bottomBar.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");

        Button saveBtn = new Button("\uD83D\uDD16  Save Route to Profile");
        saveBtn.setMaxWidth(Double.MAX_VALUE);
        saveBtn.setPrefHeight(56);
        saveBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-font-size: 14;");
        HBox.setHgrow(saveBtn, Priority.ALWAYS);

        bottomBar.getChildren().add(saveBtn);

        // Main Layout
        StackPane root = new StackPane();
        root.getChildren().addAll(scrollPane, bottomBar);
        StackPane.setAlignment(bottomBar, Pos.BOTTOM_CENTER);

        getChildren().addAll(header, root);
    }

    private Button createToggleButton(String text, boolean active) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        if (active) {
            btn.setStyle("-fx-background-color: " + PRIMARY
                    + "; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-weight: bold; -fx-font-size: 13;");
        } else {
            btn.setStyle(
                    "-fx-background-color: transparent; -fx-text-fill: rgba(255,255,255,0.6); -fx-background-radius: 8; -fx-font-weight: medium; -fx-font-size: 13;");
        }
        btn.setPrefHeight(40);
        return btn;
    }

    private HBox createTransportCard(String icon, String mode, String detail, String time, String subtext,
            String iconColor, boolean highlight) {
        HBox card = new HBox(16);
        card.setPadding(new Insets(16));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 12;");

        // Icon
        StackPane iconContainer = new StackPane();
        iconContainer.setPrefSize(40, 40);
        iconContainer.setStyle("-fx-background-color: " + (highlight ? "rgba(244,106,37,0.1)" : "rgba(138,110,96,0.1)")
                + "; -fx-background-radius: 20;");
        Label iconLabel = new Label(icon);
        iconLabel.setTextFill(Color.web(highlight ? PRIMARY : iconColor));
        iconLabel.setFont(Font.font(20));
        iconContainer.getChildren().add(iconLabel);

        // Details
        VBox details = new VBox(2);
        HBox.setHgrow(details, Priority.ALWAYS);
        Label modeLabel = new Label(mode);
        modeLabel.setTextFill(Color.WHITE);
        modeLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label detailLabel = new Label(detail);
        detailLabel.setTextFill(Color.web(TEXT_GRAY));
        detailLabel.setFont(Font.font(12));
        details.getChildren().addAll(modeLabel, detailLabel);

        // Time
        VBox timeBox = new VBox(0);
        timeBox.setAlignment(Pos.CENTER_RIGHT);
        Label timeLabel = new Label(time);
        timeLabel.setTextFill(highlight ? Color.web(PRIMARY) : Color.WHITE);
        timeLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label subtextLabel = new Label(subtext.toUpperCase());
        subtextLabel.setTextFill(Color.web(TEXT_GRAY));
        subtextLabel.setFont(Font.font(10));
        timeBox.getChildren().addAll(timeLabel, subtextLabel);

        card.getChildren().addAll(iconContainer, details, timeBox);
        return card;
    }
}
