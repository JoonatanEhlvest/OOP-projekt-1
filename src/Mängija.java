import java.util.ArrayList;
import java.util.List;

public class Mängija extends Olend{
    private int maxMana;
    private int mana;
    private int manaTaastumine;
    private List<Varustus> varustus = new ArrayList<>();

    public Mängija(String nimi, int elud, int maxMana, int tugevus, int eludeTaastumine, int manaTaastumine) {
        super(elud, nimi, tugevus, eludeTaastumine);
        this.maxMana = maxMana;
        this.mana = maxMana;
        this.manaTaastumine = manaTaastumine;
    }

    public void ründa(Olend vastane, Relv relv) {
        int rünnakuTugevus = Juhuslik.randint(0, this.getTugevus() + relv.getRünnak());
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

    public void taastaMana(int mituTaastada) {
        // taastab mana mituTaastada väärtuse võrra, kuni maxMana väärtuseni
        while (maxMana > mana && mituTaastada > 0) {
            mana++;
            mituTaastada--;
        }
    }

    public void taastaElusid(int mituTaastada) {
        // taastab elusid mituTaastada väärtuse võrra, kuni maxElud väärtuseni
        while (getMaxElud() > getElud() && mituTaastada > 0) {
            setElud(getElud() + 1);
            mituTaastada--;
        }
    }

    public void puhka() {
        System.out.println("Taastasid oma elusid " + getEludeTaastumine() + " võrra");
        taastaElusid(getEludeTaastumine());
        taastaMana(manaTaastumine);
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
