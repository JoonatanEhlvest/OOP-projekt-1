package oop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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


        //TODO erinevad stseenid: menüü, liikumine, võitlus, managa ründmine? (võib ka nt pop up window olla)

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);

        // Nupud:
        GridPane gridValikud = new GridPane();
        gridValikud.setPadding(new Insets(15, 15, 15, 15));
        gridValikud.setVgap(10);
        gridValikud.setHgap(10);

        // Nupp 1
        Button valik1 = new Button("Liigu edasi");
        GridPane.setConstraints(valik1, 0, 0);
        valik1.setPrefWidth(200);
        valik1.setPrefHeight(50);
        // Nupp 2
        Button valik2 = new Button("Vaata kaarti");
        GridPane.setConstraints(valik2, 0, 1);
        valik2.setPrefWidth(200);
        valik2.setPrefHeight(50);
        // Nupp 3
        Button valik3 = new Button("Vaata varustust");
        GridPane.setConstraints(valik3, 1, 0);
        valik3.setPrefWidth(200);
        valik3.setPrefHeight(50);
        // Nupp 4
        Button valik4 = new Button("Salvesta ja välju");
        GridPane.setConstraints(valik4, 1, 1);
        valik4.setPrefWidth(200);
        valik4.setPrefHeight(50);


        gridValikud.getChildren().addAll(valik1, valik2, valik3, valik4);
        gridValikud.setAlignment(Pos.CENTER);
        bp.setBottom(gridValikud);


        Scene scene = new Scene(bp, 1280, 720);

        double suhe = 16/9.0;
        pealava.minWidthProperty().bind(scene.heightProperty().multiply(suhe));
        pealava.minHeightProperty().bind(scene.widthProperty().divide(suhe));


        /**
         * NEED KAKS KUULARIT MUUDAVAD NENDE ASJADE SUURUST, MIS SIIN KIRJELDATUD ON, hetkel ainult tausta
         * Aga siia lisada veel peategelane, vastane ja siis nupud jne.
         * Ja pärast tsüklis saame omistada uusi asju nendele imageView-dele ja vastase isendile
         */
        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);
            double nuppuSuurus = (double) newValue*0.03;
            nuppSuurus(valik1,nuppuSuurus);
            nuppSuurus(valik2,nuppuSuurus);
            nuppSuurus(valik3,nuppuSuurus);
            nuppSuurus(valik4,nuppuSuurus);
        });

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
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

    public static void nuppSuurus(Button nupp, double uusSuurus) {
        nupp.setPrefHeight(uusSuurus);
        nupp.setPrefHeight(uusSuurus);
    }
}
