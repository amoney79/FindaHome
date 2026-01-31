package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AdminMaintenanceDashboardView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
    private static final String CARD_BG = "#1c271f";
    private static final String TEXT_GRAY = "#9db9a6";

    public AdminMaintenanceDashboardView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(40, 40);
        iconBox.setStyle("-fx-background-color: " + PRIMARY + "33; -fx-background-radius: 10;");
        Label hammerIcon = new Label("\ud83d\udee0");
        hammerIcon.setTextFill(Color.web(PRIMARY));
        hammerIcon.setStyle("-fx-font-size: 20;");
        iconBox.getChildren().add(hammerIcon);

        Label title = new Label("Maintenance");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 20));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox actions = new HBox(10);
        Label notifyBtn = createCircleAction("\ud83d\udd14");
        Label tuneBtn = createCircleAction("\u2312"); // tune icon mock
        actions.getChildren().addAll(notifyBtn, tuneBtn);

        header.getChildren().addAll(iconBox, title, spacer, actions);

        // Content
        VBox scrollContent = new VBox(25);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        scrollContent.setPadding(new Insets(20, 0, 120, 0));

        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Stats Row
        HBox statsScroll = new HBox(15);
        statsScroll.setPadding(new Insets(0, 20, 0, 20));
        statsScroll.getChildren().addAll(
                createStatCard("Pending", "12", "+2 today", "#f97316"),
                createStatCard("Active", "08", "3 on track", PRIMARY),
                createStatCard("Avg. Time", "2.4d", "-0.5d vs mo", "#94a3b8"));
        ScrollPane statsPane = new ScrollPane(statsScroll);
        statsPane.setFitToHeight(true);
        statsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        statsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        statsPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Priority Filters
        VBox prioritySect = new VBox(15);
        prioritySect.setPadding(new Insets(0, 20, 0, 20));
        Label priorityLabel = new Label("PRIORITY LEVEL");
        priorityLabel.setTextFill(Color.web(TEXT_GRAY));
        priorityLabel.setFont(Font.font("System", FontWeight.BOLD, 10));

        HBox chips = new HBox(10);
        chips.getChildren().addAll(
                createChip("Critical", "#ef4444", true),
                createChip("High", TEXT_GRAY, false),
                createChip("Medium", TEXT_GRAY, false),
                createChip("Low", TEXT_GRAY, false));
        prioritySect.getChildren().addAll(priorityLabel, chips);

        // Task List
        VBox taskList = new VBox(20);
        taskList.setPadding(new Insets(0, 20, 0, 20));

        taskList.getChildren().addAll(
                createCriticalTaskCard("Major Pipe Leak - Unit 4B", "Sarah Johnson", "Ocean View Apartments, Wing A",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuB7aVUx18rjxzcsXZcZ7ohoAoLnOwRzZp0fyHFLN75BFx968Wk5JBeJrEGmg-aXFTDz5BsQK_Hl80q2ym93mUvqWk57KxEayDkgsIVKuH6IXoCm7IWelg8hfuu58FkRXNMNYjN7d5W9MX2UxmmdZCuZO5lrgDe07cc-nL8_TZr31__SOZubHuNglIkU2KQJsjdxlYV1zg5jDcRvu7onde-9nhv2p_UQQFuNgo-wLqkZLy6SAtHZQUf6lesl8GZ2sz7fTUJ2gRN8p8U"),
                createRegularTaskCard("AC Service Maintenance", "Mike Richards", "Sky Tower, PH 02", "Medium",
                        "#3b82f6",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuDjWrvD3L1z9aEEtlNqtjiuZrm9kg27uHkFYP4jAx-5PLBHBxbvAGQLk1RvSQSlGkO6J7n-b8TLn5X4_s82TlZtsJW0csvwkjaJvtRr6r6qDAh6Umi0t1Kb_WlsREWCF3hvBNPzQ-XrIg4yY6k-Aepmr1MVNxQxCHeiMDYC5ubM22xHKztin1vaPN37x50S7dfCAJrkYUWBmB8_Ce8iH6Bg4VDD9QapP_LHNUb0_IuMqjDfllddIqD6b076uH04RT3YnBOBnF7w4Dk"),
                createRegularTaskCard("Broken Door Lock", "Linda Chen", "2 hours ago", "High", "#f97316", null));

        scrollContent.getChildren().addAll(statsPane, prioritySect, taskList);

        // FAB
        Button fab = new Button("+");
        fab.setPrefSize(56, 56);
        fab.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-size: 28; -fx-font-weight: bold; -fx-background-radius: 28;");
        StackPane.setAlignment(fab, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(fab, new Insets(0, 20, 100, 0));

        // Admin Bottom Nav
        HBox bottomNav = new HBox(0);
        bottomNav.setAlignment(Pos.CENTER);
        bottomNav.setPrefHeight(80);
        bottomNav.setStyle(
                "-fx-background-color: #111813; -fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0;");
        bottomNav.getChildren().addAll(
                createAdminNavItem("Properties", "\ud83c\udfe2", false),
                createAdminNavItem("Requests", hammerIcon.getText(), true),
                createAdminNavItem("Revenue", "\ud83d\udcb3", false),
                createAdminNavItem("Stats", "\ud83d\udcc8", false),
                createAdminNavItem("Profile", "\ud83d\udc64", false));

        layout.getChildren().addAll(header, scroll);
        getChildren().addAll(layout, fab, bottomNav);
        StackPane.setAlignment(bottomNav, Pos.BOTTOM_CENTER);
    }

    private Label createCircleAction(String icon) {
        Label l = new Label(icon);
        l.setAlignment(Pos.CENTER);
        l.setPrefSize(40, 40);
        l.setStyle(
                "-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 20; -fx-text-fill: white; -fx-font-size: 16;");
        return l;
    }

    private VBox createStatCard(String label, String value, String change, String color) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(15));
        card.setPrefWidth(140);
        card.setStyle("-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 16; -fx-border-color: "
                + BORDER_COLOR + ";");

        Label l = new Label(label.toUpperCase());
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font("System", FontWeight.BOLD, 10));

        Label v = new Label(value);
        v.setTextFill(Color.WHITE);
        v.setFont(Font.font("System", FontWeight.BOLD, 24));

        Label shift = new Label(change);
        shift.setTextFill(Color.web(color));
        shift.setFont(Font.font(10));

        card.getChildren().addAll(l, v, shift);
        return card;
    }

    private HBox createChip(String text, String color, boolean active) {
        HBox chip = new HBox(8);
        chip.setAlignment(Pos.CENTER);
        chip.setPadding(new Insets(8, 15, 8, 15));

        if (active) {
            chip.setStyle(
                    "-fx-background-color: rgba(239, 68, 68, 0.2); -fx-background-radius: 20; -fx-border-color: rgba(239, 68, 68, 0.3);");
            Circle dot = new Circle(4, Color.web("#ef4444"));
            Label l = new Label(text.toUpperCase());
            l.setTextFill(Color.web("#ef4444"));
            l.setFont(Font.font("System", FontWeight.BOLD, 10));
            chip.getChildren().addAll(dot, l);
        } else {
            chip.setStyle("-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 20;");
            Label l = new Label(text);
            l.setTextFill(Color.web(TEXT_GRAY));
            l.setFont(Font.font("System", FontWeight.MEDIUM, 12));
            chip.getChildren().add(l);
        }
        return chip;
    }

    private VBox createCriticalTaskCard(String title, String tenant, String loc, String imgUrl) {
        VBox card = new VBox(0);
        card.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.05); -fx-overflow: hidden;");

        StackPane imgStack = new StackPane();
        ImageView iv = new ImageView();
        try {
            iv.setImage(new Image(imgUrl, 400, 150, false, true));
        } catch (Exception e) {
        }
        iv.setFitWidth(360);
        iv.setFitHeight(150);
        Rectangle clip = new Rectangle(360, 150);
        clip.setArcWidth(32);
        clip.setArcHeight(32);
        iv.setClip(clip);

        Label urgentBadge = new Label("URGENT ACTION");
        urgentBadge.setStyle(
                "-fx-background-color: #ef4444; -fx-text-fill: white; -fx-font-weight: black; -fx-font-size: 10; -fx-padding: 4 8; -fx-background-radius: 4;");
        StackPane.setAlignment(urgentBadge, Pos.TOP_LEFT);
        StackPane.setMargin(urgentBadge, new Insets(12));

        imgStack.getChildren().addAll(iv, urgentBadge);

        VBox details = new VBox(12);
        details.setPadding(new Insets(15));

        HBox head = new HBox();
        VBox titleBox = new VBox(2);
        Label prio = new Label("CRITICAL PRIORITY");
        prio.setTextFill(Color.web("#ef4444"));
        prio.setFont(Font.font("System", FontWeight.BOLD, 9));
        Label tLbl = new Label(title);
        tLbl.setTextFill(Color.WHITE);
        tLbl.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleBox.getChildren().addAll(prio, tLbl);
        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);
        Label more = new Label("\u22ee");
        more.setTextFill(Color.web(TEXT_GRAY));
        head.getChildren().addAll(titleBox, s, more);

        VBox meta = new VBox(6);
        meta.getChildren().addAll(
                createMetaRow("\ud83d\udc64", "Tenant: " + tenant),
                createMetaRow("\ud83d\udccd", loc));

        HBox actions = new HBox(10);
        Button assignBtn = new Button("\ud83d\udee0  Assign Tech");
        assignBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(assignBtn, Priority.ALWAYS);
        assignBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-background-radius: 10; -fx-pref-height: 40;");
        Button viewBtn = new Button("View Details");
        viewBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(viewBtn, Priority.ALWAYS);
        viewBtn.setStyle(
                "-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10; -fx-pref-height: 40;");
        actions.getChildren().addAll(assignBtn, viewBtn);

        details.getChildren().addAll(head, meta, actions);
        card.getChildren().addAll(imgStack, details);
        return card;
    }

    private VBox createRegularTaskCard(String title, String tenant, String metaStr, String prioLabel, String prioColor,
            String avatarUrl) {
        VBox card = new VBox(15);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: " + CARD_BG
                + "; -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.05);");

        HBox head = new HBox();
        VBox titleBox = new VBox(2);
        Label prioL = new Label(prioLabel.toUpperCase() + " PRIORITY");
        prioL.setTextFill(Color.web(prioColor));
        prioL.setFont(Font.font("System", FontWeight.BOLD, 9));
        Label tLbl = new Label(title);
        tLbl.setTextFill(Color.WHITE);
        tLbl.setFont(Font.font("System", FontWeight.BOLD, 18));
        titleBox.getChildren().addAll(prioL, tLbl);
        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);

        if (avatarUrl != null) {
            ImageView av = new ImageView();
            try {
                av.setImage(new Image(avatarUrl, 24, 24, true, true));
            } catch (Exception e) {
            }
            av.setFitWidth(24);
            av.setFitHeight(24);
            Rectangle clip = new Rectangle(24, 24);
            clip.setArcWidth(24);
            clip.setArcHeight(24);
            av.setClip(clip);
            head.getChildren().addAll(titleBox, s, av);
        } else {
            Label more = new Label("\u22ee");
            more.setTextFill(Color.web(TEXT_GRAY));
            head.getChildren().addAll(titleBox, s, more);
        }

        VBox meta = new VBox(6);
        meta.getChildren().addAll(
                createMetaRow("\ud83d\udc64", "Tenant: " + tenant),
                createMetaRow(prioLabel.equals("High") ? "\ud83d\udcc5" : "\ud83c\udfe2", metaStr));

        HBox actions = new HBox(10);
        if (prioLabel.equals("Medium")) {
            Button resBtn = new Button("\u2705  Mark Resolved");
            resBtn.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(resBtn, Priority.ALWAYS);
            resBtn.setStyle(
                    "-fx-background-color: " + PRIMARY + "22; -fx-text-fill: " + PRIMARY + "; -fx-border-color: "
                            + PRIMARY + "44; -fx-font-weight: bold; -fx-background-radius: 10; -fx-pref-height: 40;");
            Label chatBtn = createCircleAction("\ud83d\udcac");
            actions.getChildren().addAll(resBtn, chatBtn);
        } else {
            Button assignBtn = new Button("\ud83d\udc64  Assign Tech");
            assignBtn.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(assignBtn, Priority.ALWAYS);
            assignBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                    + "; -fx-font-weight: bold; -fx-background-radius: 10; -fx-pref-height: 40;");
            actions.getChildren().add(assignBtn);
        }

        card.getChildren().addAll(head, meta, actions);
        return card;
    }

    private HBox createMetaRow(String icon, String text) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);
        Label i = new Label(icon);
        i.setTextFill(Color.web(TEXT_GRAY));
        i.setStyle("-fx-font-size: 14;");
        Label t = new Label(text);
        t.setTextFill(Color.web(TEXT_GRAY));
        t.setFont(Font.font(13));
        row.getChildren().addAll(i, t);
        return row;
    }

    private VBox createAdminNavItem(String label, String icon, boolean active) {
        VBox item = new VBox(5);
        item.setAlignment(Pos.CENTER);
        item.setPrefWidth(80);
        Label i = new Label(icon);
        i.setStyle("-fx-font-size: 22;");
        i.setTextFill(active ? Color.web(PRIMARY) : Color.web("#64748b"));
        Label l = new Label(label);
        l.setFont(Font.font("System", FontWeight.BOLD, 10));
        l.setTextFill(active ? Color.web(PRIMARY) : Color.web("#64748b"));
        item.getChildren().addAll(i, l);
        return item;
    }
}
