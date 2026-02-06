package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MaintenanceStatusTrackingView extends BorderPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String TEXT_GRAY = "#9db9a6";
    private static final String CARD_BG = "#1c271f";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

    public MaintenanceStatusTrackingView(String title, String status) {
        // Defaulting validation/demo data if generic
        if (title == null)
            title = "Leaking Kitchen Sink";

        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // --- Header ---
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("‹");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand; -fx-font-weight: bold;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateCached("maintenance_list", MaintenanceRequestsListView::new));

        Label navTitle = new Label("Request #REQ-8821");
        navTitle.setTextFill(Color.WHITE);
        navTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        navTitle.setAlignment(Pos.CENTER);
        navTitle.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(navTitle, Priority.ALWAYS);
        HBox.setMargin(navTitle, new Insets(0, 0, 0, 0));

        Label moreBtn = new Label("•••");
        moreBtn.setTextFill(Color.WHITE);
        moreBtn.setStyle("-fx-font-size: 14; -fx-cursor: hand; -fx-letter-spacing: 2;");

        header.getChildren().addAll(backBtn, navTitle, moreBtn);
        setTop(header);

        // --- Scroll Content ---
        VBox scrollContent = new VBox(25);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        scrollContent.setPadding(new Insets(20, 20, 100, 20));

        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // 1. Request Card
        scrollContent.getChildren().add(createRequestCard());

        // 2. Tracking Status
        scrollContent.getChildren().add(createTrackingSection());

        // 3. Rate Section
        scrollContent.getChildren().add(createRateSection());

        setCenter(scroll);

        // --- Footer Action ---
        VBox footer = new VBox();
        footer.setPadding(new Insets(20));
        // Gradient fade for footer? Simplified to solid for JavaFX
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        Button chatBtn = new Button("Chat with Landlord");
        chatBtn.setGraphic(new Label("\uD83D\uDCAC  ")); // Speech bubble
        ((Label) chatBtn.getGraphic()).setTextFill(Color.BLACK);
        chatBtn.setMaxWidth(Double.MAX_VALUE);
        chatBtn.setPrefHeight(50);
        chatBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
        chatBtn.setOnAction(e -> MainApp.navigateCached("messages", ChatView::new));

        footer.getChildren().add(chatBtn);
        setBottom(footer);
    }

    private VBox createRequestCard() {
        VBox card = new VBox(15);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.05);");

        HBox contentRow = new HBox(15);
        contentRow.setAlignment(Pos.TOP_LEFT);

        // Left Info
        VBox info = new VBox(8);
        HBox.setHgrow(info, Priority.ALWAYS);

        Label statusBadge = new Label("IN PROGRESS");
        statusBadge.setTextFill(Color.web(PRIMARY));
        statusBadge.setFont(Font.font("System", FontWeight.BOLD, 10));
        statusBadge.setPadding(new Insets(4, 8, 4, 8));
        statusBadge.setStyle("-fx-background-color: rgba(19, 236, 91, 0.15); -fx-background-radius: 6;");

        Label type = new Label("MAINTENANCE REQUEST");
        type.setTextFill(Color.web(TEXT_GRAY));
        type.setFont(Font.font("System", FontWeight.BOLD, 9));
        type.setOpacity(0.7);

        Label title = new Label("Leaking Kitchen\nSink");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setWrapText(true);

        Label loc = new Label("Unit 4B - Pine Gardens");
        loc.setGraphic(new Label("📍")); // Pin icon
        ((Label) loc.getGraphic()).setTextFill(Color.web(TEXT_GRAY));
        ((Label) loc.getGraphic()).setStyle("-fx-font-size: 10;");
        loc.setTextFill(Color.web(TEXT_GRAY));
        loc.setFont(Font.font(12));

        info.getChildren().addAll(statusBadge, type, title, loc);

        // Right Image
        ImageView iv = new ImageView();
        try {
            iv.setImage(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuDyIpPyswoXMWIuZt47ze4eWAZzeOLz2FdD3yKO5PASSC3qNbeNlDJyvvxlUuXBfKKn52QRK91gag4HSlO166S_FIKwk_zwhO6WE0D_8rD0cV-VktpfqMGSxEM5VR5MFzfnbyD8T4wJ-N1FQBrUpNCKSIu7QzkDCcdPqYo6WDXt1E8mfL3K7XgAY0LobMpNVkXdq16rExOI6gTuZuvICDPHzBcoF4pcKFXV4FoChMg-jNJpGaXVpdNHm-FQRaxQ5U7eCQTfU5Scl84",
                    100, 100, false, true, true));
        } catch (Exception e) {
        }
        iv.setFitWidth(100);
        iv.setFitHeight(100);
        Rectangle clip = new Rectangle(100, 100);
        clip.setArcWidth(12);
        clip.setArcHeight(12);
        iv.setClip(clip);

        contentRow.getChildren().addAll(info, iv);
        card.getChildren().add(contentRow);
        return card;
    }

    private VBox createTrackingSection() {
        VBox section = new VBox(20);

        VBox header = new VBox(2);
        Label title = new Label("Tracking Status");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label sub = new Label("Updates are synchronized in real-time");
        sub.setTextFill(Color.web(TEXT_GRAY));
        sub.setFont(Font.font(12));
        header.getChildren().addAll(title, sub);

        // Timeline Container
        VBox timeline = new VBox(0); // Spacing handled by rows

        // Steps
        timeline.getChildren().add(createTimelineStep(
                "Issue Reported", "Oct 24, 09:00 AM",
                TimelineState.COMPLETED, true, null));

        timeline.getChildren().add(createTimelineStep(
                "Landlord Acknowledged", "Oct 24, 11:30 AM",
                TimelineState.COMPLETED, true, null));

        timeline.getChildren().add(createTimelineStep(
                "Technician Scheduled", null,
                TimelineState.ACTIVE, true, createTechnicianCard()));

        timeline.getChildren().add(createTimelineStep(
                "Issue Resolved", "Estimated Completion: Today Evening",
                TimelineState.PENDING, false, null));

        section.getChildren().addAll(header, timeline);
        return section;
    }

    private Node createTechnicianCard() {
        HBox card = new HBox(12);
        card.setPadding(new Insets(12));
        card.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05);");
        card.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(card, new Insets(10, 0, 20, 0)); // Spacing from timeline line

        // Avatar
        Circle av = new Circle(20);
        av.setFill(Color.web("#3b82f6"));
        // Simple avatar image substitution
        Label avText = new Label("JD");
        avText.setTextFill(Color.WHITE);
        avText.setFont(Font.font("System", FontWeight.BOLD, 10));
        StackPane avStack = new StackPane(av, avText);

        VBox info = new VBox(2);
        Label name = new Label("John Doe");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label status = new Label("Arriving Today, 2:00 PM");
        status.setTextFill(Color.web(TEXT_GRAY));
        status.setFont(Font.font(11));
        info.getChildren().addAll(name, status);
        HBox.setHgrow(info, Priority.ALWAYS);

        Button callBtn = new Button("📞");
        callBtn.setStyle("-fx-background-color: #1a2e20; -fx-text-fill: " + PRIMARY
                + "; -fx-background-radius: 20; -fx-min-width: 40; -fx-min-height: 40; -fx-font-size: 14; -fx-cursor: hand;");

        card.getChildren().addAll(avStack, info, callBtn);
        return card;
    }

    private VBox createRateSection() {
        VBox sect = new VBox(15);

        HBox header = new HBox();
        Label title = new Label("Rate the Repair");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 16));
        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);
        Label badge = new Label("LOCKED");
        badge.setTextFill(Color.web("#64748b"));
        badge.setFont(Font.font("System", FontWeight.BOLD, 10));
        badge.setStyle("-fx-background-color: rgba(100, 116, 139, 0.2); -fx-padding: 4 8; -fx-background-radius: 6;");
        header.getChildren().addAll(title, s, badge);

        VBox bars = new VBox(10);
        bars.getChildren().addAll(
                createRatingRow("5", 0),
                createRatingRow("4", 0),
                createRatingRow("3", 0));

        Label count = new Label("0 REVIEWS");
        count.setTextFill(Color.web("#64748b"));
        count.setFont(Font.font(10));

        sect.getChildren().addAll(header, bars, count);
        return sect;
    }

    private HBox createRatingRow(String star, double percent) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);

        Label s = new Label(star);
        s.setTextFill(Color.web(TEXT_GRAY));
        s.setFont(Font.font(12));

        StackPane bar = new StackPane();
        Rectangle bg = new Rectangle(200, 4, Color.web("#2a3b30")); // Fixed width for demo
        bg.setArcWidth(2);
        bg.setArcHeight(2);
        Rectangle fill = new Rectangle(200 * percent, 4, Color.web(TEXT_GRAY));
        fill.setArcWidth(2);
        fill.setArcHeight(2);
        bar.getChildren().addAll(bg, fill);
        StackPane.setAlignment(fill, Pos.CENTER_LEFT);
        HBox.setHgrow(bar, Priority.ALWAYS); // fill width

        Label p = new Label("0%");
        p.setTextFill(Color.web("#64748b"));
        p.setFont(Font.font(10));

        row.getChildren().addAll(s, bar, p);
        return row;
    }

    private enum TimelineState {
        COMPLETED, ACTIVE, PENDING
    }

    private HBox createTimelineStep(String stepTitle, String timeStr, TimelineState state, boolean hasNext,
            Node content) {
        HBox row = new HBox(15);
        row.setAlignment(Pos.TOP_LEFT);

        // Timeline Line Column
        VBox lineCol = new VBox(0);
        lineCol.setAlignment(Pos.TOP_CENTER);

        // Icon
        StackPane icon = new StackPane();
        if (state == TimelineState.COMPLETED) {
            Circle bg = new Circle(12, Color.web(PRIMARY));
            Label check = new Label("✔");
            check.setTextFill(Color.BLACK);
            check.setFont(Font.font(10));
            icon.getChildren().addAll(bg, check);
        } else if (state == TimelineState.ACTIVE) {
            // Glowing effect
            Circle glow = new Circle(16, Color.web(PRIMARY, 0.3));
            Circle bg = new Circle(12, Color.web(PRIMARY));
            Label iconLbl = new Label("👤"); // Technician icon
            iconLbl.setTextFill(Color.BLACK);
            iconLbl.setFont(Font.font(12));
            icon.getChildren().addAll(glow, bg, iconLbl);
        } else {
            Circle bg = new Circle(12, Color.TRANSPARENT);
            bg.setStroke(Color.web("#3b4754"));
            bg.setStrokeWidth(2);
            Label check = new Label("✔");
            check.setTextFill(Color.web("#3b4754"));
            check.setFont(Font.font(10));
            icon.getChildren().addAll(bg, check);
        }

        lineCol.getChildren().add(icon);

        // Line
        if (hasNext) {
            Line line = new Line(0, 0, 0, content != null ? 80 : 30); // Longer line for content
            line.setStroke(state == TimelineState.COMPLETED || state == TimelineState.ACTIVE ? Color.web(PRIMARY)
                    : Color.web("#3b4754"));
            line.setStrokeWidth(2);
            // If content is present, line needs to stretch
            if (content != null) {
                // Dynamic line height tricky in VBox, sticking to fixed or VGrow
                VBox.setVgrow(line, Priority.ALWAYS);
            }
            lineCol.getChildren().add(line);
        }

        // Content Column
        VBox textCol = new VBox(4);
        HBox.setHgrow(textCol, Priority.ALWAYS);

        HBox titleRow = new HBox(10);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        Label t = new Label(stepTitle);
        t.setTextFill(state == TimelineState.PENDING ? Color.web("#64748b") : Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 14));
        titleRow.getChildren().add(t);

        if (state == TimelineState.ACTIVE) {
            Label badge = new Label("NOW");
            badge.setTextFill(Color.BLACK);
            badge.setFont(Font.font("System", FontWeight.BOLD, 9));
            badge.setPadding(new Insets(2, 6, 2, 6));
            badge.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 4;");
            titleRow.getChildren().add(badge);
        }

        textCol.getChildren().add(titleRow);

        if (timeStr != null) {
            Label tm = new Label(timeStr);
            tm.setTextFill(state == TimelineState.PENDING ? Color.web("#64748b") : Color.web(TEXT_GRAY));
            tm.setFont(Font.font(state == TimelineState.PENDING ? 13 : 12));
            if (state == TimelineState.PENDING)
                tm.setStyle("-fx-font-style: italic;");
            textCol.getChildren().add(tm);
        }

        if (content != null) {
            textCol.getChildren().add(content);
        }

        row.getChildren().addAll(lineCol, textCol);
        return row;
    }
}
