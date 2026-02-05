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

        private static final String BACKGROUND_DARK = "#0d1117";
        private static final String PRIMARY = "#216cf2"; // Blue as in screenshot
        private static final String SUCCESS = "#05c46b"; // Green for trends
        private static final String DANGER = "#ff5e57"; // Red for trends
        private static final String TEXT_GRAY = "#9ca3af";
        private static final String CARD_BG = "#161b22";
        private static final String DIVIDER_COLOR = "#30363d";

        public GuideView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
                setSpacing(0);

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-background-color: transparent;"); // Transparent to overlay image

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
                content.setPadding(new Insets(0, 0, 100, 0)); // Padding to avoid content being hidden by FAB

                // Hero Section
                StackPane hero = new StackPane();
                hero.setPrefHeight(450);
                try {
                        ImageView heroImg = new ImageView(new Image(
                                        "https://images.unsplash.com/photo-1542362567-b07e54358753?q=80&w=1000&auto=format&fit=crop",
                                        800, 450, true, true));
                        heroImg.setPreserveRatio(true);
                        heroImg.fitWidthProperty().bind(hero.widthProperty());

                        Rectangle overlay = new Rectangle();
                        overlay.widthProperty().bind(hero.widthProperty());
                        overlay.heightProperty().bind(hero.heightProperty());
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
                        hTitle.setFont(Font.font("System", FontWeight.BOLD, 38));
                        hTitle.setWrapText(true);

                        heroContent.getChildren().addAll(tag, hTitle);
                        hero.getChildren().addAll(heroImg, overlay, heroContent);
                } catch (Exception e) {
                }

                content.getChildren().add(hero);

                // Community Scores
                VBox scoreSection = new VBox(15);
                scoreSection.setPadding(new Insets(0, 20, 0, 20));
                scoreSection.getChildren().add(createSectionHeader("\ud83d\udcc8 Community Scores"));

                HBox statsRow = new HBox(15);
                statsRow.getChildren().addAll(
                                createScoreCard("Safety Score", "8.5", "+5% vs last month", SUCCESS,
                                                "\ud83d\udee1\ufe0f"),
                                createScoreCard("Quietness Score", "7.2", "-2% vs last month", DANGER, "\ud83d\udd07"));
                scoreSection.getChildren().add(statsRow);
                content.getChildren().add(scoreSection);

                // Education
                VBox education = new VBox(15);
                education.setPadding(new Insets(0, 20, 0, 20));
                education.getChildren().add(createSectionHeader("\ud83c\udf93 Education"));
                education.getChildren().addAll(
                                createAmenityItem("Kileleshwa Primary School", "Public Institution", "0.4 KM",
                                                "\ud83d\udcd6"),
                                createAmenityItem("Lavington School", "Private Secondary", "1.2 KM", "\ud83d\udca0"));
                content.getChildren().add(education);

                // Healthcare
                VBox healthcare = new VBox(15);
                healthcare.setPadding(new Insets(0, 20, 0, 20));
                healthcare.getChildren().add(createSectionHeader("\ud83c\udfe5 Healthcare"));
                healthcare.getChildren().addAll(
                                createAmenityItem("Kileleshwa Medical Plaza", "24/7 Outpatient", "0.2 KM", "\u2715"),
                                createAmenityItem("Nairobi Hospital Outpatient", "Lavington Branch", "1.5 KM",
                                                "\u2731"));
                content.getChildren().add(healthcare);

                // Transport
                VBox transport = new VBox(15);
                transport.setPadding(new Insets(0, 20, 0, 20));
                transport.getChildren().add(createSectionHeader("\ud83d\ude8c Transport"));
                transport.getChildren().addAll(
                                createAmenityItem("Ring Road Kileleshwa", "Major Artery", "0.1 KM", "\ud83d\udd31"),
                                createAmenityItem("Kandara Road Stage", "Route 48 Matatu Stage", "0.3 KM",
                                                "\ud83d\ude8c"));
                content.getChildren().add(transport);

                // Security
                VBox security = new VBox(15);
                security.setPadding(new Insets(0, 20, 0, 20));
                security.getChildren().add(createSectionHeader("\ud83d\udee1\ufe0f Security"));
                security.getChildren().add(createAmenityItem("Kileleshwa Police Station", "24hr Surveillance", "0.6 KM",
                                "\ud83d\udee1\ufe0f"));
                content.getChildren().add(security);

                scrollPane.setContent(content);

                // Floating Action Button Style for Browse
                StackPane footer = new StackPane();
                footer.setPadding(new Insets(20));
                footer.setPickOnBounds(false);

                Button browseBtn = new Button("Browse Listings in Kileleshwa \u2192");
                browseBtn.setMaxWidth(400);
                browseBtn.setPrefHeight(55);
                browseBtn.setFont(Font.font("System", FontWeight.BOLD, 15));
                browseBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(33, 108, 242, 0.3), 15, 0, 0, 5);");
                browseBtn.setOnAction(e -> MainApp.navigateToMap());
                footer.getChildren().add(browseBtn);

                VBox layoutContainer = new VBox(0);
                layoutContainer.setMaxWidth(600);
                layoutContainer.setAlignment(Pos.TOP_CENTER);
                layoutContainer.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                StackPane mainOverlay = new StackPane();
                mainOverlay.getChildren().addAll(scrollPane, header, footer);

                StackPane.setAlignment(header, Pos.TOP_CENTER);
                StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);

                layoutContainer.getChildren().add(mainOverlay);
                VBox.setVgrow(mainOverlay, Priority.ALWAYS);

                HBox rootBox = new HBox(layoutContainer);
                rootBox.setAlignment(Pos.CENTER);
                HBox.setHgrow(layoutContainer, Priority.ALWAYS);
                VBox.setVgrow(rootBox, Priority.ALWAYS);

                getChildren().clear();
                getChildren().add(rootBox);

                // Bind layout container height to GuideView height to enable scrolling
                layoutContainer.maxHeightProperty().bind(heightProperty());
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
                                + DIVIDER_COLOR + "; -fx-border-radius: 20;");
                HBox.setHgrow(card, Priority.ALWAYS);

                HBox header = new HBox();
                header.setAlignment(Pos.CENTER_LEFT);
                Label t = new Label(title);
                t.setTextFill(Color.web(TEXT_GRAY));
                t.setFont(Font.font(14));
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                Label i = new Label(icon);
                i.setTextFill(Color.web(trendColor));
                i.setFont(Font.font(16));
                header.getChildren().addAll(t, spacer, i);

                HBox scoreRow = new HBox(5);
                scoreRow.setAlignment(Pos.BOTTOM_LEFT);
                Label v = new Label(val);
                v.setTextFill(Color.WHITE);
                v.setFont(Font.font("System", FontWeight.BOLD, 32));
                Label total = new Label("/10");
                total.setTextFill(Color.web(TEXT_GRAY));
                total.setFont(Font.font(14));
                scoreRow.getChildren().addAll(v, total);

                Label tr = new Label("\u21dd " + trend); // wavy arrow
                tr.setTextFill(Color.web(trendColor));
                tr.setFont(Font.font("System", FontWeight.MEDIUM, 12));

                card.getChildren().addAll(header, scoreRow, tr);
                return card;
        }

        private HBox createAmenityItem(String name, String sub, String distance, String icon) {
                HBox item = new HBox(15);
                item.setAlignment(Pos.CENTER_LEFT);
                item.setPadding(new Insets(15));
                item.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + DIVIDER_COLOR + "; -fx-border-radius: 16;");

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
                dist.setStyle("-fx-background-color: #1c222c; -fx-text-fill: " + TEXT_GRAY
                                + "; -fx-padding: 4 10; -fx-background-radius: 8; -fx-font-size: 11;");

                item.getChildren().addAll(iconBox, tx, dist);
                return item;
        }
}
