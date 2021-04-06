import java.util.List;
import java.util.Random;

public class Aarderuum extends Koobas{
    private Varustus asi;
    private String nimi;


    public Aarderuum(String nimi) {
        this.nimi = nimi;
    }

    // Varustus siin meetod
    public void lisaVarustus(Varustus asi) {
        this.asi = asi;
        varustusVõetud(asi);
    }

    public Vastane getVastane() {
        return null;
    }

    public Varustus getAsi() {
        return asi;
    }

    @Override
    public String toString() {
        return "???";
    }
}
