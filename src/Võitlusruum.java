public class Võitlusruum extends Koobas{
    private boolean tegelaneRuumis = false;
    private Vastane vastane;

    public void onRuumis(){
        tegelaneRuumis = true;
    }

    public Võitlusruum(Vastane vastane) {
        this.vastane = vastane;
    }
}
