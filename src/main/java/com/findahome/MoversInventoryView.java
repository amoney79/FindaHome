package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MoversInventoryView extends BorderPane {

        private static final String BACKGROUND_DARK = "#221610";
        private static final String PRIMARY = "#f46a25";
        private static final String TEXT_GRAY = "#9ca3af";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

        public MoversInventoryView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // --- Top Header & Progress ---
                HBox header = new HBox(0);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 10, 20));
                header.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                Button backBtn = new Button("\u276E");
                backBtn.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18; -fx-cursor: hand;");
                backBtn.setOnAction(e -> MainApp.navigateCached("movers_quote", MoversQuoteRequestView::new));

                VBox titleBox = new VBox(2);
                titleBox.setAlignment(Pos.CENTER);
                Label title = new Label("Inventory List");
                title.setFont(Font.font("System", FontWeight.BOLD, 16));
                title.setTextFill(Color.WHITE);
                Label subTitle = new Label("Step 2 of 3");
                subTitle.setFont(Font.font(12));
                subTitle.setTextFill(Color.web(TEXT_GRAY));
                titleBox.getChildren().addAll(title, subTitle);

                Region spacerL = new Region();
                HBox.setHgrow(spacerL, Priority.ALWAYS);
                Region spacerR = new Region();
                HBox.setHgrow(spacerR, Priority.ALWAYS);

                Label helpBtn = new Label("Help");
                helpBtn.setTextFill(Color.web(PRIMARY));
                helpBtn.setFont(Font.font("System", FontWeight.BOLD, 14));
                helpBtn.setCursor(javafx.scene.Cursor.HAND);

                header.getChildren().addAll(backBtn, spacerL, titleBox, spacerR, helpBtn);

                HBox progressBar = new HBox(0);
                progressBar.setPrefHeight(4);
                Region bar1 = new Region();
                bar1.setStyle("-fx-background-color: " + PRIMARY + ";");
                bar1.prefWidthProperty().bind(widthProperty().multiply(0.66));
                Region bar2 = new Region();
                bar2.setStyle("-fx-background-color: rgba(255,255,255,0.1);");
                HBox.setHgrow(bar2, Priority.ALWAYS);
                progressBar.getChildren().addAll(bar1, bar2);

                VBox topArea = new VBox(header, progressBar);
                topArea.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");
                setTop(topArea);

                // --- Main Content ---
                VBox content = new VBox(24);
                content.setPadding(new Insets(20));
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox pageTitleBox = new VBox(6);
                Label pageTitle = new Label("What are we moving?");
                pageTitle.setTextFill(Color.WHITE);
                pageTitle.setFont(Font.font("System", FontWeight.BOLD, 22));
                Label pageDesc = new Label("Select items to help us provide an accurate quote.");
                pageDesc.setTextFill(Color.web(TEXT_GRAY));
                pageDesc.setFont(Font.font(14));
                pageDesc.setWrapText(true);
                pageTitleBox.getChildren().addAll(pageTitle, pageDesc);

                HBox filterBox = new HBox(10);
                filterBox.getChildren().addAll(
                                createFilterChip("Furniture", "\uD83E\uDE91", true),
                                createFilterChip("Electronics", "\uD83D\uDCFA", false),
                                createFilterChip("Boxes & Misc", "\uD83D\uDCE6", false));

                GridPane grid = new GridPane();
                grid.setHgap(12);
                grid.setVgap(12);
                ColumnConstraints col = new ColumnConstraints();
                col.setPercentWidth(50);
                grid.getColumnConstraints().addAll(col, col);

                grid.add(createItemCard("Sofa (3 Seater)", "\uD83E\uDE91", 1, true), 0, 0);
                grid.add(createItemCard("King Size Bed", "\uD83D\uDECF\uFE0F", 0, false), 1, 0);
                grid.add(createItemCard("Dining Table", "\uD83C\uDF7D\uFE0F", 0, false), 0, 1);
                grid.add(createItemCard("Office Desk", "\uD83D\uDDA5\uFE0F", 2, true), 1, 1);

                VBox uploadSection = new VBox(12);
                Label uploadTitle = new Label("Bulky or Specific Items?");
                uploadTitle.setTextFill(Color.WHITE);
                uploadTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                VBox uploadBox = new VBox(12);
                uploadBox.setAlignment(Pos.CENTER);
                uploadBox.setPadding(new Insets(24));
                uploadBox.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.02); -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 16; -fx-border-style: dashed; -fx-border-width: 2;");

                Label upLbl = new Label("Upload photos");
                upLbl.setTextFill(Color.WHITE);
                upLbl.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label upDesc = new Label("Photos of pianos or safes help with accuracy.");
                upDesc.setTextFill(Color.web(TEXT_GRAY));
                upDesc.setFont(Font.font(12));
                upDesc.setWrapText(true);
                upDesc.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

                Button chooseBtn = new Button("Choose Files");
                chooseBtn.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12; -fx-background-radius: 20; -fx-padding: 8 24; -fx-cursor: hand;");

                uploadBox.getChildren().addAll(upLbl, upDesc, chooseBtn);
                uploadSection.getChildren().addAll(uploadTitle, uploadBox);

                content.getChildren().addAll(pageTitleBox, filterBox, grid, uploadSection);

                ScrollPane scroll = new ScrollPane(content);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
                setCenter(scroll);

                // --- Bottom Area (Pinned) ---
                VBox bottomArea = new VBox();

                HBox statsBar = new HBox();
                statsBar.setPadding(new Insets(12, 16, 12, 16));
                statsBar.setAlignment(Pos.CENTER_LEFT);
                statsBar.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                Label countLbl = new Label("3 Items Selected");
                countLbl.setStyle("-fx-background-color: rgba(244, 106, 37, 0.1); -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-font-size: 12; -fx-padding: 4 8; -fx-background-radius: 4;");
                Region sSpacer = new Region();
                HBox.setHgrow(sSpacer, Priority.ALWAYS);
                Button viewListBtn = new Button("View List");
                viewListBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-font-size: 12; -fx-underline: true; -fx-cursor: hand;");
                statsBar.getChildren().addAll(countLbl, sSpacer, viewListBtn);

                VBox ctaArea = new VBox();
                ctaArea.setPadding(new Insets(0, 20, 35, 20));
                ctaArea.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
                Button submitBtn = new Button("Submit Quote Request  \u279C");
                submitBtn.setMaxWidth(Double.MAX_VALUE);
                submitBtn.setPrefHeight(56);
                submitBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
                submitBtn.setOnAction(e -> {
                        MainApp.navigateCached("success", SuccessView::new);
                });
                ctaArea.getChildren().add(submitBtn);

                bottomArea.getChildren().addAll(statsBar, ctaArea);
                setBottom(bottomArea);
        }

        private Button createFilterChip(String text, String icon, boolean active) {
                Button btn = new Button(text);
                btn.setGraphic(new Label(icon) {
                        {
                                setFont(Font.font(18));
                                setTextFill(active ? Color.WHITE : Color.web("#e5e7eb"));
                        }
                });
                btn.setGraphicTextGap(8);
                btn.setTextFill(active ? Color.WHITE : Color.web("#e5e7eb"));
                btn.setFont(Font.font("System", FontWeight.SEMI_BOLD, 14));
                String bg = active ? PRIMARY : "rgba(255,255,255,0.05)";
                btn.setStyle("-fx-background-color: " + bg + "; -fx-border-color: " + (active ? PRIMARY : BORDER_COLOR)
                                + "; -fx-background-radius: 20; -fx-border-radius: 20; -fx-padding: 8 20; -fx-cursor: hand;");
                return btn;
        }

        private VBox createItemCard(String name, String icon, int count, boolean selected) {
                VBox card = new VBox(10);
                card.setAlignment(Pos.CENTER);
                card.setPadding(new Insets(16));
                card.setStyle("-fx-background-color: rgba(255,255,255,0.02); -fx-background-radius: 16; -fx-border-color: "
                                + (selected ? PRIMARY : BORDER_COLOR) + "; -fx-border-width: " + (selected ? "2" : "1")
                                + "; -fx-border-radius: 16;");

                Label i = new Label(icon) {
                        {
                                setFont(Font.font(40));
                                setTextFill(selected ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                        }
                };
                Label n = new Label(name) {
                        {
                                setTextFill(Color.WHITE);
                                setFont(Font.font("System", FontWeight.BOLD, 14));
                        }
                };
                card.getChildren().addAll(i, n);

                if (selected) {
                        HBox counter = new HBox(12, new Button("-") {
                                {
                                        setStyle("-fx-background-color: transparent; -fx-border-color: rgba(255,255,255,0.2); -fx-border-radius: 15; -fx-text-fill: white; -fx-min-width: 30; -fx-min-height: 30; -fx-cursor: hand;");
                                }
                        }, new Label(String.valueOf(count)) {
                                {
                                        setTextFill(Color.WHITE);
                                        setFont(Font.font("System", FontWeight.BOLD, 18));
                                }
                        }, new Button("+") {
                                {
                                        setStyle("-fx-background-color: rgba(244, 106, 37, 0.1); -fx-text-fill: "
                                                        + PRIMARY
                                                        + "; -fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; -fx-font-weight: bold; -fx-cursor: hand;");
                                }
                        });
                        counter.setAlignment(Pos.CENTER);
                        card.getChildren().add(counter);
                } else {
                        Button add = new Button("Add Item") {
                                {
                                        setMaxWidth(Double.MAX_VALUE);
                                        setStyle("-fx-background-color: transparent; -fx-border-color: " + PRIMARY
                                                        + "; -fx-text-fill: " + PRIMARY
                                                        + "; -fx-border-radius: 8; -fx-font-weight: bold; -fx-cursor: hand;");
                                }
                        };
                        card.getChildren().add(add);
                }
                return card;
        }
}
