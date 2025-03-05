import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MasinuSistema {
    static List<Masina> masinas = new ArrayList<>();
    static final String CSV_FILE = "masinas.csv";

    public static void main(String[] args) {
        ieladetDatus();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\u001B[1;34m\n1. Pievienot mašīnu\u001B[0m");
            System.out.println("\u001B[1;34m2. Parādīt visas mašīnas\u001B[0m");
            System.out.println("\u001B[1;34m3. Kārtot pēc cenas un gada\u001B[0m");
            System.out.println("\u001B[1;34m4. Filtrēt pēc cenas diapazona\u001B[0m");
            System.out.println("\u001B[1;34m5. Saglabāt un iziet\u001B[0m");
            System.out.print("\u001B[1;33mIzvēlies darbību: \u001B[0m");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> pievienotMasinu(scanner);
                case 2 -> paraditMasinas();
                case 3 -> kartotMasinas();
                case 4 -> filtrētPēcCenas(scanner);
                case 5 -> { saglabatDatus(); return; }
                default -> System.out.println("\u001B[31mNepareiza izvēle!\u001B[0m");
            }
        }
    }

    static void pievienotMasinu(Scanner scanner) {
        System.out.print("Marka: ");
        String marka = scanner.next();
        System.out.print("Modelis: ");
        String modelis = scanner.next();
        System.out.print("Krāsa: ");
        String krasa = scanner.next();
        System.out.print("Gads: ");
        int gads = scanner.nextInt();
        System.out.print("Cena: ");
        double cena = scanner.nextDouble();
        
        masinas.add(new Masina(marka, modelis, krasa, gads, cena));
        System.out.println("\u001B[32mMašīna pievienota!\u001B[0m");
    }

    static void paraditMasinas() {
        System.out.println("\n\u001B[1;36m%-10s %-10s %-10s %-6s %-8s\u001B[0m".formatted("Marka", "Modelis", "Krāsa", "Gads", "Cena"));
        System.out.println("\u001B[1;35m----------------------------------------------------------------\u001B[0m");
        for (Masina m : masinas) {
            System.out.println(m);
        }
    }

    static void kartotMasinas() {
        masinas.sort(Comparator.comparingDouble((Masina m) -> m.cena).thenComparingInt(m -> m.gads));
        System.out.println("\u001B[32mMašīnas sakārtotas pēc cenas un gada!\u001B[0m");
        paraditMasinas();
    }

    static void filtrētPēcCenas(Scanner scanner) {
        System.out.print("Ievadi minimālo cenu: ");
        double minCena = scanner.nextDouble();
        System.out.print("Ievadi maksimālo cenu: ");
        double maxCena = scanner.nextDouble();

        System.out.println("\n\u001B[1;36m%-10s %-10s %-10s %-6s %-8s\u001B[0m".formatted("Marka", "Modelis", "Krāsa", "Gads", "Cena"));
        System.out.println("\u001B[1;35m----------------------------------------------------------------\u001B[0m");
        for (Masina m : masinas) {
            if (m.cena >= minCena && m.cena <= maxCena) {
                System.out.println(m);
            }
        }
    }

    static void ieladetDatus() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                masinas.add(new Masina(data[0], data[1], data[2], Integer.parseInt(data[3]), Double.parseDouble(data[4])));
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mNevarēja ielādēt failu, sākam ar tukšu sarakstu.\u001B[0m");
        }
    }

    static void saglabatDatus() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CSV_FILE))) {
            for (Masina m : masinas) {
                bw.write(m.marka + "," + m.modelis + "," + m.krasa + "," + m.gads + "," + m.cena + "\n");
            }
            System.out.println("\u001B[32mDati saglabāti!\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31mKļūda saglabājot failu.\u001B[0m");
        }
    }
}

