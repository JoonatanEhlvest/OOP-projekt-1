import java.util.List;
import java.util.Random;

public class Aarderuum extends Koobas{
    private boolean tegelaneRuumis = false;
    private Varustus asi;
    private String nimi;

    public Aarderuum(String nimi) {
        this.nimi = nimi;
    }

    public boolean isTegelaneRuumis() {
        return tegelaneRuumis;
    }
    public void onRuumis(){
        tegelaneRuumis = true;
    }

    // Varustus siin meetod
    public void lisaVarustus() {
        asi = getKõikAsjad().get(getSuvalineArv().nextInt(getKõikAsjad().size()));
        varustusVõetud(asi);
    }

    @Override
    public String toString() {
        return "Aarderuum{" +
                "nimi='" + nimi + '\'' +
                '}';
    }
}
