package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MoversInventoryView extends VBox {

        private static final String BACKGROUND_DARK = "#221610"; // From HTML
        private static final String BACKGROUND_BLACK = "#181311"; // Sometimes used in text
        private static final String PRIMARY = "#f46a25";
        private static final String TEXT_GRAY = "#9ca3af";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

        public MoversInventoryView() {
                setSpacing(0);
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
                VBox.setVgrow(this, Priority.ALWAYS);

                // --- Header ---
                HBox header = new HBox(0);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 0, 20)); // Adjusted padding for progress bar below
                header.setStyle("-fx-background-color: rgba(34, 22, 16, 0.8);");

                Button backBtn = new Button("\u276E"); // arrow_back_ios
                backBtn.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18; -fx-cursor: hand;");
                backBtn.setOnAction(e -> MainApp.navigateTo(new MoversQuoteRequestView()));

                VBox titleBox = new VBox(2);
                titleBox.setAlignment(Pos.CENTER);
                Label title = new Label("Inventory List");
                title.setFont(Font.font("System", FontWeight.BOLD, 16));
                title.setTextFill(Color.WHITE);
                Label subTitle = new Label("Step 2 of 3");
                subTitle.setFont(Font.font(12));
                subTitle.setTextFill(Color.web(TEXT_GRAY));
                titleBox.getChildren().addAll(title, subTitle);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                HBox.setHgrow(titleBox, Priority.ALWAYS);

                // Center the title properly
                // To do this, we put title in a stackpane or simple HBox with spacer logic
                // Let's just use the strict left-center-right layout

                Label helpBtn = new Label("Help");
                helpBtn.setTextFill(Color.web(PRIMARY));
                helpBtn.setFont(Font.font("System", FontWeight.BOLD, 14));

                header.getChildren().addAll(backBtn, spacer, titleBox, new Region(), helpBtn);
                // Distribute space: backBtn (left), titleBox (center), helpBtn (right)
                // Re-doing simple layout:
                header.getChildren().clear();
                Region leftS = new Region();
                HBox.setHgrow(leftS, Priority.ALWAYS);
                Region rightS = new Region();
                HBox.setHgrow(rightS, Priority.ALWAYS);
                header.getChildren().addAll(backBtn, leftS, titleBox, rightS, helpBtn);

                // Progress Bar
                HBox progressBar = new HBox(0);
                progressBar.setPrefHeight(4);
                progressBar.setPadding(new Insets(15, 0, 0, 0)); // Add space above bar inside header container
                // Actually HTML structure has header div then bar div.

                Region bar1 = new Region();
                bar1.setStyle("-fx-background-color: " + PRIMARY + ";");
                bar1.prefWidthProperty().bind(widthProperty().multiply(0.66)); // 2/3 width

                Region bar2 = new Region();
                bar2.setStyle("-fx-background-color: rgba(255,255,255,0.1);");
                HBox.setHgrow(bar2, Priority.ALWAYS);

                progressBar.getChildren().addAll(bar1, bar2);

                VBox topSection = new VBox(header, progressBar);
                topSection.setStyle("-fx-background-color: rgba(34, 22, 16, 0.8); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                // --- Main Content ---
                VBox content = new VBox(24);
                content.setPadding(new Insets(20));
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Title text
                VBox pageTitleBox = new VBox(6);
                Label pageTitle = new Label("What are we moving?");
                pageTitle.setTextFill(Color.WHITE);
                pageTitle.setFont(Font.font("System", FontWeight.BOLD, 22));
                Label pageDesc = new Label("Select items to help us provide an accurate quote.");
                pageDesc.setTextFill(Color.web(TEXT_GRAY));
                pageDesc.setFont(Font.font(14));
                pageDesc.setWrapText(true);
                pageTitleBox.getChildren().addAll(pageTitle, pageDesc);

                // Filter Buttons (Horizontal Scroll simulation)
                HBox filterBox = new HBox(10);
                filterBox.getChildren().addAll(
                                createFilterChip("Furniture", "\uE1C4", true), // chair
                                createFilterChip("Electronics", "\uE333", false), // tv
                                createFilterChip("Boxes & Misc", "\uE89C", false) // package_2
                );

                // Grid of Items
                GridPane grid = new GridPane();
                grid.setHgap(12);
                grid.setVgap(12);

                ColumnConstraints col1 = new ColumnConstraints();
                col1.setPercentWidth(50);
                ColumnConstraints col2 = new ColumnConstraints();
                col2.setPercentWidth(50);
                grid.getColumnConstraints().addAll(col1, col2);

                grid.add(createItemCard("Sofa (3 Seater)", "\uE1C4", 1, true), 0, 0); // chair_alt
                grid.add(createItemCard("King Size Bed", "\uE88A", 0, false), 1, 0); // bed
                grid.add(createItemCard("Dining Table", "\uE56C", 0, false), 0, 1); // table_restaurant
                grid.add(createItemCard("Office Desk", "\uE33F", 2, true), 1, 1); // desk

                // Upload Section
                VBox uploadSection = new VBox(12);
                Label uploadTitle = new Label("Bulky or Specific Items?");
                uploadTitle.setTextFill(Color.WHITE);
                uploadTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                VBox uploadBox = new VBox(12);
                uploadBox.setAlignment(Pos.CENTER);
                uploadBox.setPadding(new Insets(24));
                uploadBox.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.02); -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 16; -fx-border-style: dashed; -fx-border-width: 2;");

                StackPane iconCircle = new StackPane();
                iconCircle.setPrefSize(48, 48);
                iconCircle.setMaxSize(48, 48);
                iconCircle.setStyle("-fx-background-color: rgba(244, 106, 37, 0.1); -fx-background-radius: 24;");
                Label icon = new Label("\uE439"); // add_a_photo
                icon.setTextFill(Color.web(PRIMARY));
                icon.setFont(Font.font(20));
                iconCircle.getChildren().add(icon);

                Label upLbl = new Label("Upload photos");
                upLbl.setTextFill(Color.WHITE);
                upLbl.setFont(Font.font("System", FontWeight.BOLD, 14));

                Label upDesc = new Label(
                                "Take photos of items like pianos, safes, or custom cabinets for a more accurate quote.");
                upDesc.setTextFill(Color.web(TEXT_GRAY));
                upDesc.setFont(Font.font(12));
                upDesc.setWrapText(true);
                upDesc.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

                Button chooseBtn = new Button("Choose Files");
                chooseBtn.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12; -fx-background-radius: 20; -fx-padding: 8 24;");

                uploadBox.getChildren().addAll(iconCircle, upLbl, upDesc, chooseBtn);
                uploadSection.getChildren().addAll(uploadTitle, uploadBox);

                content.getChildren().addAll(pageTitleBox, filterBox, grid, uploadSection);

                ScrollPane scrollPane = new ScrollPane(content);
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle("-fx-background: " + BACKGROUND_DARK + "; -fx-background-color: " + BACKGROUND_DARK
                                + ";");
                VBox.setVgrow(scrollPane, Priority.ALWAYS);

                // --- Bottom Bar ---
                VBox bottomContainer = new VBox(0);

                // Stats bar
                HBox statsBar = new HBox();
                statsBar.setPadding(new Insets(12, 16, 12, 16));
                statsBar.setAlignment(Pos.CENTER_LEFT);
                statsBar.setStyle("-fx-background-color: rgba(34, 22, 16, 0.95); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                HBox statsLeft = new HBox(8);
                statsLeft.setAlignment(Pos.CENTER_LEFT);
                Label countLbl = new Label("3 Items Selected");
                countLbl.setStyle("-fx-background-color: rgba(244, 106, 37, 0.1); -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-font-size: 12; -fx-padding: 4 8; -fx-background-radius: 4;");
                Label volLbl = new Label("Est. 12m\u00B3 volume");
                volLbl.setTextFill(Color.web(TEXT_GRAY));
                volLbl.setFont(Font.font(12));
                statsLeft.getChildren().addAll(countLbl, volLbl);

                Region sSpacer = new Region();
                HBox.setHgrow(sSpacer, Priority.ALWAYS);

                Button viewListBtn = new Button("View List");
                viewListBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-font-size: 12; -fx-underline: true;");

                statsBar.getChildren().addAll(statsLeft, sSpacer, viewListBtn);

                // Action Button Area
                VBox ctaArea = new VBox();
                ctaArea.setPadding(new Insets(0, 16, 20, 16));
                ctaArea.setStyle("-fx-background-color: rgba(34, 22, 16, 1);");

                Button nextBtn = new Button("Submit Quote Request  \u279C");
                nextBtn.setMaxWidth(Double.MAX_VALUE);
                nextBtn.setPrefHeight(56);
                nextBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(244, 106, 37, 0.3), 10, 0, 0, 5);");
                // For now, loop back or show alert
                nextBtn.setOnAction(e -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Quote Request Submitted");
                        alert.setContentText("Thank you! Your inventory has been sent to our partners.");
                        alert.showAndWait();
                        MainApp.showHome();
                });

                ctaArea.getChildren().add(nextBtn);
                bottomContainer.getChildren().addAll(statsBar, ctaArea);

                getChildren().addAll(topSection, scrollPane, bottomContainer);
        }

        private Button createFilterChip(String text, String icon, boolean active) {
                Button btn = new Button(text);
                // We can't easily put icon next to text in simple button constructor without
                // graphic,
                // but for simplicity let's use graphic
                Label i = new Label(icon);
                i.setFont(Font.font(18));
                i.setTextFill(active ? Color.WHITE : (active ? Color.WHITE : Color.web(BACKGROUND_BLACK))); // Wait,
                                                                                                            // inactive
                                                                                                            // text
                                                                                                            // matches
                                                                                                            // bg?
                                                                                                            // No.
                // Inactive: bg white/dark-gray, text dark/white.
                // The HTML says inactive: bg-gray-800 text-gray-200.
                // Active: bg-primary text-white.

                Color textColor = active ? Color.WHITE : Color.web("#e5e7eb");
                i.setTextFill(textColor);

                btn.setGraphic(i);
                btn.setGraphicTextGap(8);
                btn.setTextFill(textColor);
                btn.setFont(Font.font("System", FontWeight.SEMI_BOLD, 14));
                String bg = active ? PRIMARY : "rgba(255,255,255,0.05)";
                String bColor = active ? PRIMARY : BORDER_COLOR;

                btn.setStyle("-fx-background-color: " + bg + "; -fx-border-color: " + bColor
                                + "; -fx-background-radius: 20; -fx-border-radius: 20; -fx-padding: 8 20;");
                return btn;
        }

        private VBox createItemCard(String name, String icon, int count, boolean selected) {
                VBox card = new VBox(10);
                card.setAlignment(Pos.CENTER);
                card.setPadding(new Insets(16));

                String borderColor = selected ? PRIMARY : BORDER_COLOR;
                String bg = "rgba(255,255,255,0.02)";
                // Selected cards in HTML have border-2 border-primary
                String borderW = selected ? "2" : "1";

                card.setStyle("-fx-background-color: " + bg + "; -fx-background-radius: 16; -fx-border-color: "
                                + borderColor
                                + "; -fx-border-radius: 16; -fx-border-width: " + borderW + ";");

                // Icon
                Label i = new Label(icon);
                i.setFont(Font.font(40));
                i.setTextFill(selected ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));

                // Name
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 14));

                card.getChildren().addAll(i, n);

                if (selected) {
                        // Counter Badge (Top Right) - Simulated by finding position?
                        // Better to use StackPane for card if we want badge.
                        // But let's stick to VBox and just add the counter controls below.

                        HBox counter = new HBox(12);
                        counter.setAlignment(Pos.CENTER);

                        Button minus = new Button("-");
                        minus.setStyle(
                                        "-fx-background-color: transparent; -fx-border-color: rgba(255,255,255,0.2); -fx-border-radius: 15; -fx-text-fill: white; -fx-min-width: 30; -fx-min-height: 30; -fx-padding: 0;");

                        Label cnt = new Label(String.valueOf(count));
                        cnt.setTextFill(Color.WHITE);
                        cnt.setFont(Font.font("System", FontWeight.BOLD, 18));

                        Button plus = new Button("+");
                        plus.setStyle("-fx-background-color: rgba(244, 106, 37, 0.1); -fx-text-fill: " + PRIMARY
                                        + "; -fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; -fx-padding: 0; -fx-font-weight: bold;");

                        counter.getChildren().addAll(minus, cnt, plus);
                        card.getChildren().add(counter);

                        // Note: The badge "1" or "2" in top right corner is hard in VBox.
                        // Since I'm essentially rewriting the layout logic, I'll skip the badge or wrap
                        // in StackPane if critical.
                        // Design is "Pop", so let's try to wrap it in a StackPane to handle the badge
                        // if we have time,
                        // but for now the controls below suffice.
                } else {
                        // Add Item Button
                        Button add = new Button("Add Item");
                        add.setMaxWidth(Double.MAX_VALUE);
                        add.setStyle("-fx-background-color: transparent; -fx-border-color: " + PRIMARY
                                        + "; -fx-text-fill: "
                                        + PRIMARY
                                        + "; -fx-border-radius: 8; -fx-font-weight: bold; -fx-font-size: 12;");
                        card.getChildren().add(add);
                }

                return card;
        }
}
