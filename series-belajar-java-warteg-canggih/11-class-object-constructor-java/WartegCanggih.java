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

// satu object Menu = satu baris di papan menu: nama dan harga selalu satu paket
class Menu {
    String nama;
    int harga;

    Menu(String nama, int harga) {
        this.nama = nama;
        this.harga = harga;
    }

    int hitungHarga(int porsi) {
        return harga * porsi;
    }

    void tampilkan(int nomor) {
        System.out.println(nomor + ". " + nama + " (Rp" + harga + ")");
    }
}

// data yang harus bertahan sepanjang hari sekarang jadi field milik Kasir
class Kasir {
    String namaWarteg;
    Menu[] daftarMenu;
    Scanner input;
    int[] porsiHarian;
    int omzet;
    int jumlahPembeli;

    Kasir(String namaWarteg, Menu[] daftarMenu, Scanner input) {
        this.namaWarteg = namaWarteg;
        this.daftarMenu = daftarMenu;
        this.input = input;
        this.porsiHarian = new int[daftarMenu.length];
    }

    void buka() {
        System.out.println("=".repeat(28));
        System.out.println("  KASIR " + namaWarteg.toUpperCase());
        System.out.println("=".repeat(28));

        int pilihan;
        do {
            System.out.println();
            System.out.println("1. Layani pembeli");
            System.out.println("2. Lihat daftar menu");
            System.out.println("0. Tutup kasir");
            System.out.print("Pilih: ");
            pilihan = bacaAngka();

            switch (pilihan) {
                case 1 -> layaniPembeli();
                case 2 -> tampilkanMenu();
                case 0 -> System.out.println("Kasir ditutup.");
                default -> System.out.println("Pilihan " + pilihan + " tidak ada.");
            }
        } while (pilihan != 0);

        cetakRekapHarian();
    }

    // ---------- melayani satu pembeli ----------

    void layaniPembeli() {
        input.nextLine();                         // buang sisa Enter dari "Pilih"
        System.out.print("Atas nama siapa? ");
        String namaPembeli = input.nextLine().trim();
        if (namaPembeli.isEmpty()) {
            namaPembeli = "Pembeli";
        }

        tampilkanMenu();
        int[] porsi = ambilPesanan();

        int subtotal = hitungSubtotal(porsi);
        if (subtotal == 0) {
            System.out.println("Tidak ada pesanan untuk " + namaPembeli + ".");
            return;
        }
        int diskon = hitungDiskon(subtotal);
        int total = subtotal - diskon;

        cetakStruk(namaPembeli, porsi, subtotal, diskon, total);
        terimaPembayaran(total);
        catatKeRekap(porsi, total);
    }

    int[] ambilPesanan() {
        int[] porsi = new int[daftarMenu.length];
        int pilihan;
        do {
            System.out.print("Nomor menu (0 = selesai): ");
            pilihan = bacaAngka();
            if (pilihan == 0) {
                continue;
            }
            int index = pilihan - 1;
            if (index < 0 || index >= daftarMenu.length) {
                System.out.println("Menu nomor " + pilihan + " tidak ada.");
                continue;
            }
            System.out.print("Berapa porsi? ");
            int jumlah = bacaAngka();
            if (jumlah <= 0) {
                System.out.println("Jumlah porsi minimal 1.");
                continue;
            }
            porsi[index] += jumlah;
            System.out.println("Dicatat: " + daftarMenu[index].nama + " x" + jumlah);
        } while (pilihan != 0);
        return porsi;
    }

    void terimaPembayaran(int total) {
        System.out.print("Uang pembeli: ");
        int uangPembeli = bacaAngka();
        while (uangPembeli < total) {
            System.out.print("Uang kurang Rp" + (total - uangPembeli) + ". Masukkan ulang: ");
            uangPembeli = bacaAngka();
        }
        cetakKembalian(uangPembeli - total);
    }

    void catatKeRekap(int[] porsi, int total) {
        omzet += total;
        jumlahPembeli++;
        for (int i = 0; i < porsi.length; i++) {
            porsiHarian[i] += porsi[i];
        }
    }

    // ---------- perhitungan ----------

    int hitungSubtotal(int[] porsi) {
        int subtotal = 0;
        for (int i = 0; i < daftarMenu.length; i++) {
            subtotal += daftarMenu[i].hitungHarga(porsi[i]);
        }
        return subtotal;
    }

    // tidak memakai field apa pun, jadi tetap static
    static int hitungDiskon(int subtotal) {
        if (subtotal >= 20000) {
            return 2000;
        }
        if (subtotal >= 10000) {
            return 1000;
        }
        return 0;
    }

    // ---------- tampilan ----------

    void tampilkanMenu() {
        System.out.println("Daftar menu:");
        for (int i = 0; i < daftarMenu.length; i++) {
            daftarMenu[i].tampilkan(i + 1);
        }
    }

    void cetakStruk(String namaPembeli, int[] porsi, int subtotal, int diskon, int total) {
        System.out.println();
        System.out.println("Pesanan atas nama " + namaPembeli);
        for (int i = 0; i < daftarMenu.length; i++) {
            if (porsi[i] > 0) {
                Menu menu = daftarMenu[i];
                int hargaBaris = menu.hitungHarga(porsi[i]);
                System.out.println(String.format("%-14s%3s%11s", menu.nama, "x" + porsi[i], "Rp" + hargaBaris));
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

    void cetakRekapHarian() {
        System.out.println();
        System.out.println("REKAP HARI INI");
        cetakGaris(28);
        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.println(String.format("%-14s%14s", daftarMenu[i].nama, porsiHarian[i] + " porsi"));
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
            System.out.println("Menu terlaris: " + daftarMenu[terlaris].nama);
        }
    }

    static void cetakGaris(int panjang) {
        System.out.println("-".repeat(panjang));
    }

    static void cetakBaris(String label, int nominal) {
        System.out.println(String.format("%-17s%11s", label, "Rp" + nominal));
    }

    int bacaAngka() {
        while (!input.hasNextInt()) {
            String salah = input.next();          // buang input yang bukan angka
            System.out.print("\"" + salah + "\" bukan angka, coba lagi: ");
        }
        return input.nextInt();
    }
}
