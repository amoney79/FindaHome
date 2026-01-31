package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LinkPayoutMethodView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String TEXT_GRAY = "#9db9a6";
    private static final String CARD_BG = "#111813";

    public LinkPayoutMethodView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new EarningsAnalyticsView()));

        Label title = new Label("Link Payout Method");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);
        HBox.setMargin(title, new Insets(0, 48, 0, 0)); // Center offset

        header.getChildren().addAll(backBtn, title);

        // Scroll Content
        VBox scrollContent = new VBox(0);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Hero Section
        StackPane hero = new StackPane();
        hero.setPrefHeight(240);

        ImageView bgImg = new ImageView();
        try {
            bgImg.setImage(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuBtDxQBgUKvGTh9Err1w5J7x4E4BeQG79YtlhMDu5E_99ZhVUgcLjBdpHTfbqicTEwDqRsQwi5qEAAVVkAPjZdFubFdUoh0WS_throQcS8dOi48uZutnJfFYN5ONN1BSA67eb6ly4oI_HBYOaKQWAWg5j7DYIEU4GZkWr2ZY7egwhGJiIibf2wvxpYZkbXwWjHbBc8sRkECDgLdYh5gW0FrScpeEBFwYQuixm732Y_UarBed7R5_t6mtFzfFenhyiuAqkT-rSLGYpY",
                    480, 240, false, true));
        } catch (Exception e) {
        }
        bgImg.setFitWidth(430);
        bgImg.setFitHeight(240);
        bgImg.setPreserveRatio(false);

        Region overlay = new Region();
        overlay.setStyle("-fx-background-color: linear-gradient(rgba(16, 34, 22, 0.7), rgba(16, 34, 22, 0.7));");

        StackPane iconCircle = new StackPane();
        iconCircle.setPrefSize(100, 100);
        iconCircle.setMaxSize(100, 100);
        iconCircle.setStyle(
                "-fx-background-color: rgba(19, 236, 91, 0.2); -fx-background-radius: 50; -fx-border-color: rgba(19, 236, 91, 0.3);");
        Label bankIcon = new Label("\ud83c\udfdb\ufe0f"); // Bank icon
        bankIcon.setTextFill(Color.web(PRIMARY));
        bankIcon.setStyle("-fx-font-size: 40;");
        iconCircle.getChildren().add(bankIcon);

        hero.getChildren().addAll(bgImg, overlay, iconCircle);

        // Headline
        VBox headlineSect = new VBox(10);
        headlineSect.setAlignment(Pos.CENTER);
        headlineSect.setPadding(new Insets(30, 20, 20, 20));

        Label h1 = new Label("Secure Your Earnings");
        h1.setTextFill(Color.WHITE);
        h1.setFont(Font.font("System", FontWeight.BOLD, 32));
        h1.setWrapText(true);
        h1.setAlignment(Pos.CENTER);

        Label p = new Label(
                "Rent is collected automatically through FindaHome. Link your bank account to receive direct deposits safely and quickly into your local currency.");
        p.setTextFill(Color.web(TEXT_GRAY));
        p.setFont(Font.font(16));
        p.setWrapText(true);
        p.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        headlineSect.getChildren().addAll(h1, p);

        // Security Panel
        VBox securityPanel = new VBox();
        securityPanel.setPadding(new Insets(10, 20, 20, 20));

        HBox panel = new HBox(15);
        panel.setAlignment(Pos.CENTER_LEFT);
        panel.setPadding(new Insets(20));
        panel.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: #3b5443;");

        StackPane shieldBox = new StackPane();
        shieldBox.setPadding(new Insets(8));
        shieldBox.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 8;");
        Label shield = new Label("\ud83d\udee1\ufe0f");
        shield.setTextFill(Color.web(PRIMARY));
        shield.setStyle("-fx-font-size: 20;");
        shieldBox.getChildren().add(shield);

        VBox panelText = new VBox(2);
        Label panelTitle = new Label("Bank-Grade Security");
        panelTitle.setTextFill(Color.WHITE);
        panelTitle.setFont(Font.font("System", FontWeight.BOLD, 15));
        Label panelDesc = new Label("Your data is protected with 256-bit encryption and full PCI-DSS compliance.");
        panelDesc.setTextFill(Color.web(TEXT_GRAY));
        panelDesc.setFont(Font.font(13));
        panelDesc.setWrapText(true);
        panelText.getChildren().addAll(panelTitle, panelDesc);
        HBox.setHgrow(panelText, Priority.ALWAYS);

        panel.getChildren().addAll(shieldBox, panelText);
        securityPanel.getChildren().add(panel);

        // Trust Points
        VBox trustSect = new VBox(15);
        trustSect.setPadding(new Insets(10, 25, 30, 25));
        trustSect.getChildren().addAll(
                createTrustPoint("Automated monthly rent payouts"),
                createTrustPoint("Low transaction fees for FindaHome landlords"),
                createTrustPoint("Real-time withdrawal tracking"));

        scrollContent.getChildren().addAll(hero, headlineSect, securityPanel, trustSect);

        // Footer Actions
        VBox footer = new VBox(20);
        footer.setPadding(new Insets(20, 20, 25, 20));
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        Button addBtn = new Button("Add Bank Account");
        addBtn.setGraphic(new Label("\ud83d\udcb3  "));
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setPrefHeight(56);
        addBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-font-size: 18; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(19, 236, 91, 0.2), 10, 0, 0, 5);");
        addBtn.setOnAction(e -> MainApp.navigateTo(new BankDetailsEntryView()));

        VBox bankLogoSect = new VBox(12);
        bankLogoSect.setAlignment(Pos.CENTER);
        Label bankLbl = new Label("SUPPORTED INSTITUTIONS");
        bankLbl.setTextFill(Color.web(TEXT_GRAY));
        bankLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
        bankLbl.setStyle("-fx-letter-spacing: 1px;");

        HBox logos = new HBox(25);
        logos.setAlignment(Pos.CENTER);
        logos.setOpacity(0.6);
        logos.getChildren().addAll(
                createBankLogo("\ud83c\udfe2", "Equity"),
                createBankLogo("\ud83d\udc5b", "KCB"),
                createBankLogo("\ud83d\udcb5", "Absa"),
                createBankLogo("\ud83d\udd37", "Stripe"),
                createBankLogo("\ud83c\udf10", "SWIFT"));
        bankLogoSect.getChildren().addAll(bankLbl, logos);

        Label legal = new Label(
                "By linking an account, you agree to our Landlord Financial Terms and Service Payout Agreement.");
        legal.setTextFill(Color.web(TEXT_GRAY));
        legal.setOpacity(0.5);
        legal.setFont(Font.font(10));
        legal.setWrapText(true);
        legal.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        legal.setPadding(new Insets(0, 30, 0, 30));

        footer.getChildren().addAll(addBtn, bankLogoSect, legal);

        layout.getChildren().addAll(header, scroll);
        getChildren().addAll(layout, footer);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
    }

    private HBox createTrustPoint(String text) {
        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        Label check = new Label("\u2705");
        check.setTextFill(Color.web(PRIMARY));
        Label lbl = new Label(text);
        lbl.setTextFill(Color.web("#d1d5db"));
        lbl.setFont(Font.font(14));
        row.getChildren().addAll(check, lbl);
        return row;
    }

    private VBox createBankLogo(String icon, String name) {
        VBox box = new VBox(4);
        box.setAlignment(Pos.CENTER);
        Label i = new Label(icon);
        i.setStyle("-fx-font-size: 24;");
        i.setTextFill(Color.WHITE);
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font(10));
        box.getChildren().addAll(i, n);
        return box;
    }
}
