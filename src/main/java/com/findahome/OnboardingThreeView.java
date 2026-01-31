package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.GaussianBlur;

public class OnboardingThreeView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";

    public OnboardingThreeView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Status Bar Simulation
        HBox statusBar = new HBox(15);
        statusBar.setPadding(new Insets(15, 25, 10, 25));
        statusBar.setAlignment(Pos.CENTER_LEFT);

        Label time = new Label("9:41");
        time.setTextFill(Color.WHITE);
        time.setFont(Font.font("System", FontWeight.BOLD, 14));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label signal = new Label("\ud83d\udcf6");
        Label wifi = new Label("\ud83d\udcfc");
        Label battery = new Label("\ud83d\udd0b");
        signal.setTextFill(Color.WHITE);
        wifi.setTextFill(Color.WHITE);
        battery.setTextFill(Color.WHITE);

        HBox icons = new HBox(5, signal, wifi, battery);
        icons.setAlignment(Pos.CENTER_RIGHT);

        statusBar.getChildren().addAll(time, spacer, icons);

        // Illustration Area
        VBox illustration = new VBox();
        illustration.setAlignment(Pos.CENTER);
        VBox.setVgrow(illustration, Priority.ALWAYS);

        StackPane canvas = new StackPane();
        canvas.setPrefSize(300, 300);
        canvas.setMaxSize(300, 300);

        // Background Glow
        Circle glow = new Circle(100, Color.web(PRIMARY, 0.2));
        glow.setEffect(new GaussianBlur(80));

        // Badge Illustration
        VBox badgeBox = new VBox(25);
        badgeBox.setAlignment(Pos.CENTER);

        StackPane shieldCircle = new StackPane();
        shieldCircle.setPrefSize(180, 180);
        shieldCircle.setMaxSize(180, 180);
        shieldCircle.setStyle(
                "-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 90; -fx-border-color: rgba(19, 236, 91, 0.3); -fx-border-width: 2;");

        Label shieldLabel = new Label("\ud83d\udee1\ufe0f"); // Shield icon
        shieldLabel.setTextFill(Color.web(PRIMARY));
        shieldLabel.setStyle(
                "-fx-font-size: 100; -fx-effect: dropshadow(three-pass-box, rgba(19, 236, 91, 0.5), 15, 0, 0, 0);");
        shieldCircle.getChildren().add(shieldLabel);

        HBox networkTag = new HBox(8);
        networkTag.setAlignment(Pos.CENTER);
        networkTag.setPadding(new Insets(8, 20, 8, 20));
        networkTag.setStyle(
                "-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 20; -fx-border-color: rgba(255,255,255,0.2);");
        Label checkIcon = new Label("\u2714");
        checkIcon.setTextFill(Color.web(PRIMARY));
        Label tagText = new Label("VERIFIED AGENT NETWORK");
        tagText.setTextFill(Color.WHITE);
        tagText.setFont(Font.font("System", FontWeight.MEDIUM, 10));
        tagText.setStyle("-fx-letter-spacing: 1px;");
        networkTag.getChildren().addAll(checkIcon, tagText);
        networkTag.setMaxWidth(Region.USE_PREF_SIZE);

        badgeBox.getChildren().addAll(shieldCircle, networkTag);
        canvas.getChildren().addAll(glow, badgeBox);
        illustration.getChildren().add(canvas);

        // Content
        VBox content = new VBox(15);
        content.setPadding(new Insets(0, 30, 40, 30));
        content.setAlignment(Pos.CENTER);

        Label h1 = new Label("Verified & Trusted Agents");
        h1.setTextFill(Color.WHITE);
        h1.setFont(Font.font("System", FontWeight.BOLD, 32));
        h1.setWrapText(true);
        h1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Label sub = new Label("Connect with vetted real estate professionals for a safe renting experience.");
        sub.setTextFill(Color.web("white", 0.7));
        sub.setFont(Font.font(16));
        sub.setWrapText(true);
        sub.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        sub.setMaxWidth(320);

        content.getChildren().addAll(h1, sub);

        // Footer
        VBox footer = new VBox(25);
        footer.setPadding(new Insets(10, 30, 40, 30));
        footer.setAlignment(Pos.CENTER);

        // Page Indicators
        HBox indicators = new HBox(8);
        indicators.setAlignment(Pos.CENTER);
        Region p1 = new Region();
        p1.setPrefSize(6, 6);
        p1.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 3;");
        Region p2 = new Region();
        p2.setPrefSize(6, 6);
        p2.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 3;");
        Region p3 = new Region();
        p3.setPrefSize(24, 6);
        p3.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 3;");
        indicators.getChildren().addAll(p1, p2, p3);

        Button startBtn = new Button("Get Started");
        startBtn.setMaxWidth(Double.MAX_VALUE);
        startBtn.setPrefHeight(60);
        startBtn.setFont(Font.font("System", FontWeight.BOLD, 18));
        startBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-background-radius: 12; -fx-cursor: hand;");
        startBtn.setOnAction(e -> MainApp.showHome());

        // Home Indicator
        Rectangle homeIndicator = new Rectangle(120, 4, Color.web("white", 0.3));
        homeIndicator.setArcWidth(4);
        homeIndicator.setArcHeight(4);

        footer.getChildren().addAll(indicators, startBtn, homeIndicator);

        layout.getChildren().addAll(statusBar, illustration, content, footer);
        getChildren().add(layout);
    }
}
