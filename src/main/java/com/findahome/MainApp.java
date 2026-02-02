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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class MainApp extends Application {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String CARD_BG = "#1c271f";
    private static final String PRIMARY = "#13ec5b";
    private static final String TEXT_GRAY = "#9db9a6";

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

        // Create FAB
        HBox fabContent = new HBox(8);
        fabContent.setAlignment(Pos.CENTER);
        Label mapIcon = new Label("\ud83d\uddfa");
        mapIcon.setStyle("-fx-font-size: 20; -fx-text-fill: white;");
        Label mapLabel = new Label("Map");
        mapLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: white;");
        fabContent.getChildren().addAll(mapIcon, mapLabel);

        Button fab = new Button();
        fab.setGraphic(fabContent);
        fab.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 30; -fx-padding: 10 20;");
        fab.getStyleClass().add("fab");
        fab.setOnAction(e -> navigateToMap());
        StackPane.setAlignment(fab, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(fab, new Insets(0, 20, 100, 0)); // Above bottom nav

        StackPane rootContainer = new StackPane(root, fab);
        instance.mainLayout = root;
        instance.fab = fab;

        navigateToFullScreen(new OnboardingView());

        Scene scene = new Scene(rootContainer, 400, 800);
        String css = getClass().getResource("/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setTitle("FindaHome Marketplace");
        primaryStage.setScene(scene);
        ResizeHelper.addResizeListener(primaryStage);
        primaryStage.show();
    }

    private BorderPane mainLayout;
    private Button fab;

    public static void navigateTo(Node view) {
        if (instance.mainLayout.getTop() == null) {
            instance.mainLayout.setTop(instance.createTopNav());
            instance.mainLayout.setBottom(instance.createBottomNav());
            instance.fab.setVisible(true);
        }
        contentArea.getChildren().setAll(view);
    }

    public static void navigateToFullScreen(Node view) {
        instance.mainLayout.setTop(null);
        instance.mainLayout.setBottom(null);
        instance.fab.setVisible(false);
        contentArea.getChildren().setAll(view);
    }

    public static void navigateToMap() {
        instance.mainLayout.setTop(null);
        instance.mainLayout.setBottom(null);
        instance.fab.setVisible(false);
        contentArea.getChildren().setAll(new PropertyMapView());
    }

    public static void showHome() {
        navigateTo(new VBox()); // This will reset layout via navigateTo check
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
                        PRIMARY,
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuBPRDGJXyz0FGhKnwAEopQHuCE73ByJplEc8CkGObIKDa7WH-FEgr82AKWpmycA97S86qhrgYAjVgQXQ0kmSJmNfu5qhH_JfuaPQav8gtfVVw51i06Zi9wfZQkIVtyAC8joSe7u_hxCjzoU7ttfxdWviivlPgT7oHeWzRCosqjFpmlBaAlXYhiwJwwCObP0d2kBfSW00a-R3bplDsZh9OwserqkoHwA6sm4nn7mJiorNCpzgDdJ0kD2ma0vjVVV_Zxit32JSbJznqA"),
                createBanner("Eco-friendly Villas\nReady for Viewing", "Starting from $1,200/mo", "New Listing",
                        "#16a34a",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuCgxj92Y7Fw6BU7V0tKPzcdTbWN6mOyxQe9VoXbMIfzykZ0TNJnP-p9_owLU2rzzQUO46m11mNIJM6xoDAdnc9GKlKjqxYEDWf0kH_fbs7lgeMaF9oFpBy2NTwuE5GQD78rK-1TcPGsq09DU_8S5dEHfyquG48BUaZByghm9oiM3xl2KkeEakmEkT7XHpHjM3xl2KkeEakmEkT7XHpHjMmaEQdeTo-HFamh40L8S1BA1JvTlXCqAathTVzvuSGH2XM-jvi-qgRAlOHV8vLgcxboECwuVXTVZm8"));
        ScrollPane carouselScroll = new ScrollPane(carouselBox);
        carouselScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        carouselScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Categories
        HBox categoryBox = new HBox(15);
        categoryBox.setPadding(new Insets(10, 15, 10, 15));
        categoryBox.getChildren().addAll(
                createCategory("Apartment", "\ud83c\udfe2", "rgba(19, 236, 91, 0.1)", PRIMARY),
                createCategory("Villas", "\ud83c\udfe1", "rgba(249, 115, 22, 0.1)", "#f97316"),
                createCategory("Bedsitters", "\ud83d\udcbe", "rgba(168, 85, 247, 0.1)", "#a855f7"),
                createCategory("Offices", "\ud83d\udcbc", "rgba(6, 182, 212, 0.1)", "#06b6d4"),
                createCategory("Land", "\ud83c\udfde", "rgba(16, 185, 129, 0.1)", "#10b981"),
                createCategory("Warehouse", "\ud83c\udfed", "rgba(244, 63, 94, 0.1)", "#f43f5e"));
        ScrollPane categoryScroll = new ScrollPane(categoryBox);
        categoryScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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
                new Property("Luxury Studio Unit", "Westlands, Nairobi", "$450",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDislB9eDStdQfVeUXT-SyVYIktdYj4dn1rLs71l6k9U2PyNGYNNFrTSNc9vpmx-1nxZvV3C7xTOHKuL_z-JzyJlV_T9zSmkLpWqQELXWnwdeBWTC_gAwAsO4XuJ9XTTKaNGxd6KvkFkqfHdtlaykJTFfvzJjU7r5Dz5nFelagyTDehv6EwDvE3Dmm0Pv4IBvdDn6HaikyLJuu5BGtc6TRELsBd5pTZoYhKM13gtdCCDe07Kg4J7KzTaxaSxrK6staX7TwHfOMKMTQ",
                        true, null),
                new Property("2BR Modern Suite", "Lavington, NRB", "$800",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDG00XTZfB-0RJWAH5ntfeIWEm-D7rXH3ooNudSORqshuVmuRIan4DJ6jPvc3pF2YAPsG6AEonNw-voJD_x8cZNkhrpxWPKo1ERyiAfLBDCnYbaiIe5D5M8lwdZni-UGSzbpcW-J2fmZXtnHUsalrE0JeUXttj10aKkzqhBVU0hebAqF3XLpT9-7YiX8CrqB_Cpd5QxgSn5SAmO0CeYfHRdZzEAu3g-SDwTzSFCLpGgyAfazl9BvQDZt4hBfxOxSrfNiLXNgHPtBm0",
                        false, null),
                new Property("Executive 4BR Villa", "Karen, Nairobi", "$1,500",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDblUsX1Myvx0JUaD3Z9wugmUqDxFndrVcWjFJgiYU4vIiQ5oWLZhZpbuySB8TLHvOJDx7qkEAgmRcrFKxsxQenWHL4acGft7TQpKUzP_zElU92BlOzWRYEXdg-E6GUwA1UoSFfuJIHlEYkS1LFZRPiN6oJbKdploWq2a39H1TN_tFa6pesI3Jif03gH6nGku8sJ_HmeTChoO-yhS5bMfp8fZ-Ko4FPntOio9Xk9soN_u5ciSo_xBlSdoAkLRGyKgcQ4AJ58qgRHl0",
                        false, "Hot Deal"),
                new Property("Standard Bedsitter", "Ruiru, Bypass", "$180",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuAe5msutXOEb9y9l09DYhDJqPMIKapWT6Ih_RTOyAIEBM6BhE0MceARFLMOYifKcjgmedN0ZWC3LS6XTCNhvi3flJI1SDcu6b_nEPnitzWIBckUrKrFe_QJgyeeuw8YAao2ixslWTFgRuiH7JQFpQxoR2cUiaHhEBLNy_K-42jS11uRVBsuTZygpX23GrwoGhaeVmS1wacUsvEEKew9nNypSSIp2xr7RZoFzXDTmn0tt9T-LlgWZ0QMm2Nslrv503mJU6A689WZYdo",
                        false, null));

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
        actions.setAlignment(Pos.CENTER_RIGHT);

        StackPane bellStack = new StackPane();
        bellStack.setCursor(javafx.scene.Cursor.HAND);
        Label bell = new Label("\ud83d\udd14");
        bell.setTextFill(Color.WHITE);
        bell.setStyle("-fx-font-size: 22;");
        bell.setOnMouseClicked(e -> navigateTo(new NotificationView()));

        Circle dot = new Circle(4, Color.RED);
        StackPane.setAlignment(dot, Pos.TOP_RIGHT);
        StackPane.setMargin(dot, new Insets(2, 2, 0, 0));
        bellStack.getChildren().addAll(bell, dot);

        Label cart = new Label("\ud83d\uded2");
        cart.setCursor(javafx.scene.Cursor.HAND);
        cart.setTextFill(Color.WHITE);
        cart.setStyle("-fx-font-size: 22;");

        actions.getChildren().addAll(bellStack, cart);

        header.getChildren().addAll(logo, spacer, actions);

        HBox searchContainer = new HBox(10);
        searchContainer.setAlignment(Pos.CENTER_LEFT);

        StackPane searchFieldStack = new StackPane();
        HBox.setHgrow(searchFieldStack, Priority.ALWAYS);

        TextField search = new TextField();
        search.setPromptText("Search apartments, villas...");
        search.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-text-fill: white; -fx-background-radius: 12; -fx-padding: 10 10 10 35; -fx-font-size: 13;");
        search.setPrefHeight(44);
        search.setOnAction(e -> navigateToMap());

        Label searchIcon = new Label("\ud83d\udd0d");
        searchIcon.setTextFill(Color.web("#9ca3af"));
        searchIcon.setPadding(new Insets(0, 0, 0, 10));
        StackPane.setAlignment(searchIcon, Pos.CENTER_LEFT);

        searchFieldStack.getChildren().addAll(search, searchIcon);

        Button filterBtn = new Button("\u2312"); // Settings/Filter icon
        filterBtn.setStyle("-fx-background-color: rgba(19, 91, 236, 0.1); -fx-text-fill: " + PRIMARY
                + "; -fx-background-radius: 12; -fx-min-width: 44; -fx-min-height: 44; -fx-font-size: 18;");
        filterBtn.setOnAction(e -> navigateTo(new FilterView()));

        searchContainer.getChildren().addAll(searchFieldStack, filterBtn);
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
                createNavItem("Explore", "\ud83e\udded", false, e -> navigateToMap()),
                createNavItem("Service", "\ud83d\udee0", false, e -> navigateTo(new MaintenanceRequestsListView())),
                createNavItem("Messages", "\ud83d\udcac", false, e -> navigateTo(new ChatView())),
                createNavItem("Profile", "\ud83d\udc64", false, e -> navigateTo(new TenantProfileView())));
        return bottomNav;
    }

    private VBox createNavItem(String label, String icon, boolean active,
            javafx.event.EventHandler<javafx.scene.input.MouseEvent> handler) {
        VBox item = new VBox(4);
        item.setAlignment(Pos.CENTER);
        item.setPrefWidth(75);
        item.getStyleClass().add("nav-item");
        item.setCursor(javafx.scene.Cursor.HAND);
        item.setOnMouseClicked(handler);
        Label iconLbl = new Label(icon);
        iconLbl.setStyle("-fx-font-size: 22;");
        iconLbl.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        Label textLbl = new Label(label);
        textLbl.setFont(Font.font("System", FontWeight.MEDIUM, 10));
        textLbl.setTextFill(active ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
        item.getChildren().addAll(iconLbl, textLbl);
        return item;
    }

    private StackPane createBanner(String title, String subtitle, String tag, String color, String imgUrl) {
        StackPane banner = new StackPane();
        banner.setPrefSize(350, 153); // Match aspect ratio 21/9 for 350 width is roughly 150
        banner.getStyleClass().add("banner");

        ImageView bgImg = new ImageView();
        try {
            Image img = new Image(imgUrl, 350, 153, false, true);
            bgImg.setImage(img);
        } catch (Exception e) {
        }

        Rectangle clip = new Rectangle(350, 153);
        clip.setArcWidth(32);
        clip.setArcHeight(32);
        banner.setClip(clip);

        // Background Image (Mockup with colored overlay)
        Region bgOverlay = new Region();
        bgOverlay.setStyle("-fx-background-color: linear-gradient(to right, rgba(0,0,0,0.7), transparent);");

        VBox content = new VBox(5);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER_LEFT);
        Label t = new Label(tag);
        t.setStyle("-fx-background-color: " + color
                + "; -fx-text-fill: white; -fx-font-size: 10; -fx-font-weight: bold; -fx-padding: 3 8; -fx-background-radius: 4;");
        Label titleL = new Label(title);
        titleL.setTextFill(Color.WHITE);
        titleL.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleL.setWrapText(true);
        Label subL = new Label(subtitle);
        subL.setTextFill(Color.web("#e5e7eb"));
        subL.setFont(Font.font(12));
        content.getChildren().addAll(t, titleL, subL);

        banner.getChildren().addAll(bgImg, bgOverlay, content);
        return banner;
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
        card.setCursor(javafx.scene.Cursor.HAND);

        StackPane imgContainer = new StackPane();
        imgContainer.setPrefHeight(180);

        ImageView iv = new ImageView();
        try {
            Image img = new Image(p.getImageUrl(), 180, 180, false, true);
            iv.setImage(img);
        } catch (Exception e) {
        }

        Rectangle clip = new Rectangle(180, 180);
        clip.setArcWidth(24);
        clip.setArcHeight(24);
        // Only clip top corners for the container
        // Wait, better to clip the whole card or just the image
        iv.setClip(clip);

        imgContainer.getChildren().add(iv);

        // Heart Button
        Label heart = new Label("\u2661");
        heart.setTextFill(Color.WHITE);
        heart.setStyle(
                "-fx-background-color: rgba(0,0,0,0.4); -fx-background-radius: 20; -fx-padding: 6; -fx-font-size: 14;");
        StackPane.setAlignment(heart, Pos.TOP_RIGHT);
        StackPane.setMargin(heart, new Insets(8));

        // Tags
        if (p.isVerified()) {
            Label verified = new Label("Verified");
            verified.setStyle(
                    "-fx-background-color: rgba(0,0,0,0.7); -fx-text-fill: white; -fx-font-size: 10; -fx-padding: 2 8; -fx-background-radius: 4; -fx-font-weight: bold;");
            StackPane.setAlignment(verified, Pos.BOTTOM_LEFT);
            StackPane.setMargin(verified, new Insets(8));
            imgContainer.getChildren().add(verified);
        } else if (p.getTag() != null) {
            Label tag = new Label(p.getTag());
            tag.setStyle("-fx-background-color: " + PRIMARY
                    + "; -fx-text-fill: white; -fx-font-size: 10; -fx-padding: 2 8; -fx-background-radius: 4; -fx-font-weight: bold;");
            StackPane.setAlignment(tag, Pos.BOTTOM_LEFT);
            StackPane.setMargin(tag, new Insets(8));
            imgContainer.getChildren().add(tag);
        }

        imgContainer.getChildren().add(heart);

        VBox details = new VBox(2);
        details.setPadding(new Insets(10, 12, 12, 12));
        HBox priceBox = new HBox(2);
        priceBox.setAlignment(Pos.BASELINE_LEFT);
        Label price = new Label(p.getPrice());
        price.setTextFill(Color.web(PRIMARY));
        price.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label perMo = new Label("/mo");
        perMo.setTextFill(Color.GRAY);
        perMo.setFont(Font.font(10));
        priceBox.getChildren().addAll(price, perMo);

        Label name = new Label(p.getName());
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 13));

        HBox locBox = new HBox(4);
        locBox.setAlignment(Pos.CENTER_LEFT);
        Label locIcon = new Label("\ud83d\udccd");
        locIcon.setStyle("-fx-font-size: 10;");
        locIcon.setTextFill(Color.GRAY);
        Label loc = new Label(p.getLocation());
        loc.setTextFill(Color.GRAY);
        loc.setFont(Font.font(10));
        locBox.getChildren().addAll(locIcon, loc);

        HBox statsBox = new HBox(10);
        statsBox.setAlignment(Pos.CENTER_LEFT);
        statsBox.setPadding(new Insets(5, 0, 0, 0));
        statsBox.getChildren().addAll(
                createMiniStat("\ud83d\udecf", p.getBeds()),
                createMiniStat("\ud83d\udebf", p.getBaths()));

        details.getChildren().addAll(priceBox, name, locBox, statsBox);
        card.getChildren().addAll(imgContainer, details);
        return card;
    }

    private HBox createMiniStat(String icon, String text) {
        HBox box = new HBox(3);
        box.setAlignment(Pos.CENTER_LEFT);
        Label i = new Label(icon);
        i.setStyle("-fx-font-size: 10;");
        i.setTextFill(Color.GRAY);
        Label t = new Label(text);
        t.setFont(Font.font(9));
        t.setTextFill(Color.GRAY);
        box.getChildren().addAll(i, t);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
