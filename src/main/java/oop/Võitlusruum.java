package oop;

public class Võitlusruum extends Koobas{
    private Vastane vastane;
    private String nimi;

    public Võitlusruum(Vastane vastane, String nimi) {
        this.vastane = vastane;
        this.nimi = nimi;
    }

    public Vastane getVastane() {
        return vastane;
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return nimi;
    }
}
