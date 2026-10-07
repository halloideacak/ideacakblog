public class WartegCanggih {
    public static void main(String[] args) {
        final String NAMA_WARTEG = "Warteg Canggih";

        int pilihan = 2;          // nomor menu yang dipilih pembeli
        int jumlah = 3;           // jumlah porsi
        int uangPembeli = 20000;

        String namaMenu;
        int harga;
        switch (pilihan) {
            case 1 -> { namaMenu = "Nasi putih"; harga = 5000; }
            case 2 -> { namaMenu = "Tempe orek"; harga = 4000; }
            case 3 -> { namaMenu = "Es teh"; harga = 3000; }
            default -> { namaMenu = "Tidak ada"; harga = 0; }
        }

        System.out.println("Selamat datang di " + NAMA_WARTEG + "!");

        if (harga == 0) {
            System.out.println("Maaf, menu nomor " + pilihan + " tidak ada.");
        } else {
            int subtotal = harga * jumlah;

            int diskon;
            if (subtotal >= 20000) {
                diskon = 2000;
            } else if (subtotal >= 10000) {
                diskon = 1000;
            } else {
                diskon = 0;
            }

            int total = subtotal - diskon;

            System.out.println(namaMenu + " x" + jumlah + ": Rp" + subtotal);
            System.out.println("Diskon: Rp" + diskon);
            System.out.println("Total: Rp" + total);

            if (uangPembeli >= total) {
                System.out.println("Kembalian: Rp" + (uangPembeli - total));
            } else {
                System.out.println("Uang kurang Rp" + (total - uangPembeli));
            }
        }
    }
}
