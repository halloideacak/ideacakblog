import java.util.Scanner;

public class WartegCanggih {
    public static void main(String[] args) {
        final String NAMA_WARTEG = "Warteg Canggih";

        String[] namaMenu = {"Nasi putih", "Tempe orek", "Es teh", "Telur balado", "Sayur asem"};
        int[] hargaMenu = {5000, 4000, 3000, 6000, 4000};

        Scanner input = new Scanner(System.in);
        int[] porsiHarian = new int[namaMenu.length];
        int omzet = 0;
        int jumlahPembeli = 0;

        System.out.println("=".repeat(28));
        System.out.println("  KASIR " + NAMA_WARTEG.toUpperCase());
        System.out.println("=".repeat(28));

        int pilihan;
        do {
            System.out.println();
            System.out.println("1. Layani pembeli");
            System.out.println("2. Lihat daftar menu");
            System.out.println("0. Tutup kasir");
            System.out.print("Pilih: ");
            pilihan = bacaAngka(input);

            switch (pilihan) {
                case 1 -> {
                    int[] porsi = layaniPembeli(input, namaMenu, hargaMenu);
                    int total = hitungTotal(hargaMenu, porsi);
                    if (total > 0) {
                        omzet += total;
                        jumlahPembeli++;
                        tambahkanKeRekap(porsiHarian, porsi);
                    }
                }
                case 2 -> tampilkanMenu(namaMenu, hargaMenu);
                case 0 -> System.out.println("Kasir ditutup.");
                default -> System.out.println("Pilihan " + pilihan + " tidak ada.");
            }
        } while (pilihan != 0);

        cetakRekapHarian(namaMenu, porsiHarian, jumlahPembeli, omzet);
        input.close();
    }

    // ---------- melayani satu pembeli ----------

    static int[] layaniPembeli(Scanner input, String[] namaMenu, int[] hargaMenu) {
        input.nextLine();                         // buang sisa Enter dari "Pilih"
        System.out.print("Atas nama siapa? ");
        String namaPembeli = input.nextLine().trim();
        if (namaPembeli.isEmpty()) {
            namaPembeli = "Pembeli";
        }

        tampilkanMenu(namaMenu, hargaMenu);
        int[] porsi = ambilPesanan(input, namaMenu);

        int subtotal = hitungSubtotal(hargaMenu, porsi);
        if (subtotal == 0) {
            System.out.println("Tidak ada pesanan untuk " + namaPembeli + ".");
            return porsi;
        }
        int diskon = hitungDiskon(subtotal);
        int total = subtotal - diskon;

        cetakStruk(namaPembeli, namaMenu, hargaMenu, porsi, subtotal, diskon, total);
        terimaPembayaran(input, total);
        return porsi;
    }

    static int[] ambilPesanan(Scanner input, String[] namaMenu) {
        int[] porsi = new int[namaMenu.length];
        int pilihan;
        do {
            System.out.print("Nomor menu (0 = selesai): ");
            pilihan = bacaAngka(input);
            if (pilihan == 0) {
                continue;
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
        return porsi;
    }

    static void terimaPembayaran(Scanner input, int total) {
        System.out.print("Uang pembeli: ");
        int uangPembeli = bacaAngka(input);
        while (uangPembeli < total) {
            System.out.print("Uang kurang Rp" + (total - uangPembeli) + ". Masukkan ulang: ");
            uangPembeli = bacaAngka(input);
        }
        cetakKembalian(uangPembeli - total);
    }

    // ---------- perhitungan ----------

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

    static int hitungTotal(int[] hargaMenu, int[] porsi) {
        int subtotal = hitungSubtotal(hargaMenu, porsi);
        return subtotal - hitungDiskon(subtotal);
    }

    // sengaja mengubah isi rekap, dan namanya menjelaskan efek itu
    static void tambahkanKeRekap(int[] porsiHarian, int[] porsi) {
        for (int i = 0; i < porsi.length; i++) {
            porsiHarian[i] += porsi[i];
        }
    }

    // ---------- tampilan ----------

    static void tampilkanMenu(String[] namaMenu, int[] hargaMenu) {
        System.out.println("Daftar menu:");
        for (int i = 0; i < namaMenu.length; i++) {
            System.out.println((i + 1) + ". " + namaMenu[i] + " (Rp" + hargaMenu[i] + ")");
        }
    }

    static void cetakStruk(String namaPembeli, String[] namaMenu, int[] hargaMenu, int[] porsi,
                           int subtotal, int diskon, int total) {
        System.out.println();
        System.out.println("Pesanan atas nama " + namaPembeli);
        for (int i = 0; i < namaMenu.length; i++) {
            if (porsi[i] > 0) {
                int hargaBaris = hargaMenu[i] * porsi[i];
                System.out.println(String.format("%-14s%3s%11s", namaMenu[i], "x" + porsi[i], "Rp" + hargaBaris));
            }
        }
        cetakGaris(28);
        cetakBaris("Subtotal", subtotal);
        cetakBaris("Diskon", diskon);
        cetakBaris("TOTAL", total);
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

    static void cetakRekapHarian(String[] namaMenu, int[] porsiHarian, int jumlahPembeli, int omzet) {
        System.out.println();
        System.out.println("REKAP HARI INI");
        cetakGaris(28);
        for (int i = 0; i < namaMenu.length; i++) {
            System.out.println(String.format("%-14s%14s", namaMenu[i], porsiHarian[i] + " porsi"));
        }
        cetakGaris(28);
        System.out.println(String.format("%-14s%14s", "Pembeli", jumlahPembeli + " orang"));
        cetakBaris("Omzet", omzet);

        int terlaris = 0;
        for (int i = 1; i < porsiHarian.length; i++) {
            if (porsiHarian[i] > porsiHarian[terlaris]) {
                terlaris = i;
            }
        }
        if (porsiHarian[terlaris] > 0) {
            System.out.println("Menu terlaris: " + namaMenu[terlaris]);
        }
    }

    static void cetakGaris(int panjang) {
        System.out.println("-".repeat(panjang));
    }

    static void cetakBaris(String label, int nominal) {
        System.out.println(String.format("%-17s%11s", label, "Rp" + nominal));
    }

    static int bacaAngka(Scanner input) {
        while (!input.hasNextInt()) {
            String salah = input.next();          // buang input yang bukan angka
            System.out.print("\"" + salah + "\" bukan angka, coba lagi: ");
        }
        return input.nextInt();
    }
}
