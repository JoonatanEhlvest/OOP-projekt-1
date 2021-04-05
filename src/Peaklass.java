import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Peaklass {

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

    }

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

    public String RandElem(List<Integer> list) {
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

        Mängija m1 = new Mängija("Player 1", 20, 10, 5);

        /** VARUSTUSE LISAMINE */
        Varustus varustuseList = new Varustus();
        varustuseList.lisaAsi(new Relv(Juhuslik.randint(1, 10), "Katkine Mõõk"));
        varustuseList.lisaAsi(new Kaitserüü(Juhuslik.randint(1, 10), "Mõrane Raudrüü"));

        System.out.println(varustuseList.getKõikAsjad());

        /** VASTASED */
        List<String> nimed = Arrays.asList("Rott", "Luukere", "Vampiir");

        /** KOOPA RUUMID */
        Koobas koobas = new Koobas();
        Koobas aarderuum = new Aarderuum();
        Koobas võitlusruum = new Võitlusruum(new Vastane(RandElem(nimed), Juhuslik.randint(5, 20), Juhuslik.randint(1, 10)))

        /** MÄNG ALGAB */
        while (true) {

        }
    }
}
