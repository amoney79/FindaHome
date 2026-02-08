package com.findahome;

import javafx.animation.FadeTransition;
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
import javafx.util.Duration;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class MainApp extends Application {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String CARD_BG = "#1c222c";
    private static final String PRIMARY = "#135bec";
    private static final String TEXT_GRAY = "#9ca3af";

    private static StackPane contentArea;
    private static MainApp instance;
    private static final Map<String, Node> viewCache = new HashMap<>();
    private static PropertyFeedView feedInstance;
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
        this.topNav = createTopNav();
        this.bottomNav = createBottomNav();
        root.setTop(topNav);
        root.setCenter(contentArea);
        root.setBottom(bottomNav);

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

        // Pre-load expensive views in background or immediately
        // Here we do it immediately on FX thread, but since image is background loaded,
        // it's fine.
        viewCache.put("explore", new PropertyMapView());

        primaryStage.show();
    }

    private BorderPane mainLayout;
    private Button fab;
    private VBox topNav;
    private Node bottomNav;

    public static void navigateTo(Node view) {
        applyTransition(view, false);
    }

    public static void navigateToFullScreen(Node view) {
        applyTransition(view, true);
    }

    private static void applyTransition(Node view, boolean fullScreen) {
        if (view instanceof PropertyMapView) {
            // Special case for Map: Fullscreen feel but with Bottom Nav
            instance.mainLayout.setTop(null);
            instance.mainLayout.setBottom(instance.bottomNav);
            instance.bottomNav.setVisible(true); // Ensure visibility
            instance.fab.setVisible(false); // Map has its own FABs
        } else if (fullScreen) {
            instance.mainLayout.setTop(null);
            instance.mainLayout.setBottom(null);
            instance.fab.setVisible(false);
        } else {
            if (instance.mainLayout.getTop() == null) {
                instance.mainLayout.setTop(instance.topNav);
                instance.mainLayout.setBottom(instance.bottomNav);
                instance.fab.setVisible(true);
            }
        }

        // SMOOTH CROSS-FADE
        Node oldView = contentArea.getChildren().isEmpty() ? null : contentArea.getChildren().get(0);

        view.setOpacity(0);
        contentArea.getChildren().add(view);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(10), view);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        if (oldView != null) {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(10), oldView);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> contentArea.getChildren().remove(oldView));
            fadeOut.play();
        }

        fadeIn.play();
        updateBottomNavStyles(view);
    }

    private static void updateBottomNavStyles(Node currentView) {
        if (instance.bottomNav instanceof HBox) {
            HBox nav = (HBox) instance.bottomNav;
            for (Node n : nav.getChildren()) {
                if (n instanceof VBox) {
                    VBox item = (VBox) n;
                    Label label = (Label) item.getChildren().get(1);
                    boolean isMatch = currentView.getClass().getSimpleName().contains(label.getText());
                    // Special case for Home
                    if (label.getText().equals("Home")
                            && (currentView instanceof ScrollPane || currentView instanceof PropertyFeedView))
                        isMatch = true;

                    String color = isMatch ? PRIMARY : TEXT_GRAY;
                    ((Label) item.getChildren().get(0)).setTextFill(Color.web(color));
                    label.setTextFill(Color.web(color));
                }
            }
        }
    }

    public static void navigateToMap() {
        navigateCached("explore", PropertyMapView::new);
    }

    public static void showHome() {
        if (viewCache.containsKey("dashboard")) {
            navigateTo(viewCache.get("dashboard"));
        } else {
            instance.showDashboard();
        }
    }

    public void showDashboard() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(10, 0, 100, 0));

        // Carousel (Banners)
        HBox carouselBox = new HBox(15);
        carouselBox.setPadding(new Insets(0, 15, 0, 15));

        StackPane banner1 = createBanner("10% Off Your First\nMonth's Rent", "Limited time offer in Kilimani",
                "Featured", PRIMARY,
                "https://lh3.googleusercontent.com/aida-public/AB6AXuBPRDGJXyz0FGhKnwAEopQHuCE73ByJplEc8CkGObIKDa7WH-FEgr82AKWpmycA97S86qhrgYAjVgQXQ0kmSJmNfu5qhH_JfuaPQav8gtfVVw51i06Zi9wfZQkIVtyAC8joSe7u_hxCjzoU7ttfxdWviivlPgT7oHeWzRCosqjFpmlBaAlXYhiwJwwCObP0d2kBfSW00a-R3bplDsZh9OwserqkoHwA6sm4nn7mJiorNCpzgDdJ0kD2ma0vjVVV_Zxit32JSbJznqA");
        StackPane banner2 = createBanner("Neighborhood Guides", "Safety & Lifestyle Insights", "Guides", "#3b82f6",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuB0HULlfInpu8RXSl3WrY0lOui3drjyipY7A0xgLsHPqDHZ-qj6PeDIRifJlSdQUCr85V0S-Ip1N7HZwByBqb5SSzFgCWXlr_yKJ9cIUm-4SbLkDOzGdmwlRIV9pCuEitLj8Tp8857z-CZDpcrEfx9dx2Y1ovb6Vzwy6FDdXtL1vfYyDdY2tVGXPd8KN4wssNshZpwINYIw9tf0b1xQdW2ZJv33wClmp_dzf39qrKZiFxu9HWpuU0PNoxH1oLknN5EqAFJKCKXR_mI");
        StackPane banner3 = createBanner("Moving Made Easy", "Organize your relocation", "Tools", "#f97316",
                "https://lh3.googleusercontent.com/aida-public/AB6AXuB7aVUx18rjxzcsXZcZ7ohoAoLnOwRzZp0fyHFLN75BFx968Wk5JBeJrEGmg-aXFTDz5BsQK_Hl80q2ym93mUvqWk57KxEayDkgsIVKuH6IXoCm7IWelg8hfuu58FkRXNMNYjN7d5W9MX2UxmmdZCuZO5lrgDe07cc-nL8_TZr31__SOZubHuNglIkU2KQJsjdxlYV1zg5jDcRvu7onde-9nhv2p_UQQFuNgo-wLqkZLy6SAtHZQUf6lesl8GZ2sz7fTUJ2gRN8p8U");

        banner2.setCursor(javafx.scene.Cursor.HAND);
        banner2.setOnMouseClicked(e -> navigateToFullScreen(new GuideView()));
        banner3.setCursor(javafx.scene.Cursor.HAND);
        banner3.setOnMouseClicked(e -> navigateToFullScreen(new MovingChecklistView()));

        carouselBox.getChildren().addAll(banner1, banner2, banner3);
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
                createCategory("More", "\u22ee", "rgba(255, 255, 255, 0.1)", "white"));
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

        // Recommended Items Section
        feedInstance = new PropertyFeedView();
        mainContent.getChildren().add(feedInstance);

        mainContent.getChildren().addAll(carouselScroll, categoryScroll);

        // Note: PropertyFeedView already handles its own Recommended header and list.
        // We just need to make sure the main content is laid out well.
        mainContent.getChildren().clear();
        mainContent.getChildren().addAll(carouselScroll, categoryScroll, feedInstance);

        ScrollPane mainScroll = new ScrollPane(mainContent);
        mainScroll.setFitToWidth(true);
        mainScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Infinite Scroll Listener
        mainScroll.vvalueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 0.95) {
                feedInstance.loadMoreProperties();
            }
        });

        viewCache.put("dashboard", mainScroll);
        navigateTo(mainScroll);
    }

    public static <T extends Node> void navigateCached(String key, java.util.function.Supplier<T> creator) {
        Node view = viewCache.get(key);
        if (view == null) {
            view = creator.get();
            viewCache.put(key, view);
        }
        navigateTo(view);
    }

    public static <T extends Node> void navigateCachedFullScreen(String key, java.util.function.Supplier<T> creator) {
        Node view = viewCache.get(key);
        if (view == null) {
            view = creator.get();
            viewCache.put(key, view);
        }
        navigateToFullScreen(view);
    }

    public static <T extends Node> Node getCachedView(String key, java.util.function.Supplier<T> creator) {
        Node view = viewCache.get(key);
        if (view == null) {
            view = creator.get();
            viewCache.put(key, view);
        }
        return view;
    }

    public static PropertyFeedView getFeed() {
        return feedInstance;
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
        search.textProperty().addListener((obs, oldVal, newVal) -> {
            if (feedInstance != null) {
                feedInstance.refresh(PropertyData.filter("All Types", newVal, 0, 1000000));
            }
        });
        search.setOnAction(e -> {
            if (feedInstance == null)
                navigateToMap();
        });

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
                "-fx-background-color: " + BACKGROUND_DARK
                        + "; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0;");
        bottomNav.getChildren().addAll(
                createNavItem("Home", "\u2302", true, e -> showHome()),
                createNavItem("Explore", "\ud83e\udded", false, e -> navigateToMap()),
                createNavItem("Service", "\ud83d\udee0", false, e -> navigateCached("service", ServiceHubView::new)),
                createNavItem("Messages", "\ud83d\udcac", false, e -> navigateCached("messages", ChatView::new)),
                createNavItem("Profile", "\ud83d\udc64", false,
                        e -> navigateCached("profile", TenantProfileView::new)));
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
            // Enable background loading (6th param) for smoother transitions
            Image img = new Image(imgUrl, 350, 153, false, true, true);
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
        c.setCursor(javafx.scene.Cursor.HAND);
        c.setOnMouseClicked(e -> {
            if (feedInstance != null) {
                if (label.equals("More")) {
                    navigateTo(new FilterView());
                } else {
                    String type = label.equals("Villas") ? "Villa" : label.equals("Bedsitters") ? "Studio" : label;
                    feedInstance.refresh(PropertyData.filter(type, "", 0, 1000000));
                }
            }
        });

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

    public static void main(String[] args) {
        launch(args);
    }
}
