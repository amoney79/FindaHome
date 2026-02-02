package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GuideView extends VBox {

        private static final String BACKGROUND_DARK = "#101622";
        private static final String PRIMARY = "#13ec5b";
        private static final String TEXT_GRAY = "#9da6b9";
        private static final String CARD_BG = "#1c222c";
        private static final String DIVIDER_COLOR = "#2a3544";

        public GuideView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
                setSpacing(0);

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-background-color: rgba(16, 22, 34, 0.95); -fx-border-color: " + DIVIDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u2039");
                backBtn.setFont(Font.font("System", FontWeight.BOLD, 28));
                backBtn.setTextFill(Color.WHITE);
                backBtn.setCursor(javafx.scene.Cursor.HAND);
                backBtn.setOnMouseClicked(e -> MainApp.showHome());

                Label title = new Label("Neighborhood Insight");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                HBox.setHgrow(title, Priority.ALWAYS);
                title.setAlignment(Pos.CENTER);

                Label shareBtn = new Label("\u21E8");
                shareBtn.setFont(Font.font("System", FontWeight.BOLD, 18));
                shareBtn.setTextFill(Color.WHITE);

                header.getChildren().addAll(backBtn, title, shareBtn);

                // Scroll Content
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
                scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scrollPane, Priority.ALWAYS);

                VBox content = new VBox(25);
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Hero Section
                StackPane hero = new StackPane();
                hero.setPrefHeight(300);
                try {
                        ImageView heroImg = new ImageView(new Image(
                                        "https://images.unsplash.com/photo-1542362567-b07e54358753?q=80&w=1000&auto=format&fit=crop",
                                        600, 300, true, true));
                        heroImg.setFitWidth(600);

                        Rectangle overlay = new Rectangle();
                        overlay.widthProperty().bind(hero.widthProperty());
                        overlay.heightProperty().bind(hero.heightProperty());
                        overlay.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                                        new Stop(0, Color.rgb(16, 22, 34, 0.2)),
                                        new Stop(1, Color.rgb(16, 22, 34, 0.8))));

                        VBox heroText = new VBox(5);
                        heroText.setAlignment(Pos.BOTTOM_LEFT);
                        heroText.setPadding(new Insets(30));
                        Label ht1 = new Label("KILELESHWA");
                        ht1.setTextFill(Color.web(PRIMARY));
                        ht1.setFont(Font.font("System", FontWeight.BOLD, 14));
                        Label ht2 = new Label("Heart of Nairobi");
                        ht2.setTextFill(Color.WHITE);
                        ht2.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 36));
                        heroText.getChildren().addAll(ht1, ht2);

                        hero.getChildren().addAll(heroImg, overlay, heroText);
                } catch (Exception e) {
                }

                content.getChildren().add(hero);

                // Stats Row
                HBox statsRow = new HBox(15);
                statsRow.setPadding(new Insets(0, 20, 0, 20));
                statsRow.getChildren().addAll(
                                createScoreCard("Safety", "8.5", "High", "#0bda5e"),
                                createScoreCard("Noise", "7.2", "Quiet", "#fa6238"));
                content.getChildren().add(statsRow);

                // Amenities
                VBox amenities = new VBox(20);
                amenities.setPadding(new Insets(0, 20, 40, 20));
                amenities.getChildren().add(createSectionHeader("Local Amenities"));

                amenities.getChildren().addAll(
                                createAmenityItem("Kileleshwa Primary", "Education \u2022 0.4 KM", "\ud83d\udcd6"),
                                createAmenityItem("Medical Plaza", "Health \u2022 0.2 KM", "\ud83c\udfe5"),
                                createAmenityItem("Ring Road", "Transport \u2022 0.1 KM", "\ud83d\ude8c"));
                content.getChildren().add(amenities);

                scrollPane.setContent(content);

                // Footer
                HBox footer = new HBox();
                footer.setPadding(new Insets(15, 20, 15, 20));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + DIVIDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");
                Button browseBtn = new Button("Browse Nearby Properties \u2192");
                browseBtn.setMaxWidth(Double.MAX_VALUE);
                browseBtn.setPrefHeight(50);
                browseBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 12;");
                browseBtn.setOnAction(e -> MainApp.navigateToMap());
                HBox.setHgrow(browseBtn, Priority.ALWAYS);
                footer.getChildren().add(browseBtn);

                getChildren().addAll(header, scrollPane, footer);
        }

        private Label createSectionHeader(String text) {
                Label l = new Label(text);
                l.setTextFill(Color.WHITE);
                l.setFont(Font.font("System", FontWeight.BOLD, 20));
                return l;
        }

        private VBox createScoreCard(String title, String val, String status, String color) {
                VBox card = new VBox(5);
                card.setPadding(new Insets(15));
                card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16;");
                HBox.setHgrow(card, Priority.ALWAYS);

                Label t = new Label(title);
                t.setTextFill(TEXT_GRAY.equals("") ? Color.GRAY : Color.web(TEXT_GRAY));
                t.setFont(Font.font(12));

                Label v = new Label(val);
                v.setTextFill(Color.WHITE);
                v.setFont(Font.font("System", FontWeight.BOLD, 24));

                Label s = new Label(status);
                s.setTextFill(Color.web(color));
                s.setFont(Font.font("System", FontWeight.BOLD, 10));

                card.getChildren().addAll(t, v, s);
                return card;
        }

        private HBox createAmenityItem(String name, String sub, String icon) {
                HBox item = new HBox(12);
                item.setAlignment(Pos.CENTER_LEFT);
                item.setPadding(new Insets(12));
                item.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12;");

                Label i = new Label(icon);
                i.setTextFill(Color.web(PRIMARY));
                i.setFont(Font.font(20));

                VBox tx = new VBox(2);
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label s = new Label(sub);
                s.setTextFill(Color.web(TEXT_GRAY));
                s.setFont(Font.font(12));
                tx.getChildren().addAll(n, s);

                item.getChildren().addAll(i, tx);
                return item;
        }
}
