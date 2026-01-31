package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class PushNotificationView extends StackPane {

    private static final String PRIMARY = "#13ec5b";

    public PushNotificationView() {
        setStyle("-fx-background-color: #f6f6f8;"); // Light bg for the "desktop" behind phone

        // The Phone Frame
        StackPane phoneFrame = new StackPane();
        phoneFrame.setMaxSize(375, 812);
        phoneFrame.setMinSize(375, 812);
        phoneFrame.setStyle(
                "-fx-background-color: black; -fx-background-radius: 60; -fx-border-color: #27272a; -fx-border-width: 8; -fx-border-radius: 60; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 20, 0, 0, 10);");

        // Wallpaper Layer
        StackPane wallpaper = new StackPane();
        try {
            ImageView bgImg = new ImageView(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuAQ0cq7gfvlsBtNu-7XsBusnGOWpJXL6Si-Eukj-9VhLXL41VP3qBi6Tlsuv_J87kft79PRXJI2NHRp44erp7YBrZukDTvgK27KokeWwasmawCJL7HO1bOfuYq8jheyLra4YQ65ri6IOSPIpy_EsuXz76WCIurG5zucTm5UEYgH3vkGrF9eXGBNE7QQBoio1otuOqormRc5Q_GqFVihkgUb8qg0hlqOwgk0gVn7KFDqvjXB6E1K5uuvnRP5FoPl0l1k0O9tHqKdSjU",
                    375, 812, true, true));
            bgImg.setOpacity(0.4);
            wallpaper.getChildren().add(bgImg);
        } catch (Exception e) {
        }

        Rectangle gradient = new Rectangle(375, 812);
        gradient.setArcWidth(45);
        gradient.setArcHeight(45); // Match inner radius roughly
        gradient.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#312e81", 0.9)),
                new Stop(0.5, Color.web("#0f172a", 0.9)),
                new Stop(1, Color.BLACK)));

        wallpaper.getChildren().add(gradient);

        // Clip wallpaper to rounded corners
        Rectangle clip = new Rectangle(360, 796);
        clip.setArcWidth(50);
        clip.setArcHeight(50);
        wallpaper.setClip(clip);

        phoneFrame.getChildren().add(wallpaper);

        // Content Container
        VBox content = new VBox();
        content.setPadding(new Insets(16, 24, 24, 24));
        content.setAlignment(Pos.TOP_CENTER);

        // Status Bar (Simulated)
        HBox statusBar = new HBox();
        statusBar.setPadding(new Insets(0, 8, 0, 8));
        statusBar.setAlignment(Pos.CENTER_LEFT);
        Label timeSmall = new Label("9:41");
        timeSmall.setTextFill(Color.WHITE);
        timeSmall.setFont(Font.font("System", FontWeight.BOLD, 14));
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox icons = new HBox(5);
        // Simple shape icons for signal/wifi/battery
        icons.getChildren().addAll(
                createIconShape("\ud83d\udcf6"), // Signal
                createIconShape("\ud83d\udcfb"), // Wifi
                createIconShape("\ud83d\udd0b") // Battery
        );
        statusBar.getChildren().addAll(timeSmall, spacer1, icons);

        // Lock Screen Clock
        VBox clockBox = new VBox(5);
        clockBox.setAlignment(Pos.CENTER);
        clockBox.setPadding(new Insets(40, 0, 40, 0));
        Label bigTime = new Label("9:41");
        bigTime.setTextFill(Color.web("rgba(255,255,255,0.9)"));
        bigTime.setFont(Font.font("System", FontWeight.BOLD, 72));
        Label date = new Label("Tuesday, October 24");
        date.setTextFill(Color.web("rgba(255,255,255,0.9)"));
        date.setFont(Font.font("System", FontWeight.MEDIUM, 18));
        clockBox.getChildren().addAll(bigTime, date);

        // Notification Card
        VBox notifCard = new VBox(12);
        notifCard.setPadding(new Insets(16));
        notifCard.setStyle(
                "-fx-background-color: rgba(28, 31, 39, 0.85); -fx-background-radius: 12; -fx-border-color: rgba(255,255,255,0.1); -fx-border-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");

        // App Header
        HBox appHeader = new HBox(8);
        appHeader.setAlignment(Pos.CENTER_LEFT);
        StackPane appIcon = new StackPane();
        appIcon.setPrefSize(24, 24);
        appIcon.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 6;");
        Label homeIc = new Label("\u2302"); // scale icon
        homeIc.setTextFill(Color.WHITE);
        appIcon.getChildren().add(homeIc);

        Label appName = new Label("FINDAHOME");
        appName.setTextFill(Color.web("rgba(255,255,255,0.8)"));
        appName.setFont(Font.font("System", FontWeight.BOLD, 10));

        Region sp2 = new Region();
        HBox.setHgrow(sp2, Priority.ALWAYS);
        Label notifTime = new Label("just now");
        notifTime.setTextFill(Color.web("rgba(255,255,255,0.4)"));
        notifTime.setFont(Font.font(10));

        appHeader.getChildren().addAll(appIcon, appName, sp2, notifTime);

        // Main Content
        HBox cardBody = new HBox(12);
        VBox texts = new VBox(4);
        Label nTitle = new Label("New Listing in Kileleshwa!");
        nTitle.setTextFill(Color.WHITE);
        nTitle.setFont(Font.font("System", FontWeight.BOLD, 13));
        Text nDesc = new Text(
                "A stunning 2-bedroom apartment just posted for KES 60,000. Be the first to book a viewing!");
        nDesc.setFill(Color.web("rgba(255,255,255,0.7)"));
        nDesc.setFont(Font.font(11));
        nDesc.setWrappingWidth(200);
        texts.getChildren().addAll(nTitle, nDesc);

        StackPane thumb = new StackPane();
        thumb.setPrefSize(80, 80);
        thumb.setStyle("-fx-background-radius: 8; -fx-border-color: rgba(255,255,255,0.05); -fx-border-radius: 8;");
        try {
            ImageView tImg = new ImageView(new Image(
                    "https://lh3.googleusercontent.com/aida-public/AB6AXuAVaImpp4xae7ok0Ab9OsZlGDwUSXYW7NBiz6ptHYqMvMdkCmzGVkkZ1YJv9mmew-VH-Nn6DqRbBXoMMfWDDI3bZoBDfCnCsjTVC0djLXkg7Om-ZfEr4kw3JrEriWGzcDwIk-IReUeAKxzIXY9I7-yQcRQhzoaUqgJFL6qDFaUxJe4Ns4ufQb6CP_QC30vPJeHPiL01w4NSAuaeI9R8b0HUe0HArUkFXpu4bRKzuvk2VVZOU8wxhbHNAY4m4GbnSeGYujyYykQLN7Y",
                    80, 80, true, true));
            Rectangle clipThumb = new Rectangle(80, 80);
            clipThumb.setArcWidth(8);
            clipThumb.setArcHeight(8);
            tImg.setClip(clipThumb);
            thumb.getChildren().add(tImg);
        } catch (Exception e) {
        }

        cardBody.getChildren().addAll(texts, thumb);

        // Actions
        HBox actions = new HBox(8);
        actions.setPadding(new Insets(12, 0, 0, 0));
        actions.setStyle("-fx-border-color: rgba(255,255,255,0.1); -fx-border-width: 1 0 0 0;");

        Button viewBtn = new Button("View Listing");
        viewBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(viewBtn, Priority.ALWAYS);
        viewBtn.setStyle("-fx-background-color: " + PRIMARY
                + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11; -fx-background-radius: 8;");
        viewBtn.setOnAction(e -> MainApp.navigateTo(new PropertyDetailView()));

        Button dismissBtn = new Button("Dismiss");
        dismissBtn.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(dismissBtn, Priority.ALWAYS);
        dismissBtn.setStyle(
                "-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11; -fx-background-radius: 8;");
        dismissBtn.setOnAction(e -> MainApp.navigateTo(new ManageAlertsView())); // Back to source

        actions.getChildren().addAll(viewBtn, dismissBtn);

        notifCard.getChildren().addAll(appHeader, cardBody, actions);

        content.getChildren().addAll(statusBar, clockBox, notifCard);
        phoneFrame.getChildren().add(content);

        // Lock Screen Bottom Icons
        HBox bottomIcons = new HBox();
        bottomIcons.setPadding(new Insets(0, 32, 48, 32));
        bottomIcons.setAlignment(Pos.BOTTOM_CENTER);

        StackPane flash = createLockIcon("\u2607"); // flashlight
        Region sp3 = new Region();
        HBox.setHgrow(sp3, Priority.ALWAYS);
        StackPane cam = createLockIcon("\uD83D\uDCF7"); // camera

        bottomIcons.getChildren().addAll(flash, sp3, cam);
        phoneFrame.getChildren().add(bottomIcons);
        StackPane.setAlignment(bottomIcons, Pos.BOTTOM_CENTER);

        // Home Indicator
        VBox homeArea = new VBox(8);
        homeArea.setAlignment(Pos.BOTTOM_CENTER);
        homeArea.setPadding(new Insets(0, 0, 10, 0));

        Rectangle indicator = new Rectangle(130, 5, Color.web("rgba(255,255,255,0.4)"));
        indicator.setArcWidth(5);
        indicator.setArcHeight(5);

        Label swipeTxt = new Label("Swipe up to open");
        swipeTxt.setTextFill(Color.web("rgba(255,255,255,0.5)"));
        swipeTxt.setFont(Font.font(10));

        homeArea.getChildren().addAll(swipeTxt, indicator);
        phoneFrame.getChildren().add(homeArea);
        StackPane.setAlignment(homeArea, Pos.BOTTOM_CENTER);

        // Center the phone in the view
        getChildren().add(phoneFrame);
        setAlignment(Pos.CENTER);
    }

    private Label createIconShape(String text) {
        Label l = new Label(text);
        l.setTextFill(Color.WHITE);
        l.setFont(Font.font(12));
        return l;
    }

    private StackPane createLockIcon(String text) {
        StackPane sp = new StackPane();
        sp.setPrefSize(48, 48);
        sp.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-background-radius: 24;");
        Label l = new Label(text);
        l.setTextFill(Color.WHITE);
        l.setFont(Font.font(20));
        sp.getChildren().add(l);
        return sp;
    }
}
