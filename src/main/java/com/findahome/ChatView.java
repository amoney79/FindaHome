package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChatView extends VBox {

    private static final String BACKGROUND_DARK = "#0d1117";
    private static final String CARD_BG = "#161b22";
    private static final String PRIMARY = "#135bec";

    public ChatView() {
        setSpacing(0);
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        // Top App Bar
        HBox topBar = new HBox(15);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-border-color: #30363d; -fx-border-width: 0 0 1 0;");
        Label back = new Label("<");
        back.setTextFill(Color.WHITE);
        back.setOnMouseClicked(e -> MainApp.showHome());
        VBox titleBox = new VBox(2);
        Label name = new Label("Sarah Johnson");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label status = new Label("\u2714 Verified Agent");
        status.setTextFill(Color.web(PRIMARY));
        status.setFont(Font.font(10));
        titleBox.getChildren().addAll(name, status);
        topBar.getChildren().addAll(back, titleBox);

        // Chat Area
        VBox chatArea = new VBox(15);
        chatArea.setPadding(new Insets(20));
        ScrollPane scroll = new ScrollPane(chatArea);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        chatArea.getChildren().addAll(
                createMessage("Hello! The apartment in Kilimani is available for viewing this weekend.", false),
                createMessage("Great, I'd like to see it on Saturday morning. Is 10 AM okay?", true),
                createMessage("Perfect, Saturday at 10 AM works. Please use the button below to confirm.", false));

        // Input Area
        HBox inputArea = new HBox(10);
        inputArea.setPadding(new Insets(15));
        inputArea.setAlignment(Pos.CENTER_LEFT);
        inputArea.setStyle("-fx-border-color: #30363d; -fx-border-width: 1 0 0 0;");
        TextField input = new TextField();
        input.setPromptText("Type a message...");
        input.setStyle("-fx-background-color: " + CARD_BG + "; -fx-text-fill: white; -fx-background-radius: 20;");
        HBox.setHgrow(input, Priority.ALWAYS);
        Button send = new Button("\u27a4");
        send.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: white; -fx-background-radius: 20;");
        send.setOnAction(e -> input.clear());
        inputArea.getChildren().addAll(input, send);

        getChildren().addAll(topBar, scroll, inputArea);
    }

    private HBox createMessage(String text, boolean isUser) {
        HBox box = new HBox();
        box.setAlignment(isUser ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        Label msg = new Label(text);
        msg.setWrapText(true);
        msg.setMaxWidth(280);
        msg.setPadding(new Insets(10, 15, 10, 15));
        if (isUser) {
            msg.setStyle(
                    "-fx-background-color: " + PRIMARY + "; -fx-text-fill: white; -fx-background-radius: 15 15 0 15;");
        } else {
            msg.setStyle("-fx-background-color: #30363d; -fx-text-fill: white; -fx-background-radius: 15 15 15 0;");
        }
        box.getChildren().add(msg);
        return box;
    }
}
