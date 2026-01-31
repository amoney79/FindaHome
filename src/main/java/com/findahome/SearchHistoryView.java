package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SearchHistoryView extends StackPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String PRIMARY = "#13ec5b"; // Green theme
    private static final String TEXT_GRAY = "#9da6b9";
    private static final String CARD_BG_DARK = "#1c222c";
    private static final String DIVIDER_COLOR_DARK = "#2a3544";

    public SearchHistoryView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(0, 0, 70, 0)); // Padding for bottom nav

        // Header
        VBox header = new VBox();
        header.setStyle(
                "-fx-background-color: rgba(16, 22, 34, 0.95); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 0, 5, 0, 0); -fx-border-color: "
                        + DIVIDER_COLOR_DARK + "; -fx-border-width: 0 0 1 0;");
        header.setPadding(new Insets(15, 20, 15, 20));

        HBox navBar = new HBox(15);
        navBar.setAlignment(Pos.CENTER_LEFT);

        Label backBtn = new Label("\u2039"); // Arrow back
        backBtn.setFont(Font.font("System", FontWeight.BOLD, 24));
        backBtn.setTextFill(Color.WHITE);
        backBtn.setCursor(javafx.scene.Cursor.HAND);
        backBtn.setOnMouseClicked(e -> MainApp.showHome());

        Label title = new Label("Search History");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        HBox.setHgrow(title, Priority.ALWAYS);

        Button searchIcon = new Button("\ud83d\udd0d");
        searchIcon.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18; -fx-cursor: hand;");

        navBar.getChildren().addAll(backBtn, title, searchIcon);
        header.getChildren().add(navBar);
        mainLayout.setTop(header);

        // Scrollable Content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox content = new VBox(24);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // 1. Recent Areas
        VBox recentSec = new VBox(10);
        Label recTitle = new Label("Recent Areas");
        recTitle.setTextFill(Color.WHITE);
        recTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox recList = new VBox(8);
        recList.getChildren().addAll(
                createHistoryItem("Kileleshwa", "Westlands, Nairobi", "\ud83d\uddfa"),
                createHistoryItem("Nyali", "Mombasa City, Mombasa", "\ud83d\udccd"),
                createHistoryItem("Milimani", "Kisumu Central, Kisumu", "\ud83e\udded"));
        recentSec.getChildren().addAll(recTitle, recList);

        // 2. Clear History Button
        HBox clearBox = new HBox();
        clearBox.setAlignment(Pos.CENTER);
        Button clearBtn = new Button("Clear All History");
        clearBtn.setGraphic(new Label("\ud83d\uddd1"));
        clearBtn.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: #ef4444; -fx-font-weight: bold; -fx-cursor: hand; -fx-font-size: 12;");
        clearBox.getChildren().add(clearBtn);

        // 3. Suggested Areas
        VBox suggSec = new VBox(15);
        VBox sHeader = new VBox(2);
        Label sTitle = new Label("Suggested for You");
        sTitle.setTextFill(Color.WHITE);
        sTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label sSub = new Label("Trending wards based on your history");
        sSub.setTextFill(Color.web(TEXT_GRAY));
        sSub.setFont(Font.font(12));
        sHeader.getChildren().addAll(sTitle, sSub);

        FlowPane grid = new FlowPane(12, 12);
        grid.setPrefWrapLength(300); // Trigger wrap
        grid.getChildren().addAll(
                createSuggestionCard("Bamburi", "Mombasa",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuAr1mXgDvVOzdi_5MstgtM21c3EeJGwBerleE6ehAnHIt0XycHEbSXofHSUs0J6rkmtdEALi-toPLIwOFIoRMpdQe1pibkgtrGdoNT1Dk-bGWJKVg8TM9PUK2pPJeaInPiOTjmG2XeNAPxg2wkbLssHZLWG_or0dF6VTpGzxaNkwPPL6d81XXmnNztNmGa7gs9JwOV60QVrOkfznYwPLqpaB7fBkkO_m-cJbz0BKg7mKqkLSslqq4dTWYrmktFreU1NWvlJB6CSrqo"),
                createSuggestionCard("Syokimau", "Machakos",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuC4VgViINYV5T9yfiDgBHcqJlsg_aQ-hdynrA9_GDE9TEu5YcTt3R7RRtPsSMjMHd5wJ7T7lArQO7fba6494Pag6O4D9aiN7ohmTPBXGAZoDjn51BYBe0ZRQTD4J3AXFh2_BMp92Xexwji0sPOIqimeJFWbkX9qup-fYD-XrSfM8wqU7EME2oMmtfRz2lUwWuTeh2kLHYDjPpvQ7QJGKGNuh6jBPJGOBbWr8f0LXHJBkqisAjxsOiI02DEYAM-FP8wCQYR3TIE_M4A"),
                createSuggestionCard("Ruaka", "Kiambu",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuC4pocpShw3HHBbfx7E3-yqy4XFrUR9ai3VIsBufSf6u7l7laxkYR0nUwb0946TiD_Wtgk-R8oO-KV59NusLiudmiO0S4iUum8bku5C1A9qJDjWADX6vhWbZ1FDEPKJ2jknHZP0wIRYMmBBsFs4D_aHC5AZzrKQXbkYUKwOH9HroGYR9eXGu0g9TxpEKHKuw3IyHNmcFoJ3vl7D7sRGCi5UPNxCjxNDis8joRIEM0OAS-VNzw7Yt7v8rfkRe82jCahuPXo9cfQtPu0"),
                createSuggestionCard("Runda", "Nairobi",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuBrmHY-INBb8Jxch2ewFEgmHWMJUA4ZNP52p_ZsSfCVLmW2IY3sMgtYaxGK51bVzD-oK_xBP-8CfgZql0a9fCUk8eB8u5R-EDubCMiz_E8EZNVe6TMKpupMx5q2s3LKJMpLlIiF-Yss_fHljPB3oDm09IT7kI8z3dO46Bq_cOJYSe9uNYVCCGk0DI6A14iU8Xj-VNaDaW2zuBzm7GlwPxMaydM4iu4KWxPvR8qm97PD9C7PdbIqQJlHvq1mCcUJxRkLwb22sqL3BcM"));

        suggSec.getChildren().addAll(sHeader, grid);

        content.getChildren().addAll(recentSec, clearBox, suggSec);
        scrollPane.setContent(content);
        mainLayout.setCenter(scrollPane);

        getChildren().add(mainLayout);

        // Bottom Nav (Visual Only for context)
        HBox nav = new HBox();
        nav.setAlignment(Pos.CENTER);
        nav.setPadding(new Insets(10));
        nav.setSpacing(40);
        nav.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + DIVIDER_COLOR_DARK
                + "; -fx-border-width: 1 0 0 0;");
        nav.getChildren().addAll(
                createNavItem("\u2302", "Home", false),
                createNavItem("\ud83d\udd52", "History", true),
                createNavItem("\u2665", "Saved", false),
                createNavItem("\ud83d\udc64", "Profile", false));
        StackPane.setAlignment(nav, Pos.BOTTOM_CENTER);
        getChildren().add(nav);
    }

    private HBox createHistoryItem(String title, String subtitle, String iconStr) {
        HBox item = new HBox(12);
        item.setAlignment(Pos.CENTER_LEFT);
        item.setPadding(new Insets(12));
        item.setStyle(
                "-fx-background-color: transparent; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(48, 48);
        iconBox.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 12;");
        Label icon = new Label(iconStr);
        icon.setTextFill(Color.web(PRIMARY));
        icon.setFont(Font.font(20));
        iconBox.getChildren().add(icon);

        VBox text = new VBox(2);
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label s = new Label(subtitle);
        s.setTextFill(Color.web(TEXT_GRAY));
        s.setFont(Font.font(12));
        text.getChildren().addAll(t, s);
        HBox.setHgrow(text, Priority.ALWAYS);

        Button action = new Button("Search Again");
        action.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11; -fx-background-radius: 8; -fx-cursor: hand;");

        item.getChildren().addAll(iconBox, text, action);
        return item;
    }

    private VBox createSuggestionCard(String location, String county, String imageUrl) {
        VBox card = new VBox(10);
        card.setPrefWidth(160); // Roughly half width
        card.setPadding(new Insets(12));
        card.setStyle("-fx-background-color: " + CARD_BG_DARK
                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 12;");
        card.setAlignment(Pos.CENTER);

        StackPane media = new StackPane();
        media.setPrefHeight(96);
        media.setStyle("-fx-background-color: #333; -fx-background-radius: 8;");

        try {
            ImageView img = new ImageView(new Image(imageUrl, 160, 96, false, true));
            Rectangle clip = new Rectangle(136, 96); // Adjust for padding
            clip.setArcWidth(8);
            clip.setArcHeight(8);
            img.setClip(clip);
            media.getChildren().add(img);
        } catch (Exception e) {
        }

        VBox info = new VBox(2);
        info.setAlignment(Pos.CENTER);
        Label t = new Label(location);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label s = new Label(county);
        s.setTextFill(Color.web(TEXT_GRAY));
        s.setFont(Font.font(11));
        info.getChildren().addAll(t, s);

        card.getChildren().addAll(media, info);
        return card;
    }

    private VBox createNavItem(String icon, String text, boolean active) {
        VBox item = new VBox(2);
        item.setAlignment(Pos.CENTER);
        Label ic = new Label(icon);
        ic.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        ic.setFont(Font.font(20));
        Label tx = new Label(text);
        tx.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        tx.setFont(Font.font("System", FontWeight.BOLD, 10));
        item.getChildren().addAll(ic, tx);
        return item;
    }
}
