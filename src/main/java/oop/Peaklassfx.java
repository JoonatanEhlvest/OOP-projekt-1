package oop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import java.util.*;

public class Peaklassfx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pealava) {
        /** MÄNGIJA LOOMINE */
        Mängija m1 = new Mängija("Player 1", 40, 20, 5,4,3);
        m1.setKaitserüü(new Kaitserüü(0,"Tavalised Riided"));

        /** VARUSTUSE LOOMINE JA LISAMINE VARUSTUSE LISTI */
        Varustus varustuseList = new Varustus();

        varustuseList.lisaAsi(new Relv(3, "Katkine Mõõk"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Kööginuga"));
        varustuseList.lisaAsi(new Relv(1, "Kahvel"));
        varustuseList.lisaAsi(new Relv(10, "Legendaarne Mõõk"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Vibu"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Kirves"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Oda"));
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Võlurikepp"));

        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Mõrane Raudrüü"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Kantud Nahkvest"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Käekaitsmed"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Maagiline müts"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Sõdurisaapad"));
        varustuseList.lisaAsi(new Kaitserüü(10, "Kuldne Rüü"));

        /** VASTASTE NIMEDE LOOMINE */
        List<String> nimed = Arrays.asList("Rott", "Luukere", "Vampiir", "Orc", "Bandiit", "Maag", "Maasööjauss");

        /** KOOPA RUUMIDE LOOMINE JA SUVALISES JÄRJESTUSES KOOPASE PAIGUTAMINE*/
        List<Koobas> koobas = new ArrayList<>();
        Aarderuum aarderuum = new Aarderuum("Aarderuum");
        Lõksuruum lõksuruum = new Lõksuruum(new Lõks(Juhuslik.randint(5,10),60,false));
        int raskus = 1;
        Võitlusruum võitlusruum1 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Krüpt");
        Võitlusruum võitlusruum2 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Vana Raamatukogu");
        Võitlusruum võitlusruum3 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Labor");
        Lõpuruum lõpuruum = new Lõpuruum(new Vastane("Maailma Hävitaja Võlur",100,13,5),"Viimane Tuba");
        koobas.add(aarderuum);
        koobas.add(võitlusruum1);
        koobas.add(võitlusruum2);
        koobas.add(võitlusruum3);
        koobas.add(lõksuruum);
        Collections.shuffle(koobas);
        koobas.add(lõpuruum);

        /** MÄNG ALGAB SIIT */

        pealava.setTitle("Basic Dungeon");

        Scene liikumisStseen = liikumisStseen(pealava);

        pealava.setScene(liikumisStseen);
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

    public static void nuppSuurusH(Button nupp, double uusSuurus) {
        nupp.setPrefHeight(uusSuurus);
    }

    public static void nuppSuurusW(Button nupp, double uusSuurus) {
        nupp.setPrefWidth(uusSuurus);
    }

    public static String RandElem(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    private static void Võitlus(Mängija mängija, Vastane vastane) {
        if (mängija.getRelv() == null) { // Kui mängijal ei ole relva, siis ründab käega
            if (mängija.isElus()) { // kui mängija on elus, siis ründa
                mängija.ründa(vastane); // rünnak
                System.out.println("Vastasel on järgi " + vastane.getElud() + " elu");
                if (vastane.isElus()) { // Kui vastane on elus, siis ründab vastu
                    vastane.ründa(mängija);
                    System.out.println("Sul on järgi " + mängija.getElud() + " elu");
                }
            }
        }
        else { // Kui mängijal on relv, siis ründa relvaga
            if (mängija.isElus()) {
                mängija.ründa(vastane, mängija.getRelv()); // rünnak relvaga
                System.out.println(vastane.getNimi() + " elud on " + vastane.getElud());
                if (vastane.isElus()) {
                    vastane.ründa(mängija);
                    System.out.println("Sul on järgi " + mängija.getElud() + " elu");
                }
            }
        }
    }

    public static Scene võitlusStseen(Stage pealava, Võitlusruum ruum, Vastane vastane) {
        String pilt = "images/"+ruum.getNimi()+".jpg";
        Image taust = pilt(pilt);
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(1280);
        taustapilt.setFitHeight(720);

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);

        // Nupud:
        GridPane gridValikud = new GridPane();
        gridValikud.setPadding(new Insets(15, 15, 15, 15));
        gridValikud.setVgap(10);
        gridValikud.setHgap(100);

        // Nupp 1
        Button valik1 = new Button("Ründa");
        GridPane.setConstraints(valik1, 0, 0);

        // Nupp 2
        Button valik2 = new Button("Põgene (Võtad kahju!)");
        GridPane.setConstraints(valik2, 1, 0);

        // Nupp 3
        Button valik3 = new Button("Ründa maagiaga");
        GridPane.setConstraints(valik3, 2, 0);

        gridValikud.getChildren().addAll(valik1, valik2, valik3);
        gridValikud.setAlignment(Pos.CENTER);
        bp.setTop(gridValikud);

        Scene scene = new Scene(bp, 1280, 720);

        pealava.setMinHeight(757);
        pealava.setMinWidth(1293);

        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);
            double nuppuSuurus = (double) newValue*0.15;
            nuppSuurusW(valik1,nuppuSuurus);
            nuppSuurusW(valik2,nuppuSuurus);
            nuppSuurusW(valik3,nuppuSuurus);
        });

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
            double nuppuSuurus = (double) newValue*0.05;
            nuppSuurusH(valik1,nuppuSuurus);
            nuppSuurusH(valik2,nuppuSuurus);
            nuppSuurusH(valik3,nuppuSuurus);
        });

