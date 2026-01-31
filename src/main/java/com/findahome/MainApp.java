package com.findahome;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String CARD_BG = "#1c2433";
    private static final String PRIMARY = "#135bec";
    private static final String TEXT_GRAY = "#9da6b9";

    private static StackPane contentArea;
    private static MainApp instance;
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        instance = this;
        
        primaryStage.initStyle(StageStyle.UNDECORATED);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        contentArea = new StackPane();

        // Navigation Areas
        VBox topNav = createTopNav();
        root.setTop(topNav);
        root.setCenter(contentArea);
        root.setBottom(createBottomNav());

        // Enable window dragging
        topNav.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        topNav.setOnMouseDragged(event -> {
            if (!stage.isMaximized()) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        showDashboard();

        Scene scene = new Scene(root, 400, 800);
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("FindaHome Marketplace");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void navigateTo(Node view) {
        contentArea.getChildren().setAll(view);
    }

    public static void showHome() {
        instance.showDashboard();
    }

    public void showDashboard() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(10, 0, 100, 0));

        // Carousel (Banners)
        HBox carouselBox = new HBox(15);
        carouselBox.setPadding(new Insets(0, 15, 0, 15));
        carouselBox.getChildren().addAll(
                createBanner("10% Off Your First\nMonth's Rent", "Limited time offer in Kilimani", "Featured",
                        "#135bec"),
                createBanner("Eco-friendly Villas\nReady for Viewing", "Starting from $1,200/mo", "New Listing",
                        "#16a34a"));
        ScrollPane carouselScroll = new ScrollPane(carouselBox);
        carouselScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        carouselScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Categories
        HBox categoryBox = new HBox(20);
        categoryBox.setPadding(new Insets(0, 15, 0, 15));
        categoryBox.getChildren().addAll(
                createCategory("Apartment", "\ud83c\udfe2", "rgba(19, 91, 236, 0.1)", PRIMARY),
                createCategory("Villas", "\ud83c\udfe1", "rgba(249, 115, 22, 0.1)", "#f97316"),
                createCategory("Bedsitters", "\ud83d\udccf", "rgba(168, 85, 247, 0.1)", "#a855f7"));
        ScrollPane categoryScroll = new ScrollPane(categoryBox);
        categoryScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        categoryScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Recommended Header
        HBox recHeader = new HBox();
        recHeader.setPadding(new Insets(10, 15, 0, 15));
        Label recTitle = new Label("Recommended for You");
        recTitle.setTextFill(Color.WHITE);
        recTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label viewAll = new Label("View All");
        viewAll.setTextFill(Color.web(PRIMARY));
        recHeader.getChildren().addAll(recTitle, spacer, viewAll);

        // Property Grid
        GridPane propertyGrid = new GridPane();
        propertyGrid.setHgap(12);
        propertyGrid.setVgap(12);
        propertyGrid.setPadding(new Insets(12, 15, 12, 15));

        java.util.List<Property> properties = java.util.Arrays.asList(
                new Property("Luxury Studio", "Westlands, Nairobi", "$450", "...", true, null),
                new Property("2BR Modern Suite", "Lavington, NRB", "$800", "...", false, null),
                new Property("Executive 4BR Villa", "Karen, Nairobi", "$1,500", "...", false, "Hot Deal"),
                new Property("Standard Bedsitter", "Ruiru, Bypass", "$180", "...", false, null));

        for (int i = 0; i < properties.size(); i++) {
            Property p = properties.get(i);
            VBox card = createPropertyCard(p);
            card.setOnMouseClicked(e -> navigateTo(new PropertyDetailView(p)));
            propertyGrid.add(card, i % 2, i / 2);
        }

        mainContent.getChildren().addAll(carouselScroll, categoryScroll, recHeader, propertyGrid);
        ScrollPane mainScroll = new ScrollPane(mainContent);
        mainScroll.setFitToWidth(true);
        mainScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        contentArea.getChildren().setAll(mainScroll);
    }

    private VBox createTopNav() {
        VBox topContainer = new VBox(10);
        topContainer.setPadding(new Insets(10, 15, 5, 15));

        // Window Controls
        HBox windowControls = new HBox(15);
        windowControls.setAlignment(Pos.CENTER_RIGHT);
        
        Label minBtn = new Label("\u2014");
        minBtn.setTextFill(Color.WHITE);
        minBtn.setStyle("-fx-cursor: hand; -fx-font-size: 14;");
        minBtn.setOnMouseClicked(e -> stage.setIconified(true));
        
        Label maxBtn = new Label("\ud83d\uddd2");
        maxBtn.setTextFill(Color.WHITE);
        maxBtn.setStyle("-fx-cursor: hand; -fx-font-size: 14;");
        maxBtn.setOnMouseClicked(e -> stage.setMaximized(!stage.isMaximized()));
        
        Label closeBtn = new Label("\u2715");
        closeBtn.setTextFill(Color.web("#ff5f57"));
        closeBtn.setStyle("-fx-cursor: hand; -fx-font-size: 14; -fx-font-weight: bold;");
        closeBtn.setOnMouseClicked(e -> stage.close());
        
        windowControls.getChildren().addAll(minBtn, maxBtn, closeBtn);

        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        Label logo = new Label("FindaHome");
        logo.setTextFill(Color.WHITE);
        logo.setFont(Font.font("System", FontWeight.BOLD, 20));
        logo.setOnMouseClicked(e -> showHome());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(15);
        Label bell = new Label("\ud83d\udd14");
        bell.setTextFill(Color.WHITE);
        bell.setOnMouseClicked(e -> navigateTo(new NotificationView()));
        Label cart = new Label("\ud83d\uded2");
        cart.setTextFill(Color.WHITE);
        actions.getChildren().addAll(bell, cart);

        header.getChildren().addAll(logo, spacer, actions);

        HBox searchContainer = new HBox(10);
        TextField search = new TextField();
        search.setPromptText("Search...");
        search.setStyle("-fx-background-color: " + CARD_BG + "; -fx-text-fill: white; -fx-background-radius: 12;");
        HBox.setHgrow(search, Priority.ALWAYS);
        search.setOnAction(e -> navigateTo(new ChatView()));

        Button filterBtn = new Button("\u2312");
        filterBtn.setStyle("-fx-background-color: rgba(19, 91, 236, 0.1); -fx-text-fill: " + PRIMARY
                + "; -fx-background-radius: 12;");
        filterBtn.setOnAction(e -> navigateTo(new FilterView()));

        Button adminBtn = new Button("\ud83d\udee1");
        adminBtn.setStyle(
                "-fx-background-color: rgba(19, 236, 91, 0.1); -fx-text-fill: #13ec5b; -fx-background-radius: 12;");
        adminBtn.setOnAction(e -> navigateTo(new AdminDashboardView()));

        searchContainer.getChildren().addAll(search, filterBtn, adminBtn);
        topContainer.getChildren().addAll(windowControls, header, searchContainer);
        return topContainer;
    }

    private Node createBottomNav() {
        HBox bottomNav = new HBox(0);
        bottomNav.setAlignment(Pos.CENTER);
        bottomNav.setPrefHeight(70);
        bottomNav.setStyle(
                "-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: #333; -fx-border-width: 0.5 0 0 0;");
        bottomNav.getChildren().addAll(
                createNavItem("Home", "\u2302", true, e -> showHome()),
                createNavItem("Explore", "\ud83e\udded", false, e -> {
                }),
                createNavItem("Saved", "\u2661", false, e -> {
                }),
                createNavItem("Profile", "\ud83d\udc64", false, e -> navigateTo(new LandlordDashboardView())));
        return bottomNav;
    }

    private VBox createNavItem(String label, String icon, boolean active,
            javafx.event.EventHandler<javafx.scene.input.MouseEvent> handler) {
        VBox item = new VBox(4);
        item.setAlignment(Pos.CENTER);
        item.setPrefWidth(80);
        item.setOnMouseClicked(handler);
        Label iconLbl = new Label(icon);
        iconLbl.setStyle("-fx-font-size: 24;");
        iconLbl.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        Label textLbl = new Label(label);
        textLbl.setFont(Font.font("System", 10));
        textLbl.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        item.getChildren().addAll(iconLbl, textLbl);
        return item;
    }

    private VBox createBanner(String title, String subtitle, String tag, String color) {
        VBox b = new VBox(5);
        b.setPrefSize(350, 150);
        b.setPadding(new Insets(20));
        b.setStyle("-fx-background-color: #1a1a1a; -fx-background-radius: 16;");
        Label t = new Label(tag);
        t.setStyle("-fx-background-color: " + color
                + "; -fx-text-fill: white; -fx-font-size: 10; -fx-padding: 2 8; -fx-background-radius: 4;");
        Label titleL = new Label(title);
        titleL.setTextFill(Color.WHITE);
        titleL.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label subL = new Label(subtitle);
        subL.setTextFill(Color.LIGHTGRAY);
        b.getChildren().addAll(t, titleL, subL);
        return b;
    }

    private VBox createCategory(String label, String icon, String bg, String color) {
        VBox c = new VBox(8);
        c.setAlignment(Pos.CENTER);
        StackPane iBox = new StackPane(new Label(icon));
        iBox.setPrefSize(55, 55);
        iBox.setStyle("-fx-background-color: " + bg + "; -fx-background-radius: 16;");
        ((Label) iBox.getChildren().get(0)).setStyle("-fx-font-size: 24; -fx-text-fill: " + color + ";");
        Label l = new Label(label);
        l.setTextFill(Color.WHITE);
        l.setFont(Font.font(10));
        c.getChildren().addAll(iBox, l);
        return c;
    }

    private VBox createPropertyCard(Property p) {
        VBox card = new VBox(0);
        card.getStyleClass().add("card");
        card.setPrefWidth(180);

        StackPane img = new StackPane(new Label("IMG"));
        img.setPrefHeight(180);
        img.setStyle("-fx-background-color: #333; -fx-background-radius: 12 12 0 0;");

        VBox details = new VBox(5);
        details.setPadding(new Insets(10));
        Label price = new Label(p.getPrice());
        price.setTextFill(Color.web(PRIMARY));
        price.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label name = new Label(p.getName());
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 13));
        Label loc = new Label(p.getLocation());
        loc.setTextFill(Color.GRAY);
        loc.setFont(Font.font(10));

        details.getChildren().addAll(price, name, loc);
        card.getChildren().addAll(img, details);
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
