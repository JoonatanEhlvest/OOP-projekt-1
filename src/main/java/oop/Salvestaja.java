/**
 * Sisaldab meetodeid, mis salvestavad failidesse mängu käigus muutuvad Objektid ja väärtused
 */
package oop;

import java.io.*;
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

    public static void salvestaRuuminumber(int ruuminumber) throws IOException{
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("ruuminumber.dat"))) {
            out.writeInt(ruuminumber);
        }
    }
}
