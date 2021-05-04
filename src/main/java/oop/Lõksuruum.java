package oop;

public class Lõksuruum extends Koobas{
    private Lõks lõks;

    public Lõksuruum(Lõks lõks) {
        this.lõks = lõks;
    }

    public Lõks getLõks() {
        return lõks;
    }

    public Vastane getVastane() {
        return null;
    }

    @Override
    public String toString() {
        return "Kahtlane Ruum";
    }
}
