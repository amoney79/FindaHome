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

public class ApplicationTrackerView extends StackPane {

        private static final String BACKGROUND_DARK = "#0b0e11";
        private static final String CARD_DARK = "#161b22";
        private static final String PRIMARY = "#137fec";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.08)";
        private static final String TEXT_GRAY = "#94a3b8";

        public ApplicationTrackerView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox layout = new VBox(0);

                // Top App Bar
                HBox appBar = new HBox();
                appBar.setAlignment(Pos.CENTER_LEFT);
                appBar.setPadding(new Insets(15, 20, 15, 20));
                appBar.setStyle("-fx-background-color: rgba(11, 14, 17, 0.8); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u2039");
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new TenantProfileView()));

                Label title = new Label("Application Tracker");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);

                Label tuneBtn = new Label("\u22b0");
                tuneBtn.setTextFill(Color.WHITE);
                tuneBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");

                appBar.getChildren().addAll(backBtn, title, tuneBtn);

                // Tabs
                HBox tabs = new HBox(0);
                tabs.setPadding(new Insets(0, 20, 0, 20));
                tabs.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

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
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Timeline Holder
                AnchorPane timelineContainer = new AnchorPane();
                timelineContainer.setPadding(new Insets(25, 20, 150, 20));

                // Vertical Line
                Region line = new Region();
                line.setStyle("-fx-background-color: #3b4754; -fx-background-radius: 2;");
                line.setPrefWidth(2);
                AnchorPane.setLeftAnchor(line, 14.0);
                AnchorPane.setTopAnchor(line, 40.0);
                AnchorPane.setBottomAnchor(line, 40.0);

                VBox entryList = new VBox(25);
                AnchorPane.setLeftAnchor(entryList, 0.0);
                AnchorPane.setRightAnchor(entryList, 0.0);
                AnchorPane.setTopAnchor(entryList, 0.0);

                entryList.getChildren().addAll(
                                createApplicationEntry("Modern 2BR Apartment", "Nairobi West \u2022 $1,200/mo",
                                                "SUBMITTED", 1,
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuCq4YqtYpihlp3p4CrolbtVFw4XpBUuB2S3YFFGyxHvjNFVNmax3IuCpeDMGTTHNEzWuwQ8-A7p59POFKodS8-kel3zgijZQou5XC1MSzGSmBqAKb_-D5JGoIxHHxWuNmEwZeD9ncej1QRuEC9nQRRp5__PCMWymVdnwuQBDN0oJml5uIxS7tWwCjO2ykmj2WnFPGSR_d2aICJe7LoQqaB_lctSIN4Zioq641QJyBemCOMReLdJVbc-Sj2Obg5BqXPTDgB-AUsKWog",
                                                "\u2714"),
                                createApplicationEntry("Greenwood Estate", "Karen \u2022 $2,500/mo", "UNDER REVIEW", 2,
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuCZ597PLjDM-ScbEnRLc8EPrcoDoB4ZVwtzmAWkIIsP8972q5IT1Dm7lt4DOSJc4ZDDv1P6fTqePqYYuCyR0VjquBzfwwongBw7KQxjmm6n8py3Z6Jivm2ZLGdooO_dTObcWj1weD9eyeLhNXx-glJHJm7tAtLeLWlZNwW2pGdknGw1erNe6XHgVdGsBxPiRrerYFphOXpW4KwnsN7s78sPBZSIk3bdL2EBHqAHPz35aYq2UxlmWKvXSM6loHclcDe88OCLMBoNtns",
                                                "\u231b"),
                                createApplicationEntry("Urban Loft", "Kilimani \u2022 $950/mo", "BACKGROUND CHECK", 3,
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuBc7elV-2H5Cx1qkLpTxKpGfS-mk57rmhy66JM51n4VEWlscQtR0Q4826dGCJpqS9YzPcFb8LidO8R2qcwIs-tA1Gdn3ttdQNu3j7HVHB5MMOz03HAoWVNBFJE70i3mEQZslM0Ms6IBqLOtuCJ7eZdRqJTCqCQu6cSmiU8vEsllRV5pCU-upUQspbNc5aNChzmccp2j4rUShmDzlekviB6NVwoWr3hU3XcxQeVqQJft2EhgnAUDfRgM5X_IZ1SK-jmtUTSAIi67sCM",
                                                "\ud83d\udcc4"));

                timelineContainer.getChildren().addAll(line, entryList);
                scrollContent.getChildren().add(timelineContainer);

                // Bottom Actions
                HBox bottomPanel = new HBox(20);
                bottomPanel.setAlignment(Pos.CENTER);
                bottomPanel.setPadding(new Insets(15, 30, 20, 30));
                bottomPanel.setStyle(
                                "-fx-background-color: " + CARD_DARK + "; -fx-background-radius: 20; -fx-border-color: "
                                                + BORDER_COLOR + ";");
                StackPane.setAlignment(bottomPanel, Pos.BOTTOM_CENTER);
                StackPane.setMargin(bottomPanel, new Insets(0, 25, 40, 25));

                bottomPanel.getChildren().addAll(
                                createActionItem("forum", "Agent Chats"),
                                createActionItem("share", "Share List"),
                                createActionItem("help", "Support"));

                layout.getChildren().addAll(appBar, tabs, scroll);
                getChildren().addAll(layout, bottomPanel);
        }

        private VBox createTab(String text, boolean active) {
                VBox v = new VBox(10);
                v.setAlignment(Pos.CENTER);
                Label l = new Label(text);
                l.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                l.setFont(Font.font("System", active ? FontWeight.BOLD : FontWeight.MEDIUM, 14));
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
                bullet.setMinSize(30, 30);
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

                Button chatBtn = new Button("\ud83d\udcac");
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
                Label i = new Label(icon.equals("forum") ? "\ud83d\udcac" : icon.equals("share") ? "\u27a6" : "\u2753");
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
