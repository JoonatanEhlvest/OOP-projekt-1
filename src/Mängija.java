import java.util.ArrayList;
import java.util.List;

public class Mängija extends Olend{
    private int mana;
    private List<Varustus> varustus = new ArrayList<>();

    public Mängija(String nimi, int elud, int mana, int tugevus) {
        super(elud, nimi, tugevus);
        this.mana = mana;
    }

    public void ründa(Olend vastane, Relv relv) {
        int rünnakuTugevus = randint(0, this.getTugevus() + relv.getRünnak());
        System.out.println("Ründad vastast " + vastane.getNimi() + " relvaga " + relv.getNimi());
        vastane.võtabKahju(rünnakuTugevus, false);
    }

    @Override
    public void ründa(Olend vastane) {
        System.out.println("Ründad vastast " + vastane.getNimi() + " käsitsi");
        super.ründa(vastane);
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

    @Override
    public void võtabKahju(int rünnakuTugevus, boolean ignoreeribKaitset) {
        int kahju = rünnakuTugevus;
        if (!ignoreeribKaitset) {
            kahju = rünnakuTugevus - kaitseVarustuselt();
        }
        System.out.println("Võtsid kahju " + kahju + " elu");
        super.võtabKahju(kahju, ignoreeribKaitset);
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

    @Override
    public void sureb() {
        System.out.println("Said surma");
        super.sureb();
    }

    public void uuriVastast (Vastane vastane) {
        vastane.toString();
    }

}
