import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

class Kasir {
    private final String namaWarteg;
    private final List<Menu> daftarMenu;            // bisa bertambah saat kasir buka
    private final AturanDiskon aturanDiskon;
    private final Scanner input;
    private final Map<String, Integer> porsiHarian = new LinkedHashMap<>();   // nama menu -> porsi terjual, urut sesuai menu
    private int omzet;
    private int jumlahPembeli;
    private final Queue<String> antrian = new ArrayDeque<>();   // FIFO: yang datang duluan, dilayani duluan

    Kasir(String namaWarteg, List<Menu> daftarMenu, AturanDiskon aturanDiskon, Scanner input) {
        this.namaWarteg = namaWarteg;
        this.daftarMenu = daftarMenu;
        this.aturanDiskon = aturanDiskon;
        this.input = input;
        for (Menu menu : daftarMenu) {
            porsiHarian.put(menu.getNama(), 0);
        }
    }

    void buka() {
        System.out.println("=".repeat(28));
        System.out.println("  KASIR " + namaWarteg.toUpperCase());
        System.out.println("=".repeat(28));

        int pilihan;
        do {
            System.out.println();
            System.out.println("1. Tambah antrian");
            System.out.println("2. Layani antrian berikutnya");
            System.out.println("3. Lihat antrian");
            System.out.println("4. Lihat daftar menu");
            System.out.println("5. Tambah menu");
            System.out.println("0. Tutup kasir");
            System.out.print("Pilih: ");
            pilihan = bacaAngka();

            switch (pilihan) {
                case 1 -> tambahAntrian();
                case 2 -> layaniAntrianBerikutnya();
                case 3 -> tampilkanAntrian();
                case 4 -> tampilkanMenu();
                case 5 -> tambahMenu();
                case 0 -> {
                    if (!antrian.isEmpty()) {
                        System.out.println("Masih ada " + antrian.size() + " pembeli mengantre. Layani dulu.");
                        pilihan = -1;             // batal tutup, kembali ke menu utama
                    } else {
                        System.out.println("Kasir ditutup.");
                    }
                }
                default -> System.out.println("Pilihan " + pilihan + " tidak ada.");
            }
        } while (pilihan != 0);

        cetakRekapHarian();
    }

    // ---------- antrian (Queue) ----------

    void tambahAntrian() {
        input.nextLine();                         // buang sisa Enter dari "Pilih"
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
        Map<Menu, Integer> porsi = ambilPesanan();

        int subtotal = hitungSubtotal(porsi);
        if (subtotal == 0) {
            System.out.println("Tidak ada pesanan untuk " + namaPembeli + ".");
            return;
        }
        int diskon = aturanDiskon.hitung(subtotal);
        int total = subtotal - diskon;

        cetakStruk(namaPembeli, porsi, subtotal, diskon, total);
        terimaPembayaran(total);
        catatKeRekap(porsi, total);
    }

    // ---------- menu (List) ----------

    void tambahMenu() {
        input.nextLine();                         // buang sisa Enter dari "Pilih"
        System.out.print("Nama menu baru: ");
        String nama = input.nextLine().trim();
        if (nama.isEmpty() || porsiHarian.containsKey(nama)) {
            System.out.println("Nama menu kosong atau sudah ada.");
            return;
        }
        System.out.print("Harga: ");
        int harga = bacaAngka();
        if (harga <= 0) {
            System.out.println("Harga harus lebih dari 0.");
            return;
        }
        daftarMenu.add(new Menu(nama, harga));    // List bertambah panjang, array tidak bisa
        porsiHarian.put(nama, 0);
        System.out.println(nama + " ditambahkan sebagai menu nomor " + daftarMenu.size() + ".");
    }

    // ---------- pesanan (List) ----------

