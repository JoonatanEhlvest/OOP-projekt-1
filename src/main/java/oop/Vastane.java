package oop;

public class Vastane extends Olend {
    private int kaitse;

    public Vastane(String nimi, int maxElud, int tugevus, int kaitse) {
        super(maxElud, nimi, tugevus, 0);
        this.kaitse = kaitse;
    }

    @Override
    public void võtabKahju(int rünnakuTugevus, boolean ignoreeribKaitset) {
        int kahju = rünnakuTugevus;
        if (!ignoreeribKaitset) {
            kahju = rünnakuTugevus - kaitse;
        }
        System.out.println("Tegid vastasele " + this.getNimi() + " " + Math.max(0, kahju) + " elu haiget");
        super.võtabKahju(kahju, ignoreeribKaitset);
    }

    @Override
    public void sureb() {
        System.out.println(this.getNimi() + " sai surma");
        super.sureb();
    }

    /**
     * Vastane sureb ja annab mängijale suvalise Varustuse listist võimalikudAsjad
     * @param võimalikudAsjad (Varustus)
     * @param mängija (Mängija)
     */
    public Varustus sureb(Varustus võimalikudAsjad, Mängija mängija) {
        super.sureb();
        int suvaline = Juhuslik.randint(0, võimalikudAsjad.getSize() - 1);
        Varustus asi = võimalikudAsjad.getKõikAsjad().get(suvaline);
        asi.varustusVõetud(asi);
        /*
        System.out.println("Vastasel oli " + asi);
        if (asi instanceof Kaitserüü) {
            mängija.vahetaRüüd((Kaitserüü) asi);
        }
        if (asi instanceof Relv) {
            mängija.vahetaRelva((Relv) asi);
        }
         */
        return asi;
    }

    public int getKaitse() {
        return kaitse;
    }

    public void setKaitse(int kaitse) {
        this.kaitse = kaitse;
    }
}
