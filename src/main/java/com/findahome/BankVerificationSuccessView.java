package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class BankVerificationSuccessView extends BorderPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String TEXT_GRAY = "#9db9a6";
    private static final String CARD_BG = "#1c271f";

    public BankVerificationSuccessView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Top Header (Close Button)
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(15, 20, 15, 20));

        Label title = new Label("Bank Verification");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 16));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label closeBtn = new Label("✕");
        closeBtn.setTextFill(Color.WHITE);
        closeBtn.setFont(Font.font("System", FontWeight.BOLD, 18));
        closeBtn.setCursor(javafx.scene.Cursor.HAND);
        closeBtn.setOnMouseClicked(e -> MainApp.navigateCached("landlord_dashboard", LandlordDashboardView::new));

        header.getChildren().addAll(new Region(), spacer, title, new Region(), spacer, closeBtn); // Centering trick if
                                                                                                  // needed, but simple
                                                                                                  // spacer works for
                                                                                                  // Title Left / Close
                                                                                                  // Right.
        // Actually screenshot shows Title Center, Close Right.
        // Let's re-do header layout to match screenshot more closely: Title centered, X
        // on right.
        header.getChildren().clear();
        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        header.getChildren().addAll(leftSpacer, title, rightSpacer, closeBtn);

        setTop(header);

        // Center Content
        VBox content = new VBox(25);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(20, 20, 40, 20));

        // 1. Success Icon (Green Circle with Checkmark)
        StackPane iconCircle = new StackPane();
        Circle c1 = new Circle(40, Color.web(PRIMARY, 0.1));
        Circle c2 = new Circle(30, Color.web(PRIMARY));
        // Glow effect simulation
        c2.setEffect(new javafx.scene.effect.DropShadow(20, Color.web(PRIMARY, 0.4)));

        Label check = new Label("✔");
        check.setTextFill(Color.BLACK); // Or dark green #102216
        check.setTextFill(Color.web(BACKGROUND_DARK));
        check.setFont(Font.font("System", FontWeight.BOLD, 24));

        iconCircle.getChildren().addAll(c1, c2, check);

        // 2. Text
        VBox texts = new VBox(8);
        texts.setAlignment(Pos.CENTER);
        Label h1 = new Label("Bank Account Linked");
        h1.setTextFill(Color.WHITE);
        h1.setFont(Font.font("System", FontWeight.BOLD, 24));

        Label sub = new Label("Your primary withdrawal method has been\nsuccessfully added to FindaHome.");
        sub.setTextFill(Color.web(TEXT_GRAY));
        sub.setTextAlignment(TextAlignment.CENTER);
        sub.setFont(Font.font("System", 14));
        sub.setLineSpacing(4);

        texts.getChildren().addAll(h1, sub);

        // 3. Bank Card
        HBox bankCard = new HBox(15);
        bankCard.setAlignment(Pos.CENTER_LEFT);
        bankCard.setPadding(new Insets(20));
        bankCard.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 16; -fx-border-color: #3b5443; -fx-border-radius: 16;");

        VBox bankInfo = new VBox(8);
        Label bankName = new Label("KCB Bank");
        bankName.setTextFill(Color.WHITE);
        bankName.setFont(Font.font("System", FontWeight.BOLD, 16));

        HBox details = new HBox(10);
        details.setAlignment(Pos.CENTER_LEFT);
        Label dots = new Label("•••• 5678");
        dots.setTextFill(Color.web(TEXT_GRAY));
        dots.setFont(Font.font("Monospaced", 14));

        Label badge = new Label("PRIMARY");
        badge.setTextFill(Color.web(BACKGROUND_DARK));
        badge.setFont(Font.font("System", FontWeight.BOLD, 10));
        badge.setPadding(new Insets(2, 6, 2, 6));
        badge.setStyle("-fx-background-color: #1a8f4e; -fx-text-fill: #13ec5b; -fx-background-radius: 4;");
        // Update badge contrast
        badge.setStyle("-fx-background-color: rgba(19, 236, 91, 0.15); -fx-text-fill: " + PRIMARY
                + "; -fx-background-radius: 4;");

        details.getChildren().addAll(dots, badge);
        bankInfo.getChildren().addAll(bankName, details);

        Region cardSpacer = new Region();
        HBox.setHgrow(cardSpacer, Priority.ALWAYS);

        // Bank Icon Placeholder
        StackPane bankIconContainer = new StackPane();
        Rectangle r = new Rectangle(40, 40, Color.web("#2a3b30"));
        r.setArcWidth(10);
        r.setArcHeight(10);
        Label bIcon = new Label("⟳"); // Placeholder
        bIcon.setTextFill(Color.web(TEXT_GRAY));
        bankIconContainer.getChildren().addAll(r, bIcon);

        bankCard.getChildren().addAll(bankInfo, cardSpacer, bankIconContainer);

        // 4. Info Box
        HBox infoBox = new HBox(12);
        infoBox.setAlignment(Pos.TOP_LEFT);
        infoBox.setPadding(new Insets(15));
        infoBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.05); -fx-background-radius: 12;");

        Label infoIcon = new Label("ℹ");
        infoIcon.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-background-radius: 10; -fx-padding: 0 5; -fx-font-weight: bold; -fx-font-size: 12; -fx-min-width: 20; -fx-min-height: 20; -fx-alignment: center;");

        Label infoText = new Label(
                "To ensure seamless rent withdrawals, we may send a small test deposit of less than $1.00 to your account within 24 hours.");
        infoText.setTextFill(Color.web("#d1d5db"));
        infoText.setFont(Font.font(13));
        infoText.setWrapText(true);
        infoText.setLineSpacing(2);
        HBox.setHgrow(infoText, Priority.ALWAYS);

        infoBox.getChildren().addAll(infoIcon, infoText);

        content.getChildren().addAll(new Region(), iconCircle, texts, bankCard, infoBox);
        VBox.setVgrow(content, Priority.ALWAYS); // Push footer down if needed, or just let VBox layout naturally

        // Footer Buttons
        VBox footer = new VBox(20);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(0, 20, 40, 20));

        Button earningsBtn = new Button("Go to Earnings");
        earningsBtn.setMaxWidth(Double.MAX_VALUE);
        earningsBtn.setPrefHeight(50);
        earningsBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
        earningsBtn.setOnAction(e -> MainApp.navigateCached("landlord_revenue", EarningsAnalyticsView::new));

        Label addLink = new Label("Add Another Account");
        addLink.setTextFill(Color.WHITE);
        addLink.setFont(Font.font("System", FontWeight.BOLD, 14));
        addLink.setCursor(javafx.scene.Cursor.HAND);
        addLink.setOnMouseClicked(e -> MainApp.navigateCached("bank_details_entry", BankDetailsEntryView::new));

        footer.getChildren().addAll(earningsBtn, addLink);

        setCenter(content);
        setBottom(footer);
    }
}
