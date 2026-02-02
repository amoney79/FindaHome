package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle; // Added Rectangle import
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NeighborhoodAlertsView extends StackPane {

        private static final String BACKGROUND_DARK = "#101622";
        private static final String PRIMARY = "#13ec5b"; // Updated to green theme
        private static final String TEXT_GRAY = "#9da6b9";
        private static final String CARD_BG = "#1c222c";
        private static final String DIVIDER_COLOR = "#2a3544";

        public NeighborhoodAlertsView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Main Layout
                BorderPane mainLayout = new BorderPane();
                mainLayout.setPadding(new Insets(0, 0, 80, 0)); // Padding for fixed footer

                // Header
                VBox header = new VBox();
                header.setStyle(
                                "-fx-background-color: rgba(16, 22, 34, 0.8); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 0, 5, 0, 0);");
                header.setPadding(new Insets(15, 20, 15, 20));

                HBox navBar = new HBox(15);
                navBar.setAlignment(Pos.CENTER_LEFT);

                Label backBtn = new Label("\u2039"); // Arrow back
                backBtn.setFont(Font.font("System", FontWeight.BOLD, 24));
                backBtn.setTextFill(Color.WHITE);
                backBtn.setCursor(javafx.scene.Cursor.HAND);
                backBtn.setOnMouseClicked(e -> MainApp.showHome()); // Or prev view

                Label title = new Label("Set Neighborhood Alerts");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setMaxWidth(Double.MAX_VALUE);
                title.setAlignment(Pos.CENTER);
                HBox.setHgrow(title, Priority.ALWAYS);
                HBox.setMargin(title, new Insets(0, 24, 0, 0));

                navBar.getChildren().addAll(backBtn, title);
                header.getChildren().add(navBar);
                mainLayout.setTop(header);

                // Scroll Content
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                VBox content = new VBox(24);
                content.setPadding(new Insets(20));
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // 1. Target Neighborhoods
                VBox section1 = new VBox(15);
                Label s1Title = new Label("Target Neighborhoods");
                s1Title.setTextFill(Color.WHITE);
                s1Title.setFont(Font.font("System", FontWeight.BOLD, 16));

                HBox searchBox = new HBox(10);
                searchBox.setAlignment(Pos.CENTER_LEFT);
                searchBox.setPadding(new Insets(12));
                searchBox.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 8;");
                Label searchIcon = new Label("\ud83d\udd0d");
                searchIcon.setTextFill(Color.web(TEXT_GRAY));
                TextField searchInput = new TextField();
                searchInput.setPromptText("Search Wards (e.g. Kileleshwa)");
                searchInput.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: "
                                                + TEXT_GRAY + ";");
                searchInput.setPrefWidth(200);
                HBox.setHgrow(searchInput, Priority.ALWAYS);
                searchBox.getChildren().addAll(searchIcon, searchInput);

                FlowPane chips = new FlowPane(10, 10);
                chips.getChildren().addAll(
                                createChip("Kileleshwa", true),
                                createChip("Bamburi", true),
                                createChip("Tudor", true),
                                createAddChip());

                section1.getChildren().addAll(s1Title, searchBox, chips);

                // Divider
                Separator sep1 = new Separator();
                sep1.setStyle("-fx-background-color: " + DIVIDER_COLOR + ";");

                // 2. Property Criteria
                VBox section2 = new VBox(20);
                Label s2Title = new Label("Property Criteria");
                s2Title.setTextFill(Color.WHITE);
                s2Title.setFont(Font.font("System", FontWeight.BOLD, 16));

                VBox propTypeBox = new VBox(8);
                Label lType = new Label("Property Type");
                lType.setTextFill(Color.web(TEXT_GRAY));
                ComboBox<String> typeCombo = new ComboBox<>();
                typeCombo.getItems().addAll("2-bedroom Apartment", "3-bedroom Apartment", "Standalone House",
                                "Studio / Bedsitter");
                typeCombo.setValue("2-bedroom Apartment");
                typeCombo.setMaxWidth(Double.MAX_VALUE);
                typeCombo.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-text-fill: white; -fx-font-size: 14; -fx-background-radius: 8;");
                propTypeBox.getChildren().addAll(lType, typeCombo);

                VBox priceBox = new VBox(12);
                HBox pHeader = new HBox();
                Label lPrice = new Label("Price Range (KES)");
                lPrice.setTextFill(Color.web(TEXT_GRAY));
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                Label pVal = new Label("45k - 120k");
                pVal.setTextFill(Color.web(PRIMARY));
                pVal.setFont(Font.font("System", FontWeight.BOLD, 14));
                pHeader.getChildren().addAll(lPrice, spacer, pVal);

                Slider priceSlider = new Slider(10000, 500000, 80000);
                priceSlider.setStyle("-fx-control-inner-background: " + DIVIDER_COLOR + ";");

                HBox pRot = new HBox();
                Label pMin = new Label("Min: 10k");
                pMin.setTextFill(Color.web(TEXT_GRAY, 0.5));
                pMin.setFont(Font.font(10));
                Region sp2 = new Region();
                HBox.setHgrow(sp2, Priority.ALWAYS);
                Label pMax = new Label("Max: 500k+");
                pMax.setTextFill(Color.web(TEXT_GRAY, 0.5));
                pMax.setFont(Font.font(10));
                pRot.getChildren().addAll(pMin, sp2, pMax);

                priceBox.getChildren().addAll(pHeader, priceSlider, pRot);

                section2.getChildren().addAll(s2Title, propTypeBox, priceBox);

                // Divider
                Separator sep2 = new Separator();
                sep2.setStyle("-fx-background-color: " + DIVIDER_COLOR + ";");

                // 3. Notification Methods
                VBox section3 = new VBox(15);
                Label s3Title = new Label("Notification Methods");
                s3Title.setTextFill(Color.WHITE);
                s3Title.setFont(Font.font("System", FontWeight.BOLD, 16));

                VBox toggleList = new VBox(0);
                toggleList.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12;");

                toggleList.getChildren().addAll(
                                createToggleRow("\ud83d\udd14", "Instant Push Notification",
                                                "Alert as soon as it hits the market",
                                                true, true),
                                createToggleRow("\u2709", "Daily Email Digest", "Summary of all new matches daily",
                                                false, true),
                                createToggleRow("\ud83d\udcac", "SMS Alert", "Standard carrier rates may apply", false,
                                                false));

                section3.getChildren().addAll(s3Title, toggleList);

                // 0. Search History (Recent Searches)
                VBox historySec = new VBox(15);
                Label historyTitle = new Label("Search History");
                historyTitle.setTextFill(Color.WHITE);
                historyTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

                VBox historyList = new VBox(10);
                historyList.getChildren().addAll(
                                createHistoryRow("2BR Apartments in Westlands", "Oct 20, 2023"),
                                createHistoryRow("Studios near CBD", "Oct 18, 2023"));
                historySec.getChildren().addAll(historyTitle, historyList);

                // 4. Ward Comparison
                VBox compareSec = new VBox(15);
                Label compareTitle = new Label("Ward Comparison");
                compareTitle.setTextFill(Color.WHITE);
                compareTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

                HBox compareGrid = new HBox(15);
                compareGrid.getChildren().addAll(
                                createCompareCard("Kileleshwa", "Safety: 8.5", PRIMARY),
                                createCompareCard("Kilimani", "Safety: 7.8", "#f97316"));
                compareSec.getChildren().addAll(compareTitle, compareGrid);

                content.getChildren().addAll(historySec, section1, sep1, section2, sep2, section3, compareSec);
                scrollPane.setContent(content);
                mainLayout.setCenter(scrollPane);

                // Footer
                VBox footer = new VBox();
                footer.setPadding(new Insets(16));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + DIVIDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                Button createBtn = new Button("Create Alert");
                createBtn.setGraphic(new Label("\ud83d\udd14")); // Bell icon
                createBtn.setContentDisplay(ContentDisplay.LEFT);
                createBtn.setMaxWidth(Double.MAX_VALUE);
                createBtn.setPrefHeight(56);
                createBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12;");

                footer.getChildren().add(createBtn);

                getChildren().addAll(mainLayout, footer);
                StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
        }

        private HBox createChip(String text, boolean removable) {
                HBox chip = new HBox(5);
                chip.setAlignment(Pos.CENTER);
                chip.setPadding(new Insets(6, 12, 6, 12));
                chip.setStyle(
                                "-fx-background-color: rgba(19, 236, 91, 0.2); -fx-border-color: rgba(19, 236, 91, 0.3); -fx-border-radius: 20; -fx-background-radius: 20;");

                Label lbl = new Label(text);
                lbl.setTextFill(Color.web(PRIMARY));
                lbl.setFont(Font.font("System", FontWeight.BOLD, 12));

                chip.getChildren().add(lbl);

                if (removable) {
                        Label close = new Label("\u2715");
                        close.setTextFill(Color.web(PRIMARY));
                        close.setCursor(javafx.scene.Cursor.HAND);
                        chip.getChildren().add(close);
                }

                return chip;
        }

        private Button createAddChip() {
                Button btn = new Button("+ Add Ward");
                btn.setStyle(
                                "-fx-background-color: #282e39; -fx-text-fill: #9da6b9; -fx-background-radius: 20; -fx-font-size: 12; -fx-cursor: hand;");
                btn.setPadding(new Insets(6, 16, 6, 16));
                return btn;
        }

        private HBox createHistoryRow(String query, String date) {
                HBox row = new HBox(12);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(12));
                row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12;");

                Label icon = new Label("\u23f3");
                icon.setTextFill(Color.web(PRIMARY));

                VBox txt = new VBox(2);
                Label q = new Label(query);
                q.setTextFill(Color.WHITE);
                q.setFont(Font.font("System", FontWeight.BOLD, 13));
                Label d = new Label(date);
                d.setTextFill(Color.web(TEXT_GRAY));
                d.setFont(Font.font(10));
                txt.getChildren().addAll(q, d);

                row.getChildren().addAll(icon, txt);
                return row;
        }

        private VBox createCompareCard(String name, String stat, String color) {
                VBox card = new VBox(8);
                card.setPadding(new Insets(15));
                card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + color + ";");
                HBox.setHgrow(card, Priority.ALWAYS);

                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 14));

                Label s = new Label(stat);
                s.setTextFill(Color.web(color));
                s.setFont(Font.font(11));

                card.getChildren().addAll(n, s);
                return card;
        }

        private HBox createToggleRow(String icon, String title, String subtitle, boolean checked, boolean hasBorder) {
                HBox row = new HBox(15);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(16));
                if (hasBorder) {
                        row.setStyle("-fx-border-color: " + DIVIDER_COLOR + "; -fx-border-width: 0 0 1 0;");
                }

                StackPane iconCircle = new StackPane();
                iconCircle.setPrefSize(40, 40);
                iconCircle.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 20;");
                Label ic = new Label(icon);
                ic.setTextFill(Color.web(PRIMARY));
                iconCircle.getChildren().add(ic);

                VBox text = new VBox(2);
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label s = new Label(subtitle);
                s.setTextFill(Color.web(TEXT_GRAY));
                s.setFont(Font.font(10));
                text.getChildren().addAll(t, s);
                HBox.setHgrow(text, Priority.ALWAYS);

                // Custom Toggle Switch (Simulated)
                StackPane toggle = new StackPane();
                toggle.setPrefSize(44, 24);
                Rectangle bg = new Rectangle(44, 24);
                bg.setArcWidth(24);
                bg.setArcHeight(24);
                bg.setFill(checked ? Color.web(PRIMARY) : Color.web("#475569"));

                javafx.scene.shape.Circle knob = new javafx.scene.shape.Circle(10);
                knob.setFill(Color.WHITE);
                knob.setTranslateX(checked ? 10 : -10);

                toggle.getChildren().addAll(bg, knob);

                row.getChildren().addAll(iconCircle, text, toggle);
                return row;
        }
}
