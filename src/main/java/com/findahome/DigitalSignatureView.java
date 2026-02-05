package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DigitalSignatureView extends BorderPane {

        private static final String BACKGROUND_DARK = "#101922";
        private static final String PRIMARY = "#137fec";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
        private static final String CARD_BG = "#1a1f2e";
        private static final String TEXT_GRAY = "#94a3b8";

        private Canvas canvas;
        private GraphicsContext gc;

        public DigitalSignatureView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // --- Top Navigation ---
                HBox topNav = new HBox();
                topNav.setAlignment(Pos.CENTER_LEFT);
                topNav.setPadding(new Insets(15, 20, 15, 20));
                topNav.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u276E"); // Safer arrow
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 24; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.navigateCached("lease", LeaseAgreementView::new));

                Label title = new Label("Digital Signature");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);

                Region spacer = new Region();
                spacer.setPrefWidth(28);

                topNav.getChildren().addAll(backBtn, title, spacer);
                setTop(topNav);

                // --- Content Area ---
                VBox content = new VBox(20);
                content.setPadding(new Insets(20));
                content.setAlignment(Pos.TOP_CENTER);
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Step Indicator
                HBox indicators = new HBox(8);
                indicators.setAlignment(Pos.CENTER);
                indicators.setPadding(new Insets(10, 0, 10, 0));

                Region dot1 = new Region();
                dot1.setPrefSize(6, 6);
                dot1.setStyle("-fx-background-color: #3b4754; -fx-background-radius: 3;");
                Region dot2 = new Region();
                dot2.setPrefSize(24, 6);
                dot2.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 3;");
                Region dot3 = new Region();
                dot3.setPrefSize(6, 6);
                dot3.setStyle("-fx-background-color: #3b4754; -fx-background-radius: 3;");
                indicators.getChildren().addAll(dot1, dot2, dot3);

                Label instruction = new Label("Please sign within the box below to authorize the lease agreement.");
                instruction.setTextFill(Color.WHITE);
                instruction.setFont(Font.font(14));
                instruction.setWrapText(true);
                instruction.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

                // Signature Canvas Container (Controlled Size)
                StackPane canvasContainer = new StackPane();
                canvasContainer.setPrefHeight(250); // Fixed height to prevent exponential expansion
                canvasContainer.setMaxHeight(250);
                canvasContainer.setMinWidth(300);
                canvasContainer.setMaxWidth(600); // Limit width for better ergonomics
                canvasContainer.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR
                                + " ; -fx-border-style: dashed; -fx-border-width: 2; -fx-border-radius: 16;");

                VBox hints = new VBox(10);
                hints.setAlignment(Pos.CENTER);
                hints.setMouseTransparent(true);
                Label signHere = new Label("Sign Here");
                signHere.setTextFill(Color.web(TEXT_GRAY));
                signHere.setFont(Font.font("System", FontWeight.BOLD, 16));
                hints.getChildren().add(signHere);

                // Canvas Setup
                canvas = new Canvas();
                gc = canvas.getGraphicsContext2D();
                gc.setStroke(Color.WHITE);
                gc.setLineWidth(3);
                gc.setLineCap(javafx.scene.shape.StrokeLineCap.ROUND);

                canvas.widthProperty().bind(canvasContainer.widthProperty());
                canvas.heightProperty().bind(canvasContainer.heightProperty());

                canvas.setOnMousePressed(e -> {
                        gc.beginPath();
                        gc.moveTo(e.getX(), e.getY());
                        gc.stroke();
                        hints.setVisible(false);
                });

                canvas.setOnMouseDragged(e -> {
                        gc.lineTo(e.getX(), e.getY());
                        gc.stroke();
                });

                canvasContainer.getChildren().addAll(hints, canvas);

                Label stepLabel = new Label("STEP 2 OF 3: IDENTITY VERIFICATION");
                stepLabel.setTextFill(Color.web(TEXT_GRAY));
                stepLabel.setFont(Font.font("System", FontWeight.BOLD, 10));

                content.getChildren().addAll(indicators, instruction, canvasContainer, stepLabel);

                ScrollPane scroll = new ScrollPane(content);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle(
                                "-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
                setCenter(scroll);

                // --- Bottom Actions (Pinned) ---
                HBox footer = new HBox(15);
                footer.setPadding(new Insets(20, 20, 35, 20));
                footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                Button clearBtn = new Button("Clear");
                clearBtn.setPrefHeight(56);
                clearBtn.setPrefWidth(100);
                clearBtn.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-cursor: hand;");
                clearBtn.setOnAction(e -> {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        hints.setVisible(true);
                });

                Button signBtn = new Button("Adopt & Sign Agreement");
                HBox.setHgrow(signBtn, Priority.ALWAYS);
                signBtn.setMaxWidth(Double.MAX_VALUE);
                signBtn.setPrefHeight(56);
                signBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
                signBtn.setOnAction(
                                e -> MainApp.navigateCachedFullScreen("lease_complete", LeaseSigningCompleteView::new));

                footer.getChildren().addAll(clearBtn, signBtn);
                setBottom(footer);
        }
}
