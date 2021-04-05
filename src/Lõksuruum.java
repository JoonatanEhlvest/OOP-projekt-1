public class Lõksuruum extends Koobas{
    private boolean tegelaneRuumis = false;
    private Lõks lõks;

    public void onRuumis(){
        tegelaneRuumis = true;
    }

    @Override
    public boolean isTegelaneRuumis() {
        return false;
    }

    public Lõksuruum(Lõks lõks) {
        this.lõks = lõks;
    }

}
