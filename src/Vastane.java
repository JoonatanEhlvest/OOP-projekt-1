public class Vastane extends Olend{
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
}
