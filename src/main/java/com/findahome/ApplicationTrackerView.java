package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ApplicationTrackerView extends VBox {

        private static final String BACKGROUND_DARK = "#0b0e11";
        private static final String CARD_DARK = "#161b22";
        private static final String PRIMARY = "#137fec";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.08)";
        private static final String TEXT_GRAY = "#94a3b8";

        public ApplicationTrackerView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
                setAlignment(Pos.CENTER);

                // Top App Bar
                HBox appBar = new HBox();
                appBar.setAlignment(Pos.CENTER_LEFT);
                appBar.setPadding(new Insets(15, 20, 15, 20));
                appBar.setStyle("-fx-background-color: rgba(11, 14, 17, 0.9); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("‹"); // direct Unicode char
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new TenantProfileView()));

                Label title = new Label("Application Tracker");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);

                Label tuneBtn = new Label("⊰"); // direct Unicode char
                tuneBtn.setTextFill(Color.WHITE);
                tuneBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");

                appBar.getChildren().addAll(backBtn, title, tuneBtn);

                // Tabs
                HBox tabs = new HBox(0);
                tabs.setPadding(new Insets(0, 20, 0, 20));
                tabs.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                VBox activeTab = createTab("Active (3)", true);
                VBox pastTab = createTab("Past", false);
                HBox.setHgrow(activeTab, Priority.ALWAYS);
                HBox.setHgrow(pastTab, Priority.ALWAYS);
                tabs.getChildren().addAll(activeTab, pastTab);

                // Scrollable Content
                VBox scrollContent = new VBox(0);
                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // FIXED
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Timeline Holder
                StackPane timelineContainer = new StackPane();
                timelineContainer.setPadding(new Insets(25, 20, 150, 20));

                Region line = new Region();
                line.setStyle("-fx-background-color: #3b4754; -fx-background-radius: 2;");
                line.setPrefWidth(2);
                line.setMaxWidth(2);
                line.setMaxHeight(Double.MAX_VALUE);
                StackPane.setAlignment(line, Pos.TOP_LEFT);
                StackPane.setMargin(line, new Insets(15, 0, 15, 14));

                VBox entryList = new VBox(25);
                entryList.getChildren().addAll(
                                createApplicationEntry("Modern 2BR Apartment", "Nairobi West • $1,200/mo",
                                                "SUBMITTED", 1,
                                                "https://images.unsplash.com/photo-1545324418-f1d3c5b53571?q=80&w=400&auto=format&fit=crop",
                                                "✔"),
                                createApplicationEntry("Greenwood Estate", "Karen • $2,500/mo", "UNDER REVIEW", 2,
                                                "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?q=80&w=400&auto=format&fit=crop",
                                                "⏳"),
                                createApplicationEntry("Urban Loft", "Kilimani • $950/mo", "BACKGROUND CHECK", 3,
                                                "https://images.unsplash.com/photo-1493809842364-78817add7ffb?q=80&w=400&auto=format&fit=crop",
                                                "📄"));

                timelineContainer.getChildren().addAll(line, entryList);
                scrollContent.getChildren().add(timelineContainer);

                // Bottom Actions
                HBox bottomPanel = new HBox(20);
                bottomPanel.setAlignment(Pos.CENTER);
                bottomPanel.setPadding(new Insets(15, 30, 20, 30));
                bottomPanel.setStyle(
                                "-fx-background-color: " + CARD_DARK + "; -fx-background-radius: 20; -fx-border-color: "
                                                + BORDER_COLOR + ";");

                bottomPanel.getChildren().addAll(
                                createActionItem("forum", "Agent Chats"),
                                createActionItem("share", "Share List"),
                                createActionItem("help", "Support"));

                // Layout with BorderPane (FIXED)
                BorderPane layout = new BorderPane();
                layout.setTop(new VBox(appBar, tabs));
                layout.setCenter(scroll);
                layout.setBottom(bottomPanel);

                VBox desktopColumn = new VBox(layout);
                desktopColumn.setMaxWidth(600);
                desktopColumn.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
                VBox.setVgrow(desktopColumn, Priority.ALWAYS);

                HBox centerer = new HBox(desktopColumn);
                centerer.setAlignment(Pos.CENTER);
                HBox.setHgrow(desktopColumn, Priority.ALWAYS);
                VBox.setVgrow(centerer, Priority.ALWAYS);

                getChildren().setAll(centerer);

                // Offset content for sticky header
                scrollContent.setPadding(new Insets(120, 0, 150, 0));
        }

        private VBox createTab(String text, boolean active) {
                VBox v = new VBox(10);
                v.setAlignment(Pos.CENTER);
                Label l = new Label(text);
                l.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                l.setFont(Font.font("System", active ? FontWeight.BOLD : FontWeight.NORMAL, 14)); // FIXED
                Region indicator = new Region();
                indicator.setPrefHeight(2);
                indicator.setStyle("-fx-background-color: " + (active ? PRIMARY : "transparent") + ";");
                v.getChildren().addAll(l, indicator);
                v.setPadding(new Insets(15, 0, 0, 0));
                return v;
        }

        private HBox createApplicationEntry(String title, String meta, String status, int step, String imgUrl,
                        String iconSymbol) {
                HBox container = new HBox(15);
                container.setAlignment(Pos.TOP_LEFT);

                // Timeline Bullet
                StackPane bullet = new StackPane();
                bullet.setPrefSize(30, 30);
                bullet.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 15; -fx-border-color: "
                                + BACKGROUND_DARK + "; -fx-border-width: 4; -fx-border-radius: 15;");
                Label icon = new Label(iconSymbol);
                icon.setTextFill(Color.WHITE);
                icon.setStyle("-fx-font-size: 14;");
                bullet.getChildren().add(icon);

                // Card
                VBox card = new VBox(15);
                card.setPadding(new Insets(15));
                card.setStyle("-fx-background-color: " + CARD_DARK + "; -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR + ";");
                HBox.setHgrow(card, Priority.ALWAYS);

                HBox topInfo = new HBox(15);
                topInfo.setAlignment(Pos.CENTER_LEFT);

                ImageView iv = new ImageView();
                try {
                        iv.setImage(new Image(imgUrl, 80, 80, false, true));
                } catch (Exception e) {
                        System.err.println("Image failed: " + e.getMessage()); // FIXED logging
                }
                iv.setFitWidth(80);
                iv.setFitHeight(80);
                Rectangle clip = new Rectangle(80, 80);
                clip.setArcWidth(16);
                clip.setArcHeight(16);
                iv.setClip(clip);

                VBox textMeta = new VBox(5);
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 15));
                Label m = new Label(meta);
                m.setTextFill(Color.web(TEXT_GRAY));
                m.setFont(Font.font(12));

                Label statusBadge = new Label(status);
                String badgeColor = status.contains("REVIEW") ? "#f59e0b"
                                : status.contains("CHECK") ? "#a855f7" : PRIMARY;
                statusBadge.setStyle("-fx-background-color: " + badgeColor + "22; -fx-text-fill: " + badgeColor
                                + "; -fx-font-weight: bold; -fx-font-size: 9; -fx-padding: 3 8; -fx-background-radius: 4;");

                textMeta.getChildren().addAll(t, m, statusBadge);
                topInfo.getChildren().addAll(iv, textMeta);

                // Stepper
                HBox stepper = new HBox(5);
                for (int i = 1; i <= 4; i++) {
                        Region s = new Region();
                        s.setPrefHeight(4);
                        HBox.setHgrow(s, Priority.ALWAYS);
                        s.setStyle("-fx-background-radius: 2; -fx-background-color: "
                                        + (i <= step ? PRIMARY : "#3b4754") + ";");
                        stepper.getChildren().add(s);
                }

                HBox btnRow = new HBox(8);
                Button viewBtn = new Button("View Details");
                viewBtn.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(viewBtn, Priority.ALWAYS);
                viewBtn.setPrefHeight(36);
                viewBtn.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13; -fx-background-radius: 8;");
                viewBtn.setOnAction(e -> MainApp.navigateTo(new LeaseAgreementView()));

                Button chatBtn = new Button("💬");
                chatBtn.setPrefSize(36, 36);
                chatBtn.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: " + PRIMARY
                                + "; -fx-background-radius: 8; -fx-font-size: 16;");
                chatBtn.setOnAction(e -> MainApp.navigateTo(new ChatView()));

                btnRow.getChildren().addAll(viewBtn, chatBtn);

                card.getChildren().addAll(topInfo, stepper, btnRow);
                container.getChildren().addAll(bullet, card);
                return container;
        }

        private VBox createActionItem(String icon, String label) {
                VBox v = new VBox(5);
                v.setAlignment(Pos.CENTER);
                v.setCursor(javafx.scene.Cursor.HAND);

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(40, 40);
                iconBox.setStyle("-fx-background-color: rgba(19, 127, 236, 0.1); -fx-background-radius: 20;");
                Label i = new Label(icon.equals("forum") ? "💬" : icon.equals("share") ? "➚" : "❓");
                i.setTextFill(Color.web(PRIMARY));
                i.setStyle("-fx-font-size: 18;");
                iconBox.getChildren().add(i);

                Label l = new Label(label);
                l.setTextFill(Color.web(TEXT_GRAY));
                l.setFont(Font.font("System", FontWeight.BOLD, 10));

                v.getChildren().addAll(iconBox, l);
                return v;
        }
}