import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Koobas {
    private List<Koobas> ruumid = new ArrayList<>();
    private Random suvalineArv = new Random();

    public Koobas suvalineRuum(){
        return ruumid.get(suvalineArv.nextInt(ruumid.size()));
    }

    public void lisaRuum(Koobas ruum) {
        ruumid.add(ruum);
    }

}
