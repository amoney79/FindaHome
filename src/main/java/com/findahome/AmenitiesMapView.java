package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AmenitiesMapView extends StackPane {

        private static final String BACKGROUND_DARK = "#0a0f18";
        private static final String PRIMARY = "#13ec5b"; // Green theme
        private static final String TEXT_GRAY = "#9da6b9";
        private static final String GLASS_BG = "rgba(16, 22, 34, 0.95)";
        private static final String CARD_BG = "#1c222c";
        private static final String DIVIDER_COLOR = "#2a3544";

        public AmenitiesMapView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Map Layer
                StackPane mapLayer = new StackPane();
                try {
                        ImageView mapImg = new ImageView(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuBKx6tNr2S9Vqyfq8GODTPECW7vsYtiYX_5YGAg9CPJHnJAolmF3x8BOybg1VBbfmyoBNi0J7hu2PYZbsfHLGD-K3qhcS4ulpJCq86kfmsSHX6b2jKPH0qSRH7RCl8km1Lm8aMb9-0gxlFPItq4aL4vOLXqeM21LTFbaUIDd6pQ1hTba9CuYDiNGYIgjBxe0dcxvsZ7suynnS-SDPZxpeDzi8WXCK2jGTClX72FIeHTe6TzEn8RIe2MCe6ZCiuy8kKbhffEX5bEYnQ",
                                        500, 1000, true, true));
                        mapImg.setOpacity(0.6);
                        mapLayer.getChildren().add(mapImg);
                } catch (Exception e) {
                }

                // Map Pins (Simulated)
                Pane pinLayer = new Pane();
                pinLayer.getChildren().addAll(
                                createMapPin(140, 300, "#ef4444", "\ud83c\udfe5"), // Hospital (Red)
                                createMapPin(230, 450, PRIMARY, "\ud83c\udf93"), // School (Green/Primary)
                                createMapPin(120, 600, "#22c55e", "\ud83c\udf33"), // Park (Green)
                                createMapPin(80, 200, "#16a34a", "\ud83d\uded2") // Grocer (Green)
                );
                mapLayer.getChildren().add(pinLayer);

                // UI Layer
                BorderPane uiLayer = new BorderPane();
                uiLayer.setPickOnBounds(false);

                // Top Bar
                VBox topContainer = new VBox(0);
                topContainer.setStyle("-fx-background-color: " + GLASS_BG + ";");

                // Navigation Header
                HBox navBar = new HBox(15);
                navBar.setPadding(new Insets(15, 20, 10, 20));
                navBar.setAlignment(Pos.CENTER_LEFT);

                Label backBtn = new Label("\u2039");
                backBtn.setFont(Font.font("System", FontWeight.BOLD, 24));
                backBtn.setTextFill(Color.WHITE);
                backBtn.setCursor(javafx.scene.Cursor.HAND);
                backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new GuideView())); // Back to guide

                VBox titleBox = new VBox(2);
                titleBox.setAlignment(Pos.CENTER);
                Label t1 = new Label("Kileleshwa Ward");
                t1.setTextFill(Color.WHITE);
                t1.setFont(Font.font("System", FontWeight.BOLD, 16));
                Label t2 = new Label("Nairobi, Kenya");
                t2.setTextFill(Color.web(TEXT_GRAY));
                t2.setFont(Font.font(12));
                titleBox.getChildren().addAll(t1, t2);
                HBox.setHgrow(titleBox, Priority.ALWAYS);

                StackPane searchBtn = new StackPane();
                searchBtn.setPrefSize(40, 40);
                searchBtn.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 20;");
                Label searchIcon = new Label("\ud83d\udd0d");
                searchIcon.setTextFill(Color.WHITE);
                searchBtn.getChildren().add(searchIcon);

                navBar.getChildren().addAll(backBtn, titleBox, searchBtn);

                // Filter Chips
                HBox chips = new HBox(12);
                chips.setPadding(new Insets(12, 16, 12, 16));
                chips.getChildren().addAll(
                                createFilterChip("All", "\u25a6", true, PRIMARY),
                                createFilterChip("Schools", "\ud83c\udf93", false, PRIMARY),
                                createFilterChip("Hospitals", "\ud83c\udfe5", false, "#ef4444"),
                                createFilterChip("Grocery", "\ud83d\uded2", false, "#22c55e"));
                ScrollPane chipScroll = new ScrollPane(chips);
                chipScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
                chipScroll.setVbarPolicy(ScrollBarPolicy.NEVER);
                chipScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                topContainer.getChildren().addAll(navBar, chipScroll);
                uiLayer.setTop(topContainer);

                // Map Controls
                VBox controls = new VBox(8);
                Button zoomIn = createGlassIconBtn("+");
                Button zoomOut = createGlassIconBtn("-");
                Button locate = createGlassIconBtn("\u2316");
                locate.setStyle(locate.getStyle() + "-fx-text-fill: " + PRIMARY + ";");
                controls.getChildren().addAll(zoomIn, zoomOut, locate);

                AnchorPane centerPane = new AnchorPane();
                centerPane.setPickOnBounds(false);
                centerPane.getChildren().add(controls);
                AnchorPane.setTopAnchor(controls, 16.0);
                AnchorPane.setRightAnchor(controls, 16.0);
                uiLayer.setCenter(centerPane);

                // Bottom Carousel
                VBox bottomContainer = new VBox(10);
                bottomContainer.setPadding(new Insets(0, 0, 90, 0)); // Padding for nav bar

                HBox carouselHeader = new HBox();
                carouselHeader.setPadding(new Insets(0, 16, 0, 16));
                Label hTitle = new Label("Nearby Highlights");
                hTitle.setTextFill(Color.WHITE);
                hTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                hTitle.setEffect(new javafx.scene.effect.DropShadow(2, Color.BLACK)); // Text shadow
                Region sp = new Region();
                HBox.setHgrow(sp, Priority.ALWAYS);
                Label vAll = new Label("View All");
                vAll.setTextFill(Color.web(PRIMARY));
                vAll.setFont(Font.font("System", FontWeight.BOLD, 12));
                carouselHeader.getChildren().addAll(hTitle, sp, vAll);

                HBox carousel = new HBox(16);
                carousel.setPadding(new Insets(0, 16, 0, 16));
                carousel.getChildren().addAll(
                                createHighlightCard("The Nairobi Hospital", "4.8", "0.5 km \u2022 Open 24 Hours",
                                                "HOSPITAL", "#ef4444",
                                                "View Medical Services"),
                                createHighlightCard("Nairobi Arboretum", "4.6", "1.2 km \u2022 Closes 6 PM", "PARK",
                                                "#22c55e",
                                                "Get Directions"),
                                createHighlightCard("Kileleshwa Academy", "4.9", "0.3 km \u2022 International",
                                                "SCHOOL", PRIMARY,
                                                "Inquire Enrollment"));
                ScrollPane carScroll = new ScrollPane(carousel);
                carScroll.setHbarPolicy(ScrollBarPolicy.NEVER);
                carScroll.setVbarPolicy(ScrollBarPolicy.NEVER);
                carScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                bottomContainer.getChildren().addAll(carouselHeader, carScroll);
                uiLayer.setBottom(bottomContainer);

                getChildren().addAll(mapLayer, uiLayer);

                // Simulated Bottom Nav (Visual Only since we are in a sub-view)
                HBox nav = new HBox();
                nav.setAlignment(Pos.CENTER);
                nav.setPadding(new Insets(10));
                nav.setSpacing(40);
                nav.setStyle("-fx-background-color: " + GLASS_BG + "; -fx-border-color: " + DIVIDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");
                nav.getChildren().addAll(
                                createNavItem("\ud83e\udded", "Explore", true),
                                createNavItem("\u2665", "Saved", false),
                                createNavItem("\ud83d\udd14", "Alerts", false),
                                createNavItem("\ud83d\udc64", "Profile", false));
                StackPane.setAlignment(nav, Pos.BOTTOM_CENTER);
                getChildren().add(nav);
        }

        private VBox createMapPin(double x, double y, String colorHex, String iconStr) {
                VBox pin = new VBox(0);
                pin.setAlignment(Pos.CENTER);

                StackPane bubble = new StackPane();
                bubble.setPrefSize(32, 32);
                bubble.setStyle("-fx-background-color: " + colorHex
                                + "; -fx-background-radius: 16; -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 16; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 5, 0, 0, 2);");
                Label ic = new Label(iconStr);
                ic.setTextFill(Color.WHITE);
                bubble.getChildren().add(ic);

                Rectangle stick = new Rectangle(4, 12, Color.web(colorHex));

                pin.getChildren().addAll(bubble, stick);
                pin.setTranslateX(x);
                pin.setTranslateY(y);
                return pin;
        }

        private HBox createFilterChip(String text, String iconStr, boolean active, String colorHex) {
                HBox chip = new HBox(6);
                chip.setAlignment(Pos.CENTER);
                chip.setPadding(new Insets(8, 16, 8, 16));
                if (active) {
                        chip.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 12;");
                } else {
                        chip.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 12;");
                }

                Label icon = new Label(iconStr);
                icon.setTextFill(active ? Color.WHITE : Color.web(colorHex));
                icon.setFont(Font.font(16));

                Label lbl = new Label(text);
                lbl.setTextFill(Color.WHITE);
                lbl.setFont(Font.font("System", FontWeight.BOLD, 12));

                chip.getChildren().addAll(icon, lbl);
                return chip;
        }

        private Button createGlassIconBtn(String text) {
                Button b = new Button(text);
                b.setPrefSize(40, 40);
                b.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.9); -fx-text-fill: #0a0f18; -fx-font-size: 18; -fx-background-radius: 12; -fx-cursor: hand; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);");
                return b;
        }

        private VBox createHighlightCard(String title, String rating, String sub, String tag, String colorHex,
                        String btnText) {
                VBox card = new VBox(0);
                card.setPrefWidth(260);
                card.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 16; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 4);");

                StackPane imgBox = new StackPane();
                imgBox.setPrefHeight(120);
                Rectangle bg = new Rectangle(260, 120, Color.web("#2d3748"));
                bg.setArcWidth(16);
                bg.setArcHeight(16);
                // Clip bottom corners specifically if needed, simplified here

                Label tagLbl = new Label(tag);
                tagLbl.setTextFill(Color.web(colorHex));
                tagLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
                tagLbl.setStyle("-fx-background-color: rgba(255,255,255,0.9); -fx-padding: 2 6; -fx-background-radius: 4;");
                StackPane.setAlignment(tagLbl, Pos.TOP_RIGHT);
                StackPane.setMargin(tagLbl, new Insets(8));

                imgBox.getChildren().addAll(bg, tagLbl);

                VBox content = new VBox(8);
                content.setPadding(new Insets(12));

                HBox head = new HBox();
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 14));
                Region sp = new Region();
                HBox.setHgrow(sp, Priority.ALWAYS);
                Label rate = new Label("\u2605 " + rating);
                rate.setTextFill(Color.ORANGE);
                rate.setStyle("-fx-background-color: rgba(255, 165, 0, 0.1); -fx-padding: 2 4; -fx-background-radius: 4;");
                head.getChildren().addAll(t, sp, rate);

                Label s = new Label(sub);
                s.setTextFill(Color.web(TEXT_GRAY));
                s.setFont(Font.font(11));

                Button action = new Button(btnText);
                action.setMaxWidth(Double.MAX_VALUE);
                action.setStyle("-fx-background-color: rgba(19, 236, 91, 0.15); -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-background-radius: 8;");
                if (colorHex.equals("#ef4444"))
                        action.setStyle(action.getStyle().replace(PRIMARY, "#ef4444").replace("0.15", "0.1"));

                content.getChildren().addAll(head, s, action);

                card.getChildren().addAll(imgBox, content);
                return card;
        }

        private VBox createNavItem(String icon, String text, boolean active) {
                VBox item = new VBox(2);
                item.setAlignment(Pos.CENTER);
                Label ic = new Label(icon);
                ic.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                ic.setFont(Font.font(20));
                Label tx = new Label(text);
                tx.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                tx.setFont(Font.font("System", FontWeight.BOLD, 10));
                item.getChildren().addAll(ic, tx);
                return item;
        }
}
