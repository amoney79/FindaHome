package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NeighborhoodAlertsView extends BorderPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String PRIMARY = "#13ec5b";
    private static final String TEXT_GRAY = "#9da6b9";
    private static final String CARD_BG = "#1c222c";
    private static final String DIVIDER_COLOR = "#2a3544";

    public NeighborhoodAlertsView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        VBox header = new VBox();
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + DIVIDER_COLOR
                + "; -fx-border-width: 0 0 1 0;");
        header.setPadding(new Insets(15, 20, 15, 20));

        HBox navBar = new HBox(15);
        navBar.setAlignment(Pos.CENTER_LEFT);

        Label backBtn = new Label("\u2039");
        backBtn.setFont(Font.font("System", FontWeight.BOLD, 28));
        backBtn.setTextFill(Color.WHITE);
        backBtn.setCursor(javafx.scene.Cursor.HAND);
        backBtn.setOnMouseClicked(e -> MainApp.showHome());

        Label title = new Label("Neighborhood Alerts");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        HBox.setHgrow(title, Priority.ALWAYS);
        title.setAlignment(Pos.CENTER);

        Label helpBtn = new Label("\u24D8");
        helpBtn.setTextFill(Color.web(TEXT_GRAY));
        helpBtn.setFont(Font.font(20));

        navBar.getChildren().addAll(backBtn, title, helpBtn);
        header.getChildren().add(navBar);
        setTop(header);

        // Scroll Content
        VBox content = new VBox(24);
        content.setPadding(new Insets(20, 20, 40, 20));
        content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // 1. Search & Chips
        VBox section1 = new VBox(15);
        section1.getChildren().addAll(
                createSectionHeader("Target Neighborhoods"),
                new HBox(10) {
                    {
                        setAlignment(Pos.CENTER_LEFT);
                        setPadding(new Insets(12));
                        setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12;");
                        getChildren().addAll(new Label("\ud83d\udd0d") {
                            {
                                setTextFill(Color.web(TEXT_GRAY));
                            }
                        },
                                new TextField() {
                                    {
                                        setPromptText("Search Wards...");
                                        setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
                                    }
                                });
                    }
                },
                new FlowPane(10, 10) {
                    {
                        getChildren().addAll(createChip("Kileleshwa", true), createChip("Bamburi", true),
                                createChip("Tudor", true), createAddChip());
                    }
                });

        // 2. Criteria
        VBox section2 = new VBox(20);
        section2.getChildren().addAll(
                createSectionHeader("Property Criteria"),
                createField("Property Type", new ComboBox<String>() {
                    {
                        getItems().addAll("2-bedroom Apartment", "3-bedroom Apartment", "Standalone House");
                        setValue("2-bedroom Apartment");
                        setMaxWidth(Double.MAX_VALUE);
                        setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 8;");
                    }
                }),
                new VBox(12) {
                    {
                        getChildren().addAll(
                                new HBox(new Label("Price Range") {
                                    {
                                        setTextFill(Color.web(TEXT_GRAY));
                                    }
                                }, new Region() {
                                    {
                                        HBox.setHgrow(this, Priority.ALWAYS);
                                    }
                                }, new Label("45k - 120k") {
                                    {
                                        setTextFill(Color.web(PRIMARY));
                                        setFont(Font.font("System", FontWeight.BOLD, 14));
                                    }
                                }),
                                new Slider(10000, 500000, 80000) {
                                    {
                                        setStyle("-fx-accent: " + PRIMARY + ";");
                                    }
                                });
                    }
                });

        // 3. Methods
        VBox section3 = new VBox(15);
        VBox toggleList = new VBox(0);
        toggleList.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16;");
        toggleList.getChildren().addAll(
                createToggleRow("\ud83d\udd14", "Instant Push", "Get alerts immediately", true, true),
                createToggleRow("\u2709", "Daily Email", "Summary of new matches", false, false));
        section3.getChildren().addAll(createSectionHeader("Notification Methods"), toggleList);

        content.getChildren().addAll(section1, createDivider(), section2, createDivider(), section3);

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; -fx-viewport-background-color: transparent;");
        setCenter(scroll);

        // Footer Action (Pinned)
        VBox footer = new VBox();
        footer.setPadding(new Insets(20, 20, 35, 20));
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + DIVIDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");

        Button createBtn = new Button("Create Neighborhood Alert \ud83d\udd14");
        createBtn.setMaxWidth(Double.MAX_VALUE);
        createBtn.setPrefHeight(56);
        createBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 12; -fx-cursor: hand;");
        createBtn.setOnAction(e -> MainApp.showHome());

        footer.getChildren().add(createBtn);
        setBottom(footer);
    }

    private Label createSectionHeader(String text) {
        Label l = new Label(text);
        l.setTextFill(Color.WHITE);
        l.setFont(Font.font("System", FontWeight.BOLD, 16));
        return l;
    }

    private Separator createDivider() {
        Separator s = new Separator();
        s.setStyle("-fx-background-color: " + DIVIDER_COLOR + ";");
        return s;
    }

    private VBox createField(String label, Node node) {
        VBox v = new VBox(8);
        Label l = new Label(label);
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font(12));
        v.getChildren().addAll(l, node);
        return v;
    }

    private HBox createChip(String text, boolean removable) {
        HBox chip = new HBox(8);
        chip.setAlignment(Pos.CENTER);
        chip.setPadding(new Insets(8, 14, 8, 14));
        chip.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-border-color: " + PRIMARY
                + "40; -fx-background-radius: 20; -fx-border-radius: 20;");
        Label lbl = new Label(text);
        lbl.setTextFill(Color.web(PRIMARY));
        lbl.setFont(Font.font("System", FontWeight.BOLD, 13));
        chip.getChildren().add(lbl);
        if (removable)
            chip.getChildren().add(new Label("\u2715") {
                {
                    setTextFill(Color.web(PRIMARY));
                    setCursor(javafx.scene.Cursor.HAND);
                }
            });
        return chip;
    }

    private Button createAddChip() {
        Button b = new Button("+ Add Ward");
        b.setStyle(
                "-fx-background-color: #282e39; -fx-text-fill: #9da6b9; -fx-background-radius: 20; -fx-font-size: 12; -fx-padding: 8 16; -fx-cursor: hand;");
        return b;
    }

    private HBox createToggleRow(String icon, String title, String sub, boolean checked, boolean border) {
        HBox row = new HBox(15);
        row.setPadding(new Insets(16));
        row.setAlignment(Pos.CENTER_LEFT);
        if (border)
            row.setStyle("-fx-border-color: " + DIVIDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        Label ic = new Label(icon) {
            {
                setTextFill(Color.web(PRIMARY));
                setFont(Font.font(20));
            }
        };
        VBox tx = new VBox(2, new Label(title) {
            {
                setTextFill(Color.WHITE);
                setFont(Font.font("System", FontWeight.BOLD, 14));
            }
        }, new Label(sub) {
            {
                setTextFill(Color.web(TEXT_GRAY));
                setFont(Font.font(11));
            }
        });
        HBox.setHgrow(tx, Priority.ALWAYS);

        StackPane toggle = new StackPane();
        toggle.setPrefSize(44, 24);
        Rectangle bg = new Rectangle(44, 24) {
            {
                setArcWidth(24);
                setArcHeight(24);
                setFill(checked ? Color.web(PRIMARY) : Color.web("#475569"));
            }
        };
        javafx.scene.shape.Circle knob = new javafx.scene.shape.Circle(10, Color.WHITE) {
            {
                setTranslateX(checked ? 10 : -10);
            }
        };
        toggle.getChildren().addAll(bg, knob);

        row.getChildren().addAll(ic, tx, toggle);
        return row;
    }
}