        valik1.setOnAction(actionEvent ->  {
            //... do something in here.
        });
        return scene;

    }

    public static Scene liikumisStseen(Stage pealava) {
        Image taust = pilt("images/Taust.jpg");
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(1280);
        taustapilt.setFitHeight(720);

        Image mängija = pilt("images/Player.png");
        ImageView mängijapilt = new ImageView();
        mängijapilt.setImage(mängija);
        mängijapilt.setFitWidth(128);
        mängijapilt.setFitHeight(128);

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);
        bp.getChildren().add(mängijapilt);

        // Nupud:
        GridPane gridValikud = new GridPane();
        gridValikud.setPadding(new Insets(15, 15, 15, 15));
        gridValikud.setVgap(10);
        gridValikud.setHgap(100);

        // Nupp 1
        Button valik1 = new Button("Liigu edasi");
        GridPane.setConstraints(valik1, 0, 0);

        // Nupp 2
        Button valik2 = new Button("Vaata kaarti");
        GridPane.setConstraints(valik2, 0, 1);

        // Nupp 3
        Button valik3 = new Button("Vaata varustust");
        GridPane.setConstraints(valik3, 1, 0);

        // Nupp 4
        Button valik4 = new Button("Salvesta ja välju");
        GridPane.setConstraints(valik4, 1, 1);


        gridValikud.getChildren().addAll(valik1, valik2, valik3, valik4);
        gridValikud.setAlignment(Pos.CENTER);
        bp.setBottom(gridValikud);

        Scene scene = new Scene(bp, 1280, 720);

        pealava.setMinHeight(757);
        pealava.setMinWidth(1293);




        /**
         * NEED KAKS KUULARIT MUUDAVAD NENDE ASJADE SUURUST, MIS SIIN KIRJELDATUD ON, hetkel ainult tausta
         * Aga siia lisada veel peategelane, vastane ja siis nupud jne.
         * Ja pärast tsüklis saame omistada uusi asju nendele imageView-dele ja vastase isendile
         */
        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);
            double nuppuSuurus = (double) newValue*0.15;
            nuppSuurusW(valik1,nuppuSuurus);
            nuppSuurusW(valik2,nuppuSuurus);
            nuppSuurusW(valik3,nuppuSuurus);
            nuppSuurusW(valik4,nuppuSuurus);
            mängijapilt.setY(scene.getHeight()*0.5);
            mängijapilt.setFitWidth(128+scene.getWidth()*0.05);

        });

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
            double nuppuSuurus = (double) newValue*0.05;
            nuppSuurusH(valik1,nuppuSuurus);
            nuppSuurusH(valik2,nuppuSuurus);
            nuppSuurusH(valik3,nuppuSuurus);
            nuppSuurusH(valik4,nuppuSuurus);
            mängijapilt.setX(scene.getWidth()*0.6);
            mängijapilt.setFitHeight(128+scene.getWidth()*0.05);
        });

        valik1.setOnAction(actionEvent ->  {
            pealava.setScene(liikumisStseen(pealava));
        });
        return scene;
    }
}
