public class Vastane {
    private int elud;
    private int nimi;
    private int kaitse;

    public void võtabKahju(int rünnakuTugevus) {
        int kahju = kaitse - rünnakuTugevus;
        elud -= kahju;
        System.out.println("Võtsid vastaselt " + nimi + " " + kahju + " elu");
        if (elud <= 0) {
            this.sureb();
        }
    }

    public void sureb() {
    }

    public int getNimi() {
        return nimi;
    }
}
