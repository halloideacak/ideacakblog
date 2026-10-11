public class WartegCanggih {
    public static void main(String[] args) {
        final String NAMA_WARTEG = "Warteg Canggih";

        // satu meja, banyak pesanan: tiap angka = nomor menu, x = selesai
        String pesanan = "12253x1";
        int uangPembeli = 50000;

        System.out.println("Selamat datang di " + NAMA_WARTEG + "!");
        System.out.println("Daftar menu:");
        for (int nomor = 1; nomor <= 3; nomor++) {
            String namaMenu;
            int harga;
            switch (nomor) {
                case 1 -> { namaMenu = "Nasi putih"; harga = 5000; }
                case 2 -> { namaMenu = "Tempe orek"; harga = 4000; }
                default -> { namaMenu = "Es teh"; harga = 3000; }
            }
            System.out.println(nomor + ". " + namaMenu + " (Rp" + harga + ")");
        }
        System.out.println();

        int subtotal = 0;
        int jumlahItem = 0;
        for (int i = 0; i < pesanan.length(); i++) {
            char kode = pesanan.charAt(i);

            if (kode == 'x') {
                break;                    // tanda pesanan selesai
            }

            String namaMenu;
            int harga;
            switch (kode) {
                case '1' -> { namaMenu = "Nasi putih"; harga = 5000; }
                case '2' -> { namaMenu = "Tempe orek"; harga = 4000; }
                case '3' -> { namaMenu = "Es teh"; harga = 3000; }
                default -> { namaMenu = ""; harga = 0; }
            }

            if (harga == 0) {
                System.out.println("Menu nomor " + kode + " tidak ada, dilewati");
                continue;                 // lanjut ke pesanan berikutnya
            }

            System.out.println(String.format("%-14s%10s", namaMenu, "Rp" + harga));
            subtotal += harga;
            jumlahItem++;
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

        System.out.println("-".repeat(24));
        System.out.println(String.format("%-14s%10s", "Item", jumlahItem));
        System.out.println(String.format("%-14s%10s", "Subtotal", "Rp" + subtotal));
        System.out.println(String.format("%-14s%10s", "Diskon", "Rp" + diskon));
        System.out.println(String.format("%-14s%10s", "TOTAL", "Rp" + total));

        if (uangPembeli < total) {
            System.out.println("Uang kurang Rp" + (total - uangPembeli));
        } else {
            int sisa = uangPembeli - total;
            System.out.println("Kembalian: Rp" + sisa);
            while (sisa > 0) {
                int lembar;
                if (sisa >= 10000) {
                    lembar = 10000;
                } else if (sisa >= 5000) {
                    lembar = 5000;
                } else if (sisa >= 2000) {
                    lembar = 2000;
                } else if (sisa >= 1000) {
                    lembar = 1000;
                } else {
                    lembar = sisa;        // sisa receh
                }
                System.out.println("  Rp" + lembar);
                sisa -= lembar;
            }
        }
    }
}
