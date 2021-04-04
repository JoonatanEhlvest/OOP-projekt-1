public class Lõks {
    private int kahju;
    private double aktiveerumisLävend;
    private boolean ignoreeribKaitset;

    public Lõks(int kahju, double aktiveerumisLävend, boolean ignoreeribKaitset) {
        this.kahju = kahju;
        this.aktiveerumisLävend = aktiveerumisLävend;
        this.ignoreeribKaitset = ignoreeribKaitset;
    }

    public void aktiveerumine(Olend ohver) {
        int õnn = Juhuslik.randint(1, 100);
        if (õnn <= aktiveerumisLävend) {
            System.out.println("Lõks aktiveerus");
            ohver.võtabKahju(kahju, ignoreeribKaitset);
        }
        else System.out.println("Vältisid lõksu");
    }
}
