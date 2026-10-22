import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WartegCanggih {
    public static void main(String[] args) {
        // List.of tidak bisa diubah, jadi isinya disalin ke ArrayList supaya menu bisa ditambah saat kasir buka
        List<Menu> daftarMenu = new ArrayList<>(List.of(
            new Menu("Nasi putih", 5000),
            new Menu("Tempe orek", 4000),
            new Menu("Es teh", 3000),
            new Menu("Telur balado", 6000),
            new Menu("Sayur asem", 4000),
            new MenuPaket("Paket Hemat", 10000, new String[] {"Nasi putih", "Tempe orek", "Es teh"})
        ));

        // ganti jadi new DiskonJamSepi() saat jam sepi, Kasir tidak perlu diubah sama sekali
        AturanDiskon aturanDiskon = new DiskonBertingkat();

        Scanner input = new Scanner(System.in);
        Kasir kasir = new Kasir("Warteg Canggih", daftarMenu, aturanDiskon, input);
        kasir.buka();
        input.close();
    }
}
