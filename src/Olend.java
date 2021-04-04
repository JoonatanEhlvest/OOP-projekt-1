public abstract class Olend {
    private int elud;
    private String nimi;
    private int tugevus;
    private boolean elus = true; // True, kui olend on elus

    public Olend(int elud, String nimi, int tugevus) {
        this.elud = elud;
        this.nimi = nimi;
        this.tugevus = tugevus;
    }

    public void sureb() {
        this.elus = false;
    }

    public void võtabKahju(int rünnakuTugevus, boolean ignoreeribKaitset) {
        if (rünnakuTugevus < 0) {
            rünnakuTugevus = 0;
        }
        elud -= rünnakuTugevus;
        if (elud <= 0) {
            this.sureb();
        }
    }

    public void ründa(Olend vastane) {
        int rünnakuTugevus = Juhuslik.randint(0, tugevus);
        vastane.võtabKahju(rünnakuTugevus, false);
    }

    public int getTugevus() {
        return tugevus;
    }

    public int getElud() {
        return elud;
    }

    public String getNimi() {
        return nimi;
    }

    public boolean isElus() {
        return elus;
    }

    public void setElud(int elud) {
        this.elud = elud;
    }

}
