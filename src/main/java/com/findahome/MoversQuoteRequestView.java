package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MoversQuoteRequestView extends BorderPane {

        private static final String BACKGROUND_DARK = "#181311";
        private static final String PRIMARY = "#f46a25";
        private static final String TEXT_GRAY = "#9ca3af";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

        public MoversQuoteRequestView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // --- Header ---
                HBox header = new HBox(0);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Button backBtn = new Button("\u276E"); // arrow_back_ios
                backBtn.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18; -fx-cursor: hand;");
                backBtn.setOnAction(e -> MainApp.navigateCached("moving_checklist", MovingChecklistView::new));

                Label title = new Label("Get a Quote");
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setTextFill(Color.WHITE);
                HBox.setHgrow(title, Priority.ALWAYS);
                title.setMaxWidth(Double.MAX_VALUE);
                title.setAlignment(Pos.CENTER);
                title.setTranslateX(-15);

                Button infoBtn = new Button("ℹ"); // info icon
                infoBtn.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 20; -fx-cursor: hand;");

                header.getChildren().addAll(backBtn, title, infoBtn);
                setTop(header);

                // --- Main Content ---
                VBox content = new VBox(24);
                content.setPadding(new Insets(24));
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Progress Section
                VBox progressSection = new VBox(8);
                HBox progressMeta = new HBox();
                progressMeta.setAlignment(Pos.CENTER_LEFT);
                Label stepLabel = new Label("Step 1 of 3");
                stepLabel.setTextFill(Color.web(PRIMARY));
                stepLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
                Region pSpacer = new Region();
                HBox.setHgrow(pSpacer, Priority.ALWAYS);
                Label stepName = new Label("Move Details");
                stepName.setTextFill(Color.web(TEXT_GRAY));
                stepName.setFont(Font.font("System", FontWeight.MEDIUM, 12));
                progressMeta.getChildren().addAll(stepLabel, pSpacer, stepName);

                HBox progressBar = new HBox(4);
                progressBar.setPrefHeight(6);
                Region bar1 = new Region();
                bar1.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 3;");
                HBox.setHgrow(bar1, Priority.ALWAYS);
                Region bar2 = new Region();
                bar2.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 3;");
                HBox.setHgrow(bar2, Priority.ALWAYS);
                Region bar3 = new Region();
                bar3.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 3;");
                HBox.setHgrow(bar3, Priority.ALWAYS);
                progressBar.getChildren().addAll(bar1, bar2, bar3);

                progressSection.getChildren().addAll(progressMeta, progressBar);

                // Title Section
                VBox titleSection = new VBox(8);
                Label pageTitle = new Label("Tell us about your move");
                pageTitle.setTextFill(Color.WHITE);
                pageTitle.setFont(Font.font("System", FontWeight.BOLD, 24));
                Label pageSub = new Label("Provide details for an accurate quote from our verified partners.");
                pageSub.setTextFill(Color.web(TEXT_GRAY));
                pageSub.setFont(Font.font("System", 14));
                pageSub.setWrapText(true);
                titleSection.getChildren().addAll(pageTitle, pageSub);

                // Form Fields
                VBox form = new VBox(20);
                form.getChildren().add(createInputField("Pickup Location", "\uD83D\uDCCD",
                                "Riverside, Westlands (Current Property)"));
                form.getChildren().add(createInputField("Delivery Location", "\uD83D\uDE9A",
                                "Garden City Apartments, Thika Rd"));
                form.getChildren().add(createInputField("Move Date", "\uD83D\uDCC5", "2024-06-15"));

                // House Size
                VBox houseSizeBox = new VBox(8);
                Label houseSizeLbl = new Label("House Size");
                houseSizeLbl.setTextFill(Color.WHITE);
                houseSizeLbl.setFont(Font.font("System", FontWeight.BOLD, 14));

                GridPane sizeGrid = new GridPane();
                sizeGrid.setHgap(12);
                sizeGrid.getColumnConstraints().addAll(new ColumnConstraints(), new ColumnConstraints(),
                                new ColumnConstraints());
                sizeGrid.getColumnConstraints().forEach(c -> c.setPercentWidth(33.3));

                sizeGrid.add(createSizeOption("Studio", "\uD83D\uDCF1", true), 0, 0);
                sizeGrid.add(createSizeOption("1 Bed", "\uD83D\uDEAA", false), 1, 0);
                sizeGrid.add(createSizeOption("2+ Bed", "\uD83C\uDFE0", false), 2, 0);

                houseSizeBox.getChildren().addAll(houseSizeLbl, sizeGrid);
                form.getChildren().add(houseSizeBox);

                // Packing Services
                HBox packingCard = new HBox(12);
                packingCard.setPadding(new Insets(16));
                packingCard.setAlignment(Pos.CENTER_LEFT);
                packingCard.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 12;");

                StackPane packIconBox = new StackPane();
                packIconBox.setPrefSize(40, 40);
                packIconBox.setStyle("-fx-background-color: rgba(244, 106, 37, 0.2); -fx-background-radius: 20;");
                Label packIcon = new Label("\uD83D\uDCE6");
                packIcon.setTextFill(Color.web(PRIMARY));
                packIcon.setFont(Font.font(20));
                packIconBox.getChildren().add(packIcon);

                VBox packText = new VBox(2);
                Label packTitle = new Label("Packing Services");
                packTitle.setTextFill(Color.WHITE);
                packTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label packSubS = new Label("Help with boxes and wrapping");
                packSubS.setTextFill(Color.web(TEXT_GRAY));
                packSubS.setFont(Font.font("System", 12));
                packText.getChildren().addAll(packTitle, packSubS);
                HBox.setHgrow(packText, Priority.ALWAYS);

                StackPane toggle = new StackPane();
                toggle.setPrefSize(44, 24);
                Region toggleBg = new Region();
                toggleBg.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 12;");
                Circle toggleKnob = new Circle(10, Color.WHITE);
                toggleKnob.setTranslateX(10);
                toggle.getChildren().addAll(toggleBg, toggleKnob);

                packingCard.getChildren().addAll(packIconBox, packText, toggle);
                form.getChildren().add(packingCard);

                content.getChildren().addAll(progressSection, titleSection, form);

                ScrollPane scrollPane = new ScrollPane(content);
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle(
                                "-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
                setCenter(scrollPane);

                // --- Bottom Bar (Pinned) ---
                VBox bottomBar = new VBox();
                bottomBar.setPadding(new Insets(20, 20, 35, 20));
                bottomBar.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                Button nextBtn = new Button("Next: Items & Inventory  \u279C");
                nextBtn.setMaxWidth(Double.MAX_VALUE);
                nextBtn.setPrefHeight(56);
                nextBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 16; -fx-cursor: hand;");
                nextBtn.setOnAction(e -> MainApp.navigateCached("movers_inventory", MoversInventoryView::new));

                bottomBar.getChildren().add(nextBtn);
                setBottom(bottomBar);
        }

        private VBox createInputField(String labelText, String icon, String value) {
                VBox box = new VBox(8);
                Label label = new Label(labelText);
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font("System", FontWeight.BOLD, 14));

                HBox inputContainer = new HBox(12);
                inputContainer.setAlignment(Pos.CENTER_LEFT);
                inputContainer.setPadding(new Insets(12));
                inputContainer.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 12;");

                Label iconLbl = new Label(icon);
                iconLbl.setTextFill(Color.web(PRIMARY));
                iconLbl.setFont(Font.font(18));

                TextField input = new TextField(value);
                input.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 0;");
                HBox.setHgrow(input, Priority.ALWAYS);

                inputContainer.getChildren().addAll(iconLbl, input);
                box.getChildren().addAll(label, inputContainer);
                return box;
        }

        private VBox createSizeOption(String text, String icon, boolean selected) {
                VBox box = new VBox(8);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(16));
                String borderColor = selected ? PRIMARY : "rgba(255,255,255,0.1)";
                String bgColor = selected ? "rgba(244, 106, 37, 0.1)" : "rgba(255,255,255,0.05)";
                box.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 12; -fx-border-color: "
                                + borderColor + "; -fx-border-radius: 12; -fx-border-width: " + (selected ? "2" : "1")
                                + "; -fx-cursor: hand;");

                Label iconLbl = new Label(icon);
                iconLbl.setTextFill(selected ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                iconLbl.setFont(Font.font(24));

                Label textLbl = new Label(text);
                textLbl.setTextFill(selected ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                textLbl.setFont(Font.font("System", FontWeight.BOLD, 12));

                box.getChildren().addAll(iconLbl, textLbl);
                return box;
        }
}
