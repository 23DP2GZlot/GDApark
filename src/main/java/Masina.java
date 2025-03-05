import java.io.*;
import java.util.*;

class Masina {
    String marka, modelis, krasa;
    int gads;
    double cena;

    public Masina(String marka, String modelis, String krasa, int gads, double cena) {
        this.marka = marka;
        this.modelis = modelis;
        this.krasa = krasa;
        this.gads = gads;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return String.format("\u001B[34m%-10s\u001B[0m \u001B[32m%-10s\u001B[0m \u001B[36m%-10s\u001B[0m \u001B[33m%-6d\u001B[0m \u001B[35m%-8.2f\u001B[0m", marka, modelis, krasa, gads, cena);
    }
}