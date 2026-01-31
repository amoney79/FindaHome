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
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MarketTrendsView extends VBox {

    private static final String BACKGROUND_DARK = "#221610";
    private static final String PRIMARY = "#f46a25";
    private static final String CARD_BG = "#2d1e17";
    private static final String TEXT_GRAY = "#8a6e60";

    public MarketTrendsView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // --- Header ---
        HBox header = new HBox(0);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "cc; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

        HBox leftSection = new HBox(8);
        leftSection.setAlignment(Pos.CENTER_LEFT);

        Button backBtn = new Button("\u276E"); // arrow_back_ios
        backBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                + "; -fx-font-size: 18; -fx-cursor: hand;");
        backBtn.setOnAction(e -> MainApp.showHome());

        Label title = new Label("Westlands Market");
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setTextFill(Color.WHITE);

        leftSection.getChildren().addAll(backBtn, title);
        HBox.setHgrow(leftSection, Priority.ALWAYS);

        Button shareBtn = new Button("\uE80D"); // share
        shareBtn.setStyle("-fx-background-color: rgba(244,106,37,0.1); -fx-text-fill: " + PRIMARY
                + "; -fx-background-radius: 20; -fx-font-size: 18; -fx-padding: 8;");

        header.getChildren().addAll(leftSection, shareBtn);

        // --- Main Content ---
        VBox content = new VBox(0);
        content.setPadding(new Insets(0, 0, 100, 0));

        // 1. Tabs for Bedroom Types
        HBox tabs = new HBox(24);
        tabs.setPadding(new Insets(0, 16, 0, 16));
        tabs.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

        tabs.getChildren().addAll(
                createTab("1 Bed", true),
                createTab("2 Bed", false),
                createTab("3 Bed", false));

        // 2. Main Trend Chart Section
        VBox chartSection = new VBox(16);
        chartSection.setPadding(new Insets(16));
        chartSection.setStyle("-fx-background-color: rgba(45,30,23,0.3);");

        VBox priceHeader = new VBox(4);
        Label priceLabel = new Label("Average Monthly Rent (KES)");
        priceLabel.setTextFill(Color.web(TEXT_GRAY));
        priceLabel.setFont(Font.font("System", FontWeight.MEDIUM, 14));

        HBox priceRow = new HBox(8);
        priceRow.setAlignment(Pos.BASELINE_LEFT);
        Label priceValue = new Label("68,500");
        priceValue.setTextFill(Color.WHITE);
        priceValue.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 32));

        HBox changeIndicator = new HBox(2);
        changeIndicator.setAlignment(Pos.CENTER);
        Label upArrow = new Label("\u2191"); // trending_up
        upArrow.setTextFill(Color.web("#07880b"));
        upArrow.setFont(Font.font(14));
        Label changePercent = new Label("+5.2%");
        changePercent.setTextFill(Color.web("#07880b"));
        changePercent.setFont(Font.font("System", FontWeight.BOLD, 14));
        changeIndicator.getChildren().addAll(upArrow, changePercent);

        priceRow.getChildren().addAll(priceValue, changeIndicator);

        Label priceSubtext = new Label("Last 12 months in Westlands Ward");
        priceSubtext.setTextFill(Color.web(TEXT_GRAY));
        priceSubtext.setFont(Font.font(12));

        priceHeader.getChildren().addAll(priceLabel, priceRow, priceSubtext);

        // Chart visualization (simplified)
        VBox chartContainer = new VBox(16);
        chartContainer.setPadding(new Insets(24, 0, 0, 0));

        Pane chartCanvas = new Pane();
        chartCanvas.setPrefHeight(160);
        chartCanvas.setStyle("-fx-background-color: transparent;");

        // Simple line chart simulation using SVG path
        SVGPath chartPath = new SVGPath();
        chartPath.setContent(
                "M0 120C20 120 30 40 50 40C70 40 80 60 100 60C120 60 130 110 150 110C170 110 180 50 200 50C220 50 230 115 250 115C270 115 280 80 300 80C320 80 330 60 350 60C370 60 380 130 400 130C420 130 430 15 450 15C470 15 478 40 478 40");
        chartPath.setStroke(Color.web(PRIMARY));
        chartPath.setStrokeWidth(3);
        chartPath.setFill(Color.TRANSPARENT);
        chartCanvas.getChildren().add(chartPath);

        HBox monthLabels = new HBox();
        monthLabels.setAlignment(Pos.CENTER);
        monthLabels.setPadding(new Insets(0, 8, 0, 8));
        Region m1 = new Region();
        Region m2 = new Region();
        Region m3 = new Region();
        Region m4 = new Region();
        HBox.setHgrow(m1, Priority.ALWAYS);
        HBox.setHgrow(m2, Priority.ALWAYS);
        HBox.setHgrow(m3, Priority.ALWAYS);
        HBox.setHgrow(m4, Priority.ALWAYS);

        monthLabels.getChildren().addAll(
                createMonthLabel("JAN"), m1,
                createMonthLabel("APR"), m2,
                createMonthLabel("JUL"), m3,
                createMonthLabel("OCT"), m4,
                createMonthLabel("DEC"));

        chartContainer.getChildren().addAll(chartCanvas, monthLabels);
        chartSection.getChildren().addAll(priceHeader, chartContainer);

        // 3. Market Verdict Card
        VBox verdictSection = new VBox();
        verdictSection.setPadding(new Insets(16));

        HBox verdictCard = new HBox(16);
        verdictCard.setPadding(new Insets(16));
        verdictCard.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 12;");

        VBox verdictText = new VBox(8);
        HBox.setHgrow(verdictText, Priority.ALWAYS);

        HBox verdictHeader = new HBox(6);
        verdictHeader.setAlignment(Pos.CENTER_LEFT);
        Label trendIcon = new Label("\u2191");
        trendIcon.setTextFill(Color.web(PRIMARY));
        trendIcon.setFont(Font.font(14));
        Label verdictLabel = new Label("MARKET VERDICT");
        verdictLabel.setTextFill(Color.web(PRIMARY));
        verdictLabel.setFont(Font.font("System", FontWeight.BOLD, 10));
        verdictHeader.getChildren().addAll(trendIcon, verdictLabel);

        Label verdictTitle = new Label("Rent is rising");
        verdictTitle.setTextFill(Color.WHITE);
        verdictTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        Label verdictDesc = new Label(
                "Demand in Westlands is outpacing new inventory. Listing prices have shifted up by 5.2% since January.");
        verdictDesc.setTextFill(Color.web(TEXT_GRAY));
        verdictDesc.setFont(Font.font(14));
        verdictDesc.setWrapText(true);

        verdictText.getChildren().addAll(verdictHeader, verdictTitle, verdictDesc);

        VBox growthBadge = new VBox(2);
        growthBadge.setAlignment(Pos.CENTER);
        growthBadge.setPrefWidth(96);
        growthBadge.setPadding(new Insets(12));
        growthBadge.setStyle("-fx-background-color: rgba(244,106,37,0.1); -fx-background-radius: 8;");
        Label growthValue = new Label("+5.2%");
        growthValue.setTextFill(Color.web(PRIMARY));
        growthValue.setFont(Font.font("System", FontWeight.BLACK, 24));
        Label growthLabel = new Label("GROWTH");
        growthLabel.setTextFill(Color.web(PRIMARY));
        growthLabel.setFont(Font.font("System", FontWeight.BOLD, 10));
        growthBadge.getChildren().addAll(growthValue, growthLabel);

        verdictCard.getChildren().addAll(verdictText, growthBadge);
        verdictSection.getChildren().add(verdictCard);

        // 4. Price Distribution Section
        VBox distributionSection = new VBox(16);
        distributionSection.setPadding(new Insets(16, 16, 0, 16));

        Label distTitle = new Label("Price Distribution (KES)");
        distTitle.setTextFill(Color.WHITE);
        distTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        VBox bars = new VBox(12);
        bars.getChildren().addAll(
                createDistributionBar("40k - 55k", "15% of listings", 0.15, false),
                createDistributionBar("55k - 70k (Common)", "62% of listings", 0.62, true),
                createDistributionBar("70k - 85k", "18% of listings", 0.18, false),
                createDistributionBar("85k+", "5% of listings", 0.05, false));

        distributionSection.getChildren().addAll(distTitle, bars);

        // 5. Best Time to Rent Insight
        VBox insightSection = new VBox();
        insightSection.setPadding(new Insets(16));

        StackPane insightCard = new StackPane();
        insightCard.setPadding(new Insets(20));
        insightCard.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 12;");

        VBox insightContent = new VBox(12);

        HBox insightHeader = new HBox(8);
        insightHeader.setAlignment(Pos.CENTER_LEFT);
        Label bulbIcon = new Label("\uD83D\uDCA1");
        bulbIcon.setFont(Font.font(18));
        Label insightTitle = new Label("Best Time to Rent");
        insightTitle.setTextFill(Color.WHITE);
        insightTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        insightHeader.getChildren().addAll(bulbIcon, insightTitle);

        Label insightText = new Label(
                "Historically, November & December see a 4% dip in asking prices as demand cools during the festive season.");
        insightText.setTextFill(Color.web("#ffffff", 0.9));
        insightText.setFont(Font.font("System", FontWeight.MEDIUM, 14));
        insightText.setWrapText(true);

        HBox tags = new HBox(8);
        Label tag1 = new Label("Low Demand");
        tag1.setStyle(
                "-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 4 12; -fx-font-weight: bold; -fx-font-size: 11;");
        Label tag2 = new Label("High Leverage");
        tag2.setStyle(
                "-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 4 12; -fx-font-weight: bold; -fx-font-size: 11;");
        tags.getChildren().addAll(tag1, tag2);

        insightContent.getChildren().addAll(insightHeader, insightText, tags);
        insightCard.getChildren().add(insightContent);
        insightSection.getChildren().add(insightCard);

        // 6. Hotspot Map
        VBox mapSection = new VBox(12);
        mapSection.setPadding(new Insets(16));

        Label mapTitle = new Label("Hotspot Map");
        mapTitle.setTextFill(Color.WHITE);
        mapTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

        StackPane mapContainer = new StackPane();
        mapContainer.setPrefHeight(200);
        mapContainer.setStyle("-fx-background-radius: 12;");

        ImageView mapView = new ImageView();
        try {
            Image mapImg = new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuAsLgv-ZmR7HXOjKdtwgui6ljt7AqGrhCurtSHl0eqZpQGUQGjyA3lzvlXVTHTFBOG2TZWljpuWZwzhM82MBBR80z-bwGCH6Ar49cA6eIqVcgy5R1Tc3hSDZ7C51MtezKjntV3CjXo4oxUhAWi9QJkSLTIFPmQ3I0jlj8AYu9CKRJBTc2yG4Dq0wsyWnnKX1R2lk8ykkCSc49QMteoNiYQQsJGWUT3V5g7MYKacHxfyWP3coC2rVMEm37LXZ5hLalcYqTrgYn7XQnI",
                    400, 200, false, true);
            mapView.setImage(mapImg);
        } catch (Exception e) {
        }
        mapView.setFitWidth(400);
        mapView.setFitHeight(200);
        mapView.setPreserveRatio(false);

        StackPane pinContainer = new StackPane();
        pinContainer.setPrefSize(40, 40);
        pinContainer.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-background-radius: 20; -fx-border-color: white; -fx-border-width: 4; -fx-border-radius: 20;");
        Label pinIcon = new Label("\uD83D\uDCCD");
        pinIcon.setTextFill(Color.WHITE);
        pinIcon.setFont(Font.font(20));
        pinContainer.getChildren().add(pinIcon);

        mapContainer.getChildren().addAll(mapView, pinContainer);
        mapSection.getChildren().addAll(mapTitle, mapContainer);

        content.getChildren().addAll(tabs, chartSection, verdictSection, distributionSection, insightSection,
                mapSection);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // --- Bottom Navigation Bar ---
        HBox bottomNav = new HBox();
        bottomNav.setPadding(new Insets(12, 24, 32, 24));
        bottomNav.setAlignment(Pos.CENTER);
        bottomNav.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "ee; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");

        Region n1 = new Region();
        Region n2 = new Region();
        Region n3 = new Region();
        HBox.setHgrow(n1, Priority.ALWAYS);
        HBox.setHgrow(n2, Priority.ALWAYS);
        HBox.setHgrow(n3, Priority.ALWAYS);

        bottomNav.getChildren().addAll(
                createNavItem("\uD83D\uDD0D", "Explore", false), n1,
                createNavItem("\uD83D\uDCC8", "Trends", true), n2,
                createNavItem("\u2665", "Saved", false), n3,
                createNavItem("\uD83D\uDC64", "Profile", false));

        // Main Layout
        StackPane root = new StackPane();
        root.getChildren().addAll(scrollPane, bottomNav);
        StackPane.setAlignment(bottomNav, Pos.BOTTOM_CENTER);

        getChildren().addAll(header, root);
    }

    private VBox createTab(String text, boolean active) {
        VBox tab = new VBox();
        tab.setAlignment(Pos.CENTER);
        tab.setPadding(new Insets(12, 0, 12, 0));
        tab.setStyle("-fx-border-color: " + (active ? PRIMARY : "transparent")
                + "; -fx-border-width: 0 0 3 0; -fx-cursor: hand;");

        Label label = new Label(text);
        label.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        label.setFont(Font.font("System", FontWeight.BOLD, 14));

        tab.getChildren().add(label);
        return tab;
    }

    private Label createMonthLabel(String month) {
        Label label = new Label(month);
        label.setTextFill(Color.web(TEXT_GRAY));
        label.setFont(Font.font("System", FontWeight.BOLD, 11));
        return label;
    }

    private VBox createDistributionBar(String range, String percent, double width, boolean highlight) {
        VBox bar = new VBox(4);

        HBox labels = new HBox();
        labels.setAlignment(Pos.CENTER_LEFT);
        Label rangeLabel = new Label(range);
        rangeLabel.setTextFill(highlight ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        rangeLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label percentLabel = new Label(percent);
        percentLabel.setTextFill(highlight ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        percentLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        labels.getChildren().addAll(rangeLabel, spacer, percentLabel);

        StackPane track = new StackPane();
        track.setPrefHeight(8);
        track.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 4;");

        Region fill = new Region();
        fill.setPrefHeight(8);
        fill.setStyle("-fx-background-color: " + (highlight ? PRIMARY : "rgba(244,106,37,0.4)")
                + "; -fx-background-radius: 4;");
        fill.maxWidthProperty().bind(track.widthProperty().multiply(width));
        StackPane.setAlignment(fill, Pos.CENTER_LEFT);

        track.getChildren().add(fill);
        bar.getChildren().addAll(labels, track);
        return bar;
    }

    private VBox createNavItem(String icon, String label, boolean active) {
        VBox item = new VBox(4);
        item.setAlignment(Pos.CENTER);

        Label iconLabel = new Label(icon);
        iconLabel.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        iconLabel.setFont(Font.font(20));

        Label textLabel = new Label(label);
        textLabel.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        textLabel.setFont(Font.font("System", FontWeight.BOLD, 10));

        item.getChildren().addAll(iconLabel, textLabel);
        return item;
    }
}
