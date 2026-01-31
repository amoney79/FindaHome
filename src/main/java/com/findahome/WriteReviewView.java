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

public class WriteReviewView extends VBox {

    private static final String BACKGROUND_DARK = "#101922";
    private static final String PRIMARY = "#137fec";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

    public WriteReviewView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // AppBar
        HBox appBar = new HBox(15);
        appBar.setAlignment(Pos.CENTER_LEFT);
        appBar.setPadding(new Insets(15, 20, 15, 20));
        appBar.setStyle("-fx-background-color: rgba(16, 25, 34, 0.8); -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 0 0 1 0;");

        Label closeBtn = new Label("\u2715");
        closeBtn.setTextFill(Color.web(PRIMARY));
        closeBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");
        closeBtn.setOnMouseClicked(e -> MainApp.navigateTo(new PropertyReviewsView()));

        Label title = new Label("Write a Review");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Label draftsBtn = new Label("Drafts");
        draftsBtn.setTextFill(Color.web(PRIMARY));
        draftsBtn.setFont(Font.font("System", FontWeight.SEMI_BOLD, 14));
        draftsBtn.setCursor(javafx.scene.Cursor.HAND);

        appBar.getChildren().addAll(closeBtn, title, draftsBtn);

        // Scroll Content
        VBox scrollContent = new VBox(25);
        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Headline
        VBox headline = new VBox(5);
        headline.setAlignment(Pos.CENTER);
        headline.setPadding(new Insets(30, 40, 0, 40));
        Label h1 = new Label("How was your stay?");
        h1.setTextFill(Color.WHITE);
        h1.setFont(Font.font("System", FontWeight.BOLD, 24));
        Label h2 = new Label("Your feedback helps millions of house hunters find their next home.");
        h2.setTextFill(Color.web("#ffffff", 0.6));
        h2.setFont(Font.font(14));
        h2.setWrapText(true);
        h2.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        headline.getChildren().addAll(h1, h2);

