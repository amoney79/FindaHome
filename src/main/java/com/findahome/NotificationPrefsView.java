package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.scene.shape.Circle;

public class NotificationPrefsView extends VBox {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String CARD_BG = "#1c2e22";
    private static final String PRIMARY = "#13ec5b";
    private static final String TEXT_MUTED = "#64748b";

    public NotificationPrefsView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 10, 20));

        Label back = new Label("\u2039"); // Back arrow
        back.setTextFill(Color.WHITE);
        back.setStyle("-fx-font-size: 28; -fx-cursor: hand; -fx-padding: 0 10 0 0;");
        back.setOnMouseClicked(e -> MainApp.showHome());

        Label title = new Label("Notification Preferences");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Region headerSpacer = new Region();
        headerSpacer.setPrefWidth(40); // To balance the back button

        header.getChildren().addAll(back, title, headerSpacer);

        VBox scrollContent = new VBox(30);
        scrollContent.setPadding(new Insets(20, 20, 40, 20));

        // CHANNEL PREFERENCES
        VBox channelSection = createSectionHeader("CHANNEL PREFERENCES");
        VBox channelList = new VBox(0);
        channelList.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-overflow: hidden;");
        channelList.getChildren().addAll(
                createPreferenceRow("Push Notifications", "Instant device alerts", "\ud83d\udd14", true, true),
                createPreferenceRow("Email Alerts", "Daily digests & receipts", "\u2709\ufe0f", true, true),
                createPreferenceRow("SMS Notifications", "Critical business updates", "\ud83d\udcac", false, false));

        // BUSINESS ACTIVITY
        VBox businessSection = createSectionHeader("BUSINESS ACTIVITY");
        VBox businessList = new VBox(0);
        businessList
                .setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-overflow: hidden;");
        businessList.getChildren().addAll(
                createPreferenceRow("New Leads", null, "\ud83d\udc64", true, true),
                createPreferenceRow("Booking Reminders", null, "\ud83d\uddd3\ufe0f", true, true),
                createPreferenceRow("Payment Success", null, "\ud83d\udcb0", true, true),
                createPreferenceRow("System Updates", null, "\ud83d\udd04", false, false));

        // SCHEDULE
        VBox scheduleSection = createSectionHeader("SCHEDULE");
        VBox scheduleList = new VBox(0);
        scheduleList
                .setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 16; -fx-overflow: hidden;");

        // Quiet Hours Toggle
        HBox quietRow = createPreferenceRow("Quiet Hours", null, "\ud83c\udf19", true, true);

        // Time Range Selector
        HBox timeRange = new HBox(15);
        timeRange.setAlignment(Pos.CENTER);
        timeRange.setPadding(new Insets(15, 20, 20, 20));

        VBox fromBox = createTimeDisplay("FROM", "22:00");
        Region tSpacer = new Region();
        tSpacer.setPrefSize(40, 1);
        tSpacer.setStyle("-fx-background-color: rgba(255,255,255,0.1);");
        VBox toBox = createTimeDisplay("TO", "07:00");

        timeRange.getChildren().addAll(fromBox, tSpacer, toBox);
        scheduleList.getChildren().addAll(quietRow, timeRange);

        Label scheduleNote = new Label(
                "Notifications will be silenced on your device during these hours, except for urgent system alerts.");
        scheduleNote.setTextFill(Color.web(TEXT_MUTED));
        scheduleNote.setFont(Font.font(11));
        scheduleNote.setWrapText(true);
        scheduleNote.setPadding(new Insets(10, 5, 0, 5));

        scrollContent.getChildren().addAll(
                new VBox(10, channelSection, channelList),
                new VBox(10, businessSection, businessList),
                new VBox(10, scheduleSection, scheduleList, scheduleNote));

        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        getChildren().addAll(header, scroll);
    }

    private VBox createSectionHeader(String title) {
        VBox header = new VBox();
        Label lbl = new Label(title);
        lbl.setTextFill(Color.web(TEXT_MUTED));
        lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 11; -fx-letter-spacing: 1px;");
        lbl.setPadding(new Insets(0, 5, 5, 5));
        header.getChildren().add(lbl);
        return header;
    }

    private HBox createPreferenceRow(String title, String sub, String iconStr, boolean isOn, boolean showBorder) {
        HBox row = new HBox(15);
        row.setPadding(new Insets(15, 20, 15, 20));
        row.setAlignment(Pos.CENTER_LEFT);
        if (showBorder) {
            row.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");
        }

        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(40, 40);
        iconBox.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 10;");
        Label icon = new Label(iconStr);
        icon.setTextFill(Color.web(PRIMARY));
        icon.setStyle("-fx-font-size: 18;");
        iconBox.getChildren().add(icon);

        VBox text = new VBox(2);
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.MEDIUM, 15));
        text.getChildren().add(t);

        if (sub != null) {
            Label s = new Label(sub);
            s.setTextFill(Color.web(TEXT_MUTED));
            s.setFont(Font.font(12));
            text.getChildren().add(s);
        }

        Region sSpacer = new Region();
        HBox.setHgrow(sSpacer, Priority.ALWAYS);

        // iOS Style Toggle
        StackPane toggle = createIOSSwitch(isOn);

        row.getChildren().addAll(iconBox, text, sSpacer, toggle);
        return row;
    }

    private StackPane createIOSSwitch(boolean isOn) {
        StackPane switchTrack = new StackPane();
        switchTrack.setPrefSize(51, 31);
        switchTrack.setMaxSize(51, 31);
        switchTrack.setStyle("-fx-background-radius: 16; -fx-background-color: " + (isOn ? PRIMARY : "#28392e") + ";");

        Circle thumb = new Circle(13.5, Color.WHITE);
        thumb.setEffect(new javafx.scene.effect.DropShadow(3, Color.BLACK));

        StackPane.setAlignment(thumb, isOn ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        StackPane.setMargin(thumb, new Insets(2));

        switchTrack.getChildren().add(thumb);
        switchTrack.setCursor(javafx.scene.Cursor.HAND);

        switchTrack.setOnMouseClicked(e -> {
            boolean current = switchTrack.getStyle().contains(PRIMARY);
            switchTrack.setStyle(
                    "-fx-background-radius: 16; -fx-background-color: " + (!current ? PRIMARY : "#28392e") + ";");
            StackPane.setAlignment(thumb, !current ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        });

        return switchTrack;
    }

    private VBox createTimeDisplay(String label, String time) {
        VBox box = new VBox(2);
        Label lbl = new Label(label);
        lbl.setTextFill(Color.web(TEXT_MUTED));
        lbl.setStyle("-fx-font-size: 10; -fx-font-weight: bold; -fx-letter-spacing: 0.5px;");

        Label val = new Label(time);
        val.setTextFill(Color.web(PRIMARY));
        val.setFont(Font.font("System", FontWeight.BOLD, 18));

        box.getChildren().addAll(lbl, val);
        return box;
    }
}
