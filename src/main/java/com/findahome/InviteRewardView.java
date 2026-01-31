package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InviteRewardView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
    private static final String TEXT_GRAY = "#9db9a6";

    public InviteRewardView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Top App Bar
        HBox topNav = new HBox();
        topNav.setAlignment(Pos.CENTER_LEFT);
        topNav.setPadding(new Insets(15, 20, 15, 20));
        topNav.setStyle("-fx-background-color: " + BACKGROUND_DARK + " ; -fx-border-color: " + BORDER_COLOR
                + " ; -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new TenantProfileView()));

        Label title = new Label("Invite & Earn");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Region spacer = new Region();
        spacer.setPrefWidth(28);

        topNav.getChildren().addAll(backBtn, title, spacer);

        // Scrollable Content
        VBox scrollContent = new VBox(0);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Header Banner
        VBox bannerBox = new VBox();
        bannerBox.setPadding(new Insets(20));

        StackPane banner = new StackPane();
        banner.setPrefHeight(250);
        banner.setStyle("-fx-background-radius: 20; -fx-border-color: " + PRIMARY
                + "33; -fx-border-width: 1; -fx-border-radius: 20; -fx-overflow: hidden;");

        ImageView bannerImg = new ImageView();
        try {
            bannerImg.setImage(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuDj8ROY3PbqwGfjtElprnd0Uhr8i_rBy8lz74AjiyHU3lQ-PN6z1-odYNrNG_WVrTSZcc2qC0icLGTShZVe_61k4pisoM0YOrOtiEygLXFSFhFQVxrBxsk8UXRl0Z2Ihwxsen3X6Zvhe19joXdZlToKsMjxplKQ22aj5pw4CKqZO1zOHg-kWh4HHPXSwicN6Hkl6oHvUJLgBqnV7kLGcqGogcy636aUUJLtFQdgaMmf-shpLd9_99Oy7SmJxvcBe93wyZaK-lWKRXM",
                    400, 250, false, true));
        } catch (Exception e) {
        }
        bannerImg.setFitWidth(390);
        bannerImg.setFitHeight(250);
        bannerImg.setPreserveRatio(false);
        javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(390, 250);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        bannerImg.setClip(clip);

        Pane gradient = new Pane();
        gradient.setStyle(
                "-fx-background-color: linear-gradient(to top, rgba(0,0,0,0.6), transparent); -fx-background-radius: 20;");

        banner.getChildren().addAll(bannerImg, gradient);
        bannerBox.getChildren().add(banner);

        // Headline
        VBox heroText = new VBox(10);
        heroText.setAlignment(Pos.CENTER);
        heroText.setPadding(new Insets(10, 30, 30, 30));

        Label headline = new Label("Get $50 for Every Friend!");
        headline.setTextFill(Color.WHITE);
        headline.setFont(Font.font("System", FontWeight.BLACK, 30));
        headline.setWrapText(true);
        headline.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Label subline = new Label("Help your friends find their dream home and get rewarded when they move in.");
        subline.setTextFill(Color.web(TEXT_GRAY));
        subline.setFont(Font.font(15));
        subline.setWrapText(true);
        subline.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        heroText.getChildren().addAll(headline, subline);

        // Referral Code Section
        VBox referralSec = new VBox(12);
        referralSec.setPadding(new Insets(0, 25, 30, 25));

        Label refLabel = new Label("Your Referral Code");
        refLabel.setTextFill(Color.WHITE);
        refLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        HBox codeBox = new HBox(0);
        codeBox.setAlignment(Pos.CENTER_LEFT);
        codeBox.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-background-radius: 12; -fx-border-color: "
                + PRIMARY + "44; -fx-border-width: 1; -fx-border-radius: 12;");

        TextField codeInput = new TextField("FINDHOME2024");
        codeInput.setEditable(false);
        codeInput.setPrefHeight(56);
        HBox.setHgrow(codeInput, Priority.ALWAYS);
        codeInput.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20; -fx-alignment: center; -fx-padding: 0 20;");

        Button copyBtn = new Button("\u2398"); // Symbol for copy
        copyBtn.setPrefHeight(56);
        copyBtn.setMinWidth(60);
        copyBtn.setStyle("-fx-background-color: " + PRIMARY + "22; -fx-text-fill: " + PRIMARY
                + " ; -fx-font-size: 20; -fx-background-radius: 0 11 11 0; -fx-cursor: hand; -fx-border-color: "
                + PRIMARY + "44; -fx-border-width: 0 0 0 1;");

        codeBox.getChildren().addAll(codeInput, copyBtn);
        referralSec.getChildren().addAll(refLabel, codeBox);

        // Share Button
        VBox shareBox = new VBox();
        shareBox.setPadding(new Insets(0, 25, 40, 25));
        Button shareBtn = new Button("\u27a6  Share Link");
        shareBtn.setMaxWidth(Double.MAX_VALUE);
        shareBtn.setPrefHeight(56);
        shareBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: #102216; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
        shareBox.getChildren().add(shareBtn);

        // How it Works
        VBox stepsSec = new VBox(25);
        stepsSec.setPadding(new Insets(0, 25, 60, 25));
        Label stepsTitle = new Label("How it works");
        stepsTitle.setTextFill(Color.WHITE);
        stepsTitle.setFont(Font.font("System", FontWeight.BOLD, 22));

        VBox timeline = new VBox(35);
        timeline.setPadding(new Insets(10, 0, 0, 0));

        // Connector line logic using specialized StackPane positioning
        AnchorPane timelineContainer = new AnchorPane();
        Line vertLine = new Line(0, 0, 0, 220);
        vertLine.setStroke(Color.web("#2a3e2f"));
        vertLine.setStrokeWidth(2);
        AnchorPane.setLeftAnchor(vertLine, 21.0);
        AnchorPane.setTopAnchor(vertLine, 20.0);

        VBox stepList = new VBox(40);
        stepList.getChildren().addAll(
                createStep(1, "Invite Friends", "Share your unique referral link or code with your network.", true),
                createStep(2, "They Sign a Lease",
                        "Your friend finds a home and completes their lease through FindaHome.", false),
                createStep(3, "You Both Get Rewarded", "You each receive a $50 bonus directly to your digital wallet.",
                        false));

        timelineContainer.getChildren().addAll(vertLine, stepList);
        stepsSec.getChildren().addAll(stepsTitle, timelineContainer);

        // Footer
        VBox footer = new VBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(0, 0, 60, 0));
        Label historyLink = new Label("View my referral history \u203a");
        historyLink.setTextFill(Color.web(PRIMARY));
        historyLink.setFont(Font.font("System", FontWeight.BOLD, 14));
        historyLink.setCursor(javafx.scene.Cursor.HAND);
        footer.getChildren().add(historyLink);

        scrollContent.getChildren().addAll(bannerBox, heroText, referralSec, shareBox, stepsSec, footer);

        layout.getChildren().addAll(topNav, scroll);
        getChildren().add(layout);
    }

    private HBox createStep(int index, String title, String desc, boolean active) {
        HBox row = new HBox(20);
        row.setAlignment(Pos.TOP_LEFT);

        StackPane numBox = new StackPane();
        numBox.setPrefSize(42, 42);
        numBox.setMinSize(42, 42);
        numBox.setStyle("-fx-background-color: " + (active ? PRIMARY : BACKGROUND_DARK)
                + " ; -fx-background-radius: 21; -fx-border-color: " + PRIMARY
                + " ; -fx-border-width: 2; -fx-border-radius: 21;");

        Label num = new Label(String.valueOf(index));
        num.setTextFill(active ? Color.web("#102216") : Color.web(PRIMARY));
        num.setFont(Font.font("System", FontWeight.BOLD, 16));
        numBox.getChildren().add(num);

        VBox text = new VBox(6);
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label d = new Label(desc);
        d.setTextFill(Color.web(TEXT_GRAY));
        d.setFont(Font.font(14));
        d.setWrapText(true);
        d.setPrefWidth(300);

        text.getChildren().addAll(t, d);
        row.getChildren().addAll(numBox, text);
        return row;
    }
}
