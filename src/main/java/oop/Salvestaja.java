package oop;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Salvestaja {
    public static void salvestaMängija(Mängija mängija) throws Exception{
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("mängijaSalvestus.obj"))) {
            out.writeObject(mängija);
        }
    }

    public static void salvestaKoobas(List<Koobas> koobas) throws Exception{
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("koopaSalvestus.obj"))) {
            out.writeObject(koobas);
        }
    }

    public static void salvestaVarustus(Varustus varustusList) throws Exception{
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("varustuseSalvestus.obj"))) {
            out.writeObject(varustusList);
        }
    }
}
