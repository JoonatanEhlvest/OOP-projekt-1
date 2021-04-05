import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Koobas extends Varustus{
    private List<Koobas> ruumid = new ArrayList<>();
    private Random suvalineArv = new Random();

    public Koobas suvalineRuum(){
        return ruumid.get(suvalineArv.nextInt(ruumid.size()));
    }

    public void lisaRuum(Koobas ruum) {
        ruumid.add(ruum);
    }

    public Random getSuvalineArv() {
        return suvalineArv;
    }

    @Override
    public String toString() {
        return "Koobas{ruumid=" + ruumid + '}';
    }
}
