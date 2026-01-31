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

public class CountyDiscoveryView extends VBox {

    private static final String BACKGROUND_LIGHT = "#f8f6f5";
    private static final String PRIMARY = "#f46a25";
    private static final String TEXT_GRAY = "#64748b";

    public CountyDiscoveryView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_LIGHT + ";");

        // --- iOS Status Bar ---
        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(8, 24, 8, 24));
        statusBar.setAlignment(Pos.CENTER_LEFT);
        statusBar.setStyle("-fx-background-color: " + BACKGROUND_LIGHT + ";");

        Label time = new Label("9:41");
        time.setFont(Font.font("System", FontWeight.BOLD, 12));
        time.setTextFill(Color.BLACK);

        Region statusSpacer = new Region();
        HBox.setHgrow(statusSpacer, Priority.ALWAYS);

        HBox statusIcons = new HBox(4);
        statusIcons.setAlignment(Pos.CENTER_RIGHT);
        Label signal = new Label("\uD83D\uDCF6");
        Label wifi = new Label("\uD83D\uDCF6");
        Label battery = new Label("\uD83D\uDD0B");
        signal.setFont(Font.font(12));
        wifi.setFont(Font.font(12));
        battery.setFont(Font.font(12));
        statusIcons.getChildren().addAll(signal, wifi, battery);

        statusBar.getChildren().addAll(time, statusSpacer, statusIcons);

        // --- Header & Search ---
        VBox headerSection = new VBox(16);
        headerSection.setPadding(new Insets(8, 20, 16, 20));
        headerSection.setStyle("-fx-background-color: " + BACKGROUND_LIGHT + ";");

        HBox headerTop = new HBox();
        headerTop.setAlignment(Pos.CENTER_LEFT);

        HBox logoSection = new HBox(8);
        logoSection.setAlignment(Pos.CENTER_LEFT);

        StackPane logoBox = new StackPane();
        logoBox.setPrefSize(32, 32);
        logoBox.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 8;");
        Label homeIcon = new Label("\u2302");
        homeIcon.setTextFill(Color.WHITE);
        homeIcon.setFont(Font.font(18));
        logoBox.getChildren().add(homeIcon);

        Label appName = new Label("FindaHome");
        appName.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 20));
        appName.setTextFill(Color.BLACK);

        logoSection.getChildren().addAll(logoBox, appName);
        HBox.setHgrow(logoSection, Priority.ALWAYS);

        Button notifBtn = new Button("\uD83D\uDD14");
        notifBtn.setStyle(
                "-fx-background-color: #e2e8f0; -fx-background-radius: 20; -fx-padding: 8; -fx-font-size: 16;");

        headerTop.getChildren().addAll(logoSection, notifBtn);

        // Search Bar
        HBox searchBar = new HBox();
        searchBar.setAlignment(Pos.CENTER_LEFT);
        searchBar.setPadding(new Insets(14, 16, 14, 16));
        searchBar.setStyle(
                "-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 4, 0, 0, 2);");

        Label searchIcon = new Label("\uD83D\uDD0D");
        searchIcon.setTextFill(Color.web(TEXT_GRAY));
        searchIcon.setFont(Font.font(16));

        TextField searchField = new TextField();
        searchField.setPromptText("Search for a County or Ward...");
        searchField.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-prompt-text-fill: "
                + TEXT_GRAY + "; -fx-text-fill: black;");
        HBox.setHgrow(searchField, Priority.ALWAYS);

        searchBar.getChildren().addAll(searchIcon, searchField);
        headerSection.getChildren().addAll(headerTop, searchBar);

        // --- Main Content ---
        VBox content = new VBox(32);
        content.setPadding(new Insets(16, 0, 100, 0));

        // 1. Popular Searches Section
        VBox popularSection = new VBox(16);

        HBox popularHeader = new HBox();
        popularHeader.setPadding(new Insets(0, 20, 0, 20));
        popularHeader.setAlignment(Pos.CENTER_LEFT);
        Label popularTitle = new Label("Popular Searches");
        popularTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        popularTitle.setTextFill(Color.BLACK);
        Region pSpacer = new Region();
        HBox.setHgrow(pSpacer, Priority.ALWAYS);
        Button seeAllBtn = new Button("See All");
        seeAllBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                + "; -fx-font-weight: bold; -fx-font-size: 13;");
        popularHeader.getChildren().addAll(popularTitle, pSpacer, seeAllBtn);

        ScrollPane popularScroll = new ScrollPane();
        popularScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        popularScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        popularScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        popularScroll.setPadding(new Insets(0, 0, 16, 0));

        HBox popularCards = new HBox(16);
        popularCards.setPadding(new Insets(0, 20, 0, 20));

        popularCards.getChildren().addAll(
                createPopularCard("Nairobi",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuB43Y8fFKzhhoU_6NI0PMTXsPuoqZXDFlWhoHYWg5bDKV7-XydkLOK5qhO_D0hoCwQISgaqbveUoKE0kLIVOFWX056eaTltH1Br1eb0ojPjqSKwrFSRKcdMpytvBFvaAJS5c-kZHTGWQ3y-dmldY9T8TZdUcqjcf4RZC6e9tm3do_Q_RxV_KlL5jxQ_lX6zrGrzNjekx_Wn1kJ5Pj0UrL0sctNnjnY65YKXL5i0VJIPrFUOnkzQQVzqReTtz6dOlXB2RawTnncyeHk",
                        true, "Westlands", "Kilimani", "Karen"),
                createPopularCard("Mombasa",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuATUrjGxeu37goNJKcEoBJJlQ4J8CHoKman58MX9GEflZCi6aAgCqppbYCVjvuY0vAmMJt3NrJoMRcj-F9EONiFuzSOtKNPoNSrxGXOSKkSxXRUdoX1oc6hXt_qYDWTQulxT1X3DtpW6O_h0HaVaS6HlnXP_y1rvrzu7NwWfqei3aRoaWsn09X0mvYbcjx12n1fe0fCDEsZeK6Lt7PSmBdsUsMHPy4epykP9cX0WFi7XMcLLE-ZTROHRpHCrsE9NhjrCHFOG5nj6KM",
                        false, "Nyali", "Bamburi", "Mtwapa"),
                createPopularCard("Nakuru",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuB-YdOlWkf4Qu3E8GxlAyz6KVPGjZYk1neU1Smmu9fwpS6Z8xpCF4y7lDpqF62_iPsUSWgY7L53grEofIE1Q1ylYaMtflhauzFLdaraPuP9DRl7AbOHjCQ1FMxfBWcPVh20vOEaKNXUr1wigWitU7jXTDwSKZ1VdESQneZhv-zEZAUBvUoNE7bjtLq7SuUDmW1rEuQTgx9n4FooKhFcsx3ihWi2h6_yyHtxtvoI3Dw3AWn3F_d_LiCJTDabJI8_T4QbY-v3WJZDF6k",
                        false, "Naivasha", "Lanet", "Milimani"));

        popularScroll.setContent(popularCards);
        popularSection.getChildren().addAll(popularHeader, popularScroll);

        // 2. Browse by County Grid
        VBox countySection = new VBox(16);
        countySection.setPadding(new Insets(0, 20, 0, 20));

        HBox countyHeader = new HBox();
        countyHeader.setAlignment(Pos.CENTER_LEFT);
        Label countyTitle = new Label("Browse by County");
        countyTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        countyTitle.setTextFill(Color.BLACK);
        Region cSpacer = new Region();
        HBox.setHgrow(cSpacer, Priority.ALWAYS);
        HBox mapLink = new HBox(4);
        mapLink.setAlignment(Pos.CENTER);
        Label mapIcon = new Label("\uD83D\uDDFA");
        mapIcon.setTextFill(Color.web(TEXT_GRAY));
        mapIcon.setFont(Font.font(12));
        Label mapText = new Label("View Map");
        mapText.setTextFill(Color.web(TEXT_GRAY));
        mapText.setFont(Font.font(13));
        mapLink.getChildren().addAll(mapIcon, mapText);
        countyHeader.getChildren().addAll(countyTitle, cSpacer, mapLink);

        GridPane countyGrid = new GridPane();
        countyGrid.setHgap(12);
        countyGrid.setVgap(12);

        // Add county buttons
        countyGrid.add(createCountyButton("01", "Mombasa", false), 0, 0);
        countyGrid.add(createCountyButton("02", "Kwale", false), 1, 0);
        countyGrid.add(createCountyButton("03", "Kilifi", false), 2, 0);
        countyGrid.add(createCountyButton("11", "Isiolo", false), 0, 1);
        countyGrid.add(createCountyButton("12", "Meru", false), 1, 1);
        countyGrid.add(createCountyButton("19", "Nyeri", false), 2, 1);
        countyGrid.add(createCountyButton("30", "Baringo", false), 0, 2);
        countyGrid.add(createCountyButton("32", "Nakuru", false), 1, 2);
        countyGrid.add(createCountyButton("47", "Nairobi", true), 2, 2);

        Button showAllBtn = new Button("Show All 47 Counties");
        showAllBtn.setGraphic(createIcon("\u25BC"));
        showAllBtn.setMaxWidth(Double.MAX_VALUE);
        showAllBtn.setPrefHeight(48);
        showAllBtn.setStyle(
                "-fx-background-color: white; -fx-border-color: #e2e8f0; -fx-border-radius: 12; -fx-background-radius: 12; -fx-text-fill: "
                        + TEXT_GRAY + "; -fx-font-weight: bold; -fx-font-size: 13;");
        VBox.setMargin(showAllBtn, new Insets(8, 0, 0, 0));

        countySection.getChildren().addAll(countyHeader, countyGrid, showAllBtn);
        content.getChildren().addAll(popularSection, countySection);

        ScrollPane mainScroll = new ScrollPane(content);
        mainScroll.setFitToWidth(true);
        mainScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        mainScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // --- Bottom Navigation ---
        HBox bottomNav = new HBox();
        bottomNav.setPadding(new Insets(12, 24, 32, 24));
        bottomNav.setAlignment(Pos.CENTER);
        bottomNav.setStyle(
                "-fx-background-color: rgba(255,255,255,0.8); -fx-border-color: #e2e8f0; -fx-border-width: 1 0 0 0; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 10, 0, 0, -2);");

        Region n1 = new Region();
        Region n2 = new Region();
        Region n3 = new Region();
        Region n4 = new Region();
        HBox.setHgrow(n1, Priority.ALWAYS);
        HBox.setHgrow(n2, Priority.ALWAYS);
        HBox.setHgrow(n3, Priority.ALWAYS);
        HBox.setHgrow(n4, Priority.ALWAYS);

        VBox addBtnContainer = new VBox();
        addBtnContainer.setAlignment(Pos.CENTER);
        Button addBtn = new Button("+");
        addBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-background-radius: 24; -fx-font-size: 24; -fx-font-weight: bold; -fx-pref-width: 48; -fx-pref-height: 48; -fx-effect: dropshadow(three-pass-box, rgba(244,106,37,0.3), 10, 0, 0, 4);");
        VBox.setMargin(addBtn, new Insets(-32, 0, 0, 0));
        Label addLabel = new Label("List");
        addLabel.setFont(Font.font(10));
        addLabel.setTextFill(Color.web(TEXT_GRAY));
        addBtnContainer.getChildren().addAll(addBtn, addLabel);

        bottomNav.getChildren().addAll(
                createNavItem("\uD83E\uDDED", "Discover", true), n1,
                createNavItem("\uD83D\uDDFA", "Map", false), n2,
                addBtnContainer, n3,
                createNavItem("\u2665", "Saved", false), n4,
                createNavItem("\uD83D\uDC64", "Profile", false));

        StackPane root = new StackPane();
        root.getChildren().addAll(mainScroll, bottomNav);
        StackPane.setAlignment(bottomNav, Pos.BOTTOM_CENTER);

        getChildren().addAll(statusBar, headerSection, root);
    }

    private StackPane createPopularCard(String city, String imageUrl, boolean trending, String... wards) {
        StackPane card = new StackPane();
        card.setPrefSize(256, 320);
        card.setStyle(
                "-fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 8, 0, 0, 4);");

        ImageView imageView = new ImageView();
        try {
            Image img = new Image(imageUrl, 256, 320, false, true);
            imageView.setImage(img);
        } catch (Exception e) {
        }
        imageView.setFitWidth(256);
        imageView.setFitHeight(320);
        imageView.setPreserveRatio(false);
        imageView.setStyle("-fx-background-radius: 12;");

        VBox overlay = new VBox(8);
        overlay.setPadding(new Insets(20));
        overlay.setAlignment(Pos.BOTTOM_LEFT);
        overlay.setStyle(
                "-fx-background-color: linear-gradient(to top, rgba(0,0,0,0.9) 0%, rgba(0,0,0,0.2) 50%, transparent 100%); -fx-background-radius: 12;");

        if (trending) {
            Label trendBadge = new Label("TRENDING");
            trendBadge.setStyle("-fx-background-color: " + PRIMARY
                    + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10; -fx-padding: 2 8; -fx-background-radius: 12;");
            overlay.getChildren().add(trendBadge);
        }

        Label cityName = new Label(city);
        cityName.setTextFill(Color.WHITE);
        cityName.setFont(Font.font("System", FontWeight.BOLD, 24));

        HBox wardTags = new HBox(8);
        for (String ward : wards) {
            Label tag = new Label(ward);
            tag.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-font-size: 11; -fx-padding: 4 8; -fx-background-radius: 4;");
            wardTags.getChildren().add(tag);
        }

        overlay.getChildren().addAll(cityName, wardTags);
        card.getChildren().addAll(imageView, overlay);
        return card;
    }

    private VBox createCountyButton(String number, String name, boolean highlighted) {
        VBox button = new VBox(4);
        button.setAlignment(Pos.CENTER);
        button.setPadding(new Insets(16));
        button.setPrefSize(100, 80);

        if (highlighted) {
            button.setStyle(
                    "-fx-background-color: rgba(244,106,37,0.05); -fx-background-radius: 12; -fx-border-color: rgba(244,106,37,0.2); -fx-border-width: 2; -fx-border-radius: 12; -fx-cursor: hand;");
        } else {
            button.setStyle(
                    "-fx-background-color: white; -fx-background-radius: 12; -fx-border-color: #f1f5f9; -fx-border-width: 1; -fx-border-radius: 12; -fx-cursor: hand;");
        }

        Label numLabel = new Label(number);
        numLabel.setTextFill(Color.web(PRIMARY));
        numLabel.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 20));

        Label nameLabel = new Label(name);
        nameLabel.setTextFill(Color.BLACK);
        nameLabel.setFont(Font.font("System", highlighted ? FontWeight.BOLD : FontWeight.SEMI_BOLD, 12));
        nameLabel.setWrapText(true);
        nameLabel.setAlignment(Pos.CENTER);

        button.getChildren().addAll(numLabel, nameLabel);
        return button;
    }

    private Label createIcon(String icon) {
        Label label = new Label(icon);
        label.setFont(Font.font(12));
        label.setTextFill(Color.web(TEXT_GRAY));
        return label;
    }

    private VBox createNavItem(String icon, String label, boolean active) {
        VBox item = new VBox(4);
        item.setAlignment(Pos.CENTER);

        Label iconLabel = new Label(icon);
        iconLabel.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        iconLabel.setFont(Font.font(20));

        Label textLabel = new Label(label);
        textLabel.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        textLabel.setFont(Font.font("System", active ? FontWeight.BOLD : FontWeight.NORMAL, 10));

        item.getChildren().addAll(iconLabel, textLabel);
        return item;
    }
}
