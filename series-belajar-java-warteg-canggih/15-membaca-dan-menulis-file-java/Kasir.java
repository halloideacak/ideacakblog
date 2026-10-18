import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;

class Kasir {
    String namaWarteg;
    Menu[] daftarMenu;
    Scanner input;
    ArsipWarteg arsip;
    int[] porsiHarian;
    int omzet;
    int jumlahPembeli;
    Queue<String> antrian = new ArrayDeque<>();   // FIFO: yang datang duluan, dilayani duluan

    static final int PORSI_MAKSIMAL = 20;

    Kasir(String namaWarteg, Menu[] daftarMenu, Scanner input, ArsipWarteg arsip) {
        this.namaWarteg = namaWarteg;
        this.daftarMenu = daftarMenu;
        this.input = input;
        this.arsip = arsip;
        this.porsiHarian = new int[daftarMenu.length];
    }

    void buka() {
        System.out.println("=".repeat(28));
        System.out.println("  KASIR " + namaWarteg.toUpperCase());
        System.out.println("=".repeat(28));

        try {
            int pilihan;
            do {
                System.out.println();
                System.out.println("1. Tambah antrian");
                System.out.println("2. Layani antrian berikutnya");
                System.out.println("3. Lihat antrian");
                System.out.println("4. Lihat daftar menu");
                System.out.println("0. Tutup kasir");
                System.out.print("Pilih: ");
                pilihan = bacaAngka();

                switch (pilihan) {
                    case 1 -> tambahAntrian();
                    case 2 -> layaniAntrianBerikutnya();
                    case 3 -> tampilkanAntrian();
                    case 4 -> tampilkanMenu();
                    case 0 -> {
                        if (!antrian.isEmpty()) {
                            System.out.println("Masih ada " + antrian.size() + " pembeli mengantre. Layani dulu.");
                            pilihan = -1;         // batal tutup, kembali ke menu utama
                        } else {
                            System.out.println("Kasir ditutup.");
                        }
                    }
                    default -> System.out.println("Pilihan " + pilihan + " tidak ada.");
                }
            } while (pilihan != 0);
        } catch (NoSuchElementException e) {
            // terjadi kalau input berhenti mendadak, misalnya Ctrl+D atau file input habis
            System.out.println();
            System.out.println("Input terhenti, kasir ditutup paksa.");
        } finally {
            cetakRekapHarian();                   // apa pun yang terjadi, rekap tetap dicetak
            simpanAkhirHari();
        }
    }

    void simpanAkhirHari() {
        try {
            arsip.simpanMenu(daftarMenu);
            arsip.tambahRekap(jumlahPembeli, omzet);
            System.out.println("Sisa stok dan rekap hari ini tersimpan di folder data.");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data akhir hari: " + e.getMessage());
        }
    }

    // ---------- antrian (Queue) ----------

    void tambahAntrian() {
        System.out.print("Atas nama siapa? ");
        String nama = input.nextLine().trim();
        if (nama.isEmpty()) {
            nama = "Pembeli";
        }
        antrian.offer(nama);
        System.out.println(nama + " masuk antrian ke-" + antrian.size() + ".");
    }

    void tampilkanAntrian() {
        if (antrian.isEmpty()) {
            System.out.println("Antrian kosong.");
            return;
        }
        System.out.println("Antrian saat ini:");
        int nomor = 1;
        for (String nama : antrian) {             // urut dari yang paling depan
            System.out.println(nomor + ". " + nama);
            nomor++;
        }
    }

    void layaniAntrianBerikutnya() {
        String namaPembeli = antrian.poll();      // null kalau antrian kosong
        if (namaPembeli == null) {
            System.out.println("Antrian kosong, belum ada yang bisa dilayani.");
            return;
        }
        System.out.println("Melayani " + namaPembeli + ".");

        tampilkanMenu();
        int[] porsi = ambilPesanan();

        int subtotal = hitungSubtotal(porsi);
        if (subtotal == 0) {
            System.out.println("Tidak ada pesanan untuk " + namaPembeli + ".");
            return;
        }
        int diskon = hitungDiskon(subtotal);
        int total = subtotal - diskon;

        String struk = buatStruk(namaPembeli, porsi, subtotal, diskon, total);
        System.out.print(struk);
        terimaPembayaran(total);
        catatKeRekap(porsi, total);

        try {
            arsip.tambahStruk(struk);
        } catch (IOException e) {
            // transaksi tetap sah walaupun arsipnya gagal ditulis
            System.out.println("Struk gagal disimpan: " + e.getMessage());
        }
    }

