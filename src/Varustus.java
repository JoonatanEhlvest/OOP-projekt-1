import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Varustus {
    private List<Varustus> kõikAsjad = new ArrayList<>();
    private Random suvalineArv = new Random();

    public void lisaAsi(Varustus varustus){
        kõikAsjad.add(varustus);
    }

    public List<Varustus> getKõikAsjad() {
        return kõikAsjad;
    }

    public void varustusVõetud(Varustus asi) {
        kõikAsjad.remove(asi);
    }

    public void varustusVastaselt(){
        Varustus asi = kõikAsjad.get(suvalineArv.nextInt(kõikAsjad.size()));

    }

    public int getSize() {
        return kõikAsjad.size();
    }
}
