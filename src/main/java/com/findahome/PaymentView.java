package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PaymentView extends VBox {

        private static final String BACKGROUND_DARK = "#101622";
        private static final String CARD_BG = "#161b22";
        private static final String PRIMARY = "#135bec";

        public PaymentView() {
                setSpacing(20);
                setPadding(new Insets(15));
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                Label back = new Label("<");
                back.setTextFill(Color.WHITE);
                back.setOnMouseClicked(e -> MainApp.navigateTo(new ScheduleView()));
                Label title = new Label("Secure Payment");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                header.getChildren().addAll(back, title);

                // Summary
                VBox summary = new VBox(5);
                summary.setPadding(new Insets(15));
                summary.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 12; -fx-border-color: #30363d; -fx-border-radius: 12;");
                Label prop = new Label("2-Bedroom Apartment, Kilimani");
                prop.setTextFill(Color.WHITE);
                prop.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label type = new Label("Viewing Fee Booking");
                type.setTextFill(Color.web(PRIMARY));
                type.setFont(Font.font(12));
                summary.getChildren().addAll(prop, type);

                // Amount
                VBox amountBox = new VBox(5);
                amountBox.setAlignment(Pos.CENTER);
                Label sub = new Label("Total Payable Amount");
                sub.setTextFill(Color.GRAY);
                Label amt = new Label("KSh 1,500.00");
                amt.setTextFill(Color.WHITE);
                amt.setFont(Font.font("System", FontWeight.BOLD, 36));
                amountBox.getChildren().addAll(sub, amt);

                // Methods
                VBox methods = new VBox(10);
                Label mTitle = new Label("Select Payment Method");
                mTitle.setTextFill(Color.WHITE);
                mTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

                ToggleGroup group = new ToggleGroup();
                methods.getChildren().addAll(mTitle,
                                createMethod("M-Pesa", "Pay via STK Push", true, group),
                                createMethod("Credit / Debit Card", "Visa, Mastercard", false, group),
                                createMethod("Mobile Wallet", "Airtel Money", false, group));

                // Pay Button
                Button pay = new Button("\ud83d\udd12 Pay KSh 1,500.00 Now");
                pay.setMaxWidth(Double.MAX_VALUE);
                pay.setPrefHeight(60);
                pay.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 16; -fx-font-weight: bold; -fx-font-size: 16;");
                pay.setOnAction(e -> MainApp.navigateTo(new SuccessView()));

                getChildren().addAll(header, summary, amountBox, methods, pay);
        }

        private HBox createMethod(String name, String sub, boolean active, ToggleGroup group) {
                HBox row = new HBox(15);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(15));
                row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; "
                                + (active ? "-fx-border-color: " + PRIMARY + ";" : "-fx-border-color: transparent;"));

                RadioButton rb = new RadioButton();
                rb.setToggleGroup(group);
                rb.setSelected(active);

                VBox info = new VBox(2);
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label s = new Label(sub);
                s.setTextFill(Color.GRAY);
                s.setFont(Font.font(10));
                info.getChildren().addAll(n, s);

                row.getChildren().addAll(rb, info);
                return row;
        }
}
