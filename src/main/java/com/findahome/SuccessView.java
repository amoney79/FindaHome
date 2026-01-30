package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SuccessView extends VBox {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";

        public SuccessView() {
                setAlignment(Pos.CENTER);
                setSpacing(30);
                setPadding(new Insets(40, 20, 100, 20));
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Success Icon
                StackPane iconBox = new StackPane();
                Circle bg = new Circle(45, Color.web(PRIMARY, 0.2));
                Label icon = new Label("\u2714");
                icon.setTextFill(Color.web(PRIMARY));
                icon.setStyle("-fx-font-size: 40; -fx-font-weight: bold;");
                iconBox.getChildren().addAll(bg, icon);

                // Text
                VBox textBox = new VBox(10);
                textBox.setAlignment(Pos.CENTER);
                Label title = new Label("Booking Confirmed!");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 28));
                Label desc = new Label("Your viewing has been scheduled.\nThe agent will contact you soon.");
                desc.setTextFill(Color.web("#9db9a6"));
                desc.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                textBox.getChildren().addAll(title, desc);

                // Info Card
                VBox card = new VBox(15);
                card.setPadding(new Insets(20));
                card.setStyle("-fx-background-color: #1c271f; -fx-background-radius: 16;");
                card.getChildren().addAll(
                                createRow("\ud83d\udcc5", "Tuesday, Oct 24th", "Viewing Date"),
                                createRow("\u23f0", "10:30 AM", "Scheduled Time"));

                // Buttons
                VBox buttons = new VBox(12);
                Button chat = new Button("\ud83d\udcac Chat with Agent");
                chat.setMaxWidth(Double.MAX_VALUE);
                chat.setPrefHeight(55);
                chat.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: #111813; -fx-background-radius: 12; -fx-font-weight: bold;");
                chat.setOnAction(e -> MainApp.navigateTo(new ChatView()));

                Button cal = new Button("\ud83d\udcc5 Add to Calendar");
                cal.setMaxWidth(Double.MAX_VALUE);
                cal.setPrefHeight(55);
                cal.setStyle(
                                "-fx-background-color: #28392e; -fx-text-fill: white; -fx-background-radius: 12; -fx-font-weight: bold;");

                Button backExplore = new Button("Back to Explore");
                backExplore.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold;");
                backExplore.setOnAction(e -> MainApp.showHome());

                buttons.getChildren().addAll(chat, cal, backExplore);

                getChildren().addAll(iconBox, textBox, card, buttons);
        }

        private HBox createRow(String icon, String title, String sub) {
                HBox row = new HBox(15);
                row.setAlignment(Pos.CENTER_LEFT);
                Label i = new Label(icon);
                i.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-text-fill: " + PRIMARY
                                + "; -fx-padding: 10; -fx-background-radius: 10;");
                VBox text = new VBox(2);
                Label tl = new Label(title);
                tl.setTextFill(Color.WHITE);
                tl.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label sl = new Label(sub);
                sl.setTextFill(Color.web("#9db9a6"));
                sl.setFont(Font.font(10));
                text.getChildren().addAll(tl, sl);
                row.getChildren().addAll(i, text);
                return row;
        }
}
