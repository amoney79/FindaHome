package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ManageAlertsView extends StackPane {

        private static final String BACKGROUND_DARK = "#101622";
        private static final String PRIMARY = "#13ec5b"; // Green theme
        private static final String TEXT_GRAY = "#9da6b9";
        private static final String CARD_BG = "#1c222c";
        private static final String DIVIDER_COLOR = "#2a3544";

        public ManageAlertsView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                BorderPane mainLayout = new BorderPane();

                // Header
                VBox header = new VBox();
                header.setStyle(
                                "-fx-background-color: rgba(16, 22, 34, 0.8); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 0, 5, 0, 0);");
                header.setPadding(new Insets(15, 20, 15, 20));

                HBox navBar = new HBox(15);
                navBar.setAlignment(Pos.CENTER_LEFT);

                StackPane backBtn = new StackPane();
                backBtn.setPrefSize(40, 40);
                backBtn.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 20; -fx-cursor: hand;");
                Label arrow = new Label("\u2039");
                arrow.setFont(Font.font("System", FontWeight.BOLD, 24));
                arrow.setTextFill(Color.WHITE);
                backBtn.getChildren().add(arrow);
                backBtn.setOnMouseClicked(e -> MainApp.showHome());

                Label title = new Label("My Alerts");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setMaxWidth(Double.MAX_VALUE);
                title.setAlignment(Pos.CENTER);
                title.setCursor(javafx.scene.Cursor.HAND);
                title.setOnMouseClicked(e -> MainApp.navigateTo(new MatchNotificationView()));
                HBox.setHgrow(title, Priority.ALWAYS);

                Label testBtn = new Label("Test");
                testBtn.setTextFill(Color.web(PRIMARY));
                testBtn.setFont(Font.font("System", FontWeight.BOLD, 14));
                testBtn.setCursor(javafx.scene.Cursor.HAND);

                navBar.getChildren().addAll(backBtn, title, testBtn);
                header.getChildren().add(navBar);
                mainLayout.setTop(header);

                // Content
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

                VBox content = new VBox(20);
                content.setPadding(new Insets(20));
                content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Summary
                Label summary = new Label(
                                "You have 3 active property alerts. You'll receive a notification when new matching properties are listed.");
                summary.setTextFill(Color.web(TEXT_GRAY));
                summary.setWrapText(true);
                summary.setFont(Font.font(14));

                // Alert List
                VBox alertList = new VBox(15);
                alertList.getChildren().addAll(
                                createAlertCard("Nairobi > Westlands > Kileleshwa", "< 50k KES | 1-bed apartment",
                                                "Last match: 2h ago",
                                                true),
                                createAlertCard("Mombasa > Nyali", "< 100k KES | 3-bed house", "Last match: Yesterday",
                                                true),
                                createAlertCard("Nairobi > Kilimani", "Any price | 2-bed", "Paused", false));

                // Test Notification Section
                VBox testSection = new VBox(15);
                testSection.setPadding(new Insets(20));
                testSection.setAlignment(Pos.CENTER);
                testSection.setStyle(
                                "-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 16; -fx-border-color: rgba(19, 236, 91, 0.2); -fx-border-radius: 16;");

                StackPane sendIcon = new StackPane();
                sendIcon.setPrefSize(48, 48);
                sendIcon.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 24;");
                Label sendLbl = new Label("\u27A4"); // Send icon
                sendLbl.setTextFill(Color.WHITE);
                sendIcon.getChildren().add(sendLbl);

                VBox testText = new VBox(5);
                testText.setAlignment(Pos.CENTER);
                Label tt1 = new Label("Not getting updates?");
                tt1.setTextFill(Color.WHITE);
                tt1.setFont(Font.font("System", FontWeight.BOLD, 16));
                Label tt2 = new Label("Send a test notification to check your device settings.");
                tt2.setTextFill(Color.web(TEXT_GRAY));
                tt2.setWrapText(true);
                tt2.setAlignment(Pos.CENTER);
                testText.getChildren().addAll(tt1, tt2);

                Button sendTestBtn = new Button("Send Test Notification");
                sendTestBtn.setMaxWidth(Double.MAX_VALUE);
                sendTestBtn.setPrefHeight(44);
                sendTestBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-cursor: hand;");
                sendTestBtn.setOnAction(e -> MainApp.navigateTo(new PushNotificationView()));

                testSection.getChildren().addAll(sendIcon, testText, sendTestBtn);

                content.getChildren().addAll(summary, alertList, testSection);
                scrollPane.setContent(content);
                mainLayout.setCenter(scrollPane);

                getChildren().add(mainLayout);

                // Floating Action Button
                Button fab = new Button("+ Add Alert");
                fab.setPrefHeight(56);
                fab.setPadding(new Insets(0, 24, 0, 24));
                fab.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 28; -fx-effect: dropshadow(three-pass-box, rgba(19, 236, 91, 0.4), 10, 0, 0, 4);");
                fab.setOnAction(e -> MainApp.navigateTo(new NeighborhoodAlertsView()));

                StackPane.setAlignment(fab, Pos.BOTTOM_RIGHT);
                StackPane.setMargin(fab, new Insets(0, 24, 24, 0));
                getChildren().add(fab);
        }

        private VBox createAlertCard(String location, String criteria, String status, boolean active) {
                VBox card = new VBox(0);
                card.setStyle("-fx-background-color: " + CARD_BG
                                + "; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 1);");
                if (!active) {
                        card.setOpacity(0.7);
                }

                // Top Part
                HBox top = new HBox(15);
                top.setPadding(new Insets(16));
                top.setAlignment(Pos.CENTER_LEFT);

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(48, 48);
                iconBox.setStyle("-fx-background-color: "
                                + (active ? "rgba(19, 236, 91, 0.1)" : "rgba(157, 166, 185, 0.1)")
                                + "; -fx-background-radius: 12;");
                Label icon = new Label(active ? "\uD83D\uDD14" : "\uD83D\uDD15");
                icon.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                iconBox.getChildren().add(icon);

                VBox info = new VBox(4);
                Label loc = new Label(location);
                loc.setTextFill(Color.WHITE);
                loc.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label crit = new Label(criteria);
                crit.setTextFill(Color.web(TEXT_GRAY));
                crit.setFont(Font.font(12));
                info.getChildren().addAll(loc, crit);
                HBox.setHgrow(info, Priority.ALWAYS);

                // Switch
                StackPane toggle = new StackPane();
                toggle.setPrefSize(52, 32);
                Rectangle bg = new Rectangle(52, 32);
                bg.setArcWidth(32);
                bg.setArcHeight(32);
                bg.setFill(active ? Color.web(PRIMARY) : Color.web("#475569"));
                Circle knob = new Circle(12);
                knob.setFill(Color.WHITE);
                knob.setTranslateX(active ? 10 : -10);
                toggle.getChildren().addAll(bg, knob);

                top.getChildren().addAll(iconBox, info, toggle);

                // Bottom Part
                HBox bottom = new HBox();
                bottom.setPadding(new Insets(8, 16, 8, 16));
                bottom.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 0 0 12 12; -fx-border-color: "
                                                + DIVIDER_COLOR + "; -fx-border-width: 1 0 0 0;");
                bottom.setAlignment(Pos.CENTER_LEFT);

                Label stat = new Label(status);
                stat.setTextFill(Color.web(TEXT_GRAY));
                stat.setFont(Font.font("System", FontWeight.BOLD, 10));
                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                Label del = new Label("\uD83D\uDDD1"); // Trash icon
                del.setTextFill(Color.web(TEXT_GRAY));
                del.setStyle("-fx-font-size: 16; -fx-cursor: hand;");
                del.setOnMouseEntered(e -> del.setTextFill(Color.RED));
                del.setOnMouseExited(e -> del.setTextFill(Color.web(TEXT_GRAY)));

                bottom.getChildren().addAll(stat, spacer, del);

                card.getChildren().addAll(top, bottom);
                return card;
        }
}
