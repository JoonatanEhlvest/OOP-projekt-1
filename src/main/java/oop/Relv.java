package oop;

public class Relv extends Varustus{
    int rünnak;
    String nimi;

    public Relv(int rünnak, String nimi) {
        this.rünnak = rünnak;
        this.nimi = nimi;
    }

    public int getRünnak() {
        return rünnak;
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return "[Relv: " + nimi + " ründetugevus " + rünnak + "]";
    }
}
