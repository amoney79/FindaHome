package com.findahome;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ResizeHelper {

    public static void addResizeListener(Stage stage) {
        ResizeListener resizeListener = new ResizeListener(stage);
        // Use filters to capture events before they reach nodes, ensuring we catch
        // mouse moves even over controls
        stage.getScene().addEventFilter(MouseEvent.MOUSE_MOVED, resizeListener);
        stage.getScene().addEventFilter(MouseEvent.MOUSE_PRESSED, resizeListener);
        stage.getScene().addEventFilter(MouseEvent.MOUSE_DRAGGED, resizeListener);
        stage.getScene().addEventFilter(MouseEvent.MOUSE_EXITED, resizeListener);
    }

    static class ResizeListener implements EventHandler<MouseEvent> {
        private final Stage stage;
        private Cursor cursorEvent = Cursor.DEFAULT;
        private int border = 4;
        private double startX = 0;
        private double startY = 0;

        public ResizeListener(Stage stage) {
            this.stage = stage;
        }

        @Override
        public void handle(MouseEvent mouseEvent) {
            EventType<? extends MouseEvent> mouseEventType = mouseEvent.getEventType();
            Scene scene = stage.getScene();

            if (stage.isMaximized()) {
                if (!Cursor.DEFAULT.equals(scene.getCursor())) {
                    scene.setCursor(Cursor.DEFAULT);
                }
                return;
            }

            double mouseEventX = mouseEvent.getSceneX();
            double mouseEventY = mouseEvent.getSceneY();
            double sceneWidth = scene.getWidth();
            double sceneHeight = scene.getHeight();

            if (MouseEvent.MOUSE_MOVED.equals(mouseEventType)) {
                if (mouseEventX < border && mouseEventY < border) {
                    cursorEvent = Cursor.NW_RESIZE;
                } else if (mouseEventX < border && mouseEventY > sceneHeight - border) {
                    cursorEvent = Cursor.SW_RESIZE;
                } else if (mouseEventX > sceneWidth - border && mouseEventY < border) {
                    cursorEvent = Cursor.NE_RESIZE;
                } else if (mouseEventX > sceneWidth - border && mouseEventY > sceneHeight - border) {
                    cursorEvent = Cursor.SE_RESIZE;
                } else if (mouseEventX < border) {
                    cursorEvent = Cursor.W_RESIZE;
                } else if (mouseEventX > sceneWidth - border) {
                    cursorEvent = Cursor.E_RESIZE;
                } else if (mouseEventY < border) {
                    cursorEvent = Cursor.N_RESIZE;
                } else if (mouseEventY > sceneHeight - border) {
                    cursorEvent = Cursor.S_RESIZE;
                } else {
                    cursorEvent = Cursor.DEFAULT;
                }
                scene.setCursor(cursorEvent);
            } else if (MouseEvent.MOUSE_EXITED.equals(mouseEventType)) {
                scene.setCursor(Cursor.DEFAULT);
            } else if (MouseEvent.MOUSE_PRESSED.equals(mouseEventType)) {
                if (!Cursor.DEFAULT.equals(cursorEvent)) {
                    startX = stage.getWidth() - mouseEventX;
                    startY = stage.getHeight() - mouseEventY;
                    // Provide a visual cue or consume if needed, but for resizing we generally let
                    // it pass unless we are resizing
                }
            } else if (MouseEvent.MOUSE_DRAGGED.equals(mouseEventType)) {
                if (!Cursor.DEFAULT.equals(cursorEvent)) {
                    // Consume event to prevent dragging content while resizing window
                    mouseEvent.consume();

                    if (!Cursor.W_RESIZE.equals(cursorEvent) && !Cursor.E_RESIZE.equals(cursorEvent)) {
                        double minHeight = stage.getMinHeight() > (border * 2) ? stage.getMinHeight() : (border * 2);
                        if (Cursor.NW_RESIZE.equals(cursorEvent) || Cursor.N_RESIZE.equals(cursorEvent)
                                || Cursor.NE_RESIZE.equals(cursorEvent)) {
                            if (stage.getHeight() > minHeight || mouseEventY < 0) {
                                double newHeight = stage.getY() - mouseEvent.getScreenY() + stage.getHeight();
                                stage.setHeight(newHeight > minHeight ? newHeight : minHeight);
                                stage.setY(mouseEvent.getScreenY());
                            }
                        } else {
                            if (stage.getHeight() > minHeight || mouseEventY + startY - stage.getHeight() > 0) {
                                stage.setHeight(mouseEventY + startY);
                            }
                        }
                    }

                    if (!Cursor.N_RESIZE.equals(cursorEvent) && !Cursor.S_RESIZE.equals(cursorEvent)) {
                        double minWidth = stage.getMinWidth() > (border * 2) ? stage.getMinWidth() : (border * 2);
                        if (Cursor.NW_RESIZE.equals(cursorEvent) || Cursor.W_RESIZE.equals(cursorEvent)
                                || Cursor.SW_RESIZE.equals(cursorEvent)) {
                            if (stage.getWidth() > minWidth || mouseEventX < 0) {
                                double newWidth = stage.getX() - mouseEvent.getScreenX() + stage.getWidth();
                                stage.setWidth(newWidth > minWidth ? newWidth : minWidth);
                                stage.setX(mouseEvent.getScreenX());
                            }
                        } else {
                            if (stage.getWidth() > minWidth || mouseEventX + startX - stage.getWidth() > 0) {
                                stage.setWidth(mouseEventX + startX);
                            }
                        }
                    }
                }
            }
        }
    }
}
