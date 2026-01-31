package com.findahome;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class PaymentProcessingView extends StackPane {

    private static final String OVERLAY_BG = "rgba(0, 0, 0, 0.85)";
    private static final String MODAL_BG = "#102216";
    private static final String PRIMARY = "#13ec5b";
    private static final String BORDER_COLOR = "rgba(255,255,255,0.1)";
    private static final String TEXT_GRAY = "rgba(255, 255, 255, 0.6)";

    public PaymentProcessingView() {
        setStyle("-fx-background-color: " + OVERLAY_BG + ";");

        VBox modal = new VBox(0);
        modal.setMaxWidth(360);
        modal.setMaxHeight(Region.USE_PREF_SIZE);
        modal.setStyle("-fx-background-color: " + MODAL_BG + "; -fx-background-radius: 20; -fx-border-color: "
                + BORDER_COLOR + "; -fx-border-width: 1; -fx-background-insets: 0;");
        modal.setAlignment(Pos.TOP_CENTER);

        // Handle
        Region handle = new Region();
        handle.setPrefSize(40, 4);
        handle.setMaxSize(40, 4);
        handle.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 2;");
        VBox.setMargin(handle, new Insets(12, 0, 0, 0));

        // Logo
        StackPane logoContainer = new StackPane();
        logoContainer.setPadding(new Insets(25, 0, 10, 0));

        Circle logoBg = new Circle(40, Color.WHITE);
        ImageView mpesaLogo = new ImageView();
        try {
            mpesaLogo.setImage(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuBu-vuQgF9FY003KFrGIHcF3Yl1CgsDhijNg-O7oIyyazYLcIoIpJsgr72DutlnX-CGyzVihe0DxXk0MU7aag3kn44ncHw4i7jB8ENBo4IhMIHlZDgBf8_bcqe70GGGrjsMlzm6FQNG-IrXNvZu2tB_vv4Hk6kr7O9LurXP9AgJFTQ26FUWEBMTx_HklygDnyLBcNVg-qKYeWMOP4kwHZuyqNXDJpVmcE2GOpjJhxRg5bsYJYCD3xBrgyjbCmZ744ObnlPy3CLfCD8",
                    50, 50, true, true));
        } catch (Exception e) {
        }
        mpesaLogo.setFitWidth(50);
        mpesaLogo.setFitHeight(50);

        logoContainer.getChildren().addAll(logoBg, mpesaLogo);

        Label title = new Label("Processing Payment");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 20));

        // Transaction Details
        VBox detailsBox = new VBox(12);
        detailsBox.setPadding(new Insets(20));
        VBox detailsCard = new VBox(10);
        detailsCard.setPadding(new Insets(15));
        detailsCard.setStyle(
                "-fx-background-color: rgba(255,255,255,0.05); -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.05);");

        detailsCard.getChildren().addAll(
                createDetailRow("Amount", "KSh 91,500.00", true),
                createDetailRow("Phone Number", "0712 *** 678", false));
        detailsBox.getChildren().add(detailsCard);

        // Spinner Area
        StackPane spinnerStack = new StackPane();
        spinnerStack.setPadding(new Insets(10, 0, 20, 0));

        Arc track = new Arc(0, 0, 30, 30, 0, 360);
        track.setFill(Color.TRANSPARENT);
        track.setStroke(Color.web(PRIMARY, 0.1));
        track.setStrokeWidth(4);
        track.setType(ArcType.OPEN);

        Arc spinner = new Arc(0, 0, 30, 30, 0, 90);
        spinner.setFill(Color.TRANSPARENT);
        spinner.setStroke(Color.web(PRIMARY));
        spinner.setStrokeWidth(4);
        spinner.setType(ArcType.OPEN);
        spinner.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);

        RotateTransition rt = new RotateTransition(Duration.seconds(1), spinner);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();

        Label phoneIcon = new Label("\uf3cd"); // Smartphone icon symbol
        phoneIcon.setStyle("-fx-font-family: 'Material Symbols Outlined'; -fx-font-size: 24;");
        phoneIcon.setTextFill(Color.web(PRIMARY));

        spinnerStack.getChildren().addAll(track, spinner, phoneIcon);

        Label instruction = new Label("Please check your phone for the M-Pesa PIN prompt to authorize this deposit.");
        instruction.setTextFill(Color.WHITE);
        instruction.setFont(Font.font(14));
        instruction.setWrapText(true);
        instruction.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        instruction.setPadding(new Insets(0, 30, 5, 30));

        Label subInstruction = new Label("Do not close this window until the transaction is complete.");
        subInstruction.setTextFill(Color.web(TEXT_GRAY));
        subInstruction.setFont(Font.font(12));
        subInstruction.setPadding(new Insets(0, 0, 20, 0));

        // Waiting Progress
        VBox progressBox = new VBox(8);
        progressBox.setPadding(new Insets(0, 25, 20, 25));
        Label waitLbl = new Label("WAITING FOR RESPONSE...");
        waitLbl.setTextFill(Color.web(TEXT_GRAY));
        waitLbl.setFont(Font.font("System", FontWeight.BOLD, 10));

        ProgressBar pb = new ProgressBar(0.45);
        pb.setPrefHeight(6);
        pb.setMaxWidth(Double.MAX_VALUE);
        pb.setStyle("-fx-accent: " + PRIMARY
                + "; -fx-control-inner-background: rgba(255,255,255,0.1); -fx-background-radius: 10;");

        progressBox.getChildren().addAll(waitLbl, pb);

        // Actions
        HBox footerActions = new HBox(0);
        footerActions.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 1 0 0 0;");

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(cancelBtn, Priority.ALWAYS);
        cancelBtn.setPrefHeight(56);
        cancelBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + TEXT_GRAY
                + "; -fx-font-weight: bold; -fx-background-radius: 0 0 0 20; -fx-border-color: " + BORDER_COLOR
                + " ; -fx-border-width: 0 1 0 0;");
        cancelBtn.setOnAction(e -> MainApp.navigateTo(new DepositPaymentView()));

        Button helpBtn = new Button("Help & Support");
        helpBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(helpBtn, Priority.ALWAYS);
        helpBtn.setPrefHeight(56);
        helpBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + PRIMARY
                + "; -fx-font-weight: bold; -fx-background-radius: 0 0 20 0;");

        footerActions.getChildren().addAll(cancelBtn, helpBtn);

        modal.getChildren().addAll(handle, logoContainer, title, detailsBox, spinnerStack, instruction, subInstruction,
                progressBox, footerActions);
        getChildren().add(modal);
        StackPane.setAlignment(modal, Pos.CENTER);

        // Simulation: Auto-complete after 3 seconds
        Timeline simulation = new Timeline(new KeyFrame(Duration.seconds(3.5), e -> {
            MainApp.navigateTo(new SuccessView("Payment Successful!",
                    "Your deposit has been received. You can now schedule your move-in date.", "Continue to Schedule"));
        }));
        simulation.play();
    }

    private HBox createDetailRow(String lbl, String val, boolean isBold) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);
        Label l = new Label(lbl);
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font(13));

        Region s = new Region();
        HBox.setHgrow(s, Priority.ALWAYS);

        Label v = new Label(val);
        v.setTextFill(Color.WHITE);
        v.setFont(Font.font("System", isBold ? FontWeight.BOLD : FontWeight.MEDIUM, isBold ? 16 : 14));

        row.getChildren().addAll(l, s, v);
        return row;
    }
}
