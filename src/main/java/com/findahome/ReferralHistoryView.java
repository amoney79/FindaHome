package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ReferralHistoryView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
    private static final String CARD_BG = "#1a2e20";
    private static final String TEXT_GRAY = "#9db9a6";

    public ReferralHistoryView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new InviteRewardView()));

        Label title = new Label("Referral History");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        HBox.setHgrow(title, Priority.ALWAYS);

        header.getChildren().addAll(backBtn, title, new Region());

        // Stats Summary
        HBox summary = new HBox(15);
        summary.setPadding(new Insets(25, 20, 25, 20));
        summary.setAlignment(Pos.CENTER);
        summary.getChildren().addAll(
                createStatCard("Total Earned", "$150", PRIMARY),
                createStatCard("Pending", "$50", "#eab308"),
                createStatCard("Successful", "3", "#3b82f6"));

        // List
        VBox list = new VBox(15);
        list.setPadding(new Insets(0, 20, 100, 20));

        Label listTitle = new Label("Recent Activity");
        listTitle.setTextFill(Color.WHITE);
        listTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        list.getChildren().add(listTitle);

        list.getChildren().addAll(
                createReferralRow("John Smith", "Lease Signed", "+$50", "Success", PRIMARY),
                createReferralRow("Sarah Johnson", "Viewing Scheduled", "$0", "Pending", "#eab308"),
                createReferralRow("Michael Brown", "Lease Signed", "+$50", "Success", PRIMARY),
                createReferralRow("Emily Davis", "Lease Signed", "+$50", "Success", PRIMARY));

        ScrollPane scroll = new ScrollPane(list);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        layout.getChildren().addAll(header, summary, scroll);
        getChildren().add(layout);
    }

    private VBox createStatCard(String label, String value, String color) {
        VBox card = new VBox(5);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefWidth(120);
        card.setStyle(
                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: " + color + "33;");

        Label v = new Label(value);
        v.setTextFill(Color.web(color));
        v.setFont(Font.font("System", FontWeight.BOLD, 20));

        Label l = new Label(label);
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font(10));

        card.getChildren().addAll(v, l);
        return card;
    }

    private HBox createReferralRow(String name, String activity, String amount, String status, String statusColor) {
        HBox row = new HBox(15);
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                + BORDER_COLOR + ";");

        Circle avatar = new Circle(20, Color.web(PRIMARY, 0.1));
        Label initials = new Label(name.substring(0, 1));
        initials.setTextFill(Color.web(PRIMARY));
        initials.setFont(Font.font("System", FontWeight.BOLD, 14));
        StackPane iconBox = new StackPane(avatar, initials);

        VBox info = new VBox(2);
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label a = new Label(activity);
        a.setTextFill(Color.web(TEXT_GRAY));
        a.setFont(Font.font(12));
        info.getChildren().addAll(n, a);
        HBox.setHgrow(info, Priority.ALWAYS);

        VBox statusBox = new VBox(2);
        statusBox.setAlignment(Pos.CENTER_RIGHT);
        Label amt = new Label(amount);
        amt.setTextFill(Color.WHITE);
        amt.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label st = new Label(status);
        st.setTextFill(Color.web(statusColor));
        st.setFont(Font.font("System", FontWeight.BOLD, 10));
        statusBox.getChildren().addAll(amt, st);

        row.getChildren().addAll(iconBox, info, statusBox);
        return row;
    }
}
