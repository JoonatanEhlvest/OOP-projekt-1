package oop;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Peaklassfx extends Application {
    private static final int laiusResolutsioon = 1280;
    private static final int kõrgusResolutsioon = 720;

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
        Võitlusruum võitlusruum2 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Vana_Raamatukogu");
        Võitlusruum võitlusruum3 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Labor");
        Lõpuruum lõpuruum = new Lõpuruum(new Vastane("Maailma Hävitaja Võlur",100,13,5),"Viimane Tuba");
        //koobas.add(aarderuum);
        koobas.add(võitlusruum1);
        koobas.add(võitlusruum2);
        koobas.add(võitlusruum3);
        koobas.add(lõksuruum);
        //Collections.shuffle(koobas);
        koobas.add(lõpuruum);

        /** MÄNG ALGAB SIIT */
        pealava.setTitle("Basic Dungeon");
        int ruuminumber = 0;
        Scene liikumisStseen = liikumisStseen(pealava, koobas, ruuminumber, m1, varustuseList);
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

    public static Scene võitlusStseen(Stage pealava, List<Koobas> ruumid, int ruuminumber, Mängija m1, Varustus varustuseList) {
        Võitlusruum vr = (Võitlusruum) ruumid.get(ruuminumber-1);
        Vastane vastane = vr.getVastane();

        String pilt = "images/"+vr.getNimi()+".jpg";
        Image taust = pilt(pilt);
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(laiusResolutsioon);
        taustapilt.setFitHeight(kõrgusResolutsioon);

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);

        Scene scene = new Scene(bp, laiusResolutsioon, kõrgusResolutsioon);


        Image mängija = pilt("images/Player.png");
        ImageView mängijapilt = new ImageView();
        mängijapilt.setImage(mängija);
        mängijapilt.setY(scene.getHeight()*0.56);
        mängijapilt.setX(scene.getWidth()*0.15);
        mängijapilt.setFitWidth(128+scene.getWidth()*0.1);
        mängijapilt.setFitHeight(128+scene.getWidth()*0.1);
        mängijapilt.setRotationAxis(Rotate.Y_AXIS);
        mängijapilt.setRotate(180);

        bp.getChildren().add(mängijapilt);

        // Nupud:
        GridPane gridValikud = new GridPane();
        gridValikud.setPadding(new Insets(15, 15, 15, 15));
        gridValikud.setVgap(10);
        gridValikud.setHgap(100);

        // Nupp 1
        Button ründa = new Button("Ründa");
        GridPane.setConstraints(ründa, 0, 0);

        // Nupp 2
        Button põgene = new Button("Liigu edasi");
        GridPane.setConstraints(põgene, 1, 0);

        // Nupp 3
        Button maagia = new Button("Ründa maagiaga");
        GridPane.setConstraints(maagia, 2, 0);

        gridValikud.getChildren().addAll(ründa, põgene, maagia);
        gridValikud.setAlignment(Pos.CENTER);

        bp.setTop(gridValikud);

        // Textarea
        TextArea tekstiväli = new TextArea();
        tekstiväli.setMaxSize(laiusResolutsioon*0.4, kõrgusResolutsioon*0.4);
        tekstiväli.setOpacity(1);
        //tekstiväli.setStyle("-fx-background-color: rgba(76, 175, 80, 0.1)");
        //tekstiväli.setStyle("--fx-control-inner-background: rgba(76, 175, 80, 0.1)");
        bp.setCenter(tekstiväli);

        Text hoiatus = new Text("Saad ohutult edasi liikuda kui vastane on surnud!");
        hoiatus.setFill(Color.WHITE);
        hoiatus.setFont(Font.font(30));
        bp.setCenter(hoiatus);
        BorderPane.setMargin(hoiatus,new Insets(scene.getHeight()*0.8,10,10,10));
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(e -> {
            bp.setBottom(null);
        });
        pause.play();

        pealava.setMinHeight(757);
        pealava.setMinWidth(1293);

        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);
            double nuppuSuurus = (double) newValue*0.15;
            nuppSuurusW(ründa,nuppuSuurus);
            nuppSuurusW(põgene,nuppuSuurus);
            nuppSuurusW(maagia,nuppuSuurus);
        });

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
            double nuppuSuurus = (double) newValue*0.05;
            nuppSuurusH(ründa,nuppuSuurus);
            nuppSuurusH(põgene,nuppuSuurus);
            nuppSuurusH(maagia,nuppuSuurus);
        });

        ründa.setOnAction(actionEvent -> {
            int eludenne = m1.getElud();
            Võitlus(m1,vastane);
            int vahe = eludenne - m1.getElud();

            Text tekst = new Text("Ründad vastast!");
            tekst.setFill(Color.WHITE);
            tekst.setFont(Font.font(30));
            bp.setCenter(tekst);

            Text mElud = new Text("-" + vahe);
            mElud.setFill(Color.RED);
            mElud.setFont(Font.font(40));


            Text mMana = new Text("+3");
            mMana.setFill(Color.BLUE);
            mMana.setFont(Font.font(40));


            TextFlow mTekst = new TextFlow(mElud,new Text(System.lineSeparator()),mMana);

            bp.setLeft(mTekst);

            BorderPane.setMargin(tekst,new Insets(10,10,scene.getHeight()*0.6,10));
            BorderPane.setMargin(mTekst,new Insets(scene.getHeight()*0.5,0,10,scene.getWidth()*0.1));
            PauseTransition pause1 = new PauseTransition(Duration.seconds(1.5));
            pause1.setOnFinished(e -> {
                tekst.setText(null);
                mElud.setText(null);
                mMana.setText(null);
            });

            pause1.play();
            m1.taastaMana(3);
        });
        põgene.setOnAction(actionEvent -> {
            if (vr.getVastane().isElus()) {
                Text tekst = new Text("Põgened ruumist ja võtad kahju!");
                tekst.setFill(Color.WHITE);
                tekst.setFont(Font.font(30));
                bp.setCenter(tekst);
                BorderPane.setMargin(tekst,new Insets(10,10,scene.getHeight()*0.6,10));

                int eludenne = m1.getElud();
                vastane.ründa(m1);
                int vahe = eludenne - m1.getElud();

                Text mElud = new Text("-" + vahe);
                mElud.setFill(Color.RED);
                mElud.setFont(Font.font(40));

                bp.setLeft(mElud);
                BorderPane.setMargin(mElud,new Insets(scene.getHeight()*0.5,0,10,scene.getWidth()*0.1));

                PauseTransition pause1 = new PauseTransition(Duration.seconds(3));
                pause1.setOnFinished(e -> {
                    tekst.setText(null);
                    mElud.setText(null);
                });
                pause1.play();
            }
            else {
                Text tekst = new Text("Liigud järgmisesse ruumi!");
                tekst.setFill(Color.WHITE);
                tekst.setFont(Font.font(30));
                bp.setCenter(tekst);
                BorderPane.setMargin(tekst,new Insets(10,10,scene.getHeight()*0.6,10));
                PauseTransition pause1 = new PauseTransition(Duration.seconds(3));
                pause1.setOnFinished(e -> {
                    pealava.setScene(liikumisStseen(pealava,ruumid,ruuminumber,m1,varustuseList));
                });
                pause1.play();
            }
        });
        maagia.setOnAction(actionEvent -> {
            int rünnakuTugevus = 10; // TODO sisestamine selle tugevuse jaoks
            m1.ründaMaagiaga(vastane, rünnakuTugevus);
        });

        return scene;

    }

    public static Scene liikumisStseen(Stage pealava, List<Koobas> ruumid,int ruuminumber, Mängija m1, Varustus varustuseList) {
        List<String> taustad = new ArrayList<>();
        taustad.add("Taust.jpg");
        taustad.add("Puhkeruum.jpg");
        Image taust = pilt("images/Taust.jpg");
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(laiusResolutsioon);
        taustapilt.setFitHeight(kõrgusResolutsioon);

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);

        Scene scene = new Scene(bp, laiusResolutsioon, kõrgusResolutsioon);

        Image mängija = pilt("images/Player.png");
        ImageView mängijapilt = new ImageView();
        mängijapilt.setImage(mängija);
        mängijapilt.setY(scene.getHeight()*0.5);
        mängijapilt.setX(scene.getWidth()*0.6);
        mängijapilt.setFitWidth(128+scene.getWidth()*0.05);
        mängijapilt.setFitHeight(128+scene.getWidth()*0.05);

        bp.getChildren().add(mängijapilt);


        // Nupud:
        GridPane gridValikud = new GridPane();
        gridValikud.setPadding(new Insets(15, 15, 15, 15));
        gridValikud.setVgap(10);
        gridValikud.setHgap(100);


        // Nupp 1
        Button edasi = new Button("Liigu edasi");
        GridPane.setConstraints(edasi, 0, 2);

        // Nupp 2
        Button kaart = new Button("Vaata kaarti");
        GridPane.setConstraints(kaart, 0, 3);

        // Nupp 3
        Button varustus = new Button("Vaata varustust");
        GridPane.setConstraints(varustus, 1, 2);

        // Nupp 4
        Button välju = new Button("Salvesta ja välju");
        GridPane.setConstraints(välju, 1, 3);

        gridValikud.getChildren().addAll(edasi, kaart, varustus, välju);
        gridValikud.setAlignment(Pos.CENTER);

        bp.setBottom(gridValikud);

        pealava.setMinHeight(757);
        pealava.setMinWidth(1293);


        /**
         * NEED KAKS KUULARIT MUUDAVAD NENDE ASJADE SUURUST, MIS SIIN KIRJELDATUD ON
         * Aga siia lisada veel peategelane, vastane ja siis nupud jne.
         * Ja pärast tsüklis saame omistada uusi asju nendele imageView-dele ja vastase isendile
         */
        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);
            double nuppuSuurus = (double) newValue*0.15;
            nuppSuurusW(edasi,nuppuSuurus);
            nuppSuurusW(kaart,nuppuSuurus);
            nuppSuurusW(varustus,nuppuSuurus);
            nuppSuurusW(välju,nuppuSuurus);
            mängijapilt.setY(scene.getHeight()*0.5);
            mängijapilt.setFitWidth(128+scene.getWidth()*0.05);

        });

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
            double nuppuSuurus = (double) newValue*0.05;
            nuppSuurusH(edasi,nuppuSuurus);
            nuppSuurusH(kaart,nuppuSuurus);
            nuppSuurusH(varustus,nuppuSuurus);
            nuppSuurusH(välju,nuppuSuurus);
            mängijapilt.setX(scene.getWidth()*0.6);
            mängijapilt.setFitHeight(128+scene.getWidth()*0.05);
        });

        if (ruumid.get(ruuminumber) instanceof Võitlusruum) {

            edasi.setOnAction(actionEvent -> {
                pealava.setScene(võitlusStseen(pealava, ruumid, ruuminumber+1, m1, varustuseList));
            });

        }
        else if (ruumid.get(ruuminumber) instanceof Aarderuum) { //TODO aarderuumi scene
            edasi.setOnAction(actionEvent -> {
                /** pealava.setScene(võitlusStseen(pealava, (Aarderuum) ruumid.get(ruuminumber), ruuminumber + 1)); */
            });
        }
        else if (ruumid.get(ruuminumber) instanceof Lõksuruum) { //TODO lõksuruumi scene
            edasi.setOnAction(actionEvent -> {
                /** pealava.setScene(võitlusStseen(pealava, (Lõksuruum) ruumid.get(ruuminumber), ruuminumber + 1)); */
            });
        }

        kaart.setOnAction(actionEvent ->  {
            Text vaataKaarti = new Text(ruumid.toString());
            vaataKaarti.setFill(Color.WHITE);
            vaataKaarti.setFont(Font.font(30));
            GridPane.setConstraints(vaataKaarti, 0, 0);
            gridValikud.getChildren().add(vaataKaarti);
            PauseTransition pause = new PauseTransition(Duration.seconds(6));
            pause.setOnFinished(e -> vaataKaarti.setText(null));
            pause.play();

        });

        varustus.setOnAction(actionEvent ->  {
            Text tekst1;
            Text tekst2;
            if (m1.getRelv() != null){
                tekst1 = new Text("Elud="+m1.getElud()+" Tugevus="+(m1.getTugevus()+m1.getRelv().getRünnak())+" Mana="+m1.getMana()+", mis taastub "+m1.getManaTaastumine()+" võrra");
                tekst1.setFill(Color.WHITE);
                tekst1.setFont(Font.font(30));
                GridPane.setConstraints(tekst1, 0, 0);
            }
            else {
                tekst1 = new Text("Elud="+m1.getElud()+" Tugevus="+m1.getTugevus()+" Mana="+m1.getMana()+", mis taastub "+m1.getManaTaastumine()+" võrra");
                tekst1.setFill(Color.WHITE);
                tekst1.setFont(Font.font(30));
                GridPane.setConstraints(tekst1, 0, 0);
            }
            if (m1.getKaitserüü() == null) {
                tekst2 = new Text("Varustus | " + m1.getRelv() + " ja " + "kaitserüü puudub");
                tekst2.setFill(Color.WHITE);
                tekst2.setFont(Font.font(30));
                GridPane.setConstraints(tekst2, 0, 1);
            }
            else if (m1.getRelv() == null) {
                tekst2 = new Text("Varustus | " + "relv puudub" + " ja " + m1.getKaitserüü());
                tekst2.setFill(Color.WHITE);
                tekst2.setFont(Font.font(30));
                GridPane.setConstraints(tekst2, 0, 1);
            }
            else if (m1.getKaitserüü() == null && m1.getRelv() == null) {
                tekst2 = new Text("Varustus | " + "relv puudub" + " ja " + "kaitserüü puudub");
                tekst2.setFill(Color.WHITE);
                tekst2.setFont(Font.font(30));
                GridPane.setConstraints(tekst2, 0, 1);
            }
            else {
                tekst2 = new Text("Varustus | " + m1.getRelv() + " ja " + m1.getKaitserüü());
                tekst2.setFill(Color.WHITE);
                tekst2.setFont(Font.font(30));
                GridPane.setConstraints(tekst2, 0, 1);
            }
            //TODO Need kaks if ja if else lauset võib vist ära kustutada
            if (tekst1 != null && tekst2 == null) {
                gridValikud.getChildren().add(tekst1);
                PauseTransition pause = new PauseTransition(Duration.seconds(4));
                Text finalTekst1 = tekst1;
                pause.setOnFinished(e -> finalTekst1.setText(null));
                pause.play();
            }
            else if (tekst2 != null && tekst1 == null) {
                gridValikud.getChildren().add(tekst2);
                PauseTransition pause = new PauseTransition(Duration.seconds(4));
                Text finalTekst2 = tekst2;
                pause.setOnFinished(e -> finalTekst2.setText(null));
                pause.play();
            }
            else {
                gridValikud.getChildren().addAll(tekst1,tekst2);
                PauseTransition pause = new PauseTransition(Duration.seconds(4));
                Text finalTekst2 = tekst2;
                Text finalTekst1 = tekst1;
                pause.setOnFinished(e -> {
                    finalTekst2.setText(null);
                    finalTekst1.setText(null);
                });
                pause.play();
            }

        });

        välju.setOnAction(actionEvent ->  {
            //TODO salvestamine
        });

        return scene;
    }
}
