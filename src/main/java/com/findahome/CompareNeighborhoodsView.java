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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Node;

public class CompareNeighborhoodsView extends VBox {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String PRIMARY = "#135bec";
    private static final String TEXT_GRAY = "#9da6b9";
    private static final String CARD_BG = "#1c222c"; // Dark variant of white
    private static final String DIVIDER_COLOR = "#2a3544";

    public CompareNeighborhoodsView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // --- Header ---
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: " + DIVIDER_COLOR + "; -fx-border-width: 0 0 1 0; -fx-background-color: "
                + BACKGROUND_DARK + "cc;");

        Button backBtn = new Button("\u2190"); // Arrow back
        backBtn.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 20; -fx-cursor: hand; -fx-background-radius: 50;");
        backBtn.setOnAction(e -> {
            // Go back logic, defaulting to GuideView or previous
            MainApp.showHome();
        });

        Label title = new Label("Compare Wards");
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setTextFill(Color.WHITE);
        HBox.setHgrow(title, Priority.ALWAYS);
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);

        Button shareBtn = new Button("\uE80D"); // Share icon representation
        shareBtn.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 20; -fx-cursor: hand; -fx-background-radius: 50;");

        header.getChildren().addAll(backBtn, title, shareBtn);

        // --- Main Content Scroll ---
        VBox content = new VBox(20);
        content.setPadding(new Insets(0, 0, 100, 0)); // Padding for bottom bar

        // 1. Neighborhood Image Header (Split View)
        HBox compareHeader = new HBox(8);
        compareHeader.setPadding(new Insets(15, 15, 0, 15));

        StackPane ward1 = createWardHeaderCard("Kileleshwa", "Nairobi West",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuBdXNTtzg2hHSJGDYoyAT-lfqVvcEjJ957ltT51mGIL_SMo_0Bp18hPe56Q8MDYO2BCw2MPoJjK5xj9BJ7qIDO_b9y4qLHx1bU6ICqlxM09KyVIrSOSVnSX-s72ozuns6oheQNu2Mh0UXKsjTmRlmAMo05QPzsJvBttP0omz0QSamTWRTkeFOV6HryrRBQ5ZgyZCbcBNBeI75v9_dl3kGJjUF7wtU_cTFfjqOHtYHyYKAO0hQBGrrLxPmR-ANbZqC3FRUJ065wOvv0");

        StackPane ward2 = createWardHeaderCard("Kilimani", "Nairobi West",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuB5cTCG3KHZre-b9-9axu7bIwey6V2chLfidTgdK31tf8t4pcNDS7IX2biWLZgo1jLw9RcEhaHYggzTqYy5xy28wYF-NnW3bX8XlPIKa9SQcAbZbL1ACS90lfpO3TbnYF4ML0yrTAOM6PWOwdg7UZeo7Jx8q8EoHpxsBeGK6uSkan8k9r_HYnfAW9GPfWpr42Lijyxge-hzPM1nUBirpWBw2cdzb-BNaIIRTEMQuMdN2STnMlodrN_VqtXJgoG7STYMxPmucLQkPKc");

        HBox.setHgrow(ward1, Priority.ALWAYS);
        HBox.setHgrow(ward2, Priority.ALWAYS);
        compareHeader.getChildren().addAll(ward1, ward2);

        // 2. Rent Comparison
        VBox rentSection = new VBox(8);
        rentSection.setPadding(new Insets(0, 15, 0, 15));
        Label rentTitle = new Label("AVERAGE RENT (2-BED)");
        rentTitle.setTextFill(Color.web(TEXT_GRAY));
        rentTitle.setFont(Font.font("System", FontWeight.BOLD, 10));
        rentTitle.setStyle("-fx-opacity: 0.8;");

        HBox rentCard = new HBox(0);
        rentCard.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                + DIVIDER_COLOR + "; -fx-border-radius: 12;");

        VBox rentLeft = new VBox(4);
        rentLeft.setAlignment(Pos.CENTER);
        rentLeft.setPadding(new Insets(15));
        Label p1 = new Label("KSh 85k");
        p1.setTextFill(Color.web(PRIMARY));
        p1.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label l1 = new Label("Starting from");
        l1.setTextFill(Color.web(TEXT_GRAY));
        l1.setFont(Font.font(10));
        rentLeft.getChildren().addAll(p1, l1);
        HBox.setHgrow(rentLeft, Priority.ALWAYS);

        VBox rentRight = new VBox(4);
        rentRight.setAlignment(Pos.CENTER);
        rentRight.setPadding(new Insets(15));
        rentRight.setStyle("-fx-border-color: " + DIVIDER_COLOR + "; -fx-border-width: 0 0 0 1;");
        Label p2 = new Label("KSh 95k");
        p2.setTextFill(Color.WHITE);
        p2.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label l2 = new Label("Starting from");
        l2.setTextFill(Color.web(TEXT_GRAY));
        l2.setFont(Font.font(10));
        rentRight.getChildren().addAll(p2, l2);
        HBox.setHgrow(rentRight, Priority.ALWAYS);

        rentCard.getChildren().addAll(rentLeft, rentRight);
        rentSection.getChildren().addAll(rentTitle, rentCard);

        // 3. Safety Score
        VBox safetySection = new VBox(8);
        safetySection.setPadding(new Insets(0, 15, 0, 15));
        Label safetyTitle = new Label("SAFETY SCORE");
        safetyTitle.setTextFill(Color.web(TEXT_GRAY));
        safetyTitle.setFont(Font.font("System", FontWeight.BOLD, 10));
        safetyTitle.setStyle("-fx-opacity: 0.8;");

        VBox safetyCard = new VBox(15);
        safetyCard.setPadding(new Insets(15));
        safetyCard.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                + DIVIDER_COLOR + "; -fx-border-radius: 12;");

        safetyCard.getChildren().add(createProgressRow("Kileleshwa", "8.5/10", 0.85, PRIMARY));
        safetyCard.getChildren().add(createProgressRow("Kilimani", "7.9/10", 0.79, "#64748b")); // Slate-500 logic

        safetySection.getChildren().addAll(safetyTitle, safetyCard);

        // 4. Infrastructure Grid
        VBox infraSection = new VBox(8);
        infraSection.setPadding(new Insets(0, 15, 0, 15));
        Label infraTitle = new Label("COMMUNITY & INFRASTRUCTURE");
        infraTitle.setTextFill(Color.web(TEXT_GRAY));
        infraTitle.setFont(Font.font("System", FontWeight.BOLD, 10));
        infraTitle.setStyle("-fx-opacity: 0.8;");

        VBox infraList = new VBox(12);
        infraList.getChildren().add(createComparisonRow("Primary Schools Count", "12", "Walking distance", "18",
                "Walking distance", false));
        infraList.getChildren().add(
                createComparisonRow("Distance to CBD", "5.2 km", "~15 min drive", "4.8 km", "~12 min drive", true)); // Left
                                                                                                                     // highlighted?
                                                                                                                     // HTML
                                                                                                                     // shows
                                                                                                                     // left
                                                                                                                     // primary
                                                                                                                     // for
                                                                                                                     // Distance?
                                                                                                                     // Wait
                                                                                                                     // HTML
                                                                                                                     // says:
                                                                                                                     // Left
                                                                                                                     // 5.2
                                                                                                                     // (primary),
                                                                                                                     // Right
                                                                                                                     // 4.8
                                                                                                                     // (normal).
                                                                                                                     // 5.2km
                                                                                                                     // is
                                                                                                                     // worse?
                                                                                                                     // Or
                                                                                                                     // highlights
                                                                                                                     // differences.
                                                                                                                     // Let's
                                                                                                                     // stick
                                                                                                                     // to
                                                                                                                     // visual.
        infraList.getChildren().add(
                createComparisonRow("Public Transport Access", "\u2605 Moderate", "", "\u2726 Excellent", "", true)); // Custom
                                                                                                                      // content
                                                                                                                      // logic
                                                                                                                      // needed
                                                                                                                      // for
                                                                                                                      // icons

        infraSection.getChildren().addAll(infraTitle, infraList);

        content.getChildren().addAll(compareHeader, rentSection, safetySection, infraSection);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // --- Bottom CTA ---
        HBox bottomBar = new HBox(12);
        bottomBar.setPadding(new Insets(15));
        bottomBar.setStyle("-fx-background-color: " + BACKGROUND_DARK + "ee; -fx-border-color: " + DIVIDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");
        bottomBar.setAlignment(Pos.CENTER);

        Button btnLeft = new Button("Explore Kileleshwa \u2192");
        btnLeft.setMaxWidth(Double.MAX_VALUE);
        btnLeft.setPrefHeight(45);
        btnLeft.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12;");
        HBox.setHgrow(btnLeft, Priority.ALWAYS);
        btnLeft.setOnAction(e -> MainApp.navigateTo(new AmenitiesMapView())); // Assuming this link

        Button btnRight = new Button("Explore Kilimani \u2192");
        btnRight.setMaxWidth(Double.MAX_VALUE);
        btnRight.setPrefHeight(45);
        btnRight.setStyle(
                "-fx-background-color: #334155; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 12; -fx-border-color: #475569; -fx-border-radius: 12;");
        HBox.setHgrow(btnRight, Priority.ALWAYS);
        btnRight.setOnAction(e -> MainApp.navigateTo(new AmenitiesMapView())); // Assuming similar link

        bottomBar.getChildren().addAll(btnLeft, btnRight);

        // Main Layout
        StackPane root = new StackPane();
        root.getChildren().addAll(scrollPane, bottomBar);
        StackPane.setAlignment(bottomBar, Pos.BOTTOM_CENTER);

        getChildren().addAll(header, root);
    }

    private StackPane createWardHeaderCard(String title, String subtitle, String imageUrl) {
        StackPane stack = new StackPane();
        stack.setPrefHeight(180);

        Rectangle clip = new Rectangle();
        clip.setArcWidth(16);
        clip.setArcHeight(16);
        stack.setClip(clip);

        stack.layoutBoundsProperty().addListener((obs, oldVal, newVal) -> {
            clip.setWidth(newVal.getWidth());
            clip.setHeight(newVal.getHeight());
        });

        ImageView iv = new ImageView();
        try {
            Image img = new Image(imageUrl, 300, 180, false, true);
            iv.setImage(img);
        } catch (Exception e) {
            System.err.println("Failed to load image: " + imageUrl);
        }
        iv.setFitHeight(180);
        iv.setFitWidth(300); // Allow stretch
        iv.setPreserveRatio(false);
        // Bind width for responsiveness
        iv.fitWidthProperty().bind(stack.widthProperty());

        // Overlay Gradient
        StackPane overlay = new StackPane();
        overlay.setStyle(
                "-fx-background-color: linear-gradient(to top, rgba(16, 22, 34, 0.9) 0%, rgba(16, 22, 34, 0.2) 50%, transparent 100%);");

        VBox text = new VBox(2);
        text.setAlignment(Pos.BOTTOM_LEFT);
        text.setPadding(new Insets(12));
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label st = new Label(subtitle);
        st.setTextFill(Color.web("#cbd5e1"));
        st.setFont(Font.font(10));
        text.getChildren().addAll(t, st);

        stack.getChildren().addAll(iv, overlay, text);
        StackPane.setAlignment(text, Pos.BOTTOM_LEFT);

        return stack;
    }

    private VBox createProgressRow(String label, String score, double progress, String colorHex) {
        VBox row = new VBox(5);

        HBox top = new HBox();
        top.setAlignment(Pos.CENTER_LEFT);
        Label l = new Label(label);
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font(12));
        Region r = new Region();
        HBox.setHgrow(r, Priority.ALWAYS);
        Label s = new Label(score);
        s.setTextFill(colorHex.equals(PRIMARY) ? Color.web(PRIMARY) : Color.WHITE);
        s.setFont(Font.font("System", FontWeight.BOLD, 12));
        top.getChildren().addAll(l, r, s);

        StackPane track = new StackPane();
        track.setPrefHeight(8);
        track.setStyle("-fx-background-color: #334155; -fx-background-radius: 4;");

        Region bar = new Region();
        bar.setPrefHeight(8);
        bar.setStyle("-fx-background-color: " + colorHex + "; -fx-background-radius: 4;");
        bar.maxWidthProperty().bind(track.widthProperty().multiply(progress));
        StackPane.setAlignment(bar, Pos.CENTER_LEFT);

        track.getChildren().add(bar);
        row.getChildren().addAll(top, track);
        return row;
    }

    private VBox createComparisonRow(String title, String val1, String sub1, String val2, String sub2,
            boolean highlightRight) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                + DIVIDER_COLOR + "; -fx-border-radius: 12;");

        Label t = new Label(title.toUpperCase());
        t.setTextFill(Color.web(TEXT_GRAY));
        t.setFont(Font.font("System", FontWeight.BOLD, 10));
        t.setAlignment(Pos.CENTER);
        t.setMaxWidth(Double.MAX_VALUE);

        HBox grid = new HBox(0);

        VBox left = new VBox(2);
        left.setAlignment(Pos.CENTER);
        Label v1 = new Label(val1);
        v1.setTextFill(!highlightRight && val1.contains("km") ? Color.web(PRIMARY) : Color.WHITE); // Logic specific to
                                                                                                   // visual
        v1.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label s1 = new Label(sub1);
        s1.setTextFill(Color.web(TEXT_GRAY));
        s1.setFont(Font.font(10));
        left.getChildren().addAll(v1, s1);
        HBox.setHgrow(left, Priority.ALWAYS);

        VBox right = new VBox(2);
        right.setAlignment(Pos.CENTER);
        right.setStyle("-fx-border-color: " + DIVIDER_COLOR + "; -fx-border-width: 0 0 0 1;");
        Label v2 = new Label(val2);
        v2.setTextFill(highlightRight ? Color.web(PRIMARY) : Color.WHITE);
        v2.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label s2 = new Label(sub2);
        s2.setTextFill(Color.web(TEXT_GRAY));
        s2.setFont(Font.font(10));
        right.getChildren().addAll(v2, s2);
        HBox.setHgrow(right, Priority.ALWAYS);

        grid.getChildren().addAll(left, right);
        card.getChildren().addAll(t, grid);
        return card;
    }
}
