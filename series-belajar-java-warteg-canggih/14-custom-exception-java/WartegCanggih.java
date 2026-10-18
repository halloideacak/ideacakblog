import java.util.Scanner;

public class WartegCanggih {
    public static void main(String[] args) {
        Menu[] daftarMenu = {
            new Menu("Nasi putih", 5000, 50),
            new Menu("Tempe orek", 4000, 20),
            new Menu("Es teh", 3000, 40),
            new Menu("Telur balado", 6000, 3),
            new Menu("Sayur asem", 4000, 0)
        };

        // try-with-resources: Scanner otomatis ditutup saat blok ini selesai
        try (Scanner input = new Scanner(System.in)) {
            Kasir kasir = new Kasir("Warteg Canggih", daftarMenu, input);
            kasir.buka();
        }
    }
}