    Map<Menu, Integer> ambilPesanan() {
        List<ItemPesanan> pesanan = new ArrayList<>();   // bisa dibatalkan dari mana saja, bukan cuma yang terakhir
        int pilihan;
        do {
            System.out.print("Nomor menu (0 = selesai, -1 = batalkan pesanan): ");
            pilihan = bacaAngka();
            if (pilihan == 0) {
                continue;
            }
            if (pilihan == -1) {
                batalkanPesanan(pesanan);
                continue;
            }
            int index = pilihan - 1;
            if (index < 0 || index >= daftarMenu.size()) {
                System.out.println("Menu nomor " + pilihan + " tidak ada.");
                continue;
            }
            System.out.print("Berapa porsi? ");
            int jumlah = bacaAngka();
            if (jumlah <= 0) {
                System.out.println("Jumlah porsi minimal 1.");
                continue;
            }
            Menu menu = daftarMenu.get(index);
            pesanan.add(new ItemPesanan(menu, jumlah));
            System.out.println("Dicatat: " + menu.getNama() + " x" + jumlah);
        } while (pilihan != 0);

        // ringkas daftar pesanan jadi jumlah porsi per menu, urut sesuai pesanan pertama
        Map<Menu, Integer> porsi = new LinkedHashMap<>();
        pesanan.forEach(item -> porsi.merge(item.getMenu(), item.getPorsi(), Integer::sum));
        return porsi;
    }

    void batalkanPesanan(List<ItemPesanan> pesanan) {
        if (pesanan.isEmpty()) {
            System.out.println("Belum ada pesanan yang bisa dibatalkan.");
            return;
        }
        System.out.println("Pesanan sejauh ini:");
        for (int i = 0; i < pesanan.size(); i++) {
            ItemPesanan item = pesanan.get(i);
            System.out.println("  " + (i + 1) + ". " + item.getMenu().getNama() + " x" + item.getPorsi());
        }
        System.out.print("Batalkan nomor berapa? ");
        int nomor = bacaAngka();
        if (nomor < 1 || nomor > pesanan.size()) {
            System.out.println("Pesanan nomor " + nomor + " tidak ada.");
            return;
        }
        ItemPesanan batal = pesanan.remove(nomor - 1);   // sisa pesanan otomatis bergeser maju
        System.out.println("Dibatalkan: " + batal.getMenu().getNama() + " x" + batal.getPorsi());
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

    void catatKeRekap(Map<Menu, Integer> porsi, int total) {
        omzet += total;
        jumlahPembeli++;
        porsi.forEach((menu, jumlah) -> porsiHarian.merge(menu.getNama(), jumlah, Integer::sum));
    }

    // ---------- perhitungan ----------

    int hitungSubtotal(Map<Menu, Integer> porsi) {
        return porsi.entrySet().stream()
                .mapToInt(baris -> baris.getKey().hitungHarga(baris.getValue()))
                .sum();
    }

    // ---------- tampilan ----------

    void tampilkanMenu() {
        System.out.println("Daftar menu:");
        for (int i = 0; i < daftarMenu.size(); i++) {
            daftarMenu.get(i).tampilkan(i + 1);   // Menu dan MenuPaket menampilkan diri dengan caranya sendiri
        }
    }

    void cetakStruk(String namaPembeli, Map<Menu, Integer> porsi, int subtotal, int diskon, int total) {
        System.out.println();
        System.out.println("Pesanan atas nama " + namaPembeli);
        for (Map.Entry<Menu, Integer> baris : porsi.entrySet()) {
            Menu menu = baris.getKey();
            int jumlah = baris.getValue();
            int hargaBaris = menu.hitungHarga(jumlah);
            System.out.println(String.format("%-14s%3s%11s", menu.getNama(), "x" + jumlah, "Rp" + hargaBaris));
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
        porsiHarian.forEach((nama, porsi) ->
                System.out.println(String.format("%-14s%14s", nama, porsi + " porsi")));
        cetakGaris(28);

        int totalPorsi = porsiHarian.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(String.format("%-14s%14s", "Total porsi", totalPorsi + " porsi"));
        System.out.println(String.format("%-14s%14s", "Pembeli", jumlahPembeli + " orang"));
        cetakBaris("Omzet", omzet);

        // tiga menu dengan porsi terbanyak, yang tidak laku tidak ikut
        List<String> terlaris = porsiHarian.entrySet().stream()
                .filter(baris -> baris.getValue() > 0)
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(3)
                .map(baris -> baris.getKey() + " (" + baris.getValue() + ")")
                .toList();
        if (!terlaris.isEmpty()) {
            System.out.println("Terlaris: " + String.join(", ", terlaris));
        }

        List<String> belumLaku = porsiHarian.entrySet().stream()
                .filter(baris -> baris.getValue() == 0)
                .map(Map.Entry::getKey)
                .toList();
        if (!belumLaku.isEmpty()) {
            System.out.println("Belum laku: " + String.join(", ", belumLaku));
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
