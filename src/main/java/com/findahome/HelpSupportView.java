package com.findahome;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HelpSupportView extends StackPane {

        private static final String BACKGROUND_DARK = "#102216";
        private static final String PRIMARY = "#13ec5b";
        private static final String BORDER_COLOR = "rgba(255,255,255,0.05)";
        private static final String CARD_BG = "#1a2e21";
        private static final String TEXT_GRAY = "#9db9a6";

        public HelpSupportView() {
                setStyle("-fx-background-color: " + BACKGROUND_DARK + ";");

                VBox layout = new VBox(0);
                layout.setAlignment(Pos.TOP_CENTER);

                // Top App Bar
                // HBox header = new HBox(15);
                // header.setAlignment(Pos.CENTER_LEFT);
                // header.setPadding(new Insets(15, 20, 15, 20));
                // header.setStyle("-fx-background-color: rgba(16, 34, 22, 0.8);");

                // Label backBtn = new Label("\u2039");
                // backBtn.setTextFill(Color.WHITE);
                // backBtn.setStyle("-fx-font-size: 28; -fx-cursor: hand;");
                // backBtn.setOnMouseClicked(e -> MainApp.showHome());

                Label title = new Label("Help & Support");
                title.setTextFill(Color.WHITE);
                title.setFont(Font.font("System", FontWeight.BOLD, 18));
                title.setAlignment(Pos.CENTER);
                title.setMaxWidth(Double.MAX_VALUE);
                title.setPadding(new Insets(20, 0, 10, 0));
                // HBox.setHgrow(title, Priority.ALWAYS);
                // HBox.setMargin(title, new Insets(0, 48, 0, 0)); // Offset for back button to
                // center title

                // header.getChildren().addAll(backBtn, title);

                // Scroll Content
                VBox scrollContent = new VBox(0);
                scrollContent.setAlignment(Pos.TOP_CENTER);
                scrollContent.setPadding(new Insets(0, 0, 100, 0));

                ScrollPane scroll = new ScrollPane(scrollContent);
                scroll.setFitToWidth(true);
                scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                VBox.setVgrow(scroll, Priority.ALWAYS);

                // Search Bar
                VBox searchSect = new VBox();
                searchSect.setPadding(new Insets(12, 20, 12, 20));
                HBox searchBar = new HBox(10);
                searchBar.setAlignment(Pos.CENTER_LEFT);
                searchBar.setPadding(new Insets(0, 15, 0, 15));
                searchBar.setPrefHeight(56);
                searchBar.setStyle("-fx-background-color: #28392e; -fx-background-radius: 12;");
                Label searchIcon = new Label("\ud83d\udd0d");
                searchIcon.setTextFill(Color.web(TEXT_GRAY));
                TextField searchInput = new TextField();
                searchInput.setPromptText("How can we help?");
                searchInput.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: "
                                + TEXT_GRAY + "; -fx-font-size: 15;");
                HBox.setHgrow(searchInput, Priority.ALWAYS);
                searchBar.getChildren().addAll(searchIcon, searchInput);
                searchSect.getChildren().add(searchBar);

                // Quick Links
                VBox quickSect = new VBox(15);
                quickSect.setPadding(new Insets(15, 0, 15, 0));
                Label quickTitle = new Label("Quick Links");
                quickTitle.setTextFill(Color.WHITE);
                quickTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
                quickTitle.setPadding(new Insets(0, 20, 0, 20));

                HBox chips = new HBox(12);
                chips.setPadding(new Insets(0, 20, 10, 20));

                String[][] chipData = {
                                { "\ud83d\udcc5", "Booking Issues" },
                                { "\ud83d\udc5b", "Payment Help" },
                                { "\u2699\ufe0f", "Account Settings" },
                                { "\ud83d\udee1\ufe0f", "Agent Verification" }
                };

                for (String[] data : chipData) {
                        HBox chip = createChip(data[0], data[1]);
                        chip.setOnMouseClicked(e -> {
                                chips.getChildren().forEach(node -> {
                                        node.setStyle("-fx-background-color: #28392e; -fx-background-radius: 8; -fx-cursor: hand;");
                                });
                                chip.setStyle("-fx-background-color: " + PRIMARY
                                                + "44; -fx-background-radius: 8; -fx-cursor: hand; -fx-border-color: "
                                                + PRIMARY + "; -fx-border-radius: 8;");
                        });
                        chips.getChildren().add(chip);
                }
                ScrollPane chipsPane = new ScrollPane(chips);
                chipsPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                chipsPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                chipsPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
                quickSect.getChildren().addAll(quickTitle, chipsPane);

                // FAQ Section
                VBox faqSect = new VBox(15);
                faqSect.setPadding(new Insets(25, 20, 25, 20));
                Label faqTitle = new Label("Frequently Asked Questions");
                faqTitle.setTextFill(Color.WHITE);
                faqTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                VBox faqList = new VBox(12);
                faqList.getChildren().addAll(
                                createFaqItem("How do I book a viewing?",
                                                "You can book a viewing directly through the property listing page. Click the \"Book Viewing\" button and select an available time slot from the agent's calendar.",
                                                true),
                                createFaqItem("Is my deposit safe?",
                                                "Yes, FindaHome uses a secure escrow system. Your deposit is only released once the contract is signed and you have performed the move-in inspection.",
                                                false),
                                createFaqItem("How to report a fraudulent listing?",
                                                "You can report any suspicious listing by clicking the \"Report\" button at the bottom of the property details page.",
                                                false),
                                createFaqItem("What are the agent service fees?",
                                                "Service fees vary depending on the agent and property type, but are always clearly displayed before you confirm any payment.",
                                                false));
                faqSect.getChildren().addAll(faqTitle, faqList);

                // Contact Section
                VBox contactSect = new VBox(15);
                contactSect.setPadding(new Insets(15, 20, 30, 20));
                Label contactTitle = new Label("Contact Support");
                contactTitle.setTextFill(Color.WHITE);
                contactTitle.setFont(Font.font("System", FontWeight.BOLD, 18));

                VBox contactList = new VBox(12);
                contactList.getChildren().addAll(
                                createContactBtn("\ud83d\udcac", "Live Chat", "Instant response (Typical: 2 mins)",
                                                PRIMARY, true),
                                createContactBtn("\u2709\ufe0f", "Email Support", "Reply within 24 hours", "#28392e",
                                                false),
                                createContactBtn("\ud83d\udcde", "Call Us", "Mon-Fri, 9am - 6pm", "#28392e", false));
                contactSect.getChildren().addAll(contactTitle, contactList);

                // Safety Banner
                VBox safetySect = new VBox();
                safetySect.setPadding(new Insets(0, 20, 30, 20));
                HBox safetyBanner = new HBox(12);
                safetyBanner.setPadding(new Insets(15));
                safetyBanner.setStyle(
                                "-fx-background-color: rgba(59, 130, 246, 0.1); -fx-background-radius: 12; -fx-border-color: rgba(59, 130, 246, 0.2);");
                Label infoIcon = new Label("i");
                infoIcon.setTextFill(Color.web("#60a5fa"));
                infoIcon.setStyle(
                                "-fx-font-weight: bold; -fx-font-size: 16; -fx-padding: 0 5; -fx-border-color: #60a5fa; -fx-border-radius: 10;");
                Label safetyText = new Label(
                                "Always conduct transactions within the FindaHome app. We will never ask for your password via phone or email.");
                safetyText.setTextFill(Color.web("#93c5fd"));
                safetyText.setFont(Font.font(12));
                safetyText.setWrapText(true);
                HBox.setHgrow(safetyText, Priority.ALWAYS);
                safetyBanner.getChildren().addAll(infoIcon, safetyText);
                safetySect.getChildren().add(safetyBanner);

                scrollContent.getChildren().addAll(searchSect, quickSect, faqSect, contactSect, safetySect);

                // Bottom Navigation
                // remove bottomNav creation and addition

                // layout.getChildren().addAll(header, scroll);
                // getChildren().addAll(layout, bottomNav);
                // StackPane.setAlignment(bottomNav, Pos.BOTTOM_CENTER);
                layout.getChildren().addAll(title, scroll);
                getChildren().add(layout);
        }

        private HBox createChip(String icon, String text) {
                HBox chip = new HBox(8);
                chip.setAlignment(Pos.CENTER_LEFT);
                chip.setPadding(new Insets(8, 16, 8, 12));
                chip.setStyle("-fx-background-color: #28392e; -fx-background-radius: 8; -fx-cursor: hand;");
                Label i = new Label(icon);
                i.setTextFill(Color.web(PRIMARY));
                i.setStyle("-fx-font-size: 16;");
                Label t = new Label(text);
                t.setTextFill(Color.WHITE);
                t.setFont(Font.font("System", FontWeight.MEDIUM, 14));
                chip.getChildren().addAll(i, t);

                chip.setOnMousePressed(e -> chip.setScaleX(0.95));
                chip.setOnMouseReleased(e -> chip.setScaleX(1.0));

                return chip;
        }

        private VBox createFaqItem(String question, String answer, boolean expanded) {
                VBox item = new VBox(0);
                item.setStyle("-fx-background-color: " + CARD_BG + "; -fx-background-radius: 12; -fx-border-color: "
                                + BORDER_COLOR + ";");

                HBox header = new HBox();
                header.setPadding(new Insets(15));
                header.setAlignment(Pos.CENTER_LEFT);
                header.setCursor(javafx.scene.Cursor.HAND);

                Label q = new Label(question);
                q.setTextFill(Color.WHITE);
                q.setFont(Font.font("System", FontWeight.BOLD, 14));

                Region s = new Region();
                HBox.setHgrow(s, Priority.ALWAYS);
                Label arrow = new Label(expanded ? "\u2303" : "\u2304"); // expand more/less
                arrow.setTextFill(expanded ? Color.web(PRIMARY) : Color.web(TEXT_GRAY));
                arrow.setStyle("-fx-font-size: 18;");

                header.getChildren().addAll(q, s, arrow);
                item.getChildren().add(header);

                if (expanded) {
                        Label a = new Label(answer);
                        a.setTextFill(Color.web(TEXT_GRAY));
                        a.setFont(Font.font(13));
                        a.setWrapText(true);
                        a.setPadding(new Insets(0, 15, 15, 15));
                        item.getChildren().add(a);
                }

                return item;
        }

        private Button createContactBtn(String icon, String title, String sub, String color, boolean primary) {
                Button btn = new Button();
                btn.setMaxWidth(Double.MAX_VALUE);
                btn.setPadding(new Insets(0));
                btn.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 12; -fx-cursor: hand;");

                HBox content = new HBox(15);
                content.setAlignment(Pos.CENTER_LEFT);
                content.setPadding(new Insets(15));

                StackPane iconBox = new StackPane();
                iconBox.setPrefSize(36, 36);
                iconBox.setStyle(primary ? "-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 8;"
                                : "-fx-background-color: rgba(19, 236, 91, 0.1); -fx-background-radius: 8;");
                Label i = new Label(icon);
                i.setTextFill(primary ? Color.WHITE : Color.web(PRIMARY));
                i.setStyle("-fx-font-size: 18;");
                iconBox.getChildren().add(i);

                VBox text = new VBox(2);
                Label t = new Label(title);
                t.setTextFill(primary ? Color.WHITE : Color.WHITE);
                t.setFont(Font.font("System", FontWeight.BOLD, 15));
                Label s = new Label(sub);
                s.setTextFill(primary ? Color.web("rgba(255,255,255,0.8)") : Color.web(TEXT_GRAY));
                s.setFont(Font.font(11));
                text.getChildren().addAll(t, s);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);
                Label chevron = new Label("\u203a");
                chevron.setTextFill(primary ? Color.WHITE : Color.web(TEXT_GRAY));
                chevron.setStyle("-fx-font-size: 24;");

                content.getChildren().addAll(iconBox, text, spacer, chevron);
                btn.setGraphic(content);

                btn.setOnAction(e -> {
                        if (title.equals("Live Chat")) {
                                MainApp.navigateCached("messages", ChatView::new);
                        }
                });

                return btn;
        }

}
