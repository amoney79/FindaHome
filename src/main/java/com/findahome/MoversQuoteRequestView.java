package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MoversQuoteRequestView extends VBox {

        private static final String BACKGROUND_DARK = "#181311"; // From HTML
        private static final String PRIMARY = "#f46a25"; // From HTML
        private static final String TEXT_GRAY = "#9ca3af";

        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

        public MoversQuoteRequestView() {
                setSpacing(0);
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
                VBox.setVgrow(this, Priority.ALWAYS);

                // --- Header ---
                HBox header = new HBox(0);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-background-color: rgba(24, 19, 17, 0.8); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Button backBtn = new Button("\u276E"); // arrow_back_ios
                backBtn.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18; -fx-cursor: hand;");
                backBtn.setOnAction(e -> MainApp.navigateTo(new MovingChecklistView())); // Go back to checklist

                Label title = new Label("Get a Quote");
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setTextFill(Color.WHITE);
                HBox.setHgrow(title, Priority.ALWAYS);
                title.setMaxWidth(Double.MAX_VALUE);
                title.setAlignment(Pos.CENTER);
                // Offset the title to center it properly since back button is on left
                title.setTranslateX(-15);

                Button infoBtn = new Button("ℹ"); // info icon
                infoBtn.setStyle(
                                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 20; -fx-cursor: hand;");

                header.getChildren().addAll(backBtn, title, infoBtn);

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

                // Pickup Location
                form.getChildren()
                                .add(createInputField("Pickup Location", "\uE0C8",
                                                "Riverside, Westlands (Current Property)")); // location_on

                // Delivery Location
                form.getChildren().add(
                                createInputField("Delivery Location", "\uE558", "Garden City Apartments, Thika Rd")); // local_shipping

                // Move Date and House Size
                form.getChildren().add(createInputField("Move Date", "\uE916", "2024-06-15")); // calendar_month

                // House Size
                VBox houseSizeBox = new VBox(8);
                Label houseSizeLbl = new Label("House Size");
                houseSizeLbl.setTextFill(Color.WHITE);
                houseSizeLbl.setFont(Font.font("System", FontWeight.BOLD, 14));

                GridPane sizeGrid = new GridPane();
                sizeGrid.setHgap(12);
                sizeGrid.getColumnConstraints().addAll(
                                new ColumnConstraints(), new ColumnConstraints(), new ColumnConstraints());
                sizeGrid.getColumnConstraints().get(0).setPercentWidth(33.3);
                sizeGrid.getColumnConstraints().get(1).setPercentWidth(33.3);
                sizeGrid.getColumnConstraints().get(2).setPercentWidth(33.3);

                sizeGrid.add(createSizeOption("Studio", "\uE205", true), 0, 0); // bed
                sizeGrid.add(createSizeOption("1 Bed", "\uE88A", false), 1, 0); // door_front (home)
                sizeGrid.add(createSizeOption("2+ Bed", "\uE88A", false), 2, 0); // home

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
                Label packIcon = new Label("\uE174"); // inventory_2
                packIcon.setTextFill(Color.web(PRIMARY));
                packIcon.setFont(Font.font(20));
                packIconBox.getChildren().add(packIcon);

                VBox packText = new VBox(2);
                Label packTitle = new Label("Packing Services");
                packTitle.setTextFill(Color.WHITE);
                packTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label packSub = new Label("Help with boxes and wrapping");
                packSub.setTextFill(Color.web(TEXT_GRAY));
                packSub.setFont(Font.font("System", 12));
                packText.getChildren().addAll(packTitle, packSub);
                HBox.setHgrow(packText, Priority.ALWAYS);

                // Toggle Switch (Simulation)
                StackPane toggle = new StackPane();
                toggle.setPrefSize(44, 24);
                Region toggleBg = new Region();
                toggleBg.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 12;");
                Circle toggleKnob = new Circle(10, Color.WHITE);
                toggleKnob.setTranslateX(10); // Checked state
                toggle.getChildren().addAll(toggleBg, toggleKnob);

                packingCard.getChildren().addAll(packIconBox, packText, toggle);
                form.getChildren().add(packingCard);

                content.getChildren().addAll(progressSection, titleSection, form);

                ScrollPane scrollPane = new ScrollPane(content);
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle("-fx-background: " + BACKGROUND_DARK + "; -fx-background-color: " + BACKGROUND_DARK
                                + ";");
                VBox.setVgrow(scrollPane, Priority.ALWAYS);

                // --- Bottom Bar ---
                VBox bottomBar = new VBox();
                bottomBar.setPadding(new Insets(16));
                bottomBar.setStyle("-fx-background-color: rgba(24, 19, 17, 0.9); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                Button nextBtn = new Button("Next: Items & Inventory  \u279C"); // arrow_forward
                nextBtn.setMaxWidth(Double.MAX_VALUE);
                nextBtn.setPrefHeight(56);
                nextBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 16;");
                nextBtn.setOnAction(e -> MainApp.navigateTo(new MoversInventoryView()));
                nextBtn.setOnMouseEntered(e -> nextBtn.setStyle(
                                "-fx-background-color: #ff7b3b; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 16;"));
                nextBtn.setOnMouseExited(e -> nextBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 16;"));

                bottomBar.getChildren().add(nextBtn);

                getChildren().addAll(header, scrollPane, bottomBar);
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

                // Use Label for read-only feel or TextField for editable.
                // Logic: HTML has input value="...", so editable.
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
                                + ";");

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
