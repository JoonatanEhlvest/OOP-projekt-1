public class lõpuruum extends Koobas{
    private boolean tegelaneRuumis = false;
    private Vastane vastane;

    public void onRuumis(){
        tegelaneRuumis = true;
    }

    public lõpuruum(Vastane vastane) {
        this.vastane = vastane;
    }

    public boolean isTegelaneRuumis(Koobas ruum) {
        return tegelaneRuumis;
    }
}
