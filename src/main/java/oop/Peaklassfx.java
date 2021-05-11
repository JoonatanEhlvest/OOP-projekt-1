package oop;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Peaklassfx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pealava) {
        pealava.setTitle("Basic Dungeon");

        Image taust = pilt("images/Taust.jpg");
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(1280);
        taustapilt.setFitHeight(720);


        //TODO erinevad stseenid: menüü, liikumine, võitlus

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);

        Scene scene = new Scene(bp, 1280, 720);

        double suhe = 16/9.0;
        pealava.minWidthProperty().bind(scene.heightProperty().multiply(suhe));
        pealava.minHeightProperty().bind(scene.widthProperty().divide(suhe));


        /**
         * NEED KAKS KUULARIT MUUDAVAD NENDE ASJADE SUURUST, MIS SIIN KIRJELDATUD ON, hetkel ainult tausta
         * Aga siia lisada veel peategelane, vastane ja siis nupud jne.
         * Ja pärast tsüklis saame omistada uusi asju nendele imageView-dele ja vastase isendile
         */
        pealava.widthProperty().addListener((observable, oldValue, newValue) -> taustapilt.setFitWidth((double) newValue));

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> taustapilt.setFitHeight((double) newValue));


        pealava.setScene(scene);
        pealava.show();
    }

    public static Image pilt (String path) {
        try {
            InputStream is = new FileInputStream(path);
            Image image = new Image(is);
            return image;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
