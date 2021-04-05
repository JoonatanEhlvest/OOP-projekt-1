public class Lõksuruum extends Koobas{
    private boolean tegelaneRuumis = false;
    private Lõks lõks;

    public void onRuumis(){
        tegelaneRuumis = true;
    }

    public Lõksuruum(Lõks lõks) {
        this.lõks = lõks;
    }

    public boolean isTegelaneRuumis(Koobas ruum) {
        return tegelaneRuumis;
    }
}
