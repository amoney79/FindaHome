package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class OnboardingTwoView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";

    public OnboardingTwoView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Top Skip Button
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPadding(new Insets(40, 30, 0, 30));
        Label skipBtn = new Label("Skip");
        skipBtn.setTextFill(Color.web(PRIMARY));
        skipBtn.setFont(Font.font("System", FontWeight.BOLD, 16));
        skipBtn.setCursor(javafx.scene.Cursor.HAND);
        skipBtn.setOnMouseClicked(e -> MainApp.showHome());
        topBar.getChildren().add(skipBtn);

        // Content Area
        VBox content = new VBox(0);
        content.setAlignment(Pos.CENTER);
        VBox.setVgrow(content, Priority.ALWAYS);

        // Illustration Section
        StackPane illustrationArea = new StackPane();
        illustrationArea.setPrefHeight(320);
        illustrationArea.setMaxWidth(370);
        illustrationArea.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 24;");

        // Calendar Illustration
        StackPane calendarContainer = new StackPane();
        calendarContainer.setMaxSize(200, 200);

        // Calendar Body
        VBox calendar = new VBox(0);
        calendar.setMaxSize(128, 144);
        calendar.setStyle(
                "-fx-background-color: #1e293b; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 20, 0, 0, 10);");

        // Calendar Header
        HBox calHeader = new HBox(6);
        calHeader.setAlignment(Pos.CENTER);
        calHeader.setPrefHeight(32);
        calHeader.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 12 12 0 0;");
        calHeader.getChildren().addAll(new Circle(3, Color.web(BACKGROUND_DARK)),
                new Circle(3, Color.web(BACKGROUND_DARK)));

        // Calendar Grid Mock
        GridPane calGrid = new GridPane();
        calGrid.setPadding(new Insets(12));
        calGrid.setHgap(8);
        calGrid.setVgap(8);
        calGrid.setOpacity(0.2);
        for (int i = 0; i < 8; i++) {
            Region r = new Region();
            r.setPrefSize(20, 8);
            r.setStyle("-fx-background-color: white; -fx-background-radius: 4;");
            calGrid.add(r, i % 4, i / 4);
        }

        calendar.getChildren().addAll(calHeader, calGrid);

        // Checkmark Circle
        StackPane checkCircle = new StackPane();
        checkCircle.setPrefSize(80, 80);
        checkCircle.setMaxSize(80, 80);
        checkCircle.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 40; -fx-border-color: "
                + BACKGROUND_DARK
                + "; -fx-border-width: 6; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 15, 0, 0, 8);");
        Label checkIcon = new Label("\u2713");
        checkIcon.setTextFill(Color.web(BACKGROUND_DARK));
        checkIcon.setFont(Font.font("System", FontWeight.BOLD, 40));
        checkCircle.getChildren().add(checkIcon);
        StackPane.setAlignment(checkCircle, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(checkCircle, new Insets(0, 50, 20, 0));

        calendarContainer.getChildren().addAll(calendar, checkCircle);
        illustrationArea.getChildren().add(calendarContainer);

        // Text Section
        VBox textSect = new VBox(15);
        textSect.setAlignment(Pos.CENTER);
        textSect.setPadding(new Insets(30, 40, 0, 40));

        Label headline = new Label("Easy Bookings & Payments");
        headline.setTextFill(Color.WHITE);
        headline.setFont(Font.font("System", FontWeight.BOLD, 32));
        headline.setWrapText(true);
        headline.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Label body = new Label("Schedule viewings and pay your security deposit securely through the app.");
        body.setTextFill(Color.web("#cbd5e1"));
        body.setFont(Font.font(16));
        body.setWrapText(true);
        body.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        textSect.getChildren().addAll(headline, body);

        content.getChildren().addAll(illustrationArea, textSect);

        // Bottom Controls
        VBox footer = new VBox(30);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(0, 30, 60, 30));

        // Page Indicators
        HBox indicators = new HBox(10);
        indicators.setAlignment(Pos.CENTER);
        Region p1 = new Region();
        p1.setPrefSize(8, 8);
        p1.setStyle("-fx-background-color: #334155; -fx-background-radius: 4;");
        Region p2 = new Region();
        p2.setPrefSize(24, 8);
        p2.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 4;");
        Region p3 = new Region();
        p3.setPrefSize(8, 8);
        p3.setStyle("-fx-background-color: #334155; -fx-background-radius: 4;");
        indicators.getChildren().addAll(p1, p2, p3);

        Button nextBtn = new Button("Next");
        nextBtn.setGraphic(new Label("\u2192"));
        nextBtn.setContentDisplay(ContentDisplay.RIGHT);
        nextBtn.setMaxWidth(Double.MAX_VALUE);
        nextBtn.setPrefHeight(60);
        nextBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-font-size: 18; -fx-background-radius: 12;");
        nextBtn.setOnAction(e -> MainApp.showHome());

        footer.getChildren().addAll(indicators, nextBtn);

        layout.getChildren().addAll(topBar, content, footer);
        getChildren().add(layout);
    }
}
