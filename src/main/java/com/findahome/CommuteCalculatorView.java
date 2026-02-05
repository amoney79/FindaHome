package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CommuteCalculatorView extends BorderPane {

        private static final String BACKGROUND_DARK = "#221610";
        private static final String PRIMARY = "#f46a25";
        private static final String CARD_BG = "#2d1e17";
        private static final String TEXT_GRAY = "#8a6e60";

        public CommuteCalculatorView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Header
                HBox header = new HBox(0);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

                Button backBtn = new Button("\u276E");
                backBtn.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18; -fx-cursor: hand;");
                backBtn.setOnAction(e -> MainApp.showHome());

                Label title = new Label("Commute Analysis");
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setTextFill(Color.WHITE);
                HBox.setHgrow(title, Priority.ALWAYS);
                title.setAlignment(Pos.CENTER);

                header.getChildren().addAll(backBtn, title, new Region() {
                        {
                                setPrefWidth(40);
                        }
                });
                setTop(header);

                // Scroll Content
                VBox content = new VBox(20);
                content.setPadding(new Insets(20, 20, 40, 20));
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // 1. Locations
                VBox locationCard = new VBox(16);
                locationCard.setPadding(new Insets(16));
                locationCard.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05);");

                locationCard.getChildren().addAll(
                                createLocationRow("\u2302", "PROPERTY LOCATION", "123 Kilimani Road, Nairobi", true),
                                createLocationRow("\uD83D\uDCCD", "DESTINATION", "Two Rivers Mall, Limuru Road",
                                                false));

                // 2. Map
                StackPane mapBox = new StackPane();
                mapBox.setPrefHeight(240);
                mapBox.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.05); -fx-overflow: hidden;");

                try {
                        ImageView mv = new ImageView(new Image(
                                        "https://images.unsplash.com/photo-1526778548025-fa2f459cd5c1?w=600&auto=format&fit=crop",
                                        600, 240, false, true, true));
                        mv.setFitWidth(400);
                        mv.setFitHeight(240);
                        mv.setPreserveRatio(false);
                        javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(400, 240);
                        clip.setArcWidth(32);
                        clip.setArcHeight(32);
                        mv.setClip(clip);
                        mapBox.getChildren().add(mv);
                } catch (Exception e) {
                }

                // Markers
                StackPane markers = new StackPane();
                markers.getChildren().addAll(
                                createMarker("HOME", Pos.TOP_LEFT, new Insets(40, 0, 0, 80), PRIMARY),
                                createMarker("WORK", Pos.BOTTOM_RIGHT, new Insets(0, 80, 40, 0), "#ffffff"));
                mapBox.getChildren().add(markers);

                // 3. Traffic Toggle
                HBox trafficToggle = new HBox(4);
                trafficToggle.setPadding(new Insets(4));
                trafficToggle.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12;");
                Button peak = createTrafficBtn("Peak Traffic", true);
                Button offPeak = createTrafficBtn("Off-Peak", false);
                HBox.setHgrow(peak, Priority.ALWAYS);
                HBox.setHgrow(offPeak, Priority.ALWAYS);
                trafficToggle.getChildren().addAll(offPeak, peak);

                // 4. Results
                VBox transportList = new VBox(12);
                transportList.getChildren().addAll(
                                createResultCard("\uD83D\uDE97", "Driving", "Fastest route via bypass", "25 mins",
                                                true),
                                createResultCard("\uD83D\uDE8C", "Matatu", "Stage: Kilimani Mall", "45 mins", false));

                content.getChildren().addAll(locationCard, mapBox, trafficToggle, transportList);

                ScrollPane scroll = new ScrollPane(content);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
                setCenter(scroll);

                // Footer Action (Pinned)
                VBox footer = new VBox();
                footer.setPadding(new Insets(20, 20, 35, 20));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");

                Button saveBtn = new Button("\uD83D\uDD16  Save Route to Profile");
                saveBtn.setMaxWidth(Double.MAX_VALUE);
                saveBtn.setPrefHeight(56);
                saveBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
                saveBtn.setOnAction(e -> MainApp.showHome());

                footer.getChildren().add(saveBtn);
                setBottom(footer);
        }

        private HBox createLocationRow(String icon, String label, String value, boolean hasLine) {
                HBox row = new HBox(12);
                VBox icBox = new VBox(4);
                icBox.setAlignment(Pos.TOP_CENTER);
                Label ic = new Label(icon) {
                        {
                                setTextFill(Color.web(PRIMARY));
                                setFont(Font.font(18));
                        }
                };
                icBox.getChildren().add(ic);
                if (hasLine)
                        icBox.getChildren().add(new Region() {
                                {
                                        setPrefHeight(20);
                                        setPrefWidth(2);
                                        setStyle("-fx-background-color: rgba(255,255,255,0.1);");
                                }
                        });

                VBox tx = new VBox(2, new Label(label) {
                        {
                                setTextFill(Color.web(TEXT_GRAY));
                                setFont(Font.font("System", FontWeight.BOLD, 10));
                        }
                }, new Label(value) {
                        {
                                setTextFill(Color.WHITE);
                                setFont(Font.font(14));
                        }
                });
                row.getChildren().addAll(icBox, tx);
                return row;
        }

        private VBox createMarker(String text, Pos pos, Insets margin, String colorStr) {
                VBox m = new VBox(2);
                m.setAlignment(Pos.CENTER);
                m.getChildren().addAll(
                                new Label(text) {
                                        {
                                                setStyle("-fx-background-color: " + CARD_BG
                                                                + "; -fx-text-fill: white; -fx-font-size: 9; -fx-padding: 2 6; -fx-background-radius: 4;");
                                        }
                                },
                                new Label("\uD83D\uDCCD") {
                                        {
                                                setTextFill(Color.web(colorStr));
                                                setFont(Font.font(20));
                                        }
                                });
                StackPane.setAlignment(m, pos);
                StackPane.setMargin(m, margin);
                return m;
        }

        private Button createTrafficBtn(String text, boolean active) {
                Button b = new Button(text);
                b.setMaxWidth(Double.MAX_VALUE);
                b.setPrefHeight(40);
                b.setStyle("-fx-background-color: " + (active ? PRIMARY : "transparent")
                                + "; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-weight: bold;");
                return b;
        }

        private HBox createResultCard(String icon, String mode, String detail, String time, boolean best) {
                HBox card = new HBox(15);
                card.setPadding(new Insets(16));
                card.setAlignment(Pos.CENTER_LEFT);
                card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + (best ? PRIMARY + "40" : "rgba(255,255,255,0.05)") + ";");

                StackPane ic = new StackPane(new Label(icon) {
                        {
                                setFont(Font.font(20));
                                setTextFill(best ? Color.web(PRIMARY) : Color.WHITE);
                        }
                });
                ic.setPrefSize(40, 40);
                ic.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 20;");

                VBox tx = new VBox(2, new Label(mode) {
                        {
                                setTextFill(Color.WHITE);
                                setFont(Font.font("System", FontWeight.BOLD, 14));
                        }
                }, new Label(detail) {
                        {
                                setTextFill(Color.web(TEXT_GRAY));
                                setFont(Font.font(12));
                        }
                });
                HBox.setHgrow(tx, Priority.ALWAYS);

                Label t = new Label(time) {
                        {
                                setTextFill(best ? Color.web(PRIMARY) : Color.WHITE);
                                setFont(Font.font("System", FontWeight.BOLD, 16));
                        }
                };

                card.getChildren().addAll(ic, tx, t);
                return card;
        }
}
