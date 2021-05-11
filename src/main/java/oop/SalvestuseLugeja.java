package oop;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class SalvestuseLugeja {
    public static Mängija loeMängija() throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("mängijaSalvestus.obj"))) {
            return (Mängija) in.readObject();
        }
    }
    public static List<Koobas> loeKoobas() throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("koopaSalvestus.obj"))) {
            return (List<Koobas>) in.readObject();
        }
    }
    public static Varustus loeVarustusList() throws Exception {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("varustuseSalvestus.obj"))) {
            return (Varustus) in.readObject();
        }
    }
}
