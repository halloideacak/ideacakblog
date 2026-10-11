import java.util.Arrays;

public class WartegCanggih {
    public static void main(String[] args) {
        final String NAMA_WARTEG = "Warteg Canggih";

        // daftar menu cukup ditulis sekali, index yang sama = menu yang sama
        String[] namaMenu = {"Nasi putih", "Tempe orek", "Es teh", "Telur balado", "Sayur asem"};
        int[] hargaMenu = {5000, 4000, 3000, 6000, 4000};
        int[] pecahan = {10000, 5000, 2000, 1000};

        int[] pesanan = {1, 2, 2, 4, 9, 3};   // nomor menu satu meja
        int uangPembeli = 38000;

        System.out.println("Selamat datang di " + NAMA_WARTEG + "!");
        System.out.println("Daftar menu:");
        for (int i = 0; i < namaMenu.length; i++) {
            System.out.println((i + 1) + ". " + namaMenu[i] + " (Rp" + hargaMenu[i] + ")");
        }
        System.out.println();
        System.out.println("Pesanan meja: " + Arrays.toString(pesanan));

        // rekap: berapa porsi untuk tiap menu, isi awalnya otomatis 0
        int[] porsi = new int[namaMenu.length];
        for (int nomor : pesanan) {
            int index = nomor - 1;
            if (index < 0 || index >= namaMenu.length) {
                System.out.println("Menu nomor " + nomor + " tidak ada, dilewati");
                continue;
            }
            porsi[index]++;
        }
        System.out.println();

        int subtotal = 0;
        for (int i = 0; i < namaMenu.length; i++) {
            if (porsi[i] == 0) {
                continue;
            }
            int hargaBaris = hargaMenu[i] * porsi[i];
            System.out.println(String.format("%-14s%3s%11s", namaMenu[i], "x" + porsi[i], "Rp" + hargaBaris));
            subtotal += hargaBaris;
        }

        int diskon;
        if (subtotal >= 20000) {
            diskon = 2000;
        } else if (subtotal >= 10000) {
            diskon = 1000;
        } else {
            diskon = 0;
        }
        int total = subtotal - diskon;

        System.out.println("-".repeat(28));
        System.out.println(String.format("%-17s%11s", "Subtotal", "Rp" + subtotal));
        System.out.println(String.format("%-17s%11s", "Diskon", "Rp" + diskon));
        System.out.println(String.format("%-17s%11s", "TOTAL", "Rp" + total));

        if (uangPembeli < total) {
            System.out.println("Uang kurang Rp" + (total - uangPembeli));
        } else {
            int sisa = uangPembeli - total;
            System.out.println("Kembalian: Rp" + sisa);
            for (int lembar : pecahan) {
                while (sisa >= lembar) {
                    System.out.println("  Rp" + lembar);
                    sisa -= lembar;
                }
            }
        }
    }
}
