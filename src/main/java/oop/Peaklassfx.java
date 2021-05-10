package oop;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        Group root = new Group();
        pealava.setTitle("Basic Dungeon");

        Image taust = pilt("images/Taust.jpg");
        ImageView imageView = new ImageView();
        imageView.setImage(taust);
        imageView.setFitWidth(1280);
        imageView.setFitHeight(720);


        BorderPane bp = new BorderPane();
        bp.getChildren().add(imageView);

        root.getChildren().add(bp);
        Scene scene = new Scene(root, 1280, 720);

        double suhe = 16/9;
        pealava.minWidthProperty().bind(scene.heightProperty().multiply(1.777));
        pealava.minHeightProperty().bind(scene.widthProperty().divide(1.777));


        /**
         * NEED KAKS KUULARIT MUUDAVAD NENDE ASJADE SUURUST, MIS SIIN KIRJELDATUD ON, hetkel ainult tausta
         * Aga siia lisada veel peategelane, vastane ja siis nupud jne.
         * Ja pärast tsüklis saame omistada uusi asju nendele imageView-dele ja vastase isendile
         */
        pealava.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imageView.setFitWidth((double) newValue);
            }
        });

        pealava.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imageView.setFitHeight((double) newValue);

            }
        });


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
