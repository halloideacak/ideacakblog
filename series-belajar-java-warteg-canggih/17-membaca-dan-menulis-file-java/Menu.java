// field dibuat private: dari luar class, stok hanya bisa diubah lewat method yang menjaga aturannya
class Menu {
    private final String nama;
    private final int harga;
    private int stok;

    Menu(String nama, int harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    String getNama() {
        return nama;
    }

    int getHarga() {
        return harga;
    }

    int getStok() {
        return stok;
    }

    int hitungHarga(int porsi) {
        return harga * porsi;
    }

    void kurangiStok(int porsi) throws StokTidakCukupException {
        if (porsi > stok) {
            throw new StokTidakCukupException(nama, porsi, stok);
        }
        stok -= porsi;
    }

    // dipakai saat pesanan dibatalkan
    void kembalikanStok(int porsi) {
        stok += porsi;
    }

    void tampilkan(int nomor) {
        String keterangan = stok == 0 ? "habis" : "sisa " + stok;
        System.out.println(nomor + ". " + nama + " (Rp" + harga + ", " + keterangan + ")");
    }
}
