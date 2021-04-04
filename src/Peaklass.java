public class Peaklass {
    public static void main(String[] args) {
        Mängija m1 = new Mängija("Player 1", 20, 20, 5);
        Vastane v1 = new Rott(6, 4, 2);

        automaatneVõitlus(m1, v1);

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
}
