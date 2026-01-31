package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LocationFilterView extends StackPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String CARD_BG = "#1c2433";
    private static final String PRIMARY = "#135bec";
    private static final String TEXT_GRAY = "#9da6b9";
    private static final String BORDER_COLOR = "#2a3544";

    public LocationFilterView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        VBox headerContainer = new VBox(0);
        headerContainer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "cc; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 0 0 1 0;");

        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 10, 20));

        Label closeBtn = new Label("\u2715");
        closeBtn.setTextFill(Color.WHITE);
        closeBtn.setStyle("-fx-font-size: 18; -fx-cursor: hand;");
        closeBtn.setOnMouseClicked(e -> MainApp.showHome());

        Label title = new Label("Select Location");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Label resetBtn = new Label("Reset");
        resetBtn.setTextFill(Color.web(PRIMARY));
        resetBtn.setFont(Font.font("System", FontWeight.BOLD, 14));
        resetBtn.setCursor(javafx.scene.Cursor.HAND);

        header.getChildren().addAll(closeBtn, title, resetBtn);

        // Breadcrumb
        HBox breadcrumb = new HBox(5);
        breadcrumb.setAlignment(Pos.CENTER_LEFT);
        breadcrumb.setPadding(new Insets(10, 20, 10, 20));
        breadcrumb.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        breadcrumb.getChildren().addAll(
                createBreadcrumbItem("Kenya", false),
                createBreadcrumbChevron(),
                createBreadcrumbItem("Nairobi", true),
                createBreadcrumbChevron(),
                createBreadcrumbItem("Westlands", false),
                createBreadcrumbChevron(),
                createBreadcrumbItem("Ward", false));

        headerContainer.getChildren().addAll(header, breadcrumb);

        // Scroll Content
        VBox scrollContent = new VBox(0);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        scrollContent.setPadding(new Insets(0, 0, 180, 0));

        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Search Bar
        HBox searchContainer = new HBox();
        searchContainer.setPadding(new Insets(15, 20, 15, 20));

        StackPane searchStack = new StackPane();
        HBox.setHgrow(searchStack, Priority.ALWAYS);

        TextField searchField = new TextField();
        searchField.setPromptText("Search county, sub-county or ward...");
        searchField.setPrefHeight(44);
        searchField.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-text-fill: white; -fx-prompt-text-fill: #666; -fx-background-radius: 12; -fx-padding: 0 15 0 40;");

        Label searchIcon = new Label("\ud83d\udd0d");
        searchIcon.setTextFill(Color.web(TEXT_GRAY));
        StackPane.setAlignment(searchIcon, Pos.CENTER_LEFT);
        StackPane.setMargin(searchIcon, new Insets(0, 0, 0, 12));

        searchStack.getChildren().addAll(searchField, searchIcon);
        searchContainer.getChildren().add(searchStack);

        // Section Header
        HBox sectionHeader = new HBox();
        sectionHeader.setPadding(new Insets(10, 20, 10, 20));
        sectionHeader.setStyle("-fx-background-color: rgba(255,255,255,0.03);");
        Label countiesLbl = new Label("COUNTIES (47)");
        countiesLbl.setTextFill(Color.web(TEXT_GRAY));
        countiesLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);
        Label mapLink = new Label("View Map");
        mapLink.setTextFill(Color.web(PRIMARY));
        mapLink.setFont(Font.font("System", FontWeight.MEDIUM, 12));
        mapLink.setCursor(javafx.scene.Cursor.HAND);
        sectionHeader.getChildren().addAll(countiesLbl, sp, mapLink);

        // Location List
        VBox locationList = new VBox(0);

        // Nairobi (Expanded)
        VBox nairobiSection = new VBox(0);
        nairobiSection.getChildren().add(createCountyRow("Nairobi City", "17 Sub-counties, 85 Wards", true, true));

        // Sub-counties
        VBox subCounties = new VBox(0);
        subCounties.setPadding(new Insets(0, 0, 0, 30));
        subCounties.setStyle("-fx-background-color: rgba(255,255,255,0.02);");

        // Westlands (Expanded)
        subCounties.getChildren().add(createSubCountyRow("Westlands", "5 Wards", true));

        // Wards
        VBox wards = new VBox(0);
        wards.setPadding(new Insets(0, 0, 0, 30));
        wards.setStyle("-fx-background-color: rgba(255,255,255,0.01);");
        wards.getChildren().addAll(
                createWardRow("Parklands/Highridge", false),
                createWardRow("Kileleshwa", true),
                createWardRow("Kangemi", false));
        subCounties.getChildren().add(wards);

        subCounties.getChildren().addAll(
                createSubCountyRow("Kilimani", null, false),
                createSubCountyRow("Lang'ata", null, false));

        nairobiSection.getChildren().add(subCounties);
        locationList.getChildren().add(nairobiSection);

        // Other counties
        locationList.getChildren().addAll(
                createCountyRow("Mombasa", "6 Sub-counties (Nyali, Kisauni...)", false, false),
                createCountyRow("Kiambu", "12 Sub-counties", false, false),
                createCountyRow("Kisumu", "7 Sub-counties", false, false),
                createCountyRow("Nakuru", "11 Sub-counties", false, false));

        // Popular Wards
        VBox popularSection = new VBox(12);
        popularSection.setPadding(new Insets(25, 20, 20, 20));
        Label popularTitle = new Label("POPULAR WARDS");
        popularTitle.setTextFill(Color.web(TEXT_GRAY));
        popularTitle.setFont(Font.font("System", FontWeight.BOLD, 10));

        FlowPane popularChips = new FlowPane(10, 10);
        popularChips.getChildren().addAll(
                createChip("Bamburi, Mombasa"),
                createChip("Runda, Nairobi"),
                createChip("Syokimau, Machakos"));
        popularSection.getChildren().addAll(popularTitle, popularChips);

        scrollContent.getChildren().addAll(searchContainer, sectionHeader, locationList, popularSection);

        // Footer
        VBox footer = new VBox(15);
        footer.setPadding(new Insets(20));
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");

        HBox selectionRow = new HBox();
        selectionRow.setAlignment(Pos.CENTER_LEFT);
        VBox selectionInfo = new VBox(2);
        Label selLbl = new Label("SELECTION");
        selLbl.setTextFill(Color.web(TEXT_GRAY));
        selLbl.setFont(Font.font("System", FontWeight.BOLD, 9));
        Label selValue = new Label("Nairobi / Westlands / Kileleshwa");
        selValue.setTextFill(Color.WHITE);
        selValue.setFont(Font.font("System", FontWeight.BOLD, 14));
        selectionInfo.getChildren().addAll(selLbl, selValue);
        HBox.setHgrow(selectionInfo, Priority.ALWAYS);
        Label clearBtn = new Label("Clear All");
        clearBtn.setTextFill(Color.web(PRIMARY));
        clearBtn.setFont(Font.font("System", FontWeight.BOLD, 12));
        clearBtn.setCursor(javafx.scene.Cursor.HAND);
        selectionRow.getChildren().addAll(selectionInfo, clearBtn);

        Button applyBtn = new Button("Apply Location");
        applyBtn.setGraphic(new Label("\u2713"));
        applyBtn.setContentDisplay(ContentDisplay.RIGHT);
        applyBtn.setMaxWidth(Double.MAX_VALUE);
        applyBtn.setPrefHeight(56);
        applyBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12;");
        applyBtn.setOnAction(e -> MainApp.showHome());

        footer.getChildren().addAll(selectionRow, applyBtn);

        // Map FAB
        Button mapFab = new Button("\ud83d\uddfa");
        mapFab.setPrefSize(56, 56);
        mapFab.setStyle("-fx-background-color: white; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-size: 20; -fx-background-radius: 28; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 20, 0, 0, 8);");
        mapFab.setOnAction(e -> MainApp.navigateToMap());
        StackPane.setAlignment(mapFab, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(mapFab, new Insets(0, 20, 200, 0));

        layout.getChildren().addAll(headerContainer, scroll);
        getChildren().addAll(layout, footer, mapFab);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
    }

    private Label createBreadcrumbItem(String text, boolean active) {
        Label lbl = new Label(text);
        if (active) {
            lbl.setTextFill(Color.web(PRIMARY));
            lbl.setFont(Font.font("System", FontWeight.BOLD, 12));
            lbl.setStyle("-fx-background-color: rgba(19, 91, 236, 0.1); -fx-padding: 4 8; -fx-background-radius: 4;");
        } else {
            lbl.setTextFill(Color.web(TEXT_GRAY));
            lbl.setFont(Font.font("System", FontWeight.MEDIUM, 12));
        }
        lbl.setCursor(javafx.scene.Cursor.HAND);
        return lbl;
    }

    private Label createBreadcrumbChevron() {
        Label lbl = new Label("\u203a");
        lbl.setTextFill(Color.web(TEXT_GRAY));
        return lbl;
    }

    private HBox createCountyRow(String name, String sub, boolean active, boolean expanded) {
        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(15, 20, 15, 20));
        row.setCursor(javafx.scene.Cursor.HAND);
        if (active) {
            row.setStyle("-fx-background-color: rgba(19, 91, 236, 0.05); -fx-border-color: " + PRIMARY
                    + "; -fx-border-width: 0 0 0 4;");
        } else {
            row.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");
        }

        Label icon = new Label("\ud83d\udccd");
        icon.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));

        VBox info = new VBox(2);
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font("System", FontWeight.BOLD, 15));
        Label s = new Label(sub);
        s.setTextFill(Color.web(TEXT_GRAY));
        s.setFont(Font.font(12));
        info.getChildren().addAll(n, s);
        HBox.setHgrow(info, Priority.ALWAYS);

        Label arrow = new Label(expanded ? "\u25bc" : "\u203a");
        arrow.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));

        row.getChildren().addAll(icon, info, arrow);
        return row;
    }

    private HBox createSubCountyRow(String name, String wardCount, boolean expanded) {
        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(12, 20, 12, 15));
        row.setCursor(javafx.scene.Cursor.HAND);
        row.setStyle("-fx-border-color: rgba(19, 91, 236, 0.3); -fx-border-width: 0 0 1 2;");

        Circle dot = new Circle(4, expanded ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));

        VBox info = new VBox(2);
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font("System", FontWeight.BOLD, 14));
        if (wardCount != null) {
            Label w = new Label(wardCount);
            w.setTextFill(Color.web(TEXT_GRAY));
            w.setFont(Font.font(10));
            info.getChildren().addAll(n, w);
        } else {
            info.getChildren().add(n);
        }
        HBox.setHgrow(info, Priority.ALWAYS);

        Label arrow = new Label(expanded ? "\u25bc" : "\u203a");
        arrow.setTextFill(Color.web(TEXT_GRAY));

        row.getChildren().addAll(dot, info, arrow);
        return row;
    }

    private HBox createWardRow(String name, boolean selected) {
        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(12, 20, 12, 15));
        row.setCursor(javafx.scene.Cursor.HAND);
        row.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        Label radio = new Label(selected ? "\u2714" : "\u25cb");
        radio.setTextFill(selected ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));

        Label n = new Label(name);
        n.setTextFill(selected ? Color.WHITE : Color.web("#cbd5e1"));
        n.setFont(Font.font("System", selected ? FontWeight.BOLD : FontWeight.MEDIUM, 14));
        HBox.setHgrow(n, Priority.ALWAYS);

        row.getChildren().addAll(radio, n);

        if (selected) {
            Label tag = new Label("Selected");
            tag.setTextFill(Color.WHITE);
            tag.setFont(Font.font("System", FontWeight.BOLD, 9));
            tag.setStyle("-fx-background-color: " + PRIMARY + "; -fx-padding: 2 6; -fx-background-radius: 4;");
            row.getChildren().add(tag);
        }

        return row;
    }

    private Label createChip(String text) {
        Label chip = new Label(text);
        chip.setTextFill(Color.WHITE);
        chip.setFont(Font.font("System", FontWeight.MEDIUM, 12));
        chip.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-padding: 8 12; -fx-background-radius: 8; -fx-border-color: " + BORDER_COLOR + ";");
        chip.setCursor(javafx.scene.Cursor.HAND);
        return chip;
    }
}
