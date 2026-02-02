package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MovingChecklistView extends BorderPane {

    private static final String BACKGROUND_DARK = "#221610";
    private static final String PRIMARY = "#f46a25";
    private static final String TEXT_GRAY = "#8a6e60";

    public MovingChecklistView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // --- Header ---
        VBox header = new VBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

        HBox topBar = new HBox(15);
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label backBtn = new Label("\u276E");
        backBtn.setStyle("-fx-font-size: 24; -fx-text-fill: white; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.showHome());

        Label title = new Label("Moving Checklist");
        title.setFont(Font.font("System", FontWeight.BOLD, 20));
        title.setTextFill(Color.WHITE);
        HBox.setHgrow(title, Priority.ALWAYS);

        topBar.getChildren().addAll(backBtn, title);
        header.getChildren().add(topBar);
        setTop(header);

        // --- Content ---
        VBox content = new VBox(0);
        content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // 1. Progress Section
        VBox progressSection = new VBox(12);
        progressSection.setPadding(new Insets(20));
        progressSection.setStyle(
                "-fx-background-color: rgba(45,30,23,0.6); -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        HBox pHead = new HBox();
        Label pTitle = new Label("Setup Progress");
        pTitle.setTextFill(Color.WHITE);
        pTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);
        Label pVal = new Label("35%");
        pVal.setTextFill(Color.web(PRIMARY));
        pVal.setFont(Font.font("System", FontWeight.BOLD, 16));
        pHead.getChildren().addAll(pTitle, sp, pVal);

        StackPane track = new StackPane();
        track.setPrefHeight(8);
        track.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 4;");
        Region fill = new Region();
        fill.setPrefHeight(8);
        fill.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 4;");
        fill.maxWidthProperty().bind(track.widthProperty().multiply(0.35));
        StackPane.setAlignment(fill, Pos.CENTER_LEFT);
        track.getChildren().add(fill);

        Label pDesc = new Label("12 of 34 tasks completed");
        pDesc.setTextFill(Color.web(TEXT_GRAY));
        pDesc.setFont(Font.font(12));

        progressSection.getChildren().addAll(pHead, track, pDesc);
        content.getChildren().add(progressSection);

        // 2. Sections List
        VBox sectionsList = new VBox(25);
        sectionsList.setPadding(new Insets(20, 0, 100, 0));

        sectionsList.getChildren().add(createSection("\uD83D\uDCC5", "1 Month Before",
                new ChecklistItem("Notice to current landlord", true),
                new ChecklistItem("Declutter belongings", false)));

        sectionsList.getChildren().add(createSection("\u23F0", "2 Weeks Before",
                new ChecklistItem("Book movers via FindaHome", false),
                new ChecklistItem("Update address labels", false)));

        sectionsList.getChildren().add(createSection("\uD83D\uDE9A", "Moving Day",
                new ChecklistItem("Submit meter readings", false)));

        content.getChildren().add(sectionsList);

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        setCenter(scroll);

        // --- Bottom Navigation ---
        VBox footer = new VBox();

        Button addBtn = new Button("Add Custom Task +");
        addBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-background-radius: 25; -fx-padding: 10 20; -fx-font-weight: bold;");
        StackPane fabContainer = new StackPane(addBtn);
        fabContainer.setPadding(new Insets(0, 20, 10, 0));
        StackPane.setAlignment(addBtn, Pos.CENTER_RIGHT);

        HBox nav = new HBox();
        nav.setPadding(new Insets(10, 0, 10, 0));
        nav.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");
        nav.setAlignment(Pos.CENTER);

        nav.getChildren().addAll(
                createNavItem("\uD83D\uDD0D", "Market", false),
                new Region() {
                    {
                        HBox.setHgrow(this, Priority.ALWAYS);
                    }
                },
                createNavItem("\u2713", "Checklist", true),
                new Region() {
                    {
                        HBox.setHgrow(this, Priority.ALWAYS);
                    }
                },
                createNavItem("\uD83D\uDDFA", "Map", false),
                new Region() {
                    {
                        HBox.setHgrow(this, Priority.ALWAYS);
                    }
                },
                createNavItem("\uD83D\uDC64", "Profile", false));

        footer.getChildren().addAll(fabContainer, nav);
        setBottom(footer);
    }

    private VBox createSection(String icon, String title, ChecklistItem... items) {
        VBox section = new VBox(12);
        HBox head = new HBox(10);
        head.setAlignment(Pos.CENTER_LEFT);
        head.setPadding(new Insets(0, 20, 0, 20));

        Label i = new Label(icon);
        i.setTextFill(Color.web(PRIMARY));
        i.setFont(Font.font(20));
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 18));
        head.getChildren().addAll(i, t);

        VBox list = new VBox(10);
        for (ChecklistItem item : items) {
            list.getChildren().add(createCard(item));
        }

        section.getChildren().addAll(head, list);
        return section;
    }

    private VBox createCard(ChecklistItem item) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12;");
        VBox.setMargin(card, new Insets(0, 20, 0, 20));

        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        CheckBox cb = new CheckBox();
        cb.setSelected(item.completed);
        Label t = new Label(item.task);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.MEDIUM, 14));
        row.getChildren().addAll(cb, t);

        card.getChildren().add(row);

        if (item.task.contains("Book movers")) {
            card.setCursor(javafx.scene.Cursor.HAND);
            card.setOnMouseClicked(e -> MainApp.navigateTo(new MoversQuoteRequestView()));
        }

        return card;
    }

    private VBox createNavItem(String icon, String label, boolean active) {
        VBox v = new VBox(4);
        v.setAlignment(Pos.CENTER);
        Label i = new Label(icon);
        i.setTextFill(active ? Color.web(PRIMARY) : Color.web("#9ca3af"));
        i.setFont(Font.font(20));
        Label l = new Label(label);
        l.setTextFill(active ? Color.web(PRIMARY) : Color.web("#9ca3af"));
        l.setFont(Font.font(10));
        v.getChildren().addAll(i, l);
        v.setPadding(new Insets(0, 20, 0, 20));
        return v;
    }

    private static class ChecklistItem {
        String task;
        boolean completed;

        ChecklistItem(String t, boolean c) {
            task = t;
            completed = c;
        }
    }
}
