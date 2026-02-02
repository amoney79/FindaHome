package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MovingChecklistView extends VBox {

    private static final String BACKGROUND_DARK = "#221610";
    private static final String PRIMARY = "#f46a25";
    private static final String TEXT_GRAY = "#8a6e60";

    public MovingChecklistView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // --- Header ---
        HBox header = new HBox(0);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

        HBox leftSection = new HBox(12);
        leftSection.setAlignment(Pos.CENTER_LEFT);

        Button backBtn = new Button("\u276E"); // arrow_back_ios
        backBtn.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 18; -fx-cursor: hand;");
        backBtn.setOnAction(e -> MainApp.showHome());

        Label title = new Label("Moving Checklist");
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setTextFill(Color.WHITE);

        leftSection.getChildren().addAll(backBtn, title);
        HBox.setHgrow(leftSection, Priority.ALWAYS);

        Button shareBtn = new Button("\uE80D"); // share
        shareBtn.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 20; -fx-cursor: hand; -fx-background-radius: 20; -fx-padding: 8;");

        header.getChildren().addAll(leftSection, shareBtn);

        // --- Main Content ---
        VBox content = new VBox(0);
        content.setPadding(new Insets(0, 0, 100, 0));

        // 1. Progress Section
        VBox progressSection = new VBox(8);
        progressSection.setPadding(new Insets(16));
        progressSection.setStyle(
                "-fx-background-color: rgba(45,30,23,0.5); -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 0 0 1 0;");

        HBox progressHeader = new HBox();
        progressHeader.setAlignment(Pos.CENTER_LEFT);
        Label progressLabel = new Label("Overall Progress");
        progressLabel.setTextFill(Color.WHITE);
        progressLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        Region pSpacer = new Region();
        HBox.setHgrow(pSpacer, Priority.ALWAYS);
        Label progressPercent = new Label("35%");
        progressPercent.setTextFill(Color.web(PRIMARY));
        progressPercent.setFont(Font.font("System", FontWeight.BOLD, 16));
        progressHeader.getChildren().addAll(progressLabel, pSpacer, progressPercent);

        StackPane progressTrack = new StackPane();
        progressTrack.setPrefHeight(10);
        progressTrack.setStyle("-fx-background-color: rgba(230,223,219,0.3); -fx-background-radius: 5;");

        Region progressFill = new Region();
        progressFill.setPrefHeight(10);
        progressFill.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 5;");
        progressFill.maxWidthProperty().bind(progressTrack.widthProperty().multiply(0.35));
        StackPane.setAlignment(progressFill, Pos.CENTER_LEFT);
        progressTrack.getChildren().add(progressFill);

        Label progressText = new Label("12 of 34 tasks completed");
        progressText.setTextFill(Color.web(TEXT_GRAY));
        progressText.setFont(Font.font(14));

        progressSection.getChildren().addAll(progressHeader, progressTrack, progressText);

        // 2. Checklist Sections
        VBox checklistSections = new VBox(24);
        checklistSections.setPadding(new Insets(16, 0, 0, 0));

        // Section: 1 Month Before
        checklistSections.getChildren().add(createSection(
                "\uD83D\uDCC5", "1 Month Before", // calendar_month
                new ChecklistItem("Notice to current landlord", "Sent email on Jan 5th", true),
                new ChecklistItem("Sort and declutter belongings", "", false)));

        // Section: 2 Weeks Before
        checklistSections.getChildren().add(createSection(
                "\u23F0", "2 Weeks Before", // schedule
                new ChecklistItem("Book movers via FindaHome", "", false),
                new ChecklistItem("Change address (KRA, Banks, NHIF)", "", false)));

        // Section: Moving Day
        checklistSections.getChildren().add(createSection(
                "\uD83D\uDE9A", "Moving Day", // local_shipping
                new ChecklistItem("Final meter readings (KPLC/Water)", "", false)));

        // Section: Post-Move
        checklistSections.getChildren().add(createSection(
                "\uD83C\uDFE0", "Post-Move", // home_pin
                new ChecklistItem("Explore local Ward/Market", "", false)));

        content.getChildren().addAll(progressSection, checklistSections);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // --- Floating Action Button ---
        Button addTaskBtn = new Button("Add Custom Task");
        addTaskBtn.setGraphic(createIcon("\u2795")); // add
        addTaskBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 25; -fx-padding: 12 24; -fx-font-size: 14; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");
        StackPane.setAlignment(addTaskBtn, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(addTaskBtn, new Insets(0, 24, 100, 0));

        // --- Bottom Navigation Bar ---
        HBox bottomNav = new HBox();
        bottomNav.setPadding(new Insets(8, 0, 8, 0));
        bottomNav.setAlignment(Pos.CENTER);
        bottomNav.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "; -fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");

        Region n1 = new Region();
        Region n2 = new Region();
        Region n3 = new Region();
        HBox.setHgrow(n1, Priority.ALWAYS);
        HBox.setHgrow(n2, Priority.ALWAYS);
        HBox.setHgrow(n3, Priority.ALWAYS);

        bottomNav.getChildren().addAll(
                createNavItem("\uD83D\uDD0D", "Market", false), n1,
                createNavItem("\u2713", "Checklist", true), n2,
                createNavItem("\uD83D\uDDFA", "Map", false), n3,
                createNavItem("\uD83D\uDC64", "Profile", false));

        // Main Layout
        StackPane root = new StackPane();
        root.getChildren().addAll(scrollPane, addTaskBtn, bottomNav);
        StackPane.setAlignment(bottomNav, Pos.BOTTOM_CENTER);

        getChildren().addAll(header, root);
    }

    private VBox createSection(String icon, String title, ChecklistItem... items) {
        VBox section = new VBox(8);

        HBox sectionHeader = new HBox(8);
        sectionHeader.setPadding(new Insets(0, 16, 12, 16));
        sectionHeader.setAlignment(Pos.CENTER_LEFT);

        Label iconLabel = new Label(icon);
        iconLabel.setTextFill(Color.web(PRIMARY));
        iconLabel.setFont(Font.font(20));

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 20));

        sectionHeader.getChildren().addAll(iconLabel, titleLabel);
        section.getChildren().add(sectionHeader);

        for (ChecklistItem item : items) {
            section.getChildren().add(createChecklistCard(item));
        }

        return section;
    }

    private VBox createChecklistCard(ChecklistItem item) {
        VBox card = new VBox(12);
        card.setPadding(new Insets(16));
        card.setStyle(
                "-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 12;");
        VBox.setMargin(card, new Insets(0, 16, 0, 16));

        // Checkbox and task name
        HBox taskRow = new HBox(12);
        taskRow.setAlignment(Pos.CENTER_LEFT);

        CheckBox checkbox = new CheckBox();
        checkbox.setSelected(item.isCompleted());
        checkbox.setStyle("-fx-text-fill: white; -fx-font-size: 14;");

        Label taskLabel = new Label(item.getTaskName());
        taskLabel.setTextFill(Color.WHITE);
        taskLabel.setFont(Font.font("System", FontWeight.MEDIUM, 16));
        taskLabel.setWrapText(true);
        HBox.setHgrow(taskLabel, Priority.ALWAYS);

        taskRow.getChildren().addAll(checkbox, taskLabel);

        // Notes section
        VBox notesSection = new VBox(4);
        Label notesLabel = new Label("NOTES");
        notesLabel.setTextFill(Color.web(TEXT_GRAY));
        notesLabel.setFont(Font.font("System", FontWeight.BOLD, 10));

        TextField notesField = new TextField(item.getNotes());
        notesField.setPromptText(getPromptForTask(item.getTaskName()));
        notesField.setStyle(
                "-fx-background-color: rgba(248,246,245,0.1); -fx-text-fill: white; -fx-prompt-text-fill: rgba(138,110,96,0.6); -fx-border-color: rgba(255,255,255,0.1); -fx-background-radius: 8; -fx-border-radius: 8; -fx-padding: 8;");

        notesSection.getChildren().addAll(notesLabel, notesField);
        card.getChildren().addAll(taskRow, notesSection);

        if (item.getTaskName().contains("Book movers")) {
            card.setCursor(javafx.scene.Cursor.HAND);
            card.setOnMouseClicked(e -> MainApp.navigateTo(new MoversQuoteRequestView()));
        }

        return card;
    }

    private String getPromptForTask(String taskName) {
        if (taskName.contains("landlord"))
            return "Sent email on Jan 5th, awaiting confirmation...";
        if (taskName.contains("declutter"))
            return "Donate old clothes to local shelter...";
        if (taskName.contains("movers"))
            return "Compare 3 quotes from the marketplace...";
        if (taskName.contains("address"))
            return "Update via iTax and bank apps...";
        if (taskName.contains("meter"))
            return "Take photos of the meters...";
        if (taskName.contains("Explore"))
            return "Find nearest grocery and pharmacy...";
        return "Add your notes here...";
    }

    private Label createIcon(String icon) {
        Label label = new Label(icon);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font(16));
        return label;
    }

    private VBox createNavItem(String icon, String label, boolean active) {
        VBox item = new VBox(4);
        item.setAlignment(Pos.CENTER);

        Label iconLabel = new Label(icon);
        iconLabel.setTextFill(active ? Color.web(PRIMARY) : Color.web("#9ca3af"));
        iconLabel.setFont(Font.font(20));

        Label textLabel = new Label(label);
        textLabel.setTextFill(active ? Color.web(PRIMARY) : Color.web("#9ca3af"));
        textLabel.setFont(Font.font(10));

        item.getChildren().addAll(iconLabel, textLabel);
        return item;
    }

    // Helper class for checklist items
    private static class ChecklistItem {
        private final String taskName;
        private final String notes;
        private final boolean completed;

        public ChecklistItem(String taskName, String notes, boolean completed) {
            this.taskName = taskName;
            this.notes = notes;
            this.completed = completed;
        }

        public String getTaskName() {
            return taskName;
        }

        public String getNotes() {
            return notes;
        }

        public boolean isCompleted() {
            return completed;
        }
    }
}
