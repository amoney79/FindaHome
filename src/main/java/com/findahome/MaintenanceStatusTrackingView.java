package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MaintenanceStatusTrackingView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String TEXT_GRAY = "#9db9a6";
    private static final String CARD_BG = "#1c271f";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

    public MaintenanceStatusTrackingView(String title, String status) {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateCached("maintenance_list", MaintenanceRequestsListView::new));

        Label navTitle = new Label("Request Status");
        navTitle.setTextFill(Color.WHITE);
        navTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        navTitle.setAlignment(Pos.CENTER);
        HBox.setHgrow(navTitle, Priority.ALWAYS);

        header.getChildren().addAll(backBtn, navTitle, new Region());

        // Scroll Content
        VBox scrollContent = new VBox(25);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        scrollContent.setPadding(new Insets(20, 20, 120, 20));

        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Status Card
        VBox statusCard = new VBox(15);
        statusCard.setPadding(new Insets(20));
        statusCard.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 20; -fx-border-color: "
                + BORDER_COLOR + ";");

        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 22));

        Label s = new Label(status.toUpperCase());
        s.setTextFill(Color.web(PRIMARY));
        s.setFont(Font.font("System", FontWeight.BOLD, 14));
        s.setStyle("-fx-background-color: " + PRIMARY + "22; -fx-padding: 4 12; -fx-background-radius: 8;");

        statusCard.getChildren().addAll(t, s);

        // Timeline
        VBox timeline = new VBox(0);
        timeline.getChildren().addAll(
                createTimelineStep("Request Reported", "June 15, 09:30 AM", true),
                createTimelineStep("Technician Assigned", "June 15, 11:45 AM", true),
                createTimelineStep("Inspection in Progress", "June 16, 02:00 PM", true),
                createTimelineStep("Waiting for Parts", "Expected June 18", false),
                createTimelineStep("Issue Resolved", "Pending", false));

        // Technician Info
        VBox techSect = new VBox(15);
        Label techTitle = new Label("Assigned Technician");
        techTitle.setTextFill(Color.WHITE);
        techTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        HBox techCard = new HBox(15);
        techCard.setPadding(new Insets(15));
        techCard.setAlignment(Pos.CENTER_LEFT);
        techCard.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 20; -fx-border-color: "
                + BORDER_COLOR + ";");

        Circle av = new Circle(25, Color.web(PRIMARY, 0.2));
        Label initials = new Label("JM");
        initials.setTextFill(Color.web(PRIMARY));
        initials.setFont(Font.font("System", FontWeight.BOLD, 16));
        StackPane avBox = new StackPane(av, initials);

        VBox techInfo = new VBox(2);
        Label name = new Label("John Mwangi");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label spec = new Label("Plumbing Specialist \u2022 4.9 \u2b50");
        spec.setTextFill(Color.web(TEXT_GRAY));
        spec.setFont(Font.font(12));
        techInfo.getChildren().addAll(name, spec);
        HBox.setHgrow(techInfo, Priority.ALWAYS);

        HBox techActions = new HBox(10);
        Label call = createActionIcon("\ud83d\udcde");
        Label chat = createActionIcon("\ud83d\udcac");
        techActions.getChildren().addAll(call, chat);

        techCard.getChildren().addAll(avBox, techInfo, techActions);
        techSect.getChildren().addAll(techTitle, techCard);

        scrollContent.getChildren().addAll(statusCard, timeline, techSect);

        layout.getChildren().addAll(header, scroll);
        getChildren().add(layout);
    }

    private HBox createTimelineStep(String title, String time, boolean completed) {
        HBox row = new HBox(15);
        row.setAlignment(Pos.TOP_LEFT);

        VBox dotLine = new VBox(0);
        dotLine.setAlignment(Pos.TOP_CENTER);
        Circle dot = new Circle(6, completed ? Color.web(PRIMARY) : Color.web("#3b4754"));
        Line line = new Line(0, 0, 0, 40);
        line.setStroke(completed ? Color.web(PRIMARY) : Color.web("#3b4754"));
        line.setStrokeWidth(2);
        dotLine.getChildren().addAll(dot, line);

        VBox text = new VBox(2);
        Label t = new Label(title);
        t.setTextFill(completed ? Color.WHITE : Color.web(TEXT_GRAY));
        t.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label tm = new Label(time);
        tm.setTextFill(Color.web(TEXT_GRAY));
        tm.setFont(Font.font(12));
        text.getChildren().addAll(t, tm);

        row.getChildren().addAll(dotLine, text);
        return row;
    }

    private Label createActionIcon(String icon) {
        Label l = new Label(icon);
        l.setAlignment(Pos.CENTER);
        l.setPrefSize(40, 40);
        l.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 20; -fx-text-fill: " + PRIMARY
                + "; -fx-font-size: 18;");
        return l;
    }
}
