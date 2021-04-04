import java.util.ArrayList;
import java.util.List;

public class Mängija {
    private String nimi;
    private int elud;
    private int mana;
    private int tugevus;
    private List<Varustus> varustus = new ArrayList<>();

    public void ründa(Vastane vastane, Relv relv) {
        // Kui mängijal seda relva pole, ei saa relvaga rünnata.
        int rünnakuTugevus;
        if (!varustus.contains(relv)) {
            System.out.println("Selle nimega relva sul ei ole");
            System.out.println("Ründad käsitsi");
            rünnakuTugevus = randint(0, tugevus + 1);
        }
        else {
            System.out.println("Ründad vastast " + vastane.getNimi() + " relvaga " + relv.getNimi());
            rünnakuTugevus = randint(0, tugevus + relv.getRünnak() + 1);
        }
        vastane.võtabKahju(rünnakuTugevus);
    }

    

    // Suvaline int vahemikus [min, max)
    private int randint(int min, int max) {
        return (int) ((Math.random() * (max - min) + min));
    }
}
