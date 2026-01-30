package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NotificationPrefsView extends VBox {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";

    public NotificationPrefsView() {
        setSpacing(20);
        setPadding(new Insets(15));
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        Label back = new Label("<");
        back.setTextFill(Color.WHITE);
        Label title = new Label("Notification Preferences");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        header.getChildren().addAll(back, title);

        // Sections
        VBox content = new VBox(25);
        content.getChildren().addAll(
                createSection("CHANNEL PREFERENCES",
                        createToggleRow("Push Notifications", "Instant device alerts", true),
                        createToggleRow("Email Alerts", "Daily digests", true)),
                createSection("BUSINESS ACTIVITY",
                        createToggleRow("New Leads", null, true),
                        createToggleRow("Payment Success", null, true)));

        getChildren().addAll(header, content);
    }

    private VBox createSection(String title, Node... nodes) {
        VBox s = new VBox(10);
        Label lbl = new Label(title);
        lbl.setTextFill(Color.GRAY);
        lbl.setFont(Font.font("System", FontWeight.BOLD, 10));
        VBox box = new VBox(0);
        box.setStyle("-fx-background-color: #1c2e22; -fx-background-radius: 12;");
        box.getChildren().addAll(nodes);
        s.getChildren().addAll(lbl, box);
        return s;
    }

    private HBox createToggleRow(String title, String sub, boolean active) {
        HBox row = new HBox(15);
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        VBox text = new VBox(2);
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.MEDIUM, 14));
        text.getChildren().add(t);
        if (sub != null) {
            Label s = new Label(sub);
            s.setTextFill(Color.GRAY);
            s.setFont(Font.font(10));
            text.getChildren().add(s);
        }

        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);
        CheckBox cb = new CheckBox();
        cb.setSelected(active);
        cb.setStyle("-fx-mark-color: white; -fx-box-color: " + (active ? PRIMARY : "#30363d") + ";");

        row.getChildren().addAll(text, s, cb);
        return row;
    }
}
