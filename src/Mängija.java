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
            rünnakuTugevus = randint(0, tugevus);
        }
        else {
            rünnakuTugevus = randint(0, tugevus + relv.getRünnak());
            System.out.println("Ründad vastast " + vastane.getNimi() + " relvaga " + relv.getNimi());
        }
        vastane.võtabKahju(rünnakuTugevus, false);
    }

    public void ründaMaagiaga (Vastane vastane, int rünnakuTugevus) {
        System.out.println("Ründad vastast " + vastane.getNimi() + " maagiaga");
        int manaJääk = mana - rünnakuTugevus;
        if (manaJääk < 0) {
            rünnakuTugevus += manaJääk;
        }
        mana -= rünnakuTugevus;
        vastane.võtabKahju(rünnakuTugevus, true);
    }


    public void võtabKahju(int rünnakuTugevus, boolean ignoreeribKaitset) {
        int kahju = kaitseVarustuselt() - rünnakuTugevus;
        elud -= kahju;
        System.out.println("Võtsid kahju " + kahju + " elu");
        if (elud <= 0) {
            this.sureb();
        }
    }

    public int kaitseVarustuselt() {
        int kaitseSumma = 0;
        for (Varustus ese : varustus) {
            if (ese instanceof Kaitserüü) {
                kaitseSumma += ((Kaitserüü) ese).getKaitse();
            }
        }
        return kaitseSumma;
    }



    public void sureb() {
        System.out.println("Said surma");
    }

    public void uuriVastast (Vastane vastane) {
        vastane.toString();
    }
    

    // Suvaline int vahemikus [min, max]
    private int randint(int min, int max) {
        return (int) ((Math.random() * (max - min + 1) + min));
    }
}
