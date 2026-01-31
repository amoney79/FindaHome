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

public class StoryPreviewView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String ACTION_ORANGE = "#FF5722";
    private static final String TEXT_GRAY = "#9db9a6";

    public StoryPreviewView(Property property) {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new PropertyDetailView(property)));

        Label title = new Label("Story Preview");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Label moreBtn = new Label("\u22ef");
        moreBtn.setTextFill(Color.WHITE);
        moreBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");

        header.getChildren().addAll(backBtn, title, moreBtn);

        // Main Story Simulator
        VBox simulatorArea = new VBox();
        simulatorArea.setAlignment(Pos.CENTER);
        VBox.setVgrow(simulatorArea, Priority.ALWAYS);
        simulatorArea.setPadding(new Insets(10, 20, 10, 20));

        StackPane storyContainer = new StackPane();
        storyContainer.setMaxWidth(340);
        storyContainer.setPrefHeight(600); // 9:16 roughly
        storyContainer.setStyle(
                "-fx-background-color: black; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(19, 236, 91, 0.2), 20, 0, 0, 0);");

        Rectangle clip = new Rectangle();
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        storyContainer.setClip(clip);
        storyContainer.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            clip.setWidth(newVal.getWidth());
            clip.setHeight(newVal.getHeight());
        });

        ImageView bgImg = new ImageView();
        try {
            bgImg.setImage(new Image(property.getImageUrl(), 340, 600, false, true));
        } catch (Exception e) {
        }
        bgImg.setFitWidth(340);
        bgImg.setFitHeight(600);
        bgImg.setPreserveRatio(false);

        // Gradient Overlay
        Region overlay = new Region();
        overlay.setStyle(
                "-fx-background-color: linear-gradient(to bottom, rgba(0,0,0,0.4) 0%, transparent 30%, transparent 70%, rgba(0,0,0,0.6) 100%);");

        // Watermark
        HBox watermark = new HBox(8);
        watermark.setAlignment(Pos.CENTER_LEFT);
        watermark.setPadding(new Insets(30, 0, 0, 24));
        StackPane logoBox = new StackPane();
        logoBox.setPadding(new Insets(6));
        logoBox.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 8;");
        Label logoIcon = new Label("\u2302");
        logoIcon.setTextFill(Color.web(BACKGROUND_DARK));
        logoIcon.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        logoBox.getChildren().add(logoIcon);
        Label brand = new Label("FindaHome");
        brand.setTextFill(Color.WHITE);
        brand.setFont(Font.font("System", FontWeight.BLACK, 18));
        watermark.getChildren().addAll(logoBox, brand);
        StackPane.setAlignment(watermark, Pos.TOP_LEFT);

        // Status Badge
        Label statusBadge = new Label("FOR RENT");
        statusBadge.setStyle("-fx-background-color: " + ACTION_ORANGE
                + "; -fx-text-fill: white; -fx-font-weight: 900; -fx-padding: 6 16; -fx-background-radius: 8; -fx-font-size: 12;");
        StackPane.setAlignment(statusBadge, Pos.TOP_RIGHT);
        StackPane.setMargin(statusBadge, new Insets(30, 24, 0, 0));

        // Details Card
        VBox detailBox = new VBox(15);
        detailBox.setAlignment(Pos.BOTTOM_CENTER);
        detailBox.setPadding(new Insets(0, 24, 80, 24));

        VBox glassCard = new VBox(5);
        glassCard.setPadding(new Insets(20));
        glassCard.setStyle(
                "-fx-background-color: rgba(0,0,0,0.4); -fx-background-radius: 20; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1;");

        Label price = new Label(property.getPrice() + "/mo");
        price.setTextFill(Color.WHITE);
        price.setFont(Font.font("System", FontWeight.BLACK, 32));

        Label location = new Label(property.getLocation() + ", Luxury Villa");
        location.setTextFill(Color.web("rgba(255,255,255,0.9)"));
        location.setFont(Font.font("System", FontWeight.MEDIUM, 16));

        HBox specs = new HBox(15);
        specs.setPadding(new Insets(10, 0, 0, 0));
        specs.getChildren().addAll(
                createSpecItem("\ud83d\udecf\ufe0f", "4 Beds"),
                createSpecItem("\ud83d\udebf", "3 Baths"),
                createSpecItem("\ud83d\udccf", "2.4k sqft"));
        glassCard.getChildren().addAll(price, location, specs);

        // CTA Link
        VBox ctaBox = new VBox(8);
        ctaBox.setAlignment(Pos.CENTER);
        ctaBox.setPadding(new Insets(15, 0, 0, 0));

        HBox linkSticker = new HBox(8);
        linkSticker.setAlignment(Pos.CENTER);
        linkSticker.setPadding(new Insets(8, 24, 8, 24));
        linkSticker.setStyle("-fx-background-color: white; -fx-background-radius: 25;");
        Label linkIcon = new Label("\ud83d\udd17");
        linkIcon.setTextFill(Color.web("#2563eb"));
        Label linkText = new Label("VIEW LISTING");
        linkText.setTextFill(Color.web("#2563eb"));
        linkText.setFont(Font.font("System", FontWeight.BOLD, 12));
        linkSticker.getChildren().addAll(linkIcon, linkText);

        Label linkLabel = new Label("LINK IN BIO");
        linkLabel.setTextFill(Color.WHITE);
        linkLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        linkLabel.setStyle("-fx-effect: dropshadow(three-pass-box, black, 5, 0, 0, 0);");

        ctaBox.getChildren().addAll(linkSticker, linkLabel);

        detailBox.getChildren().addAll(glassCard, ctaBox);
        StackPane.setAlignment(detailBox, Pos.BOTTOM_CENTER);

        storyContainer.getChildren().addAll(bgImg, overlay, watermark, statusBadge, detailBox);

        Label variantLbl = new Label("Variant 2: Clean Minimalist");
        variantLbl.setTextFill(Color.web(TEXT_GRAY));
        variantLbl.setFont(Font.font(13));
        VBox.setMargin(variantLbl, new Insets(15, 0, 0, 0));

        simulatorArea.getChildren().addAll(storyContainer, variantLbl);

        // Bottom Actions
        VBox footer = new VBox(12);
        footer.setPadding(new Insets(20, 24, 30, 24));

        Button shareBtn = new Button("Share to Stories");
        shareBtn.setGraphic(new Label("\ud83d\udce4"));
        shareBtn.setMaxWidth(Double.MAX_VALUE);
        shareBtn.setPrefHeight(56);
        shareBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-background-radius: 12;");

        Button saveBtn = new Button("Save to Device");
        saveBtn.setGraphic(new Label("\u2913"));
        saveBtn.setMaxWidth(Double.MAX_VALUE);
        saveBtn.setPrefHeight(56);
        saveBtn.setStyle(
                "-fx-background-color: #28392e; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12;");

        footer.getChildren().addAll(shareBtn, saveBtn);

        layout.getChildren().addAll(header, simulatorArea, footer);
        getChildren().add(layout);
    }

    private HBox createSpecItem(String icon, String text) {
        HBox item = new HBox(4);
        item.setAlignment(Pos.CENTER_LEFT);
        Label i = new Label(icon);
        i.setTextFill(Color.web("rgba(255,255,255,0.8)"));
        i.setStyle("-fx-font-size: 14;");
        Label t = new Label(text);
        t.setTextFill(Color.web("rgba(255,255,255,0.8)"));
        t.setFont(Font.font(10));
        item.getChildren().addAll(i, t);
        return item;
    }
}
