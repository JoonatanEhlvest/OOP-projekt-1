public class Lõpuruum extends Koobas{
    private Vastane vastane;
    private String nimi;

    public Lõpuruum(Vastane vastane, String nimi) {
        this.vastane = vastane;
        this.nimi = nimi;
    }

    public Vastane getVastane() {
        return vastane;
    }

    @Override
    public String toString() {
        return "Boss";
    }
}
