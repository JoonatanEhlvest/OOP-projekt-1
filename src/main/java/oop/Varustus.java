package oop;

import java.util.ArrayList;
import java.util.List;

public class Varustus {
    private List<Varustus> kõikAsjad = new ArrayList<>();

    public void lisaAsi(Varustus varustus){
        kõikAsjad.add(varustus);
    }

    public List<Varustus> getKõikAsjad() {
        return kõikAsjad;
    }

    public void varustusVõetud(Varustus asi) {
        kõikAsjad.remove(asi);
    }

    public int getSize() {
        return kõikAsjad.size();
    }
}
