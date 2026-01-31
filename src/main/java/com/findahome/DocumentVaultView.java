package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DocumentVaultView extends StackPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
        private static final String CARD_BG = "rgba(255,255,255,0.05)";
        private static final String TEXT_GRAY = "#94a3b8";

        public DocumentVaultView() {
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
                backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new TenantProfileView()));

                Label title = new Label("My Documents");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setPadding(new Insets(0, 0, 0, 15));

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button lockBtn = new Button("\ud83d\udee1");
                lockBtn.setPrefSize(40, 40);
                lockBtn.setStyle("-fx-background-color: " + PRIMARY + "22; -fx-text-fill: " + PRIMARY
                                + "; -fx-background-radius: 8; -fx-font-size: 16;");

                header.getChildren().addAll(backBtn, title, spacer, lockBtn);

                // Scroll Content
                VBox content = new VBox(25);
                content.setPadding(new Insets(20));
                ScrollPane scroll = new ScrollPane(content);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Storage Usage Card
                VBox storageCard = new VBox(15);
                storageCard.setPadding(new Insets(20));
                storageCard.setStyle(
                                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                                + BORDER_COLOR + ";");

                HBox storageHead = new HBox();
                storageHead.setAlignment(Pos.BOTTOM_LEFT);
                VBox storageText = new VBox(2);
                Label usageTitle = new Label("Vault Storage");
                usageTitle.setTextFill(Color.web(TEXT_GRAY));
                usageTitle.setFont(Font.font(13));
                Label usageVal = new Label("15MB ");
                usageVal.setTextFill(Color.WHITE);
                usageVal.setFont(Font.font("System", FontWeight.BOLD, 20));
                Label usageTotal = new Label("of 100MB");
                usageTotal.setTextFill(Color.web(TEXT_GRAY));
                usageTotal.setFont(Font.font(13));
                HBox usageLine = new HBox(usageVal, usageTotal);
                usageLine.setAlignment(Pos.BOTTOM_LEFT);
                storageText.getChildren().addAll(usageTitle, usageLine);

                Region sS = new Region();
                HBox.setHgrow(sS, Priority.ALWAYS);
                Label secureBadge = new Label("SECURE");
                secureBadge.setPadding(new Insets(4, 10, 4, 10));
                secureBadge.setStyle("-fx-background-color: " + PRIMARY + "33; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-font-size: 10; -fx-background-radius: 20;");
                storageHead.getChildren().addAll(storageText, sS, secureBadge);

                ProgressBar pb = new ProgressBar(0.15);
                pb.setMaxWidth(Double.MAX_VALUE);
                pb.setPrefHeight(10);
                pb.setStyle("-fx-accent: " + PRIMARY
                                + "; -fx-control-inner-background: rgba(255,255,255,0.1); -fx-background-radius: 10;");

                HBox storageFoot = new HBox();
                Label availLbl = new Label("85MB available");
                availLbl.setTextFill(Color.web(TEXT_GRAY));
                availLbl.setFont(Font.font(12));
                Region sfS = new Region();
                HBox.setHgrow(sfS, Priority.ALWAYS);
                Label upgradeBtn = new Label("Upgrade Storage");
                upgradeBtn.setTextFill(Color.web(PRIMARY));
                upgradeBtn.setFont(Font.font("System", FontWeight.BOLD, 12));
                upgradeBtn.setCursor(javafx.scene.Cursor.HAND);
                storageFoot.getChildren().addAll(availLbl, sfS, upgradeBtn);

                storageCard.getChildren().addAll(storageHead, pb, storageFoot);

                // Categories Section
                VBox categoriesSec = new VBox(15);
                Label catTitle = new Label("Categories");
                catTitle.setTextFill(Color.WHITE);
                catTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                GridPane grid = new GridPane();
                grid.setHgap(15);
                grid.setVgap(15);
                grid.add(createCategoryCard("Signed Leases", "3 files", "\ud83d\udcc4", PRIMARY), 0, 0);
                grid.add(createCategoryCard("Receipts", "12 files", "\ud83e\uddfe", "#3b82f6"), 1, 0);
                grid.add(createCategoryCard("ID Docs", "2 files", "\ud83e\udeaa", "#a855f7"), 0, 1);
                grid.add(createCategoryCard("Maintenance", "5 files", "\ud83d\udee0", "#f97316"), 1, 1);

                ColumnConstraints c1 = new ColumnConstraints();
                c1.setPercentWidth(50);
                ColumnConstraints c2 = new ColumnConstraints();
                c2.setPercentWidth(50);
                grid.getColumnConstraints().addAll(c1, c2);

                categoriesSec.getChildren().addAll(catTitle, grid);

                // Recent Files
                VBox recentSec = new VBox(15);
                HBox recentHead = new HBox();
                Label rTitle = new Label("Recent Files");
                rTitle.setTextFill(Color.WHITE);
                rTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                Region rS = new Region();
                HBox.setHgrow(rS, Priority.ALWAYS);
                Label viewAll = new Label("View All");
                viewAll.setTextFill(Color.web(PRIMARY));
                viewAll.setFont(Font.font("System", FontWeight.BOLD, 14));
                recentHead.getChildren().addAll(rTitle, rS, viewAll);

                VBox fileList = new VBox(12);
                fileList.getChildren().addAll(
                                createFileRow("Apartment_Lease_2023.pdf", "Oct 12, 2023 \u2022 2.4 MB", "\ud83d\udcc4",
                                                "#ef4444"),
                                createFileRow("Rent_Receipt_Nov.png", "Nov 01, 2023 \u2022 850 KB", "\ud83d\uddbc",
                                                "#3b82f6"),
                                createFileRow("National_ID_Scan.jpg", "Sep 24, 2023 \u2022 1.2 MB", "\ud83d\udee1",
                                                "#22c55e"),
                                createFileRow("Kitchen_Repair_Log.docx", "Aug 15, 2023 \u2022 45 KB", "\ud83d\udcc4",
                                                "#94a3b8"));

                recentSec.getChildren().addAll(recentHead, fileList);

                content.getChildren().addAll(storageCard, categoriesSec, recentSec);

                // Floating Action Button
                Button fab = new Button("+");
                fab.setPrefSize(56, 56);
                fab.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-size: 32; -fx-font-weight: bold; -fx-background-radius: 28; -fx-cursor: hand;");
                StackPane.setAlignment(fab, Pos.BOTTOM_RIGHT);
                StackPane.setMargin(fab, new Insets(0, 20, 100, 0));

                layout.getChildren().addAll(header, scroll);
                getChildren().addAll(layout, fab);
        }

        private VBox createCategoryCard(String title, String count, String icon, String color) {
                VBox card = new VBox(15);
                card.setPadding(new Insets(15));
                card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR + ";");

                StackPane iconBox = new StackPane();
                iconBox.setAlignment(Pos.CENTER);
                iconBox.setPrefSize(40, 40);
                iconBox.setMaxSize(40, 40);
                iconBox.setStyle("-fx-background-color: " + color + "22; -fx-background-radius: 8;");
                Label iconLbl = new Label(icon);
                iconLbl.setTextFill(Color.web(color));
                iconLbl.setStyle("-fx-font-size: 18;");
                iconBox.getChildren().add(iconLbl);

                VBox text = new VBox(2);
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label c = new Label(count);
                c.setTextFill(Color.web(TEXT_GRAY));
                c.setFont(Font.font(11));
                text.getChildren().addAll(t, c);

                card.getChildren().addAll(iconBox, text);
                return card;
        }

        private HBox createFileRow(String name, String meta, String icon, String color) {
                HBox row = new HBox(12);
                row.setPadding(new Insets(12));
                row.setAlignment(Pos.CENTER_LEFT);
                row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                                + BORDER_COLOR + ";");

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(40, 40);
                iconBox.setStyle("-fx-background-color: " + color + "22; -fx-background-radius: 8;");
                Label iconLbl = new Label(icon);
                iconLbl.setTextFill(Color.web(color));
                iconLbl.setStyle("-fx-font-size: 18;");
                iconBox.getChildren().add(iconLbl);

                VBox text = new VBox(2);
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 13));
                n.setMaxWidth(160);
                Label m = new Label(meta);
                m.setTextFill(Color.web(TEXT_GRAY));
                m.setFont(Font.font(10));
                text.getChildren().addAll(n, m);

                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);

                HBox actions = new HBox(5);
                Button dl = new Button("\u2913");
                dl.setStyle("-fx-background-color: transparent; -fx-text-fill: " + TEXT_GRAY
                                + "; -fx-font-size: 16; -fx-cursor: hand;");
                Button sh = new Button("\u27a6");
                sh.setStyle("-fx-background-color: transparent; -fx-text-fill: " + TEXT_GRAY
                                + "; -fx-font-size: 16; -fx-cursor: hand;");
                actions.getChildren().addAll(dl, sh);

                row.getChildren().addAll(iconBox, text, s, actions);
                row.setCursor(javafx.scene.Cursor.HAND);
                row.setOnMouseClicked(e -> MainApp.navigateTo(new DocumentPreviewView(name)));
                return row;
        }
}
