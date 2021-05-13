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
    private static double laiusResolutsioon = 1080; // Ekraani laius
    private static double kõrgusResolutsioon = 720; // Ekraani pikkus
    private static int raskus = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage pealava) {

        /** MÄNG ALGAB SIIT */
        pealava.setTitle("DungeonCrawl");
        Scene algusStseen = algusStseen(pealava); // algus stseeni loomine
        pealava.setScene(algusStseen); // pealava algus stseeniks
        pealava.setMinHeight(720); // Min suurus
        pealava.setMinWidth(1080);

        pealava.show();

    }

    /**
     * Loob mängija
     * @return - tagastab loodud mängija
     */
    public static Mängija looMängija() {
        /** MÄNGIJA LOOMINE */
        Mängija m1 = new Mängija("Player 1", 40, 20, 5,4,3);
        m1.setKaitserüü(new Kaitserüü(0,"Tavalised Riided"));
        return m1;
    }

    /**
     * Loob mängu ruumid
     * @return - tagastab listi ruumidest
     */
    public static List<Koobas> looKoobas() {
        /** VASTASTE NIMEDE LOOMINE */
        List<String> nimed = Arrays.asList("Orc","Maag","Lima","Zombie"); // Vastaste nimed, kasutatakse piltide jaoks

        /** KOOPA RUUMIDE LOOMINE JA SUVALISES JÄRJESTUSES KOOPASE PAIGUTAMINE*/
        List<Koobas> koobas = new ArrayList<>(); // Ruumid listina
        Aarderuum aarderuum = new Aarderuum("Aarderuum"); // Aarderuum
        Võitlusruum võitlusruum1 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Krüpt");
        Võitlusruum võitlusruum2 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Vana_Raamatukogu");
        Võitlusruum võitlusruum3 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(10,20),Juhuslik.randint(3,5)*raskus,(raskus-1)*2),"Labor");
        Lõpuruum lõpuruum = new Lõpuruum(new Vastane("Maailma Hävitaja Võlur",100,13,5),"Viimane Tuba"); //Bossiruum
        koobas.add(aarderuum);
        koobas.add(võitlusruum1);
        koobas.add(võitlusruum2);
        koobas.add(võitlusruum3);
        //Collections.shuffle(koobas);
        koobas.add(lõpuruum);

        return koobas; //Tagasta ruumide list
    }

    /**
     * Loob varustuse listi kõikide relvade ja kaitserüüdega
     * @return - Tagastab varustuse listi
     */
    public static Varustus looVarustuseList() {
        /** VARUSTUSE LOOMINE JA LISAMINE VARUSTUSE LISTI */
        Varustus varustuseList = new Varustus(); // Varustuse list

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

        return varustuseList; // Tagasta list
    }

    /**
     * Loeb pildi sisse
     * @param path - Failitee
     * @return - tagastab Image isendina pildi kui see õnnestub
     */
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

    /**
     * Abimeetod: suurendab nupu suurust
     * @param nupp - suurendatav nupp
     * @param uusSuurus - uus kõrgus
     */
    public static void nuppSuurusH(Button nupp, double uusSuurus) {
        nupp.setPrefHeight(uusSuurus);
    }

    /**
     * Abimeetod: suurendab nupu suurust
     * @param nupp - suurendatav nupp
     * @param uusSuurus - uus laius
     */
    public static void nuppSuurusW(Button nupp, double uusSuurus) {
        nupp.setPrefWidth(uusSuurus);
    }

    /**
     * Võtab suvalise elemendi listis
     * @param list - list kus võetakse element
     * @return - tagasta suvaline element listist
     */
    public static String RandElem(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    /**
     * Paneb mängija vastase vastu võitlema
     * @param mängija
     * @param vastane
     */
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

    /**
     * Stseen kus on viimane boss
     * @param pealava
     * @param ruumid
     * @param ruuminumber
     * @param m1 - mängija
     * @return - tagastab loodud stseeni
     */
    public static Scene bossStseen(Stage pealava, List<Koobas> ruumid, int ruuminumber, Mängija m1) {
        Lõpuruum lp = (Lõpuruum) ruumid.get(ruuminumber); // Ruum
        Vastane vastane = lp.getVastane(); // vastane ruumis

        Image taust = pilt("images/Bossruum.png"); // Bossiruumi taust
        ImageView taustapilt = new ImageView(); // taustapildi imageview
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(laiusResolutsioon); // Pilt ekraani suurusega
        taustapilt.setFitHeight(kõrgusResolutsioon);

        BorderPane bp = new BorderPane(); // Põhi paan, kus asjad paigutatakse
        Scene scene = new Scene(bp, laiusResolutsioon, kõrgusResolutsioon); // Stseen

        bp.getChildren().add(taustapilt);

        Image mängija = pilt("images/Player.png"); // Mängija pilt
        ImageView mängijapilt = new ImageView();
        mängijapilt.setImage(mängija);

        Text mängijaElud = new Text(String.valueOf(m1.getElud())); // Mängija elud ja mana
        Text mängijaMana = new Text(String.valueOf(m1.getMana()));
        mängijaElud.setFill(Color.RED);
        mängijaElud.setFont(Font.font(30));
        mängijaMana.setFill(Color.BLUE);
        mängijaMana.setFont(Font.font(30));

        mängijapilt.setY(scene.getHeight() * 0.56); // mängija pildi koht ja suurus
        mängijapilt.setX(scene.getWidth() * 0.15);
        mängijapilt.setFitWidth(128 + scene.getWidth() * 0.1);
        mängijapilt.setFitHeight(128 + scene.getWidth() * 0.1);
        mängijapilt.setRotationAxis(Rotate.Y_AXIS);
        mängijapilt.setRotate(180);

        TextFlow eludjamana = new TextFlow(mängijaElud,new Text("   "),mängijaMana); // elud ja mana kokkupandud
        eludjamana.setTranslateX(scene.getWidth() * 0.20);
        eludjamana.setTranslateY(scene.getWidth() * 0.28);
        bp.getChildren().add(eludjamana);

        mängijapilt.setRotationAxis(Rotate.Y_AXIS); // peegelda mängija pilt
        mängijapilt.setRotate(180);

        Image vastanepilt = pilt("images/Boss.png"); // vastase pilt
        ImageView vastanePilt = new ImageView();
        vastanePilt.setImage(vastanepilt);

        Text vastaneElud = new Text(String.valueOf(vastane.getElud())); // vastase elud
        vastaneElud.setFill(Color.RED);
        vastaneElud.setFont(Font.font(30));

        vastanePilt.setY(scene.getHeight() * 0.56);
        vastanePilt.setX(scene.getWidth() * 0.6);
        vastanePilt.setFitWidth(128 + scene.getWidth() * 0.1);
        vastanePilt.setFitHeight(128 + scene.getWidth() * 0.1);

        TextFlow elud = new TextFlow(vastaneElud); // vastase elud
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
        Button ründa = new Button("Ründa"); // rünnaku nupp
        GridPane.setConstraints(ründa, 0, 0);

        // Nupp 2
        Button maagia = new Button("Ründa maagiaga"); // ründa managa nupp
        GridPane.setConstraints(maagia, 2, 0);

        double nupuSuurusW = laiusResolutsioon*0.15;
        double nupuSuurusH = laiusResolutsioon*0.05;
        nuppSuurusW(ründa,nupuSuurusW);
        nuppSuurusW(maagia,nupuSuurusW); // muuda nuppude suurused
        nuppSuurusH(ründa,nupuSuurusH);
        nuppSuurusH(maagia,nupuSuurusH);

        gridValikud.getChildren().addAll(ründa, maagia);
        gridValikud.setAlignment(Pos.CENTER);

        bp.setTop(gridValikud);

        /**
         * Pealava kuular, kui muudetakse laiust, siis mudetakse teiste asjade suurust ka
         */
        pealava.widthProperty().addListener((observable, oldValue, newValue) -> {
            taustapilt.setFitWidth((double) newValue); // taustapildi suurus
            double nuppuSuurus = (double) newValue*0.15;
            nuppSuurusW(ründa,nuppuSuurus);
            nuppSuurusW(maagia,nuppuSuurus); // Nuppude suurused

            mängijapilt.setX(scene.getWidth() * 0.15);
            mängijapilt.setFitWidth(128 + scene.getWidth() * 0.1); // Mängija suurus ja tema elud
            eludjamana.setTranslateX(scene.getWidth() * 0.20);
            eludjamana.setTranslateY(scene.getWidth() * 0.28);

            vastanePilt.setX(scene.getWidth() * 0.6);
            vastanePilt.setFitWidth(128 + scene.getWidth() * 0.1); // vastase suurus ja tema elud
            elud.setTranslateX(scene.getWidth() * 0.67);
            elud.setTranslateY(scene.getWidth() * 0.28);

        });

        /**
         * Pealava kuular, kui muudetakse kõrgust, siis mudetakse teiste asjade suurust ka
         */
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

        ründa.setOnAction(actionEvent -> { // Ründa nupu tegevus
            if (vastane.isElus()) { // Kui vastane on elus, siis saad rünnata
                int eludenne = m1.getElud();
                int eludennev = vastane.getElud(); //Vastase ja mängija elud enne
                Võitlus(m1, vastane); // Mängija ja vastane võitlevad
                int vahe = eludenne - m1.getElud(); // Mängija elud pärast
                if (m1.getElud() <= 0) pealava.setScene(lõpuStseen(pealava,false)); // Kui boss sureb, siis lõpuStseen
                int evahe = eludennev - vastane.getElud(); // vastase elud pärast
                Text tekst = new Text("Ründad vastast!");
                tekst.setFill(Color.WHITE);
                tekst.setFont(Font.font(30));
                bp.setCenter(tekst);

                Text mElud = new Text("-" + vahe); // Mängija kaotatud elud
                mElud.setFill(Color.RED);
                mElud.setFont(Font.font(40));

                Text mMana = new Text("+3"); // mängija juurde saadud mana
                mMana.setFill(Color.BLUE);
                mMana.setFont(Font.font(40));

                Text eElud = new Text("-" + evahe); //Vastase kaotatud elud
                eElud.setFill(Color.RED);
                eElud.setFont(Font.font(40));
                eElud.setX(scene.getWidth() * 0.8);
                eElud.setY(scene.getHeight() * 0.6);

                TextFlow mTekst = new TextFlow(mElud, new Text(System.lineSeparator()), mMana); // Mängija kaotatud elu ja mana

                bp.getChildren().add(mTekst);
                bp.getChildren().add(eElud);


                BorderPane.setMargin(tekst, new Insets(10, 10, scene.getHeight() * 0.6, 10));

                mTekst.setTranslateX(scene.getWidth() * 0.1);
                mTekst.setTranslateY(scene.getHeight() * 0.6);
                eElud.setX(scene.getWidth() * 0.8);
                eElud.setY(scene.getHeight() * 0.7);

                mängijaElud.setText(String.valueOf(m1.getElud()));
                mängijaMana.setText(String.valueOf(m1.getMana())); // Mängija ja vastase elude update
                vastaneElud.setText(String.valueOf(vastane.getElud()));
                PauseTransition pause1 = new PauseTransition(Duration.seconds(1.5));
                pause1.setOnFinished(e -> {
                    tekst.setText(null);
                    mElud.setText(null); // Peale rünnakut kaota tekstid ära ekraanilt
                    mMana.setText(null);
                    eElud.setText(null);
                });

                pause1.play();
                m1.taastaMana(3); // Taasta 3 mana iga käik
            }
            if (vastane.getElud()<=0) { // Kui vastane on surnud
                vastaneElud.setText(null);
                vastanePilt.setRotationAxis(Rotate.Z_AXIS);
                vastanePilt.setRotate(90);
                Text teadeVastaseSurm = new Text("Alistasid oma vastase! Oled võitnud!.");
                teadeVastaseSurm.setFill(Color.WHITE);
                teadeVastaseSurm.setFont(Font.font(30));
                bp.setCenter(teadeVastaseSurm);
                pealava.setScene(lõpuStseen(pealava,true)); // Tuleb lõpu stseen
            }
        });

        // Maagiaga ründamine
        maagia.setOnAction(actionEvent -> {
            if (vastane.isElus()) {
                int eludennev = vastane.getElud();
                TextField sisestusAla = new TextField(); // loob tekstfieldi kuhu saab sisestada kui palju mana soovid kasutada
                sisestusAla.setPromptText("Sisesta, mitme managa tahad vastast rünnata (täisarv) ja vajuta enter");
                bp.setBottom(sisestusAla);
                PauseTransition pausPärastRünnakut = new PauseTransition(Duration.seconds(3));
                pausPärastRünnakut.setOnFinished(e -> {
                    bp.setCenter(null);
                });
                sisestusAla.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) { // Kui vajutad enterit, siis käitab
                        int rünnakuTugevus;
                        CharSequence sisestus = sisestusAla.getCharacters();
                        try {
                            rünnakuTugevus = Integer.parseInt(String.valueOf(sisestus));
                            m1.ründaMaagiaga(vastane, rünnakuTugevus); // ründa maagiaga
                            mängijaMana.setText(String.valueOf(m1.getMana()));
                            bp.setBottom(null);
                            Text edukasTekst = new Text("Ründasid vastast maagiaga ja tegid " + rünnakuTugevus + " punkti haiget");
                            edukasTekst.setFill(Color.WHITE);
                            edukasTekst.setFont(Font.font(30));
                            bp.setCenter(edukasTekst);
                            pausPärastRünnakut.play();

                            vastaneElud.setText(String.valueOf(vastane.getElud()));
                            if (vastane.getElud()<=0) { // Kui vastane on surnud, siis jätka
                                vastane.setElud(0);
                                vastanePilt.setRotationAxis(Rotate.Z_AXIS);
                                vastanePilt.setRotate(90);
                                Text teadeVastaseSurm = new Text("Alistasid oma vastase! Võid ohutult edasi liikuda.");
                                teadeVastaseSurm.setFill(Color.WHITE);
                                teadeVastaseSurm.setFont(Font.font(30));
                                bp.getChildren().add(teadeVastaseSurm);
                                pealava.setScene(lõpuStseen(pealava,true));
                            }

                        } catch (NumberFormatException e) { // Erind kui kasutaja ei sisesta täisarvu
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

    /**
     * Aarderuumi stseen, sisaldab ühte varustust
     * @param pealava
     * @param ruumid
     * @param ruuminumber
     * @param m1 mängija
     * @param varustuseList
     * @return - tagasta stseen
     */
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

        Random suvalineArv = new Random(); // valib suvalise varustuse varustuselistist
        Varustus item = varustuseList.getKõikAsjad().get(suvalineArv.nextInt(varustuseList.getKõikAsjad().size()));

        Text itemDesc = new Text(item.toString());
        itemDesc.setFill(Color.WHITE); // asja kirjeldus
        itemDesc.setFont(Font.font(30));

        Text kontroll = new Text("Sattusid aarderuumi. Kas soovid vahetada?:"); // Kas soovid vahetada vana asja vastu
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
        Jah.setOnAction(actionEvent1 -> { // Kui jah, siis vaheta varustus ja liigu järgmisesse ruumi
            if (item instanceof Relv) m1.setRelv((Relv) item);
            else if (item instanceof Kaitserüü) m1.setKaitserüü((Kaitserüü) item);
            pealava.setScene(liikumisStseen(pealava, ruumid, ruuminumber, m1, varustuseList));
        });
        Ei.setOnAction(actionEvent1 -> { // Ei korral liigu järgmisesse ruumi
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

    /**
     * Stseen, kus toimub põhitegevus
     * @param pealava
     * @param ruumid
     * @param ruuminumber
     * @param m1
     * @param varustuseList
     * @return
     */
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

        if (vr.getNimi().equals("Labor")) { // Labori pildid on veidi teised mõõtmed
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
        Button põgene = new Button("Liigu edasi"); // Põgene nupp (Liigu edasi), liigud järgmisesse ruumi
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


        Text hoiatus = new Text("Saad ohutult edasi liikuda kui vastane on surnud!"); // hoiatus liigu edasi kohta
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

        ründa.setOnAction(actionEvent -> { // sama mis eespool kirjeldatud
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
        põgene.setOnAction(actionEvent -> { // Põgene nupu tegevus
            if (vr.getVastane().isElus()) { // Kui vastane on elus, siis põgened ja võtad kahju
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
                    pealava.setScene(liikumisStseen(pealava,ruumid,ruuminumber,m1,varustuseList)); // Liigu edasi
                });
                pause1.play();
            }
            else { // Kui vastane on surnud, siis saad uue asja ja valiku, kas soovid vahetada selle vana vastu
                GridPane edasigrid = new GridPane();
                Text tekst = new Text("Liigud järgmisesse ruumi!");
                if (vr.getNimi().equals("Labor")) {
                    tekst.setFill(Color.BLACK);
                }
                else {
                    tekst.setFill(Color.WHITE);
                }
                tekst.setFont(Font.font(30));

                Varustus item = vastane.sureb(varustuseList,m1); // vastase käest varustus

                Text itemDesc = new Text(item.toString()); // kirjeldus
                if (vr.getNimi().equals("Labor")) itemDesc.setFill(Color.BLACK);
                else itemDesc.setFill(Color.WHITE);
                itemDesc.setFont(Font.font(30));

                Text kontroll = new Text("Kas soovid vahetada?:"); // kontroll
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
                    pealava.setScene(liikumisStseen(pealava, ruumid, ruuminumber, m1, varustuseList)); //Liigu edasi
                });
                Ei.setOnAction(actionEvent1 -> {
                    pealava.setScene(liikumisStseen(pealava, ruumid, ruuminumber, m1, varustuseList));
                });
            }
        });
        // Maagiaga ründamine
        maagia.setOnAction(actionEvent -> { // sama mis eespool kirjeldatud
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

    /**
     * Pausihetk mängus, saad vaadata oma varustust, kaarti ja salvestada ja väljuda mängust
     * @param pealava
     * @param ruumid
     * @param ruuminumber
     * @param m1
     * @param varustuseList
     * @return
     */
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
        // Järgmisesse ruumi liikumine, vastavalt mis tüüpi ruum on
        if (ruumid.get(ruuminumber) instanceof Võitlusruum) {

            edasi.setOnAction(actionEvent -> {
                pealava.setScene(võitlusStseen(pealava, ruumid, ruuminumber+1, m1, varustuseList));
            });
        }
        else if (ruumid.get(ruuminumber) instanceof Aarderuum) {
            edasi.setOnAction(actionEvent -> {
                pealava.setScene(aarderuumStseen(pealava,ruumid,ruuminumber+1,m1,varustuseList));
            });
        }
        else if (ruumid.get(ruuminumber) instanceof Lõpuruum) {
            edasi.setOnAction(actionEvent -> {
                pealava.setScene(bossStseen(pealava,ruumid,ruuminumber,m1));
            });
        }

        kaart.setOnAction(actionEvent ->  { // kaardi nupp
            Text vaataKaarti = new Text(ruumid.toString()); // Näitab ruumide listi ekraanil
            vaataKaarti.setFill(Color.WHITE);
            vaataKaarti.setFont(Font.font(30));
            GridPane.setConstraints(vaataKaarti, 0, 0);
            gridValikud.getChildren().add(vaataKaarti);
            PauseTransition pause = new PauseTransition(Duration.seconds(6));
            pause.setOnFinished(e -> vaataKaarti.setText(null));
            pause.play();

        });

        varustus.setOnAction(actionEvent ->  { // Näitab mängija varustust ekraanil, erinevad variandid olenevalt, mis mängijal olemas on
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

        välju.setOnAction(actionEvent ->  { // Salvestab mängu ja väljub
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

    /**
     * Nii öelda tiitelleht
     * @param pealava
     * @return tagastab stseeni
     */
    public static Scene algusStseen(Stage pealava) {
        Image taust = pilt("images/Taust.jpg");
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(laiusResolutsioon);
        taustapilt.setFitHeight(kõrgusResolutsioon);

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);

        Button valik1 = new Button("Alusta uut mängu"); // alusta uut mängu
        Button valik2 = new Button("Jätka salvestatud mängu"); // jätka salvestatud mängu
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


        valik1.setOnAction(actionEvent ->  { // UUs mäng
            Mängija m1 = looMängija();
            Varustus varustuseList = looVarustuseList();
            List<Koobas> koobas = looKoobas();
            int ruuminumber = 0;
            Scene liikumisStseen = liikumisStseen(pealava, koobas, ruuminumber, m1, varustuseList);
            pealava.setScene(liikumisStseen);
        });

        valik2.setOnAction(actionEvent ->  { // vana mängu jätkamine
            Mängija m1;
            Varustus varustuseList;
            List<Koobas> koobas;
            int ruuminumber = 0;

            try { m1 = SalvestuseLugeja.loeMängija(); // Loeb vana mängu seisu sisse
            varustuseList = SalvestuseLugeja.loeVarustusList();
            koobas = SalvestuseLugeja.loeKoobas();
            ruuminumber = SalvestuseLugeja.loeRuuminumber();}
            catch (Exception e) { // Kui ei õnnestu alustab uut mängu
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

    /**
     * Mängulõpu stseen
     * @param pealava
     * @param võit
     * @return
     */
    public static Scene lõpuStseen(Stage pealava,boolean võit) {
        Image taust = pilt("images/Taust.jpg");
        ImageView taustapilt = new ImageView();
        taustapilt.setImage(taust);
        taustapilt.setFitWidth(laiusResolutsioon);
        taustapilt.setFitHeight(kõrgusResolutsioon);

        BorderPane bp = new BorderPane();
        bp.getChildren().add(taustapilt);

        Text õnnitlused = null;
        if (võit) õnnitlused = new Text("Olete mängu võitnud!"); // Olenevalt kas võitsid või mitte näitab teksti
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
