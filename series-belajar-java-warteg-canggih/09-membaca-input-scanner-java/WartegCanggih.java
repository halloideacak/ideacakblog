import java.util.Scanner;

public class WartegCanggih {
    public static void main(String[] args) {
        final String NAMA_WARTEG = "Warteg Canggih";

        String[] namaMenu = {"Nasi putih", "Tempe orek", "Es teh", "Telur balado", "Sayur asem"};
        int[] hargaMenu = {5000, 4000, 3000, 6000, 4000};

        Scanner input = new Scanner(System.in);

        System.out.println("Selamat datang di " + NAMA_WARTEG + "!");
        System.out.print("Atas nama siapa? ");
        String namaPembeli = input.nextLine().trim();

        tampilkanMenu(namaMenu, hargaMenu);

        int[] porsi = new int[namaMenu.length];
        int pilihan;
        do {
            System.out.print("Nomor menu (0 = selesai): ");
            pilihan = bacaAngka(input);
            if (pilihan == 0) {
                continue;                 // langsung ke pengecekan while, lalu keluar
            }
            int index = pilihan - 1;
            if (index < 0 || index >= namaMenu.length) {
                System.out.println("Menu nomor " + pilihan + " tidak ada.");
                continue;
            }
            System.out.print("Berapa porsi? ");
            int jumlah = bacaAngka(input);
            if (jumlah <= 0) {
                System.out.println("Jumlah porsi minimal 1.");
                continue;
            }
            porsi[index] += jumlah;
            System.out.println("Dicatat: " + namaMenu[index] + " x" + jumlah);
        } while (pilihan != 0);

        input.nextLine();                 // buang sisa Enter setelah angka terakhir
        System.out.print("Catatan untuk dapur (boleh kosong): ");
        String catatan = input.nextLine().trim();

        int subtotal = hitungSubtotal(hargaMenu, porsi);
        if (subtotal == 0) {
            System.out.println("Tidak ada pesanan. Sampai jumpa, " + namaPembeli + "!");
            input.close();
            return;
        }
        int diskon = hitungDiskon(subtotal);
        int total = subtotal - diskon;

        System.out.println();
        System.out.println("Pesanan atas nama " + namaPembeli);
        cetakRincian(namaMenu, hargaMenu, porsi);
        cetakGaris(28);
        cetakBaris("Subtotal", subtotal);
        cetakBaris("Diskon", diskon);
        cetakBaris("TOTAL", total);
        if (!catatan.isEmpty()) {
            System.out.println("Catatan: " + catatan);
        }
        System.out.println();

        System.out.print("Uang pembeli: ");
        int uangPembeli = bacaAngka(input);
        while (uangPembeli < total) {
            System.out.print("Uang kurang Rp" + (total - uangPembeli) + ". Masukkan ulang: ");
            uangPembeli = bacaAngka(input);
        }
        cetakKembalian(uangPembeli - total);

        input.close();
    }

    static int bacaAngka(Scanner input) {
        while (!input.hasNextInt()) {
            String salah = input.next();  // buang input yang bukan angka
            System.out.print("\"" + salah + "\" bukan angka, coba lagi: ");
        }
        return input.nextInt();
    }

    static void tampilkanMenu(String[] namaMenu, int[] hargaMenu) {
        System.out.println("Daftar menu:");
        for (int i = 0; i < namaMenu.length; i++) {
            System.out.println((i + 1) + ". " + namaMenu[i] + " (Rp" + hargaMenu[i] + ")");
        }
    }

    static void cetakRincian(String[] namaMenu, int[] hargaMenu, int[] porsi) {
        for (int i = 0; i < namaMenu.length; i++) {
            if (porsi[i] > 0) {
                int hargaBaris = hargaMenu[i] * porsi[i];
                System.out.println(String.format("%-14s%3s%11s", namaMenu[i], "x" + porsi[i], "Rp" + hargaBaris));
            }
        }
    }

    static int hitungSubtotal(int[] hargaMenu, int[] porsi) {
        int subtotal = 0;
        for (int i = 0; i < hargaMenu.length; i++) {
            subtotal += hargaMenu[i] * porsi[i];
        }
        return subtotal;
    }

    static int hitungDiskon(int subtotal) {
        if (subtotal >= 20000) {
            return 2000;
        }
        if (subtotal >= 10000) {
            return 1000;
        }
        return 0;
    }

    static void cetakGaris(int panjang) {
        System.out.println("-".repeat(panjang));
    }

    static void cetakBaris(String label, int nominal) {
        System.out.println(String.format("%-17s%11s", label, "Rp" + nominal));
    }

    static void cetakKembalian(int sisa) {
        System.out.println("Kembalian: Rp" + sisa);
        int[] pecahan = {10000, 5000, 2000, 1000};
        for (int lembar : pecahan) {
            while (sisa >= lembar) {
                System.out.println("  Rp" + lembar);
                sisa -= lembar;
            }
        }
    }
}
