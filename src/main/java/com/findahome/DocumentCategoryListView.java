package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DocumentCategoryListView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
    private static final String CARD_BG = "rgba(255,255,255,0.05)";
    private static final String TEXT_GRAY = "#94a3b8";

    public DocumentCategoryListView(String category) {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new DocumentVaultView()));

        Label title = new Label(category);
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        HBox.setHgrow(title, Priority.ALWAYS);

        Label searchIcon = new Label("\ud83d\udd0d");
        searchIcon.setTextFill(Color.WHITE);
        searchIcon.setStyle("-fx-font-size: 20;");

        header.getChildren().addAll(backBtn, title, searchIcon);

        // Scroll Content
        VBox scrollContent = new VBox(15);
        scrollContent.setAlignment(Pos.TOP_CENTER);
        scrollContent.setPadding(new Insets(20));

        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Files
        if (category.equals("Signed Leases")) {
            scrollContent.getChildren().addAll(
                    createFileRow("Main_Street_Apt_Lease.pdf", "Oct 12, 2023 \u2022 2.4 MB"),
                    createFileRow("Greenwood_Villa_Contract.pdf", "Aug 15, 2023 \u2022 3.1 MB"),
                    createFileRow("Urban_Loft_Agreement.pdf", "Jan 10, 2023 \u2022 2.8 MB"));
        } else {
            scrollContent.getChildren().add(new Label("No files in this category."));
        }

        layout.getChildren().addAll(header, scroll);
        getChildren().add(layout);
    }

    private HBox createFileRow(String name, String meta) {
        HBox row = new HBox(12);
        row.setPadding(new Insets(15));
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                + BORDER_COLOR + ";");
        row.setCursor(javafx.scene.Cursor.HAND);

        StackPane iconBox = new StackPane();
        iconBox.setPrefSize(40, 40);
        iconBox.setStyle("-fx-background-color: #ef444422; -fx-background-radius: 8;");
        Label icon = new Label("\ud83d\udcc4");
        icon.setTextFill(Color.web("#ef4444"));
        iconBox.getChildren().add(icon);

        VBox text = new VBox(2);
        Label n = new Label(name);
        n.setTextFill(Color.WHITE);
        n.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label m = new Label(meta);
        m.setTextFill(Color.web(TEXT_GRAY));
        m.setFont(Font.font(11));
        text.getChildren().addAll(n, m);
        HBox.setHgrow(text, Priority.ALWAYS);

        Label more = new Label("\u22ee");
        more.setTextFill(Color.web(TEXT_GRAY));
        more.setStyle("-fx-font-size: 18;");

        row.getChildren().addAll(iconBox, text, more);
        row.setOnMouseClicked(e -> MainApp.navigateTo(new DocumentPreviewView(name)));
        return row;
    }
}
