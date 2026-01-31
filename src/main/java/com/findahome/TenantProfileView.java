package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TenantProfileView extends StackPane {

        private static final String BACKGROUND_DARK = "#101922";
        private static final String CARD_BG = "#1c2127";
        private static final String PRIMARY = "#137fec";
        private static final String BORDER_COLOR = "#3b4754";
        private static final String TEXT_GRAY = "#9dabb9";

        public TenantProfileView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox layout = new VBox(0);

                // Top Navigation Bar
                HBox topNav = new HBox();
                topNav.setAlignment(Pos.CENTER_LEFT);
                topNav.setPadding(new Insets(15, 20, 15, 20));
                topNav.setStyle("-fx-background-color: rgba(16, 25, 34, 0.8); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = new Label("\u2039");
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.showHome());

                Label navTitle = new Label("Profile");
                navTitle.setTextFill(Color.WHITE);
                navTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                navTitle.setAlignment(Pos.CENTER);
                navTitle.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(navTitle, Priority.ALWAYS);

                Label settingsBtn = new Label("\u2699");
                settingsBtn.setTextFill(Color.WHITE);
                settingsBtn.setStyle("-fx-font-size: 22; -fx-cursor: hand;");

                topNav.getChildren().addAll(backBtn, navTitle, settingsBtn);

                // Scroll Content
                VBox scrollContent = new VBox(0);
                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Profile Header
                VBox profileHeader = new VBox(15);
                profileHeader.setAlignment(Pos.CENTER);
                profileHeader.setPadding(new Insets(30, 20, 30, 20));

                StackPane avatarStack = new StackPane();
                ImageView avatar = new ImageView();
                try {
                        avatar.setImage(new Image(
                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuBPbDzFW1iSX9BSAjAWstiLdyqjEU_XrOE_rQagBw9Zo0wscais0ff6oequ8lwF9zUh8Fw-f9NAcNmnMWpinEHmlxRsIbLVN5TOQWtRiTHL0Ni5J5NV0R_Oa07Uy0qhFJkro4MZOH9eegFgfgSzCTkgKGkOzRv_pzkDupOMVfQbJEbMsfdKFgnqIyM4ppvF21EOmx_2vxOHLaQUp3x2j6KbNllWcqt-YHDc4FMG3p2ftJPRhaCK_-R0OgywIYZDCfe5DK83RrWQoWU",
                                        128, 128, true, true));
                } catch (Exception e) {
                }
                Circle clip = new Circle(64, 64, 64);
                avatar.setClip(clip);
                avatar.setFitWidth(128);
                avatar.setFitHeight(128);

                Circle ring = new Circle(64, 64, 68);
                ring.setFill(Color.TRANSPARENT);
                ring.setStroke(Color.web(PRIMARY, 0.2));
                ring.setStrokeWidth(4);

                Label vIcon = new Label("\u2705");
                vIcon.setStyle("-fx-background-color: " + PRIMARY
                                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-padding: 3; -fx-font-size: 10; -fx-border-color: "
                                + BACKGROUND_DARK + "; -fx-border-width: 3; -fx-border-radius: 12;");
                StackPane.setAlignment(vIcon, Pos.BOTTOM_RIGHT);
                StackPane.setMargin(vIcon, new Insets(0, 10, 10, 0));

                avatarStack.getChildren().addAll(ring, avatar, vIcon);

                VBox meta = new VBox(2);
                meta.setAlignment(Pos.CENTER);
                Label name = new Label("John Doe");
                name.setTextFill(Color.WHITE);
                name.setFont(Font.font("System", FontWeight.BOLD, 24));

                Label status = new Label("VERIFIED TENANT");
                status.setTextFill(Color.web(PRIMARY));
                status.setFont(Font.font("System", FontWeight.BOLD, 12));

                Label memberSince = new Label("Member since April 2023");
                memberSince.setTextFill(Color.web(TEXT_GRAY));
                memberSince.setFont(Font.font(13));

                meta.getChildren().addAll(name, status, memberSince);
                profileHeader.getChildren().addAll(avatarStack, meta);

                // Profile Strength Meter
                VBox strengthSec = new VBox(12);
                strengthSec.setPadding(new Insets(0, 20, 25, 20));

                VBox strengthCard = new VBox(15);
                strengthCard.setPadding(new Insets(20));
                strengthCard.setStyle(
                                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                                + BORDER_COLOR + ";");

                HBox meterHead = new HBox(10);
                meterHead.setAlignment(Pos.CENTER_LEFT);
                Label meterIcon = new Label("\ud83d\udcca");
                meterIcon.setTextFill(Color.web(PRIMARY));
                Label meterTitle = new Label("Profile Strength");
                meterTitle.setTextFill(Color.WHITE);
                meterTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
                Region meterS = new Region();
                HBox.setHgrow(meterS, Priority.ALWAYS);
                Label meterPct = new Label("85%");
                meterPct.setTextFill(Color.web(PRIMARY));
                meterPct.setFont(Font.font("System", FontWeight.BOLD, 14));
                meterHead.getChildren().addAll(meterIcon, meterTitle, meterS, meterPct);

                ProgressBar pb = new ProgressBar(0.85);
                pb.setMaxWidth(Double.MAX_VALUE);
                pb.setPrefHeight(8);
                pb.setStyle("-fx-accent: " + PRIMARY
                                + "; -fx-control-inner-background: #3b4754; -fx-background-radius: 10;");

                Label meterDesc = new Label("Complete your rental history to get faster approvals from landlords.");
                meterDesc.setTextFill(Color.web(TEXT_GRAY));
                meterDesc.setFont(Font.font(12));
                meterDesc.setWrapText(true);

                Button completeBtn = new Button("Complete Profile");
                completeBtn.setMaxWidth(Double.MAX_VALUE);
                completeBtn.setPrefHeight(40);
                completeBtn.setStyle("-fx-background-color: rgba(19, 127, 236, 0.1); -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-background-radius: 10; -fx-cursor: hand;");

                strengthCard.getChildren().addAll(meterHead, pb, meterDesc, completeBtn);
                strengthSec.getChildren().add(strengthCard);

                // Quick Links Grid
                VBox overviewSec = new VBox(15);
                overviewSec.setPadding(new Insets(10, 20, 25, 20));
                Label overviewTitle = new Label("Overview");
                overviewTitle.setTextFill(Color.WHITE);
                overviewTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                GridPane grid = new GridPane();
                grid.setHgap(12);
                grid.setVgap(12);

                grid.add(createQuickCard("My Applications", "3 Active", "\ud83d\udcc4", true), 0, 0);
                grid.add(createQuickCard("Saved Homes", "12 Items", "\u2661", false), 1, 0);
                grid.add(createQuickCard("Viewing Schedule", "Tomorrow, 10 AM", "\ud83d\udcc5", false), 0, 1);
                grid.add(createQuickCard("Payment History", "Last paid: Oct 1", "\ud83e\uddfe", false), 1, 1);

                ColumnConstraints col1 = new ColumnConstraints();
                col1.setPercentWidth(50);
                ColumnConstraints col2 = new ColumnConstraints();
                col2.setPercentWidth(50);
                grid.getColumnConstraints().addAll(col1, col2);

                overviewSec.getChildren().addAll(overviewTitle, grid);

                // Account Settings
                VBox settingsSec = new VBox(12);
                settingsSec.setPadding(new Insets(10, 20, 120, 20));
                Label settingsTitleLabel = new Label("Account Settings");
                settingsTitleLabel.setTextFill(Color.WHITE);
                settingsTitleLabel.setFont(Font.font("System", FontWeight.BOLD, 18));

                VBox settingsList = new VBox(0);
                settingsList.setStyle(
                                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                                + BORDER_COLOR + "; -fx-overflow: hidden;");

                settingsList.getChildren().addAll(
                                createSettingRow("Personal Information", "\ud83d\udc64"),
                                createSettingRow("Document Vault", "\ud83d\udcc1"),
                                createSettingRow("Invite & Earn", "\ud83c\udf81"),
                                createSettingRow("Notifications", "\ud83d\udd14"),
                                createSettingRow("Privacy & Security", "\ud83d\udee1\ufe0f"),
                                createSettingRow("Help Center", "\ud83d\udca1"));

                Button logoutBtn = new Button("Log Out");
                VBox.setMargin(logoutBtn, new Insets(20, 0, 0, 0));
                logoutBtn.setMaxWidth(Double.MAX_VALUE);
                logoutBtn.setPrefHeight(56);
                logoutBtn.setStyle(
                                "-fx-background-color: rgba(239, 68, 68, 0.05); -fx-text-fill: #ef4444; -fx-font-weight: bold; -fx-background-radius: 12; -fx-border-color: rgba(239, 68, 68, 0.2); -fx-cursor: hand;");

                settingsSec.getChildren().addAll(settingsTitleLabel, settingsList, logoutBtn);

                scrollContent.getChildren().addAll(profileHeader, strengthSec, overviewSec, settingsSec);

                // Bottom Navigation Bar
                HBox bottomNav = new HBox();
                bottomNav.setAlignment(Pos.CENTER);
                bottomNav.setPadding(new Insets(10, 20, 25, 20));
                bottomNav.setStyle("-fx-background-color: rgba(16, 25, 34, 0.9); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                bottomNav.getChildren().addAll(
                                createNavItem("Home", "\ud83c\udfe0", false, e -> MainApp.showHome()),
                                createNavItem("Search", "\ud83d\udd0d", false, e -> MainApp.navigateToMap()),
                                createNavItem("Messages", "\ud83d\udcac", false,
                                                e -> MainApp.navigateTo(new ChatView())),
                                createNavItem("Profile", "\ud83d\udc64", true, e -> {
                                }));
                HBox.setHgrow(bottomNav, Priority.ALWAYS);

                layout.getChildren().addAll(topNav, scroll);

                getChildren().addAll(layout, bottomNav);
                StackPane.setAlignment(bottomNav, Pos.BOTTOM_CENTER);
        }

        private VBox createQuickCard(String title, String val, String iconCode, boolean hasNotification) {
                VBox card = new VBox(12);
                card.setPadding(new Insets(15));
                card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR + "; -fx-cursor: hand;");

                // Link Viewing Schedule to ScheduleView
                if (title.contains("Viewing")) {
                        card.setOnMouseClicked(e -> MainApp.navigateTo(new ScheduleView()));
                } else if (title.contains("Applications")) {
                        card.setOnMouseClicked(e -> MainApp.navigateTo(new ApplicationTrackerView()));
                }

                StackPane iconStack = new StackPane();
                iconStack.setAlignment(Pos.TOP_LEFT);

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(40, 40);
                iconBox.setMaxSize(40, 40);
                iconBox.setStyle("-fx-background-color: rgba(19, 127, 236, 0.1); -fx-background-radius: 10;");
                Label icon = new Label(iconCode);
                icon.setTextFill(Color.web(PRIMARY));
                icon.setStyle("-fx-font-size: 18;");
                iconBox.getChildren().add(icon);

                iconStack.getChildren().add(iconBox);

                if (hasNotification) {
                        Circle dot = new Circle(4, Color.web("#ef4444"));
                        StackPane.setAlignment(dot, Pos.TOP_RIGHT);
                        iconStack.getChildren().add(dot);
                }

                VBox meta = new VBox(2);
                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 13));
                Label v = new Label(val);
                v.setTextFill(Color.web(TEXT_GRAY));
                v.setFont(Font.font(11));
                meta.getChildren().addAll(t, v);

                card.getChildren().addAll(iconStack, meta);
                return card;
        }

        private HBox createSettingRow(String text, String iconCode) {
                HBox row = new HBox(15);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(15, 20, 15, 20));
                row.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0; -fx-cursor: hand;");

                Label icon = new Label(iconCode);
                icon.setTextFill(Color.web(TEXT_GRAY));
                icon.setStyle("-fx-font-size: 18;");

                Label lbl = new Label(text);
                lbl.setTextFill(Color.WHITE);
                lbl.setFont(Font.font("System", FontWeight.MEDIUM, 14));

                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);

                Label chevron = new Label("\u203a");
                chevron.setTextFill(Color.web("#64748b"));
                chevron.setStyle("-fx-font-size: 20;");

                row.getChildren().addAll(icon, lbl, s, chevron);

                row.setOnMouseEntered(e -> row.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.02); -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0; -fx-cursor: hand;"));
                row.setOnMouseExited(e -> row
                                .setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0; -fx-cursor: hand;"));

                if (text.contains("Invite")) {
                        row.setOnMouseClicked(e -> MainApp.navigateTo(new InviteRewardView()));
                } else if (text.contains("Vault")) {
                        row.setOnMouseClicked(e -> MainApp.navigateTo(new DocumentVaultView()));
                }

                return row;
        }

        private VBox createNavItem(String label, String iconCode, boolean active,
                        javafx.event.EventHandler<javafx.scene.input.MouseEvent> handler) {
                VBox item = new VBox(5);
                item.setAlignment(Pos.CENTER);
                item.setPadding(new Insets(0, 20, 0, 20));
                HBox.setHgrow(item, Priority.ALWAYS);
                item.setCursor(javafx.scene.Cursor.HAND);
                item.setOnMouseClicked(handler);

                Label icon = new Label(iconCode);
                icon.setTextFill(active ? Color.web(PRIMARY) : Color.web("#94a3b8"));
                icon.setStyle("-fx-font-size: 20;");

                Label lbl = new Label(label);
                lbl.setTextFill(active ? Color.web(PRIMARY) : Color.web("#94a3b8"));
                lbl.setFont(Font.font("System", active ? FontWeight.BOLD : FontWeight.MEDIUM, 10));

                item.getChildren().addAll(icon, lbl);
                return item;
        }
}
