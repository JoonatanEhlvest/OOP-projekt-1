package oop;

public class Juhuslik {
    // Suvaline int vahemikus [min, max]
    static int randint(int min, int max) {
        return (int) ((Math.random() * (max - min + 1) + min));
    }
}
