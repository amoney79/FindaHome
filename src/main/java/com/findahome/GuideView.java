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

public class GuideView extends StackPane {

        private static final String BACKGROUND_DARK = "#101622";
        private static final String PRIMARY = "#13ec5b"; // Green theme
        private static final String TEXT_GRAY = "#9da6b9";
        private static final String CARD_BG = "#1c222c";
        private static final String DIVIDER_COLOR = "#2a3544";

        public GuideView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                BorderPane mainLayout = new BorderPane();

                // Header (Sticky-like)
                VBox header = new VBox();
                header.setStyle("-fx-background-color: rgba(16, 22, 34, 0.95);");
                header.setPadding(new Insets(15, 20, 15, 20));

                HBox navBar = new HBox(15);
                navBar.setAlignment(Pos.CENTER_LEFT);

                Label backBtn = new Label("\u2039"); // Arrow back
                backBtn.setFont(Font.font("System", FontWeight.BOLD, 28));
                backBtn.setTextFill(Color.WHITE);
                backBtn.setCursor(javafx.scene.Cursor.HAND);
                backBtn.setOnMouseClicked(e -> MainApp.showHome());

                Label title = new Label("Neighborhood Insight");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setMaxWidth(Double.MAX_VALUE);
                title.setAlignment(Pos.CENTER);
                HBox.setHgrow(title, Priority.ALWAYS);

                Label shareBtn = new Label("\u21E8"); // Share arrow
                shareBtn.setFont(Font.font("System", FontWeight.BOLD, 18));
                shareBtn.setTextFill(Color.WHITE);

                navBar.getChildren().addAll(backBtn, title, shareBtn);
                header.getChildren().add(navBar);
                mainLayout.setTop(header);

