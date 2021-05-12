package oop;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Salvestaja {
    public static void salvestaMängija(Mängija mängija) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("mängijaSalvestus.obj"))) {
            out.writeObject(mängija);
        }
    }

    public static void salvestaKoobas(List<Koobas> koobas) throws IOException{
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("koopaSalvestus.obj"))) {
            out.writeObject(koobas);
        }
    }

    public static void salvestaVarustus(Varustus varustusList) throws IOException{
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("varustuseSalvestus.obj"))) {
            out.writeObject(varustusList);
        }
    }
}
