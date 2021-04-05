public class Võitlusruum extends Koobas{
    private boolean tegelaneRuumis = false;
    private Vastane vastane;
    private String nimi;

    public Võitlusruum(Vastane vastane, String nimi) {
        this.vastane = vastane;
        this.nimi = nimi;
    }

    public void onRuumis(){
        tegelaneRuumis = true;
    }

    public boolean isTegelaneRuumis() {
        return tegelaneRuumis;
    }

    public Vastane getVastane() {
        return vastane;
    }


    @Override
    public String toString() {
        return "Võitlusruum{" +
                "nimi='" + nimi + '\'' +
                '}';
    }
}
