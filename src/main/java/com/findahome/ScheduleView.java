package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ScheduleView extends VBox {

    private static final String BACKGROUND_DARK = "#0d1117";
    private static final String CARD_BG = "#161b22";
    private static final String PRIMARY = "#135bec";

    public ScheduleView() {
        setSpacing(20);
        setPadding(new Insets(15));
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        Label back = new Label("<");
        back.setTextFill(Color.WHITE);
        back.setOnMouseClicked(e -> MainApp.showHome());
        Label title = new Label("Select Viewing Schedule");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        header.getChildren().addAll(back, title);

        // Calendar (Simplified)
        VBox calendar = new VBox(10);
        Label calTitle = new Label("Choose a Date - October 2023");
        calTitle.setTextFill(Color.WHITE);
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        String[] days = { "S", "M", "T", "W", "T", "F", "S" };
        for (int i = 0; i < 7; i++) {
            Label d = new Label(days[i]);
            d.setTextFill(Color.GRAY);
            d.setMinWidth(40);
            d.setAlignment(Pos.CENTER);
            grid.add(d, i, 0);
        }
        for (int i = 1; i <= 31; i++) {
            Button btn = new Button(String.valueOf(i));
            btn.setPrefSize(40, 40);
            if (i == 5)
                btn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: white; -fx-background-radius: 8;");
            else
                btn.setStyle("-fx-background-color: " + CARD_BG + "; -fx-text-fill: white; -fx-background-radius: 8;");
            grid.add(btn, (i + 5) % 7, (i + 5) / 7 + 1);
        }
        calendar.getChildren().addAll(calTitle, grid);

        // Time Slots
        VBox timeSection = new VBox(10);
        Label timeTitle = new Label("Select Time Slot");
        timeTitle.setTextFill(Color.WHITE);
        FlowPane slots = new FlowPane(10, 10);
        slots.getChildren().addAll(
                createTimeSlot("09:00 AM", false),
                createTimeSlot("10:30 AM", true),
                createTimeSlot("01:00 PM", false),
                createTimeSlot("03:30 PM", false));
        timeSection.getChildren().addAll(timeTitle, slots);

        // Footer
        Button proceed = new Button("Proceed to Payment");
        proceed.setMaxWidth(Double.MAX_VALUE);
        proceed.setPrefHeight(50);
        proceed.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-font-weight: bold;");
        proceed.setOnAction(e -> MainApp.navigateTo(new PaymentView()));

        getChildren().addAll(header, calendar, timeSection, proceed);
    }

    private Button createTimeSlot(String time, boolean active) {
        Button btn = new Button(time);
        btn.setPadding(new Insets(8, 15, 8, 15));
        if (active)
            btn.setStyle("-fx-background-color: rgba(19, 91, 236, 0.1); -fx-border-color: " + PRIMARY
                    + "; -fx-text-fill: white; -fx-background-radius: 10; -fx-border-radius: 10;");
        else
            btn.setStyle("-fx-background-color: " + CARD_BG + "; -fx-text-fill: white; -fx-background-radius: 10;");
        return btn;
    }
}
