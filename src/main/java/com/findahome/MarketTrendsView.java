package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MarketTrendsView extends BorderPane {

        private static final String BACKGROUND_DARK = "#221610";
        private static final String PRIMARY = "#f46a25";
        private static final String CARD_BG = "#2d1e17";
        private static final String TEXT_GRAY = "#8a6e60";

        public MarketTrendsView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // --- Header ---
                HBox header = new HBox(0);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

                HBox leftSection = new HBox(8);
                leftSection.setAlignment(Pos.CENTER_LEFT);

                Button backBtn = new Button("\u276E"); // arrow_back_ios
                backBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-size: 18; -fx-cursor: hand;");
                backBtn.setOnAction(e -> MainApp.showHome());

                Label title = new Label("Westlands Market");
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setTextFill(Color.WHITE);

                leftSection.getChildren().addAll(backBtn, title);
                HBox.setHgrow(leftSection, Priority.ALWAYS);

                Button shareBtn = new Button("\ud83d\udce4"); // share emoji
                shareBtn.setStyle("-fx-background-color: rgba(244,106,37,0.1); -fx-text-fill: " + PRIMARY
                                + "; -fx-background-radius: 20; -fx-font-size: 18; -fx-padding: 8; -fx-cursor: hand;");

                header.getChildren().addAll(leftSection, shareBtn);
                setTop(header);

                // --- Main Content ---
                VBox content = new VBox(0);
                content.setPadding(new Insets(0, 0, 30, 0));

                // 1. Tabs for Bedroom Types
                HBox tabs = new HBox(24);
                tabs.setPadding(new Insets(0, 16, 0, 16));
                tabs.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

                tabs.getChildren().addAll(
                                createTab("1 Bed", true),
                                createTab("2 Bed", false),
                                createTab("3 Bed", false));

                // 2. Main Trend Chart Section
                VBox chartSection = new VBox(16);
                chartSection.setPadding(new Insets(16));
                chartSection.setStyle("-fx-background-color: rgba(45,30,23,0.3);");

                VBox priceHeader = new VBox(4);
                Label priceLabel = new Label("Average Monthly Rent (KES)");
                priceLabel.setTextFill(Color.web(TEXT_GRAY));
                priceLabel.setFont(Font.font("System", FontWeight.MEDIUM, 14));

                HBox priceRow = new HBox(8);
                priceRow.setAlignment(Pos.BASELINE_LEFT);
                Label priceValue = new Label("68,500");
                priceValue.setTextFill(Color.WHITE);
                priceValue.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 32));

                HBox changeIndicator = new HBox(2);
                changeIndicator.setAlignment(Pos.CENTER);
                Label upArrow = new Label("\u2191");
                upArrow.setTextFill(Color.web("#07880b"));
                upArrow.setFont(Font.font(14));
                Label changePercent = new Label("+5.2%");
                changePercent.setTextFill(Color.web("#07880b"));
                changePercent.setFont(Font.font("System", FontWeight.BOLD, 14));
                changeIndicator.getChildren().addAll(upArrow, changePercent);

                priceRow.getChildren().addAll(priceValue, changeIndicator);

                Label priceSubtext = new Label("Last 12 months in Westlands Ward");
                priceSubtext.setTextFill(Color.web(TEXT_GRAY));
                priceSubtext.setFont(Font.font(12));

                priceHeader.getChildren().addAll(priceLabel, priceRow, priceSubtext);

                VBox chartContainer = new VBox(16);
                chartContainer.setPadding(new Insets(24, 0, 0, 0));

                Pane chartCanvas = new Pane();
                chartCanvas.setPrefHeight(120);
                SVGPath chartPath = new SVGPath();
                chartPath.setContent(
                                "M0 100C30 100 50 20 100 20C150 20 200 80 250 80C300 80 350 40 400 40C450 40 478 10 478 10");
                chartPath.setStroke(Color.web(PRIMARY));
                chartPath.setStrokeWidth(3);
                chartPath.setFill(Color.TRANSPARENT);
                chartCanvas.getChildren().add(chartPath);

                HBox monthLabels = new HBox();
                monthLabels.setAlignment(Pos.CENTER);
                monthLabels.getChildren().addAll(
                                createMonthLabel("JAN"), createSpacer(),
                                createMonthLabel("APR"), createSpacer(),
                                createMonthLabel("JUL"), createSpacer(),
                                createMonthLabel("OCT"), createSpacer(),
                                createMonthLabel("DEC"));

                chartContainer.getChildren().addAll(chartCanvas, monthLabels);
                chartSection.getChildren().addAll(priceHeader, chartContainer);

                // 3. Market Verdict Card
                VBox verdictSection = new VBox();
                verdictSection.setPadding(new Insets(16));
                HBox verdictCard = createCard(
                                new VBox(8,
                                                new HBox(6, new Label("\u2191") {
                                                        {
                                                                setTextFill(Color.web(PRIMARY));
                                                        }
                                                }, new Label("MARKET VERDICT") {
                                                        {
                                                                setTextFill(Color.web(PRIMARY));
                                                                setFont(Font.font("System", FontWeight.BOLD, 10));
                                                        }
                                                }),
                                                new Label("Rent is rising") {
                                                        {
                                                                setTextFill(Color.WHITE);
                                                                setFont(Font.font("System", FontWeight.BOLD, 18));
                                                        }
                                                },
                                                new Label("Demand in Westlands is outpacing inventory.") {
                                                        {
                                                                setTextFill(Color.web(TEXT_GRAY));
                                                                setFont(Font.font(14));
                                                                setWrapText(true);
                                                        }
                                                }),
                                new VBox(2, new Label("+5.2%") {
                                        {
                                                setTextFill(Color.web(PRIMARY));
                                                setFont(Font.font("System", FontWeight.BLACK, 24));
                                        }
                                }, new Label("GROWTH") {
                                        {
                                                setTextFill(Color.web(PRIMARY));
                                                setFont(Font.font("System", FontWeight.BOLD, 10));
                                        }
                                }) {
                                        {
                                                setAlignment(Pos.CENTER);
                                                setPadding(new Insets(12));
                                                setStyle("-fx-background-color: rgba(244,106,37,0.1); -fx-background-radius: 8;");
                                        }
                                });
                verdictSection.getChildren().add(verdictCard);

                // 4. Distribution
                VBox distributionSection = new VBox(16);
                distributionSection.setPadding(new Insets(16));
                distributionSection.getChildren().addAll(
                                new Label("Price Distribution (KES)") {
                                        {
                                                setTextFill(Color.WHITE);
                                                setFont(Font.font("System", FontWeight.BOLD, 18));
                                        }
                                },
                                new VBox(12, createDistBar("40k - 55k", 0.15), createDistBar("55k - 70k", 0.62),
                                                createDistBar("70k+", 0.23)));

                content.getChildren().addAll(tabs, chartSection, verdictSection, distributionSection);

                ScrollPane scrollPane = new ScrollPane(content);
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle(
                                "-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
                setCenter(scrollPane);

                // --- Bottom Navigation (Pinned) ---
                HBox bottomNav = new HBox();
                bottomNav.setPadding(new Insets(12, 24, 32, 24));
                bottomNav.setAlignment(Pos.CENTER);
                bottomNav.setStyle("-fx-background-color: " + BACKGROUND_DARK
                                + "f0; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");

                bottomNav.getChildren().addAll(
                                createNavItem("\uD83D\uDD0D", "Explore", false), createSpacer(),
                                createNavItem("\uD83D\uDCC8", "Trends", true), createSpacer(),
                                createNavItem("\u2665", "Saved", false), createSpacer(),
                                createNavItem("\uD83D\uDC64", "Profile", false));
                setBottom(bottomNav);
        }

        private Region createSpacer() {
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                return spacer;
        }

        private VBox createTab(String text, boolean active) {
                VBox tab = new VBox(8);
                tab.setAlignment(Pos.CENTER);
                tab.setPadding(new Insets(12, 0, 12, 0));
                tab.setStyle("-fx-border-color: " + (active ? PRIMARY : "transparent")
                                + "; -fx-border-width: 0 0 3 0; -fx-cursor: hand;");
                Label label = new Label(text);
                label.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                label.setFont(Font.font("System", FontWeight.BOLD, 14));
                tab.getChildren().add(label);
                return tab;
        }

        private Label createMonthLabel(String month) {
                return new Label(month) {
                        {
                                setTextFill(Color.web(TEXT_GRAY));
                                setFont(Font.font("System", FontWeight.BOLD, 11));
                        }
                };
        }

        private HBox createCard(Node left, Node right) {
                HBox card = new HBox(16, left, right);
                card.setPadding(new Insets(16));
                card.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05);");
                HBox.setHgrow(left, Priority.ALWAYS);
                return card;
        }

        private VBox createDistBar(String range, double width) {
                VBox bar = new VBox(4);
                HBox labels = new HBox(new Label(range) {
                        {
                                setTextFill(Color.web(TEXT_GRAY));
                        }
                }, new Region() {
                        {
                                HBox.setHgrow(this, Priority.ALWAYS);
                        }
                }, new Label((int) (width * 100) + "%") {
                        {
                                setTextFill(Color.web(TEXT_GRAY));
                        }
                });
                StackPane track = new StackPane(new Region() {
                        {
                                setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 4;");
                                setPrefHeight(8);
                        }
                }, new Region() {
                        {
                                setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 4;");
                                setPrefHeight(8);
                                setMaxWidth(400 * width);
                                StackPane.setAlignment(this, Pos.CENTER_LEFT);
                        }
                });
                bar.getChildren().addAll(labels, track);
                return bar;
        }

        private VBox createNavItem(String icon, String label, boolean active) {
                VBox v = new VBox(4);
                v.setAlignment(Pos.CENTER);
                v.setCursor(javafx.scene.Cursor.HAND);
                Label i = new Label(icon) {
                        {
                                setFont(Font.font(20));
                                setTextFill(active ? Color.web(PRIMARY) : Color.GRAY);
                        }
                };
                Label l = new Label(label) {
                        {
                                setFont(Font.font(10));
                                setTextFill(active ? Color.web(PRIMARY) : Color.GRAY);
                        }
                };
                v.getChildren().addAll(i, l);

                if (label.equals("Explore"))
                        v.setOnMouseClicked(e -> MainApp.showHome());
                if (label.equals("Profile"))
                        v.setOnMouseClicked(e -> MainApp.navigateCached("profile", TenantProfileView::new));
                if (label.equals("Saved"))
                        v.setOnMouseClicked(e -> MainApp.navigateCachedFullScreen("saved_properties",
                                        SavedPropertiesView::new));

                return v;
        }
}
