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

public class SavedPropertiesView extends StackPane {

    private static final String BACKGROUND_DARK = "#101622";
    private static final String CARD_BG = "#1c222c";
    private static final String PRIMARY = "#137fec";
    private static final String TEXT_GRAY = "#9da6b9";

    public SavedPropertiesView() {
        setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        VBox layout = new VBox(0);

        // Header
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setStyle("-fx-border-color: rgba(255,255,255,0.05); -fx-border-width: 0 0 1 0;");

        Label backBtn = new Label("\u2039");
        backBtn.setTextFill(Color.WHITE);
        backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
        backBtn.setOnMouseClicked(e -> MainApp.navigateTo(new TenantProfileView()));

        Label title = new Label("Saved Homes");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("System", FontWeight.BOLD, 18));

        header.getChildren().addAll(backBtn, title);

        // Content
        ScrollPane scroll = new ScrollPane();
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

        content.getChildren().addAll(
                createPropertyCard("Luxury Apartment", "Kileleshwa, Nairobi", "KSh 85,000/mo",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuBrmHY-INBb8Jxch2ewFEgmHWMJUA4ZNP52p_ZsSfCVLmW2IY3sMgtYaxGK51bVzD-oK_xBP-8CfgZql0a9fCUk8eB8u5R-EDubCMiz_E8EZNVe6TMKpupMx5q2s3LKJMpLlIiF-Yss_fHljPB3oDm09IT7kI8z3dO46Bq_cOJYSe9uNYVCCGk0DI6A14iU8Xj-VNaDaW2zuBzm7GlwPxMaydM4iu4KWxPvR8qm97PD9C7PdbIqQJlHvq1mCcUJxRkLwb22sqL3BcM"),
                createPropertyCard("Modern Villa", "Runda, Nairobi", "KSh 250,000/mo",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuAr1mXgDvVOzdi_5MstgtM21c3EeJGwBerleE6ehAnHIt0XycHEbSXofHSUs0J6rkmtdEALi-toPLIwOFIoRMpdQe1pibkgtrGdoNT1Dk-bGWJKVg8TM9PUK2pPJeaInPiOTjmG2XeNAPxg2wkbLssHZLWG_or0dF6VTpGzxaNkwPPL6d81XXmnNztNmGa7gs9JwOV60QVrOkfznYwPLqpaB7fBkkO_m-cJbz0BKg7mKqkLSslqq4dTWYrmktFreU1NWvlJB6CSrqo"),
                createPropertyCard("Cozy Studio", "Westlands, Nairobi", "KSh 45,000/mo",
                        "https://lh3.googleusercontent.com/aida-public/AB6AXuC4pocpShw3HHBbfx7E3-yqy4XFrUR9ai3VIsBufSf6u7l7laxkYR0nUwb0946TiD_Wtgk-R8oO-KV59NusLiudmiO0S4iUum8bku5C1A9qJDjWADX6vhWbZ1FDEPKJ2jknHZP0wIRYMmBBsFs4D_aHC5AZzrKQXbkYUKwOH9HroGYR9eXGu0g9TxpEKHKuw3IyHNmcFoJ3vl7D7sRGCi5UPNxCjxNDis8joRIEM0OAS-VNzw7Yt7v8rfkRe82jCahuPXo9cfQtPu0"));

        scroll.setContent(content);

        layout.getChildren().addAll(header, scroll);
        getChildren().add(layout);
    }

    private HBox createPropertyCard(String title, String loc, String price, String imgUrl) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-cursor: hand;");

        // Image
        StackPane imgContainer = new StackPane();
        imgContainer.setPrefSize(100, 100);
        try {
            ImageView img = new ImageView(new Image(imgUrl, 100, 100, true, true));
            Rectangle clip = new Rectangle(100, 100);
            clip.setArcWidth(8);
            clip.setArcHeight(8);
            img.setClip(clip);
            imgContainer.getChildren().add(img);
        } catch (Exception e) {
        }

        // Info
        VBox info = new VBox(5);
        Label t = new Label(title);
        t.setTextFill(Color.WHITE);
        t.setFont(Font.font("System", FontWeight.BOLD, 16));

        Label l = new Label(loc);
        l.setTextFill(Color.web(TEXT_GRAY));
        l.setFont(Font.font(13));

        Label p = new Label(price);
        p.setTextFill(Color.web(PRIMARY));
        p.setFont(Font.font("System", FontWeight.BOLD, 14));

        info.getChildren().addAll(t, l, p);
        HBox.setHgrow(info, Priority.ALWAYS);

        // Delete Button
        Button delBtn = new Button();
        delBtn.setGraphic(new Label("\u2715"));
        delBtn.setStyle(
                "-fx-background-color: rgba(255,255,255,0.1); -fx-text-fill: white; -fx-background-radius: 20; -fx-min-width: 30; -fx-min-height: 30; -fx-cursor: hand;");
        delBtn.setOnAction(e -> card.setVisible(false)); // Simple hide for now

        card.getChildren().addAll(imgContainer, info, delBtn);
        return card;
    }
}
