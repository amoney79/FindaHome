package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class OnboardingView extends StackPane {

    private static final String BACKGROUND_DARK = "#102216";
    private static final String PRIMARY = "#13ec5b";

    public OnboardingView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_CENTER);

        // Top Skip Button
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPadding(new Insets(40, 30, 0, 30));
        Label skipBtn = new Label("Skip");
        skipBtn.setTextFill(Color.web(PRIMARY));
        skipBtn.setFont(Font.font("System", FontWeight.BOLD, 16));
        skipBtn.setCursor(javafx.scene.Cursor.HAND);
        skipBtn.setOnMouseClicked(e -> MainApp.showHome());
        topBar.getChildren().add(skipBtn);

        // Content Area
        VBox content = new VBox(0);
        content.setAlignment(Pos.CENTER);
        VBox.setVgrow(content, Priority.ALWAYS);

        // Image Card
        StackPane imgContainer = new StackPane();
        imgContainer.setPadding(new Insets(0, 30, 0, 30));
        ImageView iv = new ImageView();
        try {
            iv.setImage(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuAnSVYL5mwSVy5JqWMgXYWFaToI8neouep7639-urPJU4o15_q0WJRmz1BltpF16l29DngavcR42kYnUEHgw8nIN4JQpOBX7A2-hMeWAP1Fps__5Cvwlh-5EEeBvT0wG5GfO9WAS0VgEvRbsi-0XrVM_EU4XoFBZIyGizvsv75UCmaTEJ53APlTSNZgp4oGGX8KAo2ZeFc4xmoAP36W3_dofiDX2e5uxW6IxwaDfJDwPrTIeNWOE9WedP_rXWwNtusr9zkkSMOCs-c",
                    370, 460, false, true, true));
        } catch (Exception e) {
        }
        iv.setFitWidth(370);
        iv.setFitHeight(460);
        iv.setPreserveRatio(false);
        Rectangle clip = new Rectangle(370, 460);
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        iv.setClip(clip);
        imgContainer.getChildren().add(iv);

        // Text Section
        VBox textSect = new VBox(15);
        textSect.setAlignment(Pos.CENTER);
        textSect.setPadding(new Insets(30, 40, 0, 40));

        TextFlow headline = new TextFlow();
        headline.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        Text findYour = new Text("Find Your ");
        findYour.setFill(Color.WHITE);
        findYour.setFont(Font.font("System", FontWeight.BOLD, 32));
        Text dreamHome = new Text("Dream Home");
        dreamHome.setFill(Color.web(PRIMARY));
        dreamHome.setFont(Font.font("System", FontWeight.BOLD, 32));
        headline.getChildren().addAll(findYour, dreamHome);

        Label body = new Label("Browse thousands of verified listings near your preferred locations with ease.");
        body.setTextFill(Color.web("#cbd5e1"));
        body.setFont(Font.font(16));
        body.setWrapText(true);
        body.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        textSect.getChildren().addAll(headline, body);

        content.getChildren().addAll(imgContainer, textSect);

        // Bottom Controls
        VBox footer = new VBox(30);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(0, 30, 60, 30));

        // Page Indicators
        HBox indicators = new HBox(10);
        indicators.setAlignment(Pos.CENTER);
        Region p1 = new Region();
        p1.setPrefSize(24, 8);
        p1.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 4;");
        Region p2 = new Region();
        p2.setPrefSize(8, 8);
        p2.setStyle("-fx-background-color: #334155; -fx-background-radius: 4;");
        Region p3 = new Region();
        p3.setPrefSize(8, 8);
        p3.setStyle("-fx-background-color: #334155; -fx-background-radius: 4;");
        indicators.getChildren().addAll(p1, p2, p3);

        Button nextBtn = new Button("Next");
        nextBtn.setGraphic(new Label("\u2192"));
        nextBtn.setContentDisplay(ContentDisplay.RIGHT);
        nextBtn.setMaxWidth(Double.MAX_VALUE);
        nextBtn.setPrefHeight(60);
        nextBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                + "; -fx-font-weight: bold; -fx-font-size: 18; -fx-background-radius: 12;");
        nextBtn.setOnAction(e -> MainApp.navigateToFullScreen(new OnboardingTwoView()));

        footer.getChildren().addAll(indicators, nextBtn);

        layout.getChildren().addAll(topBar, content, footer);
        getChildren().add(layout);
    }
}
