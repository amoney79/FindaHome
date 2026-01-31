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

public class SharePropertyView extends StackPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";

        public SharePropertyView(Property property, Runnable onClose) {
                // Overlay Background
                setStyle("-fx-background-color: rgba(0,0,0,0.7);");

                VBox bottomSheet = new VBox(0);
                bottomSheet.setMaxWidth(430);
                bottomSheet.setAlignment(Pos.TOP_CENTER);
                bottomSheet.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-background-radius: 24 24 0 0; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");
                StackPane.setAlignment(bottomSheet, Pos.BOTTOM_CENTER);

                // Modal Handle
                Region handle = new Region();
                handle.setPrefSize(48, 6);
                handle.setMaxWidth(48);
                handle.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 3;");
                VBox.setMargin(handle, new Insets(12, 0, 12, 0));

                Label title = new Label("Share Property");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 19));
                VBox.setMargin(title, new Insets(8, 0, 15, 0));

                // Smart Share Card
                VBox cardContainer = new VBox(12);
                cardContainer.setAlignment(Pos.CENTER);
                cardContainer.setPadding(new Insets(0, 30, 0, 30));

                StackPane smartCard = new StackPane();
                smartCard.setPrefSize(300, 375);
                smartCard.setMaxSize(300, 375);
                smartCard.setStyle(
                                "-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 15, 0, 0, 10);");

                ImageView propertyImg = new ImageView();
                try {
                        propertyImg.setImage(new Image(property.getImageUrl(), 300, 375, false, true));
                } catch (Exception e) {
                }
                propertyImg.setFitWidth(300);
                propertyImg.setFitHeight(375);
                Rectangle imgClip = new Rectangle(300, 375);
                imgClip.setArcWidth(40);
                imgClip.setArcHeight(40);
                propertyImg.setClip(imgClip);

                // Gradient Overlay
                Region gradient = new Region();
                gradient.setStyle(
                                "-fx-background-color: linear-gradient(to top, rgba(16, 34, 22, 0.95) 0%, rgba(16, 34, 22, 0.2) 40%, transparent 100%); -fx-background-radius: 20;");

                // Watermark
                HBox watermark = new HBox(8);
                watermark.setAlignment(Pos.CENTER_LEFT);
                watermark.setPadding(new Insets(6, 12, 6, 12));
                watermark.setStyle(
                                "-fx-background-color: rgba(0,0,0,0.4); -fx-background-radius: 20; -fx-border-color: rgba(255,255,255,0.2);");
                Label logoIcon = new Label("\u2302"); // Home icon
                logoIcon.setTextFill(Color.web(PRIMARY));
                Label logoText = new Label("FindaHome");
                logoText.setTextFill(Color.WHITE);
                logoText.setFont(Font.font("System", FontWeight.BOLD, 12));
                watermark.getChildren().addAll(logoIcon, logoText);
                StackPane.setAlignment(watermark, Pos.TOP_LEFT);
                StackPane.setMargin(watermark, new Insets(15));

                // Card Details
                VBox cardDetails = new VBox(4);
                cardDetails.setAlignment(Pos.BOTTOM_LEFT);
                cardDetails.setPadding(new Insets(20));

                Label status = new Label("AVAILABLE NOW");
                status.setTextFill(Color.web(PRIMARY));
                status.setFont(Font.font("System", FontWeight.BOLD, 10));
                status.setStyle("-fx-letter-spacing: 1px;");

                HBox priceRow = new HBox(4);
                priceRow.setAlignment(Pos.BASELINE_LEFT);
                Label price = new Label(property.getPrice());
                price.setTextFill(Color.WHITE);
                price.setFont(Font.font("System", FontWeight.BOLD, 24));
                Label mo = new Label("/mo");
                mo.setTextFill(Color.WHITE);
                mo.setFont(Font.font(16));
                priceRow.getChildren().addAll(price, mo);

                HBox loc = new HBox(4);
                loc.setAlignment(Pos.CENTER_LEFT);
                Label pin = new Label("\ud83d\udccd");
                pin.setTextFill(Color.web("#e4e4e7"));
                Label locName = new Label(property.getLocation());
                locName.setTextFill(Color.web("#e4e4e7"));
                locName.setFont(Font.font(14));
                loc.getChildren().addAll(pin, locName);

                cardDetails.getChildren().addAll(status, priceRow, loc);
                StackPane.setAlignment(cardDetails, Pos.BOTTOM_LEFT);

                smartCard.getChildren().addAll(propertyImg, gradient, watermark, cardDetails);

                Label previewLbl = new Label("Preview of your smart share card");
                previewLbl.setTextFill(Color.web("#71717a"));
                previewLbl.setFont(Font.font(11));

                cardContainer.getChildren().addAll(smartCard, previewLbl);

                // Actions Header
                Label shareTitle = new Label("Share via");
                shareTitle.setTextFill(Color.WHITE);
                shareTitle.setFont(Font.font("System", FontWeight.BOLD, 15));
                VBox.setMargin(shareTitle, new Insets(20, 25, 10, 25));
                shareTitle.setAlignment(Pos.CENTER_LEFT);
                shareTitle.setMaxWidth(Double.MAX_VALUE);

                // Social Grid
                HBox socialGrid = new HBox(25);
                socialGrid.setAlignment(Pos.CENTER);
                socialGrid.setPadding(new Insets(10, 20, 10, 20));
                socialGrid.getChildren().addAll(
                                createSocialItem("\ud83d\udcac", "WhatsApp", "#10b981", () -> {
                                }),
                                createSocialItem("\ud83d\udcf7", "Stories", "#ec4899",
                                                () -> MainApp.navigateTo(new StoryPreviewView(property))),
                                createSocialItem("f", "Facebook", "#3b82f6", () -> {
                                }),
                                createSocialItem("\ud83d\udd4a\ufe0f", "Twitter", "#38bdf8", () -> {
                                }));

                // Footer Buttons
                VBox footerBtns = new VBox(12);
                footerBtns.setPadding(new Insets(20, 25, 40, 25));

                Button copyLinkBtn = new Button("Copy Link");
                copyLinkBtn.setGraphic(new Label("\ud83d\udd17"));
                copyLinkBtn.setMaxWidth(Double.MAX_VALUE);
                copyLinkBtn.setPrefHeight(50);
                copyLinkBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-weight: bold; -fx-background-radius: 12;");

                Button saveImgBtn = new Button("Save Image");
                saveImgBtn.setGraphic(new Label("\u2913"));
                saveImgBtn.setMaxWidth(Double.MAX_VALUE);
                saveImgBtn.setPrefHeight(50);
                saveImgBtn.setStyle(
                                "-fx-background-color: #27272a; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1);");

                footerBtns.getChildren().addAll(copyLinkBtn, saveImgBtn);

                bottomSheet.getChildren().addAll(handle, title, cardContainer, shareTitle, socialGrid, footerBtns);
                getChildren().add(bottomSheet);

                // Dismiss on background click
                setOnMouseClicked(e -> {
                        if (e.getTarget() == this) {
                                onClose.run();
                        }
                });
        }

        private VBox createSocialItem(String icon, String label, String color, Runnable action) {
                VBox item = new VBox(8);
                item.setAlignment(Pos.CENTER);
                item.setCursor(javafx.scene.Cursor.HAND);
                item.setOnMouseClicked(e -> action.run());

                StackPane iconCircle = new StackPane();
                iconCircle.setPrefSize(50, 50);
                iconCircle.setStyle(
                                "-fx-background-color: " + color + "1a; -fx-background-radius: 25; -fx-border-color: "
                                                + color + "33;");

                Label i = new Label(icon);
                i.setTextFill(Color.web(color));
                i.setStyle("-fx-font-size: 20;");
                iconCircle.getChildren().add(i);

                Label l = new Label(label);
                l.setTextFill(Color.web("#d4d4d8"));
                l.setFont(Font.font(11));

                item.getChildren().addAll(iconCircle, l);

                item.setOnMousePressed(e -> item.setOpacity(0.7));
                item.setOnMouseReleased(e -> item.setOpacity(1.0));

                return item;
        }
}
