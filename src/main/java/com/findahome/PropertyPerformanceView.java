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

public class PropertyPerformanceView extends StackPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";
        private static final String CARD_BG = "#162a1d";
        private static final String TEXT_GRAY = "#9db9a6";

        public PropertyPerformanceView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox layout = new VBox(0);
                layout.setAlignment(Pos.TOP_CENTER);

                // Header
                HBox header = new HBox(15);
                header.setAlignment(Pos.CENTER_LEFT);
                header.setPadding(new Insets(15, 20, 15, 20));
                header.setStyle("-fx-border-color: #1a3a24; -fx-border-width: 0 0 1 0;");

                Label title = new Label("Performance");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(title, Priority.ALWAYS);

                Label tuneBtn = new Label("\u2312"); // Mock tune icon
                tuneBtn.setTextFill(Color.WHITE);
                tuneBtn.setStyle("-fx-font-size: 20; -fx-cursor: hand;");

                header.getChildren().addAll(title, tuneBtn);

                // Scroll Content
                VBox content = new VBox(0);
                content.setAlignment(Pos.TOP_CENTER);
                ScrollPane scroll = new ScrollPane(content);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Tabs
                HBox tabs = new HBox(30);
                tabs.setPadding(new Insets(15, 20, 0, 20));

                String[] tabNames = { "Monthly", "Quarterly", "Yearly" };
                for (String name : tabNames) {
                        VBox tab = createTab(name, name.equals("Monthly"));
                        tab.setCursor(javafx.scene.Cursor.HAND);
                        tab.setOnMouseClicked(e -> {
                                tabs.getChildren().forEach(node -> {
                                        if (node instanceof VBox) {
                                                ((VBox) node).getChildren().get(1).setVisible(false);
                                                ((Label) ((VBox) node).getChildren().get(0))
                                                                .setTextFill(Color.web(TEXT_GRAY));
                                        }
                                });
                                tab.getChildren().get(1).setVisible(true);
                                ((Label) tab.getChildren().get(0)).setTextFill(Color.WHITE);
                        });
                        tabs.getChildren().add(tab);
                }
                tabs.setStyle("-fx-border-color: #1a3a24; -fx-border-width: 0 0 1 0;");

                // Occupancy Chart Section
                VBox chartSection = new VBox(15);
                chartSection.setPadding(new Insets(25, 20, 25, 20));

                VBox chartCard = new VBox(20);
                chartCard.setPadding(new Insets(25));
                chartCard.setStyle(
                                "-fx-background-color: " + CARD_BG
                                                + "; -fx-background-radius: 20; -fx-border-color: #3b5443;");

                HBox chartHead = new HBox();
                VBox chartTitleBox = new VBox(2);
                Label chartTitle = new Label("Occupancy by Property");
                chartTitle.setTextFill(Color.web(TEXT_GRAY));
                chartTitle.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                HBox rateRow = new HBox(8);
                rateRow.setAlignment(Pos.BOTTOM_LEFT);
                Label rate = new Label("92%");
                rate.setTextFill(Color.WHITE);
                rate.setFont(Font.font("System", FontWeight.BOLD, 30));
                Label avg = new Label("Avg");
                avg.setTextFill(Color.web("#64748b"));
                avg.setFont(Font.font(14));
                rateRow.getChildren().addAll(rate, avg);
                chartTitleBox.getChildren().addAll(chartTitle, rateRow);

                Region cs = new Region();
                HBox.setHgrow(cs, Priority.ALWAYS);
                HBox badge = new HBox(4);
                badge.setAlignment(Pos.CENTER);
                badge.setPadding(new Insets(4, 8, 4, 8));
                badge.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 4;");
                Label trendIcon = new Label("\u2197");
                trendIcon.setTextFill(Color.web(PRIMARY));
                Label trendVal = new Label("+4%");
                trendVal.setTextFill(Color.web(PRIMARY));
                trendVal.setFont(Font.font("System", FontWeight.BOLD, 12));
                badge.getChildren().addAll(trendIcon, trendVal);
                chartHead.getChildren().addAll(chartTitleBox, cs, badge);

                HBox bars = new HBox(15);
                bars.setAlignment(Pos.BOTTOM_CENTER);
                bars.setPrefHeight(200);
                bars.getChildren().addAll(
                                createBar("90%", 0.90, "Greenwood"),
                                createBar("75%", 0.75, "Urban Loft"),
                                createBar("60%", 0.60, "Sky View"),
                                createBar("95%", 0.95, "Oak Res"),
                                createBar("85%", 0.85, "Lake"));

                chartCard.getChildren().addAll(chartHead, bars);
                chartSection.getChildren().add(chartCard);

                // Financial Health
                VBox healthSection = new VBox(15);
                healthSection.setPadding(new Insets(0, 20, 25, 20));
                Label healthTitle = new Label("Financial Health");
                healthTitle.setTextFill(Color.WHITE);
                healthTitle.setFont(Font.font("System", FontWeight.BOLD, 20));

                HBox healthGrid = new HBox(15);

                VBox scoreCard = new VBox(8);
                scoreCard.setPadding(new Insets(20));
                HBox.setHgrow(scoreCard, Priority.ALWAYS);
                scoreCard.setStyle("-fx-background-color: " + PRIMARY + "; -fx-background-radius: 20;");
                HBox scoreHead = new HBox();
                Label sTitle = new Label("Health Score");
                sTitle.setTextFill(Color.web(BACKGROUND_DARK));
                sTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
                sTitle.setOpacity(0.8);
                Region ss = new Region();
                HBox.setHgrow(ss, Priority.ALWAYS);
                Label vIcon = new Label("\u2705");
                vIcon.setTextFill(Color.web(BACKGROUND_DARK));
                scoreHead.getChildren().addAll(sTitle, ss, vIcon);
                Label scoreVal = new Label("85/100");
                scoreVal.setTextFill(Color.web(BACKGROUND_DARK));
                scoreVal.setFont(Font.font("System", FontWeight.BLACK, 30));
                Label statusBadge = new Label("Good Standing");
                statusBadge.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-size: 10; -fx-font-weight: bold; -fx-padding: 2 8; -fx-background-radius: 10;");
                scoreCard.getChildren().addAll(scoreHead, scoreVal, statusBadge);

                VBox incomeCard = new VBox(8);
                incomeCard.setPadding(new Insets(20));
                HBox.setHgrow(incomeCard, Priority.ALWAYS);
                incomeCard.setStyle("-fx-background-color: #28392e; -fx-background-radius: 20;");
                Label nTitle = new Label("Net Income");
                nTitle.setTextFill(Color.web(TEXT_GRAY));
                nTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label incomeVal = new Label("$12,450");
                incomeVal.setTextFill(Color.WHITE);
                incomeVal.setFont(Font.font("System", FontWeight.BOLD, 24));
                Label incomeTrend = new Label("+12% vs last month");
                incomeTrend.setTextFill(Color.web(PRIMARY));
                incomeTrend.setFont(Font.font("System", FontWeight.BOLD, 11));
                incomeCard.getChildren().addAll(nTitle, incomeVal, incomeTrend);

                healthGrid.getChildren().addAll(scoreCard, incomeCard);
                healthSection.getChildren().addAll(healthTitle, healthGrid);

                // Expense Breakdown
                VBox expenseSection = new VBox(15);
                expenseSection.setPadding(new Insets(10, 20, 30, 20));
                HBox exHead = new HBox();
                Label exTitle = new Label("Expense Breakdown");
                exTitle.setTextFill(Color.WHITE);
                exTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
                Region exs = new Region();
                HBox.setHgrow(exs, Priority.ALWAYS);
                Label viewAll = new Label("View All");
                viewAll.setTextFill(Color.web(PRIMARY));
                viewAll.setFont(Font.font("System", FontWeight.BOLD, 14));
                exHead.getChildren().addAll(exTitle, exs, viewAll);

                VBox exContent = new VBox(20);
                VBox grossBox = new VBox(10);
                grossBox.setPadding(new Insets(15));
                grossBox.setStyle(
                                "-fx-background-color: #1a3a24; -fx-background-radius: 16; -fx-border-color: #3b5443;");
                HBox gHead = new HBox();
                Label gTitle = new Label("Gross Income");
                gTitle.setTextFill(Color.web(TEXT_GRAY));
                gTitle.setFont(Font.font(13));
                Region gs = new Region();
                HBox.setHgrow(gs, Priority.ALWAYS);
                Label gVal = new Label("$18,200.00");
                gVal.setTextFill(Color.WHITE);
                gVal.setFont(Font.font("System", FontWeight.BOLD, 14));
                gHead.getChildren().addAll(gTitle, gs, gVal);
                ProgressBar gBar = createProgressBar(1.0, PRIMARY);
                grossBox.getChildren().addAll(gHead, gBar);

                VBox breakDownList = new VBox(15);
                breakDownList.getChildren().addAll(
                                createExpenseRow("Maintenance", "$2,150", 0.12, "#f97316"),
                                createExpenseRow("Taxes & Insurance", "$1,800", 0.10, "#3b82f6"),
                                createExpenseRow("Platform Fees", "$1,820", 0.10, PRIMARY));

                exContent.getChildren().addAll(grossBox, breakDownList);
                expenseSection.getChildren().addAll(exHead, exContent);

                // Asset Selector
                VBox assetSection = new VBox(12);
                assetSection.setPadding(new Insets(0, 0, 150, 0));
                Label assetTitle = new Label("SELECTED PROPERTY");
                assetTitle.setTextFill(Color.web(TEXT_GRAY));
                assetTitle.setOpacity(0.6);
                assetTitle.setFont(Font.font("System", FontWeight.BOLD, 11));
                assetTitle.setPadding(new Insets(0, 20, 0, 20));

                HBox assetList = new HBox(12);
                assetList.setPadding(new Insets(0, 20, 0, 20));
                assetList.getChildren().addAll(
                                createPropertyAsset("Greenwood Villa", "Primary Asset",
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuAoQ2Akzg0BvILO6Zqjo-Ru5sdfOQjsyZUXAct1CqrCYXfi1EocZtjOVuFsKsLKEjMg03hAlzQwm66JWdNgZFPRPcmloI-gNPOzOEYgwRaZBGPAOH_NgC8knwjvnF5w6r6Q9ImF2WlQ1AO8wbAZBGkk_I7D2DQ_46U-uz-iOzbXW60kt7-QXDSjNIVOxdfIZZRCeyxT7jjevzG9WTc1lHFhLPa3caaUNjCbXMJPGHxZ3ELdbPUlOEHt4inyCCGpSGTxnu7tF0fgIqM",
                                                true),
                                createPropertyAsset("Urban Loft #42", "Standard Asset",
                                                "https://lh3.googleusercontent.com/aida-public/AB6AXuCwR1ScBungwNVs54TiVcwFo8O1BShpgisVzd_pi9jE1aBtkHjg8RNLljeW85XilxoXmyBr5MUI--sfMJsuHYQGJw_166of8oRHwlLZw-xTldpPjWWfGmLEyapGUWxeh3Mnoo962WvdbPuNsgLY5mOuPLK7debNRpKNkdPPkgKYNKZ-mERhTuXRVbutpMkoN1IPtosq10iTQfP6S1iKUFQHv5a824zf3igQr32-CqzH_N8Eb2TA2ViQ0tr1ujngO3mSS2YivSv_vZI",
                                                false));
                ScrollPane assetPane = new ScrollPane(assetList);
                assetPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                assetPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                assetPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                assetSection.getChildren().addAll(assetTitle, assetPane);

                content.getChildren().addAll(tabs, chartSection, healthSection, expenseSection, assetSection);

                // Sticky Footer
                HBox footer = new HBox(12);
                footer.setPadding(new Insets(30, 20, 20, 20));
                footer.setStyle("-fx-background-color: linear-gradient(to top, " + BACKGROUND_DARK
                                + " 80%, transparent);");

                Button csvBtn = new Button("Export CSV");
                csvBtn.setGraphic(new Label("\ud83d\udcc4"));
                HBox.setHgrow(csvBtn, Priority.ALWAYS);
                csvBtn.setMaxWidth(Double.MAX_VALUE);
                csvBtn.setPrefHeight(56);
                csvBtn.setStyle(
                                "-fx-background-color: #28392e; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 16; -fx-border-color: rgba(255,255,255,0.1);");
                csvBtn.setOnAction(e -> MainApp.navigateTo(new SuccessView("Export Successful",
                                "Financial report (CSV) has been saved to your downloads.")));

                Button pdfBtn = new Button("Export PDF");
                pdfBtn.setGraphic(new Label("\ud83d\uddbc"));
                HBox.setHgrow(pdfBtn, Priority.ALWAYS);
                pdfBtn.setMaxWidth(Double.MAX_VALUE);
                pdfBtn.setPrefHeight(56);
                pdfBtn.setStyle("-fx-background-color: " + PRIMARY + "; -fx-text-fill: " + BACKGROUND_DARK
                                + "; -fx-font-weight: bold; -fx-background-radius: 16;");
                pdfBtn.setOnAction(e -> MainApp.navigateTo(new SuccessView("Export Successful",
                                "Performance summary (PDF) has been saved to your downloads.")));

                footer.getChildren().addAll(csvBtn, pdfBtn);

                layout.getChildren().addAll(header, scroll);
                getChildren().addAll(layout, footer);
                StackPane.setAlignment(footer, Pos.BOTTOM_CENTER);
        }

        private VBox createTab(String text, boolean active) {
                VBox tab = new VBox(8);
                tab.setAlignment(Pos.CENTER);
                Label l = new Label(text);
                l.setTextFill(active ? Color.WHITE : Color.web(TEXT_GRAY));
                l.setFont(Font.font("System", FontWeight.BOLD, 14));
                Rectangle line = new Rectangle(30, 3, active ? Color.web(PRIMARY) : Color.TRANSPARENT);
                tab.getChildren().addAll(l, line);
                return tab;
        }

        private VBox createBar(String val, double percent, String label) {
                VBox col = new VBox(8);
                col.setAlignment(Pos.BOTTOM_CENTER);
                VBox bar = new VBox();
                bar.setPrefWidth(40);
                bar.setPrefHeight(150 * percent);
                bar.setStyle("-fx-background-color: rgba(19, 236, 91, 0.1); -fx-border-color: " + PRIMARY
                                + "; -fx-border-width: 2 0 0 0; -fx-background-radius: 4 4 0 0;");

                Label lbl = new Label(label);
                lbl.setTextFill(Color.web(TEXT_GRAY));
                lbl.setFont(Font.font("System", FontWeight.BOLD, 10));
                lbl.setRotate(45);
                lbl.setTranslateX(10);

                col.getChildren().addAll(bar, lbl);
                return col;
        }

        private ProgressBar createProgressBar(double progress, String color) {
                ProgressBar pb = new ProgressBar(progress);
                pb.setMaxWidth(Double.MAX_VALUE);
                pb.setPrefHeight(6);
                pb.getStyleClass().add("performance-bar");
                // CSS in MainApp will handle coloring via lookup
                pb.setStyle("-fx-accent: " + color + ";");
                return pb;
        }

        private VBox createExpenseRow(String title, String val, double pct, String color) {
                VBox row = new VBox(6);
                HBox head = new HBox();
                Label t = new Label(title);
                t.setTextFill(Color.web("#cbd5e1"));
                t.setFont(Font.font(13));
                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);
                Label v = new Label(val);
                v.setTextFill(Color.WHITE);
                v.setFont(Font.font("System", FontWeight.BOLD, 14));
                head.getChildren().addAll(t, s, v);
                ProgressBar pb = createProgressBar(pct, color);
                row.getChildren().addAll(head, pb);
                return row;
        }

        private HBox createPropertyAsset(String name, String type, String url, boolean primary) {
                HBox row = new HBox(12);
                row.setPadding(new Insets(12));
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPrefWidth(240);
                row.setStyle(primary
                                ? "-fx-background-color: rgba(19, 236, 91, 0.15); -fx-background-radius: 16; -fx-border-color: "
                                                + PRIMARY + ";"
                                : "-fx-background-color: " + CARD_BG
                                                + "; -fx-background-radius: 16; -fx-border-color: #3b5443;");

                ImageView iv = new ImageView();
                try {
                        iv.setImage(new Image(url, 48, 48, false, true));
                } catch (Exception e) {
                }
                iv.setFitWidth(48);
                iv.setFitHeight(48);
                Rectangle clip = new Rectangle(48, 48);
                clip.setArcWidth(10);
                clip.setArcHeight(10);
                iv.setClip(clip);

                VBox info = new VBox(2);
                Label n = new Label(name);
                n.setTextFill(Color.WHITE);
                n.setFont(Font.font("System", FontWeight.BOLD, 14));
                Label t = new Label(type);
                t.setTextFill(primary ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                t.setFont(Font.font("System", FontWeight.MEDIUM, 11));
                info.getChildren().addAll(n, t);

                row.getChildren().addAll(iv, info);
                return row;
        }
}
