package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LeaseSigningCompleteView extends StackPane {

    private static final String BACKGROUND_DARK = "#101922";
    private static final String PRIMARY = "#137fec";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
    private static final String CARD_BG = "#1a1f2e";
    private static final String TEXT_GRAY = "#94a3b8";

    public LeaseSigningCompleteView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        Label closeBtn = new Label("\u2715");
        closeBtn.setTextFill(Color.WHITE);
        closeBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");
        closeBtn.setOnMouseClicked(e -> MainApp.showHome());

        Label title = new Label("Lease Completion");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Region spacer = new Region();
        spacer.setPrefWidth(20);

        header.getChildren().addAll(closeBtn, title, spacer);

        // Scrollable Content
        VBox scrollContent = new VBox(0);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Success Area
        VBox successArea = new VBox(25);
        successArea.setAlignment(Pos.CENTER);
        successArea.setPadding(new Insets(40, 20, 30, 20));

        StackPane iconStack = new StackPane();
        Circle glow = new Circle(80, Color.web(PRIMARY, 0.15));

        StackPane innerCircle = new StackPane();
        innerCircle.setPrefSize(100, 100);
        innerCircle.setMaxSize(100, 100);
        innerCircle.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 50;");
        Label icon = new Label("\u2714");
        icon.setTextFill(Color.WHITE);
        icon.setStyle("-fx-font-size: 50; -fx-font-weight: bold;");
        innerCircle.getChildren().add(icon);

        iconStack.getChildren().addAll(glow, innerCircle);

        VBox textStack = new VBox(10);
        textStack.setAlignment(Pos.CENTER);
        Label mainTitle = new Label("Lease Signed!");
        mainTitle.setTextFill(Color.WHITE);
        mainTitle.setFont(Font.font("System", FontWeight.BLACK, 32));

        Label subTitle = new Label("Congratulations! Your agreement for Sunrise Apartments is now legally binding.");
        subTitle.setTextFill(Color.web(TEXT_GRAY));
        subTitle.setFont(Font.font(15));
        subTitle.setWrapText(true);
        subTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        subTitle.setMaxWidth(300);

        textStack.getChildren().addAll(mainTitle, subTitle);
        successArea.getChildren().addAll(iconStack, textStack);

        // Status Card
        VBox statusCard = new VBox(12);
        statusCard.setPadding(new Insets(20));
        statusCard.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                + BORDER_COLOR + ";");
        VBox.setMargin(statusCard, new Insets(0, 20, 25, 20));

        HBox statusHeader = new HBox(8);
        statusHeader.setAlignment(Pos.CENTER_LEFT);
        Label vIcon = new Label("\u2705");
        vIcon.setTextFill(Color.web("#10b981"));
        Label vTitle = new Label("Status: Legally Binding");
        vTitle.setTextFill(Color.WHITE);
        vTitle.setFont(Font.font("System", FontWeight.BOLD, 15));
        statusHeader.getChildren().addAll(vIcon, vTitle);

        Label statusDesc = new Label("Digitally signed & secured on FindaHome");
        statusDesc.setTextFill(Color.web(TEXT_GRAY));
        statusDesc.setFont(Font.font(13));

        Label viewDoc = new Label("View Document \u27a4");
        viewDoc.setTextFill(Color.web(PRIMARY));
        viewDoc.setFont(Font.font("System", FontWeight.BOLD, 13));
        viewDoc.setPadding(new Insets(5, 0, 0, 0));
        viewDoc.setCursor(javafx.scene.Cursor.HAND);

        statusCard.getChildren().addAll(statusHeader, statusDesc, viewDoc);

        // Next Steps Timeline
        VBox nextSteps = new VBox(15);
        nextSteps.setPadding(new Insets(0, 20, 40, 20));
        Label nextStepsTitle = new Label("What's Next?");
        nextStepsTitle.setTextFill(Color.WHITE);
        nextStepsTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox timeline = new VBox(0);
        HBox paymentStep = createTimelineStep("\ud83d\udcb0", "Pay Security Deposit",
                "Within the next 24 hours to secure your booking.", true);
        paymentStep.setCursor(javafx.scene.Cursor.HAND);
        paymentStep.setOnMouseClicked(e -> MainApp.navigateTo(new DepositPaymentView()));

        timeline.getChildren().addAll(
                paymentStep,
                createTimelineStep("\ud83d\udcc5", "Schedule Move-in", "Available after payment confirmation.", true),
                createTimelineStep("\ud83d\udd11", "Collect Keys", "At the property on move-in day.", false));

        nextSteps.getChildren().addAll(nextStepsTitle, timeline);

        // Buttons
        VBox buttonArea = new VBox(15);
        buttonArea.setPadding(new Insets(0, 20, 60, 20));

        Button homeBtn = new Button("Return Home");
        homeBtn.setMaxWidth(Double.MAX_VALUE);
        homeBtn.setPrefHeight(56);
        homeBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
        homeBtn.setOnAction(e -> MainApp.showHome());

        HBox grid = new HBox(15);
        Button saveBtn = new Button("\u2913 Save PDF");
        saveBtn.setPrefHeight(56);
        saveBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(saveBtn, Priority.ALWAYS);
        saveBtn.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-border-color: "
                + BORDER_COLOR + ";");

        Button msgBtn = new Button("\ud83d\udcac Message");
        msgBtn.setPrefHeight(56);
        msgBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(msgBtn, Priority.ALWAYS);
        msgBtn.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-border-color: "
                + BORDER_COLOR + ";");
        msgBtn.setOnAction(e -> MainApp.navigateTo(new ChatView()));

        grid.getChildren().addAll(saveBtn, msgBtn);
        buttonArea.getChildren().addAll(homeBtn, grid);

        scrollContent.getChildren().addAll(successArea, statusCard, nextSteps, buttonArea);

        layout.getChildren().addAll(header, scroll);
        getChildren().add(layout);
    }

    private HBox createTimelineStep(String iconCode, String titleStr, String descStr, boolean hasLine) {
        HBox step = new HBox(15);

        VBox left = new VBox(5);
        left.setAlignment(Pos.TOP_CENTER);
        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(40, 40);
        iconBox.setStyle("-fx-background-color: " + (hasLine ? PRIMARY + "22" : "rgba(255,255,255,0.05)")
                + "; -fx-background-radius: 20;");
        Label icon = new Label(iconCode);
        icon.setTextFill(hasLine ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        icon.setStyle("-fx-font-size: 18;");
        iconBox.getChildren().add(icon);

        Region line = new Region();
        line.setPrefWidth(2);
        line.setPrefHeight(40);
        line.setStyle("-fx-background-color: " + BORDER_COLOR + ";");
        VBox.setVgrow(line, Priority.ALWAYS);

        left.getChildren().add(iconBox);
        if (hasLine)
            left.getChildren().add(line);

        VBox text = new VBox(4);
        text.setPadding(new Insets(8, 0, 15, 0));
        Label t = new Label(titleStr);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 15));
        if (!hasLine && !titleStr.contains("Pay"))
            t.setOpacity(0.6);

        Label d = new Label(descStr);
        d.setTextFill(Color.web(TEXT_GRAY));
        d.setFont(Font.font(13));
        d.setWrapText(true);
        if (!hasLine && !titleStr.contains("Pay"))
            d.setOpacity(0.6);

        text.getChildren().addAll(t, d);

        step.getChildren().addAll(left, text);
        return step;
    }
}
