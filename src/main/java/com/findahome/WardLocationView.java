package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WardLocationView extends StackPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String PRIMARY = "#13ec5b"; // Using Green theme
    private static final String TEXT_GRAY = "#9da6b9";
    private static final String BORDER_COLOR = "#2a3544";
    private static final String CARD_BG = "#1c2433";

    public WardLocationView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039"); // arrow_back_ios
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 24; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new LocationFilterView()));

        Label title = new Label("Select Location");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);
        HBox.setMargin(title, new Insets(0, 24, 0, 0)); // Offset for back button balance

        header.getChildren().addAll(backBtn, title);

        // Search Bar
        HBox searchContainer = new HBox();
        searchContainer.setPadding(new Insets(15, 20, 15, 20));

        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 8;");
        searchBox.setPadding(new Insets(0, 15, 0, 15));
        HBox.setHgrow(searchBox, Priority.ALWAYS);

        Label searchIcon = new Label("\ud83d\udd0d");
        searchIcon.setTextFill(Color.web(TEXT_GRAY));

        TextField searchInput = new TextField();
        searchInput.setPromptText("Search for area, sub-county or ward");
        searchInput.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: " + TEXT_GRAY + ";");
        searchInput.setPrefHeight(48);
        HBox.setHgrow(searchInput, Priority.ALWAYS);

        searchBox.getChildren().addAll(searchIcon, searchInput);
        searchContainer.getChildren().add(searchBox);

        // Scroll Content
        VBox scrollContent = new VBox(0);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        scrollContent.setPadding(new Insets(0, 0, 150, 0));

        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Section Selected
        HBox sectionHeader = new HBox(10);
        sectionHeader.setAlignment(Pos.CENTER_LEFT);
        sectionHeader.setPadding(new Insets(20, 20, 10, 20));

        Label sectionTitle = new Label("Nairobi City County");
        sectionTitle.setTextFill(Color.WHITE);
        sectionTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        Label selectedBadge = new Label("County Selected");
        selectedBadge.setTextFill(Color.web(PRIMARY));
        selectedBadge.setFont(Font.font("System", FontWeight.MEDIUM, 10));
        selectedBadge
                .setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-padding: 4 8; -fx-background-radius: 4;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        sectionHeader.getChildren().addAll(sectionTitle, spacer, selectedBadge);

        // Accordion List
        VBox accordionList = new VBox(2);
        accordionList.setPadding(new Insets(0, 10, 0, 10));

        // Westlands (Expanded)
        accordionList.getChildren().add(createExpandedWestlands());

        // Collapsed Items
        accordionList.getChildren().addAll(
                createCollapsedItem("Kasarani", "Clay City, Mwiki, Kasarani, Njiru, Ruai"),
                createCollapsedItem("Langata", "Karen, Nairobi West, Mugumo-ini, South C"),
                createCollapsedItem("Dagoretti North", "Kilimani, Kawangware, Gatina, Kileleshwa"),
                createCollapsedItem("Starehe", "Nairobi Central, Ngara, Pangani, Ziwani"));

        // Map Preview
        StackPane mapPreview = new StackPane();
        mapPreview.setPadding(new Insets(24, 20, 24, 20));

        StackPane mapCard = new StackPane();
        mapCard.setPrefHeight(160);
        mapCard.setStyle(
                "-fx-background-color: #282e39; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 4);");

        Label browserLabel = new Label("Browsing Nairobi County");
        browserLabel.setGraphic(new Label("\ud83d\udccd")); // location icon
        browserLabel.setContentDisplay(ContentDisplay.LEFT);
        browserLabel.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "cc; -fx-text-fill: white; -fx-padding: 8 12; -fx-background-radius: 20; -fx-border-color: rgba(255,255,255,0.1);");

        mapCard.getChildren().add(browserLabel);
        mapPreview.getChildren().add(mapCard);

        scrollContent.getChildren().addAll(sectionHeader, accordionList, mapPreview);

        // Footer
        VBox footer = new VBox(15);
        footer.setPadding(new Insets(20));
        footer.setStyle("-fx-background-color: rgba(16, 22, 34, 0.9); -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");

        HBox selectionRow = new HBox();
        selectionRow.setAlignment(Pos.CENTER_LEFT);
        Label selCount = new Label("2 wards selected");
        selCount.setTextFill(Color.web(TEXT_GRAY));
        selCount.setFont(Font.font("System", FontWeight.MEDIUM, 14));

        Region fSpacer = new Region();
        HBox.setHgrow(fSpacer, Priority.ALWAYS);

        Label clearAll = new Label("Clear all");
        clearAll.setTextFill(Color.web(PRIMARY));
        clearAll.setFont(Font.font("System", FontWeight.BOLD, 14));
        clearAll.setCursor(javafx.scene.Cursor.HAND);

        selectionRow.getChildren().addAll(selCount, fSpacer, clearAll);

        Button confirmBtn = new Button("Confirm Selection");
        confirmBtn.setGraphic(new Label("\u2714"));
        confirmBtn.setContentDisplay(ContentDisplay.RIGHT);
        confirmBtn.setMaxWidth(Double.MAX_VALUE);
        confirmBtn.setPrefHeight(56);
        confirmBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
        confirmBtn.setOnAction(e -> MainApp.showHome());

        footer.getChildren().addAll(selectionRow, confirmBtn);

        layout.getChildren().addAll(header, searchContainer, scroll);
        getChildren().addAll(layout, footer);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
    }

    private VBox createExpandedWestlands() {
        VBox section = new VBox(0);
        section.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 10, 15, 10));

        VBox titleBox = new VBox(2);
        Label title = new Label("Westlands");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label sub = new Label("5 Wards available");
        sub.setTextFill(Color.web(TEXT_GRAY));
        sub.setFont(Font.font(12));
        titleBox.getChildren().addAll(title, sub);
        HBox.setHgrow(titleBox, Priority.ALWAYS);

        Label arrow = new Label("\u25b2"); // Up arrow
        arrow.setTextFill(Color.web(TEXT_GRAY));

        header.getChildren().addAll(titleBox, arrow);

        // Content
        VBox content = new VBox(5);
        content.setPadding(new Insets(5, 10, 15, 20));
        content.setStyle("-fx-border-color: rgba(19, 236, 91, 0.3); -fx-border-width: 0 0 0 2;");
        VBox.setMargin(content, new Insets(0, 0, 0, 10));

        content.getChildren().addAll(
                createWardItem("Kitisuru", "Upscale residential area", true),
                createWardItem("Parklands/Highridge", "Commercial and residential", false),
                createWardItem("Karura", "Adjacent to Karura Forest", true),
                createWardItem("Kangemi", "High density residential", false),
                createWardItem("Mountain View", "Residential estate", false));

        section.getChildren().addAll(header, content);
        return section;
    }

    private HBox createWardItem(String name, String desc, boolean checked) {
        HBox row = new HBox(15);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10));
        row.setStyle("-fx-background-radius: 8;");
        row.setOnMouseEntered(
                e -> row.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 8;"));
        row.setOnMouseExited(e -> row.setStyle("-fx-background-radius: 8;"));
        row.setCursor(javafx.scene.Cursor.HAND);

        // Custom Checkbox
        Label checkbox = new Label(checked ? "\u2714" : "");
        checkbox.setAlignment(Pos.CENTER);
        checkbox.setPrefSize(24, 24);
        checkbox.setStyle("-fx-border-color: " + (checked ? PRIMARY : BORDER_COLOR)
                + "; -fx-border-width: 2; -fx-border-radius: 6; -fx-text-fill: " + PRIMARY
                + "; -fx-font-weight: bold;");

        VBox textBox = new VBox(2);
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font("System", FontWeight.MEDIUM, 16));
        Label d = new Label(desc);
        d.setTextFill(Color.web(TEXT_GRAY));
        d.setFont(Font.font(12));
        textBox.getChildren().addAll(n, d);
        HBox.setHgrow(textBox, Priority.ALWAYS);

        row.getChildren().addAll(textBox, checkbox); // Reversed layout as per HTML (flex-row-reverse)
        return row;
    }

    private VBox createCollapsedItem(String name, String desc) {
        VBox section = new VBox(0);
        section.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 10, 15, 10));
        header.setCursor(javafx.scene.Cursor.HAND);

        VBox titleBox = new VBox(2);
        Label title = new Label(name);
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label sub = new Label(desc);
        sub.setTextFill(Color.web(TEXT_GRAY));
        sub.setFont(Font.font(12));
        titleBox.getChildren().addAll(title, sub);
        HBox.setHgrow(titleBox, Priority.ALWAYS);

        Label arrow = new Label("\u25bc"); // Down arrow
        arrow.setTextFill(Color.web(TEXT_GRAY));

        header.getChildren().addAll(titleBox, arrow);
        section.getChildren().add(header);
        return section;
    }
}
