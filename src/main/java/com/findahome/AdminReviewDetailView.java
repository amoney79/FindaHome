package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class AdminReviewDetailView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String TEXT_GREEN_MUTED = "#9db9a6";

    public AdminReviewDetailView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox mainLayout = new VBox(0);

        // Top Navigation Bar
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: #1e2922; -fx-border-width: 0 0 1 0;");

        Label back = new Label("\u2039"); // Back arrow
        back.setTextFill(Color.WHITE);
        back.setStyle("-fx-font-size: 24; -fx-padding: 0 10 0 0; -fx-cursor: hand;");
        back.setOnMouseClicked(e -> MainApp.navigateTo(new AdminDashboardView()));

        VBox titleBox = new VBox(0);
        Label title = new Label("Agent Review");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        Label id = new Label("AGENT ID: 8829");
        id.setTextFill(Color.web(TEXT_GREEN_MUTED));
        id.setStyle("-fx-font-size: 10; -fx-font-weight: bold;");
        titleBox.getChildren().addAll(title, id);

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        StackPane verifiedBadge = new StackPane();
        verifiedBadge.setPrefSize(40, 40);
        verifiedBadge.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 20;");
        Label vIcon = new Label("\ud83d\udee1\ufe0f");
        vIcon.setTextFill(Color.web(PRIMARY));
        verifiedBadge.getChildren().add(vIcon);

        header.getChildren().addAll(back, titleBox, headerSpacer, verifiedBadge);

        // Scrollable Content
        VBox scrollContent = new VBox(30);
        scrollContent.setPadding(new Insets(25, 20, 120, 20));

        // Agent Header Section
        VBox agentHeader = new VBox(5);
        Label agentName = new Label("Johnathan Doe");
        agentName.setTextFill(Color.WHITE);
        agentName.setFont(Font.font("System", FontWeight.BOLD, 26));
        Label agentMeta = new Label("Registered on Oct 24, 2023 \u2022 Nairobi, KE");
        agentMeta.setTextFill(Color.web(TEXT_GREEN_MUTED));
        agentMeta.setFont(Font.font(14));
        agentHeader.getChildren().addAll(agentName, agentMeta);

        // Agent Documents Section
        VBox docSection = new VBox(20);
        Label docLabel = new Label("AGENT DOCUMENTS");
        docLabel.setTextFill(Color.web(TEXT_GREEN_MUTED, 0.7));
        docLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10;");

        HBox docGrid = new HBox(12);
        docGrid.getChildren().addAll(
                createDocumentCard("National ID", "\ud83d\udced",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuC73-eHZQps8SWZp7qyeetPjE9GyosuXivOOUm9d0WaJ21fp6zMfL_vW-uk9pr5Bupmyaihb0i-codmiT85vnqUi92ddkiQgkpRDEBf7_2U7rE2k7ZsNG-VOAl-fqpZ7pymQ_TC1jNsLHrhBe5nFJvGfQw4tbOktC59qCo2wDtuZjIM682wpsR6ivswVGHSBvTr3GDOEh4HLY2yqypdkd7SaaOM59BXB-V2Nu9B2TSC7ouKibuK_RU2AGT3AFXDVU34s4lLVSaVoNE"),
                createDocumentCard("RE License", "\ud83c\udf96\ufe0f",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuArF610VSmf5o2Q6FZj28SmyuP4mlSMQYce6qBfbo4iAGg1ZsEkSlM42Z7Yd8soywdlUGSUKotZvDXCE4mXCTp8Oe0BKtN2Foqlb7e-fQ360n4ssoBQwymJPl280ccdIJ2L0R02vx5sCE9yHibPKR46cS0kxloyN3uz1XC6QP-o6XnUPZ3hIxI-QGP7ajBc9IAngm7WCGKHB-GoHVpEgBqSKvBZTo2AzzuqFrjJyGQRZrOYYBeAITNFwJ6fRT2cLmi03YAxJSYGnzI"));
        docSection.getChildren().addAll(docLabel, docGrid);

        // Identity Verification
        VBox idSection = new VBox(20);
        Label idHeader = new Label("IDENTITY VERIFICATION");
        idHeader.setTextFill(Color.web(TEXT_GREEN_MUTED, 0.7));
        idHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 10;");

        VBox livenessCard = new VBox(0);
        livenessCard.setStyle(
                "-fx-background-color: #1c271f; -fx-background-radius: 16; -fx-border-color: #2a382e; -fx-border-radius: 16; -fx-overflow: hidden;");

        StackPane selfieImgFrame = new StackPane();
        selfieImgFrame.setPrefHeight(200);
        ImageView selfie = new ImageView();
        try {
            selfie.setImage(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuDcRgfLbO2irMC2cuPYRW9cfN5564HRn-HL0yLU_dSh14KCAGoLyoSA6k3gjE7paJmzdo7SUu9O9rj9fw62RGZIhgm7Eedq2njsVaHelnx_Ox3hWREDyvHzE-tWBOmGlE9e9wQnkjDbxzThKJYAxTft1t_bcKYJZs6qecmGjSRQ3pSbTV44QcDxFjfLAYIRSTticmW-h3K9yFU1L0zdsXtkcFgf7EXTJLjtShDvZhnPArE33o3wk5NG6gjBon1VE_1YD0oBJ8K0Mis",
                    430, 200, false, true));
        } catch (Exception e) {
        }
        selfie.setFitWidth(400);
        selfie.setFitHeight(200);
        selfie.setPreserveRatio(false);
        Rectangle selfieClip = new Rectangle(400, 200);
        selfieClip.setArcWidth(16);
        selfieClip.setArcHeight(16);
        selfie.setClip(selfieClip);

        HBox liveBadge = new HBox(6);
        liveBadge.setAlignment(Pos.CENTER_LEFT);
        liveBadge.setPadding(new Insets(4, 10, 4, 10));
        liveBadge.setStyle("-fx-background-color: rgba(19, 236, 91, 0.9); -fx-background-radius: 20;");
        Circle pulse = new Circle(3, Color.web(BACKGROUND_DARK));
        Label liveText = new Label("LIVE");
        liveText.setStyle("-fx-text-fill: " + BACKGROUND_DARK + "; -fx-font-weight: bold; -fx-font-size: 10;");
        liveBadge.getChildren().addAll(pulse, liveText);
        StackPane.setAlignment(liveBadge, Pos.TOP_LEFT);
        StackPane.setMargin(liveBadge, new Insets(12));

        selfieImgFrame.getChildren().addAll(selfie, liveBadge);

        VBox livenessContent = new VBox(5);
        livenessContent.setPadding(new Insets(15));
        HBox lTitleBox = new HBox();
        Label lTitleLbl = new Label("Liveness Check");
        lTitleLbl.setTextFill(Color.WHITE);
        lTitleLbl.setFont(Font.font("System", FontWeight.BOLD, 18));
        Region lSpacer = new Region();
        HBox.setHgrow(lSpacer, Priority.ALWAYS);
        Label lMatchLbl = new Label("98% Match");
        lMatchLbl.setTextFill(Color.web(PRIMARY));
        lMatchLbl.setFont(Font.font("System", FontWeight.BOLD, 14));
        lTitleBox.getChildren().addAll(lTitleLbl, lSpacer, lMatchLbl);

        Label lDescLbl = new Label(
                "Selfie matches ID document biometric data perfectly. Verification timestamp: 14:02:11 GMT+3");
        lDescLbl.setTextFill(Color.web(TEXT_GREEN_MUTED));
        lDescLbl.setWrapText(true);
        lDescLbl.setFont(Font.font(13));
        livenessContent.getChildren().addAll(lTitleBox, lDescLbl);
        livenessCard.getChildren().addAll(selfieImgFrame, livenessContent);

        idSection.getChildren().addAll(idHeader, livenessCard);

        // Review Notes
        VBox notesSection = new VBox(15);
        Label notesHeader = new Label("REVIEW NOTES");
        notesHeader.setTextFill(Color.web(TEXT_GREEN_MUTED, 0.7));
        notesHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 10;");

        StackPane notesContainer = new StackPane();
        TextArea notesArea = new TextArea();
        notesArea.setPromptText("Enter reason for rejection or internal audit notes...");
        notesArea.setPrefHeight(120);
        notesArea.setWrapText(true);
        notesArea.setStyle(
                "-fx-background-color: #1c271f; -fx-control-inner-background: #1c271f; -fx-text-fill: white; -fx-prompt-text-fill: #555; -fx-background-radius: 12; -fx-border-color: #2a382e; -fx-border-radius: 12; -fx-padding: 10;");

        Label autoSave = new Label("AUTOSAVE ENABLED");
        autoSave.setTextFill(Color.web("#555"));
        autoSave.setStyle("-fx-font-family: monospace; -fx-font-size: 9;");
        StackPane.setAlignment(autoSave, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(autoSave, new Insets(0, 15, 10, 0));

        notesContainer.getChildren().addAll(notesArea, autoSave);
        notesSection.getChildren().addAll(notesHeader, notesContainer);

        scrollContent.getChildren().addAll(agentHeader, docSection, idSection, notesSection);

        ScrollPane scrollScroll = new ScrollPane(scrollContent);
        scrollScroll.setFitToWidth(true);
        scrollScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Fixed Action Footer
        HBox footer = new HBox(15);
        footer.setPadding(new Insets(20, 20, 40, 20));
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK
                + "f2; -fx-border-color: #1e2922; -fx-border-width: 1 0 0 0;");

        Button rejectBtn = new Button("\u2715 Reject Request");
        HBox.setHgrow(rejectBtn, Priority.ALWAYS);
        rejectBtn.setMaxWidth(Double.MAX_VALUE);
        rejectBtn.setPrefHeight(56);
        rejectBtn.setStyle(
                "-fx-background-color: rgba(239, 68, 68, 0.1); -fx-text-fill: #ef4444; -fx-border-color: rgba(239, 68, 68, 0.2); -fx-background-radius: 16; -fx-border-radius: 16; -fx-font-weight: bold; -fx-font-size: 15;");
        rejectBtn.setOnAction(e -> showRejectionModal());

        Button approveBtn = new Button("\u2713 Approve");
        HBox.setHgrow(approveBtn, Priority.ALWAYS);
        approveBtn.setMaxWidth(Double.MAX_VALUE);
        approveBtn.setPrefHeight(56);
        approveBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-background-radius: 16; -fx-font-weight: bold; -fx-font-size: 15; -fx-effect: dropshadow(three-pass-box, rgba(19, 236, 91, 0.3), 15, 0, 0, 4);");
        approveBtn.setOnAction(e -> MainApp.navigateTo(new AdminDashboardView()));

        footer.getChildren().addAll(rejectBtn, approveBtn);

        mainLayout.getChildren().addAll(header, scrollScroll);
        getChildren().addAll(mainLayout, footer);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
    }

    private void showRejectionModal() {
        StackPane modalDimmer = new StackPane();
        modalDimmer.setStyle("-fx-background-color: rgba(0,0,0,0.85);");

        VBox bottomSheet = new VBox(0);
        bottomSheet.setMaxWidth(430);
        bottomSheet.setStyle(
                "-fx-background-color: #18181b; -fx-background-radius: 30 30 0 0; -fx-effect: dropshadow(three-pass-box, black, 40, 0, 0, -10);");
        StackPane.setAlignment(bottomSheet, Pos.BOTTOM_CENTER);

        // Handle
        Region handle = new Region();
        handle.setPrefSize(40, 4);
        handle.setStyle("-fx-background-color: #3f3f46; -fx-background-radius: 2;");
        StackPane handleContainer = new StackPane(handle);
        handleContainer.setPadding(new Insets(12, 0, 8, 0));

        // Content
        VBox modalContent = new VBox(25);
        modalContent.setPadding(new Insets(20, 25, 40, 25));

        VBox titleBox = new VBox(5);
        Label modalTitle = new Label("Rejection Reason");
        modalTitle.setTextFill(Color.WHITE);
        modalTitle.setFont(Font.font("System", FontWeight.BOLD, 24));
        Label subText = new Label("Select all that apply to notify the agent.");
        subText.setTextFill(Color.web("#a1a1aa"));
        subText.setFont(Font.font(14));
        titleBox.getChildren().addAll(modalTitle, subText);

        VBox reasons = new VBox(0);
        reasons.getChildren().addAll(
                createReasonRow("Blurry Document"),
                createReasonRow("Expired ID"),
                createReasonRow("Name Mismatch"),
                createReasonRow("Invalid License"));

        VBox customSection = new VBox(10);
        Label customLabel = new Label("CUSTOM FEEDBACK");
        customLabel.setTextFill(Color.web("#d4d4d8"));
        customLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10;");

        TextArea customInput = new TextArea();
        customInput.setPromptText("Provide additional details for the agent (optional)...");
        customInput.setPrefHeight(120);
        customInput.setWrapText(true);
        customInput.setStyle(
                "-fx-background-color: #27272a; -fx-control-inner-background: #27272a; -fx-text-fill: white; -fx-background-radius: 12; -fx-border-color: #3f3f46; -fx-border-radius: 12; -fx-padding: 10;");
        customSection.getChildren().addAll(customLabel, customInput);

        VBox modalActions = new VBox(12);
        Button sendBtn = new Button("Send Rejection");
        sendBtn.setMaxWidth(Double.MAX_VALUE);
        sendBtn.setPrefHeight(56);
        sendBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16; -fx-background-radius: 16;");
        sendBtn.setOnAction(e -> MainApp.navigateTo(new AdminDashboardView()));

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setMaxWidth(Double.MAX_VALUE);
        cancelBtn.setPrefHeight(45);
        cancelBtn.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15;");
        cancelBtn.setOnAction(e -> getChildren().remove(modalDimmer));

        modalActions.getChildren().addAll(sendBtn, cancelBtn);

        modalContent.getChildren().addAll(titleBox, reasons, customSection, modalActions);
        bottomSheet.getChildren().addAll(handleContainer, modalContent);
        modalDimmer.getChildren().add(bottomSheet);

        getChildren().add(modalDimmer);
    }

    private HBox createReasonRow(String reason) {
        HBox row = new HBox(15);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(15, 0, 15, 0));
        row.setStyle("-fx-border-color: #27272a; -fx-border-width: 0 0 1 0;");

        Label lbl = new Label(reason);
        lbl.setTextFill(Color.WHITE);
        lbl.setFont(Font.font("System", FontWeight.MEDIUM, 16));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        CheckBox cb = new CheckBox();
        cb.setStyle("-fx-mark-color: " + PRIMARY + "; -fx-box-border: #3f3f46; -fx-background-color: transparent;");

        row.getChildren().addAll(lbl, spacer, cb);
        row.setOnMouseClicked(e -> cb.setSelected(!cb.isSelected()));
        row.setCursor(javafx.scene.Cursor.HAND);

        return row;
    }

    private VBox createDocumentCard(String title, String icon, String imgUrl) {
        VBox box = new VBox(12);
        box.setPrefWidth(190);

        StackPane imgWrapper = new StackPane();
        imgWrapper.setPrefHeight(250);

        ImageView iv = new ImageView();
        try {
            iv.setImage(new Image(imgUrl, 190, 250, false, true));
        } catch (Exception e) {
        }
        iv.setFitWidth(190);
        iv.setFitHeight(250);
        iv.setPreserveRatio(false);
        Rectangle clip = new Rectangle(190, 250);
        clip.setArcWidth(24);
        clip.setArcHeight(24);
        iv.setClip(clip);

        imgWrapper.getChildren().add(iv);
        imgWrapper.setStyle("-fx-background-radius: 12; -fx-border-color: #2a382e; -fx-border-radius: 12;");

        HBox meta = new HBox(8);
        meta.setAlignment(Pos.CENTER_LEFT);
        Label iLbl = new Label(icon);
        iLbl.setTextFill(Color.web(TEXT_GREEN_MUTED));
        iLbl.setStyle("-fx-font-size: 14;");
        Label tLbl = new Label(title);
        tLbl.setTextFill(Color.WHITE);
        tLbl.setFont(Font.font("System", FontWeight.MEDIUM, 14));
        meta.getChildren().addAll(iLbl, tLbl);

        box.getChildren().addAll(imgWrapper, meta);
        return box;
    }
}
