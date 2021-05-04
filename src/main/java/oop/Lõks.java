package oop;

public class Lõks {
    private int kahju;
    private double aktiveerumisTõenäosus;
    private boolean ignoreeribKaitset;

    public Lõks(int kahju, double aktiveerumisTõenäosus, boolean ignoreeribKaitset) {
        this.kahju = kahju;
        this.aktiveerumisTõenäosus = aktiveerumisTõenäosus;
        this.ignoreeribKaitset = ignoreeribKaitset;
    }

    /**
     * Teeb tõenäosusega aktiveerimisTõenäosus ohvrile kahju.
     * @param ohver (Olend)
     */
    public void aktiveerumine(Olend ohver) {
        int õnn = Juhuslik.randint(1, 100);
        if (õnn <= aktiveerumisTõenäosus) {
            System.out.println("Lõks aktiveerus");
            ohver.võtabKahju(kahju, ignoreeribKaitset);
        }
        else System.out.println("Vältisid lõksu");
    }
}
