package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FilterView extends VBox {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String CARD_BG = "#1c2433";
    private static final String PRIMARY = "#135bec";

    public FilterView() {
        setSpacing(20);
        setPadding(new Insets(0, 0, 100, 0));
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));
        Label backBtn = new Label("<"); // Back icon
        backBtn.setTextFill(Color.WHITE);
        backBtn.setOnMouseClicked(e -> MainApp.showHome());
        Label title = new Label("Search & Filters");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        header.getChildren().addAll(backBtn, title);

        // Price Range (Simplified)
        VBox priceSection = new VBox(10);
        priceSection.setPadding(new Insets(0, 15, 0, 15));
        Label priceTitle = new Label("Price Range");
        priceTitle.setTextFill(Color.WHITE);
        priceTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        Slider priceSlider = new Slider(0, 100000, 15000);
        priceSlider.setStyle("-fx-control-inner-background: " + PRIMARY + ";");
        priceSection.getChildren().addAll(priceTitle, priceSlider);

        // Property Type
        VBox typeSection = new VBox(10);
        typeSection.setPadding(new Insets(0, 15, 0, 15));
        Label typeTitle = new Label("Property Type");
        typeTitle.setTextFill(Color.WHITE);
        typeTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

        HBox chips = new HBox(10);
        chips.getChildren().addAll(
                createChip("All Types", true),
                createChip("Apartment", false),
                createChip("Bedsitter", false),
                createChip("Studio", false));
        typeSection.getChildren().addAll(typeTitle, chips);

        // Amenities
        VBox amenitiesSection = new VBox(10);
        amenitiesSection.setPadding(new Insets(0, 15, 0, 15));
        Label amTitle = new Label("Amenities");
        amTitle.setTextFill(Color.WHITE);
        amTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

        GridPane amGrid = new GridPane();
        amGrid.setHgap(10);
        amGrid.setVgap(10);
        amGrid.add(createAmenity("WiFi", true), 0, 0);
        amGrid.add(createAmenity("Parking", false), 1, 0);
        amGrid.add(createAmenity("Gym", false), 2, 0);
        amenitiesSection.getChildren().addAll(amTitle, amGrid);

        Button apply = new Button("Apply Filters");
        apply.setMaxWidth(Double.MAX_VALUE);
        apply.setPrefHeight(45);
        apply.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-font-weight: bold;");
        apply.setOnAction(e -> MainApp.showHome());
        VBox.setMargin(apply, new Insets(20, 15, 0, 15));

        getChildren().addAll(header, priceSection, typeSection, amenitiesSection, apply);
    }

    private Button createChip(String text, boolean active) {
        Button btn = new Button(text);
        if (active) {
            btn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: white; -fx-background-radius: 20;");
        } else {
            btn.setStyle("-fx-background-color: " + CARD_BG
                    + "; -fx-text-fill: #9ca3af; -fx-background-radius: 20; -fx-border-color: #374151; -fx-border-radius: 20;");
        }
        return btn;
    }

    private VBox createAmenity(String name, boolean active) {
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(100, 80);
        if (active) {
            box.setStyle("-fx-background-color: rgba(19, 91, 236, 0.1); -fx-border-color: " + PRIMARY
                    + "; -fx-background-radius: 12; -fx-border-radius: 12;");
        } else {
            box.setStyle("-fx-background-color: " + CARD_BG
                    + "; -fx-border-color: #374151; -fx-background-radius: 12; -fx-border-radius: 12;");
        }
        Label lbl = new Label(name);
        lbl.setTextFill(active ? Color.web(PRIMARY) : Color.GRAY);
        box.getChildren().add(lbl);
        return box;
    }
}
