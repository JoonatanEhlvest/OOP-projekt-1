import java.util.Locale;
import java.util.Scanner;

public class Mängija extends Olend{
    private int maxMana;
    private int mana;
    private int manaTaastumine;
    private Kaitserüü kaitserüü;
    private Relv relv;

    public Mängija(String nimi, int elud, int maxMana, int tugevus, int eludeTaastumine, int manaTaastumine) {
        super(elud, nimi, tugevus, eludeTaastumine);
        this.maxMana = maxMana;
        this.mana = maxMana;
        this.manaTaastumine = manaTaastumine;
    }

    public void ründa(Olend vastane, Relv relv) {
        int rünnakuTugevus = Juhuslik.randint(1, this.getTugevus() + relv.getRünnak());
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
        System.out.println("Taastasid oma elusid " + getEludeTaastumine() + " võrra" +
                            " ja mana " + manaTaastumine + " võrra");
        taastaElusid(getEludeTaastumine());
        taastaMana(manaTaastumine);
    }

    @Override
    public void võtabKahju(int rünnakuTugevus, boolean ignoreeribKaitset) {
        int kahju = rünnakuTugevus;
        if (!ignoreeribKaitset) {
            kahju = rünnakuTugevus - kaitseVarustuselt();
        }
        if (kahju <= 0) System.out.println("Võtsid kahju " + 0 + " elu");
        else System.out.println("Võtsid kahju " + kahju + " elu");
        super.võtabKahju(kahju, ignoreeribKaitset);
    }

    public int kaitseVarustuselt() {
        return kaitserüü.getKaitse();
    }

    @Override
    public void sureb() {
        System.out.println("Said surma");
        super.sureb();
    }

    public void vahetaRüüd(Kaitserüü uusRüü) {
        System.out.println("Sul on seljas " + kaitserüü.toString());
        System.out.println("Kas tahad selle vahetada " + uusRüü.toString() + " vastu");

        while (true) {
            System.out.println("Kirjuta 'jah', kui tahad uut rüüd ja 'ei', kui ei taha vahetust teha");
            Scanner sc = new Scanner((System.in));
            String vastus = sc.nextLine();
            if (vastus.toLowerCase(Locale.ROOT).equals("jah")) {
                setKaitserüü(uusRüü);
                break;
            }
            if (vastus.toLowerCase(Locale.ROOT).equals("ei")) {
                break;
            }
            else System.out.println("Sisestus oli vigane");
        }
    }

    public void vahetaRelva(Relv uusRelv) {
        if (relv == null) {
            System.out.println("Said relva:" + uusRelv);
            setRelv(uusRelv);
        }
        else {
            System.out.println("Sul on käes " + relv.toString());
            System.out.println("Kas tahad selle vahetada " + uusRelv.toString() + " vastu");

            while (true) {
                System.out.println("Kirjuta 'jah', kui tahad uut relva ja 'ei', kui ei taha vahetust teha");
                Scanner sc = new Scanner((System.in));
                String vastus = sc.nextLine();
                if (vastus.toLowerCase(Locale.ROOT).equals("jah")) {
                    setRelv(uusRelv);
                    break;
                }
                if (vastus.toLowerCase(Locale.ROOT).equals("ei")) {
                    break;
                } else System.out.println("Sisestus oli vigane");
            }
        }
    }

    public void setKaitserüü(Kaitserüü kaitserüü) {
        this.kaitserüü = kaitserüü;
    }

    public void setRelv(Relv relv) {
        this.relv = relv;
    }

    public Kaitserüü getKaitserüü() {
        return kaitserüü;
    }

    public Relv getRelv() {
        return relv;
    }

    public void uuriVastast (Vastane vastane) {
        vastane.toString();
    }

    public int getMana() {
        return mana;
    }

    public int getManaTaastumine() {
        return manaTaastumine;
    }
}
