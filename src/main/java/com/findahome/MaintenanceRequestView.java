package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MaintenanceRequestView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String BORDER_COLOR = "#3b5443";
    private static final String CARD_BG = "#1c271f";
    private static final String TEXT_GRAY = "#9db9a6";

    private String selectedPriority = "Medium";

    public MaintenanceRequestView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new TenantProfileView()));

        Label title = new Label("Maintenance Request");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Region spacer = new Region();
        spacer.setPrefWidth(28);

        header.getChildren().addAll(backBtn, title, spacer);

        // Scrollable Content
        VBox scrollContent = new VBox(25);
        scrollContent.setPadding(new Insets(20, 20, 120, 20));
        scrollContent.setAlignment(Pos.TOP_CENTER);

        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Category Selection
        VBox categorySec = new VBox(8);
        Label catLabel = new Label("Issue Category");
        catLabel.setTextFill(Color.WHITE);
        catLabel.setFont(Font.font("System", FontWeight.MEDIUM, 15));

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll("Plumbing", "Electrical", "Structural", "Appliance");
        categoryBox.setPromptText("Select category");
        categoryBox.setMaxWidth(Double.MAX_VALUE);
        categoryBox.setPrefHeight(56);
        categoryBox.getStyleClass().add("dark-combo");
        categoryBox.setStyle("-fx-background-color: " + CARD_BG + "; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-radius: 12; -fx-background-radius: 12; -fx-color-label-visible: white;");

        categorySec.getChildren().addAll(catLabel, categoryBox);

        // Problem Description
        VBox descSec = new VBox(8);
        Label descLabel = new Label("Problem Description");
        descLabel.setTextFill(Color.WHITE);
        descLabel.setFont(Font.font("System", FontWeight.MEDIUM, 15));

        TextArea descInput = new TextArea();
        descInput.setPromptText("Describe the issue in detail (e.g., Leaking pipe under kitchen sink)...");
        descInput.setWrapText(true);
        descInput.setPrefHeight(150);
        descInput.setStyle("-fx-control-inner-background: " + CARD_BG + "; -fx-background-color: " + CARD_BG
                + "; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-radius: 12; -fx-background-radius: 12; -fx-text-fill: white; -fx-prompt-text-fill: "
                + TEXT_GRAY + ";");

        descSec.getChildren().addAll(descLabel, descInput);

        // Priority Selector
        VBox prioritySec = new VBox(12);
        Label priorityLabel = new Label("Priority Level");
        priorityLabel.setTextFill(Color.WHITE);
        priorityLabel.setFont(Font.font("System", FontWeight.MEDIUM, 15));

        HBox priorityGroup = new HBox(10);
        Button lowBtn = createPriorityButton("Low");
        Button medBtn = createPriorityButton("Medium");
        Button highBtn = createPriorityButton("High");

        updatePriorityStyles(lowBtn, medBtn, highBtn);

        lowBtn.setOnAction(e -> {
            selectedPriority = "Low";
            updatePriorityStyles(lowBtn, medBtn, highBtn);
        });
        medBtn.setOnAction(e -> {
            selectedPriority = "Medium";
            updatePriorityStyles(lowBtn, medBtn, highBtn);
        });
        highBtn.setOnAction(e -> {
            selectedPriority = "High";
            updatePriorityStyles(lowBtn, medBtn, highBtn);
        });

        priorityGroup.getChildren().addAll(lowBtn, medBtn, highBtn);
        prioritySec.getChildren().addAll(priorityLabel, priorityGroup);

        // Photo/Media Section
        VBox mediaSec = new VBox(10);
        Label mediaTitle = new Label("Photos or Videos");
        mediaTitle.setTextFill(Color.WHITE);
        mediaTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label mediaDesc = new Label("Attach clear media to help us diagnose the problem faster.");
        mediaDesc.setTextFill(Color.web(TEXT_GRAY));
        mediaDesc.setFont(Font.font(13));

        FlowPane mediaGrid = new FlowPane(10, 10);

        StackPane addPhoto = new StackPane();
        addPhoto.setPrefSize(100, 100);
        addPhoto.setStyle("-fx-background-color: transparent; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-style: dashed; -fx-border-width: 2; -fx-border-radius: 12;");
        Label photoIcon = new Label("\ud83d\udcf7");
        photoIcon.setTextFill(Color.web(TEXT_GRAY));
        photoIcon.setFont(Font.font(30));
        addPhoto.getChildren().add(photoIcon);

        mediaGrid.getChildren().addAll(
                addPhoto,
                createImageThumbnail(
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuBrAKTFGfpRlgHVbjZ3AhysxwrypiQOEE8-GSVQ7xHP5Ba4Ch1xPu80OIwGXd6kNzStAYd36yoFkEdmY3jUv-GR4RvNjUIdTZ7FxBjfJXX3uKpyDfXHdSJYVewEnHzBWwGU_02NcrrsEcTJPXlhwVwIZWXYdKPbjuNrQVAcr5A9zqmFPCeWWw2NLEJhYk-4LILtBxKay3gxJSon4c9bt4yx76mvWADpDWou7wqsdkj5JRgkBM8ry0-U4p79u7bhl46Jdtauz_rFmk0"),
                createImageThumbnail(
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuC4dXF5cvwLbAPrY2CwRHtXKPKzRw2jCXhex4xxRIsPO1TWD0WqO-zm6BA-AIVO3RON1APtFwBuTuStF6Xqhqfni01Rl8NvgRKezkbtPdKtNlb9dopi3ffNcEqzKXwuQ0a6hErZayaMOmVOtmKHbj5kgOs-tm4z0TNnKsrd4Tt2ImYIhHpo5N5cT-3TZdQvmUAUkvBrU1F5O7YLB1WpZH-UQoTPLYBu_RiG53VuHqEpOT1z0XXmEzthP70eVwuFn_mgJ1xugi33RQg"));

        mediaSec.getChildren().addAll(mediaTitle, mediaDesc, mediaGrid);

        scrollContent.getChildren().addAll(categorySec, descSec, prioritySec, mediaSec);

        // Fixed Bottom Footer
        VBox footer = new VBox(8);
        footer.setPadding(new Insets(15, 20, 35, 20));
        footer.setAlignment(Pos.CENTER);
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "cc; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");

        Button submitBtn = new Button("Submit Request");
        submitBtn.setMaxWidth(Double.MAX_VALUE);
        submitBtn.setPrefHeight(56);
        submitBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-font-size: 18; -fx-background-radius: 12; -fx-cursor: hand;");
        submitBtn.setOnAction(e -> MainApp.navigateTo(new SuccessView("Request Submitted!",
                "Our maintenance team has received your ticket and will respond within 24-48 hours.", "Continue")));

        Label hintLbl = new Label("Expect a response within 24-48 hours");
        hintLbl.setTextFill(Color.web(TEXT_GRAY));
        hintLbl.setFont(Font.font(12));

        footer.getChildren().addAll(submitBtn, hintLbl);

        layout.getChildren().addAll(header, scroll);
        getChildren().addAll(layout, footer);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
    }

    private Button createPriorityButton(String text) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(btn, Priority.ALWAYS);
        btn.setPrefHeight(48);
        return btn;
    }

    private void updatePriorityStyles(Button l, Button m, Button h) {
        String baseStyle = "-fx-background-color: " + CARD_BG + "; -fx-text-fill: white; -fx-border-color: "
                + BORDER_COLOR + "; -fx-border-radius: 12; -fx-background-radius: 12; -fx-cursor: hand;";
        String activeStyle = "-fx-background-color: " + PRIMARY + "22; -fx-text-fill: " + PRIMARY
                + "; -fx-border-color: " + PRIMARY
                + "; -fx-border-width: 2; -fx-border-radius: 12; -fx-background-radius: 12; -fx-font-weight: bold;";

        l.setStyle(selectedPriority.equals("Low") ? activeStyle : baseStyle);
        m.setStyle(selectedPriority.equals("Medium") ? activeStyle : baseStyle);
        h.setStyle(selectedPriority.equals("High") ? activeStyle : baseStyle);
    }

    private StackPane createImageThumbnail(String url) {
        StackPane thumb = new StackPane();
        thumb.setPrefSize(100, 100);

        ImageView iv = new ImageView();
        try {
            iv.setImage(new Image(url, 100, 100, false, true));
        } catch (Exception e) {
        }
        iv.setFitWidth(100);
        iv.setFitHeight(100);
        Rectangle clip = new Rectangle(100, 100);
        clip.setArcWidth(24);
        clip.setArcHeight(24);
        iv.setClip(clip);

        Button xBtn = new Button("\u2715");
        xBtn.setStyle(
                "-fx-background-color: #ef4444; -fx-text-fill: white; -fx-background-radius: 12; -fx-padding: 2; -fx-font-size: 10;");
        xBtn.setMinSize(20, 20);
        StackPane.setAlignment(xBtn, Pos.TOP_RIGHT);
        StackPane.setMargin(xBtn, new Insets(-5, -5, 0, 0));

        thumb.getChildren().addAll(iv, xBtn);
        return thumb;
    }
}
