package oop;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
public class Peaklassfx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pealava) {
        pealava.setTitle("Basic Dungeon");

        BackgroundImage taustapilt = new BackgroundImage(
                new Image("images/Taust.jpg"), BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);


        pealava.show();
    }
}
