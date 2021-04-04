public class Aarderuum extends Koobas{
    private boolean tegelaneRuumis = false;
    private Varustus asi;

    public void onRuumis(){
        tegelaneRuumis = true;
    }

    // Varustus siin meetod
    public void setAsi(Varustus asi) {
        this.asi = asi;
    }

}
