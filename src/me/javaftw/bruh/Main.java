package me.javaftw.bruh;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    static Font FONT = Font.font("Avenir", FontWeight.BOLD, 36);

    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane root = new StackPane();

        Rectangle rect = new Rectangle(250, 75, Color.BLACK);

        Text text = new Text("Play");
        text.setFont(FONT);
        text.setFill(Color.WHITE);
        text.translateXProperty().bind(rect.translateXProperty());
        text.translateYProperty().bind(rect.translateYProperty());

        root.getChildren().addAll(rect, text);

        Scene startGame = new Scene(root, 300, 500);

        GamePane pane = new GamePane();
        Scene game = new Scene(pane, 300, 500);

        rect.setOnMousePressed(e -> {
            rect.setTranslateX(3);
            rect.setTranslateY(3);
        });
        rect.setOnMouseReleased(e -> {
            primaryStage.setScene(game);
            pane.start();
        });
        text.setOnMousePressed(rect.getOnMousePressed());
        text.setOnMouseReleased(rect.getOnMouseReleased());

        primaryStage.setTitle("BruhButton");
        primaryStage.setResizable(false);
        primaryStage.setScene(startGame);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
