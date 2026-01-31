package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon; // Correct Polygon import
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WardMapView extends StackPane {

        private static final String BACKGROUND_DARK = "#0b0e14"; // Deeper dark for map base
        private static final String PRIMARY = "#135bec";
        private static final String TEXT_GRAY = "#9da6b9";

        public WardMapView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Base Map Layer (Simulated with Image)
                StackPane mapLayer = new StackPane();
                try {
                        ImageView mapImg = new ImageView(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuB40XT75MK1VHCOIh7MNeHPv9p9rDTCMSDg-ZSKymvx9w41OUh9o9n1E3P4BqCYUb0Lwg5Lgqm__c2rRHViDbfniEuBJp9MipllX5WIZzOYCJCExWt7xL-2wO9m-U7MLIxgqKzhvqEx2KnWG4AoPDN1UNwiAolvNrNQnTCyCoyI1sz88fFv5bNuqk5D6a0HHrE3BR4puNZZxh6WVcDcLKWSIVJhA54xik0FW21Lqhs4U3la3ETEYyynELmydP38OJuJi2A-ZZygSg4",
                                        500, 1000, true, true));
                        mapImg.setOpacity(0.6);
                        mapLayer.getChildren().add(mapImg);
                } catch (Exception e) {
                }

                // Mock SVG Overlays (Using JavaFX Shapes)
                Pane vectorLayer = new Pane();
                vectorLayer.setPickOnBounds(false);

                // Kilimani Overlay (Mock Polygon)
                Polygon kilimaniPoly = new Polygon();
                kilimaniPoly.getPoints().addAll(new Double[] {
                                120.0, 320.0,
                                240.0, 280.0,
                                280.0, 380.0,
                                180.0, 450.0,
                                100.0, 400.0
                });
                kilimaniPoly.setFill(Color.web(PRIMARY, 0.3));
                kilimaniPoly.setStroke(Color.web(PRIMARY));
                kilimaniPoly.setStrokeWidth(3);

                // Property Pins
                vectorLayer.getChildren().add(kilimaniPoly);
                vectorLayer.getChildren().add(createPricePin(180, 360, "KSh 18M"));
                vectorLayer.getChildren().add(createPricePin(140, 410, "KSh 12.5M"));
                vectorLayer.getChildren().add(createPricePin(80, 240, "KSh 25M")); // Westlands area

                mapLayer.getChildren().add(vectorLayer);

                // UI Layer
                AnchorPane uiLayer = new AnchorPane();
                uiLayer.setPickOnBounds(false);

                // Top Floating Bar
                HBox topBar = new HBox(10);
                topBar.setAlignment(Pos.CENTER_LEFT);
                topBar.setPadding(new Insets(8, 12, 8, 8));
                topBar.setStyle(
                                "-fx-background-color: rgba(28, 31, 39, 0.8); -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5); -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 12;");

                StackPane backBtn = new StackPane(new Label("\u2039")); // Chevron left
                backBtn.getChildren().get(0).setStyle("-fx-text-fill: white; -fx-font-size: 24;");
                backBtn.setPrefSize(40, 40);
                backBtn.setStyle("-fx-background-radius: 8; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new WardLocationView())); // Back to list

                VBox titleBox = new VBox(0);
                Label t1 = new Label("Nairobi Wards");
                t1.setTextFill(Color.WHITE);
                t1.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label t2 = new Label("BOUNDARY VIEW");
                t2.setTextFill(Color.web(TEXT_GRAY));
                t2.setFont(Font.font("System", FontWeight.NORMAL, 10));
                titleBox.getChildren().addAll(t1, t2);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button layersBtn = createMapBtn("\u2630"); // Layers icon
                layersBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18;");
                layersBtn.setOnAction(e -> MainApp.navigateTo(new AreaDensityHeatmapView()));

                topBar.getChildren().addAll(backBtn, titleBox, spacer, layersBtn);

                AnchorPane.setTopAnchor(topBar, 20.0);
                AnchorPane.setLeftAnchor(topBar, 16.0);
                AnchorPane.setRightAnchor(topBar, 16.0);

                // Search Bar
                HBox searchBar = new HBox(10);
                searchBar.setAlignment(Pos.CENTER_LEFT);
                searchBar.setPadding(new Insets(0, 15, 0, 15));
                searchBar.setStyle(
                                "-fx-background-color: rgba(28, 31, 39, 0.8); -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 12;");
                searchBar.setPrefHeight(48);

                Label searchIcon = new Label("\ud83d\udd0d");
                searchIcon.setTextFill(Color.web(TEXT_GRAY));
                TextField searchInput = new TextField();
                searchInput.setPromptText("Search Westlands, Kilimani...");
                searchInput.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: "
                                                + TEXT_GRAY + ";");
                HBox.setHgrow(searchInput, Priority.ALWAYS);
                searchBar.getChildren().addAll(searchIcon, searchInput);

                AnchorPane.setTopAnchor(searchBar, 85.0);
                AnchorPane.setLeftAnchor(searchBar, 16.0);
                AnchorPane.setRightAnchor(searchBar, 16.0);

                // Chips
                HBox chips = new HBox(8);
                chips.getChildren().addAll(
                                createChip("Wards \u2714", true),
                                createChip("Sub-Counties \u25bc", false),
                                createChip("Price \u25bc", false));
                AnchorPane.setTopAnchor(chips, 145.0);
                AnchorPane.setLeftAnchor(chips, 16.0);

                // Map Controls
                VBox controls = new VBox(8);
                Button zoomIn = createMapBtn("+");
                Button zoomOut = createMapBtn("-");
                VBox zoomGroup = new VBox(0, zoomIn, zoomOut);
                zoomGroup.setStyle(
                                "-fx-background-color: rgba(28, 31, 39, 0.8); -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 12;");

                Button locateBtn = createMapBtn("\u2316"); // My location
                controls.getChildren().addAll(zoomGroup, locateBtn);

                AnchorPane.setRightAnchor(controls, 16.0);
                AnchorPane.setBottomAnchor(controls, 300.0);

                // Ward Info Card
                HBox wardCard = new HBox(15);
                wardCard.setPadding(new Insets(15));
                wardCard.setStyle(
                                "-fx-background-color: rgba(28, 31, 39, 0.9); -fx-background-radius: 16; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 20, 0, 0, 10); -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 16;");

                VBox cardInfo = new VBox(5);
                HBox activeBadge = new HBox(5);
                activeBadge.setAlignment(Pos.CENTER_LEFT);
                Circle pulse = new Circle(3, Color.web(PRIMARY));
                Label badgeText = new Label("ACTIVE SELECTION");
                badgeText.setTextFill(Color.web(PRIMARY));
                badgeText.setFont(Font.font("System", FontWeight.BOLD, 10));
                activeBadge.getChildren().addAll(badgeText, pulse);

                Label wardName = new Label("Kilimani Ward");
                wardName.setTextFill(Color.WHITE);
                wardName.setFont(Font.font("System", FontWeight.BOLD, 18));

                Label subLoc = new Label("\ud83c\udfd9 Dagoretti North Sub-County"); // city icon
                subLoc.setTextFill(Color.web(TEXT_GRAY));
                subLoc.setFont(Font.font(12));

                Label stat = new Label("156 Properties Available");
                stat.setTextFill(Color.web(PRIMARY));
                stat.setFont(Font.font("System", FontWeight.BOLD, 14));

                Button viewListings = new Button("View Listings \u2192");
                viewListings.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

                cardInfo.getChildren().addAll(activeBadge, wardName, subLoc, stat, viewListings);

                StackPane imgPreview = new StackPane();
                try {
                        ImageView wImg = new ImageView(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuBAkfNJUdNxarsRuZ6SFr1kvOpbyxXdpKKJP_s6ec_-VNPmLaXWz80AEH_RMSKRrsQYeoDjRTLuIaQIi4UL7BiNT2PVydBAWJYDPQcNvPy2F5FwTwE-RveMRIYF4AwaQPgs5M0zTMcQUAxlokHWkIQof867xT7znYYw0MPcSE3vl-jp5BOYg1mvT8u2UTJraAFAXhk9xTDiwOT8fYiqhBAQPgI7VVzzgBgxGR3XfziMMGMrwL8H5oRVPjcdaDsICHa97li1y0wQ8jQ",
                                        100, 100, true, true));
                        Rectangle clip = new Rectangle(100, 100);
                        clip.setArcWidth(12);
                        clip.setArcHeight(12);
                        wImg.setClip(clip);
                        imgPreview.getChildren().add(wImg);
                } catch (Exception e) {
                }

                wardCard.getChildren().addAll(cardInfo, imgPreview);

                AnchorPane.setBottomAnchor(wardCard, 90.0);
                AnchorPane.setLeftAnchor(wardCard, 16.0);
                AnchorPane.setRightAnchor(wardCard, 16.0);

                // Insights Button
                Button insightsBtn = new Button("\u2139 Area Insights");
                insightsBtn.setStyle(
                                "-fx-background-color: white; -fx-text-fill: #101622; -fx-font-weight: bold; -fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
                insightsBtn.setPrefHeight(40);
                insightsBtn.setOnAction(e -> MainApp.navigateTo(new GuideView()));

                HBox insightsContainer = new HBox(insightsBtn);
                insightsContainer.setAlignment(Pos.CENTER);
                AnchorPane.setBottomAnchor(insightsContainer, 25.0); // Above nav bar (simulated)
                AnchorPane.setLeftAnchor(insightsContainer, 0.0);
                AnchorPane.setRightAnchor(insightsContainer, 0.0);

                uiLayer.getChildren().addAll(topBar, searchBar, chips, controls, wardCard, insightsContainer);

                getChildren().addAll(mapLayer, uiLayer);
        }

        private StackPane createPricePin(double x, double y, String price) {
                StackPane pin = new StackPane();
                Label lbl = new Label(price);
                lbl.setStyle(
                                "-fx-background-color: white; -fx-text-fill: #101622; -fx-font-weight: bold; -fx-font-size: 10; -fx-padding: 3 6; -fx-background-radius: 6; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);");
                pin.getChildren().add(lbl);
                pin.setTranslateX(x);
                pin.setTranslateY(y);
                return pin;
        }

        private Button createChip(String text, boolean active) {
                Button b = new Button(text);
                if (active) {
                        b.setStyle("-fx-background-color: " + PRIMARY
                                        + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15; -fx-font-size: 11;");
                } else {
                        b.setStyle(
                                        "-fx-background-color: rgba(16, 22, 34, 0.8); -fx-text-fill: white; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 15; -fx-background-radius: 15; -fx-font-size: 11;");
                }
                return b;
        }

        private Button createMapBtn(String text) {
                Button b = new Button(text);
                b.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18;");
                b.setPrefSize(40, 40);
                return b;
        }
}
