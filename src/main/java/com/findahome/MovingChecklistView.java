package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MovingChecklistView extends VBox {

    private static final String BACKGROUND_DARK = "#221610";
    private static final String PRIMARY = "#f46a25";

    public MovingChecklistView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");
        setSpacing(0);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u276E");
        backBtn.setStyle("-fx-font-size: 24; -fx-text-fill: white; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.showHome());

        Label title = new Label("Moving Checklist");
        title.setFont(Font.font("System", FontWeight.BOLD, 20));
        title.setTextFill(Color.WHITE);
        HBox.setHgrow(title, Priority.ALWAYS);

        header.getChildren().addAll(backBtn, title);

        // Scroll Content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        VBox content = new VBox(25);
        content.setPadding(new Insets(20, 20, 40, 20));
        content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Progress Widget
        VBox progress = new VBox(10);
        progress.setPadding(new Insets(15));
        progress.setStyle("-fx-background-color: rgba(45,30,23,0.6); -fx-background-radius: 16;");

        HBox pHead = new HBox();
        Label pT = new Label("Setup Progress");
        pT.setTextFill(Color.WHITE);
        pT.setFont(Font.font("System", FontWeight.BOLD, 14));
        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);
        Label pV = new Label("35%");
        pV.setTextFill(Color.web(PRIMARY));
        pHead.getChildren().addAll(pT, sp, pV);

        ProgressBar pb = new ProgressBar(0.35);
        pb.setMaxWidth(Double.MAX_VALUE);
        pb.setPrefHeight(6);
        pb.setStyle("-fx-accent: " + PRIMARY + ";");

        progress.getChildren().addAll(pHead, pb);
        content.getChildren().add(progress);

        // Sections
        content.getChildren().addAll(
                createSection("\uD83D\uDCC5", "1 Month Before", "Notice to landlord", "Declutter stuff"),
                createSection("\u23F0", "2 Weeks Before", "Book movers", "Change address"),
                createSection("\uD83D\uDE9A", "Moving Day", "Final meter readings"));

        scrollPane.setContent(content);

        // Footer Nav
        HBox footer = new HBox();
        footer.setPadding(new Insets(10, 0, 10, 0));
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");

        footer.getChildren().addAll(
                createNavItem("\ud83d\udd0d", "Market", false),
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
                createNavItem("\ud83d\uddfa", "Map", false),
                new Region() {
                    {
                        HBox.setHgrow(this, Priority.ALWAYS);
                    }
                },
                createNavItem("\ud83d\udc64", "Profile", false));

        getChildren().addAll(header, scrollPane, footer);
    }

    private VBox createSection(String icon, String title, String... tasks) {
        VBox s = new VBox(12);
        HBox h = new HBox(8);
        h.setAlignment(Pos.CENTER_LEFT);
        Label ic = new Label(icon);
        ic.setTextFill(Color.web(PRIMARY));
        ic.setFont(Font.font(18));
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 16));
        h.getChildren().addAll(ic, t);

        VBox list = new VBox(8);
        for (String task : tasks) {
            HBox card = new HBox(10);
            card.setPadding(new Insets(12));
            card.setAlignment(Pos.CENTER_LEFT);
            card.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12;");
            CheckBox cb = new CheckBox();
            Label tl = new Label(task);
            tl.setTextFill(Color.WHITE);
            card.getChildren().addAll(cb, tl);
            list.getChildren().add(card);
        }

        s.getChildren().addAll(h, list);
        return s;
    }

    private VBox createNavItem(String icon, String label, boolean active) {
        VBox v = new VBox(4);
        v.setAlignment(Pos.CENTER);
        v.setPadding(new Insets(0, 20, 0, 20));
        Label i = new Label(icon);
        i.setFont(Font.font(20));
        i.setTextFill(active ? Color.web(PRIMARY) : Color.GRAY);
        Label l = new Label(label);
        l.setFont(Font.font(10));
        l.setTextFill(active ? Color.web(PRIMARY) : Color.GRAY);
        v.getChildren().addAll(i, l);
        return v;
    }
}
