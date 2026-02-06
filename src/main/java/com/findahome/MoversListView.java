package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MoversListView extends BorderPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String ACCENT_ORANGE = "#f97316";
    private static final String TEXT_GRAY = "#9db9a6";
    private static final String CARD_BG = "#1c271f";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

    public MoversListView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Top Navigation
        HBox topNav = new HBox(15);
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setPadding(new Insets(15, 20, 15, 20));
        topNav.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        Label backIcon = new Label("‹");
        backIcon.setTextFill(Color.WHITE);
        backIcon.setStyle("-fx-font-size: 28; -fx-cursor: hand; -fx-font-weight: bold;");
        backIcon.setOnMouseClicked(e -> MainApp.navigateCached("service", ServiceHubView::new));

        Label navTitle = new Label("Movers & Packers");
        navTitle.setTextFill(Color.WHITE);
        navTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        navTitle.setAlignment(Pos.CENTER);
        navTitle.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(navTitle, Priority.ALWAYS);

        Label searchIcon = new Label("🔍");
        searchIcon.setTextFill(Color.WHITE);
        searchIcon.setStyle("-fx-font-size: 18; -fx-cursor: hand;");

        topNav.getChildren().addAll(backIcon, navTitle, searchIcon);
        setTop(topNav);

        // Content Area
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_LEFT);

        // Location Dropdown
        VBox locBox = new VBox(8);
        Label locLabel = new Label("Service Location");
        locLabel.setTextFill(Color.WHITE);
        locLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        ComboBox<String> locationDropdown = new ComboBox<>();
        locationDropdown.getItems().addAll("Nairobi", "Mombasa", "Kisumu", "Nakuru");
        locationDropdown.setValue("Nairobi");
        locationDropdown.setMaxWidth(Double.MAX_VALUE);
        locationDropdown.setPrefHeight(45);
        locationDropdown.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                + BORDER_COLOR + "; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 0 10;");

        locBox.getChildren().addAll(locLabel, locationDropdown);

        // Filter Chips
        ScrollPane chipsScroll = new ScrollPane();
        chipsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chipsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chipsScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        HBox chips = new HBox(10);
        chips.setPadding(new Insets(5, 0, 5, 0));
        chips.getChildren().addAll(
                createChip("All Movers", true),
                createChip("Office", false),
                createChip("International", false),
                createChip("Storage", false));
        chipsScroll.setContent(chips);

        // Results Header
        Label resultsHeader = new Label("Showing 24 verified movers in Nairobi");
        resultsHeader.setTextFill(Color.WHITE);
        resultsHeader.setFont(Font.font("System", FontWeight.BOLD, 16));

        // Movers List
        VBox moversList = new VBox(15);
        moversList.getChildren().addAll(
                createMoverCard("Swift Move Kenya", "4.8 (120 reviews)",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCK_hX-W6e-X-Y-Z-0-1-2-3-4-5-6-7-8-9-10",
                        new String[] { "Local Moving", "Office Relocation", "+2 more" }),
                createMoverCard("Reliable Haulers Ltd", "4.6 (85 reviews)",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCL_hX-W6e-X-Y-Z-0-1-2-3-4-5-6-7-8-9-10",
                        new String[] { "Packing Materials", "Storage" }),
                createMoverCard("Global Relocations", "4.9 (240 reviews)",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCM_hX-W6e-X-Y-Z-0-1-2-3-4-5-6-7-8-9-10",
                        new String[] { "International Moving", "Insurance Cover" }));

        content.getChildren().addAll(locBox, chipsScroll, resultsHeader, moversList);

        ScrollPane mainScroll = new ScrollPane(content);
        mainScroll.setFitToWidth(true);
        mainScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        setCenter(mainScroll);

        // Floating Compare Button
        Button compareBtn = new Button("⇆ Compare Movers (0)");
        compareBtn.setStyle(
                "-fx-background-color: #1a1a1a; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14; -fx-background-radius: 25; -fx-padding: 12 24; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 5);");
        StackPane.setAlignment(compareBtn, Pos.BOTTOM_CENTER);
        StackPane.setMargin(compareBtn, new Insets(0, 0, 30, 0));

        // Use a Wrapper StackPane to hold the center scroll and the floating button
        StackPane centerWrapper = new StackPane(mainScroll, compareBtn);
        setCenter(centerWrapper);
    }

    private Button createChip(String text, boolean active) {
        Button b = new Button(text);
        if (active) {
            b.setStyle("-fx-background-color: " + ACCENT_ORANGE
                    + "; -fx-text-fill: white; -fx-background-radius: 20; -fx-font-weight: bold; -fx-padding: 8 16;");
        } else {
            b.setStyle(
                    "-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: #3b4754; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 7 15;");
        }
        return b;
    }

    private VBox createMoverCard(String name, String rating, String logoUrl, String[] tags) {
        VBox card = new VBox(15);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05);");

        HBox top = new HBox(15);
        top.setAlignment(Pos.TOP_LEFT);

        VBox info = new VBox(6);
        HBox.setHgrow(info, Priority.ALWAYS);

        // Validated Badge
        HBox badge = new HBox(5);
        badge.setAlignment(Pos.CENTER_LEFT);
        badge.setPadding(new Insets(4, 8, 4, 8));
        badge.setStyle("-fx-background-color: rgba(19, 236, 91, 0.15); -fx-background-radius: 4;");
        Label check = new Label("✔");
        check.setTextFill(Color.web("#13ec5b")); // Keep Verified Green
        check.setFont(Font.font(10));
        Label vText = new Label("VERIFIED");
        vText.setTextFill(Color.web("#13ec5b"));
        vText.setFont(Font.font("System", FontWeight.BOLD, 10));
        badge.getChildren().addAll(check, vText);

        // Use a placeholder if necessary to constrain badge width
        HBox badgeWrap = new HBox(badge);

        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label r = new Label("⭐ " + rating);
        r.setTextFill(Color.web("#eab308")); // Yellow/Gold
        r.setFont(Font.font(13));

        info.getChildren().addAll(badgeWrap, n, r);

        // Logo
        StackPane logoBox = new StackPane();
        logoBox.setPrefSize(60, 60);
        logoBox.setStyle(
                "-fx-background-color: #0f172a; -fx-background-radius: 8; -fx-border-color: rgba(255,255,255,0.1);");

        Label logoPlaceholder = new Label("📦");
        logoPlaceholder.setStyle("-fx-font-size: 24;");
        logoBox.getChildren().add(logoPlaceholder);

        top.getChildren().addAll(info, logoBox);

        // Tags
        FlowPane tagsPane = new FlowPane(8, 8);
        for (String tag : tags) {
            Label t = new Label(tag);
            t.setTextFill(Color.web(TEXT_GRAY));
            t.setFont(Font.font(12));
            t.setPadding(new Insets(4, 10, 4, 10));
            t.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 6;");
            tagsPane.getChildren().add(t);
        }

        // Action Buttons
        HBox actions = new HBox(10);

        Button quoteBtn = new Button("Get a Quote");
        quoteBtn.setGraphic(new Label("💲 "));
        ((Label) quoteBtn.getGraphic()).setTextFill(Color.WHITE);
        quoteBtn.setMaxWidth(Double.MAX_VALUE);
        quoteBtn.setPrefHeight(40);
        HBox.setHgrow(quoteBtn, Priority.ALWAYS);
        quoteBtn.setStyle("-fx-background-color: " + ACCENT_ORANGE
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-cursor: hand;");
        quoteBtn.setOnAction(e -> MainApp.navigateCachedFullScreen("movers_quote", MoversQuoteRequestView::new));

        Button chatBtn = new Button("💬");
        chatBtn.setPrefSize(40, 40);
        chatBtn.setStyle(
                "-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: white; -fx-background-radius: 8; -fx-cursor: hand;");
        chatBtn.setOnAction(e -> MainApp.navigateCached("messages", ChatView::new));

        actions.getChildren().addAll(quoteBtn, chatBtn);

        card.getChildren().addAll(top, tagsPane, actions);
        return card;
    }
}
