package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DigitalSignatureView extends StackPane {

    private static final String BACKGROUND_DARK = "#101922";
    private static final String PRIMARY = "#137fec";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
    private static final String CARD_BG = "#1a1f2e";
    private static final String TEXT_GRAY = "#94a3b8";

    private Canvas canvas;
    private GraphicsContext gc;

    public DigitalSignatureView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Top Navigation
        HBox topNav = new HBox();
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setPadding(new Insets(15, 20, 15, 20));
        topNav.setStyle("-fx-background-color: rgba(16, 25, 34, 0.8); -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new LeaseAgreementView()));

        Label title = new Label("Digital Signature");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Region spacer = new Region();
        spacer.setPrefWidth(28);

        topNav.getChildren().addAll(backBtn, title, spacer);

        // Content Area
        VBox content = new VBox(0);
        content.setPadding(new Insets(0, 20, 20, 20));
        content.setAlignment(Pos.TOP_CENTER);
        VBox.setVgrow(content, Priority.ALWAYS);

        // Step Indicator
        HBox indicators = new HBox(8);
        indicators.setAlignment(Pos.CENTER);
        indicators.setPadding(new Insets(25, 0, 25, 0));

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

        // Instructions
        Label instruction = new Label(
                "By signing, you agree to the terms of the lease agreement for Sunshine Apartments.");
        instruction.setTextFill(Color.WHITE);
        instruction.setFont(Font.font(15));
        instruction.setWrapText(true);
        instruction.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        instruction.setPadding(new Insets(0, 0, 30, 0));

        // Signature Canvas Container
        StackPane canvasContainer = new StackPane();
        canvasContainer.setPrefHeight(300);
        VBox.setVgrow(canvasContainer, Priority.ALWAYS);
        canvasContainer.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                + BORDER_COLOR + " ; -fx-border-style: dashed; -fx-border-width: 2; -fx-border-radius: 16;");

        // Empty state hints (will be visible under/behind the canvas or removed on
        // drawing)
        VBox hints = new VBox(15);
        hints.setAlignment(Pos.CENTER);
        hints.setMouseTransparent(true);

        Label drawIcon = new Label("\u270e");
        drawIcon.setTextFill(Color.web("#3b4754"));
        drawIcon.setStyle("-fx-font-size: 60;");

        VBox hintLabels = new VBox(5);
        hintLabels.setAlignment(Pos.CENTER);
        Label signHere = new Label("Sign Here");
        signHere.setTextFill(Color.WHITE);
        signHere.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label signDesc = new Label("Use your finger to draw your signature in this area");
        signDesc.setTextFill(Color.web(TEXT_GRAY));
        signDesc.setFont(Font.font(13));
        hintLabels.getChildren().addAll(signHere, signDesc);

        hints.getChildren().addAll(drawIcon, hintLabels);

        // Baseline cues
        StackPane cues = new StackPane();
        cues.setMouseTransparent(true);
        cues.setPadding(new Insets(0, 20, 60, 20));
        StackPane.setAlignment(cues, Pos.BOTTOM_CENTER);

        Line baseline = new Line(0, 0, 100, 0); // Width will be bound later
        baseline.setStroke(Color.web("#3b4754"));
        baseline.setStrokeWidth(1);

        Label xMark = new Label("X");
        xMark.setTextFill(Color.web("#3b4754"));
        xMark.setFont(Font.font("System", FontWeight.BOLD, 12));
        StackPane.setAlignment(xMark, Pos.BOTTOM_LEFT);
        StackPane.setMargin(xMark, new Insets(0, 0, 5, 0));

        // Canvas Setup
        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);
        gc.setLineCap(javafx.scene.shape.StrokeLineCap.ROUND);

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

        // Rotation Hint
        HBox rotHint = new HBox(5);
        rotHint.setPadding(new Insets(5, 10, 5, 10));
        rotHint.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 20;");
        Label rotIcon = new Label("\ud83d\udcf3");
        Label rotText = new Label("LANDSCAPE RECOMMENDED");
        rotText.setTextFill(Color.web(TEXT_GRAY));
        rotText.setFont(Font.font("System", FontWeight.BOLD, 9));
        rotHint.getChildren().addAll(rotIcon, rotText);
        rotHint.setMaxWidth(Region.USE_PREF_SIZE);
        StackPane.setAlignment(rotHint, Pos.TOP_RIGHT);
        StackPane.setMargin(rotHint, new Insets(15));

        canvasContainer.getChildren().addAll(hints, cues, canvas, rotHint);

        // Bind canvas size to container
        canvas.widthProperty().bind(canvasContainer.widthProperty());
        canvas.heightProperty().bind(canvasContainer.heightProperty());
        baseline.startXProperty().set(0);
        baseline.endXProperty().bind(canvasContainer.widthProperty().subtract(40));

        Label stepLabel = new Label("STEP 2 OF 3: IDENTITY VERIFICATION");
        stepLabel.setTextFill(Color.web(TEXT_GRAY));
        stepLabel.setFont(Font.font("System", FontWeight.BOLD, 10));
        stepLabel.setPadding(new Insets(15, 0, 0, 0));

        content.getChildren().addAll(indicators, instruction, canvasContainer, stepLabel);

        // Buttons
        HBox actions = new HBox(15);
        actions.setPadding(new Insets(30, 0, 30, 0));
        Button clearBtn = new Button("Clear");
        clearBtn.setPrefHeight(56);
        clearBtn.setPrefWidth(120);
        clearBtn.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-cursor: hand;");
        clearBtn.setOnAction(e -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            hints.setVisible(true);
        });

        Button signBtn = new Button("Adopt & Sign");
        HBox.setHgrow(signBtn, Priority.ALWAYS);
        signBtn.setMaxWidth(Double.MAX_VALUE);
        signBtn.setPrefHeight(56);
        signBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
        signBtn.setOnAction(e -> MainApp.navigateTo(new LeaseSigningCompleteView()));

        actions.getChildren().addAll(clearBtn, signBtn);
        content.getChildren().add(actions);

        layout.getChildren().addAll(topNav, content);
        getChildren().add(layout);
    }
}
