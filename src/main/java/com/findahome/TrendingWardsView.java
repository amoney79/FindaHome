package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.effect.DropShadow;

public class TrendingWardsView extends StackPane {

    private static final String BACKGROUND_DARK = "#221610"; // Updated per HTML dark theme
    private static final String BACKGROUND_LIGHT = "#f8f6f5";
    private static final String PRIMARY = "#f46a25"; // Orange primary
    private static final String CARD_BG_DARK = "#111827"; // gray-900 equivalent
    private static final String TEXT_WHITE = "#FFFFFF";
    private static final String TEXT_GRAY = "#9ca3af";

    public TrendingWardsView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Main Layout
        VBox mainLayout = new VBox(0);

        // 1. Top Navigation Bar
        VBox topBarContainer = new VBox(0);
        topBarContainer.setStyle(
                "-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: #1f2937; -fx-border-width: 0 0 1 0;");
        topBarContainer.setPadding(new Insets(10, 16, 10, 16));

        HBox topBar = new HBox(0);
        topBar.setAlignment(Pos.CENTER_LEFT);

        Button backBtn = new Button("\uE5CB"); // arrow_back_ios
        backBtn.setFont(Font.font("Material Symbols Outlined", 20)); // Placeholder font logic
        backBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-cursor: hand;");
        backBtn.setPadding(new Insets(8));
        backBtn.setOnAction(e -> MainApp.showHome());

        VBox titleBox = new VBox(2);
        titleBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(titleBox, Priority.ALWAYS);

