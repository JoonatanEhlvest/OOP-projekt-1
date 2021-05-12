package oop;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Peaklassfx extends Application {
    private static double laiusResolutsioon = 1080;
    private static double kõrgusResolutsioon = 720;
    private static int raskus = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pealava) {

        /** MÄNG ALGAB SIIT */
        pealava.setTitle("Basic Dungeon");
        Scene algusStseen = algusStseen(pealava);
        pealava.setScene(algusStseen);
        pealava.setMinHeight(720);
        pealava.setMinWidth(1080);

        pealava.show();

    }

    public static Mängija looMängija() {
        /** MÄNGIJA LOOMINE */
        Mängija m1 = new Mängija("Player 1", 40, 20, 5,4,3);
        m1.setKaitserüü(new Kaitserüü(0,"Tavalised Riided"));
        return m1;
    }

    public static List<Koobas> looKoobas() {
        /** VASTASTE NIMEDE LOOMINE */
        List<String> nimed = Arrays.asList("Orc","Maag","Lima","Zombie");

        /** KOOPA RUUMIDE LOOMINE JA SUVALISES JÄRJESTUSES KOOPASE PAIGUTAMINE*/
        List<Koobas> koobas = new ArrayList<>();
        Aarderuum aarderuum = new Aarderuum("Aarderuum");
        Võitlusruum võitlusruum1 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Krüpt");
        Võitlusruum võitlusruum2 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Vana_Raamatukogu");
        Võitlusruum võitlusruum3 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Labor");
        Lõpuruum lõpuruum = new Lõpuruum(new Vastane("Maailma Hävitaja Võlur",100,13,5),"Viimane Tuba");
        koobas.add(aarderuum);
        koobas.add(võitlusruum1);
        koobas.add(võitlusruum2);
        koobas.add(võitlusruum3);
        //Collections.shuffle(koobas);
        koobas.add(lõpuruum);

        return koobas;
    }

    public static Varustus looVarustuseList() {
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

        return varustuseList;
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

    public static Scene bossStseen(Stage pealava, List<Koobas> ruumid, int ruuminumber, Mängija m1) {
        Lõpuruum lp = (Lõpuruum) ruumid.get(ruuminumber);
        Vastane vastane = lp.getVastane();

        Image taust = pilt("images/Bossruum.png");
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(laiusResolutsioon);
        taustapilt.setFitHeight(kõrgusResolutsioon);

        BorderPane bp = new BorderPane();
        Scene scene = new Scene(bp, laiusResolutsioon, kõrgusResolutsioon);

        bp.getChildren().add(taustapilt);

        Image mängija = pilt("images/Player.png");
        ImageView mängijapilt = new ImageView();
        mängijapilt.setImage(mängija);

        Text mängijaElud = new Text(String.valueOf(m1.getElud()));
        Text mängijaMana = new Text(String.valueOf(m1.getMana()));
        mängijaElud.setFill(Color.RED);
        mängijaElud.setFont(Font.font(30));
        mängijaMana.setFill(Color.BLUE);
        mängijaMana.setFont(Font.font(30));

        mängijapilt.setY(scene.getHeight() * 0.56);
        mängijapilt.setX(scene.getWidth() * 0.15);
        mängijapilt.setFitWidth(128 + scene.getWidth() * 0.1);
        mängijapilt.setFitHeight(128 + scene.getWidth() * 0.1);
        mängijapilt.setRotationAxis(Rotate.Y_AXIS);
        mängijapilt.setRotate(180);

        mängijapilt.setY(scene.getHeight() * 0.56);
        mängijapilt.setX(scene.getWidth() * 0.15);
        mängijapilt.setFitWidth(128 + scene.getWidth() * 0.1);
        mängijapilt.setFitHeight(128 + scene.getWidth() * 0.1);

        TextFlow eludjamana = new TextFlow(mängijaElud,new Text("   "),mängijaMana);
        eludjamana.setTranslateX(scene.getWidth() * 0.20);
        eludjamana.setTranslateY(scene.getWidth() * 0.28);
        bp.getChildren().add(eludjamana);

        mängijapilt.setRotationAxis(Rotate.Y_AXIS);
        mängijapilt.setRotate(180);

        Image vastanepilt = pilt("images/Boss.png");
        ImageView vastanePilt = new ImageView();
        vastanePilt.setImage(vastanepilt);

        Text vastaneElud = new Text(String.valueOf(vastane.getElud()));
        vastaneElud.setFill(Color.RED);
        vastaneElud.setFont(Font.font(30));

        vastanePilt.setY(scene.getHeight() * 0.56);
        vastanePilt.setX(scene.getWidth() * 0.6);
        vastanePilt.setFitWidth(128 + scene.getWidth() * 0.1);
        vastanePilt.setFitHeight(128 + scene.getWidth() * 0.1);

        TextFlow elud = new TextFlow(vastaneElud);
        elud.setTranslateX(scene.getWidth() * 0.67);
        elud.setTranslateY(scene.getWidth() * 0.28);
        bp.getChildren().add(elud);

        bp.getChildren().add(mängijapilt);
        bp.getChildren().add(vastanePilt);

        // Nupud:
        GridPane gridValikud = new GridPane();
        gridValikud.setPadding(new Insets(15, 15, 15, 15));
        gridValikud.setVgap(10);
        gridValikud.setHgap(100);

        // Nupp 1
        Button ründa = new Button("Ründa");
        GridPane.setConstraints(ründa, 0, 0);

        // Nupp 2
        Button maagia = new Button("Ründa maagiaga");
        GridPane.setConstraints(maagia, 2, 0);

        double nupuSuurusW = laiusResolutsioon*0.15;
        double nupuSuurusH = laiusResolutsioon*0.05;
        nuppSuurusW(ründa,nupuSuurusW);
        nuppSuurusW(maagia,nupuSuurusW);
        nuppSuurusH(ründa,nupuSuurusH);
        nuppSuurusH(maagia,nupuSuurusH);

        gridValikud.getChildren().addAll(ründa, maagia);
        gridValikud.setAlignment(Pos.CENTER);

        bp.setTop(gridValikud);

        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);
            double nuppuSuurus = (double) newValue*0.15;
            nuppSuurusW(ründa,nuppuSuurus);
            nuppSuurusW(maagia,nuppuSuurus);

            mängijapilt.setX(scene.getWidth() * 0.15);
            mängijapilt.setFitWidth(128 + scene.getWidth() * 0.1);
            eludjamana.setTranslateX(scene.getWidth() * 0.20);
            eludjamana.setTranslateY(scene.getWidth() * 0.28);

            vastanePilt.setX(scene.getWidth() * 0.6);
            vastanePilt.setFitWidth(128 + scene.getWidth() * 0.1);
            elud.setTranslateX(scene.getWidth() * 0.67);
            elud.setTranslateY(scene.getWidth() * 0.28);

        });

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
            double nuppuSuurus = (double) newValue*0.05;
            nuppSuurusH(ründa,nuppuSuurus);
            nuppSuurusH(maagia,nuppuSuurus);

            mängijapilt.setY(scene.getHeight() * 0.56);
            mängijapilt.setFitHeight(128 + scene.getWidth() * 0.1);

            vastanePilt.setY(scene.getHeight() * 0.56);
            vastanePilt.setFitHeight(128 + scene.getWidth() * 0.1);

        });

        ründa.setOnAction(actionEvent -> {
            if (vastane.isElus()) {
                int eludenne = m1.getElud();
                int eludennev = vastane.getElud();
                Võitlus(m1, vastane);
                int vahe = eludenne - m1.getElud();
                if (m1.getElud() <= 0) pealava.setScene(lõpuStseen(pealava,false));
                int evahe = eludennev - vastane.getElud();
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

                Text eElud = new Text("-" + evahe);
                eElud.setFill(Color.RED);
                eElud.setFont(Font.font(40));
                eElud.setX(scene.getWidth() * 0.8);
                eElud.setY(scene.getHeight() * 0.6);

                TextFlow mTekst = new TextFlow(mElud, new Text(System.lineSeparator()), mMana);

                bp.getChildren().add(mTekst);
                bp.getChildren().add(eElud);


                BorderPane.setMargin(tekst, new Insets(10, 10, scene.getHeight() * 0.6, 10));

                mTekst.setTranslateX(scene.getWidth() * 0.1);
                mTekst.setTranslateY(scene.getHeight() * 0.6);
                eElud.setX(scene.getWidth() * 0.8);
                eElud.setY(scene.getHeight() * 0.7);

                mängijaElud.setText(String.valueOf(m1.getElud()));
                mängijaMana.setText(String.valueOf(m1.getMana()));
                vastaneElud.setText(String.valueOf(vastane.getElud()));
                PauseTransition pause1 = new PauseTransition(Duration.seconds(1.5));
                pause1.setOnFinished(e -> {
                    tekst.setText(null);
                    mElud.setText(null);
                    mMana.setText(null);
                    eElud.setText(null);
                });

                pause1.play();
                m1.taastaMana(3);
            }
            if (vastane.getElud()<=0) {
                vastaneElud.setText(null);
                vastanePilt.setRotationAxis(Rotate.Z_AXIS);
                vastanePilt.setRotate(90);
                Text teadeVastaseSurm = new Text("Alistasid oma vastase! Oled võitnud!.");
                teadeVastaseSurm.setFill(Color.WHITE);
                teadeVastaseSurm.setFont(Font.font(30));
                bp.setCenter(teadeVastaseSurm);
                pealava.setScene(lõpuStseen(pealava,true));
            }
        });

        // Maagiaga ründamine
        maagia.setOnAction(actionEvent -> {
            if (vastane.isElus()) {
                int eludennev = vastane.getElud();
                TextField sisestusAla = new TextField();
                sisestusAla.setPromptText("Sisesta, mitme managa tahad vastast rünnata (täisarv) ja vajuta enter");
                bp.setBottom(sisestusAla);
                PauseTransition pausPärastRünnakut = new PauseTransition(Duration.seconds(3));
                pausPärastRünnakut.setOnFinished(e -> {
                    bp.setCenter(null);
                });
                sisestusAla.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        int rünnakuTugevus;
                        CharSequence sisestus = sisestusAla.getCharacters();
                        try {
                            rünnakuTugevus = Integer.parseInt(String.valueOf(sisestus));
                            m1.ründaMaagiaga(vastane, rünnakuTugevus);
                            mängijaMana.setText(String.valueOf(m1.getMana()));
                            bp.setBottom(null);
                            Text edukasTekst = new Text("Ründasid vastast maagiaga ja tegid " + rünnakuTugevus + " punkti haiget");
                            edukasTekst.setFill(Color.WHITE);
                            edukasTekst.setFont(Font.font(30));
                            bp.setCenter(edukasTekst);
                            pausPärastRünnakut.play();

                            vastaneElud.setText(String.valueOf(vastane.getElud()));
                            if (vastane.getElud()<=0) {
                                vastane.setElud(0);
                                vastanePilt.setRotationAxis(Rotate.Z_AXIS);
                                vastanePilt.setRotate(90);
                                Text teadeVastaseSurm = new Text("Alistasid oma vastase! Võid ohutult edasi liikuda.");
                                teadeVastaseSurm.setFill(Color.WHITE);
                                teadeVastaseSurm.setFont(Font.font(30));
                                bp.getChildren().add(teadeVastaseSurm);
                                pealava.setScene(lõpuStseen(pealava,true));
                            }

                        } catch (NumberFormatException e) {
                            Text exceptionText = new Text("Sisestasid vigase numbri: " + sisestus + "\nPalun sisesta täisarv");
                            exceptionText.setFill(Color.WHITE);
                            exceptionText.setFont(Font.font(30));
                            bp.setCenter(exceptionText);
                            pausPärastRünnakut.play();
                        }

                    }
                });
            }
        });
        return scene;
    }

    public static Scene aarderuumStseen(Stage pealava, List<Koobas> ruumid, int ruuminumber, Mängija m1, Varustus varustuseList) {
        Image taust = pilt("images/Aarderuum.jpg");
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

        mängijapilt.setY(scene.getHeight() * 0.56);
        mängijapilt.setX(scene.getWidth() * 0.15);
        mängijapilt.setFitWidth(128 + scene.getWidth() * 0.1);
        mängijapilt.setFitHeight(128 + scene.getWidth() * 0.1);
        mängijapilt.setRotationAxis(Rotate.Y_AXIS);
        mängijapilt.setRotate(180);

        bp.getChildren().add(mängijapilt);

        double nupuSuurusW = laiusResolutsioon*0.15;
        double nupuSuurusH = laiusResolutsioon*0.05;

        GridPane edasigrid = new GridPane();

        Random suvalineArv = new Random();
        Varustus item = varustuseList.getKõikAsjad().get(suvalineArv.nextInt(varustuseList.getKõikAsjad().size()));

        Text itemDesc = new Text(item.toString());
        itemDesc.setFill(Color.WHITE);
        itemDesc.setFont(Font.font(30));

        Text kontroll = new Text("Sattusid aarderuumi. Kas soovid vahetada?:");
        kontroll.setFill(Color.WHITE);
        kontroll.setFont(Font.font(30));
        GridPane.setConstraints(kontroll, 0, 2);

        // Jah
        Button Jah = new Button("Jah");
        Jah.setPrefWidth(kõrgusResolutsioon*0.15);
        Jah.setPrefHeight(laiusResolutsioon*0.05);


        GridPane.setConstraints(Jah, 0, 3);

        // Ei
        Button Ei = new Button("Ei");
        Ei.setPrefWidth(kõrgusResolutsioon*0.15);
        Ei.setPrefHeight(laiusResolutsioon*0.05);
        GridPane.setConstraints(Ei, 1, 3);


        GridPane.setConstraints(itemDesc, 0, 1);

        edasigrid.getChildren().addAll(itemDesc,Jah,Ei, kontroll);
        edasigrid.setTranslateX(scene.getWidth()*0.2);
        edasigrid.setTranslateY(scene.getHeight()*0.3);

        bp.setCenter(edasigrid);
        Jah.setOnAction(actionEvent1 -> {
            if (item instanceof Relv) m1.setRelv((Relv) item);
            else if (item instanceof Kaitserüü) m1.setKaitserüü((Kaitserüü) item);
            pealava.setScene(liikumisStseen(pealava, ruumid, ruuminumber, m1, varustuseList));
        });
        Ei.setOnAction(actionEvent1 -> {
            pealava.setScene(liikumisStseen(pealava, ruumid, ruuminumber, m1, varustuseList));
        });

        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);

            mängijapilt.setX(scene.getWidth() * 0.15);
            mängijapilt.setFitWidth(128 + scene.getWidth() * 0.1);
            edasigrid.setTranslateX(scene.getWidth()*0.2);
            Ei.setPrefWidth(scene.getWidth()*0.15);
            Jah.setPrefWidth(scene.getWidth()*0.15);
        });

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);

            mängijapilt.setY(scene.getHeight() * 0.56);
            mängijapilt.setFitHeight(128 + scene.getWidth() * 0.1);
            edasigrid.setTranslateY(scene.getHeight()*0.3);
            Ei.setPrefHeight(scene.getHeight()*0.05);
            Jah.setPrefHeight(scene.getHeight()*0.05);
        });

        return scene;
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

        Text mängijaElud = new Text(String.valueOf(m1.getElud()));
        Text mängijaMana = new Text(String.valueOf(m1.getMana()));
        mängijaElud.setFill(Color.RED);
        mängijaElud.setFont(Font.font(30));
        mängijaMana.setFill(Color.BLUE);
        mängijaMana.setFont(Font.font(30));

        if (vr.getNimi().equals("Labor")) {
            mängijapilt.setY(scene.getHeight()*0.5);
            mängijapilt.setX(scene.getWidth()*0.25);
            mängijapilt.setFitWidth(128+scene.getWidth()*0.07);
            mängijapilt.setFitHeight(128+scene.getWidth()*0.07);

            TextFlow eludjamana = new TextFlow(mängijaElud,new Text("   "),mängijaMana);
            eludjamana.setTranslateX(scene.getWidth() * 0.28);
            eludjamana.setTranslateY(scene.getWidth() * 0.25);
            bp.getChildren().add(eludjamana);
        }
        else {
            mängijapilt.setY(scene.getHeight() * 0.56);
            mängijapilt.setX(scene.getWidth() * 0.15);
            mängijapilt.setFitWidth(128 + scene.getWidth() * 0.1);
            mängijapilt.setFitHeight(128 + scene.getWidth() * 0.1);

            TextFlow eludjamana = new TextFlow(mängijaElud,new Text("   "),mängijaMana);
            eludjamana.setTranslateX(scene.getWidth() * 0.20);
            eludjamana.setTranslateY(scene.getWidth() * 0.28);
            bp.getChildren().add(eludjamana);
        }

        mängijapilt.setRotationAxis(Rotate.Y_AXIS);
        mängijapilt.setRotate(180);

        Image vastanepilt = pilt("images/"+vastane.getNimi()+".png");
        ImageView vastanePilt = new ImageView();
        vastanePilt.setImage(vastanepilt);

        Text vastaneElud = new Text(String.valueOf(vastane.getElud()));
        vastaneElud.setFill(Color.RED);
        vastaneElud.setFont(Font.font(30));

        if (vr.getNimi().equals("Labor")) {
            vastanePilt.setY(scene.getHeight()*0.5);
            vastanePilt.setX(scene.getWidth()*0.65);
            vastanePilt.setFitWidth(128+scene.getWidth()*0.07);
            vastanePilt.setFitHeight(128+scene.getWidth()*0.07);

            TextFlow elud = new TextFlow(vastaneElud);
            elud.setTranslateX(scene.getWidth() * 0.72);
            elud.setTranslateY(scene.getWidth() * 0.26);
            bp.getChildren().add(elud);
        }
        else {
            vastanePilt.setY(scene.getHeight() * 0.56);
            vastanePilt.setX(scene.getWidth() * 0.6);
            vastanePilt.setFitWidth(128 + scene.getWidth() * 0.1);
            vastanePilt.setFitHeight(128 + scene.getWidth() * 0.1);

            TextFlow elud = new TextFlow(vastaneElud);
            elud.setTranslateX(scene.getWidth() * 0.67);
            elud.setTranslateY(scene.getWidth() * 0.28);
            bp.getChildren().add(elud);
        }

        bp.getChildren().add(mängijapilt);
        bp.getChildren().add(vastanePilt);

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

        double nupuSuurusW = laiusResolutsioon*0.15;
        double nupuSuurusH = laiusResolutsioon*0.05;
        nuppSuurusW(ründa,nupuSuurusW);
        nuppSuurusW(põgene,nupuSuurusW);
        nuppSuurusW(maagia,nupuSuurusW);
        nuppSuurusH(ründa,nupuSuurusH);
        nuppSuurusH(põgene,nupuSuurusH);
        nuppSuurusH(maagia,nupuSuurusH);

        gridValikud.getChildren().addAll(ründa, põgene, maagia);
        gridValikud.setAlignment(Pos.CENTER);

        bp.setTop(gridValikud);


        Text hoiatus = new Text("Saad ohutult edasi liikuda kui vastane on surnud!");
        if (vr.getNimi().equals("Labor")) {
            hoiatus.setFill(Color.BLACK);
        }
        else {
            hoiatus.setFill(Color.WHITE);
        }
        hoiatus.setFont(Font.font(30));
        bp.setCenter(hoiatus);
        BorderPane.setMargin(hoiatus,new Insets(scene.getHeight()*0.65,10,10,10));
        PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
        pause.setOnFinished(e -> {
            hoiatus.setText(null);
        });
        pause.play();

        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);
            double nuppuSuurus = (double) newValue*0.15;
            nuppSuurusW(ründa,nuppuSuurus);
            nuppSuurusW(põgene,nuppuSuurus);
            nuppSuurusW(maagia,nuppuSuurus);
            if (vr.getNimi().equals("Labor")) {
                mängijapilt.setX(scene.getWidth()*0.25);
                mängijapilt.setFitWidth(128+scene.getWidth()*0.07);
                TextFlow eludjamana = new TextFlow(mängijaElud,new Text("   "),mängijaMana);
                eludjamana.setTranslateX(scene.getWidth() * 0.28);
                eludjamana.setTranslateY(scene.getWidth() * 0.25);
                bp.getChildren().add(eludjamana);
            }
            else {
                mängijapilt.setX(scene.getWidth() * 0.15);
                mängijapilt.setFitWidth(128 + scene.getWidth() * 0.1);
                TextFlow eludjamana = new TextFlow(mängijaElud,new Text("   "),mängijaMana);
                eludjamana.setTranslateX(scene.getWidth() * 0.20);
                eludjamana.setTranslateY(scene.getWidth() * 0.28);
                bp.getChildren().add(eludjamana);
            }
            if (vr.getNimi().equals("Labor")) {
                vastanePilt.setX(scene.getWidth()*0.65);
                vastanePilt.setFitWidth(128+scene.getWidth()*0.07);
                TextFlow elud = new TextFlow(vastaneElud);
                elud.setTranslateX(scene.getWidth() * 0.72);
                elud.setTranslateY(scene.getWidth() * 0.26);
                bp.getChildren().add(elud);

            }
            else {
                vastanePilt.setX(scene.getWidth() * 0.6);
                vastanePilt.setFitWidth(128 + scene.getWidth() * 0.1);
                TextFlow elud = new TextFlow(vastaneElud);
                elud.setTranslateX(scene.getWidth() * 0.67);
                elud.setTranslateY(scene.getWidth() * 0.28);
                bp.getChildren().add(elud);
            }
        });

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
            double nuppuSuurus = (double) newValue*0.05;
            nuppSuurusH(ründa,nuppuSuurus);
            nuppSuurusH(põgene,nuppuSuurus);
            nuppSuurusH(maagia,nuppuSuurus);
            if (vr.getNimi().equals("Labor")) {
                mängijapilt.setY(scene.getHeight()*0.5);
                mängijapilt.setFitHeight(128+scene.getWidth()*0.07);
            }
            else {
                mängijapilt.setY(scene.getHeight() * 0.56);
                mängijapilt.setFitHeight(128 + scene.getWidth() * 0.1);
            }
            if (vr.getNimi().equals("Labor")) {
                vastanePilt.setY(scene.getHeight()*0.5);
                vastanePilt.setFitHeight(128+scene.getWidth()*0.07);
            }
            else {
                vastanePilt.setY(scene.getHeight() * 0.56);
                vastanePilt.setFitHeight(128 + scene.getWidth() * 0.1);
            }
        });

        ründa.setOnAction(actionEvent -> {
            if (vastane.isElus()) {
                int eludenne = m1.getElud();
                int eludennev = vastane.getElud();
                Võitlus(m1, vastane);
                int vahe = eludenne - m1.getElud();
                if (m1.getElud() <= 0) pealava.setScene(lõpuStseen(pealava,false));
                int evahe = eludennev - vastane.getElud();
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

                Text eElud = new Text("-" + evahe);
                eElud.setFill(Color.RED);
                eElud.setFont(Font.font(40));
                eElud.setX(scene.getWidth() * 0.8);
                eElud.setY(scene.getHeight() * 0.6);

                TextFlow mTekst = new TextFlow(mElud, new Text(System.lineSeparator()), mMana);

                bp.getChildren().add(mTekst);
                bp.getChildren().add(eElud);


                BorderPane.setMargin(tekst, new Insets(10, 10, scene.getHeight() * 0.6, 10));
                if (vr.getNimi().equals("Labor")) {
                    mTekst.setTranslateX(scene.getWidth() * 0.2);
                    mTekst.setTranslateY(scene.getHeight() * 0.5);
                    eElud.setX(scene.getWidth() * 0.8);
                    eElud.setY(scene.getHeight() * 0.6);
                } else {
                    mTekst.setTranslateX(scene.getWidth() * 0.1);
                    mTekst.setTranslateY(scene.getHeight() * 0.6);
                    eElud.setX(scene.getWidth() * 0.8);
                    eElud.setY(scene.getHeight() * 0.7);
                }
                mängijaElud.setText(String.valueOf(m1.getElud()));
                mängijaMana.setText(String.valueOf(m1.getMana()));
                vastaneElud.setText(String.valueOf(vastane.getElud()));
                PauseTransition pause1 = new PauseTransition(Duration.seconds(1.5));
                pause1.setOnFinished(e -> {
                    tekst.setText(null);
                    mElud.setText(null);
                    mMana.setText(null);
                    eElud.setText(null);
                });

                pause1.play();
                m1.taastaMana(3);
            }
            if (vastane.getElud()<=0) {
                m1.setElud(m1.getElud()+3);
                if (m1.getElud() > 40) m1.setElud(40);
                vastaneElud.setText(null);
                vastanePilt.setRotationAxis(Rotate.Z_AXIS);
                vastanePilt.setRotate(90);
                Text teadeVastaseSurm = new Text("Alistasid oma vastase! Võid ohutult edasi liikuda.");
                teadeVastaseSurm.setFill(Color.WHITE);
                teadeVastaseSurm.setFont(Font.font(30));
                bp.setCenter(teadeVastaseSurm);
                raskus += 1;
            }
        });
        põgene.setOnAction(actionEvent -> {
            if (vr.getVastane().isElus()) {
                Text tekst = new Text("Põgened ruumist ja võtad kahju!");
                if (vr.getNimi().equals("Labor")) {
                    tekst.setFill(Color.BLACK);
                }
                else {
                    tekst.setFill(Color.WHITE);
                }
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
                    pealava.setScene(liikumisStseen(pealava,ruumid,ruuminumber,m1,varustuseList));
                });
                pause1.play();
            }
            else {
                GridPane edasigrid = new GridPane();
                Text tekst = new Text("Liigud järgmisesse ruumi!");
                if (vr.getNimi().equals("Labor")) {
                    tekst.setFill(Color.BLACK);
                }
                else {
                    tekst.setFill(Color.WHITE);
                }
                tekst.setFont(Font.font(30));

                Varustus item = vastane.sureb(varustuseList,m1);

                Text itemDesc = new Text(item.toString());
                if (vr.getNimi().equals("Labor")) itemDesc.setFill(Color.BLACK);
                else itemDesc.setFill(Color.WHITE);
                itemDesc.setFont(Font.font(30));

                Text kontroll = new Text("Kas soovid vahetada?:");
                if (vr.getNimi().equals("Labor")) kontroll.setFill(Color.BLACK);
                else kontroll.setFill(Color.WHITE);
                kontroll.setFont(Font.font(30));
                GridPane.setConstraints(kontroll, 0, 2);

                // Jah
                Button Jah = new Button("Jah");
                GridPane.setConstraints(Jah, 0, 3);
                Jah.setPrefWidth(scene.getHeight()*0.15);
                Jah.setPrefHeight(scene.getHeight()*0.05);
                // Ei
                Button Ei = new Button("Ei");
                GridPane.setConstraints(Ei, 1, 3);

                GridPane.setConstraints(itemDesc, 0, 1);
                GridPane.setConstraints(tekst, 0, 0);
                GridPane.setConstraints(tekst, 0, 0);
                edasigrid.getChildren().addAll(tekst,itemDesc,Jah,Ei, kontroll);

                bp.setCenter(edasigrid);
                BorderPane.setMargin(edasigrid, new Insets(scene.getHeight() * 0.2, 0, 0, 0));
                Jah.setOnAction(actionEvent1 -> {
                    if (item instanceof Relv) m1.setRelv((Relv) item);
                    else if (item instanceof Kaitserüü) m1.setKaitserüü((Kaitserüü) item);
                    pealava.setScene(liikumisStseen(pealava, ruumid, ruuminumber, m1, varustuseList));
                });
                Ei.setOnAction(actionEvent1 -> {
                    pealava.setScene(liikumisStseen(pealava, ruumid, ruuminumber, m1, varustuseList));
                });
            }
        });
        // Maagiaga ründamine
        maagia.setOnAction(actionEvent -> {
            if (vastane.isElus()) {
                int eludennev = vastane.getElud();
                TextField sisestusAla = new TextField();
                sisestusAla.setPromptText("Sisesta, mitme managa tahad vastast rünnata (täisarv) ja vajuta enter");
                bp.setBottom(sisestusAla);
                PauseTransition pausPärastRünnakut = new PauseTransition(Duration.seconds(2));
                pausPärastRünnakut.setOnFinished(e -> {
                    bp.setCenter(null);
                });
                sisestusAla.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        int rünnakuTugevus;
                        CharSequence sisestus = sisestusAla.getCharacters();
                        try {
                            rünnakuTugevus = Integer.parseInt(String.valueOf(sisestus));
                            m1.ründaMaagiaga(vastane, rünnakuTugevus);
                            mängijaMana.setText(String.valueOf(m1.getMana()));
                            bp.setBottom(null);
                            Text edukasTekst = new Text("Ründasid vastast maagiaga ja tegid " + rünnakuTugevus + " punkti haiget");
                            edukasTekst.setFill(Color.WHITE);
                            edukasTekst.setFont(Font.font(30));
                            bp.setCenter(edukasTekst);
                            pausPärastRünnakut.play();

                            vastaneElud.setText(String.valueOf(vastane.getElud()));
                            if (vastane.getElud()<=0) {
                                m1.setElud(m1.getElud()+3);
                                if (m1.getElud() > 40) m1.setElud(40);
                                vastaneElud.setText(null);
                                vastanePilt.setRotationAxis(Rotate.Z_AXIS);
                                vastanePilt.setRotate(90);
                                Text teadeVastaseSurm = new Text("Alistasid oma vastase! Võid ohutult edasi liikuda.");
                                teadeVastaseSurm.setFill(Color.WHITE);
                                teadeVastaseSurm.setFont(Font.font(30));
                                bp.getChildren().add(teadeVastaseSurm);
                                raskus += 1;
                            }

                        } catch (NumberFormatException e) {
                            Text exceptionText = new Text("Sisestasid vigase numbri: " + sisestus + "\nPalun sisesta täisarv");
                            exceptionText.setFill(Color.WHITE);
                            exceptionText.setFont(Font.font(30));
                            bp.setCenter(exceptionText);
                            pausPärastRünnakut.play();
                        }

                    }
                });
            }
        });

        return scene;

    }

    public static Scene liikumisStseen(Stage pealava, List<Koobas> ruumid,int ruuminumber, Mängija m1, Varustus varustuseList) {
        Image taust;
        if (ruuminumber == 0) taust = pilt("images/Taust.jpg");
        else {
            taust = pilt("images/Puhkeruum.jpg");
        }
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

        if (ruuminumber == 0){
            mängijapilt.setY(scene.getHeight()*0.5);
            mängijapilt.setX(scene.getWidth()*0.6);
            mängijapilt.setFitWidth(128+scene.getWidth()*0.05);
            mängijapilt.setFitHeight(128+scene.getWidth()*0.05);
        }
        else {
            mängijapilt.setY(scene.getHeight()*0.5);
            mängijapilt.setX(scene.getWidth()*0.6);
            mängijapilt.setFitWidth(128+scene.getWidth()*0.1);
            mängijapilt.setFitHeight(128+scene.getWidth()*0.1);
        }


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

        // Algväärtustab nuppude suurused
        double nupuSuurusW = laiusResolutsioon*0.15;
        double nupuSuurusH = kõrgusResolutsioon*0.05;
        nuppSuurusW(edasi,nupuSuurusW);
        nuppSuurusW(kaart,nupuSuurusW);
        nuppSuurusW(varustus,nupuSuurusW);
        nuppSuurusW(välju,nupuSuurusW);
        nuppSuurusH(edasi,nupuSuurusH);
        nuppSuurusH(kaart,nupuSuurusH);
        nuppSuurusH(varustus,nupuSuurusH);
        nuppSuurusH(välju,nupuSuurusH);


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
            if (ruuminumber == 0){
                mängijapilt.setY(scene.getHeight()*0.5);
                mängijapilt.setFitWidth(128+scene.getWidth()*0.05);
            }
            else {
                mängijapilt.setY(scene.getHeight()*0.5);
                mängijapilt.setFitWidth(128+scene.getWidth()*0.1);
            }
        });

        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
            double nuppuSuurus = (double) newValue*0.05;
            nuppSuurusH(edasi,nuppuSuurus);
            nuppSuurusH(kaart,nuppuSuurus);
            nuppSuurusH(varustus,nuppuSuurus);
            nuppSuurusH(välju,nuppuSuurus);
            if (ruuminumber == 0){
                mängijapilt.setX(scene.getWidth()*0.6);
                mängijapilt.setFitHeight(128+scene.getWidth()*0.05);
            }
            else {
                mängijapilt.setX(scene.getWidth()*0.6);
                mängijapilt.setFitHeight(128+scene.getWidth()*0.1);
            }
        });

        if (ruumid.get(ruuminumber) instanceof Võitlusruum) {

            edasi.setOnAction(actionEvent -> {
                pealava.setScene(võitlusStseen(pealava, ruumid, ruuminumber+1, m1, varustuseList));
            });

        }
        else if (ruumid.get(ruuminumber) instanceof Aarderuum) { //TODO aarderuumi scene
            edasi.setOnAction(actionEvent -> {
                pealava.setScene(aarderuumStseen(pealava,ruumid,ruuminumber+1,m1,varustuseList));
            });
        }
        else if (ruumid.get(ruuminumber) instanceof Lõpuruum) { //TODO lõksuruumi scene
            edasi.setOnAction(actionEvent -> {
                pealava.setScene(bossStseen(pealava,ruumid,ruuminumber,m1));
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

            gridValikud.getChildren().addAll(tekst1,tekst2);
            PauseTransition pause = new PauseTransition(Duration.seconds(4));
            Text finalTekst2 = tekst2;
            Text finalTekst1 = tekst1;
            pause.setOnFinished(e -> {
                finalTekst2.setText(null);
                finalTekst1.setText(null);
            });
            pause.play();
        });

        välju.setOnAction(actionEvent ->  {
            try {
            Salvestaja.salvestaMängija(m1);
            Salvestaja.salvestaKoobas(ruumid);
            Salvestaja.salvestaVarustus(varustuseList);
            Salvestaja.salvestaRuuminumber(ruuminumber);
            }
            catch (IOException e) {
                System.out.println("Viga salvestamisega:" + e);
                throw new RuntimeException(e);
            }
            pealava.close();
        });

        return scene;
    }

    public static Scene algusStseen(Stage pealava) {
        Image taust = pilt("images/Taust.jpg");
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(laiusResolutsioon);
        taustapilt.setFitHeight(kõrgusResolutsioon);

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);

        Button valik1 = new Button("Alusta uut mängu");
        Button valik2 = new Button("Jätka salvestatud mängu");
        // Algväärtustab nuppude suurused
        double nupuSuurusW = laiusResolutsioon*0.15;
        double nupuSuurusH = kõrgusResolutsioon*0.05;
        nuppSuurusW(valik1,nupuSuurusW);
        nuppSuurusW(valik2,nupuSuurusW);
        nuppSuurusH(valik1,nupuSuurusH);
        nuppSuurusH(valik2,nupuSuurusH);

        VBox vb = new VBox(valik1, valik2);
        vb.setAlignment(Pos.CENTER);
        bp.setCenter(vb);

        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);
            double nuppuSuurus = (double) newValue*0.15;
            nuppSuurusW(valik1,nuppuSuurus);
            nuppSuurusW(valik2,nuppuSuurus);
            // Lahutame välja timmitud arvu piksleid, kuna muidu muutus aken stseene vahetades järjest suuremaks
            laiusResolutsioon = (double) newValue- 16;
        });
        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
            double nuppuSuurus = (double) newValue*0.05;
            nuppSuurusH(valik1,nuppuSuurus);
            nuppSuurusH(valik2,nuppuSuurus);
            // Lahutame välja timmitud arvu piksleid, kuna muidu muutus aken stseene vahetades järjest suuremaks
            kõrgusResolutsioon = (double) newValue - 39;
        });


        valik1.setOnAction(actionEvent ->  {
            Mängija m1 = looMängija();
            Varustus varustuseList = looVarustuseList();
            List<Koobas> koobas = looKoobas();
            int ruuminumber = 0;
            Scene liikumisStseen = liikumisStseen(pealava, koobas, ruuminumber, m1, varustuseList);
            pealava.setScene(liikumisStseen);
        });

        valik2.setOnAction(actionEvent ->  {
            Mängija m1;
            Varustus varustuseList;
            List<Koobas> koobas;
            int ruuminumber = 0;

            try { m1 = SalvestuseLugeja.loeMängija();
            varustuseList = SalvestuseLugeja.loeVarustusList();
            koobas = SalvestuseLugeja.loeKoobas();
            ruuminumber = SalvestuseLugeja.loeRuuminumber();}
            catch (Exception e) {
                System.out.println("Salvestatu lugemisel tekkis viga: " + e);
                System.out.println("Alustan uut mängu");
                m1 = looMängija();
                varustuseList = looVarustuseList();
                koobas = looKoobas();
            }

            Scene liikumisStseen = liikumisStseen(pealava, koobas, ruuminumber, m1, varustuseList);
            pealava.setScene(liikumisStseen);
        });
        Scene algusStseen = new Scene(bp, laiusResolutsioon, kõrgusResolutsioon);
        return algusStseen;
    }

    public static Scene lõpuStseen(Stage pealava,boolean võit) {
        Image taust = pilt("images/Taust.jpg");
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(laiusResolutsioon);
        taustapilt.setFitHeight(kõrgusResolutsioon);

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);

        Text õnnitlused = null;
        if (võit) õnnitlused = new Text("Olete mängu võitnud!");
        else õnnitlused = new Text("Said surma!");
        õnnitlused.setFill(Color.WHITE);
        õnnitlused.setFont(Font.font(30));
        Button valik1 = new Button("Välju mängust");
        Button valik2 = new Button("Alusta uut mängu");

        // Algväärtustab nuppude suurused
        double nupuSuurusW = laiusResolutsioon*0.15;
        double nupuSuurusH = kõrgusResolutsioon*0.05;
        nuppSuurusW(valik1,nupuSuurusW);
        nuppSuurusW(valik2,nupuSuurusW);
        nuppSuurusH(valik1,nupuSuurusH);
        nuppSuurusH(valik2,nupuSuurusH);

        VBox vb = new VBox(õnnitlused ,valik1, valik2);
        vb.setAlignment(Pos.CENTER);
        bp.setCenter(vb);

        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue);
            double nuppuSuurus = (double) newValue*0.15;
            nuppSuurusW(valik1,nuppuSuurus);

        });
        pealava.heightProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitHeight((double) newValue);
            double nuppuSuurus = (double) newValue*0.05;
            nuppSuurusH(valik1,nuppuSuurus);
            nuppSuurusH(valik2,nuppuSuurus);
        });


        valik1.setOnAction(actionEvent ->  {
            pealava.close();
        });

        valik2.setOnAction(actionEvent ->  {
            pealava.setScene(algusStseen(pealava));
        });
        Scene lõpuStseen = new Scene(bp, laiusResolutsioon, kõrgusResolutsioon);
        return lõpuStseen;
    }
}
