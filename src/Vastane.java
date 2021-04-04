public class Vastane extends Olend{
    private int kaitse;
    private boolean elus = true; // True, kui vastane on elus

    public Vastane(String nimi, int elud, int tugevus, int kaitse) {
        super(elud, nimi, tugevus);
        this.kaitse = kaitse;
    }

    @Override
    public void võtabKahju(int rünnakuTugevus, boolean ignoreeribKaitset) {
        int kahju = rünnakuTugevus;
        if (!ignoreeribKaitset) {
            kahju = rünnakuTugevus - kaitse;
        }
        System.out.println("Tegid vastasele " + this.getNimi() + " " + kahju + " elu haiget");
        super.võtabKahju(kahju, ignoreeribKaitset);
    }

    @Override
    public void sureb() {
        System.out.println(this.getNimi() + " sai surma");
        super.sureb();
    }
}
