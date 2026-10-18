import java.util.Scanner;

public class WartegCanggih {
    public static void main(String[] args) {
        Menu[] daftarMenu = {
            new Menu("Nasi putih", 5000),
            new Menu("Tempe orek", 4000),
            new Menu("Es teh", 3000),
            new Menu("Telur balado", 6000),
            new Menu("Sayur asem", 4000)
        };

        Scanner input = new Scanner(System.in);
        Kasir kasir = new Kasir("Warteg Canggih", daftarMenu, input);
        kasir.buka();
        input.close();
    }
}