        // Overall Rating
        VBox overallRating = new VBox(15);
        overallRating.setPadding(new Insets(0, 20, 0, 20));
        VBox rateBox = new VBox(15);
        rateBox.setAlignment(Pos.CENTER);
        rateBox.setPadding(new Insets(25));
        rateBox.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 16; -fx-border-color: "
                + BORDER_COLOR + ";");

        HBox stars = createStarRating(4, 32);
        Label rateStatus = new Label("Great (4.0/5.0)");
        rateStatus.setTextFill(Color.WHITE);
        rateStatus.setFont(Font.font("System", FontWeight.MEDIUM, 16));
        rateBox.getChildren().addAll(stars, rateStatus);
        overallRating.getChildren().add(rateBox);

        // Category Ratings
        VBox categories = new VBox(10);
        categories.setPadding(new Insets(0, 20, 0, 20));
        Label catTitle = new Label("Category Ratings");
        catTitle.setTextFill(Color.WHITE);
        catTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox catList = new VBox(5);
        catList.getChildren().addAll(
                createCategoryRow("Safety", "\ud83d\udee1\ufe0f", 5),
                createCategoryRow("Amenities", "\ud83c\udfca", 3),
                createCategoryRow("Communication", "\ud83d\udcac", 4));
        categories.getChildren().addAll(catTitle, catList);

        // Review Details
        VBox details = new VBox(12);
        details.setPadding(new Insets(0, 20, 0, 20));
        Label detTitle = new Label("Review Details");
        detTitle.setTextFill(Color.WHITE);
        detTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        TextArea ta = new TextArea();
        ta.setPromptText("Share your experience living here... What did you like or dislike?");
        ta.setPrefHeight(120);
        ta.setWrapText(true);
        ta.setStyle(
                "-fx-control-inner-background: #1e293b; -fx-text-fill: white; -fx-prompt-text-fill: #64748b; -fx-background-radius: 12; -fx-border-color: "
                        + BORDER_COLOR + "; -fx-border-radius: 12; -fx-background-insets: 0;");

        Label charLimit = new Label("Minimum 50 characters");
        charLimit.setTextFill(Color.web("#ffffff", 0.4));
        charLimit.setFont(Font.font(12));
        HBox limitBox = new HBox(charLimit);
        limitBox.setAlignment(Pos.CENTER_RIGHT);
        details.getChildren().addAll(detTitle, ta, limitBox);

        // Photos
        VBox photos = new VBox(12);
        photos.setPadding(new Insets(0, 20, 0, 20));
        VBox photoHeader = new VBox(4);
        Label photoTitle = new Label("Add Photos");
        photoTitle.setTextFill(Color.WHITE);
        photoTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label photoSub = new Label("Help others see the real condition of the property.");
        photoSub.setTextFill(Color.web("#ffffff", 0.6));
        photoSub.setFont(Font.font(13));
        photoHeader.getChildren().addAll(photoTitle, photoSub);

        HBox photoRow = new HBox(12);
        photoRow.setAlignment(Pos.CENTER_LEFT);

        VBox addPhoto = new VBox(8);
        addPhoto.setAlignment(Pos.CENTER);
        addPhoto.setPrefSize(96, 96);
        addPhoto.setStyle(
                "-fx-background-color: rgba(19, 127, 236, 0.05); -fx-border-color: rgba(19, 127, 236, 0.4); -fx-border-style: dashed; -fx-border-width: 2; -fx-border-radius: 12; -fx-background-radius: 12; -fx-cursor: hand;");
        Label addIcon = new Label("\ud83d\udcf7");
        addIcon.setTextFill(Color.web(PRIMARY));
        addIcon.setStyle("-fx-font-size: 24;");
        Label addText = new Label("ADD PHOTO");
        addText.setTextFill(Color.web(PRIMARY));
        addText.setFont(Font.font("System", FontWeight.BOLD, 10));
        addPhoto.getChildren().addAll(addIcon, addText);

        photoRow.getChildren().addAll(addPhoto,
                createPhotoThumbnail(
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuAYr7k1127W_ZN3l3-0BICxKhpNEfikURGUg5byjKySpupHYIcnoeh7qdP_02cD1VB9O2S5cn5qIlcD2-KMlqVnFwS5mrueknU6zeQ_Kz83Ednq0tsSdiQgK7kO_52tBP6cdDOHgMmbcdddFgxvZ0ux3rap0QOtqxFors-PY1z1PaO7-OdxmC7OAZNiRb-xSoGuccHG1s39XY8s-JfqkS1wsYRR0iX7Fhss3yJvDvNa1vIdu1Mi_kzXGk471Zi1TvCIFei9R76fYZ8"),
                createPhotoThumbnail(
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDRKak90P4Je9yNo_NUla-yGgzLrZ_eqOQwJQ4MoMYpKOrzqOEE_qMOuL1gJgcQhyZLU7p6aUn_So0wfA1lpF5J2b0VdWx-BcZfLfvp90djWZhFDspzpGhp82hZWP-tC82bF-qcmF1u4DbWmpMapbHB_GsQ2cj2wOCuTiPZtggZ230CPjb4jmmd_o5-HEvNyFOUvlMPlqvlVg4alE5z58uds2lAQy3AmeRc58Vvbd3nIppJ-YeE-U6FpdbukHAoBSvbMUremCWnTmM"));
        ScrollPane photoScroll = new ScrollPane(photoRow);
        photoScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        photoScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        photoScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        photos.getChildren().addAll(photoHeader, photoScroll);

        // Anonymous Toggle
        HBox toggleSec = new HBox(15);
        toggleSec.setPadding(new Insets(20));
        toggleSec.setAlignment(Pos.CENTER_LEFT);
        toggleSec.setStyle("-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 12; -fx-border-color: "
                + BORDER_COLOR + "; -fx-border-radius: 12;");
        VBox.setMargin(toggleSec, new Insets(0, 20, 100, 20));

        VBox toggleTxt = new VBox(2);
        Label toggleTitle = new Label("Post Anonymously");
        toggleTitle.setTextFill(Color.WHITE);
        toggleTitle.setFont(Font.font("System", FontWeight.BOLD, 15));
        Label toggleSub = new Label("Your identity will be hidden from everyone.");
        toggleSub.setTextFill(Color.web("#ffffff", 0.5));
        toggleSub.setFont(Font.font(12));
        toggleTxt.getChildren().addAll(toggleTitle, toggleSub);

        Region s1 = new Region();
        HBox.setHgrow(s1, Priority.ALWAYS);
        ToggleButton switchBtn = new ToggleButton();
        switchBtn.setStyle(
                "-fx-background-color: #2e3a4e; -fx-background-radius: 15; -fx-min-width: 44; -fx-min-height: 24; -fx-padding: 0;");
        Circle knob = new Circle(10, Color.WHITE);
        switchBtn.setGraphic(knob);
        switchBtn.setAlignment(Pos.CENTER_LEFT);
        switchBtn.selectedProperty().addListener((obs, oldV, newV) -> {
            switchBtn.setStyle("-fx-background-color: " + (newV ? PRIMARY : "#2e3a4e")
                    + "; -fx-background-radius: 15; -fx-min-width: 44; -fx-min-height: 24; -fx-padding: 0;");
            switchBtn.setAlignment(newV ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        });

        toggleSec.getChildren().addAll(toggleTxt, s1, switchBtn);

        scrollContent.getChildren().addAll(headline, overallRating, categories, details, photos, toggleSec);

        // Footer
        HBox footer = new HBox();
        footer.setPadding(new Insets(15, 20, 30, 20));
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");

        Button submitBtn = new Button("Submit Review  \u27a4");
        submitBtn.setMaxWidth(Double.MAX_VALUE);
        submitBtn.setPrefHeight(56);
        submitBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
        submitBtn.setOnAction(e -> MainApp.navigateTo(new SuccessView())); // Placeholder navigation
        HBox.setHgrow(submitBtn, Priority.ALWAYS);
        footer.getChildren().add(submitBtn);

        StackPane root = new StackPane(scroll, appBar, footer);
        StackPane.setAlignment(appBar, Pos.TOP_CENTER);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);

        getChildren().add(root);
    }

    private HBox createStarRating(int filled, int size) {
        HBox h = new HBox(8);
        h.setAlignment(Pos.CENTER);
        for (int i = 0; i < 5; i++) {
            Label star = new Label("\u2b50");
            star.setStyle("-fx-font-size: " + size + ";");
            if (i >= filled)
                star.setOpacity(0.2);
            h.getChildren().add(star);
        }
        return h;
    }

    private HBox createCategoryRow(String name, String iconCode, int stars) {
        HBox row = new HBox(15);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10, 0, 10, 0));

        HBox meta = new HBox(12);
        meta.setAlignment(Pos.CENTER_LEFT);

        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(40, 40);
        iconBox.setStyle("-fx-background-color: rgba(19, 127, 236, 0.1); -fx-background-radius: 8;");
        Label icon = new Label(iconCode);
        icon.setTextFill(Color.web(PRIMARY));
        icon.setStyle("-fx-font-size: 18;");
        iconBox.getChildren().add(icon);

        Label lbl = new Label(name);
        lbl.setTextFill(Color.WHITE);
        lbl.setFont(Font.font("System", FontWeight.MEDIUM, 15));
        meta.getChildren().addAll(iconBox, lbl);

        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);
        HBox starBox = createStarRating(stars, 16);

        row.getChildren().addAll(meta, s, starBox);
        return row;
    }

    private StackPane createPhotoThumbnail(String url) {
        StackPane p = new StackPane();
        p.setPrefSize(96, 96);

        ImageView iv = new ImageView();
        try {
            iv.setImage(new Image(url, 96, 96, false, true));
        } catch (Exception e) {
        }
        iv.setFitWidth(96);
        iv.setFitHeight(96);
        Rectangle clip = new Rectangle(96, 96);
        clip.setArcWidth(24);
        clip.setArcHeight(24);
        iv.setClip(clip);

        Label close = new Label("\u2715");
        close.setTextFill(Color.WHITE);
        close.setStyle(
                "-fx-background-color: rgba(0,0,0,0.5); -fx-background-radius: 10; -fx-padding: 2 5; -fx-font-size: 10; -fx-cursor: hand;");
        StackPane.setAlignment(close, Pos.TOP_RIGHT);
        StackPane.setMargin(close, new Insets(5));

        p.getChildren().addAll(iv, close);
        return p;
    }
}
