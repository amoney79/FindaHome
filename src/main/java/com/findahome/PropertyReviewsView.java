package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PropertyReviewsView extends VBox {

    private static final String BACKGROUND_DARK = "#101922";
    private static final String CARD_BG = "#1c2433";
    private static final String PRIMARY = "#137fec";
    private static final String TEXT_GRAY = "#9da6b9";

    public PropertyReviewsView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Top AppBar
        HBox appBar = new HBox(15);
        appBar.setAlignment(Pos.CENTER_LEFT);
        appBar.setPadding(new Insets(15, 20, 15, 20));
        appBar.setStyle(
                "-fx-background-color: rgba(16, 25, 34, 0.8); -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.showHome()); // Navigate back

        Label title = new Label("Property Reviews");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Label shareBtn = new Label("\u27a6");
        shareBtn.setTextFill(Color.WHITE);
        shareBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");

        appBar.getChildren().addAll(backBtn, title, shareBtn);

        // Scroll Content
        VBox scrollContent = new VBox(0);
        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Overall Rating Summary
        VBox summarySec = new VBox(20);
        summarySec.setPadding(new Insets(20));

        HBox summaryFlex = new HBox(30);
        summaryFlex.setAlignment(Pos.CENTER_LEFT);

        VBox scoreBox = new VBox(5);
        Label scoreNum = new Label("4.5");
        scoreNum.setTextFill(Color.WHITE);
        scoreNum.setFont(Font.font("System", FontWeight.BLACK, 48));

        HBox stars = createStarRating(4);
        Label countLbl = new Label("128 reviews");
        countLbl.setTextFill(Color.web("#ffffff", 0.6));
        countLbl.setFont(Font.font(14));
        scoreBox.getChildren().addAll(scoreNum, stars, countLbl);

        VBox distGrid = new VBox(8);
        HBox.setHgrow(distGrid, Priority.ALWAYS);
        distGrid.getChildren().addAll(
                createRatingBar("5", 0.6),
                createRatingBar("4", 0.2),
                createRatingBar("3", 0.1));
        summaryFlex.getChildren().addAll(scoreBox, distGrid);
        summarySec.getChildren().add(summaryFlex);

        // Category Ratings
        VBox catSec = new VBox(15);
        catSec.setPadding(new Insets(20));
        Label catTitle = new Label("Category Ratings");
        catTitle.setTextFill(Color.WHITE);
        catTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        GridPane catGrid = new GridPane();
        catGrid.setHgap(12);
        catGrid.setVgap(12);
        catGrid.add(createCategoryCard("Cleanliness", "4.8", "\u2728"), 0, 0);
        catGrid.add(createCategoryCard("Location", "4.2", "\ud83d\udccd"), 1, 0);
        catGrid.add(createCategoryCard("Agent Service", "4.5", "\ud83d\udc64"), 0, 1);
        catGrid.add(createCategoryCard("Value", "4.0", "\ud83d\udcb5"), 1, 1);

        catSec.getChildren().addAll(catTitle, catGrid);

        // Filter Chips
        HBox chips = new HBox(10);
        chips.setPadding(new Insets(15, 20, 15, 20));
        chips.getChildren().addAll(
                createChip("Latest", true),
                createChip("Highest Rated", false),
                createChip("With Photos", false));
        ScrollPane chipScroll = new ScrollPane(chips);
        chipScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chipScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chipScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Reviews List
        VBox listSec = new VBox(25);
        listSec.setPadding(new Insets(20, 20, 100, 20));

        listSec.getChildren().addAll(
                createReviewCard("Marcus Thompson", "Verified", "2 days ago", 5,
                        "Absolutely loved staying here. The natural light in the living room is incredible. The agent was very responsive and helped with the move-in process seamlessly.",
                        true,
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDZTG9mPtN1JZP0SUIP9e7A8L9tlE3vPQsQwNB4auc6DpYTn4GglC45tAgnsRpykPR1Lhqtq-mjAHac-AOgfcAH-RayQpJva_Uq4z_JL-w6ErkAwDmv3R-HuDz0uuaVNA8pLa99VefeOz_xp5mrs6FJWbhH-oehevssrso7DJm28GNkA8dd8vnGRhtcvHHsJag2XRhB6l3ZyXfYJQpsZSgYZ0blQJ0sJo4RmZw3uWcfLfZl4Fc4Zo24-7DoPwfI2tTAdHlMyYsnFos"),
                createReviewCard("Sarah Jenkins", "Verified", "1 week ago", 4,
                        "Great location, everything is within walking distance. The apartment was spotless when I arrived. Only downside is the gym is a bit small, but functional.",
                        false,
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCXFXmHqH4A4PNJteP8yiCTWmwW9_YK1Y3Z0039e3v0PC8aq1Rg7b3e2ilLQXv6S2rJD2FdRc30DvqWPmyqamXzuh87QbrVvUNFyaeJKwCWoC9UW9wCEj6iH6im_T6bhobzy6oLaXnL8UAZ97M47ZQsKRsF9s7_AY99mAfmhtpA4k9Ce-BfJWLZJlR9QWrdCWQesxjQnBmKPIuyBDGesUx7mYFWH_AI5YN806bzqsnnUhjc_UXIndQ7tWUPo-h8zOkXRlp_i-4Lae4"),
                createReviewCard("James Wilson", "", "2 weeks ago", 3,
                        "Decent place for the price. Value for money is definitely the highlight here.", false,
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuA0AszApX1E7l-t9ZppwBzRcOp1oKjAP7gyD8XWN9LvwuatX7YlyTnySz_mLDDgbhA0vTNAFRpJgLzLMjWLI4CMiqacL5-lzZ3lqUv5BQtVr1wcTCMNdFCqeNmfuM9Wcn4g1eZ4AxZj9cOxkIETrkvRUy2uqJ4Fm0FfWORrLrazDfAeaWYshZtDA6MqYJ05vOktO0qMjmjSddoOIsxulHZ-oJIoS1xVbIsMLZmWsHajJFusb7laOOTdiW4jXvefCTmD-2Y93pbrPmI"));

        scrollContent.getChildren().addAll(summarySec, catSec, chipScroll, listSec);

        // FAB
        Button fab = new Button("+ Write a Review");
        fab.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-background-radius: 30; -fx-padding: 15 25; -fx-font-weight: bold; -fx-font-size: 15; -fx-cursor: hand;");
        fab.setOnAction(e -> MainApp.navigateTo(new WriteReviewView()));
        StackPane fabArea = new StackPane(fab);
        fabArea.setAlignment(Pos.BOTTOM_RIGHT);
        fabArea.setPadding(new Insets(0, 20, 30, 0));
        fabArea.setPickOnBounds(false);

        StackPane root = new StackPane(scroll, appBar, fabArea);
        StackPane.setAlignment(appBar, Pos.TOP_CENTER);

        getChildren().add(root);
    }

    private HBox createStarRating(int filled) {
        HBox h = new HBox(2);
        for (int i = 0; i < 5; i++) {
            Label star = new Label("\u2b50");
            star.setStyle("-fx-font-size: 14;");
            if (i >= filled)
                star.setOpacity(0.3);
            h.getChildren().add(star);
        }
        return h;
    }

    private HBox createRatingBar(String starNum, double percent) {
        HBox h = new HBox(10);
        h.setAlignment(Pos.CENTER_LEFT);
        Label num = new Label(starNum);
        num.setTextFill(Color.web("#ffffff", 0.8));
        num.setFont(Font.font(12));

        ProgressBar pb = new ProgressBar(percent);
        pb.setPrefWidth(120);
        pb.setPrefHeight(6);
        pb.setStyle("-fx-accent: " + PRIMARY
                + "; -fx-control-inner-background: rgba(255,255,255,0.1); -fx-background-radius: 10;");
        HBox.setHgrow(pb, Priority.ALWAYS);

        Label pct = new Label((int) (percent * 100) + "%");
        pct.setTextFill(Color.web("#ffffff", 0.5));
        pct.setFont(Font.font(12));
        h.getChildren().addAll(num, pb, pct);
        return h;
    }

    private VBox createCategoryCard(String title, String val, String icon) {
        VBox v = new VBox(8);
        v.setPadding(new Insets(15));
        v.setStyle(
                "-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1;");
        v.setPrefWidth(180);

        Label t = new Label(title.toUpperCase());
        t.setTextFill(Color.web("#ffffff", 0.6));
        t.setFont(Font.font("System", FontWeight.MEDIUM, 10));

        HBox b = new HBox(10);
        b.setAlignment(Pos.CENTER_LEFT);
        Label vl = new Label(val);
        vl.setTextFill(Color.WHITE);
        vl.setFont(Font.font("System", FontWeight.BOLD, 22));
        Label i = new Label(icon);
        i.setTextFill(Color.web(PRIMARY));
        i.setStyle("-fx-font-size: 20;");
        b.getChildren().addAll(vl, i);

        v.getChildren().addAll(t, b);
        return v;
    }

    private Button createChip(String text, boolean active) {
        Button b = new Button(text + (active ? " \u2304" : ""));
        b.setStyle("-fx-background-color: " + (active ? PRIMARY : "rgba(255,255,255,0.1)")
                + "; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding: 8 16; -fx-font-size: 13; -fx-font-weight: bold; -fx-border-color: rgba(255,255,255,0.05);");
        return b;
    }

    private VBox createReviewCard(String name, String tag, String time, int rating, String text, boolean hasPhotos,
            String avatarUrl) {
        VBox card = new VBox(12);
        card.setPadding(new Insets(0, 0, 20, 0));
        card.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        HBox top = new HBox(12);
        top.setAlignment(Pos.CENTER_LEFT);

        StackPane avatarFrame = new StackPane();
        ImageView iv = new ImageView();
        try {
            iv.setImage(new Image(avatarUrl, 40, 40, false, true));
        } catch (Exception e) {
        }
        iv.setFitWidth(40);
        iv.setFitHeight(40);
        Rectangle clip = new Rectangle(40, 40);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        iv.setClip(clip);
        avatarFrame.getChildren().add(iv);

        VBox meta = new VBox();
        HBox nameRow = new HBox(8);
        nameRow.setAlignment(Pos.CENTER_LEFT);
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font("System", FontWeight.BOLD, 14));

        if (!tag.isEmpty()) {
            Label tl = new Label(tag.toUpperCase());
            tl.setTextFill(Color.web(PRIMARY));
            tl.setStyle(
                    "-fx-background-color: rgba(19, 127, 236, 0.2); -fx-background-radius: 10; -fx-font-size: 8; -fx-font-weight: bold; -fx-padding: 2 8;");
            nameRow.getChildren().add(tl);
        }

        Label t = new Label(time);
        t.setTextFill(Color.web("#ffffff", 0.4));
        t.setFont(Font.font(12));
        meta.getChildren().addAll(nameRow, t);

        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);
        HBox stars = createStarRating(rating);

        top.getChildren().addAll(avatarFrame, meta, s, stars);

        Label content = new Label(text);
        content.setTextFill(Color.web("#ffffff", 0.8));
        content.setFont(Font.font(14));
        content.setWrapText(true);
        content.setLineSpacing(5);

        card.getChildren().addAll(top, content);

        if (hasPhotos) {
            HBox photos = new HBox(10);
            photos.setPadding(new Insets(5, 0, 0, 0));
            photos.getChildren().addAll(
                    createReviewPhoto(
                            "https://lh3.googleusercontent.com/aida-public/AB6AXuBfTi2r71xO1trsFryLvIwZJgrzRuid2lNFFtxd3f7crHHiR93JWnzubnwz5-wQWVGf3cVu2n9XUVkJombQ6Kp0yYw9UYt_vYUwfUhAYM9BXtH5xWxu2va4XJDsqpKVjPbR2p4TaoRufrIgSi1r13XMiEVn_5I02JLYrBxhlUYT4cAPi1SKXUt9ikArk5iLpQCN1JCI0tzMmEJ8MFL312vKgXDfb6aFCoDOrGH6ay1ecYPrQWSifI4k4uDRw0qrq_6o2wbcfyr3reQ"),
                    createReviewPhoto(
                            "https://lh3.googleusercontent.com/aida-public/AB6AXuB1tWzMC2h3xV7MC4tbvzsW_RtW7XuDfF6SwltqVdnc8EdbJdhflOf5gGLU3tGdZnRKApF8y2wbLJ1a5shhLyIgxbd6OIZOKwf0h0A79ysVvQUu9I-mMxfVg7Hty9NF4tI8-b30EKZmJvIUJMHUXyK6Hf1W_pHkH8sJV8METdBSfWkqMiI9mJD5Mss1QQqFmN3JxItIyvdE_54WN5P9YOkLrccyyGLtdgmNO9yQlHMarf8DV42NzTjTnK0jOW3V0QlItoQcIE3IsJg"));
            card.getChildren().add(photos);
        }

        return card;
    }

    private StackPane createReviewPhoto(String url) {
        StackPane p = new StackPane();
        ImageView iv = new ImageView();
        try {
            iv.setImage(new Image(url, 80, 80, false, true));
        } catch (Exception e) {
        }
        iv.setFitWidth(80);
        iv.setFitHeight(80);
        Rectangle clip = new Rectangle(80, 80);
        clip.setArcWidth(16);
        clip.setArcHeight(16);
        iv.setClip(clip);
        p.getChildren().add(iv);
        return p;
    }
}
