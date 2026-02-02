package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ServiceHubView extends StackPane {

    private static final String BACKGROUND_DARK = "#0f172a";
    private static final String PRIMARY = "#13ec5b";
    private static final String TEXT_GRAY = "#94a3b8";
    private static final String CARD_BG = "#1e293b";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";

    public ServiceHubView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        VBox header = new VBox(15);
        header.setPadding(new Insets(50, 20, 20, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK + "cc; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 0 0 1 0;");

        Label pageTitle = new Label("Services & Tools");
        pageTitle.setTextFill(Color.WHITE);
        pageTitle.setFont(Font.font("System", FontWeight.BOLD, 32));

        Label pageSubtitle = new Label("Everything you need for your home journey");
        pageSubtitle.setTextFill(Color.web(TEXT_GRAY));
        pageSubtitle.setFont(Font.font(14));

        header.getChildren().addAll(pageTitle, pageSubtitle);

        // Content
        VBox scrollContent = new VBox(25);
        scrollContent.setPadding(new Insets(20));
        scrollContent.setAlignment(Pos.TOP_CENTER);

        // 1. Moving & Settling Section
        scrollContent.getChildren().add(createSectionTitle("Moving & Settling"));
        GridPane movingGrid = new GridPane();
        movingGrid.setHgap(15);
        movingGrid.setVgap(15);
        movingGrid.add(createServiceCard("Moving Checklist", "\u2713", "#f46a25",
                e -> MainApp.navigateToFullScreen(new MovingChecklistView())), 0, 0);
        movingGrid.add(createServiceCard("Movers Quotes", "\ud83d\ude9a", "#3b82f6",
                e -> MainApp.navigateToFullScreen(new MoversQuoteRequestView())), 1, 0);
        movingGrid.add(createServiceCard("Neighborhood Guide", "\ud83d\uddfa", "#8b5cf6",
                e -> MainApp.navigateToFullScreen(new GuideView())), 0, 1);
        movingGrid.add(createServiceCard("Market Trends", "\ud83d\udcc8", "#ec4899",
                e -> MainApp.navigateToFullScreen(new MarketTrendsView())), 1, 1);
        scrollContent.getChildren().add(movingGrid);

        // 2. Home Management
        scrollContent.getChildren().add(createSectionTitle("Home Management"));
        GridPane homeGrid = new GridPane();
        homeGrid.setHgap(15);
        homeGrid.setVgap(15);
        homeGrid.add(createServiceCard("Maintenance", "\ud83d\udee0", PRIMARY,
                e -> MainApp.navigateTo(new MaintenanceRequestsListView())), 0, 0);
        homeGrid.add(createServiceCard("Lease Agreements", "\ud83d\udcc4", "#eab308",
                e -> MainApp.navigateToFullScreen(new LeaseAgreementView())), 1, 0);
        homeGrid.add(createServiceCard("Neighborhood Alerts", "\ud83d\udd14", "#f43f5e",
                e -> MainApp.navigateToFullScreen(new NeighborhoodAlertsView())), 0, 1);
        homeGrid.add(createServiceCard("Payment History", "\ud83d\udcb3", "#10b981",
                e -> MainApp.navigateToFullScreen(new PaymentHistoryView())), 1, 1);
        scrollContent.getChildren().add(homeGrid);

        // 3. Discovery Tools
        scrollContent.getChildren().add(createSectionTitle("Discovery Tools"));
        VBox discoveryList = new VBox(12);
        discoveryList.getChildren().addAll(
                createWideServiceCard("Commute Calculator", "\ud83d\ude97", "Calculate travel time to work/school",
                        e -> MainApp.navigateToFullScreen(new CommuteCalculatorView())),
                createWideServiceCard("County Discovery", "\ud83c\udfde", "Explore different counties in Kenya",
                        e -> MainApp.navigateToFullScreen(new CountyDiscoveryView())));
        scrollContent.getChildren().add(discoveryList);

        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        layout.getChildren().addAll(header, scroll);
        getChildren().add(layout);
    }

    private Label createSectionTitle(String title) {
        Label l = new Label(title.toUpperCase());
        l.setTextFill(Color.web(PRIMARY));
        l.setFont(Font.font("System", FontWeight.BOLD, 12));
        l.setPadding(new Insets(10, 0, 5, 0));
        return l;
    }

    private VBox createServiceCard(String title, String icon, String color,
            javafx.event.EventHandler<javafx.scene.input.MouseEvent> handler) {
        VBox card = new VBox(12);
        card.setPadding(new Insets(20));
        card.setAlignment(Pos.CENTER);
        card.setPrefWidth(170);
        card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 20; -fx-border-color: "
                + BORDER_COLOR + "; -fx-cursor: hand;");
        card.setOnMouseClicked(handler);

        StackPane iconBox = new StackPane(new Label(icon));
        iconBox.setPrefSize(50, 50);
        iconBox.setStyle("-fx-background-color: " + color + "22; -fx-background-radius: 15;");
        ((Label) iconBox.getChildren().get(0)).setStyle("-fx-font-size: 24; -fx-text-fill: " + color + ";");

        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 14));
        t.setWrapText(true);
        t.setAlignment(Pos.CENTER);

        card.getChildren().addAll(iconBox, t);
        return card;
    }

    private HBox createWideServiceCard(String title, String icon, String desc,
            javafx.event.EventHandler<javafx.scene.input.MouseEvent> handler) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-border-color: "
                + BORDER_COLOR + "; -fx-cursor: hand;");
        card.setOnMouseClicked(handler);

        Label i = new Label(icon);
        i.setStyle("-fx-font-size: 24;");
        i.setPrefSize(40, 40);
        i.setAlignment(Pos.CENTER);

        VBox text = new VBox(2);
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 15));
        Label d = new Label(desc);
        d.setTextFill(Color.web(TEXT_GRAY));
        d.setFont(Font.font(12));
        text.getChildren().addAll(t, d);

        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);
        Label arrow = new Label("\u203a");
        arrow.setTextFill(Color.web(TEXT_GRAY));
        arrow.setStyle("-fx-font-size: 24;");

        card.getChildren().addAll(i, text, s, arrow);
        return card;
    }
}
