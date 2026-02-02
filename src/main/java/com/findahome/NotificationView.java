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
                header.setPadding(new Insets(15, 20, 10, 20));

                Label back = new Label("\u2039"); // Back arrow
                back.setTextFill(Color.WHITE);
                back.setStyle("-fx-font-size: 28; -fx-cursor: hand; -fx-padding: 0 10 0 0;");
                back.setOnMouseClicked(e -> MainApp.showHome());

                Label title = new Label("Notifications");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);

                Label readAll = new Label("Read all");
                readAll.setTextFill(Color.web(PRIMARY));
                readAll.setFont(Font.font("System", FontWeight.SEMI_BOLD, 14));
                readAll.setCursor(javafx.scene.Cursor.HAND);

                header.getChildren().addAll(back, title, readAll);

                // Tabs
                HBox tabs = new HBox(30);
                tabs.setPadding(new Insets(15, 20, 0, 20));
                tabs.setStyle("-fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

                VBox allTab = createTab("All", true);
                VBox bookingsTab = createTab("Bookings", false);
                VBox systemTab = createTab("System", false);

                tabs.getChildren().addAll(allTab, bookingsTab, systemTab);

                // Content
                VBox list = new VBox(0);

                allTab.setOnMouseClicked(e -> {
                        updateTabStyles(tabs, allTab);
                        list.getChildren().clear();
                        addNotifications(list, "All");
                });

                bookingsTab.setOnMouseClicked(e -> {
                        updateTabStyles(tabs, bookingsTab);
                        list.getChildren().clear();
                        addNotifications(list, "Bookings");
                });

                systemTab.setOnMouseClicked(e -> {
                        updateTabStyles(tabs, systemTab);
                        list.getChildren().clear();
                        addNotifications(list, "System");
                });

                addNotifications(list, "All");

                ScrollPane scroll = new ScrollPane(list);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // iOS Home Indicator
                StackPane footer = new StackPane();
                footer.setPadding(new Insets(10, 0, 15, 0));
                Region indicator = new Region();
                indicator.setPrefSize(120, 5);
                indicator.setMaxSize(120, 5);
                indicator.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 2.5;");
                footer.getChildren().add(indicator);

                getChildren().addAll(header, tabs, scroll, footer);
        }

        private void updateTabStyles(HBox tabs, VBox activeTab) {
                tabs.getChildren().forEach(node -> {
                        if (node instanceof VBox) {
                                VBox tab = (VBox) node;
                                ((Label) tab.getChildren().get(0)).setTextFill(Color.web("white", 0.5));
                                tab.getChildren().get(1).setStyle("-fx-background-color: transparent;");
                        }
                });
                ((Label) activeTab.getChildren().get(0)).setTextFill(Color.WHITE);
                activeTab.getChildren().get(1).setStyle("-fx-background-color: " + PRIMARY + ";");
        }

        private void addNotifications(VBox list, String filter) {
                if (filter.equals("All") || filter.equals("Bookings")) {
                        list.getChildren().add(createNotification("New Viewing Request for Apartment A",
                                        "A tenant is interested in a physical tour for tomorrow at 10 AM.", "Just now",
                                        "\ud83d\uddd3\ufe0f", true, PRIMARY));
                }
                if (filter.equals("All") || filter.equals("System")) {
                        list.getChildren().add(createNotification("Verification Approved!",
                                        "You are now a Verified Agent on FindaHome. Enjoy premium perks.", "15m ago",
                                        "\ud83d\udee1\ufe0f", true, "#60a5fa"));
                }
                if (filter.equals("All") || filter.equals("Bookings")) {
                        list.getChildren()
                                        .add(createNotification("Payment Received",
                                                        "Viewing Fee for Apartment B has been credited to your wallet.",
                                                        "2h ago", "\ud83d\udcb0", false, "white"));
                        list.getChildren().add(createNotification("New Message from Tenant John",
                                        "\"Hi, is the parking space included in the monthly rent?\"", "5h ago",
                                        "\ud83d\udcac", false,
                                        "white"));
                }
                if (filter.equals("All") || filter.equals("System")) {
                        list.getChildren().add(createNotification("System Update: Marketplace Fees",
                                        "We have updated our terms regarding lead commissions for 2024.", "Yesterday",
                                        "\ud83d\udce3",
                                        false, "white"));
                }
        }

        private VBox createTab(String name, boolean active) {
                VBox tab = new VBox(8);
                tab.setAlignment(Pos.CENTER);
                Label lbl = new Label(name);
                lbl.setTextFill(active ? Color.WHITE : Color.web("white", 0.5));
                lbl.setFont(Font.font("System", FontWeight.BOLD, 14));

                Region indicator = new Region();
                indicator.setPrefHeight(3);
                indicator.setPrefWidth(active ? 40 : 0);
                indicator.setStyle("-fx-background-color: " + (active ? PRIMARY : "transparent") + ";");

                tab.getChildren().addAll(lbl, indicator);
                tab.setCursor(javafx.scene.Cursor.HAND);
                return tab;
        }

        private HBox createNotification(String title, String desc, String time, String iconStr, boolean unread,
                        String iconColor) {
                HBox row = new HBox(15);
                row.setPadding(new Insets(15, 20, 15, 20));
                row.setAlignment(Pos.CENTER_LEFT);
                row.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");
                if (unread) {
                        row.setStyle(row.getStyle() + "-fx-background-color: rgba(19, 236, 91, 0.05);");
                }

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(48, 48);
                iconBox.setStyle("-fx-background-color: " + iconColor + "1a; -fx-background-radius: 12;");
                Label icon = new Label(iconStr);
                icon.setTextFill(Color.web(iconColor));
                icon.setStyle("-fx-font-size: 20;");
                iconBox.getChildren().add(icon);

                VBox text = new VBox(2);
                Label t = new Label(title);
                t.setTextFill(unread ? Color.WHITE : Color.web("white", 0.9));
                t.setFont(Font.font("System", unread ? FontWeight.BOLD : FontWeight.MEDIUM, 15));
                t.setWrapText(false);
                t.setMaxWidth(280);

                Label d = new Label(desc);
                d.setTextFill(unread ? Color.web("white", 0.6) : Color.web("white", 0.5));
                d.setFont(Font.font(13));
                d.setWrapText(true);
                d.setMaxWidth(280);

                Label tm = new Label(time);
                tm.setTextFill(Color.web("white", 0.4));
                tm.setFont(Font.font("System", FontWeight.MEDIUM, 11));
                VBox.setMargin(tm, new Insets(4, 0, 0, 0));

                text.getChildren().addAll(t, d, tm);

                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);

                row.getChildren().addAll(iconBox, text, s);

                if (unread) {
                        Circle dot = new Circle(5, Color.web(PRIMARY));
                        dot.setEffect(new javafx.scene.effect.DropShadow(12, Color.web(PRIMARY, 0.6)));
                        row.getChildren().add(dot);
                }

                row.setCursor(javafx.scene.Cursor.HAND);
                return row;
        }
}
