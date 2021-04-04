public class Vastane {
    private int elud;
    private int nimi;
    private int kaitse;

    public void võtabKahju(int rünnakuTugevus, boolean ignoreeribKaitset) {
        int kahju = kaitse - rünnakuTugevus;
        elud -= kahju;
        System.out.println("Tegid vastasele " + nimi + " " + kahju + " elu");
        if (elud <= 0) {
            this.sureb();
        }
    }

    public void sureb() {
    }

    public int getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return "Vastane{" +
                "elud=" + elud +
                ", nimi=" + nimi +
                ", kaitse=" + kaitse +
                '}';
    }
}
