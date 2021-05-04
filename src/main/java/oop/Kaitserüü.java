package oop;

public class Kaitserüü extends Varustus{
    private int kaitse;
    private String nimi;

    public Kaitserüü(int kaitse, String nimi) {
        this.kaitse = kaitse;
        this.nimi = nimi;
    }

    public int getKaitse() {
        return kaitse;
    }

    @Override
    public String toString() {
        return "[Kaitserüü: " + nimi + " kaitsetase " + kaitse + "]";
    }
}
