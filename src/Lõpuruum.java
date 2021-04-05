public class Lõpuruum extends Koobas{
    private boolean tegelaneRuumis = false;
    private Vastane vastane;
    private String nimi;

    public void onRuumis(){
        tegelaneRuumis = true;
    }

    public Lõpuruum(Vastane vastane, String nimi) {
        this.vastane = vastane;
        this.nimi = nimi;
    }

    public boolean isTegelaneRuumis() {
        return tegelaneRuumis;
    }

    public Vastane getVastane() {
        return vastane;
    }

    @Override
    public String toString() {
        return "Lõpuruum{" +
                "nimi='" + nimi + '\'' +
                '}';
    }
}
