import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

public class WartegCanggih {
    public static void main(String[] args) {
        ArsipWarteg arsip = new ArsipWarteg(Path.of("data"));

        Menu[] daftarMenu;
        try {
            daftarMenu = arsip.bacaMenu();
        } catch (NoSuchFileException e) {
            System.out.println("File " + e.getMessage() + " tidak ditemukan. Jalankan program dari folder yang berisi folder data.");
            return;
        } catch (IOException e) {
            System.out.println("Daftar menu gagal dibaca: " + e.getMessage());
            return;
        }
        if (daftarMenu.length == 0) {
            System.out.println("Daftar menu kosong, kasir tidak bisa dibuka.");
            return;
        }

        // try-with-resources: Scanner otomatis ditutup saat blok ini selesai
        try (Scanner input = new Scanner(System.in)) {
            Kasir kasir = new Kasir("Warteg Canggih", daftarMenu, input, arsip);
            kasir.buka();
        }
    }
}
