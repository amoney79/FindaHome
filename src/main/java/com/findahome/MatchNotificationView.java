package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MatchNotificationView extends StackPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String PRIMARY = "#13ec5b"; // Green theme
    private static final String RED_ACCENT = "#ef4444";
    private static final String TEXT_GRAY = "#9da6b9";

    public MatchNotificationView() {
        setStyle("-fx-background-color: #f6f6f8;"); // Desktop background

        // Mobile Screen Frame
        StackPane frame = new StackPane();
        frame.setMaxSize(390, 844);
        frame.setMinSize(390, 844);
        frame.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-background-radius: 40; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 15, 0, 0, 5); -fx-border-color: #333; -fx-border-width: 8; -fx-border-radius: 40;");
        frame.setPickOnBounds(false);

        // 1. Background Content (Blurred App Feed Simulation)
        VBox bgContent = new VBox(20);
        bgContent.setPadding(new Insets(60, 20, 20, 20));
        bgContent.setOpacity(0.6);
        bgContent.setEffect(new GaussianBlur(4));

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        Label menu = new Label("\u2630");
        menu.setTextFill(Color.WHITE);
        menu.setFont(Font.font(24));
        Region s1 = new Region();
        HBox.setHgrow(s1, Priority.ALWAYS);
        Label title = new Label("FindaHome");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        Region s2 = new Region();
        HBox.setHgrow(s2, Priority.ALWAYS);
        Label bell = new Label("\ud83d\udd14");
        bell.setTextFill(Color.WHITE);
        bell.setFont(Font.font(20));
        topBar.getChildren().addAll(menu, s1, title, s2, bell);

        HBox search = new HBox(10);
        search.setAlignment(Pos.CENTER_LEFT);
        search.setPadding(new Insets(0, 15, 0, 15));
        search.setPrefHeight(48);
        search.setStyle("-fx-background-color: #282e39; -fx-background-radius: 10;");
        Label mag = new Label("\ud83d\udd0d");
        mag.setTextFill(Color.web(TEXT_GRAY));
        Label placeholder = new Label("Search properties in Kenya");
        placeholder.setTextFill(Color.web(TEXT_GRAY));
        search.getChildren().addAll(mag, placeholder);

        VBox card = new VBox();
        card.setPrefHeight(200);
        card.setStyle("-fx-background-color: #1c1f27; -fx-background-radius: 12;");
        Rectangle imgPlace = new Rectangle(350, 140, Color.web("#333"));
        VBox tBox = new VBox(5);
        tBox.setPadding(new Insets(10));
        tBox.getChildren().add(new Label("Luxury Loft"));
        card.getChildren().addAll(imgPlace, tBox);

        bgContent.getChildren().addAll(topBar, search, card);
        frame.getChildren().add(bgContent);

        // Usage of Status Bar Area
        VBox contentLayer = new VBox();
        contentLayer.setPadding(new Insets(15, 20, 0, 20));

        // 2. Status Bar
        HBox statusBar = new HBox();
        statusBar.setAlignment(Pos.CENTER_LEFT);
        Label time = new Label("9:41");
        time.setTextFill(Color.WHITE);
        time.setFont(Font.font("System", FontWeight.BOLD, 14));
        Region s3 = new Region();
        HBox.setHgrow(s3, Priority.ALWAYS);
        Label icons = new Label("\ud83d\udcf6 \ud83d\udcfb \ud83d\udd0b");
        icons.setTextFill(Color.WHITE);
        statusBar.getChildren().addAll(time, s3, icons);

        contentLayer.getChildren().add(statusBar);

        // 3. Notification Banner
        VBox banner = new VBox(12);
        banner.setPadding(new Insets(16));
        banner.setTranslateY(10); // Spacing from top
        banner.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 5); -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 20;");

        // Header
        HBox bHead = new HBox();
        bHead.setAlignment(Pos.CENTER_LEFT);

        HBox appBadge = new HBox(8);
        appBadge.setAlignment(Pos.CENTER_LEFT);
        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(28, 28);
        iconBox.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 8;");
        Label ic = new Label("\u2302"); // scale/home icon
        ic.setTextFill(Color.WHITE);
        iconBox.getChildren().add(ic);
        Label appName = new Label("FindaHome Match");
        appName.setTextFill(Color.WHITE);
        appName.setFont(Font.font("System", FontWeight.BOLD, 12));
        appBadge.getChildren().addAll(iconBox, appName);

        Region s4 = new Region();
        HBox.setHgrow(s4, Priority.ALWAYS);

        HBox liveTag = new HBox(4);
        liveTag.setAlignment(Pos.CENTER);
        liveTag.setPadding(new Insets(2, 8, 2, 8));
        liveTag.setStyle("-fx-background-color: " + RED_ACCENT + "; -fx-background-radius: 10;");
        Circle dot = new Circle(3, Color.WHITE);
        Label liveTxt = new Label("LIVE");
        liveTxt.setTextFill(Color.WHITE);
        liveTxt.setFont(Font.font("System", FontWeight.BOLD, 10));
        liveTag.getChildren().addAll(dot, liveTxt);

        bHead.getChildren().addAll(appBadge, s4, liveTag);

        // Content
        VBox bContent = new VBox(2);
        Label heading = new Label("New Match Found!");
        heading.setTextFill(Color.WHITE);
        heading.setFont(Font.font("System", FontWeight.BOLD, 18));

        HBox sub = new HBox(8);
        sub.setAlignment(Pos.CENTER_LEFT);
        Label price = new Label("KES 35,000/mo");
        price.setTextFill(Color.web("rgba(255,255,255,0.9)"));
        price.setFont(Font.font("System", FontWeight.MEDIUM, 14));
        Circle sep = new Circle(2, Color.web("rgba(255,255,255,0.5)"));
        Label loc = new Label("Kasarani");
        loc.setTextFill(Color.web("rgba(255,255,255,0.9)"));
        loc.setFont(Font.font(14));
        sub.getChildren().addAll(price, sep, loc);

        bContent.getChildren().addAll(heading, sub);

        // Actions
        HBox bActions = new HBox(12);
        bActions.setPadding(new Insets(8, 0, 0, 0));

        Button quickView = new Button("Quick View");
        quickView.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(quickView, Priority.ALWAYS);
        quickView.setPrefHeight(40);
        quickView.setStyle("-fx-background-color: white; -fx-text-fill: " + PRIMARY
                + "; -fx-font-weight: bold; -fx-font-size: 13; -fx-background-radius: 10; -fx-cursor: hand;");
        // Reuse PropertyDetailView but with Kasarani data
        quickView.setOnAction(e -> {
            Property p = new Property("Kasarani Apartment", "Kasarani, Nairobi", "KES 35,000/mo", "", true,
                    "New Match");
            MainApp.navigateTo(new PropertyDetailView(p));
        });

        Button dismiss = new Button("Dismiss");
        dismiss.setPrefHeight(40);
        dismiss.setStyle(
                "-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13; -fx-background-radius: 10; -fx-cursor: hand;");
        dismiss.setOnAction(e -> MainApp.navigateTo(new ManageAlertsView()));

        bActions.getChildren().addAll(quickView, dismiss);

        // Handle
        StackPane handleBox = new StackPane();
        handleBox.setPadding(new Insets(4, 0, 0, 0));
        Rectangle handle = new Rectangle(40, 4, Color.web("rgba(255,255,255,0.3)"));
        handle.setArcWidth(4);
        handle.setArcHeight(4);
        handleBox.getChildren().add(handle);

        banner.getChildren().addAll(bHead, bContent, bActions, handleBox);
        contentLayer.getChildren().add(banner);

        frame.getChildren().add(contentLayer);

        // Home Indicator
        Rectangle homeInd = new Rectangle(134, 5, Color.WHITE);
        homeInd.setArcWidth(5);
        homeInd.setArcHeight(5);
        homeInd.setOpacity(0.3);
        StackPane.setAlignment(homeInd, Pos.BOTTOM_CENTER);
        StackPane.setMargin(homeInd, new Insets(0, 0, 10, 0));
        frame.getChildren().add(homeInd);

        getChildren().add(frame);
    }
}
