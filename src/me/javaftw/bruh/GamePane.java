package me.javaftw.bruh;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * BruhButton by Tarun Boddupalli
 * <p>
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 Tarun Boddupalli
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
public class GamePane extends StackPane {

    private Rectangle overlayRect;
    private Text text;
    private StackPane mainPane;

    public GamePane() {
        Circle bruhButtonBorderCircle = new Circle(113);
        bruhButtonBorderCircle.setFill(new RadialGradient(0, 0.1, 0, 0, 20, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(0.2, Color.DARKGRAY), new Stop(1, Color.BLACK)));
        bruhButtonBorderCircle.setEffect(new DropShadow(5, 10, 10, Color.DARKGRAY));

        Circle bruhButtonCircle = new Circle(100);
        bruhButtonCircle.setFill(Color.RED);

        Text bruhText = new Text("BRUH");
        bruhText.setFont(Main.FONT);
        bruhText.setFill(Color.WHITE);

        bruhButtonBorderCircle.setOnMousePressed(e -> {
            bruhButtonBorderCircle.setTranslateX(3);
            bruhButtonBorderCircle.setTranslateY(3);
            bruhButtonCircle.setTranslateX(3);
            bruhButtonCircle.setTranslateY(3);
            bruhText.setTranslateX(3);
            bruhText.setTranslateY(3);
            bruhButtonBorderCircle.setEffect(new DropShadow(5, 5, 5, Color.DARKGRAY));
            bruhText.setFill(Color.BLACK);
            new AudioClip(getClass().getResource("bruh.mp3").toExternalForm()).play();
        });

        bruhButtonBorderCircle.setOnMouseReleased(e -> {
            bruhButtonBorderCircle.setTranslateX(0);
            bruhButtonBorderCircle.setTranslateY(0);
            bruhButtonCircle.setTranslateX(0);
            bruhButtonCircle.setTranslateY(0);
            bruhText.setTranslateX(0);
            bruhText.setTranslateY(0);
            bruhButtonBorderCircle.setEffect(new DropShadow(5, 10, 10, Color.DARKGRAY));
            bruhText.setFill(Color.WHITE);
        });

        bruhButtonCircle.setOnMousePressed(bruhButtonBorderCircle.getOnMousePressed());
        bruhButtonCircle.setOnMouseReleased(bruhButtonBorderCircle.getOnMouseReleased());
        bruhText.setOnMousePressed(bruhButtonBorderCircle.getOnMousePressed());
        bruhText.setOnMouseReleased(bruhButtonBorderCircle.getOnMouseReleased());

        mainPane = new StackPane();
        mainPane.getChildren().addAll(bruhButtonBorderCircle, bruhButtonCircle, bruhText);
        mainPane.setDisable(true);

        overlayRect = new Rectangle(300, 500, Color.rgb(0, 0, 0, 0.7));
        text = new Text("Starting in 3...");
        text.setFont(Main.FONT);

        getChildren().add(mainPane);
        getChildren().add(text);
        getChildren().add(overlayRect);
    }

    public void start() {
        /*AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };*/
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new KeyValue(text.textProperty(), "Starting in 2...")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2), new KeyValue(text.textProperty(), "Starting in 1...")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3), new KeyValue(text.textProperty(), "GO!")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(4), new KeyValue(overlayRect.visibleProperty(), false), new KeyValue(text.visibleProperty(), false)));
        timeline.setOnFinished(e -> {
            getChildren().removeAll(overlayRect, text);
            mainPane.setDisable(false);
        });
        timeline.play();
    }

}
