package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChatView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String BORDER_COLOR = "rgba(19, 236, 91, 0.1)";
    private static final String BUBBLE_AGENT = "#28392e";
    private static final String TEXT_GRAY = "#9db9a6";

    public ChatView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Header
        VBox header = new VBox(0);
        header.setStyle("-fx-background-color: " + BACKGROUND_DARK + "f2; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 0 0 1 0;");

        HBox topArea = new HBox(12);
        topArea.setAlignment(Pos.CENTER_LEFT);
        topArea.setPadding(new Insets(10, 15, 10, 15));

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new HelpSupportView()));

        HBox agentInfo = new HBox(12);
        agentInfo.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(agentInfo, Priority.ALWAYS);

        StackPane avatarStack = new StackPane();
        ImageView avatar = new ImageView();
        try {
            avatar.setImage(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuBOEGO14JJe61XPORpRHzBsj9mYWtQfEDNY8zTTUFXSMQF82XOa4wswYZGnTk_ay4ULt2pLYqnaiWwPMacyCDF6HbOBYqhtzKbDbtj4FTif_PXDYE944bukUL5_pm41F2MbKtXwUH0G0goNDAzpZVGPBL-kBnNdsIR4jvRQehDtIN-BUJmN_O4nlw4E8aN4wux1q6ZpCKC6ddRDtyR20SeZ7FZz7iWPAChg50Vb_cKCFCVjJqbsCZQ0qt6pk8gXVqnvL0z6vhR9IEY",
                    40, 40, true, true));
        } catch (Exception e) {
        }
        avatar.setFitWidth(40);
        avatar.setFitHeight(40);
        Circle clip = new Circle(20, 20, 20);
        avatar.setClip(clip);

        Circle statusDot = new Circle(6, Color.web(PRIMARY));
        statusDot.setStroke(Color.web(BACKGROUND_DARK));
        statusDot.setStrokeWidth(2);
        StackPane.setAlignment(statusDot, Pos.BOTTOM_RIGHT);
        avatarStack.getChildren().addAll(avatar, statusDot);

        VBox nameBox = new VBox(0);
        Label name = new Label("Sarah from FindaHome");
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font("System", FontWeight.BOLD, 15));
        Label online = new Label("ONLINE");
        online.setTextFill(Color.web(PRIMARY));
        online.setFont(Font.font("System", FontWeight.BOLD, 9));
        nameBox.getChildren().addAll(name, online);
        agentInfo.getChildren().addAll(avatarStack, nameBox);

        Label infoBtn = new Label("\u24d8");
        infoBtn.setTextFill(Color.WHITE);
        infoBtn.setStyle("-fx-font-size: 18; -fx-cursor: hand;");

        topArea.getChildren().addAll(backBtn, agentInfo, infoBtn);

        HBox slaBox = new HBox();
        slaBox.setAlignment(Pos.CENTER);
        slaBox.setPadding(new Insets(0, 0, 10, 0));
        Label sla = new Label("Average response time: 2 mins");
        sla.setStyle("-fx-background-color: " + PRIMARY + "1a; -fx-text-fill: " + PRIMARY
                + "; -fx-font-size: 10; -fx-font-weight: bold; -fx-padding: 4 12; -fx-background-radius: 20;");
        slaBox.getChildren().add(sla);

        header.getChildren().addAll(topArea, slaBox);

        // Chat History
        VBox chatHistory = new VBox(25);
        chatHistory.setPadding(new Insets(20));
        ScrollPane scroll = new ScrollPane(chatHistory);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Encryption info
        VBox encryptBox = new VBox(5);
        encryptBox.setAlignment(Pos.CENTER);
        Label dateLbl = new Label("TODAY");
        dateLbl.setTextFill(Color.web(TEXT_GRAY));
        dateLbl.setFont(Font.font("System", FontWeight.BOLD, 10));
        HBox encryptRow = new HBox(5);
        encryptRow.setAlignment(Pos.CENTER);
        Label lockIcon = new Label("\ud83d\udd12");
        lockIcon.setTextFill(Color.web(TEXT_GRAY));
        lockIcon.setOpacity(0.6);
        Label lockText = new Label("Messages are end-to-end encrypted");
        lockText.setTextFill(Color.web(TEXT_GRAY));
        lockText.setOpacity(0.6);
        lockText.setFont(Font.font(10));
        encryptRow.getChildren().addAll(lockIcon, lockText);
        encryptBox.getChildren().addAll(dateLbl, encryptRow);

        chatHistory.getChildren().addAll(encryptBox,
                createAgentMessage("Hi! How can I help you with your property search today?"),
                createAgentMessage("Please select a topic or type your message below."),
                createUserMessage("I need help with a listing I saw in Nairobi."));

        // Footer
        VBox footer = new VBox(0);
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");

        // Quick Replies
        VBox quickRepliesBox = new VBox(8);
        quickRepliesBox.setPadding(new Insets(12, 0, 12, 0));
        Label qrTitle = new Label("QUICK REPLIES");
        qrTitle.setTextFill(Color.web(TEXT_GRAY));
        qrTitle.setFont(Font.font("System", FontWeight.BOLD, 10));
        qrTitle.setPadding(new Insets(0, 15, 0, 15));

        HBox qrChips = new HBox(10);
        qrChips.setPadding(new Insets(0, 15, 0, 15));
        qrChips.getChildren().addAll(
                createQuickReply("Where is my refund?"),
                createQuickReply("Report a listing"),
                createQuickReply("Change my password"));
        ScrollPane qrPane = new ScrollPane(qrChips);
        qrPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        qrPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        qrPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        quickRepliesBox.getChildren().addAll(qrTitle, qrPane);

        // Input Bar
        HBox inputBar = new HBox(10);
        inputBar.setAlignment(Pos.CENTER_LEFT);
        inputBar.setPadding(new Insets(10, 15, 25, 15));

        HBox inputFrame = new HBox(10);
        inputFrame.setAlignment(Pos.CENTER_LEFT);
        inputFrame.setPadding(new Insets(0, 5, 0, 5));
        inputFrame.setStyle(
                "-fx-background-color: #1a2e20; -fx-background-radius: 12; -fx-border-color: " + PRIMARY + "1a;");
        HBox.setHgrow(inputFrame, Priority.ALWAYS);
        inputFrame.setPrefHeight(48);

        Label addBtn = new Label("\u2295");
        addBtn.setTextFill(Color.web(TEXT_GRAY));
        addBtn.setStyle("-fx-font-size: 22; -fx-cursor: hand;");

        TextField inputField = new TextField();
        inputField.setPromptText("Type a message...");
        inputField.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: rgba(157, 185, 166, 0.4);");
        HBox.setHgrow(inputField, Priority.ALWAYS);

        Button sendBtn = new Button("\u27a4");
        sendBtn.setPrefSize(36, 36);
        sendBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-background-radius: 18; -fx-font-weight: bold;");

        inputFrame.getChildren().addAll(addBtn, inputField, sendBtn);
        inputBar.getChildren().add(inputFrame);

        footer.getChildren().addAll(quickRepliesBox, inputBar);

        layout.getChildren().addAll(header, scroll);
        getChildren().addAll(layout, footer);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
    }

    private HBox createAgentMessage(String text) {
        HBox row = new HBox(10);
        row.setAlignment(Pos.BOTTOM_LEFT);

        ImageView av = new ImageView();
        try {
            av.setImage(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuC1ZXXv5PGZfh4TevaZzNf2VHbrHW45DGqGeqyWfrbOb3cVjVn7l3Vp_981Wy_E0dZc80MAgaPvgMLqlKU3JKIkgrIa2WOYTJyFcbb06NcCuGy6UoG19UmZ-QhCL46ByurlCqLp_WWdDNAYG9nRMFSmBdEM4bFy3V18BlWHEtBhbUVdNuanLde6v_sGz-AfLb20pHudPYLTeGd9nUdp5UhFJT9jiX0oDnv-S1IZ4IzNPS2v5FTE-PIB_ZZnfoUkbj8N0pm8uWMH5wM",
                    32, 32, true, true));
        } catch (Exception e) {
        }
        av.setFitWidth(32);
        av.setFitHeight(32);
        Circle clip = new Circle(16, 16, 16);
        av.setClip(clip);

        VBox content = new VBox(4);
        Label sender = new Label("Sarah");
        sender.setTextFill(Color.web(TEXT_GRAY));
        sender.setFont(Font.font("System", FontWeight.BOLD, 10));

        Label msg = new Label(text);
        msg.setTextFill(Color.WHITE);
        msg.setWrapText(true);
        msg.setMaxWidth(280);
        msg.setStyle(
                "-fx-background-color: " + BUBBLE_AGENT + "; -fx-background-radius: 16 16 16 2; -fx-padding: 12 16;");

        Label time = new Label("09:41 AM");
        time.setTextFill(Color.web(TEXT_GRAY));
        time.setOpacity(0.6);
        time.setFont(Font.font(9));

        content.getChildren().addAll(sender, msg, time);
        row.getChildren().addAll(av, content);
        return row;
    }

    private HBox createUserMessage(String text) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_RIGHT);

        VBox content = new VBox(4);
        content.setAlignment(Pos.TOP_RIGHT);

        Label msg = new Label(text);
        msg.setTextFill(Color.web(BACKGROUND_DARK));
        msg.setFont(Font.font("System", FontWeight.MEDIUM, 14));
        msg.setWrapText(true);
        msg.setMaxWidth(280);
        msg.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 16 16 2 16; -fx-padding: 12 16;");

        HBox meta = new HBox(4);
        meta.setAlignment(Pos.CENTER_RIGHT);
        Label time = new Label("09:42 AM");
        time.setTextFill(Color.web(TEXT_GRAY));
        time.setOpacity(0.6);
        time.setFont(Font.font(9));
        Label check = new Label("\u2713\u2713");
        check.setTextFill(Color.web(PRIMARY));
        check.setFont(Font.font(10));
        meta.getChildren().addAll(time, check);

        content.getChildren().addAll(msg, meta);
        row.getChildren().add(content);
        return row;
    }

    private Button createQuickReply(String text) {
        Button b = new Button(text);
        b.setStyle("-fx-background-color: rgba(19, 236, 91, 0.05); -fx-text-fill: " + PRIMARY
                + "; -fx-border-color: rgba(19, 236, 91, 0.4); -fx-border-radius: 20; -fx-background-radius: 20; -fx-font-size: 11; -fx-font-weight: bold; -fx-padding: 6 15;");
        b.setCursor(javafx.scene.Cursor.HAND);
        return b;
    }
}
