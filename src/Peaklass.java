import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Peaklass {
    /*
    public static void main(String[] args) {
        Mängija m1 = new Mängija("Player 1", 20, 10, 5, 2,1);
        Vastane v1 = new Rott(6, 4, 2);
        Ogad lõks1 = new Ogad(8,40, true);


        automaatneVõitlus(m1, v1);
        System.out.println("Sul on alles " + m1.getElud() + " elu");

        m1.puhka();
        System.out.println("Sul on alles " + m1.getElud() + " elu");


        lõks1.aktiveerumine(m1);
        System.out.println("Sul on alles " + m1.getElud() + " elu");

    } */
    private static void automaatneVõitlus(Mängija mängija, Vastane vastane) {
        while (true) {
            if (mängija.isElus()) {
                mängija.ründa(vastane);
                System.out.println("Vastasel on järgi " + vastane.getElud() + " elu");
            } else break;
            if (vastane.isElus()) {
                vastane.ründa(mängija);
                System.out.println("Sul on järgi " + mängija.getElud() + " elu");
            } else break;
        }
    }

    public static String RandElem(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static void main(String[] args) {
        /*
        Mängija m1 = new Mängija("Player 1", 20, 10, 5, 2,1);
        Vastane v1 = new Rott(6, 4, 2);
        Ogad lõks1 = new Ogad(8,40, true);


        automaatneVõitlus(m1, v1);
        System.out.println("Sul on alles " + m1.getElud() + " elu");

        m1.puhka();
        System.out.println("Sul on alles " + m1.getElud() + " elu");


        lõks1.aktiveerumine(m1);
        System.out.println("Sul on alles " + m1.getElud() + " elu");
        */


        Mängija m1 = new Mängija("Player 1", 20, 10, 5,2,1);

        /** VARUSTUSE LISAMINE */
        Varustus varustuseList = new Varustus();
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Katkine Mõõk"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Mõrane Raudrüü"));

        System.out.println(varustuseList.getKõikAsjad());

        /** VASTASED */
        List<String> nimed = Arrays.asList("Rott", "Luukere", "Vampiir");

        /** KOOPA RUUMID */

        Koobas koobas = new Koobas();
        Aarderuum aarderuum = new Aarderuum("Aarderuum");
        Võitlusruum võitlusruum1 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(5,20),Juhuslik.randint(1,10),Juhuslik.randint(0,5)),"Tuba1");
        Võitlusruum võitlusruum2 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(5,20),Juhuslik.randint(1,10),Juhuslik.randint(0,5)),"Tuba2");
        Võitlusruum võitlusruum3 = new Võitlusruum(new Vastane(RandElem(nimed),Juhuslik.randint(5,20),Juhuslik.randint(1,10),Juhuslik.randint(0,5)),"Tuba3");
        Lõpuruum lõpuruum = new Lõpuruum(new Vastane("Boss",100,30,20),"ViimaneTuba");
        koobas.lisaRuum(aarderuum);
        koobas.lisaRuum(võitlusruum1);
        koobas.lisaRuum(võitlusruum2);
        koobas.lisaRuum(võitlusruum3);
        Collections.shuffle(koobas.getRuumid());
        koobas.lisaRuum(lõpuruum);
        System.out.println(koobas);

        /** MÄNG ALGAB */
    }
}
