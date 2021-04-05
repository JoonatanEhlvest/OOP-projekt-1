public abstract class Olend {
    private int maxElud;
    private int elud;
    private int eludeTaastumine; // Elude taastumiskiirus
    private String nimi;
    private int tugevus;
    private boolean elus = true; // True, kui olend on elus

    public Olend(int maxElud, String nimi, int tugevus, int eludeTaastumine) {
        this.maxElud = maxElud;
        this.elud = maxElud;
        this.nimi = nimi;
        this.tugevus = tugevus;
        this.eludeTaastumine = eludeTaastumine;
    }

    public void sureb() {
        this.elus = false;
    }

    public void võtabKahju(int rünnakuTugevus, boolean ignoreeribKaitset) {
        rünnakuTugevus = Math.max(rünnakuTugevus, 0);
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

    public int getMaxElud() {
        return maxElud;
    }

    public int getEludeTaastumine() {
        return eludeTaastumine;
    }
}
