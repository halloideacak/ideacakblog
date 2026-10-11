import java.util.Arrays;

public class WartegCanggih {
    public static void main(String[] args) {
        final String NAMA_WARTEG = "Warteg Canggih";

        String[] namaMenu = {"Nasi putih", "Tempe orek", "Es teh", "Telur balado", "Sayur asem"};
        int[] hargaMenu = {5000, 4000, 3000, 6000, 4000};

        int[] pesanan = {1, 2, 2, 4, 9, 3};   // nomor menu satu meja
        int uangPembeli = 38000;

        System.out.println("Selamat datang di " + NAMA_WARTEG + "!");
        tampilkanMenu(namaMenu, hargaMenu);
        System.out.println();
        System.out.println("Pesanan meja: " + Arrays.toString(pesanan));

        int[] porsi = rekapPorsi(pesanan, namaMenu.length);
        System.out.println();

        cetakRincian(namaMenu, hargaMenu, porsi);
        int subtotal = hitungSubtotal(hargaMenu, porsi);
        int diskon = hitungDiskon(subtotal);
        int total = subtotal - diskon;

        cetakGaris(28);
        cetakBaris("Subtotal", subtotal);
        cetakBaris("Diskon", diskon);
        cetakBaris("TOTAL", total);

        cetakKembalian(uangPembeli, total);
    }

    static void tampilkanMenu(String[] namaMenu, int[] hargaMenu) {
        System.out.println("Daftar menu:");
        for (int i = 0; i < namaMenu.length; i++) {
            System.out.println((i + 1) + ". " + namaMenu[i] + " (Rp" + hargaMenu[i] + ")");
        }
    }

    static int[] rekapPorsi(int[] pesanan, int jumlahMenu) {
        int[] porsi = new int[jumlahMenu];
        for (int nomor : pesanan) {
            int index = nomor - 1;
            if (index < 0 || index >= jumlahMenu) {
                System.out.println("Menu nomor " + nomor + " tidak ada, dilewati");
                continue;
            }
            porsi[index]++;
        }
        return porsi;
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

    static void cetakKembalian(int uangPembeli, int total) {
        if (uangPembeli < total) {
            System.out.println("Uang kurang Rp" + (total - uangPembeli));
            return;
        }
        int sisa = uangPembeli - total;
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
