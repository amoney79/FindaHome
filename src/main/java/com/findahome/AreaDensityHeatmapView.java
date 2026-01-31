package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle; // Added Circle import
import javafx.scene.shape.Rectangle; // Added Rectangle import
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AreaDensityHeatmapView extends StackPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String PRIMARY = "#13ec5b"; // Updated to green theme
    private static final String TEXT_GRAY = "#9da6b9";
    private static final String GLASS_BG = "rgba(16, 22, 34, 0.8)";
    private static final String BORDER_WHITE_10 = "rgba(255,255,255,0.1)";

    public AreaDensityHeatmapView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Map Layer
        StackPane mapLayer = new StackPane();
        try {
            ImageView mapImg = new ImageView(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuB40XT75MK1VHCOIh7MNeHPv9p9rDTCMSDg-ZSKymvx9w41OUh9o9n1E3P4BqCYUb0Lwg5Lgqm__c2rRHViDbfniEuBJp9MipllX5WIZzOYCJCExWt7xL-2wO9m-U7MLIxgqKzhvqEx2KnWG4AoPDN1UNwiAolvNrNQnTCyCoyI1sz88fFv5bNuqk5D6a0HHrE3BR4puNZZxh6WVcDcLKWSIVJhA54xik0FW21Lqhs4U3la3ETEYyynELmydP38OJuJi2A-ZZygSg4",
                    500, 1000, true, true));
            mapImg.setOpacity(0.4); // Darker map
            mapLayer.getChildren().add(mapImg);
        } catch (Exception e) {
        }

        // Heatmap Spots (Simulated with Circles and Gradients)
        Pane heatmapLayer = new Pane();
        heatmapLayer.setOpacity(0.6);

        Circle heat1 = new Circle(120, 200, 100);
        heat1.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web(PRIMARY, 0.4)),
                new Stop(1, Color.TRANSPARENT)));

        Circle heat2 = new Circle(280, 400, 80);
        heat2.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#4facfe", 0.4)),
                new Stop(1, Color.TRANSPARENT)));

        heatmapLayer.getChildren().addAll(heat1, heat2);
        mapLayer.getChildren().add(heatmapLayer);

        // Labels Layer
        Pane labelsLayer = new Pane();
        labelsLayer.getChildren().add(createMapLabel(180, 300, "Kileleshwa", true));
        labelsLayer.getChildren().add(createMapLabel(80, 200, "Westlands", false));
        labelsLayer.getChildren().add(createMapLabel(280, 500, "Roysambu", true));
        mapLayer.getChildren().add(labelsLayer);

        // UI Layer
        BorderPane uiLayer = new BorderPane();
        uiLayer.setPickOnBounds(false);

        // Top Bar
        VBox topContainer = new VBox(15);
        topContainer.setPadding(new Insets(40, 16, 20, 16)); // Top padding for status bar
        topContainer.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(16,22,34,0.9), transparent);");

        // Header Row
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);

        Button backBtn = createGlassIconBtn("\u2039"); // Back chevron
        backBtn.setOnAction(e -> MainApp.navigateTo(new WardMapView())); // Go back to boundary map

        VBox titleBox = new VBox(2);
        titleBox.setAlignment(Pos.CENTER);
        Label t1 = new Label("Nairobi Density");
        t1.setTextFill(Color.WHITE);
        t1.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label t2 = new Label("Heatmap View");
        t2.setTextFill(Color.web(TEXT_GRAY));
        t2.setFont(Font.font(12));
        titleBox.getChildren().addAll(t1, t2);
        HBox.setHgrow(titleBox, Priority.ALWAYS);

        Button layersBtn = createGlassIconBtn("\u2630"); // Layers icon simulation

        header.getChildren().addAll(backBtn, titleBox, layersBtn);

        // Search Bar
        HBox searchBar = new HBox(10);
        searchBar.setAlignment(Pos.CENTER_LEFT);
        searchBar.setPadding(new Insets(0, 15, 0, 15));
        searchBar.setPrefHeight(48);
        searchBar.setStyle("-fx-background-color: " + GLASS_BG + "; -fx-background-radius: 12; -fx-border-color: "
                + BORDER_WHITE_10
                + "; -fx-border-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);");

        Label searchIcon = new Label("\ud83d\udd0d");
        searchIcon.setTextFill(Color.web("white", 0.6));
        TextField searchInput = new TextField();
        searchInput.setPromptText("Search Ward (e.g. Kileleshwa)");
        searchInput.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: rgba(255,255,255,0.4);");
        HBox.setHgrow(searchInput, Priority.ALWAYS);
        Label micIcon = new Label("\ud83c\udfa4");
        micIcon.setTextFill(Color.web("white", 0.4));

        searchBar.getChildren().addAll(searchIcon, searchInput, micIcon);

        topContainer.getChildren().addAll(header, searchBar);
        uiLayer.setTop(topContainer);

        // Center Layer (Floaters)
        AnchorPane centerLayer = new AnchorPane();
        centerLayer.setPickOnBounds(false);

        // Legend
        VBox legend = new VBox(5);
        legend.setAlignment(Pos.CENTER);
        legend.setPadding(new Insets(10, 6, 10, 6));
        legend.setStyle("-fx-background-color: " + GLASS_BG + "; -fx-background-radius: 20; -fx-border-color: "
                + BORDER_WHITE_10 + "; -fx-border-radius: 20;");

        Label lHigh = new Label("HIGH");
        lHigh.setTextFill(Color.web("white", 0.6));
        lHigh.setFont(Font.font(7));
        Rectangle gradBar = new Rectangle(6, 100);
        gradBar.setFill(new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web(PRIMARY)),
                new Stop(1, Color.web("#4facfe"))));
        gradBar.setArcWidth(6);
        gradBar.setArcHeight(6);
        Label lLow = new Label("LOW");
        lLow.setTextFill(Color.web("white", 0.6));
        lLow.setFont(Font.font(7));

        legend.getChildren().addAll(lHigh, gradBar, lLow);
        AnchorPane.setLeftAnchor(legend, 16.0);
        AnchorPane.setTopAnchor(legend, 150.0); // Offset from top

        // Map Controls
        VBox controls = new VBox(8);
        Button zoomIn = createGlassIconBtn("+");
        Button zoomOut = createGlassIconBtn("-");
        Button locate = createGlassIconBtn("\u2316");
        locate.setStyle(locate.getStyle() + "-fx-text-fill: " + PRIMARY + ";");
        controls.getChildren().addAll(zoomIn, zoomOut, locate);
        AnchorPane.setRightAnchor(controls, 16.0);
        AnchorPane.setTopAnchor(controls, 150.0);

        centerLayer.getChildren().addAll(legend, controls);
        uiLayer.setCenter(centerLayer);

        // Bottom Sheet
        VBox bottomSheet = new VBox(0);
        bottomSheet.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-background-radius: 24 24 0 0; -fx-border-color: " + BORDER_WHITE_10
                + "; -fx-border-width: 1 0 0 0; -fx-border-radius: 24 24 0 0; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 0, -5, 0, 0);");
        bottomSheet.setMaxHeight(400); // Constraint height

        // Handle
        HBox handleBox = new HBox();
        handleBox.setAlignment(Pos.CENTER);
        handleBox.setPadding(new Insets(12));
        Rectangle handle = new Rectangle(40, 4, Color.web("white", 0.2));
        handle.setArcWidth(4);
        handle.setArcHeight(4);
        handleBox.getChildren().add(handle);

        // Content
        VBox content = new VBox(20);
        content.setPadding(new Insets(0, 24, 24, 24));

        // Filter Header
        HBox filterHead = new HBox();
        Label fhTitle = new Label("Density Filters");
        fhTitle.setTextFill(Color.WHITE);
        fhTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);
        Label reset = new Label("Reset");
        reset.setTextFill(Color.web(PRIMARY));
        reset.setFont(Font.font("System", FontWeight.BOLD, 12));
        filterHead.getChildren().addAll(fhTitle, sp, reset);

        // Chips
        HBox chips = new HBox(8);
        chips.getChildren().addAll(createFilterChip("Sub-Counties", true), createFilterChip("Ward Level", false),
                createFilterChip("Apartments", false));

        // Sliders
        VBox sliders = new VBox(20);
        sliders.getChildren().add(createSliderRow("Distance to CBD", "Under 12km", 0.6));
        sliders.getChildren().add(createSliderRow("Proximity to Thika Road", "Any", 0.0));

        // Stats Box
        HBox statsBox = new HBox(15);
        statsBox.setAlignment(Pos.CENTER_LEFT);
        statsBox.setPadding(new Insets(15));
        statsBox.setStyle(
                "-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 16; -fx-border-color: rgba(19, 236, 91, 0.2); -fx-border-radius: 16;");

        StackPane statIcon = new StackPane();
        statIcon.setPrefSize(40, 40);
        statIcon.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 10;");
        Label icon = new Label("\ud83d\udcca"); // Chart icon
        icon.setTextFill(Color.WHITE);
        statIcon.getChildren().add(icon);

        VBox statText = new VBox(2);
        Label st1 = new Label("ACTIVE LISTINGS");
        st1.setTextFill(Color.web(PRIMARY));
        st1.setFont(Font.font("System", FontWeight.BOLD, 10));
        Label st2 = new Label("12,482 Properties matched");
        st2.setTextFill(Color.WHITE);
        st2.setFont(Font.font("System", FontWeight.BOLD, 16));
        statText.getChildren().addAll(st1, st2);

        statsBox.getChildren().addAll(statIcon, statText);

        content.getChildren().addAll(filterHead, chips, sliders, statsBox);
        bottomSheet.getChildren().addAll(handleBox, content);

        uiLayer.setBottom(bottomSheet);

        getChildren().addAll(mapLayer, uiLayer);
    }

    private Button createGlassIconBtn(String text) {
        Button b = new Button(text);
        b.setPrefSize(40, 40);
        b.setStyle("-fx-background-color: " + GLASS_BG
                + "; -fx-text-fill: white; -fx-font-size: 18; -fx-background-radius: 20; -fx-border-color: "
                + BORDER_WHITE_10 + "; -fx-border-radius: 20; -fx-cursor: hand;");
        return b;
    }

    private VBox createMapLabel(double x, double y, String text, boolean primary) {
        VBox box = new VBox(2);
        box.setAlignment(Pos.CENTER);

        Label lbl = new Label(text);
        lbl.setStyle("-fx-background-color: " + (primary ? PRIMARY : "rgba(255,255,255,0.1)")
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10; -fx-padding: 2 6; -fx-background-radius: 4; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 3, 0, 0, 1);");

        Circle dot = new Circle(4);
        dot.setFill(primary ? Color.web(PRIMARY) : Color.web("#4facfe"));
        dot.setStroke(Color.WHITE);
        dot.setStrokeWidth(1);

        box.getChildren().addAll(lbl, dot);
        box.setTranslateX(x);
        box.setTranslateY(y);
        return box;
    }

    private Button createFilterChip(String text, boolean active) {
        Button b = new Button(text);
        if (active) {
            b.setStyle("-fx-background-color: " + PRIMARY
                    + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15; -fx-font-size: 11;");
        } else {
            b.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: rgba(255,255,255,0.8); -fx-background-radius: 15; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 15; -fx-font-size: 11;");
        }
        return b;
    }

    private VBox createSliderRow(String label, String value, double progress) {
        VBox row = new VBox(8);

        HBox top = new HBox();
        Label l = new Label(label);
        l.setTextFill(Color.web("white", 0.9));
        l.setFont(Font.font(12));
        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);
        Label v = new Label(value);
        v.setTextFill(Color.web(PRIMARY));
        v.setFont(Font.font("System", FontWeight.BOLD, 12));
        top.getChildren().addAll(l, s, v);

        ProgressBar pb = new ProgressBar(progress);
        pb.setMaxWidth(Double.MAX_VALUE);
        pb.setPrefHeight(6);
        pb.setStyle("-fx-accent: " + PRIMARY
                + "; -fx-control-inner-background: rgba(255,255,255,0.1); -fx-text-box-border: transparent;");

        row.getChildren().addAll(top, pb);
        return row;
    }
}
