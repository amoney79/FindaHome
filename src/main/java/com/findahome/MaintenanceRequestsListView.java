package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MaintenanceRequestsListView extends StackPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
        private static final String CARD_BG = "#1c271f";
        private static final String TEXT_GRAY = "#9db9a6";

        private VBox scrollContent;
        private Button activeTab, pastTab;
        private boolean showingActive = true;

        public MaintenanceRequestsListView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox layout = new VBox(0);
                layout.setAlignment(Pos.TOP_CENTER);

                // Header
                VBox header = new VBox(15);
                header.setPadding(new Insets(50, 20, 20, 20));
                header.setStyle("-fx-background-color: " + BACKGROUND_DARK + "cc; -fx-border-color: " + BORDER_COLOR
                                + "; -fx-border-width: 0 0 1 0;");

                HBox topActions = new HBox();
                topActions.setAlignment(Pos.CENTER_LEFT);

                Label backBtn = new Label("\u2039");
                backBtn.setTextFill(Color.WHITE);
                backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                backBtn.setOnMouseClicked(e -> MainApp.navigateCached("profile", TenantProfileView::new));

                Region s1 = new Region();
                HBox.setHgrow(s1, Priority.ALWAYS);

                HBox rightActions = new HBox(10);
                Label searchIcon = createActionCircle("\ud83d\udd0d");
                Label notifyIcon = createActionCircle("\ud83d\udd14");
                rightActions.getChildren().addAll(searchIcon, notifyIcon);

                topActions.getChildren().addAll(backBtn, s1, rightActions);

                Label pageTitle = new Label("My Maintenance \nRequests");
                pageTitle.setTextFill(Color.WHITE);
                pageTitle.setFont(Font.font("System", FontWeight.BOLD, 28));
                pageTitle.setWrapText(true);

                header.getChildren().addAll(topActions, pageTitle);

                // Segmented Control
                HBox segmented = new HBox(5);
                segmented.setPadding(new Insets(5));
                segmented.setStyle("-fx-background-color: #1a2e20; -fx-background-radius: 12;");
                segmented.setMaxWidth(350);

                activeTab = createTabButton("Active", true);
                pastTab = createTabButton("Past", false);
                activeTab.setMaxWidth(Double.MAX_VALUE);
                pastTab.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(activeTab, Priority.ALWAYS);
                HBox.setHgrow(pastTab, Priority.ALWAYS);

                activeTab.setOnAction(e -> switchTab(true));
                pastTab.setOnAction(e -> switchTab(false));

                segmented.getChildren().addAll(activeTab, pastTab);

                VBox segContainer = new VBox(segmented);
                segContainer.setPadding(new Insets(20, 20, 10, 20));

                // Scrollable List
                scrollContent = new VBox(15);
                scrollContent.setPadding(new Insets(10, 20, 120, 20));

                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Initial load
                refreshList();

                // Floating Action Button
                Button fab = new Button("+");
                fab.setPrefSize(60, 60);
                fab.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-size: 30; -fx-font-weight: bold; -fx-background-radius: 30; -fx-effect: dropshadow(gaussian, rgba(19,236,91,0.2), 20, 0, 0, 10); -fx-cursor: hand;");
                fab.setOnAction(e -> MainApp.navigateCached("maintenance_form", MaintenanceRequestView::new));

                StackPane.setAlignment(fab, Pos.BOTTOM_RIGHT);
                StackPane.setMargin(fab, new Insets(0, 20, 100, 0));

                layout.getChildren().addAll(header, segContainer, scroll);
                getChildren().addAll(layout, fab);
        }

        private void switchTab(boolean active) {
                if (showingActive == active)
                        return;
                showingActive = active;
                updateTabStyles();
                refreshList();
        }

        private void updateTabStyles() {
                String activeStyle = "-fx-background-color: " + BACKGROUND_DARK + "; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-background-radius: 8;";
                String inactiveStyle = "-fx-background-color: transparent; -fx-text-fill: #9db9a6; -fx-font-weight: bold;";

                activeTab.setStyle(showingActive ? activeStyle : inactiveStyle);
                pastTab.setStyle(!showingActive ? activeStyle : inactiveStyle);
        }

        private void refreshList() {
                scrollContent.getChildren().clear();

                if (showingActive) {
                        scrollContent.getChildren().addAll(
                                        createRequestCard("Leaking Kitchen Sink", "June 15, 2023", "In Progress",
                                                        PRIMARY,
                                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDyIpPyswoXMWIuZt47ze4eWAZzeOLz2FdD3yKO5PASSC3qNbeNlDJyvvxlUuXBfKKn52QRK91gag4HSlO166S_FIKwk_zwhO6WE0D_8rD0cV-VktpfqMGSxEM5VR5MFzfnbyD8T4wJ-N1FQBrUpNCKSIu7QzkDCcdPqYo6WDXt1E8mfL3K7XgAY0LobMpNVkXdq16rExOI6gTuZuvICDPHzBcoF4pcKFXV4FoChMg-jNJpGaXVpdNHm-FQRaxQ5U7eCQTfU5Scl84",
                                                        true),
                                        createRequestCard("Broken AC Unit", "June 12, 2023", "Technician Assigned",
                                                        "#60a5fa",
                                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCXclCObvoIbbYQ5bCTReSJ83TCrhD6m2VSdpS9Qq2gbeMcqQPRYK6DMK4mUK6sHYMdoHU-oavk6_KUmCU1hFTVaQJLsZ1UWb6Xn6TtW3qLmFXJQYsbSY-eHJ8-eCxNkjkRbQA82GHGiwKlefpNTlt6tvXKpvNHL-hanKhJ0W5fcqts6onkWgWopYzaQsvuuR1xSCdQeM4STLvNx-x96ng11venBoScGsSzUbmuGqNEunlNj9Mtbu93xIXoOetAD1WGaF5ufCkxXf4",
                                                        false),
                                        createRequestCard("Clogged Drain", "June 14, 2023", "Reported", "#94a3b8",
                                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCz4vlCvCcw-0lJIK7vbLFl67DogZAy1sCZ9_HePUoBgL2UnRp8EfYeH3sHUAeYIonWZuN9xFRPG8UAseEo9p8csmaIKSgWG2xJvCUIvqlTWJqL8GSPd9Wn86Nal3JEMo2uIIUacHtMyxG8Fcg6f4bCo7rO3Q8EHxICzT10jhBBT1hYGW3HyT8a8ZcDURzofAvsa_mZtfGOrggPIaufy6wb__8H831TEaqpl6Rx8tPmGbUhKynHUW5dyQFqMXmeRrOf0wf6c4H0CmE",
                                                        false));
                } else {
                        scrollContent.getChildren().addAll(
                                        createRequestCard("Flickering Lights", "May 10, 2023", "Resolved", "#10b981",
                                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCHXb-p-q-G-z-H-D-E-F-G-H-I-J-K-L-M-N-O-P-Q-R-S-T-U-V-W-X-Y-Z",
                                                        false),
                                        createRequestCard("Squeaky Door", "April 22, 2023", "Resolved", "#10b981",
                                                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCHXb-p-q-G-z-H-D-E-F-G-H-I-J-K-L-M-N-O-P-Q-R-S-T-U-V-W-X-Y-Z",
                                                        false));
                }
        }

        private Label createActionCircle(String icon) {
                Label lbl = new Label(icon);
                lbl.setAlignment(Pos.CENTER);
                lbl.setPrefSize(40, 40);
                lbl.setStyle(
                                "-fx-background-color: #1a2e20; -fx-background-radius: 20; -fx-text-fill: #9db9a6; -fx-font-size: 18;");
                return lbl;
        }

        private Button createTabButton(String text, boolean active) {
                Button btn = new Button(text);
                btn.setPrefHeight(34);
                btn.setStyle(active
                                ? "-fx-background-color: " + BACKGROUND_DARK + "; -fx-text-fill: " + PRIMARY
                                                + "; -fx-font-weight: bold; -fx-background-radius: 8;"
                                : "-fx-background-color: transparent; -fx-text-fill: #9db9a6; -fx-font-weight: bold;");
                return btn;
        }

        private VBox createRequestCard(String title, String date, String status, String statusColor, String imgUrl,
                        boolean showTechnician) {
                VBox card = new VBox(15);
                card.setPadding(new Insets(15));
                card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                                + BORDER_COLOR + ";");

                HBox top = new HBox(15);
                top.setAlignment(Pos.TOP_LEFT);

                VBox info = new VBox(2);
                HBox.setHgrow(info, Priority.ALWAYS);

                HBox statusBox = new HBox(6);
                statusBox.setAlignment(Pos.CENTER_LEFT);
                Circle dot = new Circle(3, Color.web(statusColor));
                Label statusLbl = new Label(status.toUpperCase());
                statusLbl.setTextFill(Color.web(statusColor));
                statusLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
                statusBox.getChildren().addAll(dot, statusLbl);

                Label t = new Label(title);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 18));

                Label d = new Label("Reported " + date);
                d.setTextFill(Color.web(TEXT_GRAY));
                d.setFont(Font.font(13));

                info.getChildren().addAll(statusBox, t, d);

                ImageView iv = new ImageView();
                try {
                        // backgroundLoading = true (6th param) for smoother UI
                        iv.setImage(new Image(imgUrl, 80, 80, false, true, true));
                } catch (Exception e) {
                }
                iv.setFitWidth(80);
                iv.setFitHeight(80);
                Rectangle clip = new Rectangle(80, 80);
                clip.setArcWidth(20);
                clip.setArcHeight(20);
                iv.setClip(clip);

                top.getChildren().addAll(info, iv);

                HBox foot = new HBox();
                foot.setAlignment(Pos.CENTER_LEFT);
                foot.setPadding(new Insets(10, 0, 0, 0));
                foot.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0;");

                if (showTechnician) {
                        HBox techStack = new HBox(-8);
                        techStack.setAlignment(Pos.CENTER_LEFT);
                        Circle techCircle = new Circle(14);
                        techCircle.setFill(Color.web("#3b82f6"));
                        techStack.getChildren().add(techCircle);
                        Label techPlus = new Label("+1");
                        techPlus.setPrefSize(28, 28);
                        techPlus.setAlignment(Pos.CENTER);
                        techPlus.setStyle("-fx-background-color: " + PRIMARY
                                        + "; -fx-background-radius: 14; -fx-text-fill: #102216; -fx-font-size: 10; -fx-font-weight: bold;");
                        techStack.getChildren().add(techPlus);
                        foot.getChildren().add(techStack);
                } else {
                        Label hint = new Label(
                                        status.equals("Reported") ? "Pending management approval"
                                                        : status.equals("Resolved") ? "Closed on June 20, 2023"
                                                                        : "Next: Visit scheduled for tomorrow");
                        hint.setTextFill(Color.web("#64748b"));
                        hint.setFont(Font.font(11));
                        foot.getChildren().add(hint);
                }

                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);

                Button actionBtn = new Button("Track Status \u203a");
                actionBtn.setStyle("-fx-background-color: " + PRIMARY + "22; -fx-text-fill: " + PRIMARY
                                + "; -fx-font-weight: bold; -fx-font-size: 12; -fx-background-radius: 8; -fx-padding: 8 12;");
                actionBtn.setCursor(javafx.scene.Cursor.HAND);
                actionBtn.setOnAction(e -> MainApp.navigateTo(new MaintenanceStatusTrackingView(title, status)));

                foot.getChildren().addAll(s, actionBtn);

                card.getChildren().addAll(top, foot);
                return card;
        }
}
