package com.findahome;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class ApplicationTrackerView extends StackPane {

        private static final String BACKGROUND_DARK = "#0b0e11";
        private static final String CARD_DARK = "#161b22";
        private static final String PRIMARY = "#137fec";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.08)";
        private static final String TEXT_GRAY = "#94a3b8";

        // Data model for clean management
        private record ApplicationData(String title, String meta, String status, int step, String imgUrl, String icon,
                        String updated) {
        }

        private VBox activeList;
        private VBox pastList;
        private StackPane contentStack;
        private VBox activeTab;
        private VBox pastTab;

        public ApplicationTrackerView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                // Initialize Views for fast switching
                activeList = new VBox(25);
                pastList = new VBox(25);
                contentStack = new StackPane();

                // Header Section
                VBox headerArea = createHeaderArea();

                // Scroll Content
                VBox scrollContainer = new VBox(0);
                ScrollPane scroll = createScrollPane(scrollContainer);

                // Timeline & Entries
                StackPane activeTimeline = createTimelineContainer(activeList);
                StackPane pastTimeline = createTimelineContainer(pastList);

                pastTimeline.setVisible(false);
                pastTimeline.setOpacity(0);
                contentStack.getChildren().addAll(activeTimeline, pastTimeline);
                scrollContainer.getChildren().add(contentStack);

                // Pre-populate data
                populateAllData();
                switchToTab(true); // Show active applications initially

                // Bottom Actions (Docked)
                HBox bottomPanel = createBottomPanel();

                // Main Layout Structure
                BorderPane contentLayout = new BorderPane();
                contentLayout.setTop(headerArea);
                contentLayout.setCenter(scroll);
                contentLayout.setBottom(bottomPanel);

                // Responsive Desktop Column
                VBox desktopColumn = new VBox(contentLayout);
                desktopColumn.setMaxWidth(600);
                desktopColumn.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
                VBox.setVgrow(desktopColumn, Priority.ALWAYS);

                HBox centerer = new HBox(desktopColumn);
                centerer.setAlignment(Pos.CENTER);
                HBox.setHgrow(desktopColumn, Priority.ALWAYS);
                VBox.setVgrow(centerer, Priority.ALWAYS);

                getChildren().setAll(centerer);
        }

        private VBox createHeaderArea() {
                HBox appBar = new HBox();
                appBar.setAlignment(Pos.CENTER_LEFT);
                appBar.setPadding(new Insets(15, 20, 15, 20));
                appBar.setStyle("-fx-background-color: rgba(11, 14, 17, 0.95); -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                Label backBtn = createStyledIcon("‹", 28,
                                e -> MainApp.navigateCached("profile", TenantProfileView::new));

                Label title = new Label("Application Tracker");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);

                Label tuneBtn = createStyledIcon("⊰", 20, null);
                appBar.getChildren().addAll(backBtn, title, tuneBtn);

                HBox tabs = new HBox(0);
                tabs.setPadding(new Insets(0, 20, 0, 20));
                tabs.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                activeTab = createTab("Active (3)", true);
                activeTab.setOnMouseClicked(e -> switchToTab(true));

                pastTab = createTab("Past", false);
                pastTab.setOnMouseClicked(e -> switchToTab(false));

                HBox.setHgrow(activeTab, Priority.ALWAYS);
                HBox.setHgrow(pastTab, Priority.ALWAYS);
                tabs.getChildren().addAll(activeTab, pastTab);

                return new VBox(appBar, tabs);
        }

        private ScrollPane createScrollPane(VBox content) {
                ScrollPane scroll = new ScrollPane(content);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);
                return scroll;
        }

        private StackPane createTimelineContainer(VBox entryList) {
                StackPane container = new StackPane();
                container.setPadding(new Insets(25, 20, 40, 20));

                Region line = new Region();
                line.setStyle("-fx-background-color: #313d4b; -fx-background-radius: 2;");
                line.setPrefWidth(2);
                line.setMaxWidth(2);
                line.setMaxHeight(Double.MAX_VALUE);
                StackPane.setAlignment(line, Pos.TOP_LEFT);
                StackPane.setMargin(line, new Insets(15, 0, 15, 14));

                container.getChildren().addAll(line, entryList);
                return container;
        }

        private void populateAllData() {
                // Active Data
                var activeData = java.util.List.of(
                                new ApplicationData("Modern 2BR Apartment", "Nairobi West • $1,200/mo", "SUBMITTED", 1,
                                                "https://images.unsplash.com/photo-1545324418-f1d3c5b53571?q=80&w=400&auto=format&fit=crop",
                                                "✔", "2 hours ago"),
                                new ApplicationData("Greenwood Estate", "Karen • $2,500/mo", "UNDER REVIEW", 2,
                                                "https://images.unsplash.com/photo-1512917774080-9991f1c4c750?q=80&w=400&auto=format&fit=crop",
                                                "⏳", "1 day ago"),
                                new ApplicationData("Urban Loft", "Kilimani • $950/mo", "BACKGROUND CHECK", 3,
                                                "https://images.unsplash.com/photo-1493809842364-78817add7ffb?q=80&w=400&auto=format&fit=crop",
                                                "📄", "3 days ago"));

                // Past Data
                var pastData = java.util.List.of(
                                new ApplicationData("Sunset Villa", "Runda • $3,500/mo", "EXPIRED", 0,
                                                "https://images.unsplash.com/photo-1512918728675-ed5a9ecde9d7?q=80&w=400&auto=format&fit=crop",
                                                "✖", "2 weeks ago"),
                                new ApplicationData("Cozy Studio", "South B • $450/mo", "REJECTED", 4,
                                                "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?q=80&w=400&auto=format&fit=crop",
                                                "✘", "1 month ago"));

                for (var item : activeData)
                        activeList.getChildren().add(createApplicationEntry(item));
                for (var item : pastData)
                        pastList.getChildren().add(createApplicationEntry(item));
        }

        private void switchToTab(boolean showActive) {
                updateTabStyles(activeTab, showActive);
                updateTabStyles(pastTab, !showActive);

                Node activeNode = contentStack.getChildren().get(0);
                Node pastNode = contentStack.getChildren().get(1);

                Node appearing = showActive ? activeNode : pastNode;
                Node disappearing = showActive ? pastNode : activeNode;

                if (appearing.isVisible())
                        return;

                disappearing.setVisible(false);
                disappearing.setOpacity(0);

                appearing.setVisible(true);
                FadeTransition ft = new FadeTransition(Duration.millis(10), appearing);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
        }

        private void updateTabStyles(VBox tab, boolean active) {
                Label l = (Label) tab.getChildren().get(0);
                Region r = (Region) tab.getChildren().get(1);
                l.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                l.setFont(Font.font("System", active ? FontWeight.BOLD : FontWeight.MEDIUM, 14));
                r.setStyle("-fx-background-color: " + (active ? PRIMARY : "transparent")
                                + "; -fx-background-radius: 3 3 0 0;");
        }

        private HBox createApplicationEntry(ApplicationData item) {
                HBox container = new HBox(15);
                container.setAlignment(Pos.TOP_LEFT);

                StackPane bullet = new StackPane();
                bullet.setPrefSize(30, 30);
                bullet.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 15; -fx-border-color: "
                                + BACKGROUND_DARK + "; -fx-border-width: 4;");
                Label icon = new Label(item.icon());
                icon.setTextFill(Color.WHITE);
                icon.setStyle("-fx-font-size: 13;");
                bullet.getChildren().add(icon);

                VBox card = new VBox(15);
                card.setPadding(new Insets(18));
                card.setStyle("-fx-background-color: " + CARD_DARK + "; -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR + ";");
                HBox.setHgrow(card, Priority.ALWAYS);

                // Hover effect using standard JavaFX style properties (concatenation)
                card.setOnMouseEntered(e -> card.setStyle(
                                "-fx-background-color: #1c2128; -fx-background-radius: 16; -fx-border-color: rgba(19, 127, 236, 0.3);"));
                card.setOnMouseExited(e -> card.setStyle("-fx-background-color: " + CARD_DARK
                                + "; -fx-background-radius: 16; -fx-border-color: " + BORDER_COLOR + ";"));

                HBox topInfo = new HBox(15);
                topInfo.setAlignment(Pos.CENTER_LEFT);

                ImageView iv = createRoundedImage(item.imgUrl(), 80);

                VBox textMeta = new VBox(4);
                Label t = new Label(item.title());
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 16));

                Label m = new Label(item.meta());
                m.setTextFill(Color.web(TEXT_GRAY));
                m.setFont(Font.font(12));

                HBox statusRow = new HBox(8);
                statusRow.setAlignment(Pos.CENTER_LEFT);

                Label statusBadge = createStatusBadge(item.status());
                Label updated = new Label("Updated " + item.updated());
                updated.setTextFill(Color.web("#64748b"));
                updated.setFont(Font.font(10));

                statusRow.getChildren().addAll(statusBadge, updated);
                textMeta.getChildren().addAll(t, m, statusRow);
                topInfo.getChildren().addAll(iv, textMeta);

                HBox stepper = createStepper(item.step());

                HBox btnRow = new HBox(10);
                Button viewBtn = createStyledButton("View Details", true,
                                e -> MainApp.navigateCachedFullScreen("lease", LeaseAgreementView::new));
                Button chatBtn = createStyledButton("💬", false,
                                e -> MainApp.navigateCached("messages", ChatView::new));
                btnRow.getChildren().addAll(viewBtn, chatBtn);

                card.getChildren().addAll(topInfo, stepper, btnRow);
                container.getChildren().addAll(bullet, card);
                return container;
        }

        private Label createStatusBadge(String status) {
                Label badge = new Label(status);
                String color = status.contains("REVIEW") ? "#f59e0b" : status.contains("CHECK") ? "#a855f7" : PRIMARY;
                badge.setStyle("-fx-background-color: " + color + "22; -fx-text-fill: " + color
                                + "; -fx-font-weight: bold; -fx-font-size: 9; -fx-padding: 3 8; -fx-background-radius: 4;");
                return badge;
        }

        private HBox createStepper(int step) {
                HBox stepper = new HBox(5);
                for (int i = 1; i <= 4; i++) {
                        Region s = new Region();
                        s.setPrefHeight(4);
                        HBox.setHgrow(s, Priority.ALWAYS);
                        s.setStyle("-fx-background-radius: 2; -fx-background-color: "
                                        + (i <= step ? PRIMARY : "#2d343d") + ";");
                        stepper.getChildren().add(s);
                }
                return stepper;
        }

        private Button createStyledButton(String text, boolean isPrimary,
                        javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
                Button btn = new Button(text);
                btn.setOnAction(handler);
                btn.setCursor(javafx.scene.Cursor.HAND);

                if (isPrimary) {
                        btn.setMaxWidth(Double.MAX_VALUE);
                        HBox.setHgrow(btn, Priority.ALWAYS);
                        btn.setPrefHeight(40);
                        btn.setStyle("-fx-background-color: " + PRIMARY
                                        + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13; -fx-background-radius: 10;");
                        btn.setOnMouseEntered(e -> btn.setStyle(
                                        "-fx-background-color: #1a8bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13; -fx-background-radius: 10;"));
                        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: " + PRIMARY
                                        + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13; -fx-background-radius: 10;"));
                } else {
                        btn.setPrefSize(40, 40);
                        btn.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: " + PRIMARY
                                        + "; -fx-background-radius: 10; -fx-font-size: 16;");
                        btn.setOnMouseEntered(
                                        e -> btn.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: "
                                                        + PRIMARY + "; -fx-background-radius: 10; -fx-font-size: 16;"));
                        btn.setOnMouseExited(e -> btn
                                        .setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: "
                                                        + PRIMARY + "; -fx-background-radius: 10; -fx-font-size: 16;"));
                }
                return btn;
        }

        private HBox createBottomPanel() {
                HBox panel = new HBox(35);
                panel.setAlignment(Pos.CENTER);
                panel.setPadding(new Insets(15, 20, 25, 20));
                panel.setStyle("-fx-background-color: " + CARD_DARK + "; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 1 0 0 0;");

                panel.getChildren().addAll(
                                createActionItem("forum", "Agent Chats", e -> MainApp.navigateTo(new ChatView())),
                                createActionItem("share", "Share List", e -> showShareSuccess()),
                                createActionItem("help", "Support", e -> MainApp.navigateTo(new HelpSupportView())));
                return panel;
        }

        private void showShareSuccess() {
                Label toast = new Label("Application list shared successfully! 🚀");
                toast.setStyle("-fx-background-color: #059669; -fx-text-fill: white; -fx-padding: 12 25; " +
                                "-fx-background-radius: 25; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
                toast.setOpacity(0);
                toast.setMouseTransparent(true); // Don't block clicks

                this.getChildren().add(toast);
                StackPane.setAlignment(toast, Pos.TOP_CENTER);
                StackPane.setMargin(toast, new Insets(100, 0, 0, 0));

                FadeTransition fadeIn = new FadeTransition(Duration.millis(300), toast);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);

                FadeTransition fadeOut = new FadeTransition(Duration.millis(300), toast);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setDelay(Duration.seconds(2.5));
                fadeOut.setOnFinished(e -> this.getChildren().remove(toast));

                fadeIn.setOnFinished(e -> fadeOut.play());
                fadeIn.play();
        }

        private VBox createActionItem(String icon, String label,
                        javafx.event.EventHandler<? super javafx.scene.input.MouseEvent> clickHandler) {
                VBox v = new VBox(6);
                v.setAlignment(Pos.CENTER);
                v.setCursor(javafx.scene.Cursor.HAND);
                if (clickHandler != null)
                        v.setOnMouseClicked(clickHandler);

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(42, 42);
                iconBox.setStyle("-fx-background-color: rgba(19, 127, 236, 0.08); -fx-background-radius: 12;");
                Label i = new Label(icon.equals("forum") ? "💬" : icon.equals("share") ? "➚" : "❓");
                i.setTextFill(Color.web(PRIMARY));
                i.setStyle("-fx-font-size: 18;");
                iconBox.getChildren().add(i);

                Label l = new Label(label);
                l.setTextFill(Color.web(TEXT_GRAY));
                l.setFont(Font.font("System", FontWeight.BOLD, 10));

                v.getChildren().addAll(iconBox, l);

                v.setOnMouseEntered(e -> {
                        iconBox.setStyle("-fx-background-color: rgba(19, 127, 236, 0.15); -fx-background-radius: 12;");
                        l.setTextFill(Color.WHITE);
                });
                v.setOnMouseExited(e -> {
                        iconBox.setStyle("-fx-background-color: rgba(19, 127, 236, 0.08); -fx-background-radius: 12;");
                        l.setTextFill(Color.web(TEXT_GRAY));
                });

                return v;
        }

        private VBox createTab(String text, boolean active) {
                VBox v = new VBox(10);
                v.setAlignment(Pos.CENTER);
                v.setCursor(javafx.scene.Cursor.HAND);
                Label l = new Label(text);
                l.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                l.setFont(Font.font("System", active ? FontWeight.BOLD : FontWeight.MEDIUM, 14));
                Region indicator = new Region();
                indicator.setPrefHeight(3);
                indicator.setStyle("-fx-background-color: " + (active ? PRIMARY : "transparent")
                                + "; -fx-background-radius: 3 3 0 0;");
                v.getChildren().addAll(l, indicator);
                v.setPadding(new Insets(15, 0, 0, 0));
                return v;
        }

        private Label createStyledIcon(String symbol, int size,
                        javafx.event.EventHandler<javafx.scene.input.MouseEvent> handler) {
                Label l = new Label(symbol);
                l.setTextFill(Color.WHITE);
                l.setStyle("-fx-font-size: " + size + "; -fx-cursor: hand;");
                if (handler != null)
                        l.setOnMouseClicked(handler);
                return l;
        }

        private ImageView createRoundedImage(String url, int size) {
                ImageView iv = new ImageView();
                try {
                        Image img = new Image(url, size, size, false, true);
                        iv.setImage(img);
                } catch (Exception e) {
                }
                iv.setFitWidth(size);
                iv.setFitHeight(size);
                Rectangle clip = new Rectangle(size, size);
                clip.setArcWidth(20);
                clip.setArcHeight(20);
                iv.setClip(clip);
                return iv;
        }
}