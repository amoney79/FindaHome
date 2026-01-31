package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DocumentPreviewView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
    private static final String TEXT_GRAY = "#9db9a6";

    public DocumentPreviewView(String documentTitle) {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new DocumentVaultView()));

        Label title = new Label(documentTitle);
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        HBox actions = new HBox(15);
        actions.setAlignment(Pos.CENTER_RIGHT);
        Label dlBtn = new Label("\u2913");
        dlBtn.setTextFill(Color.WHITE);
        dlBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");
        Label shBtn = new Label("\u27a6");
        shBtn.setTextFill(Color.WHITE);
        shBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");
        actions.getChildren().addAll(dlBtn, shBtn);

        header.getChildren().addAll(backBtn, title, actions);

        // Scroll Content
        VBox scrollContent = new VBox(0);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Document Preview Area
        VBox previewArea = new VBox();
        previewArea.setPadding(new Insets(20));

        StackPane docContainer = new StackPane();
        docContainer.setPrefHeight(450);
        docContainer
                .setStyle("-fx-background-color: #1a1f2e; -fx-background-radius: 12; -fx-border-color: " + BORDER_COLOR
                        + "; -fx-border-width: 1; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 20, 0, 0, 10);");

        // Simulated Document Content
        VBox docContent = new VBox(25);
        docContent.setPadding(new Insets(30));
        docContent.setOpacity(0.8);

        HBox docHead = new HBox();
        Region logoMock = new Region();
        logoMock.setPrefSize(100, 40);
        logoMock.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 4;");
        Region dateMock = new Region();
        dateMock.setPrefSize(60, 15);
        dateMock.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 4;");
        Region spacerH = new Region();
        HBox.setHgrow(spacerH, Priority.ALWAYS);
        docHead.getChildren().addAll(logoMock, spacerH, dateMock);

        Region titleMock = new Region();
        titleMock.setPrefSize(250, 25);
        titleMock.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 4;");

        VBox bodyMock = new VBox(12);
        for (int i = 0; i < 3; i++) {
            Region line = new Region();
            line.setPrefHeight(12);
            line.setMaxWidth(i == 2 ? 200 : Double.MAX_VALUE);
            line.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 4;");
            bodyMock.getChildren().add(line);
        }

        VBox detailMocks = new VBox(15);
        detailMocks.setPadding(new Insets(30, 0, 0, 0));
        detailMocks.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0;");
        for (int i = 0; i < 2; i++) {
            HBox row = new HBox();
            Region l = new Region();
            l.setPrefSize(80, 12);
            l.setStyle("-fx-background-color: rgba(255,255,255,0.1);");
            Region s = new Region();
            HBox.setHgrow(s, Priority.ALWAYS);
            Region r = new Region();
            r.setPrefSize(60, 12);
            r.setStyle("-fx-background-color: rgba(255,255,255,0.1);");
            row.getChildren().addAll(l, s, r);
            detailMocks.getChildren().add(row);
        }

        Region sealMock = new Region();
        StackPane.setAlignment(sealMock, Pos.BOTTOM_RIGHT);
        sealMock.setPrefSize(80, 40);
        sealMock.setMaxSize(80, 40);
        sealMock.setStyle(
                "-fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1; -fx-border-style: dashed; -fx-border-radius: 4;");
        Label sealLbl = new Label("Digital Seal");
        sealLbl.setTextFill(Color.web(TEXT_GRAY));
        sealLbl.setFont(Font.font(9));
        StackPane sealStack = new StackPane(sealMock, sealLbl);
        StackPane.setAlignment(sealStack, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(sealStack, new Insets(0, 30, 30, 0));

        docContent.getChildren().addAll(docHead, titleMock, bodyMock, detailMocks);
        docContainer.getChildren().addAll(docContent, sealStack);

        // Zoom/Overlay Buttons
        HBox zoomTools = new HBox(12);
        zoomTools.setPadding(new Insets(8, 12, 8, 12));
        zoomTools.setStyle(
                "-fx-background-color: rgba(0,0,0,0.5); -fx-background-radius: 20; -fx-border-color: rgba(255,255,255,0.1);");
        Label zoomIcon = new Label("\ud83d\udd0d"); // zoom
        zoomIcon.setTextFill(Color.WHITE);
        Label fullIcon = new Label("\u26f6"); // fullscreen
        fullIcon.setTextFill(Color.WHITE);
        zoomTools.getChildren().addAll(zoomIcon, fullIcon);
        StackPane.setAlignment(zoomTools, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(zoomTools, new Insets(15));
        docContainer.getChildren().add(zoomTools);

        previewArea.getChildren().add(docContainer);

        // File Details
        VBox detailsSec = new VBox(15);
        detailsSec.setPadding(new Insets(10, 20, 20, 20));
        Label detailsTitle = new Label("File Details");
        detailsTitle.setTextFill(Color.WHITE);
        detailsTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox detailsCard = new VBox(0);
        detailsCard.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 16;");
        detailsCard.getChildren().addAll(
                createDetailRow("Date Uploaded", "12 June 2024", false),
                createDetailRow("File Size", "450 KB", false),
                createDetailRow("Property", "Sunrise Apartments", true));
        detailsSec.getChildren().addAll(detailsTitle, detailsCard);

        scrollContent.getChildren().addAll(previewArea, detailsSec);

        // Footer
        VBox footer = new VBox();
        footer.setPadding(new Insets(15, 20, 35, 20));
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "cc; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");
        Button sendBtn = new Button("\u27a4  Send to Agent/Landlord");
        sendBtn.setMaxWidth(Double.MAX_VALUE);
        sendBtn.setPrefHeight(56);
        sendBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: #111813; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
        sendBtn.setOnAction(e -> MainApp.navigateTo(new SuccessView("Document Sent!",
                "The document has been successfully shared with your landlord.", "Back to Vault")));
        footer.getChildren().add(sendBtn);

        layout.getChildren().add(scroll);
        getChildren().addAll(layout, footer);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
    }

    private HBox createDetailRow(String lblStr, String valStr, boolean isPrimary) {
        HBox row = new HBox();
        row.setPadding(new Insets(15, 10, 15, 10));
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        Label lbl = new Label(lblStr);
        lbl.setTextFill(Color.web(TEXT_GRAY));
        lbl.setFont(Font.font(14));

        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);

        Label val = new Label(valStr);
        val.setTextFill(isPrimary ? Color.web(PRIMARY) : Color.WHITE);
        val.setFont(Font.font("System", isPrimary ? FontWeight.BOLD : FontWeight.MEDIUM, 14));

        row.getChildren().addAll(lbl, s, val);
        return row;
    }
}
