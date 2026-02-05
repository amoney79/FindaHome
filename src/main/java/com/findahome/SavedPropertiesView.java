package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SavedPropertiesView extends BorderPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String CARD_BG = "#1c222c";
    private static final String PRIMARY = "#137fec";
    private static final String TEXT_GRAY = "#9da6b9";

    public SavedPropertiesView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateCached("profile", TenantProfileView::new));

        Label title = new Label("Saved Homes");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        HBox.setHgrow(title, Priority.ALWAYS);

        Button editBtn = new Button("Edit");
        editBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                + "; -fx-font-weight: bold; -fx-cursor: hand;");

        header.getChildren().addAll(backBtn, title, editBtn);
        setTop(header);

        // Content
        VBox content = new VBox(20);
        content.setPadding(new Insets(20, 20, 40, 20));
        content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        content.getChildren().addAll(
                createPropertyCard("Luxury Apartment", "Kileleshwa, Nairobi", "KSh 85,000/mo",
                        "https://images.unsplash.com/photo-1545324418-cc1a3fa10c00?w=400&auto=format&fit=crop"),
                createPropertyCard("Modern Villa", "Runda, Nairobi", "KSh 250,000/mo",
                        "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?w=400&auto=format&fit=crop"),
                createPropertyCard("Cozy Studio", "Westlands, Nairobi", "KSh 45,000/mo",
                        "https://images.unsplash.com/photo-1493663284031-b7e3aefcae8e?w=400&auto=format&fit=crop"),
                createPropertyCard("Garden Duplex", "Lavington, Nairobi", "KSh 120,000/mo",
                        "https://images.unsplash.com/photo-1484154218962-a197022b5858?w=400&auto=format&fit=crop"));

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
        setCenter(scroll);

        // Footer Action (Pinned)
        VBox footer = new VBox();
        footer.setPadding(new Insets(20, 20, 35, 20));
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0;");

        Button findMoreBtn = new Button("Find More Homes \uD83D\uDD0D");
        findMoreBtn.setMaxWidth(Double.MAX_VALUE);
        findMoreBtn.setPrefHeight(56);
        findMoreBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand; -fx-effect: dropshadow(three-pass-box, rgba(19, 127, 236, 0.2), 10, 0, 0, 5);");
        findMoreBtn.setOnAction(e -> MainApp.showHome());

        footer.getChildren().add(findMoreBtn);
        setBottom(footer);
    }

    private HBox createPropertyCard(String title, String loc, String price, String imgUrl) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 16; -fx-cursor: hand; -fx-border-color: rgba(255,255,255,0.05);");

        // Image
        StackPane imgContainer = new StackPane();
        imgContainer.setPrefSize(90, 90);
        try {
            ImageView img = new ImageView(new Image(imgUrl, 180, 180, true, true, true));
            img.setFitWidth(90);
            img.setFitHeight(90);
            Rectangle clip = new Rectangle(90, 90);
            clip.setArcWidth(16);
            clip.setArcHeight(16);
            img.setClip(clip);
            imgContainer.getChildren().add(img);
        } catch (Exception e) {
        }

        // Info
        VBox info = new VBox(4);
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label l = new Label("\uD83D\uDCCD " + loc);
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font(13));

        Label p = new Label(price);
        p.setTextFill(Color.web(PRIMARY));
        p.setFont(Font.font("System", FontWeight.BOLD, 15));

        info.getChildren().addAll(t, l, p);
        HBox.setHgrow(info, Priority.ALWAYS);

        // Quick Actions
        VBox actions = new VBox(8);
        actions.setAlignment(Pos.CENTER);
        Button heartBtn = new Button("\u2764"); // Heart
        heartBtn.setStyle(
                "-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: #ef4444; -fx-background-radius: 20; -fx-min-width: 36; -fx-min-height: 36; -fx-cursor: hand;");
        actions.getChildren().add(heartBtn);

        card.getChildren().addAll(imgContainer, info, actions);

        card.setOnMouseEntered(e -> card.setStyle(
                "-fx-background-color: #252b36; -fx-background-radius: 16; -fx-cursor: hand; -fx-border-color: "
                        + PRIMARY + "40;"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 16; -fx-cursor: hand; -fx-border-color: rgba(255,255,255,0.05);"));

        return card;
    }
}
