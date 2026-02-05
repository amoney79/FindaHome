package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
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

public class GuideView extends BorderPane {

        private static final String BACKGROUND_DARK = "#0d1117";
        private static final String PRIMARY = "#216cf2";
        private static final String SUCCESS = "#05c46b";
        private static final String DANGER = "#ff5e57";
        private static final String TEXT_GRAY = "#9ca3af";
        private static final String CARD_BG = "#161b22";
        private static final String DIVIDER_COLOR = "#30363d";

        public GuideView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-background-color: rgba(13, 17, 23, 0.9); -fx-border-color: " + DIVIDER_COLOR
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

                Label shareBtn = new Label("\uD83D\uDCE4");
                shareBtn.setFont(Font.font("System", FontWeight.BOLD, 18));
                shareBtn.setTextFill(Color.WHITE);
                shareBtn.setCursor(javafx.scene.Cursor.HAND);

                header.getChildren().addAll(backBtn, title, shareBtn);
                setTop(header);

                // Scroll Content
                VBox content = new VBox(25);
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
                content.setPadding(new Insets(0, 0, 30, 0));

                // Hero Section
                StackPane hero = new StackPane();
                hero.setPrefHeight(300);
                try {
                        ImageView heroImg = new ImageView(new Image(
                                        "https://images.unsplash.com/photo-1542362567-b07e54358753?q=80&w=1000&auto=format&fit=crop",
                                        800, 300, false, true, true));
                        heroImg.setFitHeight(300);
                        heroImg.setPreserveRatio(false);
                        heroImg.fitWidthProperty().bind(widthProperty());

                        Rectangle overlay = new Rectangle();
                        overlay.widthProperty().bind(widthProperty());
                        overlay.setHeight(300);
                        overlay.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                                        new Stop(0, Color.TRANSPARENT),
                                        new Stop(0.6, Color.rgb(13, 17, 23, 0.4)),
                                        new Stop(1, Color.web(BACKGROUND_DARK))));

                        VBox heroContent = new VBox(10);
                        heroContent.setAlignment(Pos.BOTTOM_LEFT);
                        heroContent.setPadding(new Insets(30));

                        Label tag = new Label("NEIGHBORHOOD GUIDE");
                        tag.setTextFill(Color.web(TEXT_GRAY));
                        tag.setFont(Font.font("System", FontWeight.BOLD, 12));
                        tag.setStyle("-fx-letter-spacing: 2px;");

                        Label hTitle = new Label("Kileleshwa, Nairobi");
                        hTitle.setTextFill(Color.WHITE);
                        hTitle.setFont(Font.font("System", FontWeight.BOLD, 32));
                        hTitle.setWrapText(true);

                        heroContent.getChildren().addAll(tag, hTitle);
                        hero.getChildren().addAll(heroImg, overlay, heroContent);
                } catch (Exception e) {
                }

                content.getChildren().add(hero);

                // Community Scores
                VBox scoreSection = new VBox(15);
                scoreSection.setPadding(new Insets(0, 20, 0, 20));
                scoreSection.getChildren().add(createSectionHeader("\uD83D\uDCC8 Community Scores"));

                HBox statsRow = new HBox(15);
                statsRow.getChildren().addAll(
                                createScoreCard("Safety Score", "8.5", "+5% vs last month", SUCCESS,
                                                "\uD83D\uDEE1\uFE0F"),
                                createScoreCard("Quietness Score", "7.2", "-2% vs last month", DANGER, "\uD83D\uDD07"));
                scoreSection.getChildren().add(statsRow);
                content.getChildren().add(scoreSection);

                // Amenities Sections (Unified for simplicity)
                content.getChildren().add(createAmenitySection("\uD83C\uDF93 Education",
                                createAmenityItem("Kileleshwa Primary School", "Public Institution", "0.4 KM",
                                                "\uD83D\uDCD6"),
                                createAmenityItem("Lavington School", "Private Secondary", "1.2 KM", "\uD83D\uDCA0")));

                content.getChildren().add(createAmenitySection("\uD83C\uDFE5 Healthcare",
                                createAmenityItem("Kileleshwa Medical Plaza", "24/7 Outpatient", "0.2 KM", "\u2715"),
                                createAmenityItem("Nairobi Hospital Outpatient", "Lavington Branch", "1.5 KM",
                                                "\u2731")));

                content.getChildren().add(createAmenitySection("\uD83D\uDE8C Transport",
                                createAmenityItem("Ring Road Kileleshwa", "Major Artery", "0.1 KM", "\uD83D\uDD31"),
                                createAmenityItem("Kandara Road Stage", "Route 48 Matatu Stage", "0.3 KM",
                                                "\uD83D\uDE8C")));

                ScrollPane scrollPane = new ScrollPane(content);
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle(
                                "-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
                setCenter(scrollPane);

                // Footer Action (Pinned)
                VBox footer = new VBox();
                footer.setPadding(new Insets(20, 20, 35, 20));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + DIVIDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                Button browseBtn = new Button("Browse Listings in Kileleshwa \u2192");
                browseBtn.setMaxWidth(Double.MAX_VALUE);
                browseBtn.setPrefHeight(56);
                browseBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand; -fx-effect: dropshadow(three-pass-box, rgba(33, 108, 242, 0.3), 10, 0, 0, 5);");
                browseBtn.setOnAction(e -> MainApp.navigateToMap());
                footer.getChildren().add(browseBtn);
                setBottom(footer);
        }

        private VBox createAmenitySection(String title, Node... items) {
                VBox sect = new VBox(15);
                sect.setPadding(new Insets(0, 20, 0, 20));
                sect.getChildren().add(createSectionHeader(title));
                sect.getChildren().addAll(items);
                return sect;
        }

        private Label createSectionHeader(String text) {
                Label l = new Label(text);
                l.setTextFill(Color.WHITE);
                l.setFont(Font.font("System", FontWeight.BOLD, 20));
                return l;
        }

        private VBox createScoreCard(String title, String val, String trend, String trendColor, String icon) {
                VBox card = new VBox(10);
                card.setPadding(new Insets(20));
                card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 20; -fx-border-color: "
                                + DIVIDER_COLOR + ";");
                HBox.setHgrow(card, Priority.ALWAYS);

                HBox head = new HBox();
                head.setAlignment(Pos.CENTER_LEFT);
                Label t = new Label(title);
                t.setTextFill(Color.web(TEXT_GRAY));
                t.setFont(Font.font(14));
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                Label i = new Label(icon);
                i.setTextFill(Color.web(trendColor));
                i.setFont(Font.font(16));
                head.getChildren().addAll(t, spacer, i);

                HBox scoreRow = new HBox(5);
                scoreRow.setAlignment(Pos.BOTTOM_LEFT);
                Label v = new Label(val);
                v.setTextFill(Color.WHITE);
                v.setFont(Font.font("System", FontWeight.BOLD, 32));
                Label total = new Label("/10");
                total.setTextFill(Color.web(TEXT_GRAY));
                total.setFont(Font.font(14));
                scoreRow.getChildren().addAll(v, total);

                Label tr = new Label("\u21dd " + trend);
                tr.setTextFill(Color.web(trendColor));
                tr.setFont(Font.font("System", FontWeight.MEDIUM, 12));

                card.getChildren().addAll(head, scoreRow, tr);
                return card;
        }

        private HBox createAmenityItem(String name, String sub, String distance, String icon) {
                HBox item = new HBox(15);
                item.setAlignment(Pos.CENTER_LEFT);
                item.setPadding(new Insets(15));
                item.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + DIVIDER_COLOR + ";");

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(45, 45);
                iconBox.setStyle("-fx-background-color: rgba(33, 108, 242, 0.1); -fx-background-radius: 12;");
                Label i = new Label(icon);
                i.setTextFill(Color.web(PRIMARY));
                i.setFont(Font.font(20));
                iconBox.getChildren().add(i);

                VBox tx = new VBox(4);
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 15));
                Label s = new Label(sub);
                s.setTextFill(Color.web(TEXT_GRAY));
                s.setFont(Font.font(13));
                tx.getChildren().addAll(n, s);
                HBox.setHgrow(tx, Priority.ALWAYS);

                Label dist = new Label(distance);
                dist.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-text-fill: " + TEXT_GRAY
                                + "; -fx-padding: 4 10; -fx-background-radius: 8; -fx-font-size: 11;");

                item.getChildren().addAll(iconBox, tx, dist);
                return item;
        }
}