    // ---------- pesanan (Stack) ----------

    int[] ambilPesanan() {
        Deque<ItemPesanan> pesanan = new ArrayDeque<>();   // LIFO: yang terakhir masuk, dibatalkan duluan
        int pilihan;
        do {
            System.out.print("Nomor menu (0 = selesai, -1 = batalkan terakhir): ");
            pilihan = bacaAngka();
            if (pilihan == 0) {
                continue;
            }
            if (pilihan == -1) {
                batalkanTerakhir(pesanan);
                continue;
            }
            int index = pilihan - 1;
            if (index < 0 || index >= daftarMenu.length) {
                System.out.println("Menu nomor " + pilihan + " tidak ada.");
                continue;
            }
            System.out.print("Berapa porsi? ");
            int jumlah = bacaAngka();
            try {
                tambahPesanan(pesanan, index, jumlah);
                System.out.println("Dicatat: " + daftarMenu[index].getNama() + " x" + jumlah);
            } catch (StokTidakCukupException e) {
                System.out.println("Ditolak: " + e.getMessage());
                if (e.getSisaStok() > 0) {
                    System.out.println("Tawarkan " + e.getSisaStok() + " porsi saja, atau menu lain.");
                }
            } catch (PesananTidakValidException e) {
                System.out.println("Ditolak: " + e.getMessage());
            }
        } while (pilihan != 0);

        // ringkas isi stack jadi jumlah porsi per menu, untuk struk dan rekap
        int[] porsi = new int[daftarMenu.length];
        for (ItemPesanan item : pesanan) {
            porsi[item.indexMenu] += item.porsi;
        }
        return porsi;
    }

    // semua aturan bisnis pesanan dicek di sini; yang melanggar dilempar sebagai exception
    void tambahPesanan(Deque<ItemPesanan> pesanan, int index, int jumlah) throws PesananTidakValidException {
        if (jumlah < 1 || jumlah > PORSI_MAKSIMAL) {
            throw new PesananTidakValidException("Jumlah porsi harus 1 sampai " + PORSI_MAKSIMAL + ".");
        }
        daftarMenu[index].kurangiStok(jumlah);    // bisa melempar StokTidakCukupException
        pesanan.push(new ItemPesanan(index, jumlah));
    }

    void batalkanTerakhir(Deque<ItemPesanan> pesanan) {
        if (pesanan.isEmpty()) {
            System.out.println("Belum ada pesanan yang bisa dibatalkan.");
            return;
        }
        ItemPesanan batal = pesanan.pop();
        Menu menu = daftarMenu[batal.indexMenu];
        menu.kembalikanStok(batal.porsi);         // stok yang sudah dipesan dikembalikan
        System.out.println("Dibatalkan: " + menu.getNama() + " x" + batal.porsi);
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

    // struk dirakit jadi satu String, supaya bisa dicetak ke layar dan disimpan ke file
    String buatStruk(String namaPembeli, int[] porsi, int subtotal, int diskon, int total) {
        String struk = "\nPesanan atas nama " + namaPembeli + "\n";
        for (int i = 0; i < daftarMenu.length; i++) {
            if (porsi[i] > 0) {
                Menu menu = daftarMenu[i];
                int hargaBaris = menu.hitungHarga(porsi[i]);
                struk += String.format("%-14s%3s%11s\n", menu.getNama(), "x" + porsi[i], "Rp" + hargaBaris);
            }
        }
        struk += "-".repeat(28) + "\n";
        struk += String.format("%-17s%11s\n", "Subtotal", "Rp" + subtotal);
        struk += String.format("%-17s%11s\n", "Diskon", "Rp" + diskon);
        struk += String.format("%-17s%11s\n", "TOTAL", "Rp" + total);
        return struk;
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
            System.out.println(String.format("%-14s%14s", daftarMenu[i].getNama(), porsiHarian[i] + " porsi"));
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
            System.out.println("Menu terlaris: " + daftarMenu[terlaris].getNama());
        }
    }

    static void cetakGaris(int panjang) {
        System.out.println("-".repeat(panjang));
    }

    static void cetakBaris(String label, int nominal) {
        System.out.println(String.format("%-17s%11s", label, "Rp" + nominal));
    }

    // selalu membaca satu baris penuh, jadi tidak ada lagi sisa Enter yang tertinggal
    int bacaAngka() {
        while (true) {
            String teks = input.nextLine().trim();
            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.print("\"" + teks + "\" bukan angka, coba lagi: ");
            }
        }
    }
}
