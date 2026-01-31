package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GuideView extends StackPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String PRIMARY = "#13ec5b"; // Green theme
    private static final String TEXT_GRAY = "#9da6b9";
    private static final String CARD_BG = "#1c222c";
    private static final String DIVIDER_COLOR = "#2a3544";

    public GuideView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        BorderPane mainLayout = new BorderPane();

        // Header (Sticky-like)
        VBox header = new VBox();
        header.setStyle(
                "-fx-background-color: rgba(16, 22, 34, 0.9); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 0, 5, 0, 0);");
        header.setPadding(new Insets(15, 20, 15, 20));

        HBox navBar = new HBox(15);
        navBar.setAlignment(Pos.CENTER_LEFT);

        Label backBtn = new Label("\u2039"); // Arrow back
        backBtn.setFont(Font.font("System", FontWeight.BOLD, 24));
        backBtn.setTextFill(Color.WHITE);
        backBtn.setCursor(javafx.scene.Cursor.HAND);
        backBtn.setOnMouseClicked(e -> MainApp.showHome());

        Label title = new Label("Kileleshwa Insight");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        HBox.setHgrow(title, Priority.ALWAYS);

        Label shareBtn = new Label("\u21E8"); // Share arrow (simulated)
        shareBtn.setFont(Font.font("System", FontWeight.BOLD, 18));
        shareBtn.setTextFill(Color.WHITE);

        navBar.getChildren().addAll(backBtn, title, shareBtn);
        header.getChildren().add(navBar);
        mainLayout.setTop(header);

        // Scroll Content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        VBox content = new VBox(20);
        content.setPadding(new Insets(0, 0, 100, 0)); // Bottom padding for footer
        content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Hero Section
        StackPane hero = new StackPane();
        hero.setPrefHeight(320);
        try {
            ImageView heroImg = new ImageView(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuB0HULlfInpu8RXSl3WrY0lOui3drjyipY7A0xgLsHPqDHZ-qj6PeDIRifJlSdQUCr85V0S-Ip1N7HZwByBqb5SSzFgCWXlr_yKJ9cIUm-4SbLkDOzGdmwlRIV9pCuEitLj8Tp8857z-CZDpcrEfx9dx2Y1ovb6Vzwy6FDdXtL1vfYyDdY2tVGXPd8KN4wssNshZpwINYIw9tf0b1xQdW2ZJv33wClmp_dzf39qrKZiFxu9HWpuU0PNoxH1oLknN5EqAFJKCKXR_mI",
                    500, 320, false, true));
            Rectangle clip = new Rectangle(500, 320); // Basic clip
            heroImg.setClip(clip);
            hero.getChildren().add(heroImg);
        } catch (Exception e) {
        }

        // Gradient Overlay
        Rectangle gradient = new Rectangle();
        gradient.widthProperty().bind(hero.widthProperty());
        gradient.heightProperty().bind(hero.heightProperty());
        gradient.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(0, 0, 0, 0.1)),
                new Stop(1, Color.rgb(16, 22, 34, 1.0)))); // Blend to background
        hero.getChildren().add(gradient);

        VBox heroText = new VBox(5);
        heroText.setAlignment(Pos.BOTTOM_LEFT);
        heroText.setPadding(new Insets(24));
        Label ht1 = new Label("NEIGHBORHOOD GUIDE");
        ht1.setTextFill(Color.web("rgba(255,255,255,0.8)"));
        ht1.setFont(Font.font("System", FontWeight.BOLD, 12));
        Label ht2 = new Label("Kileleshwa, Nairobi");
        ht2.setTextFill(Color.WHITE);
        ht2.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 32));
        ht2.setWrapText(true);
        heroText.getChildren().addAll(ht1, ht2);
        hero.getChildren().add(heroText);

        content.getChildren().add(hero);

        // Community Scores
        VBox scoresSec = new VBox(15);
        scoresSec.setPadding(new Insets(0, 20, 0, 20));
        scoresSec.getChildren().add(createSectionTitle("\ud83d\udcca", "Community Scores")); // Analytics icon

        HBox scoreCards = new HBox(15);
        scoreCards.getChildren().addAll(
                createScoreCard("Safety Score", "8.5", "+5% vs last month", "#0bda5e", "\ud83d\udee1"),
                createScoreCard("Quietness Score", "7.2", "-2% vs last month", "#fa6238", "\ud83d\udd07"));
        scoresSec.getChildren().add(scoreCards);
        content.getChildren().add(scoresSec);

        // Amenities Sections
        content.getChildren().add(createAmenitySection("\ud83c\udf93", "Education",
                createAmenityItem("Kileleshwa Primary School", "Public Institution", "0.4 KM", "\ud83d\udcd6"),
                createAmenityItem("Lavington School", "International Curriculum", "1.2 KM", "\ud83d\udcdc")));

        content.getChildren().add(createAmenitySection("\ud83c\udfe5", "Healthcare",
                createAmenityItem("Kileleshwa Medical Plaza", "24/7 Outpatient", "0.2 KM", "\ud83c\udfe5"),
                createAmenityItem("Nairobi Hospital Outpatient", "Lavington Branch", "1.5 KM", "\ud83d\ude91")));

        content.getChildren().add(createAmenitySection("\ud83d\ude8c", "Transport",
                createAmenityItem("Ring Road Kileleshwa", "Major Artery", "0.1 KM", "\u26f5"),
                createAmenityItem("Kandara Road Stage", "Route 48 Matatu Stage", "0.3 KM", "\ud83d\ude8f")));

        content.getChildren().add(createAmenitySection("\ud83d\udc6e", "Security",
                createAmenityItem("Kileleshwa Police Station", "24hr Surveillance", "0.6 KM", "\ud83d\udc6e")));

        scrollPane.setContent(content);
        mainLayout.setCenter(scrollPane);

        getChildren().add(mainLayout);

        // Sticky Footer
        VBox footer = new VBox();
        footer.setPadding(new Insets(16));
        footer.setStyle("-fx-background-color: rgba(16, 22, 34, 0.9); -fx-border-color: " + DIVIDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");

        Button browseBtn = new Button("Browse Listings in Kileleshwa  \u2794");
        browseBtn.setMaxWidth(Double.MAX_VALUE);
        browseBtn.setPrefHeight(56);
        browseBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12;");
        browseBtn.setOnAction(e -> MainApp.navigateTo(new AmenitiesMapView())); // Connect to map view

        footer.getChildren().add(browseBtn);

        getChildren().add(footer);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
    }

    private HBox createSectionTitle(String icon, String text) {
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER_LEFT);
        Label ic = new Label(icon);
        ic.setTextFill(Color.web(PRIMARY));
        ic.setFont(Font.font(20));
        Label tx = new Label(text);
        tx.setTextFill(Color.WHITE);
        tx.setFont(Font.font("System", FontWeight.BOLD, 20));
        box.getChildren().addAll(ic, tx);
        return box;
    }

    private VBox createScoreCard(String title, String score, String trend, String colorHex, String iconStr) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                + DIVIDER_COLOR + "; -fx-border-radius: 12;");
        HBox.setHgrow(card, Priority.ALWAYS);

        HBox head = new HBox();
        head.setAlignment(Pos.CENTER_LEFT);
        Label t = new Label(title);
        t.setTextFill(Color.web(TEXT_GRAY));
        t.setFont(Font.font(14));
        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);
        Label ic = new Label(iconStr);
        ic.setTextFill(Color.web(colorHex));
        head.getChildren().addAll(t, sp, ic);

        HBox scoreBox = new HBox(2);
        Label sc = new Label(score);
        sc.setTextFill(Color.WHITE);
        sc.setFont(Font.font("System", FontWeight.BOLD, 32));
        Label max = new Label("/10");
        max.setTextFill(Color.web(TEXT_GRAY));
        max.setFont(Font.font(14));
        max.setPadding(new Insets(12, 0, 0, 0));
        scoreBox.getChildren().addAll(sc, max);

        Label tr = new Label(trend);
        tr.setTextFill(Color.web(colorHex));
        tr.setFont(Font.font("System", FontWeight.BOLD, 12));

        card.getChildren().addAll(head, scoreBox, tr);
        return card;
    }

    private VBox createAmenitySection(String icon, String title, HBox... items) {
        VBox section = new VBox(15);
        section.setPadding(new Insets(0, 20, 0, 20));
        section.getChildren().add(createSectionTitle(icon, title));

        VBox list = new VBox(10);
        list.getChildren().addAll(items);
        section.getChildren().add(list);
        return section;
    }

    private HBox createAmenityItem(String name, String sub, String dist, String iconStr) {
        HBox item = new HBox(12);
        item.setPadding(new Insets(16));
        item.setAlignment(Pos.CENTER_LEFT);
        item.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                + DIVIDER_COLOR + "; -fx-border-radius: 12;");

        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(40, 40);
        iconBox.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 10;");
        Label ic = new Label(iconStr);
        ic.setTextFill(Color.web(PRIMARY));
        iconBox.getChildren().add(ic);

        VBox text = new VBox(2);
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label s = new Label(sub);
        s.setTextFill(Color.web(TEXT_GRAY));
        s.setFont(Font.font(12));
        text.getChildren().addAll(n, s);
        HBox.setHgrow(text, Priority.ALWAYS);

        Label dst = new Label(dist);
        dst.setTextFill(Color.web(TEXT_GRAY));
        dst.setFont(Font.font("System", FontWeight.BOLD, 10));
        dst.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-padding: 4 8; -fx-background-radius: 4;");

        item.getChildren().addAll(iconBox, text, dst);
        return item;
    }
}
