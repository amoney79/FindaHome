package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Circle;

public class NotificationView extends VBox {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";

    public NotificationView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15));
        header.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");
        Label back = new Label("<");
        back.setTextFill(Color.WHITE);
        back.setOnMouseClicked(e -> MainApp.showHome());
        Label title = new Label("Notifications");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);
        Label readAll = new Label("Read all");
        readAll.setTextFill(Color.web(PRIMARY));
        readAll.setFont(Font.font(12));
        header.getChildren().addAll(back, title, s, readAll);

        // Content
        VBox list = new VBox(0);
        list.getChildren().addAll(
                createNotification("New Viewing Request", "A tenant is interested for tomorrow at 10 AM.", "Just now",
                        true),
                createNotification("Verification Approved!", "You are now a Verified Agent.", "15m ago", true),
                createNotification("Payment Received", "Viewing Fee has been credited.", "2h ago", false));
        ScrollPane scroll = new ScrollPane(list);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        getChildren().addAll(header, scroll);
    }

    private HBox createNotification(String title, String desc, String time, boolean unread) {
        HBox row = new HBox(15);
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        if (unread)
            row.setStyle("-fx-background-color: rgba(19, 236, 91, 0.05);");
        row.setStyle(row.getStyle() + "-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        StackPane iconBox = new StackPane();
        Circle bg = new Circle(20, Color.web(PRIMARY, 0.1));
        Label icon = new Label("\ud83d\udd14");
        iconBox.getChildren().addAll(bg, icon);

        VBox text = new VBox(2);
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label d = new Label(desc);
        d.setTextFill(Color.GRAY);
        d.setFont(Font.font(12));
        Label tm = new Label(time);
        tm.setTextFill(Color.DARKGRAY);
        tm.setFont(Font.font(10));
        text.getChildren().addAll(t, d, tm);

        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);

        row.getChildren().addAll(iconBox, text, s);
        if (unread) {
            Circle dot = new Circle(4, Color.web(PRIMARY));
            row.getChildren().add(dot);
        }

        return row;
    }
}
