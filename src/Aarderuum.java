import java.util.List;
import java.util.Random;

public class Aarderuum extends Koobas{
    private boolean tegelaneRuumis = false;
    private Varustus asi;

    public void onRuumis(){
        tegelaneRuumis = true;
    }

    // Varustus siin meetod
    public void lisaVarustus() {
        asi = getKõikAsjad().get(getSuvalineArv().nextInt(getKõikAsjad().size()));
        varustusVõetud(asi);
    }
}
