package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class LeaseAgreementView extends StackPane {

    private static final String BACKGROUND_DARK = "#101922";
    private static final String CARD_DARK = "#1a1f2e";
    private static final String PRIMARY = "#137fec"; // Using app brand color for consistency
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
    private static final String TEXT_GRAY = "#94a3b8";

    public LeaseAgreementView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);

        // Top App Bar
        HBox appBar = new HBox();
        appBar.setAlignment(Pos.CENTER_LEFT);
        appBar.setPadding(new Insets(15, 20, 15, 20));
        appBar.setStyle("-fx-background-color: rgba(16, 25, 34, 0.8); -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new ApplicationTrackerView()));

        Label title = new Label("Review Lease Agreement");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);

        Region spacer = new Region();
        spacer.setPrefWidth(28); // Balance the back button

        appBar.getChildren().addAll(backBtn, title, spacer);

        // Scrollable Content
        VBox scrollContent = new VBox(0);
        ScrollPane scroll = new ScrollPane(scrollContent);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Page Indicators
        HBox indicators = new HBox(8);
        indicators.setAlignment(Pos.CENTER);
        indicators.setPadding(new Insets(25, 0, 20, 0));

        Region dot1 = new Region();
        dot1.setPrefSize(24, 6);
        dot1.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 3;");
        Region dot2 = new Region();
        dot2.setPrefSize(6, 6);
        dot2.setStyle("-fx-background-color: #3b4754; -fx-background-radius: 3;");
        Region dot3 = new Region();
        dot3.setPrefSize(6, 6);
        dot3.setStyle("-fx-background-color: #3b4754; -fx-background-radius: 3;");

        indicators.getChildren().addAll(dot1, dot2, dot3);

        // Instruction Panel
        VBox instructionPanel = new VBox(10);
        instructionPanel.setPadding(new Insets(0, 20, 20, 20));

        VBox instrCard = new VBox(10);
        instrCard.setPadding(new Insets(20));
        instrCard.setStyle("-fx-background-color: " + CARD_DARK + "; -fx-background-radius: 16; -fx-border-color: "
                + BORDER_COLOR + ";");

        Label instrTitle = new Label("Review Terms");
        instrTitle.setTextFill(Color.WHITE);
        instrTitle.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label instrDesc = new Label(
                "Please read the document below carefully before proceeding to the digital signature.");
        instrDesc.setTextFill(Color.web(TEXT_GRAY));
        instrDesc.setFont(Font.font(13));
        instrDesc.setWrapText(true);

        Label helpBtn = new Label("Help Center \u27a4");
        helpBtn.setTextFill(Color.web(PRIMARY));
        helpBtn.setFont(Font.font("System", FontWeight.BOLD, 13));
        helpBtn.setPadding(new Insets(5, 0, 0, 0));
        helpBtn.setCursor(javafx.scene.Cursor.HAND);

        instrCard.getChildren().addAll(instrTitle, instrDesc, helpBtn);
        instructionPanel.getChildren().add(instrCard);

        // Financial Summary
        VBox summarySec = new VBox(15);
        summarySec.setPadding(new Insets(0, 20, 25, 20));

        VBox summaryCard = new VBox(0);
        summaryCard.setStyle("-fx-background-color: " + PRIMARY + "11; -fx-background-radius: 16; -fx-border-color: "
                + PRIMARY + "33;");

        Label sumHeader = new Label("FINANCIAL SUMMARY");
        sumHeader.setTextFill(Color.web(PRIMARY));
        sumHeader.setFont(Font.font("System", FontWeight.BOLD, 11));
        sumHeader.setPadding(new Insets(15, 20, 10, 20));

        VBox sumLines = new VBox(0);
        sumLines.getChildren().addAll(
                createSummaryRow("Monthly Rent", "$1,200/mo", true),
                createSummaryRow("Security Deposit", "$1,200 (Refundable)", true),
                createSummaryRow("Tenancy Period", "12 Months", false));
        sumLines.setPadding(new Insets(0, 0, 15, 0));

        summaryCard.getChildren().addAll(sumHeader, sumLines);
        summarySec.getChildren().add(summaryCard);

        // Document Section
        VBox docSec = new VBox(15);
        docSec.setPadding(new Insets(0, 20, 40, 20));

        Label docTitle = new Label("Lease Document");
        docTitle.setTextFill(Color.WHITE);
        docTitle.setFont(Font.font("System", FontWeight.BOLD, 20));

        VBox docHolder = new VBox(20);
        docHolder.setPadding(new Insets(25));
        docHolder.setStyle("-fx-background-color: rgba(255,255,255,0.03); -fx-background-radius: 16; -fx-border-color: "
                + BORDER_COLOR + ";");
        docHolder.setPrefHeight(400);

        ScrollPane docScroll = new ScrollPane(createDocumentContent());
        docScroll.setFitToWidth(true);
        docScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        docScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        docHolder.getChildren().add(docScroll);
        docSec.getChildren().addAll(docTitle, docHolder);

        scrollContent.getChildren().addAll(indicators, instructionPanel, summarySec, docSec);

        // Fixed Bottom Bar
        HBox footer = new HBox(12);
        footer.setPadding(new Insets(15, 20, 35, 20));
        footer.setStyle("-fx-background-color: " + BACKGROUND_DARK + "; -fx-border-color: " + BORDER_COLOR
                + "; -fx-border-width: 1 0 0 0;");

        Button dlBtn = new Button("\u2913");
        dlBtn.setPrefSize(56, 56);
        dlBtn.setStyle(
                "-fx-background-color: rgba(255,255,255,0.05); -fx-text-fill: white; -fx-background-radius: 12; -fx-font-size: 20; -fx-border-color: "
                        + BORDER_COLOR + ";");

        Button signBtn = new Button("Continue to Sign  \u270e");
        HBox.setHgrow(signBtn, Priority.ALWAYS);
        signBtn.setMaxWidth(Double.MAX_VALUE);
        signBtn.setPrefHeight(56);
        signBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: 900; -fx-font-size: 16; -fx-background-radius: 12;");
        signBtn.setOnAction(e -> MainApp.navigateTo(new DigitalSignatureView()));

        footer.getChildren().addAll(dlBtn, signBtn);

        layout.getChildren().add(scroll);

        getChildren().addAll(layout, appBar, footer);
        StackPane.setAlignment(appBar, Pos.TOP_CENTER);
        StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
    }

    private HBox createSummaryRow(String lbl, String val, boolean border) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10, 20, 10, 20));
        if (border)
            row.setStyle("-fx-border-color: " + PRIMARY + "22; -fx-border-width: 0 0 1 0;");

        Label l = new Label(lbl);
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font(13));

        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);

        Label v = new Label(val);
        v.setTextFill(Color.WHITE);
        v.setFont(Font.font("System", FontWeight.BOLD, 14));

        row.getChildren().addAll(l, s, v);
        return row;
    }

    private VBox createDocumentContent() {
        VBox doc = new VBox(20);

        Label title = new Label("RESIDENTIAL LEASE AGREEMENT");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setUnderline(true);
        title.setStyle("-fx-underline: " + PRIMARY + ";");

        doc.getChildren().add(title);

        doc.getChildren().addAll(
                createLegalSection("1. PARTIES",
                        "This Lease Agreement is made on October 24, 2023, between FindaHome Realty (\"Landlord\") and the individual identified in the application (\"Tenant\")."),
                createLegalSection("2. PREMISES",
                        "The Landlord leases to the Tenant the residential property located at: 425 Oak Avenue, Nairobi, Kenya. The premises include the fixtures and appliances listed in the inventory report."),
                createLegalSection("3. TERM",
                        "The term of this lease shall be for 12 months, commencing on November 1st, 2023, and ending on October 31st, 2024. Tenant must provide 30 days notice of intent to vacate."),
                createLegalSection("4. RENT PAYMENTS",
                        "Rent is due on the 1st of each month. A late fee of $50 will be applied if payment is received after the 5th of the month. Payment should be made via the FindaHome digital wallet."),
                createLegalSection("5. MAINTENANCE",
                        "Tenant shall keep the premises in clean and sanitary condition. Landlord is responsible for structural repairs and electrical plumbing issues not caused by tenant negligence."));

        Label footerText = new Label("End of visible section - Scroll to see all terms");
        footerText.setTextFill(Color.web("#3b4754"));
        footerText.setFont(Font.font(10));
        VBox footBox = new VBox(footerText);
        footBox.setAlignment(Pos.CENTER);
        footBox.setPadding(new Insets(20, 0, 0, 0));
        footBox.setStyle(
                "-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 1 0 0 0; -fx-border-style: dashed;");

        doc.getChildren().add(footBox);

        return doc;
    }

    private VBox createLegalSection(String head, String content) {
        VBox v = new VBox(8);
        Label h = new Label(head);
        h.setTextFill(Color.web(PRIMARY));
        h.setFont(Font.font("System", FontWeight.BOLD, 14));

        Text text = new Text(content);
        text.setFill(Color.web("#cbd5e1"));
        text.setFont(Font.font(12));
        TextFlow flow = new TextFlow(text);
        flow.setLineSpacing(4);

        v.getChildren().addAll(h, flow);
        return v;
    }
}
