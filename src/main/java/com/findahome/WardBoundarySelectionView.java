package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WardBoundarySelectionView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String CARD_BG = "#1c271f";
    private static final String TEXT_GRAY = "#9db9a6";

    public WardBoundarySelectionView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.showHome());

        Label title = new Label("Ward Boundaries");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        HBox.setHgrow(title, Priority.ALWAYS);

        header.getChildren().addAll(backBtn, title, new Region());

        // Map Section
        StackPane mapStack = new StackPane();
        mapStack.setPrefHeight(400);

        ImageView mapImg = new ImageView();
        try {
            mapImg.setImage(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuCyasgGRNx5tRGMuc_AEZIJ22unAv5veHNgrniiBfnvdptdDscv2G8VMkuL3A-Umg33tapqmv68vQ2cfNgCiS308MNxsf1FkGtVBnRVqs9zNakB1pP5KsNw7AAmsDPLxFcAQjFR7JmMwYbz7znRGFqjEoV_ngOEHnxh1VyaX9k85Sv6y_YiIj2Lne8j7LaVUrKEg5fLuE4tdnZH1sBhCWyGGtPgZn9DMTYhCUwFuuGI-AnH7mfgOg0mx545jxs3_heSaYnMW5NtSFc",
                    400, 400, false, true));
        } catch (Exception e) {
        }
        mapImg.setFitWidth(400);
        mapImg.setFitHeight(400);
        mapImg.setOpacity(0.5);

        // Simulated Boundary Polygons
        Polygon p1 = new Polygon(100, 100, 200, 80, 250, 150, 150, 200);
        p1.setFill(Color.web(PRIMARY, 0.2));
        p1.setStroke(Color.web(PRIMARY));
        p1.setStrokeWidth(2);

        Polygon p2 = new Polygon(200, 80, 300, 50, 350, 120, 250, 150);
        p2.setFill(Color.web("#3b82f6", 0.1));
        p2.setStroke(Color.web("#3b82f6", 0.5));

        mapStack.getChildren().addAll(mapImg, p1, p2);

        // Info Panel (Floating)
        VBox infoPanel = new VBox(10);
        infoPanel.setPadding(new Insets(15));
        infoPanel.setStyle(
                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: " + PRIMARY + ";");
        infoPanel.setMaxHeight(Region.USE_PREF_SIZE);
        infoPanel.setMaxWidth(300);
        StackPane.setAlignment(infoPanel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(infoPanel, new Insets(0, 0, 20, 0));

        Label wardName = new Label("Westlands Ward");
        wardName.setTextFill(Color.WHITE);
        wardName.setFont(Font.font("System", FontWeight.BOLD, 16));

        HBox stats = new HBox(15);
        stats.getChildren().addAll(
                createStat("Density", "High", "#ef4444"),
                createStat("Avg Rent", "85k", PRIMARY));

        infoPanel.getChildren().addAll(wardName, stats);

        layout.getChildren().addAll(header, mapStack);
        getChildren().add(layout);
        getChildren().add(infoPanel);
    }

    private VBox createStat(String label, String val, String color) {
        VBox v = new VBox(2);
        Label l = new Label(label);
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font(10));
        Label valL = new Label(val);
        valL.setTextFill(Color.web(color));
        valL.setFont(Font.font("System", FontWeight.BOLD, 14));
        v.getChildren().addAll(l, valL);
        return v;
    }
}