                // Scroll Content
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
                scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                VBox content = new VBox(20);
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Hero Section
                StackPane hero = new StackPane();
                hero.setPrefHeight(300);
                try {
                        ImageView heroImg = new ImageView(new Image(
                                        "https://images.unsplash.com/photo-1542362567-b07e54358753?q=80&w=1000&auto=format&fit=crop",
                                        800, 300, true, true));
                        heroImg.setPreserveRatio(true);
                        heroImg.setFitWidth(800);

                        // Darker Overlay for better text readability
                        Rectangle overlay = new Rectangle();
                        overlay.widthProperty().bind(hero.widthProperty());
                        overlay.heightProperty().bind(hero.heightProperty());
                        overlay.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                                        new Stop(0, Color.rgb(16, 22, 34, 0.4)),
                                        new Stop(0.7, Color.rgb(16, 22, 34, 0.7)),
                                        new Stop(1, Color.rgb(16, 22, 34, 1.0))));

                        VBox heroText = new VBox(5);
                        heroText.setAlignment(Pos.BOTTOM_LEFT);
                        heroText.setPadding(new Insets(30));
                        Label ht1 = new Label("NEIGHBORHOOD GUIDE");
                        ht1.setTextFill(Color.web(PRIMARY));
                        ht1.setFont(Font.font("System", FontWeight.BOLD, 14));
                        Label ht2 = new Label("Kileleshwa, Nairobi");
                        ht2.setTextFill(Color.WHITE);
                        ht2.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 36));
                        heroText.getChildren().addAll(ht1, ht2);

                        hero.getChildren().addAll(heroImg, overlay, heroText);
                } catch (Exception e) {
                }

                content.getChildren().add(hero);

                // Community Scores
                VBox scoresSec = new VBox(20);
                scoresSec.setPadding(new Insets(0, 20, 0, 20));
                scoresSec.getChildren().add(createSectionTitle("\ud83d\udcca", "Community Scores"));

                HBox scoreCards = new HBox(15);
                scoreCards.getChildren().addAll(
                                createScoreCard("Safety Score", "8.5", "Increasing", "#0bda5e", "\ud83d\udee1"),
                                createScoreCard("Quietness", "7.2", "Stable", "#fa6238", "\ud83d\udd07"));
                scoresSec.getChildren().add(scoreCards);
                content.getChildren().add(scoresSec);

                // Amenities Section
                VBox amenitiesSect = new VBox(25);
                amenitiesSect.setPadding(new Insets(10, 20, 30, 20));

                Label amTitle = new Label("Neighborhood Amenities");
                amTitle.setTextFill(Color.WHITE);
                amTitle.setFont(Font.font("System", FontWeight.BOLD, 22));

                VBox amGrid = new VBox(20);
                amGrid.getChildren().addAll(
                                createAmenityCategory("Education", "\ud83c\udf93",
                                                createAmenityItem("Kileleshwa Primary", "Public \u2022 0.4 KM",
                                                                "\ud83d\udcd6"),
                                                createAmenityItem("Lavington School", "Intl \u2022 1.2 KM",
                                                                "\ud83d\udcdc")),
                                createAmenityCategory("Healthcare", "\ud83c\udfe5",
                                                createAmenityItem("Medical Plaza", "Clinic \u2022 0.2 KM",
                                                                "\ud83c\udfe5"),
                                                createAmenityItem("Nairobi Hospital", "Branch \u2022 1.5 KM",
                                                                "\ud83d\ude91")),
                                createAmenityCategory("Transport", "\ud83d\ude8c",
                                                createAmenityItem("Ring Road", "Major \u2022 0.1 KM", "\u26f5"),
                                                createAmenityItem("Matatu Stage", "Stage \u2022 0.3 KM",
                                                                "\ud83d\ude8f")));

                amenitiesSect.getChildren().addAll(amTitle, amGrid);
                content.getChildren().add(amenitiesSect);

                scrollPane.setContent(content);
                mainLayout.setCenter(scrollPane);

                // Footer
                VBox footer = new VBox();
                footer.setPadding(new Insets(20));
                footer.setStyle("-fx-background-color: rgba(16, 22, 34, 0.95); -fx-border-color: " + DIVIDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                Button browseBtn = new Button("Browse Listings in Kileleshwa  \u2794");
                browseBtn.setMaxWidth(Double.MAX_VALUE);
                browseBtn.setPrefHeight(56);
                browseBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12;");
                browseBtn.setOnAction(e -> MainApp.navigateTo(new AmenitiesMapView()));

                footer.getChildren().add(browseBtn);
                mainLayout.setBottom(footer);

                getChildren().add(mainLayout);
        }

        private HBox createSectionTitle(String icon, String text) {
                HBox box = new HBox(10);
                box.setAlignment(Pos.CENTER_LEFT);
                Label ic = new Label(icon);
                ic.setTextFill(Color.web(PRIMARY));
                ic.setFont(Font.font(22));
                Label tx = new Label(text);
                tx.setTextFill(Color.WHITE);
                tx.setFont(Font.font("System", FontWeight.BOLD, 22));
                box.getChildren().addAll(ic, tx);
                return box;
        }

        private VBox createAmenityCategory(String title, String icon, HBox... items) {
                VBox cat = new VBox(12);
                HBox head = new HBox(10);
                head.setAlignment(Pos.CENTER_LEFT);
                Label ic = new Label(icon);
                ic.setTextFill(Color.web(PRIMARY));
                ic.setFont(Font.font(16));
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 16));
                head.getChildren().addAll(ic, t);

                VBox list = new VBox(10);
                list.getChildren().addAll(items);
                cat.getChildren().addAll(head, list);
                return cat;
        }

        private VBox createScoreCard(String title, String score, String detail, String colorHex, String iconStr) {
                VBox card = new VBox(8);
                card.setPadding(new Insets(15));
                card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + DIVIDER_COLOR + "; -fx-border-radius: 16;");
                HBox.setHgrow(card, Priority.ALWAYS);

                Label t = new Label(title);
                t.setTextFill(Color.web(TEXT_GRAY));
                t.setFont(Font.font(13));

                HBox scoreRow = new HBox(5);
                scoreRow.setAlignment(Pos.BASELINE_LEFT);
                Label sc = new Label(score);
                sc.setTextFill(Color.WHITE);
                sc.setFont(Font.font("System", FontWeight.BOLD, 28));
                Label max = new Label("/10");
                max.setTextFill(Color.web(TEXT_GRAY));
                max.setFont(Font.font(14));
                scoreRow.getChildren().addAll(sc, max);

                Label det = new Label(iconStr + " " + detail);
                det.setTextFill(Color.web(colorHex));
                det.setFont(Font.font("System", FontWeight.MEDIUM, 12));

                card.getChildren().addAll(t, scoreRow, det);
                return card;
        }

        private HBox createAmenityItem(String name, String sub, String iconStr) {
                HBox item = new HBox(12);
                item.setPadding(new Insets(12));
                item.setAlignment(Pos.CENTER_LEFT);
                item.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 14; -fx-border-color: "
                                + DIVIDER_COLOR + "; -fx-border-radius: 14;");

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(44, 44);
                iconBox.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 10;");
                Label ic = new Label(iconStr);
                ic.setTextFill(Color.web(PRIMARY));
                ic.setFont(Font.font(18));
                iconBox.getChildren().add(ic);

                VBox text = new VBox(2);
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 15));
                Label s = new Label(sub);
                s.setTextFill(Color.web(TEXT_GRAY));
                s.setFont(Font.font(13));
                text.getChildren().addAll(n, s);
                HBox.setHgrow(text, Priority.ALWAYS);

                item.getChildren().addAll(iconBox, text);
                return item;
        }
}