        HBox titleRow = new HBox(6);
        titleRow.setAlignment(Pos.CENTER);
        Label title = new Label("Trending Now");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));

        // Pulse effect simulation
        StackPane pulse = new StackPane();
        Circle p1 = new Circle(4, Color.web(PRIMARY));
        p1.setOpacity(0.75);
        Circle p2 = new Circle(4, Color.web(PRIMARY));
        pulse.getChildren().addAll(p1, p2);

        titleRow.getChildren().addAll(title, pulse);

        Label subTitle = new Label("MARKET INSIGHTS");
        subTitle.setTextFill(Color.web(PRIMARY));
        subTitle.setFont(Font.font("System", FontWeight.BOLD, 10));

        titleBox.getChildren().addAll(titleRow, subTitle);

        Button monitorBtn = new Button("\uE9D2"); // monitoring icon
        monitorBtn.setFont(Font.font("Material Symbols Outlined", 24));
        monitorBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");

        topBar.getChildren().addAll(backBtn, titleBox, monitorBtn);

        // 2. Segmented Control
        HBox segmentedControl = new HBox(0);
        segmentedControl.setPadding(new Insets(12, 16, 12, 16));
        segmentedControl.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        HBox segmentBg = new HBox(4);
        segmentBg.setPadding(new Insets(4));
        segmentBg.setAlignment(Pos.CENTER);
        segmentBg.setStyle("-fx-background-color: #1f2937; -fx-background-radius: 12;"); // gray-800
        HBox.setHgrow(segmentBg, Priority.ALWAYS);

        segmentBg.getChildren().addAll(
                createSegmentButton("National", true),
                createSegmentButton("By County", false));
        segmentedControl.getChildren().add(segmentBg);

        // 3. County Chips
        ScrollPane chipsScroll = new ScrollPane();
        chipsScroll.setFitToHeight(true);
        chipsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chipsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chipsScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        HBox chipsBox = new HBox(12);
        chipsBox.setPadding(new Insets(0, 16, 16, 16));
        chipsBox.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        chipsBox.getChildren().addAll(
                createChip("Nairobi", true),
                createChip("Mombasa", false),
                createChip("Kisumu", false),
                createChip("Kiambu", false));
        chipsScroll.setContent(chipsBox);

        topBarContainer.getChildren().addAll(topBar, segmentedControl, chipsScroll);

        // --- Content ---
        VBox contentList = new VBox(8); // Gap 8px approx gap-2
        contentList.setPadding(new Insets(16));

        // Rank 1
        contentList.getChildren().add(createRank1Card());

        // Rank 2
        contentList.getChildren().add(createRankCard(2, "Nyali", "Kisauni, Mombasa",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuAa4pRTWoupCOIVxZCfW8a_WqWWHhm0uX-CDr9-aBBr5tL8kDUvI-PviN7HI_SRZc_zR3b2uRKJazMi7eX5VVhC59pxThomUQ0JULm5ZLc8OTN7HFAaDJ-A8ytintilaPVSwuMeKbDkkvddps0A9Nz4GMFzCPlsaPOI1h_qnGlhUTlbfKLvw158-_ZXWtPc_NgRDNYYQA2we4kABM1KKUjmMRK19InHzLdf3tNk6JgvWnLKkWy0W2QwZQQVqPBXU2F1BH77dFDamGM",
                "#9ca3af", 0.88, "2.1k"));

        // Rank 3
        contentList.getChildren().add(createRankCard(3, "Kilimani", "Dagoretti North, Nairobi",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuDfglYz0RjI0dPgs-65vZbgAOQZvH1HxHC47QP1Y4h_oM8T0Uyh5EZlU7znfTYtTw4tehh2TgL5vFxPS-zEMEf9yO7-9b0Wjz0lao9t1yaCRwb_WYix3Gwwhy7vLbed0LqjJAgAEnMLrBHX2p9uVLzeWIr1pAeT9zvd3ISuAiFoYErKZ2_Hm1ebixLXKk06yqwxRwqsOtoE0LbD8VruZ5WW44oLxEV_f5AotVzt8FZH7UH87wklRWcAodTksQAD35OH65jCDpN6WOA",
                "#fdba74", 0.82, "1.8k"));

        // Rank 4-6
        contentList.getChildren().add(createListItem(4, "Langata", "Langata, Nairobi",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuC-0yO5oXmKbcri2gzfRQZjL19MXXLXHgFzHqv2tMH4K98Z5godDw5gNxB16KFX2VcoyWTo41vFMYzpU9CYure-Y2bPdW4XEd_u1-BzKwUQjaqbQPaoKE8xojQNOzegYMUJuwdC20M5oP8of2XvGHzJCW1cW2wwErvwOG_odV9br2_tcUFgmdDQmNEbPdGyQzBt7SSqrBoSJc33mGMNXLhix4GjxcYQfGjv67EXtPp8fR3MCMSJWJRVE4ZCrBv-uKnAMvuiYG6L-20",
                "1.4k"));
        contentList.getChildren().add(createListItem(5, "Milimani", "Kisumu Central, Kisumu",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuAsSntHXUHdzmX4UHjyGJGmesMu4ziUUkNLB8NezRBMtlaKwu4UzJ2UX6mabCCNhKkxDRaeiewAP5Z6kkahQv3tIPKaLw2ANzjdrVcygf0v6ETjqiAXqfXs1jo6lrnwIkTQh_Q19hcTXzRZwL1UlXNDnNj8Ohb3vFDLAkHYKkC7S7J7Cm-zJP2X5c4B8vbHzSpAFAkQBmVGp6FFjPjwtFrby8al03qTT0j8Mw3M8VTfEp-Jkgl_eLMJBvK3l7yDQXofjkWubeU1Frk",
                "1.1k"));
        contentList.getChildren().add(createListItem(6, "Syokimau", "Mavoko, Machakos",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuDOniHHYGFJwmCWGms0MrAzS-JwLFkDYylCLsS_FDzWgbH88AxwIe6IM8ZAfXewzm_r3uzD8fQcUl0mJ1pizNkXqpOfEa1D8zZ4f1rui5bag2gUpFDEx4VmCpbJ7nppPUgOg_xea0o4LPWzDXO6Xhl-Wm55q8g4scIa1bHSvLSo7kfNO-CWwrCsXzOf1UetgxiPEPi8CJMvEw4gFiIsQb5-28rLsA2SXMKO9kHzGR-b0wO4h4KZiQFa5ABX2CeNwd5rRpQaBnTQCDE",
                "980"));

        // Spacer for FAB
        Region spacer = new Region();
        spacer.setPrefHeight(100);
        contentList.getChildren().add(spacer);

        ScrollPane scrollPane = new ScrollPane(contentList);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        mainLayout.getChildren().addAll(topBarContainer, scrollPane);

        // Fab
        Button mapFab = new Button("MAP VIEW");
        mapFab.setGraphic(new Label("\uE55B")); // map icon
        mapFab.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 30; -fx-padding: 12 24 12 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5); -fx-font-size: 14;");
        StackPane.setAlignment(mapFab, Pos.BOTTOM_CENTER);
        StackPane.setMargin(mapFab, new Insets(0, 0, 32, 0));

        getChildren().addAll(mainLayout, mapFab);
    }

    private Button createSegmentButton(String text, boolean active) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(btn, Priority.ALWAYS);
        if (active) {
            btn.setStyle("-fx-background-color: #374151; -fx-text-fill: " + PRIMARY
                    + "; -fx-background-radius: 8; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");
        } else {
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #9ca3af; -fx-font-weight: bold;");
        }
        return btn;
    }

    private Button createChip(String text, boolean active) {
        Button btn = new Button(text);
        if (active) {
            btn.setStyle("-fx-background-color: " + PRIMARY
                    + "; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 8 20; -fx-font-weight: bold;");
            btn.setGraphic(new Label("\uE313")); // arrow_down
            btn.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);
        } else {
            btn.setStyle(
                    "-fx-background-color: #1f2937; -fx-text-fill: #d1d5db; -fx-background-radius: 20; -fx-padding: 8 16; -fx-font-weight: medium; -fx-border-color: #374151; -fx-border-radius: 20;");
        }
        return btn;
    }

    private VBox createRank1Card() {
        VBox card = new VBox(0);
        card.setStyle("-fx-background-color: " + CARD_BG_DARK
                + "; -fx-background-radius: 12; -fx-border-color: rgba(244, 106, 37, 0.2); -fx-border-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(244, 106, 37, 0.15), 15, 0, 0, 0);");
        card.setPadding(new Insets(16));

        HBox mainRow = new HBox(16);

        // Image + Rank
        StackPane imgStack = new StackPane();
        ImageView iv = new ImageView();
        try {
            Image img = new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuB1JWbg4pePkdnc5_JAZN1MJQkzymJnuv3TVWKDDcnbkwak-cJ_gecpDzYqcE5Q0EqSO5JfLSXgJWChDyg8EfLkdyaWmFJ3iuE1-J0JaXk-nPaUCvoZeXKpNn7NQHP7iXtAQOyg11_4s3pOR9oVtCLXmH_Gcv3-XnHqI58gi0I9JEqizfkv0YvypwcSXD9c9-8IJMDQ_9xEfwZt1CfRuVfB1Wj2-O7UeQHcKH7tMbb3P5acyCKjCnw4eIP9Kiz1GY_UDfdym6Xa_2k",
                    80, 80, false, true);
            iv.setImage(img);
        } catch (Exception e) {
        }
        iv.setFitWidth(80);
        iv.setFitHeight(80);

        Rectangle clip = new Rectangle(80, 80);
        clip.setArcWidth(12);
        clip.setArcHeight(12);
        iv.setClip(clip);

        Label rank = new Label("1");
        rank.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 2 8; -fx-border-color: white; -fx-border-radius: 20; -fx-border-width: 2;");
        StackPane.setAlignment(rank, Pos.TOP_LEFT);
        StackPane.setMargin(rank, new Insets(-8, 0, 0, -8));

        imgStack.getChildren().addAll(iv, rank);

        // Details
        VBox details = new VBox(4);
        details.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(details, Priority.ALWAYS);

        HBox nameRow = new HBox(4);
        Label name = new Label("Kileleshwa");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label fire = new Label("\uE80E"); // fire icon
        fire.setFont(Font.font("Material Symbols Outlined", 18));
        fire.setTextFill(Color.web(PRIMARY));
        nameRow.getChildren().addAll(name, fire);

        Label sub = new Label("Westlands, Nairobi");
        sub.setTextFill(Color.web(TEXT_GRAY));
        sub.setFont(Font.font(13));

        HBox trend = new HBox(4);
        Label up = new Label("\uE8E5"); // trend up
        up.setTextFill(Color.web(PRIMARY));
        Label trendText = new Label("Rising fast (+22%)");
        trendText.setTextFill(Color.web(PRIMARY));
        trendText.setFont(Font.font(11));
        trend.getChildren().addAll(up, trendText);

        details.getChildren().addAll(nameRow, sub, trend);

        // Metric
        VBox metric = new VBox(2);
        metric.setAlignment(Pos.CENTER_RIGHT);
        Label mLabel = new Label("HOT METER");
        mLabel.setTextFill(Color.web(PRIMARY));
        mLabel.setFont(Font.font("System", FontWeight.BOLD, 10));

        HBox meter = new HBox(8);
        StackPane track = new StackPane();
        track.setPrefSize(70, 8);
        track.setStyle("-fx-background-color: #1f2937; -fx-background-radius: 4;"); // gray-800
        Region bar = new Region();
        bar.setPrefSize(66, 8); // 95%
        bar.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 4;");
        track.getChildren().add(bar);
        StackPane.setAlignment(bar, Pos.CENTER_LEFT);

        Label score = new Label("2.5k");
        score.setTextFill(Color.WHITE);
        score.setFont(Font.font("System", FontWeight.BOLD, 14));
        meter.getChildren().addAll(track, score);

        Label views = new Label("Views today");
        views.setTextFill(Color.gray(0.5));
        views.setFont(Font.font(10));

        metric.getChildren().addAll(mLabel, meter, views);

        mainRow.getChildren().addAll(imgStack, details, metric);
        card.getChildren().add(mainRow);
        return card;
    }

    private VBox createRankCard(int r, String n, String loc, String url, String badgeColor, double progress,
            String viewsCount) {
        VBox card = new VBox(0);
        card.setStyle("-fx-background-color: " + CARD_BG_DARK
                + "; -fx-background-radius: 12; -fx-border-color: #1f2937; -fx-border-radius: 12;");
        card.setPadding(new Insets(12, 16, 12, 16));

        HBox mainRow = new HBox(16);

        // Image + Rank
        StackPane imgStack = new StackPane();
        ImageView iv = new ImageView();
        try {
            Image img = new Image(url, 70, 70, false, true);
            iv.setImage(img);
        } catch (Exception e) {
        }
        iv.setFitWidth(70);
        iv.setFitHeight(70);

        Rectangle clip = new Rectangle(70, 70);
        clip.setArcWidth(12);
        clip.setArcHeight(12);
        iv.setClip(clip);

        Label rank = new Label(String.valueOf(r));
        rank.setStyle("-fx-background-color: " + badgeColor
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 2 8; -fx-border-color: white; -fx-border-radius: 20; -fx-border-width: 2; -fx-font-size: 10;");
        StackPane.setAlignment(rank, Pos.TOP_LEFT);
        StackPane.setMargin(rank, new Insets(-8, 0, 0, -8));

        imgStack.getChildren().addAll(iv, rank);

        // Details
        VBox details = new VBox(4);
        details.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(details, Priority.ALWAYS);

        Label name = new Label(n);
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label sub = new Label(loc);
        sub.setTextFill(Color.web(TEXT_GRAY));
        sub.setFont(Font.font(13));

        details.getChildren().addAll(name, sub);

        // Metric
        VBox metric = new VBox(2);
        metric.setAlignment(Pos.CENTER_RIGHT);

        HBox meter = new HBox(8);
        StackPane track = new StackPane();
        track.setPrefSize(60, 6);
        track.setStyle("-fx-background-color: #1f2937; -fx-background-radius: 4;");
        Region bar = new Region();
        bar.setPrefSize(60 * progress, 6);
        bar.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 4;");
        track.getChildren().add(bar);
        StackPane.setAlignment(bar, Pos.CENTER_LEFT);

        Label score = new Label(viewsCount);
        score.setTextFill(Color.WHITE);
        score.setFont(Font.font("System", FontWeight.BOLD, 14));
        meter.getChildren().addAll(track, score);

        Label views = new Label("Views today");
        views.setTextFill(Color.gray(0.5));
        views.setFont(Font.font(10));

        metric.getChildren().addAll(meter, views);

        mainRow.getChildren().addAll(imgStack, details, metric);
        card.getChildren().add(mainRow);
        return card;
    }

    private HBox createListItem(int r, String n, String loc, String url, String count) {
        HBox card = new HBox(16);
        card.setPadding(new Insets(12, 16, 12, 16));
        card.setStyle("-fx-background-color: " + CARD_BG_DARK
                + "; -fx-background-radius: 12; -fx-border-color: #1f2937; -fx-border-width: 0 0 1 0;");
        card.setAlignment(Pos.CENTER_LEFT);

        Label rank = new Label(String.valueOf(r));
        rank.setTextFill(Color.web("#9ca3af"));
        rank.setFont(Font.font("System", FontWeight.BOLD, 14));
        rank.setMinWidth(24);
        rank.setAlignment(Pos.CENTER);

        ImageView iv = new ImageView();
        try {
            Image img = new Image(url, 60, 60, false, true);
            iv.setImage(img);
        } catch (Exception e) {
        }
        iv.setFitWidth(60);
        iv.setFitHeight(60);
        Rectangle clip = new Rectangle(60, 60);
        clip.setArcWidth(12);
        clip.setArcHeight(12);
        iv.setClip(clip);

        VBox details = new VBox(2);
        details.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(details, Priority.ALWAYS);
        Label name = new Label(n);
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label l = new Label(loc);
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font(12));
        details.getChildren().addAll(name, l);

        VBox meta = new VBox(0);
        meta.setAlignment(Pos.CENTER_RIGHT);
        Label c = new Label(count);
        c.setTextFill(Color.WHITE);
        c.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label s = new Label("Searches");
        s.setTextFill(Color.gray(0.5));
        s.setFont(Font.font(9));
        meta.getChildren().addAll(c, s);

        card.getChildren().addAll(rank, iv, details, meta);
        return card;
    }
}
