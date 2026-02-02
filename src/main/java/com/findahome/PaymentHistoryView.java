package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PaymentHistoryView extends StackPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String CARD_BG = "#1c222c";
    private static final String TEXT_GRAY = "#9da6b9";
    private static final String SUCCESS_GREEN = "#13ec5b";

    public PaymentHistoryView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new TenantProfileView()));

        Label title = new Label("Payment History");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));

        header.getChildren().addAll(backBtn, title);

        // Content
        ScrollPane scroll = new ScrollPane();
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        content.getChildren().addAll(
                createTransactionCard("Viewing Fee - Kilimani Apt", "Oct 1, 2023", "- KSh 1,500", true),
                createTransactionCard("Rent Deposit - Westlands", "Sep 15, 2023", "- KSh 45,000", true));

        scroll.setContent(content);
        layout.getChildren().addAll(header, scroll);
        getChildren().add(layout);
    }

    private HBox createTransactionCard(String title, String date, String amount, boolean success) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12;");

        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(40, 40);
        iconBox.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 20;");
        Label icon = new Label("\u2713");
        icon.setTextFill(Color.web(SUCCESS_GREEN));
        iconBox.getChildren().add(icon);

        VBox info = new VBox(2);
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label d = new Label(date);
        d.setTextFill(Color.web(TEXT_GRAY));
        d.setFont(Font.font(12));
        info.getChildren().addAll(t, d);
        HBox.setHgrow(info, Priority.ALWAYS);

        Label amt = new Label(amount);
        amt.setTextFill(Color.WHITE);
        amt.setFont(Font.font("System", FontWeight.BOLD, 14));

        card.getChildren().addAll(iconBox, info, amt);
        return card;
    }
}
